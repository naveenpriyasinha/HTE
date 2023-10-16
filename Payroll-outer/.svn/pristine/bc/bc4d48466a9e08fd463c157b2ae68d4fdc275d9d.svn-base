package com.tcs.sgv.stamp.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.stamp.valueobject.MstStampCategory;
import com.tcs.sgv.stamp.valueobject.MstStampCategoryVO;



public class MstStampCategoryDAOImpl extends GenericDaoHibernateImpl<MstStampCategory,BigDecimal>
{
	public MstStampCategoryDAOImpl(Class<MstStampCategory> type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
	public List getStampCategory()
	{
		List categoryList=new ArrayList();
		Session session=getSession();
		String strQuery="select stamp.category_code,stamp.category_name  from mst_stamp_category stamp";
		Query query=session.createSQLQuery(strQuery);
		List result=query.list();
		if(result.size()>0)
		{
			Iterator itResult=result.iterator();
			while(itResult.hasNext())
			{
				Object[] tuple=(Object[])itResult.next();
				String strCategoryID=tuple[0].toString();
				String strCategoryName=tuple[1].toString();
				ComboValuesVO vo=new ComboValuesVO();
				vo.setId(strCategoryID);
				vo.setDesc(strCategoryName);
				categoryList.add(vo);
			}
		}
		return categoryList;
	}
	public List getAllStampCategoryData()
	{
		List stampCategoryData=new ArrayList();
		Session session=getSession();
		String strQuery="select a.category_id,a.category_name,a.category_desc from mst_stamp_category a";
		Query query=session.createSQLQuery(strQuery);
		//System.out.println("hi again");
		List result=query.list();
		//System.out.println(""+result.size());
		if(result.size()>0)
		{
			Iterator itResult=result.iterator();
			while(itResult.hasNext())
			{
				//System.out.println("hi");
				Object[] tuple=(Object[])itResult.next();
				MstStampCategoryVO vo=new MstStampCategoryVO();
				vo.setCategoryId(tuple[0].toString());
				vo.setCategoryName(tuple[1].toString());
				String categoryDesc = (tuple[2] != null)? tuple[2].toString(): "" ;
				vo.setCategoryDesc(categoryDesc);
				stampCategoryData.add(vo);
				
			}
		}
		return stampCategoryData;
	}
	
	
	public void deleteDataForMstStampCategory(String[] categoryID) {
		// TODO Auto-generated method stub
		Session session=getSession();
		String groupIDs="(";
		for(int i=0;i<categoryID.length;i++)
		{
			groupIDs=groupIDs+categoryID[i].toString();
			if(i!=(categoryID.length-1))
			{
				groupIDs=groupIDs+",";
			}
		}		
		groupIDs=groupIDs+")";
		String strQuery="delete mst_stamp_category where category_id in "+groupIDs;
		Query query=session.createSQLQuery(strQuery);
		query.executeUpdate();
	}
	
	public MstStampCategoryVO getUpdateDataForMstStampCategory(String categoryID)
	{
		MstStampCategoryVO categoryVO=new MstStampCategoryVO();
		Session session=getSession();
		
		String strQuery="select cat.category_name, cat.category_desc from mst_stamp_category cat where cat.category_id="+categoryID;
		
		Query query=session.createSQLQuery(strQuery);
		List result=query.list();
		if(result.size()>0)
		{
			Iterator itResult=result.iterator();
			while(itResult.hasNext())
			{
				Object[] tuple=(Object[])itResult.next();
				categoryVO.setCategoryName(tuple[0].toString());
				String categoryDesc = (tuple[1] != null)? tuple[1].toString(): "" ;
				categoryVO.setCategoryDesc(categoryDesc);
				
			}
		}
		return categoryVO;
	}
}
