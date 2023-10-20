package com.operations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import com.model.Customer;

public class CommandExecutor {
  private Session session;
  
  public CommandExecutor() {
	  
    session = new Configuration().configure().buildSessionFactory().openSession();
  }
  

  private String[] GetClassAndMethodFromDB(String commandName) {
	  
    String query = "SELECT CLASS_NAME, METHOD FROM COMMAND_EXECUTER WHERE COMMAND_NAME = :COMMAND_NAME";
    
    Object[] result = (Object[])session.createNativeQuery(query).setParameter("COMMAND_NAME", commandName).uniqueResult();
    

    if (result != null) {
    	
      return new String[] { (String)result[0], (String)result[1] };
    }
    
    return null;
  }
  
  public Boolean ExecuteCommand(String commandName, Object data) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	  
    String[] classAndMethod = GetClassAndMethodFromDB(commandName);
    
    String className = classAndMethod[0];
    String method = classAndMethod[1];
    
    Class<?> clazz = Class.forName(className);
    
    Method myMethod = clazz.getDeclaredMethod(method, Object.class);
    myMethod.setAccessible(true);
    
    Object instance = clazz.getDeclaredConstructor().newInstance();
    
    Boolean result = (Boolean)myMethod.invoke(instance, data);
    
    return result;
  }
  
  public Map<?,?> GetCommand(String commandName) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	  
	  String[] classAndMethod = GetClassAndMethodFromDB(commandName);
	    
	  String className = classAndMethod[0];
	  String method = classAndMethod[1];
	    
	  Class<?> clazz = Class.forName(className);
	    
	  Method myMethod = clazz.getDeclaredMethod(method);
	  myMethod.setAccessible(true);
	    
	  Object instance = clazz.getDeclaredConstructor().newInstance();
	    
	  List<Object> resultList = (List<Object>) myMethod.invoke(instance);
	    
	  Map<Integer,Object> resultMap = new HashMap<>();
	    
	  for (int i = 0; i < resultList.size(); i++) {	  
		  
		  resultMap.put(i, resultList.get(i));
	  }
	    
	    return resultMap;
  }
  
}