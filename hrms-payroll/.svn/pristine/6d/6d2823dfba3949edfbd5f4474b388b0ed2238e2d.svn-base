package com.tcs.sgv.allowance.service;


import java.util.Map;

import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;



public class IdGenerator  {
	
	public Long PKGeneratorWODBLOC(String tableName,Map objectArgs)
    {
		 Long  srNoLong = new Long(0);
        ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
        //System.out.println(":::::::::::::::in id gen::::::::::::::::::"+ tableName);
        
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
            //System.out.println("val - " + str);
            srNoLong = Long.valueOf(str);
            //System.out.println("key generated  for " + tableName + ": \t " + srNoLong);
        }
        catch (Exception e)
        {
            //System.out.println("Exception in primaryKeyGenerator in investigation Service");
        }
        return srNoLong;

    }
	
	public Long PKGenerator(String tableName,Map objectArgs)
    {
		 Long  srNoLong = new Long(0);
        ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
        //System.out.println(":::::::::::::::in id gen::::::::::::::::::"+ tableName);
        
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
            Map resultMap = (Map) resultObj.getResultValue();
            srNoLong = (Long) resultMap.get("newID");
            //System.out.println("key generated  for " + tableName + ": \t " + srNoLong);
        }
        catch (Exception e)
        {
            //System.out.println("Exception in primaryKeyGenerator in investigation Service");
        }
        return srNoLong;

    }
}
	
	
	
	
