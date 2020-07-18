window.onload = function() {
    console.log("onload complete")

    $("#write_complete_button").click (function(){
        console.log("#goods_form submitted.");
        $("#goods_form").submit();
    });

    $("#back-button").click (function(){
        console.log("back-button clicked");
        window.location.href = "/goods";
    });

};
