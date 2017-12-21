var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    //$("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/auction-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            var json = JSON.parse(greeting.body);
            if (json.statusCodeValue == 200) {
                var data = json.body;
                showGreeting(data.rateMessage);
                changeLastPrice(data.price);
            }
        });
    });
}

function changeLastPrice(price) {
    $("#ratePrice").text(price);
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({
        'price': $("#price").val(),
        'lotId': $("#price").attr("data-id"),
        'userEmail': $("#price").attr("data-item")
    }));
}

function showGreeting(message) {

    saveMessage(message);

    $("#greetings").append("<tr><td>" + message + "</td></tr>");

}

function saveMessage(message) {
    var messages = localStorage.getItem('messages');
    if (messages == null) {
        messages = [];
    } else {
        messages = JSON.parse(messages);
    }
    messages.push(message);

    if (messages.length > 5) {
        messages = messages.slice(0, 5);
    }

    localStorage.setItem('messages', JSON.stringify(messages));
}

$(function () {
    $("#rateForm").on('submit', function (e) {
        e.preventDefault();
    });
    console.log($("#connect"))
    $("#connect").ready(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendName();
    });

    var messages = localStorage.getItem('messages');
    if (messages == null) {
        messages = [];
    } else {
        messages = JSON.parse(messages);
    }
    var count = 0;
    for (var key in messages) {
        ++count;
        $("#greetings").append("<tr><td>" + messages[key] + "</td></tr>");
    }
    $("#countMessages").append(" " + count);

    $("#delete").click(function () {
        $("#greetings").html("");
        localStorage.removeItem('messages');
        count = 0;
    });
});
