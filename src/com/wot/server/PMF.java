package com.wot.server;


import java.util.HashMap;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public final class PMF {
    //private static ClassLoader props;
	static public HashMap <String, String> props =  new HashMap<String, String>();
	static {
		/*
		 * <property name="javax.jdo.PersistenceManagerFactoryClass"
           value="org.datanucleus.api.jdo.JDOPersistenceManagerFactory"/>
       <property name="javax.jdo.option.ConnectionURL" value="appengine"/>
       <property name="javax.jdo.option.NontransactionalRead" value="true"/>
       <property name="javax.jdo.option.NontransactionalWrite" value="true"/>
       <property name="javax.jdo.option.RetainValues" value="true"/>
       <property name="datanucleus.appengine.autoCreateDatastoreTxns" value="true"/>
       <property name="datanucleus.appengine.singletonPMFForName" value="true"/>
		 */
		props.put("javax.jdo.PersistenceManagerFactoryClass", "org.datanucleus.api.jdo.JDOPersistenceManagerFactory");
		props.put("javax.jdo.option.ConnectionURL", "appengine");
		props.put("javax.jdo.option.NontransactionalRead", "true");
		props.put("javax.jdo.option.NontransactionalWrite", "true");
		props.put("javax.jdo.option.RetainValues", "true");
		props.put("datanucleus.appengine.autoCreateDatastoreTxns", "true");
		props.put("datanucleus.appengine.singletonPMFForName", "true");
		
	}
	
    
    
    
	private static final PersistenceManagerFactory pmfInstance =
        //JDOHelper.getPersistenceManagerFactory("transactions-optional");
	
    JDOHelper.getPersistenceManagerFactory(props);


    private PMF() {
    	
    }

    public static PersistenceManagerFactory get() {
        return pmfInstance;
    }
}
