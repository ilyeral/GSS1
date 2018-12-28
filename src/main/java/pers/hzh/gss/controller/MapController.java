package pers.hzh.gss.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
    public String checkPolygon(Polygon polygon){//返回1 不相交 返回0 相交 返回-1 超出父区
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
    public Polygon addPolygon(Polygon polygon,HttpSession session){//可以创建大区 管理者可以创建小区
        logger.info("POST addPolygon");
        int result=0;
        Polygon result_polygon=new Polygon();
        logger.info("new result_polygon");
        if(!session.getAttribute("manager_id").equals("000001")){
            logger.info(session.getAttribute("manager_id"));
           logger.info("manager_id!=000001");
           Polygon polygon1=polygonService.getPolygonById(polygon.getParent());
           if(!polygon1.getManager().equals(session.getAttribute("manager_id"))){
               result_polygon.setId(-1);
               return result_polygon;
           }
        }
        try {
            logger.info("try addPolygon");
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
        logger.info(result.get(0).getDate());
        return result;
    }
    @RequestMapping(value = "/getPolygonById",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Polygon getPolygonNewest(int  id){
        logger.info("POST getPolygonById");
        Polygon result=polygonService.getPolygonById(id);
        return result;
    }
    @RequestMapping(value = "/getPolygonByParent",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Polygon> getPolygonByParent(int  parent){
        logger.info("POST getPolygonByParent");
        List<Polygon> result=polygonService.getPolygonByParent(parent);
        return result;
    }
    @RequestMapping(value = "/updatePolygon",method = RequestMethod.POST,produces = "application/text;charset=UTF-8")
    @ResponseBody
    public String updatePolygon(Polygon polygon){//
        logger.info("POST updatePolygon");
        int check=1;
        List<Polygon> polygons=polygonService.getPolygonByParent(polygon.getParent());
        List<Polygon> polygons1=polygonService.getPolygonByParent(polygon.getId());
        for(int i=0;i<polygons.size();i++){
            if(polygon.getId()!=polygons.get(i).getId()) {
                if (OverLayUtil.polygonIntersect(polygon, polygons.get(i)) != 0) {
                    logger.info("与同级冲突:" + i);
                    check = 0;
                }
            }
        }
        for(int i=0;i<polygons1.size();i++){
            if(OverLayUtil.polygonIntersect(polygon,polygons1.get(i))!=3){
                logger.info("与子块冲突:"+i);
                check=0;
            }
        }
        Polygon polygon1;
        if(polygon.getParent()!=0){
            polygon1=polygonService.getPolygonById(polygon.getParent());
            if(OverLayUtil.polygonIntersect(polygon,polygon1)!=2){
                logger.info("与父级冲突");
                check=-1;
            }
        }
        int result=0;
        if(check==1){
            result=polygonService.updatePolygon(polygon.getId(), polygon.getName(), polygon.getNote(), polygon.getManager(), polygon.getPoint());
        }
        logger.info("check:"+check);
        return result+"";
    }
    @RequestMapping(value = "/deletePolygonById",method = RequestMethod.POST,produces = "application/text;charset=UTF-8")
    @ResponseBody
    @Transactional
    public String deletePolygonById(Polygon polygon,HttpSession session){//只有boss和创建者有权限删除
        logger.info("POST deletePolygonById");
        if(!session.getAttribute("manager_id").equals("000001")){
            Polygon polygon1=polygonService.getPolygonById(polygon.getId());
            if(!polygon1.getMaker().equals(session.getAttribute("manager_id"))){
                return "-1";
            }
        }
        int result=polygonService.deletePolygonById(polygon.getId());
        int result1=polygonService.deletePolygonByParent(polygon.getId());
        return result+"";
    }
}
