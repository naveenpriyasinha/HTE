package com.tcs.sgv.eis.util;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.dao.CmnLookupMstDAO;
import com.tcs.sgv.common.dao.CmnLookupMstDAOImpl;
import com.tcs.sgv.common.valueobject.CmnBranchMst;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.common.valueobject.MstBankPay;
import com.tcs.sgv.common.valueobject.OrgDdoMst;
import com.tcs.sgv.common.valueobject.RltBankBranchPay;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.dcps.valueobject.DcpsCadreMst;
import com.tcs.sgv.dcps.valueobject.MstDcpsBillGroup;
import com.tcs.sgv.dcps.valueobject.MstEmp;
import com.tcs.sgv.dcps.valueobject.RltDcpsPayrollEmp;
import com.tcs.sgv.eis.dao.BankDetailDAOImpl;
import com.tcs.sgv.eis.dao.BillHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.EmpInfoDAOImpl;
import com.tcs.sgv.eis.dao.GradDesgScaleMapDAO;
import com.tcs.sgv.eis.dao.GradeMasterDAO;
import com.tcs.sgv.eis.dao.HrPayOfficePostMpgDAOImpl;
import com.tcs.sgv.eis.dao.OrderHeadMpgDAOImpl;
import com.tcs.sgv.eis.dao.OrderHeadPostmpgDAOImpl;
import com.tcs.sgv.eis.dao.OrderMstDAOImpl;
import com.tcs.sgv.eis.dao.OtherDetailDAOImpl;
import com.tcs.sgv.eis.dao.PayBillDAOImpl;
import com.tcs.sgv.eis.dao.PsrPostMpgDAOImpl;
import com.tcs.sgv.eis.service.IdGenerator;
import com.tcs.sgv.eis.valueobject.HrEisBankDtls;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisGdMpg;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.eis.valueobject.HrEisProofDtl;
import com.tcs.sgv.eis.valueobject.HrEisScaleMst;
import com.tcs.sgv.eis.valueobject.HrEisSgdMpg;
import com.tcs.sgv.eis.valueobject.HrPayGpfBalanceDtls;
import com.tcs.sgv.eis.valueobject.HrPayOfficepostMpg;
import com.tcs.sgv.eis.valueobject.HrPayOrderHeadMpg;
import com.tcs.sgv.eis.valueobject.HrPayOrderHeadPostMpg;
import com.tcs.sgv.eis.valueobject.HrPayOrderMst;
import com.tcs.sgv.eis.valueobject.HrPayPaybill;
import com.tcs.sgv.eis.valueobject.HrPayPsrPostMpg;
import com.tcs.sgv.eis.valueobject.MstPayrollDesignationMst;
import com.tcs.sgv.ess.dao.EmpDAO;
import com.tcs.sgv.ess.dao.EmpDAOImpl;
import com.tcs.sgv.ess.dao.OrgPostDetailsRltDao;
import com.tcs.sgv.ess.dao.OrgPostDetailsRltDaoImpl;
import com.tcs.sgv.ess.dao.OrgPostMstDao;
import com.tcs.sgv.ess.dao.OrgPostMstDaoImpl;
import com.tcs.sgv.ess.dao.OrgUserMstDaoImpl;
import com.tcs.sgv.ess.dao.UserPostDao;
import com.tcs.sgv.ess.dao.UserPostDaoImpl;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgGradeMst;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;
import com.tcs.sgv.user.dao.AdminOrgPostDtlDaoImpl;


public class NewEmployeeConfigServiceHelper 
{
public final Log logger = LogFactory.getLog(getClass());
	
	ServiceLocator serv;
	
	/**
	 * @return the serv
	 */
	public ServiceLocator getServ() {
		return serv;
	}

	/**
	 * @param serv the serv to set
	 */
	public void setServ(ServiceLocator serv) {
		this.serv = serv;
	}

	public NewEmployeeConfigServiceHelper(ServiceLocator serv)
	{
	super();
	this.serv=serv;
	}
	
//Added by Abhilash
	
	public OrgUserMst insertOrgUserMstForUserCreation( OrgUserMst orgUserMst) throws Exception
	{
		OrgUserMstDaoImpl orgUserMstDaoImpl=new OrgUserMstDaoImpl(OrgUserMst.class,serv.getSessionFactory());
		logger.info("insertOrgUserMstForUserCreation in NewEmployeeConfigServiceHelper");
		long id =1;
		orgUserMst.setActivateFlag(1);
		orgUserMst.setCreatedDate(new java.util.Date());
		CmnLookupMstDAOImpl cmnDao = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
		CmnLookupMst cmnLookupMst =  cmnDao.read(id);
		orgUserMst.setCmnLookupMst(cmnLookupMst);
		orgUserMst.setUserName("a"+orgUserMst.getUserId());
		orgUserMst.setPassword("a");
		orgUserMst.setStartDate(new java.util.Date());
		orgUserMst.setSecretQueOther("Que");
		orgUserMst.setSecretQueCode("Secret_Other");
		orgUserMst.setSecretAnswer("Enter User Name");
		
		orgUserMstDaoImpl.create(orgUserMst);
		return orgUserMst;
	}
	
