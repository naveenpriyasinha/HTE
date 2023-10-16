package com.tcs.sgv.eis.employeeInfo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.common.valueobject.CmnAttachmentMpg;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLookupMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.employeeInfo.valueobject.EmpOtherDetailsVO;
import com.tcs.sgv.eis.employeeInfo.valueobject.GeneralEmpInfoVO;
import com.tcs.sgv.eis.hrModInfo.valueobject.HrModEmpRlt;
import com.tcs.sgv.eis.hrModInfo.valueobject.HrModMst;
import com.tcs.sgv.eis.valueobject.HrEisBiometricDtl;
import com.tcs.sgv.eis.valueobject.HrEisEmpMst;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.ess.valueobject.OrgDesignationMst;
import com.tcs.sgv.ess.valueobject.OrgEmpDsgnMpg;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgPostMst;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;
import com.tcs.sgv.payfixation.valueobject.HrPayfixMst;

/**
 * @author 223001
 * 
 */
public class GeneralEmpInfoDaoImpl extends GenericDaoHibernateImpl {
	private static final Log logger = LogFactory.getLog(GeneralEmpInfoDaoImpl.class);
	public GeneralEmpInfoDaoImpl(Class type, SessionFactory sessionFactory) {
		super(type);
		setSessionFactory(sessionFactory);
	}

