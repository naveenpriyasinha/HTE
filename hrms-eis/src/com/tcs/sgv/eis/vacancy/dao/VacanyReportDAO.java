package com.tcs.sgv.eis.vacancy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.tcs.sgv.common.valueobject.CmnLocationMst;


public class VacanyReportDAO extends DefaultReportDataFinder implements
		ReportDataFinder {

	List districtList=new ArrayList();
	private final static Log logger = LogFactory.getLog(VacanyReportDAO.class.getClass());
	List listOfLocation=new ArrayList();
	String Lang_Id = "en_US";
	String Loc_Id = "LC1";
	static long loggedInUserLoc =-1;
	static CmnLocationMst loggedInUserCmnLocationMst = null;
	String locationCode = null;
	
	List departmentList=new ArrayList();

	
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

		StringBuffer sql = new StringBuffer();
		String lStrSqlQuery = "";
		PreparedStatement lPStmt = null;
		ResultSet lRs = null;

		StringBuffer sqlPresentStrength = new StringBuffer();
		String lStrSqlQueryForPS = "";
		PreparedStatement lPStmtPS = null;
		ResultSet lRsPS = null;

		StringBuffer sqlPostName = new StringBuffer();
		String lStrSqlQueryPostName = "";
		PreparedStatement lPStmtPostName = null;
		ResultSet lRsPostName = null;

		long sanctionStrength = 0l;
		long vacantPost = 0l;
		long presentStrength = 0l;
		String post = "";
		int year = 0;
		String month="";
		String qtr = "";
		String day = "";
		
		StringBuilder startDate = null;
		
		try {
			lCon = DBConnection.getConnection();
			lStmt = lCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

	//		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
	//		Hashtable sessionKeys = (Hashtable) ((Hashtable) criteria).get(IReportConstants.SESSION_KEYS);
			Hashtable request = (Hashtable) ((Hashtable) criteria).get(IReportConstants.REQUEST_KEYS);
			
			if((request.get("asondate") != null) && (!request.get("asondate").equals(""))){
				startDate = new StringBuilder(request.get("asondate").toString());
			}else
			{
				if((request.get("cmbYear")!=null) && (!request.get("cmbYear").equals("")))
				{
					year =  Integer.valueOf(request.get("cmbYear").toString());
				}
				
				if((request.get("cmbMonth")!=null) && (!request.get("cmbMonth").equals("")))
				{
					switch (Integer.valueOf(request.get("cmbMonth").toString())){
					
						case 1:
						case 3:
						case 5:
						case 7:
						case 8:
						case 10:
						case 12:
								day = "31";
								break;
						case 2:
								if(year%400 ==0 || (year%100 != 0 && year%4 == 0)){
									day = "28";
								}else{
									day = "29";
								}
								
								break;
						case 4:
						case 6:
						case 9:
						case 11:
							day = "30";
							break;
						default:
							day = "30";
							break;
					}	
					
					if(request.get("cmbMonth").toString().length()==1){
						month = "0"+request.get("cmbMonth").toString();
					}else{
						month = request.get("cmbMonth").toString();
					}
					startDate =  new StringBuilder(day+"/"+month+"/"+year);
				}else{
					if((request.get("cmbQuarter")!=null) && (!request.get("cmbQuarter").equals("")))
					{
						switch(Integer.valueOf(request.get("cmbQuarter").toString())){
						
							case 1:
								qtr = "31/03";
								break;
							case 2:
								qtr = "30/06";
								break;
							case 3:
								qtr = "30/09";
								break;
							case 4:
								qtr = "31/12";
								break;
							default:
								day = "31/12";
								break;
						}
						startDate = new StringBuilder(qtr+"/"+year);
					}
				}
			}
			
			
			PreparedStatement IPStmtLocId=null;
			PreparedStatement IPStmtBrchCode=null;
			List lstOfLocation= new ArrayList();
			ResultSet IRsLocId=null;
			List lstOfLocCode=new ArrayList();
			ResultSet rsLocCode=null;
			ResultSet rsBrchCode=null;
			PreparedStatement prepareLocCode=null;
			StringBuilder builderLocCode=null;
			//Long locationId=0L;
			
			String[] Dsgn_code = null;
			String[] TempBranchCode=null;
			String TempBranchString="";
			String sbStringBranch="";
			//String dsgnlist = "";
			String branchlist = "";
			int serialNo = 0;
			String [] strDate = null;
			Date dateEntered = null;
			StringBuilder builderBranchCode=null;
			List dsgnIdList = new ArrayList();
			
			if(report.getParameterValue("Designation") != null && report.getParameterValue("Designation") != ""){
				 String designationString="";
				Dsgn_code=(String[])report.getParameterValue("Designation");
				for (int i = 0; i < Dsgn_code.length; i++) {
					designationString="'"+Dsgn_code[i]+"',"+designationString;
					dsgnIdList.add(Dsgn_code[i]);
				}
				logger.info("designationString $$$$$$$$$$$$$$$$$  "+designationString);
				
				if(!designationString.equals(""))
				{
					designationString=designationString.substring(0, (designationString.length()-1));	
				}
			}
			
			if (report.getParameterValue("Branch") != null && report.getParameterValue("Branch") != "") {
				TempBranchCode = (String[]) report.getParameterValue("Branch");
				
				for (int i = 0; i < TempBranchCode.length; i++) {
					TempBranchString = TempBranchString.concat("'").concat(TempBranchCode[i]).concat("',");
				}
				logger.info("TempBranchString >>> "+TempBranchString);
				if (!TempBranchString.equals("")) {
					sbStringBranch = TempBranchString.substring(0,TempBranchString.lastIndexOf(','));
					
					builderBranchCode = new StringBuilder("select branch_Code from cmn_branch_mst where branch_id in ("+ sbStringBranch + ")");
					IPStmtBrchCode = lCon.prepareStatement(builderBranchCode.toString());
					rsBrchCode = IPStmtBrchCode.executeQuery();
					while (rsBrchCode.next()) {
						branchlist = branchlist + "'" + rsBrchCode.getString("branch_code") + "',";
					}
					try{
						rsBrchCode.close();
						IPStmtBrchCode.close();
					}catch(Exception e){
						logger.info("ERROR IN CONN OBJECT CLOSING &&&&&&&& ",e);
					}
				}
			}
			logger.info("branchlist String %%%%%%%%%%%%%  "+branchlist);
			
			if(!("".equals(startDate))){
				strDate=startDate.toString().split("/");
			}
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(Integer.parseInt(strDate[2]),Integer.parseInt(strDate[1]),Integer.parseInt(strDate[0]));
			dateEntered = calendar.getTime();
			
			if (report.getLangId().equals("en_US")) {
				langId = 1L;
			} else {
				langId = 2L;
			}
			
			String[] locationCodeArray=(String[])report.getParameterValue("Location");
			String locationCodeString="";
			for(int k=0;k<locationCodeArray.length;k++)
			{
				locationCodeString = locationCodeString + "'" + locationCodeArray[k] + "',";
			}
			
			if(locationCodeString.length()!=0)
			{
				locationCodeString=locationCodeString.substring(0, (locationCodeString.length()-1));		
			}
			logger.info("locationCodeString which selected in parameter &&&&&&&&&&&^^^^^^^^^ "+locationCodeString);
			
			List locationIdList = new ArrayList();
			StringBuilder LocId=new StringBuilder("select loc_id from cmn_location_mst where location_code in ("+locationCodeString+")");
			String LocIdquery=LocId.toString();
			IPStmtLocId=lCon.prepareStatement(LocIdquery);
			IRsLocId=IPStmtLocId.executeQuery();
			while(IRsLocId.next()){
				locationIdList.add(IRsLocId.getLong("loc_id"));
			}
			try{
				IRsLocId.close();
				IPStmtLocId.close();
			}catch(Exception e){
				logger.info("ERROR IN CONN OBJECT CLOSING &&&&&&&& ",e);
			}
			
			for (Iterator iter = locationIdList.iterator(); iter.hasNext();) {
				Long locId = (Long) iter.next();
				List lstOfAllLocation=getLocationId(locId,lCon);
				for (Iterator iterator = lstOfAllLocation.iterator(); iterator.hasNext();) {
					Long locIdelement = (Long) iterator.next();
					if(!lstOfLocation.contains(locIdelement)){
						lstOfLocation.add(locIdelement);
					}
				}
				lstOfLocation.add(locId);
				lstOfAllLocation.clear();
			}
			
			String strOfLocationId="";
			String strOfSubStringOfLocationId="";
			if(!(lstOfLocation.isEmpty())){
				for (Iterator iter = lstOfLocation.iterator(); iter.hasNext();) {
					Long element = (Long) iter.next();
					strOfLocationId=strOfLocationId+element+",";
				}
			}
			strOfSubStringOfLocationId=strOfLocationId.substring(0,strOfLocationId.lastIndexOf(','));
			builderLocCode=new StringBuilder("select location_code from cmn_location_mst where loc_id in("+strOfSubStringOfLocationId+")");
			prepareLocCode=lCon.prepareStatement(builderLocCode.toString());
			rsLocCode=prepareLocCode.executeQuery();
			while(rsLocCode.next()){
				lstOfLocCode.add(rsLocCode.getString("location_code"));
			}
			try{
				rsLocCode.close();
				prepareLocCode.close();
			}catch(Exception e){
				logger.info("ERROR IN CONN OBJECT CLOSING &&&&&&&& ",e);
			}
			
			String strOfLocCode="";
			String strOfLocCodeSubString="";
			for (Iterator iter = lstOfLocCode.iterator(); iter.hasNext();) {
				String element = (String) iter.next();
				strOfLocCode=strOfLocCode+"'"+element+"'"+",";
			}
			strOfLocCodeSubString=strOfLocCode.substring(0,strOfLocCode.lastIndexOf(','));
			logger.info("Location Code String with child location &&&&&&&&& "+strOfLocCodeSubString);
			
			if(!dsgnIdList.isEmpty()){
				
				List CopyOfList = new ArrayList();
				for (Iterator iter = dsgnIdList.iterator(); iter.hasNext();) {
					String element = (String) iter.next();
					if (!(CopyOfList.contains(element))) {
						CopyOfList.add(element);
					}
				}

				// Sanctioned Strength
				for (Iterator iter = CopyOfList.iterator(); iter.hasNext();) {
					
					sanctionStrength = 0l;
					presentStrength = 0l;
					String element = (String) iter.next();
						
					if(!branchlist.equals("")){
						sql = new StringBuffer("select count(*) as Sanctionedstrength from org_post_mst o where (o.location_code in("+strOfLocCodeSubString+") and o.dsgn_code=? and o.branch_code in("+branchlist.substring(0, branchlist.length()-1)+") and ((end_date is not null and end_date>?) or (end_date is null and start_date<=?)))");
					}
					else{
						sql = new StringBuffer("select count(*) as Sanctionedstrength from org_post_mst o where (o.location_code in("+strOfLocCodeSubString+") and o.dsgn_code=? and ((end_date is not null and end_date>?) or (end_date is null and start_date<=?)))");
					}
					
					lStrSqlQuery = sql.toString();
					lPStmt = lCon.prepareStatement(lStrSqlQuery);
					lPStmt.setString(1, element);
					lPStmt.setDate(2, new java.sql.Date( calendar.getTime().getTime()));
					lPStmt.setDate(3, new java.sql.Date( calendar.getTime().getTime()));
					lRs = lPStmt.executeQuery();
					if(lRs.next()){
						sanctionStrength = lRs.getLong("Sanctionedstrength");
					}
					try{
						lRs.close();
						lPStmt.close();
					}catch(Exception e){
						logger.info("ERROR IN CONN OBJECT CLOSING &&&&&&&& ",e);
					}
					
					if(!branchlist.equals("")){
						sqlPresentStrength = new StringBuffer("select count(*) as PresentVacancyStrength from org_userpost_rlt this_ inner join org_post_mst postmst1_ on this_.post_id=postmst1_.post_id where ((this_.end_date is not null and this_.end_date>=? and this_.start_date<=?) or (this_.end_date is null and this_.start_date<=?)) and ((postmst1_.end_date is not null and postmst1_.end_date>?) or (postmst1_.end_date is null and postmst1_.start_date<=?)) and postmst1_.location_code in("+strOfLocCodeSubString+") and postmst1_.dsgn_code=? and postmst1_.branch_code in ("+branchlist.substring(0, branchlist.length()-1)+")");
					}else
					{
						sqlPresentStrength = new StringBuffer("select count(*) as PresentVacancyStrength from org_userpost_rlt this_ inner join org_post_mst postmst1_ on this_.post_id=postmst1_.post_id where ((this_.end_date is not null and this_.end_date>=? and this_.start_date<=?) or (this_.end_date is null and this_.start_date<=?)) and ((postmst1_.end_date is not null and postmst1_.end_date>?) or (postmst1_.end_date is null and postmst1_.start_date<=?)) and postmst1_.location_code in("+strOfLocCodeSubString+") and postmst1_.dsgn_code=?");
					}
					lStrSqlQueryForPS = sqlPresentStrength.toString();
					lPStmtPS = lCon.prepareStatement(lStrSqlQueryForPS);
					lPStmtPS.setDate(1,  new java.sql.Date( calendar.getTime().getTime()));
					lPStmtPS.setDate(2,  new java.sql.Date( calendar.getTime().getTime()));
					lPStmtPS.setDate(3,  new java.sql.Date( calendar.getTime().getTime()));
					lPStmtPS.setDate(4,  new java.sql.Date( calendar.getTime().getTime()));
					lPStmtPS.setDate(5,  new java.sql.Date( calendar.getTime().getTime()));
					lPStmtPS.setString(6, element);
					lRsPS = lPStmtPS.executeQuery();
					if(lRsPS.next()){
						presentStrength = lRsPS.getLong("PresentVacancyStrength");
					}
					try{
						lRsPS.close();
						lPStmtPS.close();
					}catch(Exception e){
						logger.info("ERROR IN CONN OBJECT CLOSING &&&&&&&& ",e);
					}
					
					vacantPost = sanctionStrength - presentStrength;
					
					//Post Name
					if (presentStrength != 0) {
						
						sqlPostName = new StringBuffer("select dsgn_name from org_designation_mst where dsgn_code='"+ element +"' and lang_id =?");
						lStrSqlQueryPostName = sqlPostName.toString();
						lPStmtPostName = lCon.prepareStatement(lStrSqlQueryPostName);
						lPStmtPostName.setLong(1, langId);
						lRsPostName = lPStmtPostName.executeQuery();
						
						while (lRsPostName.next()){
							serialNo++;
							post = lRsPostName.getString("dsgn_name");
							pendingList = new ArrayList();
							pendingList.add(serialNo); // since there will be
							pendingList.add(post);
							pendingList.add(sanctionStrength);
							pendingList.add(presentStrength);
							pendingList.add(vacantPost); // for finding
							DataList.add(pendingList);
						}
						try{
							lRsPostName.close();
							lPStmtPostName.close();
						}catch(Exception e){
							logger.info("ERROR IN CONN OBJECT CLOSING &&&&&&&& ",e);
						}
						
					} else {
						
						sqlPostName = new StringBuffer("select dsgn_name from org_designation_mst where dsgn_code='"+ element +"' and lang_id =?");
						lStrSqlQueryPostName = sqlPostName.toString();
						lPStmtPostName = lCon.prepareStatement(lStrSqlQueryPostName);
						lPStmtPostName.setLong(1, langId);
						lRsPostName=lPStmtPostName.executeQuery();
						
						while (lRsPostName.next()){
							serialNo++;
							post = lRsPostName.getString("dsgn_name");
							pendingList = new ArrayList();
							pendingList.add(serialNo); // since there will be
							pendingList.add(post);
							pendingList.add(sanctionStrength);
							pendingList.add(presentStrength);
							pendingList.add(vacantPost); // for finding
							DataList.add(pendingList);
						}
						try{
							lRsPostName.close();
							lPStmtPostName.close();
						}catch(Exception e){
							logger.info("ERROR IN CONN OBJECT CLOSING &&&&&&&& ",e);
						}
					}
				}// End of For Loop
			} 
			else {
				
				if(!branchlist.equals("")){
					sql = new StringBuffer("select dsgn_code as DsgnCode from org_post_mst o where branch_code in (" + branchlist.substring(0, branchlist.length()-1) +") and o.location_code in ("+strOfLocCodeSubString +")  and ((end_date is not null and end_date > ? ) or (end_date is null and start_date <= ?))");
				}else{
					sql = new StringBuffer("select dsgn_code as DsgnCode from org_post_mst o where o.location_code in ("+strOfLocCodeSubString +")  and ((end_date is not null and end_date > ?) or (end_date is null and start_date <= ?))");
				}
				lStrSqlQuery = sql.toString();
				lPStmt = lCon.prepareStatement(lStrSqlQuery);
				lPStmt.setDate(1, new java.sql.Date( calendar.getTime().getTime()));
				lPStmt.setDate(2, new java.sql.Date( calendar.getTime().getTime()));
				lRs = lPStmt.executeQuery();
				List dsgnList = new ArrayList();
				while(lRs.next()){
					dsgnList.add(lRs.getString("DsgnCode"));
				}
				try{
					lRs.close();
					lPStmt.close();
				}catch(Exception e){
					logger.info("ERROR IN CONN OBJECT CLOSING &&&&&&&& ",e);
				}
				
				List CopyOfList = new ArrayList();
				for (Iterator iter = dsgnList.iterator(); iter.hasNext();) {
					String element = (String) iter.next();
					if (!(CopyOfList.contains(element))) {
						CopyOfList.add(element);
					}
				}

				for (Iterator iter = CopyOfList.iterator(); iter.hasNext();) {
					
					sanctionStrength = 0l;
					presentStrength = 0l;
					String element = (String) iter.next();
					
//					 Sanctioned Strength
					if(!branchlist.equals("")){
						sql = new StringBuffer("select count(*) as Sanctionedstrength from org_post_mst o where (o.location_code in("+strOfLocCodeSubString+") and o.dsgn_code=? and o.branch_code in("+branchlist.substring(0, branchlist.length()-1)+") and ((end_date is not null and end_date>?) or (end_date is null and start_date<=?)))");
					}
					else{
						sql = new StringBuffer("select count(*) as Sanctionedstrength from org_post_mst o where (o.location_code in("+strOfLocCodeSubString+") and o.dsgn_code=? and ((end_date is not null and end_date>?) or (end_date is null and start_date<=?)))");
					}
					lStrSqlQuery = sql.toString();
					lPStmt = lCon.prepareStatement(lStrSqlQuery);
					lPStmt.setString(1, element);
					lPStmt.setDate(2, new java.sql.Date( calendar.getTime().getTime()));
					lPStmt.setDate(3, new java.sql.Date( calendar.getTime().getTime()));
					lRs = lPStmt.executeQuery();
					if(lRs.next()){
						sanctionStrength = lRs.getLong("Sanctionedstrength");
					}
					try{
						lRs.close();
						lPStmt.close();
					}catch(Exception e){
						logger.info("ERROR IN CONN OBJECT CLOSING &&&&&&&& ",e);
					}
					
					// Present Strength
					if(!branchlist.equals("")){
						sqlPresentStrength = new StringBuffer("select count(*) as PresentVacancyStrength from org_userpost_rlt this_ inner join org_post_mst postmst1_ on this_.post_id=postmst1_.post_id where ((this_.end_date is not null and this_.end_date>=? and this_.start_date<=?) or (this_.end_date is null and this_.start_date<=?)) and ((postmst1_.end_date is not null and postmst1_.end_date>?) or (postmst1_.end_date is null and postmst1_.start_date<=?)) and postmst1_.location_code in("+strOfLocCodeSubString+") and postmst1_.dsgn_code=? and postmst1_.branch_code in ("+branchlist.substring(0, branchlist.length()-1)+")");
					}else
					{
						sqlPresentStrength = new StringBuffer("select count(*) as PresentVacancyStrength from org_userpost_rlt this_ inner join org_post_mst postmst1_ on this_.post_id=postmst1_.post_id where ((this_.end_date is not null and this_.end_date>=? and this_.start_date<=?) or (this_.end_date is null and this_.start_date<=?)) and ((postmst1_.end_date is not null and postmst1_.end_date>?) or (postmst1_.end_date is null and postmst1_.start_date<=?)) and postmst1_.location_code in("+strOfLocCodeSubString+") and postmst1_.dsgn_code=?");
					}
					lStrSqlQueryForPS = sqlPresentStrength.toString();
					lPStmtPS = lCon.prepareStatement(lStrSqlQueryForPS);
					lPStmtPS.setDate(1, new java.sql.Date( calendar.getTime().getTime()));
					lPStmtPS.setDate(2, new java.sql.Date( calendar.getTime().getTime()));
					lPStmtPS.setDate(3, new java.sql.Date( calendar.getTime().getTime()));
					lPStmtPS.setDate(4, new java.sql.Date( calendar.getTime().getTime()));
					lPStmtPS.setDate(5, new java.sql.Date( calendar.getTime().getTime()));
					lPStmtPS.setString(6, element);
					lRsPS = lPStmtPS.executeQuery();
					if(lRsPS.next()){
						presentStrength = lRsPS.getLong("PresentVacancyStrength");
					}
					try{
						lRsPS.close();
						lPStmtPS.close();
					}catch(Exception e){
						logger.info("ERROR IN CONN OBJECT CLOSING &&&&&&&& ",e);
					}
					
					vacantPost = sanctionStrength - presentStrength;
					
					// Post Name
					if (presentStrength != 0) {
						
						sqlPostName = new StringBuffer("select dsgn_name from org_designation_mst where dsgn_code='"+ element +"' and lang_id =?");
						lStrSqlQueryPostName = sqlPostName.toString();
						lPStmtPostName = lCon.prepareStatement(lStrSqlQueryPostName);
						lPStmtPostName.setLong(1, langId);
						lRsPostName = lPStmtPostName.executeQuery();
						
						while (lRsPostName.next()){
							serialNo++;
							post = lRsPostName.getString("dsgn_name");
							pendingList = new ArrayList();
							pendingList.add(serialNo); // since there will be
							pendingList.add(post);
							pendingList.add(sanctionStrength);
							pendingList.add(presentStrength);
							pendingList.add(vacantPost); // for finding
							DataList.add(pendingList);
						}
						try{
							lRsPostName.close();
							lPStmtPostName.close();
						}catch(Exception e){
							logger.info("ERROR IN CONN OBJECT CLOSING &&&&&&&& ",e);
						}
					} 
					else {
						
						sqlPostName = new StringBuffer("select dsgn_name from org_designation_mst where dsgn_code='"+ element +"' and lang_id =?");
						lStrSqlQueryPostName = sqlPostName.toString();
						lPStmtPostName = lCon.prepareStatement(lStrSqlQueryPostName);
						lPStmtPostName.setLong(1, langId);
						lRsPostName = lPStmtPostName.executeQuery();
						
						while (lRsPostName.next()){
							serialNo++;
							post = lRsPostName.getString("dsgn_name");
							pendingList = new ArrayList();
							pendingList.add(serialNo); // since there will be
							pendingList.add(post);
							pendingList.add(sanctionStrength);
							pendingList.add(presentStrength);
							pendingList.add(vacantPost); // for finding
							DataList.add(pendingList);
						}
						try{
							lRsPostName.close();
							lPStmtPostName.close();
						}catch(Exception e){
							logger.info("ERROR IN CONN OBJECT CLOSING &&&&&&&& ",e);
						}
					}
				}
			}

		} catch (Exception e) {
			logger.error("ERROR IN FINDREPORTDATA METHOD OF VACANCY REPORT "+e);
		//	logger.error("Error is: "+ e.getMessage());
		} finally {
			try {
				lCon.close();
			} catch (SQLException e) {
				logger.error("ERROR IN FINDREPORTDATA METHOD OF VACANCY REPORT "+e);
			//	logger.error("Error is: "+ e.getMessage());
			}
		}

		return DataList;
	}
	
	private List getLocationId(long locId ,Connection connection){
		PreparedStatement prepareStatement=null;
		ResultSet rs=null;
		long parentId;
		long locationID;
		
		try{
			
			StringBuilder locationIdList=new StringBuilder("select loc_id,parent_loc_id from cmn_location_mst where parent_loc_Id=?");
			prepareStatement=connection.prepareStatement(locationIdList.toString());
			prepareStatement.setLong(1, locId);
			rs=prepareStatement.executeQuery();
			while(rs.next()){
				locationID=rs.getLong("loc_id");
				parentId=rs.getLong("parent_loc_id");
				listOfLocation.add(locationID);
				if(parentId !=-1){
					getLocationId(locationID,connection);
				}
			}
		}catch(Exception e){
	//		logger.error("Error is: "+ e.getMessage());
			logger.info("ERROR in getLocationId method >>> ",e);
		}
		
		return listOfLocation;
	}
	
}

