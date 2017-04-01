/**
 * 版权声明：开源随便使用
 * 日    期：16-8-1
 */
package com.nswt.osp.impl;

import com.nswt.osp.request.UploadFile;

import java.io.File;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class ExtUploadFile extends UploadFile {
    public ExtUploadFile(String fileType, byte[] content) {
        super(fileType, content);
    }

    public ExtUploadFile(File file) {
        super(file);
    }
}

