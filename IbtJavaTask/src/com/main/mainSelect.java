package com.main;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Map.Entry;

import com.model.Customer;
import com.operations.CommandExecutor;

public class mainSelect {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		CommandExecutor cmdExec = new CommandExecutor();
		
		Map<Integer,Customer> custMap = (Map<Integer, Customer>) cmdExec.GetCommand("GetCustomer");
    	
    	for (Entry<Integer,Customer> cust : custMap.entrySet()) {

			System.out.println(
					"Key: " + cust.getKey() 
					+ "\nCust No: " + cust.getValue().getCustomerİd()
					+ "\nName: " + cust.getValue().getFirstName());
			
			System.out.println("-----------");
		}

	}

}