	public OrgEmpMst insertOrgEmpMst(OrgEmpMst orgEmpMst, RltDcpsPayrollEmp empData, MstEmp empMst , Map objectArgs) throws Exception
	{
		Date sysdate = new Date();
					 
			EmpDAO orgEmpDAO = new EmpDAOImpl(OrgEmpMst.class, serv.getSessionFactory());
			CmnLookupMstDAOImpl cmnDao = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
		 
			if( orgEmpMst !=null)
			{
				logger.info("Emp Mst data is "+empMst.getDcpsEmpId());
				////////////////Amish Copied From
				
				long scaleId = Long.parseLong(empMst.getPayScale());
				GenericDaoHibernateImpl genDao = new GenericDaoHibernateImpl(HrEisScaleMst.class);
				genDao.setSessionFactory(serv.getSessionFactory());
				HrEisScaleMst hrEisScaleMst = (HrEisScaleMst)genDao.read(scaleId);
								
				long dsgnId = Long.parseLong(empMst.getDesignation());
				genDao = new GenericDaoHibernateImpl(OrgDesignationMst.class);
				genDao.setSessionFactory(serv.getSessionFactory());
				OrgDesignationMst orgDesignationMst = (OrgDesignationMst)genDao.read(dsgnId);
				
				
				GradeMasterDAO gradeDao = new GradeMasterDAO(OrgGradeMst.class,serv.getSessionFactory());
				 
				long cadre = Long.parseLong(empMst.getCadre()!=null&&!empMst.getCadre().equals("")?empMst.getCadre():"0");
				Map loginMap = (Map) objectArgs.get("baseLoginMap");
				CmnLocationMst cmnLocationMst = (CmnLocationMst) loginMap.get("locationVO");
				// added by manish for tight binding of cadre group and designation
				PayBillDAOImpl billDAOImpl = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
				
				String ddoCode="";
				OrgDdoMst ddoMst = new OrgDdoMst();
				if(objectArgs.get("ddoCode")!= null)
				{	ddoCode= objectArgs.get("ddoCode").toString();
					ddoMst=billDAOImpl.getOrgDdoMstFromDDOCode(ddoCode);
					 genDao = new GenericDaoHibernateImpl(CmnLocationMst.class);
					genDao.setSessionFactory(serv.getSessionFactory());
					cmnLocationMst = (CmnLocationMst)genDao.read(Long.parseLong(ddoMst.getLocationCode())); 
					
				}
				
					
				
				long parentLocId = cmnLocationMst.getParentLocId();
				AdminOrgPostDtlDaoImpl adminOrgPostDtlDaoImpl = new AdminOrgPostDtlDaoImpl(
						OrgPostMst.class, serv.getSessionFactory());
				List<MstPayrollDesignationMst> lst = adminOrgPostDtlDaoImpl
						.getMstDcpsDsgnObject(parentLocId, dsgnId);
				MstPayrollDesignationMst mst = new MstPayrollDesignationMst();
				if (lst != null && lst.size() > 0)
					mst = lst.get(0);
				long cadreTypeId = mst.getCadreTypeId(); //cadre must come 
				
				logger.info("cadre is "+cadre);
								//added by vaibhav tyagi: start
								genDao = new GenericDaoHibernateImpl(DcpsCadreMst.class);
								genDao.setSessionFactory(serv.getSessionFactory());
								DcpsCadreMst cdrMst = (DcpsCadreMst)genDao.read(cadre);
								logger.info("DcpsCadreMst is "+cdrMst);
								String superAnnAge = cdrMst.getSuperAntunAge()!=null&&!cdrMst.getSuperAntunAge().equals("")?cdrMst.getSuperAntunAge().toString():"0";
								int superAnnuationDate= Integer.parseInt(superAnnAge);
								logger.info("superAnnuationDate is "+superAnnuationDate);
								
				// ended by manish
				 /* genDao = new GenericDaoHibernateImpl(DcpsCadreMst.class);
				genDao.setSessionFactory(serv.getSessionFactory());
				DcpsCadreMst cdrMst = (DcpsCadreMst)genDao.read(cadreTypeId);
				 */
			 
				long grpName = Long.parseLong(new Long(cadreTypeId).toString());
				CmnLookupMst  loonkupGrd = cmnDao.read(grpName);
				
				 genDao = new GenericDaoHibernateImpl(OrgGradeMst.class);
				genDao.setSessionFactory(serv.getSessionFactory());
				
				long gradeId = Long.parseLong(loonkupGrd.getLookupShortName().toString());
				logger.info(loonkupGrd.getLookupShortName().toString());
				
				GradDesgScaleMapDAO sgdDao = new GradDesgScaleMapDAO(HrEisSgdMpg.class,serv.getSessionFactory());
				OrgGradeMst orgGradeMst = (OrgGradeMst)genDao.read(gradeId);
				
							
				
				////////////Amish Endsw
				
							
				/*long cadre = Long.parseLong(empMst.getCadre()!=null&&!empMst.getCadre().equals("")?empMst.getCadre():"0");
				logger.info("cadre is "+cadre);
				GenericDaoHibernateImpl genDao = new GenericDaoHibernateImpl(DcpsCadreMst.class);
				genDao.setSessionFactory(serv.getSessionFactory());
				DcpsCadreMst cdrMst = (DcpsCadreMst)genDao.read(cadre);
				logger.info("DcpsCadreMst is "+cdrMst);
				//logger.info("Group name of cadre is "+DcpsCadreMst.getGroupName());
				long grpName = Long.parseLong(cdrMst.getGroupId());
				CmnLookupMst  loonkupGrd = cmnDao.read(grpName);
				
				 genDao = new GenericDaoHibernateImpl(OrgGradeMst.class);
				genDao.setSessionFactory(serv.getSessionFactory());
				logger.info(loonkupGrd.getLookupShortName().toString());
				OrgGradeMst orgGradeMst = (OrgGradeMst)genDao.read(Long.parseLong(loonkupGrd.getLookupShortName().toString()));
				*/
				orgEmpMst.setStartDate(sysdate);
				orgEmpMst.setOrgGradeMst(orgGradeMst);
				orgEmpMst.setCadre(String.valueOf(cadre));
				orgEmpMst.setEmpDob(empMst.getDob());
				orgEmpMst.setEmpDoj(empMst.getDoj());
				
				String name = empMst.getName();
				String[] fullName= null;
				if(!name.equals(""))
					 fullName = name.split(" ");
				
				if(fullName!=null && fullName.length>0)
				{
					if(fullName.length==1)
					{
						orgEmpMst.setEmpFname(fullName[0]);
						orgEmpMst.setEmpMname(" ");
						orgEmpMst.setEmpLname(" ");
						
					}
					else if(fullName.length==2)
					{
						orgEmpMst.setEmpFname(fullName[0]);
						orgEmpMst.setEmpLname(fullName[1]);
						orgEmpMst.setEmpMname(" ");
					}
					else if(fullName.length==3)
					{
						orgEmpMst.setEmpFname(fullName[0]);
						orgEmpMst.setEmpMname(fullName[1]);
						orgEmpMst.setEmpLname(fullName[2]);
					}
					else
					{
						orgEmpMst.setEmpFname(fullName[0]);
						orgEmpMst.setEmpMname(fullName[1]);
						String lName ="";
						 for(int i=2;i<fullName.length;i++)
						 {
							 lName += fullName[i];
						 }
						 orgEmpMst.setEmpLname(lName);
					}
					
				}
				
				long langId =1;
				genDao = new GenericDaoHibernateImpl(CmnLanguageMst.class);
				genDao.setSessionFactory(serv.getSessionFactory());
				CmnLanguageMst  cmnLanguageMst = (CmnLanguageMst)genDao.read(langId);
				orgEmpMst.setCmnLanguageMst(cmnLanguageMst);
				orgEmpMst.setActivateFlag(1);
				orgEmpMst.setEmpGPFnumber(empData.getPfAcNo());
				
				long salId = Long.parseLong(empMst.getSalutation());
				int yr=empMst.getDob().getYear();
				
				Date doexp = new Date();
				doexp.setYear(yr+superAnnuationDate);
				doexp.setMonth(empMst.getDob().getMonth());
				doexp.setDate(empMst.getDob().getDate());
				orgEmpMst.setEmpPrefix(cmnDao.read(salId).getLookupName());
				orgEmpMst.setEmpSrvcExp(doexp);
				orgEmpMst.setEmpSrvcFlag(1);
				orgEmpMst.setCreatedDate(sysdate);
				
				orgEmpMst.setTrnCounter(1);
				logger.info("creating OrgEmpMst");
				 
					long orgEmpMstCreated=orgEmpDAO.create(orgEmpMst);
					logger.info(" org_emp_mst created "+orgEmpMstCreated);
			}
		 
			return orgEmpMst;
	}
	//Saving Basic Emp Info Code ends
	
