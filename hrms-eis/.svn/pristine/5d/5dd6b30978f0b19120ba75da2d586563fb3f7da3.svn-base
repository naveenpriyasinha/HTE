package com.tcs.sgv.eis.util;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.TrnBillRegister;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.eis.dao.commonDetailsDaoImpl;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadMpg;

public class commonDetailsServiceImplHelper 

{

	Log logger = LogFactory.getLog(getClass());
	ServiceLocator serv;
	
	public commonDetailsServiceImplHelper(ServiceLocator serv) 
	{
		super();
		this.serv = serv;
	}
	
	//Added by Abhilash
	/**
	 * @Author	: Abhilash
	 * @Date	: 17/02/2011 
	 * Function	: This method will return employee information.
	 * @param    int Month,String Year,long locId,long langId,String billNo
	 * @return   List
	 */
 	public List getDataOfAllEmployees(int Month,String Year,long locId,long langId,String billNo)
 	{
 		commonDetailsDaoImpl commondetailsDAO = new commonDetailsDaoImpl(TrnBillRegister.class,serv.getSessionFactory());
 		return commondetailsDAO.getDataforDisplay(Month,Year, locId,langId, billNo);
 	}
 	
 	/**
	 * @Author	: Abhilash
	 * @Date	: 17/02/2011 
	 * Function	: This method will return budget Head Structure.
	 * @param    long locId,String billNo,String Year
	 * @return   List
	 */
 	
 	public List getBudgerHeadStructure(long locId,String billNo,String Year)
 	{
 		commonDetailsDaoImpl commondetailsDAO = new commonDetailsDaoImpl(TrnBillRegister.class,serv.getSessionFactory());
 		return commondetailsDAO.getxtraDataforDisplay( locId , billNo, Year);	
 	}
 	
 	/**
	 * @Author	: Abhilash
	 * @Date	: 17/02/2011 
	 * Function	: This method will return payBillGroupID From ServiceLocator object.
	 * @param    ServiceLocator serv,int Month,String Year,String billNo,long locId
	 * @return   List
	 */
 	
 	public List getPayBillGroupId(ServiceLocator serv,int Month,String Year,String billNo,long locId)
 	{
 		commonDetailsDaoImpl commondetailsDAO = new commonDetailsDaoImpl(TrnBillRegister.class,serv.getSessionFactory());
 		return commondetailsDAO.getPaybillGrpId(Month, Year, billNo, locId);
 	}
 	
 	/**
	 * @Author	: Abhilash
	 * @Date	: 17/02/2011 
	 * Function	: This method will return payBillGrpId from List object.
	 * @param    List billData,String payBillGrpId
	 * @return   String
	 */
 	
 	
 	public String getPayBillGrpId(List billData,String payBillGrpId)
 	{
 		for (int i = 0; i < billData.size(); i++) 
		{
			Object rowList[] = (Object[]) billData.get(i);
			payBillGrpId = (rowList[0] != null && !(rowList[0].equals(""))? rowList[0].toString():"");
			logger.info("commonDetailsServiceImplHelper payBillGrpIdpayBillGrpIdpayBillGrpId" + payBillGrpId);
		
			//billNo.add(rowList[1] != null && !(rowList[1].equals(""))? rowList[1].toString():"");
		}	
 		return payBillGrpId;
 	}
 	
