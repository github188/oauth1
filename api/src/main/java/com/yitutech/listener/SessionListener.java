package com.yitutech.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * @author zhijun.li@yitu-inc.com
 * @date 2018/4/4
 */
public class SessionListener implements HttpSessionAttributeListener {


    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        HttpSession session = se.getSession();
        System.out.println("aaaas" + " " + session);
        session.getAttribute("");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {

    }
}
