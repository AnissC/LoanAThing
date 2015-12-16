/**
 * Created by Nico on 16/12/2015.
 */
var k = [38, 38, 40, 40, 37, 39, 37, 39, 66, 65],
    n = 0;
$(document).keydown(function (e) {
    if (e.keyCode === k[n++]) {
        if (n === k.length) {
            $("p, h1, h2, h3, h4, h5").each(function(){
                $(this).text("TRISTANOTOUR");
            });
            $("img").each(function(){
                $(this).attr('src', '../inc/images/tristanotour.png')
            })
            n = 0;
            return false;
        }
    }
    else {
        n = 0;
    }
});