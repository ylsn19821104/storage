package com.l1.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.l1.dao.RentDtlDao;
import com.l1.dao.SeqDao;
import com.l1.entity.RentDtl;
import com.l1.entity.Seq;
import com.l1.service.SeqService;
import org.springframework.stereotype.Service;

import com.l1.dao.RentDao;
import com.l1.entity.Rent;
import com.l1.service.RentService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("rentService")
public class RentServiceImpl implements RentService {
    @Resource
    private RentDao rentDao;

    @Resource
    private RentDtlDao rentDtlDao;

    @Resource
    private SeqService seqService;

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
        return ret > 0 ? rent.getId() : -1;
    }

    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRES_NEW)
    @Override
    public int saveRentWithDetails(Rent rent, List<RentDtl> details) {
        String billNo = seqService.next("CZ");
        rent.setBillNo(billNo);
        int id = this.save(rent);
        if (details != null && details.size() > 0) {
            for (RentDtl item : details) {
                item.setId(id);
            }
            rentDtlDao.batchSave(details);
        }
        return id;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public int updateWithDetails(Rent rent, List<RentDtl> inserted,List<RentDtl> updated,Integer[] ids) {
        int id = this.update(rent);
        rentDtlDao.deleteByRentId(rent.getId());
        if (inserted != null && inserted.size() > 0) {
            for (RentDtl item : inserted) {
                item.setId(rent.getId());
            }
            rentDtlDao.batchSave(inserted);
        }
        if (updated != null && updated.size() > 0) {
            for (RentDtl item : updated) {
                item.setId(rent.getId());
                rentDtlDao.update(item);
            }
        }
        if(ids!=null&&ids.length>0){
            rentDtlDao.delete(ids);
        }
        return id;
    }

    @Override
    public int finish(Integer[] ids) {
        //TODO 将出租明细数量添加回仓库
        return rentDao.finish(ids);
    }

}
