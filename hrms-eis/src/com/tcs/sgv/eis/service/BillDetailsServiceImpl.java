package com.tcs.sgv.eis.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.tcs.sgv.common.constant.DBConstants;
import com.tcs.sgv.common.helper.ReportExportHelper;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.utils.StringUtility;
import com.tcs.sgv.core.constant.ErrorConstants;
import com.tcs.sgv.core.service.ServiceImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.core.valueobject.ResultObject;
import com.tcs.sgv.eis.dao.BillDetailsDAO;
import com.tcs.sgv.eis.dao.BillDetailsDAOImpl;
import com.tcs.sgv.eis.valueobject.CMPRecord;
import com.tcs.sgv.eis.valueobject.HrPayCmpDtls;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class BillDetailsServiceImpl extends ServiceImpl
{

    private static Logger logger = Logger.getLogger(BillDetailsServiceImpl.class);

    private String convertTextFile(final List record)
    {

        final StringBuffer returnString = new StringBuffer();
        System.getProperty("line.separator");
        final Iterator<CMPRecord> it = record.iterator();

        while (it.hasNext())
        {
            final CMPRecord employeerec = it.next();
            returnString.append(employeerec.toString());
            returnString.append("\r\n");
        }

        return returnString.toString();
    }

    public ResultObject generateExcelForBill(final Map objectArgs)
    {
        logger.info("Inside Get generateExcelForBill");
        ResultObject resultObject = new ResultObject(ErrorConstants.SUCCESS);
        final ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
        logger.info("serviceLocator" + serviceLocator.getSessionFactory());

        HttpServletRequest request = null;
        // logger.info("current session hash Code is ::: " +
        // serviceLocator.getSessionFactory().getCurrentSession().hashCode());
        if (objectArgs.get("requestObj") != null)
        {
            request = (HttpServletRequest) objectArgs.get("requestObj");
            request.getSession();
        }

        // BillDetailsDAO billDetailsDAO = new
        // BillDetailsDAOImpl(BillDetailsDAOImpl.class,serviceLocator.getSessionFactory());
        final BillDetailsDAO billDetailsDAO = new BillDetailsDAOImpl(CMPRecord.class, serviceLocator
                .getSessionFactory());

        Boolean isError = false;
        final StringBuffer status = new StringBuffer();

        String cmpFileFlag = "";
        new StringBuilder();
        String recordList = "";

        try
        {
            int paybillMonth = 0;
            int paybillYear = 0;
            String authNo = null;
            List lstPaybillId = null;
            Object objPaybill[];
            new HashMap();
            Long paybillIdForNgrList = 0l;
            int intStatus;

            if (objectArgs.get("authNo") != null && !objectArgs.get("authNo").equals(""))
            {
                authNo = objectArgs.get("authNo").toString();
            } else
            {
                if (StringUtility.getParameter("AuthNo", request) != null
                        && StringUtility.getParameter("AuthNo", request) != "")
                {
                    authNo = StringUtility.getParameter("AuthNo", request);
                    logger.info("AuthNo is------------" + authNo);
                    objectArgs.put("webServiceCall", "MS");
                    objectArgs.put("authNo", authNo);
                }

            }
            if (objectArgs.get("webServiceCall") == null)
            {
                objectArgs.put("webServiceCall", "MS");
            }

            billDetailsDAO.modifyCMPfileGen(authNo);
            
            // Check for valid auth_no

            lstPaybillId = billDetailsDAO.getPaybillIdFromAuthNo(authNo);
            logger.info("lstPaybillId-----------" + lstPaybillId);

            if (lstPaybillId != null && !lstPaybillId.isEmpty())
            {
                final Iterator itPaybill = lstPaybillId.iterator();
                logger.info("Inside iff");
                while (itPaybill.hasNext())
                {
                    objPaybill = (Object[]) itPaybill.next();
                    if (objPaybill[0] != null)
                    {
                        paybillMonth = Integer.parseInt(objPaybill[0].toString());
                    }
                    objectArgs.put("paybillMonth", paybillMonth);
                    logger.info("paybillMonth====" + paybillMonth);
                    if (objPaybill[1] != null)
                    {
                        paybillYear = Integer.parseInt(objPaybill[1].toString());
                    }
                    objectArgs.put("paybillYear", paybillYear);
                    logger.info("paybillYear====" + paybillYear);
                    if (objPaybill[2] != null)
                    {
                        paybillIdForNgrList = Long.parseLong(objPaybill[2].toString());
                    }
                    objectArgs.put("paybillIdForNgrList", paybillIdForNgrList);
                    logger.info("paybillYear====" + paybillIdForNgrList);
                    if (objPaybill[3] != null)
                    {
                        intStatus = Integer.parseInt(objPaybill[3].toString());

                        if (intStatus == 3 || intStatus == -1)
                        {
                            isError = true;
                            status.append("02~");// auth_no is rejected by Beams
                        }
                    }
                    if (objPaybill[4] != null)
                    {
                        cmpFileFlag = objPaybill[4].toString();
                        // logger.info("cmpFileFlag===="+cmpFileFlag);
                    }

                }
            }
            // auth_no is not present
            else
            {
                isError = true;
                status.append("01~");// Invalid auth_no
            }
            if (!isError)
            {

            	 
                // Check if File is PreviouslyGenerated

                if (cmpFileFlag.equals("DV") || cmpFileFlag.equals("MD") || cmpFileFlag.equals("WD"))
                {
                    logger.info("cmpFileFlag===1");
                    List<CMPRecord> records = null;
                    final String criteria = "authNo";
                    final String value = authNo + this.insertExtraSpace(authNo.length(), 25);
                    logger.info("cmpFileFlag===2");
                    records = billDetailsDAO.getListByColumnAndValue(criteria, value);
                    logger.info("cmpFileFlag===3");
                    final XStream xStream = new XStream(new DomDriver("UTF-8"));
                    xStream.alias("EmployeeRecord", CMPRecord.class);
                    xStream.registerConverter(new CMPRecordConverter());
                    logger.info("cmpFileFlag===4");
                    if (objectArgs.get("webServiceCall").equals("WBS"))
                    {
                        recordList = xStream.toXML(records);
                    } else
                    {
                        recordList = this.convertTextFile(records);
                        String fileName = "";
                        if (records.size() > 0)
                        {
                            fileName = records.get(0).getAuthNo().trim() + "_" + records.get(0).getNoOfPayees().trim()
                                    + "_" + records.get(0).getBillNetAmt().trim();
                        }
                        logger.info("fileName===3" + fileName);
                        objectArgs.put("FileName", fileName);
                    }
                    logger.info("cmpFileFlag===5");
                    // objectArgs.put("FileName", authNo);
                    logger.info("cmpFileFlag===6");
                    objectArgs.put("EmployeeRecord", recordList);
                    logger.info("cmpFileFlag===7");
                } else
                {

                    recordList = this.insertAndGenerateXMLData(objectArgs);
                }
            }
            objectArgs.put("isError", isError);
            objectArgs.put("status", status);
            logger.info("cmpFileFlag===8");

            if (objectArgs.get("webServiceCall").equals("MS"))
            {
                logger.info("cmpFileFlag===9");
                resultObject.setResultValue(objectArgs);
                final Map lDetailMap = new HashMap();
                lDetailMap.put(DBConstants.DIS_TEXTFILE, recordList.toString());
                final ReportExportHelper rptExpHelper = new ReportExportHelper();
                rptExpHelper.getExportData(resultObject, DBConstants.DIS_TEXTFILE, objectArgs, lDetailMap, false);

            }
        } catch (final Exception e)
        {
            resultObject = new ResultObject(ErrorConstants.ERROR);
            resultObject.setResultCode(-1);
            resultObject.setViewName("errorPage");
            logger.info(e);
        }
        return resultObject;
    }

    public ResultObject getBillReport(final Map objectArgs) throws Exception
    {

        logger.info("Entering into getBillReport ");
        final ResultObject objRes = new ResultObject(ErrorConstants.ERROR);
        try
        {

            objRes.setResultCode(ErrorConstants.SUCCESS);
            objRes.setResultValue(objectArgs);
            objRes.setViewName("billDetails");
        } catch (final Exception e)
        {
            logger.info("Null Pointer Exception Ocuures..getBillReport of BillDetailsServiceImpl");
            logger.error("Error is: " + e.getMessage());
            objectArgs.put("msg", "There is Some Problem.Please Try Again Later.");
            objRes.setResultValue(objectArgs);
            objRes.setViewName("errorInsert");
        }
        return objRes;
    }

    private String insertAndGenerateXMLData(final Map objectArgs)
    {

        List commonDetails = null;
        final ServiceLocator serviceLocator = (ServiceLocator) objectArgs.get("serviceLocator");
        final BillDetailsDAO billDetailsDAO = new BillDetailsDAOImpl(CMPRecord.class, serviceLocator
                .getSessionFactory());
        final BillDetailsDAO cmpDtlsDao = new BillDetailsDAOImpl(HrPayCmpDtls.class, serviceLocator.getSessionFactory());
        String authNo = (String) objectArgs.get("authNo");
        commonDetails = billDetailsDAO.getCommonDetails(authNo);
        logger.info("commonDetails" + commonDetails.size());
        int noOfPayees = commonDetails.size();

        logger.info("noOfPayees before ddo is" + noOfPayees);
        // lstNgrAmount = billDetailsDAO.getNonGovDeductionAmt(authNo);
        // logger.info("lstNgrAmount-----------"+lstNgrAmount);
        List ngrDtlsList = null;
        final char bulkUploadFlag = 'A';
        Object objPaybill[];
        Object objNgrAmount[];
        List lstNgrAmount = null;
        List otherDed = null;
        Long ngrAmount = 0L;

        final Map ngrEmpDedMap = new HashMap();
        String ddoEmail = "-";
        String ddoContact = "-";
        Long ddoForPayeesPrv = null;
        new ArrayList<Object>();
        new ArrayList<Object>();
        new ArrayList<Object>();
        new ArrayList<Object>();
        final StringBuilder emptextFile = new StringBuilder();
        final List<CMPRecord> cmprecordList = new ArrayList<CMPRecord>();
        Object objTotal[];
        Object objTotalDed[];
        String uidSeededFlag;
        String accountOrUIDNo = "-";
        String uidNo;
        String accNo;
        String billIdentifier = "J";

        String fileName = "";
        String payBillGrpId = null;
        String treasuryCode = "";
        String ddoCode = "";
        String billNetAmount = "";
        String schemeCode = "";
        String ddoName = null;
        String ddoAccNo = "";
        String ddoIFSCode = "";
        String payBillId = "";
        List ddoDtls;
        Long empNetAmount = 0L;
        String empName;
        String ifscCode;
        String netAmountStr;
        String billdate = "";
        String emailId;
        String contactNo;
        String billDateStr = "N";
        String billNetAmtStr = "N";
        String ngrAmountStr = "";
        Long totalDed = 0L;
        Long tempNetZero = 0l;
        Long prvDdoCode = null;
        String reptDdoCode = null;
        long PRN = 0L;
        final Long paybillGrpId = Long.parseLong(objectArgs.get("paybillIdForNgrList").toString());

        // for(int i=0;i<lstPaybillId.size();i++){
        ngrDtlsList = billDetailsDAO.getNonGovDedFromPaybillID(authNo);
        if (ngrDtlsList != null && ngrDtlsList.size() > 0)
        {

            final Iterator it = ngrDtlsList.iterator();
            while (it.hasNext())
            {
                objPaybill = (Object[]) it.next();
                if (objPaybill[0] != null)
                {
                    ngrEmpDedMap.put(objPaybill[0].toString(), Long.parseLong(objPaybill[1].toString()));
                }

                if (ddoForPayeesPrv == null)
                {
                    noOfPayees++;
                    ddoForPayeesPrv = objPaybill[2] != null ? Long.parseLong(objPaybill[2].toString()) : 0l;
                } else
                {
                    if (!ddoForPayeesPrv.equals(Long.parseLong(objPaybill[2].toString())))
                    {
                        noOfPayees++;
                        ddoForPayeesPrv = objPaybill[2] != null ? Long.parseLong(objPaybill[2].toString()) : 0l;
                    }
                }
                ngrAmount += Long.parseLong(objPaybill[1].toString());
            }
        }
        // }

        logger.info("noOfPayees after ddo is" + noOfPayees);
        logger.info("ngrAmount final is" + ngrAmount);
    	//Addidng final number of payee is as below
		
		noOfPayees = commonDetails.size();
		int validDDOCount=billDetailsDAO.getCount(authNo);
		
		noOfPayees=noOfPayees+validDDOCount;
		//
        /*
         * if(lstNgrAmount != null && !lstNgrAmount.isEmpty()){ Iterator
         * itNgrAmount = lstNgrAmount.iterator();
         * logger.info("Inside iff for lstNgrAmount"); while
         * (itNgrAmount.hasNext()) { objNgrAmount = (Object[])
         * itNgrAmount.next(); if(objNgrAmount[0]!=null){
         * ngrAmount=Long.parseLong((objNgrAmount[0].toString())); if(ngrAmount
         * > 0L){ noOfPayees++; } } } }
         */
        // For Pay By Date
        final int paybillMonth = Integer.parseInt(objectArgs.get("paybillMonth").toString());
        final int paybillYear = Integer.parseInt(objectArgs.get("paybillYear").toString());
        logger.info("Pay By date Started");
        final DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        final Calendar calendar = Calendar.getInstance();
        calendar.clear();

        calendar.set(Calendar.MONTH, paybillMonth - 1);
        calendar.set(Calendar.YEAR, paybillYear);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        final Date payDate = calendar.getTime();
        final String payByDate = sdf.format(payDate);
        logger.info("payDate====" + payByDate);

        new ReportExportHelper();
        new ArrayList();
        new ArrayList();
        new HashMap();
        final Map net = new HashMap();
        System.getProperty("line.separator");

        if (commonDetails != null && !commonDetails.isEmpty())
        {
            final Iterator itTotal = commonDetails.iterator();
            while (itTotal.hasNext())
            {
                final CMPRecord record = new CMPRecord();
                objTotal = (Object[]) itTotal.next();
                new ArrayList();

                // Checking for UID Based
                accNo = (objTotal[9] != null ? objTotal[9] : "-").toString();
                uidNo = (objTotal[12] != null ? objTotal[12] : "-").toString();
                uidSeededFlag = (objTotal[13] != null ? objTotal[13] : "-").toString();
                payBillId = (objTotal[5] != null ? objTotal[5] : "-").toString();
                // If UID seeded then UID No will be passed else Account No
                if (uidSeededFlag.equals("S"))
                {
                    if (!uidNo.equals("-"))
                    {
                        accountOrUIDNo = uidNo;
                    }
                    billIdentifier = "J";
                } else
                {
                    if (!accNo.equals("-"))
                    {
                        accountOrUIDNo = accNo;
                    }
                }

                // Adding employee details starts from here
                // empDetails.add(bulkUploadFlag);
                emptextFile.append(bulkUploadFlag);
                record.setBulkUploadFlag("A");
                // //emptextFile.append(" ");
                // empDetails.add(billIdentifier);
                emptextFile.append(billIdentifier);
                record.setBillId(billIdentifier);
                // //emptextFile.append(" ");
                treasuryCode = (objTotal[0] != null ? objTotal[0] : "").toString();
                // empDetails.add(treasuryCode);
                emptextFile.append(treasuryCode);
                record.setTreasuryCode(treasuryCode);
                // //emptextFile.append(" ");
                if (PRN == 0)
                {
                    PRN = billDetailsDAO.PRNGenerator(treasuryCode, noOfPayees);
                }
                logger.info("PRN number----------" + PRN);
                reptDdoCode = (objTotal[1] != null ? objTotal[1] : "").toString();
                // empDetails.add(ddoCode);
                emptextFile.append(reptDdoCode.substring(4));
                record.setDdoCode(reptDdoCode.substring(4));
                // //emptextFile.append(" ");
                // emp name
                empName = (objTotal[8] != null ? objTotal[8] : "").toString();
                /*
                 * for(int i=empName.length();i<50;i++){ empName+=" "; }
                 */
                empName = empName + this.insertExtraSpace(empName.length(), 50);
                if (empName.length() > 50)
                {
                    empName = empName.substring(0, 50);
                }
                emptextFile.append(empName);
                record.setBenefName(empName);
                // emptextFile.append(" ");
                logger.info("emp name length " + empName.length());

                accountOrUIDNo = accountOrUIDNo + this.insertExtraSpace(accountOrUIDNo.length(), 20);
                emptextFile.append(accountOrUIDNo);
                record.setAccNo(accountOrUIDNo);
                // emptextFile.append(" ");

                ifscCode = (objTotal[10] != null ? objTotal[10] : "").toString();
                emptextFile.append(ifscCode + this.insertExtraSpace(ifscCode.length(), 11));
                record.setIfscCode(ifscCode + this.insertExtraSpace(ifscCode.length(), 11));
                // emptextFile.append(" ");

                // micr Code

                emptextFile.append(this.insertExtraSpace(0, 9));
                record.setMicrCode(this.insertExtraSpace(0, 9));
                // emptextFile.append(" ");

                // account type

                emptextFile.append(this.insertExtraSpace(0, 2));
                record.setAccType(this.insertExtraSpace(0, 2));
                // emptextFile.append(" ");

                ddoCode = objTotal[16].toString();
                // Amount
                empNetAmount = objTotal[11] != null ? Long.parseLong(objTotal[11].toString()) : 0L;
                if (ngrEmpDedMap.get(payBillId) != null)
                {
                    logger.info("NGR Amount is" + ngrEmpDedMap.get(payBillId).toString());
                    empNetAmount = empNetAmount - Long.parseLong(ngrEmpDedMap.get(payBillId).toString());
                    logger.info("empNetAmount is...." + empNetAmount);
                    if (empNetAmount == 0)
                    {
                        if (prvDdoCode == null)
                        {
                            tempNetZero = 1l;
                            prvDdoCode = Long.parseLong(ddoCode);
                        }

                        else
                        {
                            if (prvDdoCode == Long.parseLong(ddoCode))
                            {
                                tempNetZero = tempNetZero + 1;
                            }

                            else
                            {
                                tempNetZero = 1l;
                                prvDdoCode = Long.parseLong(ddoCode);
                            }
                        }
                        empNetAmount = 1L;
                        net.put(ddoCode, tempNetZero);
                        // ddoList.add(ddoCode);
                    }

                }

                logger.info("DDO Code is: " + ddoCode);
                if (net.get(ddoCode) != null)
                {
                    logger.info("Amount is: " + net.get(ddoCode).toString());
                }

                logger.info("Map contains: " + net);

                netAmountStr = empNetAmount.toString() + "00";
                emptextFile.append(netAmountStr + this.insertExtraSpace(netAmountStr.length(), 13));
                record.setAmount(netAmountStr + this.insertExtraSpace(netAmountStr.length(), 13));
                // emptextFile.append(" ");

                // Payment Reference No
                final String payrefNo = treasuryCode + PRN++;

                record.setPaymentRefNo(payrefNo + this.insertExtraSpace(payrefNo.length(), 25));
                emptextFile.append(payrefNo + this.insertExtraSpace(payrefNo.length(), 25));
                /*
                 * payBillId = (objTotal[5]!=null ? objTotal[5] :
                 * "").toString();
                 * emptextFile.append(payBillId+insertExtraSpace(
                 * payBillId.length(),25));
                 */
                // emptextFile.append(" ");
                // pay By date
                emptextFile.append(payByDate + this.insertExtraSpace(payByDate.length(), 10));
                record.setPayBydate(payByDate + this.insertExtraSpace(payByDate.length(), 10));
                // emptextFile.append(" ");

                // scheme Code
                logger.info("schemeCode is**********" + schemeCode.toLowerCase());
                schemeCode = (objTotal[2] != null ? objTotal[2] : "").toString();
                emptextFile.append(schemeCode + this.insertExtraSpace(schemeCode.length(), 10));
                record.setSchemeCode(schemeCode + this.insertExtraSpace(schemeCode.length(), 10));
                logger.info("schemeCode is**********" + schemeCode);
                // emptextFile.append(" ");

                // ddo Bill NO
                payBillGrpId = null;
                payBillGrpId = (objTotal[6] != null ? objTotal[6] : "").toString();
                logger.info("payBillGrpId is before : " + payBillGrpId);
                payBillGrpId = payBillGrpId + this.insertExtraSpace(payBillGrpId.length(), 75);
                logger.info("payBillGrpId is after : " + payBillGrpId);
                record.setDdoBillNo(payBillGrpId);
                emptextFile.append(payBillGrpId);
                logger.info("payBillGrpId is last : " + payBillGrpId);

                // emptextFile.append(" ");

                // Authorisation Number (BEAMS 25 NUM Mandatory Field
                authNo = authNo + this.insertExtraSpace(authNo.length(), 25);
                record.setAuthNo(authNo);
                emptextFile.append(authNo);

                // emptextFile.append(" ");

                // To Bill No
                emptextFile.append(this.insertExtraSpace(0, 25));
                record.setToBilNo(this.insertExtraSpace(0, 25));
                // emptextFile.append(" ");

                // Bill Date

                billdate = (objTotal[3] != null ? objTotal[3] : "").toString();
                if (billDateStr.equals("N"))
                {
                    billDateStr = billdate;
                }
                emptextFile.append(billdate + this.insertExtraSpace(billdate.length(), 10));
                record.setBillDate(billdate + this.insertExtraSpace(billdate.length(), 10));
                // emptextFile.append(" ");

                // Narration
                emptextFile.append("Salary" + this.insertExtraSpace("Salary".length(), 25));
                record.setNarration("Salary" + this.insertExtraSpace("Salary".length(), 25));
                // emptextFile.append(" ");

                // No Of Payees
                emptextFile.append(new Integer(noOfPayees).toString()
                        + this.insertExtraSpace(new Integer(noOfPayees).toString().length(), 5));
                record.setNoOfPayees(new Integer(noOfPayees).toString()
                        + this.insertExtraSpace(new Integer(noOfPayees).toString().length(), 5));
                // emptextFile.append(" ");

                // bill Net AMount

                billNetAmount = (objTotal[4] != null ? objTotal[4] : "").toString();
                billNetAmtStr = billNetAmount + "00";
                emptextFile.append(billNetAmtStr + this.insertExtraSpace(billNetAmtStr.length(), 13));
                record.setBillNetAmt(billNetAmtStr + this.insertExtraSpace(billNetAmtStr.length(), 13));
                // emptextFile.append(" ");

                // E-maiiiD of the Beneficiary
                emailId = objTotal[14] != null ? objTotal[14].toString() : "";
                emptextFile.append(emailId + this.insertExtraSpace(emailId.length(), 40));
                record.setEmailId(emailId + this.insertExtraSpace(emailId.length(), 40));
                // emptextFile.append(" ");

                // contact no
                contactNo = objTotal[15] != null ? objTotal[15].toString() : "";
                emptextFile.append(contactNo + this.insertExtraSpace(contactNo.length(), 10));
                record.setCellNo(contactNo + this.insertExtraSpace(contactNo.length(), 10));

                uidSeededFlag = "";
                accountOrUIDNo = "";
                uidNo = "";
                accNo = "";
                billIdentifier = "J";
                logger.info("inside loop ddo details" + emptextFile.length());
                emptextFile.append("\r\n");

                try
                {

                    // billDetailsDAO.create(record);
                    billDetailsDAO.saveCmpDetails(record);
                    final HrPayCmpDtls cmpDtls = new HrPayCmpDtls();
                    cmpDtls.setPaymentRefNo(payrefNo);
                    final Date lDtcurDate = SessionHelper.getCurDate();
                    cmpDtls.setCreatedDate(lDtcurDate);
                    // cmpDtlsDao.create(cmpDtls);

                } catch (final Exception e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                cmprecordList.add(record);

            }

            logger.info("before ddo details" + emptextFile.length());

            // Checking if any employee is given NGR amount

            logger.info("Auth No before NGR Details is:: " + authNo);
            lstNgrAmount = billDetailsDAO.getNonGovDeductionforDDO(authNo);
            logger.info("Auth No after NGR Details is:: " + authNo);

            logger.info("Auth No before Deduction Details is:: " + authNo);
            otherDed = billDetailsDAO.getOtherDedForDDO(authNo);
            logger.info("Auth No after Deduction Details is:: " + authNo);
            logger.info("lstNgrAmount-----------" + lstNgrAmount);
            // if(ngremployees)
            // {
            if (lstNgrAmount != null && !lstNgrAmount.isEmpty())
            {
                // CMPRecord record=new CMPRecord();
                final Iterator itNgrAmount = lstNgrAmount.iterator();
                logger.info("Inside iff");
                while (itNgrAmount.hasNext())
                {
                    final CMPRecord record = new CMPRecord();
                    objNgrAmount = (Object[]) itNgrAmount.next();
                    if (objNgrAmount[0] != null)
                    {
                        ngrAmount = Long.parseLong(objNgrAmount[0].toString());
                    }
                    else{
                    	ngrAmount=0l;	
                    }
                    logger.info("ngrAmount for DDO is====" + ngrAmount);
                    /*
                     * if(objNgrAmount[1]!=null)
                     * totalDed=Long.parseLong((objNgrAmount[1].toString()));
                     */
                    logger.info("totalDed for DDO is====" + totalDed);
                    if (objNgrAmount[2] != null)
                    {
                        objNgrAmount[2].toString();
                    }
                    if (objNgrAmount[1] != null)
                    {
                        ddoCode = objNgrAmount[1].toString();
                    }
                    ddoDtls = billDetailsDAO.getDdoDtls(ddoCode);
                    if (ddoDtls != null && !ddoDtls.isEmpty())
                    {
                        final Iterator ddoIterator = ddoDtls.iterator();
                        logger.info("ddoCode====" + ddoDtls);
                        new ArrayList();
                        while (ddoIterator.hasNext())
                        {

                            objTotal = (Object[]) ddoIterator.next();

                            ddoName = (objTotal[0] != null ? objTotal[0] : "-").toString();
                            ddoAccNo = (objTotal[1] != null ? objTotal[1] : "-").toString();
                            ddoIFSCode = (objTotal[2] != null ? objTotal[2] : "-").toString();
                            ddoEmail = (objTotal[3] != null ? objTotal[3] : "-").toString();
                            ddoContact = (objTotal[4] != null ? objTotal[4] : "-").toString();
                        }
                    }

                    // Get Other Ded For DDO
                    if (otherDed != null && !otherDed.isEmpty())
                    {
                        final Iterator otherDedIterator = otherDed.iterator();
                        new ArrayList();
                        while (otherDedIterator.hasNext())
                        {

                            objTotalDed = (Object[]) otherDedIterator.next();

                            if (objTotalDed[1].equals(ddoCode))
                            {
                                totalDed = Long.parseLong((objTotalDed[0] != null ? objTotalDed[0] : "0").toString());
                            }
                        }
                    }

                    // Adding DDO details if NGR is given

                    Long ddoNetAmt = Long.valueOf(ngrAmount) + Long.valueOf(totalDed);

                    logger.info("Net Amount Before Check" + ddoNetAmt);
                    if (net.get(ddoCode) != null && !net.get(ddoCode).equals("")
                            && Long.parseLong(net.get(ddoCode).toString()) != 0)
                    {
                        // if(prvDdoCodeTotal==null){
                        ddoNetAmt = ddoNetAmt - Long.parseLong(net.get(ddoCode).toString());
                        // prvDdoCodeTotal=Long.parseLong(ddoCode);
                        // }
                        /*
                         * else if(prvDdoCodeTotal!=Long.parseLong(ddoCode)){
                         * ddoNetAmt=
                         * ddoNetAmt-Long.parseLong(net.get(ddoCode).toString
                         * ()); prvDdoCodeTotal=Long.parseLong(ddoCode); }
                         */

                    }

                    logger.info("Net Amount After Check" + ddoNetAmt);
                    
                    if(ddoNetAmt!=null && ddoNetAmt>0)
                    emptextFile.append(bulkUploadFlag);
                    record.setBulkUploadFlag("A");
                    // emptextFile.append(" ");
                    if(ddoNetAmt!=null && ddoNetAmt>0)
                    emptextFile.append(billIdentifier);
                    record.setBillId(billIdentifier);
                    // emptextFile.append(" ");
                    if(ddoNetAmt!=null && ddoNetAmt>0)
                    emptextFile.append(treasuryCode);
                    record.setTreasuryCode(treasuryCode);
                    // emptextFile.append(" ");
                    if(ddoNetAmt!=null && ddoNetAmt>0)
                    emptextFile.append(reptDdoCode.substring(4));
                    record.setDdoCode(reptDdoCode.substring(4));
                    // emptextFile.append(" ");
                    if (ddoName.length() < 50)
                    { if(ddoNetAmt!=null && ddoNetAmt>0)
                        emptextFile.append(ddoName + this.insertExtraSpace(ddoName.length(), 50));
                        record.setBenefName(ddoName + this.insertExtraSpace(ddoName.length(), 50));
                        logger.info("Name length" + (ddoName + this.insertExtraSpace(ddoName.length(), 50)).length());
                    } else
                    { if(ddoNetAmt!=null && ddoNetAmt>0)
                        emptextFile.append(ddoName.substring(0, 50));
                        record.setBenefName(ddoName.substring(0, 50));
                        logger.info("Name length" + ddoName.substring(0, 50).length());
                    }
                    // emptextFile.append(" ");
                    logger.info("Name length" + ddoName + this.insertExtraSpace(ddoName.length(), 50).length());
                    if(ddoNetAmt!=null && ddoNetAmt>0)
                    emptextFile.append(ddoAccNo + this.insertExtraSpace(ddoAccNo.length(), 20));
                    record.setAccNo(ddoAccNo + this.insertExtraSpace(ddoAccNo.length(), 20));
                    // emptextFile.append(" ");
                    if(ddoNetAmt!=null && ddoNetAmt>0)
                    emptextFile.append(ddoIFSCode + this.insertExtraSpace(ddoIFSCode.length(), 11));
                    record.setIfscCode(ddoIFSCode + this.insertExtraSpace(ddoIFSCode.length(), 11));
                    // emptextFile.append(" ");
                    if(ddoNetAmt!=null && ddoNetAmt>0)
                    emptextFile.append(this.insertExtraSpace(0, 9));
                    record.setMicrCode(this.insertExtraSpace(0, 9));
                    // emptextFile.append(" ");
                    if(ddoNetAmt!=null && ddoNetAmt>0)
                    emptextFile.append(this.insertExtraSpace(0, 2));
                    record.setAccType(this.insertExtraSpace(0, 2));
                    // emptextFile.append(" ");

                    // Long ddoNetAmt=
                    // Long.valueOf(ngrAmount)+(Long.valueOf(billNetAmount)-Long.valueOf(totalDed));
                  

                    ngrAmountStr = new Long(ddoNetAmt).toString() + "00";
                    if(ddoNetAmt!=null && ddoNetAmt>0)
                    emptextFile.append(ngrAmountStr + this.insertExtraSpace(ngrAmountStr.length(), 13));
                    record.setAmount(ngrAmountStr + this.insertExtraSpace(ngrAmountStr.length(), 13));
                    // emptextFile.append(" ");

                    final String payrefNo = treasuryCode + PRN++;
                    if(ddoNetAmt!=null && ddoNetAmt>0)
                    emptextFile.append(payrefNo + this.insertExtraSpace(payrefNo.length(), 25));
                    record.setPaymentRefNo(payrefNo + this.insertExtraSpace(payrefNo.length(), 25));
                    // i=i+1;
                    logger.info("payrefNo" + payrefNo);
                    // emptextFile.append(i+ddoBillGrpId+insertExtraSpace((ddoBillGrpId.length()+
                    // i.toString().length()), 25));
                    // emptextFile.append(" ");
                    if(ddoNetAmt!=null && ddoNetAmt>0)
                    emptextFile.append(payByDate + this.insertExtraSpace(payByDate.length(), 10));
                    record.setPayBydate(payByDate + this.insertExtraSpace(payByDate.length(), 10));

                    logger.info("hii payb by date********" + payByDate + this.insertExtraSpace(payByDate.length(), 10));

                    // emptextFile.append(" ");
                    logger.info("schemeCode is *******" + schemeCode);
                    if(ddoNetAmt!=null && ddoNetAmt>0)
                    emptextFile.append(schemeCode + this.insertExtraSpace(schemeCode.length(), 10));
                    record.setSchemeCode(schemeCode + this.insertExtraSpace(schemeCode.length(), 10));
                    logger.info("hii schemeCode by date********" + schemeCode
                            + this.insertExtraSpace(schemeCode.length(), 10));
                    // emptextFile.append(" ");
                    if(ddoNetAmt!=null && ddoNetAmt>0)
                    emptextFile.append(payBillGrpId + this.insertExtraSpace(payBillGrpId.length(), 75));
                    record.setDdoBillNo(payBillGrpId + this.insertExtraSpace(payBillGrpId.length(), 75));
                    logger.info("hii payBillGrpId by  ********" + payBillGrpId
                            + this.insertExtraSpace(payBillGrpId.length(), 75));
                    // emptextFile.append(" ");
                    if(ddoNetAmt!=null && ddoNetAmt>0)
                    emptextFile.append(authNo + this.insertExtraSpace(authNo.length(), 25));
                    record.setAuthNo(authNo + this.insertExtraSpace(authNo.length(), 25));
                    logger.info("hii authNo by  ********" + authNo + this.insertExtraSpace(authNo.length(), 25));
                    // emptextFile.append(" ");
                    if(ddoNetAmt!=null && ddoNetAmt>0)
                    emptextFile.append(this.insertExtraSpace(0, 25));
                    record.setToBilNo(this.insertExtraSpace(0, 25));
                    logger.info("insering extra space*********");
                    // emptextFile.append(" ");
                    if(ddoNetAmt!=null && ddoNetAmt>0)
                    emptextFile.append(billDateStr + this.insertExtraSpace(billDateStr.length(), 10));
                    record.setBillDate(billDateStr + this.insertExtraSpace(billDateStr.length(), 10));
                    // emptextFile.append(" ");
                    logger.info("insering extra space******billDateStr***" + billDateStr);
                    // Narration
                    if(ddoNetAmt!=null && ddoNetAmt>0)
                    emptextFile.append("Salary" + this.insertExtraSpace("Salary".length(), 25));
                    record.setNarration("Salary" + this.insertExtraSpace("Salary".length(), 25));
                    // emptextFile.append(" ");
                    logger.info("insering extra space******Salary***");
                    // No Of Payees
                    if(ddoNetAmt!=null && ddoNetAmt>0)
                    emptextFile.append(new Integer(noOfPayees).toString()
                            + this.insertExtraSpace(new Integer(noOfPayees).toString().length(), 5));
                    record.setNoOfPayees(new Integer(noOfPayees).toString()
                            + this.insertExtraSpace(new Integer(noOfPayees).toString().length(), 5));
                    // emptextFile.append(" ");
                    logger.info("insering extra space******noOfPayees***" + noOfPayees);

                    // bill Net AMount
                    if(ddoNetAmt!=null && ddoNetAmt>0)
                    emptextFile.append(billNetAmtStr + this.insertExtraSpace(billNetAmtStr.length(), 13));
                    record.setBillNetAmt(billNetAmtStr + this.insertExtraSpace(billNetAmtStr.length(), 13));
                    // emptextFile.append(" ");

                    logger.info("insering extra space******billNetAmtStr***" + billNetAmtStr);
                    // E-maiiiD of the Beneficiary
                    if(ddoNetAmt!=null && ddoNetAmt>0)
                    emptextFile.append(ddoEmail + this.insertExtraSpace(ddoEmail.length(), 40));
                    record.setEmailId(ddoEmail + this.insertExtraSpace(ddoEmail.length(), 40));
                    // emptextFile.append(" ");
                    logger.info("insering extra space******ddoEmail***" + ddoEmail);
                    // contact no
                    if(ddoNetAmt!=null && ddoNetAmt>0)
                    emptextFile.append(ddoContact + this.insertExtraSpace(ddoContact.length(), 10));
                    record.setCellNo(ddoContact + this.insertExtraSpace(ddoContact.length(), 10));
                    logger.info("insering extra space******ddoContact***" + ddoContact);
                    // emptextFile.append(" ");
                    if(ddoNetAmt!=null && ddoNetAmt>0)
                    emptextFile.append("\r\n");

                    logger.info("No error till here ddoNetAmt****************"+ddoNetAmt);

                    // Adding DDO details if NGR is given ends
                    if(ddoNetAmt!=null && ddoNetAmt>0)
					{
	                    try
	                    {
	                        // billDetailsDAO.create(record);
	                        billDetailsDAO.saveCmpDetails(record);
	                        final HrPayCmpDtls cmpDtls = new HrPayCmpDtls();
	                        cmpDtls.setPaymentRefNo(payrefNo);
	                        final Date lDtcurDate = SessionHelper.getCurDate();
	                        cmpDtls.setCreatedDate(lDtcurDate);
	                        // cmpDtlsDao.create(cmpDtls);
	                        cmpDtlsDao.saveCmpPayDetails(cmpDtls);
	                        logger.info("No error in try");
	
	                    } catch (final Exception e)
	                    {
	                        // TODO Auto-generated catch block
	                        e.printStackTrace();
	                    }
	                    cmprecordList.add(record);
					}
                }
            }

            // logger.info("Map contains: "+net);

        }
        if (cmprecordList.size() > 0)
        {
            fileName = ((CMPRecord) cmprecordList.get(0)).getAuthNo().trim() + "_"
                    + ((CMPRecord) cmprecordList.get(0)).getNoOfPayees().trim() + "_"
                    + ((CMPRecord) cmprecordList.get(0)).getBillNetAmt().trim();
        }
        logger.info("FileName" + fileName);
        objectArgs.put("FileName", fileName);

        final XStream xStream = new XStream(new DomDriver("UTF-8"));
        xStream.alias("EmployeeRecord", CMPRecord.class);
        xStream.registerConverter(new CMPRecordConverter());
        logger.info("cmprecordList--------" + cmprecordList);
        final String recordStr = xStream.toXML(cmprecordList);
        logger.info("recordStr" + recordStr);
        objectArgs.put("EmployeeRecord", recordStr);
        logger.info("Error not even here");

        try
        {
            billDetailsDAO.updateCMPFlag(authNo, "MD");
        } catch (final Exception e)
        {
            e.printStackTrace();
        }

        return emptextFile.toString();
    }

    private String insertExtraSpace(final int startlenth, final int endLength)
    {

        String whiteSpace = "";
        for (int i = startlenth; i < endLength; i++)
        {
            whiteSpace += " ";
        }

        return whiteSpace;
    }
}
