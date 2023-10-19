package com.operations;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class CustomerOperations
{
  private Session session;
  
  public CustomerOperations() {
	  
    session = new Configuration().configure().buildSessionFactory().openSession();
  }
  

  private Boolean CustomerAdd(Object data) {
	  
    boolean result = false;
    
    try
    {
      session.beginTransaction();
      session.persist(data);
      session.getTransaction().commit();
      result = true;
    }
    catch (HibernateException e)
    {
      if (session.getTransaction() != null) session.getTransaction().rollback();
      e.printStackTrace();
    }
    
    return result;
  }
  

  private Boolean CustomerEdit(Object data) {
	  
    boolean result = false;
    
    try
    {
      session.beginTransaction();
      session.merge(data);
      session.getTransaction().commit();
      result = true;
    }
    catch (HibernateException e)
    {
      if (session.getTransaction() != null) session.getTransaction().rollback();
      e.printStackTrace();
    }
    
    return result;
  }
  
  private Boolean CustomerDelete(Object data) {
	  
    boolean result = false;
    
    try
    {
      session.beginTransaction();
      session.remove(data);
      session.getTransaction().commit();
      result = true;
    }
    catch (HibernateException e)
    {
      if (session.getTransaction() != null) session.getTransaction().rollback();
      e.printStackTrace();
    }
    
    return result;
  }
}