 function test(){
   $.ajax({
            //�ύ���ݵ����� POST GET
            type:"POST",
            //�ύ����ַ
            url:"http://localhost:8080/weixin/test",
            //�ύ������
            data:{name:"sanmao"},
            //�������ݵĸ�ʽ
           // datatype: "html",//"xml", "html", "script", "json", "jsonp", "text".
            //������֮ǰ���õĺ���
           // beforeSend:function(){$("#msg").html("logining");},
            //�ɹ�����֮����õĺ���             
            success:function(data){
               alert(data);            
            }   ,
            //����ִ�к���õĺ���
            complete: function(XMLHttpRequest, textStatus){
               alert(XMLHttpRequest.responseText);
               alert(textStatus);
                //HideLoading();
            },
            //���ó���ִ�еĺ���
            error: function(){
                //���������
            }         
         });

  }
