package com.tcs.sgv.dss.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.BptmCommonServiceImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.dss.dao.DSSRptExpEdpDtlsDAOImpl;
import com.tcs.sgv.dss.dao.DSSRptPaymentDtlsDAOImpl;
import com.tcs.sgv.dss.dao.DSSRptReceiptDtlsDAOImpl;
import com.tcs.sgv.dss.dao.DssRptExpenditureDtlsDAOImpl;
import com.tcs.sgv.dss.valueobject.RptExpEdpDtls;
import com.tcs.sgv.dss.valueobject.RptExpenditureDtls;
import com.tcs.sgv.dss.valueobject.RptPaymentDtls;
import com.tcs.sgv.dss.valueobject.RptReceiptDtls;

public class DssDataServiceImpl  extends ServiceImpl implements DssDataService
{
	Log logger = LogFactory.getLog(getClass());

	HashMap hashReturn = new HashMap();
	HashMap hashReturnExp = new HashMap();
	HashMap hashReturnExpEdp = new HashMap();
	HashMap hashReturnReceipt = new HashMap();
	ArrayList 	lArrayLstRptExpEdpDtlsVO = new ArrayList();
	ArrayList 	lArrayLstRptExpEdpDtlsVO_DAO = new ArrayList();
	ArrayList 	lArrayLstReceiptDtlsVO = new ArrayList();
	ArrayList 	lArrayLstReceiptDtlsVO_DAO = new ArrayList();


	Log glogger = LogFactory.getLog(getClass());
	double gGrossAmount = 0;
	double gRecoveryAmount = 0;
	double gDeductionA = 0;
	double gDeductionB = 0;
	double gDisbursementAmnt = 0;

	public HashMap insertExpData(Map inputMap)
	{
		double lGrossAmount = 0;
		double lRecoveryAmount = 0;
		double lDeductionA = 0;
		double lDeductionB = 0;
		double lDeductionG = 0;
		double lDisbursementAmnt = 0;

		Map amountMap = null;
		Map map = null;

		
		
		boolean isDeductionA = false;
		boolean lBlnExpEdp = false;

		BigDecimal lbgdcGrossAmount = null;
		BigDecimal lbgdcRecoveryAmount = null;
		BigDecimal lbgdcDeductionA = null;
		BigDecimal lbgdcDeductionB = null;
		BigDecimal lbgdcDisbursementAmnt = null;
		BigDecimal lbgdcNetAmnt = null;
		BigDecimal lbgdcExpAmnt = null;
		
		HttpServletRequest request = null;
		long lLongUserId = 0;
		Date d = new Date();
		
		try
		{
			if(inputMap != null)
			{

				logger.info("-----------------------insertExpData function of DssDataServiceImpl starts--------------------");


				
				map = (HashMap)inputMap.get("map");
				
				request = (HttpServletRequest)map.get("requestObj");
				lLongUserId=SessionHelper.getUserId(request);
				

				BigDecimal lbgdcExpId = new BigDecimal(BptmCommonServiceImpl.getNextSeqNum("rpt_expenditure_dtls", map));



				if(map.containsKey("serviceLocator")&& inputMap.containsKey("RptExpenditureVO"))
				{
					ServiceLocator serv = (ServiceLocator)map.get("serviceLocator");
					RptExpenditureDtls lObjRptExpenditureDtlsVO = (RptExpenditureDtls)inputMap.get("RptExpenditureVO");
					DssRptExpenditureDtlsDAOImpl lObjExpenditureDtlsDAO = new DssRptExpenditureDtlsDAOImpl(RptExpenditureDtls.class, serv.getSessionFactory());

					if((inputMap.containsKey("RptExpEdpVOArrLst") || inputMap.containsKey("RptReceiptVOArrLst") ))
					{
						if((inputMap.containsKey("RptExpEdpVOArrLst")))
						{

							lBlnExpEdp = true;

							DSSRptExpEdpDtlsDAOImpl lObjExpEdpDtlsDAO = new DSSRptExpEdpDtlsDAOImpl(RptExpEdpDtls.class, serv.getSessionFactory());
							lArrayLstRptExpEdpDtlsVO = (ArrayList)inputMap.get("RptExpEdpVOArrLst");

							logger.info("The sizeof ExpEdpArray is ------------------------> "+ lArrayLstRptExpEdpDtlsVO.size());

							for(int i =0; i < lArrayLstRptExpEdpDtlsVO.size();i++ )
							{
								RptExpEdpDtls lObjRptExpEdpDtls = (RptExpEdpDtls)lArrayLstRptExpEdpDtlsVO.get(i);
								BigDecimal lbgdcExpEdpId = new BigDecimal(BptmCommonServiceImpl.getNextSeqNum("rpt_exp_edp_dtls", map));

								lObjRptExpEdpDtls.setExpId(lbgdcExpId);
								lObjRptExpEdpDtls.setActive('Y');
								lObjRptExpEdpDtls.setClientData('N');
								lObjRptExpEdpDtls.setCrtUsr(String.valueOf(lLongUserId));
								lObjRptExpEdpDtls.setCrtDt(d);
								lObjRptExpEdpDtls.setExpEdpId(lbgdcExpEdpId);

								if(lObjRptExpEdpDtls.getEdpType()== '0')
									lGrossAmount = lGrossAmount + lObjRptExpEdpDtls.getAmount().doubleValue();
								else if(lObjRptExpEdpDtls.getEdpType()== '1')
									lRecoveryAmount = lRecoveryAmount + lObjRptExpEdpDtls.getAmount().doubleValue();


								lArrayLstRptExpEdpDtlsVO_DAO.add(lObjRptExpEdpDtls);
							}

							hashReturnExpEdp =	lObjExpEdpDtlsDAO.insertVO(lArrayLstRptExpEdpDtlsVO_DAO);
						}

						if(inputMap.containsKey("RptReceiptVOArrLst") )
						{
							DSSRptReceiptDtlsDAOImpl lObjReceiptDtlsDAO = new DSSRptReceiptDtlsDAOImpl(RptReceiptDtls.class, serv.getSessionFactory());
							lArrayLstReceiptDtlsVO = (ArrayList)inputMap.get("RptReceiptVOArrLst");

							logger.info("The sizeof ReceiptArray is ------------------------> "+ lArrayLstReceiptDtlsVO.size());

							for(int i =0; i < lArrayLstReceiptDtlsVO.size();i++ )
							{
								RptReceiptDtls lObjRptReceiptDtlsVO = (RptReceiptDtls)lArrayLstReceiptDtlsVO.get(i);
								BigDecimal lbgdcRcptId = new BigDecimal(BptmCommonServiceImpl.getNextSeqNum("rpt_receipt_dtls", map));

								lObjRptReceiptDtlsVO.setRcptId(lbgdcRcptId);
								lObjRptReceiptDtlsVO.setExpId(lbgdcExpId);
								lObjRptReceiptDtlsVO.setActive('Y');
								lObjRptReceiptDtlsVO.setClientData('N');
								lObjRptReceiptDtlsVO.setCrtUsr(String.valueOf(lLongUserId));
								lObjRptReceiptDtlsVO.setCrtDt(d);

								if(lObjRptReceiptDtlsVO.getDedctnType()!=null && lObjRptReceiptDtlsVO.getDedctnType() == 'A')
								{
									lDeductionA = lDeductionA + lObjRptReceiptDtlsVO.getAmount().doubleValue();
									isDeductionA = true;
								}
								else if(lObjRptReceiptDtlsVO.getDedctnType()!=null && lObjRptReceiptDtlsVO.getDedctnType() == 'B')
									lDeductionB = lDeductionB + lObjRptReceiptDtlsVO.getAmount().doubleValue();
								else
									lDeductionG = lDeductionG + lObjRptReceiptDtlsVO.getAmount().doubleValue();



								lDisbursementAmnt = lDisbursementAmnt + lObjRptReceiptDtlsVO.getDisbursementAmt().doubleValue();

								lArrayLstReceiptDtlsVO_DAO.add(lObjRptReceiptDtlsVO);
							}
							hashReturnReceipt = lObjReceiptDtlsDAO.insertVO(lArrayLstReceiptDtlsVO_DAO);
						}


						if(!(lBlnExpEdp))
						{
							lGrossAmount = Double.parseDouble(lObjRptExpenditureDtlsVO.getGrossAmnt().toString());
						}

						lbgdcDeductionA = new BigDecimal(lDeductionA);
						lbgdcDeductionB = new BigDecimal(lDeductionB);
						lbgdcGrossAmount = new BigDecimal(lGrossAmount);
						lbgdcRecoveryAmount = new BigDecimal(lRecoveryAmount);
						lbgdcNetAmnt = new BigDecimal((lGrossAmount-(lRecoveryAmount+lDeductionA+lDeductionB+lDisbursementAmnt+lDeductionG)));

						if(isDeductionA)
							lbgdcExpAmnt = new BigDecimal((lbgdcNetAmnt.doubleValue() + lDeductionA) );
						else
							lbgdcExpAmnt = new BigDecimal(lGrossAmount - lRecoveryAmount);


						lObjRptExpenditureDtlsVO.setDedctnaAmt(lbgdcDeductionA);
						lObjRptExpenditureDtlsVO.setDedctnbAmt(lbgdcDeductionB);
						if(lBlnExpEdp)
						{
							lObjRptExpenditureDtlsVO.setGrossAmnt(lbgdcGrossAmount);
						}

						lObjRptExpenditureDtlsVO.setRecoveryAmt(lbgdcRecoveryAmount);
						lObjRptExpenditureDtlsVO.setNetAmt(lbgdcNetAmnt);
						lObjRptExpenditureDtlsVO.setExpAmt(lbgdcExpAmnt);
						lObjRptExpenditureDtlsVO.setDeduction(new BigDecimal(lDeductionG));
						lObjRptExpenditureDtlsVO.setExpId(lbgdcExpId);
						lObjRptExpenditureDtlsVO.setActive('Y');
						lObjRptExpenditureDtlsVO.setClientData('N');
						lObjRptExpenditureDtlsVO.setCrtUsr(String.valueOf(lLongUserId));
						lObjRptExpenditureDtlsVO.setCrtDt(d);

						hashReturnExp = lObjExpenditureDtlsDAO.insertVO(lObjRptExpenditureDtlsVO);

						hashReturn.put("flag", "true");
						hashReturn.put("Status", "Successful Transaction");
						hashReturn.put("voName", "RptExpenditureDtls");


					}
					else 
					{

						lObjRptExpenditureDtlsVO.setActive('Y');
						lObjRptExpenditureDtlsVO.setClientData('N');
						lObjRptExpenditureDtlsVO.setCrtUsr(String.valueOf(lLongUserId));
						lObjRptExpenditureDtlsVO.setCrtDt(d);
						lObjRptExpenditureDtlsVO.setExpId(lbgdcExpId);


						hashReturn = lObjExpenditureDtlsDAO.insertVO(lObjRptExpenditureDtlsVO);
					}

				}
				else
				{
					hashReturn.put("flag", "false");
					hashReturn.put("Status", "Data is not Sufficient in Map");
					hashReturn.put("voName", "RptExpenditureDtls");

				}


			}
			else
			{
				hashReturn.put("flag", "false");
				hashReturn.put("Status", "Map is Null");
				hashReturn.put("voName", "RptExpenditureDtls");
			}
			
			logger.info("-----------------------insertExpData function of DssDataServiceImpl Ends--------------------");
			
			return hashReturn;
		}
		catch(Exception e)
		{
			logger.error("This is Error: -", e);
			hashReturn.put("flag", "false");
			hashReturn.put("Status", e);
			hashReturn.put("voName", " ");
			return hashReturn;
		}
	}
	public HashMap insertExpEdpData(Map inputMap)
	{
		HashMap lRptAmounts = null;
		HashMap map = null;
		BigDecimal lbgdcExpId = null;

		double lGrossAmount = 0;
		double lRecoveryAmount = 0;
		double lDeductionA = 0;
		double lDeductionB = 0;
		double lDeductionG = 0;
		double lDisbursementAmnt = 0;

		RptExpEdpDtls lObjExpEdpDtls = null;

		BigDecimal lbgdcGrossAmount = null;
		BigDecimal lbgdcRecoveryAmount = null;
		BigDecimal lbgdcDeductionA = null;
		BigDecimal lbgdcDeductionB = null;
		BigDecimal lbgdcDisbursementAmnt = null;
		BigDecimal lbgdcNetAmnt = null;
		BigDecimal lbgdcExpAmnt = null;

		String columns[]=new String[3] ;
		Object values[] =new Object[3];
		Object list_iterator[] = {};
		List lst = null;
		
		HttpServletRequest request = null;
		long lLongUserId = 0;
		Date d = new Date();

		ArrayList lArrayLstRptExpEdpDtlsVO_DAO = new ArrayList();
		try
		{
			if(inputMap != null)
			{
				logger.info("-----------------------insertExpData function of DssDataServiceImpl starts--------------------");

				map = (HashMap)inputMap.get("map");
				
				request = (HttpServletRequest)map.get("requestObj");
				lLongUserId=SessionHelper.getUserId(request);



				if(map.containsKey("serviceLocator")&& inputMap.containsKey("RptExpEdpVOArrLst"))
				{
					ServiceLocator serv = (ServiceLocator)map.get("serviceLocator");

					DssRptExpenditureDtlsDAOImpl lObjExpenditureDtlsDAO = new DssRptExpenditureDtlsDAOImpl(RptExpenditureDtls.class, serv.getSessionFactory());
					DSSRptExpEdpDtlsDAOImpl lObjExpEdpDtlsDAO = new DSSRptExpEdpDtlsDAOImpl(RptExpEdpDtls.class, serv.getSessionFactory());

					if(inputMap.containsKey("expNo") && inputMap.containsKey("expTypeCode"))
					{
						BigDecimal lbgdcExpNo = new BigDecimal(inputMap.get("expNo").toString());
						String lExpTypeCode = (String)inputMap.get("expTypeCode");



						//-------------------------------------------------------------------------------------
						// getting Amounts of Expenditure Corresponding to ExpNo and ExpTypeCode
						//-------------------------------------------------------------------------------------

						columns[0] = "expNo";
						columns[1] = "expTypeCode";
						columns[2]= "active";

						values[0] = lbgdcExpNo;
						values[1] = lExpTypeCode;
						values[2]= 'Y';

						lst = lObjExpenditureDtlsDAO.getListByColumnAndValue(columns, values);
						if(!lst.isEmpty())
						{
							RptExpenditureDtls lObjRptExpenditureDtls = (RptExpenditureDtls)lst.get(0);
							lbgdcExpId = lObjRptExpenditureDtls.getExpId();
							lDeductionA = lObjRptExpenditureDtls.getDedctnaAmt().doubleValue();
							lDeductionB = lObjRptExpenditureDtls.getDedctnbAmt().doubleValue();
							lDeductionG = lObjRptExpenditureDtls.getDeduction().doubleValue();
						}
						//--------------------------------------------------------------------------------------
						//	getting Gross & Recovery Amounts from Edp Table corresponding to ExpId
						//----------------------------------------------------------------------------------------

						String columns1[]=new String[2] ;
						Object values1[] =new Object[2];

						columns1[0]= "expId";
						columns1[1]= "active";

						values1[0]= lbgdcExpId;
						values1[1]= 'Y';

						lst = lObjExpEdpDtlsDAO.getListByColumnAndValue(columns1, values1);

						if(lst.size()>0)
						{
							for(int y = 0 ; y < lst.size();y++)
							{
								logger.info("This is the Gross & Recovery Amount Calculation loop  Deleting Edp " + y);
								lObjExpEdpDtls = (RptExpEdpDtls)lst.get(y);

								if(lObjExpEdpDtls.getEdpType()== '0')
								{
									lGrossAmount = lGrossAmount + lObjExpEdpDtls.getAmount().doubleValue();
								}
								else if(lObjExpEdpDtls.getEdpType()== '1')
								{
									lRecoveryAmount = lRecoveryAmount + lObjExpEdpDtls.getAmount().doubleValue();
								}	


							}
						}


						//-----------------------------------------------------------------------------------------
						//	Adding the Edp VOs and accumulating the Gross and recovery mounts
						//------------------------------------------------------------------------------------------	


						ArrayList lArrayLstRptExpEdpDtlsVO = (ArrayList)inputMap.get("RptExpEdpVOArrLst");

						for(int i =0; i < lArrayLstRptExpEdpDtlsVO.size();i++ )
						{

							BigDecimal lbgdcExpEdpId = new BigDecimal(BptmCommonServiceImpl.getNextSeqNum("rpt_exp_edp_dtls", map));
							RptExpEdpDtls lObjRptExpEdpDtlsVO = (RptExpEdpDtls)lArrayLstRptExpEdpDtlsVO.get(i);

							lObjRptExpEdpDtlsVO.setExpId(lbgdcExpId);
							lObjRptExpEdpDtlsVO.setExpEdpId(lbgdcExpEdpId);
							lObjRptExpEdpDtlsVO.setActive('Y');
							lObjRptExpEdpDtlsVO.setClientData('N');
							lObjRptExpEdpDtlsVO.setCrtUsr(String.valueOf(lLongUserId));
							lObjRptExpEdpDtlsVO.setCrtDt(d);

							if(lObjRptExpEdpDtlsVO.getEdpType() == '0')
								lGrossAmount = lGrossAmount + lObjRptExpEdpDtlsVO.getAmount().doubleValue();
							else if(lObjRptExpEdpDtlsVO.getEdpType() == '1')
								lRecoveryAmount = lRecoveryAmount + lObjRptExpEdpDtlsVO.getAmount().doubleValue();

							logger.info("Hi this is the value of Gross Amount in DssDataservicesImpl./java :-> " + lGrossAmount);


							lArrayLstRptExpEdpDtlsVO_DAO.add(lObjRptExpEdpDtlsVO);
						}

						lObjExpEdpDtlsDAO.insertVO(lArrayLstRptExpEdpDtlsVO_DAO);


						//-----------------------------------------------------------------------------------------
						//	calculating the amounts for Expenditure
						//-----------------------------------------------------------------------------------------
						lbgdcDeductionA = new BigDecimal(lDeductionA);
						lbgdcDeductionB = new BigDecimal(lDeductionB);
						lbgdcGrossAmount = new BigDecimal(lGrossAmount);
						lbgdcRecoveryAmount = new BigDecimal(lRecoveryAmount);


						lbgdcNetAmnt = new BigDecimal((lGrossAmount-(lRecoveryAmount+lDeductionA+lDeductionB+lDisbursementAmnt+lDeductionG)));

						int totalRowsDdctnA = lObjExpenditureDtlsDAO.getTotalRowsDdctnA(lbgdcExpId);

						if(totalRowsDdctnA > 0)
							lbgdcExpAmnt = lbgdcNetAmnt.add(lbgdcDeductionA);
						else
							lbgdcExpAmnt = new BigDecimal(lGrossAmount-lRecoveryAmount);

						//-----------------------------------------------------------------------------------------

						//-----------------------------------------------------------------------------------------
						//	setting amounts into Expenditure VO and then updating the Expenditure VO
						//------------------------------------------------------------------------------------------

						RptExpenditureDtls temp = lObjExpenditureDtlsDAO.read(lbgdcExpId);

						if(temp!=null)
						{
							temp.setGrossAmnt(lbgdcGrossAmount);
							temp.setDedctnaAmt(lbgdcDeductionA);
							temp.setDedctnbAmt(lbgdcDeductionB);						
							temp.setDeduction(new BigDecimal(lDeductionG));
							temp.setNetAmt(lbgdcNetAmnt);
							temp.setExpAmt(lbgdcExpAmnt);
							temp.setRecoveryAmt(lbgdcRecoveryAmount);

							lObjExpenditureDtlsDAO.updateVo(temp);


							hashReturn.put("flag", "True");
							hashReturn.put("Status", "Successful Transaction");
							hashReturn.put("voName", "RptExpEdpDtls");
						}
					}
					else
					{
						hashReturn.put("flag", "false");
						hashReturn.put("Status", "Insufficient Data");
						hashReturn.put("voName", "RptExpEdpDtls");
					}

				}
				else
				{
					hashReturn.put("flag", "false");
					hashReturn.put("Status", "Incorrect Keys");
					hashReturn.put("voName", "RptExpEdpDtls");
				}
			}
			else
			{
				hashReturn.put("flag", "false");
				hashReturn.put("Status", "Map is Null");
				hashReturn.put("voName", "RptExpEdpDtls");
			}

			logger.info("-----------------------insertExpData function of DssDataServiceImpl Ends--------------------");
			
			return hashReturn;
		}
		catch(Exception e)
		{
			logger.error("This is Error", e);
			hashReturn.put("flag", "false");
			hashReturn.put("Status", e);
			hashReturn.put("voName", " ");
			return hashReturn;
		}
	}




