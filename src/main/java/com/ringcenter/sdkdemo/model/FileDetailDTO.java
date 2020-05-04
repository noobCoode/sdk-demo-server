package com.ringcenter.sdkdemo.model;

import com.ringcenter.sdkdemo.repo.po.FileInfoPO;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Xu Dongdong
 * @date 2020-5-1
 */
@Data
@AllArgsConstructor
public class FileDetailDTO {
    private String title;
    private String content;

    /**
     * po2dto
     *
     * @param fileInfoPO
     * @return
     */
    public static FileDetailDTO convert2DTO(FileInfoPO fileInfoPO) {
        return new FileDetailDTO(fileInfoPO.getTitle(), fileInfoPO.getContent());
    }
}
