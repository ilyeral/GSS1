package pers.hzh.gss.dao;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import pers.hzh.gss.model.Marker;

import java.util.List;

@MapperScan
public interface MarkerDao {
    int insertMarker(@Param("title") String title, @Param("note") String note,@Param("point")String point,@Param("type")String type,@Param("level")String level);
    Marker selectMarkerById(@Param("id")String id);
    List<Marker> selectAllMarker();
    int updataNoteById(@Param("id")int id,@Param("title")String title,@Param("note")String note,@Param("level")String level);
    int deleteMarkerById(@Param("id")String id);
    Marker selectTheNewest();
}
