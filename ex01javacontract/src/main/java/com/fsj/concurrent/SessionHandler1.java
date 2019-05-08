package com.fsj.concurrent;

import lombok.Data;

import java.io.IOException;

/**
 * @author fengshenjie
 * @date 19/5/8 10:48
 * @link http://www.jasongj.com/java/threadlocal/
 */
public class SessionHandler1 {
    @Data
    public static class Session {
        private String id;
        private String user;
        private String status;
    }

    public Session createSession() {
        return new Session();
    }

    public String getUser(Session session) {
        return session.getUser();
    }

    public String getStatus(Session session) {
        return session.getStatus();
    }

    public void setStatus(Session session, String status) {
        session.setStatus(status);
    }

    public static void main(String[] args) throws IOException {
        new Thread(() -> {
            SessionHandler1 handler = new SessionHandler1();
            Session session = handler.createSession();
            handler.getStatus(session);
            handler.getUser(session);
            handler.setStatus(session, "close");
            handler.getStatus(session);
        }).start();
        System.in.read();
    }
}
