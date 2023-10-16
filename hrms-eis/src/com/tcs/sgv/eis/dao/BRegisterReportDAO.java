package com.tcs.sgv.eis.dao;

/*
 * Purpose: Register-B reports
 * Date: September-2009
 */


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tcs.sgv.common.business.reports.ReportDataFinder;
import com.tcs.sgv.common.dao.CmnLanguageMstDaoImpl;
import com.tcs.sgv.common.dao.reports.DefaultReportDataFinder;
import com.tcs.sgv.common.exception.reports.ReportException;
import com.tcs.sgv.common.util.reports.IReportConstants;
import com.tcs.sgv.common.valuebeans.reports.DefaultComboItem;
import com.tcs.sgv.common.valuebeans.reports.ReportColumnVO;
import com.tcs.sgv.common.valuebeans.reports.ReportVO;
import com.tcs.sgv.common.valuebeans.reports.StyleVO;
import com.tcs.sgv.common.valuebeans.reports.StyledData;
import com.tcs.sgv.common.valueobject.CmnLanguageMst;
import com.tcs.sgv.common.valueobject.CmnLocationMst;
import com.tcs.sgv.core.service.ServiceLocator;
import com.tcs.sgv.reports.dao.ReportParameterDAO;




public class BRegisterReportDAO 
				extends DefaultReportDataFinder 
							implements ReportDataFinder {
	
	/* class level variable declaration */
	private static Logger logger = Logger.getLogger(PayrollReportsDAO.class);
	
	//Resources
	ResourceBundle constantsBundle = ResourceBundle.getBundle("resources.Payroll");

	
	@Override
	public Collection findReportData(ReportVO reportVO, Object criteria)
			throws ReportException {


		//For DB Connection		
		Map requestAttributes 			= (Map) ((Map)criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		ServiceLocator serviceLocator 	= (ServiceLocator) requestAttributes.get("serviceLocator");
		SessionFactory sessionFactory 	= serviceLocator.getSessionFactory();

		Map serviceMap 		= (Map)requestAttributes.get("serviceMap");						
		Map baseLoginMap 	= (Map)serviceMap.get("baseLoginMap");

		CmnLocationMst locationVO=(CmnLocationMst)baseLoginMap.get("locationVO");

		String langName	=	reportVO.getLangId();
		CmnLanguageMstDaoImpl cmnLanguageMstDaoImpl = new CmnLanguageMstDaoImpl(CmnLanguageMst.class,serviceLocator.getSessionFactory());
		CmnLanguageMst cmnLanguageMst =   cmnLanguageMstDaoImpl.getLangVOByShortName(langName);  
		long langId = cmnLanguageMst.getLangId();

		//Report PARAM
		long locId  		= locationVO.getLocId(); //location id
		int year	 		= Integer.parseInt( this.checkIfNull(reportVO.getParameterValue("year")) );	//selected Year
		String billNo 		= null;	//selected Bill No
		String paybillType	= this.checkIfNull( reportVO.getParameterValue("billType") );	//pay bill or arrears or supplementary
		String reportType	= this.checkIfNull( reportVO.getParameterValue("reportType") );	//reportType: billWise or monthWise??
		int selMonth		= Integer.parseInt( this.checkIfNull(reportVO.getParameterValue("month")) );	//selected Year

		String supplementary1 	=  constantsBundle.getString("supplPaybillFirst");
		String supplementary2 	=  constantsBundle.getString("supplPaybillSecond");
		String supplementary3 	=  constantsBundle.getString("supplPaybillThird");
		String arrears 			=  constantsBundle.getString("arrearbillTypeId");
		String paybill 			=  constantsBundle.getString("paybillTypeId");
		
		
		String postType		="";
		String subheadCode	="";
		String classIds		="";
		String desgnIds		="";
		
		//parsing bill no
		String BillNoinner = this.checkIfNull(reportVO.getParameterValue("billNo"));

			if(!BillNoinner.equalsIgnoreCase("") && !BillNoinner.equalsIgnoreCase("-1"))
			{
				logger.info("the innser bill no is "+BillNoinner);
				StringTokenizer st1=new StringTokenizer(BillNoinner,"~");
				int l=0;

				while(st1.hasMoreTokens())
				{
					if(l==0)
						subheadCode=st1.nextToken();
					else if(l==1)
						classIds=st1.nextToken();
					else if(l==2)
						desgnIds=st1.nextToken();
					else if(l==3)
						postType=st1.nextToken().trim();
					else if(l==4)
						billNo=st1.nextToken();

					l++;
				} 
			}

		logger.info("langId: " +langId);
		logger.info("locId: " +locId);
		logger.info("year: " +year);
		logger.info("billNo: " +billNo);
		logger.info("selMonth: " +selMonth);
		logger.info("reportType: " +reportType);


		ArrayList returnList = new ArrayList(); //reports data


		try{

			/* For Register-B report */
			if( reportVO.getReportCode().equals("109") || reportVO.getReportCode().equals("2500109") )
			{
				logger.info("*********************     Inside Register-B billWise report  *********************");	



				/** ************************************************
				 * Report Styles Declaration Section - Begin       *
				 * ************************************************/
				
				//FOR Gross, Deduc TOTAL: BOLD, Black, AND RIGHT ALIGN
				StyleVO[] styleVO = new StyleVO[3];
					//Bold Font
					styleVO[0] = new StyleVO();
					styleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
					styleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLD);
					// align
					styleVO[1] = new StyleVO();
					styleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
					styleVO[1].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					// color
					styleVO[2] = new StyleVO();
					styleVO[2].setStyleId(IReportConstants.STYLE_FONT_COLOR);
					styleVO[2].setStyleValue(IReportConstants.VALUE_FONT_COLOR_BLACK );


				//FOR NetTotal: Bold , Right Align and BLUE Color
				StyleVO[] styleColorVO = new StyleVO[3];
					//Bold Font
					styleColorVO[0] = new StyleVO();
					styleColorVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
					styleColorVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLD);
					// align
					styleColorVO[1] = new StyleVO();
					styleColorVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
					styleColorVO[1].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					// color
					styleColorVO[2] = new StyleVO();
					styleColorVO[2].setStyleId(IReportConstants.STYLE_FONT_COLOR);
					styleColorVO[2].setStyleValue(IReportConstants.VALUE_FONT_COLOR_BLUE );

					
				//FOR HEADER: Bold , Left Align  and GREEN Color
				StyleVO[] styleHeaderColorVO = new StyleVO[3];
					//Bold Font
					styleHeaderColorVO[0] = new StyleVO();
					styleHeaderColorVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
					styleHeaderColorVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLD);
					// align
					styleHeaderColorVO[1] = new StyleVO();
					styleHeaderColorVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
					styleHeaderColorVO[1].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_LEFT);
					// color
					styleHeaderColorVO[2] = new StyleVO();
					styleHeaderColorVO[2].setStyleId(IReportConstants.STYLE_FONT_COLOR);
					styleHeaderColorVO[2].setStyleValue(IReportConstants.VALUE_FONT_COLOR_GREEN);
					
					
				//FOR ADDITIONAL HEADER: Bold , Center Align, Large and BLACK Color
				StyleVO[] styleAddlHeaderColorVO = new StyleVO[4];
					//Bold Font
					styleAddlHeaderColorVO[0] = new StyleVO();
					styleAddlHeaderColorVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
					styleAddlHeaderColorVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLD);
					// align
					styleAddlHeaderColorVO[1] = new StyleVO();
					styleAddlHeaderColorVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
					styleAddlHeaderColorVO[1].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
					// font size
					styleAddlHeaderColorVO[2] = new StyleVO();
					styleAddlHeaderColorVO[2].setStyleId(IReportConstants.STYLE_FONT_SIZE);
					styleAddlHeaderColorVO[2].setStyleValue(IReportConstants.VALUE_FONT_SIZE_LARGER);
					// color
					styleAddlHeaderColorVO[3] = new StyleVO();
					styleAddlHeaderColorVO[3].setStyleId(IReportConstants.STYLE_FONT_COLOR);
					styleAddlHeaderColorVO[3].setStyleValue(IReportConstants.VALUE_FONT_COLOR_BLACK);

					
				/** *********************************************************
				 *  	 Report Styles Declaration Section - End            *
				 *  *********************************************************/

	
					
				/** *************************************************************************************
				 *  Add the report's static part here ( i.e. Sr no, allowance and deduction name )		*		
				 ***********************************************************************************  **/

					
					
				/** -- set reports additional header -- */
				StyledData  headerStyledData = new StyledData();
							headerStyledData.setStyles(styleAddlHeaderColorVO);
							headerStyledData.setData("Bill No: " + billNo +" ( Financial Year: "+year+" - "+ (year+1) +" )" );
							reportVO.setAdditionalHeader(headerStyledData);

				
				/** -- Allowances header -- */
				ArrayList allowancesHeader = new ArrayList();	//ALLOWANCES/DUES HEADER
						headerStyledData = new StyledData();
						headerStyledData.setStyles( styleHeaderColorVO );
						headerStyledData.setData("- DUES -");	//give header name
						headerStyledData.setColspan(26);		//merge all 26 columns i.e. Allowance + Rs. + Paise
						allowancesHeader.add(headerStyledData);	//Add "Dues" Header
						
				/** -- Recovery header -- */
				ArrayList recoveriesHeader = new ArrayList();	//RECOVERY HEADER
						headerStyledData = new StyledData();
						headerStyledData.setStyles( styleHeaderColorVO );
						headerStyledData.setData("- RECOVERIES -");	//give header name
						headerStyledData.setColspan(26);		//merge all 26 columns i.e. Recoveries. + Paise
						recoveriesHeader.add(headerStyledData);	//Add "Dues" Header

				/** -- Deduction header -- */						
				ArrayList deductionHeader = new ArrayList();
						headerStyledData = new StyledData();
						headerStyledData.setStyles( styleHeaderColorVO );
						headerStyledData.setData("- DEDUCTIONS -");	//give header name
						headerStyledData.setColspan(26);			//merge all 26 columns i.e. Deductions + Rs. + Paise
						deductionHeader.add(headerStyledData);		//Add "DEDUCTIONS" Header
				
				

						
                        
				/** -- Allowance/Dues -- */
				
				int SrNo=0; //For Sr.No.
				
				returnList.add(allowancesHeader);	//Add allowances/dues header
				
				//PAY OF OFFICER
				ArrayList row1 = new ArrayList();		 
					row1.add(0,++SrNo);	
					row1.add(1,"PAY OF OFFICER");
				returnList.add(row1);
				
				//PAY OF ESTABLISHMENT
				ArrayList row2 = new ArrayList();								
				row2.add(0,++SrNo);
				row2.add(1,"PAY OF ESTABLISHMENT");
				returnList.add(row2);
				
				//GRADE PAY
				ArrayList row3 = new ArrayList();								
				row3.add(0,++SrNo);
				row3.add(1,"GRADE PAY");
				returnList.add(row3);
				
				//DEARNESS ALLOWANCE
				ArrayList row4 = new ArrayList(); 						
				row4.add(0,++SrNo);
				row4.add(1,"DEARNESS ALLOWANCE");
				returnList.add(row4); 
				
				//OTHER ALLOWANCE
				ArrayList row5 = new ArrayList();		
				row5.add(0,++SrNo);
				row5.add(1,"OTHER ALLOWANCE");
				returnList.add(row5); 
				
				//MEDICAL ALLOWANCE
				ArrayList row6 = new ArrayList();	
				row6.add(0,++SrNo);
				row6.add(1,"MEDICAL ALLOWANCE");
				returnList.add(row6);
				
				//BONUS
				ArrayList row7 = new ArrayList();	
				row7.add(0,++SrNo);
				row7.add(1,"BONUS");
				returnList.add(row7);
				
				//HOUSE RENT ALLOWANCE
				ArrayList row8 = new ArrayList();	
				row8.add(0,++SrNo);
				row8.add(1,"HOUSE RENT ALLOWANCE");
				returnList.add(row8);
				
				//CITY-CUM-LOCAL ALLOWANCE
				ArrayList row9 = new ArrayList();	
				row9.add(0,++SrNo);
				row9.add(1,"CITY-CUM-LOCAL ALLOWANCE");
				returnList.add(row9);
				
				//TRANSPORT ALLOWANCE
				ArrayList row10 = new ArrayList();	
				row10.add(0,++SrNo);
				row10.add(1,"TRANSPORT ALLOWANCE");
				returnList.add(row10);
				
				//DEARNESS PAY - GAZETTED
				ArrayList row11 = new ArrayList();	
				row11.add(0,++SrNo);
				row11.add(1,"DEARNESS PAY - GAZETTED");
				returnList.add(row11);
				
				//DEARNESS PAY - NON-GAZETTED
				ArrayList row12 = new ArrayList();	
				row12.add(0,++SrNo);
				row12.add(1,"DEARNESS PAY - NON-GAZETTED");
				returnList.add(row12);
				
				//WASHING ALLOWANCE
				ArrayList row13 = new ArrayList();	
				row13.add(0,++SrNo);
				row13.add(1,"WASHING ALLOWANCE");
				returnList.add(row13);
				
				//OTHER CHARGES
				ArrayList row14 = new ArrayList();	
				row14.add(0,++SrNo);
				row14.add(1,"OTHER CHARGES");
				returnList.add(row14);
				
				//SPECIAL PAY
				ArrayList row15 = new ArrayList();	
				row15.add(0,++SrNo);
				row15.add(1,"SPECIAL PAY");
				returnList.add(row15);
				
				//LEAVE SALARY
				ArrayList row16 = new ArrayList();		
				row16.add(0,++SrNo);
				row16.add(1,"LEAVE SALARY");
				returnList.add(row16);
				
				
				// ----  ADD A BLANK ROW ----
				ArrayList row500 = new ArrayList();
				row500.add(0,"");
				row500.add(1,"");
				returnList.add(row500);
				
				//GROSS AMOUNT
				ArrayList row19 = new ArrayList();	
				row19.add(0,"");
				row19.add(1,new StyledData("TOTAL DUES...", styleVO));
				returnList.add(row19);
				
				// ----  ADD A BLANK ROW ----
				ArrayList row501 = new ArrayList();	
				row501.add(0,"");
				row501.add(1,"");
				returnList.add(row501);


				
				/** -- 		Recoveries 		-- */
				
				SrNo=0;								//RESET. Start again for recoveries
				returnList.add(recoveriesHeader);	//Add recoveries header
				
				// ----  ADD A BLANK ROW ----
				/*ArrayList row502 = new ArrayList();
				row502.add(0,"");
				row502.add(1,"");
				returnList.add(row502);*/
				
				//PAY OF RECOVERY
				ArrayList row20 = new ArrayList();
				row20.add(0,++SrNo);
				row20.add(1,"PAY OF RECOVERY");
				returnList.add(row20);		 
				
				//FESTIVE ADVANCE
				ArrayList row21 = new ArrayList();		
				row21.add(0,++SrNo);
				row21.add(1,"FESTIVE ADVANCE");
				returnList.add(row21);
				
				//FOOD GRAIN ADVANCE
				ArrayList row22 = new ArrayList();	
				row22.add(0,++SrNo);
				row22.add(1,"FOOD GRAIN ADVANCE");
				returnList.add(row22);
				
				
				
				/** -- 		Deductions 		-- */
				
				SrNo=0;								//RESET. Start again for deductions
				returnList.add(deductionHeader);	//Add deduction's header

				
				//INCOME TAX
				ArrayList row30 = new ArrayList();		
				row30.add(0,++SrNo);
				row30.add(1,"INCOME TAX");
				returnList.add(row30);
				
				//SURCHARGE ON I.T.
				ArrayList row31 = new ArrayList();		
				row31.add(0,++SrNo);
				row31.add(1,"SURCHARGE ON I.T.");
				returnList.add(row31);
				
				//P.L.I.
				ArrayList row32 = new ArrayList();	
				row32.add(0,++SrNo);
				row32.add(1,"POSTAL LIFE INSURANCE / B.S.I.");
				returnList.add(row32);
				
				//I.A.S. PROVIDENT FUND
				ArrayList row33 = new ArrayList();		 
				row33.add(0,++SrNo);
				row33.add(1,"I.A.S. PROVIDENT FUND");
				returnList.add(row33);
				
				//G.P.F. CONTRIBUTION
				ArrayList row34 = new ArrayList();		
				row34.add(0,++SrNo);
				row34.add(1,"G.P.F. CONTRIBUTION-I,II,III");
				returnList.add(row34);
				
				//G.P.F. IV
				ArrayList row35 = new ArrayList();	
				row35.add(0,++SrNo);
				row35.add(1,"G.P.F.- IV");
				returnList.add(row35);
				
				//C.P.S.
				ArrayList row36 = new ArrayList();	
				row36.add(0,++SrNo);
				row36.add(1,"C.P.S.");
				returnList.add(row36);
				
				//HOUSE RENT
				ArrayList row37 = new ArrayList();		
				row37.add(0,++SrNo);
				row37.add(1,"HOUSE RENT");
				returnList.add(row37);
				
				//PROFESSIONAL TAX 
				ArrayList row38 = new ArrayList();
				row38.add(0,++SrNo);
				row38.add(1,"PROFESSIONAL TAX");
				returnList.add(row38);
				
				//STATE GOVERNMENT INSURANCE SCHEME - 1979
				ArrayList row39 = new ArrayList();	
				row39.add(0,++SrNo);
				row39.add(1,"S.I.S. - 1979");
				returnList.add(row39);
				
				//STATE GOVERNMENT INSURANCE SCHEME - IF :1981
				ArrayList row40 = new ArrayList();	
				row40.add(0,++SrNo);
				row40.add(1,"S.I.S. - 1981, I.F.");
				returnList.add(row40);
				
				//STATE GOVERNMENT INSURANCE SCHEME - SF:1981
				ArrayList row41 = new ArrayList();		
				row41.add(0,++SrNo);
				row41.add(1,"S.I.S. - 1981, S.F.");
				returnList.add(row41);
				
				//A.I.S. INSURANCE SCHEME - 1980: IF
				ArrayList row42 = new ArrayList();	
				row42.add(0,++SrNo);
				row42.add(1,"A.I.S. SCHEME-1980, I.F.");
				returnList.add(row42);
				
				//A.I.S. INSURANCE SCHEME - 1980: SF
				ArrayList row43 = new ArrayList();		 
				row43.add(0,++SrNo);
				row43.add(1,"A.I.S. SCHEME-1980, S.F.");
				returnList.add(row43);
				
				//H.B.A PRINCIPLE
				ArrayList row44 = new ArrayList();		 
				row44.add(0,++SrNo);
				row44.add(1,"HOUSE BUILDING ADVANCE - PRINCIPLE");
				returnList.add(row44);
				
				//H.B.A INTEREST
				ArrayList row45 = new ArrayList();		 
				row45.add(0,++SrNo);
				row45.add(1,"HOUSE BUILDING ADVANCE - INTEREST");
				returnList.add(row45);
				
				//CAR_SCOOTER_MOPED ADV. PRINCIPLE
				ArrayList row46 = new ArrayList();		 
				row46.add(0,++SrNo);
				row46.add(1,"MCA ADVANCE - PRINCPLE");
				returnList.add(row46);

				//CAR_SCOOTER_MOPED ADV. INTEREST
				ArrayList row47 = new ArrayList();		 
				row47.add(0,++SrNo);
				row47.add(1,"MCA ADVANCE - INTEREST");
				returnList.add(row47);
				
				//G.P.F ADVANCE
				ArrayList row48 = new ArrayList();	
				row48.add(0,++SrNo);
				row48.add(1,"G.P.F ADVANCE");
				returnList.add(row48);
				
				//DA G.P.F
				ArrayList row49 = new ArrayList();	
				row49.add(0,++SrNo);
				row49.add(1,"DA G.P.F.");
				returnList.add(row49);
				
				//DA G.P.F - IV
				ArrayList row50 = new ArrayList();	
				row50.add(0,++SrNo);
				row50.add(1,"DA G.P.F - IV");
				returnList.add(row50);
				
				//FAN ADVANCE
				ArrayList row51 = new ArrayList();		
				row51.add(0,++SrNo);
				row51.add(1,"FAN ADVANCE");
				returnList.add(row51);
				
				//CYCLE AND OTHER CONVEY. ADV.
				ArrayList row52 = new ArrayList();		  
				row52.add(0,++SrNo);
				row52.add(1,"CYCLE AND OTHER CONVEY. ADVANCE");
				returnList.add(row52);
				
				//JEEP RENT
				ArrayList row53 = new ArrayList();		 
				row53.add(0,++SrNo);
				row53.add(1,"JEEP RENT");
				returnList.add(row53);
				
				//L.E.
				ArrayList row54 = new ArrayList();		  
				row54.add(0,++SrNo);
				row54.add(1,"L.E.");
				returnList.add(row54);
				
				//Miscellaneous Recovery
				ArrayList row55 = new ArrayList();		 
				row55.add(0,++SrNo);
				row55.add(1,"MISCELLANEOUS RECOVERY");
				returnList.add(row55);
				
				// ----  ADD A BLANK ROW ----
				ArrayList row503 = new ArrayList();	
				row503.add(0,"");
				row503.add(1,"");
				returnList.add(row503);
				
				// ----  ADD A BLANK ROW ----
				ArrayList row504 = new ArrayList();	
				row504.add(0,"");
				row504.add(1,"");
				returnList.add(row504);
				
				//TOTAL DEDUCTION
				ArrayList row59 = new ArrayList();		 
				row59.add(0,"");
				row59.add(1, new StyledData("TOTAL-DEDUCTION...", styleVO) );
				returnList.add(row59);
				
				//NET AMOUTNT PAYABLE
				ArrayList row70 = new ArrayList();		
				row70.add(0,"");
				row70.add(1, new StyledData("NET AMOUNT PAYABLE...", styleColorVO) );
				returnList.add(row70);
				
				// ----  ADD A BLANK ROW ----
				ArrayList row505 = new ArrayList();
				row505.add(0,"");
				row505.add(1,"");
				returnList.add(row505);
				
						
				/** *****************************************************************************
				 * Report's static part over ( i.e. Sr no, allowance and deduction name )		*		
				 ***************************************************************************** **/

				//Create HQL Query
				StringBuffer hqlQuery = new StringBuffer();

				hqlQuery.append("SELECT ")
						/* ----- DUES/Allowances STARTS ----- */

						.append("sum ( CASE WHEN hrPayPaybill.basic0101 IS NOT NULL AND hrPayPaybill.basic0101 != 0 THEN ((hrPayPaybill.basic0101 + hrPayPaybill.allow0101) - ( CASE WHEN hrEisScaleMst.scaleGradePay != 0 AND hrEisScaleMst.scaleGradePay IS NOT NULL THEN hrEisScaleMst.scaleGradePay ELSE 0 END)) ELSE 0 END ),")
						//.append("sum(hrPayPaybill.basic0101 ),") 	//Pay of officer		---> For GAZ. officers

						.append("sum ( CASE WHEN hrPayPaybill.basic0102 IS NOT NULL AND hrPayPaybill.basic0102 != 0 THEN ((hrPayPaybill.basic0102 + hrPayPaybill.allow0101) - ( CASE WHEN hrEisScaleMst.scaleGradePay != 0 AND hrEisScaleMst.scaleGradePay IS NOT NULL THEN hrEisScaleMst.scaleGradePay ELSE 0 END)) ELSE 0 END) ,")
						//.append("sum(hrPayPaybill.basic0102 ),") 	//Pay of establishment	---> For Non. GAZ officers

						.append("sum(hrEisScaleMst.scaleGradePay),")//Grade Pay
						.append("sum(hrPayPaybill.allow0103 ),")	//Dearness Allowance
						.append("sum(hrPayPaybill.allow0104 ),")	//Other Allowances
						.append("sum(hrPayPaybill.allow0107 ),")	//Medical Allowance
						.append("sum(hrPayPaybill.allow0108 ),")	//Bonus
						.append("sum(hrPayPaybill.allow0110 ),") 	//HRA
						.append("sum(hrPayPaybill.allow0111 ),") 	//CLA
						.append("sum(hrPayPaybill.allow0113 ),") 	//Travelling Allowance
						.append("sum(hrPayPaybill.allow0119 ),")	//Dearness Pay (GAZ)
						.append("sum(hrPayPaybill.allow0120 ),")	//Dearness Pay (Non-GAZ)
						.append("sum(hrPayPaybill.allow1301 ),")	//WA
						.append("sum(hrPayPaybill.allow5006 ),")	//Other charges
						.append("sum(hrPayPaybill.allow0102 ),") 	//Special pay --add to po and pe
						.append("sum(hrPayPaybill.ls ),")			//Leave Salary
						//.append("sum(hrPayPaybill.allow0101 ),")	//Personal Pay -- add to po and pe
						.append("sum(hrPayPaybill.grossAmt 	),") 	//TOTAL DUES/ GROSS Amount
						/* ------ DUES/Allowances ENDS ------ */


						/* ------ RECOVERIES STARTS ------ */
						.append("sum(hrPayPaybill.deduc0101 ),") 	//Pay of Recovery
						.append("sum(hrPayPaybill.adv7057 	),")	//Festive Advance
						.append("sum(hrPayPaybill.adv7058 	),")	//Food Advance
						/* ------ RECOVERIES ENDS ------ */

						
						/* ------ DEDUCTIONS STARTS ------- */
						.append("sum(hrPayPaybill.it 		),")	//Income Tax
						.append("sum(hrPayPaybill.surcharge	),")	//Surcharge of Income Tax
						.append("sum(hrPayPaybill.deduc9530 )+sum(hrPayPaybill.deduc9540 ),")	//Postal life insurance(PLI) /B.L.I.
						.append("sum(hrPayPaybill.deduc9620	),")	//I.A.S. Provident Fund
						.append("sum(hrPayPaybill.deduc9670 ),") 	//GPF; Other then Class-IV
						.append("sum(hrPayPaybill.deduc9531 ),")	//GPF_IV  
						.append("sum(hrPayPaybill.deduc9534 ),")	//New CPS
						.append("sum(hrPayPaybill.deduc9550 ),")	//House Rent
						.append("sum(hrPayPaybill.deduc9570 ),")	//Professional Tax
						.append("sum(hrPayPaybill.deduc9580),")		//SIS-1979 				--> for IAS officers Rs.5
						.append("sum(hrPayPaybill.deduc9581 ),")	//SIS-1981 scheme, SIS_IF
						.append("sum(hrPayPaybill.deduc9582 ),")	//SIS-1981 scheme, SIS_SF
						.append("sum(hrPayPaybill.deduc9583 ),")	//AIS-1980 scheme, AIS_IF
						.append("sum(hrPayPaybill.deduc9584 ),")	//AIS-1980 scheme, AIS_SF
						.append("sum(hrPayPaybill.loan9591	),")	//House Building Advance Principle
						.append("sum(hrPayPaybill.loanInt9591),")	//House Building Advance Interest
						.append("sum(hrPayPaybill.loan9592	),")	//Car/Scooter/Moped Advance Principle
						.append("sum(hrPayPaybill.loanInt9592),")	//Car/Scooter/Moped Advance Interest
						.append("sum(hrPayPaybill.adv9670 	),")	//GPF-Advance
						.append("sum(hrPayPaybill.deduc9999 ),")	//DA-GPF
						.append("sum(hrPayPaybill.deduc9998 ),")	//DA-GPF_IV
						.append("sum(hrPayPaybill.loan9720 	),")	//Fan Advance
						.append("sum(hrPayPaybill.loan9740 	),")	//Cycle/OCA Advance
						.append("sum(hrPayPaybill.deduc9780	),")	//Jeep Rent
						.append("sum(hrPayPaybill.le 		),")	//LE
						.append("sum(hrPayPaybill.deduc9910	),")	//Miscellaneous Recoveries
						.append("sum(hrPayPaybill.totalDed 	),")	//Total Deduction

						/* ------ DEDUCTIONS ENDS -------- */

						.append("sum(hrPayPaybill.netTotal 	),")	//Net Total
						.append("paybillHeadMpg.month ");			//Month
				
				
						if(paybillType.equals(arrears)) {
							logger.info("paybill Type is : Arrears");
							hqlQuery.append(" FROM HrPayArrearPaybill hrPayPaybill,");	//For arrears
						}
						else{
							logger.info("paybill Type is : Regular PayBill");
							hqlQuery.append(" FROM HrPayPaybill hrPayPaybill,");	//For regular pay bill
						}
						
						hqlQuery.append("	PaybillHeadMpg paybillHeadMpg, HrPayBillHeadMpg hrPayBillHeadMpg, 	")
						.append("	HrEisEmpMst hrEisEmpMst, HrEisOtherDtls as hrEisOtherDtls LEFT OUTER JOIN hrEisOtherDtls.hrEisSgdMpg as hrEisSgdMpg LEFT OUTER JOIN hrEisSgdMpg.hrEisScaleMst as hrEisScaleMst , OrgEmpMst orgEmpMst ")
						.append("		WHERE 	hrEisEmpMst.empId = hrEisOtherDtls.hrEisEmpMst.empId AND paybillHeadMpg.cmnLocationMst.locId=" +locId 											 	 )
						.append(" 				AND( ( paybillHeadMpg.month>=3 AND paybillHeadMpg.year="+year+" ) OR (( paybillHeadMpg.month<=2 AND paybillHeadMpg.year="+ ++year+" )) ) ")
												.append("  	AND hrPayBillHeadMpg.billId=" +billNo 										 	 )
												.append("	AND hrPayBillHeadMpg.cmnLocationMst.locId=" +locId							 	 )
												.append("	AND paybillHeadMpg.billNo = hrPayBillHeadMpg.billHeadId 						")
												.append("	AND hrPayPaybill.paybillGrpId = paybillHeadMpg.hrPayPaybill						")
												.append(" 	AND paybillHeadMpg.approveFlag in ("+constantsBundle.getString("created") +", "+constantsBundle.getString("approved")+")  ");

												//if pay bill
												if(paybillType.equals(paybill)) {
													hqlQuery.append("  AND paybillHeadMpg.billTypeId.lookupId=" +paybill );
												}
								
												//if supplementary1
												if(paybillType.equals(supplementary1)) {
													hqlQuery.append("  AND paybillHeadMpg.billTypeId.lookupId=" +supplementary1 );
												}
								
												//if supplementary2
												if(paybillType.equals(supplementary2)) {
													hqlQuery.append("  AND paybillHeadMpg.billTypeId.lookupId=" +supplementary2 );
												}
								
												//if supplementary3
												if(paybillType.equals(supplementary3)) {
													hqlQuery.append("  AND paybillHeadMpg.billTypeId.lookupId=" +supplementary3 );
												}
								
												//if arrears
												if(paybillType.equals(arrears)) {
													hqlQuery.append("  AND paybillHeadMpg.billTypeId.lookupId=" +arrears );
												}
												hqlQuery.append("and orgEmpMst.empId=hrEisEmpMst.orgEmpMst.empId  and orgEmpMst.cmnLanguageMst.langId="+langId )
												.append(" and hrEisEmpMst.empId = hrPayPaybill.hrEisEmpMst.empId ")
												.append(" and paybillHeadMpg.orgGradeMst.gradeId = orgEmpMst.orgGradeMst.gradeId ")
						.append("	GROUP BY paybillHeadMpg.month, paybillHeadMpg.year")
						.append("	ORDER BY paybillHeadMpg.year");


				//Execute query
				Session hibSession = sessionFactory.getCurrentSession();
				Query sqlQuery = hibSession.createQuery(hqlQuery.toString());
				List list = sqlQuery.list(); //query returned data
				Iterator iterator  = list.iterator();

				ReportColumnVO[] reportColumnVO = reportVO.getColumnsToDisplay();

				int j=2;

				//Iterate for 12 months i.e. financial year
				for(int loop=1; loop<=12; loop++)
				{
					Object[] object = null;

					if( iterator.hasNext()){
						object = (Object[]) iterator.next();
					}
					else{

						// Current Reporting framework version forces developer to insert data of all the cols.
						// Filling blank cols.
						object = new String[55]; //exclude sr.no and component name
					}

					//DUES-ALLOWANCES
					String PO 				 = object[0]!= null? object[0].toString():"0"; 		//Pay of Officer
					String PE 				 = object[1]!= null? object[1].toString():"0";		//Pay of Establishment
					String gradePay			 = object[2]!= null? object[2].toString():"0";		//Grade Pay
					String dearnessAllowance = object[3]!= null? object[3].toString():"0";		//DEARNESS ALLOWANCE
					String otherAllowance 	 = object[4]!= null? object[4].toString():"0";		//OTHER ALLOWANCE
					String medicalAllowance  = object[5]!= null? object[5].toString():"0";		//MEDICAL ALLOWANCE
					String bonus 			 = object[6]!= null? object[6].toString():"0";		//BONUS
					String HRA 				 = object[7]!= null? object[7].toString():"0";		//HOUSE RENT ALLOWANCE
					String CLA 				 = object[8]!= null? object[8].toString():"0";		//CITY-CUM-LOCAL ALLOWANCE
					String transportAllowance= object[9]!= null? object[9].toString():"0";		//TRANSPORT ALLOWANCE
					String dearnessPay		 = object[10]!= null? object[10].toString():"0";	//DEARNESS PAY - GAZ
					String dearnessPayNGO	 = object[11]!= null? object[11].toString():"0";	//DEARNESS PAY - Non. GAZ
					String washingAllowance	 = object[12]!= null? object[12].toString():"0";	//WASHING ALLOWANCE
					String otherCharges		 = object[13]!= null? object[13].toString():"0";	//OTHER CHARGES
					String specialPay 		 = object[14]!= null? object[14].toString():"0";	//SPECIAL PAY
					String leaveSalary 		 = object[15]!= null? object[15].toString():"0";	//LEAVE SALARY
					String grossAmount 	 	 = object[16]!= null? object[16].toString():"0";	//GROSS AMOUNT i.e. total dues
 
					//RECOVERIES
					String payOfRecovery	 = object[17]!= null? object[17].toString():"0";	//PAY OF RECOVERY
					String festiveAdvance 	 = object[18]!= null? object[18].toString():"0";	//FESTIVE ADVANCE
					String foodAdvance 		 = object[19]!= null? object[19].toString():"0";	//FOOD GRAIN ADVANCE

					//DEDUCTIONS
					String IT 				 = object[20]!= null? object[20].toString():"0";	//INCOME TAX
					String surcharge		 = object[21]!= null? object[21].toString():"0";	//SURCHARGE ON I.T.
					String PLI 				 = object[22]!= null? object[22].toString():"0";	//P.L.I. + BSI
					String IAS_PF 			 = object[23]!= null? object[23].toString():"0";	//IAS_PF
					String GPF 				 = object[24]!= null? object[24].toString():"0";	//G.P.F. CONTRIBUTION: Other then class-IV
					String GPFIV			 = object[25]!= null? object[25].toString():"0";	//GPFIV
					String CPS			 	 = object[26]!= null? object[26].toString():"0";	//C.P.S.
					String HRR 				 = object[27]!= null? object[27].toString():"0";	//HOUSE RENT
					String PT 				 = object[28]!= null? object[28].toString():"0";	//PROFESSIONAL TAX
					String SIS1979			 = object[29]!= null? object[29].toString():"0";	//STATE GOVERNMENT INSURANCE SCHEME-1979
					String SIS_IF 			 = object[30]!= null? object[30].toString():"0";	//SIS_IF
					String SIS_SF 			 = object[31]!= null? object[31].toString():"0";	//SIS_SF
					String AIS_IF 			 = object[32]!= null? object[32].toString():"0";	//AIS_IF
					String AIS_SF 			 = object[33]!= null? object[33].toString():"0";	//AIS_SF
					String HBA_Principle 	 = object[34]!= null? object[34].toString():"0";	//HBA loan [principle]
					String HBA_Interest	 	 = object[35]!= null? object[35].toString():"0";	//HBA loan [interest]
					String MCA_Principle	 = object[36]!= null? object[36].toString():"0";	//Car.Scooter.Moped loan [principle]
					String MCA_Interest		 = object[37]!= null? object[37].toString():"0";	//Car.Scooter.Moped loan [interest]
					String GPFAdvance 		 = object[38]!= null? object[38].toString():"0";	//G.P.F ADVANCE
					String DAGPF 			 = object[39]!= null? object[39].toString():"0";	//DA G.P.F
					String DAGPFIV 			 = object[40]!= null? object[40].toString():"0";	//DA G.P.F - IV
					String fanAdvance 		 = object[41]!= null? object[41].toString():"0";	//FAN ADVANCE
					String OCA				 = object[42]!= null? object[42].toString():"0";	//CYCLE AND OTHER CONVEY. ADV.
					String jeepRent			 = object[43]!= null? object[43].toString():"0";	//JEEP RENT
					String LE				 = object[44]!= null? object[44].toString():"0";	//L.E.
					String miscRecov		 = object[45]!= null? object[45].toString():"0";	//Miscellaneous Recovery
					String totalDeduction	 = object[46]!= null? object[46].toString():"0";	//TOTAL DEDUCTION
					String netTotal			 = object[47]!= null? object[47].toString():"0";	//NET AMOUTNT PAYABLE
					Double month			 = object[48]!= null? (Double)object[48]:0;	//current paybill Month

					object=null;

					/** ---------   REPORT DATA STARTS -------------- */

					//set report column header
					/*if(month > 0){
						reportColumnVO[ reportColumnVO[j].getParentColumnId() ].setColumnHeader(ReportParameterDAO.getMonthName( month.intValue()));
					}
					else{
						reportColumnVO [ reportColumnVO[j].getParentColumnId() ].setColumnHeader(" ");
					}*/
					
					//Rupees cols
					//allowances
					row1.add(j,PO);	
					row2.add(j,PE);
					row3.add(j,gradePay);
					row4.add(j,dearnessAllowance);						
					row5.add(j,otherAllowance);
					row6.add(j,medicalAllowance);
					row7.add(j,bonus);
					row8.add(j,HRA);
					row9.add(j,CLA);
					row10.add(j,transportAllowance);
					row11.add(j,dearnessPay);
					row12.add(j,dearnessPayNGO);
					row13.add(j,washingAllowance);
					row14.add(j,otherCharges);
					row15.add(j,specialPay);
					row16.add(j,leaveSalary);
					
					row500.add(j,"");						
					row19.add(new StyledData(grossAmount, styleVO));
					row501.add(j,"");
				
					//Recoveries
					//row502.add(j,"");
					row20.add(j,payOfRecovery);
					row21.add(j,festiveAdvance);
					row22.add(j,foodAdvance);
					
					//Deductions
					row30.add(j,IT);
					row31.add(j,surcharge);
					row32.add(j,PLI);
					row33.add(j,IAS_PF); 
					row34.add(j,GPF);
					row35.add(j,GPFIV);
					row36.add(j,CPS);
					row37.add(j,HRR);
					row38.add(j,PT);
					row39.add(j,SIS1979);
					row40.add(j,SIS_IF);
					row41.add(j,SIS_SF);
					row42.add(j,AIS_IF);
					row43.add(j,AIS_SF);
					row44.add(j,HBA_Principle);
					row45.add(j,HBA_Interest);
					row46.add(j,MCA_Principle);
					row47.add(j,MCA_Interest);
					row48.add(j,GPFAdvance);
					row49.add(j,DAGPF);
					row50.add(j,DAGPFIV);
					row51.add(j,fanAdvance);
					row52.add(j,OCA);
					row53.add(j,jeepRent);
					row54.add(j,LE);
					row55.add(j,miscRecov);
					
					row503.add(j,"");
					row504.add(j,"");
					row59.add(j,new StyledData(totalDeduction, styleVO) );
					row70.add(j,new StyledData(netTotal, styleColorVO) );
					row505.add(j,"");
					
					j++;	//next col
					
					//corresponding Paise cols for rupees
					//Allowances
					row1.add(j,"0");
					row2.add(j,"0");
					row3.add(j,"0");
					row4.add(j,"0");
					row5.add(j,"0");
					row6.add(j,"0");
					row7.add(j,"0");
					row8.add(j,"0");
					row9.add(j,"0");
					row10.add(j,"0");
					row11.add(j,"0");
					row12.add(j,"0");
					row13.add(j,"0");
					row14.add(j,"0");
					row15.add(j,"0");
					row16.add(j,"0");
					
					row500.add(j,"");
					row19.add(j,new StyledData("0", styleVO));
					row501.add(j,"");
					
					//Recoveries
					//row502.add(j,"");
					row20.add(j,"0");
		 			row21.add(j,"0");
					row22.add(j,"0");
					
					//Deductions
					row30.add(j,"0");
					row31.add(j,"0");
					row32.add(j,"0");
					row33.add(j,"0");
					row34.add(j,"0");
					row35.add(j,"0");
					row36.add(j,"0");
					row37.add(j,"0");
					row38.add(j,"0");
					row39.add(j,"0");
					row40.add(j,"0");
					row41.add(j,"0");
					row42.add(j,"0");
					row43.add(j,"0");
					row44.add(j,"0");
					row45.add(j,"0");
					row46.add(j,"0");
					row47.add(j,"0");
					row48.add(j,"0");
					row49.add(j,"0");
					row50.add(j,"0");
					row51.add(j,"0");
					row52.add(j,"0");
					row53.add(j,"0");
					row54.add(j,"0");
					row55.add(j,"0");
					
					row503.add(j,"");
					row504.add(j,"");
					row59.add(j, new StyledData("0", styleVO) );
					row70.add(j, new StyledData("0", styleColorVO) );
					row505.add(j,"");

					j++;	//next col for new iteration
					
				}//end iterator : for loop

				/** ---------   REPORT DATA ENDS -------------- */

				logger.info("*********************  Register-B report over  *********************");
			}//end reportCode if


			
			
			
			
			
			
			/* For Register-B MonthWise report */
			if( reportVO.getReportCode().equals("111") || reportVO.getReportCode().equals("2500111") )
			{
				logger.info("*********************  Inside Register-B Monthwise report  *********************");	

				

				/** ************************************************
				 * Report Styles Declaration Section - Begin       *
				 * ************************************************/
				
				//FOR Gross, Deduc TOTAL: BOLD, Black, AND RIGHT ALIGN
				StyleVO[] styleVO = new StyleVO[3];
					//Bold Font
					styleVO[0] = new StyleVO();
					styleVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
					styleVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLD);
					// align
					styleVO[1] = new StyleVO();
					styleVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
					styleVO[1].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					// color
					styleVO[2] = new StyleVO();
					styleVO[2].setStyleId(IReportConstants.STYLE_FONT_COLOR);
					styleVO[2].setStyleValue(IReportConstants.VALUE_FONT_COLOR_BLACK );


				//FOR NetTotal: Bold , Right Align and BLUE Color
				StyleVO[] styleColorVO = new StyleVO[3];
					//Bold Font
					styleColorVO[0] = new StyleVO();
					styleColorVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
					styleColorVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLD);
					// align
					styleColorVO[1] = new StyleVO();
					styleColorVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
					styleColorVO[1].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_RIGHT);
					// color
					styleColorVO[2] = new StyleVO();
					styleColorVO[2].setStyleId(IReportConstants.STYLE_FONT_COLOR);
					styleColorVO[2].setStyleValue(IReportConstants.VALUE_FONT_COLOR_BLUE );

					
				//FOR HEADER: Bold , Left Align  and GREEN Color
				StyleVO[] styleHeaderColorVO = new StyleVO[3];
					//Bold Font
					styleHeaderColorVO[0] = new StyleVO();
					styleHeaderColorVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
					styleHeaderColorVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLD);
					// align
					styleHeaderColorVO[1] = new StyleVO();
					styleHeaderColorVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
					styleHeaderColorVO[1].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_LEFT);
					// color
					styleHeaderColorVO[2] = new StyleVO();
					styleHeaderColorVO[2].setStyleId(IReportConstants.STYLE_FONT_COLOR);
					styleHeaderColorVO[2].setStyleValue(IReportConstants.VALUE_FONT_COLOR_GREEN);
					
					
				//FOR ADDITIONAL HEADER: Bold , Center Align, Large and BLACK Color
				StyleVO[] styleAddlHeaderColorVO = new StyleVO[4];
					//Bold Font
					styleAddlHeaderColorVO[0] = new StyleVO();
					styleAddlHeaderColorVO[0].setStyleId(IReportConstants.STYLE_FONT_WEIGHT);
					styleAddlHeaderColorVO[0].setStyleValue(IReportConstants.VALUE_FONT_WEIGHT_BOLD);
					// align
					styleAddlHeaderColorVO[1] = new StyleVO();
					styleAddlHeaderColorVO[1].setStyleId(IReportConstants.STYLE_FONT_ALIGNMENT);
					styleAddlHeaderColorVO[1].setStyleValue(IReportConstants.VALUE_FONT_ALIGNMENT_CENTER);
					// font size
					styleAddlHeaderColorVO[2] = new StyleVO();
					styleAddlHeaderColorVO[2].setStyleId(IReportConstants.STYLE_FONT_SIZE);
					styleAddlHeaderColorVO[2].setStyleValue(IReportConstants.VALUE_FONT_SIZE_LARGER);
					// color
					styleAddlHeaderColorVO[3] = new StyleVO();
					styleAddlHeaderColorVO[3].setStyleId(IReportConstants.STYLE_FONT_COLOR);
					styleAddlHeaderColorVO[3].setStyleValue(IReportConstants.VALUE_FONT_COLOR_BLACK);

					
				/** *********************************************************
				 *  	 Report Styles Declaration Section - End            *
				 *  *********************************************************/

	
					
				/** *************************************************************************************
				 *  Add the report's static part here ( i.e. Sr no, allowance and deduction name )		*		
				 ***********************************************************************************  **/

					
					
				/** -- set reports additional header -- */
				StyledData  headerStyledData = new StyledData();
							headerStyledData.setStyles(styleAddlHeaderColorVO);
							headerStyledData.setData("Month: " + ReportParameterDAO.getMonthName(selMonth) +" ( Financial Year: "+year+" - "+ (year+1) +" )" );
							reportVO.setAdditionalHeader(headerStyledData);

				
				/** -- Allowances header -- */
				ArrayList allowancesHeader = new ArrayList();	//ALLOWANCES/DUES HEADER
						headerStyledData = new StyledData();
						headerStyledData.setStyles( styleHeaderColorVO );
						headerStyledData.setData("- DUES -");	//give header name
						headerStyledData.setColspan(26);		//merge all 26 columns i.e. Allowance + Rs. + Paise
						allowancesHeader.add(headerStyledData);	//Add "Dues" Header
						
				/** -- Recovery header -- */
				ArrayList recoveriesHeader = new ArrayList();	//RECOVERY HEADER
						headerStyledData = new StyledData();
						headerStyledData.setStyles( styleHeaderColorVO );
						headerStyledData.setData("- RECOVERIES -");	//give header name
						headerStyledData.setColspan(26);		//merge all 26 columns i.e. Recoveries. + Paise
						recoveriesHeader.add(headerStyledData);	//Add "Dues" Header

				/** -- Deduction header -- */						
				ArrayList deductionHeader = new ArrayList();
						headerStyledData = new StyledData();
						headerStyledData.setStyles( styleHeaderColorVO );
						headerStyledData.setData("- DEDUCTIONS -");	//give header name
						headerStyledData.setColspan(26);			//merge all 26 columns i.e. Deductions + Rs. + Paise
						deductionHeader.add(headerStyledData);		//Add "DEDUCTIONS" Header
				
				

				
			

				/** -- Allowance/Dues -- */
				
				int SrNo=0; //For Sr.No.
				
				returnList.add(allowancesHeader);	//Add allowances/dues header
				
				//PAY OF OFFICER
				ArrayList row1 = new ArrayList();		 
					row1.add(0,++SrNo);	
					row1.add(1,"PAY OF OFFICER");
				returnList.add(row1);
				
				//PAY OF ESTABLISHMENT
				ArrayList row2 = new ArrayList();								
				row2.add(0,++SrNo);
				row2.add(1,"PAY OF ESTABLISHMENT");
				returnList.add(row2);
				
				//GRADE PAY
				ArrayList row3 = new ArrayList();								
				row3.add(0,++SrNo);
				row3.add(1,"GRADE PAY");
				returnList.add(row3);
				
				//DEARNESS ALLOWANCE
				ArrayList row4 = new ArrayList(); 						
				row4.add(0,++SrNo);
				row4.add(1,"DEARNESS ALLOWANCE");
				returnList.add(row4); 
				
				//OTHER ALLOWANCE
				ArrayList row5 = new ArrayList();		
				row5.add(0,++SrNo);
				row5.add(1,"OTHER ALLOWANCE");
				returnList.add(row5); 
				
				ArrayList row6 = new ArrayList();	
				row6.add(0,++SrNo);
				row6.add(1,"DEARNESS PAY");
				returnList.add(row6);
				
				/*//DEARNESS PAY - GAZETTED
				ArrayList row6 = new ArrayList();	
				row6.add(0,++SrNo);
				row6.add(1,"DEARNESS PAY - GAZETTED");
				returnList.add(row6);
				
				//DEARNESS PAY - NON-GAZETTED
				ArrayList row7 = new ArrayList();	
				row7.add(0,++SrNo);
				row7.add(1,"DEARNESS PAY - NON-GAZETTED");
				returnList.add(row7);*/
				
				//MEDICAL ALLOWANCE
				ArrayList row7 = new ArrayList();	
				row7.add(0,++SrNo);
				row7.add(1,"MEDICAL ALLOWANCE");
				returnList.add(row7);
				
				//BONUS
				ArrayList row8 = new ArrayList();	
				row8.add(0,++SrNo);
				row8.add(1,"BONUS");
				returnList.add(row8);
				
				//HOUSE RENT ALLOWANCE
				ArrayList row9 = new ArrayList();	
				row9.add(0,++SrNo);
				row9.add(1,"HOUSE RENT ALLOWANCE");
				returnList.add(row9);
				
				//CITY-CUM-LOCAL ALLOWANCE
				ArrayList row10 = new ArrayList();	
				row10.add(0,++SrNo);
				row10.add(1,"CITY-CUM-LOCAL ALLOWANCE");
				returnList.add(row10);
				
				//TRANSPORT ALLOWANCE
				ArrayList row11 = new ArrayList();	
				row11.add(0,++SrNo);
				row11.add(1,"TRANSPORT ALLOWANCE");
				returnList.add(row11);
				
				
				//WASHING ALLOWANCE
				ArrayList row12 = new ArrayList();	
				row12.add(0,++SrNo);
				row12.add(1,"WASHING ALLOWANCE");
				returnList.add(row12);
				
				//OTHER CHARGES
				ArrayList row13 = new ArrayList();	
				row13.add(0,++SrNo);
				row13.add(1,"OTHER CHARGES");
				returnList.add(row13);
				
				/*//SPECIAL PAY
				ArrayList row15 = new ArrayList();	
				row15.add(0,++SrNo);
				row15.add(1,"SPECIAL PAY");
				returnList.add(row15);*/
				
				//LEAVE SALARY
				ArrayList row14 = new ArrayList();		
				row14.add(0,++SrNo);
				row14.add(1,"LEAVE SALARY");
				returnList.add(row14);
				
				
				// ----  ADD A BLANK ROW ----
				ArrayList row500 = new ArrayList();
				row500.add(0,"");
				row500.add(1,"");
				returnList.add(row500);
				
				//GROSS AMOUNT
				ArrayList row19 = new ArrayList();	
				row19.add(0,"");
				row19.add(1,new StyledData("TOTAL DUES...", styleVO));
				returnList.add(row19);
				
				// ----  ADD A BLANK ROW ----
				ArrayList row501 = new ArrayList();	
				row501.add(0,"");
				row501.add(1,"");
				returnList.add(row501);


				
				/** -- 		Recoveries 		-- */
				
				SrNo=0;								//RESET. Start again for recoveries
				returnList.add(recoveriesHeader);	//Add recoveries header
				
				// ----  ADD A BLANK ROW ----
				/*ArrayList row502 = new ArrayList();
				row502.add(0,"");
				row502.add(1,"");
				returnList.add(row502);*/
				
				//PAY OF RECOVERY
				ArrayList row20 = new ArrayList();
				row20.add(0,++SrNo);
				row20.add(1,"PAY OF RECOVERY");
				returnList.add(row20);		 
				
				//FESTIVE ADVANCE
				ArrayList row21 = new ArrayList();		
				row21.add(0,++SrNo);
				row21.add(1,"FESTIVE ADVANCE");
				returnList.add(row21);
				
				//FOOD GRAIN ADVANCE
				ArrayList row22 = new ArrayList();	
				row22.add(0,++SrNo);
				row22.add(1,"FOOD GRAIN ADVANCE");
				returnList.add(row22);
				
				
				
				/** -- 		Deductions 		-- */
				
				SrNo=0;								//RESET. Start again for deductions
				returnList.add(deductionHeader);	//Add deduction's header

				
				//INCOME TAX
				ArrayList row30 = new ArrayList();		
				row30.add(0,++SrNo);
				row30.add(1,"INCOME TAX");
				returnList.add(row30);
				
				//SURCHARGE ON I.T.
				ArrayList row31 = new ArrayList();		
				row31.add(0,++SrNo);
				row31.add(1,"SURCHARGE ON I.T.");
				returnList.add(row31);
				
				//P.L.I.
				ArrayList row32 = new ArrayList();	
				row32.add(0,++SrNo);
				row32.add(1,"POSTAL LIFE INSURANCE / B.S.I.");
				returnList.add(row32);
				
				//I.A.S. PROVIDENT FUND
				ArrayList row33 = new ArrayList();		 
				row33.add(0,++SrNo);
				row33.add(1,"I.A.S. PROVIDENT FUND");
				returnList.add(row33);
				
				//G.P.F. CONTRIBUTION
				ArrayList row34 = new ArrayList();		
				row34.add(0,++SrNo);
				row34.add(1,"G.P.F. CONTRIBUTION-I,II,III");
				returnList.add(row34);
				
				//G.P.F. IV
				ArrayList row35 = new ArrayList();	
				row35.add(0,++SrNo);
				row35.add(1,"G.P.F.- IV");
				returnList.add(row35);
				
				//C.P.S.
				ArrayList row36 = new ArrayList();	
				row36.add(0,++SrNo);
				row36.add(1,"C.P.S.");
				returnList.add(row36);
				
				//HOUSE RENT
				ArrayList row37 = new ArrayList();		
				row37.add(0,++SrNo);
				row37.add(1,"HOUSE RENT");
				returnList.add(row37);
				
				//PROFESSIONAL TAX 
				ArrayList row38 = new ArrayList();
				row38.add(0,++SrNo);
				row38.add(1,"PROFESSIONAL TAX");
				returnList.add(row38);
				
				//STATE GOVERNMENT INSURANCE SCHEME - 1979
				ArrayList row39 = new ArrayList();	
				row39.add(0,++SrNo);
				row39.add(1,"S.I.S. - 1979");
				returnList.add(row39);
				
				//STATE GOVERNMENT INSURANCE SCHEME - IF :1981
				ArrayList row40 = new ArrayList();	
				row40.add(0,++SrNo);
				row40.add(1,"S.I.S. - 1981, I.F.");
				returnList.add(row40);
				
				//STATE GOVERNMENT INSURANCE SCHEME - SF:1981
				ArrayList row41 = new ArrayList();		
				row41.add(0,++SrNo);
				row41.add(1,"S.I.S. - 1981, S.F.");
				returnList.add(row41);
				
				//A.I.S. INSURANCE SCHEME - 1980: IF
				ArrayList row42 = new ArrayList();	
				row42.add(0,++SrNo);
				row42.add(1,"A.I.S. SCHEME-1980, I.F.");
				returnList.add(row42);
				
				//A.I.S. INSURANCE SCHEME - 1980: SF
				ArrayList row43 = new ArrayList();		 
				row43.add(0,++SrNo);
				row43.add(1,"A.I.S. SCHEME-1980, S.F.");
				returnList.add(row43);
				
				//H.B.A PRINCIPLE
				ArrayList row44 = new ArrayList();		 
				row44.add(0,++SrNo);
				row44.add(1,"HOUSE BUILDING ADVANCE - PRINCIPLE");
				returnList.add(row44);
				
				//H.B.A INTEREST
				ArrayList row45 = new ArrayList();		 
				row45.add(0,++SrNo);
				row45.add(1,"HOUSE BUILDING ADVANCE - INTEREST");
				returnList.add(row45);
				
				//CAR_SCOOTER_MOPED ADV. PRINCIPLE
				ArrayList row46 = new ArrayList();		 
				row46.add(0,++SrNo);
				row46.add(1,"MCA ADVANCE - PRINCPLE");
				returnList.add(row46);

				//CAR_SCOOTER_MOPED ADV. INTEREST
				ArrayList row47 = new ArrayList();		 
				row47.add(0,++SrNo);
				row47.add(1,"MCA ADVANCE - INTEREST");
				returnList.add(row47);
				
				//G.P.F ADVANCE
				ArrayList row48 = new ArrayList();	
				row48.add(0,++SrNo);
				row48.add(1,"G.P.F ADVANCE");
				returnList.add(row48);
				
				//DA G.P.F
				ArrayList row49 = new ArrayList();	
				row49.add(0,++SrNo);
				row49.add(1,"DA G.P.F.");
				returnList.add(row49);
				
				//DA G.P.F - IV
				ArrayList row50 = new ArrayList();	
				row50.add(0,++SrNo);
				row50.add(1,"DA G.P.F - IV");
				returnList.add(row50);
				
				//FAN ADVANCE
				ArrayList row51 = new ArrayList();		
				row51.add(0,++SrNo);
				row51.add(1,"FAN ADVANCE");
				returnList.add(row51);
				
				//CYCLE AND OTHER CONVEY. ADV.
				ArrayList row52 = new ArrayList();		  
				row52.add(0,++SrNo);
				row52.add(1,"CYCLE AND OTHER CONVEY. ADVANCE");
				returnList.add(row52);
				
				//JEEP RENT
				ArrayList row53 = new ArrayList();		 
				row53.add(0,++SrNo);
				row53.add(1,"JEEP RENT");
				returnList.add(row53);
				
				//L.E.
				ArrayList row54 = new ArrayList();		  
				row54.add(0,++SrNo);
				row54.add(1,"L.E.");
				returnList.add(row54);
				
				//Miscellaneous Recovery
				ArrayList row55 = new ArrayList();		 
				row55.add(0,++SrNo);
				row55.add(1,"MISCELLANEOUS RECOVERY");
				returnList.add(row55);
				
				// ----  ADD A BLANK ROW ----
				ArrayList row503 = new ArrayList();	
				row503.add(0,"");
				row503.add(1,"");
				returnList.add(row503);
				
				// ----  ADD A BLANK ROW ----
				ArrayList row504 = new ArrayList();	
				row504.add(0,"");
				row504.add(1,"");
				returnList.add(row504);
				
				//TOTAL DEDUCTION
				ArrayList row59 = new ArrayList();		 
				row59.add(0,"");
				row59.add(1, new StyledData("TOTAL-DEDUCTION...", styleVO) );
				returnList.add(row59);
				
				//NET AMOUTNT PAYABLE
				ArrayList row70 = new ArrayList();		
				row70.add(0,"");
				row70.add(1, new StyledData("NET AMOUNT PAYABLE...", styleColorVO) );
				returnList.add(row70);
				
				// ----  ADD A BLANK ROW ----
				ArrayList row505 = new ArrayList();
				row505.add(0,"");
				row505.add(1,"");
				returnList.add(row505);
				
						
				/** *****************************************************************************
				 * Report's static part over ( i.e. Sr no, allowance and deduction name )		*		
				 ***************************************************************************** **/

			
				
				
				
				
				//Create HQL Query
				StringBuffer hqlQuery = new StringBuffer();

				hqlQuery.append("SELECT ")
						/* ----- DUES/Allowances STARTS ----- */

						.append("sum ( CASE WHEN hrPayPaybill.basic0101 IS NOT NULL AND hrPayPaybill.basic0101 != 0 THEN ((hrPayPaybill.basic0101 + hrPayPaybill.allow0101) - ( CASE WHEN hrEisScaleMst.scaleGradePay != 0 AND hrEisScaleMst.scaleGradePay IS NOT NULL THEN hrEisScaleMst.scaleGradePay ELSE 0 END)) ELSE 0 END ),")
						//.append("sum(hrPayPaybill.basic0101 ),") 	//Pay of officer		---> For GAZ. officers

						.append("sum ( CASE WHEN hrPayPaybill.basic0102 IS NOT NULL AND hrPayPaybill.basic0102 != 0 THEN ((hrPayPaybill.basic0102 + hrPayPaybill.allow0101 + hrPayPaybill.allow0102) - ( CASE WHEN hrEisScaleMst.scaleGradePay != 0 AND hrEisScaleMst.scaleGradePay IS NOT NULL THEN hrEisScaleMst.scaleGradePay ELSE 0 END)) ELSE 0 END) ,")
						//.append("sum(hrPayPaybill.basic0102 ),") 	//Pay of establishment	---> For Non. GAZ officers

						.append("sum(hrEisScaleMst.scaleGradePay),")//Grade Pay
						.append("sum(hrPayPaybill.allow0103 ),")	//Dearness Allowance
						.append("sum(hrPayPaybill.allow0104 ),")	//Other Allowances
						.append("sum(hrPayPaybill.allow0107 ),")	//Medical Allowance
						.append("sum(hrPayPaybill.allow0108 ),")	//Bonus
						.append("sum(hrPayPaybill.allow0110 ),") 	//HRA
						.append("sum(hrPayPaybill.allow0111 ),") 	//CLA
						.append("sum(hrPayPaybill.allow0113 ),") 	//Travelling Allowance
						
						.append("sum(hrPayPaybill.allow0119 )+sum(hrPayPaybill.allow0120 ),")
						
						//.append("sum(hrPayPaybill.allow0119 ),")	//Dearness Pay (GAZ)
						//.append("sum(hrPayPaybill.allow0120 ),")	//Dearness Pay (Non-GAZ)
						
						.append("sum(hrPayPaybill.allow1301 ),")	//WA
						.append("sum(hrPayPaybill.allow5006 ),")	//Other charges
						//.append("sum(hrPayPaybill.allow0102 ),") 	//Special pay --add to po and pe
						.append("sum(hrPayPaybill.ls ),")			//Leave Salary
						//.append("sum(hrPayPaybill.allow0101 ),")	//Personal Pay -- add to po and pe
						.append("sum(hrPayPaybill.grossAmt 	),") 	//TOTAL DUES/ GROSS Amount
						/* ------ DUES/Allowances ENDS ------ */


						/* ------ RECOVERIES STARTS ------ */
						.append("sum(hrPayPaybill.deduc0101 ),") 	//Pay of Recovery
						.append("sum(hrPayPaybill.adv7057 	),")	//Festive Advance
						.append("sum(hrPayPaybill.adv7058 	),")	//Food Advance
						/* ------ RECOVERIES ENDS ------ */

						
						/* ------ DEDUCTIONS STARTS ------- */
						.append("sum(hrPayPaybill.it 		),")	//Income Tax
						.append("sum(hrPayPaybill.surcharge	),")	//Surcharge of Income Tax
						.append("sum(hrPayPaybill.deduc9530 )+sum(hrPayPaybill.deduc9540 ),")	//Postal life insurance(PLI) /B.L.I.
						.append("sum(hrPayPaybill.deduc9620	),")	//I.A.S. Provident Fund
						.append("sum(hrPayPaybill.deduc9670 ),") 	//GPF; Other then Class-IV
						.append("sum(hrPayPaybill.deduc9531 ),")	//GPF_IV  
						.append("sum(hrPayPaybill.deduc9534 ),")	//New CPS
						.append("sum(hrPayPaybill.deduc9550 ),")	//House Rent
						.append("sum(hrPayPaybill.deduc9570 ),")	//Professional Tax
						.append("sum(hrPayPaybill.deduc9580),")		//SIS-1979 				--> for IAS officers Rs.5
						.append("sum(hrPayPaybill.deduc9581 ),")	//SIS-1981 scheme, SIS_IF
						.append("sum(hrPayPaybill.deduc9582 ),")	//SIS-1981 scheme, SIS_SF
						.append("sum(hrPayPaybill.deduc9583 ),")	//AIS-1980 scheme, AIS_IF
						.append("sum(hrPayPaybill.deduc9584 ),")	//AIS-1980 scheme, AIS_SF
						.append("sum(hrPayPaybill.loan9591	),")	//House Building Advance Principle
						.append("sum(hrPayPaybill.loanInt9591),")	//House Building Advance Interest
						.append("sum(hrPayPaybill.loan9592	),")	//Car/Scooter/Moped Advance Principle
						.append("sum(hrPayPaybill.loanInt9592),")	//Car/Scooter/Moped Advance Interest
						.append("sum(hrPayPaybill.adv9670 	),")	//GPF-Advance
						.append("sum(hrPayPaybill.deduc9999 ),")	//DA-GPF
						.append("sum(hrPayPaybill.deduc9998 ),")	//DA-GPF_IV
						.append("sum(hrPayPaybill.loan9720 	),")	//Fan Advance
						.append("sum(hrPayPaybill.loan9740 	),")	//Cycle/OCA Advance
						.append("sum(hrPayPaybill.deduc9780	),")	//Jeep Rent
						.append("sum(hrPayPaybill.le 		),")	//LE
						.append("sum(hrPayPaybill.deduc9910	),")	//Miscellaneous Recoveries
						.append("sum(hrPayPaybill.totalDed 	),")	//Total Deduction
						
						/* ------ DEDUCTIONS ENDS -------- */

						.append("sum(hrPayPaybill.netTotal 	), ")	//Net Total
						.append("hrPayBillHeadMpg.billId ");
						//.append(" , paybillHeadMpg.month ");			//Month
				
				
						if(paybillType.equals(arrears)) {
							logger.info("paybill Type is : Arrears");
							hqlQuery.append(" FROM HrPayArrearPaybill hrPayPaybill,");	//For arrears
						}
						else{
							logger.info("paybill Type is : Regular PayBill");
							hqlQuery.append(" FROM HrPayPaybill hrPayPaybill,");	//For regular pay bill
						}
						
						hqlQuery.append("	PaybillHeadMpg paybillHeadMpg, HrPayBillHeadMpg hrPayBillHeadMpg, 	")
						.append("	HrEisEmpMst hrEisEmpMst, HrEisOtherDtls as hrEisOtherDtls LEFT OUTER JOIN hrEisOtherDtls.hrEisSgdMpg as hrEisSgdMpg LEFT OUTER JOIN hrEisSgdMpg.hrEisScaleMst as hrEisScaleMst , OrgEmpMst orgEmpMst ")
						.append("		WHERE 	hrEisEmpMst.empId = hrEisOtherDtls.hrEisEmpMst.empId AND paybillHeadMpg.cmnLocationMst.locId=" +locId 											 	 )
						.append(" 				AND paybillHeadMpg.month =" +selMonth +" AND paybillHeadMpg.year="+ year )
												//.append("  	AND hrPayBillHeadMpg.billId=" +billNo 										 	 )
												.append("	AND hrPayBillHeadMpg.cmnLocationMst.locId=" +locId							 	 )
												.append("	AND paybillHeadMpg.billNo = hrPayBillHeadMpg.billHeadId 						")
												.append("	AND hrPayPaybill.paybillGrpId = paybillHeadMpg.hrPayPaybill						")
												.append(" 	AND paybillHeadMpg.approveFlag in ("+constantsBundle.getString("created") +", "+constantsBundle.getString("approved")+")  ");

												//if pay bill
												if(paybillType.equals(paybill)) {
													hqlQuery.append("  AND paybillHeadMpg.billTypeId.lookupId=" +paybill );
												}
								
												//if supplementary1
												if(paybillType.equals(supplementary1)) {
													hqlQuery.append("  AND paybillHeadMpg.billTypeId.lookupId=" +supplementary1 );
												}
								
												//if supplementary2
												if(paybillType.equals(supplementary2)) {
													hqlQuery.append("  AND paybillHeadMpg.billTypeId.lookupId=" +supplementary2 );
												}
								
												//if supplementary3
												if(paybillType.equals(supplementary3)) {
													hqlQuery.append("  AND paybillHeadMpg.billTypeId.lookupId=" +supplementary3 );
												}
								
												//if arrears
												if(paybillType.equals(arrears)) {
													hqlQuery.append("  AND paybillHeadMpg.billTypeId.lookupId=" +arrears );
												}
												hqlQuery.append("and orgEmpMst.empId=hrEisEmpMst.orgEmpMst.empId  and orgEmpMst.cmnLanguageMst.langId="+langId )
												.append(" and hrEisEmpMst.empId = hrPayPaybill.hrEisEmpMst.empId ")
												.append(" and paybillHeadMpg.orgGradeMst.gradeId = orgEmpMst.orgGradeMst.gradeId ")
						.append("	GROUP BY hrPayBillHeadMpg.billId")
						.append("	ORDER BY hrPayBillHeadMpg.billId");



				//Execute query
				Session hibSession = sessionFactory.getCurrentSession();
				Query sqlQuery = hibSession.createQuery(hqlQuery.toString());
				List list = sqlQuery.list(); //query returned data
				Iterator iterator  = list.iterator();


				ReportColumnVO[] colVO = reportVO.getColumnsToDisplay();
				
				int j=2;

				//Iterate for 24 Bills
				for(int loop=1; loop<=24; loop++)
				{
					Object[] object = null;

					if( iterator.hasNext()){
						object = (Object[]) iterator.next();
					}
					else{

						// Current Reporting framework version forces developer to insert data of all the cols.
						// Filling blank cols.
						object = new String[55]; //exclude sr.no and component name (allow, recov and deduc )
					}

					//DUES-ALLOWANCES
					String PO 				 = object[0]!= null? object[0].toString():"0"; 		//Pay of Officer
					String PE 				 = object[1]!= null? object[1].toString():"0";		//Pay of Establishment
					String gradePay			 = object[2]!= null? object[2].toString():"0";		//Grade Pay
					String dearnessAllowance = object[3]!= null? object[3].toString():"0";		//DEARNESS ALLOWANCE
					String otherAllowance 	 = object[4]!= null? object[4].toString():"0";		//OTHER ALLOWANCE
					String medicalAllowance  = object[5]!= null? object[5].toString():"0";		//MEDICAL ALLOWANCE
					String bonus 			 = object[6]!= null? object[6].toString():"0";		//BONUS
					String HRA 				 = object[7]!= null? object[7].toString():"0";		//HOUSE RENT ALLOWANCE
					String CLA 				 = object[8]!= null? object[8].toString():"0";		//CITY-CUM-LOCAL ALLOWANCE
					String transportAllowance= object[9]!= null? object[9].toString():"0";		//TRANSPORT ALLOWANCE
					String dearnessPay		 = object[10]!= null? object[10].toString():"0";
					
					//String dearnessPay		 = object[10]!= null? object[10].toString():"0";	//DEARNESS PAY - GAZ
					//String dearnessPayNGO	 = object[11]!= null? object[11].toString():"0";	//DEARNESS PAY - Non. GAZ
					
					String washingAllowance	 = object[11]!= null? object[11].toString():"0";	//WASHING ALLOWANCE
					String otherCharges		 = object[12]!= null? object[12].toString():"0";	//OTHER CHARGES
					//String specialPay 		 = object[14]!= null? object[14].toString():"0";	//SPECIAL PAY
					String leaveSalary 		 = object[13]!= null? object[13].toString():"0";	//LEAVE SALARY
					String grossAmount 	 	 = object[14]!= null? object[14].toString():"0";	//GROSS AMOUNT i.e. total dues
 
					//RECOVERIES
					String payOfRecovery	 = object[15]!= null? object[15].toString():"0";	//PAY OF RECOVERY
					String festiveAdvance 	 = object[16]!= null? object[16].toString():"0";	//FESTIVE ADVANCE
					String foodAdvance 		 = object[17]!= null? object[17].toString():"0";	//FOOD GRAIN ADVANCE

					//DEDUCTIONS
					String IT 				 = object[18]!= null? object[18].toString():"0";	//INCOME TAX
					String surcharge		 = object[19]!= null? object[19].toString():"0";	//SURCHARGE ON I.T.
					String PLI 				 = object[20]!= null? object[20].toString():"0";	//P.L.I. + BSI
					String IAS_PF 			 = object[21]!= null? object[21].toString():"0";	//IAS_PF
					String GPF 				 = object[22]!= null? object[22].toString():"0";	//G.P.F. CONTRIBUTION: Other then class-IV
					String GPFIV			 = object[23]!= null? object[23].toString():"0";	//GPFIV
					String CPS			 	 = object[24]!= null? object[24].toString():"0";	//C.P.S.
					String HRR 				 = object[25]!= null? object[25].toString():"0";	//HOUSE RENT
					String PT 				 = object[26]!= null? object[26].toString():"0";	//PROFESSIONAL TAX
					String SIS1979			 = object[27]!= null? object[27].toString():"0";	//STATE GOVERNMENT INSURANCE SCHEME-1979
					String SIS_IF 			 = object[28]!= null? object[28].toString():"0";	//SIS_IF
					String SIS_SF 			 = object[29]!= null? object[29].toString():"0";	//SIS_SF
					String AIS_IF 			 = object[30]!= null? object[30].toString():"0";	//AIS_IF
					String AIS_SF 			 = object[31]!= null? object[31].toString():"0";	//AIS_SF
					String HBA_Principle 	 = object[32]!= null? object[32].toString():"0";	//HBA loan [principle]
					String HBA_Interest	 	 = object[33]!= null? object[33].toString():"0";	//HBA loan [interest]
					String MCA_Principle	 = object[34]!= null? object[34].toString():"0";	//Car.Scooter.Moped loan [principle]
					String MCA_Interest		 = object[35]!= null? object[35].toString():"0";	//Car.Scooter.Moped loan [interest]
					String GPFAdvance 		 = object[36]!= null? object[36].toString():"0";	//G.P.F ADVANCE
					String DAGPF 			 = object[37]!= null? object[37].toString():"0";	//DA G.P.F
					String DAGPFIV 			 = object[38]!= null? object[38].toString():"0";	//DA G.P.F - IV
					String fanAdvance 		 = object[39]!= null? object[39].toString():"0";	//FAN ADVANCE
					String OCA				 = object[40]!= null? object[40].toString():"0";	//CYCLE AND OTHER CONVEY. ADV.
					String jeepRent			 = object[41]!= null? object[41].toString():"0";	//JEEP RENT
					String LE				 = object[42]!= null? object[42].toString():"0";	//L.E.
					String miscRecov		 = object[43]!= null? object[43].toString():"0";	//Miscellaneous Recovery
					String totalDeduction	 = object[44]!= null? object[44].toString():"0";	//TOTAL DEDUCTION
					String netTotal			 = object[45]!= null? object[45].toString():"0";	//NET AMOUTNT PAYABLE
					String billNos			 = object[46]!= null? object[46].toString():" ";	//Bill No
					//double month			 = object[48]!= null? (Double)object[48]:0;	//current paybill Month

					if( !billNos.equals("") ){
						colVO[j].setColumnHeader("Bill: " +'\n'+billNos);
					}

					object=null;


					/** ---------   REPORT DATA STARTS -------------- */

					//Rupees cols
					//allowances
					row1.add(j,PO);	
					row2.add(j,PE);
					row3.add(j,gradePay);
					row4.add(j,dearnessAllowance);						
					row5.add(j,otherAllowance);
					row6.add(j,dearnessPay);
					//row7.add(j,dearnessPayNGO);
					
					row7.add(j,medicalAllowance);
					row8.add(j,bonus);
					row9.add(j,HRA);
					row10.add(j,CLA);
					row11.add(j,transportAllowance);
					//row11.add(j,dearnessPay);
					//row12.add(j,dearnessPayNGO);
					row12.add(j,washingAllowance);
					row13.add(j,otherCharges);
					//row15.add(j,specialPay);
					row14.add(j,leaveSalary);
					
					row500.add(j,"");						
					row19.add(new StyledData(grossAmount, styleVO));
					row501.add(j,"");
				
					//Recoveries
					//row502.add(j,"");
					row20.add(j,payOfRecovery);
					row21.add(j,festiveAdvance);
					row22.add(j,foodAdvance);
					
					//Deductions
					row30.add(j,IT);
					row31.add(j,surcharge);
					row32.add(j,PLI);
					row33.add(j,IAS_PF); 
					row34.add(j,GPF);
					row35.add(j,GPFIV);
					row36.add(j,CPS);
					row37.add(j,HRR);
					row38.add(j,PT);
					row39.add(j,SIS1979);
					row40.add(j,SIS_IF);
					row41.add(j,SIS_SF);
					row42.add(j,AIS_IF);
					row43.add(j,AIS_SF);
					row44.add(j,HBA_Principle);
					row45.add(j,HBA_Interest);
					row46.add(j,MCA_Principle);
					row47.add(j,MCA_Interest);
					row48.add(j,GPFAdvance);
					row49.add(j,DAGPF);
					row50.add(j,DAGPFIV);
					row51.add(j,fanAdvance);
					row52.add(j,OCA);
					row53.add(j,jeepRent);
					row54.add(j,LE);
					row55.add(j,miscRecov);
					
					row503.add(j,"");
					row504.add(j,"");
					row59.add(j,new StyledData(totalDeduction, styleVO) );
					row70.add(j,new StyledData(netTotal, styleColorVO) );
					row505.add(j,"");
					
					j++;	//next col
					
					
				}//end iterator : for loop

				/** ---------   REPORT DATA ENDS -------------- */

				logger.info("*********************  Register-B report over  *********************");
			}//end reportCode if


		}catch(Exception e){
			logger.error("Error is: "+ e.getMessage());
			logger.error("ERROR in BRegisterReportDAO: "+e.getMessage(),e);
		}

