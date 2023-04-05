$("#NameSuccess").hide();
$("#NameError").hide();
$("#EmailSuccess").hide();
$("#EmailError").hide();

function blank() {
    let username = document.getElementById("username");
    let password = document.getElementById("password");
    let nickname = document.getElementById("nickname");
    let email = document.getElementById("email");


    if (username.value == "") {
        username.focus();
        $("#NameBlankError").html("입력해주세요");
        return false;
    } else {
        $("#NameBlankError").hide();
    }
    ;
    console.log("확인1")
//일단 조건 없이
    var pwdCheck = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
    if (password.value == "" /*|| !pwdCheck.test(password.value)*/) {
        password.focus();
        $("#PassError").html("입력해주세요")
        return false;
    } else {
        $("#PassError").hide();
    }
    ;
    if (nickname.value == "") {
        nickname.focus();
        $(".NickError").html("입력해주세요")
        return false;
    } else {
        $(".NickError").hide();
    }
    ;
    if (email.value == "") {
        email.focus();
        $("#EmailBlankError").html("입력해주세요")
        return false;
    } else {
        $("#EmailBlankError").hide();
    }
    ;
    console.log("확인3")
    return true;
};

function validateUsername() {
    let username = $("#username").val();
    $.ajax({
        url: '/validUsername',
        type: 'POST',
        data: {
            "username": username,
        }
    }).done(function (result) {
        console.log(result)
        if (result) {

            $("#NameSuccess").hide();
            $("#NameError").show();
        } else {

            $("#NameError").hide();
            $("#NameSuccess").show();
        }
    }).fail(function (jqXHR) {
        console.log(jqXHR);
      }).always(function () {
        console.log("실행되는지 확인")
    })
}

function validateEmail() {
    let email = $('#email').val();
    $.ajax({
        url: '/validEmail',
        type: 'POST',
        data: {
            "userEmail": email,
        }
    }).done(function (result) {
        console.log(result)
        if (result) {

            $("#EmailSuccess").hide();
            $("#EmailError").show();
        } else {

            $("#EmailError").hide();
            $("#EmailSuccess").show();
        }
    }).fail(function (jqXHR) {
        console.log(jqXHR);
    }).always(function () {
        console.log("실행되는지 확인")
    })
}

function userSignIns() {

    if (blank()) {

        if (!validateUsername() && !validateEmail()) {


            $.ajax({
                type: "POST",
                url: "/signup",

                contentType: "application/json",
                data: JSON.stringify({
                    "username": $("#username").val(),
                    "email": $("#email").val(),
                    "password": $("#password").val(),
                    "nickName": $("#nickname").val(),
                    "child": $("#child").val(),
                    "memo": $("#memo").val()
                }),

            }).done(function (result) {
                if (result === "SUCCESS")
                    history.back();

            }).fail(function (jqXHR) {
                console.log(jqXHR);

            }).always(function () {
                console.log("실행되는지 확인")
            })
        } else {
            alert("중복확인을 확인 해주세요")

        }
    }
}

