package com.emc.xcp.common.stringutil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.documentum.fc.client.DfQuery;
import com.documentum.fc.client.DfSingleDocbaseModule;
import com.documentum.fc.client.IDfCollection;
import com.documentum.fc.client.IDfQuery;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfLogger;

public class availablelanguages extends DfSingleDocbaseModule {
	/**
	 * Method to fetch available languages linked to a Document
	 * 
	 * @param document r_object_id
	 * @return string containing all the eligible languages
	 * @throws DfException, Exception
	 */

	public String findavailablelanguages(String document_obj_id) throws Exception  {
		IDfQuery  language_query = null;
		IDfSession session = null;
		IDfCollection coll_lang=null;
		List<String> mylanguagesList = new ArrayList<String>();
		String queryStringForLanguage;

		try{


			//create query string to find languages and their count based on 
			queryStringForLanguage="select  languages, count(languages) as blockcount  from geatyc_content where r_object_id in +"
					+ "(select child_id from dm_relation where relation_name='geatyc_block_content' and parent_id in +"
					+ "(select r_object_id from geatyc_block where code in ( select block from geatyc_document_component where r_object_id in +"
					+ "( select child_id from dm_relation where relation_name = 'geatyc_document_componentd' and parent_id = '"+document_obj_id+"')))) "
					+ "group by languages having count(languages) >= (select count(r_object_id)  from geatyc_block where code in "
					+ "( select block from geatyc_document_component where r_object_id in "
					+ "( select child_id from dm_relation where relation_name = 'geatyc_document_componentd' and parent_id = '"+document_obj_id+"')))";
			System.out.println("Query String: "+queryStringForLanguage);
			DfLogger.info(this,"Query String: "+queryStringForLanguage,null,null);
			language_query = new DfQuery();
			language_query.setDQL(queryStringForLanguage);
			session = getSession();
			coll_lang = language_query.execute(session,IDfQuery.READ_QUERY);
			while(coll_lang.next())
			{
				String lang=coll_lang.getString("languages");
				mylanguagesList.add(lang);
				System.out.println("Added '"+lang+"' Language to list.");
				DfLogger.info(this,"Added '"+lang+"' Language to list.",null,null);

			}
		}catch (Exception ioe) {
			ioe.printStackTrace();
			DfLogger.error(this.getClass(), "Exception thrown in findavailablelanguages method",null, ioe);

		}
		finally{
			// closing the collections
			try {
				if (coll_lang != null)
					coll_lang.close();

			} catch (DfException e) {
				DfLogger.error(this.getClass(),
						"Exception Inside finally closing coll:", null, e);

			}
			session.disconnect();
		}

		if(!(mylanguagesList.isEmpty())){
			return mylanguagesList.toString();	
		}
		else
		{
			return null;	
		}


	}

	public static Object getKeyFromValue(Map<String, Integer> hm, Object value) {
		List<Object> myList = new ArrayList<Object>();
		Object r=null;
		for (Object o : hm.keySet()) {
			if (hm.get(o).equals(value)) {
				myList.add(o);
			}
		}
		r=myList;
		return r;
	}

}