	public void insertHrEisProofDtls( HrEisProofDtl hrEisProofDtl, RltDcpsPayrollEmp empData, MstEmp empMst)throws Exception
	{
		GenericDaoHibernateImpl generateDao = new GenericDaoHibernateImpl(HrEisProofDtl.class);
		generateDao.setSessionFactory(serv.getSessionFactory());
		Date sysdate = new Date();
		CmnLookupMst proofLookupId = new CmnLookupMst();
		long proofId = 300166;
		CmnLookupMstDAO cmnLookupDao = new CmnLookupMstDAOImpl(	CmnLookupMst.class, serv.getSessionFactory());
		proofLookupId = cmnLookupDao.read(proofId);
		hrEisProofDtl.setCmnLookupMst(proofLookupId);
		hrEisProofDtl.setProofNum(empMst.getPANNo());
		hrEisProofDtl.setCreatedDate(sysdate);
		logger.info(" hr_eis_proof_dtls Created "+generateDao.create(hrEisProofDtl));
	}
	 
	public void insertHrEisEmpDtl ( HrEisEmpMst empMst  )throws Exception
	{
		Date sysdate = new Date();
		empMst.setCreatedDate(sysdate);
	 
		EmpInfoDAOImpl empinfodao = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
	
		empMst.setTrnCounter(new Integer(1));
		long hrEisEmpMst =  empinfodao.create(empMst);
		logger.info(" hrEisEmpMst created "+hrEisEmpMst);
		
	}
	
	
	public void insertHrGpfBalanceDtls(HrPayGpfBalanceDtls hrGpfBalanceDtls , RltDcpsPayrollEmp empData,MstEmp empMst,String DCPSAcc )throws Exception
	{
		 
		GenericDaoHibernateImpl genericDaoHibernateImpl = new GenericDaoHibernateImpl(HrPayGpfBalanceDtls.class);
		genericDaoHibernateImpl.setSessionFactory(serv.getSessionFactory());
		EmpInfoDAOImpl empinfodao = new EmpInfoDAOImpl(HrEisEmpMst.class,serv.getSessionFactory());
		Date sysdate = new Date();
		
		long cadre = Long.parseLong(empMst.getCadre()!=null&&!empMst.getCadre().equals("")?empMst.getCadre():"0");
		GenericDaoHibernateImpl genDao = new GenericDaoHibernateImpl(DcpsCadreMst.class);
		genDao.setSessionFactory(serv.getSessionFactory());
		DcpsCadreMst cdrMst = (DcpsCadreMst)genDao.read(cadre);
		
		CmnLookupMstDAOImpl cmnDao = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
		long grpName = Long.parseLong(cdrMst.getGroupId());
		CmnLookupMst  loonkupGrd = cmnDao.read(grpName);
		
		 genDao = new GenericDaoHibernateImpl(OrgGradeMst.class);
		genDao.setSessionFactory(serv.getSessionFactory());
		OrgGradeMst orgGradeMst = (OrgGradeMst)genDao.read(Long.parseLong(loonkupGrd.getLookupShortName().toString()));
		
		logger.info("insertHrGpfBalanceDtls in NewEmployeeConfigServiceHelper");
		logger.info("Going to insert GPF AC no as " + empData.getPfAcNo());
		String gpfAccNo  = empData.getPfAcNo()!=null && !empData.getPfAcNo().trim().equals("")?empData.getPfAcNo().trim():"";
		if(gpfAccNo.equals(""))
		{
			gpfAccNo= DCPSAcc;
		}
		logger.info("Going to insert gpfAccNo as " + gpfAccNo);
		hrGpfBalanceDtls.setGpfAccNo(gpfAccNo);
		 
		hrGpfBalanceDtls.setTrnCounter(1);

		hrGpfBalanceDtls.setOrgGradeMst(orgGradeMst);
		hrGpfBalanceDtls.setCreatedDate(sysdate);

		genericDaoHibernateImpl.create(hrGpfBalanceDtls);	
	}
	
	public void insertHrEisBankDtls(HrEisBankDtls bankDtls, RltDcpsPayrollEmp empData,MstEmp empMst)throws Exception
	{
		Date sysdate = new Date();
		BankDetailDAOImpl bankDetailDAO = new BankDetailDAOImpl(HrEisBankDtls.class,serv.getSessionFactory());
		//IdGenerator idGen = new IdGenerator();
		
		long branchId = empMst.getBranchName()!=null&&!empMst.getBranchName().equals("")?Long.parseLong(empMst.getBranchName()):0;
		long bankId = empMst.getBankName()!=null&&!empMst.getBankName().equals("")?Long.parseLong(empMst.getBankName()):0;
		bankDtls.setBankAcctNo(empMst.getBankAccountNo());
		if(branchId !=0)
		{
			GenericDaoHibernateImpl genericDaoHibernateImpl = new GenericDaoHibernateImpl(RltBankBranchPay.class);
			genericDaoHibernateImpl.setSessionFactory(serv.getSessionFactory());
			RltBankBranchPay rltBankBranch = (RltBankBranchPay)genericDaoHibernateImpl.read(branchId);
			bankDtls.setRltBankBranch(rltBankBranch);
		}
		if(bankId !=0)
		{
			GenericDaoHibernateImpl genericDaoHibernateImpl = new GenericDaoHibernateImpl(MstBankPay.class);
			genericDaoHibernateImpl.setSessionFactory(serv.getSessionFactory());
			MstBankPay mstBank = (MstBankPay)genericDaoHibernateImpl.read(bankId);
			bankDtls.setMstBank(mstBank);
			
		}
		bankDtls.setCreatedDate(sysdate);		
		
	 
		long bankCreated= bankDetailDAO.create(bankDtls);
		
		logger.info("hr_eis_bank_dtls created"+bankCreated);
	}
	
