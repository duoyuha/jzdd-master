package cn.backend.service.pile;

import cn.backend.model.primary.pile.PileEntity;
import cn.backend.model.primary.pile.PileQuery;
import cn.backend.service.IBaseService;
import cn.zdwl.common.file.UploadFile;

import java.util.List;

/**
 * @author sunkw
 * @date 2019/07/08
 */
public interface IPileService extends IBaseService<PileEntity, PileQuery> {

    /**
     * 根据编号查找
     *
     * @param no
     * @return
     */
    PileEntity findByNo(String no);

    /**
     * 根据名称查找
     *
     * @param no
     * @param corpNo
     * @return
     */
    PileEntity findByNameAndCorp(String no, String corpNo);

    /**
     * 新建和上传
     *
     * @param entity
     * @param uploadFiles
     * @return
     */
    PileEntity addAndUpload(PileEntity entity, List<UploadFile> uploadFiles);

    /**
     * 编辑和上传
     *
     * @param entity
     * @param uploadFiles
     * @return
     */
    PileEntity editAndUpload(PileEntity entity, List<UploadFile> uploadFiles);

}

