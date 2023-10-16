package com.tcs.sgv.pdpla.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.pdpla.dao.GetHeadDetailDAOImpl;
import com.tcs.sgv.pdpla.valueobject.MstPdAccount;

public class GetHeadDetailServiceImpl extends ServiceImpl implements GetHeadDetailService
{
	Log logger = LogFactory.getLog(getClass());

    public ResultObject getHeadDetail(Map inputMap)
    {
    	ResultObject objRes = new ResultObject(ErrorConstants.SUCCESS, "FAIL");
    	ServiceLocator servLoc = (ServiceLocator)inputMap.get("serviceLocator");
    	
    	try
		{
			HttpServletRequest request = (HttpServletRequest) inputMap.get("requestObj");
			
			//System.out.println("-------------- Inside ServiceImpl----------try --------------");
			
			MstPdAccount MstPdAccVO = new MstPdAccount();
			String lPdPlaAccountNo = request.getParameter("pdPlaNo");
			lPdPlaAccountNo = lPdPlaAccountNo.trim();
			MstPdAccVO.setAccountNo(lPdPlaAccountNo);
			
			//System.out.println(lPdPlaAccountNo);
			
			//System.out.println("-------------- Inside ServiceImpl --------b4 goin to DAO--------------");
			GetHeadDetailDAOImpl lHeadDAO = new GetHeadDetailDAOImpl(MstPdAccount.class, servLoc.getSessionFactory());
			//System.out.println("-------------- Inside ServiceImpl --------DAO obj created--------------");
			
			Map lHeadListMap = lHeadDAO.getMajorHead(lPdPlaAccountNo);
			
			   	String lMajorHead = (String) lHeadListMap.get("mjrhead");
            	String lMinorHead = (String) lHeadListMap.get("mnrhead");
            	String lDeptName = (String) lHeadListMap.get("deptName");
            	
            	//System.out.println(lMajorHead);
            	//System.out.println(lMinorHead);
            	//System.out.println(lDeptName);
            	String lStrResult = new AjaxXmlBuilder().addItem("id_txtMajorHead", lMajorHead).addItem("id_txtMinorHead",lMinorHead).addItem("id_txtDeptName", lDeptName).toString();
				//System.out.println(lStrResult);
                inputMap.put("ajaxKey", lStrResult);
				objRes.setResultValue(inputMap);
				objRes.setViewName("ajaxData");
		}
    	
    	catch(Exception e)
    	{
    		logger.error("Error in getGrantAmountForDDO and Error is : " + e, e);
    	}
    	
    	//System.out.println("-------------- Inside ServiceImpl------- before return 2 --------------");
		
    	
    	return objRes;
    }
}
