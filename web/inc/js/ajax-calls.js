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
    $("#user").click(function () {
        $.ajax({
            method: "GET",
            url: "/profil",
            success: function () {
                location.reload();
            },
        });
    })
})


/*function loadUserProfil($content, idUser){
    $.post("/profil",
        { id: 1},
        function(result){
            alert(result);
        }
    );

    $.get("/profil", function(responseJson) {          // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response JSON...
        var $table = $("<table>").appendTo($("#user-profil")); // Create HTML <table> element and append it to HTML DOM element with ID "somediv".
        $.each(responseJson, function(index, user) {    // Iterate over the JSON array.
            $("<tr>").appendTo($table)                     // Create HTML <tr> element, set its text content with currently iterated item and append it to the <table>.
                .append($("<td>").text(user.id))        // Create HTML <td> element, set its text content with id of currently iterated product and append it to the <tr>.
                .append($("<td>").text(user.name));    // Create HTML <td> element, set its text content with price of currently iterated product and append it to the <tr>.
        });
    });
}*/