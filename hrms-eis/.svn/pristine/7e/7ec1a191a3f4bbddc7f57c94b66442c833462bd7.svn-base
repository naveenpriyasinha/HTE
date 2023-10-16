package com.tcs.sgv.eis.service;


import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.util.CommomnDataObjectFatch;



public class IdGenerator  {
	Log logger = LogFactory.getLog(getClass());
	
	public Long PKGeneratorWODBLOC(String tableName,Map objectArgs)
    {
		
		 Long  srNoLong = new Long(0);
        ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
        logger.info(":::::::::::::::in id gen::::::::::::::::::"+ tableName);
        
       try
        {
            ServiceLocator servLoc = (ServiceLocator) objectArgs.get("serviceLocator");
            objectArgs.put("tablename", tableName);
          //  objectArgs.put("serviceLocator", servLoc);
            //GENERATE_ID_SRVC
            ResultObject resultObj = servLoc.executeService("GENERATE_ID_WODBLOCATION", objectArgs);
           /* long receivedcode = resultObj.getResultCode();
            if (receivedcode == -1)
            {
                return receivedcode;
            } */
            Map resultMap = (Map) resultObj.getResultValue();
            String str = (String)resultMap.get("newID");
            logger.info("val - " + str);
            srNoLong = Long.valueOf(str);
            logger.info("key generated  for " + tableName + ": \t " + srNoLong);
        }
        catch (Exception e)
        {
        	logger.error("Error is: "+ e.getMessage());
            //logger.info("Exception in primaryKeyGenerator in investigation Service");
        }
        return srNoLong;

    }
	
	public Long PKGenerator(String tableName,Map objectArgs)
    {
		 Long  srNoLong = new Long(0);
        ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
        logger.info(":::::::::::::::in id gen::::::::::::::::::"+ tableName);
        
       try
        {
            ServiceLocator servLoc = (ServiceLocator) objectArgs.get("serviceLocator");
            objectArgs.put("tablename", tableName);
          //  objectArgs.put("serviceLocator", servLoc);
            //GENERATE_ID_SRVC
            ResultObject resultObj = servLoc.executeService("GENERATE_ID_SRVC", objectArgs);
           /* long receivedcode = resultObj.getResultCode();
            if (receivedcode == -1)
            {
                return receivedcode;
            } */
            
            logger.info("resultObj.getResultValue()"+resultObj.getResultValue());
            Map resultMap = (Map) resultObj.getResultValue();
            srNoLong = (Long) resultMap.get("newID");
            logger.info("key generated  for " + tableName + ": \t " + srNoLong);
        }
        catch (Exception e)
        {
            logger.info("Exception in primaryKeyGenerator in investigation Service");
            logger.error("Error is: "+ e.getMessage());
        }
        return srNoLong;

    }
	public Long PKGeneratorWebService(String tableName,ServiceLocator servLoc,long userId,long langId,long locId)
    {
		 Long  srNoLong = new Long(0);
        ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
        logger.info(":::::::::::::::in id gen::::::::::::::::::"+ tableName);
        
       try
        {
            //ServiceLocator servLoc = (ServiceLocator) objectArgs.get("serviceLocator");
            //objectArgs.put("tablename", tableName);
          //  objectArgs.put("serviceLocator", servLoc);
            //GENERATE_ID_SRVC
    	   CommomnDataObjectFatch cmn = new CommomnDataObjectFatch(servLoc);
    	   Map passMap = new HashMap();
    	   passMap.put("tablename", tableName);
    	   passMap.put("serviceLocator", servLoc);
    	   Map baseLoginMap = new HashMap();
    	   passMap.put("singleTran","Y");
    	   baseLoginMap.put("userId", userId);
    	   baseLoginMap.put("langId", langId);
    	   baseLoginMap.put("locationVO", cmn.getCmnLocationMst(locId));
    	   passMap.put("baseLoginMap", baseLoginMap);
    	   passMap.put("CmnLocationMstDst", cmn.getCmnLocationMst(locId));
    	   //passMap.put("counter", 0);
    	   
    	   
            ResultObject resultObj = servLoc.executeService("GENERATE_ID_SRVC", passMap);
           /* long receivedcode = resultObj.getResultCode();
            if (receivedcode == -1)
            {
                return receivedcode;
            } */
            Map resultMap = (Map) resultObj.getResultValue();
            srNoLong = (Long) resultMap.get("newID");
            logger.info("key generated  for " + tableName + ": \t " + srNoLong);
        }
        catch (Exception e)
        {
            logger.info("Exception in primaryKeyGenerator in investigation Service");
        }
        return srNoLong;

    }
}
	
	
	
	