/*		
		for(int k1=0;k1<returnList.size();k1++)
		{
		}*/

		return returnList;
	}//end method: findReportData


	@Override
	public ReportVO getUserReportVO( ReportVO report, Object criteria )
							throws ReportException
	{

		logger.info ("***********Inside User Report VO *********************");
		Hashtable sessionKeys = (Hashtable) ( (Hashtable) criteria ).get( IReportConstants.SESSION_KEYS );
		
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		String date = fmt.format(today);
		
		fmt = new SimpleDateFormat("yyyy");
		String yr = fmt.format(today);
		
		fmt = new SimpleDateFormat("MM");
		String month = fmt.format(today);

		Map requestKeys 	= (Map)((Map) criteria).get(IReportConstants.REQUEST_ATTRIBUTES);
		Map serviceMap 		= (Map)requestKeys.get("serviceMap");						
		Map baseLoginMap 	= (Map)serviceMap.get("baseLoginMap");
		
		CmnLocationMst locationVO =(CmnLocationMst)baseLoginMap.get("locationVO");
		long locationId = locationVO.getLocId();
		

		if(month.charAt(0)=='0')
		{
			month = month.substring(1,2);
		}

		//For default parameters
		if(  report.getReportCode().equals("109") || report.getReportCode().equals("2500109") ||  report.getReportCode().equals("111") || report.getReportCode().equals("2500111") )
		{   
			logger.info("Setting default report paramaters");
			//set default to current year
			report.setParameterValue("year",yr);
			//set default to current month
			report.setParameterValue("month",month);
			//set department as dept of current logged in user
			report.setParameterValue("department",locationId+"");
			//set default billType to paybill
			report.setParameterValue("billType",constantsBundle.getString("paybillTypeId"));
		}

		return report;
	}

	
	final String checkIfNull(Object lStra)
	{
		String lStrb="";
		if( lStra != null )
		{
			lStrb = (((String)lStra).trim());
		}
	 return lStrb;
	}//end method:checkIfNull
	
	
	public ArrayList getReportType(String lstrLangId, String lstrLocId)
	{
		ArrayList lCmbList = new ArrayList();	
		DefaultComboItem ci1 = new DefaultComboItem( "",""); 

			ci1 = new DefaultComboItem( "BILLWISE","Bill Wise"); 
				lCmbList.add(ci1);          
			ci1 = new DefaultComboItem( "MONTHWISE","Month Wise"); 
				lCmbList.add(ci1);

	    return lCmbList;	       
	  }//end method:getReportType



}//end class