	//Creating New Post
	public long insertOrgPostMst( OrgPostMst loggedInOrgPostMst,long newPostId,long loggedInUser,long langId,long locId,OrgUserMst loggedInOrgUserMst,OrgPostMst orgPostMst,OrgPostMst setOrgPostMst,CmnBranchMst cmnBranchMst,OrgDesignationMst orgDesignationMst_en,CmnLocationMst cmnLocationMst_en)throws Exception
	{
		
		OrgPostMstDao orgPostMstDao = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
		//IdGenerator idgen = new IdGenerator();
	
	   Date sysdate = new Date();
		logger.info("newPostId:::"+newPostId);					
		logger.info("insertOrgPostMst in NewEmployeeConfigServiceHelper");
		orgPostMst.setPostId(newPostId);
		orgPostMst.setDsgnCode(orgDesignationMst_en.getDsgnCode());
		if (cmnBranchMst != null)
			orgPostMst.setBranchCode(cmnBranchMst.getBranchCode());
		orgPostMst.setLocationCode(cmnLocationMst_en.getLocationCode());
		orgPostMst.setCreatedDate(sysdate);
		orgPostMst.setOrgPostMstByCreatedByPost(setOrgPostMst);
		orgPostMst.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
		orgPostMst.getPostTypeLookupId();
		orgPostMstDao.create(orgPostMst);
		return newPostId;
	}
	
	public void insertOrgPostDetailsRlt(OrgPostMst loggedInOrgPostMst,long orgPostDtlId_en,OrgPostMst setOrgPostMst,long postId,OrgUserMst loggedInOrgUserMst,OrgPostDetailsRlt orgPostDetailsRlt_en,CmnBranchMst objCmnBranchMst_en,CmnLanguageMst cmnLanguageMst_en, CmnLocationMst cmnLocationMst_en,OrgDesignationMst orgDesignationMst_en,long langId,long loggedInUser,long locId)throws Exception
	{
		logger.info("insertOrgPostDetailsRlt in NewEmployeeConfigServiceHelper");
		Date sysdate = new Date();
		OrgPostDetailsRltDao orgPostDetailsRltDao = new OrgPostDetailsRltDaoImpl(OrgPostDetailsRlt.class, serv.getSessionFactory());
		//IdGenerator idgen = new IdGenerator();
		CommomnDataObjectFatch cmn = new CommomnDataObjectFatch(serv);
		OrgPostMst orgPostMst = cmn.getOrgPostMst(postId);
		
		orgPostDetailsRlt_en.setOrgDesignationMst(orgDesignationMst_en);
		orgPostDetailsRlt_en.setCmnLocationMst(cmnLocationMst_en);
		orgPostDetailsRlt_en.setCmnLanguageMst(cmnLanguageMst_en);
		orgPostDetailsRlt_en.setCmnBranchMst(objCmnBranchMst_en);					
		orgPostDetailsRlt_en.setCreatedDate(sysdate);					
		orgPostDetailsRlt_en.setOrgPostMstByCreatedByPost(setOrgPostMst);					
		orgPostDetailsRlt_en.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
		orgPostDetailsRlt_en.setPostDetailId(orgPostDtlId_en);
		orgPostDetailsRlt_en.setOrgPostMst(orgPostMst);
		orgPostDetailsRltDao.create(orgPostDetailsRlt_en);
	}
	
	public void insertHrPayPsrPostMpg(long newPostId,long psrPostId, CmnLocationMst cmnLocationMst_en,long psrId,HrPayPsrPostMpg hrPayPsrPostMpg,OrgPostMst orgPostMst,long loggedInUser,long langId,long locId)throws Exception
	{
		logger.info("insertHrPayPsrPostMpg in NewEmployeeConfigServiceHelper");
		PsrPostMpgDAOImpl psrPostMpgDAOImpl = new PsrPostMpgDAOImpl(HrPayPsrPostMpg.class, serv.getSessionFactory());
		//IdGenerator idgen = new IdGenerator();
		logger.info("psrPostIdpsrPostIdpsrPostIdpsrPostId" + psrPostId);
		//long newPostId = orgPostMst.getPostId();
		hrPayPsrPostMpg.setPostId(newPostId);
		hrPayPsrPostMpg.setPsrId(psrId);
		hrPayPsrPostMpg.setLoc_id(Long.parseLong(cmnLocationMst_en.getLocationCode()));
		hrPayPsrPostMpg.setPsrPostId(psrPostId);
		psrPostMpgDAOImpl.create(hrPayPsrPostMpg);
	}
	
	public void insertHrPayOfficepostMpg(HrPayOfficepostMpg hrOfficePostMpg,OrgPostMst setOrgPostMst,long postId,CmnLocationMst cmnLocationMst_en,OrgUserMst loggedInOrgUserMst, long loggedInUser,long langId,long locId)throws Exception
	{
		logger.info("insertHrPayOfficepostMpg in NewEmployeeConfigServiceHelper");
		Date sysdate = new Date();
		IdGenerator idgen = new IdGenerator();
		CommomnDataObjectFatch cmn = new CommomnDataObjectFatch(serv);
		OrgPostMst orgPostMst = cmn.getOrgPostMst(postId);
		long officePostId = idgen.PKGeneratorWebService("hr_pay_officepost_mpg", serv, loggedInUser, langId, locId);
		logger.info("generated officePostId is ===>"+officePostId);
		hrOfficePostMpg.setOfficePostId(officePostId);
		hrOfficePostMpg.setCmnLocationMst(cmnLocationMst_en);
		hrOfficePostMpg.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
		hrOfficePostMpg.setCreatedDate(sysdate);
		hrOfficePostMpg.setOrgUserMstByUpdatedBy(loggedInOrgUserMst);
		hrOfficePostMpg.setOrgPostMstByCreatedByPost(setOrgPostMst);
		hrOfficePostMpg.setOrgPostMstByUpdatedByPost(setOrgPostMst);
		hrOfficePostMpg.setOrgPostMstByPostId(orgPostMst);
		HrPayOfficePostMpgDAOImpl officePostMpgDAOImpl = new HrPayOfficePostMpgDAOImpl(HrPayOfficepostMpg.class, serv.getSessionFactory());
		officePostMpgDAOImpl.create(hrOfficePostMpg);
	}
	
	
	 
	
	public void updateOrgPostMst( OrgPostMst updateOrgPostMst,OrgUserMst loggedInOrgUserMst,OrgPostMst setOrgPostMst,CmnBranchMst cmnBranchMst,CmnLocationMst cmnLocationMst_en,OrgPostMst orgPostMst,OrgDesignationMst orgDesignationMst_en)throws Exception
	{
		
		logger.info("updateOrgPostMst in NewEmployeeConfigServiceHelper");
		Date sysdate = new Date();
		OrgPostMstDao orgPostMstDao = new OrgPostMstDaoImpl(OrgPostMst.class, serv.getSessionFactory());
		updateOrgPostMst.setPostLevelId(orgPostMst.getPostLevelId());
		updateOrgPostMst.setStartDate(orgPostMst.getStartDate());
		updateOrgPostMst.setEndDate(orgPostMst.getEndDate());
		updateOrgPostMst.setParentPostId(orgPostMst.getParentPostId());
		updateOrgPostMst.setDsgnCode(orgDesignationMst_en.getDsgnCode());
		updateOrgPostMst.setActivateFlag(orgPostMst.getActivateFlag());
		if (cmnBranchMst != null)
			updateOrgPostMst.setBranchCode(cmnBranchMst.getBranchCode());
		else
			updateOrgPostMst.setBranchCode(null);
		updateOrgPostMst.setLocationCode(cmnLocationMst_en.getLocationCode());
		updateOrgPostMst.setActivateFlag(orgPostMst.getActivateFlag());
		if (orgPostMst.getPostTypeLookupId() != null)
			updateOrgPostMst.setPostTypeLookupId(orgPostMst.getPostTypeLookupId());
		else
			updateOrgPostMst.setPostTypeLookupId(null);
		updateOrgPostMst.setCmnLookupMst(orgPostMst.getCmnLookupMst());
		updateOrgPostMst.setUpdatedDate(sysdate);
		updateOrgPostMst.setOrgPostMstByUpdatedByPost(setOrgPostMst);
		updateOrgPostMst.setOrgUserMstByUpdatedBy(loggedInOrgUserMst);
		orgPostMstDao.update(updateOrgPostMst);
	}
	
