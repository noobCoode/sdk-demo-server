package com.ringcenter.sdkdemo.repo.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ringcenter.sdkdemo.repo.dao.IFileInfoDao;
import com.ringcenter.sdkdemo.repo.mapper.FileInfoMapper;
import com.ringcenter.sdkdemo.repo.po.FileInfoPO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xudongdong
 * @since 2020-04-30
 */
@Repository
public class FileInfoDaoImpl extends ServiceImpl<FileInfoMapper, FileInfoPO> implements IFileInfoDao {

    /**
     * query file
     * tips:title is unique
     *
     * @param title
     * @return
     */
    @Override
    public FileInfoPO queryFileByTitle(String title) {
        return this.baseMapper.queryFileByTitle(title);
    }

    /**
     * query all file
     *
     * @return
     */
    @Override
    public List<FileInfoPO> queryAllFile() {
        return this.baseMapper.queryAllFile();
    }

    /**
     * update file
     * tips:title is unique
     *
     * @param title
     * @param content
     * @return
     */
    @Override
    public void updateFileByTitle(String title, String content) {
        this.baseMapper.updateFileByTitle(title, content);
    }


}
