package com.tcs.sgv.stamp.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.common.helper.SessionHelper;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.stamp.valueobject.MstStampGroup;
import com.tcs.sgv.stamp.valueobject.MstStampGroupVO;
import com.tcs.sgv.stamp.valueobject.RltDnmLocation;


public class MstStampGroupDAOImpl extends GenericDaoHibernateImpl<MstStampGroup,BigDecimal>
{
	public MstStampGroupDAOImpl(Class<MstStampGroup> type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}	
	public List getGroupName()
	{
		List groupName=new ArrayList();
		Session session=getSession();
		String strQuery="select grp_code,grp_name from mst_stamp_group";
		Query query=session.createSQLQuery(strQuery);
		List result=query.list();
		if(result.size()==0)
		{
			return groupName;
		}
		Iterator itResult=result.iterator();
		while(itResult.hasNext())
		{
			ComboValuesVO vo=new ComboValuesVO();
			Object[] tuple=(Object[])itResult.next();
			String strGroupCode=tuple[0].toString();
			String strGroupName=tuple[1].toString();
			//System.out.println("group code is "+strGroupCode);
			//System.out.println("group name is "+strGroupName);
			vo.setId(strGroupCode);
			vo.setDesc(strGroupName);
			groupName.add(vo);
		}
		return groupName;
	}
	public List getSubTreasuryName(String strLocationCode)
	{
		List subTresuryName=new ArrayList();
		Session session=getSession();
		String strQuery="select loc_id,loc_name from cmn_location_mst where parent_loc_id=100101";
		Query query=session.createSQLQuery(strQuery);
		List result=query.list();
		if(result.size()>0)
		{
			Iterator itResult=result.iterator();
			while(itResult.hasNext())
			{
				ComboValuesVO vo=new ComboValuesVO();
				Object[] tuple=(Object[])itResult.next();
				String strTresuryCode=tuple[0].toString();
				String strTresuryName=tuple[1].toString();
				vo.setId(strTresuryCode);
				vo.setDesc(strTresuryName);
				subTresuryName.add(vo);
			}
		}
		return subTresuryName;
	}	
	public List getSubTreasuryNameListWithTreasury(String strLocationCode,IndentDetailsDAOImpl indentDetailsVO,StampCommonDAOImpl commonDAO)
	{
		List subTresuryName=new ArrayList();
		//String strTreasuryName=indentDetailsVO.getTreasuryName(strLocationCode, commonDAO);
		String strTreasuryName=indentDetailsVO.getTreasuryName("100101", commonDAO);
		Session session=getSession();
		String strQuery="select loc_id,loc_name from cmn_location_mst where parent_loc_id=100101";
		Query query=session.createSQLQuery(strQuery);
		List result=query.list();
		int flag=0;
		if(result.size()>0)
		{
			Iterator itResult=result.iterator();
			while(itResult.hasNext())
			{
				if(flag==0)
				{
					flag=1;
					ComboValuesVO treasuryVO=new ComboValuesVO();
					//treasuryVO.setId(strLocationCode);
					treasuryVO.setId("100101");
					treasuryVO.setDesc(strTreasuryName);
					subTresuryName.add(treasuryVO);
				}
				ComboValuesVO vo=new ComboValuesVO();
				Object[] tuple=(Object[])itResult.next();
				String strTresuryCode=tuple[0].toString();
				String strTresuryName=tuple[1].toString();
				vo.setId(strTresuryCode);
				vo.setDesc(strTresuryName);
				subTresuryName.add(vo);
			}
		}
		return subTresuryName;
	}
	public List[] getGroupNameList(String strGroupCode)
	{
		List[] groupCodeAndName=new ArrayList[2];
		List groupName=new ArrayList();
		List groupCode=new ArrayList();
		Session session=getSession();
		String strQuery="select grp_Code,grp_Name from Mst_Stamp_Group";
		if(strGroupCode.equals("0")==false)
		{
			strQuery=strQuery+" where grp_Code="+strGroupCode;
		}
		else
		{
			strQuery=strQuery+" where grp_Code in ( select distinct grp_Code from Mst_Stamp_Denomination )";
		}
			
		strQuery=strQuery+" order by Grp_Code";
		Query query=session.createSQLQuery(strQuery);
		List result=query.list();
		if(result.size()>0)
		{
			Iterator itResult=result.iterator();
			while(itResult.hasNext())
			{
				Object[] tuple=(Object[])itResult.next();
				String strCode=tuple[0].toString();
				String strGroupName=tuple[1].toString();
				groupName.add(strGroupName);
				groupCode.add(strCode);
			}
		}
		groupCodeAndName[0]=groupCode;
		groupCodeAndName[1]=groupName;
		return groupCodeAndName;
	}
	public List getDetailsForEachGroupCode(List groupCode)
	{
		List groupDetails=new ArrayList();
		
		Session session=getSession();
		if(groupCode.size()>0)
		{
			Iterator itGroupCode=groupCode.iterator();
			while(itGroupCode.hasNext())
			{
				List outerList=new ArrayList();
				String strGroupCode=itGroupCode.next().toString();
				String strQuery="select distinct Dnm_Value from mst_Stamp_Denomination where Grp_Code="+strGroupCode+" order by dnm_value";
				Query query=session.createSQLQuery(strQuery);
				List result=query.list();
				int count=1;
				if(result.size()>0)
				{
					Iterator itResult=result.iterator();
					while(itResult.hasNext())
					{
						String strDenominationValue=itResult.next().toString();
						String strLastStockBeforeIndent="0";
						String strReceivedAgainstPrevIndent="0";
						String strQtySold="0";
						String strClosingBalance="0";
						String strQtyDue="0";
						String strQtyRequired="0";
						String strQtyPassed="0";
						String strRemarks="-";
						List innerList=new ArrayList();
						innerList.add(count);
						innerList.add(strDenominationValue);
						innerList.add(strLastStockBeforeIndent);
						innerList.add(strReceivedAgainstPrevIndent);
						innerList.add(strQtySold);
						innerList.add(strClosingBalance);
						innerList.add(strQtyDue);
						innerList.add(strQtyRequired);
						innerList.add(strQtyPassed);
						innerList.add(strRemarks);
						//System.out.println("Count= "+count+" value = "+strDenominationValue);
						count++;
						outerList.add(innerList);
						innerList=null;
					}
					groupDetails.add(outerList);
					outerList=null;
				}
			}
		}
		return groupDetails;
	}
	public List getAllGroupCode()
	{
		List allGroupCode=new ArrayList();
		Session session=getSession();
		String strQuery="select grp_code from mst_stamp_Group";
		Query query=session.createSQLQuery(strQuery);
		List result=query.list();
		if(result.size()>0)
		{
			Iterator itResult=result.iterator();
			while(itResult.hasNext())
			{
				String strGroupCode=itResult.next().toString();
				ComboValuesVO vo=new ComboValuesVO();
				vo.setId(strGroupCode);
				vo.setDesc(strGroupCode);
				allGroupCode.add(vo);
			}
		}
		return allGroupCode;
	}
	public String getGroupCode(String strGroupName)
	{
		String strGroupCode="";
		Session session=getSession();
		String strQuery="select grp_Code  from Mst_Stamp_Group where grp_Name="+"'"+strGroupName+"'";
		Query query=session.createSQLQuery(strQuery);
		List result=query.list();
		if(result.size()>0)
		{
			Iterator itResult=result.iterator();
			while(itResult.hasNext())
			{
				strGroupCode=itResult.next().toString();
			}
		}
		return strGroupCode;
	}
	public String getDenominationCode(String strDenominationValue,String strGroupCode)
	{
		String strDenominationCode="";
		Session session=getSession();
		String strQuery="select dnm_Code from  mst_Stamp_Denomination where Grp_Code="+strGroupCode+" and Dnm_Value="+strDenominationValue;
		Query query=session.createSQLQuery(strQuery);
		List result=query.list();
		if(result.size()>0)
		{
			Iterator itResult=result.iterator();
			while(itResult.hasNext())
			{
				strDenominationCode=itResult.next().toString();
			}
		}
		return strDenominationCode;
	}
	public String getGroupName(String strGroupCode)
	{
		String strGroupname="";
		Session session=getSession();
		String strQuery="select grp_Name  from Mst_Stamp_Group where grp_Code="+strGroupCode;
		Query query=session.createSQLQuery(strQuery);
		List result=query.list();
		if(result.size()>0)
		{
			Iterator itResult=result.iterator();
			while(itResult.hasNext())
			{
				strGroupname=itResult.next().toString();
			}
		}
		return strGroupname;
	}
	public String getGroupDescription(String strGroupCode)
	{
		String strGroupDescription="";
		Session session=getSession();
		String strQuery="select grp_desc  from Mst_Stamp_Group where grp_Code="+strGroupCode;
		Query query=session.createSQLQuery(strQuery);
		List result=query.list();
		if(result.size()>0)
		{
			Iterator itResult=result.iterator();
			while(itResult.hasNext())
			{
				strGroupDescription=itResult.next().toString();
			}
		}
		return strGroupDescription;
	}
	public String getIndentID(Map objectArgs)
	{
		String strIndentID="";
		String strLocationCode=SessionHelper.getLocationCode(objectArgs);
		ServiceLocator serv=(ServiceLocator)objectArgs.get("serviceLocator");
		StampCommonDAOImpl commonDAO=new StampCommonDAOImpl(RltDnmLocation.class,serv.getSessionFactory());
		boolean result=commonDAO.isTreasury(strLocationCode);
		Calendar c=Calendar.getInstance();
		int month=c.get(Calendar.MONTH)+1;
		int year=c.get(Calendar.YEAR);
		String strUniqueNo=commonDAO.getUniqueNoForIndentPreparation(strLocationCode, serv);
		if(result==true)
		{
			strIndentID=strLocationCode+String.valueOf(month)+String.valueOf(year)+strUniqueNo;
		}
		else
		{
			String strParentTreasuryCode=commonDAO.getParentTreasuryCodeForSubTreasury(strLocationCode);
			strIndentID=strParentTreasuryCode+strLocationCode+String.valueOf(month)+String.valueOf(year)+strUniqueNo;
		}
		return strIndentID;
	}
	
