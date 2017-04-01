/**
 * 版权声明：开源随便使用
 * 日    期：16-7-14
 */
package com.knowlegene.sample.request;

import com.nswt.osp.AbstractOspRequest;
import com.nswt.osp.annotation.IgnoreSign;

import javax.validation.constraints.Pattern;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class LogonRequest extends AbstractOspRequest {

    //@NotNull
    @Pattern(regexp = "\\w{4,30}")
    private String userName;

    @IgnoreSign
    @Pattern(regexp = "\\w{6,30}")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

