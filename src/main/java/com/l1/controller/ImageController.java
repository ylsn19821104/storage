package com.l1.controller;

import com.l1.entity.Image;
import com.l1.service.ImageService;
import com.l1.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luopotaotao on 2016/5/6.
 */
@Controller
@RequestMapping("image")
public class ImageController {
    @Autowired
    @Qualifier("imageServiceImpl")
    private ImageService imageService;

    @RequestMapping
    public String upload() {
        return "imageManage";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Boolean> upload(@RequestParam(required = true) MultipartFile img, Image image, HttpSession session) {
        Map<String,Boolean> ret = new HashMap<String, Boolean>();

        String contentType = img.getContentType();
        if (!contentType.startsWith("image/")) {
            ret.put("flag",false);
            return ret;
        }
        String path = session.getServletContext().getRealPath("WEB-INF/resources/images/upload");
        String originalFilename = img.getOriginalFilename();
        String fileName = null;
        String suffix = null;
        if (originalFilename != null && !originalFilename.isEmpty()) {
            suffix = originalFilename.substring(originalFilename.indexOf("."));
            image.setSuffix(suffix);
            if (suffix != null && !suffix.isEmpty()) {
                StringBuilder fileNameBuilder = new StringBuilder();
                fileNameBuilder.append(image.getId()).append(suffix);
                fileName = fileNameBuilder.toString();
            } else {
                fileName = String.valueOf(image.getId());
            }
        }
//        FileUtil.save(img,fileName);
        File targetFile = new File(path, fileName);

        if(targetFile.exists()){
            targetFile.delete();
        }
        //保存
        try {
            img.transferTo(targetFile);
            ret.put("flag",imageService.save(image)>0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> query(Integer page, Integer rows) {
        Map<String,Object> ret = new HashMap<String, Object>();
        List<Image> list = imageService.find(page, rows);
        int count = imageService.queryTotal();
        ret.put("total",count);
        ret.put("rows",list);
        return ret;
    }
    @RequestMapping("comboList")
    @ResponseBody
    public List<Image> queryAll(Integer page, Integer rows) {
        return imageService.findAll();
    }

    @RequestMapping(value = "findById",method = RequestMethod.GET)
    @ResponseBody
    public Image findById(int id){
        return imageService.findById(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Boolean> update(@RequestParam(required = false) MultipartFile img, Image image, HttpSession session) {
        Map<String,Boolean> ret = new HashMap<String, Boolean>();

        if(img!=null&&img.getSize()>0){
            String contentType = img.getContentType();
            if (!contentType.startsWith("image/")) {
                ret.put("flag",false);
                return ret;
            }
            String path = session.getServletContext().getRealPath("WEB-INF/resources/images/upload");
            String originalFilename = img.getOriginalFilename();
            String fileName = null;
            String suffix = null;
            if (originalFilename != null && !originalFilename.isEmpty()) {
                suffix = originalFilename.substring(originalFilename.indexOf("."));
                image.setSuffix(suffix);
                if (suffix != null && !suffix.isEmpty()) {
                    StringBuilder fileNameBuilder = new StringBuilder();
                    fileNameBuilder.append(image.getId()).append(suffix);
                    fileName = fileNameBuilder.toString();
                } else {
                    fileName = String.valueOf(image.getId());
                }
            }

//            FileUtil.save(img,fileName);
            File targetFile = new File(path, fileName);
            targetFile.delete();
            //保存
            try {
                img.transferTo(targetFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ret.put("flag",imageService.update(image)>0);

        return ret;
    }

    @RequestMapping("remove")
    @ResponseBody
    public Map<String,Object> remove(@RequestParam("ids[]") Integer[] ids){
        Map<String,Object> ret = new HashMap<String, Object>();
        List<Image> images = imageService.findByIds(ids);
        if(images!=null&&images.size()>0){
            String dir = this.getClass().getResource("/").toString().replace("file:/","").replace("classes/","")+"resources/images/upload";

            for(Image image:images){
//                FileUtil.remove(image.getId()+image.getSuffix());
                File file = new File(dir,image.getId()+image.getSuffix());
                if(file.exists()){
                    file.delete();
                }
            }

        }
        imageService.remove(ids);
        ret.put("flag",ids.length);
        return ret;
    }

}
