package pers.hzh.gss.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.hzh.gss.model.Marker;
import pers.hzh.gss.model.OverLay;
import pers.hzh.gss.model.Polygon;
import pers.hzh.gss.service.MarkerService;
import pers.hzh.gss.service.PolygonService;
import pers.hzh.gss.utils.OverLayUtil;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/index")
public class MapController {
    private static Logger logger = Logger.getLogger(MapController.class);
    @Autowired
    MarkerService markerService;
    @Autowired
    PolygonService polygonService;



    @RequestMapping(value = "/getMap",method = RequestMethod.GET)
    public String login(Model model){
        logger.info("GET map");
        return "index/grid_map";
    }
    @RequestMapping(value = "/addMarker",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Marker addMarker(Marker marker){
        logger.info("POST addMarker");
        int result=0;
        Marker result_marker=new Marker();
        try {
            result = markerService.addMarker(marker);
        }catch (Exception e){
            return result_marker;
        }
        if(result==1){
            result_marker=markerService.selectTheNewest();
            logger.info("RETURN result_marker");
        }
        return result_marker;
    }
    @RequestMapping(value = "/getNote",method = RequestMethod.POST,produces = "application/text;charset=UTF-8")
    @ResponseBody
    public String getNote(String id){
        logger.info("POST getNote :"+id);
        Marker result= markerService.getMarkerById(id);
        logger.info("RETURN "+result.getTitle()+"#"+result.getNote()+"#"+result.getLevel());
        return result.getTitle()+"#"+result.getNote()+"#"+result.getLevel();
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
    public String updataNote(int id,String title,String note,String level){
        logger.info("POST updataNote");
        int result= markerService.updataNote(id,title,note,level);
        return result+"";
    }
    @RequestMapping(value = "/deleteMarker",method = RequestMethod.POST,produces = "application/text;charset=UTF-8")
    @ResponseBody
    public String deleteMarker(String id){
        logger.info("POST deleteMarker");
        int result= markerService.deleteMarkerById(id);
        return result+"";
    }

    @RequestMapping(value = "/checkPolygon",method = RequestMethod.POST,produces = "application/text;charset=UTF-8")
    @ResponseBody
    public String checkPolygon(Polygon polygon){
        logger.info("POST checkPolygon");
        int result=1;
        List<Polygon> polygons=polygonService.getPolygonByParent(polygon.getParent());
        for(int i=0;i<polygons.size();i++){
            if(OverLayUtil.polygonIntersect(polygon,polygons.get(i))!=0){
                result=0;
            }
        }
        Polygon polygon1;
        if(polygon.getParent()!=0){
            polygon1=polygonService.getPolygonById(polygon.getParent());
            if(OverLayUtil.polygonIntersect(polygon,polygon1)!=2){
                result=-1;
            }
        }
        return result+"";
    }


    @RequestMapping(value = "/addPolygon",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Polygon addPolygon(Polygon polygon,HttpSession session){
        logger.info("POST addPolygon");
        int result=0;
        Polygon result_polygon=new Polygon();
        try {
            result = polygonService.addPolygon(polygon.getName(), polygon.getNote(), (String) session.getAttribute("manager_id"), polygon.getManager(), polygon.getParent(), polygon.getPoint());
        }catch (Exception e){
            logger.info(result_polygon.getId());
            return result_polygon;
        }
        if(result==1){
            result_polygon=polygonService.selectTheNewest();
        }
        logger.info(result_polygon.getId());
        return result_polygon;
    }
    @RequestMapping(value = "/getAllPolygon",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Polygon> getAllPolygon(){
        logger.info("POST getPolygonByName");
        List<Polygon> result=polygonService.getAllPolygon();
        return result;
    }
    @RequestMapping(value = "/getPolygonById",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Polygon getPolygonNewest(int  id){
        logger.info("POST getPolygonById");
        Polygon result=polygonService.getPolygonById(id);
        return result;
    }
}