	/**
	 * @param userId
	 * @return
	 */
	public GeneralEmpInfoVO findByEmpIDOtherDetail(long userId, long langId) {

		GeneralEmpInfoVO genEmpVO = new GeneralEmpInfoVO();// this the Custom
															// VO
		List empAdd = new ArrayList();
		List empDtl = new ArrayList();
		List empPostDtl = new ArrayList();
		List empPhotoList = new ArrayList();
		Session hibSession = null;
		long biometricId = 0;
		CmnLanguageMst cmnLanguageMst = new CmnLanguageMst();
		cmnLanguageMst.setLangId(langId);
		hibSession = getSession();

		genEmpVO.setUserId(userId);

		/* this code is used for to get the EmpId and Emp Name DOJ DOR */
		OrgUserMst orgUserObj = new OrgUserMst();
		orgUserObj.setUserId(userId);
		Criteria EmpCrit = hibSession.createCriteria(OrgEmpMst.class);
		EmpCrit.add(Restrictions.eq("orgUserMst", orgUserObj));
		EmpCrit.add(Restrictions.eq("cmnLanguageMst", cmnLanguageMst));
		empDtl = EmpCrit.list();
		
		if (empDtl.isEmpty() == false) {
			OrgEmpMst empDet = (OrgEmpMst) empDtl.get(0);

			genEmpVO.setEmpName(empDet.getEmpFname() + " " + empDet.getEmpMname()+ " "
					+ empDet.getEmpLname());
			genEmpVO.setDoj(empDet.getEmpDoj());
			// generalEmpInfoVO.setDor(empDet.getEmpSrvcExp());
			genEmpVO.setEmpid(empDet.getEmpId());
			genEmpVO.setDob(empDet.getEmpDob());
			genEmpVO.setDor(empDet.getEmpSrvcExp());
			genEmpVO.setEmpId(empDet.getEmpId());
			logger.info("EmpName>>>>>>>>>>" + genEmpVO.getEmpName());
			/* this code used to get Emp Address ,city */
			
			/*OrgUserpostRlt orgUserpostRlt = (OrgUserpostRlt) hibSession.get(
					OrgUserpostRlt.class, userId);*/
			
			OrgUserpostRlt orgUserpostRlt = new OrgUserpostRlt();
			
			Criteria criteriaUserPost = hibSession.createCriteria(OrgUserpostRlt.class);
			criteriaUserPost.add(Restrictions.eq("orgUserMst.userId", userId))
							.add(Restrictions.eq("activateFlag", 1l))
							.add(Restrictions.in("cmnLookupUserPostType", this.getUserpostTypeStatusLookupMst()));
								

			List userPost = criteriaUserPost.list();
			
			logger.info("===========userPostList in Employee Info==============="+ userPost.size());
			
			if (userPost != null && !userPost.isEmpty())
			{
				orgUserpostRlt = (OrgUserpostRlt) userPost.get(0);
			}
				
				
				
		   
			long postId = 0;
			
			if (orgUserpostRlt.getOrgPostMstByPostId() != null)
			{
				postId = orgUserpostRlt.getOrgPostMstByPostId().getPostId();
			}
			
			OrgPostMst orgPostMst = new OrgPostMst();
			orgPostMst.setPostId(postId);

			/*
			 * OrgPostDetailsRlt orgPostDtlObj = (OrgPostDetailsRlt)
			 * hibSession.get( OrgPostDetailsRlt.class, postId );
			 */
			Criteria criteriaPost = hibSession
					.createCriteria(OrgPostDetailsRlt.class);
			criteriaPost.add(Restrictions.eq("orgPostMst", orgPostMst));
			criteriaPost.add(Restrictions.eq("cmnLanguageMst", cmnLanguageMst));
			empPostDtl = criteriaPost.list();
			if (empPostDtl.isEmpty() == false) {

				OrgPostDetailsRlt orgPostDetailsRlt = (OrgPostDetailsRlt) empPostDtl
						.get(0);
				logger.info("orgPostDetailsRlt>>>>>>>>>>>>>byPankaj"
						+ orgPostDetailsRlt.getPostName());
				genEmpVO.setDepId(orgPostDetailsRlt.getCmnLocationMst()
						.getDepartmentId());

				genEmpVO.setPostId(orgPostDetailsRlt.getOrgPostMst()
						.getPostId());
				genEmpVO.setPostName(orgPostDetailsRlt.getPostName());
				
				/*System.out
						.println("orgPostDetailsRlt>>>>>>>Designation ID >>by Pankaj >>>>"
								+ orgPostDetailsRlt.getOrgDesignationMst()
										.getDsgnName());
				genEmpVO.setDesigid(orgPostDetailsRlt.getOrgDesignationMst()
						.getDsgnId());
				genEmpVO.setDesigCode(orgPostDetailsRlt.getOrgDesignationMst()
						.getDsgnCode());
				genEmpVO.setDesignation(orgPostDetailsRlt
						.getOrgDesignationMst().getDsgnName());*/
			} else {
				logger.info("<<<<empPostDtl List is empty >>>>");
			}
			
			 OrgEmpMst empMst =this.getEmpIdbyUserId(userId, langId);
  	          List<OrgDesignationMst> empDesigList=  this.getActiveDesignationsForEmployee(empMst);
  	          
  	          if(!empDesigList.isEmpty()){
  	        	 OrgDesignationMst designationMst=(OrgDesignationMst) empDesigList.get(0);
  	        	genEmpVO.setDesigCode(designationMst.getDsgnCode());
  	        	genEmpVO.setDesigid(designationMst.getDsgnId());
  	        	genEmpVO.setDesignation(designationMst.getDsgnName());
  	        	
  	          }
  	          
  	          
  	        HrPayfixMst hrPayfixMst= this.getPayDtlsofEmp(userId);
    		if(hrPayfixMst!=null)
    		{
    			/*genEmpVO.setSalary(hrPayfixMst.getRevPay());
    			
    			if (hrPayfixMst.getRevPayScale() != null)
    			{
    				genEmpVO.setScaleStart(hrPayfixMst.getRevPayScale().getScaleStartAmt());
    				genEmpVO.setScaleInc(hrPayfixMst.getRevPayScale().getScaleIncrAmt());
    				genEmpVO.setScaleEnd(hrPayfixMst.getRevPayScale().getScaleEndAmt());
					genEmpVO.setScaleId(hrPayfixMst.getRevPayScale().getScaleId());
    			}*/
    		}

			OrgEmpMst orgEmpMst = (OrgEmpMst) getEnglishEmpIdbyUserId(userId)
					.get(0);
			long empId = orgEmpMst.getEmpId();

			Criteria criteriaEmpMst = hibSession
					.createCriteria(HrEisEmpMst.class);
			criteriaEmpMst.add(Restrictions.eq("orgEmpMst", orgEmpMst));
			empAdd = criteriaEmpMst.list();
			if (empAdd.isEmpty() == false) {
				HrEisEmpMst eisEmpMst = (HrEisEmpMst) empAdd.get(0);

				logger.info("Pankaj Test Emp ID english >>>>>>>" + empId);

				
				/*if(eisEmpMst.getCmnAddressMstByEmpCurrentAddressId()!=null && eisEmpMst.getCmnAddressMstByEmpCurrentAddressId().getAddressId()!=0 )
				{
					logger.info("eisEmpMst>>>>>>>>>>>>>>by Pankaj....>>>>"+ eisEmpMst.getCmnAddressMstByEmpCurrentAddressId().getAddressId());
					genEmpVO.setAddressId(eisEmpMst.getCmnAddressMstByEmpCurrentAddressId().getAddressId());
				}else
				{
					logger.info("eisEmpMst.getCmnAddressMstByEmpCurrentAddressId() is null on Line 134 ");
				}*/

				/*Criteria criteriaOtherEmpDtl = hibSession
						.createCriteria(HrEisOtherDtls.class);
				criteriaOtherEmpDtl.add(Restrictions.eq("hrEisEmpMst.empId",
						empId));
				empOtherDltList = criteriaOtherEmpDtl.list();
				if (empOtherDltList.isEmpty() == false) {

					HrEisOtherDtls hrEisOtherDtls = (HrEisOtherDtls) empOtherDltList
							.get(0);
					System.out
							.println("EMP salary By Pankaj >>>>>>>>>>>>>>>>>>>>>"
									+ hrEisOtherDtls.getotherCurrentBasic());
					genEmpVO.setSalary(hrEisOtherDtls.getotherCurrentBasic());
					genEmpVO.setScaleStart(hrEisOtherDtls.getHrEisSgdMpg()
							.getHrEisScaleMst().getScaleStartAmt());
					genEmpVO.setScaleInc(hrEisOtherDtls.getHrEisSgdMpg()
							.getHrEisScaleMst().getScaleIncrAmt());
					genEmpVO.setScaleEnd(hrEisOtherDtls.getHrEisSgdMpg()
							.getHrEisScaleMst().getScaleEndAmt());
				} else {
					System.out
							.println("empOtherDltList List is Empty hrEisEmpMst ");
				}*/
				
				if (eisEmpMst.getHrPersonBiometricDtls() != null) {

					biometricId = eisEmpMst.getHrPersonBiometricDtls()
							.getBiometricId();
					logger.info("biometric id in dao is :::::::"
							+ biometricId);
					Criteria critbiometId = hibSession
							.createCriteria(HrEisBiometricDtl.class);
					critbiometId.add(Restrictions
							.eq("biometricId", biometricId));
					empPhotoList = critbiometId.list();

					if (empPhotoList.isEmpty() == false) {
						HrEisBiometricDtl hrPersonBiometDtl = (HrEisBiometricDtl) empPhotoList
								.get(0);

						if (hrPersonBiometDtl
								.getCmnAttachmentMstByEmpPhotoAttachmentId() != null) {

							genEmpVO
									.setEmpPhotoId(hrPersonBiometDtl
											.getCmnAttachmentMstByEmpPhotoAttachmentId()
											.getAttachmentId());

							Criteria criteriaPhoto = hibSession
									.createCriteria(CmnAttachmentMpg.class);
							criteriaPhoto.add(Restrictions.eq(
									"cmnAttachmentMst.attachmentId", genEmpVO
											.getEmpPhotoId()));
							List photoSrList = criteriaPhoto.list();
							// List thumbImp = critPhotoId2.list();
							
							if (photoSrList != null && !photoSrList.isEmpty())
							{
								CmnAttachmentMpg cmnAttachmentMpg1 = (CmnAttachmentMpg) photoSrList
										.get(0);
								// CmnAttachmentMpg cmnAttachmentMpg2 =
								// (CmnAttachmentMpg) thumbImp.get(0);
	
								genEmpVO.setPhotoSrNo(cmnAttachmentMpg1.getSrNo());
							}
						} else {
							logger.info("hrPersonBiometDtl.getCmnAttachmentMstByEmpPhotoAttachmentId()==null");
						}

					}

				} else {
					logger.info("empAdd List ");
				}

			}else
			{
				logger.info("empAdd List ");
			}
				// Vo To Set all the
		}

		return genEmpVO;
	}

