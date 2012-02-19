package com.tc.webatm.db.hibernate;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.ProceedingJoinPoint;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
public class HibernateProxyAspect {
    @Autowired
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Around("execution(public * com.tc.webatm.dao.*.hibernate.*.*(..)) && " +
            "@annotation(com.tc.webatm.db.ExecutionMarkers.FetchMethod)")
    public Object fetchProxy(ProceedingJoinPoint pjp) throws Throwable {
        Object retVal = null;
        try {
            SessionProvider.SELF.setSession(sessionFactory.openSession());
            retVal = pjp.proceed();
        } finally {
            if (SessionProvider.SELF.getSession().isOpen()) {
                SessionProvider.SELF.getSession().close();
            }
        }
        return retVal;
    }

    @Around("execution(public * com.tc.webatm.dao.*.hibernate.*.*(..)) && " +
            "@annotation(com.tc.webatm.db.ExecutionMarkers.AmendMethod)")
    public Object updateProxy(ProceedingJoinPoint pjp) throws Throwable {
        Object retVal = null;
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();

            SessionProvider.SELF.setSession(session);
            retVal = pjp.proceed();

            transaction = session.getTransaction();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if ((session != null) && session.isOpen()) {
                session.close();
            }
        }
        return retVal;
    }
}
