/*********************************WebUpload ���ļ��ϴ� begin*****************************************/
$(function(){
    var $list = $("#thelist");
    var  uploader ;// ʵ����   
    uploader = WebUploader.create({ 
           auto:false, //�Ƿ��Զ��ϴ�
            pick: {
                id: ��#attach��,
                name:"file",  //����ط� name ûʲô�ã���Ȼ�򿪵�������input������ȷʵ�Ĺ����ˡ������ύ����̨ȡ�����ļ���������Զ���file��name���ԣ�����Ҫ��fileVal ���ʹ�á�
                label: �����ѡ��ͼƬ��,
                multiple:false            //Ĭ��Ϊtrue�����ǿ��Զ�ѡ
            },
            swf: ��../../main/js/Uploader.swf��,  
            //fileVal:��multiFile��,  //�Զ���file��name���ԣ����õİ汾��0.1.5 ,�򿪿ͻ��˵������������ɵ�input ��name û�Ĺ�����
                                             //���ֻ���Ĭ�ϵ�file,������û��Ŷ����Ȼ�ͻ�������û�ı䣬�����ύ������̨����Ҫ��multiFile ���������ȡ�ļ��ģ���file ��ȡ�����ļ���
                                             // ����������ʱ�������ط��ĸİ����������ˡ���
            server: "ContentsDetail!ajaxAttachUpload.action",  
            duplicate:true,//�Ƿ���ظ�ѡ��ͬһ�ļ�
            resize: false,
            formData: {
                "status":"file",
                "contentsDto.contentsId":"0000004730",
                "uploadNum":"0000004730",
                "existFlg":��false��
            },  
            compress: null,//ͼƬ��ѹ��
            chunked: true,  //��Ƭ����
            chunkSize: 5 * 1024 * 1024, //ÿƬ5M  
            chunkRetry:false,//���ʧ�ܣ�������
            threads:1,//�ϴ�������������ͬʱ����ϴ���������
            // runtimeOrder: ��flash��,  
            // ����ȫ�ֵ���ק���ܡ������������ͼƬ�Ͻ�ҳ���ʱ�򣬰�ͼƬ�򿪡�  
            disableGlobalDnd: true
        });  

        // �����ļ���ӽ�����ʱ��
       uploader.on( "fileQueued", function( file ) {
           console.log("fileQueued:");
           $list.append( "<div id=��"+  file.id + "�� class=��item��>" +
               "<h4 class=��info��>" + file.name + "</h4>" +
               "<p class=��state��>�ȴ��ϴ�...</p>" +
           "</div>" );
       });

       //�������ļ��ϴ�����ʱ����
       uploader.on("uploadFinished",function(){
           console.log("uploadFinished:");
       })

        //��ĳ���ļ��ϴ����������Ӧ�󣬻����ʹ��¼���ѯ�ʷ������Ӧ�Ƿ���Ч��
        uploader.on("uploadAccept",function(object,ret){
            //��������Ӧ��
            //ret._raw  ������ data
            var data =JSON.parse(ret._raw);
            if(data.resultCode != "1" && data.resultCode != "3"){
                if(data.resultCode == "9"){
                    uploader.reset();
                    alert("error");
                    return false;
                }
            }else{
                //E05017
                uploader.reset();
                alert("error");
                return false;
            }
           })

       //���ļ��ϴ��ɹ�ʱ������
         uploader.on( "uploadSuccess", function( file ) {
           $( "#"+file.id ).find("p.state").text("���ϴ�");
       });

       uploader.on( "uploadError", function( file ) {
           $( "#"+file.id ).find("p.state").text("�ϴ�����");
           uploader.cancelFile(file);
           uploader.removeFile(file,true);
           uploader.reset();
       });


       $("#upload").on("click", function() {
           uploader.upload();
       })

});
/*********************************WebUpload ���ļ��ϴ� end*******************************************/

