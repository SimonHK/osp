/**
 * 版权声明： 开源随便使用
 * 日    期：16-8-1
 */
package com.nswt.osp.request;

/**
 * <pre>
 *    上传文件字符串转换时发生错误
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class IllegalUploadFileFormatException extends IllegalArgumentException {

    public IllegalUploadFileFormatException() {
        super();
    }

    public IllegalUploadFileFormatException(String s) {
        super(s);
    }

    public IllegalUploadFileFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalUploadFileFormatException(Throwable cause) {
        super(cause);
    }
}

