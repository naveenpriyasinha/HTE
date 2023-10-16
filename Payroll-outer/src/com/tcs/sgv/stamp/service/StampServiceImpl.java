package com.tcs.sgv.stamp.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ajaxtags.xml.AjaxXmlBuilder;

import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.BptmCommonServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.stamp.dao.IndentDetailsDAOImpl;
import com.tcs.sgv.stamp.dao.IndentHeaderDAOImpl;
import com.tcs.sgv.stamp.dao.MstStampCategoryDAOImpl;
import com.tcs.sgv.stamp.dao.MstStampDenominationDAOImpl;
import com.tcs.sgv.stamp.dao.MstStampGroupDAOImpl;
import com.tcs.sgv.stamp.dao.MstStampStockDAOImpl;
import com.tcs.sgv.stamp.dao.MstVenderDAOImpl;
import com.tcs.sgv.stamp.dao.RltDnmLocationDAOImpl;
import com.tcs.sgv.stamp.dao.StampCommonDAOImpl;
import com.tcs.sgv.stamp.dao.TrnStampChallanDtlsDAOImpl;
import com.tcs.sgv.stamp.dao.TrnStampChallanHdrDAOImpl;
import com.tcs.sgv.stamp.dao.TrnStampRegisterDtlsDAOImpl;
import com.tcs.sgv.stamp.dao.TrnStampRegisterHdrDAOImpl;
import com.tcs.sgv.stamp.valueobject.MstStampCategory;
import com.tcs.sgv.stamp.valueobject.MstStampCategoryVO;
import com.tcs.sgv.stamp.valueobject.MstStampDenomination;
import com.tcs.sgv.stamp.valueobject.MstStampDenominationVO;
import com.tcs.sgv.stamp.valueobject.MstStampGroup;
import com.tcs.sgv.stamp.valueobject.MstStampStock;
import com.tcs.sgv.stamp.valueobject.MstVendor;
import com.tcs.sgv.stamp.valueobject.MstVendorVO;
import com.tcs.sgv.stamp.valueobject.RltDnmLocation;
import com.tcs.sgv.stamp.valueobject.TrnStampChallanDtls;
import com.tcs.sgv.stamp.valueobject.TrnStampChallanHdr;
import com.tcs.sgv.stamp.valueobject.TrnStampIndentDtls;
import com.tcs.sgv.stamp.valueobject.TrnStampIndentHdr;
import com.tcs.sgv.stamp.valueobject.TrnStampRegesterDtls;
import com.tcs.sgv.stamp.valueobject.TrnStampRegisterHdr;

