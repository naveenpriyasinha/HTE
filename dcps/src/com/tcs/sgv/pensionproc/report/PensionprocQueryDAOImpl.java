package com.tcs.sgv.pensionproc.report;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.dao.CommonDAO;
import com.tcs.sgv.common.dao.CommonDAOImpl;
import com.tcs.sgv.common.service.IFMSCommonServiceImpl;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.URLData;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.pensionpay.valueobject.TrnPensionTransferDtls;
import com.tcs.sgv.pensionproc.dao.TrnPnsnProcInwardPensionDAOImpl;

public class PensionprocQueryDAOImpl extends GenericDaoHibernateImpl implements PensionprocQueryDAO {
	
	private Session ghibSession = null;
	SessionFactory gObjSessionFactory = null;
	private static final Logger gLogger = Logger.getLogger(TrnPnsnProcInwardPensionDAOImpl.class);
	private SimpleDateFormat gObjDtFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	private ResourceBundle gObjRsrcBndleCaseLables = ResourceBundle.getBundle("resources/pensionproc/PensionCaseLabels");
	private ResourceBundle gObjRsrcBndle  = ResourceBundle.getBundle("resources/pensionproc/PensionCaseConstants");
	private ResourceBundle gObjRsrcBndle1 = ResourceBundle.getBundle("resources/pensionpay/PensionCaseConstants");
	private ResourceBundle gObjRsrcBndleCaseLables1 = ResourceBundle.getBundle("resources/pensionpay/PensionCaseLabels_en_US");
	
