<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">

<%@ page contentType="text/html; charset=euc-kr" pageEncoding="euc-kr"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Mentor ChatBot</title>
    <script src="/webjars/jquery/2.1.3/dist/jquery.min.js"></script>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    <style type="text/css">
        .bgcolor1{background-color:#3b5998}
        .bgcolor2{background-color:#eceff5}
        .bgcolor3{background-color:#dd3c10}
        .inputPic{padding-right:5px}.fcb{color:#000}
        .composerInput{height:30px;width:300px;margin-bottom:7px}
        .mfsxs{font-size:x-small}
        .mfsxs:hover{background-color:#3b5998;color:#fff}
    </style>

    <script type="text/javascript">
        function chat(){
            var userMessage = document.form1.userMessage.value;
            var messageHistory = document.form1.messageResult.value;
            var user = document.form1.user.value;
            var chat = {
                userMessage : userMessage,
                messageHistory : messageHistory,
                user : user
            };
            $.ajax({
                type: "POST",
                dataType: "json",
                contentType: "application/json",
                url: "chat",
                data: JSON.stringify(chat),
                success: [
                    function(result) {
                        var resultMessage = result.messageHistory + result.userMessage + result.chatBotMessage;
                        var reply = document.getElementById("messageResult");
                        reply.value = resultMessage;

                        if (result.jobCode != 0) {
                            externalApiCall(result.jobCode);
                        }

                        document.form1.messageResult.focus();
                        document.form1.userMessage.focus();
                    }
                ],
                error: [
                    function(request){
                        alert("userMessage:"+request.responseText);
                    }]

            });
            document.form1.userMessage.value = "";
        }

        function externalApiCall(code) {
            console.log("code: " + code);
            $.ajax({
                type: "POST",
                dataType: "json",
                contentType: "application/json",
                url: "jobInfo",
                data: JSON.stringify(code),
                success: [
                    function(result) {
                        var apiResult = document.getElementById("apiResult");
                        apiResult.value += result;
                        console.log(result);

                        $("#list_table_json tbody tr").remove();

                        var event_data = "";
                        $.each(result, function(index, value) {
                            event_data += '<tr>';
                            event_data += '<td>'+value.job+'</td>';
                            event_data += '<td>'+value.equalEmployment+'</td>';
                            event_data += '<td>'+value.possibility+'</td>';
                            event_data += '<td>'+value.profession+'</td>';
                            event_data += '<td>'+value.prospect+'</td>';
                            event_data += '<td>'+value.salary+'</td>';
                            event_data += '<td>'+value.similarJob+'</td>';
                            event_data += '<td>'+value.summary+'</td>';
                            event_data += '</tr>';
                        });
                        $("#list_table_json").append(event_data);
                    }
                ],
                error: [
                    function(request){
                        alert("userMessage:"+request.responseText);
                    }
                ]
            });
        }
    </script>
</head>

<body tabindex="0">
<div class="bgcolor1">
    <table cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td valign="top"><font color="#FFFFFF">Mentor ChatBot</font><p></td>
        </tr>
    </table>
</div>
<form name="form1" method="post" action="javascript:chat()" enctype="mutipart/form-data">
    <input type="hidden" name="hidden1"></input>
    <div class="bgcolor2">
        <table cellspacing="0" cellpadding="0" class="comboInput">
            <tr>
                <td class="inputPic">User : </td>
                <td class="inputPic">
                    <input type="text" class="inputPic composerInput" id="composerInput2" name="user" data-sigil="textarea"><br>
                </td>
            </tr>
            <tr>
                <td class="inputPic">Message : </td>
                <td class="inputPic">
                    <input type="text" class="inputPic composerInput" id="composerInput1" name="userMessage" data-sigil="textarea"><br>
                </td>
            </tr>
            <tr>
                <td class="inputPic">&nbsp;</td>
                <td class="inputPic">
                    <input value="Send Message" type="submit" name="Send">
                </td>
            </tr>
            <tr>
                <td class="inputPic">Chat :</td>
                <td>
                    <span></span><br>
                    <textarea id="messageResult" name="messageResult" height="2000" rows="20" cols="45" data-sigil="textarea">
						</textarea>
                </td>
            </tr>
        </table>
        <hr>
        <textarea id="apiResult" name="apiResult" height="2000"></textarea>
    </div>
</form>

<table class="table" id="list_table_json">
    <thead>
    <tr>
        <th>직업명</th>
        <th>고용평등</th>
        <th>발전가능</th>
        <th>직업분야</th>
        <th>전망</th>
        <th>연봉</th>
        <th>직업상세정보</th>
    </tr>
    </thead>
</table>

</body>
</html>

