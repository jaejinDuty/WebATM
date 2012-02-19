package com.tc.webatm.db.hibernate;

import org.hibernate.Session;

public enum SessionProvider {
    SELF;

    private Session session;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
