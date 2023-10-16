package com.tcs.sgv.pdpla.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pdpla.valueobject.TrnPdChallan;

public class PDPLAChallanDataDAOImpl extends GenericDaoHibernateImpl<TrnPdChallan ,Long> implements PDPLAChallanDataDAO{

	public PDPLAChallanDataDAOImpl(Class<TrnPdChallan> type, SessionFactory sessionFactory)
	  
	  {
		super(type);
		setSessionFactory(sessionFactory);
	  }
	        Log logger = LogFactory.getLog(getClass());
	        String voname = "-";
    public HashMap insertVO(ArrayList  lArrayLstChallanVO_DAO) 
	
       {
		
		  	       HashMap returnMap = new HashMap();
			       boolean flag = false;
			       String reason = "inserted successfully";
		    try
			    {
				   logger.info("----------------------------insertVO function of PDPLAChallanDataDAOImpl starts---------------------");
			       
				     for(int i =0; i <  lArrayLstChallanVO_DAO.size();i++ )
				       {
					      TrnPdChallan lObjChallanData = (TrnPdChallan ) lArrayLstChallanVO_DAO.get(i);
					      lObjChallanData.toString();
					      create(lObjChallanData);
					      flag = true;
				       }
	 						
				   logger.info("----------------------------insertVO function of PDPLAChallanDataDAOImpl ends---------------------");
			    }
		    catch(Exception e)
			    {
				     logger.error("This is Error: -", e);
				     reason = e.toString();
				     voname = this.toString();
			    }
		
		
		    returnMap.put("flag",flag);
			returnMap.put("status", reason);
			returnMap.put("voName", voname);
			
			return returnMap;
	  }

		
	}

	

