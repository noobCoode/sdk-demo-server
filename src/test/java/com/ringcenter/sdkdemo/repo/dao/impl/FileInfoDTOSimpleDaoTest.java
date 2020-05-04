package com.ringcenter.sdkdemo.repo.dao.impl;

import com.ringcenter.sdkdemo.model.FileInfo;
import com.ringcenter.sdkdemo.repo.dao.IFileInfoDao;
import com.ringcenter.sdkdemo.repo.po.FileInfoPO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Xu Dongdong
 * @date 2020-4-30
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class FileInfoDTOSimpleDaoTest {

    @Autowired
    private IFileInfoDao fileInfoDao;

    //region properties
    private static final String TITLE = "测试2";
    private static final String CONTENT = "我就是测试一下";
    //endregion

    @Test
    void insert_should_success() {
        FileInfoPO fileInfoPO = FileInfoPO.createFileInfo(TITLE, CONTENT, null);
        boolean res = fileInfoDao.save(fileInfoPO);
        Assertions.assertTrue(res);
    }

    @Test
    void query_should_success() {
        FileInfoPO fileInfoPO = fileInfoDao.queryFileByTitle(TITLE);
        Assertions.assertEquals(CONTENT, fileInfoPO.getContent());
    }

    @Test
    void query_all_file_should_have_2(){
        List<FileInfoPO> fileInfoPOS=fileInfoDao.queryAllFile();
        Assertions.assertEquals(2,fileInfoPOS.size());
    }

    @Test
    @Transactional
    @Rollback
    void update_should_success() {
        String changedContent = "changed content";
        fileInfoDao.updateFileByTitle(TITLE, changedContent);
        FileInfoPO fileInfoPO=fileInfoDao.queryFileByTitle(TITLE);
        Assertions.assertEquals(changedContent,fileInfoPO.getContent());
    }
}