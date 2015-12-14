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


function loadUserProfil($content, idUser){

    $.get("/profil", function(responseJson) {
        user = JSON.parse(responseJson);
        $content.append("<h1 class='text-center'>Profil</h1>");
        if (user.hasOwnProperty("lastname")) { $content.lastChild().after("<p>Prenom : "+ user.lastname +"</p>") };
        if (user.hasOwnProperty("fisrtname")) { $content.lastChild().after("<p>Nom : "+ user.firstname +"</p>") };
        if (user.hasOwnProperty("email")) { $content.lastChild().after("<p>Email : "+ user.email +"</p>") };
        if (user.hasOwnProperty("address")) { $content.lastChild().after("<p>Adresse : "+ user.address +"</p>") };
        if (user.hasOwnProperty("city")) { $content.lastChild().after("<p>Ville : "+ user.city +"</p>") };
        if (user.hasOwnProperty("zipCode")) { $content.lastChild().after("<p>Code Postal : "+ user.zipCode +"</p>") };
        if (user.hasOwnProperty("birthday")) { $content.lastChild().after("<p>Date de naissance : "+ user.birthday +"</p>") };
    });
}