	public HashMap insertReceiptData(Map inputMap)
	{
		HashMap map = null;
		String columns[]=new String[3] ;
		Object values[] =new Object[3];
		Object list_iterator[] = {};
		List lst = null;
		HttpServletRequest request = null;
		long lLongUserId = 0;
		Date d = new Date();

		try
		{
			if(inputMap != null)
			{
				map = (HashMap)inputMap.get("map");
				request = (HttpServletRequest)map.get("requestObj");
				lLongUserId=SessionHelper.getUserId(request);


				logger.info("-------------------------hi i am in inesrtReceiptData---------------------------");

				if(map.containsKey("serviceLocator")&& inputMap.containsKey("RptReceiptVOArrLst"))
				{
					ServiceLocator serv = (ServiceLocator)map.get("serviceLocator");

					DSSRptReceiptDtlsDAOImpl lObjReceiptDtlsDAO = new DSSRptReceiptDtlsDAOImpl(RptReceiptDtls.class, serv.getSessionFactory());
					DssRptExpenditureDtlsDAOImpl lObjExpenditureDtlsDAO = new DssRptExpenditureDtlsDAOImpl(RptExpenditureDtls.class, serv.getSessionFactory());

					lArrayLstReceiptDtlsVO = (ArrayList)inputMap.get("RptReceiptVOArrLst");

					if(inputMap.containsKey("expNo") && (inputMap.containsKey("expTypeCode")))
					{
						HashMap lRptAmounts = null;

						double lGrossAmount = 0;
						double lRecoveryAmount = 0;
						double lDeductionA = 0;
						double lDeductionB = 0;
						double lDeductionG = 0;
						double lDisbursementAmnt = 0;

						boolean isDeductionA = false;

						BigDecimal lbgdcExpId = null;
						BigDecimal lbgdcGrossAmount = null;
						BigDecimal lbgdcRecoveryAmount = null;
						BigDecimal lbgdcDeductionA = null;
						BigDecimal lbgdcDeductionB = null;
						BigDecimal lbgdcDisbursementAmnt = null;
						BigDecimal lbgdcNetAmnt = null;
						BigDecimal lbgdcExpAmnt = null;

						BigDecimal lbgdcExpNo = new BigDecimal(inputMap.get("expNo").toString());
						String lExpTypeCode = (String)inputMap.get("expTypeCode");

						//Anshu - Change the method call to hibernate
						/*lRptAmounts = lObjExpenditureDtlsDAO.getAmount(lbgdcExpNo, lExpTypeCode);

						BigDecimal lbgdcExpId = new BigDecimal(lRptAmounts.get("expId").toString());
						lGrossAmount = Double.parseDouble(lRptAmounts.get("grossAmount").toString());
						lRecoveryAmount = Double.parseDouble(lRptAmounts.get("recoveryAmount").toString());
						lDeductionA = Double.parseDouble(lRptAmounts.get("dedctnaAmount").toString());
						lDeductionB = Double.parseDouble(lRptAmounts.get("dedctnbAmount").toString());*/




						//-------------------------------------------------------------------------------------
						// getting Amounts of Expenditure Corresponding to ExpNo and ExpTypeCode
						//-------------------------------------------------------------------------------------

						columns[0] = "expNo";
						columns[1] = "expTypeCode";
						columns[2]= "active";

						values[0] = lbgdcExpNo;
						values[1] = lExpTypeCode;
						values[2]= 'Y';

						lst = lObjExpenditureDtlsDAO.getListByColumnAndValue(columns, values);
						if(!lst.isEmpty())
						{
							RptExpenditureDtls lObjRptExpenditureDtls = (RptExpenditureDtls)lst.get(0);
							lbgdcExpId = lObjRptExpenditureDtls.getExpId();
							lDeductionA = lObjRptExpenditureDtls.getDedctnaAmt().doubleValue();
							lDeductionB = lObjRptExpenditureDtls.getDedctnbAmt().doubleValue();
							lDeductionG = lObjRptExpenditureDtls.getDeduction().doubleValue();
							lGrossAmount = lObjRptExpenditureDtls.getGrossAmnt().doubleValue();
							lRecoveryAmount = lObjRptExpenditureDtls.getRecoveryAmt().doubleValue();

						}

						//--------------------------------------------------------------------------------------


						ArrayList lArrayLstReceiptDtlsVO_DAO = new ArrayList();

						for(int i =0; i < lArrayLstReceiptDtlsVO.size();i++ )
						{

							logger.info("hi iam in for loop of insertReceipt services-------------------------");
							RptReceiptDtls lObjRptReceiptDtlsVO = (RptReceiptDtls)lArrayLstReceiptDtlsVO.get(i);
							BigDecimal lbgdcRcptId = new BigDecimal(BptmCommonServiceImpl.getNextSeqNum("rpt_receipt_dtls", map));
							lObjRptReceiptDtlsVO.setRcptId(lbgdcRcptId);
							lObjRptReceiptDtlsVO.setExpId(lbgdcExpId);
							lObjRptReceiptDtlsVO.setActive('Y');
							lObjRptReceiptDtlsVO.setClientData('N');
							lObjRptReceiptDtlsVO.setCrtUsr(String.valueOf(lLongUserId));
							lObjRptReceiptDtlsVO.setCrtDt(d);

							if(lObjRptReceiptDtlsVO.getDedctnType()!=null && lObjRptReceiptDtlsVO.getDedctnType() == 'A')
							{
								lDeductionA = lDeductionA + lObjRptReceiptDtlsVO.getAmount().doubleValue();
								isDeductionA = true;
							}
							else if(lObjRptReceiptDtlsVO.getDedctnType()!=null && lObjRptReceiptDtlsVO.getDedctnType() == 'B')
								lDeductionB = lDeductionB + lObjRptReceiptDtlsVO.getAmount().doubleValue();
							else
								lDeductionG = lDeductionG + lObjRptReceiptDtlsVO.getAmount().doubleValue();

							lDisbursementAmnt = lDisbursementAmnt + lObjRptReceiptDtlsVO.getDisbursementAmt().doubleValue();

							lArrayLstReceiptDtlsVO_DAO.add(lObjRptReceiptDtlsVO);

						}


						hashReturn = lObjReceiptDtlsDAO.insertVO(lArrayLstReceiptDtlsVO_DAO);

						//-----------------------------------------------------------------------------------
						//	calculating the Expenditure Amounts
						//-----------------------------------------------------------------------------------


						if(!(isDeductionA))
						{
							int totalRowsDdctnA = lObjExpenditureDtlsDAO.getTotalRowsDdctnA(lbgdcExpId);
							if(totalRowsDdctnA > 0)
								isDeductionA  =true;
						}

						lbgdcDeductionA = new BigDecimal(lDeductionA);
						lbgdcDeductionB = new BigDecimal(lDeductionB);
						lbgdcGrossAmount = new BigDecimal(lGrossAmount);
						lbgdcRecoveryAmount = new BigDecimal(lRecoveryAmount);

						lbgdcNetAmnt = new BigDecimal((lGrossAmount-(lRecoveryAmount+lDeductionA+lDeductionB+lDisbursementAmnt + lDeductionG)));

						if(isDeductionA)
							lbgdcExpAmnt = new BigDecimal((lbgdcNetAmnt.doubleValue() + lDeductionA) );
						else
							lbgdcExpAmnt = lbgdcGrossAmount.subtract(lbgdcRecoveryAmount);

						//----------------------------------------------------------------------------------------



						//-----------------------------------------------------------------------------------------
						//	setting the Expenditure VO s and updating it.
						//------------------------------------------------------------------------------------------
						if(!(lbgdcExpId == null))
						{
							RptExpenditureDtls lObjRptExpenditureDtls = lObjExpenditureDtlsDAO.read(lbgdcExpId);
							lObjRptExpenditureDtls.setGrossAmnt(lbgdcGrossAmount);
							lObjRptExpenditureDtls.setDedctnaAmt(lbgdcDeductionA);
							lObjRptExpenditureDtls.setDedctnbAmt(lbgdcDeductionB);
							lObjRptExpenditureDtls.setDeduction(new BigDecimal(lDeductionG));
							lObjRptExpenditureDtls.setNetAmt(lbgdcNetAmnt);
							lObjRptExpenditureDtls.setExpAmt(lbgdcExpAmnt);
							lObjRptExpenditureDtls.setRecoveryAmt(lbgdcRecoveryAmount);
							lObjExpenditureDtlsDAO.updateVo(lObjRptExpenditureDtls);
						}
						//------------------------------------------------------------------------------------------



						hashReturn.put("flag", "True");
						hashReturn.put("Status", "Successful Transaction");
						hashReturn.put("voName", "RptReceiptDtls");



					}
					else 
					{
						for(int i =0; i < lArrayLstReceiptDtlsVO.size();i++ )
						{
							RptReceiptDtls lObjRptReceiptDtlsVO = (RptReceiptDtls)lArrayLstReceiptDtlsVO.get(i);
							BigDecimal lbgdcExpId = new BigDecimal(BptmCommonServiceImpl.getNextSeqNum("rpt_receipt_dtls", map));
							lObjRptReceiptDtlsVO.setRcptId(lbgdcExpId);
							lObjRptReceiptDtlsVO.setActive('Y');
							lObjRptReceiptDtlsVO.setClientData('N');
							lObjRptReceiptDtlsVO.setCrtUsr(String.valueOf(lLongUserId));
							lObjRptReceiptDtlsVO.setCrtDt(d);

							lArrayLstReceiptDtlsVO_DAO.add(lObjRptReceiptDtlsVO);
						}

						hashReturn = lObjReceiptDtlsDAO.insertVO(lArrayLstReceiptDtlsVO_DAO);
					}

				}
				else
				{
					hashReturn.put("flag", "false");
					hashReturn.put("Status", "Value in Map is Not Sufficient");
					hashReturn.put("voName", "RptReceiptDtls");
				}
			}
			else
			{
				hashReturn.put("flag", "false");
				hashReturn.put("Status", "Map is Null");
				hashReturn.put("voName", "RptReceiptDtls");
			}
			
			logger.info("------------------------- inesrtReceiptData of DssDataServicesImpl Ends---------------------------");
			
			return hashReturn ;
		}
		catch(Exception e)
		{
			logger.error("This is Error", e);
			hashReturn.put("flag", "false");
			hashReturn.put("Status", e);
			hashReturn.put("voName", " ");
			return hashReturn;
		}
	}