	public PensionprocQueryDAOImpl(Class type, SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
		gObjSessionFactory = sessionFactory;
		ghibSession = sessionFactory.getCurrentSession();
	}
	public List getPensionCaseTrackingReport(ReportVO lObjReport, String lStrFromDate, String lStrToDate, String lStrPensionType, String lStrRetirementDate,			
			String lStrPensionerName, String lStrSevaarthId, String lStrPpoNo,String lStrLocCode,Long lLngDdoCode) throws Exception {

		
		ArrayList lArrListOuter = new ArrayList();
		
		String lStrDdoName = getDdoName(lStrLocCode);
		String lStrDdoAsstName = getDdoAsstName(lStrLocCode);		
		
		
		Query lHibQry = null;
		StringBuilder lSBQuery = new StringBuilder();
		
		try {

			
				lSBQuery.append(" SELECT inw.inwardDate,inw.sevaarthId,dtl.pnsnrName, ");
				lSBQuery.append(" dtl.retirementDate,inw.commensionDate,inw.pensionType,inw.caseStatus,inw.inwardPensionId,inw.caseType ");
				lSBQuery.append(" FROM TrnPnsnProcInwardPension inw , TrnPnsnProcPnsnrDtls dtl ");
				lSBQuery.append(" WHERE inw.inwardPensionId=dtl.inwardPensionId and inw.ddoCode = :ddoCode");
				
				
				if (!"".equals(lStrFromDate) && !"".equals(lStrToDate)) {
					lSBQuery.append(" AND inw.inwardDate BETWEEN :lDtFrmDate AND :lDtToDate ");
				}								
				if (!"".equals(lStrPpoNo)) {
					lSBQuery.append(" AND inw.ppoNo= :lStrPPONo");
				}
				if (!"".equals(lStrRetirementDate)) {
					lSBQuery.append(" AND dtl.retirementDate = :lDtRetiredDate");
				}
				if (lStrPensionerName != null && !lStrPensionerName.equals("")) {
					lSBQuery.append(" AND dtl.pnsnrName like :lStrName");
				}				
				if (!"".equals(lStrPensionType)) {
					lSBQuery.append(" AND inw.pensionType = :lStrPensionType");
				}
				if (!"".equals(lStrSevaarthId)) {
					lSBQuery.append(" AND inw.sevaarthId = :lStrSevaarthId");
				}
	
				lSBQuery.append(" ORDER BY inw.inwardPensionId");
				
	
				lHibQry = ghibSession.createQuery(lSBQuery.toString());
				
				lHibQry.setParameter("ddoCode",lLngDdoCode);
					
				if (!"".equals(lStrFromDate) && !"".equals(lStrFromDate)) {
					lHibQry.setParameter("lDtFrmDate", gObjDtFormat.parse(lStrFromDate));
					lHibQry.setParameter("lDtToDate", gObjDtFormat.parse(lStrToDate));
				}
				if (!"".equals(lStrSevaarthId)) {
					lHibQry.setParameter("lStrSevaarthId", lStrSevaarthId.toUpperCase().trim());
				}				
				if (!"".equals(lStrPpoNo)) {
					lHibQry.setParameter("lStrPPONo", lStrPpoNo.toUpperCase().trim());
				}
				if (!"".equals(lStrRetirementDate)) {
					lHibQry.setParameter("lDtRetiredDate", gObjDtFormat.parse(lStrRetirementDate));
				}
				if (lStrPensionerName != null && !lStrPensionerName.equals("")) {
					lHibQry.setParameter("lStrName", '%' +lStrPensionerName.toUpperCase().trim() +"%");
				}				
				if (!"".equals(lStrPensionType)) {
					lHibQry.setParameter("lStrPensionType", lStrPensionType);
				}
			
			String urlPrefix = "";
			List lLstFinal = lHibQry.list();
			if (lLstFinal != null && !lLstFinal.isEmpty()) {
				lArrListOuter = new ArrayList();
				Iterator it = lLstFinal.iterator();
				while (it.hasNext()) {
					
					Object[] tuple = (Object[]) it.next();
					ArrayList lArrListInner = new ArrayList();
					if (tuple[0] != null) {
						lArrListInner.add(IFMSCommonServiceImpl.getStringFromDate((Date) tuple[0]));// inwd
						// date
					} else {
						lArrListInner.add("");
					}
					if (tuple[1] != null) {
						urlPrefix = "ifms.htm?actionFlag=loadPensionCaseInwardForm&showReadOnly=Y&inwardId="+ tuple[7].toString() ;
						lArrListInner.add(new URLData(tuple[1].toString(), urlPrefix)); // Sevaarth Id
					} else {
						lArrListInner.add("");
					}
					if (tuple[2] != null) {
						lArrListInner.add(tuple[2]);// pensioner_name
					} else {
						lArrListInner.add("");
					}
					if (tuple[8] != null) {
						lArrListInner.add(tuple[8]);// pensioner_name
					} else {
						lArrListInner.add("");
					}
					if (tuple[3] != null) {
						lArrListInner.add(IFMSCommonServiceImpl.getStringFromDate((Date) tuple[3]));// retirement_date
					} else {
						lArrListInner.add("");
					}
					if (tuple[4] != null) {
						lArrListInner.add(IFMSCommonServiceImpl.getStringFromDate((Date) tuple[4]));// commencement_date
					} else {
						lArrListInner.add("");
					}
					if (tuple[5] != null) {
						lArrListInner.add(gObjRsrcBndleCaseLables.getString("PNSNTYPE." + tuple[5].toString().trim()));// pension_type					
					} else {
						lArrListInner.add("");
					}
					if (tuple[6] != null) {
						// case_status
						
					if(tuple[6].equals("DRAFT"))
						lArrListInner.add("Draft");
					
					else if(tuple[6].equals("FWDBYDEO"))
						lArrListInner.add("Fowarded to DDO");
					
					else if(tuple[6].equals("APRVDBYDDO"))
						lArrListInner.add("Approved By DDO");
					
					else if(tuple[6].equals("RJCTBYDDO"))
						lArrListInner.add("Rejected By DDO");
					
					else if(tuple[6].equals("SENDTOAG"))
						lArrListInner.add("Send to AG");
					
					else if(tuple[6].equals("MISBYDDO"))
						lArrListInner.add("Move for Correction due to approved by DDO mistake");
					
					else if(tuple[6].equals("AGQUERY"))
						lArrListInner.add("Move for correction due to AG query");
					
					else if(tuple[6].equals("APRVDBYAG"))
						lArrListInner.add("Approved By AG");
					
					if (tuple[6].equals("FRWDTOAG")) {						
						lArrListInner.add("Forwarded to AG"); // Lying with
					}
					
					} else {
						lArrListInner.add("");
					}
					if (tuple[6].equals("FWDBYDEO")) {						
						lArrListInner.add(lStrDdoName); // Lying with
					} else {
						lArrListInner.add(lStrDdoAsstName);
					}
					
					if (tuple[6] != null && (!tuple[6].equals("FRWDTOAG"))) {
						lArrListInner.add(gObjRsrcBndle.getString("LYINGAT." + tuple[6].toString().trim())); // Lying At
					} 
					
					else {
						lArrListInner.add("");
					}					
					lArrListOuter.add(lArrListInner);
				}
			}
		} catch (Exception e) {
			gLogger.error("Exception in PensionCaseTrackingReportQueryDAOImpl.getPensionCaseTrackingReport is ::" + e.getMessage(), e);
	
		}
		return lArrListOuter;
	}
	
