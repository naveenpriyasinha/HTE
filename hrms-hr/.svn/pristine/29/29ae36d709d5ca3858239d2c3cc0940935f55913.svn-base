package com.tcs.sgv.hr.payfixation.dao;

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


public class PayFixationReportDAO extends DefaultReportDataFinder
{	
		public Collection findReportData(ReportVO report, Object criteria)
				throws ReportException
				{
			
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

				Long userId = (Long) loginDetail.get("userId");

				PreparedStatement lPStmt = null;
				ResultSet lRs = null;
				StringBuffer sql = new StringBuffer();
				String lStrSqlQuery = "";
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


				if(report.getReportCode().equals("411111"))
				{

					
					

					sql = new StringBuffer("select to_char(payfix.pay_fix_date,'dd/MM/yyyy') as PayFixDate,payfix.prev_pay_scale as PrevPayScale,payfix.prev_pay as PrevPay, ");
					sql.append(" payfix.rev_pay_scale as RevPayScale,payfix.rev_pay as RevPay,to_char(payfix.nxt_incr_date,'dd/MM/yyyy') as NextInc,payfix.remarks as Remarks, ");
					sql.append(" lookup.lookup_name as Name ");
					sql.append(" from hst_hr_payfix_mst payfix,cmn_lookup_mst lookup ");
					sql.append(" where payfix.reas_pay_fix_id=lookup.lookup_id  "); 
					sql.append("  and payfix.User_Id = ? ");

					lStrSqlQuery = "";
					lStrSqlQuery = sql.toString();

					lPStmt = lCon.prepareStatement(lStrSqlQuery);
					lPStmt.setLong(1, userId);
					
					int counter = 1;

					lRs = lPStmt.executeQuery();

					while (lRs.next()) {

						ArrayList rowList = new ArrayList();
						rowList.add(String.valueOf(counter));
						rowList.add(lRs.getString("PayFixDate"));
						rowList.add(lRs.getString("PrevPayScale"));
						rowList.add(lRs.getString("PrevPay"));
						rowList.add(lRs.getString("RevPayScale"));
						rowList.add(lRs.getString("RevPay"));
						rowList.add(lRs.getString("Name"));
						rowList.add(lRs.getString("NextInc"));
						String remarks=lRs.getString("Remarks");
					
						
						
						if(remarks==null)
						{
						
						rowList.add("");
						}
						else 
						{
							rowList.add(lRs.getString("Remarks"));	
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


