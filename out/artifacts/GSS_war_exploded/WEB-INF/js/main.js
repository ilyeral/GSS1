function logout() {
    console.log("ajax--logout");//打印服务端返回的数据(调试用)
    $.ajax({
        //几个参数需要注意一下
        type: "GET",//方法类型
        dataType: "text",//预期服务器返回的数据类型
        url: getRootPath()+"/logout.do" ,//url
        success: function (result) {
            console.log(result);//打印服务端返回的数据(调试用)
            if (result=="logout") {
                //跳转
                window.location.replace(getRootPath()+"/login");
            }
        },
        error : function() {
            alert("登出失败！");
        }
    });
}
//js获取项目根路径，如： http://localhost:8083/uimcardprj
function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht);
}