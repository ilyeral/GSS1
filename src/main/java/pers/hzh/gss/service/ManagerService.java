package pers.hzh.gss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.hzh.gss.dao.ManagerDao;
import pers.hzh.gss.model.Manager;

import java.util.List;

@Service
public class ManagerService {
    @Autowired
    private ManagerDao managerDao;

    /**
     * 通过ID查询
     * @param id
     * @return
     */
    public Manager selectByID(String id){
        return managerDao.selectByID(id);
    }

    /**
     * 添加manager
     * @param id
     * @param password
     * @return
     */
    public int insertManager(String id,String password){
        return managerDao.insertManager(id,password);
    }

    /**
     * 删除manager
     * @param id
     * @return
     */
    public int deleteManager(String id){
        return managerDao.deleteManager(id);
    }

    /**
     * 修改密码
     * @param password
     * @return
     */
    public int updatePassword(String id,String password){
        return managerDao.updatePassword(id,password);
    }
    public List<Manager> selectAllManager(){
        return managerDao.selectAllManager();
    }
}
