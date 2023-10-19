package com.main;

import com.model.Customer;
import com.operations.CommandExecutor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

public class RunApp {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        
    	CommandExecutor cmdExec = new CommandExecutor();
    	
    	
        Customer custAdd = new Customer();
        custAdd.setCustomerİd((int)(Math.random() * 1000.0 + 1.0));
        custAdd.setPidNo(null);
        custAdd.setTaxNo(Long.valueOf(11111L));
        custAdd.setFirstName("add1");
        custAdd.setLastName("add1");
        custAdd.setGender("K");
        custAdd.setBirthDate((Date)java.sql.Date.valueOf("1995-01-01"));
        custAdd.setStatus(true);
        System.out.println(cmdExec.ExecuteCommand("CreateCustomer", (Object)custAdd));
        
        
        custAdd.setCustomerİd((int)(Math.random() * 1000.0 + 1.0));
        System.out.println(cmdExec.ExecuteCommand("CreateCustomer", (Object)custAdd));
        
        
        custAdd.setFirstName("edit1");
        custAdd.setLastName("edit1");
        System.out.println(cmdExec.ExecuteCommand("UpdateCustomer", (Object)custAdd));
        
        
        Customer custDelete = new Customer();
        custDelete.setCustomerİd(511);
        custDelete.setFirstName("test1");
        custDelete.setLastName("test1");
        custDelete.setGender("K");
        custDelete.setBirthDate((Date)java.sql.Date.valueOf("1995-01-01"));
        custDelete.setStatus(true);
        System.out.println(cmdExec.ExecuteCommand("DeleteCustomer", (Object)custDelete));
    }
}
