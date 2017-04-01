/**
 * 版权声明： 开源随便使用
 * 日    期：16-6-6
 */
package com.nswt.osp.other;

import org.testng.annotations.Test;

import java.text.MessageFormat;

/**
 * @author : Admin
 * @date: 2014/7/4
 */
public class MessageFormatTest {

    @Test
    public void testFormat(){
        String str = MessageFormat.format("aaa{0}bbb{1}", "X");
        System.out.println(str);
        str = MessageFormat.format("aaa bbb", "X","Y");
        System.out.println(str);
}
}
