package pers.hzh.gss.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.hzh.gss.model.Marker;
import pers.hzh.gss.service.MarkerService;

import java.util.List;

@Controller
@RequestMapping("/index")
public class MapController {
    private static Logger logger = Logger.getLogger(MapController.class);
    @Autowired
    MarkerService markerService;

    @RequestMapping(value = "/getMap",method = RequestMethod.GET)
    public String login(Model model){
        logger.info("GET map");
        return "index/grid_map";
    }
    @RequestMapping(value = "/addMarker",method = RequestMethod.POST,produces = "application/text;charset=UTF-8")
    @ResponseBody
    public String addMarker(Marker marker){
        logger.info("POST addMarker");
        int result= markerService.addMarker(marker);
        return result+"";
    }
    @RequestMapping(value = "/getNote",method = RequestMethod.POST,produces = "application/text;charset=UTF-8")
    @ResponseBody
    public String getNote(String title){
        logger.info("POST getNote :"+title);
        Marker result= markerService.getNote(title);
        logger.info("RETURN "+result.getNote()+"#"+result.getLevel());
        return result.getNote()+"#"+result.getLevel();
    }
    @RequestMapping(value = "/getAllMarker",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Marker> getAllMarker(){
        logger.info("GET getAllMarker");
        List<Marker> result= markerService.getAllMarker();
        return result;
    }
    @RequestMapping(value = "/updataNote",method = RequestMethod.POST,produces = "application/text;charset=UTF-8")
    @ResponseBody
    public String updataNote(String title,String note,String level){
        logger.info("GET updataNote");
        int result= markerService.updataNote(title,note,level);
        return result+"";
    }
    @RequestMapping(value = "/deleteMarker",method = RequestMethod.POST,produces = "application/text;charset=UTF-8")
    @ResponseBody
    public String deleteMarker(String title){
        logger.info("GET getAllMarker");
        int result= markerService.deleteMarkerByTitle(title);
        return result+"";
    }
}