/*********************************WebUpload ���ļ��ϴ� begin*****************************************/
$(function(){
    var $list = $("#thelist1");
    var fileSize = 0;  //���ļ���С
    var fileName = []; //�ļ����б�
    var fileSizeOneByOne =[];//ÿ���ļ���С
    var  uploader ;// ʵ����   
    uploader = WebUploader.create({ 
            auto:false, //�Ƿ��Զ��ϴ�
            pick: {
                id: ��#multi��,
                label: �����ѡ���ļ���,
                name:"multiFile"
            },
            swf: ��../../main/js/Uploader.swf��,  
            server: "ContentsDetail!multiUpload.action",  
            duplicate:true, //ͬһ�ļ��Ƿ���ظ�ѡ��
            resize: false,
            formData: {
                "status":"multi",
                "contentsDto.contentsId":"0000004730",
                "uploadNum":"0000004730",
                "existFlg":��false��
            },  
            compress: null,//ͼƬ��ѹ��
            chunked: true,  //��Ƭ
            chunkSize: 5 * 1024 * 1024,   //ÿƬ5M
            chunkRetry:false,//���ʧ�ܣ�������
            threads:1,//�ϴ�������������ͬʱ����ϴ���������
            //fileNumLimit:50,//��֤�ļ�������, ����������������
            // runtimeOrder: ��flash��,  
            // ����ȫ�ֵ���ק���ܡ������������ͼƬ�Ͻ�ҳ���ʱ�򣬰�ͼƬ�򿪡�  
            disableGlobalDnd: true
        });  

       // �����ļ���ӽ�����ʱ��
       uploader.on( "fileQueued", function( file ) {
           console.log("fileQueued:");
           $list.append( "<div id=��"+  file.id + "�� class=��item��>" +
               "<h4 class=��info��>" + file.name + "</h4>" +
               "<p class=��state��>�ȴ��ϴ�...</p>" +
           "</div>" );
       });

       // ����ʼ�ϴ�����ʱ����
       uploader.on( "startUpload", function() {
           console.log("startUpload");
           //��Ӷ���ı�����
           $.extend( true, uploader.options.formData, {"fileSize":fileSize,"multiFileName":fileName.join(","),"fileSizeOneByOne":fileSizeOneByOne.join(",")}); 
       });


       //��ĳ���ļ��ϴ����������Ӧ�󣬻����ʹ��¼���ѯ�ʷ������Ӧ�Ƿ���Ч��
       uploader.on("uploadAccept",function(object,ret){
            //��������Ӧ��
            //ret._raw  ������ data
            console.log("uploadAccept");
            console.log(ret);
            var data =JSON.parse(ret._raw);
            if(data.resultCode!="1" && data.resultCode !="3"){
                if(data.resultCode == "9"){
                    alert("error");
                    uploader.reset();
                    return;
                }
            }else{
                uploader.reset();
                alert("error");
            }
        })

       uploader.on( "uploadSuccess", function( file ) {
           $( "#"+file.id ).find("p.state").text("���ϴ�");
       });

       uploader.on( "uploadError", function( file,reason  ) {
           $( "#"+file.id ).find("p.state").text("�ϴ�����");
           console.log("uploadError");
           console.log(file);
           console.log(reason);
           //����ļ�
           var fileArray = uploader.getFiles();
           for(var i = 0 ;i<fileArray.length;i++){
               uploader.cancelFile(fileArray[i]);
               uploader.removeFile(fileArray[i],true);
            }
           uploader.reset();
           fileSize = 0;
           fileName = [];
           fileSizeOneByOne=[];
       });

     //��validate��ͨ��ʱ���������ʹ����¼�����ʽ֪ͨ������
       uploader.on("error",function(){
           console.log("error");
           uploader.reset();
           fileSize = 0;
           fileName = [];
           fileSizeOneByOne=[];
           alert("error");
       })


       //�������ģ̬������ϴ���ť�����file��ʱ�򲻻ᴥ���ؼ�
       //�޸�model�ڲ�������ᴥ��ѡ���ļ���BUG
       /*     $("#multi .webuploader-pick").click(function () {
                uploader.reset();
                fileSize = 0;
                fileName = [];
                fileSizeOneByOne=[];
                $("#multi :file").click();//�ؼ�����
            });*/

            //ѡ���ļ�֮��ִ���ϴ�  
            $(document).on("change","input[name=��multiFile��]", function() {
                   var fileArray1 = uploader.getFiles();
                   for(var i = 0 ;i<fileArray1.length;i++){
                       //��̨��
                       fileSize +=fileArray1[i].size;
                       fileSizeOneByOne.push(fileArray1[i].size);
                       fileName.push(fileArray1[i].name);
                    }
                   console.log(fileSize);
                   console.log(fileSizeOneByOne);
                   console.log(fileName);
            })

          /**
         * ���ļ��ϴ�
         */
        $("input[name=��multiUpload��]").on("click",function(){
            uploader.upload();
        })
});
/*********************************WebUpload ���ļ��ϴ� end*****************************************/

/************************************webuploader���Դ������ύ����̨�Ĳ����б�*************************
 * {

//web uploader ���Դ����� 
lastModifiedDate=[Wed Apr 27 2016 16:45:01 GMT+0800 (�й���׼ʱ��)], 
chunks=[3], chunk=[0], 
type=[audio/wav], uid=[yangl],  id=[WU_FILE_0], 
size=[268620636], name=[3.wav],

//formData�Ĳ���
contentsDto.contentsId=[0000004730], existFlg=[false], 
status=[file], uploadNum=[0000004730]
}
*********************************************************************************************/