	public HashMap insertPaymentData(Map inputMap)
	{
		HashMap map = null;
		HttpServletRequest request = null;
		long lLongUserId = 0;
		Date d = new Date();

		try
		{
			//Anshu - keep containsKey check
			map = (HashMap)inputMap.get("map");
			
			request = (HttpServletRequest)map.get("requestObj");
			lLongUserId=SessionHelper.getUserId(request);


			logger.info("----------------------------insertPaymentData function of DssDataServiceImpl starts-------------------");

			ServiceLocator serv = (ServiceLocator)map.get("serviceLocator");
			RptPaymentDtls lObjRptPaymentDtlsVO = (RptPaymentDtls)inputMap.get("RptPaymentVO");
			DSSRptPaymentDtlsDAOImpl lObjPaymentDtlsDAO = new DSSRptPaymentDtlsDAOImpl(RptPaymentDtls.class, serv.getSessionFactory());

			BptmCommonServiceImpl serviceImplBudHd=new BptmCommonServiceImpl();

			BigDecimal lbgdcPaymentId = new BigDecimal(BptmCommonServiceImpl.getNextSeqNum("rpt_payment_dtls", map));

			lObjRptPaymentDtlsVO.setPaymentId(lbgdcPaymentId);
			lObjRptPaymentDtlsVO.setActive('Y');
			lObjRptPaymentDtlsVO.setClientData('N');
			lObjRptPaymentDtlsVO.setCrtUsr(String.valueOf(lLongUserId));
			lObjRptPaymentDtlsVO.setCrtDt(d);

			hashReturn = lObjPaymentDtlsDAO.insertVO(lObjRptPaymentDtlsVO);

			logger.info("----------------------------insertPaymentData function of DssDataServiceImpl ends-------------------");

			return hashReturn;

		}
		catch(Exception e)
		{
			logger.error("This is Error", e);
			hashReturn.put("flag", "false");
			hashReturn.put("Status", e);
			hashReturn.put("voName", " ");
			return hashReturn;
		}


	}

	public HashMap deleteExpData(Map inputMap)
	{
		hashReturn = new HashMap();
		BigDecimal lbgdcExpId = null;
		String columns[]=new String[2] ;
		Object values[] =new Object[2];
		Object list_iterator[] = {};
		List lst = null;
		Iterator it = null;

		try
		{
			//Anshu - containsKey chk
			HashMap map = (HashMap)inputMap.get("map");
			logger.info("-----------------------deleteExpData function of DssDataServiceImpl starts-------------------");

			ServiceLocator serv = (ServiceLocator)map.get("serviceLocator");
			DssRptExpenditureDtlsDAOImpl lObjExpenditureDtlsDAO = new DssRptExpenditureDtlsDAOImpl(RptExpenditureDtls.class, serv.getSessionFactory());
			DSSRptExpEdpDtlsDAOImpl lObjExpEdpDtlsDAOImpl = new DSSRptExpEdpDtlsDAOImpl(RptExpEdpDtls.class,serv.getSessionFactory());
			DSSRptReceiptDtlsDAOImpl lObjRptReceiptDtlsDAOImpl = new DSSRptReceiptDtlsDAOImpl(RptReceiptDtls.class,serv.getSessionFactory());

			if(! (inputMap == null) )
			{			
				BigDecimal bgdcExpNo = new  BigDecimal(inputMap.get("expNo").toString());
				String lStrExpTypeCode = inputMap.get("expTypeCode").toString();
				logger.info("hi i am going into the delete exp data function of Expenditure DAO----------");



				//lbgdcExpId = lObjExpenditureDtlsDAO.getExpId(bgdcExpNo, lStrExpTypeCode);
				columns[0]="expNo";
				columns[1]="expTypeCode";

				values[0]=bgdcExpNo;
				values[1] =lStrExpTypeCode; 

				//------------------------------------------------------------------------------------
				//getting & Deleting the Expenditure VO according to the given Exp_No and Exp_Type_code
				//-------------------------------------------------------------------------------------

				lst = lObjExpenditureDtlsDAO.getListByColumnAndValue(columns, values);

				if(!lst.isEmpty())
				{
					RptExpenditureDtls lObjRptExpenditureDtls = (RptExpenditureDtls)lst.get(0) ;
					lObjRptExpenditureDtls.setActive('N');
					lObjExpenditureDtlsDAO.updateVo(lObjRptExpenditureDtls);

					lObjRptExpenditureDtls.toString();

					logger.info("the Expenditure VO is successfully Updated : +++++++++++++++++++++++++++++++++++++++");
				}
				//--------------------------------------------------------------------------------
				// getting & deleting the Edp Vos corresponding to that ExpId	
				//--------------------------------------------------------------------------------

				lst = lObjExpEdpDtlsDAOImpl.getListByColumnAndValue("expId", lbgdcExpId);

				if(lst.size()>0)
				{
					for(int y = 0 ; y < lst.size();y++)
					{
						logger.info("This is the deleteign loop of ExpEdpDtls VO in Deleting Expenditure " + y);
						RptExpEdpDtls lObjRptExpEdpDtls = (RptExpEdpDtls)lst.get(y);
						lObjRptExpEdpDtls.setActive('N');
						lObjExpEdpDtlsDAOImpl.update(lObjRptExpEdpDtls);
					}
				}

				//---------------------------------------------------------------------------
				// getting & deleting the Receipt VOs corresponding to that ExpId
				//----------------------------------------------------------------------------

				lst = lObjRptReceiptDtlsDAOImpl.getListByColumnAndValue("expId", lbgdcExpId);

				if(lst.size()>0)
				{
					for(int y = 0 ; y < lst.size();y++)
					{
						logger.info("This is the deleteign loop of ReceiptDtls VO in Deleting Expenditure " + y);

						RptReceiptDtls lObjRptReceiptDtls = (RptReceiptDtls)lst.get(y);
						lObjRptReceiptDtls.setActive('N');
						lObjRptReceiptDtlsDAOImpl.update(lObjRptReceiptDtls);
					}
				}

				hashReturn.put("Status", "Successful Transaction");
				hashReturn.put("flag","True");
				hashReturn.put("voName", "RptExpenditureDtls"); 


			}
			else
			{
				hashReturn.put("Status", "Map Is null");
				hashReturn.put("flag","false");
				hashReturn.put("voName", "RptExpenditureDtls"); 
			}

			logger.info("-----------------------deleteExpData function of DssDataServiceImpl ends-------------------");
		}
		catch(Exception e)
		{
			hashReturn.put("Status", e.toString());
			hashReturn.put("flag","false");
			hashReturn.put("voName", "RptExpenditureDtls"); 
			logger.error("This is Error", e);
		}
		return hashReturn;

	}

	public HashMap deletePaymentData(Map inputMap)
	{
		hashReturn = new HashMap();
		RptPaymentDtls lObjRptPaymentDtls = null;
		String[] columns= new String[2];
		Object[] values=new Object[2];

		List lst = null;

		try
		{
			//Anshu - containsKey chk
			HashMap map = (HashMap)inputMap.get("map");

			logger.info("-----------------------deletePaymentData function of DssDataServiceImpl starts-------------------");

			ServiceLocator serv = (ServiceLocator)map.get("serviceLocator");
			DSSRptPaymentDtlsDAOImpl lObjPaymentDtlsDAO = new DSSRptPaymentDtlsDAOImpl(RptPaymentDtls.class, serv.getSessionFactory());

			if(! (inputMap == null) )
			{			
				BigDecimal bgdcChqNo = new BigDecimal(inputMap.get("chqNo").toString());
				String lStrChqTypeCode = inputMap.get("chqTypeCode").toString();
				columns[0] = "chqNo";
				columns[1] ="chqTypeCode";

				values[0] = bgdcChqNo;
				values[1] = lStrChqTypeCode;

				lst = lObjPaymentDtlsDAO.getListByColumnAndValue(columns, values);

				if(!lst.isEmpty())
				{
					lObjRptPaymentDtls = (RptPaymentDtls)lst.get(0);
					lObjRptPaymentDtls.setActive('N');
					lObjPaymentDtlsDAO.update(lObjRptPaymentDtls);

					hashReturn.put("Status", "Deleted Successfully");
					hashReturn.put("flag","true");
					hashReturn.put("voName", "RptPaymentDtls"); 
				}

			}
			else
			{
				hashReturn.put("Status", "Map Is null");
				hashReturn.put("flag","false");
				hashReturn.put("voName", "RptPaymentDtls"); 
			}
			logger.info("-----------------------deletePaymentData function of DssDataServiceImpl ends-------------------");
		}
		catch(Exception e)
		{
			hashReturn.put("Status", e.toString());
			hashReturn.put("flag","false");
			hashReturn.put("voName", "RptPaymentDtls"); 
			logger.error("This is Error", e);
		}

		return hashReturn;
	}

