package pers.hzh.gss.utils;

import pers.hzh.gss.model.*;
import java.awt.geom.Line2D;
import java.util.List;

public class OverLayUtil {
    /**
     * 判断两者是否相交  不相交返回0  相交返回1  2包含1返回2  1包含2返回3
     * @param polygon1
     * @param polygon2
     * @return
     */
    public static int polygonIntersect(Polygon polygon1,Polygon polygon2){
        List<Point> point_arr_1=StrUtil.strToPointList(polygon1.getPoint());
        List<Point> point_arr_2=StrUtil.strToPointList(polygon2.getPoint());
        int result=0;
        for(int i=0;i<point_arr_1.size();i++){
            for(int j=0;j<point_arr_2.size();j++ ){
                Point point_a1;
                Point point_a2;
                Point point_b1;
                Point point_b2;
                if(i==point_arr_1.size()-1){
                    point_a1=point_arr_1.get(i);
                    point_a2=point_arr_1.get(0);
                }else{
                    point_a1=point_arr_1.get(i);
                    point_a2=point_arr_1.get(i+1);
                }
                if(j==point_arr_2.size()-1){
                    point_b1=point_arr_2.get(j);
                    point_b2=point_arr_2.get(0);
                }else{
                    point_b1=point_arr_2.get(j);
                    point_b2=point_arr_2.get(j+1);
                }
                if(linesIntersect(point_a1,point_a2,point_b1,point_b2)){
                    result= 1 ;
                }
            }
        }
        if(result==0){
            if(rayCasting(point_arr_1.get(0),point_arr_2)){
                result=2;
            }
            if(rayCasting(point_arr_2.get(0),point_arr_1)){
                result=3;
            }
        }
        return result;
    }

    /**
     * 判断线段是否相交
     * @param point_a1
     * @param point_a2
     * @param point_b1
     * @param point_b2
     * @return
     */
    public static boolean linesIntersect(Point point_a1,Point point_a2,Point point_b1,Point point_b2){
        return Line2D.Double.linesIntersect(point_a1.getLng(),point_a1.getLat(),point_a2.getLng(),point_a2.getLat(),point_b1.getLng(),point_b1.getLat(),point_b2.getLng(),point_b2.getLat());
    }
    private static boolean rayCasting(Point p, List<Point> list) {
        double px = p.lng, py = p.lat;
        boolean flag = false;
        //
        for (int i = 0, l = list.size(), j = l - 1; i < l; j = i, i++) {
            //取出边界的相邻两个点
            double sx = list.get(i).lng,
                    sy = list.get(i).lat,
                    tx = list.get(j).lng,
                    ty = list.get(j).lat;
            // 点与多边形顶点重合
            if ((sx == px && sy == py) || (tx == px && ty == py)) {
                return true;
            }
            // 判断线段两端点是否在射线两侧
            //思路:作p点平行于y轴的射线 作s,t的平行线直线  如果射线穿过线段，则py的值在sy和ty之间
            if ((sy < py && ty >= py) || (sy >= py && ty < py)) {
                // 线段上与射线 Y 坐标相同的点的 X 坐标 ,即求射线与线段的交点
                double x = sx + (py - sy) * (tx - sx) / (ty - sy);
                // 点在多边形的边上
                if (x == px) {
                    return true;
                }
                // 射线穿过多边形的边界
                if (x > px) {
                    flag = !flag;
                }
            }
        }
        // 射线穿过多边形边界的次数为奇数时点在多边形内
        return flag ;
    }
}
