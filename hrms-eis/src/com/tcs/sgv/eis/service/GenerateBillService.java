package com.tcs.sgv.eis.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tcs.sgv.allowance.service.SalaryRules;
import com.tcs.sgv.common.dao.CmnDatabaseMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLocationMstDaoImpl;
import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.utils.DBUtility;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.common.valueobject.CmnDatabaseMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.MstScheme;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.common.valueobject.SgvcFinYearMst;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.dcps.service.OfflineContriServiceImpl;
import com.tcs.sgv.dcps.service.OfflineContriVOGenerator;
import com.tcs.sgv.dcps.valueobject.MstDcpsBillGroup;
import com.tcs.sgv.eis.dao.BillHeadMpgDAO;
import com.tcs.sgv.eis.dao.BillHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.EmpLeaveDAOImpl;
import com.tcs.sgv.eis.dao.HrEmpTraMpgDaoImpl;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.PaybillHeadMpgDAOImpl;
import com.tcs.sgv.eis.util.GenerateBillServiceHelper;
import com.tcs.sgv.eis.valueobject.EmpPaybillVO;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrEmpTraMpg;
import com.tcs.sgv.eis.valueobject.HrEmpTraMpgHst;
import com.tcs.sgv.eis.valueobject.HrLeaveEmpDtls;
import com.tcs.sgv.eis.valueobject.HrPayBillHeadCustomVO;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayPaybillLoanDtls;
import com.tcs.sgv.eis.valueobject.HrPayPayslipNonGovt;
import com.tcs.sgv.eis.valueobject.HrPaybillTrnLog;
import com.tcs.sgv.eis.valueobject.PaybillEmpCompMpg;
import com.tcs.sgv.eis.valueobject.PaybillHeadMpg;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;

@SuppressWarnings( { "unchecked", "deprecation" })
public class GenerateBillService extends ServiceImpl
{

    private final transient Log logger = LogFactory.getLog(getClass());
    private final transient ResourceBundle constantsBundle = ResourceBundle.getBundle("resources.eis.eis_Constants");
    private final transient ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
    long billTypeId = Long.parseLong(resourceBundle.getString("paybillTypeId"));
    long multipleMonthBillTypeId = Long.parseLong(resourceBundle.getString("multipleMonthSupplBill"));
    long gradeCode4 = Long.parseLong(constantsBundle.getString("GradeCode4"));
    long gradeCode3 = Long.parseLong(constantsBundle.getString("GradeCode3"));
    long permenantPostType = Long.parseLong(resourceBundle.getString("PermanentPost"));
    long temporaryPostType = Long.parseLong(resourceBundle.getString("TemporaryPost"));
    final static boolean NON_GAZETTED_BILL = false;

    // Added by Paurav for Paybill Group Id
    long paybillGrpId = 0;
	//private long ;

    // Ended by Paurav

    public ResultObject generatePaybill(Map objectArgs)
    {
        // added by Ankit Bhatt
        logger.info("GenerateBillService start time " + System.currentTimeMillis());

        // if counter=0,mean it is the first record inserting in Pay bill table,
        // then it will be incremented.
        // int counter = 0; // used for checking nextSeqNumber of hr_pay_paybill
        // long payBillIdCnt = 0;// by manoj for vacant post issue change
        IdGenerator idGen = new IdGenerator();
        HrPaybillTrnLog hrPaybillTrnLog = new HrPaybillTrnLog();
        hrPaybillTrnLog.setHrPaybillTrnLogId(idGen.PKGenerator("HR_PAYBILL_TRN_LOG", objectArgs));
        hrPaybillTrnLog.setGenerationStartTime(new Date());
        // ended by Ankit Bhatt.

        ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
        ServiceLocator serv = (ServiceLocator) objectArgs.get("serviceLocator");
        // HttpServletRequest request =
        // (HttpServletRequest)objectArgs.get("requestObj");
        // setting session interval
        // request.getSession().setMaxInactiveInterval(-1);
        //

        PayBillDAOImpl payDao = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());// change
        // by manoj for vacant post issue added by Ankit Bhatt.

        Map loginMap = (Map) objectArgs.get("baseLoginMap");
        Session currSession = (Session) objectArgs.get("currentSession");
        Boolean fromPayBillScheduler = objectArgs.get("fromPayBillScheduler") != null ? (Boolean) objectArgs
                .get("fromPayBillScheduler") : false;

        if (fromPayBillScheduler)
        {
            logger.error("GenerateBillService start time from scheduler " + System.currentTimeMillis());
            logger.error("GenerateBillService paybillNo from scheduler " + objectArgs.get("paybillNo"));

        } else
        {
            logger.error("GenerateBillService start time roshan " + System.currentTimeMillis());
        }
        // ended.
        try
        {
            long psrNo = 0;
            // OrgPostMst orgPostMst = new OrgPostMst();
            logger.error("GenerateBillService start time roshan1");
            long userId = StringUtility.convertToLong(loginMap.get("userId").toString());
            OrgUserMstDaoImpl orgUserMstDaoImpl = new OrgUserMstDaoImpl(OrgUserMst.class, serv.getSessionFactory());
            OrgUserMst orgUserMst = orgUserMstDaoImpl.read(userId);

            long dbId = StringUtility.convertToLong(loginMap.get("dbId").toString());
            CmnDatabaseMstDaoImpl cmnDatabaseMstDaoImpl = new CmnDatabaseMstDaoImpl(CmnDatabaseMst.class, serv
                    .getSessionFactory());
            CmnDatabaseMst cmnDatabaseMst = cmnDatabaseMstDaoImpl.read(dbId);

            long locId = StringUtility.convertToLong(loginMap.get("locationId").toString());
            CmnLocationMstDaoImpl cmnLocationMstDaoImpl = new CmnLocationMstDaoImpl(CmnLocationMst.class, serv
                    .getSessionFactory());
            CmnLocationMst cmnLocationMst = cmnLocationMstDaoImpl.read(locId);

            long langId = StringUtility.convertToLong(loginMap.get("langId").toString());
            CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class, serv
                    .getSessionFactory());
            CmnLanguageMst cmnLanguageMst = cmnLanguageMstDaoImpl.read(langId);
            // Added by Ankit Bhatt.
            int monthGiven = objectArgs.get("month") != null ? Integer.parseInt(objectArgs.get("month").toString())
                    : -1;
            int yearGiven = objectArgs.get("year") != null ? Integer.parseInt(objectArgs.get("year").toString()) : -1;
            // Added By Mrugesh
            PaybillHeadMpg paybillheadmpgVO = new PaybillHeadMpg();

            // List dcpsContrilist = new ArrayList<String>();
            List dcpsContrilist = null;
            // List dcpslist = new ArrayList();
            // List chk = new ArrayList();
            // long regFlag = 0;
            StringBuffer empIdsStr = new StringBuffer();
            StringBuffer postIdsStr = new StringBuffer();
            StringBuffer orgEmpIdsStr = new StringBuffer();

            if (objectArgs.get("paybillHeadMpgVO") != null)
            {
                paybillheadmpgVO = (PaybillHeadMpg) objectArgs.get("paybillHeadMpgVO");
            }
            // Ended
            if (monthGiven == -1 && yearGiven == -1)
            {
                Date DBDate = DBUtility.getCurrentDateFromDB();
                monthGiven = DBDate.getMonth() + 1;
                yearGiven = DBDate.getYear() + 1900;
                logger.info("DB Month is " + monthGiven + "\nCurrent year is " + yearGiven);
                DBDate = null;
            }

            // by manoj for retirement date issue

            // added by ravysh for multiple month supplementary bill
            String billTypeCmb = (String) objectArgs.get("billTypeCmb");
            String cmbbillTypeCmb=billTypeCmb;
			logger.info("inside the cmbbillTypeCmb" +cmbbillTypeCmb);
            int from_Month = objectArgs.get("from_Month") != null ? Integer.parseInt(objectArgs.get("from_Month")
                    .toString()) : -1;
            int from_Year = objectArgs.get("from_Year") != null ? Integer.parseInt(objectArgs.get("from_Year")
                    .toString()) : -1;
            // ended by ravysh

            // String deptNo = (String)
            // (objectArgs.get("deptNo")!=null?objectArgs.get("deptNo").toString():"");
            String demandNo = (String) objectArgs.get("demandNo");
            String mjrHead = (String) objectArgs.get("mjrHead");
            String subMjrHead = (String) objectArgs.get("subMjrHead");
            String mnrHead = (String) objectArgs.get("mnrHead");
            String subHead = (String) objectArgs.get("subHead");
            // String dtlHead = (String) objectArgs.get("dtlHead");
            // String strGradeIds = (String) objectArgs.get("gradeId1");
            logger.info("Head Details are :- demandNo = " + demandNo + " Major Head is = " + mjrHead
                    + " Sub Major Head is = " + subMjrHead + " Minor Head is = " + mnrHead + " Sub Head is = "
                    + subHead);

            Calendar cal2 = Calendar.getInstance();
            cal2.set(Calendar.YEAR, yearGiven);
            cal2.set(Calendar.MONTH, monthGiven - 1);

            java.util.Date finYrDate = cal2.getTime();
            cal2 = null;

            Calendar calSupplBill = Calendar.getInstance();
            SgvcFinYearMst finYrMst = payDao.getFinYrInfo(finYrDate, langId);

            if (fromPayBillScheduler)
            {
                finYrMst = payDao.getFinYrInfoSchdl(finYrDate, langId);
            }

            finYrDate = null;

            // GenericDaoHibernateImpl genDAO = new
            // GenericDaoHibernateImpl(SgvaBudsubhdMst.class);
            // genDAO.setSessionFactory(serv.getSessionFactory());
            logger.info("B4 going to execute values are " + cmnLanguageMst.getLangName() + " "
                    + finYrMst.getFinYearId());

            // Added By Urvin Shah.
            long billNo = 0; // PK of MstDcpsBillGroup.billGroupId
            MstDcpsBillGroup billHeadMpg = new MstDcpsBillGroup();
            if (objectArgs.get("paybillNo") != null)
            {
                billNo = Long.parseLong(objectArgs.get("paybillNo").toString());
                logger.info("Bill No is:-" + billNo);
                BillHeadMpgDAO billHeadMpgDAO = new BillHeadMpgDAOImpl(MstDcpsBillGroup.class, serv.getSessionFactory());
                billHeadMpg = (MstDcpsBillGroup) billHeadMpgDAO.read(billNo);
                billHeadMpgDAO = null;
            }

            /*
             * PayrollCommonDAO commonDAO = new
             * PayrollCommonDAO(serv.getSessionFactory()); SgvcFinYearMst
             * sgvcFinYearMst = commonDAO.getFinYrInfo(new Date(),langId);
             */
            // SgvcFinYearMst sgvcFinYearMst = finYrMst;
            // List<EmpPaybillVO> generateEmpList = new
            // ArrayList<EmpPaybillVO>();
            List<EmpPaybillVO> generateEmpList = null;

            List<Long> vacantPostIdList = null;
            // List<Long> vacantPostIdList = new ArrayList<Long>();

            objectArgs.put("locId", locId);
            objectArgs.put("billNo", billNo); // PK of
                                              // MstDcpsBillGroup.billGroupId                                                                                
            objectArgs.put("month", monthGiven);
            objectArgs.put("year", yearGiven);
            objectArgs.put("cmnLanguageMst", cmnLanguageMst);
            logger.info("Fetching Employee List start time " + System.currentTimeMillis());
            Map<Object, Object> eligibleEmpMap = new GenerateBillServiceHelper().getEligibleEmployeesByBillNo(objectArgs);
            logger.info("Fetching Employee List End time " + System.currentTimeMillis());
            generateEmpList = (List) eligibleEmpMap.get("generateEmpList");
            vacantPostIdList = (List<Long>) eligibleEmpMap.get("vacantPostIdList");

            eligibleEmpMap = null;

            OtherDetailDAOImpl otherDetailDAOImpl = new OtherDetailDAOImpl(HrEisOtherDtls.class, serv
                    .getSessionFactory());
            // List<Long> eligiblePosts = new ArrayList<Long>();
            // List<Long> eligibleNonVacantPostIdList = new ArrayList<Long>();
            // Map<Long,EmpPaybillVO> hrEisOtherDtlsMap = new
            // HashMap<Long,EmpPaybillVO>();
            Map<Long, EmpPaybillVO> empPaybillVOMap = new HashMap<Long, EmpPaybillVO>();

            StringBuffer dcpsEmpIds = new StringBuffer();
            List dcpsEmpList = new ArrayList();
            List dcpsEmpListNew = new ArrayList();

            Calendar calGivenStart = Calendar.getInstance();
            calGivenStart.set(Calendar.YEAR, yearGiven);
            calGivenStart.set(Calendar.MONTH, (monthGiven - 1));

            calGivenStart.set(Calendar.DAY_OF_MONTH, 1);
            Date givenDateStart = calGivenStart.getTime();
            calGivenStart = null;

            Calendar calGivenEnd = Calendar.getInstance();
            calGivenEnd.set(Calendar.YEAR, yearGiven);
            calGivenEnd.set(Calendar.MONTH, (monthGiven - 1));
            int maxDay = calGivenEnd.getActualMaximum(Calendar.DAY_OF_MONTH);
            calGivenEnd.set(Calendar.DAY_OF_MONTH, maxDay);
            Date givenDateEnd = calGivenEnd.getTime();
            calGivenEnd = null;

            logger.info("===> givenDateStart :: " + givenDateStart + "::givenDateEnd :: " + givenDateEnd);

            if (generateEmpList != null && !generateEmpList.isEmpty())
            {
                logger.error("I am not empty roshan1");

                EmpPaybillVO tempObj = null;
                for (Iterator<EmpPaybillVO> userIDIt = generateEmpList.iterator(); userIDIt.hasNext();)
                {
                    tempObj = (EmpPaybillVO) userIDIt.next();
                    long eisEmpId = tempObj.getEisEmpId();
                    empIdsStr.append(eisEmpId);
                    empIdsStr.append(',');

                    long postId = tempObj.getPostId();
                    postIdsStr.append(postId);
                    postIdsStr.append(',');

                    long orgEmpId = tempObj.getOrgEmpId();
                    orgEmpIdsStr.append(orgEmpId);
                    orgEmpIdsStr.append(',');

                    String DCPSFlag = null;
                    DCPSFlag = tempObj.getDcpsOrGPF();
                    logger.error("GenerateBillService start time roshan9" + DCPSFlag);

                    if (DCPSFlag.equalsIgnoreCase("Y"))
                    {
                        // EmpCompMpgDAOImpl empCompoMpg = new
                        // EmpCompMpgDAOImpl(HrEisEmpCompMpg.class,serv.getSessionFactory());
                        dcpsEmpIds.append(tempObj.getOrgEmpId());
                        dcpsEmpIds.append(',');
                        logger
                                .info("===> (tempObj.getEmpDOJ().after(givenDateStart) ) && (tempObj.getEmpDOJ().before(givenDateEnd))  :;"
                                        + ((tempObj.getEmpDOJ().after(givenDateStart)) && (tempObj.getEmpDOJ()
                                                .before(givenDateEnd))));
                        // if((tempObj.getEmpDOJ().after(givenDateStart) ) &&
                        // (tempObj.getEmpDOJ().before(givenDateEnd)))
                        if ((tempObj.getEmpDOJ().after(givenDateStart)) && (tempObj.getEmpDOJ().before(givenDateEnd)))
                        {
                            logger.info("===> tempObj.getOrgEmpId() :;" + tempObj.getOrgEmpId());
                            dcpsEmpListNew.add(tempObj.getOrgEmpId());
                        }
                        dcpsEmpList.add(tempObj.getOrgEmpId());
                    }

                    // logger.info("chk size after checking null::::;"+chk.size());

                    /*
                     * if(!eligiblePosts.contains(tempObj.getPostId()))
                     * eligiblePosts.add(tempObj.getPostId());
                     * if(!eligibleNonVacantPostIdList
                     * .contains(tempObj.getPostId()))
                     * eligibleNonVacantPostIdList.add(tempObj.getPostId());
                     * if(!hrEisOtherDtlsMap.containsKey(tempObj.getPostId()))
                     * hrEisOtherDtlsMap.put(tempObj.getPostId(), tempObj);
                     */
                    if (!empPaybillVOMap.containsKey(tempObj.getEisEmpId()))
                    {
                        empPaybillVOMap.put(tempObj.getEisEmpId(), tempObj);
                    }
                    tempObj = null;
                }

                logger.info("finYrMst.getFinYearId()::::::::::::: " + finYrMst.getFinYearId());
                logger.info("monthGiven::::::::::::: " + monthGiven);
                String dcpsEmpIdStr = null;
                if (dcpsEmpIds != null && dcpsEmpIds.length() > 2)
                {
                    dcpsEmpIdStr = dcpsEmpIds.substring(0, dcpsEmpIds.length() - 1);
                }
                /*
                 * if (dcpsEmpIdStr != null) { dcpsContrilist =
                 * otherDetailDAOImpl.getDCPSData(dcpsEmpIdStr, monthGiven,
                 * finYrMst.getFinYearId()); }
                 */
            }

