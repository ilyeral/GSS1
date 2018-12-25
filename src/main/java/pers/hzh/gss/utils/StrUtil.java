package pers.hzh.gss.utils;

import org.apache.log4j.Logger;
import pers.hzh.gss.controller.ManagerController;
import pers.hzh.gss.model.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StrUtil {
    private static Logger logger = Logger.getLogger(StrUtil.class);
    /**
     * 生成随机字符串
     * @param length
     * @return
     */
    public static String RandomStr(int length){
        String str="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(36);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 生成随机数字串
     * @param length
     * @return
     */
    public static String RandomNum(int length){
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(10);
            sb.append(number);
        }
        return sb.toString();
    }

    /**
     * 随机生成指定长度16进制数
     * @param length
     * @return
     */
    public static String RandomHex(int length){
        String str="0123456789ABCDEF";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(16);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 不分大小写判断验证码
     * @param str1 传进来的
     * @param str2 生成的
     * @return
     */
    public static boolean strEquals(String str1,String str2){
        char[] ch1=str1.toCharArray();
        char[] ch2=str2.toCharArray();
        if(ch1.length==ch2.length){
            for(int i=0;i<ch1.length;i++){
                if(ch1[i]>='0'&&ch1[i]<='9'){
                    if(!(ch1[i]==ch2[i])){
                        return false;
                    }
                }else {
                    if (!(ch1[i] - ch2[i] == 32 || ch1[i] - ch2[i] == -32)) {
                        return false;
                    }
                }
            }
            return true;
        }

        return false;
    }
     public static List<Point> strToPointList(String point_list){
         String[] point_arr=point_list.split("#");
         List<Point> point=new ArrayList<Point>();
         for(int i=0;i<point_arr.length-1;i++){
             Point point_=new Point(point_arr[i].split(",")[0],point_arr[i].split(",")[1]);
             point.add(point_);
         }
        return point;
     }
}