	/**
	 * @param langId
	 * @param userId
	 * @return
	 */
	public EmpOtherDetailsVO getEmpDtlByLangAndUserId(long langId, long userId) {

		EmpOtherDetailsVO empOtherDetailsVO = new EmpOtherDetailsVO();
		List empOtherDltList = new ArrayList();
		List empAdd = new ArrayList();
		List empDtl = new ArrayList();
		List empPostDtl = new ArrayList();
		List empPhotoList = new ArrayList();
		Session hibSession = null;
		long biometricId = 0;
		CmnLanguageMst cmnLanguageMst = new CmnLanguageMst();
		cmnLanguageMst.setLangId(langId);
		hibSession = getSession();

		empOtherDetailsVO.setUserId(userId);

		/* this code is used for to get the EmpId and Emp Name DOJ DOR */
		OrgUserMst orgUserObj = new OrgUserMst();
		orgUserObj.setUserId(userId);
		Criteria EmpCrit = hibSession.createCriteria(OrgEmpMst.class);
		EmpCrit.add(Restrictions.eq("orgUserMst", orgUserObj));
		EmpCrit.add(Restrictions.eq("cmnLanguageMst", cmnLanguageMst));
		empDtl = EmpCrit.list();
		if (empDtl.isEmpty() == false) {
			OrgEmpMst empDet = (OrgEmpMst) empDtl.get(0);

			empOtherDetailsVO.setEmpName(empDet.getEmpFname()
					+ empDet.getEmpMname() + empDet.getEmpLname());
			empOtherDetailsVO.setDateOfJoin(empDet.getEmpDoj());
			// generalEmpInfoVO.setDor(empDet.getEmpSrvcExp());
			empOtherDetailsVO.setEmpId(empDet.getEmpId());
			empOtherDetailsVO.setDateOfBirth(empDet.getEmpDob());

			logger.info("EmpName>>>>>>>>>>"
					+ empOtherDetailsVO.getEmpName());
			/* this code used to get Emp Address ,city */
			/*OrgUserpostRlt orgUserpostRlt = (OrgUserpostRlt) hibSession.get(
					OrgUserpostRlt.class, userId);*/
			
			
			
			OrgUserpostRlt orgUserpostRlt = new OrgUserpostRlt();
			
			Criteria criteriaUserPost = hibSession.createCriteria(OrgUserpostRlt.class);
			criteriaUserPost.add(Restrictions.eq("orgUserMst.userId", userId))
							.add(Restrictions.eq("activateFlag", 1l))
							.add(Restrictions.in("cmnLookupUserPostType", this.getUserpostTypeStatusLookupMst()));
								

			List userPost = criteriaUserPost.list();
			
			logger.info("===========userPostList in Employee Info==============="+ userPost.size());
			
			if (userPost != null && !userPost.isEmpty())
			{
				orgUserpostRlt = (OrgUserpostRlt) userPost.get(0);
			}
		   
			long postId = 0;
			
			if (orgUserpostRlt.getOrgPostMstByPostId() != null)
			{
				postId = orgUserpostRlt.getOrgPostMstByPostId().getPostId();
			}
			
			OrgPostMst orgPostMst = new OrgPostMst();
			orgPostMst.setPostId(postId);

			/*
			 * OrgPostDetailsRlt orgPostDtlObj = (OrgPostDetailsRlt)
			 * hibSession.get( OrgPostDetailsRlt.class, postId );
			 */
			Criteria criteriaPost = hibSession
					.createCriteria(OrgPostDetailsRlt.class);
			criteriaPost.add(Restrictions.eq("orgPostMst", orgPostMst));
			criteriaPost.add(Restrictions.eq("cmnLanguageMst", cmnLanguageMst));
			empPostDtl = criteriaPost.list();
			if (empPostDtl.isEmpty() == false) {

				OrgPostDetailsRlt orgPostDetailsRlt = (OrgPostDetailsRlt) empPostDtl
						.get(0);
				logger.info("orgPostDetailsRlt>>>>>>>>>>>>>byPankaj"
						+ orgPostDetailsRlt.getPostName());
				empOtherDetailsVO.setDepId(orgPostDetailsRlt
						.getCmnLocationMst().getDepartmentId());

				empOtherDetailsVO.setPostId(orgPostDetailsRlt.getOrgPostMst()
						.getPostId());
				empOtherDetailsVO.setEmpPostName(orgPostDetailsRlt
						.getPostName());
				/*System.out
				.println("orgPostDetailsRlt>>>>>>>Designation ID >>by Pankaj >>>>"
						+ orgPostDetailsRlt.getOrgDesignationMst()
								.getDsgnName());
				empOtherDetailsVO.setDesigId(orgPostDetailsRlt
						.getOrgDesignationMst().getDsgnId());
				empOtherDetailsVO.setDesignation(orgPostDetailsRlt
						.getOrgDesignationMst().getDsgnName());*/

			} else {
				logger.info("<<<<empPostDtl List is empty >>>>");
			}
			
			OrgEmpMst empMst =this.getEmpIdbyUserId(userId, langId);
	          List<OrgDesignationMst> empDesigList=  this.getActiveDesignationsForEmployee(empMst);
	          
	          if(!empDesigList.isEmpty()){
	        	 OrgDesignationMst designationMst=(OrgDesignationMst) empDesigList.get(0);
	        	 empOtherDetailsVO.setDesigId(designationMst.getDsgnId());
	        	 empOtherDetailsVO.setDesignation(designationMst.getDsgnName());
	        	
	          }

			OrgEmpMst orgEmpMst = (OrgEmpMst) getEnglishEmpIdbyUserId(userId)
					.get(0);
			long empId = orgEmpMst.getEmpId();

			Criteria criteriaEmpMst = hibSession
					.createCriteria(HrEisEmpMst.class);
			criteriaEmpMst.add(Restrictions.eq("orgEmpMst", orgEmpMst));
			empAdd = criteriaEmpMst.list();

			HrEisEmpMst eisEmpMst = (HrEisEmpMst) empAdd.get(0);

			logger.info("Pankaj Test Emp ID english >>>>>>>" + empId);

			logger.info("eisEmpMst>>>>>>>>>>>>>>by Pankaj....>>>>"
					+ eisEmpMst.getCmnAddressMstByEmpCurrentAddressId()
							.getAddressId());
			empOtherDetailsVO.setAddressId(eisEmpMst
					.getCmnAddressMstByEmpCurrentAddressId().getAddressId());

			Criteria criteriaOtherEmpDtl = hibSession
					.createCriteria(HrEisOtherDtls.class);
			criteriaOtherEmpDtl
					.add(Restrictions.eq("hrEisEmpMst.empId", empId));
			empOtherDltList = criteriaOtherEmpDtl.list();
			if (empOtherDltList.isEmpty() == false) {

				HrEisOtherDtls hrEisOtherDtls = (HrEisOtherDtls) empOtherDltList
						.get(0);
				logger.info("EMP salary By Pankaj >>>>>>>>>>>>>>>>>>>>>"
						+ hrEisOtherDtls.getotherCurrentBasic());
				empOtherDetailsVO.setSalary(hrEisOtherDtls
						.getotherCurrentBasic());
				empOtherDetailsVO.setScaleStart(hrEisOtherDtls.getHrEisSgdMpg()
						.getHrEisScaleMst().getScaleStartAmt());
				empOtherDetailsVO.setScaleInc(hrEisOtherDtls.getHrEisSgdMpg()
						.getHrEisScaleMst().getScaleIncrAmt());
				empOtherDetailsVO.setScaleEnd(hrEisOtherDtls.getHrEisSgdMpg()
						.getHrEisScaleMst().getScaleEndAmt());
			} else {
				logger.info("empOtherDltList List is Empty hrEisEmpMst ");
			}

			biometricId = eisEmpMst.getHrPersonBiometricDtls().getBiometricId();
			logger.info("biometric id in dao is :::::::" + biometricId);
			Criteria critbiometId = hibSession
					.createCriteria(HrEisBiometricDtl.class);
			critbiometId.add(Restrictions.eq("biometricId", biometricId));
			empPhotoList = critbiometId.list();

			if (empPhotoList.isEmpty() == false) {
				HrEisBiometricDtl hrPersonBiometDtl = (HrEisBiometricDtl) empPhotoList
						.get(0);
				empOtherDetailsVO.setEmpPhotoId(hrPersonBiometDtl
						.getCmnAttachmentMstByEmpPhotoAttachmentId()
						.getAttachmentId());

				Criteria criteriaPhoto = hibSession
						.createCriteria(CmnAttachmentMpg.class);
				criteriaPhoto.add(Restrictions.eq(
						"cmnAttachmentMst.attachmentId", empOtherDetailsVO
								.getEmpPhotoId()));
				List photoSrList = criteriaPhoto.list();
				// List thumbImp = critPhotoId2.list();
				
				if (photoSrList != null && !photoSrList.isEmpty())
				{
					CmnAttachmentMpg cmnAttachmentMpg1 = (CmnAttachmentMpg) photoSrList
							.get(0);
					// CmnAttachmentMpg cmnAttachmentMpg2 = (CmnAttachmentMpg)
					// thumbImp.get(0);
	
					empOtherDetailsVO.setPhotoSrNo(cmnAttachmentMpg1.getSrNo());
				}
			}

		}

		return empOtherDetailsVO;
	}

