<%--
  Created by IntelliJ IDEA.
  User: Helga
  Date: 13.12.2017
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="${contextPath}/resources/js/jquery.min.js"></script>

<button type="button" class="button-btn small-btn green-bg" data-toggle="modal" data-target="#exampleModal"
        id="countMessages" style="margin-top: 20px; margin-bottom: 20px;">
    Messages
</button>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Messages</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <table id="conversation" class="table table-striped">
                    <tbody id="greetings">
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal" id="delete">Delete</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<script src="${contextPath}/resources/js/websocket.js"></script>
