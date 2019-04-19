<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">

<%@ page contentType="text/html; charset=euc-kr" pageEncoding="euc-kr"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Mentor ChatBot</title>
    <script src="/webjars/jquery/2.1.3/dist/jquery.min.js"></script>

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
            var message = document.form1.message.value;
            var messageHistory = document.form1.messageResult.value;
            var user = document.form1.user.value;
            var chat = {
                message : message,
                messageHistory : messageHistory,
                user : user
            };
            $.ajax({
                type: "POST",
                dataType: "text",
                contentType: "application/json",
                url: "control",
                data: JSON.stringify(chat),
                success: [
                    function(result) {
                        console.log(result);
                        var reply = document.getElementById("messageResult");
                        reply.value = result;
                        document.form1.messageResult.focus();
                        document.form1.message.focus();
                    }
                ],
                error: [
                    function(request,status,error){
                        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                    }]

            });
            document.form1.message.value = "";
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
                    <input type="text" class="inputPic composerInput" id="composerInput1" name="message" data-sigil="textarea"><br>
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
    </div>
</form>
</body>
</html>

