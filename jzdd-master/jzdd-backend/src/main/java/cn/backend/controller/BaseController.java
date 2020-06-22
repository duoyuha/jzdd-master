package cn.backend.controller;

import cn.backend.model.primary.user.UserEntity;
import cn.backend.service.user.IUserService;
import cn.zdwl.common.context.BaseContextHandler;
import cn.zdwl.common.file.FileUtils;
import cn.zdwl.common.file.UploadFile;
import cn.zdwl.common.translate.TranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Set;

/**
 * 通用controller
 */
public abstract class BaseController {

    @Resource
    private IUserService userService;

    @Autowired
    protected TranslateService translateService;

    @Autowired
    private Validator validator;

    @Value("${file.savepath}")
    private String filePath;

    @Value("${file.viewpath}")
    private String viewpath;

    /**
     * 获取用户信息
     *
     * @return
     */
    protected UserEntity getUserEntity() {
        String userId = BaseContextHandler.getUserId();
        return userService.findById(userId);
    }

    /**
     * 获取上传文件列表
     *
     * @param folderName
     * @return
     */
    protected List<UploadFile> getFile(List<MultipartFile> files, String folderName) {
        List<UploadFile> fileList = new ArrayList<>();
        for (MultipartFile mf : files) {
            fileList.add(getFile(mf, folderName));
        }
        return fileList;
    }

    protected UploadFile getFile(MultipartFile file, String folderName) {
        return FileUtils.saveFile(file, filePath, folderName, viewpath);
    }

    /**
     * 校验
     *
     * @param obj
     * @param clazz
     */
    protected void validate(Object obj, Class clazz) {
        Set<ConstraintViolation<Object>> err = validator.validate(obj, clazz);
        if (!CollectionUtils.isEmpty(err)) {
            throw new ConstraintViolationException(err);
        }
    }

    /**
     * 获取公司
     *
     * @return
     */
    protected String getCorpNo() {
        return BaseContextHandler.getCorpNo();
    }


    /**
     * 设置下载名
     *
     * @param res
     * @param fileName
     */
    protected void setDownloadFileName(HttpServletResponse res, String fileName) {
        res.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
    }

    /**
     * 转化为String
     *
     * @param t
     * @return
     */
    protected String parseString(Object t) {
        if (null == t) {
            return "";
        } else {
            return t.toString();
        }
    }

    protected boolean crateFile(String encoded, String path) {
        File result = new File(path);
        try (FileOutputStream fos = new FileOutputStream(result)) {
            fos.write(Base64.getDecoder().decode(encoded));
            fos.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