	public void updateOrgPostDetailsRlt( OrgPostDetailsRlt orgPostDetailsRlt_en,CmnBranchMst objCmnBranchMst_en,OrgPostMst setOrgPostMst,OrgPostDetailsRlt updateOrgPostDetailsRlt_en,OrgUserMst loggedInOrgUserMst,CmnLanguageMst cmnLanguageMst_en,OrgDesignationMst orgDesignationMst_en,CmnLocationMst cmnLocationMst_en)throws Exception
	{
		logger.info("updateOrgPostDetailsRlt in NewEmployeeConfigServiceHelper");
		Date sysdate = new Date();
		OrgPostDetailsRltDao orgPostDetailsRltDao = new OrgPostDetailsRltDaoImpl(OrgPostDetailsRlt.class, serv.getSessionFactory());
		updateOrgPostDetailsRlt_en.setOrgDesignationMst(orgDesignationMst_en);
		updateOrgPostDetailsRlt_en.setCmnLocationMst(cmnLocationMst_en);
		updateOrgPostDetailsRlt_en.setCmnLanguageMst(cmnLanguageMst_en);
		updateOrgPostDetailsRlt_en.setUpdatedDate(sysdate);
		updateOrgPostDetailsRlt_en.setOrgUserMstByUpdatedBy(loggedInOrgUserMst);
		updateOrgPostDetailsRlt_en.setOrgPostMstByUpdatedByPost(setOrgPostMst);
		updateOrgPostDetailsRlt_en.setCmnBranchMst(objCmnBranchMst_en);
		updateOrgPostDetailsRlt_en.setPostName(orgPostDetailsRlt_en.getPostName());
		updateOrgPostDetailsRlt_en.setPostShortName(orgPostDetailsRlt_en.getPostShortName());
		if (updateOrgPostDetailsRlt_en != null)
			orgPostDetailsRltDao.update(updateOrgPostDetailsRlt_en);
	}
	

	public void insertHrPayOfficepostMpgForCash2AdminWhenListZero( long postId,HrPayOfficepostMpg hrOfficePostMpg,OrgPostMst setOrgPostMst,long loggedInUser,long langId,long locId,CmnLocationMst cmnLocationMst_en,OrgUserMst loggedInOrgUserMst)throws Exception
	{
		
		IdGenerator idgen = new IdGenerator();
		CommomnDataObjectFatch cmn = new CommomnDataObjectFatch(serv);
		OrgPostMst orgPostMst = cmn.getOrgPostMst(postId);
		HrPayOfficePostMpgDAOImpl officePostMpgDAOImpl = new HrPayOfficePostMpgDAOImpl(HrPayOfficepostMpg.class, serv.getSessionFactory());
	long officePostId = idgen.PKGeneratorWebService("hr_pay_officepost_mpg", serv, loggedInUser, langId, locId);
	logger.info("generated officePostId is ===>"+officePostId);
	hrOfficePostMpg.setOfficePostId(officePostId);
	hrOfficePostMpg.setCmnLocationMst(cmnLocationMst_en);
	hrOfficePostMpg.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
	hrOfficePostMpg.setOrgUserMstByUpdatedBy(loggedInOrgUserMst);
	hrOfficePostMpg.setOrgPostMstByCreatedByPost(setOrgPostMst);
	hrOfficePostMpg.setOrgPostMstByUpdatedByPost(setOrgPostMst);
	
	hrOfficePostMpg.setOrgPostMstByPostId(orgPostMst);
	officePostMpgDAOImpl.create(hrOfficePostMpg);
	
	}
	
	
	
	
	public void insertOrgUserpostRlt(OrgUserpostRlt orgUserpostRlt, RltDcpsPayrollEmp empData,MstEmp empMst)throws Exception
	{
		
		 
		UserPostDao userPostDao  = new UserPostDaoImpl(OrgUserpostRlt.class,serv.getSessionFactory());	
		
		CmnLookupMstDAOImpl cmnLookupMstDao  = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
		CmnLookupMst cmnLookupMst = cmnLookupMstDao.getLookUpVOByLookUpNameAndLang("Primary_Post",1);
		orgUserpostRlt.setActivateFlag(1);
		logger.info("Inside New Emp Reg service - Date of Current post in DCPS is " + empData.getCurrPostJoiningDate());
		orgUserpostRlt.setStartDate(empData.getCurrPostJoiningDate());
		
		orgUserpostRlt.setCmnLookupUserPostType(cmnLookupMst);
		orgUserpostRlt.setCreatedDate(new java.util.Date());
		long userPostId=userPostDao.create(orgUserpostRlt);
		logger.info(" org_user_post_rlt created"+userPostId);
	}
	public void updateOrgUserpostRlt(OrgPostMst loggedInOrgPostMst,long langId,OrgUserMst loggedInOrgUserMst,long lUserPostRltId,OrgUserMst orgUserMst,OrgUserpostRlt orgUserpostRltVoGen)throws Exception
	{
		OrgUserpostRlt orgUserpostRlt=new OrgUserpostRlt();
		CmnLookupMstDAOImpl cmnLookupMstDao  = new CmnLookupMstDAOImpl(CmnLookupMst.class, serv.getSessionFactory());
		CmnLookupMst cmnLookupMst = cmnLookupMstDao.getLookUpVOByLookUpNameAndLang("Primary_Post",langId);
		
		UserPostDao userPostDao  = new UserPostDaoImpl(OrgUserpostRlt.class,serv.getSessionFactory());	
		logger.info("Activate Flag is :--->>>"+orgUserpostRltVoGen.getActivateFlag());					
		long aFlag = orgUserpostRltVoGen.getActivateFlag();
		orgUserpostRlt = userPostDao.read(lUserPostRltId);
		CommomnDataObjectFatch cmn = new CommomnDataObjectFatch(serv);
		//OrgUserMst orgUserMst = cmn.getorgUserMst(userId);
		logger.info("orgUserpostRlt getEmpPostId:::"+orgUserpostRlt.getEmpPostId());
		orgUserpostRlt.setUpdatedDate(new java.util.Date());
		orgUserpostRlt.setOrgUserMstByUpdatedBy(loggedInOrgUserMst);
		orgUserpostRlt.setOrgPostMstByUpdatedByPost(loggedInOrgPostMst);
		orgUserpostRlt.setOrgPostMstByPostId(orgUserpostRltVoGen.getOrgPostMstByPostId());
		orgUserpostRlt.setOrgUserMst(orgUserMst);
		orgUserpostRlt.setStartDate(orgUserpostRltVoGen.getStartDate());
		orgUserpostRlt.setEndDate(orgUserpostRltVoGen.getEndDate());
		orgUserpostRlt.setCmnLookupUserPostType(cmnLookupMst);
		orgUserpostRlt.setActivateFlag(aFlag);
		userPostDao.update(orgUserpostRlt);
	}
	
