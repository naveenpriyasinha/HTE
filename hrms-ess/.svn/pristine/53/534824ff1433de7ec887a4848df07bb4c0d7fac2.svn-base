package com.tcs.sgv.ess.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.common.valueobject.CmnCityMst;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.ess.valueobject.GeneralEmpInfoVO;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgUserMst;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;
public class GeneralEmpInfoDaoImpl extends GenericDaoHibernateImpl{
	
	
	public GeneralEmpInfoDaoImpl(Class type, SessionFactory sessionFactory){
        super(type);
        setSessionFactory(sessionFactory);
    }

	public GeneralEmpInfoVO findByEmpIDOtherDetail(long userId) {
		List otherDtl = new ArrayList();
		List otherDtl1 = new ArrayList();
		List cityNameList = new ArrayList();
		Session hibSession = null;
		
		hibSession = getSession();
		
		GeneralEmpInfoVO genEmpVO = new GeneralEmpInfoVO();//this the Custom Vo To Set all the values 
		
		System.out.println("User Id -------------->" + userId);
				
		/*this code is used for to get the EmpId and Emp Name DOJ DOR */
		OrgUserMst orgUserObj = new OrgUserMst();
		orgUserObj.setUserId(userId);
		Criteria EmpCrit = hibSession.createCriteria(OrgEmpMst.class);
		System.out.println("Emp Id after -------------->" + userId);
		EmpCrit.add(Restrictions.eq("orgUserMst", orgUserObj));
		otherDtl = EmpCrit.list();
		OrgEmpMst empDet = (OrgEmpMst) otherDtl.get(0);

		genEmpVO.setEmpName(empDet.getEmpFname() + empDet.getEmpMname()
				+ empDet.getEmpLname());
		genEmpVO.setDoj(empDet.getEmpDoj());
		genEmpVO.setDor(empDet.getEmpSrvcExp());
		genEmpVO.setEmpId(empDet.getEmpId());

		
		/* this code used to get Emp Address ,city */
		OrgUserpostRlt uprlt = (OrgUserpostRlt) hibSession.get(
				OrgUserpostRlt.class, userId);

		long postId = uprlt.getOrgPostMstByPostId().getPostId();
		OrgPostDetailsRlt orgPostDtlObj = (OrgPostDetailsRlt) hibSession.get(
				OrgPostDetailsRlt.class, postId);
		/*System.out.println("Address+++++>>>>-----"
				+ orgPostDtlObj.getCmnLocationMst().getLocAddr1());*/
		try {
			genEmpVO.setEmpAdd(orgPostDtlObj.getCmnLocationMst().getLocAddr1());
					
			Criteria EmpCity = hibSession.createCriteria(CmnCityMst.class);
			EmpCity.add(Restrictions.eq("cityId", orgPostDtlObj.getCmnLocationMst()
					.getLocCityId()));
			cityNameList = EmpCity.list();
			CmnCityMst cityObj = (CmnCityMst) cityNameList.get(0);
			System.out.println("City Name +++ " + cityObj.getCityName());
			genEmpVO.setCityName(cityObj.getCityName());
		
		
		
		/*String query1 = "from OrgPostDetailsRlt  where orgPostMst.postId like "
				+ postId;

		Query sqlQuery1 = hibSession.createQuery(query1);
		List emppay = new ArrayList();
		emppay = sqlQuery1.list();*/
		
		
		System.out.println(orgPostDtlObj.getOrgDesignationMst().getDsgnId());
		genEmpVO.setDesigCode(orgPostDtlObj.getOrgDesignationMst().getDsgnCode());
		genEmpVO.setDesigid(orgPostDtlObj.getOrgDesignationMst().getDsgnId());
		genEmpVO.setDesignation(orgPostDtlObj.getOrgDesignationMst().getDsgnName());
		genEmpVO.setDesigid(orgPostDtlObj.getOrgDesignationMst().getDsgnId());

		Criteria EmpCrit1 = hibSession.createCriteria(HrEisOtherDtls.class);

		EmpCrit1.add(Restrictions.eq("hrEisEmpMst.empId", empDet.getEmpId()));
		otherDtl = EmpCrit1.list();

		HrEisOtherDtls hrEisSalDet = (HrEisOtherDtls) otherDtl.get(0);
		long salary = hrEisSalDet.getotherCurrentBasic();
		System.out.println("scale Name ----->>>>"
				+ hrEisSalDet.getHrEisSgdMpg().getHrEisScaleMst()
						.getScaleStartAmt()
				+ "-"
				+ hrEisSalDet.getHrEisSgdMpg().getHrEisScaleMst()
						.getScaleIncrAmt()
				+ "-"
				+ hrEisSalDet.getHrEisSgdMpg().getHrEisScaleMst()
						.getScaleEndAmt());
		genEmpVO.setSalary(salary);
		genEmpVO.setScaleStart(hrEisSalDet.getHrEisSgdMpg().getHrEisScaleMst()
				.getScaleStartAmt());
		genEmpVO.setScaleInc(hrEisSalDet.getHrEisSgdMpg().getHrEisScaleMst()
				.getScaleIncrAmt());
		genEmpVO.setScaleEnd(hrEisSalDet.getHrEisSgdMpg().getHrEisScaleMst()
				.getScaleEndAmt());
		} catch (NullPointerException e) {
			genEmpVO.setEmpAdd("No Record");
			genEmpVO.setCityName("No Record");
			genEmpVO.setDesignation("No Record");
			genEmpVO.setDesigid(1l);
			genEmpVO.setSalary(00l);
			genEmpVO.setScaleStart(00l);
			genEmpVO.setScaleInc(00l);
			genEmpVO.setScaleEnd(00l);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return genEmpVO;
	}	
	
	
	
	
}
