$(document).ready(function () {
    $("#logout").click(function () {
        $.ajax({
            method: "GET",
            url: "/logout",
            success: function () {
                location.reload();
            },
        });
    })
})