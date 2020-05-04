package com.ringcenter.sdkdemo.repo.dao;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ringcenter.sdkdemo.repo.po.FileInfoPO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xudongdong
 * @since 2020-04-30
 */
public interface IFileInfoDao extends IService<FileInfoPO> {

    /**
     * query file
     * tips:title is unique
     *
     * @param title
     * @return
     */
    FileInfoPO queryFileByTitle(String title);

    /**
     * query all file
     *
     * @return
     */
    List<FileInfoPO> queryAllFile();

    /**
     * update file
     * tips:title is unique
     *
     * @param title
     * @param content
     * @return
     */
    void updateFileByTitle(String title, String content);
}
