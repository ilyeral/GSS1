package pers.hzh.gss.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ImageUtil {
    public static BufferedImage ImageCode(String textCode){
        int width=99;
        int height=38;
        int interLine=4;//干扰线
        Color backColor=new Color(242,242,242);
        Color lineColor=null;
        Color foreColor=null;
        boolean randomLocation=false;
        //创建BufferedImage对象
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Random r=new Random();

        //绘制背景
        g.setColor(backColor==null?getRandomColor():backColor);
        g.fillRect(0,0,width,height);

        //绘制干扰线
        if(interLine>0){
            int x=r.nextInt(4),y=0;
            int x1=width-r.nextInt(4),y1=0;
            for(int i=0;i<interLine;i++){
                g.setColor(lineColor==null?getRandomColor():lineColor);
                y=r.nextInt(height-r.nextInt(4));
                y1=r.nextInt(height-r.nextInt(4));
                g.drawLine(x,y,x1,y1);
            }
        }

        //写验证码
        int fsize=(int)(height*0.8);//字体大小为图片高度的80%
        int fx=0;
        int fy=fsize;
        g.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,fsize));


        //写字符
        for(int i=0;i<textCode.length();i++){
            fy=randomLocation?(int)((Math.random()*0.3+0.6)*height):fy;//每个字符高低是否随机
            g.setColor(foreColor==null?getRandomColor():foreColor);
            g.drawString(textCode.charAt(i)+"",fx,fy);
            fx+=(width / textCode.length()) * (Math.random() * 0.3 + 0.8); //依据宽度浮动
        }

        //扭曲图片
        shearX(g, width, height, backColor);
        shearY(g, width, height, backColor);

        //添加噪点
        float yawpRate = 0.05f;// 噪声率
        int area = (int) (yawpRate * width * height);//噪点数量
        for (int i = 0; i < area; i++) {
            int xxx = r.nextInt(width);
            int yyy = r.nextInt(height);
            int rgb = getRandomColor().getRGB();
            image.setRGB(xxx, yyy, rgb);
        }

        g.dispose();
        return image;
    }

    /**
     * 随机颜色--在180内
     * @return
     */
    public static Color getRandomColor(){
        Random random=new Random();
        int r=random.nextInt(180);
        int g=random.nextInt(180);
        int b=random.nextInt(180);
        return new Color(r,g,b);
    }

    /**
     * 扭曲图片
     * @param g
     * @param w1
     * @param h1
     * @param color
     */
    public static void shearX(Graphics g, int w1, int h1, Color color) {
        Random random=new Random();
        int period = 2;

        boolean borderGap = true;
        int frames = 1;
        int phase = random.nextInt(2);

        for (int i = 0; i < h1; i++) {
            double d = (double) (period >> 1)* Math.sin((double) i / (double) period
                    + (2.2831853071795862D * (double) phase)/ (double) frames);
            g.copyArea(0, i, w1, 1, (int) d, 0);
            if (borderGap) {
                g.setColor(color);
                g.drawLine((int) d, i, 0, i);
                g.drawLine((int) d + w1, i, w1, i);
            }
        }

    }

    /**
     * 扭曲图片
     * @param g
     * @param w1
     * @param h1
     * @param color
     */
    public static void shearY(Graphics g, int w1, int h1, Color color) {
        Random random = new Random();
        int period = random.nextInt(40) + 10; // 50;

        boolean borderGap = true;
        int frames = 5;
        int phase = random.nextInt(1);
        for (int i = 0; i < w1; i++) {
            double d = (double) (period >> 2)
                    * Math.sin((double) i / (double) period
                    + (2.2831853071795862D * (double) phase) / (double) frames);
            g.copyArea(i, 0, 1, h1, 0, (int) d);
            if (borderGap) {
                g.setColor(color);
                g.drawLine(i, (int) d, i, 0);
                g.drawLine(i, (int) d + h1, i, h1);
            }

        }
    }
}