public class StampServiceImpl extends ServiceImpl
{
	public ResultObject getStampCategory(Map objectArgs) 
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		MstStampCategoryDAOImpl stampCategory=new MstStampCategoryDAOImpl(MstStampCategory.class,serv.getSessionFactory());
		List categoryList=stampCategory.getStampCategory();
		objectArgs.put("categoryList", categoryList);
		result.setResultValue(objectArgs);
		result.setViewName("stampGroupMst");
		return result;
	}
	public ResultObject insertMstStampGroupData(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		HttpSession session=request.getSession();
		MstStampGroup mstStampGroupVO=(MstStampGroup)objectArgs.get("mstStampGroupVO");
		MstStampGroupDAOImpl stampGroupDAO=new MstStampGroupDAOImpl(MstStampGroup.class,serv.getSessionFactory());
		String strGroupID=request.getParameter("groupID");
		if(strGroupID!=null && strGroupID.length()>0)
		{
			MstStampGroup updateVO=stampGroupDAO.read(new BigDecimal(strGroupID));
			updateVO.setGrpId(new BigDecimal(strGroupID));
			updateVO.setCategoryCode(mstStampGroupVO.getCategoryCode());
			updateVO.setGrpCode(mstStampGroupVO.getGrpCode());
			updateVO.setGrpDesc(mstStampGroupVO.getGrpDesc());
			updateVO.setGrpName(mstStampGroupVO.getGrpName());
			updateVO.setMinHd(mstStampGroupVO.getMinHd());
			updateVO.setMjrHd(mstStampGroupVO.getMjrHd());
			updateVO.setSubHd(mstStampGroupVO.getSubHd());
			updateVO.setSubMjrHd(mstStampGroupVO.getSubMjrHd());
			//stampGroupDAO.update(updateVO);
		}
		else
		{
			BigDecimal lbgdcMstGrpId = new BigDecimal(BptmCommonServiceImpl.getNextSeqNum("mst_stamp_group", objectArgs));
			mstStampGroupVO.setGrpId(lbgdcMstGrpId);
			mstStampGroupVO.setStatus("1");
			//stampGroupDAO.create(mstStampGroupVO);
		}
		session.setAttribute("insertGroup", "1");
		result.setResultValue(objectArgs);		
		return result;
	}
	public ResultObject insertMstStampDenominationData(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		HttpSession session=request.getSession();
		MstStampDenominationDAOImpl stampDenominationDAO=new MstStampDenominationDAOImpl(MstStampDenomination.class,serv.getSessionFactory());
		MstStampDenomination stampDenominationVO=(MstStampDenomination)objectArgs.get("stampDenominationVO");
		String denominationID=request.getParameter("denominationID");
		if(denominationID!=null && denominationID.length()>0)
		{
			MstStampDenomination updateVO=stampDenominationDAO.read(new BigDecimal(denominationID));
			updateVO.setDnmId(new BigDecimal(denominationID));
			updateVO.setDnmCode(stampDenominationVO.getDnmCode());
			updateVO.setDnmDesc(stampDenominationVO.getDnmDesc());
			updateVO.setDnmValue(stampDenominationVO.getDnmValue());
			updateVO.setGrpCode(stampDenominationVO.getGrpCode());
			updateVO.setLabelPerSheet(stampDenominationVO.getLabelPerSheet());
			updateVO.setDiscount(stampDenominationVO.getDiscount());			
			//stampDenominationDAO.update(updateVO);
		}
		else
		{
			BigDecimal lbgdcMstGrpId = new BigDecimal(BptmCommonServiceImpl.getNextSeqNum("mst_stamp_denomination", objectArgs));
			stampDenominationVO.setDnmId(lbgdcMstGrpId);
			stampDenominationVO.setStatus("1");
			//stampDenominationDAO.create(stampDenominationVO);
		}
		session.setAttribute("insertDenomination", "1");
		result.setResultValue(objectArgs);
		result.setViewName("denominationMstAll");
		return result;
	}
	public ResultObject insertMstStampBaseEntry(Map objectArgs)
	{
	
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		HttpSession session=request.getSession();
		MstStampCategory mstStampCategoryVO=(MstStampCategory)objectArgs.get("mstStampCategoryVO");
		MstStampCategoryDAOImpl stampCategoryDAO=new MstStampCategoryDAOImpl(MstStampCategory.class,serv.getSessionFactory());
		String categoryID=request.getParameter("categoryID");
		if(categoryID!=null && categoryID.length()>0)
		{
			MstStampCategory updateVO=stampCategoryDAO.read(new BigDecimal(categoryID));
			updateVO.setCategoryDesc(mstStampCategoryVO.getCategoryDesc());
			updateVO.setCategoryName(mstStampCategoryVO.getCategoryName());
			//stampCategoryDAO.update(updateVO);
		}
		else
		{
			//stampCategoryDAO.create(mstStampCategoryVO);
		}
		session.setAttribute("insertCategory", "1");
		result.setResultValue(objectArgs);	
		result=getAllMstStampCategoryData(objectArgs);
		result.setViewName("StampBaseEntryAll");
		return result;
	}
	public ResultObject updateChallanStatus(Map objectArgs)
	{
	
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		HttpSession session=request.getSession();
		TrnStampChallanHdrDAOImpl ChallanHdrDAO=new TrnStampChallanHdrDAOImpl(TrnStampChallanHdr.class,serv.getSessionFactory());
		String strEnfreshmentNo=request.getParameter("cmbEnfreshmentNo");
		String strBankRefNo=request.getParameter("txtBankRefNo");
		String strChallanBankAmt=request.getParameter("txtChallanBankAmt");
		String strChallanStatus=request.getParameter("cmbChallanStatus");
		String strChallanId=ChallanHdrDAO.getPrimaryKey(strEnfreshmentNo);
		
		TrnStampChallanHdr updateVO=ChallanHdrDAO.read(new BigDecimal(strChallanId));
		updateVO.setBankScrollRefNo(strBankRefNo);
		updateVO.setBankScrollRefAmt(new BigDecimal(strChallanBankAmt));
		updateVO.setStatus(strChallanStatus);
		//ChallanHdrDAO.update(updateVO);
		result=getAllMstStampGroupData(objectArgs);
		return result;
	}
	
	public ResultObject getUpdateDataForMstStampGroup(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		String[] strGroupID=request.getParameterValues("groupID");
		MstStampGroupDAOImpl stampGroupDAO=new MstStampGroupDAOImpl(MstStampGroup.class,serv.getSessionFactory());
		String str=strGroupID[0];
		MstStampGroup groupData=stampGroupDAO.getUpdateDataForMstStampGroup(strGroupID[0].toString());
		objectArgs.put("groupData", groupData);
		result=getStampCategory(objectArgs);
		result.setResultValue(objectArgs);
		result.setViewName("stampGroupMst");
		return result;		
	}
	public ResultObject getUpdateDataForMstStampDenomination(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		String[] denominationID=request.getParameterValues("denominationID");
		MstStampDenominationDAOImpl denominationDAO=new MstStampDenominationDAOImpl(MstStampDenomination.class,serv.getSessionFactory());
		MstStampDenominationVO denominationVO=denominationDAO.getUpdateDataForMstStampDenomination(denominationID[0].toString());
		objectArgs.put("denominationVO",denominationVO);
		result=getAllGroupCode(objectArgs);
		result.setResultValue(objectArgs);
		result.setViewName("denominationMst");
		return result;
	}
	public ResultObject getUpdateDataForMstVendor(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		String[] vendorID=request.getParameterValues("vendorID");
		MstVenderDAOImpl vendorDAO=new MstVenderDAOImpl(MstVendor.class,serv.getSessionFactory());
		MstVendorVO vendorVO=vendorDAO.getUpdateDataForMstVendor(vendorID[0].toString());
		objectArgs.put("vendorVO",vendorVO);
		result.setResultValue(objectArgs);
		result.setViewName("venderMst");
		return result;		
	}
	public ResultObject getUpdateDataForMstStampCategory(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		String[] categoryID=request.getParameterValues("categoryID");
		MstStampCategoryDAOImpl categoryDAO=new MstStampCategoryDAOImpl(MstStampCategory.class,serv.getSessionFactory());
		MstStampCategoryVO categoryVO=categoryDAO.getUpdateDataForMstStampCategory(categoryID[0].toString());
		objectArgs.put("categoryVO",categoryVO);
		result.setResultValue(objectArgs);
		result.setViewName("stampBaseEntry");
		return result;
	}
	public ResultObject deleteDataForMstStampGroup(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		String[] strGroupID=request.getParameterValues("groupID");
		MstStampGroupDAOImpl stampGroupDAO=new MstStampGroupDAOImpl(MstStampGroup.class,serv.getSessionFactory());
		stampGroupDAO.deleteDataForMstStampGroup(strGroupID);
		HttpSession session=request.getSession();
		session.setAttribute("insertGroup", "1");
		result=getAllMstStampGroupData(objectArgs);
		result.setViewName("stampGroupMstAll");
		return result;
	}
	public ResultObject deleteDataForMstStampDenomination(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		String[] denominationID=request.getParameterValues("denominationID");
		MstStampDenominationDAOImpl denominationDAO=new MstStampDenominationDAOImpl(MstStampDenomination.class,serv.getSessionFactory());
		denominationDAO.deleteDataForMstStampDenomination(denominationID);
		result=getAllMstStampDenominationData(objectArgs);
		HttpSession session=request.getSession();
		session.setAttribute("insertDenomination", "1");
		result.setResultValue(objectArgs);
		result.setViewName("denominationMstAll");
		return result;
	}
	public ResultObject deleteDataForMstVender(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		String[] vendorID=request.getParameterValues("vendorID");
		MstVenderDAOImpl vendorDAO=new MstVenderDAOImpl(MstVendor.class,serv.getSessionFactory());
		vendorDAO.deleteDataForMstVender(vendorID);
		result=getAllMstVenderData(objectArgs);
		result.setResultValue(objectArgs);
		HttpSession session=request.getSession();
		session.setAttribute("insert", "1");
		result.setViewName("vendorMstAll");
		return result;
	}
	public ResultObject deleteDataForMstStampBase(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		String[] categoryID=request.getParameterValues("categoryID");
		MstStampCategoryDAOImpl stampCategoryDAO=new MstStampCategoryDAOImpl(MstStampCategory.class,serv.getSessionFactory());
		stampCategoryDAO.deleteDataForMstStampCategory(categoryID);
		result=getAllMstStampCategoryData(objectArgs);
		result.setViewName("StampBaseEntryAll");
		return result;
	}
	public ResultObject insertMstVenderData(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		HttpSession session=request.getSession();
		MstVendor venderVO=(MstVendor)objectArgs.get("venderVO");
		MstVenderDAOImpl venderDAO=new MstVenderDAOImpl(MstVendor.class,serv.getSessionFactory());
		String vendorID=request.getParameter("vendorID");
		if(vendorID!=null && vendorID.length()>0)
		{
			MstVendor updateVO=venderDAO.read(new BigDecimal(vendorID));
			updateVO.setVenId(new BigDecimal(vendorID));
			updateVO.setActive(venderVO.getActive());
			updateVO.setDiscAllowed(venderVO.getDiscAllowed());
			updateVO.setEndDate(venderVO.getEndDate());
			updateVO.setStartDate(venderVO.getStartDate());
			updateVO.setPlaceOfBusiness(venderVO.getPlaceOfBusiness());
			updateVO.setVenAdd(venderVO.getVenAdd());
			updateVO.setVenCode(venderVO.getVenCode());
			updateVO.setVenRegNo(venderVO.getVenRegNo());
			updateVO.setVenName(venderVO.getVenName());			
			//venderDAO.update(updateVO);
		}
		else
		{
			BigDecimal lbgdcMstGrpId = new BigDecimal(BptmCommonServiceImpl.getNextSeqNum("mst_vendor", objectArgs));
			venderVO.setVenId(lbgdcMstGrpId);
			venderVO.setStatus("1");
			//venderDAO.create(venderVO);
		}		
		session.setAttribute("insert", "1");
		//result=getAllMstVenderData(objectArgs);
		result.setResultValue(objectArgs);
		//result.setViewName("vendorMstAll");
		result.setViewName("venderMst");
		return result;
	}
	public ResultObject getAllMstStampGroupData(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		MstStampGroupDAOImpl stampGroupDAO=new MstStampGroupDAOImpl(MstStampGroup.class,serv.getSessionFactory());
		List allStampGroupData=stampGroupDAO.getAllStampGroupData();
		objectArgs.put("allStampGroupData", allStampGroupData);
		result.setResultValue(objectArgs);
		result.setViewName("stampGroupMstAll");
		
		return result;		
	}
	public ResultObject getAllMstStampDenominationData(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		MstStampDenominationDAOImpl stampDenominationDAO=new MstStampDenominationDAOImpl(MstStampDenomination.class,serv.getSessionFactory());
		List allStampDenominationData=stampDenominationDAO.getAllStampDenominationData();
		objectArgs.put("allStampDenominationData", allStampDenominationData);
		result.setResultValue(objectArgs);
		result.setViewName("denominationMstAll");
		return result;		
	}
	public ResultObject getAllMstVenderData(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		MstVenderDAOImpl venderDAO=new MstVenderDAOImpl(MstVendor.class,serv.getSessionFactory());
		List vendorData=venderDAO.getAllMstVenderData();
		objectArgs.put("vendorData", vendorData);
		result.setResultValue(objectArgs);
		result.setViewName("vendorMstAll");
		return result;		
	}
	public ResultObject getAllGroupCode(Map objectArgs)
	{
	
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		MstStampGroupDAOImpl stampGroupDAO=new MstStampGroupDAOImpl(MstStampGroup.class,serv.getSessionFactory());
		List allGroupCode=stampGroupDAO.getAllGroupCode();
		objectArgs.put("groupCode", allGroupCode);
		result.setResultValue(objectArgs);
		result.setViewName("denominationMst");
		return result;
	}
	public ResultObject getAllGroupName(Map objectArgs)
	{
	
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		MstStampGroupDAOImpl stampGroupDAO=new MstStampGroupDAOImpl(MstStampGroup.class,serv.getSessionFactory());
		List allGroupName=stampGroupDAO.getAllGroupName();
		objectArgs.put("groupName", allGroupName);
		result.setResultValue(objectArgs);
		result.setViewName("DLR2SLR");
		return result;
	}
	public ResultObject getAllVendorIndentDetails(Map objectArgs)
	{
		//System.out.println("hi");
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		MstStampGroupDAOImpl stampGroupDAO=new MstStampGroupDAOImpl(MstStampGroup.class,serv.getSessionFactory());
		List allGroupCode=stampGroupDAO.getAllGroupCode();
		TrnStampChallanHdrDAOImpl ChallanDAO=new TrnStampChallanHdrDAOImpl(TrnStampChallanHdr.class,serv.getSessionFactory());
		String strChallanId=ChallanDAO.getChallanID(objectArgs);
		objectArgs.put("groupCode", allGroupCode);
		//System.out.println(""+strChallanId);
		objectArgs.put("ChallanId", strChallanId);
		result.setResultValue(objectArgs);
		result.setViewName("VendorIndent");
		return result;
	}
	public ResultObject getAllMstStampCategoryData(Map objectArgs)
	{
		//System.out.println("Neo is here");
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		MstStampCategoryDAOImpl stampCategoryDAO=new MstStampCategoryDAOImpl(MstStampCategory.class,serv.getSessionFactory());
		List allStampCategoryData=stampCategoryDAO.getAllStampCategoryData();
		objectArgs.put("allStampCategoryData", allStampCategoryData);
		result.setResultValue(objectArgs);
		result.setViewName("StampBaseEntryAll");
		
		return result;		
	}
	public ResultObject getGroupNameByAjax(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		MstStampGroupDAOImpl stampGroupDAO=new MstStampGroupDAOImpl(MstStampGroup.class,serv.getSessionFactory());
		String strGroupCode=request.getParameter("groupCode").toString();
		String strGroupname=stampGroupDAO.getGroupName(strGroupCode);
		String strGroupDescription=stampGroupDAO.getGroupDescription(strGroupCode);
		String tempResult = new AjaxXmlBuilder().addItem("txtStampGroupName", strGroupname).addItem("txtDescription", strGroupDescription).toString();
		objectArgs.put("ajaxKey",tempResult);
		result.setResultValue(objectArgs);
		result.setViewName("ajaxData");
		return result;
	}
	public ResultObject getChallanAmountByAjax(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		TrnStampChallanHdrDAOImpl EnfreshmentNoDAO=new TrnStampChallanHdrDAOImpl(TrnStampChallanHdr.class,serv.getSessionFactory());
		String strEnfreshmentNo=request.getParameter("EnfreshmentNo").toString();
		String strChallanAmt=EnfreshmentNoDAO.getChallanAmount(strEnfreshmentNo);
		String strVendorCode=EnfreshmentNoDAO.getVendorCode(strEnfreshmentNo);
		//System.out.println(""+strVendorCode);
		if (strVendorCode=="" || strVendorCode==null)
		{
			strVendorCode=" ";
		}
		//System.out.println(""+strVendorCode);
		String strVendorName=EnfreshmentNoDAO.getVendorName(strEnfreshmentNo);
		String tempResult = new AjaxXmlBuilder().addItem("txtChallanAmt", strChallanAmt).addItem("txtVenderCode", strVendorCode).addItem("txtVenderName", strVendorName).toString();
		objectArgs.put("ajaxKey",tempResult);
		result.setResultValue(objectArgs);
		result.setViewName("ajaxData");
		return result;
	}
	public ResultObject getComissionByAjax(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		StampCommonDAOImpl commonDAO=new StampCommonDAOImpl(RltDnmLocation.class,serv.getSessionFactory());
		MstStampDenominationDAOImpl DenominationDAO=new MstStampDenominationDAOImpl(MstStampDenomination.class,serv.getSessionFactory());
		String strDenominationCode=request.getParameter("denominationCode").toString();
		String strComission=DenominationDAO.getComissionForDenomination(strDenominationCode, commonDAO);
		String strStampValue=DenominationDAO.getUnitValueForDenominationCode(strDenominationCode, commonDAO);
		String tempResult = new AjaxXmlBuilder().addItem("txtCommission", strComission).addItem("txtStampValue", strStampValue).toString();
		objectArgs.put("ajaxKey",tempResult);
		result.setResultValue(objectArgs);
		result.setViewName("ajaxData");
		return result;
	}
	public ResultObject getMajorHeadByAjax(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		StampCommonDAOImpl commonDAO=new StampCommonDAOImpl(RltDnmLocation.class,serv.getSessionFactory());
		MstStampGroupDAOImpl stampGroupDAO=new MstStampGroupDAOImpl(MstStampGroup.class,serv.getSessionFactory());
		String strGroupCode=request.getParameter("groupCode").toString();
		String strGroupname=stampGroupDAO.getGroupName(strGroupCode);
		String strMajorHead=stampGroupDAO.getMajorHead(strGroupCode, commonDAO);
		String strSubMajorHead=stampGroupDAO.getSubMajorHead(strGroupCode, commonDAO);
		String strMinorHead=stampGroupDAO.getMinorHead(strGroupCode, commonDAO);
		String strSubHead=stampGroupDAO.getSubMinorHead(strGroupCode, commonDAO);
		String tempResult = new AjaxXmlBuilder().addItem("txtStampGroupName", strGroupname).addItem("txtMajorHead", strMajorHead).addItem("txtSubMajorHead", strSubMajorHead).addItem("txtMinorHead", strMinorHead).addItem("txtSubHead", strSubHead).toString();
		objectArgs.put("ajaxKey",tempResult);
		result.setResultValue(objectArgs);
		result.setViewName("ajaxData");
		return result;
	}
	public ResultObject getVendorNameByAjax(Map objectArgs)
	{
		//System.out.println("Inside Ajax Page");
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		StampCommonDAOImpl commonDAO=new StampCommonDAOImpl(RltDnmLocation.class,serv.getSessionFactory());
		MstVenderDAOImpl VendorDAO=new MstVenderDAOImpl(MstVendor.class,serv.getSessionFactory());
		String strVendorCode=request.getParameter("VendorCode").toString();
		String strVendorname=VendorDAO.getVendorName(strVendorCode, commonDAO);
		String tempResult = new AjaxXmlBuilder().addItem("txtVenderName", strVendorname).toString();
		objectArgs.put("ajaxKey",tempResult);
		result.setResultValue(objectArgs);
		result.setViewName("ajaxData");
		return result;
	}
	
	
	public ResultObject getGroupName(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		MstStampGroupDAOImpl stampGroupDAO=new MstStampGroupDAOImpl(MstStampGroup.class,serv.getSessionFactory());
		List groupName=stampGroupDAO.getGroupName();
		String strPagename="";
		strPagename=request.getParameter("pageName");
		if(strPagename==null)
		{
			strPagename="";
		}
		objectArgs.put("groupName",groupName);
		result.setResultValue(objectArgs);
		if(strPagename.equals("IndentPreparation")==true)
		{
			result.setViewName("toIndentPreparation");
		}
		else if(strPagename.equals("stampReceipt")==true)
		{
			StampCommonDAOImpl commonDAO=new StampCommonDAOImpl(RltDnmLocation.class,serv.getSessionFactory());
			MstStampDenominationDAOImpl stampDenomination=new MstStampDenominationDAOImpl(MstStampDenomination.class,serv.getSessionFactory());
			Hashtable table=stampDenomination.getDenominationCodeWithTotalSheet(commonDAO);
			objectArgs.put("DenominationTable", table);
			result.setViewName("stampReceipt");
		}
		else if(strPagename.equals("singleLockRegReport")==true)
		{
			result.setViewName("singleLockRegReport");
		}
		else if(strPagename.equals("DnmMstForSubTreasury")==true)
		{
			result.setViewName("DnmMstForSubTreasury");
		}
		else if(strPagename.equals("DLR2SLR")==true)
		{
			StampCommonDAOImpl commonDAO=new StampCommonDAOImpl(RltDnmLocation.class,serv.getSessionFactory());
			MstStampDenominationDAOImpl stampDenomination=new MstStampDenominationDAOImpl(MstStampDenomination.class,serv.getSessionFactory());
			Hashtable table=stampDenomination.getDenominationCodeWithTotalSheet(commonDAO);
			objectArgs.put("DenominationTable", table);
			result.setViewName("DLR2SLR");
		}		
		return result;
	}
	public ResultObject getSubTreasuryName(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		MstStampGroupDAOImpl stampGroupDAO=new MstStampGroupDAOImpl(MstStampGroup.class,serv.getSessionFactory());
		String strLocationCode=SessionHelper.getLocationCode(objectArgs);
		List subTresuryName=stampGroupDAO.getSubTreasuryName("100101");
		objectArgs.put("subTresuryName", subTresuryName);
		result.setResultValue(objectArgs);
		result.setViewName("toIndentPreparation");
		return result;
	}
	public ResultObject generateIndentData(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		HttpSession session=request.getSession();
		String strFromDate=null;
		String strToDate=null;
		String strStampGroupCode=null;
		String strTresuryInfo=null;
		String strSubTresuryCode=null;
		try
		{
			strFromDate=StringUtility.getParameter("dtFromDate",request);
			session.setAttribute("fromDate", strFromDate);
			strToDate=StringUtility.getParameter("dtToDate",request);
			session.setAttribute("toDate", strToDate);
			strStampGroupCode=StringUtility.getParameter("cmbStampGroupName",request);
			strTresuryInfo=StringUtility.getParameter("rdTreasuryAndSubTreasury",request);
			if(strTresuryInfo.equals("2")==true)
			{
				strSubTresuryCode=StringUtility.getParameter("cmbSubTreasuty",request);
				session.setAttribute("subTresuryCode",strSubTresuryCode);
			}
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		MstStampGroupDAOImpl stampGroupDAO=new MstStampGroupDAOImpl(MstStampGroup.class,serv.getSessionFactory());
		List[] groupCodeAndName=stampGroupDAO.getGroupNameList(strStampGroupCode);
		List groupCode=groupCodeAndName[0];
		List groupName=groupCodeAndName[1];
		List outerList=stampGroupDAO.getDetailsForEachGroupCode(groupCode);
		objectArgs.put("groupName", groupName);
		objectArgs.put("denominationTable", outerList);
		
		session.setAttribute("groupName", groupName);
		session.setAttribute("denominationTable", outerList);
		result.setResultValue(objectArgs);
		result.setViewName("indentDetails");
		return result;
	}
	
	public ResultObject saveIndentData(Map objectArgs)
	{
		
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		HttpSession session=request.getSession();
		List groupName=(List)session.getAttribute("groupName");
		List denominationTable=(List)session.getAttribute("denominationTable");
		Iterator itGroup=groupName.iterator();
		Iterator itTable=denominationTable.iterator();
		String strFromDate=session.getAttribute("fromDate").toString();
		String strToDate=session.getAttribute("toDate").toString();
		String strSubTresuryCode="";
		long grossAmount=0;
		try
		{
			strSubTresuryCode=session.getAttribute("subTresuryCode").toString();
		}
		catch(Exception ex)
		{
			
		}
		String strReqQty="reqQty_";
		String strPassedQty="passedQty_";
		String strRemark="remark_";
		String strReqQtyData="";
		String strPassedQtyData="";
		String strRemarkData="";
		boolean flag=true;
		int temp=1;
		String presentIndentNo=null;	
		List displayGroupname=new ArrayList();
		List displayDenominationTable=new ArrayList();
		String strIndentID=null;
		IndentHeaderDAOImpl indentHeader=new IndentHeaderDAOImpl(TrnStampIndentHdr.class,serv.getSessionFactory());
		BigDecimal headerPK=null;
		while(itGroup.hasNext())
		{
			String strGroupname=itGroup.next().toString();
			displayGroupname.add(strGroupname);
			List outerList=(List)itTable.next();
			Iterator itOuter=outerList.iterator();
			List displayOuterList=new ArrayList();
			int srNo=1;
			while(itOuter.hasNext())
			{
				List displayInnerList=new ArrayList();
				List innerList=(List)itOuter.next();
				Iterator itInner=innerList.iterator();
				int count=0;
				String strDenomination="";
				String strStockOnLastIndent="";
				String strSupplyAgainstLastIndent="";
				String strPrevIndentQtySold="";
				String strClosingBalance="";
				String strQtyDue="";
				
				while(itInner.hasNext())
				{
					
					String strData=itInner.next().toString();
					
					if(count==1)
					{
						strDenomination=strData;
						displayInnerList.add(srNo);
						srNo++;
						displayInnerList.add(strData);
						strReqQty="reqQty_"+strData+"_"+strGroupname;
						strPassedQty="passedQty_"+strData+"_"+strGroupname;
						strRemark="remark_"+strData+"_"+strGroupname;
						try
						{
							strReqQtyData=StringUtility.getParameter(strReqQty, request);
							strPassedQtyData=StringUtility.getParameter(strPassedQty, request);
							strRemarkData=StringUtility.getParameter(strRemark, request);
							
						}
						catch(Exception ex)
						{
						
						}
					}
					else if(count==2)
					{
						strStockOnLastIndent=strData;
						displayInnerList.add(strData);
					}
					else if(count==3)
					{
						strSupplyAgainstLastIndent=strData;
						displayInnerList.add(strData);
					}
					else if(count==4)
					{
						strPrevIndentQtySold=strData;
						displayInnerList.add(strData);
					}
					else if(count==5)
					{
						strClosingBalance=strData;
						displayInnerList.add(strData);
					}
					else if(count==6)
					{
						strQtyDue=strData;
						displayInnerList.add(strData);
					}
					count++;
				}
				displayInnerList.add(strReqQtyData);
				displayInnerList.add(strPassedQtyData);
				displayInnerList.add(strRemarkData);
				displayOuterList.add(displayInnerList);
				String strLocationCode=SessionHelper.getLocationCode(objectArgs);
				String strIndentType="Normal";
				
				MstStampGroupDAOImpl stampDAO=new MstStampGroupDAOImpl(MstStampGroup.class,serv.getSessionFactory());
				
				try
				{
					strIndentID=session.getAttribute("IndentNo").toString();
				}
				catch(Exception ex)
				{
					if(flag==true)
					{
						strIndentID=stampDAO.getIndentID(objectArgs);
						session.setAttribute("IndentNo", strIndentID);
					}
				}
				
				presentIndentNo=isIndentPresent(strIndentID,serv);
				String strGroupCode=stampDAO.getGroupCode(strGroupname);
				String strDenominationCode=stampDAO.getDenominationCode(strDenomination, strGroupCode);
				if(flag==true)
				{
					//IndentHeaderDAOImpl indentHeader=new IndentHeaderDAOImpl(TrnStampIndentHdr.class,serv.getSessionFactory());
					TrnStampIndentHdr indentHeaderVO=new TrnStampIndentHdr();
					BigDecimal indentID=BigDecimal.valueOf(Long.parseLong(strIndentID));
					indentHeaderVO.setIndNo(indentID);
					try
					{
						Date fromDate= new SimpleDateFormat("dd/MM/yy").parse(strFromDate);
						Date toDate=new SimpleDateFormat("dd/MM/yy").parse(strToDate);
						indentHeaderVO.setFromDate(fromDate);
						indentHeaderVO.setToDate(toDate);
					}
					catch(Exception ex)
					{}
					
					Integer locationCode=Integer.parseInt(strLocationCode);
					indentHeaderVO.setLocCode(locationCode);
					indentHeaderVO.setTypeCode(strIndentType);
					indentHeaderVO.setStatus("Open");
					indentHeaderVO.setGrossAmnt(new BigDecimal("0"));
					if(presentIndentNo!=null)
					{
						TrnStampIndentHdr updateHeaderVO=indentHeader.read(new BigDecimal(presentIndentNo));
						updateHeaderVO.setIndHdrId(new BigDecimal(presentIndentNo));
						updateHeaderVO.setIndNo(indentHeaderVO.getIndNo());
						updateHeaderVO.setFromDate(indentHeaderVO.getFromDate());
						updateHeaderVO.setToDate(indentHeaderVO.getToDate());
						updateHeaderVO.setLocCode(indentHeaderVO.getLocCode());
						updateHeaderVO.setTypeCode(indentHeaderVO.getTypeCode());
						updateHeaderVO.setStatus(indentHeaderVO.getStatus());
						updateHeaderVO.setGrossAmnt(indentHeaderVO.getGrossAmnt());
						//indentHeader.update(updateHeaderVO);
					}
					else
					{
						BigDecimal lbgdcMstGrpId = new BigDecimal(BptmCommonServiceImpl.getNextSeqNum("trn_stamp_indent_hdr", objectArgs));
						headerPK=lbgdcMstGrpId;
						indentHeaderVO.setIndHdrId(lbgdcMstGrpId);
						//indentHeader.create(indentHeaderVO);
					}
					flag=false;
				}
				
				IndentDetailsDAOImpl indentDetails=new IndentDetailsDAOImpl(TrnStampIndentDtls.class,serv.getSessionFactory());
				TrnStampIndentDtls indentDetailsVO=new TrnStampIndentDtls();
				
				indentDetailsVO.setIndNo(BigDecimal.valueOf(Long.parseLong(strIndentID)));
				indentDetailsVO.setGrpCode(Short.parseShort(strGroupCode));
				indentDetailsVO.setDnmValue(Integer.parseInt(strDenomination));				
				temp++;
				indentDetailsVO.setStockOnLastInd(Integer.parseInt(strStockOnLastIndent));	
				indentDetailsVO.setSupplyAgainstLastInd(Integer.parseInt(strSupplyAgainstLastIndent));
				indentDetailsVO.setQtySold(Integer.parseInt(strPrevIndentQtySold));
				indentDetailsVO.setQtyDue(Integer.parseInt(strQtyDue));
				indentDetailsVO.setQtyRequd(Integer.parseInt(strReqQtyData));
				indentDetailsVO.setQtyPassed(Integer.parseInt(strPassedQtyData));
				grossAmount=grossAmount+(Integer.parseInt(strPassedQtyData)*Integer.parseInt(strDenomination));
				indentDetailsVO.setRemarks(strRemarkData);
				indentDetailsVO.setClosingBalPrevPeriod(Integer.parseInt(strClosingBalance));
				StampCommonDAOImpl commonDAO=new StampCommonDAOImpl(RltDnmLocation.class,serv.getSessionFactory());
				Hashtable fieldList=new Hashtable();
				fieldList.put("ind_no",strIndentID);
				fieldList.put("grp_code",strGroupCode);
				fieldList.put("dnm_value",strDenomination);
				String strPresentDetailsID=commonDAO.isPresent("trn_stamp_indent_dtls", fieldList, "ind_dtl_id");
				if(strPresentDetailsID!=null && strPresentDetailsID.length()>0)
				{
					TrnStampIndentDtls detailsUpdateVO=indentDetails.read(new BigDecimal(strPresentDetailsID));
					detailsUpdateVO.setIndDtlId(new BigDecimal(strPresentDetailsID));
					detailsUpdateVO.setIndNo(indentDetailsVO.getIndNo());
					detailsUpdateVO.setGrpCode(indentDetailsVO.getGrpCode());
					detailsUpdateVO.setDnmValue(indentDetailsVO.getDnmValue());				
					detailsUpdateVO.setStockOnLastInd(indentDetailsVO.getStockOnLastInd());	
					detailsUpdateVO.setSupplyAgainstLastInd(indentDetailsVO.getSupplyAgainstLastInd());
					detailsUpdateVO.setQtySold(indentDetailsVO.getQtySold());
					detailsUpdateVO.setQtyDue(indentDetailsVO.getQtyDue());
					detailsUpdateVO.setQtyRequd(indentDetailsVO.getQtyRequd());
					detailsUpdateVO.setQtyPassed(indentDetailsVO.getQtyPassed());
					detailsUpdateVO.setRemarks(indentDetailsVO.getRemarks());
					detailsUpdateVO.setClosingBalPrevPeriod(indentDetailsVO.getClosingBalPrevPeriod());
					//indentDetails.update(detailsUpdateVO);
				}
				else
				{
					BigDecimal lbgdcMstGrpIds = new BigDecimal(BptmCommonServiceImpl.getNextSeqNum("trn_stamp_indent_dtls", objectArgs));
					indentDetailsVO.setIndDtlId(lbgdcMstGrpIds);
					//indentDetails.create(indentDetailsVO);
				}
			}
			displayDenominationTable.add(displayOuterList);
		}
		if(headerPK==null)
		{
			headerPK=new BigDecimal(presentIndentNo);
		}
		TrnStampIndentHdr updateGrossAmtVO=indentHeader.read(headerPK);
		updateGrossAmtVO.setIndHdrId(headerPK);
		String strValue=String.valueOf(grossAmount);
		updateGrossAmtVO.setGrossAmnt(new BigDecimal(strValue));
		//indentHeader.update(updateGrossAmtVO);
		
		objectArgs.put("groupName", displayGroupname);
		session.setAttribute("displayGroupname", displayGroupname);
		objectArgs.put("denominationTable", displayDenominationTable);
		session.setAttribute("displayDenominationTable", displayDenominationTable);
		result.setResultValue(objectArgs);
		result.setViewName("displayIndentDetails");
		session.setAttribute("IndentNo",null);
		return result;
	}
	public String isIndentPresent(String strIndentID,ServiceLocator serv)
	{
		String result=null;;
		IndentHeaderDAOImpl indentHeader=new IndentHeaderDAOImpl(TrnStampIndentHdr.class,serv.getSessionFactory());
		result=indentHeader.isIndentPresent(strIndentID);
		return result;
	}
	public ResultObject getEditModeIndentData(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		HttpSession session=request.getSession();
		List groupName=(List)session.getAttribute("displayGroupname");
		List denominationTable=(List)session.getAttribute("displayDenominationTable");
		objectArgs.put("groupName", groupName);
		objectArgs.put("denominationTable", denominationTable);
		result.setResultValue(objectArgs);
		result.setViewName("indentDetails");
		return result;
	}
	
	
	public ResultObject getIndentDataFromDB(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		MstStampGroupDAOImpl stampDAO=new MstStampGroupDAOImpl(MstStampGroup.class,serv.getSessionFactory());
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		String strIndentID=request.getParameter("InvoiceNo");
		String strFromDate=request.getParameter("FromDate");
		String strToDate=request.getParameter("ToDate");
		HttpSession session=request.getSession();
		session.setAttribute("fromDate", strFromDate);
		session.setAttribute("toDate",strToDate);
		session.setAttribute("IndentNo", strIndentID);
		IndentDetailsDAOImpl indentDetail=new IndentDetailsDAOImpl(TrnStampIndentDtls.class,serv.getSessionFactory());
		List[] groupNameAndDenoTable=indentDetail.getGroupnameAndDenominationTable(strIndentID);
		List groupNameFromDB=groupNameAndDenoTable[0];
		List denominationFromDB=groupNameAndDenoTable[1];
		
		session.setAttribute("displayGroupname", groupNameFromDB);
		session.setAttribute("displayDenominationTable", denominationFromDB);
		session.setAttribute("groupName", groupNameFromDB);
		session.setAttribute("denominationTable", denominationFromDB);
		objectArgs.put("groupName", groupNameFromDB);
		objectArgs.put("denominationTable", denominationFromDB);
		result.setResultValue(objectArgs);
		result.setViewName("displayIndentDetails");
		return result;
	}
	
	public ResultObject getIndentID(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		IndentHeaderDAOImpl indentHeader=new IndentHeaderDAOImpl(TrnStampIndentHdr.class,serv.getSessionFactory());
		List indentData=indentHeader.getIndentData();
		objectArgs.put("indentID", indentData);
		result.setResultValue(objectArgs);
		return result;
	}
	public ResultObject getDenominationCode(Map objectArgs)
	{
		//System.out.println("I am inside");
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		String strGroupCode=request.getParameter("groupname").toString();
		MstStampDenominationDAOImpl stampDenomination=new MstStampDenominationDAOImpl(MstStampDenomination.class,serv.getSessionFactory());
		List denominationCode=stampDenomination.getDenominationCode(strGroupCode);
		
		try
		{	
		String AjaxResult = new AjaxXmlBuilder().addItems(denominationCode,"desc","id").toString();
		objectArgs.put("denominationCode",denominationCode);
		objectArgs.put("ajaxKey",AjaxResult);
		result.setResultValue(objectArgs);
		result.setViewName("ajaxData");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}		
		return result;
	}
	public ResultObject setMinimumStockTresuryAndSubTresury(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		String strGroupCode=request.getParameter("cmbGroupName");
		MstStampDenominationDAOImpl denominationDAO=new MstStampDenominationDAOImpl(MstStampDenomination.class,serv.getSessionFactory());
		List denominationDetails=denominationDAO.setMinimumStockForTresuryAndSubTresury(strGroupCode);
		objectArgs.put("denominationData", denominationDetails);
		objectArgs.put("groupCode", strGroupCode);
		RltDnmLocationDAOImpl dnmLocationDAO=new RltDnmLocationDAOImpl(RltDnmLocation.class,serv.getSessionFactory());
		ArrayList checkboxStatus=dnmLocationDAO.getCheckBoxStatus(strGroupCode,serv);
		objectArgs.put("checkBoxStatus", checkboxStatus);
		result=getGroupName(objectArgs);
		result.setResultValue(objectArgs);
		result.setViewName("DnmMstForSubTreasury");		
		return result;
	}
	public ResultObject insertMinStockData(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		String[] chkList=(String[])request.getParameterValues("denominationID");
		String strGroup=request.getParameter("cmbGroupName");
		Hashtable table=new Hashtable();
		for(int count=0;(chkList!=null && count<chkList.length);count++)
		{
			String strKey=chkList[count].toString();
			String strValue=request.getParameter("txt"+strKey);
			if(strValue==null || strValue.length()==0)
			{
				strValue="0";
			}
			table.put(strKey,strValue);
		}
		RltDnmLocationDAOImpl dnmLocationDAO=new RltDnmLocationDAOImpl(RltDnmLocation.class,serv.getSessionFactory());
		ArrayList[] dnmLocationIDAndGroupCode=dnmLocationDAO.insertMinStockData(table,serv,objectArgs);
		ArrayList groupList=dnmLocationIDAndGroupCode[1];
		dnmLocationDAO.updateStatus(dnmLocationIDAndGroupCode,serv,objectArgs,strGroup);
		ArrayList checkboxStatus=dnmLocationDAO.getCheckBoxStatus(strGroup,serv);
		objectArgs.put("checkBoxStatus", checkboxStatus);
		result=getGroupName(objectArgs);
		result.setResultValue(objectArgs);
		result.setViewName("DnmMstForSubTreasury");
		return result;
	}
	public ResultObject insertReceiptData(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		List stockVOList=(List)objectArgs.get("voList");
		BigDecimal lbgdcTrnHdrId = new BigDecimal(BptmCommonServiceImpl.getNextSeqNum("trn_stamp_register_hdr", objectArgs));
		TrnStampRegisterHdr regHDRVO=(TrnStampRegisterHdr)objectArgs.get("registerHDRVO");
		TrnStampRegisterHdrDAOImpl regHdrDAO=new TrnStampRegisterHdrDAOImpl(TrnStampRegisterHdr.class,serv.getSessionFactory());
		regHDRVO.setTrnRegId(lbgdcTrnHdrId);
		List detailsVOList=(List)objectArgs.get("detailsList");
		
		if(detailsVOList.size()>0)
		{
			Iterator itResult=detailsVOList.iterator();
			while(itResult.hasNext())
			{
				TrnStampRegesterDtls detailsVO=(TrnStampRegesterDtls)itResult.next();
				BigDecimal lbgdcTrnDtlId = new BigDecimal(BptmCommonServiceImpl.getNextSeqNum("trn_stamp_regester_dtls", objectArgs));
				detailsVO.setTrnRegId(lbgdcTrnHdrId);
				detailsVO.setTrnRegDtlsId(lbgdcTrnDtlId);
			}
		}	
		//System.out.println("I am started - Imran");
		addTransaction(objectArgs,regHDRVO,detailsVOList,stockVOList);
		//System.out.println("I am completed - Imran");
		result=getGroupName(objectArgs);
		StampCommonDAOImpl commonDAO=new StampCommonDAOImpl(RltDnmLocation.class,serv.getSessionFactory());
		MstStampDenominationDAOImpl stampDenomination=new MstStampDenominationDAOImpl(MstStampDenomination.class,serv.getSessionFactory());
		Hashtable table=stampDenomination.getDenominationCodeWithTotalSheet(commonDAO);
		objectArgs.put("DenominationTable", table);
		result=getIndentID(objectArgs);
		result.setResultValue(objectArgs);
		result.setViewName("stampReceipt");
		return result;
	}
	public ResultObject insertVendorIndentData(Map objectArgs)
	{
		//System.out.println("i am neo");
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		
		TrnStampChallanHdr challanHdrVO=(TrnStampChallanHdr)objectArgs.get("challanHdrVO");
		TrnStampChallanHdrDAOImpl challanHdrDAO=new TrnStampChallanHdrDAOImpl(TrnStampChallanHdr.class,serv.getSessionFactory());
		//challanHdrDAO.create(challanHdrVO);
		
		//System.out.println("i am neo1");
		
		TrnStampChallanDtlsDAOImpl detailsDAO=new TrnStampChallanDtlsDAOImpl(TrnStampChallanDtls.class,serv.getSessionFactory());
		List detailsList=(List)objectArgs.get("detailsList");
		if(detailsList.size()>0)
		{
			Iterator itResult=detailsList.iterator();
			while(itResult.hasNext())
			{
				TrnStampChallanDtls detailsVO=(TrnStampChallanDtls)itResult.next();
				//detailsDAO.create(detailsVO);
			}
		}	
		result.setResultValue(objectArgs);
		result.setViewName("VendorIndent");		
		return result;
		
	}
	
	public ResultObject insertDLR2SLRData(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		
		//register header entry
		BigDecimal lbgdcMstGrpId = new BigDecimal(BptmCommonServiceImpl.getNextSeqNum("trn_stamp_register_hdr", objectArgs));
		TrnStampRegisterHdr regHDRVO=(TrnStampRegisterHdr)objectArgs.get("registerHDRVO");
		TrnStampRegisterHdrDAOImpl regHdrDAO=new TrnStampRegisterHdrDAOImpl(TrnStampRegisterHdr.class,serv.getSessionFactory());
		regHDRVO.setTrnRegId(lbgdcMstGrpId);
		//regHdrDAO.create(regHDRVO);
		//register details entry
		TrnStampRegisterDtlsDAOImpl detailsDAO=new TrnStampRegisterDtlsDAOImpl(TrnStampRegesterDtls.class,serv.getSessionFactory());
		List detailsList=(List)objectArgs.get("detailsList");
		if(detailsList.size()>0)
		{
			Iterator itResult=detailsList.iterator();
			while(itResult.hasNext())
			{
				TrnStampRegesterDtls detailsVO=(TrnStampRegesterDtls)itResult.next();
				detailsVO.setTrnRegId(lbgdcMstGrpId);
				//detailsDAO.create(detailsVO);
			}
		}	
		result=getGroupName(objectArgs);
		StampCommonDAOImpl commonDAO=new StampCommonDAOImpl(RltDnmLocation.class,serv.getSessionFactory());
		MstStampDenominationDAOImpl stampDenomination=new MstStampDenominationDAOImpl(MstStampDenomination.class,serv.getSessionFactory());
		Hashtable table=stampDenomination.getDenominationCodeWithTotalSheet(commonDAO);
		objectArgs.put("DenominationTable", table);
		result=getIndentID(objectArgs);
		result.setResultValue(objectArgs);
		result.setViewName("DLR2SLR");
		return result;
		
	}
	public ResultObject getDenominationCodeByAjax(Map objectArgs)
	{
		//System.out.println("I am inside");
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		String strGroupName=request.getParameter("groupname").toString();
		MstStampGroupDAOImpl stampDAO=new MstStampGroupDAOImpl(MstStampGroup.class,serv.getSessionFactory());
		String strGroupCode=stampDAO.getGroupCode(strGroupName);
		MstStampDenominationDAOImpl stampDenomination=new MstStampDenominationDAOImpl(MstStampDenomination.class,serv.getSessionFactory());
		List denominationCode=stampDenomination.getDenominationCode(strGroupCode);
		try
		{	
		String AjaxResult = new AjaxXmlBuilder().addItems(denominationCode,"desc","id").toString();
		objectArgs.put("denominationCode",denominationCode);
		objectArgs.put("ajaxKey",AjaxResult);
		result.setResultValue(objectArgs);
		result.setViewName("ajaxData");
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}		
		return result;
	}
	public ResultObject getPrintingStatus(Map objectArgs)
		{
			//System.out.println("I am inside");
			ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
			ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
			CmnLookupMstDAOImpl cmnDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
			List PrintingStatus = cmnDAO.getAllChildrenByLookUpNameAndLang("Printing Status",SessionHelper.getLangId(objectArgs));
			objectArgs.put("PrintingStatus",PrintingStatus); 
			result.setResultValue(objectArgs);
			result.setViewName("ChallanEditing");
			return result;
		}
	public ResultObject getChallanStatus(Map objectArgs)
	{
		//System.out.println("I am inside");
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		CmnLookupMstDAOImpl cmnDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
		List ChallanStatus = cmnDAO.getAllChildrenByLookUpNameAndLang("ChallanStatus",SessionHelper.getLangId(objectArgs));
		objectArgs.put("ChallanStatus",ChallanStatus);
		
		TrnStampChallanHdrDAOImpl EnfreshmentNo=new TrnStampChallanHdrDAOImpl(TrnStampChallanHdr.class,serv.getSessionFactory());
		List EnfreshmentNoList=EnfreshmentNo.getEnfreshmentNo();
		objectArgs.put("EnfreshmentNoList", EnfreshmentNoList);
		
		String strPagename="";
		strPagename=request.getParameter("pageName");
		if(strPagename==null)
		{
			strPagename="";
		}
		result.setResultValue(objectArgs);
		if(strPagename.equals("challanCancellation")==true)
		{
			result.setViewName("challanCancellation");
		}
		else if(strPagename.equals("challanAuthorization")==true)
		{
			result.setViewName("challanAuthorization");
		}
		
		return result;
	}
	public ResultObject getIndentValueByAjax(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		IndentHeaderDAOImpl headerDAO=new IndentHeaderDAOImpl(TrnStampIndentHdr.class,serv.getSessionFactory());
		StampCommonDAOImpl commonDAO=new StampCommonDAOImpl(RltDnmLocation.class,serv.getSessionFactory());
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		String strIndentNo=request.getParameter("indent");
		String tempResult="";
		if(strIndentNo.equals("0")==true)
		{
			tempResult = new AjaxXmlBuilder().addItem("stampIndentValue","-").toString();
			objectArgs.put("ajaxKey",tempResult);
			result.setResultValue(objectArgs);
			result.setViewName("ajaxData");
			return result;
		}		
		String strIndentValue=headerDAO.getIndentValue(strIndentNo,commonDAO);
		tempResult = new AjaxXmlBuilder().addItem("stampIndentValue", strIndentValue).toString();
		objectArgs.put("ajaxKey",tempResult);
		result.setResultValue(objectArgs);
		result.setViewName("ajaxData");
		return result;
	}
	public ResultObject getUnitValueForDenominationCodeByAjax(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		String strDenominationCode=request.getParameter("denoCode");
		MstStampDenominationDAOImpl denominationDAO=new MstStampDenominationDAOImpl(MstStampDenomination.class,serv.getSessionFactory());
		StampCommonDAOImpl commonDAO=new StampCommonDAOImpl(RltDnmLocation.class,serv.getSessionFactory());
		String strUnitValue=denominationDAO.getUnitValueForDenominationCode(strDenominationCode,commonDAO);
		String tempResult="";
		if((strUnitValue!=null && strUnitValue.equals("0")==true) || strUnitValue==null || strUnitValue.length()==0)
		{
			tempResult = new AjaxXmlBuilder().addItem("txtUnitValue","-").toString();
			objectArgs.put("ajaxKey",tempResult);
			result.setResultValue(objectArgs);
			result.setViewName("ajaxData");
			return result;
		}		
		tempResult = new AjaxXmlBuilder().addItem("txtUnitValue", strUnitValue).toString();
		objectArgs.put("ajaxKey",tempResult);
		result.setResultValue(objectArgs);
		result.setViewName("ajaxData");
		return result;
	}
	public void addTransaction(Map objectArgs, TrnStampRegisterHdr regHeaderVO, List regDetailsVOList, List stampStockVOList )
	{
//		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
//		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");

//		HttpSession session=request.getSession();
//		TrnStampRegisterHdr trnStampRegisterHdrVO = new TrnStampRegisterHdr();
//		TrnStampRegisterHdr trnStampRegisterHdrVO = regHeaderVO;
		String locCd = String.valueOf(regHeaderVO.getLocCode());
		String typeCode = regHeaderVO.getTypeCode();
		TrnStampRegisterHdrDAOImpl trnRegHdrDAO=new TrnStampRegisterHdrDAOImpl(TrnStampRegisterHdr.class,serv.getSessionFactory());
/*		String typeCode = "03";
		BigDecimal trnHdrRegId = new BigDecimal(BptmCommonServiceImpl.getNextSeqNum("trn_stamp_register_hdr", objectArgs));
		trnStampRegisterHdrVO.setTrnRegId(trnHdrRegId);
		trnStampRegisterHdrVO.setTypeCode(typeCode);
		trnStampRegisterHdrVO.setRefDocNo(new BigDecimal("011"));
		trnStampRegisterHdrVO.setLocCode(new Long("82").longValue());
		trnStampRegisterHdrVO.setTrnDate(new Date());
		
		trnStampRegisterHdrVO.setGrossAmt(new BigDecimal(1));
		trnStampRegisterHdrVO.setCreatedDate(new Date());
		trnStampRegisterHdrVO.setCreatedPostId(new BigDecimal(1));
		trnStampRegisterHdrVO.setCreatedUserId(new BigDecimal(1));

		trnStampRegisterHdrVO.setDbId(new BigDecimal(1));
		trnStampRegisterHdrVO.setPartyAddress("");
		trnStampRegisterHdrVO.setVendorCode(new Short("1"));
		trnStampRegisterHdrVO.setVendorPartyName("");
*/		
		//trnRegHdrDAO.create(regHeaderVO);
/**** Detail Insertions Start ***************/
		Iterator dtlVOIterator = regDetailsVOList.iterator();
		while(dtlVOIterator.hasNext())
		{
			TrnStampRegesterDtls trnStampRegisterDtlVO = (TrnStampRegesterDtls) dtlVOIterator.next();
			TrnStampRegisterDtlsDAOImpl trnDtlDAO=new TrnStampRegisterDtlsDAOImpl(TrnStampRegesterDtls.class,serv.getSessionFactory());
/*
			BigDecimal trnDtlRegId = new BigDecimal(BptmCommonServiceImpl.getNextSeqNum("trn_stamp_regester_dtls", objectArgs));
			trnStampRegisterDtlVO.setTrnRegDtlsId(trnDtlRegId);
			trnStampRegisterDtlVO.setTrnRegId(trnHdrRegId);
			trnStampRegisterDtlVO.setDnmCode(1);
			trnStampRegisterDtlVO.setDnmValue(1);
			trnStampRegisterDtlVO.setSheets(new Integer(1));
			trnStampRegisterDtlVO.setStamps(new Integer(1));
			//TODO
			
			trnStampRegisterDtlVO.setTotalStamps(101);
			trnStampRegisterDtlVO.setCreatedDate(new Date());
			trnStampRegisterDtlVO.setCreatedPostId(new BigDecimal(1));
			trnStampRegisterDtlVO.setCreatedUserId(new BigDecimal(1));			
			trnStampRegisterDtlVO.setGrpCode(s);
*/			
			//trnDtlDAO.create(trnStampRegisterDtlVO);
			/********** Insert into Stock Table **************/
			
			MstStampStock mstStpStkVO = new MstStampStock();
			MstStampStockDAOImpl mstStpStkDAO=new MstStampStockDAOImpl (MstStampStock.class,serv.getSessionFactory());

			String grpCd = trnStampRegisterDtlVO.getGrpCode().toString();
			String dnmCd = new Integer(trnStampRegisterDtlVO.getDnmCode()).toString();
			
			String stkId = mstStpStkDAO.getStockId(grpCd, dnmCd, locCd);
/**
			 03	Issued from Treasury to sub-treasury
			 05	Issued to Vendor/Party
			 We will reduce quantity of Single Lock Register in above both cases
*/			 
			if("03".equals(typeCode) || "05".equals(typeCode))
			{
				mstStpStkVO = mstStpStkDAO.read(new BigDecimal(stkId));
			
				mstStpStkVO.setSheetsSl(mstStpStkVO.getSheetsSl() - trnStampRegisterDtlVO.getSheets());
				mstStpStkVO.setStampsSl(mstStpStkVO.getStampsSl() - trnStampRegisterDtlVO.getStamps());
				//mstStpStkDAO.update(mstStpStkVO);
			}
/**
 * 			01	From IGRS
 *			04	Received from treasury by sub-treasury
 *			In above cases we will increase quantity of Double Lock Register  
 */			
			else if("01".equals(typeCode) || "04".equals(typeCode))
			{
				mstStpStkVO = mstStpStkDAO.read(new BigDecimal(stkId));
				
				mstStpStkVO.setSheetsDl(mstStpStkVO.getSheetsDl() + trnStampRegisterDtlVO.getSheets());
				mstStpStkVO.setStampsDl(mstStpStkVO.getStampsDl() + trnStampRegisterDtlVO.getStamps());
				//mstStpStkDAO.update(mstStpStkVO);
			}
/**
 * 			02	From Treasury (DL) to Treasury (SL)
 * 			In Single case we will reduce quantity of double lock register and 
 * 			increase the quantity of Single lock register 
 */			
			else if("02".equals(typeCode))
			{
				mstStpStkVO = mstStpStkDAO.read(new BigDecimal(stkId));
				
				mstStpStkVO.setSheetsDl(mstStpStkVO.getSheetsDl() - trnStampRegisterDtlVO.getSheets());
				mstStpStkVO.setStampsDl(mstStpStkVO.getStampsDl() - trnStampRegisterDtlVO.getStamps());
				mstStpStkVO.setSheetsSl(mstStpStkVO.getSheetsSl() + trnStampRegisterDtlVO.getSheets());
				mstStpStkVO.setStampsSl(mstStpStkVO.getStampsSl() + trnStampRegisterDtlVO.getStamps());
				//mstStpStkDAO.update(mstStpStkVO);
			}
		}
	}
	
	public ResultObject getIndentConsolidateData(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		StampCommonDAOImpl commonDAO=new StampCommonDAOImpl(RltDnmLocation.class,serv.getSessionFactory());
		MstStampGroupDAOImpl stampGroupDAO=new MstStampGroupDAOImpl(MstStampGroup.class,serv.getSessionFactory());
		IndentDetailsDAOImpl indentDetailsDAO=new IndentDetailsDAOImpl(TrnStampIndentDtls.class,serv.getSessionFactory());
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		String strLocationCode=SessionHelper.getLocationCode(objectArgs);
		List subTreasuryList=stampGroupDAO.getSubTreasuryNameListWithTreasury(strLocationCode,indentDetailsDAO,commonDAO);
		List[] indentDataAndSubTreasuryList=indentDetailsDAO.getTreasuryAndSubTreasuryIndentData(subTreasuryList, strLocationCode, commonDAO);
		List treasuryAndSubTreasuryIndentData=indentDataAndSubTreasuryList[0];
		subTreasuryList=indentDataAndSubTreasuryList[1];
		List indentNo=indentDataAndSubTreasuryList[2];
		objectArgs.put("subTreasuryList", subTreasuryList);
		objectArgs.put("treasuryAndSubTreasuryIndentData", treasuryAndSubTreasuryIndentData);
		objectArgs.put("indentNo", indentNo);
		result.setResultValue(objectArgs);
		result.setViewName("IndentConsolidationPreparation");
		return result;
	}
	public ResultObject insertIndentConsolidateData(Map objectArgs)
	{
		ResultObject result=new ResultObject(ErrorConstants.SUCCESS,"FAIL");
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		HttpServletRequest request=(HttpServletRequest)objectArgs.get("requestObj");
		IndentDetailsDAOImpl indentDetailsDAO=new IndentDetailsDAOImpl(TrnStampIndentDtls.class,serv.getSessionFactory());
		StampCommonDAOImpl commonDAO=new StampCommonDAOImpl(RltDnmLocation.class,serv.getSessionFactory());
		String[] subTreasuryIndentNo=request.getParameterValues("chkSubTreasuryCode");
		HttpSession session=request.getSession();
		String strTreasuryIndentNo=session.getAttribute("MainTreasuryIndentno").toString();
		String strIndentList=indentDetailsDAO.getIndentNoWhererList(subTreasuryIndentNo,strTreasuryIndentNo);
		List[] groupCodeAndGroupname=indentDetailsDAO.getGroupnameAndGroupCodeForConsolidationIndent(subTreasuryIndentNo, strTreasuryIndentNo, objectArgs, commonDAO);
		List groupCode=groupCodeAndGroupname[0];
		List groupName=groupCodeAndGroupname[1];
		List denominationData=indentDetailsDAO.getDenominationDetailsForConsolidation(groupCode, strIndentList);
		objectArgs.put("groupName", groupName);
		objectArgs.put("denominationTable", denominationData);
		String[] fromDateandToDate=indentDetailsDAO.getFromDateAndToDate(strTreasuryIndentNo, commonDAO);
		session.setAttribute("fromDate",fromDateandToDate[0].toString());
		session.setAttribute("toDate",fromDateandToDate[1].toString());
		session.setAttribute("groupName", groupName);
		session.setAttribute("denominationTable", denominationData);
		result.setResultValue(objectArgs);
		result.setViewName("indentDetails");
		return result;
		
	}
	
	
}