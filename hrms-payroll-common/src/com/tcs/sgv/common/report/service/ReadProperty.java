package com.tcs.sgv.common.report.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

public class ReadProperty {
//	String str, key;
	private String key;
	private String val;
	private static ReadProperty readProperty=null;
	private ReadProperty() {
		
	}
	public Object[] getKeyValue(){
		Map map=new HashMap();
		/*try{ */
		int check = 0;
		
		while (check == 0) {
			check = 1;
			BufferedReader bf = new BufferedReader(new InputStreamReader(
					System.in));

			ResourceBundle bundle = ResourceBundle
					.getBundle("resources/report/treeElement");
			Enumeration enumeration = bundle.getKeys();
			//System.out.println("Getting the Value from Properties  ::>>"					+ bundle.getString("Pending"));
			map.put("Pending", bundle.getString("Pending"));
			
			
			//System.out.println("Getting the Value from Properties  ::>>"					+ bundle.getString("Approve"));
			setKey(bundle.getString("Approve"));
			setVal(bundle.getString("Approve"));
			map.put("Approve",bundle.getString("Approve"));
			while (enumeration.hasMoreElements()) {
				//System.out.println("The Value and The Key is ::>>"						+ enumeration.nextElement());
			}

		}
		Set set=map.entrySet();
		Object[] objects=set.toArray();
	return objects;
	}
	
	public static ReadProperty getInstance(){
		readProperty=new ReadProperty();
		return readProperty;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}
}
