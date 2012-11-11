package com.chenjun.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.chenjun.dao.AccountDao;
import com.chenjun.dao.bean.Account;
import com.chenjun.hibernate.util.HibernateUtil;
import com.chenjun.sha1.SHA1;

public class AccountDaoImpl implements AccountDao{
	
	public Account get(Serializable accountId) {
		Account account = null;
		
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		try{
			account = (Account)session.get(Account.class, accountId);
			
			tx.commit();
		}
		catch(Exception ex){
			if(tx !=  null){
				tx.rollback();
			}
		}
		
		finally{
			HibernateUtil.closeSession(session);
		}
		
		return account;
	}
	
	public Account get(String username, String password) {
		Account account = null;
		
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		try{
			Query query = session.createQuery("from Account a where a.username = :username and password = :password");
			query.setString("username", username);
			query.setString("password", SHA1.sha1(password));
			account = (Account) query.uniqueResult();
			
			tx.commit();
		}
		catch(Exception ex){
			if(tx !=  null){
				tx.rollback();
			}
		}
		
		finally{
			HibernateUtil.closeSession(session);
		}
		
		return account;
	}
	
	@SuppressWarnings("unchecked")
	public boolean register(String username, String password) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		try{
			Query query = session.createQuery("from Account where username = '" + username + "'");
			List<Account> list = (List<Account>) query.list();
			
			if(list.size() != 0){
				tx.commit();
				return false;
			}
			
			Account account = new Account();
			account.setUsername(username);
			account.setPassword(SHA1.sha1(password));
			
			session.save(account);
			tx.commit();
		}
		catch(Exception ex){
			if(tx !=  null){
				tx.rollback();
			}
			return false;
		}
		finally{
			HibernateUtil.closeSession(session);
		}
		
		return true;
	}
	
	
	public void update(Account account) {
		Session session = null;
		Transaction tx = null;
		
		try{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			
			session.saveOrUpdate(account);
			
			tx.commit();
		}
		catch(Exception ex){
			ex.printStackTrace();
			if(tx != null){
				tx.rollback();
			}
		}
		finally{
			HibernateUtil.closeSession(session);
		}
	}
}
