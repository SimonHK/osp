/**
 * Copyright： 版权所有 违者必究 2013
 */
package com.nswt.osp.session;

/**
 * 线程绑定的会话执有器，使请求线程范围的调用堆栈的所有对象都可以通过{@link #get()}这个静态方法获取会话。
 * {@link OspSessionHolder}依赖于{@link SessionBindInterceptor}拦截器工作。
 * @author : Admin
 * @date: 16-10-16
 */
public class OspSessionHolder {

    private static  ThreadLocal<Session> ospSession = new ThreadLocal<Session>();

    public static  void put(Session session){
        ospSession.set(session);
    }
    public static Session get(){
        return ospSession.get();
    }

    public static <T> T get(Class<T> sessionClazz){
        return (T)ospSession.get();
    }
}
