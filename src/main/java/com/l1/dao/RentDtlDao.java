package com.l1.dao;

import com.l1.entity.RentDtl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RentDtlDao {
    List<RentDtl> find(Map<String, Object> map);

    List<RentDtl> findByIds(String ids);

    RentDtl findById(Integer id);

    List<String> findNamesByIds(String ids);

    Long getTotal(Map<String, Object> map);

    Integer add(RentDtl color);

    Integer update(RentDtl color);

    Integer deleteById(Integer id);

    Integer deleteByRentId(Integer id);

    void delete(Integer[] ids);

    int batchSave(List<RentDtl> details);
}
