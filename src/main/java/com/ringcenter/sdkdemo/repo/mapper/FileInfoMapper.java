package com.ringcenter.sdkdemo.repo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ringcenter.sdkdemo.repo.po.FileInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xudongdong
 * @since 2020-04-30
 */
@Mapper
public interface FileInfoMapper extends BaseMapper<FileInfoPO> {

    /**
     * query file
     * tips:title is unique
     *
     * @param title
     * @return
     */
    @Select("select * from file_info where title=#{title} limit 1")
    FileInfoPO queryFileByTitle(String title);


    /**
     * query all file
     * tips:title is unique
     *
     * @return
     */
    @Select("select * from file_info")
    List<FileInfoPO> queryAllFile();

    /**
     * update file
     * tips:title is unique
     *
     * @param title
     * @param content
     * @return
     */
    @Update("update file_info set content=#{content} where title=#{title} ")
    void updateFileByTitle(String title, String content);
}