	public HashMap deleteReceiptData(Map inputMap)
	{
		hashReturn = new HashMap();

		String columns[]=new String[4] ;
		Object values[] =new Object[4];
		RptExpEdpDtls lObjExpEdpDtls = null;
		RptReceiptDtls lObjRptReceiptDtls = null;
		double lDoubleGrossAmount = 0.00;
		double lDoubleRecoveryAmount = 0.00;
		double lDoubleDeductionA = 0.00;
		double lDoubleDeductionB = 0.00;
		double lDoubleDeductionG = 0.00;
		double lDoubleDisbursement = 0.00;
		boolean IsDeductionA = false;
		double lDoubleNetAmount = 0.00;
		double lDoubleExpAmount = 0.00;

		BigDecimal lbgdcExpId = null;

		RptReceiptDtls lObjReceiptDtls = null;
		RptExpenditureDtls lObjRptExpenditureDtls = null;



		try
		{
			//Anshu - contains chk
			HashMap map = (HashMap)inputMap.get("map");

			logger.info("-----------------------deleteReceiptData function of DssDataServiceImpl starts-------------------");

			ServiceLocator serv = (ServiceLocator)map.get("serviceLocator");
			DSSRptReceiptDtlsDAOImpl lObjReceiptDtlsDAO = new DSSRptReceiptDtlsDAOImpl(RptReceiptDtls.class, serv.getSessionFactory());
			DssRptExpenditureDtlsDAOImpl lObjExpenditureDtlsDAOImpl = new DssRptExpenditureDtlsDAOImpl(RptExpenditureDtls.class,serv.getSessionFactory());

			if(! (inputMap == null) )
			{			

				BigDecimal bgdcRcptNo = new BigDecimal(inputMap.get("rcptNo").toString());
				String lStrRcptTypeCode = inputMap.get("rcptTypeCode").toString();
				String lStrChallanCatgCode = inputMap.get("challanCatgCode").toString();
				BigDecimal trnReceiptId = new BigDecimal(inputMap.get("trnReceiptId").toString());

				columns[0] = "rcptNo";
				columns[1] = "rcptTypeCode";
				columns[2] = "challanCatgCode";
				columns[3] = "trnReceiptId";

				values[0] = bgdcRcptNo;
				values[1] = lStrRcptTypeCode;
				values[2] = lStrChallanCatgCode;
				values[3] = trnReceiptId;

				//-----------------------------------------------------------------------------------------
				// Getting & Deleting the ReceiptVO
				//-----------------------------------------------------------------------------------------

				List lst = lObjReceiptDtlsDAO.getListByColumnAndValue(columns, values);

				if(!lst.isEmpty())
				{

					lObjReceiptDtls = (RptReceiptDtls)lst.get(0);
					lObjReceiptDtls.setActive('N');
					lObjReceiptDtlsDAO.update(lObjReceiptDtls);

					lbgdcExpId = lObjReceiptDtls.getExpId();


					if(lbgdcExpId.equals(0))
					{
						for(int q = 0 ; q < lst.size() ; q++)
						{
							lObjReceiptDtls = (RptReceiptDtls)lst.get(q);
							lObjReceiptDtls.setActive('N');
							lObjReceiptDtlsDAO.update(lObjReceiptDtls);
						}
					}
					else
					{

						//----------------------------------------------------------------------------------------------
						// Getting the ExpenditureVO , that is to be updated
						//-----------------------------------------------------------------------------------------------

						lObjReceiptDtls = (RptReceiptDtls)lst.get(0);
						lObjReceiptDtls.setActive('N');
						lObjReceiptDtlsDAO.update(lObjReceiptDtls);

						lbgdcExpId = lObjReceiptDtls.getExpId();

						if(! (lbgdcExpId.equals(0)))
						{
							lObjRptExpenditureDtls =  lObjExpenditureDtlsDAOImpl.read(lbgdcExpId);
							lDoubleGrossAmount = lObjRptExpenditureDtls.getGrossAmnt().doubleValue();
							lDoubleRecoveryAmount = lObjRptExpenditureDtls.getRecoveryAmt().doubleValue();


							//--------------------------------------------------------------------------------------------
							// Get the Deductions and Disbursments
							//--------------------------------------------------------------------------------------------

							String columns1[]=new String[2] ;
							Object values1[] =new Object[2];

							columns1[0]= "expId";
							columns1[1]= "active";

							values1[0]= lbgdcExpId;
							values1[1]= 'Y';

							lst = lObjReceiptDtlsDAO.getListByColumnAndValue(columns1, values1);

							if(lst.size()>0)
							{
								for(int y = 0 ; y < lst.size();y++)
								{
									logger.info("This is the Gross & Recovery Amount Calculation loop  Deleting Edp " + y);
									lObjRptReceiptDtls = (RptReceiptDtls)lst.get(y);

									if(lObjRptReceiptDtls.getDedctnType()!=null && lObjRptReceiptDtls.getDedctnType()=='A')
									{
										lDoubleDeductionA = lDoubleDeductionA + lObjRptReceiptDtls.getAmount().doubleValue();
										IsDeductionA = true;
									}	
									else if(lObjRptReceiptDtls.getDedctnType()!=null && lObjRptReceiptDtls.getDedctnType()=='B')
									{
										lDoubleDeductionB = lDoubleDeductionB + lObjRptReceiptDtls.getAmount().doubleValue();
									}
									else
									{
										lDoubleDeductionG = lDoubleDeductionG + lObjRptReceiptDtls.getAmount().doubleValue();
									}

									lDoubleDisbursement = lDoubleDisbursement + lObjRptReceiptDtls.getDisbursementAmt().doubleValue();

								}
							}




							//-------------------------------------------------------------------------------------------------
							// Calculatin the Expenditure VO ' Amount
							//------------------------------------------------------------------------------------------------- 

							lDoubleNetAmount = lDoubleGrossAmount - (lDoubleDeductionA + lDoubleDeductionB + lDoubleDeductionG + lDoubleDisbursement + lDoubleRecoveryAmount);

							if(IsDeductionA)
								lDoubleExpAmount = lDoubleNetAmount + lDoubleDeductionA;
							else
								lDoubleExpAmount = lDoubleGrossAmount - lDoubleRecoveryAmount;


							lObjRptExpenditureDtls.setGrossAmnt(new BigDecimal(lDoubleGrossAmount));
							lObjRptExpenditureDtls.setDedctnaAmt(new BigDecimal(lDoubleDeductionA));
							lObjRptExpenditureDtls.setDedctnbAmt(new BigDecimal(lDoubleDeductionB));						
							lObjRptExpenditureDtls.setDeduction(new BigDecimal(lDoubleDeductionG));
							lObjRptExpenditureDtls.setNetAmt(new BigDecimal(lDoubleNetAmount));
							lObjRptExpenditureDtls.setExpAmt(new BigDecimal(lDoubleExpAmount));
							lObjRptExpenditureDtls.setRecoveryAmt(new BigDecimal(lDoubleRecoveryAmount));

							lObjExpenditureDtlsDAOImpl.updateVo(lObjRptExpenditureDtls);

							hashReturn.put("Status", "SuccessFul Transaction");
							hashReturn.put("flag","True");
							hashReturn.put("voName", "RptReceiptDtls"); 
						}
					}
				}
				//-----------------------------------------------------------------------------------------------

			}
			else
			{
				hashReturn.put("Status", "Map Is null");
				hashReturn.put("flag","false");
				hashReturn.put("voName", "RptReceiptDtls"); 
			}

			logger.info("-----------------------deleteReceiptData function of DssDataServiceImpl ends-------------------");
		}
		catch(Exception e)
		{
			hashReturn.put("Status", e.toString());
			hashReturn.put("flag","false");
			hashReturn.put("voName", "RptReceiptDtls"); 
			logger.error("This is Error", e);
		}

		return hashReturn;
	}


	public HashMap deleteExpEdpData(Map inputMap)
	{

		hashReturn = new HashMap();
		String[] columns=new String[2];
		Object[] values=new Object[2];
		RptExpEdpDtls lObjExpEdpDtls = null;
		RptReceiptDtls lObjRptReceiptDtls = null;
		double lDoubleGrossAmount = 0.00;
		double lDoubleRecoveryAmount = 0.00;
		double lDoubleDeductionA = 0.00;
		double lDoubleDeductionB = 0.00;
		double lDoubleDeductionG = 0.00;
		double lDoubleDisbursement = 0.00;
		boolean IsDeductionA = false;
		double lDoubleNetAmount = 0.00;
		double lDoubleExpAmount = 0.00;

		List lst = null;
		BigDecimal bgdcexpId =  null;

		try
		{
			//Anshu - chk
			HashMap map = (HashMap)inputMap.get("map");

			logger.info("-----------------------deleteExpEdpData function of DssDataServiceImpl starts-------------------");
			ServiceLocator serv = (ServiceLocator)map.get("serviceLocator");

			DSSRptExpEdpDtlsDAOImpl lObjExpEdpDtlsDAO = new DSSRptExpEdpDtlsDAOImpl(RptExpEdpDtls.class, serv.getSessionFactory());
			DssRptExpenditureDtlsDAOImpl lObjExpenditureDtlsDAO = new DssRptExpenditureDtlsDAOImpl(RptExpenditureDtls.class, serv.getSessionFactory());
			DSSRptReceiptDtlsDAOImpl lObjReceiptDtlsDAOImpl = new DSSRptReceiptDtlsDAOImpl(RptReceiptDtls.class,serv.getSessionFactory());

			if(! (inputMap == null) )
			{			
				BigDecimal bgdcExpNo = new BigDecimal(inputMap.get("expNo").toString());
				String lStrExpTypeCode = inputMap.get("expTypeCode").toString();
				BigDecimal trnExpEdpId = new BigDecimal(inputMap.get("trnExpEdpId").toString());

				//--------------------------------------------------------------------------------------------
				//	getting the Expenditure VO , to get ExpId
				//--------------------------------------------------------------------------------------------

				columns[0]="expNo";
				columns[1]="expTypeCode";

				values[0]=bgdcExpNo;
				values[1] =lStrExpTypeCode; 

				lst = lObjExpenditureDtlsDAO.getListByColumnAndValue(columns, values);

				if(!lst.isEmpty())
				{
					RptExpenditureDtls lObjRptExpenditureDtls = (RptExpenditureDtls)lst.get(0) ;
					bgdcexpId = lObjRptExpenditureDtls.getExpId();



					//--------------------------------------------------------------------------------------
					//getting & deleting the Edp VO
					//--------------------------------------------------------------------------------------


					columns[0]= "expId";
					columns[1]= "trnExpEdpId";

					values[0]= bgdcexpId;
					values[1]= trnExpEdpId;

					lst = lObjExpEdpDtlsDAO.getListByColumnAndValue(columns, values);

					if(!lst.isEmpty())
					{
						RptExpEdpDtls lObjRptExpEdpDtls = (RptExpEdpDtls)lst.get(0);
						lObjRptExpEdpDtls.setActive('N');
						lObjExpEdpDtlsDAO.update(lObjRptExpEdpDtls);
					}
					//-------------------------------------------------------------------------------
					// Get total Gross & Recovery Amount of corresponding ExpId
					//-------------------------------------------------------------------------------

					columns[0]= "expId";
					columns[1]= "active";

					values[0]= bgdcexpId;
					values[1]= 'Y';

					lst = lObjExpEdpDtlsDAO.getListByColumnAndValue(columns, values);

					if(lst.size()>0)
					{
						for(int y = 0 ; y < lst.size();y++)
						{
							logger.info("This is the Gross & Recovery Amount Calculation loop  Deleting Edp " + y);
							lObjExpEdpDtls = (RptExpEdpDtls)lst.get(y);

							if(lObjExpEdpDtls.getEdpType()== '0')
							{
								lDoubleGrossAmount = lDoubleGrossAmount + lObjExpEdpDtls.getAmount().doubleValue();
							}
							else if(lObjExpEdpDtls.getEdpType()== '1')
							{
								lDoubleRecoveryAmount = lDoubleRecoveryAmount + lObjExpEdpDtls.getAmount().doubleValue();
							}	


						}
					}

					//--------------------------------------------------------------------------------------------
					// Get deduction A , B , General & disbursment
					//--------------------------------------------------------------------------------------------
					columns[0]= "expId";
					columns[1]= "active";
					//Anshu - can add deducition_type ='A' as condition instead of the below code

					values[0]= bgdcexpId;
					values[1]= 'Y';

					lst = lObjReceiptDtlsDAOImpl.getListByColumnAndValue(columns, values);

					if(lst.size()>0)
					{
						for(int y = 0 ; y < lst.size();y++)
						{
							logger.info("This is the Gross & Recovery Amount Calculation loop  Deleting Edp " + y);
							lObjRptReceiptDtls = (RptReceiptDtls)lst.get(y);

							if(lObjRptReceiptDtls.getDedctnType()!=null && lObjRptReceiptDtls.getDedctnType()=='A')
							{
								lDoubleDeductionA = lDoubleDeductionA + lObjRptReceiptDtls.getAmount().doubleValue();
								IsDeductionA = true;
							}
							else if(lObjRptReceiptDtls.getDedctnType()!=null && lObjRptReceiptDtls.getDedctnType()=='B')
							{
								lDoubleDeductionB = lDoubleDeductionB + lObjRptReceiptDtls.getAmount().doubleValue();
							}
							else
							{
								lDoubleDeductionG = lDoubleDeductionG + lObjRptReceiptDtls.getAmount().doubleValue();
							}

							lDoubleDisbursement = lDoubleDisbursement + lObjRptReceiptDtls.getDisbursementAmt().doubleValue();

						}
					}
				}
				//-------------------------------------------------------------------------------------------
				// updating the Expenditure VO
				//------------------------------------------------------------------------------------------

				lDoubleNetAmount = lDoubleGrossAmount - (lDoubleDeductionA + lDoubleDeductionB + lDoubleDeductionG + lDoubleDisbursement + lDoubleRecoveryAmount);

				if(IsDeductionA)
					lDoubleExpAmount = lDoubleNetAmount + lDoubleDeductionA;
				else
					lDoubleExpAmount = lDoubleGrossAmount - lDoubleRecoveryAmount;

				RptExpenditureDtls lObjRptExpenditureDtls = lObjExpenditureDtlsDAO.read(bgdcexpId);
				lObjRptExpenditureDtls.setGrossAmnt(new BigDecimal(lDoubleGrossAmount));
				lObjRptExpenditureDtls.setDedctnaAmt(new BigDecimal(lDoubleDeductionA));
				lObjRptExpenditureDtls.setDedctnbAmt(new BigDecimal(lDoubleDeductionB));						
				lObjRptExpenditureDtls.setNetAmt(new BigDecimal(lDoubleNetAmount));
				lObjRptExpenditureDtls.setExpAmt(new BigDecimal(lDoubleExpAmount));
				lObjRptExpenditureDtls.setRecoveryAmt(new BigDecimal(lDoubleRecoveryAmount));
				lObjRptExpenditureDtls.setDeduction(new BigDecimal(lDoubleDeductionG));

				lObjExpenditureDtlsDAO.updateVo(lObjRptExpenditureDtls);

				hashReturn.put("Status", "successful Transaction");
				hashReturn.put("flag","true");
				hashReturn.put("voName", "RptExpEdpDtls"); 


			}
			else
			{
				hashReturn.put("Status", "Map Is null");
				hashReturn.put("flag","false");
				hashReturn.put("voName", "RptExpEdpDtls"); 
			}

			logger.info("-----------------------deleteExpEdpData function of DssDataServiceImpl ends-------------------");
		}
		catch(Exception e)
		{
			hashReturn.put("Status", e.toString());
			hashReturn.put("flag","false");
			hashReturn.put("voName", "RptExpEdpDtls"); 
			logger.error("This is Error", e);
		}


		return hashReturn;
	}

