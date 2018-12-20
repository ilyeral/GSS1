package pers.hzh.gss.dao;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import pers.hzh.gss.model.Manager;

@MapperScan
public interface ManagerDao {
    /**
     * 通过ID查询
     * @param id
     * @return
     */
    Manager selectByID(@Param("id") String id);

    /**
     * 添加manager
     * @param id
     * @param password
     * @return
     */
    int insertManager(@Param("id")String id,@Param("password")String password);

    /**
     * 删除manager
     * @param id
     * @return
     */
    int deleteManager(@Param("id")String id);

    /**
     * 修改密码
     * @param id
     * @param password
     * @return
     */
    int updatePassword(@Param("id")String id,@Param("password")String password);
}
