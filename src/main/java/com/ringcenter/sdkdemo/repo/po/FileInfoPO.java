package com.ringcenter.sdkdemo.repo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * <p>
 *
 * </p>
 *
 * @author xudongdong
 * @since 2020-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@TableName("file_info")
@AllArgsConstructor
public class FileInfoPO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String title;

    private String content;

    private LocalDateTime createTime;

    private LocalDateTime lastEditTime;

    public FileInfoPO(String id, String title, String content, Timestamp createTime, Timestamp lastEditTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createTime = createTime.toLocalDateTime();
        this.lastEditTime = lastEditTime == null ? null : lastEditTime.toLocalDateTime();
    }

    /**
     * init PO
     *
     * @param title
     * @param lastEditTime
     * @return
     */
    public static FileInfoPO createFileInfo(String title,
                                            String content,
                                            LocalDateTime lastEditTime) {
        return builder()
                .id(UUID.randomUUID().toString().replace("-", ""))
                .title(title)
                .content(content)
                .lastEditTime(lastEditTime)
                .createTime(LocalDateTime.now())
                .build();
    }


}
