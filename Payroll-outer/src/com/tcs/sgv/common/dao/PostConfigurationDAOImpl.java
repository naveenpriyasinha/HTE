
package com.tcs.sgv.common.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;

import com.tcs.sgv.common.valueobject.ConfigAudCategory;
import com.tcs.sgv.common.valueobject.ConfigAudSelection;
import com.tcs.sgv.common.valueobject.EmpPostVO;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

/**
 * 
 * @author 206819
 *
 */
public class PostConfigurationDAOImpl extends GenericDaoHibernateImpl<ConfigAudSelection,Integer> implements PostConfigurationDAO {
	
	Log logger = LogFactory.getLog(PostConfigurationDAOImpl.class);
	
	public PostConfigurationDAOImpl(Class<ConfigAudSelection> type, SessionFactory sessionFactory){
        super(type);
        setSessionFactory(sessionFactory);
	}
	
	
	/**
	 * This method get the employee bound to task on basis of value of cardexNo, billType, majorHead, goNgo  
	 * @param office_id Office location Id
	 * @param branch_id Branch Id 
	 * @param mpArg Contains query criteria(s) (cardexNo, billType, majorHead, goNgo)
	 * @return EmpPostVO
	 */
	public EmpPostVO getSelectedEmp(String officeCode, String branchCode, Map mpArg) {
		EmpPostVO empPostVO = null;
		Session hibSession = getSession();
		
		try {
			String cardexNo = mpArg.get("cardexNo")!=null?mpArg.get("cardexNo").toString():null;
			String billType = mpArg.get("billType")!=null?mpArg.get("billType").toString():null;
			String majorHead =mpArg.get("majorHead")!=null?mpArg.get("majorHead").toString():null;
			String goNgo = mpArg.get("goNgo")!=null?mpArg.get("goNgo").toString():null;
			String deptCode = mpArg.get("deptCode")!=null?mpArg.get("deptCode").toString():null;
						
			List cfgCtList =	hibSession.createCriteria(ConfigAudCategory.class)
								.add(Expression.eq("officeCode", officeCode))
								.add(Expression.eq("branchCode", branchCode)).list();
			
			if (cfgCtList!=null && cfgCtList.size()>0) {
				ConfigAudCategory cfgAudCatVO = (ConfigAudCategory) cfgCtList.get(0);
				logger.info("---------------------> " + cfgAudCatVO);
				logger.info("---------------------> " + cfgAudCatVO.getCardexNo());
				StringBuffer sql = new StringBuffer();
				sql.append(" select e.empId, e.orgUserMst.userId, upr.orgPostMstByPostId.postId, e.empPrefix, e.empFname,e.empLname,e.empMname from OrgEmpMst as e, OrgUserpostRlt as upr " +
						   " where upr.orgUserMst.userId = e.orgUserMst.userId and upr.orgPostMstByPostId.postId in " +
						   " (select cas.postId from ConfigAudSelection as cas where cas.audCatId = " + cfgAudCatVO.getAudCatId() + " ");

				if(cfgAudCatVO.getCardexNo()!=null && cfgAudCatVO.getCardexNo()==1 && cardexNo!=null && cardexNo.length()>0) sql.append(" and cas.cardexNo = " + cardexNo);
//				if(cfgAudCatVO.getCardexNo()!=null && cfgAudCatVO.getCardexNo()==1 ) sql.append(" and cas.cardexNo = " + cardexNo);
				if(cfgAudCatVO.getBillType()!=null && cfgAudCatVO.getBillType()==1 && billType!=null && billType.length()>0) sql.append(" and cas.billType = " + billType);
				if(cfgAudCatVO.getMajorHead()!=null && cfgAudCatVO.getMajorHead()==1 && majorHead!=null && majorHead.length()>0) sql.append(" and cas.majorHead = '" + majorHead +"'");
				if(cfgAudCatVO.getGoNgo()!=null && cfgAudCatVO.getGoNgo()==1 && goNgo!=null && goNgo.length()>0) sql.append(" and cas.goNgo = '" + goNgo+"'");
				if(cfgAudCatVO.getDeptCode()!=null && cfgAudCatVO.getDeptCode()==1 && deptCode!=null && deptCode.length()>0) sql.append(" and cas.deptCode = '" + deptCode+"'");
				sql.append(")");
				logger.info("QUERY IS "+sql);
				Query sqlQuery = hibSession.createQuery(sql.toString());
				List resList = sqlQuery.list();
				if (resList!=null && resList.size()>0) {
					Iterator it = resList.iterator();
					Object[] objArr = (Object[]) it.next();
					empPostVO = new EmpPostVO();
					empPostVO.setEmpId((Long) objArr[0]);					
					empPostVO.setUserId((Long) objArr[1]);
					empPostVO.setPostId((Long) objArr[2]);
					empPostVO.setEmpPrefix(objArr[3].toString());
					empPostVO.setEmpFname(objArr[4].toString());
					empPostVO.setEmpMname(objArr[5].toString());
					empPostVO.setEmpLname(objArr[6].toString());
					empPostVO.setFullName(objArr[3].toString() + " " + objArr[4].toString() + " "+ objArr[6].toString() + " " + objArr[5].toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occured in PostConfigurationDAOImpl.getSelectedEmp #\n"+e);
		}		
		logger.info("empPostVO : " + empPostVO);
		return empPostVO;		
	}
	
	/**
	 * This method get the employee bound to task on basis of cardexNo, billType, majorHead, goNgo
	 * @param branch_id Branch Id 
	 * @param office_id Office Id
	 * @return List
	 */
	public List getEmpsList(String office_code, String branch_code) {
		List dataList = new ArrayList();
		
		try {
			String sql = "select e.empId, e.orgUserMst.userId, upr.orgPostMstByPostId.postId, e.empPrefix, e.empFname,e.empMname,e.empLname from OrgEmpMst as e, OrgUserpostRlt as upr " +
						 " where upr.orgUserMst.userId = e.orgUserMst.userId and upr.orgPostMstByPostId.postId in " +
						 " (select cas.postId from ConfigAudSelection as cas, ConfigAudCategory as cac " +
						 " where cas.audCatId = cac.audCatId and cac.officeCode='"+office_code+"' and cac.branchCode='"+branch_code+"') order by e.empFname,e.empMname,e.empLname";
		
			Session hibSession = getSession();
			Query sqlQuery = hibSession.createQuery(sql);
			List resList = sqlQuery.list();
			if (resList!=null) {
				Iterator it = resList.iterator();
				while(it.hasNext()) {
					Object[] objArr = (Object[]) it.next();
					EmpPostVO empPostVO = new EmpPostVO();
					empPostVO.setEmpId((Long) objArr[0]);					
					empPostVO.setUserId((Long) objArr[1]);
					empPostVO.setPostId((Long) objArr[2]);
					empPostVO.setEmpPrefix(objArr[3].toString());
					empPostVO.setEmpFname(objArr[4].toString());
					empPostVO.setEmpMname(objArr[5].toString());
					empPostVO.setEmpLname(objArr[6].toString());
					empPostVO.setFullName(objArr[3].toString() + " " + objArr[4].toString() + " "+ objArr[5].toString() +" " + objArr[6].toString());
					dataList.add(empPostVO);
				}
			}				
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception occured in PostConfigurationDAOImpl.getEmpsList #\n"+e);
		}
		
		return dataList;
	}
}