	public List getAllStampGroupData()
	{
		List stampGroupData=new ArrayList();
		Session session=getSession();
		String strQuery="select mst.grp_id,mst.grp_code,mst.grp_name,cat.category_name,mst.grp_desc,mst.mjr_hd,mst.sub_mjr_hd,mst.min_hd,mst.sub_hd  from mst_stamp_group mst,mst_stamp_category cat where mst.category_code=cat.category_code and mst.status='1'";
		Query query=session.createSQLQuery(strQuery);
		List result=query.list();
		//System.out.println(""+result.size());
		int count=1;
		if(result.size()>0)
		{
			Iterator itResult=result.iterator();
			while(itResult.hasNext())
			{
				//System.out.println("hi");
				Object[] tuple=(Object[])itResult.next();
				MstStampGroupVO vo=new MstStampGroupVO();
				vo.setSrNo(String.valueOf(count));
				vo.setGrpID(tuple[0].toString());
				vo.setStampGroupCode(tuple[1].toString());
				vo.setStampGroupName(tuple[2].toString());
				if(tuple[3]!=null)
				{	
					vo.setStampBaseGroupName(tuple[3].toString());
				}
				else
				{
					vo.setStampBaseGroupName("");
				}
				if(tuple[4]!=null)
				{
					vo.setDescription(tuple[4].toString());
				}
				else
				{
					vo.setDescription("");
				}
				vo.setMajorHead(tuple[5].toString());
				vo.setSubMajorHead(tuple[6].toString());
				vo.setMinorHead(tuple[7].toString());
				vo.setSubHead(tuple[8].toString());
				stampGroupData.add(vo);
				count++;
			}
		}
		return stampGroupData;
	}
	