	public RptExpenditureDtls getExpData(Map inputMap)
	{
		RptExpenditureDtls lObjRptExpenditureDtls = null;
		String columns[]=new String[2] ;
		Object values[] =new Object[2];
		List lst = null;

		try
		{
			//Anshu - chk
			HashMap map = (HashMap)inputMap.get("map");

			logger.info("-----------------------getExpData function of DssDataServices starts-------------------------");
			if(inputMap.containsKey("expNo") && (inputMap.containsKey("expTypeCode")) && (map.containsKey("serviceLocator")))
			{
				BigDecimal bgdcExpNo = new BigDecimal(inputMap.get("expNo").toString());
				String lStrExpTypeCode = inputMap.get("expTypeCode").toString();
				ServiceLocator serv = (ServiceLocator)map.get("serviceLocator");
				DssRptExpenditureDtlsDAOImpl lObjExpenditureDtlsDAO = new DssRptExpenditureDtlsDAOImpl(RptExpenditureDtls.class, serv.getSessionFactory());

				columns[0] = "expNo";
				columns[1] = "expTypeCode";

				values[0] = bgdcExpNo;
				values[1] = lStrExpTypeCode;

				lst = lObjExpenditureDtlsDAO.getListByColumnAndValue(columns, values);
				if(!lst.isEmpty())
				{
					lObjRptExpenditureDtls = (RptExpenditureDtls)lst.get(0);
				}

			}

			logger.info("-----------------------getExpData function of DssDataServices ends-------------------------");
			return lObjRptExpenditureDtls;
		}
		catch(Exception e)
		{
			logger.error("This is Error", e);
			return lObjRptExpenditureDtls;
		}
	}


	public RptExpEdpDtls getExpEdpData(Map inputMap)
	{
		RptExpEdpDtls lObjRptExpEdpDtls = null;
		try
		{
			logger.info("-----------------------getExpEdpData function of DssDataServices starts-------------------------");
			HashMap map = (HashMap)inputMap.get("map");

			if(inputMap.containsKey("expNo") && (inputMap.containsKey("expTypeCode")) && (map.containsKey("serviceLocator")) && (inputMap.containsKey("trnExpEdpId")))
			{
				BigDecimal bgdcExpNo = new BigDecimal((inputMap.get("expNo").toString()));
				String lStrExpTypeCode = inputMap.get("expTypeCode").toString();
				BigDecimal ltrnReceiptId =  new BigDecimal((inputMap.get("trnExpEdpId").toString()));
				ServiceLocator serv = (ServiceLocator)map.get("serviceLocator");

				DSSRptExpEdpDtlsDAOImpl lObjExpEdpDtlsDAODAO = new DSSRptExpEdpDtlsDAOImpl(RptExpEdpDtls.class, serv.getSessionFactory());

				//Anshu - Change to hibernate call
				lObjRptExpEdpDtls = lObjExpEdpDtlsDAODAO.getData(bgdcExpNo, lStrExpTypeCode, ltrnReceiptId);
			}

			logger.info("-----------------------getExpEdpData function of DssDataServices ends-------------------------");

			return lObjRptExpEdpDtls;
		}
		catch(Exception e)
		{
			logger.error("This is Error", e);
			return lObjRptExpEdpDtls;
		}
	}
	public RptReceiptDtls getReceiptData(Map inputMap)
	{
		RptReceiptDtls lObjRptReceiptDtls = null;
		String columns[]=new String[4] ;
		Object values[] =new Object[4];
		List lst = null;

		try
		{
			HashMap map = (HashMap)inputMap.get("map");

			logger.info("-----------------------getReceiptData function of DssDataServices starts-------------------------");

			if(inputMap.containsKey("rcptNo") && (inputMap.containsKey("rcptTypeCode")) && (map.containsKey("serviceLocator")) && (inputMap.containsKey("challanCatgCode")) && (inputMap.containsKey("trnReceiptId")))
			{
				BigDecimal bgdcRcptNo = new BigDecimal((inputMap.get("rcptNo").toString()));
				String lStrRcptTypeCode = inputMap.get("rcptTypeCode").toString();
				String lStrChallanCatgCode = inputMap.get("challanCatgCode").toString();
				BigDecimal bgdcTrnReceiptId = new BigDecimal((inputMap.get("trnReceiptId").toString()));
				ServiceLocator serv = (ServiceLocator)map.get("serviceLocator");
				DSSRptReceiptDtlsDAOImpl lObjReceiptDtlsDAO = new DSSRptReceiptDtlsDAOImpl(RptReceiptDtls.class, serv.getSessionFactory());

				columns[0] = "rcptNo";
				columns[1] = "rcptTypeCode";
				columns[2] = "challanCatgCode";
				columns[3] = "trnReceiptId";

				values[0] = bgdcRcptNo;
				values[1] = lStrRcptTypeCode;
				values[2] = lStrChallanCatgCode;
				values[3] = bgdcTrnReceiptId;

				//-----------------------------------------------------------------------------------------
				// Getting & Deleting the ReceiptVO
				//-----------------------------------------------------------------------------------------

				lst = lObjReceiptDtlsDAO.getListByColumnAndValue(columns, values);
				if(!lst.isEmpty())
					lObjRptReceiptDtls = (RptReceiptDtls)lst.get(0);
			}

			logger.info("-----------------------getReceiptData function of DssDataServices ends-------------------------");

			return lObjRptReceiptDtls;
		}
		catch(Exception e)
		{
			logger.error("This is Error", e);
			return lObjRptReceiptDtls;
		}
	}

	public RptPaymentDtls getPaymentData( Map inputMap)
	{
		RptPaymentDtls lObjRptPaymentDtls = null;
		String columns[]=new String[2] ;
		Object values[] =new Object[2];
		List lst = null;


		try
		{

			logger.info("-----------------------getPaymentData function of DssDataServices starts-------------------------");
			HashMap map = (HashMap)inputMap.get("map");

			if(inputMap.containsKey("chqNo") && (inputMap.containsKey("chqTypeCode")) && (map.containsKey("serviceLocator")))
			{
				BigDecimal bgdcChqNo = new BigDecimal((inputMap.get("chqNo").toString()));
				String lStrChqTypeCode = inputMap.get("chqTypeCode").toString();
				ServiceLocator serv = (ServiceLocator)map.get("serviceLocator");
				DSSRptPaymentDtlsDAOImpl lObjPaymentDtlsDAO = new DSSRptPaymentDtlsDAOImpl(RptPaymentDtls.class, serv.getSessionFactory());

				columns[0] = "chqNo";
				columns[1] ="chqTypeCode";

				values[0] = bgdcChqNo;
				values[1] = lStrChqTypeCode;

				lst = lObjPaymentDtlsDAO.getListByColumnAndValue(columns, values);
				if(!lst.isEmpty())
					lObjRptPaymentDtls = (RptPaymentDtls)lst.get(0);

			}
			logger.info("-----------------------getPaymentData function of DssDataServices ends-------------------------");
			return lObjRptPaymentDtls;
		}
		catch(Exception e)
		{
			logger.error("This is Error", e);
			return lObjRptPaymentDtls;
		}
	}

	public HashMap updateExpData(Map inputMap)
	{
		hashReturn = new HashMap();
		try
		{
			logger.info("-----------------------updateExpData function of DssDataServices starts-------------------------");

			HashMap map = (HashMap)inputMap.get("map");

			if(! (inputMap == null) )
			{
				ServiceLocator serv = (ServiceLocator)map.get("serviceLocator");
				DssRptExpenditureDtlsDAOImpl lObjExpenditureDtlsDAO = new DssRptExpenditureDtlsDAOImpl(RptExpenditureDtls.class, serv.getSessionFactory());
				RptExpenditureDtls lObjRptExpenditureDtlsVO = (RptExpenditureDtls)inputMap.get("RptExpenditureVO");

				lObjRptExpenditureDtlsVO.toString();

				lObjExpenditureDtlsDAO.update(lObjRptExpenditureDtlsVO);
			}
			else
			{
				hashReturn.put("Status", "Map Is null");
				hashReturn.put("flag","false");
				hashReturn.put("voName", "RptExpenditureDtls"); 
			}

			logger.info("-----------------------updateExpData function of DssDataServices ends-------------------------");
		}
		catch(Exception e)
		{
			hashReturn.put("Status", e.toString());
			hashReturn.put("flag","false");
			hashReturn.put("voName", "RptExpenditureDtls"); 
			logger.error("This is Error", e);

		}

		return hashReturn;
	}

