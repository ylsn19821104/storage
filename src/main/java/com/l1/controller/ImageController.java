package com.l1.controller;

import com.l1.entity.Image;
import com.l1.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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

        File targetFile = new File(path, fileName);

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
    public List<Image> query(Integer page, Integer rows) {
        return imageService.find(page, rows);
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

        if(img!=null){
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


}
