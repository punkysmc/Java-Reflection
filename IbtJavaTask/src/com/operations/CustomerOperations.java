package com.operations;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class CustomerOperations {
	
    private Session session = new Configuration().configure().buildSessionFactory().openSession();

    
    private Boolean CustomerAdd(Object data) {
    	
        boolean result = false;
        
        try {
        	
            this.session.beginTransaction();
            this.session.persist(data);
            this.session.getTransaction().commit();
            result = true;
            
        }
        catch (HibernateException e) {
        	
            if (this.session.getTransaction() != null) {
                this.session.getTransaction().rollback();
            }
            
            e.printStackTrace();
        }
        
        return result;
        
    }
    

    private Boolean CustomerEdit(Object data) {
    	
        boolean result = false;
        
        try {
        	
            this.session.beginTransaction();
            this.session.merge(data);
            this.session.getTransaction().commit();
            result = true;
            
        }
        catch (HibernateException e) {
        	
            if (this.session.getTransaction() != null) {
                this.session.getTransaction().rollback();
            }
            
            e.printStackTrace();
        }
        
        return result;
        
    }
    

    private Boolean CustomerDelete(Object data) {
    	
        boolean result = false;
        
        try {
            this.session.beginTransaction();
            this.session.remove(data);
            this.session.getTransaction().commit();
            result = true;
            
        }
        catch (HibernateException e) {
        	
            if (this.session.getTransaction() != null) {
                this.session.getTransaction().rollback();
            }
            
            e.printStackTrace();
        }
        
        return result;
        
    }
}