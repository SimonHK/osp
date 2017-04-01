/**
 * 版权声明： 开源随便使用
 * 日    期：16-8-1
 */
package com.nswt.osp.security;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public interface FileUploadController {

    /**
     * 上传文件的类型是否是允许
     * @param fileType
     * @return
     */
    boolean isAllowFileType(String fileType);

    /**
     * 是否超过了上传大小的限制
     * @param fileSize
     * @return
     */
    boolean isExceedMaxSize(int fileSize);

    /**
     * 获取支持上传的文件格式
     * @return
     */
    String getAllowFileTypes();

    /**
     * 获取最大的文件大小，单位为K
     * @return
     */
    int getMaxSize();
}

