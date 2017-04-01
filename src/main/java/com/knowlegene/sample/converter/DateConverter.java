/**
 * 版权声明： 开源随便使用
 * 日    期：16-6-8
 */
package com.knowlegene.sample.converter;

import com.nswt.osp.request.OspConverter;

import java.util.Date;

/**
 * @author : Admin
 * @date: 14-3-18
 */
public class DateConverter implements OspConverter<String,Date> {


    public Date convert(String s) {
        return DateUtils.parseDate(s);
    }


    public String unconvert(Date date) {
        return DateUtils.format(date,DateUtils.DATETIME_FORMAT);
    }


    public Class<String> getSourceClass() {
        return String.class;
    }


    public Class<Date> getTargetClass() {
        return Date.class;
    }
}
