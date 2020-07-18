window.onload = function() {
    console.log("onload complete")

    $("#write_complete_button").click (function(){
        console.log("#goods_form submitted.");

        var $form = $("#write_form");
        var $title = $("#write_title");
        var $category = $("#write_category option:selected");
        var $price = $("#write_price");
        var $body = $("#write_body");

        do {
            console.log($category.val());
            if ($category.val() == "카테고리") {
                alert("카테고리를 설정해주세요");
                break;
            }
            if ($title.val().length == 0) {
                alert("제목을 입력해주세요");
                break;
            }
            if ($body.val().length == 0) {
                alert("내용을 입력해주세요");
                break;
            }
            if ($price.val().length == 0)
                $price.val(0);

            $form.submit();
        } while (false);

    });

    $("#back-button").click (function(){
        console.log("back-button clicked");
        window.location.href = "/goods";
    });

};
