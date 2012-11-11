package com.chenjun.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	
	static{
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	public static Session getSession(){
		return sessionFactory.openSession();
	}
	
	public static void closeSession(Session session){
		if(null != session){
			session.close();
		}
	}
}
