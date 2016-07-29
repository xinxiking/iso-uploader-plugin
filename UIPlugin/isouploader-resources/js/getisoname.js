var isoAvailable = new Array();
var isoName = new Array();
var availableIso = null;

function showIsoName() {
          $.ajax({
            type: "GET",
            url: "/ovirt-engine/api/storagedomains/?search=status%3Dactive",
            headers: {"Content-Type":"application/xml",
                      "Prefer":"persistent-auth",
                     },
            success: function(data) {
              //var oSerializer = new XMLSerializer();
              //var getXMLString = oSerializer.serializeToString(data);
              //alert(getXMLString);
              var j = 0;
              var k = 0;
              var isoNameXML = data.getElementsByTagName("name");
              var isoTypeXML=data.getElementsByTagName("type");
              var isoAvailableXML=data.getElementsByTagName("available");
              var getIsoName=new Array(isoNameXML.length);
              var getIsoType=new Array(isoTypeXML.length);
              var getIsoAvailable=new Array(isoAvailableXML.length);
              for(var i=0;i<isoNameXML.length;i++){
                 getIsoName[i]=isoNameXML[i].childNodes[0].data;
              }
              for(var i=0;i<isoAvailableXML.length;i++){
                 getIsoAvailable[i]=isoAvailableXML[i].childNodes[0].data;
              }
              //alert(getIsoName.join());
              for(var i=0;i<isoTypeXML.length;i=i+2){
                 if(isoTypeXML[i].childNodes[0].data=="iso"){
                     isoName[j] = getIsoName[i/2];
                     j++;
                 }
              }
              for(var i=0;i<isoTypeXML.length;i=i+2){
                 if(isoTypeXML[i].childNodes[0].data=="iso"){
                     isoAvailable[k] = getIsoAvailable[i/2];
                     k++;
                 }
              }
              //alert(isoName.join());
              if(lang == 'zh_CN'){
                  selectid.options[0] = new Option("请选择存储域","请选择存储域");
              }
              else{
                  selectid.options[0] = new Option("Choice Domain","Choice Domain");
              }
              for(var i = 0;i < isoName.length;i++){
                 selectid.options[i+1] = new Option(isoName[i],isoName[i]);
              }
            }
          });
}


function servletGetIsoName(){
        var str = $("#selectid option:selected").val();   //获得选中的值
        for(var i=0;i<isoName.length;i++){
            if(isoName[i]==str){
                availableIso = isoAvailable[i];
            }
        }
        $.ajax({
            type:"post",
            url:"/iso-uploader-plugin/GetIsoDomain",
             dataType: "json",
            //headers: {"Content-Type":"application/json"},
            data:{'selectname':str},
            success:function(msg){
                if(msg){
                    alert(msg);
                }
            }
        });
}

function removeTempFile(){
    $.ajax({
        type:"post",
        url:"/iso-uploader-plugin/RemoveTempFile",
        success:function(msg){
            if(msg){
                alert(msg);
            }
        }
    });
    alert("缓存文件夹已清空！！！");
}
