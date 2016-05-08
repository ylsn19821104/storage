package com.l1.service;

import com.l1.entity.Image;

import java.util.List;

/**
 * Created by luopotaotao on 2016/5/7.
 */
public interface ImageService {
    int save(Image image);
    List<Image> find(Integer page,Integer rows);
    List<Image> findAll();
    Image findById(int id);
    int update(Image image);
    int batchUpdate(List<Image> images);
    int remove(Integer[] ids);

    int queryTotal();

    List<Image> findByIds(Integer[] ids);
}