	public String getDdoName(String lStrLocCode){
		String lStrDdoName = "";
		Query lHibQry = null;
		StringBuilder lSBQuery = new StringBuilder();
		List lLstDdoName = null;
		try {
				lSBQuery.append(" SELECT e.EMP_FNAME || e.EMP_MNAME || e.EMP_LNAME ");
				lSBQuery.append(" FROM org_emp_mst e,org_user_mst u,ORG_DDO_MST d,ORG_USERPOST_RLT p  ");
				lSBQuery.append(" where d.LOCATION_CODE = :LocationCode ");
				lSBQuery.append(" and d.POST_ID = p.POST_ID and p.USER_ID = u.USER_ID and u.USER_ID = e.USER_ID and u.ACTIVATE_FLAG = 1");
				
				lHibQry = ghibSession.createSQLQuery(lSBQuery.toString());
				lHibQry.setParameter("LocationCode", lStrLocCode);
				
				lLstDdoName = lHibQry.list();
				
				if(!lLstDdoName.isEmpty())
					lStrDdoName = (String) lLstDdoName.get(0);
				
					
		}catch(Exception e){
			gLogger.error("Exception in getDdoName",e);
		}
		return lStrDdoName;
	}
	public String getDdoAsstName(String lStrLocCode){
		String lStrDdoAsstName = "";		
		Query lHibQry = null;
		StringBuilder lSBQuery = new StringBuilder();
		List lLstDdoAsstName = null;
		try {
				lSBQuery.append(" SELECT e.EMP_FNAME || e.EMP_MNAME || e.EMP_LNAME ");
				lSBQuery.append(" FROM org_emp_mst e,org_user_mst u,ORG_DDO_MST d,ORG_USERPOST_RLT p,RLT_DCPS_DDO_ASST r ");
				lSBQuery.append(" where d.LOCATION_CODE = :LocationCode and d.POST_ID = r.DDO_POST_ID ");
				lSBQuery.append(" and r.ASST_POST_ID = p.POST_ID and p.USER_ID = u.USER_ID and u.ACTIVATE_FLAG = 1 and u.USER_ID = e.USER_ID ");
				
				lHibQry = ghibSession.createSQLQuery(lSBQuery.toString());
				lHibQry.setParameter("LocationCode", lStrLocCode);
				
				lLstDdoAsstName = lHibQry.list();
				
				if(!lLstDdoAsstName.isEmpty())
					lStrDdoAsstName = (String) lLstDdoAsstName.get(0);
				
					
		}catch(Exception e){
			gLogger.error("Exception in getDdoAsstName",e);
		}
		return lStrDdoAsstName;
	}
	
