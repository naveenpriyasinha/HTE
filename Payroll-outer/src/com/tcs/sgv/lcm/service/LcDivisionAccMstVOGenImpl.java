package com.tcs.sgv.lcm.service;



import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.BptmCommonServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valuebeans.CommonVO;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.service.VOGeneratorService;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.lcm.dao.LcDivisionAccMstDAOImpl;
import com.tcs.sgv.lcm.valueobject.LcAccMstVO;
import com.tcs.sgv.lcm.valueobject.MstLcDivisionAccount;

public class LcDivisionAccMstVOGenImpl 
extends ServiceImpl  implements VOGeneratorService
{
    Log logger = LogFactory.getLog(getClass());
	
	public ResultObject generateMap(Map p_objServiceArgs) 
	{
		logger.info("\n------------ Inside generateMap of LcDivisionAccMstVOGenImpl----------- ");
		
		HttpServletRequest request = (HttpServletRequest) p_objServiceArgs.get("requestObj");	
		ServiceLocator serv = (ServiceLocator)p_objServiceArgs.get("serviceLocator");
		
		LcDivisionAccMstDAOImpl lLcDivAccDaoImpl = new LcDivisionAccMstDAOImpl(MstLcDivisionAccount.class,serv.getSessionFactory());
		LcAccMstVO lObjAccMstVO = new LcAccMstVO();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Long createdUserId=SessionHelper.getUserId(request);
		Long createdPostId=SessionHelper.getPostId(p_objServiceArgs);
		Long updatedUserId=SessionHelper.getUserId(request);
		Long updatedPostId=SessionHelper.getPostId(p_objServiceArgs);
		
		Long lLngLocId = SessionHelper.getLocationId(p_objServiceArgs);
		Long lLngDbId = SessionHelper.getDbId(p_objServiceArgs);
		
		String lStrLocCode=SessionHelper.getLocationCode(p_objServiceArgs);
		
		Long LLngLangId= SessionHelper.getLangId(p_objServiceArgs);
		
		long lLngAccId=0;
		long lLngAccNo=0;
		Long lLngPostId;
		Long lLngPostDtlId;
		String lStrAccCrtDt="";
		Date lDtAccCrtDt=null;
		String lStrDivCode="";		
		String lStrUnderCode="";
		String lStrUnderCodeDesc="";
		String lStrBrnchCode="";
		String lStrMjrHd="";
		String lStrSbMjrHd="";
		String lStrMinHd="";
		String lStrSbHd="";
		String lStrDtlHd="";
		String lStrObjHd="";
		
		char lChActive='0';
		Date lDtCrtDt=null;
		Date lDtUpdDt = null;			
		CommonVO lObjCmnVO = new CommonVO();
		BptmCommonServiceImpl serviceImpl=new BptmCommonServiceImpl();
		//SeqNumServiceImpl serviceImpl = new SeqNumServiceImpl();	
	    lLngAccId = serviceImpl.getNextSeqNum("mst_lc_division_account", p_objServiceArgs);
	    lLngPostId = serviceImpl.getNextSeqNum("org_post_mst", p_objServiceArgs);
	    lLngPostDtlId = serviceImpl.getNextSeqNum("org_post_details_rlt", p_objServiceArgs);
		logger.info("------Auto generate ID------"+lLngAccId);
		String lStrLocationCode = ""; 
		String lStrAdd1 = "";
		String lStrAdd2 = "";
		String lStrPin = "";
		String lStrDivNm = "";
		String lStrDept = "";
		try
		{
			logger.info("-----------In try block------------");
			if(!(StringUtility.getParameter("txtAccDate", request)).equals(""))
			{			
			 lStrAccCrtDt = StringUtility.getParameter("txtAccDate", request);
			 logger.info("-------lStrAccCrtDt-------"+lStrAccCrtDt);
			 lDtAccCrtDt = sdf.parse(lStrAccCrtDt);			 
			 logger.info("-------lDtAccCrtDt After Parse-------"+lDtAccCrtDt);
			}
			if(!(StringUtility.getParameter("id_division", request)).equals(""))
			{				
				lStrDivCode = (StringUtility.getParameter("id_division", request).toString());				
				logger.info("-------lStrDivCode-------"+lStrDivCode);
			}
			if(!(StringUtility.getParameter("txtUnderCode", request)).equals(""))
			{				
				lStrUnderCode=(String)StringUtility.getParameter("txtUnderCode", request);
				logger.info("-------lStrUnderCode-------"+lStrUnderCode);
			}
			if(!(StringUtility.getParameter("txtUnderCodeDesc", request)).equals(""))
			{				
				lStrUnderCodeDesc=(String)StringUtility.getParameter("txtUnderCodeDesc", request);
				logger.info("-------lStrUnderCodeDesc-------"+lStrUnderCodeDesc);
			}
			if(!(StringUtility.getParameter("id_branch", request)).equals(""))
			{				
				lStrBrnchCode=(StringUtility.getParameter("id_branch", request).toString());				
				logger.info("-------lStrBrnchCode-------"+lStrBrnchCode);
			}
			if(!(StringUtility.getParameter("txtDivCode", request)).equals(""))
			{				
				lStrLocationCode=(StringUtility.getParameter("txtDivCode", request).toString());				
				logger.info("-------txtDivCode  -------"+lStrLocationCode);
			}
			if(!(StringUtility.getParameter("txtAddress1", request)).equals(""))
			{				
				lStrAdd1=(StringUtility.getParameter("txtAddress1", request).toString());				
				logger.info("-------txtAddress1 -------"+lStrAdd1);
			}
			if(!(StringUtility.getParameter("txtAddress2", request)).equals(""))
			{				
				lStrAdd2=(StringUtility.getParameter("txtAddress2", request).toString());				
				logger.info("-------txtAddress2-------"+lStrAdd2);
			}
			if(!(StringUtility.getParameter("txtPin", request)).equals(""))
			{				
				lStrPin=(StringUtility.getParameter("txtPin", request).toString());				
				logger.info("-------txtPin -------"+lStrPin);
			}
			if(!(StringUtility.getParameter("txtDivName", request)).equals(""))
			{				
				lStrDivNm=(StringUtility.getParameter("txtDivName", request).toString());				
				logger.info("-------txtDivName-------"+lStrDivNm);
			}
			if(!(StringUtility.getParameter("id_dept", request)).equals(""))
			{				
				lStrDept=(StringUtility.getParameter("id_dept", request).toString());				
				logger.info("-------id_dept-------"+lStrDept);
			}
			
			
			if(!(StringUtility.getParameter("txtMjrHd", request)).equals(""))
			{				
				lStrMjrHd=(StringUtility.getParameter("txtMjrHd", request).toString());				
				logger.info("-------mjr hd-------"+lStrMjrHd);
			}
			if(!(StringUtility.getParameter("txtSubMjrHd", request)).equals(""))
			{				
				lStrSbMjrHd=(StringUtility.getParameter("txtSubMjrHd", request).toString());				
				logger.info("-------sub mjr hd-------"+lStrSbMjrHd);
			}
			if(!(StringUtility.getParameter("txtMinHd", request)).equals(""))
			{				
				lStrMinHd=(StringUtility.getParameter("txtMinHd", request).toString());				
				logger.info("-------Min Hd-------"+lStrMinHd);
			}
			if(!(StringUtility.getParameter("txtSubHd", request)).equals(""))
			{				
				lStrSbHd=(StringUtility.getParameter("txtSubHd", request).toString());			
				logger.info("-------Sub Hd-------"+lStrSbHd);
			}
			if(!(StringUtility.getParameter("txtDtlHd", request)).equals(""))
			{				
				lStrDtlHd=(StringUtility.getParameter("txtDtlHd", request).toString());			
				logger.info("-------Dtl Hd-------"+lStrDtlHd);
			}
			if(!(StringUtility.getParameter("txtObjHd", request)).equals(""))
			{				
				lStrObjHd=(StringUtility.getParameter("txtObjHd", request).toString());				
				logger.info("-------obj hd-------"+lStrObjHd);
			}
			
		
			
			lDtCrtDt=new Date();
			MstLcDivisionAccount lObjLcAccMstVo=null;			
			lObjLcAccMstVo = new MstLcDivisionAccount();
//			OrgPostMst lObjPost = new OrgPostMst();
			//OrgPostDetailsRlt lObjPostDtl = new OrgPostDetailsRlt();
			
			lObjLcAccMstVo.setLcAccId(new BigDecimal(lLngAccId));
			lObjLcAccMstVo.setLcAccNo(new BigDecimal(lLngAccId));
			lObjLcAccMstVo.setAccCrtDt(lDtAccCrtDt);
			lObjLcAccMstVo.setDivisionCode(lStrDivCode);
			lObjLcAccMstVo.setUnderCode(lStrUnderCode);
			lObjLcAccMstVo.setUnderCodeDesc(lStrUnderCodeDesc);
			lObjLcAccMstVo.setBranchCode(lStrBrnchCode);
			
			lObjLcAccMstVo.setActive(lChActive);
			
			lObjLcAccMstVo.setCreatedUserId(new BigDecimal(createdUserId));
			lObjLcAccMstVo.setCreatedPostId(new BigDecimal(createdPostId));
			lObjLcAccMstVo.setCreatedDate(lDtCrtDt);
			
			lObjLcAccMstVo.setLocCode(lStrLocCode);
			lObjLcAccMstVo.setLangId((new BigDecimal(LLngLangId)));
			lObjLcAccMstVo.setDbId((new BigDecimal(lLngDbId)));
			lObjLcAccMstVo.setMajorHd(lStrMjrHd);
			lObjLcAccMstVo.setSubmjrHd(lStrSbMjrHd);
			lObjLcAccMstVo.setMinHd(lStrMinHd);
			lObjLcAccMstVo.setSubHd(lStrSbHd);
			lObjLcAccMstVo.setObjHd(lStrObjHd);
			lObjLcAccMstVo.setDtlHd(lStrDtlHd);
			
			
	/*		lObjPost.setPostId(lLngPostId);
			lObjPost.setParentPostId(parentPostId);
			lObjPost.setPostLevelId(postLevelId);
			lObjPost.setStatusLookupId(statusLookupId);
			lObjPost.setStartDate(startDate);
			lObjPost.setEndDate(endDate);
			lObjPost.setActivateFlag(activateFlag);
			lObjPost.setCreatedBy(createdBy);
			lObjPost.setCreatedByPost(createdByPost);
			lObjPost.setCreatedDate(createdDate);
			lObjPost.setUpdatedBy(updatedBy);
			lObjPost.setUpdatedByPost(updatedByPost);
			lObjPost.setUpdatedDate(updatedDate);
			lObjPost.setLocationCode(locationCode);
			lObjPost.setBranchCode(branchCode);
			lObjPost.setDsgnCode(dsgnCode);*/
			
			
			
			logger.info("---------acc id :: "+lLngAccId);
			logger.info("---------setLcAccNo :: "+lLngAccId);
			logger.info("---------setAccCrtDt :: "+lDtAccCrtDt);
			logger.info("---------setDivisionCode :: "+lStrDivCode);
			logger.info("---------setUnderCode :: "+lStrUnderCode);
			logger.info("---------setUnderCodeDesc :: "+lStrUnderCodeDesc);
			logger.info("---------setBranchCode :: "+lStrBrnchCode);
			
			logger.info("---------lStrMjrHd :: "+lStrMjrHd);
			logger.info("---------lStrSbMjrHd :: "+lStrSbMjrHd);
			logger.info("---------lStrMinHd :: "+lStrMinHd);
			logger.info("---------lStrSbHd :: "+lStrSbHd);
			logger.info("---------lStrObjHd :: "+lStrObjHd);
			logger.info("---------lStrDtlHd :: "+lStrDtlHd);
		
			logger.info("-----------lStrLocationCode"+lStrLocationCode);
			logger.info("-----------lStrAdd1"+ lStrAdd1);
			logger.info("-----------lStrAdd2"+ lStrAdd2);
			logger.info("-----------lStrPin"+ lStrPin);
			
			
			logger.info("----Lc Acc VO in VOGEN-----"+lObjLcAccMstVo);
		
			Map resultMap = new HashMap();
			
			resultMap.putAll(p_objServiceArgs);
			resultMap.put("lObjLcAccMstVo", lObjLcAccMstVo);
			resultMap.put("lStrLocationCode", lStrLocationCode);
			resultMap.put("lStrAdd1", lStrAdd1);
			resultMap.put("lStrAdd2", lStrAdd2);
			resultMap.put("lStrPin", lStrPin);
			resultMap.put("DivName", lStrDivNm);
			resultMap.put("lStrDept", lStrDept);
		
			ResultObject retObj = new ResultObject(ErrorConstants.SUCCESS);
			retObj.setResultValue(resultMap);			
			logger.info("------Returning from VOGEN Of Lc Acc Master-----------");
			return retObj;
			
		}
		catch(Exception e)
		{			
			ResultObject retObj = new ResultObject(ErrorConstants.ERROR);			
			logger.error("----------Error in VOGEN of LC Acc Master----------",e);
			e.printStackTrace();
			return retObj;
		}	
	}
	
	
}
