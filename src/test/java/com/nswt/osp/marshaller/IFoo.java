/**
 * 版权声明： 开源随便使用
 * 日    期：16-6-6
 */
package com.nswt.osp.marshaller;

import javax.xml.bind.annotation.XmlTransient;

/**
 * @author : Admin
 * @date: 14-4-21
 */
public interface IFoo {

    @XmlTransient
    Integer getI1();
}