	public HashMap updateExpEdpData(Map inputMap)
	{
		hashReturn = new HashMap();
		String[] columns= new String[2];
		Object[] values=new Object[2];

		RptExpEdpDtls lObjExpEdpDtls = null;
		RptReceiptDtls lObjRptReceiptDtls = null;
		double lDoubleGrossAmount = 0.00;
		double lDoubleRecoveryAmount = 0.00;
		double lDoubleDeductionA = 0.00;
		double lDoubleDeductionB = 0.00;
		double lDoubleDeductionG = 0.00;
		double lDoubleDisbursement = 0.00;
		boolean IsDeductionA = false;
		double lDoubleNetAmount = 0.00;
		double lDoubleExpAmount = 0.00;
		BigDecimal bgdcexpId = null;
		List lst = null;

		try
		{
			HashMap map = (HashMap)inputMap.get("map");

			logger.info("-----------------------updateExpEdpData function of DssDataServices starts-------------------------");

			if(! (inputMap == null) )
			{
				ServiceLocator serv = (ServiceLocator)map.get("serviceLocator");

				DSSRptExpEdpDtlsDAOImpl lObjExpEdpDtlsDAO = new DSSRptExpEdpDtlsDAOImpl(RptExpEdpDtls.class, serv.getSessionFactory());
				DssRptExpenditureDtlsDAOImpl lObjExpenditureDtlsDAO = new DssRptExpenditureDtlsDAOImpl(RptExpenditureDtls.class, serv.getSessionFactory());
				DSSRptReceiptDtlsDAOImpl lObjReceiptDtlsDAOImpl = new DSSRptReceiptDtlsDAOImpl(RptReceiptDtls.class,serv.getSessionFactory());

				RptExpEdpDtls lObjRptRptExpEdpDtlsVO = (RptExpEdpDtls)inputMap.get("RptExpEdpVO");
				bgdcexpId = lObjRptRptExpEdpDtlsVO.getExpId();

				//-----------------------------------------------------------------------------------
				// updating the Edp VO
				//-----------------------------------------------------------------------------------

				logger.info("updating the RptExpEdpVO in updateExpEdpData function-------------------------");

				lObjExpEdpDtlsDAO.update(lObjRptRptExpEdpDtlsVO);

				//------------------------------------------------------------------------------------
				// calculating the Gross Amount & Recovery Amounts
				//------------------------------------------------------------------------------------

				logger.info("calculating the Gross Amount & Recovery Amounts in updateExpEdpData function-----------------");

				columns[0]= "expId";
				columns[1]= "active";

				values[0]= bgdcexpId;
				values[1]= 'Y';

				lst = lObjExpEdpDtlsDAO.getListByColumnAndValue(columns, values);

				if(lst.size()>0)
				{
					for(int y = 0 ; y < lst.size();y++)
					{
						logger.info("This is the Gross & Recovery Amount Calculation loop  Deleting Edp " + y);
						lObjExpEdpDtls = (RptExpEdpDtls)lst.get(y);

						if(lObjExpEdpDtls.getEdpType()== '0')
						{
							lDoubleGrossAmount = lDoubleGrossAmount + lObjExpEdpDtls.getAmount().doubleValue();
						}
						else if(lObjExpEdpDtls.getEdpType()== '1')
						{
							lDoubleRecoveryAmount = lDoubleRecoveryAmount + lObjExpEdpDtls.getAmount().doubleValue();
						}	


					}
				}

				//------------------------------------------------------------------------------------
				//calculating the Deductions & Disbursment Amounts
				//------------------------------------------------------------------------------------

				logger.info("calculating the Deductions Amount & Disbursment Amounts in updateExpEdpData function-----------------");

				columns[0]= "expId";
				columns[1]= "active";

				//Anshu - deduction A can be chked by adding that in column  
				values[0]= bgdcexpId;
				values[1]= 'Y';

				lst = lObjReceiptDtlsDAOImpl.getListByColumnAndValue(columns, values);

				if(lst.size()>0)
				{
					for(int y = 0 ; y < lst.size();y++)
					{
						logger.info("This is the Gross & Recovery Amount Calculation loop  Deleting Edp " + y);
						lObjRptReceiptDtls = (RptReceiptDtls)lst.get(y);

						if(lObjRptReceiptDtls.getDedctnType()!=null && lObjRptReceiptDtls.getDedctnType()=='A')
						{
							lDoubleDeductionA = lDoubleDeductionA + lObjRptReceiptDtls.getAmount().doubleValue();
							IsDeductionA = true;
						}
						else if(lObjRptReceiptDtls.getDedctnType()!=null && lObjRptReceiptDtls.getDedctnType()=='B')
						{
							lDoubleDeductionB = lDoubleDeductionB + lObjRptReceiptDtls.getAmount().doubleValue();
						}
						else
						{
							lDoubleDeductionG = lDoubleDeductionG + lObjRptReceiptDtls.getAmount().doubleValue();
						}

						lDoubleDisbursement = lDoubleDisbursement + lObjRptReceiptDtls.getDisbursementAmt().doubleValue();

					}
				}

				//-------------------------------------------------------------------------------------------------
				//	calculating the Expenditure Amounts
				//-------------------------------------------------------------------------------------------------

				logger.info("calculating the Expenditure Amounts in updateExpEdp function-------------------------------");


				lDoubleNetAmount = lDoubleGrossAmount - (lDoubleDeductionA + lDoubleDeductionB + lDoubleDeductionG + lDoubleDisbursement + lDoubleRecoveryAmount);

				if(IsDeductionA)
					lDoubleExpAmount = lDoubleNetAmount + lDoubleDeductionA;
				else
					lDoubleExpAmount = lDoubleGrossAmount - lDoubleRecoveryAmount;

				RptExpenditureDtls lObjRptExpenditureDtls = lObjExpenditureDtlsDAO.read(bgdcexpId);

				lObjRptExpenditureDtls.setGrossAmnt(new BigDecimal(lDoubleGrossAmount));
				lObjRptExpenditureDtls.setDedctnaAmt(new BigDecimal(lDoubleDeductionA));
				lObjRptExpenditureDtls.setDedctnbAmt(new BigDecimal(lDoubleDeductionB));						
				lObjRptExpenditureDtls.setNetAmt(new BigDecimal(lDoubleNetAmount));
				lObjRptExpenditureDtls.setExpAmt(new BigDecimal(lDoubleExpAmount));
				lObjRptExpenditureDtls.setRecoveryAmt(new BigDecimal(lDoubleRecoveryAmount));
				lObjRptExpenditureDtls.setDeduction(new BigDecimal(lDoubleDeductionG));

				lObjExpenditureDtlsDAO.updateVo(lObjRptExpenditureDtls);

				hashReturn.put("Status", "SuccessFul Transaction");
				hashReturn.put("flag","true");
				hashReturn.put("voName", "RptExpEdpDtls"); 

				//-------------------------------------------------------------------------------------

				//hashReturn = lObjExpEdpDtlsDAO.updateVo(lObjRptRptExpEdpDtlsVO);
			}
			else
			{
				hashReturn.put("Status", "Map Is null");
				hashReturn.put("flag","false");
				hashReturn.put("voName", "RptExpEdpDtls"); 
			}

			logger.info("-----------------------updateExpEdpData function of DssDataServices ends-------------------------");
		}
		catch(Exception e)
		{
			hashReturn.put("Status", e.toString());
			hashReturn.put("flag","false");
			hashReturn.put("voName", "RptExpEdpDtls"); 
			logger.error("This is Error", e);
		}

		return hashReturn;
	}

	public HashMap updateReceiptData(Map inputMap)
	{
		hashReturn = new HashMap();
		String[] columns=new String[2];
		Object[] values=new Object[2];
		RptExpEdpDtls lObjExpEdpDtls = null;
		RptReceiptDtls lObjRptReceiptDtls = null;
		RptExpenditureDtls lObjRptExpenditureDtls = null;
		double lDoubleGrossAmount = 0.00;
		double lDoubleRecoveryAmount = 0.00;
		double lDoubleDeductionA = 0.00;
		double lDoubleDeductionB = 0.00;
		double lDoubleDeductionG = 0.00;
		double lDoubleDisbursement = 0.00;
		boolean IsDeductionA = false;
		double lDoubleNetAmount = 0.00;
		double lDoubleExpAmount = 0.00;
		BigDecimal bgdcexpId = null;
		List lst = null;

		try
		{
			logger.info("-----------------------updateReceiptData function of DssDataServices starts-------------------------");
			HashMap map = (HashMap)inputMap.get("map");

			if(! (inputMap == null) )
			{
				ServiceLocator serv = (ServiceLocator)map.get("serviceLocator");
				DSSRptReceiptDtlsDAOImpl lObjReceiptDtlsDAO = new DSSRptReceiptDtlsDAOImpl(RptReceiptDtls.class, serv.getSessionFactory());
				DSSRptExpEdpDtlsDAOImpl lObjExpEdpDtlsDAO = new DSSRptExpEdpDtlsDAOImpl(RptExpEdpDtls.class, serv.getSessionFactory());
				DssRptExpenditureDtlsDAOImpl lObjExpenditureDtlsDAO = new DssRptExpenditureDtlsDAOImpl(RptExpenditureDtls.class, serv.getSessionFactory());

				RptReceiptDtls lObjRptReceiptDtlsVO = (RptReceiptDtls)inputMap.get("RptReceiptVO");
				bgdcexpId = lObjRptReceiptDtlsVO.getExpId();

				//-----------------------------------------------------------------------------------
				// updating the Edp VO
				//-----------------------------------------------------------------------------------

				logger.info("updating the RptreceiptVO in updateReceiptData function-------------------------");

				lObjReceiptDtlsDAO.update(lObjRptReceiptDtlsVO);

				//------------------------------------------------------------------------------------
				// calculating the Gross Amount & Recovery Amounts
				//------------------------------------------------------------------------------------

				logger.info("updating the RptreceiptVO in updateReceiptData function-------------------------");


				//Anshu - Instead of ExpEDP VOs you can get ExpVO for gross and recovery START
				/*	columns[0]= "expId";
				columns[1]= "active";

				values[0]= bgdcexpId;
				values[1]= 'Y';

				lst = lObjExpEdpDtlsDAO.getListByColumnAndValue(columns, values);

				 if(lst.size()>0)
				 {
				 	for(int y = 0 ; y < lst.size();y++)
					{
				 		logger.info("This is the Gross & Recovery Amount Calculation loop  Deleting Edp " + y);
				 		lObjExpEdpDtls = (RptExpEdpDtls)lst.get(y);

				 		if(lObjExpEdpDtls.getEdpType()== '0')
				 		{
				 			lDoubleGrossAmount = lDoubleGrossAmount + lObjExpEdpDtls.getAmount().doubleValue();
				 		}
				 		else if(lObjExpEdpDtls.getEdpType()== '1')
				 		{
				 			lDoubleRecoveryAmount = lDoubleRecoveryAmount + lObjExpEdpDtls.getAmount().doubleValue();
				 		}	


				    }
				 }*/


				List lst1 = lObjExpenditureDtlsDAO.getListByColumnAndValue("expId", bgdcexpId);
				if(!(lst1.isEmpty()))
				{
					lObjRptExpenditureDtls = (RptExpenditureDtls)lst1.get(0);

					lDoubleGrossAmount = lObjRptExpenditureDtls.getGrossAmnt().doubleValue();
					lDoubleRecoveryAmount = lObjRptExpenditureDtls.getRecoveryAmt().doubleValue();
				}





				//Anshu -- END 
				//------------------------------------------------------------------------------------
				//calculating the Deductions & Disbursment Amounts
				//------------------------------------------------------------------------------------

				logger.info("calculating the Deductions & Disbursment Amounts in updateReceiptData Function------------------");

				columns[0]= "expId";
				columns[1]= "active";

				values[0]= bgdcexpId;
				values[1]= 'Y';

				lst = lObjReceiptDtlsDAO.getListByColumnAndValue(columns, values);

				if(lst.size()>0)
				{
					for(int y = 0 ; y < lst.size();y++)
					{
						logger.info("This is the Gross & Recovery Amount Calculation loop  Deleting Edp " + y);
						lObjRptReceiptDtls = (RptReceiptDtls)lst.get(y);

						if(lObjRptReceiptDtls.getDedctnType()!=null && lObjRptReceiptDtls.getDedctnType()=='A')
						{
							lDoubleDeductionA = lDoubleDeductionA + lObjRptReceiptDtls.getAmount().doubleValue();
							IsDeductionA = true;
						}
						else if(lObjRptReceiptDtls.getDedctnType()!=null && lObjRptReceiptDtls.getDedctnType()=='B')
						{
							lDoubleDeductionB = lDoubleDeductionB + lObjRptReceiptDtls.getAmount().doubleValue();
						}
						else
						{
							lDoubleDeductionG = lDoubleDeductionG + lObjRptReceiptDtls.getAmount().doubleValue();
						}

						lDoubleDisbursement = lDoubleDisbursement + lObjRptReceiptDtls.getDisbursementAmt().doubleValue();

					}
				}

				//-------------------------------------------------------------------------------------------------
				//	calculating the Expenditure Amounts
				//-------------------------------------------------------------------------------------------------

				logger.info("calculating the Expenditure Amounts in updateReceipt Function-------------------------");

				if(!(lst1.isEmpty()))
				{
					lDoubleNetAmount = lDoubleGrossAmount - (lDoubleDeductionA + lDoubleDeductionB + lDoubleDeductionG + lDoubleDisbursement + lDoubleRecoveryAmount);

					if(IsDeductionA)
						lDoubleExpAmount = lDoubleNetAmount + lDoubleDeductionA;
					else
						lDoubleExpAmount = lDoubleGrossAmount - lDoubleRecoveryAmount;

					lObjRptExpenditureDtls = lObjExpenditureDtlsDAO.read(bgdcexpId);

					lObjRptExpenditureDtls.setGrossAmnt(new BigDecimal(lDoubleGrossAmount));
					lObjRptExpenditureDtls.setDedctnaAmt(new BigDecimal(lDoubleDeductionA));
					lObjRptExpenditureDtls.setDedctnbAmt(new BigDecimal(lDoubleDeductionB));						
					lObjRptExpenditureDtls.setNetAmt(new BigDecimal(lDoubleNetAmount));
					lObjRptExpenditureDtls.setExpAmt(new BigDecimal(lDoubleExpAmount));
					lObjRptExpenditureDtls.setRecoveryAmt(new BigDecimal(lDoubleRecoveryAmount));
					lObjRptExpenditureDtls.setDeduction(new BigDecimal(lDoubleDeductionG));

					lObjExpenditureDtlsDAO.updateVo(lObjRptExpenditureDtls);

				}


				//hashReturn = lObjReceiptDtlsDAO.updateVo(lObjRptReceiptDtlsVO);
			}
			else
			{
				hashReturn.put("Status", "Map Is null");
				hashReturn.put("flag","false");
				hashReturn.put("voName", "RptReceiptDtls"); 
			}

			logger.info("-----------------------updateReceiptData function of DssDataServices ends-------------------------");
		}
		catch(Exception e)
		{
			hashReturn.put("Status", e.toString());
			hashReturn.put("flag","false");
			hashReturn.put("voName", "RptReceiptDtls"); 
			logger.error("This is Error", e);
		}

		return hashReturn;
	}

