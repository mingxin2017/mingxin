// �ļ��ϴ�
jQuery(function() {
    var $ = jQuery,
        $list = $('#thelist'),
        $btn = $('#ctlBtn'),
        state = 'pending',
        uploader;

    uploader = WebUploader.create({

        // ��ѹ��image
        resize: false,

        // swf�ļ�·��
        swf: BASE_URL + '/js/Uploader.swf',

        // �ļ����շ���ˡ�
        server: 'http://webuploader.duapp.com/server/fileupload.php',

        // ѡ���ļ��İ�ť����ѡ��
        // �ڲ����ݵ�ǰ�����Ǵ�����������inputԪ�أ�Ҳ������flash.
        pick: '#picker'
    });

    // �����ļ���ӽ�����ʱ��
    uploader.on( 'fileQueued', function( file ) {
        $list.append( '<div id="' + file.id + '" class="item">' +
            '<h4 class="info">' + file.name + '</h4>' +
            '<p class="state">�ȴ��ϴ�...</p>' +
        '</div>' );
    });

    // �ļ��ϴ������д���������ʵʱ��ʾ��
    uploader.on( 'uploadProgress', function( file, percentage ) {
        var $li = $( '#'+file.id ),
            $percent = $li.find('.progress .progress-bar');

        // �����ظ�����
        if ( !$percent.length ) {
            $percent = $('<div class="progress progress-striped active">' +
              '<div class="progress-bar" role="progressbar" style="width: 0%">' +
              '</div>' +
            '</div>').appendTo( $li ).find('.progress-bar');
        }

        $li.find('p.state').text('�ϴ���');

        $percent.css( 'width', percentage * 100 + '%' );
    });

    uploader.on( 'uploadSuccess', function( file ) {
        $( '#'+file.id ).find('p.state').text('���ϴ�');
    });

    uploader.on( 'uploadError', function( file ) {
        $( '#'+file.id ).find('p.state').text('�ϴ�����');
    });

    uploader.on( 'uploadComplete', function( file ) {
        $( '#'+file.id ).find('.progress').fadeOut();
    });

    uploader.on( 'all', function( type ) {
        if ( type === 'startUpload' ) {
            state = 'uploading';
        } else if ( type === 'stopUpload' ) {
            state = 'paused';
        } else if ( type === 'uploadFinished' ) {
            state = 'done';
        }

        if ( state === 'uploading' ) {
            $btn.text('��ͣ�ϴ�');
        } else {
            $btn.text('��ʼ�ϴ�');
        }
    });

    $btn.on( 'click', function() {
        if ( state === 'uploading' ) {
            uploader.stop();
        } else {
            uploader.upload();
        }
    });
});


// ͼƬ�ϴ�demo
jQuery(function() {
    var $ = jQuery,
        $list = $('#fileList'),
        // �Ż�retina, ��retina�����ֵ��2
        ratio = window.devicePixelRatio || 1,

        // ����ͼ��С
        thumbnailWidth = 100 * ratio,
        thumbnailHeight = 100 * ratio,

        // Web Uploaderʵ��
        uploader;

    // ��ʼ��Web Uploader
    uploader = WebUploader.create({

        // �Զ��ϴ���
        auto: true,

        // swf�ļ�·��
        swf: BASE_URL + '/js/Uploader.swf',

        // �ļ����շ���ˡ�
        server: 'http://webuploader.duapp.com/server/fileupload.php',

        // ѡ���ļ��İ�ť����ѡ��
        // �ڲ����ݵ�ǰ�����Ǵ�����������inputԪ�أ�Ҳ������flash.
        pick: '#filePicker',

        // ֻ����ѡ���ļ�����ѡ��
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        }
    });

    // �����ļ���ӽ�����ʱ��
    uploader.on( 'fileQueued', function( file ) {
        var $li = $(
                '<div id="' + file.id + '" class="file-item thumbnail">' +
                    '<img>' +
                    '<div class="info">' + file.name + '</div>' +
                '</div>'
                ),
            $img = $li.find('img');

        $list.append( $li );

        // ��������ͼ
        uploader.makeThumb( file, function( error, src ) {
            if ( error ) {
                $img.replaceWith('<span>����Ԥ��</span>');
                return;
            }

            $img.attr( 'src', src );
        }, thumbnailWidth, thumbnailHeight );
    });

    // �ļ��ϴ������д���������ʵʱ��ʾ��
    uploader.on( 'uploadProgress', function( file, percentage ) {
        var $li = $( '#'+file.id ),
            $percent = $li.find('.progress span');

        // �����ظ�����
        if ( !$percent.length ) {
            $percent = $('<p class="progress"><span></span></p>')
                    .appendTo( $li )
                    .find('span');
        }

        $percent.css( 'width', percentage * 100 + '%' );
    });

    // �ļ��ϴ��ɹ�����item��ӳɹ�class, ����ʽ����ϴ��ɹ���
    uploader.on( 'uploadSuccess', function( file ) {
        $( '#'+file.id ).addClass('upload-state-done');
    });

    // �ļ��ϴ�ʧ�ܣ���ʵ�ϴ�����
    uploader.on( 'uploadError', function( file ) {
        var $li = $( '#'+file.id ),
            $error = $li.find('div.error');

        // �����ظ�����
        if ( !$error.length ) {
            $error = $('<div class="error"></div>').appendTo( $li );
        }

        $error.text('�ϴ�ʧ��');
    });

    // ����ϴ����ˣ��ɹ�����ʧ�ܣ���ɾ����������
    uploader.on( 'uploadComplete', function( file ) {
        $( '#'+file.id ).find('.progress').remove();
    });
});