	public long  insertHrPayOrderMst( HrPayOrderMst hrPayOrderMstVo,CmnLanguageMst cmnLangMstEng,long loggedInUser,long langId,long locId)throws Exception
	{
		Date sysdate = new Date();
		OrderMstDAOImpl ordermstDAOImpl = new OrderMstDAOImpl(HrPayOrderMst.class,serv.getSessionFactory());
		CommomnDataObjectFatch cmn = new CommomnDataObjectFatch(serv);
		//CmnLanguageMst cmnLangMstEng = cmn.getCmnLanguageMst(langId);
		IdGenerator idGen = new IdGenerator();
		long newOrderMstId = idGen.PKGeneratorWebService("hr_pay_order_mst", serv, loggedInUser, langId, locId);
		logger.info("generated newOrderMstId id is ::"+newOrderMstId);
		hrPayOrderMstVo.setOrderId(newOrderMstId);
		hrPayOrderMstVo.setCmnLanguageMst(cmnLangMstEng);
		hrPayOrderMstVo.setCreatedDate(sysdate);
		ordermstDAOImpl.create(hrPayOrderMstVo);
		return newOrderMstId;
	}
	public long  insertHrPayOrderHeadMpg(OrgPostMst loggedInOrgPostMst,long orderheadId,long lLongBillHeadId,OrgUserMst loggedInOrgUserMst,long newOrderMstId, long loggedInUser,long langId,long locId)throws Exception
	{
		Date sysdate = new Date();
		MstDcpsBillGroup bhmVO = new MstDcpsBillGroup();
		BillHeadMpgDAOImpl bhmDAOImpl = new BillHeadMpgDAOImpl(MstDcpsBillGroup.class,serv.getSessionFactory());						
		bhmVO = bhmDAOImpl.read(lLongBillHeadId);
		HrPayOrderHeadMpg hrPayOrderHeadMpgVO = new HrPayOrderHeadMpg();
		OrderHeadMpgDAOImpl orderHeadMpgDAOImp = new OrderHeadMpgDAOImpl(HrPayOrderHeadMpg.class,serv.getSessionFactory());
		IdGenerator idGen = new IdGenerator();
		long newOrderHeadId = idGen.PKGeneratorWebService("hr_pay_order_head_mpg", serv, loggedInUser, langId, locId);
		orderheadId = newOrderHeadId;
		hrPayOrderHeadMpgVO.setOrderHeadId(newOrderHeadId);
		hrPayOrderHeadMpgVO.setOrderId(newOrderMstId);
		hrPayOrderHeadMpgVO.setSubheadId(bhmVO.getDcpsDdoSchemeCode());
		hrPayOrderHeadMpgVO.setCreatedDate(sysdate);
		hrPayOrderHeadMpgVO.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
		hrPayOrderHeadMpgVO.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
		hrPayOrderHeadMpgVO.setTrnCounter(1);
		orderHeadMpgDAOImp.create(hrPayOrderHeadMpgVO);
		return orderheadId;
	}
	public HrPayOrderHeadPostMpg insertHrPayOrderHeadPostMpg(long newPostId,long orderHeadPostId,OrgUserMst loggedInOrgUserMst,OrgPostMst loggedInOrgPostMst, long loggedInUser,long orderheadId,long langId,long locId)throws Exception
	{
		HrPayOrderHeadPostMpg hrPayOrderHeadPostMpg=new HrPayOrderHeadPostMpg();
		OrderHeadPostmpgDAOImpl ohpMpgDAO = new OrderHeadPostmpgDAOImpl(HrPayOrderHeadPostMpg.class,serv.getSessionFactory());
		Date sysdate = new Date();
		logger.info("orderHeadPostIdorderHeadPostIdorderHeadPostIdorderHeadPostId" + orderHeadPostId);
		hrPayOrderHeadPostMpg.setOrderHeadPostId(orderHeadPostId);
		hrPayOrderHeadPostMpg.setOrderHeadId(orderheadId);
		hrPayOrderHeadPostMpg.setPostId(newPostId);
		hrPayOrderHeadPostMpg.setTrnCounter(new Integer(1));
		hrPayOrderHeadPostMpg.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
		hrPayOrderHeadPostMpg.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
		hrPayOrderHeadPostMpg.setCreatedDate(sysdate);	
		ohpMpgDAO.create(hrPayOrderHeadPostMpg);//ready to insert
		return hrPayOrderHeadPostMpg;
	}
	
