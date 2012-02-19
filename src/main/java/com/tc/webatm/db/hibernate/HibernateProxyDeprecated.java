package com.tc.webatm.db.hibernate;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Deprecated
public class HibernateProxyDeprecated implements MethodInterceptor {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        SessionProvider.SELF.setSession(sessionFactory.openSession());
        Object result = methodInvocation.proceed();
        sessionFactory.close();
        return result;
    }
}
