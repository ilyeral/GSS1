package pers.hzh.gss.dao;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import pers.hzh.gss.model.OverLay;

import java.util.List;

@MapperScan
public interface OverLayDao {
    int insertOverLay(@Param("title") String title, @Param("note") String note,@Param("point")String point,@Param("type")String type);
    String selectNoteByTitle(@Param("title")String title);
    List<OverLay> selectAllOverLay();
    int updataNoteByTitle(@Param("title")String title,@Param("note")String note);
    int deleteOverLayByTitle(@Param("title")String title);
}
