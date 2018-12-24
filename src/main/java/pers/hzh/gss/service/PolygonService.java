package pers.hzh.gss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.hzh.gss.dao.PolygonDao;
import pers.hzh.gss.model.Polygon;

import java.util.List;
@Service
public class PolygonService {
    @Autowired
    private PolygonDao polygonDao;
    public int addPolygon(String name,String note,String maker,String manager,int parent,String point){
        return polygonDao.insertPolygon(name,note,maker,manager,parent,point);
    }
    public List<Polygon> getAllPolygon(){
        return polygonDao.selectAllPolygon();
    }
    public Polygon getPolygonByName(String name){
        return polygonDao.selectPolygonByName(name);
    }
}
