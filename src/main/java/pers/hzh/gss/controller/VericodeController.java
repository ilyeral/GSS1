package pers.hzh.gss.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pers.hzh.gss.utils.ImageUtil;
import pers.hzh.gss.utils.StrUtil;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.OutputStream;

@Controller
public class VericodeController {
    private static Logger logger = Logger.getLogger(VericodeController.class);

    @RequestMapping("/vericode.do")
    public void valicode(HttpServletResponse response,HttpSession session) throws Exception{
        logger.info("CREATE imageCode");
        String code=StrUtil.RandomStr(4);

        //将验证码存入Session
        session.setAttribute("imageCode",code);
        //将图片输出给浏览器
        BufferedImage image = ImageUtil.ImageCode(code);
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
        os.close();
    }
}
