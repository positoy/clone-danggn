window.onload = function () {
    console.log('onload complete');
    (function () {
        $user = $('user');

        var newlogin = $user.attr('data-newlogin');
        console.log(newlogin);
        if (newlogin != '' && newlogin != undefined)
            $('.toast').fadeIn(400).delay(3000).fadeOut(400);

        const userid = $user.attr('data-id');
        console.log(userid);
        var naver_login_uri =
            'https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=AlS3TCLxJYn7SNPp75LE&state=hello&redirect_uri=';
        naver_login_uri =
            naver_login_uri +
            encodeURIComponent(
                'http://' + window.location.hostname + ':8080/goods'
            );
        console.log(naver_login_uri);
        $('.downnav__list__item a').each(function (index, item) {
            if (userid == '' || userid === undefined) {
                var isWriteMenu = $(item).attr('href').indexOf('write') != -1;
                var isChatMenu = $(item).attr('href').indexOf('chat') != -1;
                var isUserMenu = $(item).attr('href').indexOf('user') != -1;
                if (isWriteMenu || isChatMenu || isUserMenu)
                    $(item).attr('href', naver_login_uri);
            }
        });
    })();

    $('#write_price').keypress(function (event) {
        console.log('onkeypress' + event.keyCode);
        if (event.keyCode < '0' || event.keyCode > '57') return false;
        var cur = $('#write_price').val() + Number.parseInt(event.keyCode - 48);
        if (Number.parseInt(cur) > 2000000000) return false;
    });

    $('#write_pics').on('change', function () {
        console.log(this.files);

        var input = this;

        if (input.files) {
            var filesAmount = input.files.length;

            var $count = $('#write_camera_box_count').text(filesAmount + '/10');

            for (i = 0; i < filesAmount; i++) {
                var reader = new FileReader();
                reader.onload = function (event) {
                    var $div = $('<div>').attr(
                        'class',
                        'contents__pictures__item'
                    );
                    var $img = $('<img>').attr('src', event.target.result);
                    $div.append($img);
                    $('.contents__pictures').prepend($div);
                };

                reader.readAsDataURL(input.files[i]);
            }
        }
    });

    $('#write_camera').click(function () {
        if ($('#write_pics').get(0).files.length < 10) $('#write_pics').click();
    });

    $('#write_form_pics').submit(function (e) {
        console.log('#write_form_pics.submit');
        console.log($('#write_form_pics').attr('action'));
        e.preventDefault();
        var formData = new FormData(this);
        const userid = $user.attr('data-id');

        $.ajax({
            url: $('#write_form_pics').attr('action') + '?userid' + userid,
            type: 'POST',
            data: formData,
            success: function (response) {
                const redirectURL = response.split('redirect:')[1];
                window.location.href = redirectURL;
            },
            cache: false,
            contentType: false,
            processData: false,
        });
    });

    $('#write_complete_button').click(function () {
        console.log('write_complete_button.click');
        $('#write_form_pics').submit();
        return;

        var $form = $('#write_form');
        var $title = $('#write_title');
        var $category = $('#write_category option:selected');
        var $price = $('#write_price');
        var $body = $('#write_body');

        do {
            console.log($category.val());
            if ($category.val() == '카테고리') {
                alert('카테고리를 설정해주세요');
                break;
            }
            if ($title.val().length == 0) {
                alert('제목을 입력해주세요');
                break;
            }
            if ($body.val().length == 0) {
                alert('내용을 입력해주세요');
                break;
            }
            if ($price.val().length == 0) $price.val(0);

            $form.submit();
        } while (false);
    });

    $('#back-button').click(function () {
        console.log('back-button clicked');
        window.location.href = '/goods';
    });
};
