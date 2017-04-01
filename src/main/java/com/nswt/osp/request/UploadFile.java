/**
 * 版权声明： 开源随便使用
 * 日    期：16-8-1
 */
package com.nswt.osp.request;

import com.nswt.osp.annotation.IgnoreSign;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;

/**
 * <pre>
 *    上传的文件
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
@IgnoreSign
public class UploadFile {

    private String fileType;

    private byte[] content;

    /**
     * 根据文件内容构造
     *
     * @param content
     */
    public UploadFile(String fileType, byte[] content) {
        this.content = content;
        this.fileType = fileType;
    }

    /**
     * 根据文件构造
     * @param file
     */
    public UploadFile(File file) {
        try {
            this.content = FileCopyUtils.copyToByteArray(file);
            this.fileType = file.getName().substring(file.getName().lastIndexOf('.')+1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getFileType() {
        return fileType;
    }

    public byte[] getContent() {
        return content;
    }
}


