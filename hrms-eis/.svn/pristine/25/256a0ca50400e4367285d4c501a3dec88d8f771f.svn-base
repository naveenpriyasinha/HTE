package com.tcs.sgv.eis.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.BillGroupPostMpgDAOImpl;
import com.tcs.sgv.eis.dao.PsrPostMpgDAOImpl;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadMpg;
import com.tcs.sgv.eis.valueobject.HrPayPsrPostMpg;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;

public class BillGroupPostMpgServiceImpl extends ServiceImpl{

	Log logger = LogFactory.getLog(getClass());
	int msg = 0;
	
	public ResultObject getBillGroupPostDtlsService(Map ObjectArgs)
	{
		HttpServletRequest request = (HttpServletRequest) ObjectArgs.get("requestObj");
		ResultObject resultobject = new ResultObject(ErrorConstants.SUCCESS);
		
		try
		{
			logger.info("===> in try of getBillGroupPostDtlsService........... ");
			HttpSession session = request.getSession();
			ServiceLocator serv = (ServiceLocator) ObjectArgs.get("serviceLocator");
			
			BillGroupPostMpgDAOImpl billgroupDAOimpl = new BillGroupPostMpgDAOImpl(HrPayPsrPostMpg.class,serv.getSessionFactory());
			List<HrPayBillHeadMpg> BillNoList = billgroupDAOimpl.getAllUniqueBbillNo();
			//System.out.println("====> BillNoList.size() :: "+BillNoList.size());
			
			
			ObjectArgs.put("BillNoList", BillNoList);
			
			resultobject.setResultCode(ErrorConstants.SUCCESS);
			resultobject.setResultValue(ObjectArgs);
			resultobject.setViewName("BillGroupPostMpg");
		}
		catch(Exception e)
		{
			logger.info("===> in Excetion of getBillGroupPostDtlsService Method...");
			logger.error("Error is: "+ e.getMessage());
			ObjectArgs.put("msg", "There is Some Problem.Please Try Later.");
			resultobject.setResultValue(ObjectArgs);
			resultobject.setViewName("errorInsert");
		}
	    return resultobject;	
	}
	public ResultObject getListofPostMappedNotMapped(Map ObjectArgs)
	{
		//System.out.println("===> getListofPostMappedNotMapped..............");
		ResultObject objectRes = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) ObjectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) ObjectArgs.get("requestObj");
		try
		{
			//System.out.println("==> in try getListofPostMappedNotMapped....");
			long BillNo = 0;
			if(StringUtility.getParameter("BillNO", request)!=null)
			   BillNo = Long.parseLong(StringUtility.getParameter("BillNO", request).toString());
				
			//System.out.println("====> value of Bill No in Service  :: "+BillNo);
			BillGroupPostMpgDAOImpl billgroupDAOimpl = new BillGroupPostMpgDAOImpl(HrPayPsrPostMpg.class,serv.getSessionFactory());
			List<OrgPostDetailsRlt> PostListNotMapped = billgroupDAOimpl.getPostNameNotMapped(BillNo);
			List<OrgPostDetailsRlt> PostListMapped = billgroupDAOimpl.getPostNameMapped(BillNo);
			StringBuffer strBuffer = new  StringBuffer();
			StringBuffer StrbufMappedList = new StringBuffer();
			
			//System.out.println("==============> PostListMapped.size() :: "+PostListMapped.size());
			//System.out.println("==============> PostListNotMapped.size() :: "+PostListNotMapped.size());
			if(PostListMapped!=null || PostListMapped.size()!=0)
			{
				strBuffer.append("<PostMappedList>");
				for(int i=0;i<PostListMapped.size();i++)
				{
					OrgPostDetailsRlt orgpostrlt = PostListMapped.get(i);
					//System.out.println("===> PostListMapped :: "+orgpostrlt.getPostDetailId());
					StrbufMappedList.append(orgpostrlt.getPostDetailId());
					StrbufMappedList.append(",");
					
					strBuffer.append("<PostDtlsID>").append(orgpostrlt.getOrgPostMst().getPostId()).append("</PostDtlsID>");
					strBuffer.append("<PostDtlsName>").append(orgpostrlt.getPostName()).append("</PostDtlsName>");
				}
				strBuffer.append("</PostMappedList>");
			}
			//System.out.println("=====> in Service StrbufMappedList :: "+StrbufMappedList.toString());
			
			
			if(PostListNotMapped!=null || PostListNotMapped.size()!=0)
			{
				strBuffer.append("<PostNotMappedList>");
				for(int i=0;i<PostListNotMapped.size();i++)
				{
					OrgPostDetailsRlt orgDetailsRlt = PostListNotMapped.get(i);
					//System.out.println("==> PostListNotMapped :: "+orgDetailsRlt.getPostDetailId());
					strBuffer.append("<PostDtlsID>").append(orgDetailsRlt.getOrgPostMst().getPostId()).append("</PostDtlsID>");
					strBuffer.append("<PostDtlsName>").append(orgDetailsRlt.getPostName()).append("</PostDtlsName>");
				}
				strBuffer.append("</PostNotMappedList>");
			}
			//System.out.println("String Buffer of getListofPostMappedNotMapped :: "+strBuffer.toString());
			String PostListMappedNotMapped =  new AjaxXmlBuilder().addItem("ajax_key", strBuffer.toString()).toString();
			ObjectArgs.put("ajaxKey", PostListMappedNotMapped);
			objectRes.setResultValue(ObjectArgs);
			objectRes.setViewName("ajaxData");
		}
		catch(Exception e)
		{
			//System.out.println("==> in catch getListofPostMappedNotMapped....");
			logger.error("Error is: "+ e.getMessage());
			objectRes.setThrowable(e);
			objectRes.setResultCode(ErrorConstants.ERROR);
			objectRes.setViewName("errorPage");
		}
		
	
		return objectRes;
	}
	public ResultObject InsertUpdateBillData(Map objectArgs)
	{
		ResultObject objectRes = new ResultObject(ErrorConstants.SUCCESS);
		ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
		HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
		try 
		{
			
			//System.out.println("==> in try of InsertUpdateBillData... ");
			BillGroupPostMpgDAOImpl billgroupDAOimpl = new BillGroupPostMpgDAOImpl(HrPayPsrPostMpg.class,serv.getSessionFactory());
			long BillNo = 0;
			BillNo = Long.parseLong(objectArgs.get("cmbBillNo").toString());
			//System.out.println("==> in service BillNo :: "+BillNo);
			String NewMappedPostId = objectArgs.get("MappedPostId").toString(); 
			//NewMappedPostId = objectArgs.get("MappedPostId").toString();
			
			long sizeofMappedPostList = Long.valueOf(objectArgs.get("sizeofMappedPostList").toString());
			
			List<OrgPostDetailsRlt> OldPostListMapped = billgroupDAOimpl.getPostNameMapped(BillNo);
			
		    StringBuffer OldMappedPostIdList = new StringBuffer();
		    for(OrgPostDetailsRlt xyz :OldPostListMapped)
		    {
		    	if(xyz.getOrgPostMst()!=null)
		    	{
		    		OldMappedPostIdList.append(String.valueOf(xyz.getOrgPostMst().getPostId())+",");
		    	}
		    }
		    //System.out.println("===> size of OldMappedPostIdList :: "+OldMappedPostIdList.length());
		    if(OldMappedPostIdList.length()!=0)
		    {
		    	OldMappedPostIdList.deleteCharAt(OldMappedPostIdList.length()-1);
		    }
		    //System.out.println("====> str :: "+OldPostListMapped.size()+ "=====> "+OldMappedPostIdList );
		    
			//System.out.println("===> New List in Service sizeofMappedPostList :: "+sizeofMappedPostList);
			//System.out.println("===> New List in Service NewMappedPostId :: "+NewMappedPostId);
			
			//System.out.println("===> Old List in Service OldPostListMapped.size : "+ OldPostListMapped.size() );
			String lStrOldMappedPostIdList =  OldMappedPostIdList.toString(); 
			//System.out.println("===> string lStrOldMappedPostIdList :: "+lStrOldMappedPostIdList);
			
			//lStrOldMappedPostIdList = OldMappedPostIdList.toString();
			if(NewMappedPostId==null || NewMappedPostId=="")
			{
				//System.out.println("in setting 000000...."); 
				NewMappedPostId = "0";
			}
			
			if(lStrOldMappedPostIdList.equals(null) || lStrOldMappedPostIdList.equals(""))
			{
				//System.out.println("in setting 000000....");
				lStrOldMappedPostIdList = "0";
			}
			
			//System.out.println("===> before going to getUpdateListAs0 Method...."+(lStrOldMappedPostIdList)+"NewMappedPostId :: "+(NewMappedPostId));
			
			List<HrPayPsrPostMpg> UpdateListAs0 =  billgroupDAOimpl.getUpdateListAs0(lStrOldMappedPostIdList,NewMappedPostId,BillNo);
			//System.out.println("==> in Service UpdateListAs0 :: "+UpdateListAs0.size());
			if(UpdateListAs0.size()!=0)
			{
				for(int i=0;i<UpdateListAs0.size();i++)
				{
					HrPayPsrPostMpg hrPayPsrPostMpg = (HrPayPsrPostMpg)UpdateListAs0.get(i);
					//System.out.println("=====> before hrPayPsrPostMpg.getPostId() "+hrPayPsrPostMpg.getPostId());
					
					PsrPostMpgDAOImpl psrPostMpgDAOImpl = new PsrPostMpgDAOImpl(HrPayPsrPostMpg.class,serv.getSessionFactory());
					String[] cols = new String[]{"postId"};
					Object[] vals = new Object[]{hrPayPsrPostMpg.getPostId()};
					List hrPayPsrPostMpgList = psrPostMpgDAOImpl.getListByColumnAndValue(cols, vals);
					if(hrPayPsrPostMpgList!=null && hrPayPsrPostMpgList.size()>0)
						 hrPayPsrPostMpg = (HrPayPsrPostMpg)hrPayPsrPostMpgList.get(0);
					 
					//logger.info("After in UpdateListAs0 Post Id OHP :: " + hrPayPsrPostMpg.getPostId());
					//System.out.println("===> After in UpdateListAs0 Post Id OHP :: "+ hrPayPsrPostMpg.getPostId());
					 
					if(hrPayPsrPostMpg!=null && hrPayPsrPostMpg.getPsrId()!=0 && hrPayPsrPostMpg.getPsrId()!=0)
					 {
						 hrPayPsrPostMpg.setBillNo(null);
						 psrPostMpgDAOImpl.update(hrPayPsrPostMpg);
					 }
				}
			}
			
			
			List<HrPayPsrPostMpg> UpdateListAsBillNo = billgroupDAOimpl.getUpdateListAsBillNo(NewMappedPostId,lStrOldMappedPostIdList);
			//System.out.println("==> in Service UpdateListAsBillNo :: "+UpdateListAsBillNo.size());
			if(UpdateListAsBillNo.size()!=0)
			{
				for(int i=0;i<UpdateListAsBillNo.size();i++)
				{
					HrPayPsrPostMpg hrPayPsrPostMpg = (HrPayPsrPostMpg)UpdateListAsBillNo.get(i);
					//System.out.println("=====> UpdateListAsBillNo hrPayPsrPostMpg.getPostId() "+hrPayPsrPostMpg.getPostId());
					
					PsrPostMpgDAOImpl psrPostMpgDAOImpl = new PsrPostMpgDAOImpl(HrPayPsrPostMpg.class,serv.getSessionFactory());
					String[] cols = new String[]{"postId"};
					Object[] vals = new Object[]{hrPayPsrPostMpg.getPostId()};
					List hrPayPsrPostMpgList = psrPostMpgDAOImpl.getListByColumnAndValue(cols, vals);
					if(hrPayPsrPostMpgList!=null && hrPayPsrPostMpgList.size()>0)
						 hrPayPsrPostMpg = (HrPayPsrPostMpg)hrPayPsrPostMpgList.get(0);
					 
					//logger.info("After in UpdateListAs0 Post Id OHP :: " + hrPayPsrPostMpg.getPostId());
					//System.out.println("===> After in UpdateListAsBillNo Post Id OHP :: "+ hrPayPsrPostMpg.getPostId());
					 
					if(hrPayPsrPostMpg!=null && hrPayPsrPostMpg.getPsrId()!=0 && hrPayPsrPostMpg.getPsrId()!=0)
					 {
						 hrPayPsrPostMpg.setBillNo(BillNo);
						 psrPostMpgDAOImpl.update(hrPayPsrPostMpg);
					 }
				}
			}
			
			ResultObject ForDisplayObject = this.getBillGroupPostDtlsService(objectArgs); 
			Map forPassValueoDisplayService = (Map) ForDisplayObject.getResultValue();
			
			objectArgs.put("BillNoList", forPassValueoDisplayService.get("BillNoList"));
			
			objectRes.setResultValue(objectArgs);
			objectRes.setViewName("BillGroupPostMpg");
		}
		catch(Exception e)
		{
			//System.out.println("==> in Catch of InsertUpdateBillData... ");
			logger.error("Error is: "+ e.getMessage());
			objectRes.setThrowable(e);
			objectRes.setResultCode(ErrorConstants.ERROR);
			objectRes.setViewName("errorPage");
		}
		
		return objectRes;
	}
}
