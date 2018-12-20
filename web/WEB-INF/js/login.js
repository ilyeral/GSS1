function checkForm() {
    var userName = document.getElementById("id");
    var passWord = document.getElementById("password");

    if (userName.value == "" || userName.value == "") {
        userName.focus();
        return false;
    }
    if (passWord.value == "") {
        passWord.focus();
        return false;
    }
    return true;
}
function login_fail(str){
    document.getElementById("login_result").innerHTML=str;
    setTimeout("login_clear()",3000);
}
function login_clear(){
    document.getElementById("login_result").innerHTML="";
}
function codeLineNone(){
    document.getElementById("CodeLine").style.display="none";
}
function codeLineInline(){
    console.log("codeLineInline_");//打印服务端返回的数据(调试用)
    document.getElementById("CodeLine").style.display="inline";
}
//清空验证码  聚焦
function codeLineClean(){
    console.log("cleanCode_");//打印服务端返回的数据(调试用)
    document.getElementById("vericode").value="";
    document.getElementById("vericode").focus();
}
function login() {
    if(!checkForm()){
        console.log("false");//打印服务端返回的数据(调试用)
        return false;
    }
    console.log("ajax--");//打印服务端返回的数据(调试用)
    $.ajax({
        //几个参数需要注意一下
        type: "POST",//方法类型
        dataType: "text",//预期服务器返回的数据类型
        url: getRootPath()+"/login" ,//url
        data: $('#login_form').serialize(),
        contentType : "application/x-www-form-urlencoded",
        success: function (result) {
            console.log(result);//打印服务端返回的数据(调试用)
            if (result=="success") {
                //跳转
                window.location.replace(getRootPath()+"/index");
            }else if(result=="fail"){
                login_fail("账号或密码错误");
                codeLineInline();
                refreshCode();
            }else if(result=="code_fail"){
                login_fail("验证码错误");
                codeLineClean();
                codeLineInline();
                refreshCode();
            } else if(result=="code_empty"){
                login_fail("请输入验证码");
                codeLineClean();
                codeLineInline();
            }
            ;
        },
        error : function() {
            alert("异常！");
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

//刷新验证码
function refreshCode(){
    document.getElementById("getcode").src = "/vericode.do?time=" + new Date().getTime();
}
