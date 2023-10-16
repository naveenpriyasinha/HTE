package com.tcs.sgv.eis.service;
//Comment By Maruthi For import Organisation.
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.BillHeadDao;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadCustomVO;

public class HomePageImageServiceImpl<T> extends ServiceImpl{


	Log logger = LogFactory.getLog( getClass() );	 
	public ResultObject homePageImage(Map objectArgs)
	{

		ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");		
		Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
		Map voToService = (Map)objectArgs.get("voToServiceMap");
		try
		{
			logger.info("Service Called");
			//HrPayBillHeadCustomVO hrpaybill=new HrPayBillHeadCustomVO();

			String ImagePath = "";
			// long location_id =  Long.parseLong(voToService.get("loc_Id").toString());

			long locId=StringUtility.convertToLong(loginDetailsMap.get("locationId").toString());
			/*CmnLocationMstDaoImpl cmnLocationMstDaoImpl=new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());
			CmnLocationMst cmnLocationMst=cmnLocationMstDaoImpl.read(locId);*/	

			BillHeadDao billHeadDao = new BillHeadDao(HrPayBillHeadCustomVO.class,serv.getSessionFactory());

			List imagePathList = billHeadDao.getImagePathForHomePage(locId);

			/*StringBuffer propertyList = new StringBuffer();
			
			Iterator  it = ImagePathList.iterator();

			String imagePathName ="";

			//for(int i=0;i<ImagePathList.size();i++)
				if(ImagePathList!=null && !ImagePathList.isEmpty())
				{	    
					imagePathName = (String) ImagePathList.get(0);
					logger.info("+++++ imagePathName+++++"+imagePathName);
					propertyList.append("<image-mapping>");   	 
					propertyList.append("<imagePathName>").append("<![CDATA[").append(imagePathName).append("]]>").append("</imagePathName>");               
					propertyList.append("</image-mapping>");     
				}
				Map result = new HashMap();
				String imagePathData = new AjaxXmlBuilder().addItem("ajax_key", propertyList.toString()).toString();
				logger.info("The Image XML is:-"+imagePathData);
				result.put("ajaxKey", imagePathData);
				//result.put("imagePathName", imagePathName);
				//result.put("locationid",locId);
				//hrpaybill.setCmnLocationMst(cmnLocationMst);
				resultObject.setResultValue(result);
				resultObject.setViewName("ajaxData");                          	  	                          	               	   	                              	          	  
				logger.info("After Service Called.........\n"+imagePathData);*/
			String impagePath = "";
			int imageFlag=0;
			if(imagePathList!=null && !imagePathList.isEmpty()){
				impagePath = imagePathList.get(0).toString();
				imageFlag=1;
			}
			 
			//Added by Rohini to display image on Home Page
			objectArgs.put("imagePath",impagePath);
			objectArgs.put("imageFlag",imageFlag);
			resultObject.setResultValue(objectArgs);
			resultObject.setViewName("PayrollHomePageImage");
            //Ended by Rohini
			
		}
		catch(Exception e)
		{
			logger.error("Error is: "+ e.getMessage());
		}

		return resultObject;
	}//end method : homePageImage()
}//end class