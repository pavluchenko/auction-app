$(document).ready(function () {
    $('.js-example-basic-single').select2({
        placeholder: "Select a state",
        ajax: {
            url: "rest/search/",
            dataType: 'json',
            delay: 250,
            data: function (params) {
                return {
                    name: params.term // search term
                };
            },
            processResults: function (data, params) {
                params.page = params.page || 1;

                return {
                    results: data,
                    pagination: {
                        more: (params.page * 30) < data.total_count
                    }
                };
            },
            cache: true
        },
        escapeMarkup: function (markup) {
            return markup;
        }, // let our custom formatter work
        minimumInputLength: 1,
        templateResult: formatRepo,
        templateSelection: formatRepoSelection
    });
    $('.js-example-basic-single').on('select2:select', function (e) {
        var data = e.params.data;
        console.log(data);
        document.location.href = document.location.origin + "/lots/1/" + data.name;

    });
    function formatRepo(repo) {

        var markup = "<div class='select2-result-repository clearfix'>" +
//                    "<div class='select2-result-repository__avatar'><img src='" + repo.owner.avatar_url + "' /></div>" +
            "<div class='select2-result-repository__meta'>" +
            "<div class='select2-result-repository__title'>" + repo.name + "</div>";

        if (repo.description) {
            markup += "<div class='select2-result-repository__description'>" + repo.description + "</div>";
        }

        return markup;
    }

    function formatRepoSelection(repo) {
        return repo.description || repo.name;
    }
});
