/**
 * 版权声明： 开源随便使用
 * 日    期：16-8-1
 */
package com.nswt.osp.request;

/**
 * <pre>
 *   将以BASE64位编码字符串转换为字节数组的{@link UploadFile}对象
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class UploadFileConverter implements OspConverter<String, UploadFile> {


    public UploadFile convert(String source) {
        String fileType = UploadFileUtils.getFileType(source);
        byte[] contentBytes = UploadFileUtils.decode(source);
        return new UploadFile(fileType, contentBytes);
    }


    public String unconvert(UploadFile target) {
        return UploadFileUtils.encode(target);
    }


    public Class<String> getSourceClass() {
        return String.class;
    }


    public Class<UploadFile> getTargetClass() {
        return UploadFile.class;
    }
}

