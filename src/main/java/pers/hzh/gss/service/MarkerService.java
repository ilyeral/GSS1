package pers.hzh.gss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.hzh.gss.dao.MarkerDao;
import pers.hzh.gss.model.Marker;

import java.util.List;

@Service
public class MarkerService {
    @Autowired
    MarkerDao markerDao;
    public int addMarker(Marker marker){
        return markerDao.insertMarker(marker.getTitle(), marker.getNote(), marker.getPoint(), marker.getType(), marker.getLevel());
    }
    public Marker getNote(String title){
        return markerDao.selectNoteByTitle(title);
    }
    public List<Marker> getAllMarker(){
        return markerDao.selectAllMarker();
    }
    public int updataNote(String title,String note,String level){
        return markerDao.updataNoteByTitle(title,note,level);
    }
    public int deleteMarkerByTitle(String title){
        return markerDao.deleteMarkerByTitle(title);
    }
}