	/**
	 * @param userId
	 * @return
	 */
	public List getEnglishEmpIdbyUserId(long userId) {
		List empId = new ArrayList();
		Session hibSession = null;
		hibSession = getSession();
		OrgUserMst orgUserMst = new OrgUserMst();
		orgUserMst.setUserId(userId);
		CmnLanguageMst cmnLanguageMst = new CmnLanguageMst();
		cmnLanguageMst.setLangId(1);
		Criteria criteriaEmpId = hibSession.createCriteria(OrgEmpMst.class);
		criteriaEmpId.add(Restrictions.eq("orgUserMst", orgUserMst));
		criteriaEmpId.add(Restrictions.eq("cmnLanguageMst", cmnLanguageMst));
		empId = criteriaEmpId.list();

		return empId;

	}
	
	public OrgEmpMst getEmpIdbyUserId(long userId,long langId) {
		List empId = new ArrayList();
		OrgEmpMst empMst =null;
		Session hibSession = null;
		hibSession = getSession();
		OrgUserMst orgUserMst = new OrgUserMst();
		orgUserMst.setUserId(userId);
		CmnLanguageMst cmnLanguageMst = new CmnLanguageMst();
		cmnLanguageMst.setLangId(langId);
		Criteria criteriaEmpId = hibSession.createCriteria(OrgEmpMst.class);
		criteriaEmpId.add(Restrictions.eq("orgUserMst", orgUserMst));
		criteriaEmpId.add(Restrictions.eq("cmnLanguageMst", cmnLanguageMst));
		empId = criteriaEmpId.list();
			
		if(empId.isEmpty()!=true){
			 empMst = (OrgEmpMst)empId.get(0);
			
		}
		
		
		return empMst;
	}
	
	
	
