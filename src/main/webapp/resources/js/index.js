$(document).ready(function () {
    $(".playlist li").click(function () {
        $("#frame").attr("src", $(this).attr("value"));
    });
    
    $(".menu ul li").click(function (){
        if(!$(this).hasClass("type-selected")){
            $(this).addClass("type-selected");
        }
    });

});
