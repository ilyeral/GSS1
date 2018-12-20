package pers.hzh.gss.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.hzh.gss.model.OverLay;
import pers.hzh.gss.service.OverLayService;

import java.util.List;

@Controller
@RequestMapping("/index")
public class MapController {
    private static Logger logger = Logger.getLogger(MapController.class);
    @Autowired
    OverLayService overLayService;

    @RequestMapping(value = "/getMap",method = RequestMethod.GET)
    public String login(Model model){
        logger.info("GET map");
        return "index/grid_map";
    }
    @RequestMapping(value = "/addOverLay",method = RequestMethod.POST,produces = "application/text;charset=UTF-8")
    @ResponseBody
    public String addOverLay(OverLay overLay){
        logger.info("POST addOverLay");
        int result=overLayService.addOverLay(overLay);
        return result+"";
    }
    @RequestMapping(value = "/getNote",method = RequestMethod.POST,produces = "application/text;charset=UTF-8")
    @ResponseBody
    public String getNote(String title){
        logger.info("POST getNote :"+title);
        String result=overLayService.getNote(title);
        logger.info("RETURN "+result);
        return result+"";
    }
    @RequestMapping(value = "/getAllOverLay",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<OverLay> getAllOverLay(){
        logger.info("GET getAllOverLay");
        List<OverLay> result=overLayService.getAllOverLay();
        return result;
    }
    @RequestMapping(value = "/updataNote",method = RequestMethod.POST,produces = "application/text;charset=UTF-8")
    @ResponseBody
    public String updataNote(String title,String note){
        logger.info("GET getAllOverLay");
        int result=overLayService.updataNote(title,note);
        return result+"";
    }
    @RequestMapping(value = "/deleteOverLay",method = RequestMethod.POST,produces = "application/text;charset=UTF-8")
    @ResponseBody
    public String deleteOverLay(String title){
        logger.info("GET getAllOverLay");
        int result=overLayService.deleteOverLayByTitle(title);
        return result+"";
    }
}