	public HrPayfixMst getPayDtlsofEmp(long userId) 
	{
	Session hibSession = getSession();
	
	HrPayfixMst hrPayfixMst=null;
	OrgUserMst orgUserMst=new OrgUserMst();
	orgUserMst.setUserId(userId);
	List<HrPayfixMst> ExamDtlVOList = new ArrayList<HrPayfixMst>();
	try
	{

	Criteria crit = hibSession.createCriteria(HrPayfixMst.class);

	crit.add(Restrictions.eq("userId", orgUserMst));
	//crit.add(Restrictions.eq("flagType", 'Y'));
	crit.add(Restrictions.le("payFixDate", new Date()));
	crit.add(Restrictions.ge("nxtIncrDate", new Date()));

	crit.addOrder(Order.desc("payFixDate"));

	ExamDtlVOList = crit.list();
	logger.info("list in dao========="+ExamDtlVOList.size());
	if (ExamDtlVOList != null && !ExamDtlVOList.isEmpty())
	{
	hrPayfixMst = (HrPayfixMst)ExamDtlVOList.get(0);
	}
	}
	catch(Exception e){
	logger.error("error while getting current pay dtls of employee"+e);
	}
	return hrPayfixMst;
	} 

	
	private List<CmnLookupMst> getUserpostTypeStatusLookupMst()
	{
		Session session = getSession();
		
		Criteria criteria = session.createCriteria(CmnLookupMst.class);
		criteria.add(Restrictions.eq("lookupName","Primary_Post"));
		 
		List<CmnLookupMst> locList=criteria.list();
		
		return locList;
	}

