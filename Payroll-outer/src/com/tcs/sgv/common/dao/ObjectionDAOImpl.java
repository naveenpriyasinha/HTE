/**
 * 
 */
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

import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.common.valueobject.MstObjection;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;

/**
 * @see This is used to get Objection details for a bill
 * @author 203818
 *
 */
public class ObjectionDAOImpl
		extends GenericDaoHibernateImpl<MstObjection,Integer> 
		implements ObjectionDAO 
{
	Log logger = LogFactory.getLog(getClass());
	public ObjectionDAOImpl(Class<MstObjection> type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
	
	/**
	 * To get bill wise Objection details
	 * @param Map:inputMap
	 * @return List
	 */
	public List getBilldetails(Map inputMap)
	{
		List<MstObjection> dataList = new ArrayList<MstObjection>();
		Session hibSession = getSession();
//		HttpServletRequest request = (HttpServletRequest)inputMap.get("requestObj");
		Long lLngLangId = SessionHelper.getLangId(inputMap);
		Query query = hibSession.createSQLQuery("SELECT * FROM MST_OBJECTION WHERE LANG_ID=" +lLngLangId);
		List resList = query.list();
		if (resList!=null && resList.size()>0)
		{
			Iterator it = resList.iterator();
			while(it.hasNext()) 
			{
				Object[] tuple = (Object[]) it.next();
				MstObjection lObjObjectionVO = new MstObjection();
//				lObjObjectionVO.setObjectionId(Long.parseLong(tuple[0].toString()));			
				lObjObjectionVO.setObjectionDesc(tuple[1].toString());
				lObjObjectionVO.setObjectionCode(tuple[2].toString());
				lObjObjectionVO.setActivateFlag(Long.parseLong(tuple[4].toString()));
				lObjObjectionVO.setCreatedUserId(Long.parseLong(tuple[7].toString()));
				lObjObjectionVO.setCreatedPostId(Long.parseLong(tuple[8].toString()));
/*				lObjObjectionVO.setLangId(Long.parseLong(tuple[3].toString()));
				lObjObjectionVO.setStartDate((Date)tuple[5]);
				lObjObjectionVO.setEndDate((Date)tuple[6]);
				lObjObjectionVO.setCreatedDate((Date)tuple[9]);
				lObjObjectionVO.setUpdatedUserId(Long.parseLong(tuple[10].toString()));
				lObjObjectionVO.setUpdatedPostId(Long.parseLong(tuple[11].toString()));
				lObjObjectionVO.setUpdatedDate((Date)tuple[12]);
				lObjObjectionVO.setLocId(Long.parseLong(tuple[13].toString()));
				lObjObjectionVO.setDbId(Long.parseLong(tuple[14].toString()));
*/				dataList.add(lObjObjectionVO);
			}	
		}
		return dataList;
	}
	
	/**
	 * TO get selected objections for user who save objections for bill
	 * @param Long: billNo
	 * @param LongL userId
	 * @return List
	 */
	public List getSelectedObj(Long billNo, Long userId)
	{
		Session hibSession = getSession();
		Query query = hibSession.createSQLQuery("SELECT OBJECTION_CODE FROM RLT_BILL_OBJECTION WHERE USER_ID = " +userId +" AND BILL_NO = " +billNo);
		logger.info("Query to get selected objections : " +query);
		List lst = query.list();
		if(lst!=null)
			return lst;
		else
			return null;
	}
}
