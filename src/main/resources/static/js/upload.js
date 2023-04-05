if (localStorage.hasOwnProperty("accessToken")) {
    console.log("allowed");
} else {

    alert("로그인해주세요")
    history.back();
}


let urlpath = window.location.pathname.split('/');
const postId = urlpath[3];
const board = urlpath[1];
console.log("url 확인 : " + board + " and :" + postId);
const uploadUrl = ("/" + board + "/" + postId + "/form");


$('#fileImage').change(function () {
    showImageThumbnail(this);
});

function showImageThumbnail(fileInput) {
    file = fileInput.files[0];
    reader = new FileReader();
    reader.onload = function (e) {
        $('#thumbnail').attr('src', e.target.result
        )
    }
    reader.readAsDataURL(file);
}


function userBlank() {

    let title = document.getElementById("title");
    let content = document.getElementById("postContent")

    if (title.value == "") {
        title.focus();
        $("#titleError").html("입력해주세요")
        return false;
    } else {
        $("#titleError").hide();

    }

    if (content.value == "") {
        content.focus();
        $("#contentError").html("입력해주세요")
        return false;
    } else {
        $("#contentError").hide();

    }

}


function amdinBlank() {
    let homeTitle = document.getElementById("homeTitle");
    let homeName = document.getElementById("homeName");
    let homeCCTV = document.getElementById("homeCCTV");
    let homeSize = document.getElementById("homeSize")
    let homeAddress = document.getElementById("homeAddress")
    let homeRegister = document.getElementById("homeRegister")

    let homeChildren = document.getElementById("homeChildren")
    let homeNumber = document.getElementById("homeNumber")
    let homeMeal = document.getElementById("homeMeal")
    let homeDetails = document.getElementById("homeDetails")


    if (homeTitle.value == "") {
        homeTitle.focus();
        $("#titleError").html("입력해주세요")
        return false;
    } else {
        $("#titleError").hide();
        return true;
    }

    if (homeName.value == "") {
        homeName.focus();
        $("#NameError").html("입력해주세요")
        return false;
    } else {
        $("#NameError").hide();
        return true;
    }
    if (homeCCTV.value == "") {
        homeCCTV.focus();
        $("#CCTVError").html("입력해주세요")
        return false;
    } else {
        $("#CCTVError").hide();
        return true;
    }
    if (homeChildren.value == "") {
        homeChildren.focus();
        $("#ChildError").html("입력해주세요")
        return false;
    } else {
        $("#ChildError").hide();
        return true;
    }
    if (homeSize.value == "") {
        homeSize.focus();
        $("#SizeError").html("입력해주세요")
        return false;
    } else {
        $("#SizeError").hide();
        return true;
    }
    if (homeAddress.value == "") {
        homeAddress.focus();
        $("#AddressError").html("입력해주세요")
        return false;
    } else {
        $("#AddressError").hide();
        return true;
    }
    if (homeRegister.value == "") {
        homeRegister.focus();
        $("#RegError").html("입력해주세요")
        return false;
    } else {
        $("#RegError").hide();
        return true;
    }
    if (homeNumber.value == "") {
        homeNumber.focus();
        $("#NoError").html("입력해주세요")
        return false;
    } else {
        $("#NoError").hide();
        return true;
    }

    if (homeMeal.value == "") {
        homeMeal.focus();
        $("#mealError").html("입력해주세요")
        return false;
    } else {
        $("#mealError").hide();
        return true;
    }

    if (homeDetails.value == "") {
        homeDetails.focus();
        $("#DetailError").html("입력해주세요")
        return false;
    } else {
        $("#DetailError").hide();
        return true;
    }

}

function formUpdate() {

    const adminPostRequest = {}
    $.ajax({
        type: "POST",
        url: uploadUrl,
        dataType: "json",
        headers: {"Authorization": accessToken},
        contentType: "application/json",
        data: JSON.stringify({
            "homeName": $("#homeName").val(),
            "homeTitle": $("#homeTitle").val(),
            "homeVideo": $("#homeVideo").val(),
            "homeAddress": $("#homeAddress").val(),
            "homeRegister": $("#homeRegister").val(),
            "homeChildren": $("#homeChildren").val(),
            "homeNumber": $("#homeNumber").val(),
            "homeMeal": $("#homeMeal").val(),
            "homeDetails": $("#homeDetails").val(),
            "homeCCTV": $("#homeCCTV").val(),
            "homeSize": $("#homeSize").val(),

        }),

    }).done(function (result) {
        console.log(result)
    }).fail(function (jqXHR) {
        console.log(jqXHR);
        $("#errorMsg").show();
    }).always(function () {
        console.log("실행되는지 확인")
    })
}


