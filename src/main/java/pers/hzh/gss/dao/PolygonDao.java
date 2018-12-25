package pers.hzh.gss.dao;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import pers.hzh.gss.model.Polygon;

import java.util.List;

@MapperScan
public interface PolygonDao {
    int insertPolygon(@Param("name")String name,@Param("note")String note,@Param("maker")String maker,@Param("manager")String manager,@Param("parent")int parent,@Param("point")String point);
    List<Polygon> selectAllPolygon();
    Polygon selectPolygonById(@Param("id") int id);
    Polygon selectTheNewest();
    List<Polygon> selectPolygonByParent(@Param("parent") int parent);
}
