/**
 * 
 */
package com.wfp.utils;

import java.util.Hashtable;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.NameNotFoundException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import com.enterprisehorizons.util.Logger;

/**
 * @author kaleem.mohammed
 * 
 */
public class TestLDAP implements IEPICConstants{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("########## START");
		
		String uid ="yyyy";
		String pwd="xxx";
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL,
				"ldaps://ldap-dev.globalepic.lu:636/dc=emergency,dc=lu");

		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PROTOCOL, "ssl");
		env.put(Context.SECURITY_PRINCIPAL,
				"uid="+uid+",ou=users,ou=people,dc=emergency,dc=lu");
		env.put(Context.SECURITY_CREDENTIALS, pwd );
		env.put(Context.STATE_FACTORIES, "PersonStateFactory");
		env.put(Context.OBJECT_FACTORIES, "PersonObjectFactory");
		env.put("java.naming.ldap.attributes.binary", "jpegPhoto");
		
		String FILTER = "(&"+FILTER_LDAP_USERS+"((uid="+uid+")))";
		// limit returned attributes to those we care about
		String returnedAtts[] = {""+PROPERTY_IMAGE };

		DirContext ctx = null;
		try {
			ctx = new InitialDirContext(env);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		 byte bt[] = null;
		
		NamingEnumeration results = null;
		try {
			SearchControls controls = new SearchControls();
			controls.setReturningAttributes(returnedAtts);
			controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		
			String name = LDAP_FILTER_URL +"uid="+ uid + ","+LDAP_BASE;
			results = ctx.search(name,FILTER, controls);

			while (results.hasMore()) {
				SearchResult searchResult = (SearchResult) results.next();
				Attributes attributes = searchResult.getAttributes();
				Attribute attr = attributes.get("jpegPhoto");
				if( attr!=null ){
				byte[] photo = (byte[])attr.get() ;
				System.out.println(new String(photo));
				}
				else System.out.println(" No pic : "+attr );
			
				
			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			if (results != null) {
				try {
					results.close();
				} catch (Exception e) {
					// Never mind this.
				}
			}
			if (ctx != null) {
				try {
					ctx.close();
				} catch (Exception e) {
					// Never mind this.
				}
			}
		}

	}
	
	

}