	public MstStampGroup getUpdateDataForMstStampGroup(String strGroupID)
	{
		Session session=getSession();
		String strQuery="select mst.category_code,mst.grp_code,mst.grp_name,mst.grp_desc,mst.mjr_hd,mst.sub_mjr_hd,mst.min_hd,mst.sub_hd from mst_stamp_group mst where mst.grp_id="+strGroupID;
		Query query=session.createSQLQuery(strQuery);
		List result=query.list();
		MstStampGroup stampGroupVO=new MstStampGroup();
		if(result.size()>0)
		{
			Iterator itResult=result.iterator();
			while(itResult.hasNext())
			{
				Object[] tuple=(Object[])itResult.next();
				
				stampGroupVO.setCategoryCode(Byte.parseByte(tuple[0].toString()));
				stampGroupVO.setGrpCode(Short.parseShort(tuple[1].toString()));
				stampGroupVO.setGrpName(tuple[2].toString());
				if(tuple[3]!=null)
				{
					stampGroupVO.setGrpDesc(tuple[3].toString());
				}
				else
				{
					stampGroupVO.setGrpDesc("");
				}
				stampGroupVO.setMjrHd(Short.parseShort(tuple[4].toString()));
				stampGroupVO.setSubMjrHd(Short.parseShort(tuple[5].toString()));
				stampGroupVO.setMinHd(Short.parseShort(tuple[6].toString()));
				stampGroupVO.setSubHd(Short.parseShort(tuple[7].toString()));
				
			}
		}
		return stampGroupVO;
	}
	public void deleteDataForMstStampGroup(String[] strGroupID)
	{
		for(int count=0;count<strGroupID.length;count++)
		{
			
			MstStampGroup updateVO=this.read(new BigDecimal(strGroupID[count].toString()));
			updateVO.setGrpId(new BigDecimal(strGroupID[count].toString()));
			updateVO.setStatus("0");
			//this.update(updateVO);
		}
	}
	public List getAllGroupName()
	{
		List allGroupName=new ArrayList();
		Session session=getSession();
		String strQuery="select grp_name from mst_stamp_Group";
		Query query=session.createSQLQuery(strQuery);
		List result=query.list();
		if(result.size()>0)
		{
			Iterator itResult=result.iterator();
			while(itResult.hasNext())
			{
				String strGroupName=itResult.next().toString();
				ComboValuesVO vo=new ComboValuesVO();
				vo.setId(strGroupName);
				vo.setDesc(strGroupName);
				allGroupName.add(vo);
			}
		}
		return allGroupName;
	}
	public String getMajorHead(String strGroupCode,StampCommonDAOImpl commonDAO)
	{
		String strMajorHead="";
		String strTablename="mst_stamp_group grp";
		String strFieldList="grp.mjr_hd";
		String strWhereCondition="grp.grp_code="+strGroupCode;
		Iterator itResult=commonDAO.getIterator(strTablename,strFieldList,strWhereCondition);
		if(itResult!=null)
		{
			while(itResult.hasNext())
			{
				strMajorHead=itResult.next().toString();
			}
		}
		return strMajorHead;
	}
	public String getSubMajorHead(String strGroupCode,StampCommonDAOImpl commonDAO)
	{
		String strSubMajorHead="";
		String strTablename="mst_stamp_group grp";
		String strFieldList="grp.sub_mjr_hd";
		String strWhereCondition="grp.grp_code="+strGroupCode;
		Iterator itResult=commonDAO.getIterator(strTablename,strFieldList,strWhereCondition);
		if(itResult!=null)
		{
			while(itResult.hasNext())
			{
				strSubMajorHead=itResult.next().toString();
			}
		}
		return strSubMajorHead;
	}
	public String getMinorHead(String strGroupCode,StampCommonDAOImpl commonDAO)
	{
		String strMinorHead="";
		String strTablename="mst_stamp_group grp";
		String strFieldList="grp.min_hd";
		String strWhereCondition="grp.grp_code="+strGroupCode;
		Iterator itResult=commonDAO.getIterator(strTablename,strFieldList,strWhereCondition);
		if(itResult!=null)
		{
			while(itResult.hasNext())
			{
				strMinorHead=itResult.next().toString();
			}
		}
		return strMinorHead;
	}
	public String getSubMinorHead(String strGroupCode,StampCommonDAOImpl commonDAO)
	{
		String strSubMinorHead="";
		String strTablename="mst_stamp_group grp";
		String strFieldList="grp.sub_hd";
		String strWhereCondition="grp.grp_code="+strGroupCode;
		Iterator itResult=commonDAO.getIterator(strTablename,strFieldList,strWhereCondition);
		if(itResult!=null)
		{
			while(itResult.hasNext())
			{
				strSubMinorHead=itResult.next().toString();
			}
		}
		return strSubMinorHead;
	}
}

