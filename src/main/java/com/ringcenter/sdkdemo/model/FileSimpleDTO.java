package com.ringcenter.sdkdemo.model;

import com.ringcenter.sdkdemo.repo.po.FileInfoPO;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Xu Dongdong
 * @date 2020-4-30
 */
@Data
@AllArgsConstructor
public class FileSimpleDTO {
    private String uuid;
    private String title;

    /**
     * po2dto
     *
     * @param fileInfoPO
     * @return
     */
    public static FileSimpleDTO convert2DTO(FileInfoPO fileInfoPO) {
        return new FileSimpleDTO(fileInfoPO.getId(), fileInfoPO.getTitle());
    }
}
