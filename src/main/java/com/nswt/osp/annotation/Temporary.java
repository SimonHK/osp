/**
 * 版权声明： 开源随便使用
 * 日    期：16-6-30
 */
package com.nswt.osp.annotation;

import java.lang.annotation.*;

/**
 * 默认情况下，请求对象的所有field都会作为请求参数提交，如果希望某个field不作为参数提交，可以打上{@Temporary}注解，如下所示：
 * <pre class="code">
 * <DIV>&nbsp; public class MyOspRequest implements OspRequest{
 * <DIV>&nbsp;</DIV>
 * <DIV>&nbsp; private String field1;</DIV>
 * <DIV>&nbsp;</DIV>
 * <DIV>&nbsp; @Temporary</DIV>
 * <DIV>&nbsp; private String field2;</DIV>&nbsp;}
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Temporary {

}

