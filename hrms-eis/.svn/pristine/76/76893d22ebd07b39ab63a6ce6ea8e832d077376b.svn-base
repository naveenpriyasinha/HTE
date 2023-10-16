package com.tcs.sgv.eis.service;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import bds.authorization.MapConverter;
import bds.authorization.PayrollBEAMSIntegrateWS;

import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.dao.FinancialYearDAOImpl;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.BDSIntegrationDAOImpl;
import com.tcs.sgv.eis.dao.DeptCompMPGDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.PayrollBEAMSMpgDAO;
import com.tcs.sgv.eis.dao.PayrollBEAMSMpgDAOImpl;
import com.tcs.sgv.eis.valueobject.HrPayLocComMpg;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayrollBeamsMpg;
import com.tcs.sgv.eis.valueobject.TrnIfmsBeamsIntegration;
import com.tcs.sgv.eis.zp.ReportingDDO.dao.ConsolidatedBillMstDaoImpl;
import com.tcs.sgv.eis.zp.ReportingDDO.valueobject.ConsolidatedBillMst;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class BDSIntegrationServiceImpl extends ServiceImpl
{
    Log logger = LogFactory.getLog(this.getClass());
    ResourceBundle integrationBundleConst = ResourceBundle.getBundle("resources.Payroll");
    /* Global Variable for Logger Class */
    // private final Log gLogger = LogFactory.getLog(getClass());
    private String gStrPostId = null; /* STRING POST ID */

    private String gStrUserId = null; /* STRING USER ID */

    private String gStrLocale = null; /* STRING LOCALE */

    private Locale gLclLocale = null; /* LOCALE */

    private Long gLngLangId = null; /* LANG ID */

    private Long gLngDBId = null; /* DB ID */

    private Date gDtCurDate = null; /* CURRENT DATE */

    private HttpServletRequest request = null; /* REQUEST OBJECT */

    private ServiceLocator serv = null; /* SERVICE LOCATOR */

    private HttpSession session = null; /* SESSION */
    // private final ResourceBundle integrationBundleConst =
    // ResourceBundle.getBundle("resources/common/IFMSIntegration");
    /* Global Variable for PostId */
    Long gLngPostId = null;

    /* Global Variable for UserId */
    Long gLngUserId = null;

    /* Global Variable for Current Date */
    Date gDtCurrDt = null;

    /* Global Variable for Location Code */
    String gStrLocationCode = null;

    /* Global Variable for User Loc Map */
    // static HashMap sMapUserLoc = new HashMap();
    /* Global Variable for User Location */
    String gStrUserLocation = null;

    public ResultObject forwardBillDataToBEAMS(Map objectArgs)
    {
        final ResultObject resultObject = new ResultObject(0);
        try
        {
            final HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
            final ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");

            final Map loginMap = (Map) objectArgs.get("baseLoginMap");
            final long lLongLoggedInLocation = Long.valueOf(loginMap.get("locationId").toString()).longValue();
            StringUtility.convertToLong(loginMap.get("userId").toString()).longValue();
            StringUtility.convertToLong(loginMap.get("primaryPostId").toString()).longValue();

            this.logger.error("lLongLoggedInLocation is----->" + lLongLoggedInLocation);

            final PayBillDAOImpl payBillDAOImpl = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
            final ConsolidatedBillMstDaoImpl consolidatedBillMstDaoImpl = new ConsolidatedBillMstDaoImpl(
                    ConsolidatedBillMst.class, serv.getSessionFactory());
            final FinancialYearDAOImpl financialYearDAOImpl = new FinancialYearDAOImpl(SgvcFinYearMst.class, serv
                    .getSessionFactory());
            final BDSIntegrationDAOImpl treasuryIntegrationDAOImpl = new BDSIntegrationDAOImpl(HrPayPaybill.class, serv
                    .getSessionFactory());
            new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
            new DeptCompMPGDAOImpl(HrPayLocComMpg.class, serv.getSessionFactory());

            final HashMap lMapBillDetailsMap = new HashMap();
            final long lLongBillGroupId = !"".equals(StringUtility.getParameter("billNo", request)) ? Long
                    .parseLong(StringUtility.getParameter("billNo", request)) : 0L;
            this.logger.error("BillGroup Id is----->" + lLongBillGroupId);

            final ConsolidatedBillMst consolidatedBillMst = (ConsolidatedBillMst) consolidatedBillMstDaoImpl
                    .read(lLongBillGroupId);

            final int benCount = consolidatedBillMstDaoImpl.getCount(lLongBillGroupId);

            final List lListDDO = payBillDAOImpl.getDDOCodeByLoggedInlocId(lLongLoggedInLocation);

            this.setSessionInfo(objectArgs);
            SgvcFinYearMst sgvcFinYearMst = new SgvcFinYearMst();
            OrgDdoMst orgDdoMst = null;
            long lLongFinYearIdPK = 0L;
            long lLongCurrFinYear = 0L;
            long lLongNextFinyear = 0L;
            String lStrDdoCode = null;
            String lStrSchemeCode = null;
            String lStrSubSchemeCode = null;
            String lStrDetailHead = null;
            long lLongGrossAmt = 0L;
            long lLongTotalDeduction = 0L;
            Date lDateBillCreation = null;
            long paybillId = 0L;

            Map lMapDeducBifurcatedMap = null;
            Double.valueOf(0D);
            Double.valueOf(consolidatedBillMst.getMonth());
            Double.valueOf(consolidatedBillMst.getYear());
            Long lLngTrnIFMSBeamsIntegrationId = null;

            lDateBillCreation = consolidatedBillMst.getCreatedDate();

            lLongGrossAmt = consolidatedBillMst.getGrossAmt();

            lLongTotalDeduction = consolidatedBillMst.getPf() + consolidatedBillMst.getPt() + consolidatedBillMst.getDcps();
                 //   + consolidatedBillMst.getIt() + consolidatedBillMst.getHrr()  + consolidatedBillMst.getGis();

            paybillId = consolidatedBillMst.getConsBillId();

            if (lListDDO != null && !lListDDO.isEmpty())
            {
                orgDdoMst = (OrgDdoMst) lListDDO.get(0);
                if (orgDdoMst != null)
                {
                    lStrDdoCode = orgDdoMst.getDdoCode();
                }
                this.logger.error("lStrDdoCode  is----->" + lStrDdoCode);
            }

            lLongFinYearIdPK = financialYearDAOImpl.getFinYearIdByCurDate();

            sgvcFinYearMst = financialYearDAOImpl.read(Long.valueOf(lLongFinYearIdPK));

            if (sgvcFinYearMst != null)
            {
                lLongCurrFinYear = Long.valueOf(sgvcFinYearMst.getFinYearCode()).longValue();
                lLongNextFinyear = lLongCurrFinYear + 1L;
            }
            this.logger.error("lLongCurrFinYear  is----->" + lLongCurrFinYear);
            this.logger.error("lLongNextFinyear  is----->" + lLongNextFinyear);

            final List resultList = treasuryIntegrationDAOImpl.getSchemeAndSubHeadOfBill(lLongBillGroupId);
            if (resultList != null && !resultList.isEmpty())
            {
                /*// final Object[] row = (Object[]) resultList.get(0);
                lStrSchemeCode = resultList.get(0).toString();
                lStrDetailHead = "36";
                this.logger.error("lStrSchemeCode  is----->" + lStrSchemeCode);
                this.logger.error("lStrDetailHead  is----->" + lStrDetailHead);*/
            	
            	Iterator IT = resultList.iterator();

				Object[] lObj = null;
				while (IT.hasNext())
				{
					lObj = (Object[]) IT.next();
					if (lObj[0]!= null)
						lStrSchemeCode = lObj[0].toString();
					if (lObj[1]!= null)
						lStrSubSchemeCode = lObj[1].toString();
				}
				
				/*Object[] row = (Object[]) resultList.get(0);
				lStrSchemeCode = String.valueOf(row[0]);*/
				lStrDetailHead = "36";
				this.logger.error("lStrSchemeCode  is----->" + lStrSchemeCode);
				this.logger.error("lStrSubSchemeCode  is----->" + lStrSubSchemeCode);
				this.logger.error("lStrDetailHead  is----->" + lStrDetailHead);
            	
            }

            lMapBillDetailsMap.put("PaybillId", String.valueOf(paybillId));

            if (lStrDdoCode.equals("2222222222"))
            {
                lMapBillDetailsMap.put("DDOCode", "9101005555");
            } else
            {
                lMapBillDetailsMap.put("DDOCode", String.valueOf(lStrDdoCode));
            }

            final Calendar cal = Calendar.getInstance();
            cal.setTime(lDateBillCreation);
            final int month = cal.get(Calendar.MONTH);
            final int year = cal.get(Calendar.YEAR);
            int curYear = 0;
            final String finYear = this.getFinYear((month + 1), year);
            final String finYearArray[] = finYear.split(",");

            if (month == 1 || month == 2 || month == 3)
            {
                curYear = year - 1;

            } else
            {
                curYear = year;
            }
            int nxtYear = curYear + 1;

            this.logger.error("curYear  is----->" + curYear);
            this.logger.error("nxtYear  is----->" + nxtYear);

            if (consolidatedBillMst.getMonth() == 3)
            { // /
                lMapBillDetailsMap.put("FinYear1", String.valueOf(consolidatedBillMst.getYear()));
                lMapBillDetailsMap.put("FinYear2", String.valueOf(consolidatedBillMst.getYear() + 1));
            } else
            {
                // lMapBillDetailsMap.put("FinYear1",
                // String.valueOf(finYearArray[0]));
                // lMapBillDetailsMap.put("FinYear2",
                // String.valueOf(finYearArray[1]));
                lMapBillDetailsMap.put("FinYear1", String.valueOf(lLongCurrFinYear));
                lMapBillDetailsMap.put("FinYear2", String.valueOf(lLongNextFinyear));
            }

            final int payMonth = Integer.parseInt(consolidatedBillMst.getMonth().toString());

            String strMonth = null;
            if (payMonth < 10)
            {
                strMonth = "0" + payMonth;
            } else
            {
                strMonth = payMonth + "";
            }

            lMapBillDetailsMap.put("PayMonth", strMonth);
            lMapBillDetailsMap.put("PayYear", Integer.parseInt(consolidatedBillMst.getYear().toString()));

            if (treasuryIntegrationDAOImpl.isCmpDDO(lStrDdoCode))
            {
                lMapBillDetailsMap.put("PaymentMode", "CMP");
                lMapBillDetailsMap.put("BulkFlag", "Y");
            } else
            {
                lMapBillDetailsMap.put("PaymentMode", "CMP");
                lMapBillDetailsMap.put("BulkFlag", "Y");
            }

            lMapBillDetailsMap.put("PayeeCount", "1");
            lMapBillDetailsMap.put("BillPortalName", "HTESEVAARTH");

            /*
             * lMapBillDetailsMap .put("SchemeCode",
             * String.valueOf(lStrSchemeCode));
             */

            lMapBillDetailsMap.put("SchemeCode", String.valueOf(lStrSchemeCode));
            
            logger.info("Above lStrSubSchemeCode if");
			//Added by saurabh for subscheme
			if(!lStrSubSchemeCode.equals("-") && !lStrSubSchemeCode.equals(null))
			{
				logger.info("Inside lStrSubSchemeCode if");
				lMapBillDetailsMap.put("SubSchemeCode", String.valueOf(lStrSubSchemeCode));
			}
            lMapBillDetailsMap.put("DetailHead", String.valueOf(lStrDetailHead));
            lMapBillDetailsMap.put("GrossAmount", String.valueOf(lLongGrossAmt));

            // if month is march then bill creation date will be 1st april of
            // that year
            if (consolidatedBillMst.getMonth() == 3)
            {
                lMapBillDetailsMap.put("BillCreationDate", String.valueOf(consolidatedBillMst.getYear())
                        + "-04-01 00:00:00.0");
            } else
            {
                lMapBillDetailsMap.put("BillCreationDate", String.valueOf(lDateBillCreation));
            }

            lMapBillDetailsMap.put("BeneficiaryCount", Integer.valueOf(benCount));

            /*final List pfDetails = consolidatedBillMstDaoImpl.getPFDetailsForConsBill(lLongBillGroupId);
        	Long gpfHeadActCodeOne = 0l;
			Long gpfHeadActCodeTwo = 0l;

            final Iterator it = pfDetails.iterator();

            while (it.hasNext())
            {
            	Object[] obj = (Object[]) it.next();
				if (obj[0] != null)
				{
					if (obj[1] == null)
						obj[1] = "0";
					
					if((obj[0].toString()).equals("8336514701"))
					{
						gpfHeadActCodeOne = Long.parseLong(obj[1].toString()); 
					}
					else 
					{
						gpfHeadActCodeTwo = Long.parseLong(obj[1].toString()); 
					}
				}
            }*/

            lMapDeducBifurcatedMap = new HashMap();
            lMapDeducBifurcatedMap.put("RC0028001201", consolidatedBillMst.getPt());
            lMapDeducBifurcatedMap.put("RC8336514701", consolidatedBillMst.getPf());
            lMapDeducBifurcatedMap.put("RC8342535701", consolidatedBillMst.getDcps());
            lMapDeducBifurcatedMap.put("RC0030046401", consolidatedBillMst.getRevenueStamp());
            // lMapDeducBifurcatedMap.put("RC8009501201", gpfABC + gpfAdvABC);
            // lMapDeducBifurcatedMap.put("RC8009517301", gpfD + gpfAdvD);
          //  lMapDeducBifurcatedMap.put("RC8336514701", gpfHeadActCodeOne);
		//	lMapDeducBifurcatedMap.put("RC8336518301", gpfHeadActCodeTwo);
            
           // lMapDeducBifurcatedMap.put("RC8658518201", consolidatedBillMst.getIt());
            // lMapDeducBifurcatedMap.put("RC0216001300",
            // consolidatedBillMst.getHrr());
         //   lMapDeducBifurcatedMap.put("RC0216006901", consolidatedBillMst.getHrr());
            
         //   lMapDeducBifurcatedMap.put("RC8011502301", consolidatedBillMst.getGis());

            /*
             * if (lMapDeducBifurcatedMap.containsKey("RC0000000000")) { long
             * zeroRCCodeDed =
             * Long.parseLong(lMapDeducBifurcatedMap.get("RC0000000000"
             * ).toString()); lMapDeducBifurcatedMap.remove("RC0000000000");
             * lLongTotalDeduction = lLongTotalDeduction - zeroRCCodeDed; }
             */

            final Map lMapDeducBifurcatedMapSuppzero = new HashMap();
            lMapDeducBifurcatedMapSuppzero.putAll(lMapDeducBifurcatedMap);
            final Iterator entries = lMapDeducBifurcatedMap.entrySet().iterator();

            while (entries.hasNext())
            {
                final Map.Entry entry = (Map.Entry) entries.next();
                final Long value = Long.valueOf(entry.getValue().toString());
                if (value == 0)
                {
                    lMapDeducBifurcatedMapSuppzero.remove(entry.getKey().toString());
                }
            }
            this.logger.error("finalXML is :: " + lMapDeducBifurcatedMapSuppzero);
            lMapBillDetailsMap.put("TotalDeduction", String.valueOf(lLongTotalDeduction));
            lMapBillDetailsMap.put("BifurcatedDedMapInnerMap", lMapDeducBifurcatedMapSuppzero);
            // Integer
            // billType=treasuryIntegrationDAOImpl.getTypeOfBill(paybillId);
            final Integer billType = 1;
            String strBillType = billType.toString();
            // if(billType<10)
            strBillType = "0" + billType; // 01 for Salary Bill
            // if(billType==99)
            // strBillType="02";

            this.logger.error("strBillType " + strBillType + " paybillId" + paybillId);
            lMapBillDetailsMap.put("BillType", strBillType);
            lMapBillDetailsMap.put("FormId", "MTR44");
            final XStream xStream = new XStream(new DomDriver("UTF-8"));
            xStream.alias("collection", Map.class);
            xStream.registerConverter(new MapConverter());
            this.logger.error("finalXML is :: " + xStream.toXML(lMapDeducBifurcatedMapSuppzero));

            final PayrollBEAMSIntegrateWS payrollBEAMSIntegrateWSObj = new PayrollBEAMSIntegrateWS();
            final HashMap resultMap = payrollBEAMSIntegrateWSObj.getBillApprvFrmBEAMSWS(lMapBillDetailsMap, "");
            this.logger.error("resultMap is ::: " + resultMap);

            new PayrollBEAMSMpgDAOImpl(HrPayrollBeamsMpg.class, serv.getSessionFactory());
            String authNo = null;
            String statusCode = null;
            byte[] pdfData = null;
            if (resultMap != null && !resultMap.isEmpty())
            {
                authNo = resultMap.get("authNo") != null ? (String) resultMap.get("authNo") : null;
                statusCode = resultMap.get("statusCode") != null ? (String) resultMap.get("statusCode") : null;
                pdfData = resultMap.get("pdfData") != null ? (byte[]) resultMap.get("pdfData") : null;
                this.logger.error("authNo is ::: " + authNo);
                this.logger.error("statusCode is ::: " + statusCode);
                this.logger.error("pdfData is ::: " + pdfData);

                final BDSIntegrationDAOImpl lObjTreasuryIntegrationDAO = new BDSIntegrationDAOImpl(
                        TrnIfmsBeamsIntegration.class, serv.getSessionFactory());
                final TrnIfmsBeamsIntegration lObjTrnIfmsBeamsIntegration = new TrnIfmsBeamsIntegration();
                lLngTrnIFMSBeamsIntegrationId = IFMSCommonServiceImpl.getNextSeqNum("TRN_IFMS_BEAMS_INTEGRATION",
                        objectArgs);
                this.logger.info("ID is :::" + lLngTrnIFMSBeamsIntegrationId);
                lObjTrnIfmsBeamsIntegration.setIfmsBeamsIntegrationId(lLngTrnIFMSBeamsIntegrationId);
                lObjTrnIfmsBeamsIntegration.setBillNo(lLongBillGroupId);
                lObjTrnIfmsBeamsIntegration.setPaybillId(paybillId);
                lObjTrnIfmsBeamsIntegration.setBillType(strBillType);
                new BigDecimal(lLongGrossAmt);
                lObjTrnIfmsBeamsIntegration.setBillGrossAmt(new BigDecimal(consolidatedBillMst.getGrossAmt()));
                // lObjTrnIfmsBeamsIntegration.setTotalRecoveryAmt(new
                // BigDecimal(lTotalRecoveryAmt));
                lObjTrnIfmsBeamsIntegration.setBillNetAmt(new BigDecimal(consolidatedBillMst.getNetAmt()));
                lObjTrnIfmsBeamsIntegration.setSchemeCode(String.valueOf(lStrSchemeCode));
                lObjTrnIfmsBeamsIntegration.setDtlheadCode(String.valueOf(lStrDetailHead));
                lObjTrnIfmsBeamsIntegration.setBillCreationDate(lDateBillCreation);
                lObjTrnIfmsBeamsIntegration.setFinYear1(lMapBillDetailsMap.get("FinYear1").toString());
                lObjTrnIfmsBeamsIntegration.setFinYear2(lMapBillDetailsMap.get("FinYear2").toString());
                lObjTrnIfmsBeamsIntegration.setYearMonth(new Integer(consolidatedBillMst.getMonth().intValue()));
                lObjTrnIfmsBeamsIntegration.setNoOfBeneficiary(Integer.valueOf(benCount));
                lObjTrnIfmsBeamsIntegration.setDdoCode(String.valueOf(lStrDdoCode));
                lObjTrnIfmsBeamsIntegration.setAuthNo(authNo);
                lObjTrnIfmsBeamsIntegration.setStatusCode(statusCode);
                if (pdfData != null)
                {
                    lObjTrnIfmsBeamsIntegration.setAuthSlip(new SerialBlob(pdfData));
                }
                if (statusCode != null && statusCode.length() > 0)
                {
                    if ("00".equals(statusCode))
                    {
                        lObjTrnIfmsBeamsIntegration.setBillValidSatus("Y");
                    } else
                    {
                        lObjTrnIfmsBeamsIntegration.setBillValidSatus("N");
                    }
                } else
                {
                    lObjTrnIfmsBeamsIntegration.setBillValidSatus("N");
                }
                lObjTrnIfmsBeamsIntegration.setBeamsBillStatus(null);
                lObjTrnIfmsBeamsIntegration.setLocationCode(this.gStrLocationCode);
                lObjTrnIfmsBeamsIntegration.setDbId(new Integer(this.gLngDBId.toString()));

                lObjTrnIfmsBeamsIntegration.setCreatedDate(this.gDtCurDate);
                lObjTrnIfmsBeamsIntegration.setCreatedUserId(this.gLngUserId);
                lObjTrnIfmsBeamsIntegration.setCreatedPostId(this.gLngPostId);
                // lObjTrnIfmsBeamsIntegration.setBeamsBillType("02");
                final String[] lArrCriteria = new String[4];
                lArrCriteria[0] = "paybillId";
                lArrCriteria[1] = "authNo";
                lArrCriteria[2] = "dtlheadCode";
                lArrCriteria[3] = "ddoCode";
                final Object[] lArrObj = new Object[4];
                lArrObj[0] = lObjTrnIfmsBeamsIntegration.getPaybillId();
                lArrObj[1] = lObjTrnIfmsBeamsIntegration.getAuthNo();
                lArrObj[2] = lObjTrnIfmsBeamsIntegration.getDtlheadCode();
                lArrObj[3] = lObjTrnIfmsBeamsIntegration.getDdoCode();
                this.logger.info(lArrObj.toString());
                final List<TrnIfmsBeamsIntegration> lLstTrnIfmsBeamsIntegration = lObjTreasuryIntegrationDAO
                        .getListByColumnAndValue(lArrCriteria, lArrObj);
                if (lLstTrnIfmsBeamsIntegration != null && lLstTrnIfmsBeamsIntegration.size() >= 1)
                {
                    final TrnIfmsBeamsIntegration objTrnIfmsBeamsIntegration = lLstTrnIfmsBeamsIntegration.get(0);
                    objTrnIfmsBeamsIntegration.setAuthNo(authNo);
                    objTrnIfmsBeamsIntegration.setStatusCode(statusCode);
                    if (pdfData != null)
                    {
                        objTrnIfmsBeamsIntegration.setAuthSlip(new SerialBlob(pdfData));
                    }
                    if (statusCode != null && statusCode.length() > 0)
                    {
                        if ("00".equals(statusCode))
                        {
                            objTrnIfmsBeamsIntegration.setBillValidSatus("Y");
                        } else
                        {
                            objTrnIfmsBeamsIntegration.setBillValidSatus("N");
                        }
                    } else
                    {
                        objTrnIfmsBeamsIntegration.setBillValidSatus("N");
                    }
                    objTrnIfmsBeamsIntegration.setBeamsBillStatus(null);
                    lObjTreasuryIntegrationDAO.update(objTrnIfmsBeamsIntegration);
                } else
                {
                    lObjTreasuryIntegrationDAO.create(lObjTrnIfmsBeamsIntegration);
                }

            }

            /*
             * if ((resultMap != null) && (!(resultMap.isEmpty()))) { authNo =
             * (resultMap.get("authNo") != null) ?
             * (String)resultMap.get("authNo") : null;
             * 
             * statusCode = (resultMap.get("statusCode") != null) ?
             * (String)resultMap.get("statusCode") : null; pdfData =
             * (resultMap.get("pdfData") != null) ?
             * (byte[])resultMap.get("pdfData") : null;
             * this.logger.error("authNo is ::: " + authNo);
             * this.logger.error("statusCode is ::: " + statusCode);
             * this.logger.error("pdfData is ::: " + pdfData);
             * 
             * HrPayrollBeamsMpg hrPayrollBeamsMpg = new HrPayrollBeamsMpg();
             * IdGenerator idGenerator = new IdGenerator(); long id =
             * idGenerator.PKGenerator("HR_PAYROLL_BEAMS_MPG",
             * objectArgs).longValue(); this.logger.error("id ::: " + id);
             * hrPayrollBeamsMpg.setId(id);
             * 
             * hrPayrollBeamsMpg.setPaybillId(Long.valueOf(paybillHeadMpg.
             * getHrPayPaybill()));
             * hrPayrollBeamsMpg.setBillNo(Long.valueOf(lLongBillGroupId));
             * hrPayrollBeamsMpg
             * .setBillNetAmount(Long.valueOf(paybillHeadMpg.getBillNetAmt()));
             * hrPayrollBeamsMpg
             * .setBillGrossAmt(Long.valueOf(paybillHeadMpg.getBillGrossAmt()));
             * hrPayrollBeamsMpg.setAuthNo(authNo);
             * hrPayrollBeamsMpg.setStatusCode(statusCode);
             * 
             * if (pdfData != null) hrPayrollBeamsMpg.setauthSlip(new
             * SerialBlob(pdfData));
             * hrPayrollBeamsMpg.setCreatedBy(Long.valueOf(userId));
             * hrPayrollBeamsMpg.setCreatedDate(new Date());
             * hrPayrollBeamsMpg.setCreatedByPost(Long.valueOf(postId));
             * payrollBEAMSMpgDAO.create(hrPayrollBeamsMpg); }
             */
            final List finalMsg = new ArrayList();
            if (statusCode != null && statusCode.length() > 0 && !"00".equals(statusCode))
            {

                finalMsg.add("Bill is rejected by BEAMS.Reason of rejection,");
                final String[] stCode = statusCode.split("\\|");
                for (int cnt = 0; cnt < stCode.length; ++cnt)
                {
                    final String key = "Status" + stCode[cnt];
                    final String stMsg = String.valueOf(cnt + 1) + ") " + this.integrationBundleConst.getString(key);
                    finalMsg.add(stMsg);
                }
                objectArgs.put("finalMsg", finalMsg);
            }

            objectArgs.put("authNo", authNo);
            objectArgs.put("statusCode", statusCode);

            final CmnLookupMstDAO cmnlookupMstDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv
                    .getSessionFactory());

            if ("00".equals(statusCode))
            {
                consolidatedBillMst.setStatus(5);
                consolidatedBillMst.setUpdatedDate(new Date());
                // OrgUserMst userMst = new OrgUserMst();
                // userMst.setUserId(userId);
                // paybillHeadMpg.setOrgUserMstByUpdatedBy(userMst);
                // OrgPostMst postMst = new OrgPostMst();
                // postMst.setPostId(postId);
                //consolidatedBillMst.setAuthNumber(authNo);
                // paybillHeadMpg.setOrgPostMstByUpdatedByPost(postMst);
                consolidatedBillMstDaoImpl.update(consolidatedBillMst);
            } else if (!"00".equals(statusCode) && resultMap != null && !resultMap.isEmpty())
            {
                consolidatedBillMst.setStatus(2);
                consolidatedBillMstDaoImpl.update(consolidatedBillMst);
            } else
            {
                final String key = "Status19";
                final String stMsg = this.integrationBundleConst.getString(key);
                finalMsg.add(stMsg);
                objectArgs.put("finalMsg", finalMsg);
                objectArgs.put("statusCode", key);

            }
            final Map voToService = new HashMap();
            voToService.put("month", Long.valueOf(consolidatedBillMst.getMonth()));
            voToService.put("year", Long.valueOf(consolidatedBillMst.getYear()));
            voToService.put("flag", "Y");
            voToService.put("schemeCode", lStrSchemeCode);
            objectArgs.put("voToServiceMap", voToService);

            final ResultObject resultBill = serv.executeService("viewConsolidatedBill", objectArgs);
            objectArgs = (Map) resultBill.getResultValue();
            if (!"00".equals(statusCode) && resultMap != null && !resultMap.isEmpty())
            {
                objectArgs.put("statusMsg", "Bill Rejected By BEAMS");
            }
            resultObject.setViewName("searchConsolidateBill");
            resultObject.setResultCode(0);
            resultObject.setResultValue(objectArgs);
        } catch (final Exception e)
        {
            e.printStackTrace();
            this.logger.error("Exception Occurrs in getData Method of BDSIntegrationServiceImpl..Exception is "
                    + e.getMessage());
        }
        return resultObject;
    }

    public ResultObject getAuthSlip(final Map objectArgs)
    {
        final ResultObject resultObject = new ResultObject(0);
        try
        {
            final HttpServletRequest request = (HttpServletRequest) objectArgs.get("requestObj");
            final ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
            final HttpServletResponse response = (HttpServletResponse) objectArgs.get("responseObj");
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();

            final PayrollBEAMSMpgDAO payrollBEAMSMpgDAO = new PayrollBEAMSMpgDAOImpl(TrnIfmsBeamsIntegration.class,
                    serv.getSessionFactory());
            final String authNo = !"".equals(StringUtility.getParameter("authNo", request)) ? StringUtility
                    .getParameter("authNo", request).toString() : "";
            this.logger.error("authNo is ::" + authNo);

            final TrnIfmsBeamsIntegration hrPayrollBeamsMpg = payrollBEAMSMpgDAO.getPayBillAuthSlipDtls(authNo);
            byte[] lBytes = (byte[]) null;
            this.logger.error("hrPayrollBeamsMpg is ::; " + hrPayrollBeamsMpg);
            if (hrPayrollBeamsMpg != null)
            {
                final Blob blob = hrPayrollBeamsMpg.getAuthSlip();
                this.logger.error("blob is ::; " + blob);
                final int blobLength = (int) blob.length();
                this.logger.error("blobLength is ::; " + blobLength);
                lBytes = blob.getBytes(1, blobLength);
                this.logger.error("lBytes is ::; " + lBytes.length);
            }
            baos.write(lBytes);

            response.setContentLength(lBytes.length);
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "inline;filename=authSlip.pdf");
            response.getOutputStream().write(lBytes);
            response.getOutputStream().flush();
            response.getOutputStream().close();

            resultObject.setResultValue(objectArgs);
            resultObject.setResultCode(0);
            resultObject.setViewName("authorizationSlip");
        } catch (final Exception e)
        {
            e.printStackTrace();
            this.logger.error("Exception Occurrs in getData Method of BDSIntegrationServiceImpl..Exception is "
                    + e.getMessage());
        }
        return resultObject;
    }

    /*
     * public ResultObject getBeamsAuthSlip(Map objectArgs) {
     * 
     * ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
     * HttpServletRequest request = (HttpServletRequest)
     * objectArgs.get("requestObj"); ServiceLocator serv = (ServiceLocator)
     * objectArgs.get("serviceLocator"); HttpServletResponse response =
     * (HttpServletResponse) objectArgs.get("responseObj");
     * ByteArrayOutputStream baos = new ByteArrayOutputStream();
     * TreasuryIntegrationDAO lObjTreasuryIntegrationDAO = null;
     * List<TrnIfmsBeamsIntegration> lLstTrnIfmsBeamsIntegration = null;
     * TrnIfmsBeamsIntegration lObjTrnIfmsBeamsIntegration = null; try {
     * lObjTreasuryIntegrationDAO = new
     * TreasuryIntegrationDAOImpl(TrnIfmsBeamsIntegration.class,
     * serv.getSessionFactory()); String authNo =
     * !"".equals(StringUtility.getParameter("authNo", request)) ?
     * StringUtility.getParameter("authNo", request).toString() : "";
     * gLogger.info("authNo is ::" + authNo);
     * 
     * lLstTrnIfmsBeamsIntegration =
     * lObjTreasuryIntegrationDAO.getListByColumnAndValue("authNo", authNo);
     * byte[] lBytes = null; if (lLstTrnIfmsBeamsIntegration != null &&
     * lLstTrnIfmsBeamsIntegration.size() > 0) { lObjTrnIfmsBeamsIntegration =
     * lLstTrnIfmsBeamsIntegration.get(0); Blob blob =
     * lObjTrnIfmsBeamsIntegration.getAuthSlip(); gLogger.info("blob is ::; " +
     * blob); int blobLength = (int) blob.length();
     * gLogger.info("blobLength is ::; " + blobLength); lBytes =
     * blob.getBytes(1, blobLength); gLogger.info("lBytes is ::; " +
     * lBytes.length); } baos.write(lBytes);
     * 
     * // // Start code for Display Download Pdf Option in your browser
     * response.setContentLength(lBytes.length);
     * response.setContentType("application/pdf");
     * response.addHeader("Content-Disposition",
     * "inline;filename=authSlip.pdf");
     * response.getOutputStream().write(lBytes);
     * response.getOutputStream().flush(); response.getOutputStream().close();
     * 
     * resultObject.setResultValue(objectArgs);
     * resultObject.setResultCode(ErrorConstants.SUCCESS);
     * resultObject.setViewName("authorizationSlip");
     * 
     * // //End Code For Display AuthSlip Pdf in New Jsp } catch (Exception e) {
     * // e.printStackTrace();gLogger.error(
     * "Exception Occurrs in getData Method of DisplayOuterServiceImpl..Exception is "
     * + e.getMessage()); } return resultObject; }
     */
    private String getFinYear(final long month, final long year)
    {
        if (month >= 4 && month <= 12)
        {
            final long nxtYear = year + 1;
            return year + "," + nxtYear;
        } else if (month >= 1 && month < 4)
        {
            final long prevYear = year - 1;
            return prevYear + "," + year;
        }
        return "";
    }

    private void setSessionInfo(final Map inputMap)
    {

        try
        {
            this.request = (HttpServletRequest) inputMap.get("requestObj");
            this.session = this.request.getSession();
            this.serv = (ServiceLocator) inputMap.get("serviceLocator");
            this.gLclLocale = new Locale(SessionHelper.getLocale(this.request));
            this.gStrLocale = SessionHelper.getLocale(this.request);
            this.gLngLangId = SessionHelper.getLangId(inputMap);
            this.gLngPostId = SessionHelper.getPostId(inputMap);
            this.gStrPostId = this.gLngPostId.toString();
            this.gLngUserId = SessionHelper.getUserId(inputMap);
            this.gStrUserId = this.gLngUserId.toString();
            this.gStrLocationCode = SessionHelper.getLocationCode(inputMap);
            this.gLngDBId = SessionHelper.getDbId(inputMap);
            this.gDtCurDate = SessionHelper.getCurDate();
            this.gDtCurrDt = SessionHelper.getCurDate();
        } catch (final Exception e)
        {

        }

    }
}
