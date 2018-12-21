function get_window_1() {
    var window_1="" +
        "<div style='margin-top:10px; height: 500px;width: 300px;border:1px solid rgba(123, 123, 123, 0.7);background-color: rgba(123, 123, 123, 0.7); '>\n" +
        "    <form  id='overlay' method='post' onsubmit='return false' action='##'><input  type='text'     name='point' id='point' style='display:none' />\n" +
        "        <input  type='text'     name='type' id='type' style='display:none' />\n" +
        "        <h3 style='color: aliceblue;margin-left:25px '>添加新标记点</h3>\n" +
        "        <div style='width:300px; margin:15px auto; text-align: center'>\n" +
        "            <textarea id='title' name='title' placeholder='标记点名称'   from='overlay'  style='width:180px; height:20px;padding:10px 10px; border:1px #999999 solid; font-size:16px; color:#fff; background-color:rgba(31,27,24,0.7);OVERFLOW:   hidden;'></textarea>\n" +
        "            <div style='height: 40px;width: 50px;float:right;margin:2px 20px 0 0;'>\n" +
        "                <select name='level'id='level' style='width: 50px;height: 40px;border:1px solid #000;border-radius: 4px;' ><option value='S'>S级</option><option value='A'>A级</option></select></div>\n" +
        "        </div>\n" +
        "        <div style='width:300px; margin:15px auto; text-align: center'>\n" +
        "            <textarea name='note' placeholder='备注信息'  cols='40'   from='overlay' style='width:250px; height:250px;padding:0px 10px; border:1px #999999 solid; font-size:14px; color:#fff; background-color:rgba(31,27,24,0.7);OVERFLOW:   hidden;'></textarea> </div>\n" +
        "        <div style='float: left;margin: 20px 20px 20px 140px;width: 40px;height: 25px;'>\n" +
        "            <input type='button' onclick='addmarker_()' from='overlay' style='background:#078be4; color:#FFF; font-size:14px; font-family:Microsoft YaHei;' value='添&nbsp;&nbsp;&nbsp;&nbsp;加'/> </div>\n" +
        "        <div style='float: left;margin: 20px 20px 20px 20px;width: 40px;height: 25px;'>\n" +
        "            <input type='button' onclick='disaddmarker_()'  style='background:#C2D8E4; color:#FFF; font-size:14px; font-family:Microsoft YaHei;' value='取&nbsp;&nbsp;&nbsp;&nbsp;消'/> </div>\n" +
        "    </form>\n" +
        "</div>";
    return window_1;
}
var note_2;
var marker_2_title;
function get_window_2() {
    var window_2="" +
        "<div style='margin-top:10px;height: 500px;width: 300px;border:1px solid rgba(223,223,223,0.7);background-color: rgba(206,206,206,0.7); '>\n" +
        "    <h3 style='color: #9b9b9b;margin-left:25px '>标记点信息</h3>\n" +
        "    <form  id='overlay' method='post' onsubmit='return false' action='##'>\n" +
        "        <div style='width:300px; margin:15px auto; text-align: center'>\n" +
        "            <input class='listinp' type='text'     name='point' id='point' style='display:none' />\n" +
        "            <textarea name='title' id='title'   disabled  style='width:180px; height:20px;padding:10px 10px; border:1px #999999 solid; font-size:16px; color:#fff; background-color:rgba(31,27,24,0.7);OVERFLOW:   hidden;'>"+marker_2_title+"</textarea>\n" +
        "            <div style='height: 40px;width: 50px;float:right;margin:2px 20px 0 0;'>\n" +
        "                <select name='level'id='level'disabled='disabled' style='width: 50px;height: 40px;border:1px solid #000;border-radius: 4px;' ><option value='S'>S级</option><option value='A'>A级</option></select></div></div>\n" +
        "        <div style='width:300px; margin:15px auto; text-align: center'>\n" +
        "            <textarea name='note'  id='note' cols='40'  disabled style='width:250px; height:250px;padding:0px 10px; border:1px #999999 solid; font-size:14px; color:#fff; background-color:rgba(31,27,24,0.7);OVERFLOW:   hidden;'>"+note_2+"</textarea>        </div>\n" +
        "        <div id='sub' style='float: left;margin: 10px 20px 10px 140px;width: 40px;height: 25px;display:none'>\n" +
        "            <input type='button' onclick='update_note()' class='btn' value='提&nbsp;&nbsp;&nbsp;&nbsp;交'/>        </div>\n" +
        "        <div id='canl' style='float: left;margin: 10px 20px 10px 20px;width: 40px;height: 25px;display:none'>\n" +
        "            <input type='button' onclick='close_InfoWindow()' class='btn' value='取&nbsp;&nbsp;&nbsp;&nbsp;消'/>        </div>\n" +
        "        <div id='change' style='float: left;margin: 10px 20px 10px 20px;width: 40px;height: 25px;'>\n" +
        "            <input type='button' onclick='updata_InfoWindow()' class='btn' value='修&nbsp;&nbsp;&nbsp;&nbsp;改'/>        </div>\n" +
        "    </form>\n" +
        "</div>";
    return window_2;
}
