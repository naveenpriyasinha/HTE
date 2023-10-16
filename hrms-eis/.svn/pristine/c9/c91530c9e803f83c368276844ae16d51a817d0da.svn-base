package com.tcs.sgv.eis.vacancy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tcs.sgv.common.business.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.util.DBConnection;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.valuebeans.reports.ReportTemplate;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.URLData;

public class VacanyIfmsReportDAO extends DefaultReportDataFinder implements
ReportDataFinder{

	Log logger = LogFactory.getLog(VacanyIfmsReportDAO.class.getClass());
	List listOfLocation=new ArrayList();
	List listOfSubLocation=new ArrayList();
	public Collection findReportData(ReportVO report, Object criteria)
	throws ReportException {
		
		Connection lCon = null;
		Statement lStmt = null;
		List pendingList = null;
		ArrayList DataList = new ArrayList();
		long langId = 0L;
		
		StyleVO[] baseFont = new StyleVO[1];
		baseFont[0] = new StyleVO();
		
		baseFont[0].setStyleId(IReportConstants.STYLE_FONT_SIZE);
		baseFont[0].setStyleValue("13");
		
		ReportTemplate rt = new ReportTemplate();
		rt.put(IReportConstants.TMPLT_COLUMN_HEADER, baseFont);
		rt.put(IReportConstants.TMPLT_BASE_FONT, baseFont);
		report.setReportTemplate(rt);
		
		PreparedStatement lPStmt=null;
		ResultSet lRs = null;
		int serialNo = 1;

		try {
			lCon = DBConnection.getConnection();
			lStmt = lCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

			Hashtable request = (Hashtable) ((Hashtable) criteria).get(IReportConstants.REQUEST_KEYS);
			
			String strLang = report.getLangId();
			if(strLang.equalsIgnoreCase("en_US") || strLang.equalsIgnoreCase("1")){
				langId = 1;
			}else{
				langId = 2;
			}
			
			if(report.getReportCode().equals("300230")){
				
				logger.info("Inside 300230");
				List deptIdlist = new ArrayList();
				String depCode = "'100002','100004','100005'";
				
				StringBuilder querystr=new StringBuilder("select department_id from org_department_mst where department_code in("+depCode+") and lang_id="+langId);
				String query=querystr.toString();
				lPStmt=lCon.prepareStatement(query);
				lRs=lPStmt.executeQuery();
				while(lRs.next()){
					deptIdlist.add(lRs.getLong("department_id"));
				}
				logger.info("deptIdlist List Size %%%%%%%%%% "+deptIdlist.size());
				
				String depName = null;
				
				long vacantpost;
				long sanctionStrength;
				long presentStrength;
				long totalPost;
				
				StringBuilder presentquerystr = null;
				StringBuilder sancquerystr = null;
				String sancquery = null;
				String presentquery = null;
				PreparedStatement sanclPStmt = null;
				PreparedStatement presentlPStmt = null;
				ResultSet presentlRs =null;
				ResultSet sanclRs = null;
				
				StringBuilder vacantquerystr = null;
				String vacantquery = null;
				PreparedStatement vacantlPStmt = null;
				ResultSet vacantlRs = null;
				
				StringBuilder loccodeQuerystr = null;
				String loccodequery = null;
				PreparedStatement loccodelPStmt = null;
				ResultSet loccodelRs = null;
				String loccodestr = null;
				
				StringBuilder depQueryStr = null;
				String depquery = null;
				PreparedStatement deplPStmt = null;
				ResultSet deplRs =null;
				
				StringBuilder locIdQuerystr = null;
				String locIdquery = null;
				PreparedStatement locIdlPStmt = null;
				ResultSet locIdlRs =null;
				
				List locIdList = null;
				for (Iterator iter = deptIdlist.iterator(); iter.hasNext();) {
					
					Long deptId = (Long) iter.next();
					
					pendingList = new ArrayList();
					vacantpost = 0l;
					sanctionStrength = 0l;
					presentStrength = 0l;
					totalPost = 0l;
					loccodestr = "";
					
					depQueryStr =new StringBuilder("select dep_name from org_department_mst where department_id="+deptId);
					depquery=depQueryStr.toString();
					deplPStmt=lCon.prepareStatement(depquery);
					deplRs=deplPStmt.executeQuery();
					if(deplRs.next()){
						depName = deplRs.getString("dep_name");
					}
					try{
						deplRs.close();
						deplPStmt.close();
					}catch(Exception e){
						logger.error("ERROR in CONNECTION CLOSING &&&&&&&&&&&&&&&&&&&& ",e);
					}
					
					
					locIdList = new ArrayList();
					locIdQuerystr =new StringBuilder("select loc_id from cmn_location_mst where department_id='"+deptId+"' and lang_id="+langId);
					locIdquery=locIdQuerystr.toString();
					locIdlPStmt=lCon.prepareStatement(locIdquery);
					locIdlRs=locIdlPStmt.executeQuery();
					while(locIdlRs.next()){
						locIdList.add(locIdlRs.getLong("loc_id"));
					}
					try{
						locIdlRs.close();
						locIdlPStmt.close();
					}catch(Exception e){
						logger.error("ERROR in CONNECTION CLOSING &&&&&&&&&&&&&&&&&&&& ",e);
					}
					
					for (Iterator iterator = locIdList.iterator(); iterator.hasNext();) {
						Long locId = (Long) iterator.next();
						
						List lstOfLocation = null;
						lstOfLocation=getSubLocationId(locId,lCon);
						lstOfLocation.add(locId);
						
						String strLocationId = "";
						for (Iterator locIDiterator = lstOfLocation.iterator(); locIDiterator.hasNext();) {
							Long element = (Long) locIDiterator.next();
							strLocationId = strLocationId + element + ",";
						}
						lstOfLocation.clear();
						
						List locCodeList = new ArrayList();
						if(strLocationId.substring(0, strLocationId.length()-1).length() > 0){
							loccodeQuerystr =new StringBuilder("select location_code from cmn_location_mst where loc_id in (" +strLocationId.substring(0, strLocationId.length()-1)+ ") and lang_id="+langId);
							loccodequery=loccodeQuerystr.toString();
							loccodelPStmt=lCon.prepareStatement(loccodequery);
							loccodelRs=loccodelPStmt.executeQuery();
							while(loccodelRs.next()){
								locCodeList.add(loccodelRs.getString("location_code"));
							}
							try{
								loccodelRs.close();
								loccodelPStmt.close();
							}catch(Exception e){
								logger.error("ERROR in CONNECTION CLOSING &&&&&&&&&&&&&&&&&&&& ",e);
							}
						}
						
						for (Iterator locCodeIterator = locCodeList.iterator(); locCodeIterator.hasNext();) {
							String locCode = (String) locCodeIterator.next();
							
							sancquerystr=new StringBuilder("select sanctioned_strength from hr_eis_vacancy_admin_mst o where o.loc_code='" + locCode + "'");
							sancquery=sancquerystr.toString();
							sanclPStmt=lCon.prepareStatement(sancquery);
							sanclRs=sanclPStmt.executeQuery();
							while(sanclRs.next()){
								sanctionStrength = sanctionStrength + sanclRs.getLong("sanctioned_strength");
							}
							try{
								sanclRs.close();
								sanclPStmt.close();
							}catch(Exception e){
								logger.error("ERROR in CONNECTION CLOSING &&&&&&&&&&&&&&&&&&&& ",e);
							}
							
							presentquerystr = new StringBuilder("select count(*) as PresentVacancyStrength from org_userpost_rlt this_ inner join org_post_mst postmst1_ on this_.post_id=postmst1_.post_id where this_.activate_flag=1 and postmst1_.location_code ='"+ locCode +"'");
							presentquery=presentquerystr.toString();
							presentlPStmt=lCon.prepareStatement(presentquery);
							presentlRs=presentlPStmt.executeQuery();
							if(presentlRs.next()){
								presentStrength = presentStrength + presentlRs.getLong("PresentVacancyStrength");
							}
							try{
								presentlRs.close();
								presentlPStmt.close();
							}catch(Exception e){
								logger.error("ERROR in CONNECTION CLOSING &&&&&&&&&&&&&&&&&&&& ",e);
							}
							
							vacantquerystr = new StringBuilder("select count(*) as total from org_post_mst where  location_code ='" +locCode+ "' and activate_flag=1");
							vacantquery=vacantquerystr.toString();
							vacantlPStmt=lCon.prepareStatement(vacantquery);
							vacantlRs=vacantlPStmt.executeQuery();
							if(vacantlRs.next()){
								totalPost = totalPost + vacantlRs.getLong("total");
							}
							try{
								vacantlRs.close();
								vacantlPStmt.close();
							}catch(Exception e){
								logger.error("ERROR in CONNECTION CLOSING &&&&&&&&&&&&&&&&&&&& ",e);
							}
							
							vacantpost = totalPost-presentStrength ;
						}
					}
					
					URLData url=new URLData(depName,"./hdiits.htm?actionFlag=reportService&reportCode=300231&action=generateReport&FromParaPage=TRUE&deptId="+deptId);
					url.setTargetValue(0);
					pendingList.add(serialNo);
					pendingList.add(url);
					pendingList.add(deptId);
					pendingList.add(sanctionStrength);
					pendingList.add(presentStrength);
					pendingList.add(vacantpost);
					pendingList.add(totalPost);
					
					DataList.add(pendingList);
					serialNo++;
				}
				logger.info("300230 Executed &&&&&&& ");
				
			}else if(report.getReportCode().equals("300231")){
				
				logger.info("Inside 300231");
				
				long deptId = 0l;
				long vacantpost=0l;;
				long sanctionStrength=0l;
				long presentStrength=0l;
				long totaplPresentPost=0l;
				long totalPost;
				long totalSum=0l;
				long totalvacantPost=0l;
				List locationCodelst = new ArrayList();
				List lstOfTotLoc=new ArrayList();
				List locNamelist = new ArrayList();
				
				deptId = Long.valueOf(request.get("deptId").toString());
				logger.info("Department Id from request Object >>> "+deptId);
				
				StringBuilder querystr=new StringBuilder("select location_code,loc_name from cmn_location_mst where department_id = "+deptId+" and lang_id ="+langId);
				String query=querystr.toString();
				lPStmt=lCon.prepareStatement(query);
				lRs=lPStmt.executeQuery();
				
				while(lRs.next()){
					locationCodelst.add(lRs.getString("location_code"));
					locNamelist.add(lRs.getString("loc_name"));
				}
				lRs.close();
				lPStmt.close();
				logger.info("locationCodelst Size &&&&&&  "+locationCodelst.size());
				
				long parentlocId =0l;
				int i=0;
				for (Iterator iter = locationCodelst.iterator(); iter.hasNext();) {
					if(!(lstOfTotLoc.isEmpty())){
						lstOfTotLoc.clear();
					}
					vacantpost = 0l;
					sanctionStrength = 0l;
					presentStrength = 0l;
					totalPost = 0l;
					totalvacantPost=0l;
					totalSum=0l;
					totaplPresentPost=0l;
					String locCode = (String) iter.next();
					String locname = locNamelist.get(i).toString();
					i++;
				
					querystr=new StringBuilder("select loc_id from cmn_location_mst where location_code = '"+locCode+"' and lang_id ="+langId);
					query=querystr.toString();
					lPStmt=lCon.prepareStatement(query);
					lRs=lPStmt.executeQuery();
					if(lRs.next()){
						parentlocId = lRs.getLong("loc_id");
					}
				
					List lstOfLocation = null;
					lstOfLocation=getSubLocationId(parentlocId,lCon);
					lstOfLocation.add(parentlocId);
					
					StringBuffer strLocationId = new StringBuffer();
					for (Iterator iterator = lstOfLocation.iterator(); iterator.hasNext();) {
						Long element = (Long) iterator.next();
						//strLocationId = strLocationId + element + ",";
						strLocationId = strLocationId.append(element).append(',');
					}
				
				//	if(strLocationId.substring(0, strLocationId.length()-1).length() > 0){
					if(!strLocationId.equals("")){
						querystr=new StringBuilder("select location_code,loc_name from cmn_location_mst where loc_id in("+ strLocationId.substring(0, strLocationId.length()-1)+") and lang_id ="+langId);
						query=querystr.toString();
						lPStmt=lCon.prepareStatement(query);
						lRs=lPStmt.executeQuery();
						while(lRs.next()){
							lstOfTotLoc.add(lRs.getString("location_code"));
							locNamelist.add(lRs.getString("loc_name"));
						}
						try{
							lRs.close();
							lPStmt.close();
						}catch(Exception e){
							logger.error("ERROR in CONNECTION CLOSING &&&&&&&&&&&&&&&&&&&& ",e);
						}
					}
				
					StringBuilder presentquerystr = null;
					StringBuilder sancquerystr = null;
					String sancquery = null;
					String presentquery = null;
					PreparedStatement sanclPStmt = null;
					PreparedStatement presentlPStmt = null;
					ResultSet presentlRs =null;
					ResultSet sanclRs = null;
					URLData url=null;
					StringBuilder vacantquerystr = null;
					String vacantquery = null;
					PreparedStatement vacantlPStmt = null;
					ResultSet vacantlRs = null;
					
					vacantpost = 0l;
					sanctionStrength = 0l;
					presentStrength = 0l;
					totalPost = 0l;
					totalvacantPost=0l;
					totalSum=0l;
					totaplPresentPost=0l;
					
					for (Iterator iteratorLoc = lstOfTotLoc.iterator(); iteratorLoc.hasNext();) {
						
						String element = (String) iteratorLoc.next();
						
						pendingList = new ArrayList();
						
						sancquerystr=new StringBuilder("select sanctioned_strength from hr_eis_vacancy_admin_mst o where o.loc_code ='"+element+"'");
						sancquery=sancquerystr.toString();
						sanclPStmt=lCon.prepareStatement(sancquery);
						sanclRs=sanclPStmt.executeQuery();
						while(sanclRs.next()){
							sanctionStrength = sanctionStrength + sanclRs.getLong("sanctioned_strength");
						}
						try{
							sanclRs.close();
							sanclPStmt.close();
						}catch(Exception e){
							logger.error("ERROR in CONNECTION CLOSING &&&&&&&&&&&&&&&&&&&& ",e);
						}
						
						
						presentquerystr = new StringBuilder("select count(*) as PresentVacancyStrength from org_userpost_rlt this_ inner join org_post_mst postmst1_ on this_.post_id=postmst1_.post_id where this_.activate_flag=1 and postmst1_.location_code ='"+element+"'");
						presentquery=presentquerystr.toString();
						presentlPStmt=lCon.prepareStatement(presentquery);
						presentlRs=presentlPStmt.executeQuery();
						if(presentlRs.next()){
							presentStrength = presentlRs.getLong("PresentVacancyStrength");
						}
						
						totaplPresentPost=totaplPresentPost+presentStrength;
						
						try{
							presentlRs.close();
							presentlPStmt.close();
						}catch(Exception e){
							logger.error("ERROR in CONNECTION CLOSING &&&&&&&&&&&&&&&&&&&& ",e);
						}
						
						vacantquerystr = new StringBuilder("select count(*) as total from org_post_mst where location_code='" +element+ "' and activate_flag=1");
						vacantquery=vacantquerystr.toString();
						vacantlPStmt=lCon.prepareStatement(vacantquery);
						vacantlRs=vacantlPStmt.executeQuery();
						if(vacantlRs.next()){
							totalPost = vacantlRs.getLong("total");
						}
						
						try{
							vacantlRs.close();
							vacantlPStmt.close();
						}catch(Exception e){
							logger.error("ERROR in CONNECTION CLOSING &&&&&&&&&&&&&&&&&&&& ",e);
						}
						
						totalSum=totalSum+totalPost;
						totalvacantPost=totalSum-totaplPresentPost;
						
					}
				url=new URLData(locname,"./hdiits.htm?actionFlag=reportService&reportCode=300232&action=generateReport&FromParaPage=TRUE&locCode="+locCode);
				url.setTargetValue(0);
				pendingList.add(serialNo);
				pendingList.add(url);
				pendingList.add(locCode);
				pendingList.add(sanctionStrength);
				pendingList.add(totaplPresentPost);
				pendingList.add(totalvacantPost);
				pendingList.add(totalSum);
				DataList.add(pendingList);
				serialNo++;
				lstOfLocation.clear();
			}
				
			logger.info("300231 executed &&&&&&&&& ");
		}else if(report.getReportCode().equals("300232")){
				
				logger.info("Inside 300232");
				String locCode = request.get("locCode").toString();
				logger.info("locCode from request object &&&&&&  "+locCode);
				long parentlocId =0l;
				String parentLocName=null;
				List locationCodelst = new ArrayList();
				List locNamelist = new ArrayList();
				
				StringBuilder querystr=new StringBuilder("select loc_id,loc_name from cmn_location_mst where location_code = '"+locCode+"' and lang_id ="+langId);
				String query=querystr.toString();
				lPStmt=lCon.prepareStatement(query);
				lRs=lPStmt.executeQuery();
				if(lRs.next()){
					parentlocId = lRs.getLong("loc_id");
					parentLocName=lRs.getString("loc_name");
				}
				
				List lstOfLocation = null;
				lstOfLocation=getLocationId(parentlocId,lCon);
				lstOfLocation.add(parentlocId);
				
				String strLocationId = "";
				for (Iterator iter = lstOfLocation.iterator(); iter.hasNext();) {
					Long element = (Long) iter.next();
					strLocationId = strLocationId + element + ",";
				}
				
				if(strLocationId.substring(0, strLocationId.length()-1).length() > 0){
					querystr=new StringBuilder("select location_code,loc_name from cmn_location_mst where loc_id in("+ strLocationId.substring(0, strLocationId.length()-1)+") and lang_id ="+langId);
					query=querystr.toString();
					lPStmt=lCon.prepareStatement(query);
					lRs=lPStmt.executeQuery();
					while(lRs.next()){
						locationCodelst.add(lRs.getString("location_code"));
						locNamelist.add(lRs.getString("loc_name"));
					}
					
					try{
						lRs.close();
						lPStmt.close();
					}catch(Exception e){
						logger.error("ERROR in CONNECTION CLOSING &&&&&&&&&&&&&&&&&&&& ",e);
					}
				}
				logger.info("locationCodelst &&&&& "+locationCodelst.size());
				
				long vacantpost;
				long sanctionStrength;
				long presentStrength;
				long totaplPresentPost;
				long totalPost;
				long totalSum;
				long totalvacantPost;
				StringBuilder presentquerystr = null;
				StringBuilder sancquerystr = null;
				String sancquery = null;
				String presentquery = null;
				PreparedStatement sanclPStmt = null;
				PreparedStatement presentlPStmt = null;
				ResultSet presentlRs =null;
				ResultSet sanclRs = null;
				URLData url=null;
				StringBuilder vacantquerystr = null;
				String vacantquery = null;
				PreparedStatement vacantlPStmt = null;
				ResultSet vacantlRs = null;
				long locId=0l;
				int i=0;
				List lstOfSubLocation=null;
				
				for (Iterator iter = locationCodelst.iterator(); iter.hasNext();) {
					pendingList = new ArrayList();
					vacantpost = 0l;
					sanctionStrength = 0l;
					presentStrength = 0l;
					totalPost = 0l;
					totalvacantPost=0l;
					totalSum=0l;
					totaplPresentPost=0l;
					
					String element = (String) iter.next();
					String locname = locNamelist.get(i).toString();
					i++;
					
					StringBuilder subQuerystr=new StringBuilder("select loc_id from cmn_location_mst where location_code = '"+element+"' and lang_id ="+langId);
					lPStmt=lCon.prepareStatement(subQuerystr.toString());
					lRs=lPStmt.executeQuery();
					if(lRs.next()){
						locId = lRs.getLong("loc_id");
					}
					
					try{
						lRs.close();
						lPStmt.close();
					}catch(Exception e){
						logger.error("ERROR in CONNECTION CLOSING &&&&&&&&&&&&&&&&&&&& ",e);
					}
					
					if(!(String.valueOf(parentlocId).equals(String.valueOf(locId)))){
						logger.info("Inside the If Condition of the Recursive method");
						lstOfSubLocation=getSubLocationId(locId, lCon);
						lstOfSubLocation.add(locId);
					}else{
						logger.info("Inside the Else Condition of the Recursive method");
						lstOfSubLocation = new ArrayList();
						lstOfSubLocation.add(parentlocId);
					}
					
					for (Iterator iterator = lstOfSubLocation.iterator(); iterator.hasNext();) {
						
						Long elementSubLocation = (Long) iterator.next();
						sancquerystr=new StringBuilder("select sanctioned_strength from hr_eis_vacancy_admin_mst o where o.loc_code ='"+elementSubLocation+"'");
						sancquery=sancquerystr.toString();
						sanclPStmt=lCon.prepareStatement(sancquery);
						sanclRs=sanclPStmt.executeQuery();
						while(sanclRs.next()){
							sanctionStrength = sanctionStrength + sanclRs.getLong("sanctioned_strength");
						}
						
						try{
							sanclRs.close();
							sanclPStmt.close();
						}catch(Exception e){
							logger.error("ERROR in CONNECTION CLOSING &&&&&&&&&&&&&&&&&&&& ",e);
						}
						
						presentquerystr = new StringBuilder("select count(*) as PresentVacancyStrength from org_userpost_rlt this_ inner join org_post_mst postmst1_ on this_.post_id=postmst1_.post_id where this_.activate_flag=1 and postmst1_.location_code ='"+elementSubLocation+"'");
						presentquery=presentquerystr.toString();
						presentlPStmt=lCon.prepareStatement(presentquery);
						presentlRs=presentlPStmt.executeQuery();
						if(presentlRs.next()){
							presentStrength = presentlRs.getLong("PresentVacancyStrength");
						}
						
						totaplPresentPost=totaplPresentPost+presentStrength;
						
						try{
							presentlRs.close();
							presentlPStmt.close();
						}catch(Exception e){
							logger.error("ERROR in CONNECTION CLOSING &&&&&&&&&&&&&&&&&&&& ",e);
						}
						
						vacantquerystr = new StringBuilder("select count(*) as total from org_post_mst where location_code='" +elementSubLocation+ "' and activate_flag=1");
						vacantquery=vacantquerystr.toString();
						vacantlPStmt=lCon.prepareStatement(vacantquery);
						vacantlRs=vacantlPStmt.executeQuery();
						
						if(vacantlRs.next()){
							totalPost = vacantlRs.getLong("total");
						}
						
						try{
							vacantlRs.close();
							vacantlPStmt.close();
						}catch(Exception e){
							logger.error("ERROR in CONNECTION CLOSING &&&&&&&&&&&&&&&&&&&& ",e);
						}
						
						totalSum=totalSum+totalPost;
						totalvacantPost=totalSum-totaplPresentPost;
					}
					
					url=new URLData(locname,"./hdiits.htm?actionFlag=reportService&reportCode=300234&action=generateReport&FromParaPage=TRUE&locCode="+element);
					url.setTargetValue(0);
					pendingList.add(serialNo);
					pendingList.add(url);
					pendingList.add(element);
					pendingList.add(sanctionStrength);
					pendingList.add(totaplPresentPost);
					pendingList.add(totalvacantPost);
					pendingList.add(totalSum);
					DataList.add(pendingList);
					serialNo++;
					lstOfSubLocation.clear();
				}
				logger.info("300232 Executed &&&&& ");
				
			}else if(report.getReportCode().equals("300234")){

				logger.info("Inside 300234");
				String locCode = request.get("locCode").toString();
				logger.info("locCode from request object &&&&&&  "+locCode);
				long parentlocId =0l;
				List locationCodelst = new ArrayList();
				List locNamelist = new ArrayList();
				
				StringBuilder querystr=new StringBuilder("select loc_id from cmn_location_mst where location_code = '"+locCode+"' and lang_id ="+langId);
				String query=querystr.toString();
				lPStmt=lCon.prepareStatement(query);
				lRs=lPStmt.executeQuery();
				if(lRs.next()){
					parentlocId = lRs.getLong("loc_id");
				}
				
				List lstOfLocation = null;
				lstOfLocation=getLocationId(parentlocId,lCon);
				lstOfLocation.add(parentlocId);
				
				String strLocationId = "";
				for (Iterator iter = lstOfLocation.iterator(); iter.hasNext();) {
					Long element = (Long) iter.next();
					strLocationId = strLocationId + element + ",";
				}
				
				if(strLocationId.substring(0, strLocationId.length()-1).length() > 0){
					querystr=new StringBuilder("select location_code,loc_name from cmn_location_mst where loc_id in("+ strLocationId.substring(0, strLocationId.length()-1)+") and lang_id ="+langId);
					query=querystr.toString();
					lPStmt=lCon.prepareStatement(query);
					lRs=lPStmt.executeQuery();
					while(lRs.next()){
						locationCodelst.add(lRs.getString("location_code"));
						locNamelist.add(lRs.getString("loc_name"));
					}
					
					try{
						lRs.close();
						lPStmt.close();
					}catch(Exception e){
						logger.error("ERROR in CONNECTION CLOSING &&&&&&&&&&&&&&&&&&&& ",e);
					}
				}
				logger.info("locationCodelst size >>> "+locationCodelst.size());
				
				long vacantpost;
				long sanctionStrength;
				long presentStrength;
				long totalPost;
				StringBuilder presentquerystr = null;
				StringBuilder sancquerystr = null;
				String sancquery = null;
				String presentquery = null;
				PreparedStatement sanclPStmt = null;
				PreparedStatement presentlPStmt = null;
				ResultSet presentlRs =null;
				ResultSet sanclRs = null;
				
				StringBuilder vacantquerystr = null;
				String vacantquery = null;
				PreparedStatement vacantlPStmt = null;
				ResultSet vacantlRs = null;
				List lstOfSubLocation=null;
				int i=0;
				
				for (Iterator iter = locationCodelst.iterator(); iter.hasNext();) {
					pendingList = new ArrayList();
					vacantpost = 0l;
					sanctionStrength = 0l;
					presentStrength = 0l;
					totalPost = 0l;
					URLData url=null;
					String element = (String) iter.next();
					String locname = locNamelist.get(i).toString();
					i++;
					
					sancquerystr=new StringBuilder("select sanctioned_strength from hr_eis_vacancy_admin_mst o where o.loc_code ='"+element+"'");
					sancquery=sancquerystr.toString();
					sanclPStmt=lCon.prepareStatement(sancquery);
					sanclRs=sanclPStmt.executeQuery();
					while(sanclRs.next()){
						sanctionStrength = sanctionStrength + sanclRs.getLong("sanctioned_strength");
					}
					try{
						sanclRs.close();
						sanclPStmt.close();
					}catch(Exception e){
						logger.error("ERROR in CONNECTION CLOSING &&&&&&&&&&&&&&&&&&&& ",e);
					}
					
					presentquerystr = new StringBuilder("select count(*) as PresentVacancyStrength from org_userpost_rlt this_ inner join org_post_mst postmst1_ on this_.post_id=postmst1_.post_id where this_.activate_flag=1 and postmst1_.location_code ='"+element+"'");
					presentquery=presentquerystr.toString();
					presentlPStmt=lCon.prepareStatement(presentquery);
					presentlRs=presentlPStmt.executeQuery();
					if(presentlRs.next()){
						presentStrength = presentlRs.getLong("PresentVacancyStrength");
					}
					try{
						presentlRs.close();
						presentlPStmt.close();
					}catch(Exception e){
						logger.error("ERROR in CONNECTION CLOSING &&&&&&&&&&&&&&&&&&&& ",e);
					}
					
					vacantquerystr = new StringBuilder("select count(*) as total from org_post_mst where location_code='" +element+ "' and activate_flag=1");
					vacantquery=vacantquerystr.toString();
					vacantlPStmt=lCon.prepareStatement(vacantquery);
					vacantlRs=vacantlPStmt.executeQuery();
					if(vacantlRs.next()){
						totalPost = vacantlRs.getLong("total");
					}
					try{
						vacantlRs.close();
						vacantlPStmt.close();
					}catch(Exception e){
						logger.error("ERROR in CONNECTION CLOSING &&&&&&&&&&&&&&&&&&&& ",e);
					}
					
					vacantpost =totalPost- presentStrength ;
					url=new URLData(locname,"./hdiits.htm?actionFlag=reportService&reportCode=300233&action=generateReport&FromParaPage=TRUE&locCode="+element);
					url.setTargetValue(0);
					
					pendingList.add(serialNo);
					pendingList.add(url);
					pendingList.add(element);
					pendingList.add(sanctionStrength);
					pendingList.add(presentStrength);
					pendingList.add(vacantpost);
					pendingList.add(totalPost);
					
					DataList.add(pendingList);
					serialNo++;
				}
				logger.info("300233 Executed &&& ");
				
			}else if(report.getReportCode().equals("300233")){
				
				logger.info("Inside 300233");
				
				String locCode = request.get("locCode").toString();
				long locId = 0l;
				String dsgnIdStr = "";
				String dsgnCodeStr ="";
				List dsgnCodeList = new ArrayList();
				List dsgnNameList = new ArrayList();
				
				StringBuilder querystr=new StringBuilder("select loc_id from cmn_location_mst where location_code = '"+locCode+"' and lang_id ="+langId);
				String query=querystr.toString();
				lPStmt=lCon.prepareStatement(query);
				lRs=lPStmt.executeQuery();
				if(lRs.next()){
					locId = lRs.getLong("loc_id");
				}
				try{
					lRs.close();
					lPStmt.close();
				}catch(Exception e){
					logger.error("ERROR in CONNECTION CLOSING &&&&&&&&&&&&&&&&&&&& ",e);
				}
				
				querystr=new StringBuilder("select o.dsgn_id as dsgn_id,dsgn_code from org_post_details_rlt o,org_designation_mst d where d.dsgn_id=o.dsgn_id and o.loc_id = '"+locId+"' and o.lang_id ="+langId);
				query=querystr.toString();
				lPStmt=lCon.prepareStatement(query);
				lRs=lPStmt.executeQuery();
				
				while(lRs.next()){
					dsgnIdStr = dsgnIdStr + lRs.getLong("dsgn_id") + ",";
					//dsgnIdStr = dsgnIdStr.append(lRs.getLong("dsgn_id")).append(',');
					dsgnCodeStr = dsgnCodeStr + lRs.getString("dsgn_code") + ",";
					//dsgnCodeStr = dsgnCodeStr.append(lRs.getString("dsgn_code")).append(',');
				}
				
				if(!dsgnCodeStr.equals("")){
					dsgnCodeStr=dsgnCodeStr.substring(0, dsgnCodeStr.length()-1).replaceAll(",", "','");
					querystr=new StringBuilder("select dsgn_code from hr_eis_vacancy_admin_mst where loc_code = '"+locCode+"' and dsgn_code not in('"+ dsgnCodeStr +"') ");
					query=querystr.toString();
					lPStmt=lCon.prepareStatement(query);
					lRs=lPStmt.executeQuery();
					dsgnCodeStr="";
					while(lRs.next()){
						dsgnCodeStr = dsgnCodeStr + lRs.getLong("dsgn_code") + ",";
					}
					try{
						lRs.close();
						lPStmt.close();
					}catch(Exception e){
						logger.error("ERROR in CONNECTION CLOSING &&&&&&&&&&&&&&&&&&&& ",e);
					}
					
					dsgnCodeStr=dsgnCodeStr.substring(0, dsgnCodeStr.length()-1).replaceAll(",", "','");   //substring(0, dsgnCodeStr.length()-1).replaceAll(",", "','");
					querystr=new StringBuilder("select dsgn_id from org_designation_mst where dsgn_code in('"+ dsgnCodeStr +"')");
					query=querystr.toString();
					lPStmt=lCon.prepareStatement(query);
					lRs=lPStmt.executeQuery();
					while(lRs.next()){
						dsgnIdStr = dsgnIdStr + lRs.getLong("dsgn_id") + ",";
					}
					try{
						lRs.close();
						lPStmt.close();
					}catch(Exception e){
						logger.error("ERROR in CONNECTION CLOSING &&&&&&&&&&&&&&&&&&&& ",e);
					}
				}
				
				querystr=new StringBuilder("select dsgn_code,dsgn_name from org_designation_mst where dsgn_id in("+dsgnIdStr.substring(0, dsgnIdStr.length()-1)+")");
				query=querystr.toString();
				lPStmt=lCon.prepareStatement(query);
				lRs=lPStmt.executeQuery();
				while(lRs.next()){
					dsgnCodeList.add(lRs.getString("dsgn_code"));
					dsgnNameList.add(lRs.getString("dsgn_name"));
				}
				
				try{
					lRs.close();
					lPStmt.close();
				}catch(Exception e){
					logger.error("ERROR in CONNECTION CLOSING &&&&&&&&&&&&&&&&&&&& ",e);
				}
				
				long totalPost;
				long vacantpost;
				long sanctionStrength;
				long presentStrength;
				
				StringBuilder presentquerystr = null;
				StringBuilder sancquerystr = null;
				String sancquery = null;
				String presentquery = null;
				PreparedStatement sanclPStmt = null;
				PreparedStatement presentlPStmt = null;
				ResultSet presentlRs =null;
				ResultSet sanclRs = null;
				
				StringBuilder vacantquerystr = null;
				String vacantquery = null;
				PreparedStatement vacantlPStmt = null;
				ResultSet vacantlRs = null;
				
				int i=0;
				for (Iterator iter = dsgnCodeList.iterator(); iter.hasNext();) {
					String element = (String) iter.next();
					
					String dsgnName = dsgnNameList.get(i).toString();
					i++;
					
					pendingList = new ArrayList();
					vacantpost = 0l;
					sanctionStrength = 0l;
					presentStrength = 0l;
					totalPost = 0l;
					URLData url=null;
					
					sancquerystr=new StringBuilder("select sanctioned_strength from hr_eis_vacancy_admin_mst o where o.dsgn_code ='"+element+"' and loc_code='"+locCode+"'");
					sancquery=sancquerystr.toString();
					sanclPStmt=lCon.prepareStatement(sancquery);
					sanclRs=sanclPStmt.executeQuery();
					if(sanclRs.next()){
						sanctionStrength = sanctionStrength + sanclRs.getLong("sanctioned_strength");
					}
					
					try{
						sanclRs.close();
						sanclPStmt.close();
					}catch(Exception e){
						logger.error("ERROR in CONNECTION CLOSING &&&&&&&&&&&&&&&&&&&& ",e);
					}
					
					presentquerystr = new StringBuilder("select count(*) as PresentVacancyStrength from org_userpost_rlt this_ inner join org_post_mst postmst1_ on this_.post_id=postmst1_.post_id where this_.activate_flag=1 and postmst1_.dsgn_code ='"+element+"' and postmst1_.location_code='"+locCode+"'");
					presentquery=presentquerystr.toString();
					presentlPStmt=lCon.prepareStatement(presentquery);
					presentlRs=presentlPStmt.executeQuery();
					if(presentlRs.next()){
						presentStrength = presentlRs.getLong("PresentVacancyStrength");
					}
					
					try{
						presentlRs.close();
						presentlPStmt.close();
					}catch(Exception e){
						logger.error("ERROR in CONNECTION CLOSING &&&&&&&&&&&&&&&&&&&& ",e);
					}
					
					vacantquerystr = new StringBuilder("select count(*) as total from org_post_mst where dsgn_code='" +element+ "' and location_code='"+locCode+"' and activate_flag=1");
					vacantquery=vacantquerystr.toString();
					vacantlPStmt=lCon.prepareStatement(vacantquery);
					vacantlRs=vacantlPStmt.executeQuery();
					if(vacantlRs.next()){
						totalPost = vacantlRs.getLong("total");
					}
					
					try{
						vacantlRs.close();
						vacantlPStmt.close();
					}catch(Exception e){
						logger.error("ERROR in CONNECTION CLOSING &&&&&&&&&&&&&&&&&&&& ",e);
					}
					
					vacantpost = totalPost-presentStrength ;
					
					url=new URLData(dsgnName,"./hdiits.htm?actionFlag=reportService&reportCode=300229&action=generateReport&FromParaPage=TRUE&dsgnCode="+element);
					url.setTargetValue(0);
					
					pendingList.add(serialNo);
					pendingList.add(url);
					pendingList.add(element);
					pendingList.add(sanctionStrength);
					pendingList.add(presentStrength);
					pendingList.add(vacantpost);
					pendingList.add(totalPost);
					
					DataList.add(pendingList);
					serialNo++;
				}
				logger.info("300233 Executed >>");
				
			}else if(report.getReportCode().equals("300229")){
				
				logger.info("Inside 300229");
				String dsgnCode = request.get("dsgnCode").toString();
				
				List userIdList = new ArrayList();
				List postIdList = new ArrayList();
				List locCodeList = new ArrayList();
				
				StringBuilder querystr=new StringBuilder("select u.user_id as userId,o.location_code as locCode,o.post_id as postId from org_post_mst o, org_userpost_rlt u where o.post_id=u.post_id and dsgn_code='"+ dsgnCode +"'");
				String query=querystr.toString();
				lPStmt=lCon.prepareStatement(query);
				lRs=lPStmt.executeQuery();
				while(lRs.next()){
					userIdList.add(lRs.getLong("userId"));
					postIdList.add(lRs.getLong("postId"));
					locCodeList.add(lRs.getString("locCode"));
				}
				
				try{
					lRs.close();
					lPStmt.close();
				}catch(Exception e){
					logger.error("ERROR in CONNECTION CLOSING &&&&&&&&&&&&&&&&&&&& ",e);
				}
				
				int index = 0;
				StringBuilder empmstquerystr = null;
				String empmstquery = "";
				PreparedStatement empmstlPStmt = null;
				ResultSet empmstlRs = null;
				
				StringBuilder postquerystr = null;
				String postquery = "";
				PreparedStatement postlPStmt = null;
				ResultSet postlRs = null;
				
				StringBuilder locquerystr = null;
				String locquery = "";
				PreparedStatement loclPStmt = null;
				ResultSet loclRs = null;
				
				String empGpfNum = null;
				String empName = null;
				Date empdoj = null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String postName = null;
				String locName = null;
				
				for (Iterator userIditer = userIdList.iterator(); userIditer.hasNext();) {
					Long userId = (Long) userIditer.next();
					Long postId = (Long) postIdList.get(index);
					String locCode = (String) locCodeList.get(index);
					index++;
					
					empName = "";
					empdoj = new Date();
					postName = "";
					locName = "";
					empGpfNum = ""; 
					
					empmstquerystr=new StringBuilder("select emp_id,emp_gpf_num,emp_fname,emp_mname,emp_lname,emp_doj from org_emp_mst where user_id="+userId);
					empmstquery=empmstquerystr.toString();
					empmstlPStmt=lCon.prepareStatement(empmstquery);
					empmstlRs=empmstlPStmt.executeQuery();
					if(empmstlRs.next()){
						if((empmstlRs.getString("emp_gpf_num") != null) && (!empmstlRs.getString("emp_gpf_num").equals(""))){
							empGpfNum = empmstlRs.getString("emp_gpf_num");
						}
				//		System.out.println("Emp Id  >> "+empmstlRs.getLong("emp_id") + "Emp Gpf Num >> "+empmstlRs.getString("emp_gpf_num"));
						
						if((empmstlRs.getString("emp_fname")!=null) && (!empmstlRs.getString("emp_fname").equals(""))){
							empName = empName + empmstlRs.getString("emp_fname") + " ";
						}
						if((empmstlRs.getString("emp_mname")!=null) && (!empmstlRs.getString("emp_mname").equals(""))){
							empName = empName + empmstlRs.getString("emp_mname") + " ";
						}
						if((empmstlRs.getString("emp_lname")!=null) && (!empmstlRs.getString("emp_lname").equals(""))){
							empName = empName + empmstlRs.getString("emp_lname");
						}
						
						if((empmstlRs.getDate("emp_doj")!=null && (!empmstlRs.getDate("emp_doj").equals("")))){
							empdoj = empmstlRs.getDate("emp_doj");
						}
				//		System.out.println("Employee Date of Joining >>> "+ sdf.format(empmstlRs.getDate("emp_doj")));
					}
					
					try{
						empmstlRs.close();
						empmstlPStmt.close();
					}catch(Exception e){
						logger.error("ERROR in CONNECTION CLOSING &&&&&&&&&&&&&&&&&&&& ",e);
					}
					
					postquerystr=new StringBuilder("select post_name from org_post_details_rlt where post_id="+postId);
					postquery=postquerystr.toString();
					postlPStmt=lCon.prepareStatement(postquery);
					postlRs=postlPStmt.executeQuery();
					if(postlRs.next()){
						postName = postlRs.getString("post_name");
					}
					try{
						postlRs.close();
						postlPStmt.close();
					}catch(Exception e){
						logger.error("ERROR in CONNECTION CLOSING &&&&&&&&&&&&&&&&&&&& ",e);
					}
					
					
					locquerystr=new StringBuilder("select loc_name from cmn_location_mst where location_code='"+locCode+"'and lang_id="+langId);
					locquery=locquerystr.toString();
					loclPStmt=lCon.prepareStatement(locquery);
					loclRs=loclPStmt.executeQuery();
					if(loclRs.next()){
						locName = loclRs.getString("loc_name");
					}
					
					try{
						loclRs.close();
						loclPStmt.close();
					}catch(Exception e){
						logger.error("ERROR in CONNECTION CLOSING &&&&&&&&&&&&&&&&&&&& ",e);
					}
					
					pendingList = new ArrayList();
					pendingList.add(serialNo);
					if((empGpfNum != null) && (!empGpfNum.equals(""))){
						pendingList.add(empGpfNum);
					}else{
						pendingList.add("N/A");
					}
					pendingList.add(empName);
					pendingList.add(sdf.format(empdoj));
					pendingList.add(postName);
					pendingList.add(locName);
					serialNo++;
					
					DataList.add(pendingList);
				}
				logger.info("300229 Executed &&&&&&& ");
			}
			
		}catch(Exception e){
			logger.error("ERROR IN FINDREPORTDATA METHOD OF VACANCY REPORT "+e);
		//	logger.error("Error is: "+ e.getMessage());
		}finally {
			try {
				lCon.close();
			} catch (SQLException e) {
				logger.error("ERROR IN FINDREPORTDATA METHOD OF VACANCY REPORT "+e);
		//		logger.error("Error is: "+ e.getMessage());
			}
		}
		return DataList;
	}
	
	private List getLocationId(long locId ,Connection connection){
		PreparedStatement prepareStatement=null;
		ResultSet rs=null;
		long parentId;
		long locationID;
		
	//	List listOfLocations=new ArrayList();
		try{
			
			StringBuilder locationIdList=new StringBuilder("select loc_id,parent_loc_id from cmn_location_mst where parent_loc_Id=?");
			prepareStatement=connection.prepareStatement(locationIdList.toString());
			prepareStatement.setLong(1, locId);
			rs=prepareStatement.executeQuery();
			while(rs.next()){
				locationID=rs.getLong("loc_id");
				parentId=rs.getLong("parent_loc_id");
				
				Long locationId=0L;
				listOfLocation.add(locationID);
				/*if(parentId !=-1){
					getLocationId(locationID,connection);
				}*/
			}
		}catch(Exception e){
		//	logger.error("Error is: "+ e.getMessage());
			logger.info("ERROR in getLocationId method ",e);
		}
		
		return listOfLocation;
	}
	
	private List getSubLocationId(long locId ,Connection connection){
		PreparedStatement prepareStatement=null;
		ResultSet rs=null;
		long parentId;
		long locationID;
		
	//	List listOfLocations=new ArrayList();
		try{
			
			StringBuilder locationIdList=new StringBuilder("select loc_id,parent_loc_id from cmn_location_mst where parent_loc_Id=?");
			prepareStatement=connection.prepareStatement(locationIdList.toString());
			prepareStatement.setLong(1, locId);
			rs=prepareStatement.executeQuery();
			
			while(rs.next()){
				locationID=rs.getLong("loc_id");
				parentId=rs.getLong("parent_loc_id");
				
		//		Long locationId=0L;
				listOfSubLocation.add(locationID);
				if(parentId !=-1){
					
					getSubLocationId(locationID,connection);
				}
			}
		}catch(Exception e){
	//		logger.error("Error is: "+ e.getMessage());
			logger.info("ERROR in getSubLocationId method >>> ",e);
		}finally{
			try{
				rs.close();	
				prepareStatement.close();
			}catch(Exception e){
				logger.error("Error is: "+ e.getMessage());
			}
		}
		return listOfSubLocation;
	}
}