 	/**
	 * @Author	: Abhilash
	 * @Date	: 17/02/2011 
	 * Function	: This method will return budgetData from List object.
	 * @param    List bugdetData,List xtraDataList
	 * @return   List
	 */
 	
 	
 	public List getBudgetData(List bugdetData,List xtraDataList)
 	{
 		for (int i = 0; i < xtraDataList.size(); i++) 
		{
			Object rowList[] = (Object[]) xtraDataList.get(i);
			StringWriter strbudWriter = new StringWriter();			
			strbudWriter.append(((rowList[1] != null && !(rowList[1].toString().trim()).equals(""))? rowList[1].toString():"")+"~");
			strbudWriter.append(((rowList[2] != null && !(rowList[2].toString().trim()).equals(""))? rowList[2].toString():"")+"~");
			strbudWriter.append(((rowList[3] != null && !(rowList[3].toString().trim()).equals(""))? rowList[3].toString():"")+"~");
			strbudWriter.append(((rowList[4] != null && !(rowList[4].toString().trim()).equals(""))? rowList[4].toString():"")+"~");
			strbudWriter.append(((rowList[5] != null && !(rowList[5].toString().trim()).equals(""))? rowList[5].toString():"")+"~");
			strbudWriter.append(((rowList[6] != null && !(rowList[6].toString().trim()).equals(""))? "00":"")+"~");
			strbudWriter.append(((rowList[7] != null && !(rowList[7].toString().trim()).equals(""))? rowList[7].toString():"")+"~");
			bugdetData.add(strbudWriter.toString());
			// bugdetBillData.add((rowList[8] != null && !(rowList[8].toString().trim()).equals(""))? rowList[8].toString():"");
		}
 		return bugdetData;
 	}
 	
 	/**
	 * @Author	: Abhilash
	 * @Date	: 17/02/2011 
	 * Function	: This method will return netAmount from List object.
	 * @param    List DataList,int rowsize,List listedData,int netAmount
	 * @return   int
	 */
 	
 	
 	public int getNetAmoutOfBill(List DataList,int rowsize,List listedData,int netAmount)
 	{
 		for (int i = 0; i < DataList.size(); i++) 
		{
			Object rowList[] = (Object[]) DataList.get(i);
			rowsize = (rowList.length) - 1;
			for(int j = 0 ; j < rowsize ; j++ )
			{
				listedData.add(((rowList[j] != null && !(rowList[j].toString().trim()).equals(""))? rowList[j].toString():"")+"~");
				if(j == (rowsize-1))
					listedData.add("#");
			}
			if(rowList[rowsize] != null && !(rowList[rowsize].toString().trim()).equals(""))
			netAmount = netAmount + Integer.parseInt(rowList[rowsize].toString());
			logger.info("commonDetailsServiceImplHelper netAmount" + netAmount);
			logger.info("commonDetailsServiceImplHelper listedData" + listedData.size());
		}
 		return netAmount;
 	}
 	
 	/**
	 * @Author	: Abhilash
	 * @Date	: 17/02/2011 
	 * Function	: This method will return BillList from ServiceLocator object.
	 * @param    long locId
	 * @return   List
	 */
 	
 	public List getBillList(long locId)
 	{
 		commonDetailsDaoImpl commondetailsDAO = new commonDetailsDaoImpl(TrnBillRegister.class,serv.getSessionFactory());
 		List billList = new ArrayList(); 
		List<HrPayBillHeadMpg> BillList = new ArrayList();	
		billList = commondetailsDAO.getBillListForDisplay(locId);
		for(Iterator itr=billList.iterator();itr.hasNext();)
		{    			
			Object[] row = (Object[])itr.next();
			HrPayBillHeadMpg hrPayBillHeadMpg = new HrPayBillHeadMpg();					
			hrPayBillHeadMpg.setBillId(Long.parseLong(row[0].toString()));
			hrPayBillHeadMpg.setBillHeadId(Long.parseLong(row[1].toString()));		 	
			BillList.add(hrPayBillHeadMpg);
			
		}   
		return BillList;
 	}
 	
 	/**
	 * @Author	: Abhilash
	 * @Date	: 17/02/2011 
	 * Function	: This method will return budget month list from ServiceLocator object.
	 * @param    ServiceLocator serv,long langId
	 * @return   List
	 */
 	
 	public List getMonthList(long langId)
 	{
 		CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
 		return lookupDAO.getAllChildrenByLookUpNameAndLang("Month", langId);
 	}
 	
 	/**
	 * @Author	: Abhilash
	 * @Date	: 17/02/2011 
	 * Function	: This method will return year List ServiceLocator object.
	 * @param    ServiceLocator serv,long langId
	 * @return   List
	 */
 	
 	public 	List getYearList(long langId)
 	{
 		CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
 		return lookupDAO.getAllChildrenByLookUpNameAndLang("Year", langId);
 	}
 	//Ended by Abhilash
}
