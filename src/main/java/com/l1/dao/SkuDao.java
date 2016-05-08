package com.l1.dao;

import java.util.List;
import java.util.Map;

import com.l1.entity.Sku;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SkuDao {

    public List<Sku> find(Map<String, Object> map);

    public List<Map<String, String>> findForCombo(Map<String, Object> map);
    public List<Map<String, String>> findForUploadCombo(Map<String, Object> map);

    public List<Sku> findByIds(String ids);

    public Sku findById(Integer id);

    public Long getTotal(Map<String, Object> map);

    public Integer add(Sku sku);

    public Integer update(Sku sku);

    public Integer deleteById(Integer id);

    public Integer deleteByItemId(Integer itemId);

    public Integer updateImageId(Integer id, Integer ImageId);

}
