package com.l1.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.l1.dao.RentDtlDao;
import com.l1.entity.RentDtl;
import org.springframework.stereotype.Service;

import com.l1.dao.RentDao;
import com.l1.entity.Rent;
import com.l1.service.RentService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("rentService")
public class RentServiceImpl implements RentService {
    @Resource
    private RentDao rentDao;

    @Resource
    private RentDtlDao rentDtlDao;

    @Override
    public List<Rent> find(Map<String, Object> map) {
        return rentDao.find(map);
    }

    @Override
    public Long getTotal(Map<String, Object> map) {
        return rentDao.getTotal(map);
    }

    @Override
    public Integer add(Rent rent) {
        return rentDao.add(rent);
    }

    @Override
    public Integer update(Rent rent) {
        return rentDao.update(rent);
    }

    @Override
    public Integer deleteById(Integer id) {
        return rentDao.deleteById(id);
    }

    @Override
    public Integer delete(String[] ids) {
        return rentDao.delete(ids);
    }



    @Override
    public List<Rent> findByIds(String ids) {
        return rentDao.findByIds(ids);
    }

    @Override
    public List<String> findNamesByIds(String ids) {
        return rentDao.findNamesByIds(ids);
    }

    @Override
    public Rent findById(Integer id) {
        return rentDao.findById(id);
    }

    @Override
    public int save(Rent rent) {
        int ret = rentDao.save(rent);
        return ret>0?rent.getId():-1;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public int saveRentWithDetails(Rent rent, List<RentDtl> details) {
        int id = this.save(rent);
        if(details!=null&&details.size()>0){
            for(RentDtl item:details){
                item.setId(id);
            }
            rentDtlDao.batchSave(details);
        }
        return id;
    }

}
