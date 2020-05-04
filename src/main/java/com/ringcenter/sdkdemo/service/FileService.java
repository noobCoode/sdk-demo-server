package com.ringcenter.sdkdemo.service;

import com.ringcenter.sdkdemo.enums.ErrorEnum;
import com.ringcenter.sdkdemo.model.FileDetailDTO;
import com.ringcenter.sdkdemo.model.FileInfo;
import com.ringcenter.sdkdemo.model.FileSimpleDTO;
import com.ringcenter.sdkdemo.model.ResultBean;
import com.ringcenter.sdkdemo.repo.dao.IFileInfoDao;
import com.ringcenter.sdkdemo.repo.po.FileInfoPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Xu Dongdong
 * @date 2020-4-30
 */
@Service
public class FileService {

    private IFileInfoDao fileInfoDao;

    @Value("/temp.txt")
    private String txtRelationPath;

    @Autowired
    public FileService(IFileInfoDao fileInfoDao) {
        this.fileInfoDao = fileInfoDao;
    }

    /**
     * save file
     *
     * @param fileInfo
     * @return
     */
    public ResultBean saveFile(FileInfo fileInfo) {
        //1、do2po
        FileInfoPO fileInfoPO = FileInfoPO.createFileInfo(fileInfo.getTitle(), fileInfo.getContent(), LocalDateTime.now());
        //2、 save po
        boolean isSuccess = fileInfoDao.save(fileInfoPO);
        if (isSuccess) {
            return ResultBean.newSuccess(FileSimpleDTO.convert2DTO(fileInfoPO));
        } else {
            return ResultBean.newFail(ErrorEnum.SAVE_FAIL, "");
        }
    }

    /**
     * 根据title下载文件
     *
     * @param title
     * @param httpServletResponse
     * @throws IOException
     */
    public void downloadFileByTitle(String title, HttpServletResponse httpServletResponse) throws IOException {
        FileInfoPO fileInfoPO = fileInfoDao.queryFileByTitle(title);
        downloadTxt(fileInfoPO, httpServletResponse);
    }

    /**
     * 下载txt
     *
     * @param fileInfoPO
     * @param response
     * @throws IOException
     */
    private void downloadTxt(FileInfoPO fileInfoPO, HttpServletResponse response) throws IOException {
        //清空输出流
        response.resetBuffer();
        // 设定输出文件头
        response.setHeader("Content-disposition", String.format("attachment; filename=%s.txt", fileInfoPO.getTitle()));
        // 定义输出类型
        response.setContentType("application/octet-stream");
        //获取输出流
        OutputStream os = new BufferedOutputStream(response.getOutputStream());
        //写内容
        os.write(fileInfoPO.getContent().getBytes());
        os.flush();
        os.close();
    }

    /**
     * update file
     *
     * @param title
     * @param fileInfo
     * @return
     */
    public ResultBean updateFile(String title, FileInfo fileInfo) {
        //query
        FileInfoPO fileInfoPO = fileInfoDao.queryFileByTitle(title);
        //update
        fileInfoDao.updateFileByTitle(title, fileInfo.getContent());
        //po2dto
        return ResultBean.newSuccess(FileSimpleDTO.convert2DTO(fileInfoPO));

    }

    /**
     * 获取文件详情
     *
     * @param title
     * @return
     */
    public ResultBean getFileDetail(String title) {
        FileInfoPO fileInfoPO = fileInfoDao.queryFileByTitle(title);
        //如果能查到，并且可更新
        if (fileInfoPO == null) {
            return ResultBean.newFail(ErrorEnum.NO_FILE, "");
        }
        return ResultBean.newSuccess(FileDetailDTO.convert2DTO(fileInfoPO));
    }

    /**
     * 获取简略详情的list
     *
     * @return
     */
    public ResultBean getFileSimpleList() {
        List<FileInfoPO> fileInfoPOS = fileInfoDao.queryAllFile();
        return ResultBean.newSuccess(fileInfoPOS);
    }
}
