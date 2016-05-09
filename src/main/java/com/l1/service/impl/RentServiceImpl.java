package com.l1.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.l1.dao.RentDtlDao;
import com.l1.dao.SeqDao;
import com.l1.entity.RentDtl;
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
    private SeqDao seqDao;

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

    @Transactional(isolation = Isolation.SERIALIZABLE,propagation = Propagation.REQUIRES_NEW)
    @Override
    public int saveRentWithDetails(Rent rent, List<RentDtl> details) {
        String billNo = generateBillNo();
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

    private String generateBillNo(){
        StringBuilder sb = new StringBuilder();
        String str0 = "CZ";
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String str1 = format.format(new Date());
        seqDao.add(str0);
        int number = seqDao.find(str0);
        String str2 = String.format("%1$,04d", number);
        return sb.append(str0).append(str1).append(str2).toString();
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public int updateWithDetails(Rent rent, List<RentDtl> details) {
        int id = this.update(rent);
        rentDtlDao.deleteByRentId(rent.getId());
        if (details != null && details.size() > 0) {
            for (RentDtl item : details) {
                item.setId(rent.getId());
            }
            rentDtlDao.batchSave(details);
        }
        return id;
    }

    @Override
    public int finish(Integer[] ids) {
        //TODO 将出租明细数量添加回仓库
        return rentDao.finish(ids);
    }

}
