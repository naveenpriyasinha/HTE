package com.tcs.sgv.hr.payincrement.dao;



import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.eis.valueobject.HrEisOtherDtls;
import com.tcs.sgv.ess.valueobject.OrgEmpMst;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;
import com.tcs.sgv.ess.valueobject.OrgUserpostRlt;
import com.tcs.sgv.hr.payincrement.valueobject.PayincGeneralEmpInfoVO;
public class PayincGeneralDaoImp extends GenericDaoHibernateImpl implements PayincGeneralDao{
	
	public PayincGeneralDaoImp(Class type, SessionFactory sessionFactory){
        super(type);
        setSessionFactory(sessionFactory);
    }

	
	 /* (non-Javadoc)
	 * @see com.tcs.sgv.hr.payincrement.dao.PayincGeneralDao#findByEmpIDOtherDetail(long, long)
	 */
	public PayincGeneralEmpInfoVO findByEmpIDOtherDetail(long userid,long langid){
		 
		
		 List otherDtl=new ArrayList();
		 Session hibSession=null;
		 hibSession = getSession();
		 //OrgEmpMst hrEisMst =(OrgEmpMst) hibSession.get(OrgEmpMst.class, EmpId);
		 /*OrgEmpMst hrEisMst = new OrgEmpMst();
		 hrEisMst.setEmpId(EmpId);*/
		 PayincGeneralEmpInfoVO genEmpVO =new PayincGeneralEmpInfoVO();
		
		
		 Criteria EmpCrit = hibSession.createCriteria(OrgEmpMst.class);
				 EmpCrit .add(Restrictions.eq("orgUserMst.userId",userid));
				 EmpCrit .add(Restrictions.eq("cmnLanguageMst.langId",langid));
		 		 otherDtl= EmpCrit.list();

			OrgEmpMst empDet=(OrgEmpMst)otherDtl.get(0);
			
			
			genEmpVO.setEmpName(empDet.getEmpFname()+" "+empDet.getEmpMname()+" "+empDet.getEmpLname());
			genEmpVO.setDoj(empDet.getEmpDoj());
			genEmpVO.setDor(empDet.getEmpSrvcExp());
			genEmpVO.setEmpid(empDet.getEmpId());
		
			
			long userId=empDet.getOrgUserMst().getUserId();
			OrgUserpostRlt uprlt = (OrgUserpostRlt)hibSession.get(OrgUserpostRlt.class, userId);
			
			long postId=uprlt.getOrgPostMstByPostId().getPostId();
			
		
			 String query1="from OrgPostDetailsRlt  where orgPostMst.postId like "+postId+" and cmnLanguageMst.langId="+langid;

		        Query sqlQuery1 = hibSession.createQuery(query1);
		        List emppay = new ArrayList();
		        emppay = sqlQuery1.list();
		        OrgPostDetailsRlt EmpMaster=new OrgPostDetailsRlt();
		        EmpMaster=(OrgPostDetailsRlt)emppay.get(0);
		       
			
			
			genEmpVO.setDesignation(EmpMaster.getOrgDesignationMst().getDsgnName());
			genEmpVO.setDesigid(EmpMaster.getOrgDesignationMst().getDsgnId());
			
		
			Criteria EmpCrit1 = hibSession.createCriteria(HrEisOtherDtls.class);
		    EmpCrit1 .add(Restrictions.eq("hrEisEmpMst.empId",empDet.getEmpId()));
		    otherDtl= EmpCrit1 .list();
		
			HrEisOtherDtls hrEisSalDet =(HrEisOtherDtls)otherDtl.get(0);
			
			
			long salary=hrEisSalDet.getotherCurrentBasic();
			genEmpVO.setSalary(salary);
			
			
			return genEmpVO;
	 }	
	
	
	
}
