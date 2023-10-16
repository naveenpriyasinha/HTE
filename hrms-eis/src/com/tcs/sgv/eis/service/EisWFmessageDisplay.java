package com.tcs.sgv.eis.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.tcs.sgv.common.utils.fileupload.AttachmentHelper;
import com.tcs.sgv.core.service.ServiceLocator;

public class EisWFmessageDisplay 
{
	
	
    public static long getLangId(ServiceLocator serv,Map objectArgs){ 

            Map loginMap = (Map) objectArgs.get("baseLoginMap"); 

            /*  Get The Person Post */                                                                       
            long langId = Long.parseLong(loginMap.get("langId").toString()); 

            /*End of the geting Person Post Code*/ 
            return  langId; 
    } 	
	        
	public static void removeAttachmentFromSession(HttpSession session, String strAttachName, int rowNumber)
	{
		String key = (String) session.getAttribute("name")+strAttachName + session.getId() + rowNumber;
		AttachmentHelper.fileItemArrayListMap.remove(key);
	}
	
}
