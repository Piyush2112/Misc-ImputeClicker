package com.emc.xcp.common.stringutil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class bhbh {
	  public static void main(String[] args) {
		    Map<String, Integer> map = new HashMap<String, Integer>();
		    
		    map.put("key_1",1);
		    map.put("key_2",1);
		    map.put("key_3",4);
		    map.put("key_4",4);

		    System.out.println(getKeyFromValue(map,4));
		  }
		  public static Object getKeyFromValue(Map<String, Integer> hm, Object value) {
			  List<Object> myList = new ArrayList<Object>();
			  Object r=null;
		    for (Object o : hm.keySet()) {
		      if (hm.get(o).equals(value)) {
		        myList.add(o);
		    	  //return o;
		      }
		    }
		    r=myList;
		    //String [] str= (String[]) myList.toArray();
		    System.out.println("String form:"+ myList.toString());
		    return r;
		  }
		}
