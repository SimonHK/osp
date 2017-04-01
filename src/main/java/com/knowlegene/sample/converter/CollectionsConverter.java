/**
 * 版权声明： 开源随便使用
 * 日    期：16-6-8
 */
package com.knowlegene.sample.converter;

import com.nswt.osp.request.OspConverter;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * com.knowlegene.apiservice.entity.User: stamen
 * Date: 13-10-25
 * Time: 上午11:54
 * To change this template use File | Settings | File Templates.
 */
public class CollectionsConverter implements OspConverter<String, Collection> {


    public String unconvert(Collection target) {
        return null;
    }


    public Class<String> getSourceClass() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    public Class<Collection> getTargetClass() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    public Collection convert(String s) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
