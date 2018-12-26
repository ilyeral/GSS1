package pers.hzh.gss.utils;

import org.apache.log4j.Logger;
import pers.hzh.gss.model.*;
import java.awt.geom.Line2D;
import java.util.List;

public class OverLayUtil {
    private static Logger logger = Logger.getLogger(OverLayUtil.class);
    /**
     * 判断两者是否相交  不相交返回0  相交返回1  2包含1返回2  1包含2返回3
     * @param polygon1
     * @param polygon2
     * @return
     */
    public static int polygonIntersect(Polygon polygon1,Polygon polygon2){
        List<Point> point_arr_1=StrUtil.strToPointList(polygon1.getPoint());
        List<Point> point_arr_2=StrUtil.strToPointList(polygon2.getPoint());
//        for(int i=0;i<point_arr_1.size();i++){
//            Point point=point_arr_1.get(i);
//            logger.info(point.lng+","+point.lat);
//        }
//        logger.info("---------");
//        for(int i=0;i<point_arr_2.size();i++){
//            Point point=point_arr_2.get(i);
//            logger.info(point.lng+","+point.lat);
//        }
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
            if(isPolygonContainsPoint(point_arr_1.get(0),point_arr_2)){
                result=2;
            }
            if(isPolygonContainsPoint(point_arr_2.get(0),point_arr_1)){
                result=3;
            }
        }
        //logger.info(result);
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

    public static boolean isPolygonContainsPoint(Point point,List<Point> mPoints ) {

        int nCross = 0;
        for (int i = 0; i < mPoints.size(); i++) {
            Point p1 = mPoints.get(i);
            Point p2 = mPoints.get((i + 1) % mPoints.size());
            // 取多边形任意一个边,做点point的水平延长线,求解与当前边的交点个数
            // p1p2是水平线段,要么没有交点,要么有无限个交点
            if (p1.lat == p2.lat)
                continue;
            // point 在p1p2 底部 --> 无交点
            if (point.lat < Math.min(p1.lat, p2.lat))
                continue;
            // point 在p1p2 顶部 --> 无交点
            if (point.lat >= Math.max(p1.lat, p2.lat))
                continue;
            // 求解 point点水平线与当前p1p2边的交点的 X 坐标
            double x = (point.lat - p1.lat) * (p2.lng - p1.lng) / (p2.lat - p1.lat) + p1.lng;
            if (x > point.lng) // 当x=point.x时,说明point在p1p2线段上
                nCross++; // 只统计单边交点
        }
        // 单边交点为偶数，点在多边形之外 ---
        return (nCross % 2 == 1);
    }
}