	public List<OrgDesignationMst>  getActiveDesignationsForEmployee(OrgEmpMst objOrgEmpMst)
    {
        List<OrgEmpDsgnMpg> empDesgnMpgList= null;
        List<OrgDesignationMst> dsgnList = new ArrayList<OrgDesignationMst>();
       
        Criteria objCrt = null;
     
        try
        {
            Session hibSession = getSession();      
            objCrt = hibSession.createCriteria(OrgEmpDsgnMpg.class);
            
            objCrt.add(Restrictions.eq("orgEmpMst",objOrgEmpMst));
            objCrt.add(Restrictions.eq("activateFlag",1l));
            objCrt.createAlias("orgDesignationMst", "desigMst");
            objCrt.addOrder(Order.desc("desigMst.dsgnLevel"));
            empDesgnMpgList = objCrt.list();
             
            Iterator itrEmpDsgnMpg = empDesgnMpgList.iterator();
                 
              while(itrEmpDsgnMpg.hasNext())
              {
                  OrgEmpDsgnMpg objOrgEmpDsgnMpg =(OrgEmpDsgnMpg) itrEmpDsgnMpg.next();
                  OrgDesignationMst objOrgDesignationMst = objOrgEmpDsgnMpg.getOrgDesignationMst();
                  
                  dsgnList.add(objOrgDesignationMst);
                 
              }
          
        }catch(Exception e)
        {
            logger.error(e.getMessage(), e);
        }
        
        
        return dsgnList;
    }
	
	public HrModEmpRlt getEmpData(long modId,long requestId,long userId)
	{
		HrModEmpRlt hrModEmpRlt = null;
		Session hibSession = hibSession = getSession();
		HrModMst hrModMst = new HrModMst(modId);
		
		Criteria criteriaModEmpRlt = hibSession.createCriteria(HrModEmpRlt.class);
		
		criteriaModEmpRlt.add(Restrictions.eq("reqId", requestId));
		criteriaModEmpRlt.add(Restrictions.eq("hrModMst", hrModMst));
		criteriaModEmpRlt.add(Restrictions.eq("userId", userId));
		
		List modempList = criteriaModEmpRlt.list();
		
		if (modempList != null && !modempList.isEmpty())
		{
			hrModEmpRlt = (HrModEmpRlt) modempList.get(0);
		}
		
		return hrModEmpRlt;
	}
}
