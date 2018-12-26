function add_marker_to_li(marker)///////////////////////////////////////////////添加到列表
{
    $("#polygon_List").append("" +
        "<li class='sidebar_li_2_1' id='polygonli"+id+"'>"+
        "<a class='sidebar_a_2_1' id='polygona"+id+"'>"+name+"</a>"+
        "<ul class='sidebar_ul_3'id='Child_Polygon_List"+id+"'>"+
        "</ul>"+
        " </li>");
    $("#polygonli"+id).attr("class",'sidebar_li_2_1');
    $("#polygona"+id).attr("class",'sidebar_a_2_1');
    $("#Child_Polygon_List"+id).attr("class",'sidebar_ul_3');

    $("#Child_Polygon_List"+id).append("" +
        "<li class='sidebar_li_3'id='child_polygon_li"+id+"'>"+
        "<a class='sidebar_a_2_1'id='child_polygon_a"+id+"'>"+name+"</a>"+
        " </li>");
    $("#child_polygon_li"+id).attr("class",'sidebar_li_3');
    $("#child_polygon_a"+id).attr("class",'sidebar_a_2_1');
}