            /*
             * if(vacantPostIdList!=null && vacantPostIdList.size() >0) for(long
             * vacantPostId : vacantPostIdList) {
             * eligiblePosts.add(vacantPostId); }
             */

            logger.info("size of vacant post id list is " + vacantPostIdList.size());
            logger.info("vacant post id list is " + vacantPostIdList);
            logger.info("size of hrEisOtherDtls is " + generateEmpList.size());
            logger.info("Given month and year for generating payroll is " + monthGiven + "--" + yearGiven);
            // logger.info("Eligible post size is " + eligiblePosts.size());
            // logger.info("Eligible posts " + eligiblePosts);
            // logger.info("Other Details Map size is " +
            // hrEisOtherDtlsMap.size());
            // logger.info("Other Details Map  " + hrEisOtherDtlsMap);
            // ended by Ankit Bhatt

            long paybillGenerated = 0;
            // added by Ankit Bhatt for setting bill gross and net amount
            long billGrossAmt = 0;
            long billNetAmt = 0;
            // ended
            String allowPaybillGeneration =null;
			String zeroAmount =null;
			String negativeAmount =null;
			String nullAccount =null; 
			String empNameStr= null;
			String positiveAmount="false";
			//nill bill code stops
			//ifsc code validation
			String ifscCodeAbsent = null;
			String ifscCodeInValid = null;
			String negativeNetAfterNGR = null;
			String GpfCodeAbsent = null;
			//added by Saurabh S
		    //long noOfEmp = 0;
            //ended by Saurabh s
            Long dcpsVoucherStatus = 0l;
            
        	String flag=null;

            if ((generateEmpList == null || generateEmpList.isEmpty())
                    && (vacantPostIdList == null || vacantPostIdList.isEmpty()))
            {
                logger.info("no emp found. paybill already generated");
                resultObject.setResultCode(ErrorConstants.SUCCESS);
                objectArgs.put("status", "2");
                objectArgs.put("month", monthGiven);
                objectArgs.put("year", yearGiven);
                objectArgs.put("messageForPaybillSch", "no emp found. paybill already generated");
                resultObject.setResultValue(objectArgs);
                // resultObject.setViewName("payroll");Commented By Javed
                ResultObject resultBill = serv.executeService("ViewTokenNumber", objectArgs);
                objectArgs = (Map) resultBill.getResultValue();

                resultObject.setViewName("ViewTokenNumber");
                paybillGenerated = 0;
                objectArgs.put("paybillGenerated", "0");
            }

