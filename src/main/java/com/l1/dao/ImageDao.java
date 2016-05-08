package com.l1.dao;

import com.l1.entity.Image;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by luopotaotao on 2016/5/7.
 */
@Repository
public interface ImageDao {
    int save(Image image);
    List<Image> find();
    Image findById(int id);
    List<Image> findAll();
    int update(Image image);
    int batchUpdate(List<Image> images);
    int remove(List<Integer> ids);
}