	public void updateHrPayOrderHeadPostMpg(String lStrOhpId,long orderheadId, long loggedInUser,long langId,long locId,OrgUserMst loggedInOrgUserMst,OrgPostMst loggedInOrgPostMst,long lOhpId,OrgUserpostRlt orgUserpostRltVoGen)throws Exception
	{
		Date sysdate = new Date();
		logger.info("lOhpId in service update mode::"+lOhpId);
		HrPayOrderHeadPostMpg hrPayOrderHeadPostMpg=new HrPayOrderHeadPostMpg();
		OrderHeadPostmpgDAOImpl ohpMpgDAO = new OrderHeadPostmpgDAOImpl(HrPayOrderHeadPostMpg.class,serv.getSessionFactory());//object of DAOIMPL
		hrPayOrderHeadPostMpg.setOrderHeadId(orderheadId);
		logger.info("orderheadId in service update mode::"+orderheadId);
		logger.info("orgUserpostRltVoGen.getOrgPostMstByPostId().getPostId():::"+orgUserpostRltVoGen.getOrgPostMstByPostId().getPostId());
		hrPayOrderHeadPostMpg.setPostId(orgUserpostRltVoGen.getOrgPostMstByPostId().getPostId());
		hrPayOrderHeadPostMpg.setTrnCounter(new Integer(1));
		hrPayOrderHeadPostMpg.setOrgPostMstByUpdatedByPost(loggedInOrgPostMst);
		hrPayOrderHeadPostMpg.setOrgUserMstByUpdatedBy(loggedInOrgUserMst);
		hrPayOrderHeadPostMpg.setUpdatedDate(sysdate);	
		//TODO
		logger.info("lOhpId::"+lOhpId);
		if(lOhpId!=0)
		{
			logger.info("ohpid is not 0 in edit mode simply update::");
			if(lOhpId!=0)							
				hrPayOrderHeadPostMpg = ohpMpgDAO.read(lOhpId);
			else
				hrPayOrderHeadPostMpg = ohpMpgDAO.read(Long.parseLong(lStrOhpId));							
			hrPayOrderHeadPostMpg.setOrderHeadId(orderheadId);
			logger.info(" pOst id to be update::"+orgUserpostRltVoGen.getOrgPostMstByPostId().getPostId());						
			ohpMpgDAO.update(hrPayOrderHeadPostMpg);
		}
		else
		{
			logger.info("ohpid is o in edit mode so insert new entry::");
			IdGenerator idGen = new IdGenerator();
			long orderHeadPostId = idGen.PKGeneratorWebService("hr_pay_order_head_post_mpg", serv, loggedInUser, langId, locId);
			logger.info (" orderHeadPostId:: "+orderHeadPostId);
			hrPayOrderHeadPostMpg.setOrgPostMstByCreatedByPost(loggedInOrgPostMst);
			hrPayOrderHeadPostMpg.setOrgUserMstByCreatedBy(loggedInOrgUserMst);
			hrPayOrderHeadPostMpg.setCreatedDate(sysdate);	
			hrPayOrderHeadPostMpg.setOrderHeadPostId(orderHeadPostId);						
			ohpMpgDAO.create(hrPayOrderHeadPostMpg);//ready to insert
		}

	}

	
	public void HrPayPsrPostMpgListNotZero(HrPayPsrPostMpg hrPayPsrPostMpg,List hrPayPsrPostMpgList,long billNo)throws Exception
	{
		PsrPostMpgDAOImpl psrPostMpgDAOImpl = new PsrPostMpgDAOImpl(HrPayPsrPostMpg.class, serv.getSessionFactory());
		hrPayPsrPostMpg = (HrPayPsrPostMpg)hrPayPsrPostMpgList.get(0);
		hrPayPsrPostMpg.setBillNo(billNo);
		psrPostMpgDAOImpl.update(hrPayPsrPostMpg);
	}
	
	public void HrPayPsrPostMpgLisIsZero(long psrPostId,HrPayPsrPostMpg hrPayPsrPostMpg,long psrId,long billNo,long locId)throws Exception
	{
		HrPayOrderHeadPostMpg hrPayOrderHeadPostMpg=new HrPayOrderHeadPostMpg();
		PsrPostMpgDAOImpl psrPostMpgDAOImpl = new PsrPostMpgDAOImpl(HrPayPsrPostMpg.class, serv.getSessionFactory());
		hrPayPsrPostMpg.setPsrId(psrId);//psr no
		hrPayPsrPostMpg.setBillNo(billNo);
		hrPayPsrPostMpg.setLoc_id(locId);
		hrPayPsrPostMpg.setPostId(hrPayOrderHeadPostMpg.getPostId());
		hrPayPsrPostMpg.setPsrPostId(psrPostId);//pk
		psrPostMpgDAOImpl.create(hrPayPsrPostMpg);
	}
	
	
	
