package pers.hzh.gss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.hzh.gss.dao.OverLayDao;
import pers.hzh.gss.model.OverLay;

import java.util.List;

@Service
public class OverLayService {
    @Autowired
    OverLayDao overLayDao;
    public int addOverLay(OverLay overLay){
        return overLayDao.insertOverLay(overLay.getTitle(),overLay.getNote(),overLay.getPoint(),overLay.getType(),overLay.getLevel());
    }
    public OverLay getNote(String title){
        return overLayDao.selectNoteByTitle(title);
    }
    public List<OverLay> getAllOverLay(){
        return overLayDao.selectAllOverLay();
    }
    public int updataNote(String title,String note,String level){
        return overLayDao.updataNoteByTitle(title,note,level);
    }
    public int deleteOverLayByTitle(String title){
        return overLayDao.deleteOverLayByTitle(title);
    }
}