	public HashMap updatePaymentData(Map inputMap)
	{
		hashReturn = new HashMap();
		try
		{
			logger.info("-----------------------updateReceiptData function of DssDataServices starts-------------------------");

			HashMap map = (HashMap)inputMap.get("map");

			if(! (inputMap == null) )
			{
				ServiceLocator serv = (ServiceLocator)map.get("serviceLocator");
				DSSRptPaymentDtlsDAOImpl lObjPaymentDtlsDAO = new DSSRptPaymentDtlsDAOImpl(RptPaymentDtls.class, serv.getSessionFactory());
				RptPaymentDtls lObjRptPaymentDtlsVO = (RptPaymentDtls)inputMap.get("RptPaymentVO");
				lObjPaymentDtlsDAO.update(lObjRptPaymentDtlsVO);
			}
			else
			{
				hashReturn.put("Status", "Map Is null");
				hashReturn.put("flag","false");
				hashReturn.put("voName", "RptPaymentDtls"); 
			}

			logger.info("-----------------------updateReceiptData function of DssDataServices ends-------------------------");

		}
		catch(Exception e)
		{
			hashReturn.put("Status", e.toString());
			hashReturn.put("flag","false");
			hashReturn.put("voName", "RptPaymentDtls"); 
			logger.error("This is Error", e);
		}

		return hashReturn;
	}

	//Anshu - to be reviewed
	public HashMap BulkUpdateData(Map inputMap)
	{
		hashReturn = null;
		try
		{
			logger.info("-----------------------BulkupdateData function of DssDataServices starts-------------------------");
			HashMap map = (HashMap)inputMap.get("map");
			if(inputMap != null)
			{
				ServiceLocator serv = (ServiceLocator)map.get("serviceLocator");

				DssRptExpenditureDtlsDAOImpl lObjExpenditureDtlsDAO = new DssRptExpenditureDtlsDAOImpl(RptExpenditureDtls.class, serv.getSessionFactory());

				DSSRptExpEdpDtlsDAOImpl lObjExpEdpDtlsDAO = new DSSRptExpEdpDtlsDAOImpl(RptExpEdpDtls.class, serv.getSessionFactory());

				DSSRptReceiptDtlsDAOImpl lObjReceiptDtlsDAO = new DSSRptReceiptDtlsDAOImpl(RptReceiptDtls.class, serv.getSessionFactory());



				BigDecimal expNo = new BigDecimal( inputMap.get("expNo").toString());


				String expTypeCode = inputMap.get("expTypeCode").toString();



				BigDecimal expId = lObjExpenditureDtlsDAO.getExpId(expNo, expTypeCode);

				if(inputMap.containsKey("RptExpEdpVOArrLst"))
				{

					lObjExpEdpDtlsDAO.deleteData(expId);
					ArrayList ArrExpEdp = (ArrayList)inputMap.get("RptExpEdpVOArrLst");

					Map input = inputMap;

					if(input.containsKey("RptReceipVOArrLst"))
						input.remove("RptReceipVOArrLst");
					insertExpEdpData(input);


				}
				if(inputMap.containsKey("RptReceipVOArrLst"))
				{
					ArrayList ArrExpEdp = (ArrayList)inputMap.get("RptReceipVOArrLst");

					lObjReceiptDtlsDAO.deleteData(expId);
					Map input = inputMap;
					if(input.containsKey("RptExpEdpVOArrLst"))
						input.remove("RptExpEdpVOArrLst");
					insertReceiptData(input);
				}
			}

			logger.info("-----------------------BulkupdateData function of DssDataServices ends-------------------------");

			return hashReturn;
		}
		catch(Exception e)
		{
			logger.error("The Error is at last" , e);
			return hashReturn;
		}
	}


	public Map LcUpdateVO(Map InputMap)
	{
		logger.info("--------------MMMMM---------");
		Map hashReturn = new HashMap();
		Map map =(HashMap)InputMap.get("map");
		try
		{
			ServiceLocator serv = (ServiceLocator)map.get("serviceLocator");
			DSSRptReceiptDtlsDAOImpl lObjReceiptDtlsDAO = new DSSRptReceiptDtlsDAOImpl(RptReceiptDtls.class, serv.getSessionFactory());

			lObjReceiptDtlsDAO.update((RptReceiptDtls)InputMap.get("RptReceiptVO"));
		}
		catch(Exception e)
		{
			hashReturn.put("Status", e.toString());
			hashReturn.put("flag","false");
			hashReturn.put("voName", "RptPaymentDtls"); 
			logger.error("This is Error", e);
		}
		logger.info("--------------NNNNN---------");
		return hashReturn;
	}

	public HashMap deleteExpData1(Map inputMap)
	{
		hashReturn = new HashMap();
		BigDecimal lbgdcExpId = null;
		String columns[]=new String[2] ;
		Object values[] =new Object[2];
		Object list_iterator[] = {};
		List lst = null;
		Iterator it = null;

		try
		{
			//Anshu - containsKey chk
			HashMap map = (HashMap)inputMap.get("map");
			logger.info("-----------------------deleteExpData function of DssDataServiceImpl starts-------------------");

			ServiceLocator serv = (ServiceLocator)map.get("serviceLocator");
			DssRptExpenditureDtlsDAOImpl lObjExpenditureDtlsDAO = new DssRptExpenditureDtlsDAOImpl(RptExpenditureDtls.class, serv.getSessionFactory());
			DSSRptExpEdpDtlsDAOImpl lObjExpEdpDtlsDAOImpl = new DSSRptExpEdpDtlsDAOImpl(RptExpEdpDtls.class,serv.getSessionFactory());
			DSSRptReceiptDtlsDAOImpl lObjRptReceiptDtlsDAOImpl = new DSSRptReceiptDtlsDAOImpl(RptReceiptDtls.class,serv.getSessionFactory());

			if(! (inputMap == null) )
			{			
				BigDecimal bgdcExpNo = new  BigDecimal(inputMap.get("expNo").toString());
				String lStrExpTypeCode = inputMap.get("expTypeCode").toString();
				logger.info("hi i am going into the delete exp data function of Expenditure DAO----------");



				//lbgdcExpId = lObjExpenditureDtlsDAO.getExpId(bgdcExpNo, lStrExpTypeCode);
				columns[0]="expNo";
				columns[1]="expTypeCode";

				values[0]=bgdcExpNo;
				values[1] =lStrExpTypeCode; 

				//------------------------------------------------------------------------------------
				//getting & Deleting the Expenditure VO according to the given Exp_No and Exp_Type_code
				//-------------------------------------------------------------------------------------

				lst = lObjExpenditureDtlsDAO.getListByColumnAndValue(columns, values);

				if(!lst.isEmpty())
				{
					RptExpenditureDtls lObjRptExpenditureDtls = (RptExpenditureDtls)lst.get(0) ;
					lObjRptExpenditureDtls.setActive('N');
					lObjExpenditureDtlsDAO.updateVo(lObjRptExpenditureDtls);

					lObjRptExpenditureDtls.toString();

					logger.info("the Expenditure VO is successfully Updated : +++++++++++++++++++++++++++++++++++++++");
				}
				//--------------------------------------------------------------------------------
				// getting & deleting the Edp Vos corresponding to that ExpId	
				//--------------------------------------------------------------------------------

				lst = lObjExpEdpDtlsDAOImpl.getListByColumnAndValue("expId", lbgdcExpId);

				if(lst.size()>0)
				{
					for(int y = 0 ; y < lst.size();y++)
					{
						logger.info("This is the deleteign loop of ExpEdpDtls VO in Deleting Expenditure " + y);
						RptExpEdpDtls lObjRptExpEdpDtls = (RptExpEdpDtls)lst.get(y);
						lObjRptExpEdpDtls.setActive('N');
						lObjExpEdpDtlsDAOImpl.update(lObjRptExpEdpDtls);
					}
				}

				//---------------------------------------------------------------------------
				// getting & deleting the Receipt VOs corresponding to that ExpId
				//----------------------------------------------------------------------------

				lst = lObjRptReceiptDtlsDAOImpl.getListByColumnAndValue("expId", lbgdcExpId);

				if(lst.size()>0)
				{
					for(int y = 0 ; y < lst.size();y++)
					{
						logger.info("This is the deleteign loop of ReceiptDtls VO in Deleting Expenditure " + y);

						RptReceiptDtls lObjRptReceiptDtls = (RptReceiptDtls)lst.get(y);
						lObjRptReceiptDtls.setActive('N');
						lObjRptReceiptDtlsDAOImpl.update(lObjRptReceiptDtls);
					}
				}

				hashReturn.put("Status", "Successful Transaction");
				hashReturn.put("flag","True");
				hashReturn.put("voName", "RptExpenditureDtls"); 


			}
			else
			{
				hashReturn.put("Status", "Map Is null");
				hashReturn.put("flag","false");
				hashReturn.put("voName", "RptExpenditureDtls"); 
			}

			logger.info("-----------------------deleteExpData function of DssDataServiceImpl ends-------------------");
		}
		catch(Exception e)
		{
			hashReturn.put("Status", e.toString());
			hashReturn.put("flag","false");
			hashReturn.put("voName", "RptExpenditureDtls"); 
			logger.error("This is Error", e);
		}
		return hashReturn;

	}

