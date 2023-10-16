package com.tcs.sgv.stamp.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.apps.common.valuebeans.ComboValuesVO;
import com.tcs.sgv.core.dao.GenericDaoHibernateImpl;
import com.tcs.sgv.stamp.valueobject.TrnStampIndentDtls;

public class IndentDetailsDAOImpl extends GenericDaoHibernateImpl<TrnStampIndentDtls,BigDecimal>
{
	public IndentDetailsDAOImpl(Class<TrnStampIndentDtls> type, SessionFactory sessionFactory)
	{
		super(type);
		setSessionFactory(sessionFactory);
	}
	public List[] getGroupnameAndDenominationTable(String strIndentID)
	{
		List[] groupNameAndDenominationTable=new ArrayList[2];
		List groupCode=getGroupCode(strIndentID);
		List groupName=getGroupName(groupCode);
		List denominationTable=getDenominationDetails(groupCode,strIndentID);
		groupNameAndDenominationTable[0]=groupName;
		groupNameAndDenominationTable[1]=denominationTable;
		return groupNameAndDenominationTable;
	}
	public List getGroupCode(String strIndentID)
	{
		List groupCode=new ArrayList();
		Session session=getSession();
		String strQuery="select det.grp_code from trn_stamp_indent_dtls det where det.ind_no="+strIndentID;
		Query query=session.createSQLQuery(strQuery);
		List result=query.list();
		if(result.size()>0)
		{
			Iterator itResult=result.iterator();
			while(itResult.hasNext())
			{
				String strGroupcode=itResult.next().toString();
				groupCode.add(strGroupcode);
			}
		}
		return groupCode;
	}
	public List getGroupName(List groupCode)
	{
		List groupName=new ArrayList();
		Session session=getSession();
		String strGroupCode="(";
		if(groupCode.size()>0)
		{
			Iterator itGroup=groupCode.iterator();
			while(itGroup.hasNext())
			{
				strGroupCode=strGroupCode+itGroup.next().toString()+",";
			}
			strGroupCode=strGroupCode.substring(0,strGroupCode.length()-1);
			strGroupCode=strGroupCode+")";
		}
		String strQuery="select sGroup.Grp_Name from Mst_Stamp_Group sGroup where sGroup.Grp_Code in "+strGroupCode;
		Query query=session.createSQLQuery(strQuery);
		List result=query.list();
		if(result.size()>0)
		{
			Iterator itResult=result.iterator();
			while(itResult.hasNext())
			{
				String strGroupName=itResult.next().toString();
				groupName.add(strGroupName);
			}
		}
		return groupName;
	}
	public List getDenominationDetails(List groupCode,String strIndentNo)
	{
		Session session=getSession();
		List groupDetails=new ArrayList();
		if(groupCode.size()>0)
		{
			Iterator itGroupCode=groupCode.iterator();
			while(itGroupCode.hasNext())
			{
				List outerList=new ArrayList();
				String strGroupCode=itGroupCode.next().toString();
				String strQuery="select distinct det.dnm_value,det.stock_on_last_ind,det.supply_against_last_ind,det.qty_sold,det.closing_bal_prev_period,det.qty_due,det.qty_requd,det.qty_passed,det.remarks "+
								" from trn_stamp_indent_dtls det where det.grp_code="+strGroupCode+" and det.ind_no="+strIndentNo;
				Query query=session.createSQLQuery(strQuery);
				List result=query.list();
				int count=1;
				if(result.size()>0)
				{
					Iterator itResult=result.iterator();
					while(itResult.hasNext())
					{
						Object[] tuple=(Object[])itResult.next();
						String strDenominationValue=tuple[0].toString();
						String strLastStockBeforeIndent=tuple[1].toString();
						String strReceivedAgainstPrevIndent=tuple[2].toString();
						String strQtySold=tuple[3].toString();
						String strClosingBalance=tuple[4].toString();
						String strQtyDue=tuple[5].toString();
						String strQtyRequired=tuple[6].toString();
						String strQtyPassed=tuple[7].toString();
						String strRemarks=tuple[8].toString();
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
	public List[] getTreasuryAndSubTreasuryIndentData(List subTreasuryList,String strTreasuryLocationCode,StampCommonDAOImpl commonDAO)
	{
		List[] indentDataAndSubTreasuryList=new List[3];
		List AllIndentData=new ArrayList();
		List[] indentNoAndsubTreasuryList=new List[2];
		indentNoAndsubTreasuryList=getAllIndentNoForTreasuryAndSubTreasury(subTreasuryList, strTreasuryLocationCode, commonDAO);
		ArrayList allIndentNo=(ArrayList)indentNoAndsubTreasuryList[0];
		subTreasuryList=indentNoAndsubTreasuryList[1];
		for(int count=0;count<allIndentNo.size();count++)
		{
			String strIndentNo=allIndentNo.get(count).toString();
			if(strIndentNo!=null && strIndentNo.length()>0)
			{
				List[] indentData=getConsolidateGroupnameAndDenominationDetails(strIndentNo);
				AllIndentData.add(indentData);
			}
		}
		indentDataAndSubTreasuryList[0]=AllIndentData;
		indentDataAndSubTreasuryList[1]=subTreasuryList;
		indentDataAndSubTreasuryList[2]=allIndentNo;
		return indentDataAndSubTreasuryList;
	}
	public List[] getAllIndentNoForTreasuryAndSubTreasury(List subTreasuryList,String strTreasuryLocationCode,StampCommonDAOImpl commonDAO)
	{
		List[] indentNoAndSubTreasuryList=new List[2];
		ArrayList indentNo=new ArrayList();
		List tempSubTreasuryList=new ArrayList();
		//indentNo.add(getIndentNo(strTreasuryLocationCode, commonDAO));
		if(subTreasuryList.size()>0)
		{
			Iterator itResult=subTreasuryList.iterator();
			while(itResult.hasNext())
			{
				ComboValuesVO vo=(ComboValuesVO)itResult.next();
				String strLocationCode=vo.getId();
				String strLocationName=vo.getDesc();
				String strIndentNo=getIndentNo(strLocationCode, commonDAO);
				if(strIndentNo!=null && strIndentNo.length()>0)
				{
					indentNo.add(strIndentNo);
					tempSubTreasuryList.add(vo);
				}
				else
				{
					subTreasuryList.remove(strLocationCode);
					subTreasuryList.remove(strLocationName);
				}
			}
		}
		indentNoAndSubTreasuryList[0]=indentNo;
		indentNoAndSubTreasuryList[1]=tempSubTreasuryList;
		return indentNoAndSubTreasuryList;
	}
	public String getIndentNo(String strLocationCode,StampCommonDAOImpl commonDAO)
	{
		String strIndentNo="";
		String strTablename="trn_stamp_indent_hdr hdr";
		String strFieldList="hdr.ind_no";
		String strWhereCondition="hdr.loc_code="+strLocationCode+" and hdr.status='Open' and hdr.type_code='Normal'";
		Iterator itResult=commonDAO.getIterator(strTablename,strFieldList,strWhereCondition);
		if(itResult!=null)
		{
			while(itResult.hasNext())
			{
				strIndentNo=itResult.next().toString();
			}
		}
		return strIndentNo;
	}
	public List[] getConsolidateGroupnameAndDenominationDetails(String strIndentID)
	{
		List[] groupNameAndDenominationTable=new ArrayList[2];
		List groupCode=getGroupCode(strIndentID);
		List groupName=getGroupName(groupCode);
		List denominationTable=getConsolidateDenominationDetails(groupCode,strIndentID);
		groupNameAndDenominationTable[0]=groupName;
		groupNameAndDenominationTable[1]=denominationTable;
		return groupNameAndDenominationTable;
	}
	public List getConsolidateDenominationDetails(List groupCode,String strIndentNo)
	{
		Session session=getSession();
		List groupDetails=new ArrayList();
		if(groupCode.size()>0)
		{
			Iterator itGroupCode=groupCode.iterator();
			while(itGroupCode.hasNext())
			{
				List outerList=new ArrayList();
				String strGroupCode=itGroupCode.next().toString();
				String strQuery="select distinct det.dnm_value,det.stock_on_last_ind,det.supply_against_last_ind,det.qty_sold,det.closing_bal_prev_period,det.qty_due,det.qty_requd,det.qty_passed,det.remarks "+
								" from trn_stamp_indent_dtls det where det.grp_code="+strGroupCode+" and det.ind_no="+strIndentNo;
				Query query=session.createSQLQuery(strQuery);
				List result=query.list();
				int count=1;
				if(result.size()>0)
				{
					Iterator itResult=result.iterator();
					while(itResult.hasNext())
					{
						Object[] tuple=(Object[])itResult.next();
						String strDenominationValue=tuple[0].toString();
						String strLastStockBeforeIndent=tuple[1].toString();
						String strReceivedAgainstPrevIndent=tuple[2].toString();
						String strQtySold=tuple[3].toString();
						String strClosingBalance=tuple[4].toString();
						String strQtyDue=tuple[5].toString();
						String strQtyRequired=tuple[6].toString();
						String strQtyPassed=tuple[7].toString();
						String strRemarks=tuple[8].toString();
						//IndentConsolidationVO vo=new IndentConsolidationVO();
						/*vo.setCount(String.valueOf(count));
						vo.setDenominationValue(strDenominationValue);
						vo.setLastStockBeforeIndent(strLastStockBeforeIndent);
						vo.setReceivedAgainstPrevIndent(strReceivedAgainstPrevIndent);
						vo.setQtySold(strQtySold);
						vo.setClosingBalance(strClosingBalance);
						vo.setQtyDue(strQtyDue);
						vo.setQtyRequired(strQtyRequired);
						vo.setQtyPassed(strQtyPassed);
						vo.setRemarks(strRemarks);
						outerList.add(vo);
						vo=null;*/
						count++;						
					}
					groupDetails.add(outerList);
					outerList=null;
				}
			}
		}
		return groupDetails;
	}
	public String getTreasuryName(String strLocationCode,StampCommonDAOImpl commonDAO)
	{
		String strTreasuryName="";
		String strTablename="";
		String strFieldList="";
		String strWhereCondition="";
		strTablename="cmn_location_mst m ";
		strFieldList="m.loc_name ";
		strWhereCondition="m.loc_id="+strLocationCode;
		Iterator itResult=commonDAO.getIterator(strTablename, strFieldList,strWhereCondition );
		if(itResult!=null)
		{
			strTreasuryName=itResult.next().toString();			
		}			
		return strTreasuryName;
	}
	public String getIndentNoWhererList(String[] subTreasuryIndentNo,String strTreasuryIndentNo)
	{
		String strWhereCondition="dt.ind_no in (";
		for(int c=0;c<subTreasuryIndentNo.length;c++)
		{
			strWhereCondition=strWhereCondition+" "+subTreasuryIndentNo[c]+",";
		
		}
		strWhereCondition=strWhereCondition+" "+strTreasuryIndentNo+" )";
		return strWhereCondition;
	}
	public List[] getGroupnameAndGroupCodeForConsolidationIndent(String[] subTreasuryIndentNo,String strTreasuryIndentNo,Map objectArgs,StampCommonDAOImpl commonDAO)
	{
		String strWhereCondition="dts.ind_no in (";
		for(int c=0;c<subTreasuryIndentNo.length;c++)
		{
			strWhereCondition=strWhereCondition+" "+subTreasuryIndentNo[c]+",";
		
		}
		strWhereCondition=strWhereCondition+" "+strTreasuryIndentNo+" ) and dts.grp_code=grp.grp_code";
		String strTablename="trn_stamp_indent_dtls dts,mst_stamp_group grp";
		String strFieldList="distinct dts.grp_code,grp.grp_name";
		List[] groupCodeAndName=new ArrayList[2];
		List groupName=new ArrayList();
		List groupCode=new ArrayList();
		List result=commonDAO.selectRecord(strTablename, strFieldList,strWhereCondition);
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
	public List getDenominationDetailsForConsolidation(List groupCode,String strIndentList)
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
				String strQuery="select distinct dt.dnm_value,sum(dt.stock_on_last_ind),sum(dt.supply_against_last_ind),"+
					"sum(dt.qty_sold),sum(dt.closing_bal_prev_period),sum(dt.qty_due),sum(dt.qty_requd),sum(dt.qty_passed) "+
					" from "+ 
					" trn_stamp_indent_dtls dt where "+strIndentList+
					" and dt.grp_code="+strGroupCode+ "group by dt.dnm_value";
				Query query=session.createSQLQuery(strQuery);
				List result=query.list();
				int count=1;
				if(result.size()>0)
				{
					Iterator itResult=result.iterator();
					while(itResult.hasNext())
					{
						Object[] tuple=(Object[])itResult.next();
						String strDenominationValue=tuple[0]!=null?tuple[0].toString():"";
						String strLastStockBeforeIndent=tuple[1]!=null?tuple[1].toString():"";
						String strReceivedAgainstPrevIndent=tuple[2]!=null?tuple[2].toString():"";
						String strQtySold=tuple[3]!=null?tuple[3].toString():"";
						String strClosingBalance=tuple[4]!=null?tuple[4].toString():"";
						String strQtyDue=tuple[5]!=null?tuple[5].toString():"";
						String strQtyRequired=tuple[6]!=null?tuple[6].toString():"";
						String strQtyPassed=tuple[7]!=null?tuple[7].toString():"";
						String strRemarks="";
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
	public String[] getFromDateAndToDate(String strIndentNo,StampCommonDAOImpl commonDAO)
	{
		String strTablename="trn_stamp_indent_hdr hdr";
		String strFieldList="to_char(hdr.from_date,'dd/MM/yyyy'),to_char(hdr.to_date,'dd/MM/yyyy')";
		String strWherecondition="hdr.ind_no="+strIndentNo;
		String strFromDate="";
		String strToDate="";
		String[] strData=new String[2];
		Iterator itResult=commonDAO.getIterator(strTablename, strFieldList,strWherecondition);
		if(itResult!=null)
		{
			Object[] tuple=(Object[])itResult.next();
			strFromDate=tuple[0]!=null?tuple[0].toString():"";
			strToDate=tuple[1]!=null?tuple[1].toString():"";
		}
		strData[0]=strFromDate;
		strData[1]=strToDate;
		return strData;
	}

}
	

