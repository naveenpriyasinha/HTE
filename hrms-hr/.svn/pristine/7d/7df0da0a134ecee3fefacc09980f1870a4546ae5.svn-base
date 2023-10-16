package com.tcs.sgv.hr.payincrement.dao;





import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.tcs.sgv.common.dao.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.util.DBConnection;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.valuebeans.reports.ReportTemplate;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;

public class PayIncrmntReportDAO extends DefaultReportDataFinder {

	public Collection findReportData(ReportVO report, Object criteria)

	throws ReportException {
		
		String langId = report.getLangId();
		String locId = report.getLocId();
		Connection lCon = null;
		Statement lStmt = null;
		Statement lStmt1 = null;
		//ArrayList resultList=new ArrayList();
		Session hibSession = null;

		ArrayList DataList = new ArrayList();

		//Added by Ravish for setting up font size
		StyleVO[] baseFont = new StyleVO[1];
		baseFont[0] = new StyleVO();
		baseFont[0].setStyleId(IReportConstants.STYLE_FONT_SIZE);
		baseFont[0].setStyleValue("13");
		/*baseFont[1] = new StyleVO(  );
		 baseFont[1].setStyleId( IReportConstants.STYLE_FONT_COLOR );
		 baseFont[1].setStyleValue( IReportConstants.VALUE_FONT_COLOR_DARKGRAY );*/
		//report.setStyleList(baseFont);
		ReportTemplate rt = new ReportTemplate();
		rt.put(IReportConstants.TMPLT_COLUMN_HEADER, baseFont);
		rt.put(IReportConstants.TMPLT_BASE_FONT, baseFont);
		report.setReportTemplate(rt);

		Hashtable requestKeys = (Hashtable) ((Hashtable) criteria).get(IReportConstants.REQUEST_KEYS);


		
		//Added by Ravish for setting up font size
		try {
			lCon = DBConnection.getConnection();
			lStmt = lCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			lStmt1 = lCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			//SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

			//HibSession session=new HibSession(LeaveReportDAO.class,sessionFactory);

			//  hibSession =sessionFactory.openSession();
			List pendingList = new ArrayList();

			Hashtable sessionKeys = (Hashtable) ((Hashtable) criteria)
			.get(IReportConstants.SESSION_KEYS);
			Map loginDetail = (HashMap) sessionKeys.get("loginDetailsMap");

			Long empId = (Long) loginDetail.get("userId");

			PreparedStatement lPStmt = null;
			ResultSet lRs = null;
			StringBuffer sql = new StringBuffer();
			String lStrSqlQuery = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


			if(report.getReportCode().equals("200190"))
			{

				sql = new StringBuffer("select to_char(payInc.Next_Inc_Dt,'dd/MM/yyyy') as NextInc,payInc.Basic_Sal as BasSalary,payInc.Present_Pay_Scale as PayScale,payInc.Remarks as Remarks,payInc.Inc_Amt as IncAmt,to_char(payInc.Last_Inc_Dt,'dd/MM/yyyy') as IncrDate ");
				sql.append(" from hst_hr_eis_pay_inc payInc ");
				//sql.append(" where payInc.Emp_Id in (SELECT orgEmpMst.Emp_Id from org_emp_mst orgEmpMst ");
				//sql.append("  where orgEmpMst.User_Id =(select orgEmpMst.User_Id from org_emp_mst orgEmpMst ");
				sql.append("  where payInc.User_Id  = ? ");

				lStrSqlQuery = "";
				lStrSqlQuery = sql.toString();

				lPStmt = lCon.prepareStatement(lStrSqlQuery);
				lPStmt.setLong(1, empId);


				int counter = 1;

				lRs = lPStmt.executeQuery();

				while (lRs.next()) {

					ArrayList rowList = new ArrayList();
					rowList.add(String.valueOf(counter));
					rowList.add(lRs.getString("IncrDate"));
					rowList.add(lRs.getString("IncAmt"));
					rowList.add(lRs.getString("PayScale"));
					rowList.add(lRs.getString("BasSalary"));
					rowList.add(lRs.getString("NextInc"));

					if(lRs.getString("Remarks")!=null){

						rowList.add(lRs.getString("Remarks"));

					}
					else{

						rowList.add("");

					}

					counter++;
					DataList.add(rowList);
				}
			}


		}

		catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {

				lCon.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//	hibSession.close();
		}

		

		return DataList;
	}


}
