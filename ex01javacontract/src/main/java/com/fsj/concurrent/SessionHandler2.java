package com.fsj.concurrent;

import lombok.Data;

import java.util.concurrent.CountDownLatch;

/**
 * @author fengshenjie
 * @date 19/5/8 10:49
 */
public class SessionHandler2 {
    public static ThreadLocal<Session> session = new ThreadLocal<Session>();

    @Data
    public static class Session {
        private String id;
        private String user;
        private String status;
    }

    public void createSession() {
        session.set(new Session());
    }

    public String getUser() {
        return session.get().getUser();
    }

    public String getStatus() {
        return session.get().getStatus();
    }

    public void setStatus(String status) {
        session.get().setStatus(status);
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(() -> {
            SessionHandler2 handler = new SessionHandler2();
            handler.createSession();//线程需要初始化自己的session
            handler.getStatus();
            handler.getUser();
            handler.setStatus("close");
            System.out.println(handler.getStatus());
            countDownLatch.countDown();
        }).start();
        countDownLatch.await();
    }
}

