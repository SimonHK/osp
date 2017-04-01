/**
 * 版权声明：开源随便使用
 * 日    期：16-8-1
 */
package com.knowlegene.sample.request;

import com.nswt.osp.AbstractOspRequest;
import com.nswt.osp.request.UploadFile;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class UploadUserPhotoRequest extends AbstractOspRequest {

    private String userId;

    private UploadFile photo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UploadFile getPhoto() {
        return photo;
    }

    public void setPhoto(UploadFile photo) {
        this.photo = photo;
    }
}