	public void  insertOtherDtls( HrEisOtherDtls hrEisOtherDtls,  RltDcpsPayrollEmp empData,MstEmp empMst,Map objectArgs  ) throws Exception
	{
		long scaleId = Long.parseLong(empMst.getPayScale());
		GenericDaoHibernateImpl genDao = new GenericDaoHibernateImpl(HrEisScaleMst.class);
		genDao.setSessionFactory(serv.getSessionFactory());
		HrEisScaleMst hrEisScaleMst = (HrEisScaleMst)genDao.read(scaleId);
		CmnLookupMstDAOImpl cmnDao = new CmnLookupMstDAOImpl(CmnLookupMst.class,serv.getSessionFactory());
		
		long dsgnId = Long.parseLong(empMst.getDesignation());
		 genDao = new GenericDaoHibernateImpl(OrgDesignationMst.class);
		genDao.setSessionFactory(serv.getSessionFactory());
		OrgDesignationMst orgDesignationMst = (OrgDesignationMst)genDao.read(dsgnId);
		
		
		GradeMasterDAO gradeDao = new GradeMasterDAO(OrgGradeMst.class,serv.getSessionFactory());
		 
		long cadre = Long.parseLong(empMst.getCadre()!=null&&!empMst.getCadre().equals("")?empMst.getCadre():"0");
		Map loginMap = (Map) objectArgs.get("baseLoginMap");
		CmnLocationMst cmnLocationMst = (CmnLocationMst) loginMap.get("locationVO");
		// added by manish for tight binding of cadre group and designation
		PayBillDAOImpl billDAOImpl = new PayBillDAOImpl(HrPayPaybill.class,serv.getSessionFactory());
		
		String ddoCode="";
		OrgDdoMst ddoMst = new OrgDdoMst();
		if(objectArgs.get("ddoCode")!= null)
		{	ddoCode= objectArgs.get("ddoCode").toString();
			ddoMst=billDAOImpl.getOrgDdoMstFromDDOCode(ddoCode);
			 genDao = new GenericDaoHibernateImpl(CmnLocationMst.class);
			genDao.setSessionFactory(serv.getSessionFactory());
			cmnLocationMst = (CmnLocationMst)genDao.read(Long.parseLong(ddoMst.getLocationCode())); 
			
		}
		
		
		
		
		long parentLocId = cmnLocationMst.getParentLocId();
		AdminOrgPostDtlDaoImpl adminOrgPostDtlDaoImpl = new AdminOrgPostDtlDaoImpl(
				OrgPostMst.class, serv.getSessionFactory());
		List<MstPayrollDesignationMst> lst = adminOrgPostDtlDaoImpl
				.getMstDcpsDsgnObject(parentLocId, dsgnId);
		MstPayrollDesignationMst mst = new MstPayrollDesignationMst();
		if (lst != null && lst.size() > 0)
			mst = lst.get(0);
		long cadreTypeId = mst.getCadreTypeId(); //cadre must come 

		// ended by manish
		 /* genDao = new GenericDaoHibernateImpl(DcpsCadreMst.class);
		genDao.setSessionFactory(serv.getSessionFactory());
		DcpsCadreMst cdrMst = (DcpsCadreMst)genDao.read(cadreTypeId);
		 */
	 
		long grpName = Long.parseLong(new Long(cadreTypeId).toString());
		CmnLookupMst  loonkupGrd = cmnDao.read(grpName);
		
		 genDao = new GenericDaoHibernateImpl(OrgGradeMst.class);
		genDao.setSessionFactory(serv.getSessionFactory());
		//logger.info(loonkupGrd.getLookupShortName().toString());
		long gradeId = Long.parseLong(loonkupGrd.getLookupShortName().toString());
		
		GradDesgScaleMapDAO sgdDao = new GradDesgScaleMapDAO(HrEisSgdMpg.class,serv.getSessionFactory());
		OrgGradeMst orgGradeMst = (OrgGradeMst)genDao.read(gradeId);
		
		
		HrEisGdMpg gdMpg=null;
		HrEisSgdMpg sgdMpg = null;
		List<HrEisGdMpg> gdList  = gradeDao.getDuplicateData(gradeId, dsgnId,cmnLocationMst.getLocId());
		IdGenerator idGen = new IdGenerator();
		if(gdList!=null && gdList.size()>0)
		{
			gdMpg= gdList.get(0);
			long gdId = gdMpg.getGdMapId();
			
			//List<HrEisSgdMpg> sgdMpglist = sgdDao.getScaleGradeDesgMasterData( gdId,  scaleId,cmnLocationMst.getLocId());
			//List<HrEisSgdMpg> sgdMpglist = sgdDao.getScaleGradeDesgMasterData( gdId,  scaleId);
			List<HrEisSgdMpg> sgdMpglist = sgdDao.getScaleGradeDesgMasterData( gdId,  scaleId,cmnLocationMst.getLocId());
			if(sgdMpglist!=null && sgdMpglist.size()>0)
			{	sgdMpg = sgdMpglist.get(0);
			
			}
			else
			{
				//insert in sgd mapping
				sgdMpg = new HrEisSgdMpg();
				long sgdId = idGen.PKGenerator("HR_EIS_SGD_MPG", objectArgs);
				sgdMpg.setSgdMapId(sgdId);
				sgdMpg.setCmnDatabaseMst(hrEisOtherDtls.getCmnDatabaseMst());
				//sgdMpg.setCmnLocationMst(hrEisScaleMst.getCmnLocationMst());
				sgdMpg.setCmnLocationMst(cmnLocationMst);
				sgdMpg.setCreatedDate(new java.util.Date());
				sgdMpg.setHrEisGdMpg(gdMpg);
				sgdMpg.setHrEisScaleMst(hrEisScaleMst);
				sgdMpg.setOrgPostMstByCreatedByPost(hrEisOtherDtls.getOrgPostMstByCreatedByPost());
				sgdMpg.setOrgUserMstByCreatedBy(hrEisOtherDtls.getOrgUserMstByCreatedBy());
				long sgdMpgCreated = sgdDao.create(sgdMpg);
				logger.info(" HR_EIS_SGD_MPG created "+sgdMpgCreated);
			}
			
			
			
		}
		else
		{
			//insert gdmpg
			gdMpg = new HrEisGdMpg();
			long gdId =  idGen.PKGenerator("HR_EIS_GD_MPG", objectArgs);
			gdMpg.setCmnDatabaseMst(hrEisOtherDtls.getCmnDatabaseMst());
			//gdMpg.setCmnLocationMst(hrEisScaleMst.getCmnLocationMst());
			gdMpg.setCmnLocationMst(cmnLocationMst);
			
			gdMpg.setCreatedDate(new java.util.Date());
			gdMpg.setGdMapId(gdId);
			gdMpg.setOrgDesignationMst(orgDesignationMst);
			gdMpg.setOrgGradeMst(orgGradeMst);
			gdMpg.setOrgPostMstByCreatedByPost(hrEisOtherDtls.getOrgPostMstByCreatedByPost());
			gdMpg.setOrgUserMstByCreatedBy(hrEisOtherDtls.getOrgUserMstByCreatedBy());
			genDao = new GenericDaoHibernateImpl(HrEisGdMpg.class);
			genDao.setSessionFactory(serv.getSessionFactory());
			logger.info("gd Created "+genDao.create(gdMpg));
			
			
			
			//insert in sgd mapping
			
			sgdMpg = new HrEisSgdMpg();
			long sgdId = idGen.PKGenerator("HR_EIS_SGD_MPG", objectArgs);
			sgdMpg.setSgdMapId(sgdId);
			sgdMpg.setCmnDatabaseMst(hrEisOtherDtls.getCmnDatabaseMst());
			//sgdMpg.setCmnLocationMst(hrEisScaleMst.getCmnLocationMst());
			sgdMpg.setCmnLocationMst(cmnLocationMst);
			sgdMpg.setCreatedDate(new java.util.Date());
			sgdMpg.setHrEisGdMpg(gdMpg);
			sgdMpg.setHrEisScaleMst(hrEisScaleMst);
			sgdMpg.setOrgPostMstByCreatedByPost(hrEisOtherDtls.getOrgPostMstByCreatedByPost());
			sgdMpg.setOrgUserMstByCreatedBy(hrEisOtherDtls.getOrgUserMstByCreatedBy());
			long sgdMpgCreatedTwo = sgdDao.create(sgdMpg);
			logger.info(" HR_EIS_SGD_MPG created "+sgdMpgCreatedTwo);
		}
		
		
		
		hrEisOtherDtls.setHrEisSgdMpg(sgdMpg);
		hrEisOtherDtls.setPhyChallenged(empData.getPhychallanged()); 
		//11 jan 2012
		hrEisOtherDtls.setIsAvailedHRA(0);
		hrEisOtherDtls.setTrnCounter(1);
		logger.info(" empMst.getBasicPay() is "+ empMst.getBasicPay());
		Double basicPay= empMst.getBasicPay();
		long lngBasic = (basicPay).longValue();
		
		logger.info("basic arr is "+lngBasic);
		 
		hrEisOtherDtls.setotherCurrentBasic(new Long(lngBasic));
	
		OtherDetailDAOImpl otherDao = new OtherDetailDAOImpl(HrEisOtherDtls.class,serv.getSessionFactory());
		long otherDtlsId = otherDao.create(hrEisOtherDtls);
		logger.info(" hr_eis_other_dtls created "+otherDtlsId);
		
		
	}
	//Ended by Abhilash
	
}