	public HashMap deleteReceiptData1(Map inputMap)
	{
		hashReturn = new HashMap();

		String columns[]=new String[4] ;
		Object values[] =new Object[4];
		RptExpEdpDtls lObjExpEdpDtls = null;
		RptReceiptDtls lObjRptReceiptDtls = null;
		double lDoubleGrossAmount = 0.00;
		double lDoubleRecoveryAmount = 0.00;
		double lDoubleDeductionA = 0.00;
		double lDoubleDeductionB = 0.00;
		double lDoubleDeductionG = 0.00;
		double lDoubleDisbursement = 0.00;
		boolean IsDeductionA = false;
		double lDoubleNetAmount = 0.00;
		double lDoubleExpAmount = 0.00;

		BigDecimal lbgdcExpId = null;

		RptReceiptDtls lObjReceiptDtls = null;
		RptExpenditureDtls lObjRptExpenditureDtls = null;



		try
		{
			//Anshu - contains chk
			HashMap map = (HashMap)inputMap.get("map");

			logger.info("-----------------------deleteReceiptData function of DssDataServiceImpl starts-------------------");

			ServiceLocator serv = (ServiceLocator)map.get("serviceLocator");
			DSSRptReceiptDtlsDAOImpl lObjReceiptDtlsDAO = new DSSRptReceiptDtlsDAOImpl(RptReceiptDtls.class, serv.getSessionFactory());
			DssRptExpenditureDtlsDAOImpl lObjExpenditureDtlsDAOImpl = new DssRptExpenditureDtlsDAOImpl(RptExpenditureDtls.class,serv.getSessionFactory());

			if(! (inputMap == null) )
			{			

				BigDecimal bgdcRcptNo = new BigDecimal(inputMap.get("rcptNo").toString());
				String lStrRcptTypeCode = inputMap.get("rcptTypeCode").toString();
				String lStrChallanCatgCode = inputMap.get("challanCatgCode").toString();
				BigDecimal trnReceiptId = new BigDecimal(inputMap.get("trnReceiptId").toString());

				columns[0] = "rcptNo";
				columns[1] = "rcptTypeCode";
				columns[2] = "challanCatgCode";
				columns[3] = "trnReceiptId";

				values[0] = bgdcRcptNo;
				values[1] = lStrRcptTypeCode;
				values[2] = lStrChallanCatgCode;
				values[3] = trnReceiptId;

				//-----------------------------------------------------------------------------------------
				// Getting & Deleting the ReceiptVO
				//-----------------------------------------------------------------------------------------

				List lst = lObjReceiptDtlsDAO.getListByColumnAndValue(columns, values);

				if(!lst.isEmpty())
				{
					lObjReceiptDtls = (RptReceiptDtls)lst.get(0);
					lObjReceiptDtls.setActive('N');
					lObjReceiptDtlsDAO.update(lObjReceiptDtls);

					//----------------------------------------------------------------------------------------------
					// Getting the ExpenditureVO , that is to be updated
					//-----------------------------------------------------------------------------------------------

					lbgdcExpId = lObjReceiptDtls.getExpId();

					if(lbgdcExpId.intValueExact() != 0)
					{
						lObjRptExpenditureDtls =  lObjExpenditureDtlsDAOImpl.read(lbgdcExpId);
						lDoubleGrossAmount = lObjRptExpenditureDtls.getGrossAmnt().doubleValue();
						lDoubleRecoveryAmount = lObjRptExpenditureDtls.getRecoveryAmt().doubleValue();


						//--------------------------------------------------------------------------------------------
						// Get the Deductions and Disbursments
						//--------------------------------------------------------------------------------------------

						String columns1[]=new String[2] ;
						Object values1[] =new Object[2];

						columns1[0]= "expId";
						columns1[1]= "active";

						values1[0]= lbgdcExpId;
						values1[1]= 'Y';

						lst = lObjReceiptDtlsDAO.getListByColumnAndValue(columns1, values1);

						if(lst.size()>0)
						{
							for(int y = 0 ; y < lst.size();y++)
							{
								logger.info("This is the Gross & Recovery Amount Calculation loop  Deleting Edp " + y);
								lObjRptReceiptDtls = (RptReceiptDtls)lst.get(y);

								if(lObjRptReceiptDtls.getDedctnType()!=null && lObjRptReceiptDtls.getDedctnType()=='A')
								{
									lDoubleDeductionA = lDoubleDeductionA + lObjRptReceiptDtls.getAmount().doubleValue();
									IsDeductionA = true;
								}
								else if(lObjRptReceiptDtls.getDedctnType()!=null && lObjRptReceiptDtls.getDedctnType()=='B')
								{
									lDoubleDeductionB = lDoubleDeductionB + lObjRptReceiptDtls.getAmount().doubleValue();
								}
								else
								{
									lDoubleDeductionG = lDoubleDeductionG + lObjRptReceiptDtls.getAmount().doubleValue();
								}

								lDoubleDisbursement = lDoubleDisbursement + lObjRptReceiptDtls.getDisbursementAmt().doubleValue();

							}
						}

						//-------------------------------------------------------------------------------------------------
						// Calculatin the Expenditure VO ' Amount
						//------------------------------------------------------------------------------------------------- 

						lDoubleNetAmount = lDoubleGrossAmount - (lDoubleDeductionA + lDoubleDeductionB + lDoubleDeductionG + lDoubleDisbursement + lDoubleRecoveryAmount);

						if(IsDeductionA)
							lDoubleExpAmount = lDoubleNetAmount + lDoubleDeductionA;
						else
							lDoubleExpAmount = lDoubleGrossAmount - lDoubleRecoveryAmount;


						lObjRptExpenditureDtls.setGrossAmnt(new BigDecimal(lDoubleGrossAmount));
						lObjRptExpenditureDtls.setDedctnaAmt(new BigDecimal(lDoubleDeductionA));
						lObjRptExpenditureDtls.setDedctnbAmt(new BigDecimal(lDoubleDeductionB));						
						lObjRptExpenditureDtls.setDeduction(new BigDecimal(lDoubleDeductionG));
						lObjRptExpenditureDtls.setNetAmt(new BigDecimal(lDoubleNetAmount));
						lObjRptExpenditureDtls.setExpAmt(new BigDecimal(lDoubleExpAmount));
						lObjRptExpenditureDtls.setRecoveryAmt(new BigDecimal(lDoubleRecoveryAmount));

						lObjExpenditureDtlsDAOImpl.updateVo(lObjRptExpenditureDtls);

						hashReturn.put("Status", "SuccessFul Transaction");
						hashReturn.put("flag","True");
						hashReturn.put("voName", "RptReceiptDtls"); 
					}

				}
				//-----------------------------------------------------------------------------------------------

			}
			else
			{
				hashReturn.put("Status", "Map Is null");
				hashReturn.put("flag","false");
				hashReturn.put("voName", "RptReceiptDtls"); 
			}

			logger.info("-----------------------deleteReceiptData function of DssDataServiceImpl ends-------------------");
		}
		catch(Exception e)
		{
			hashReturn.put("Status", e.toString());
			hashReturn.put("flag","false");
			hashReturn.put("voName", "RptReceiptDtls"); 
			logger.error("This is Error", e);
		}

		return hashReturn;
	}


	public HashMap deleteExpEdpData1(Map inputMap)
	{

		hashReturn = new HashMap();
		String[] columns=new String[2];
		Object[] values=new Object[2];
		RptExpEdpDtls lObjExpEdpDtls = null;
		RptReceiptDtls lObjRptReceiptDtls = null;
		double lDoubleGrossAmount = 0.00;
		double lDoubleRecoveryAmount = 0.00;
		double lDoubleDeductionA = 0.00;
		double lDoubleDeductionB = 0.00;
		double lDoubleDeductionG = 0.00;
		double lDoubleDisbursement = 0.00;
		boolean IsDeductionA = false;
		double lDoubleNetAmount = 0.00;
		double lDoubleExpAmount = 0.00;

		List lst = null;
		BigDecimal bgdcexpId =  null;

		try
		{
			//Anshu - chk
			HashMap map = (HashMap)inputMap.get("map");

			logger.info("-----------------------deleteExpEdpData function of DssDataServiceImpl starts-------------------");
			ServiceLocator serv = (ServiceLocator)map.get("serviceLocator");

			DSSRptExpEdpDtlsDAOImpl lObjExpEdpDtlsDAO = new DSSRptExpEdpDtlsDAOImpl(RptExpEdpDtls.class, serv.getSessionFactory());
			DssRptExpenditureDtlsDAOImpl lObjExpenditureDtlsDAO = new DssRptExpenditureDtlsDAOImpl(RptExpenditureDtls.class, serv.getSessionFactory());
			DSSRptReceiptDtlsDAOImpl lObjReceiptDtlsDAOImpl = new DSSRptReceiptDtlsDAOImpl(RptReceiptDtls.class,serv.getSessionFactory());

			if(! (inputMap == null) )
			{			
				BigDecimal bgdcExpNo = new BigDecimal(inputMap.get("expNo").toString());
				String lStrExpTypeCode = inputMap.get("expTypeCode").toString();
				BigDecimal trnExpEdpId = new BigDecimal(inputMap.get("trnExpEdpId").toString());

				//--------------------------------------------------------------------------------------------
				//	getting the Expenditure VO , to get ExpId
				//--------------------------------------------------------------------------------------------

				columns[0]="expNo";
				columns[1]="expTypeCode";

				values[0]=bgdcExpNo;
				values[1] =lStrExpTypeCode; 

				lst = lObjExpenditureDtlsDAO.getListByColumnAndValue(columns, values);

				if(!lst.isEmpty())
				{
					RptExpenditureDtls lObjRptExpenditureDtls = (RptExpenditureDtls)lst.get(0) ;
					bgdcexpId = lObjRptExpenditureDtls.getExpId();



					//--------------------------------------------------------------------------------------
					//getting & deleting the Edp VO
					//--------------------------------------------------------------------------------------


					columns[0]= "expId";
					columns[1]= "trnExpEdpId";

					values[0]= bgdcexpId;
					values[1]= trnExpEdpId;

					lst = lObjExpEdpDtlsDAO.getListByColumnAndValue(columns, values);

					if(!lst.isEmpty())
					{
						RptExpEdpDtls lObjRptExpEdpDtls = (RptExpEdpDtls)lst.get(0);
						lObjRptExpEdpDtls.setActive('N');
						lObjExpEdpDtlsDAO.update(lObjRptExpEdpDtls);
					}
					//-------------------------------------------------------------------------------
					// Get total Gross & Recovery Amount of corresponding ExpId
					//-------------------------------------------------------------------------------

					columns[0]= "expId";
					columns[1]= "active";

					values[0]= bgdcexpId;
					values[1]= 'Y';

					lst = lObjExpEdpDtlsDAO.getListByColumnAndValue(columns, values);

					if(lst.size()>0)
					{
						for(int y = 0 ; y < lst.size();y++)
						{
							logger.info("This is the Gross & Recovery Amount Calculation loop  Deleting Edp " + y);
							lObjExpEdpDtls = (RptExpEdpDtls)lst.get(y);

							if(lObjExpEdpDtls.getEdpType()== '0')
							{
								lDoubleGrossAmount = lDoubleGrossAmount + lObjExpEdpDtls.getAmount().doubleValue();
							}
							else if(lObjExpEdpDtls.getEdpType()== '1')
							{
								lDoubleRecoveryAmount = lDoubleRecoveryAmount + lObjExpEdpDtls.getAmount().doubleValue();
							}	


						}
					}

					//--------------------------------------------------------------------------------------------
					// Get deduction A , B , General & disbursment
					//--------------------------------------------------------------------------------------------
					columns[0]= "expId";
					columns[1]= "active";
					//Anshu - can add deducition_type ='A' as condition instead of the below code

					values[0]= bgdcexpId;
					values[1]= 'Y';

					lst = lObjReceiptDtlsDAOImpl.getListByColumnAndValue(columns, values);

					if(lst.size()>0)
					{
						for(int y = 0 ; y < lst.size();y++)
						{
							logger.info("This is the Gross & Recovery Amount Calculation loop  Deleting Edp " + y);
							lObjRptReceiptDtls = (RptReceiptDtls)lst.get(y);

							if(lObjRptReceiptDtls.getDedctnType()!=null && lObjRptReceiptDtls.getDedctnType()=='A')
							{
								lDoubleDeductionA = lDoubleDeductionA + lObjRptReceiptDtls.getAmount().doubleValue();
								IsDeductionA = true;
							}
							else if(lObjRptReceiptDtls.getDedctnType()!=null && lObjRptReceiptDtls.getDedctnType()=='B')
							{
								lDoubleDeductionB = lDoubleDeductionB + lObjRptReceiptDtls.getAmount().doubleValue();
							}
							else
							{
								lDoubleDeductionG = lDoubleDeductionG + lObjRptReceiptDtls.getAmount().doubleValue();
							}

							lDoubleDisbursement = lDoubleDisbursement + lObjRptReceiptDtls.getDisbursementAmt().doubleValue();

						}
					}
				}
				//-------------------------------------------------------------------------------------------
				// updating the Expenditure VO
				//------------------------------------------------------------------------------------------

				lDoubleNetAmount = lDoubleGrossAmount - (lDoubleDeductionA + lDoubleDeductionB + lDoubleDeductionG + lDoubleDisbursement + lDoubleRecoveryAmount);

				if(IsDeductionA)
					lDoubleExpAmount = lDoubleNetAmount + lDoubleDeductionA;
				else
					lDoubleExpAmount = lDoubleGrossAmount - lDoubleRecoveryAmount;

				RptExpenditureDtls lObjRptExpenditureDtls = lObjExpenditureDtlsDAO.read(bgdcexpId);
				lObjRptExpenditureDtls.setGrossAmnt(new BigDecimal(lDoubleGrossAmount));
				lObjRptExpenditureDtls.setDedctnaAmt(new BigDecimal(lDoubleDeductionA));
				lObjRptExpenditureDtls.setDedctnbAmt(new BigDecimal(lDoubleDeductionB));						
				lObjRptExpenditureDtls.setNetAmt(new BigDecimal(lDoubleNetAmount));
				lObjRptExpenditureDtls.setExpAmt(new BigDecimal(lDoubleExpAmount));
				lObjRptExpenditureDtls.setRecoveryAmt(new BigDecimal(lDoubleRecoveryAmount));
				lObjRptExpenditureDtls.setDeduction(new BigDecimal(lDoubleDeductionG));

				lObjExpenditureDtlsDAO.updateVo(lObjRptExpenditureDtls);

				hashReturn.put("Status", "successful Transaction");
				hashReturn.put("flag","true");
				hashReturn.put("voName", "RptExpEdpDtls"); 


			}
			else
			{
				hashReturn.put("Status", "Map Is null");
				hashReturn.put("flag","false");
				hashReturn.put("voName", "RptExpEdpDtls"); 
			}

			logger.info("-----------------------deleteExpEdpData function of DssDataServiceImpl ends-------------------");
		}
		catch(Exception e)
		{
			hashReturn.put("Status", e.toString());
			hashReturn.put("flag","false");
			hashReturn.put("voName", "RptExpEdpDtls"); 
			logger.error("This is Error", e);
		}


		return hashReturn;
	}

}