            else
            {
                // for time management
                double start, prev, curr;
                start = prev = System.currentTimeMillis();
                // end of time mgt
                long postId = StringUtility.convertToLong(loginMap.get("primaryPostId").toString());
                OrgPostMstDaoImpl orgPostMstDaoImpl = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
                OrgPostMst orgPostMst = orgPostMstDaoImpl.read(postId);
                Date sysdate = new Date();

                Calendar calc = Calendar.getInstance();
                calc.set(Calendar.MONTH, monthGiven - 1);
                calc.set(Calendar.YEAR, yearGiven);
                java.util.Date approvalDate = calc.getTime();
                logger.info("the approval date is " + approvalDate);
                calc = null;

                long dcpsEmpListSize = 0;

                long dcpsEmpListNewSize = dcpsEmpListNew.size();
                dcpsEmpListSize = dcpsEmpList.size();

                logger.info("the approval dcpsEmpListNewSize " + dcpsEmpListNewSize);
                logger.info("the approval dcpsEmpListSize " + dcpsEmpListSize);
                long mstDcpsEmpList = dcpsEmpListSize - dcpsEmpListNewSize;
                logger.info("the approval after deduct mstDcpsEmpList " + mstDcpsEmpList);
                // logger.info("the approval after deduct dcpsContrilist " +
                // dcpsContrilist.size());

                // logger.info("the approval condition " +
                // (dcpsContrilist.size()!=mstDcpsEmpList));

                boolean isDCPSApproved = true;

                if (!fromPayBillScheduler)
                {
                    if (mstDcpsEmpList > 0)
                        if (dcpsContrilist != null && dcpsContrilist.size() != mstDcpsEmpList)
                        {
                            // isDCPSApproved = false;
                        }
                }
                // Integration with DCPS started by manish
                if (fromPayBillScheduler)
                {
                    String User = "DDOAsst";
                    String Use = "ViewAll";
                    OfflineContriServiceImpl contriServiceImpl = new OfflineContriServiceImpl();
                    objectArgs.put("cmbBillGroup", billNo);
                    objectArgs.put("cmbYear", finYrMst.getFinYearId());
                    objectArgs.put("cmbMonth", monthGiven);
                    objectArgs.put("Type", "Online");
                    objectArgs.put("User", User);
                    objectArgs.put("Use", Use);
                    objectArgs.put("schemeCodeForBGInBillSer", billHeadMpg.getDcpsDdoSchemeCode());
                    // System.out.println("Scheme code in Generate Bill Service-->"+billHeadMpg.getDcpsDdoSchemeCode());
                    objectArgs.put("cmbDDOCode", billHeadMpg.getDcpsDdoCode().trim());
                    objectArgs.put("fromPayBillScheduler", fromPayBillScheduler);

                    Session hibSessionForDcps = serv.getSessionFactory().openSession();
                    Transaction transactionForDcps = hibSessionForDcps.getTransaction();
                    transactionForDcps.begin();
                    objectArgs.put("currentSessionForDcps", hibSessionForDcps);
                    objectArgs.put("wfOfflineSession", hibSessionForDcps);

                    ResultObject resultObjectOne = contriServiceImpl.loadOfflineDCPSFormSchdlr(objectArgs);
                    Map objectArgsOne = (HashMap) resultObjectOne.getResultValue();

                    resultObjectOne = null;

                    objectArgsOne.put("hdnCounter", objectArgsOne.get("empList") != null ? ((ArrayList) objectArgsOne
                            .get("empList")).size() : 0);

                    if (objectArgsOne.get("dcpsVoucherStatus") != null)
                    {
                        dcpsVoucherStatus = Long.valueOf(objectArgsOne.get("dcpsVoucherStatus").toString().trim());
                    }

                    logger.info("dcpsVoucherStatus......" + dcpsVoucherStatus);
                    // commented by vaibhav tyagi
                    if (!(dcpsVoucherStatus == 1 || dcpsVoucherStatus == 3 || dcpsVoucherStatus == 0))
                    {
                        // isDCPSApproved = false;
                    }

                    if (objectArgsOne.get("empList") != null && ((ArrayList) objectArgsOne.get("empList")).size() != 0)
                    {
                        OfflineContriVOGenerator offlineContriVOGenerator = new OfflineContriVOGenerator();

                        ResultObject resultObjectTwo = offlineContriVOGenerator
                                .generateContriVOListSchdlr(objectArgsOne);
                        Map objectArgsTwo = (HashMap) resultObjectTwo.getResultValue();

                        resultObjectTwo = null;

                        OfflineContriServiceImpl offilenContriService = new OfflineContriServiceImpl();

                        // ResultObject resultObjectThree =
                        // offilenContriService.saveContributionsSchdlr(objectArgsTwo);
                        // Map objectArgsThree
                        // =(HashMap)resultObjectThree.getResultValue();

                        objectArgsTwo = null;
                        // resultObjectThree = null;
                        hibSessionForDcps.getTransaction().commit();
                        hibSessionForDcps.close();
                        offlineContriVOGenerator = null;
                        offilenContriService = null;

                    } else
                    {
                        hibSessionForDcps.getTransaction().rollback();
                        hibSessionForDcps.close();
                    }
                    // isDCPSApproved = true;
                    /*
                     * ResultObject resultObjectFour =
                     * offilenContriService.FwdContriToDDOSchdlr
                     * (objectArgsThree); Map objectArgsFour
                     * =(HashMap)resultObjectFour.getResultValue();
                     * 
                     * 
                     * ResultObject resultObjectFive =
                     * offilenContriService.FwdContriToTOSchdlr(objectArgsFour);
                     * Map objectArgsFive
                     * =(HashMap)resultObjectFive.getResultValue();
                     */
                    contriServiceImpl = null;
                }
                // ebded by manish
                // if (isDCPSApproved)
                // Added to Check DCPS aprvd frm treasury
                Boolean lBLFlagPrvsContri = true;
                /*
                 * if (dcpsEmpIdStr != null) { isDCPSApproved=
                 * otherDetailDAOImpl.ChckContriAtTreasury( monthGiven - 1,
                 * finYrMst.getFinYearId(),billNo); }
                 */

                int mnth = monthGiven - 1;
                long yr = finYrMst.getFinYearId();
                if (mnth == 3)
                {
                    yr = yr - 1;
                }
                if (mnth == 0)
                {
                    mnth = 12;
                }

                lBLFlagPrvsContri = otherDetailDAOImpl.ChckContriAtTreasury(mnth, yr, billNo);

                // if (isDCPSApproved)
                if (lBLFlagPrvsContri)
                {
                	/*
					 Code To Generate Multiple PK in single hit By Amish
					 */
					int lIntTotalPksHrPayPaybill = vacantPostIdList.size()+generateEmpList.size();
					objectArgs.put("counter", lIntTotalPksHrPayPaybill);
					Long pkSeqHrPayPaybill = idGen.PKGenerator("HR_PAY_PAYBILL", objectArgs);
					objectArgs.remove("counter");
					objectArgs.put("pkSeqHrPayPaybill", pkSeqHrPayPaybill);
					//Code Ends Here By Amish
					logger.info("Trying to generate paybill for " + generateEmpList.size() + " employees ");

					// added by ravysh
					int paybill_Month = monthGiven;
					int paybill_Year = yearGiven;
					if (billTypeCmb.equals("multiplemonthpaybill"))
					{

						paybill_Month = from_Month;
						paybill_Year = from_Year;
					}

					do
					{
						//started  by manish 
						//boolean generatePaybillFlag=true;
						/*List  statusList= payDao.getChangedRecords(monthGiven, yearGiven, -1, locId);
							if(statusList != null && statusList.size()>0)
							{
								generatePaybillFlag = false;
							}*/
						//ended by manish 
						//temporary commented, once dump logic gets perfect, needs to be uncommented
						/*
							if(billTypeCmb.equalsIgnoreCase("paybill") && generatePaybillFlag ) {
								logger.info("Bill type is paybill, inside id of GenerateBillService");
								GeneratePaybillService paybillService = new GeneratePaybillService();
								objectArgs.put("from_Month", monthGiven);
								objectArgs.put("from_Year", yearGiven);
								if(objectArgs.containsKey("eligiblePosts"))
									objectArgs.remove("eligiblePosts");
								objectArgs.put("eligiblePosts", eligiblePosts);
								if(objectArgs.containsKey("hrEisOtherDtlsMap"))
									objectArgs.remove("hrEisOtherDtlsMap");
								objectArgs.put("hrEisOtherDtlsMap", hrEisOtherDtlsMap);
								objectArgs.put("eligibleVacantPostIdList", vacantPostIdList);
								objectArgs.put("eligibleNonVacantPostIdList", eligibleNonVacantPostIdList);

								Map resultMap = paybillService.generatePaybill(objectArgs);
								hrEisOtherDtls.clear();
								hrEisOtherDtls = (ArrayList<HrEisOtherDtls>)(resultMap!=null && resultMap.get("hrEisOtherDtls")!=null ?
										resultMap.get("hrEisOtherDtls"):new ArrayList());
								paybillGrpId = resultMap!=null && resultMap.get("paybillGrpId")!=null ?
										Long.valueOf(resultMap.get("paybillGrpId").toString()):0;								
							    logger.info("Size of hrEisOtherDtls after checking changed records " + hrEisOtherDtls.size());
							    logger.info("paybillGrpId " + paybillGrpId);
							    vacantPostIdList = (ArrayList<Long>)(resultMap!=null && resultMap.get("vacantPostIdList")!=null ?
										resultMap.get("vacantPostIdList"):new ArrayList());															   
							}
						 */
						//ended
						objectArgs.put("hrEisOtherDtls", generateEmpList);
						objectArgs.put("empPaybillVOMap", empPaybillVOMap);
						objectArgs.put("month", monthGiven);
						objectArgs.put("year", yearGiven);
						objectArgs.put("empIdsStr", empIdsStr);
						objectArgs.put("postIdsStr", postIdsStr);
						objectArgs.put("orgEmpIdsStr", orgEmpIdsStr);
						objectArgs.put("paybillGrpId", paybillGrpId);
						objectArgs.put("currentFinYr", finYrMst);
						/*if(paybillGrpId!=0)
							 objectArgs.put("paybillGrpId", paybillGrpId);*/
						if (generateEmpList != null && !generateEmpList.isEmpty())
						{
							logger.info("generateBillCalculation start time" + System.currentTimeMillis());
							//Kishan
							objectArgs = new GenerateBillCalculation().generateBillCalculation(objectArgs);

							logger.info("generateBillCalculation End time" + System.currentTimeMillis());
							paybill_Month = objectArgs.get("paybill_Month") != null ? Integer.valueOf(objectArgs.get("paybill_Month").toString()) : monthGiven;
							paybill_Year = objectArgs.get("paybill_Year") != null ? Integer.valueOf(objectArgs.get("paybill_Year").toString()) : yearGiven;
							paybillGrpId = objectArgs.get("paybillGrpId") != null ? Long.valueOf(objectArgs.get("paybillGrpId").toString()) : paybillGrpId;
							paybillGenerated = objectArgs.get("paybillGenerated") != null ? Long.valueOf(objectArgs.get("paybillGenerated").toString()) : 0;
							billGrossAmt = objectArgs.get("billGrossAmt") != null ? Long.valueOf(objectArgs.get("billGrossAmt").toString()) : 0;
							billNetAmt = objectArgs.get("billNetAmt") != null ? Long.valueOf(objectArgs.get("billNetAmt").toString()) : 0;
                            //added by Saurabh S
							//noOfEmp = objectArgs.get("noOfEmp") != null ? Long.valueOf(objectArgs.get("noOfEmp").toString()) : 0;
							//ended by Saurabh s
							//nill bill : start
							allowPaybillGeneration = (objectArgs.get("allowGeneration") != null ? objectArgs.get("allowGeneration").toString() : "true");
							zeroAmount=(objectArgs.get("zeroAmount") != null ? objectArgs.get("zeroAmount").toString() : "false");
							positiveAmount=(objectArgs.get("positiveAmount") != null ? objectArgs.get("positiveAmount").toString() : "false");
							negativeAmount=(objectArgs.get("negativeAmount") != null ? objectArgs.get("negativeAmount").toString() : "false");
							nullAccount=(objectArgs.get("nullAccount") != null ? objectArgs.get("nullAccount").toString() : "false");
							//nullAccount="false";//remove to add null account check

							//ifsc code validation
							ifscCodeAbsent = (objectArgs.get("ifscCodeAbsent") != null ? objectArgs.get("ifscCodeAbsent").toString() : "false");
							ifscCodeInValid = (objectArgs.get("ifscCodeInValid") != null ? objectArgs.get("ifscCodeInValid").toString() : "false");						
							negativeNetAfterNGR = (objectArgs.get("negativeNetAfterNGR") != null ? objectArgs.get("negativeNetAfterNGR").toString() : "false");
							 // added by Tejashree for GPF Subscription validation
							GpfCodeAbsent = (objectArgs.get("GpfCodeAbsent") != null ? objectArgs.get("GpfCodeAbsent").toString() : "false");
							
							
							empNameStr= (objectArgs.get("empName") != null ? objectArgs.get("empName").toString() : "NA");
							logger.info("ALlow Generation is : "+empNameStr);
							logger.info("negativeAmount : "+negativeAmount);
							//uncomment below 2 lines for null accno check
							if(nullAccount.equals("true"))
								allowPaybillGeneration="false";
							logger.info("allowPaybillGeneration : "+allowPaybillGeneration);
							logger.info("cmbbillTypeCmb : "+cmbbillTypeCmb);

							
							/// uncomment below if to add ifsc code validations
							if(nullAccount.equals("false") && ifscCodeAbsent.equals("false") && ifscCodeInValid.equals("false") && GpfCodeAbsent.equals("false")){
							//if(nullAccount.equals("false") ) { /// comment below code to while adding above if
								// 99 - nill bill
								// 02 - paybill 
								if(cmbbillTypeCmb.equals("99") &&  negativeAmount.equals("false"))
									allowPaybillGeneration="true";
								if(cmbbillTypeCmb.equals("99") &&  positiveAmount.equals("true"))
									allowPaybillGeneration="false";
								///added to check net amount of BILL, if it is zero, 
								////then paybill will not be allowed to generata
								if(!cmbbillTypeCmb.equals("99") && billNetAmt== 0l ){
									allowPaybillGeneration = "false";
								}
								if(!cmbbillTypeCmb.equals("99") && billNetAmt> 0l ){
									allowPaybillGeneration = "true";
								}
								if(cmbbillTypeCmb.equals("99") && billNetAmt== 0l ){
									allowPaybillGeneration = "true";
								}
								
								if(negativeNetAfterNGR != null && negativeNetAfterNGR.equals("true")){
									allowPaybillGeneration = "false";
								}
								if(negativeAmount != null && negativeAmount.equals("true")){
									allowPaybillGeneration = "false";
								}
							}
							logger.info("allowPaybillGeneration : "+allowPaybillGeneration);
							//nill bill : end

						}
						else
							paybill_Month++;
					}
					while ((paybill_Month <= monthGiven && paybill_Year == yearGiven) || (paybill_Year < yearGiven)); // added

					// added by ravysh to prevent vacant posts in multiple month
					// supplimentary bill
					logger.info("ALlow Generation is : "+allowPaybillGeneration);
					//nill bill
					if(allowPaybillGeneration.equals("true") ){
						pkSeqHrPayPaybill = Long.valueOf(objectArgs.get("pkSeqHrPayPaybill").toString());
						if (!billTypeCmb.equals("multiplemonthpaybill")  )
						{
							HrPayPaybill hrVacantPaybill = null;
							List<HrPayPaybill> hrPayPaybillVacantList = new ArrayList<HrPayPaybill>();
							if (vacantPostIdList != null && vacantPostIdList.size() > 0)
							{

								logger.info("going to generate paybill for vacant post ");
								for (int vacantCnt = 0; vacantCnt < vacantPostIdList.size(); vacantCnt++)
								{
									hrVacantPaybill = new HrPayPaybill();

									// Also capturing postId
									long PostId = Long.parseLong(vacantPostIdList.get(vacantCnt).toString());
									OrgPostMst PayBillPostMst = orgPostMstDaoImpl.read(PostId);
									hrVacantPaybill.setOrgPostMst(PayBillPostMst);
									psrNo = payDao.getPsrNoFromPostId(PayBillPostMst.getPostId());
									hrVacantPaybill.setPsrNo(psrNo);
									hrVacantPaybill.setEmpLname("z");
									hrVacantPaybill.setCmnDatabaseMst(cmnDatabaseMst);
									hrVacantPaybill.setCmnLocationMst(cmnLocationMst);
									hrVacantPaybill.setCreatedDate(sysdate);

									hrVacantPaybill.setOrgUserMstByCreatedBy(orgUserMst);
									hrVacantPaybill.setOrgPostMstByCreatedByPost(orgPostMst);
									hrVacantPaybill.setHrEisEmpMst(null);
									hrVacantPaybill.setScaleId(0);
									long vacantPayBillId = 0;

									synchronized (idGen)
									{
										//vacantPayBillId = idGen.PKGenerator("HR_PAY_PAYBILL", objectArgs);
										vacantPayBillId = ++pkSeqHrPayPaybill;
									}

									hrVacantPaybill.setId(vacantPayBillId);
									paybillheadmpgVO.setBillCategory(2);
									if (yearGiven != -1 && monthGiven != -1)
									{
										// Modified By Mrugesh
										paybillheadmpgVO.setMonth(monthGiven);
										paybillheadmpgVO.setYear(yearGiven);
										// Ended
									}
									else
									{
										SimpleDateFormat sdf = new SimpleDateFormat("MM");
										Date date = new Date();
										String month = sdf.format(date);

										if (month.charAt(0) == '0')
										{
											month = month.substring(1, month.length());
										}

										sdf = new SimpleDateFormat("yyyy");
										String year = sdf.format(date);
										// Modified By Mrugesh
										paybillheadmpgVO.setMonth(Integer.parseInt(month));
										paybillheadmpgVO.setYear(Integer.parseInt(year));
										// Ended
									}

									if (paybillGrpId == 0)
									{
										paybillGrpId = vacantPayBillId;

									}
									logger.info("paybillGrpId in vacant is " + paybillGrpId);
									hrVacantPaybill.setPaybillGrpId(paybillGrpId);
									hrVacantPaybill.setTrnCounter(Integer.valueOf(1));
									/*if(fromPayBillScheduler)
										currSession.save(hrVacantPaybill);
									else
										payDao.create(hrVacantPaybill);
									 */
									hrPayPaybillVacantList.add(hrVacantPaybill);
									paybillGenerated++;

								}
								objectArgs.put("hrVacantPaybill_Batch", hrPayPaybillVacantList);
							}
						}
						// added by Ankit Bhatt

						logger.info("the paybillGenerated++ " + paybillGenerated);
						logger.info("paybill Grp Id is " + paybillGrpId);
						if (paybillGenerated != 0)
						{
							/*HrPayPaybill hrHeadPayPaybill = new HrPayPaybill();
						hrHeadPayPaybill.setId(paybillGrpId);*/
							// Modified By Mrugesh
							paybillheadmpgVO.setMonth(monthGiven);
							paybillheadmpgVO.setYear(yearGiven);
							// Ended

							// added by ravysh for multiple month suppl bill
							if (billTypeCmb.equals("multiplemonthpaybill"))
							{
								paybillheadmpgVO.setMonth(calSupplBill.get(Calendar.MONTH) + 1);
								paybillheadmpgVO.setYear(calSupplBill.get(Calendar.YEAR));
							}

							// added by Ankit Bhatt for Paybill And Order_Head Mapping
							// Entry
							//long paybill_head_id = 0; // Paybill_ID in
							// "paybill_head_mpg" table.
							long paybill_head_pk = 0;
							// ended by Ankit Bhatt.					
							//paybill_head_id = payBillIdCnt;
							//counter++;

							PaybillHeadMpg payBillHeadMpg = null;
							// Added By Mrugesh
							CmnLookupMstDAOImpl cmnLookupMstDAOImpl = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
							// added by ravysh
							long lSupplBillNo = payDao.getSupplBillNo(monthGiven, yearGiven, billNo, locId);
							if (lSupplBillNo != 0 && lSupplBillNo == billTypeId)
								billTypeId = billTypeId + 2;
							else if (lSupplBillNo != 0 && lSupplBillNo >= billTypeId + 2)
								billTypeId = lSupplBillNo + 1;
							// added by ravysh for multiplemonth suppl. bill
							if (billTypeCmb.equals("multiplemonthpaybill"))
								billTypeId = multipleMonthBillTypeId;

							//temp code to set Type ID = Paybill ID
							billTypeId = Long.parseLong(resourceBundle.getString("paybillTypeId"));
							//ended
							CmnLookupMst cmnLookupMst = cmnLookupMstDAOImpl.read(billTypeId);
							// Ended
							//for (i = 0; i < gradeIds.length; i++) {
							synchronized (idGen)
							{
								paybill_head_pk = idGen.PKGenerator("paybill_head_mpg", objectArgs);
							}
							PaybillHeadMpgDAOImpl paybillHeadMpgDAOImpl = new PaybillHeadMpgDAOImpl(PaybillHeadMpg.class, serv.getSessionFactory());

							//added by manish 		
							List paybillHeadMpgList = payDao.getAllPaybillHeadMpgData(monthGiven, yearGiven, billNo, 4, paybillGrpId);

							if (paybillHeadMpgList != null && paybillHeadMpgList.size() > 0)
							{
								new GeneratePaybillService().updatePaybillHeadMpg(paybillGrpId, serv);
							}
							else
							{
								payBillHeadMpg = new PaybillHeadMpg();

								/*
								 * hrPayPayBill = new HrPayPaybill();
								 * hrPayPayBill.setId(paybill_head_id);
								 * logger.info("HrPayPaybill in save of Mpg " +
								 * hrPayPayBill);
								 */
								payBillHeadMpg.setId(paybill_head_pk);
								//payBillHeadMpg.setHrPayPaybill(hrPayPaybill.getPaybillGrpId());
								payBillHeadMpg.setHrPayPaybill(paybillGrpId);
								MstScheme schemeMst = new MstScheme();
								/*List ddoList=payDao.getDDOCodeByLoggedInPost(postId);
								String ddoCode=null;
								if(ddoList != null && ddoList.size()>0)
								{
									OrgDdoMst orgDdoMst=(OrgDdoMst)ddoList.get(0);
									ddoCode=orgDdoMst.getDdoCode();
								}*/

								String ddoCode = "";
								PayBillDAOImpl payBillDAO = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
								List<OrgDdoMst> ddoCodeList = payBillDAO.getDDOCodeByLoggedInlocId(locId);
								if (ddoCodeList != null)
									logger.info("After query execution for DDO Code " + ddoCodeList.size());

								OrgDdoMst ddoMst = null;
								if (ddoCodeList != null && ddoCodeList.size() > 0)
								{
									ddoMst = ddoCodeList.get(0);
								}

								if (ddoMst != null)
								{
									ddoCode = ddoMst.getDdoCode();
								}
								logger.info("DDO Code is " + ddoCode);
								
								
								//added by poonam for vocational admin office
							
								
								
								
								

								if (billHeadMpg != null)
								{
									String schemeCode = billHeadMpg.getDcpsDdoSchemeCode();
									List<MstScheme> schemeMstList = paybillHeadMpgDAOImpl.getMstScheme(schemeCode, ddoCode);
									if (schemeMstList != null && schemeMstList.size() > 0)
										schemeMst = schemeMstList.get(0);
									//if(schemeMst != null)
									//long schemeId = schemeMst.getSchemeId();

								}
								payBillHeadMpg.setMstScheme(schemeMst);
								payBillHeadMpg.setCmnLocationMst(cmnLocationMst);
								payBillHeadMpg.setCmnDatabaseMst(cmnDatabaseMst);
								payBillHeadMpg.setCreatedDate(new Date());
								payBillHeadMpg.setOrgUserMstByCreatedBy(orgUserMst);
								payBillHeadMpg.setOrgPostMstByCreatedByPost(orgPostMst);
								// Added By Mrugesh
								//payBillHeadMpg.setApproveFlag(0);// Hard-coded

								//edited by shailesh 
								payBillHeadMpg.setApproveFlag(0);// change statement generated//changed 0 from 6(change statement 6 or paybill 0) by roshan sir
								payBillHeadMpg.setMonth(monthGiven);
								payBillHeadMpg.setYear(yearGiven);
								// added by ravysh for multiple month suppl. bill
								if (billTypeCmb.equals("multiplemonthpaybill"))
								{
									payBillHeadMpg.setMonth(calSupplBill.get(Calendar.MONTH) + 1);
									payBillHeadMpg.setYear(calSupplBill.get(Calendar.YEAR));
								}
								payBillHeadMpg.setBillNo(billHeadMpg);// billHeadMpg
								payBillHeadMpg.setBillTypeId(cmnLookupMst);// paybillTypeId

								payBillHeadMpg.setHeadChargable("");
								// Ended by Mrugesh

								//added by Ankit for setting bill net and gross amt
								payBillHeadMpg.setVoucherNumber(0l); //default 0 at time of bill generation
								payBillHeadMpg.setBillGrossAmt(billGrossAmt);
								payBillHeadMpg.setBillNetAmt(billNetAmt);
							//	payBillHeadMpg.setBillCategory(Long.parseLong(cmbbillTypeCmb));
								logger.info("Bill Gross amount is " + billGrossAmt);
								logger.info("Bill net amount is " + billNetAmt);
								long month;
								//ended
								//paybillHeadMpgDAOImpl.create(payBillHeadMpg);
								/*if(fromPayBillScheduler)
									currSession.save(payBillHeadMpg);
								else
									paybillHeadMpgDAOImpl.create(payBillHeadMpg);
								 */
								//added by Saurabh S
								//String.valueOf(number);
								Long noOfEmp =payBillDAO.checkEmpCount(String.valueOf(billNo));
								logger.info("noOfEmp net noOfEmp is " + noOfEmp);
								payBillHeadMpg.setNoOfEmp(noOfEmp);
								
								//payBillHeadMpg.setNoOfEmp(noOfEmp);
								//ended by Saurabh S
								objectArgs.put("payBillHeadMpg_Batch", payBillHeadMpg);

							}

							//ended by manish 
							//}

							// for time management
							curr = System.currentTimeMillis();
							logger.info("the start time is " + start);
							logger.info("the prev time is " + prev);
							logger.info("the curr time is " + curr);
							logger.info("the time taken inside edpList loop " + (curr - start));
							prev = curr;
							// end of time mgt	

							/*//dumping records for next month
							//checking if we found record of next month with approve flag=1
							//then no need to dump records for approve flag=4
							int nextMonth = monthGiven;
							int nextYear = yearGiven;
							if(monthGiven==12) {
								nextMonth=1;
								nextYear++;
							}
							else {
								nextMonth++;
							}
							List<Object[]> nextPaybillRecords = payDao.getPaybillDataByLoc
							(nextMonth, nextYear, locId, billNo,false);
							if(nextPaybillRecords!=null && nextPaybillRecords.size()>0) {
								logger.info("Next month and year is " + nextMonth + " - " + nextYear);
								logger.info("Next month paybill is generated and approved.");
								logger.info("so we will not dump records for next month");
							}
							else {
							logger.info("going to dump records from GenerateBillService");
							Map dumpRecordsParaMap = objectArgs;

							dumpRecordsParaMap.put("month", monthGiven);
							dumpRecordsParaMap.put("year", yearGiven);
							dumpRecordsParaMap.put("billNo", billNo);
							dumpRecordsParaMap.put("approveFlag", 0); //next month record, so approve flag=4
							dumpRecordsParaMap.put("locId", locId);
							Map paybillDumpMap = new GeneratePaybillService().copyPaybillForNextMonth(objectArgs);
							if(paybillDumpMap!=null && paybillDumpMap.size()>0) {
								long nextMonthPaybillGrpId = paybillDumpMap.get("paybillGrpId")!=null?
										Long.valueOf(paybillDumpMap.get("paybillGrpId").toString()):0;
								long nextMonthPaybillHeadId = paybillDumpMap.get("paybillHeadId")!=null?
												Long.valueOf(paybillDumpMap.get("paybillHeadId").toString()):0;
								logger.info("Next month data has been dumped in paybill table with group id " + nextMonthPaybillGrpId);
								logger.info("Next month data has been dumped in paybill head mpg" +
										" table with group id " + nextMonthPaybillHeadI
										d);
							}
							}
							//dump record code end
							 */
						}

						resultObject.setResultCode(ErrorConstants.SUCCESS);
						objectArgs.put("status", "1");
						// added by Ankit Bhatt.

						objectArgs.put("demandNo", demandNo);
						objectArgs.put("mjrHead", mjrHead);
						objectArgs.put("subMjrHead", subMjrHead);
						objectArgs.put("mnrHead", mnrHead);
						objectArgs.put("subHead", subHead);
						// Modified By Mrugesh
						objectArgs.put("month", Math.round(paybillheadmpgVO.getMonth()));
						objectArgs.put("year", Math.round(paybillheadmpgVO.getYear()));
						// Ended
						/* objectArgs.put("requestObj", proxyReq); */
						// ended by Ankit Bhatt.
						// by manoj for exception handling in outer
						objectArgs.put("paybillGenerated", "1");
						// end by manoj for exception handling in outer
						resultObject.setResultValue(objectArgs);

						logger.info("GenerateBillService end time " + System.currentTimeMillis());
						//resultObject.setViewName("payroll");Commented By Javed
						ResultObject resultBill = serv.executeService("ViewTokenNumber", objectArgs);
						objectArgs = (Map) resultBill.getResultValue();

						//edited by shailesh
						resultObject.setViewName("ViewTokenNumber");
						//resultObject.setViewName("viewChangeStatement");

						/**
						 * 
						 * Commit The Codes
						 * 
						 * 
						 */

						//List<HrPayPaybill> payBillVOParentList = new ArrayList<HrPayPaybill>();
						//List<List<HrPayPaybillLoanDtls>> hrPayPaybillLoanParentDtlsList = new ArrayList<List<HrPayPaybillLoanDtls>>();
						//List<List<HrPayPayslipNonGovt>> payslipNonGovtsParentList = new ArrayList<List<HrPayPayslipNonGovt>>();
						List<HrPayPaybill> payBillVOParentList = (ArrayList<HrPayPaybill>) objectArgs.get("payBillVOParentList_Batch");
						List<List<HrPayPaybillLoanDtls>> hrPayPaybillLoanParentDtlsList = (ArrayList<List<HrPayPaybillLoanDtls>>) objectArgs.get("hrPayPaybillLoanParentDtlsList_Batch");
						List<List<HrPayPayslipNonGovt>> payslipNonGovtsParentList = (ArrayList<List<HrPayPayslipNonGovt>>) objectArgs.get("payslipNonGovtsParentList_Batch");
						List<PaybillEmpCompMpg>  paybillEmpCompoMpgList = (ArrayList<PaybillEmpCompMpg>)objectArgs.get("paybillEmpCompoMpgList");

						List<HrPayPaybill> hrPayPaybillList = (ArrayList<HrPayPaybill>) objectArgs.get("hrVacantPaybill_Batch");
						PaybillHeadMpg payBillHeadMpg = (PaybillHeadMpg) objectArgs.get("payBillHeadMpg_Batch");
						logger.info("hrPayPaybillList : " + hrPayPaybillList + " :: hrPayPaybillList.size=" + (hrPayPaybillList != null ? hrPayPaybillList.size() : 0));

						if (!fromPayBillScheduler)
						{

							if (payBillVOParentList != null && !payBillVOParentList.isEmpty())
							{
								for (HrPayPaybill payBillVO : payBillVOParentList)
								{
									logger.info("payBillVO 5 HrPayPaybill");
									logger.info("HrPayPaybill="+payBillVO);
									payDao.create(payBillVO);
									payBillVO=null;
								}
							}

							payBillVOParentList = null;
							// payDao.getSession().flush();

							GenericDaoHibernateImpl paybillLoanDtlsDao = new GenericDaoHibernateImpl(HrPayPaybillLoanDtls.class);
							paybillLoanDtlsDao.setSessionFactory(serv.getSessionFactory());
							if (hrPayPaybillLoanParentDtlsList != null && !hrPayPaybillLoanParentDtlsList.isEmpty())
							{
								for (List<HrPayPaybillLoanDtls> hrPayPaybillLoanDtlsList : hrPayPaybillLoanParentDtlsList)
								{
									if (hrPayPaybillLoanDtlsList != null && !hrPayPaybillLoanDtlsList.isEmpty())
									{
										for (HrPayPaybillLoanDtls dtls : hrPayPaybillLoanDtlsList)
										{
											paybillLoanDtlsDao.getSession().saveOrUpdate(dtls);
										}
									}

								}
							}

							hrPayPaybillLoanParentDtlsList = null;
							// paybillLoanDtlsDao.getSession().flush();

							GenericDaoHibernateImpl nonGovtPayslipDao = new GenericDaoHibernateImpl(HrPayPayslipNonGovt.class);
							nonGovtPayslipDao.setSessionFactory(serv.getSessionFactory());
							if (payslipNonGovtsParentList != null && !payslipNonGovtsParentList.isEmpty())
							{
								for (List<HrPayPayslipNonGovt> payslipNonGovtsList : payslipNonGovtsParentList)
								{
									if (payslipNonGovtsList != null && !payslipNonGovtsList.isEmpty())
									{
										for (HrPayPayslipNonGovt dtl : payslipNonGovtsList)
										{
											nonGovtPayslipDao.create(dtl);
										}
									}
								}
							}
							payslipNonGovtsParentList = null;

							// nonGovtPayslipDao.getSession().flush();
							if (hrPayPaybillList != null && !hrPayPaybillList.isEmpty())
							{
								for (HrPayPaybill hrPayPaybillVacant : hrPayPaybillList)
								{
									logger.info("hrPayPaybillVacant 10 HrPayPaybill");
									payDao.create(hrPayPaybillVacant);
								}
							}

							hrPayPaybillList = null;
							// payDao.getSession().flush();
							//For History of Emp Compo					
							if(paybillEmpCompoMpgList != null && !paybillEmpCompoMpgList.isEmpty()){
								GenericDaoHibernateImpl genDaoForCompoHst = new GenericDaoHibernateImpl(PaybillEmpCompMpg.class);
								genDaoForCompoHst.setSessionFactory(serv.getSessionFactory());
								for(PaybillEmpCompMpg paybillEmpCompMpg : paybillEmpCompoMpgList){
									genDaoForCompoHst.create(paybillEmpCompMpg);
								}
							}
							//History of Emp Compo Ends
							PaybillHeadMpgDAOImpl paybillHeadMpgDAOImpl = new PaybillHeadMpgDAOImpl(PaybillHeadMpg.class, serv.getSessionFactory());
							if (!paybillHeadMpgDAOImpl.checkPaybillGenerated(monthGiven, yearGiven, String.valueOf(billNo))){
								paybillHeadMpgDAOImpl.create(payBillHeadMpg);
								hrPaybillTrnLog.setBillNo(billNo);
								hrPaybillTrnLog.setPaybillHeadMpgId(payBillHeadMpg.getHrPayPaybill());
								hrPaybillTrnLog.setCreatedDate(new Date());
								hrPaybillTrnLog.setCreatedBy(userId);
								hrPaybillTrnLog.setGenerationEndTime(new Date());
								GenericDaoHibernateImpl genDaoForBillLog = new GenericDaoHibernateImpl(HrPaybillTrnLog.class);
								genDaoForBillLog.setSessionFactory(serv.getSessionFactory());
								genDaoForBillLog.create(hrPaybillTrnLog);
							}
							else
								objectArgs.put("status", "99");
							// paybillHeadMpgDAOImpl.getSession().flush();
						}
						else
						{
							if (payBillVOParentList != null && !payBillVOParentList.isEmpty())
							{
								for (HrPayPaybill payBillVO : payBillVOParentList)
								{
									logger.info("payBillVO 15 HrPayPaybill");
									currSession.save(payBillVO);
								}
							}

							payBillVOParentList = null;

							GenericDaoHibernateImpl paybillLoanDtlsDao = new GenericDaoHibernateImpl(HrPayPaybillLoanDtls.class);
							paybillLoanDtlsDao.setSessionFactory(serv.getSessionFactory());
							if (hrPayPaybillLoanParentDtlsList != null && !hrPayPaybillLoanParentDtlsList.isEmpty())
							{
								for (List<HrPayPaybillLoanDtls> hrPayPaybillLoanDtlsList : hrPayPaybillLoanParentDtlsList)
								{
									if (hrPayPaybillLoanDtlsList != null && !hrPayPaybillLoanDtlsList.isEmpty())
									{
										for (HrPayPaybillLoanDtls dtls : hrPayPaybillLoanDtlsList)
										{
											currSession.saveOrUpdate(dtls);
										}
									}
								}
							}
							hrPayPaybillLoanParentDtlsList = null;

							GenericDaoHibernateImpl nonGovtPayslipDao = new GenericDaoHibernateImpl(HrPayPayslipNonGovt.class);
							nonGovtPayslipDao.setSessionFactory(serv.getSessionFactory());
							if (payslipNonGovtsParentList != null && !payslipNonGovtsParentList.isEmpty())
							{
								for (List<HrPayPayslipNonGovt> payslipNonGovtsList : payslipNonGovtsParentList)
								{
									if (payslipNonGovtsList != null && !payslipNonGovtsList.isEmpty())
									{
										for (HrPayPayslipNonGovt dtl : payslipNonGovtsList)
										{
											currSession.save(dtl);
										}
									}
								}
							}

							payslipNonGovtsParentList = null;
							if (hrPayPaybillList != null && !hrPayPaybillList.isEmpty())
							{
								for (HrPayPaybill hrPayPaybillVacant : hrPayPaybillList)
								{
									logger.info("hrPayPaybillVacant 20 HrPayPaybill");
									currSession.save(hrPayPaybillVacant);
								}
							}

							hrPayPaybillList = null;
							//For History of Emp Compo					
							if(paybillEmpCompoMpgList != null && !paybillEmpCompoMpgList.isEmpty()){
								for(PaybillEmpCompMpg paybillEmpCompMpg : paybillEmpCompoMpgList){
									currSession.save(paybillEmpCompMpg);
								}
							}
							//History of Emp Compo Ends
							//PaybillHeadMpgDAOImpl paybillHeadMpgDAOImpl = new PaybillHeadMpgDAOImpl(PaybillHeadMpg.class, serv.getSessionFactory());
							currSession.save(payBillHeadMpg);
						}

						objectArgs.remove("payBillVOParentList_Batch");
						objectArgs.remove("hrPayPaybillLoanParentDtlsList_Batch");
						objectArgs.remove("payslipNonGovtsParentList_Batch");

						objectArgs.remove("hrVacantPaybill_Batch");
						objectArgs.remove("payBillHeadMpg_Batch");

					}
					/**
					 * 
					 * 
					 * End Commit the Codes
					 * 
					 */
					//for nill bill
					else{
						logger.info("Coming into the Service method getPaybillParaPage**********");
						//ServiceLocator serv = (ServiceLocator)objectArgs.get("serviceLocator");
						PayBillDAOImpl payBillDAO = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
						//Map loginDetailsMap = (Map) objectArgs.get("baseLoginMap");
						//long langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
						logger.info("lng id id " + langId);

						CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
						List yearList = lookupDAO.getAllChildrenByLookUpNameAndLang("Year", langId);
						Collections.reverse(yearList);

						//long userId=Long.parseLong(loginDetailsMap.get("userId").toString());
						//OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
						//OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);

						//CmnLocationMstDaoImpl locationDAO = new CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());

						List locationIdList = payBillDAO.getconcernedDept(langId, userId);//locationDAO.getListByColumnAndValue("cmnLanguageMst.langId", langId);

						//List locationList = locationDAO.getListByColumnAndValue("cmnLanguageMst.langId", langId);
						//CmnLocationMst locMst = null;

						//added by Ankit Bhatt on 19th June 2011 for Maha Payroll
						String ddo_code = null;
						//long postId = StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
						List<OrgDdoMst> ddoCodeList = payBillDAO.getDDOCodeByLoggedInlocId(locId);
						if (ddoCodeList != null)
							logger.info("After query execution for DDO Code " + ddoCodeList.size());

						OrgDdoMst ddoMst = null;
						if (ddoCodeList != null && ddoCodeList.size() > 0)
						{
							ddoMst = ddoCodeList.get(0);
						}

						ddoCodeList = null;

						if (ddoMst != null)
						{
							ddo_code = ddoMst.getDdoCode();
						}

						ddoMst = null;
						logger.info("DDO Code is " + ddo_code);
						//ended by Ankit Bhatt
						
						
					
						
						

						//Added by Mrugesh
						ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
						long finYearId = Long.parseLong(resourceBundle.getString("finYearId"));
						//long locId=Long.parseLong(loginDetailsMap.get("locationId").toString());
						List billNoList = new ArrayList();
						if (ddo_code != null)
						{
							billNoList = payBillDAO.getBillNo(finYearId, locId, ddo_code);
						}
						//List arrearList = payBillDAO.getArrearList(locId);
						List arrearList = new ArrayList();
						logger.info("The size of billNoList is---->" + billNoList.size());
						ArrayList billList = new ArrayList();
						for (Iterator itr = billNoList.iterator(); itr.hasNext();)
						{
							HrPayBillHeadCustomVO billNoCustomVO = new HrPayBillHeadCustomVO();
							Object[] row = (Object[]) itr.next();
							String billNo1 = row[1].toString(); //dcpsDdoBillDescription = Bill Number of GAD
							String billHeadId = row[0].toString(); ////dcpsDdoBillGroupId = Bill Value (PK)
							billNoCustomVO.setBillId(billNo1);
							billNoCustomVO.setBillHeadId(Long.parseLong(billHeadId));
							billList.add(billNoCustomVO);
						}

						billNoList = null;
						//Ended by Mrugesh
						/*if(locationList!=null && locationList.size()!=0)
							{
							 for(Iterator it=locationList.iterator();it.hasNext();)
							 {
								 locMst = (CmnLocationMst)it.next();	            
							     logger.info("To Avoid LazyInitialization in getPaybillParaPage" + locMst.getLocationCode() + " / " + locMst.getLocName());           
							 }
							}*/
						java.util.Calendar calendar = java.util.Calendar.getInstance();
						int curYear = calendar.get(java.util.Calendar.YEAR);
						int curMonth = calendar.get(java.util.Calendar.MONTH);
						logger.info("curYear====>" + curYear + "and month===>" + curMonth);
						long locationid = 0;
						for (Iterator iter = locationIdList.iterator(); iter.hasNext();)
						{
							//ArrayList rowList = new ArrayList();
							Object[] row = (Object[]) iter.next();
							locationid = Long.parseLong(row[0].toString());
						}

						locationIdList = null;
						objectArgs.put("locationid", locationid);
						objectArgs.put("yearList", yearList);
						//objectArgs.put("deptList", locationList);
						objectArgs.put("billList", billList);
						objectArgs.put("curYear", curYear);
						objectArgs.put("curMonth", curMonth);
						objectArgs.put("arrearList", arrearList);
						if(negativeAmount.equals("true")){
							String negEmpName= (objectArgs.get("negEmpName") != null ? objectArgs.get("negEmpName").toString() : null);
							objectArgs.put("msg", "Paybill cannot be generated as Net Amount is negative or zero for employee(s) :-  " +negEmpName);
						}
						if(nullAccount.equals("true")){
							String nullEmpName= (objectArgs.get("nullEmpName") != null ? objectArgs.get("nullEmpName").toString() : null);
							objectArgs.put("msg", "Paybill cannot be generated as Account Number is Not Available for employee(s) :-  " +nullEmpName);
						}
						if(cmbbillTypeCmb.equals("99") &&  positiveAmount.equals("true"))		
							objectArgs.put("msg", "Nill Paybill cannot be generated if Net Amount is Greater Then Zero For Any Emlpoyees");

						///lines checks net amont of individaul employees and restrict to generate paybill
						//if(zeroAmount.equals("true") && !cmbbillTypeCmb.equals("99") )
						//objectArgs.put("msg", "Paybill cannot be generated as Net Amount is 0 for employee(s) :-  " +empNameStr);

						//to restrict 0 net amount paybill generation
						if(billNetAmt == 0l && !cmbbillTypeCmb.equals("99")){
							objectArgs.put("msg", "Paybill cannot be generated as Net Amount of paybill is zero.");
						}
						
						if(ifscCodeAbsent.equals("true")){
							String ifscPresentEmpName = (objectArgs.get("ifscAbsentEmpName") != null ? objectArgs.get("ifscAbsentEmpName").toString() : null);
							objectArgs.put("msg", "Paybill cannot be generated as IFSC code is not present for employees :- "+ifscPresentEmpName);
							ifscPresentEmpName = null;
						}
						if(ifscCodeInValid.equals("true")) {
							String ifscCodeValidEmpName = (objectArgs.get("ifscCodeInValidEmpName") != null ? objectArgs.get("ifscCodeInValidEmpName").toString() : null);
							objectArgs.put("msg", "Paybill cannot be generated as IFSC code is not valid for employees "+ifscCodeValidEmpName);
							ifscCodeValidEmpName = null;
						}
						
						if(negativeNetAfterNGR != null && negativeNetAfterNGR.equals("true")) {
							String negativeNetAfterNGREmpName = (objectArgs.get("negativeNetAfterNGREmpName") != null ? objectArgs.get("negativeNetAfterNGREmpName").toString() : null);
							objectArgs.put("msg", "Paybill cannot be generated as NGR amount is greater than or equal to Net amount for employees "+negativeNetAfterNGREmpName);
							negativeNetAfterNGREmpName = null;
						}
			             if(GpfCodeAbsent.equals("true")){
		                        logger.info("in GPf TIcked 'N' ");
		                        String GpfSubPresentEmpName = (objectArgs.get("GpfSubsAbsentEmpName") != null ? objectArgs.get("GpfSubsAbsentEmpName").toString() : null);
		                        objectArgs.put("msg", "Paybill cannot be generated as GpF subscription exceeds 5 lacs for the financial year :- "+GpfSubPresentEmpName+" Kindly untick the component from Emp. Eligibility for Allowances and Deductions");
		                        GpfSubPresentEmpName = null;
		                    }
						
						ifscCodeAbsent = null;
						ifscCodeInValid = null;
						cmbbillTypeCmb = null;
						resultObject.setResultCode(ErrorConstants.SUCCESS);
						resultObject.setResultValue(objectArgs);
						resultObject.setViewName("paybillPara");

						/*objectArgs.put("month", monthGiven);
							objectArgs.put("year", yearGiven);
							paybillGenerated = 0;
							objectArgs.put("paybillGenerated", "0");
							resultObject.setResultValue(objectArgs);
							resultObject.setViewName("paybillPara");*/
					}
					//nill Bill : end
                } else
                {
                    logger.info("Coming into the Service method getPaybillParaPage**********");
                    // ServiceLocator serv =
                    // (ServiceLocator)objectArgs.get("serviceLocator");
                    PayBillDAOImpl payBillDAO = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
                    // Map loginDetailsMap = (Map)
                    // objectArgs.get("baseLoginMap");
                    // long
                    // langId=StringUtility.convertToLong(loginDetailsMap.get("langId").toString());
                    logger.info("lng id id " + langId);

                    CmnLookupMstDAO lookupDAO = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
                    List yearList = lookupDAO.getAllChildrenByLookUpNameAndLang("Year", langId);
                    Collections.reverse(yearList);

                    // long
                    // userId=Long.parseLong(loginDetailsMap.get("userId").toString());
                    // OrgUserMstDaoImpl orgUserMstDaoImpl=new
                    // OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
                    // OrgUserMst orgUserMst=orgUserMstDaoImpl.read(userId);

                    // CmnLocationMstDaoImpl locationDAO = new
                    // CmnLocationMstDaoImpl(CmnLocationMst.class,serv.getSessionFactory());

                    List locationIdList = payBillDAO.getconcernedDept(langId, userId);// locationDAO.getListByColumnAndValue("cmnLanguageMst.langId",
                                                                                      // langId);

                    // List locationList =
                    // locationDAO.getListByColumnAndValue("cmnLanguageMst.langId",
                    // langId);
                    // CmnLocationMst locMst = null;

                    // added by Ankit Bhatt on 19th June 2011 for Maha Payroll
                    String ddo_code = null;
                    // long postId =
                    // StringUtility.convertToLong(loginDetailsMap.get("primaryPostId").toString());
                    List<OrgDdoMst> ddoCodeList = payBillDAO.getDDOCodeByLoggedInlocId(locId);
                    if (ddoCodeList != null)
                        logger.info("After query execution for DDO Code " + ddoCodeList.size());

                    OrgDdoMst ddoMst = null;
                    if (ddoCodeList != null && ddoCodeList.size() > 0)
                    {
                        ddoMst = ddoCodeList.get(0);
                    }

                    ddoCodeList = null;

                    if (ddoMst != null)
                    {
                        ddo_code = ddoMst.getDdoCode();
                    }

                    ddoMst = null;
                    logger.info("DDO Code is " + ddo_code);
                    // ended by Ankit Bhatt

                    
                    
                    
                	
                    
                    
                 // Added by Mrugesh
                    ResourceBundle resourceBundle = ResourceBundle.getBundle("resources.Payroll");
                    long finYearId = Long.parseLong(resourceBundle.getString("finYearId"));
                    // long
                    // locId=Long.parseLong(loginDetailsMap.get("locationId").toString());
                    List billNoList = new ArrayList();
                    if (ddo_code != null)
                    {
                        billNoList = payBillDAO.getBillNo(finYearId, locId, ddo_code);
                    }
                    // List arrearList = payBillDAO.getArrearList(locId);
                    List arrearList = new ArrayList();
                    logger.info("The size of billNoList is---->" + billNoList.size());
                    ArrayList billList = new ArrayList();
                    for (Iterator itr = billNoList.iterator(); itr.hasNext();)
                    {
                        HrPayBillHeadCustomVO billNoCustomVO = new HrPayBillHeadCustomVO();
                        Object[] row = (Object[]) itr.next();
                        String billNo1 = row[1].toString(); // dcpsDdoBillDescription
                                                            // = Bill Number of
                                                            // GAD
                        String billHeadId = row[0].toString(); // //dcpsDdoBillGroupId
                                                               // = Bill Value
                                                               // (PK)
                        billNoCustomVO.setBillId(billNo1);
                        billNoCustomVO.setBillHeadId(Long.parseLong(billHeadId));
                        billList.add(billNoCustomVO);
                    }

                    billNoList = null;
                    // Ended by Mrugesh
                    /*
                     * if(locationList!=null && locationList.size()!=0) {
                     * for(Iterator it=locationList.iterator();it.hasNext();) {
                     * locMst = (CmnLocationMst)it.next();logger.info(
                     * "To Avoid LazyInitialization in getPaybillParaPage" +
                     * locMst.getLocationCode() + " / " + locMst.getLocName());
                     * } }
                     */
                    java.util.Calendar calendar = java.util.Calendar.getInstance();
                    int curYear = calendar.get(java.util.Calendar.YEAR);
                    int curMonth = calendar.get(java.util.Calendar.MONTH);
                    logger.info("curYear====>" + curYear + "and month===>" + curMonth);
                    long locationid = 0;
                    for (Iterator iter = locationIdList.iterator(); iter.hasNext();)
                    {
                        // ArrayList rowList = new ArrayList();
                        Object[] row = (Object[]) iter.next();
                        locationid = Long.parseLong(row[0].toString());
                    }

                    locationIdList = null;
                    objectArgs.put("locationid", locationid);
                    objectArgs.put("yearList", yearList);
                    // objectArgs.put("deptList", locationList);
                    objectArgs.put("billList", billList);
                    objectArgs.put("curYear", curYear);
                    objectArgs.put("curMonth", curMonth);
                    objectArgs.put("arrearList", arrearList);
                    objectArgs.put("msg", "DCPS Contribution has to be approved before generating paybill");

                    if (dcpsVoucherStatus == -3)
                    {
                        objectArgs.put("messageForPaybillSch",
                                "DCPS Contributions are rejected and are with DDO Assistant");
                    } else if (dcpsVoucherStatus == 5)
                    {
                        objectArgs.put("messageForPaybillSch", "DCPS Contribution are with DDO");
                    } else
                    {
                        objectArgs.put("messageForPaybillSch",
                                "DCPS Contribution has to be approved before generating paybill");
                    }

                    resultObject.setResultCode(ErrorConstants.SUCCESS);
                    resultObject.setResultValue(objectArgs);
                    resultObject.setViewName("paybillPara");

                    /*
                     * objectArgs.put("month", monthGiven);
                     * objectArgs.put("year", yearGiven); paybillGenerated = 0;
                     * objectArgs.put("paybillGenerated", "0");
                     * resultObject.setResultValue(objectArgs);
                     * resultObject.setViewName("paybillPara");
                     */

                }
                logger.info("GenerateBillService End time " + System.currentTimeMillis());

                // added by Ankit Bhatt
                orgPostMstDaoImpl = null;
                orgPostMst = null;
                sysdate = null;
            } // ending else condition
            // ended by Ankit Bhatt.
            // Object assigning null values
            dcpsContrilist = null;
            eligibleEmpMap = null;
            dcpsEmpList = null;
            // eligibleNonVacantPostIdList=null;
            loginMap = null;
            // chk = null;
            // eligiblePosts=null;
            // hrEisOtherDtlsMap=null;
            dcpsEmpListNew = null;
            empPaybillVOMap = null;
            generateEmpList = null;
            vacantPostIdList = null;
            // Kishan
            currSession = null;
            fromPayBillScheduler = null;
            orgUserMst = null;
            cmnDatabaseMst = null;
            cmnLocationMst = null;
            cmnLanguageMst = null;
            empIdsStr = null;
            postIdsStr = null;
            orgEmpIdsStr = null;
            billTypeCmb = null;
            demandNo = null;
            mjrHead = null;
            subMjrHead = null;
            mnrHead = null;
            subHead = null;
            calSupplBill = null;
            finYrMst = null;
            billHeadMpg = null;
            otherDetailDAOImpl = null;
            dcpsEmpIds = null;
            givenDateEnd = null;
            givenDateStart = null;
            // Null Assignment Ends
        } catch (Exception e)
        {
            logger.info("Exception in generate bill service-----" + e);
            resultObject.setResultCode(ErrorConstants.ERROR);
            objectArgs.put("msg", "Problem while inserting into database");
            // by manoj for exception handling in outer
            objectArgs.put("paybillGenerated", "0");
            // end by manoj for exception handling in outer
            resultObject.setResultValue(objectArgs);
            resultObject.setThrowable(e);
            logger.error("Error is: " + e.getMessage());
            resultObject.setViewName("errorInsert");
        }

        return resultObject;
    }

    public static double round(double value)
    {
        long temp = new Double(value).longValue();
        double roundedValue = (value - temp) >= 0.5 ? Math.ceil(value) : Math.floor(value);
        return roundedValue;

    }

    public int dayofDate(Date date)
    {
        int dayofDate = 0;
        SimpleDateFormat sdfObj = new SimpleDateFormat("dd");
        String days = sdfObj.format(date);
        dayofDate = Integer.parseInt(days);
        return dayofDate;
    }

    public int monthofDate(Date date)
    {
        int monthofDate = 0;
        SimpleDateFormat sdfObj = new SimpleDateFormat("MM");
        String days = sdfObj.format(date);
        monthofDate = Integer.parseInt(days);
        return monthofDate;
    }

    public int yearofDate(Date date)
    {
        int yearofDate = 0;
        SimpleDateFormat sdfObj = new SimpleDateFormat("yyyy");
        String days = sdfObj.format(date);
        yearofDate = Integer.parseInt(days);
        return yearofDate;
    }

    /*
     * public long calMonthlyIncomeTax(Map objectArgs){ ServiceLocator serv =
     * (ServiceLocator)objectArgs.get("serviceLocator");
     * 
     * //HttpServletRequest request = (HttpServletRequest)
     * objectArgs.get("requestObj"); //long empId=302133; //long empId =
     * Long.parseLong(request.getParameter("empId")); //int monthGiven=
     * Integer.parseInt(request.getParameter("month")); //int yearGiven =
     * Integer.parseInt(request.getParameter("year")); //double currentGrossAmt
     * = 435; //double currProfTax = 60; //double currFoodAdvAmt = 240; //double
     * currFestAdvAmt = 200; //int monthGiven=12; //int yearGiven=2007;
     * 
     * 
     * // Fetch the values from the objectArgs Map of current Month.
     * 
     * 
     * long empId = Long.parseLong(objectArgs.get("empId").toString()); double
     * currentGrossAmt =
     * Double.parseDouble(objectArgs.get("grossAmount").toString()); double
     * currProfTax =
     * Double.parseDouble(objectArgs.get("curretPtofTax").toString()); double
     * currFoodAdvAmt =
     * Double.parseDouble(objectArgs.get("currFoodAdvAmt").toString()); double
     * currFestAdvAmt =
     * Double.parseDouble(objectArgs.get("currFestAdvAmt").toString()); int
     * monthGiven= Integer.parseInt(objectArgs.get("monthGiven").toString());
     * int yearGiven=Integer.parseInt(objectArgs.get("yearGiven").toString());
     * logger.info("The EmployeeId is:-"+empId+"Gross Amount:-"+currentGrossAmt+
     * "Proffessional Tax:-"+currProfTax+"Food Advance Amount:-"+currFoodAdvAmt+
     * "Festival Advance Amount:-"+currFestAdvAmt);
     * 
     * 
     * 
     * long totalInvestAmt=0l; // Total Investment Amount of the current
     * Financial Year. double approvedInvestAmt = 0d; // Total Approved
     * Investment Amount. long totalExemptAmt=0l; //Total Exemption Amount of
     * the Current Financial Year. Map incomeTaxData = new HashMap(); //Map of
     * Past months' Total Gross Amount,Income Tax,Proffessional Tax of Current
     * Financial Year. double totalPastProffTaxAmt=0d; // Total Past Months'
     * Proffessional Tax Amount of the current Financial Year. double
     * totalNextProfTaxAmt = 0d; //Total Next months' Proffessional Tax Amount
     * of the Current Financial Year. double totalProfTaxAmt = 0d; // Total
     * Proffessioanl Tax of the Current Financial Year. double
     * totalNextFoodAdvaceAmt=0d; // Total Next Months' Food Advance Amount of
     * the Current Financial Year. double totalNextFestAdvaceAmt=0d; // Total
     * Next Months' Festival Advance Amount of the Current Financial Year.
     * double totalAllowancesAmt=0d; // Total of all Allowances of perticular
     * employee. double totalPastGrossAmt=0d; // Total Past Months' Gross Amount
     * of the current Financial Year.
     * 
     * double totalPaidIncomeTax = 0d; // Total Past Months' Income Tax Amount
     * of the current Financial Year. double totalIncomeTax = 0d; // Total
     * Income Tax Amount of the Current financial Year. double totalRemainedIt =
     * 0d; // Total Remained Income Tax of the current financial Year. long
     * monthlyIncomeTax = 0l; // Income tax of the next months of the current
     * financial year. double totalNextGrossAmt = 0d; // Total Next Months'
     * Gross Amount of the Current Financial Year. double totalGrossAmt = 0d;
     * //Total Gross Amount of the current financial year. double
     * netTaxableIncome = 0d; // Total Taxable Income of the current financial
     * year. String gender = "M";
     * 
     * IncomeTaxRules incomeTaxRules = new IncomeTaxRules();
     * 
     * 
     * long foodLoanTypeId = 2; long festivalLoanTypeId = 16; int nextMonths=0;
     * 
     * try { PayBillDAOImpl payBillDaoImpl = new
     * PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
     * incomeTaxData =
     * payBillDaoImpl.getPastIncTaxData(empId,monthGiven,yearGiven);
     * logger.info("You are in the Income Tax Calaulation"); // Calculation for
     * the Total Gross Amount if(monthGiven > 3){ nextMonths = 15 - monthGiven;
     * } else{ nextMonths = 3 - monthGiven; }
     * logger.info("The Next Months are:-"+nextMonths); totalPastGrossAmt =
     * (Double) incomeTaxData.get("totalPastGrossAmt");
     * logger.info("The PastGross Amount is:-"+totalPastGrossAmt);
     * totalAllowancesAmt = payBillDaoImpl.getAllowancesTotal(empId); //Monthly
     * Allowances total of a perticular (by considering the zero leave).
     * logger.info("Total of All Allowances is:-"+totalAllowancesAmt);
     * totalNextFoodAdvaceAmt = payBillDaoImpl.getTotNextAdvacesAmt(empId,
     * foodLoanTypeId, monthGiven);
     * logger.info("Total Next Food Advance Amount is:-"
     * +totalNextFoodAdvaceAmt); totalNextFestAdvaceAmt =
     * payBillDaoImpl.getTotNextAdvacesAmt(empId, festivalLoanTypeId,
     * monthGiven);
     * logger.info("Total Next Festival Advance Amount is:-"+totalNextFestAdvaceAmt
     * );
     * 
     * 
     * 
     * // Equation for the Gross = AllowancesTotal - (Pay Recovery + Food
     * Advance EMI + Festival Advace EMI)
     * 
     * totalNextGrossAmt = (totalAllowancesAmt nextMonths) -
     * (totalNextFoodAdvaceAmt + totalNextFestAdvaceAmt + currFoodAdvAmt +
     * currFestAdvAmt);
     * logger.info("Total Next Gross Amount is:-"+totalNextGrossAmt);
     * totalGrossAmt = totalPastGrossAmt + currentGrossAmt + totalNextGrossAmt;
     * logger.info("Total Gross Amount is:-"+totalGrossAmt);
     * 
     * // Total Investment, Exemption and the deduction under chapter-6.
     * totalInvestAmt = payBillDaoImpl.getInvestAmtOfCurrFinYear(empId); // Call
     * for fetching the Total Investment Amount of the Current Financial Year.
     * totalExemptAmt = payBillDaoImpl.getExemptAmtOfCurrFinYear(empId); // Call
     * for fetching the Total Exemption and Deduction Chapter-6 Amount of the
     * Current Financial Year.
     * approvedInvestAmt=incomeTaxRules.getInvestmentAmount
     * ((double)totalInvestAmt);
     * logger.info("Total Investment Amount is:-"+totalInvestAmt);
     * logger.info("Total approved Investment Amount is:-"+approvedInvestAmt);
     * logger.info("Total Investment Amount is:-"+totalInvestAmt+
     * "\n Total Exemption Amount is:-"+totalExemptAmt);
     * 
     * // Proffessioanl Tax Calculateion totalPastProffTaxAmt = (Double)
     * incomeTaxData.get("totalPastProffTaxAmt"); totalNextProfTaxAmt =
     * payBillDaoImpl.getNextProfTax(empId);
     * logger.info("Total Past Proffessional Tax is:-"+totalPastProffTaxAmt);
     * logger.info("Total Next Proffessional Tax is:-"+totalNextProfTaxAmt
     * nextMonths); totalProfTaxAmt = totalPastProffTaxAmt + currProfTax +
     * (totalNextProfTaxAmt nextMonths);
     * 
     * // Calculation for the Income Tax of an employee.
     * 
     * totalPaidIncomeTax = (Double) incomeTaxData.get("totalPastIncomeTax");
     * logger.info("Total Paid Income Tax amount is:-"+totalPaidIncomeTax);
     * netTaxableIncome = totalGrossAmt - (totalProfTaxAmt + approvedInvestAmt +
     * totalExemptAmt);
     * logger.info("Total Next Income Tax amount is:-"+netTaxableIncome);
     * totalIncomeTax = incomeTaxRules.calculateIncomeTax(netTaxableIncome, 1,
     * gender); logger.info("Total Income Tax is:-"+totalIncomeTax);
     * totalRemainedIt = totalIncomeTax - totalPaidIncomeTax;
     * logger.info("Total Remained Income Tax is:-"+totalRemainedIt); nextMonths
     * += 1; monthlyIncomeTax =(long) totalRemainedIt / nextMonths ;
     * logger.info("The Monthly Income Tax Amount is:-"+monthlyIncomeTax); }
     * catch (Exception e){ logger.error("Error is: "+ e.getMessage()); } return
     * monthlyIncomeTax; }
     */

    public Map checkMaxDayOfPostRecord(Set orguserPostrlt, int monthGiven, int yearGiven)
    {
        Map maxDaysMap = new HashMap();

        long maxDaysOfPost = -1;
        long maxDaysUserPostRltId = 0;
        long maxDaysUserId = 0;

        Calendar calGiven = Calendar.getInstance();

        logger.info("the orgUserPostRlt is " + orguserPostrlt.size());

        // added by Ankit Bhatt
        boolean isSingleRecord = false;
        // int orguserPostrltSize = orguserPostrlt.size();
        // if(orguserPostrltSize<=1)
        isSingleRecord = true;
        // ended

        int startDays;
        int startMonth;
        int startYear;

        int endMonth = 0;
        int endYear = 0;
        int endDays = 0;

        long maxDay = 0;
        long daysOfPost = 0;

        maxDaysMap.put("maxDaysUserPostRltId", maxDaysUserPostRltId);
        maxDaysMap.put("maxDaysUserId", maxDaysUserId);
        maxDaysMap.put("maxDaysOfPost", maxDaysOfPost);

        // by manoj for calculating deduction for maxdays of post.
        for (Iterator upIt = orguserPostrlt.iterator(); upIt.hasNext();)
        {
            OrgUserpostRlt orgUPRltMaxDays = (OrgUserpostRlt) upIt.next();

            logger.info("the post id to chk is " + orgUPRltMaxDays.getOrgPostMstByPostId().getPostId());
            long activateFlag = orgUPRltMaxDays.getActivateFlag();

            Date endDate = orgUPRltMaxDays.getEndDate();

            int month = 0;
            int year = 0;

            if (endDate != null)
            {
                month = monthofDate(endDate);
                year = yearofDate(endDate);
            }

            if ((activateFlag == 1 || (activateFlag == 0 && month == monthGiven && year == yearGiven)))
            {

                startDays = dayofDate(orgUPRltMaxDays.getStartDate());
                startMonth = monthofDate(orgUPRltMaxDays.getStartDate());
                startYear = yearofDate(orgUPRltMaxDays.getStartDate());

                endMonth = 0;
                endYear = 0;
                endDays = 0;

                maxDay = 0;
                daysOfPost = 0;

                calGiven.set(Calendar.YEAR, yearGiven);
                calGiven.set(Calendar.MONTH, (monthGiven - 1));
                calGiven.set(Calendar.DAY_OF_MONTH, 1);

                maxDay = calGiven.getActualMaximum(5);
                Date givenDate = calGiven.getTime();
                logger.info(" calculating maxdays the value of maxDay " + maxDay + " and startDays is " + startDays);

                if (orgUPRltMaxDays.getEndDate() != null)
                {
                    endDays = dayofDate(orgUPRltMaxDays.getEndDate());
                    endMonth = monthofDate(orgUPRltMaxDays.getEndDate());
                    endYear = yearofDate(orgUPRltMaxDays.getEndDate());

                    logger.info(" calculating maxdays the value of endDays " + endDays + " and startDays is "
                            + startDays);
                    logger
                            .info(" calculating maxdays the value of endMonth " + endMonth + " and endYear is "
                                    + endYear);
                    logger.info(" calculating maxdays the value of startMonth " + startMonth + " and startYear is "
                            + startYear);
                    logger.info(" calculating maxdays the value of monthGiven " + monthGiven + " and yearGiven is "
                            + yearGiven);
                    if (endMonth == monthGiven && endYear == yearGiven)
                    // End date in current month and year.
                    {

                        if (startMonth == monthGiven && startYear == yearGiven)
                        // Start and end date both are in the same month and
                        // year.
                        {
                            daysOfPost = endDays - (startDays - 1);
                            logger.info(" calculating maxdays Total Days:-" + daysOfPost);
                        } else
                        // start date is not in current month but end date is in
                        // current month.
                        {
                            daysOfPost = endDays;
                            logger.info(" calculating maxdays Total No. Days:-" + daysOfPost);
                        }
                    } else
                    // End date is not in current month and year.
                    {
                        if (startMonth == monthGiven && startYear == yearGiven) // if
                        // startDate is in current month.
                        {
                            daysOfPost = maxDay - (startDays - 1);
                        } else if (givenDate.after(orgUPRltMaxDays.getStartDate())
                                && givenDate.before(orgUPRltMaxDays.getEndDate())) // if
                        // whole current month is between the start and end
                        // date.
                        {
                            daysOfPost = maxDay;
                            logger.info("calculating maxdays when start date is less than current date " + daysOfPost);
                        } else
                        // if whole current month is not between the
                        // start and end date.
                        {
                            continue;
                        }
                    }
                } else
                // if End Date is null.
                {

                    if (startMonth == monthGiven && startYear == yearGiven) // if
                    // startDate is in current month.
                    {
                        daysOfPost = maxDay - (startDays - 1);
                    } else if (givenDate.after(orgUPRltMaxDays.getStartDate())) // if
                    // startdate is less then current date.
                    {
                        daysOfPost = maxDay;
                    } else
                        // if start date is greater then current date.
                        continue;
                }

                logger.info("calculating maxdays The day diff is " + daysOfPost);

                if (daysOfPost > maxDaysOfPost)
                {
                    logger.info("from if and maxDaysOfPost " + maxDaysOfPost);

                    maxDaysOfPost = daysOfPost;

                    maxDaysMap.put("maxDaysUserPostRltId", orgUPRltMaxDays.getEmpPostId());
                    maxDaysMap.put("maxDaysUserId", orgUPRltMaxDays.getOrgUserMst().getUserId());
                    maxDaysMap.put("maxDaysOfPost", maxDaysOfPost);
                    // added by Ankit Bhatt
                    if (isSingleRecord)
                        maxDaysMap.put("daysOfPost", daysOfPost);
                    // ended
                }
                logger.info(" and maxDaysOfPost " + maxDaysOfPost);
                logger.info(" Single record in user post for post id " + orgUPRltMaxDays.getEmpPostId()
                        + " and days of post is " + daysOfPost);

            }
        }
        // end by manoj for calculating deduction for maxdays of post.

        logger.info("maxDaysMap " + maxDaysMap);
        return maxDaysMap;

    }

    public Map calculateTraDays(long empid, ServiceLocator serv, int monthGiven, int yearGiven)
    {

        Map vehicalAvailedMap = new HashMap();

        // added by manoj for HrEmpTraMpg related issues
        long daysofVehiAvailed = 0;
        long daysofVehiAvailedHst = 0;
        List VehiAvailedDaysList = new ArrayList();
        boolean isVehiAvailed = false;
        long taDistance = 0;

        // added by manoj for Emp TRa mapping issue
        // HrEmpTraMpg hrEmpTraMpg = new HrEmpTraMpg();
        HrEmpTraMpg hrEmpTraMpg = null;
        HrEmpTraMpgHst hrEmpTraMpgHst = null;
        HrEmpTraMpgHst hrEmpTraMpgHstPrev = null;
        HrEmpTraMpgDaoImpl hrEmpTraMpgDao = new HrEmpTraMpgDaoImpl(HrEmpTraMpg.class, serv.getSessionFactory());
        List vehicalAvailedList = null;
        List vehicalAvailedHstList = null;
        Calendar calGiven = Calendar.getInstance();
        calGiven.set(Calendar.YEAR, yearGiven);
        calGiven.set(Calendar.MONTH, (monthGiven - 1));
        calGiven.set(Calendar.DAY_OF_MONTH, 1);

        long maxDay = calGiven.getActualMaximum(5);
        Date givenDate = calGiven.getTime();
        // end by manoj for emp Tra mapping issue

        /*
         * if(maxDaysFlag==1) {
         */
        logger.info(" going to calculate vehical availed days for empid " + empid);

        vehicalAvailedList = hrEmpTraMpgDao.getHrEmpTraData(empid);

        if (vehicalAvailedList != null && vehicalAvailedList.size() > 0)
        {
            int availedStartDay = 0;
            int availedStartMonth = 0;
            int availedStartYear = 0;

            int availedEndDay = 0;
            int availedEndMonth = 0;
            int availedEndYear = 0;

            int availedHstStartDay = 0;
            int availedHstStartMonth = 0;
            int availedHstStartYear = 0;

            int availedHstEndDay = 0;
            int availedHstEndMonth = 0;
            int availedHstEndYear = 0;

            int availedHstEndDayPrev = 0;
            /*
             * int availedHstEndMonthPrev = 0; int availedHstEndYearPrev = 0;
             */

            long mstTrnCount = 0;
            long hstTrnCount = 0;
            logger.info("ther size of vehicalAvailedList.size() " + vehicalAvailedList.size());
            for (int count = 0; count < vehicalAvailedList.size(); count++)
            {
                hrEmpTraMpg = (HrEmpTraMpg) vehicalAvailedList.get(count);

                taDistance = hrEmpTraMpg.getDistance();
                isVehiAvailed = Boolean.getBoolean(hrEmpTraMpg.getVehicalAvailed().toLowerCase());

                mstTrnCount = hrEmpTraMpg.getTrnCounter();

                logger.info("taDistance " + taDistance + " isVechAvailed " + isVehiAvailed + " mstTrnCount "
                        + mstTrnCount);

                daysofVehiAvailedHst = 0;

                if (hrEmpTraMpg.getVehicalAvailed().equalsIgnoreCase("true"))
                {// to
                    // chk
                    // whether
                    // vehical
                    // is
                    // availed
                    // of
                    // not

                    logger.info("vehical is availed by employee");
                    logger.info(" availedStartMonth " + availedStartMonth + " == " + monthGiven
                            + "&& availedStartYear " + availedStartYear + "==" + yearGiven);
                    availedStartDay = dayofDate(hrEmpTraMpg.getVehicalAvailDate());
                    availedStartMonth = monthofDate(hrEmpTraMpg.getVehicalAvailDate());
                    availedStartYear = yearofDate(hrEmpTraMpg.getVehicalAvailDate());
                    if (availedStartMonth == monthGiven && availedStartYear == yearGiven)
                    {// if
                        // start
                        // month
                        // falls
                        // in
                        // current
                        // month
                        // then
                        // only
                        // need
                        // to
                        // chk
                        // for
                        // the
                        // history
                        // table
                        vehicalAvailedHstList = hrEmpTraMpgDao.getHrEmpTraDataHst(empid);
                        logger.info("ther size of vehicalAvailedHstList.size() " + vehicalAvailedHstList.size());
                        if (vehicalAvailedHstList != null && vehicalAvailedHstList.size() > 0)
                        {
                            availedHstStartDay = 0;
                            availedHstStartMonth = 0;
                            availedHstStartYear = 0;

                            availedHstEndDay = 0;
                            availedHstEndMonth = 0;
                            availedHstEndYear = 0;

                            logger.info("ther size of vehicalAvailedHstList.size() " + vehicalAvailedHstList.size());
                            for (int countHst = 0; countHst < vehicalAvailedHstList.size(); countHst++)
                            {

                                daysofVehiAvailedHst = 0;
                                hrEmpTraMpgHst = (HrEmpTraMpgHst) vehicalAvailedHstList.get(countHst);

                                hstTrnCount = hrEmpTraMpgHst.getId().getTrnCounter();

                                logger.info("ther hrEmpTraMpgHst " + hrEmpTraMpgHst + " hstTrnCount " + hstTrnCount
                                        + " mstTrnCount " + mstTrnCount);
                                if (vehicalAvailedHstList.size() > 1)
                                {
                                    logger
                                            .info("hrEmpTraMpg.getVehicalAvailed() "
                                                    + hrEmpTraMpgHst.getVehicalAvailed());

                                    if (hstTrnCount != mstTrnCount
                                            && hrEmpTraMpgHst.getVehicalAvailed().equalsIgnoreCase("true")
                                            && hrEmpTraMpgHst.getVehicalAvailDate().before(
                                                    hrEmpTraMpg.getVehicalAvailDate()))
                                    {
                                        if (hrEmpTraMpgHst.getVehicalAvailed().equalsIgnoreCase("true"))
                                        {// to
                                            // chk
                                            // whether
                                            // vehical
                                            // is
                                            // availed
                                            // of
                                            // not
                                            // in
                                            // hst
                                            logger.info("vehical is availed by employee in history");

                                            availedHstStartDay = dayofDate(hrEmpTraMpgHst.getVehicalAvailDate());
                                            availedHstStartMonth = monthofDate(hrEmpTraMpgHst.getVehicalAvailDate());
                                            availedHstStartYear = yearofDate(hrEmpTraMpgHst.getVehicalAvailDate());

                                            logger.info("the hst error chking is leave date "
                                                    + hrEmpTraMpgHst.getVehicalLeaveDate());
                                            if (hrEmpTraMpgHst.getVehicalLeaveDate() != null)
                                            {
                                                if (countHst == 0)
                                                {
                                                    /*
                                                     * hrEmpTraMpgHstPrev
                                                     * =(HrEmpTraMpgHst
                                                     * )vehicalAvailedHstList
                                                     * .get(countHst-1);
                                                     * if(hrEmpTraMpgHstPrev
                                                     * .getVehicalLeaveDate
                                                     * ()!=null) {
                                                     * availedHstEndDayPrev=
                                                     * dayofDate
                                                     * (hrEmpTraMpgHstPrev
                                                     * .getVehicalLeaveDate());
                                                     * availedHstEndMonthPrev
                                                     * =monthofDate
                                                     * (hrEmpTraMpgHstPrev
                                                     * .getVehicalLeaveDate());
                                                     * availedHstEndYearPrev =
                                                     * yearofDate
                                                     * (hrEmpTraMpgHstPrev
                                                     * .getVehicalLeaveDate());
                                                     * logger.info(
                                                     * " countHst !=0 the value of availedHstEndDayPrev "
                                                     * +availedHstEndDayPrev+
                                                     * " and availedHstStartDay is "
                                                     * +availedHstStartDay); }
                                                     */

                                                    availedHstEndDay = dayofDate(hrEmpTraMpgHst.getVehicalLeaveDate());
                                                    availedHstEndMonth = monthofDate(hrEmpTraMpgHst
                                                            .getVehicalLeaveDate());
                                                    availedHstEndYear = yearofDate(hrEmpTraMpgHst.getVehicalLeaveDate());
                                                    logger.info("the value of availedHstEndDay " + availedHstEndDay
                                                            + " and availedHstStartDay is " + availedHstStartDay);
                                                    logger.info("the value of availedHstEndMonth " + availedHstEndMonth
                                                            + " and availedHstEndYear is " + availedHstEndYear);
                                                    if (availedHstEndMonth == monthGiven
                                                            && availedHstEndYear == yearGiven) // End
                                                    // date
                                                    // in
                                                    // current
                                                    // month
                                                    // and
                                                    // year.
                                                    {
                                                        if (availedHstStartMonth == monthGiven
                                                                && availedHstStartYear == yearGiven) // Start
                                                        // and
                                                        // end
                                                        // date
                                                        // both
                                                        // are
                                                        // in
                                                        // the
                                                        // same
                                                        // month
                                                        // and
                                                        // year.
                                                        {
                                                            daysofVehiAvailedHst = availedHstEndDay
                                                                    - (availedHstStartDay - 1);
                                                            logger.info("Total daysofVehiAvailedHst:-"
                                                                    + daysofVehiAvailedHst);
                                                        } else
                                                        // start date is
                                                        // not in
                                                        // current month
                                                        // but end date
                                                        // is in current
                                                        // month.
                                                        {
                                                            daysofVehiAvailedHst = availedHstEndDay;
                                                            logger.info("Total No. daysofVehiAvailedHst:-"
                                                                    + daysofVehiAvailedHst);
                                                        }
                                                    } else
                                                    // End date is not in
                                                    // current month and
                                                    // year.
                                                    {
                                                        if (availedHstStartMonth == monthGiven
                                                                && availedHstStartYear == yearGiven) // if
                                                        // startDate
                                                        // is
                                                        // in
                                                        // current
                                                        // month.
                                                        {
                                                            daysofVehiAvailedHst = maxDay - (availedHstStartDay - 1);
                                                        } else if (givenDate
                                                                .after(hrEmpTraMpgHst.getVehicalAvailDate())
                                                                && givenDate.before(hrEmpTraMpgHst
                                                                        .getVehicalLeaveDate())) // if
                                                        // whole
                                                        // current
                                                        // month
                                                        // is
                                                        // between
                                                        // the
                                                        // start
                                                        // and
                                                        // end
                                                        // date.
                                                        {
                                                            daysofVehiAvailedHst = maxDay;
                                                            logger
                                                                    .info("daysofVehiAvailedHst for hst when start date is less than current date "
                                                                            + daysofVehiAvailedHst);
                                                        } else
                                                        // if whole
                                                        // current month
                                                        // is not
                                                        // between the
                                                        // start and end
                                                        // date.
                                                        {
                                                            continue;
                                                        }
                                                    }

                                                }// end of loop for checking
                                                // counter ==0
                                                else
                                                {
                                                    hrEmpTraMpgHstPrev = (HrEmpTraMpgHst) vehicalAvailedHstList
                                                            .get(countHst - 1);
                                                    if (hrEmpTraMpgHstPrev.getVehicalLeaveDate() != null)
                                                    {
                                                        availedHstEndDayPrev = dayofDate(hrEmpTraMpgHstPrev
                                                                .getVehicalLeaveDate());
                                                        /*
                                                         * availedHstEndMonthPrev
                                                         * =monthofDate(
                                                         * hrEmpTraMpgHstPrev
                                                         * .getVehicalLeaveDate
                                                         * ());
                                                         * availedHstEndYearPrev
                                                         * =yearofDate(
                                                         * hrEmpTraMpgHstPrev
                                                         * .getVehicalLeaveDate
                                                         * ());
                                                         */
                                                        logger.info(" countHst !=0 the value of availedHstEndDayPrev "
                                                                + availedHstEndDayPrev + " and availedHstStartDay is "
                                                                + availedHstStartDay);
                                                    }

                                                    availedHstEndDay = dayofDate(hrEmpTraMpgHst.getVehicalLeaveDate());
                                                    availedHstEndMonth = monthofDate(hrEmpTraMpgHst
                                                            .getVehicalLeaveDate());
                                                    availedHstEndYear = yearofDate(hrEmpTraMpgHst.getVehicalLeaveDate());
                                                    logger.info("the value of availedHstEndDay " + availedHstEndDay
                                                            + " and availedHstStartDay is " + availedHstStartDay);

                                                    // if(availedHstEndMonthPrev==monthGiven
                                                    // &&
                                                    // availedHstEndYearPrev==yearGiven
                                                    // &&
                                                    // availedHstEndDayPrev<availedHstStartDay)
                                                    // if(hrEmpTraMpgHstPrev.getVehicalLeaveDate().before(hrEmpTraMpgHst.getVehicalAvailDate()))
                                                    if (hrEmpTraMpgHstPrev.getVehicalLeaveDate() != null
                                                            && hrEmpTraMpgHst.getVehicalAvailDate() != null
                                                            && hrEmpTraMpgHstPrev.getVehicalLeaveDate().before(
                                                                    hrEmpTraMpgHst.getVehicalAvailDate()))
                                                    {
                                                        if (availedHstEndMonth == monthGiven
                                                                && availedHstEndYear == yearGiven) // End
                                                        // date
                                                        // in
                                                        // current
                                                        // month
                                                        // and
                                                        // year.
                                                        {
                                                            if (availedHstStartMonth == monthGiven
                                                                    && availedHstStartYear == yearGiven) // Start
                                                            // and
                                                            // end
                                                            // date
                                                            // both
                                                            // are
                                                            // in
                                                            // the
                                                            // same
                                                            // month
                                                            // and
                                                            // year.
                                                            {
                                                                daysofVehiAvailedHst = availedHstEndDay
                                                                        - (availedHstStartDay - 1);
                                                                logger.info("Total daysofVehiAvailedHst:-"
                                                                        + daysofVehiAvailedHst);
                                                            } else
                                                            // start date
                                                            // is not in
                                                            // current
                                                            // month but
                                                            // end date
                                                            // is in
                                                            // current
                                                            // month.
                                                            {
                                                                daysofVehiAvailedHst = availedHstEndDay;
                                                                logger.info("Total No. daysofVehiAvailedHst:-"
                                                                        + daysofVehiAvailedHst);
                                                            }
                                                        } else
                                                        // End date is
                                                        // not in
                                                        // current month
                                                        // and year.
                                                        {
                                                            if (availedHstStartMonth == monthGiven
                                                                    && availedHstStartYear == yearGiven) // if
                                                            // startDate
                                                            // is
                                                            // in
                                                            // current
                                                            // month.
                                                            {
                                                                daysofVehiAvailedHst = maxDay
                                                                        - (availedHstStartDay - 1);
                                                            } else if (givenDate.after(hrEmpTraMpgHst
                                                                    .getVehicalAvailDate())
                                                                    && givenDate.before(hrEmpTraMpgHst
                                                                            .getVehicalLeaveDate())) // if
                                                            // whole
                                                            // current
                                                            // month
                                                            // is
                                                            // between
                                                            // the
                                                            // start
                                                            // and
                                                            // end
                                                            // date.
                                                            {
                                                                daysofVehiAvailedHst = maxDay;
                                                                logger
                                                                        .info("daysofVehiAvailedHst for hst when start date is less than current date "
                                                                                + daysofVehiAvailedHst);
                                                            } else
                                                            // if whole
                                                            // current
                                                            // month is
                                                            // not
                                                            // between
                                                            // the start
                                                            // and end
                                                            // date.
                                                            {
                                                                continue;
                                                            }
                                                        }
                                                    }// end of if block for
                                                    // comparing with prev
                                                    // record
                                                    else
                                                    {
                                                        logger.info("curr and prev date same");
                                                    }
                                                }// end of loop for checking
                                                // counter !=0

                                            }// end of if block for checking hst
                                            // leave date not null
                                            VehiAvailedDaysList.add(daysofVehiAvailedHst);
                                        }// end of if block for hst table to
                                        // test whether quater avail or not
                                        else
                                        {
                                            logger.info("vehical is not availed by employee in hst");
                                        }
                                    }
                                }// end of allocation if block

                            }// end of for QauterHst loop

                        }// end of quaterHstList chk for null
                    }

                    vehicalAvailedHstList = null;

                    logger.info("the mst error chking is if vehical is availed or not "
                            + hrEmpTraMpg.getVehicalAvailed());
                    // if(hrEssQuaterMst.getAllocationEndDate()==null)
                    if (hrEmpTraMpg.getVehicalLeaveDate() != null)
                    {
                        availedEndDay = dayofDate(hrEmpTraMpg.getVehicalLeaveDate());
                        availedEndMonth = monthofDate(hrEmpTraMpg.getVehicalLeaveDate());
                        availedEndYear = yearofDate(hrEmpTraMpg.getVehicalLeaveDate());
                        logger.info("the value of availedEndDay " + availedEndDay + " and availedStartDay is "
                                + availedStartDay);

                        if (availedEndMonth == monthGiven && availedEndYear == yearGiven) // End
                        // date
                        // in
                        // current
                        // month
                        // and
                        // year.
                        {
                            if (availedStartMonth == monthGiven && availedStartYear == yearGiven) // Start
                            // and
                            // end
                            // date
                            // both
                            // are
                            // in
                            // the
                            // same
                            // month
                            // and
                            // year.
                            {
                                daysofVehiAvailed = availedEndDay - (availedStartDay - 1);
                                logger.info("Total daysofVehiAvailed:-" + daysofVehiAvailed);
                            } else
                            // start date is not in current month but end
                            // date is in current month.
                            {
                                daysofVehiAvailed = availedEndDay;
                                logger.info("Total No. daysofVehiAvailed:-" + daysofVehiAvailed);
                            }
                        } else
                        // End date is not in current month and year.
                        {
                            if (availedStartMonth == monthGiven && availedStartYear == yearGiven) // if
                            // startDate
                            // is
                            // in
                            // current
                            // month.
                            {
                                daysofVehiAvailed = maxDay - (availedStartDay - 1);
                            } else if (givenDate.after(hrEmpTraMpg.getVehicalAvailDate())
                                    && givenDate.before(hrEmpTraMpg.getVehicalLeaveDate())) // if
                            // whole
                            // current
                            // month
                            // is
                            // between
                            // the
                            // start
                            // and
                            // end
                            // date.
                            {
                                daysofVehiAvailed = maxDay;
                                logger.info("daysofVehiAvailed  when start date is less than current date "
                                        + daysofVehiAvailed);
                            } else
                            // if whole current month is not between the
                            // start and end date.
                            {
                                continue;
                            }
                        }
                    } else
                    {
                        if (availedStartMonth == monthGiven && availedStartYear == yearGiven) // if
                        // startDate
                        // is
                        // in
                        // current
                        // month.
                        {
                            daysofVehiAvailed = maxDay - (availedStartDay - 1);
                        } else if (givenDate.after(hrEmpTraMpg.getVehicalAvailDate())) // if
                        // startdate
                        // is
                        // less
                        // then
                        // current
                        // date.
                        {
                            daysofVehiAvailed = maxDay;
                        } else
                            // if start date is greater then current date.
                            continue;

                        logger.info("daysofVehiAvailed " + daysofVehiAvailed);

                    }

                    VehiAvailedDaysList.add(daysofVehiAvailed);

                }// vehical availed chk if block ends
                else
                {
                    logger.info("vehical is not availed by employee");
                }
            }// end of for loop VehiAvailed master table
        } else
        // if quaterList is null master table
        {
            daysofVehiAvailed = 0;

            VehiAvailedDaysList.add(daysofVehiAvailed);

        }

        vehicalAvailedList = null;
        /*
         * }//end of maxDaysFlag if block else // if maxDaysFlag is not equal to
         * 1 { quater_id=0; daysofQauter=0;
         * 
         * quaterIdList.add(quater_id); quaterDaysList.add(daysofQauter);
         * 
         * }
         */

        long totalVehicalAvailDays = 0;

        for (int count = 0; count < VehiAvailedDaysList.size(); count++)
        {
            totalVehicalAvailDays += (Long) VehiAvailedDaysList.get(count);
        }

        VehiAvailedDaysList = null;

        logger.info("totalVehicalAvailDays " + totalVehicalAvailDays);

        if (totalVehicalAvailDays < maxDay)
        {
            isVehiAvailed = false;
            logger.info("vehical is not availed for whole month");
        } else
        {
            isVehiAvailed = true;
            logger.info("vehical is availed for whole month");
        }

        vehicalAvailedMap.put("isVehiAvailed", isVehiAvailed);
        vehicalAvailedMap.put("taDistance", taDistance);
        vehicalAvailedMap.put("totalVehicalAvailDays", totalVehicalAvailDays);

        return vehicalAvailedMap;
    }

    @SuppressWarnings("static-access")
    public Map empLeaveDtls(long empid, ServiceLocator serv, int monthGiven, int yearGiven)
    {
        Map empLeaveMap = new HashMap();

        EmpLeaveDAOImpl empLeaveDao = new EmpLeaveDAOImpl(HrLeaveEmpDtls.class, serv.getSessionFactory());
        HrLeaveEmpDtls hrEmpLeave = null;
        List<HrLeaveEmpDtls> hrEmpLeaveList = null;

        SalaryRules salRules = new SalaryRules(); // Added By Urvin Shah.

        double CLLeaveBal = 0;
        double CLLeaveTaken = 0;
        double leaveExcess = 0;
        long HPL = Long.parseLong(constantsBundle.getString("HPL"));// Hardcoded
        // removed
        long CL = Long.parseLong(constantsBundle.getString("CL"));// hardcoded
        // removed
        long EL = Long.parseLong(constantsBundle.getString("EL"));// hardcoded
        // removed

        long LWP = Long.parseLong(constantsBundle.getString("LWP"));// hardcoded
        // removed

        // For Deducting allowances and deductions in case of leave
        double PTExcess = 0;
        double groupInsurance = 0;
        double GPFExcess = 0;
        double LoanRecoveryExcess = 0;
        double TRAExcess = 0;
        double HRAExcess = 0;
        double CLAExcess = 0;
        double MAExcess = 0;
        double WAExcess = 0;
        long leavetype = 0;
        double persExcess = 0;
        double splExcess = 0;
        double LWPExcess = 0;
        double LWPInmonth = 0;
        int LWPFlag = 0;
        // persExcess and splExcess are considered only for current month as it
        // may happened that pesonal pay or spl pay was not given to him ion
        // prev month or it may be variable

        logger.info("months given from leave function is " + monthGiven + " and year " + yearGiven);

        // for argument from leave emp mpg table

        hrEmpLeaveList = empLeaveDao.getAllEmpLeaveData(empid);
        // List<HrLeaveEmpDtls>
        // hrEmpLeaveList=empLeaveDao.getEmpCurLeaveData(empid);
        // Added by Paurav for calculating all the leaves
        Calendar cal = Calendar.getInstance();
        int noOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        boolean ELHPLflag = false;
        for (int li = 0; li < hrEmpLeaveList.size(); li++)
        {

            hrEmpLeave = hrEmpLeaveList.get(li);
            // edited by Ankit Bhatt for Generating Paybill for Given month &
            // year.
            // Date dt = new Date();
            int frmMonth = monthofDate(hrEmpLeave.getLeaveFromDate());
            int frmYear = yearofDate(hrEmpLeave.getLeaveToDate());
            SimpleDateFormat sdfObj = new SimpleDateFormat("dd");
            int frmDay = Integer.parseInt(sdfObj.format(hrEmpLeave.getLeaveFromDate()));
            // hardcoded leave ID
            // Need to verity by Manoj.
            leavetype = hrEmpLeave.getHrEssLeaveMst().getLeavecode();

            if (leavetype == EL || leavetype == HPL)
                ELHPLflag = true;
            else
                ELHPLflag = false;
            // if leave type is not EL or HPL basic will also take LS
            // LS appears only in EL or HPL

            Calendar cal1 = new GregorianCalendar(frmYear, frmMonth - 1, 1);
            int frmNoOfDays = cal1.getActualMaximum(cal1.DAY_OF_MONTH);
            int toMonth = monthofDate(hrEmpLeave.getLeaveToDate());
            int toYear = yearofDate(hrEmpLeave.getLeaveToDate());
            logger.info(dayofDate(hrEmpLeave.getLeaveFromDate()) + "******"
                    + monthofDate(hrEmpLeave.getLeaveFromDate()) + "****" + yearofDate(hrEmpLeave.getLeaveFromDate())
                    + "**************" + frmYear + "*******" + frmMonth);

            // Allowance deduction considered after 30 and 180 days hence
            // considered in 2nd and 4th case only

            // Added by Paurav

            int LeaveInmonth = salRules.calculateLeaveDays(hrEmpLeave.getLeaveFromDate(), hrEmpLeave.getLeaveToDate(),
                    monthGiven, yearGiven);
            if (LeaveInmonth < 0)
                LeaveInmonth = 0;
            String Bal = hrEmpLeave.getLeaveAvailBal() + "";
            int leaveBal = Integer.parseInt(Bal);
            String leavetaken = hrEmpLeave.getLeaveTaken() + "";
            int LeaveTaken = Integer.parseInt(leavetaken);

            int Excess = salRules.calculateLeaveExcess(hrEmpLeave.getLeaveFromDate(), hrEmpLeave.getLeaveToDate(),
                    leaveBal, LeaveTaken, monthGiven, yearGiven);
            if (Excess < 0)
                Excess = 0;
            // ///////NoTE: CLLeaveBal doenot come in processing till now
            // ///////this bolck is sufficient if current month sleave is only
            // considered
            if (ELHPLflag)// take care of LS
            {
                CLLeaveTaken += LeaveInmonth;
            } else
            // Atleast add excess as excess is deducted from laeve total
            // to get LS
            {
                CLLeaveTaken += Excess;
                // No need to worry about HPL excess it wont com ein else part
                // :) we will add leave taken in that case and calculate LS
                // Also forget about other types of exces such as TRAExcess
                // ,HRAexcess etc they are not deducted from basic TO calculate
                // LS BUT DEDUCTION is increased
            }
            Calendar startDate = Calendar.getInstance();

            startDate.setTime(hrEmpLeave.getLeaveFromDate());
            Calendar endDate = Calendar.getInstance();
            endDate.setTime(hrEmpLeave.getLeaveToDate());

            Calendar calStPayroll = Calendar.getInstance();
            Calendar calEndPayroll = Calendar.getInstance();
            calStPayroll.set(Calendar.DAY_OF_MONTH, 1);
            calEndPayroll.set(Calendar.DAY_OF_MONTH, 30);
            logger.info("the minimum Date is:-" + calStPayroll.getTime());
            logger.info("the minimum Date is:-" + calEndPayroll.getTime());

            if (leavetype == LWP)
            {
                /*
                 * if(startDate.before(arg0)){
                 * 
                 * }
                 */
                logger.info("The End Date id:-" + endDate.getTime());
                logger.info("The Start Date is:-" + calStPayroll.getTime());
                if (endDate.getTime().after(calStPayroll.getTime()))
                {
                    LWPInmonth = LeaveInmonth;
                    LWPExcess = daysBetween(startDate, endDate) + 1;
                    logger.info("The Total no of Days of LWP are:-" + LWPExcess);
                }

            }

            if (LWPExcess >= noOfDays)
                LWPFlag = 1;
            logger.info("from generatebill service the LWPExcess " + LWPExcess + " and noofDays " + noOfDays
                    + " LWPFlag " + LWPFlag);

            // ////////////////No need to interfere in excess it will be
            // deducted in all cases
            leaveExcess += Excess;
            if (leavetype == HPL)
            {
                persExcess += Excess;
                splExcess += Excess;
            }
            logger.info(Excess + "Leaves taken " + leaveBal + "in Current " + LeaveTaken + "Month  " + CLLeaveTaken);
            if (leavetype == HPL)
            {
                leaveExcess += (LeaveInmonth - Excess) / 2.0;
                persExcess += (LeaveInmonth - Excess) / 2.0;
                splExcess += (LeaveInmonth - Excess) / 2.0;
            }

            // For leave greater than 30 days
            if (leavetype != CL && LeaveTaken > 30)
            {
                int x = salRules.calculateLeaveExcess(hrEmpLeave.getLeaveFromDate(), hrEmpLeave.getLeaveToDate(), 30,
                        LeaveTaken, monthGiven, yearGiven);
                if (x > 0)
                    TRAExcess += x;
            }
            // if(TRAExcess<0)
            // TRAExcess=0;
            WAExcess = TRAExcess;// same rule for both
            // For leave greater than 180 days
            if (leavetype != CL && LeaveTaken > 180)
            {
                int x = salRules.calculateLeaveExcess(hrEmpLeave.getLeaveFromDate(), hrEmpLeave.getLeaveToDate(), 180,
                        LeaveTaken, monthGiven, yearGiven);
                if (x > 0)
                    // HRAExcess+=x;
                    // if(HRAExcess<0)
                    // HRAExcess=0;
                    CLAExcess = HRAExcess;
                MAExcess = HRAExcess;
            }
            if (leavetype == CL)
            {
                logger.info("leave in month is---->>>" + LeaveInmonth);
                CLLeaveTaken = LeaveTaken;
                CLLeaveBal = leaveBal;

                PTExcess += LeaveInmonth;
                groupInsurance += LeaveInmonth;
                GPFExcess += LeaveInmonth;
                LoanRecoveryExcess += LeaveInmonth;
            }
            /*
             * if(hrEmpLeave.getLeaveAvailBal()<hrEmpLeave.getLeaveTaken()) {
             * long diffLeaves = hrEmpLeave.getLeaveTaken() -
             * hrEmpLeave.getLeaveAvailBal();
             * 
             * if(leavesInMonth >= diffLeaves) { leaveExcess+= diffLeaves; }
             * else { leaveExcess += leavesInMonth; } }
             */
            // Ended By Paurav
            // Code for calulation of leave after Previous month payroll
            // generation
            int Month = monthGiven;
            int Year = yearGiven;
            /*
             * if(monthGiven>1) --Month; else { Month=12; --Year; }
             */
            logger.info("Going to execute Leave function from PaybillDAO");
            // String cols[] = { "hrEisEmpMst", "month", "year", "approveFlag"
            // };
            HrEisEmpMst hrEisEmpMst = new HrEisEmpMst();
            hrEisEmpMst.setEmpId(empid);
            double dMonth = Month;
            double dYear = Year;
            // Object vals[] = { hrEisEmpMst, dMonth, dYear, new Long(1) };
            PayBillDAOImpl paybillDAO1 = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());

            List<HrPayPaybill> empdata = paybillDAO1.getPaybillListForLeave(empid, new Double(dMonth).longValue(),
                    new Double(dYear).longValue(), 1);

            Date payrolldate = new Date();
            if (empdata != null && empdata.size() > 0)
            {
                payrolldate = empdata.get(0).getCreatedDate();

            }
            empdata = null;
            // Calendar Cal = new GregorianCalendar(Year,Month-1,1);
            Calendar Cal = new GregorianCalendar(Year, monthGiven, 1);
            noOfDays = Cal.getActualMaximum(5);

            logger.info(payrolldate + "Inside Payrolldate.before(Leave created date)" + hrEmpLeave.getCreatedDate());

            // if(payrolldate.before(hrEmpLeave.getCreatedDate()))
            // if(hrEmpLeave.getCreatedDate().before(payrolldate))
            {

                logger.info("Inside Payrolldate.before(Leave created date)");
                // For first 2nd and 3rd cases leave balance has been already
                // calculated for current month payroll :)
                logger.info("Inside Payrolldate.before " + Month + "==" + frmMonth + "&&" + Month + "==" + toMonth
                        + " && (" + Year + "==" + frmYear + " &&" + Year + "==" + toYear);

                if (Month == frmMonth && Month == toMonth && (Year == frmYear && Year == toYear))
                {
                    if (hrEmpLeave.getLeaveAvailBal() < hrEmpLeave.getLeaveTaken())
                        leaveExcess += hrEmpLeave.getLeaveTaken() - hrEmpLeave.getLeaveAvailBal();
                    CLLeaveBal += (int) hrEmpLeave.getLeaveAvailBal();

                    logger.info("Inside Payrolldate.before CLLeaveBal " + CLLeaveBal);

                    if (ELHPLflag)// take care of LS
                    {
                        CLLeaveTaken += (int) hrEmpLeave.getLeaveTaken();
                    } else
                    // Atleast add excess as this excess is deducted from
                    // leave total to get LS
                    {
                        if (hrEmpLeave.getLeaveAvailBal() < hrEmpLeave.getLeaveTaken())
                            CLLeaveTaken += hrEmpLeave.getLeaveTaken() - hrEmpLeave.getLeaveAvailBal();
                        // No need to worry about HPL excess it wont come in
                        // else part :) we will add leave taken in that case and
                        // calculate LS
                    }

                    if (leavetype == HPL)
                    {
                        if (hrEmpLeave.getLeaveAvailBal() < hrEmpLeave.getLeaveTaken())
                        {
                            logger.info("IF cond leaveExcess in HPL ");
                            leaveExcess += hrEmpLeave.getLeaveAvailBal() / 2.0;
                            logger.info("IF cond leaveExcess in HPL " + leaveExcess);
                        } else
                        {
                            logger.info("leaveExcess in HPL ");
                            leaveExcess += hrEmpLeave.getLeaveTaken() / 2.0;
                            logger.info("leaveExcess in HPL " + leaveExcess);
                        }
                    }
                    // else if(leavetype==LWP)
                    // leaveExcess+=hrEmpLeave.getLeaveTaken();

                    if (leavetype != CL && hrEmpLeave.getLeaveTaken() > 30)
                    {
                        TRAExcess += 1;
                        WAExcess = TRAExcess;// same rule for both
                    }
                    if (leavetype == CL)
                    {
                        String leaveimonth = hrEmpLeave.getLeaveTaken() + "";
                        LeaveInmonth = Integer.parseInt(leaveimonth);
                        PTExcess += LeaveInmonth;
                        groupInsurance += LeaveInmonth;
                        GPFExcess += LeaveInmonth;
                        LoanRecoveryExcess += LeaveInmonth;
                    }

                } else if (((Month + (Year - frmYear) * 12) > frmMonth && Month < (toMonth + (toYear - Year) * 12) && (Year >= frmYear && Year <= toYear)))
                {
                    logger.info("Month+(Year-frmYear)*12" + Month + (Year - frmYear) * 12 + ">" + frmMonth);
                    // GregorianCalendar da1 = new
                    // GregorianCalendar(2004,0,1,1,1,1);
                    GregorianCalendar da2 = new GregorianCalendar(Year, Month - 1, noOfDays);
                    long d1 = hrEmpLeave.getLeaveFromDate().getTime();
                    long d2 = da2.getTime().getTime();
                    long difMil = d2 - d1;
                    long milPerDay = 1000 * 60 * 60 * 24;
                    long days = difMil / milPerDay + 1;
                    long excessDays = (days - hrEmpLeave.getLeaveAvailBal()) > 0 ? (days - hrEmpLeave
                            .getLeaveAvailBal()) : 0;
                    if (hrEmpLeave.getLeaveAvailBal() < hrEmpLeave.getLeaveTaken())
                        leaveExcess += excessDays;
                    // CLLeaveBal +=(int)hrEmpLeave.getLeaveAvailBal();

                    if (ELHPLflag)// take care of LS
                    {
                        CLLeaveTaken += (int) days;
                    } else
                    // Atleast add excess as this excess is deducted from
                    // leave total to get LS
                    {
                        if (hrEmpLeave.getLeaveAvailBal() < hrEmpLeave.getLeaveTaken())
                            CLLeaveTaken += excessDays;
                        // No need to worry about HPL excess it wont come in
                        // else part :) we will add leave taken in that case and
                        // calculate LS
                    }

                    if (leavetype == HPL)
                    {
                        if (hrEmpLeave.getLeaveAvailBal() < hrEmpLeave.getLeaveTaken())
                            leaveExcess += hrEmpLeave.getLeaveAvailBal() / 2.0;
                        else
                            leaveExcess += days / 2.0;
                    }
                    // else if(leavetype==LWP)
                    // leaveExcess+=days;
                    if (leavetype == CL)
                    {
                        String leaveimonth = days + "";
                        LeaveInmonth = Integer.parseInt(leaveimonth);
                        PTExcess += LeaveInmonth;
                        groupInsurance += LeaveInmonth;
                        GPFExcess += LeaveInmonth;
                        LoanRecoveryExcess += LeaveInmonth;
                    }
                } else if (Month == frmMonth && Month < (toMonth + (toYear - Year) * 12)
                        && (Year == frmYear && Year <= toYear))
                {
                    if (hrEmpLeave.getLeaveAvailBal() < hrEmpLeave.getLeaveTaken())
                    {
                        GregorianCalendar da2 = new GregorianCalendar(Year, Month - 1, noOfDays);
                        long d1 = hrEmpLeave.getLeaveFromDate().getTime();
                        long d2 = da2.getTime().getTime();
                        long difMil = d2 - d1;
                        long milPerDay = 1000 * 60 * 60 * 24;
                        long days = difMil / milPerDay + 1;
                        days -= hrEmpLeave.getLeaveAvailBal();
                        if (days > 0)
                            leaveExcess += days;
                        if (leavetype == HPL)
                        {
                            // We need to deduct from balance as it is HPL
                            leaveExcess += days > 0 ? ((noOfDays) - days) / 2.0 : (noOfDays) / 2.0;
                        }

                        if (ELHPLflag)// take care of LS
                        {
                            CLLeaveTaken += frmNoOfDays - frmDay + 1;
                        } else
                        // Atleast add excess as this excess is deducted
                        // from leave total to get LS
                        {
                            if (days > 0)
                                CLLeaveTaken += days;
                            // No need to worry about HPL excess it wont come in
                            // else part :) we will add leave taken in that case
                            // and calculate LS
                        }

                    }
                    // CLLeaveBal +=(int)hrEmpLeave.getLeaveAvailBal();

                    if (leavetype == HPL)
                    {
                        if (hrEmpLeave.getLeaveAvailBal() < hrEmpLeave.getLeaveTaken())
                        {
                        }
                        // considerd above
                        // leaveExcess+=hrEmpLeave.getLeaveAvailBal()/2.0;
                        else
                            leaveExcess += (frmNoOfDays - frmDay + 1) / 2.0;
                    }
                    // else if(leavetype==LWP)
                    // leaveExcess+=frmNoOfDays-frmDay+1;

                    if (frmDay == 1 && frmNoOfDays > 30 && leavetype != CL)
                    {
                        TRAExcess += 1;
                        WAExcess = TRAExcess;// same rule for both
                    }
                    if (leavetype == CL)
                    {
                        String leaveimonth = frmNoOfDays - frmDay + 1 + "";
                        LeaveInmonth = Integer.parseInt(leaveimonth);
                        PTExcess += LeaveInmonth;
                        groupInsurance += LeaveInmonth;
                        GPFExcess += LeaveInmonth;
                        LoanRecoveryExcess += LeaveInmonth;
                    }
                } else if ((Month + (Year - frmYear) * 12) > frmMonth && Month == toMonth
                        && (Year >= frmYear && Year == toYear))
                {

                    if (hrEmpLeave.getLeaveAvailBal() < hrEmpLeave.getLeaveTaken())
                        leaveExcess += hrEmpLeave.getLeaveTaken() - hrEmpLeave.getLeaveAvailBal();
                    CLLeaveBal += (int) hrEmpLeave.getLeaveAvailBal();

                    if (ELHPLflag)// take care of LS
                    {
                        CLLeaveTaken += (int) hrEmpLeave.getLeaveTaken();
                    } else
                    // Atleast add excess as this excess is deducted from
                    // leave total to get LS
                    {
                        if (hrEmpLeave.getLeaveAvailBal() < hrEmpLeave.getLeaveTaken())
                            CLLeaveTaken += hrEmpLeave.getLeaveTaken() - hrEmpLeave.getLeaveAvailBal();
                        // No need to worry about HPL excess it wont come in
                        // else part :) we will add leave taken in that case and
                        // calculate LS
                    }

                    if (leavetype == HPL)
                    {
                        if (hrEmpLeave.getLeaveAvailBal() < hrEmpLeave.getLeaveTaken())
                            leaveExcess += hrEmpLeave.getLeaveAvailBal() / 2.0;
                        else
                            leaveExcess += (hrEmpLeave.getLeaveTaken()) / 2.0;
                    }
                    // else if(leavetype==LWP)
                    // leaveExcess+=hrEmpLeave.getLeaveTaken();
                    if (leavetype == CL)
                    {
                        String leaveimonth = hrEmpLeave.getLeaveTaken() + "";
                        LeaveInmonth = Integer.parseInt(leaveimonth);
                        PTExcess += LeaveInmonth;
                        groupInsurance += LeaveInmonth;
                        GPFExcess += LeaveInmonth;
                        LoanRecoveryExcess += LeaveInmonth;

                    }

                } else
                {
                    if (leavetype == CL)
                    {
                        logger.info("the leave type is CL ");
                        CLLeaveBal += (int) hrEmpLeave.getLeaveAvailBal();
                        CLLeaveTaken += hrEmpLeave.getLeaveTaken() - leaveExcess;
                    }
                }
            }
            /*
             * else { CLLeaveTaken=noOfDays; }
             */

        }

        hrEmpLeaveList = null;

        logger.info("the CLLeaveBal " + CLLeaveBal);
        logger.info("the CLLeaveTaken " + CLLeaveTaken);
        logger.info("the LeaveExcess " + leaveExcess);
        logger.info("the TRAExcess " + TRAExcess);
        logger.info("the HRAExcess " + HRAExcess);
        logger.info("the CLAExcess " + CLAExcess);
        logger.info("the MAExcess " + MAExcess);
        logger.info("the persExcess " + persExcess);
        logger.info("the splExcess " + splExcess);
        logger.info("the LWPExcess " + LWPExcess);
        logger.info("the LWPInmonth " + LWPInmonth);

        logger.info("the LWPFlag " + LWPFlag);

        empLeaveMap.put("CLLeaveBal", CLLeaveBal);
        empLeaveMap.put("CLLeaveTaken", CLLeaveTaken);
        empLeaveMap.put("leaveExcess", leaveExcess);
        empLeaveMap.put("TRAExcess", TRAExcess);
        empLeaveMap.put("HRAExcess", HRAExcess);
        empLeaveMap.put("CLAExcess", CLAExcess);
        empLeaveMap.put("MAExcess", MAExcess);
        empLeaveMap.put("WAExcess", WAExcess);
        empLeaveMap.put("leaveType", leavetype);
        empLeaveMap.put("persExcess", persExcess);
        empLeaveMap.put("splExcess", splExcess);

        empLeaveMap.put("LWPExcess", LWPExcess);
        empLeaveMap.put("LWPInmonth", LWPInmonth);
        empLeaveMap.put("LWPFlag", LWPFlag);

        return empLeaveMap;
    }

    public static long daysBetween(Calendar startDate, Calendar endDate)
    {
        Calendar date = (Calendar) startDate.clone();
        long daysBetween = 0;
        while (date.before(endDate))
        {
            date.add(Calendar.DAY_OF_MONTH, 1);
            daysBetween++;
        }
        return daysBetween;
    }

    // added by manish

    public HrPayPaybill readPaybillObjet(long postId, int month, int year, int approvalFlag, ServiceLocator serv)
            throws Exception
    {
        logger.info("Inside readPaybillObjet for month ");
        PayBillDAOImpl paybillDAOImpl = new PayBillDAOImpl(HrPayPaybill.class, serv.getSessionFactory());
        HrPayPaybill paybill = paybillDAOImpl.getPaybillDataByPost(postId, approvalFlag, month, year);
        return paybill;
    }

    // ended by manish
}