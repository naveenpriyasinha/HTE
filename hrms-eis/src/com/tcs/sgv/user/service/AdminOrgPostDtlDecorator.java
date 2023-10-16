package com.tcs.sgv.user.service;

import java.util.List;

import javax.servlet.jsp.PageContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.displaytag.decorator.TableDecorator;
import com.tcs.sgv.ess.valueobject.OrgPostDetailsRlt;

public class AdminOrgPostDtlDecorator extends TableDecorator
{
	Log logger = LogFactory.getLog(getClass());
	public String getLink2()
	{		
		String temp="";		
		try
		{
			PageContext context = getPageContext();
			try{
			int lIndex= getListIndex();
			List orgPostDtlList = (List)context.getAttribute("orgPostDtlList");			
			OrgPostDetailsRlt orgPostDetailsRlt = (OrgPostDetailsRlt) orgPostDtlList.get(lIndex);
			temp = String.valueOf(orgPostDetailsRlt.getOrgPostMst().getPostId());
			}catch(Exception ex){logger.error("Error in Admin OrgPOst decorator",ex);}
		}
		catch(Exception e)
		{
			logger.error("error in displayTagDecorator -- getLink2(...)",e);						
		}
		return "<a href=\"javascript:test('"+temp+"')\">Edit</a>  |  Delete <input type=checkbox name=deletedata value="+temp+">" ;
	}	
}