function formSend() {
    console.log("비어있는곳은 다 채워볼까?")
    if (amdinBlank()) {
        console.log("비어있는곳은 다 채움")

        const imageFile = document.querySelector('#fileImage').files[0];
        console.log(imageFile);


        let homeName = $("#homeName").val();
        let homeTitle = $("#homeTitle").val();
        let homeVideo = $("#homeVideo").val();
        let homeAddress = $("#homeAddress").val();
        let homeRegister = $("#homeRegister").val();
        let homeNumber = $("#homeNumber").val();
        let homeMeal = $("#homeMeal").val();
        let homeDetails = $("#homeDetails").val();
        let homeCCTV = $("#homeCCTV").val()
        let homeSize = $("#homeSize").val()
        let homeChildren = $("#homeChildren").val()
        const form = new FormData();

        form.append("homeCCTV", homeCCTV)
        form.append("homeSize", homeSize)
        form.append("homeChildren", homeChildren)
        form.append("images", imageFile)
        form.append("homeName", homeName)
        form.append("homeTitle", homeTitle)
        form.append("homeVideo", homeVideo)
        form.append("homeAddress", homeAddress)
        form.append("homeRegister", homeRegister)
        form.append("homeNumber", homeNumber)
        form.append("homeMeal", homeMeal)
        form.append("homeDetails", homeDetails)


        $.ajax({
            type: "POST",
            url: "/homes/formSend",
            headers: {"Authorization": accessToken},

            contentType: false,
            processData: false,
            /*            contentType: "application/json",*/
            data: form
            /*          JSON.stringify({
                      "homeName": $("#homeName").val(),
                      "homeTitle": $("#homeTitle").val(),
                      "homeVideo": $("#homeVideo").val(),
                      "homeAddress": $("#homeAddress").val(),
                      "homeRegister": $("#homeRegister").val(),
                      "homeChildren": $("#homeChildren").val(),
                      "homeNumber": $("#homeNumber").val(),
                      "homeMeal": $("#homeMeal").val(),
                      "homeDetails": $("#homeDetails").val(),
                      "homeCCTV": $("#homeCCTV").val(),
                      "homeSize": $("#homeSize").val(),

                  })*/,

        }).done(function (result) {
            console.log("확인")
            window.location.replace('http://localhost:9011/homes/posts');
            /*window.location.replace('http://115.85.182.246:9011/homes/posts');*/

        }).fail(function (jqXHR) {
            console.log(jqXHR);


            console.log("failed")
            $("#errorMsg").show();
        }).always(function () {
            console.log("실행되는지 확인")
        })
    }
}

function userformUpdate() {

    $.ajax({
        type: "POST",
        url: uploadUrl,
        headers: {"Authorization": accessToken},
        contentType: "application/json",
        data: JSON.stringify({
            "title": $("#title").val(),
            "postContent": $("#postContent").val(),

        })

    }).done(function (result) {
        console.log("확인")
        window.location.replace('http://localhost:9011/community/posts');
        /*window.location.replace('http://115.85.182.246:9011/community/posts');*/

    }).fail(function (jqXHR) {
        console.log(jqXHR);


        console.log("failed")
        $("#errorMsg").show();
    }).always(function () {
        console.log("실행되는지 확인")
    })

}

function userformSend() {

    if (userBlank()) {
        $.ajax({
            type: "POST",
            url: "/community/formSend",
            headers: {"Authorization": accessToken},
            contentType: "application/json",
            data: JSON.stringify({
                "title": $("#title").val(),
                "postContent": $("#postContent").val()

            }),

        }).done(function (result) {
            console.log("확인")
            window.location.replace('http://localhost.246:9011/community/posts');
            /*window.location.replace('http://115.85.182.246:9011/community/posts');*/

        }).fail(function (jqXHR) {
            console.log(jqXHR);


            console.log("failed")
            $("#errorMsg").show();
        }).always(function () {
            console.log("실행되는지 확인")
        })
    }
}