	public List getPensionPpoTrackingReport(ReportVO lObjReport, String lStrPpoNo,String lStrLocCode) throws Exception {

		
			CommonDAO lObjCommonDAO = new CommonDAOImpl(gObjSessionFactory);
			TrnPensionTransferDtls lObjTrnPensionTransferDtls = new TrnPensionTransferDtls();
			ArrayList lArrListOuter = new ArrayList();
			String lStrRoleId = null;
			String lStrEmpName = null;
			String lStrArrPensionType[] = new String[14];
			Boolean lBlFlag = false;
			String lStrCaseStatus = null;
			Long lLngPostId = null;

			try {

				lStrArrPensionType[0] = "ABSORPTION";
				lStrArrPensionType[1] = "COMPASSIONATE";
				lStrArrPensionType[2] = "COMPENSATION";
				lStrArrPensionType[3] = "COMPULSORY";
				lStrArrPensionType[4] = "EXTRAORDINARY";
				lStrArrPensionType[5] = "FAMILYPNSN";
				lStrArrPensionType[6] = "INVALID";
				lStrArrPensionType[7] = "RETIRING105";
				lStrArrPensionType[8] = "RETIRING104";
				lStrArrPensionType[9] = "SUPERANNU";
				lStrArrPensionType[10] = "VOLUNTARY64";
				lStrArrPensionType[11] = "VOLUNTARY65";
				lStrArrPensionType[12] = "INJURY";
				lStrArrPensionType[13] = "GALLANTRY";

				StringBuilder lSBQuery = new StringBuilder();
				

				lSBQuery.append("SELECT req.ppo_Inward_Date,req.ppo_No,(case when hdr.ALIVE_FLAG='Y' then hdr.first_Name else mpfd.NAME end) ");
				lSBQuery.append(",hdr.date_Of_Retirement,req.COMMENCEMENT_DATE,req.pension_Type,req.case_Status, ");
				lSBQuery.append(" dtls.pensioner_Code,req.case_Owner,hdr.location_Code,clm.loc_Name ");
				lSBQuery.append(" FROM Trn_Pension_Rqst_Hdr req ");
				lSBQuery.append(" join Mst_Pensioner_Dtls dtls on  req.pensioner_Code = dtls.pensioner_Code "); 
				lSBQuery.append("join Mst_Pensioner_Hdr hdr on  req.pensioner_Code = hdr.pensioner_Code  ");
				lSBQuery.append("join Cmn_Location_Mst clm on clm.lOCATION_CODE = hdr.location_Code ");
				lSBQuery.append("left outer join MST_PENSIONER_FAMILY_DTLS mpfd on mpfd.PENSIONER_CODE=req.PENSIONER_CODE and mpfd.PERCENTAGE=100 where  ");

				
				if (lStrPpoNo != null && lStrPpoNo.length() > 0) {
					lSBQuery.append("  req.ppo_No like '%" + lStrPpoNo.trim() + "%' and ");
				}
				lSBQuery.append(" clm.loc_Id = hdr.location_Code ");
				Query lObjQuery = ghibSession.createSQLQuery(lSBQuery.toString());

				

				// Query.setString("locCode", lStrLocationCode);
				String urlPrefix = "";
				String lStrToLocCode = "";
				List lLstFinal = lObjQuery.list();
				if (lLstFinal != null && !lLstFinal.isEmpty()) {
					lArrListOuter = new ArrayList();
					Iterator it = lLstFinal.iterator();
					while (it.hasNext()) {
						lBlFlag = false;
						Object[] tuple = (Object[]) it.next();
						ArrayList lArrListInner = new ArrayList();
						if (tuple[0] != null) {
							lArrListInner.add(IFMSCommonServiceImpl.getStringFromDate((Date) tuple[0]));// inwd
							// date
						} else {
							lArrListInner.add("");
						}
						if (tuple[1] != null) {
							urlPrefix = "ifms.htm?actionFlag=loadPhyPenCase&showReadOnly=Y&showHistoryBtn=Y&pensionerId=" + tuple[7].toString();
							lArrListInner.add(new URLData(tuple[1].toString(), urlPrefix)); // ppo_no
						} else {
							lArrListInner.add("");
						}
						if (tuple[2] != null) {
							lArrListInner.add(tuple[2]);// pensioner_name
						} else {
							lArrListInner.add("");
						}
						if (tuple[3] != null) {
							lArrListInner.add(tuple[3]);// retirement_date
						} else {
							lArrListInner.add("");
						}
						if (tuple[4] != null) {
							lArrListInner.add(tuple[4]);// commencement_date
						} else {
							lArrListInner.add("");
						}
						if (tuple[5] != null) {
							for (Integer lInt = 0; lInt < lStrArrPensionType.length; lInt++) {
								if (lStrArrPensionType[lInt].equals(tuple[5].toString().trim())) {
									lBlFlag = true;
								}
							}
							if (lBlFlag) {
								lArrListInner.add(gObjRsrcBndleCaseLables1.getString("PNSNTYPE." + tuple[5].toString().trim()));// pension_type
							} else {
								lArrListInner.add(tuple[5].toString());
							}
						} else {
							lArrListInner.add("");
						}
						if (tuple[6] != null) {
							lArrListInner.add(tuple[6]);// case_status
						} else {
							lArrListInner.add("");
						}
						if ("ROLEID." + tuple[6].toString().trim() != null && tuple[6].toString().length() > 0) {
							lStrCaseStatus = tuple[6].toString();
							if ((gObjRsrcBndle1.getString("STATFLG.APPROVED")).equals(lStrCaseStatus) || (gObjRsrcBndle1.getString("STATFLG.REJECTED")).equals(lStrCaseStatus)
									|| (gObjRsrcBndle1.getString("STATFLG.MODIFIEDBYAUDITOR")).equals(lStrCaseStatus) || (gObjRsrcBndle1.getString("STATFLG.IDENTIFIED")).equals(lStrCaseStatus)) {
								lLngPostId = Long.parseLong(tuple[8].toString());
								//lStrEmpName = lObjCommonDAO.getEmpNameByPostId(lLngPostId);
							} else {
								if(gObjRsrcBndle1.getString("STATFLG.TRANSFERNEW").equals(lStrCaseStatus))
								{
									lStrRoleId = gObjRsrcBndle1.getString("ROLEID." + tuple[6].toString().trim());
									lObjTrnPensionTransferDtls = getTransferDtlsFromPnsnrCode(tuple[7].toString());
									lStrToLocCode = lObjTrnPensionTransferDtls.getToLocation();
									lStrEmpName = getEmpNameFromRoleId(lStrRoleId, lStrToLocCode);

								}
								else
								{
									lStrRoleId = gObjRsrcBndle1.getString("ROLEID." + tuple[6].toString().trim());
									lStrEmpName = getEmpNameFromRoleId(lStrRoleId, tuple[9].toString());
								}
								//System.out.println( "lStrRoleId :"+lStrRoleId);
							}
							lArrListInner.add(lStrEmpName); // Lying with
						} else {
							lArrListInner.add("");
						}
						if (tuple[6] != null) {
							lArrListInner.add(gObjRsrcBndle1.getString("LYINGAT." + tuple[6].toString().trim()));
						} else {
							lArrListInner.add("");
						}
						if (tuple[10] != null && !gObjRsrcBndle1.getString("STATFLG.TRANSFERNEW").equals(lStrCaseStatus))  {
							lArrListInner.add(tuple[10]);
						} else if (gObjRsrcBndle1.getString("STATFLG.TRANSFERNEW").equals(lStrCaseStatus)) {

							lArrListInner.add(lObjCommonDAO.getTreasuryName(1L, Long.parseLong(lStrToLocCode)));
						}else {
							lArrListInner.add("");
						}
						lArrListOuter.add(lArrListInner);
					}
				}
			} catch (Exception e) {
				// e.printStackTrace();
				gLogger.error("Exception in PensionCaseTrackingReportQueryDAOImpl.getPensionCaseTrackingReport is ::" + e.getMessage(), e);

			}
			return lArrListOuter;
}
	public String getEmpNameFromRoleId(String lStrRoleId, String lStrLocCode) {

		String lStrEmpName = "";
		Query lHibQry = null;
		StringBuffer lSBQuery = null;
		String lStrActiveFlag = null;
		try {

			lSBQuery = new StringBuffer();
			lSBQuery.append(" SELECT concat(concat(concat(concat(oem.empFname,' '),oem.empMname),' '),oem.empLname),oup.activateFlag \n");
			lSBQuery.append(" FROM AclRoleMst arm,AclPostroleRlt apr,OrgUserpostRlt oup,OrgEmpMst oem, OrgPostMst opm \n");
			lSBQuery.append(" WHERE arm.roleId=apr.aclRoleMst.roleId \n");
			lSBQuery.append(" AND oup.orgPostMstByPostId.postId=apr.orgPostMst.postId \n");
			lSBQuery.append(" AND oup.orgUserMst.userId = oem.orgUserMst.userId \n");
			lSBQuery.append(" AND oup.orgPostMstByPostId.postId=opm.postId\n");
			lSBQuery.append(" AND arm.roleId=:roleId \n");
			lSBQuery.append(" AND opm.locationCode=:locationCode \n");
			lSBQuery.append(" Order by oup.createdDate desc \n");

			lHibQry = ghibSession.createQuery(lSBQuery.toString());
			lHibQry.setParameter("locationCode", lStrLocCode);
			lHibQry.setParameter("roleId", Long.valueOf(lStrRoleId));
			List lLstEmpName = lHibQry.list();

			if (!lLstEmpName.isEmpty()) {
				Object[] lObjArr = (Object[]) lLstEmpName.get(0);
				lStrActiveFlag = (lObjArr[1] != null) ? lObjArr[1].toString() : null;
				if (lStrActiveFlag != null && "1".equals(lStrActiveFlag)) {
					lStrEmpName = (lObjArr[0] != null) ? lObjArr[0].toString() : "";
				} else {
					lStrEmpName = "Vacant Post";
				}
			}
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("Error is :" + e, e);
		}
		return lStrEmpName;
	}
	
	public TrnPensionTransferDtls getTransferDtlsFromPnsnrCode(String lStrPensionerCode) throws Exception {

		Session hiSession = getSession();
		StringBuffer query = new StringBuffer();
		List<TrnPensionTransferDtls> lLstTrnPensionTransferDtls = null;
		TrnPensionTransferDtls lObjTrnPensionTransferDtls = new TrnPensionTransferDtls();
		try {
			query.append(" SELECT P FROM TrnPensionTransferDtls P WHERE P.pensionerCode in (:pensionerCode) order by P.createdDate desc ");

			Query sqlQuery = hiSession.createQuery(query.toString());
			sqlQuery.setParameter("pensionerCode", lStrPensionerCode);

			lLstTrnPensionTransferDtls = sqlQuery.list();
			if(!lLstTrnPensionTransferDtls.isEmpty())
			{
				lObjTrnPensionTransferDtls = lLstTrnPensionTransferDtls.get(0);
			}
		} catch (Exception e) {
			logger.error("Error is" + e, e);
			throw e;
		}
		return lObjTrnPensionTransferDtls;
	}
}
