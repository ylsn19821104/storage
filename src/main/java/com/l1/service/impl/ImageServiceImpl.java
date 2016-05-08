package com.l1.service.impl;

import com.l1.dao.ImageDao;
import com.l1.dao.SkuDao;
import com.l1.entity.Image;
import com.l1.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luopotaotao on 2016/5/7.
 */
@Service("imageServiceImpl")
public class ImageServiceImpl implements ImageService{
    @Autowired
    private ImageDao imageDao;

    @Override
    public int save(Image image) {
        int ret = imageDao.save(image);
        return ret;
    }

    @Override
    public List<Image> find(Integer page, Integer rows) {
        return imageDao.find();
    }
    @Override
    public List<Image> findAll() {
        return imageDao.findAll();
    }

    @Override
    public Image findById(int id) {
        return imageDao.findById(id);
    }

    @Override
    public int update(Image image) {
        return imageDao.update(image);
    }

    @Override
    public int batchUpdate(List<Image> images) {
        return imageDao.batchUpdate(images);
    }

    @Override
    public int remove(List<Integer> ids) {
        return imageDao.remove(ids);
    }
}
