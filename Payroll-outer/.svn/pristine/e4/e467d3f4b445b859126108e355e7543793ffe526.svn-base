package com.tcs.sgv.pdpla.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pdpla.valueobject.TrnPdChq;

public class PDPLAChqDataDAOImpl extends GenericDaoHibernateImpl<TrnPdChq ,Long> implements PDPLAChqDataDAO {

	public PDPLAChqDataDAOImpl(Class<TrnPdChq> type,SessionFactory sessionFactory)
	  {
		 super(type);
		 setSessionFactory(sessionFactory);
	  }
	 
	 Log logger = LogFactory.getLog(getClass());
     String voname = "-";
	
   public HashMap insertVO(ArrayList  lArrayLstChqVO_DAO) 
	
	{
		   HashMap returnMap = new HashMap();
	       boolean flag = false;
	       String reason = "inserted successfully";
      try
	  
        {
		    logger.info("----------------------------insertVO function of PDPLAChqDataDAOImpl starts---------------------");
	       
		     for(int i =0; i <  lArrayLstChqVO_DAO.size();i++ )
		        {
			       TrnPdChq lObjChqData = (TrnPdChq ) lArrayLstChqVO_DAO.get(i);
			       lObjChqData.toString();
			       create( lObjChqData);
			       flag = true;
		        }
					
		   logger.info("----------------------------insertVO function of PDPLAChqDataDAOImpl ends---------------------");
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


