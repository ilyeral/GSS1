package pers.hzh.gss.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.hzh.gss.model.Manager;
import pers.hzh.gss.model.Polygon;
import pers.hzh.gss.service.ManagerService;
import pers.hzh.gss.utils.StrUtil;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    private static Logger logger = Logger.getLogger(ManagerController.class);


    ManagerController(){
        logger.info("ManagerController create");
    }

    /**
     * 返回登录界面
     * @param model
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Model model){
        logger.info("GET login");
        return "login";
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(Model model){
        logger.info("GET index");
        return "index";
    }

    /**
     * 登录验证
     * @param manager
     * @param session
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST,produces = "application/text;charset=UTF-8")
    @ResponseBody
    private String list(Manager manager,HttpSession session) {//返回字符串
        logger.info("POST login");
        logger.info("Manager  "+"id:"+manager.getId());
        if(session.getAttribute("need_vericode")==null||
                session.getAttribute("need_vericode").equals("0")){
            //不需要验证码
            logger.info("不需要验证码login");
            return loginDET(manager,session);
        }else{
            //需要验证码
            logger.info("需要验证码:"+session.getAttribute("imageCode"));
            logger.info("验证码:"+manager.getVericode());
            String code=(String) session.getAttribute("imageCode");
            if(StrUtil.strEquals(manager.getVericode(),code)){
                //验证码正确
                logger.info("验证码正确");
                return loginDET(manager,session);
            }else if(manager.getVericode().equals("")) {
                logger.info("验证码为空");
                return "code_empty";
            }else {
                logger.info("验证码错误");
                //返回验证码错误
                return "code_fail";
            }
        }
    }

    @RequestMapping(value = "/logout.do",method = RequestMethod.GET)
    @ResponseBody
    public String logout(HttpSession session){
        logger.info("GET logout");
        session.setAttribute("manager_id", null);
        return "logout";
    }
    @RequestMapping(value = "/getColorById",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getColorById(String  id){
        logger.info("POST getColorById");
        Manager result=managerService.selectByID(id);

        return result.getColor();
    }
    @RequestMapping(value = "/getAllManager",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Manager> getAllManager(){
        logger.info("POST getAllManager");
        List<Manager> result=managerService.selectAllManager();
        return result;
    }

    private String loginDET(Manager manager,HttpSession session){
        Manager theManager=managerService.selectByID(manager.getId());
        String password="";
        if( theManager!=null){
            password=theManager.getPassword();
        }else{
            logger.info("无此账号");
            return "fail";
        }
        if(manager.getPassword().equals(password)){
            //登陆成功
            logger.info("登陆成功");
            session.setAttribute("manager_id", manager.getId());
            return "success";
        }else{
            logger.info("登录失败");
            session.setAttribute("need_vericode", "1");
            return "fail";
        }
    }
}
