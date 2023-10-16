package com.tcs.sgv.eis.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.StringTokenizer;

// Incepted  by abhishek11 b and Hemant6 k. Dated 22/07/2010 2000Hrs
/*
 This utility will give you the list of months and year between two given dates.
 This list will be like month, year paired

 eg. B/W 01/12/2009 to 28/02/2010
 O/P : [12,2009,01,2010,02,2010]

 */

public class getDiffDates {
	Log logger = LogFactory.getLog( getClass() );
	public static List getDiffDatesLst(Date Date1, Date Date2)
	{
		
		List Monthlist = new ArrayList();
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdfParse = new SimpleDateFormat("yyyy-MM-dd");
			//System.out.println(">>>>>>>>>>>  start date " + Date1);
			//System.out.println(">>>>>>>>>>>  End date   " + Date2);
			String date1 = sdf.format(Date1);
			String date2 = sdf.format(Date2);
			int Month1 =0;
			int Month2 =0;
			int Year1 =0;
			int Year2 =0;

			StringTokenizer st = new StringTokenizer(date1,"/");
			int i = 0;
			while(st.hasMoreTokens())
			{
				if(i==1)			
					Month1 = Integer.parseInt(st.nextToken().toString());
				Year1  =Integer.parseInt(st.nextToken().toString());
				i++;
			}
			StringTokenizer st2 = new StringTokenizer(date2,"/");
			i = 0;
			while(st2.hasMoreTokens()){
				if(i==1)			
					Month2 = Integer.parseInt(st2.nextToken().toString());
				Year2  =Integer.parseInt(st2.nextToken().toString());
				i++;
			}


			int cnt1, cnt2, cnt3;
			if(Year2 == Year1)
			{
				cnt2=Month1;
				while(cnt2<=Month2)
				{
					Monthlist.add(cnt2++);
					Monthlist.add(Year1);
				}
			}
			else
			{
				cnt1=Month1;
				cnt3 = 0;
				for (i=0;i<(Year2-Year1);i++)
				{
					cnt3 = Year1 + i;
					while(cnt1<=12)
					{					
						Monthlist.add(cnt1++);
						Monthlist.add(cnt3);
					}
					cnt1=1;
					cnt3++;

				}
				cnt2=1;
				while(cnt2<=Month2)
				{
					Monthlist.add(cnt2++);
					Monthlist.add(cnt3);
				}
			}	

			//System.out.println(Monthlist);
		}
		catch(Exception e)
		{
			//logger.error("Error is: "+ e.getMessage());
		}
		return Monthlist;
	}

}
