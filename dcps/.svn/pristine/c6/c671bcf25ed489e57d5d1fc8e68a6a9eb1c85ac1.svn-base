<%try{%>
<%@ page language="java" import=" java.util.ArrayList,
                                  java.util.Date"%>
<%
	response.setHeader("Cache-Control","no-cache"); 	//HTTP 1.1 
	response.setHeader("Pragma","no-cache"); 	//HTTP 1.0 
	response.setHeader ("Expires", "0"); 	 //prevents caching at the proxy server
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%

Date ldateobj = new Date();
Calendar cal = Calendar.getInstance();
cal.setTime(ldateobj);
int year = cal.get(cal.YEAR);
int month = cal.get(cal.MONTH);
int date = cal.get(cal.DATE);
int hrs = cal.get(cal.HOUR);
int mins = cal.get(cal.MINUTE);
int secs = cal.get(cal.SECOND);

ArrayList larrdate=new ArrayList();
larrdate.add(Integer.toString(year));
larrdate.add(Integer.toString(month));
larrdate.add(Integer.toString(date));
larrdate.add(Integer.toString(hrs));
larrdate.add(Integer.toString(mins));
larrdate.add(Integer.toString(secs));
%>


<%@page import="java.util.Locale"%>
<%@page import="java.util.ResourceBundle"%>

<%@page import="java.util.Calendar"%>
<%@page import="com.tcs.sgv.web.jsp.tags.CalendarhelperInterface"%>
<%@page import="com.tcs.sgv.web.jsp.tags.TagUtilities"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.tcs.sgv.web.jsp.tags.CalendarAttributes"%>
<%@page import="com.tcs.sgv.common.utils.StringUtility"%>
<html>
<head>
<script type="text/javascript" src="<c:url value="/script/common/ContextMenu.js"/>"></script> 
<style type="text/css">
.LegendGenHldy {background-color:#ffbbbb} 
.LegendOptHldy {background-color:#bbffff}
.LegendSatHldy {background-color:#ffbbff}
</style>


<%
    int lStrGetYear =(Integer.parseInt(larrdate.get(0).toString())) ;

    int lStrGetMonth = (Integer.parseInt(larrdate.get(1).toString())) ;

    int lStrGetDate= (Integer.parseInt(larrdate.get(2).toString())) ;

	int lStrGetHrs = (Integer.parseInt(larrdate.get(3).toString())) ;

  	int lStrGetMin = (Integer.parseInt(larrdate.get(4).toString())) ;

    int lStrGetSec = (Integer.parseInt(larrdate.get(5).toString())) ;
%>    
	<!-- c:out value="$(lStrGetYear)"/> -->
	<%
		String userLocale1="en_US";
			        ResourceBundle LabelBundleCon = ResourceBundle.getBundle("resources/calendar/CalendarLables", new Locale("en","US"));
			ResourceBundle LabelBundle = ResourceBundle.getBundle("resources/calendar/CalendarLables",new Locale("en","US"));
		
			String strOptType = (String)LabelBundleCon.getString("MIS.OptionalHldy");
		   	String strGenType = (String)LabelBundleCon.getString("MIS.GenHldy");
		   	
	%>
<form name="javavalues">
<input type="hidden" name="txtJavaYear" value="<%=lStrGetYear%>"></input>
<input type="hidden" name="txtJavaMonth" value="<%=lStrGetMonth%>"></input>
<input type="hidden" name="txtJavaDay" value="<%=lStrGetDate%>"></input>
<input type="hidden" name="txtJavaHour" value="<%=lStrGetHrs%>"></input>
<input type="hidden" name="txtJavaMinute" value="<%=lStrGetMin%>"></input>
<input type="hidden" name="txtJavaSec" value="<%=lStrGetSec%>"></input>
</form>
 
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set> 
<script language="javascript">
	var	fixedX = -1			// x position (-1 if to appear below control)
	var	fixedY = -1			// y position (-1 if to appear below control)
	var startAt = 1			// 0 - sunday ; 1 - monday
	var showWeekNumber = 1	// 0 - don't show; 1 - show
	var showToday = 1		// 0 - don't show; 1 - show
   
    var imgDir="${contextPath}/images/CalendarImages/";

	var gotoString = "Go To Current Month"
	var todayString = "Today is"
	var weekString = "Wk"
	var scrollLeftMessage = "Click to scroll to previous month. Hold mouse button to scroll automatically."
	var scrollRightMessage = "Click to scroll to next month. Hold mouse button to scroll automatically."

	var selectMonthMessage = "Click to select a month."
	var selectYearMessage = "Click to select a year."
	var selectDateMessage = "Select [date] as date." // do not replace [date], it will be replaced by date.
	var	crossobj, crossMonthObj, crossYearObj, monthSelected, yearSelected, dateSelected, omonthSelected, oyearSelected, odateSelected, monthConstructed, yearConstructed, intervalID1, intervalID2, timeoutID1, timeoutID2, ctlToPlaceValue, ctlNow, dateFormat, nStartingYear

	var	bPageLoaded=false
	var	ie=document.all
 	var	dom=document.getElementById

	var	ns4=document.layers

    var today = new Date(document.javavalues.txtJavaYear.value, 
                        document.javavalues.txtJavaMonth.value,
                        document.javavalues.txtJavaDay.value,
                        document.javavalues.txtJavaHour.value,
                        document.javavalues.txtJavaMinute.value,
                        document.javavalues.txtJavaSec.value);


  
	var	dateNow	 = today.getDate()
	var	monthNow = today.getMonth()
	var	yearNow	 = today.getYear()

 	var	imgsrc = new Array("drop1.gif","drop2.gif","left1.gif","left2.gif","right1.gif","right2.gif")
	var	img	= new Array()
  var array=new Object;

	function HolidayRec (d, m, y, desc)
	{

		this.d = d
		this.m = m
		this.y = y
		this.desc = desc
	}

	var HolidaysCounter = 0
	var Holidays = new Array()
	function addHoliday (d, m, y, desc)
	{
		Holidays[HolidaysCounter++] = new HolidayRec ( d, m, y, desc )
	}
	if (dom)
	{
		for	(i=0;i<imgsrc.length;i++)
		{
			img[i] = new Image
			img[i].src = imgDir + imgsrc[i]
			//alert("The whole image path is " + img[i].src);
		}
		
		

    //document.write ("<div id='calendar'	style='position:absolute;visibility:hidden;'><table	width="+((showWeekNumber==1)?250:220)+" style='font-family:arial;font-size:11px;border-width:1;border-style:solid;border-color:#a0a0a0;font-family:arial; font-size:11px}' bgcolor='#ffffff'><tr bgcolor='#0000aa'><td><table width='"+((showWeekNumber==1)?248:218)+"'><tr><td style='padding:2px;font-family:arial; font-size:11px;'><font color='#ffffff'><B><span id='caption'></span></B></font>      </td><td align=right></td></tr></table></td></tr><tr><td style='padding:5px' bgcolor=#ffffff><span id='content'></span></td></tr>")    
    document.write ("<div id='calendar'	style='position:absolute;visibility:hidden;'><table	width="+((showWeekNumber==1)?260:220)+" style='font-family:arial;font-size:11px;border-width:1;border-style:solid;border-color:#a0a0a0;font-family:arial; font-size:11px}' bgcolor='#ffffff'><tr bgcolor='#0000aa'><td style='padding:2px;font-family:arial; font-size:11px;'><font color='#ffffff'><B><span id='caption'></span></B></font></td></tr><tr><td style='padding:2px' bgcolor=#ffffff><span id='content'></span></td></tr>")
    
       //<a href='javascript:hideCalendar()'><IMG SRC='"+imgDir+"close.gif' WIDTH='15' HEIGHT='13' BORDER='0' ALT='Close the Calendar'></a>
		if (showToday==1)
		{
			document.write ("<tr bgcolor=#f0f0f0><td style='padding:5px' align=center><span id='lblToday'></span></td></tr>")      
		}
		document.write ("<tr bgcolor=#f0f0f0><td style='padding:5px' align=center><span id='lblLegend'></span></td></tr>") 	
    document.write ("</table></div><div id='selectMonth' style='position:absolute;visibility:hidden;'></div><div id='selectYear'	style='position:absolute;visibility:hidden;'></div>");    
	}

	var	monthName =	new	Array("January","February","March","April","May","June","July","August","September","October","November","December")
	if (startAt==0)
	{
		dayName = new Array	("Sun","Mon","Tue","Wed","Thu","Fri","Sat")
	}
	else
	{
		dayName = new Array	("Mon","Tue","Wed","Thu","Fri","Sat","Sun")
	}
	var	styleAnchor="text-decoration:none;color:black;"
	var	styleLightBorder="border-style:solid;border-width:1px;border-color:#a0a0a0;"
	function swapImage(srcImg, destImg){
		if (ie)	{
   document.getElementById(srcImg).setAttribute("src",imgDir + destImg) }
	}
  <%
         String lstr="";
         if(request.getParameter("requestSent")!=null)
         {
          lstr=(String)request.getParameter("requestSent");
          }
          else  if(request.getAttribute("requestSent")!=null)
         {
          lstr=(String)request.getAttribute("requestSent");
          }
  %>
         txtDate="<%=lstr%>";
	function init()	{
		if (!ns4)
		{
			if (!ie) { yearNow += 1900	}

			crossobj=(dom)?document.getElementById("calendar").style : ie? document.all.calendar : document.calendar
			hideCalendar()

			crossMonthObj=(dom)?document.getElementById("selectMonth").style : ie? document.all.selectMonth	: document.selectMonth

			crossYearObj=(dom)?document.getElementById("selectYear").style : ie? document.all.selectYear : document.selectYear

			monthConstructed=false;
			yearConstructed=false;

			if (showToday==1)
			{																																																	  
        document.getElementById("lblToday").innerHTML =	todayString + " <a onmousemove='window.status=\""+gotoString+"\"' onmouseout='window.status=\"\"' title='"+gotoString+"' style='"+styleAnchor+"' href='#' onclick='javascript:monthSelected=monthNow;yearSelected=yearNow;constructCalendar();' >"+dayName[(today.getDay()-startAt==-1)?6:(today.getDay()-startAt)]+", " + dateNow + " " + monthName[monthNow].substring(0,3)	+ "	" +	yearNow	+ "</a>"
      }


			sHTML1="<span id='spanLeft'	style='border-style:solid;border-width:1;border-color:#3366FF;cursor:pointer' onmouseover='swapImage(\"changeLeft\",\"left2.gif\");this.style.borderColor=\"#88AAFF\";window.status=\""+scrollLeftMessage+"\"' onclick='javascript:decMonth()' onmouseout='clearInterval(intervalID1);swapImage(\"changeLeft\",\"left1.gif\");this.style.borderColor=\"#3366FF\";window.status=\"\"' onmousedown='clearTimeout(timeoutID1);timeoutID1=setTimeout(\"StartDecMonth()\",500)'	onmouseup='clearTimeout(timeoutID1);clearInterval(intervalID1)'>&nbsp<IMG id='changeLeft' SRC='"+imgDir+"left1.gif' width=10 height=11 BORDER=0>&nbsp</span>&nbsp;"
			sHTML1+="<span id='spanRight' style='border-style:solid;border-width:1;border-color:#3366FF;cursor:pointer'	onmouseover='swapImage(\"changeRight\",\"right2.gif\");this.style.borderColor=\"#88AAFF\";window.status=\""+scrollRightMessage+"\"' onmouseout='clearInterval(intervalID1);swapImage(\"changeRight\",\"right1.gif\");this.style.borderColor=\"#3366FF\";window.status=\"\"' onclick='incMonth()' onmousedown='clearTimeout(timeoutID1);timeoutID1=setTimeout(\"StartIncMonth()\",500)'	onmouseup='clearTimeout(timeoutID1);clearInterval(intervalID1)'>&nbsp<IMG id='changeRight' SRC='"+imgDir+"right1.gif'	width=10 height=11 BORDER=0>&nbsp</span>&nbsp"
			sHTML1+="<span id='spanMonth' style='border-style:solid;border-width:1;border-color:#3366FF;cursor:pointer'	onmouseover='swapImage(\"changeMonth\",\"drop2.gif\");this.style.borderColor=\"#88AAFF\";window.status=\""+selectMonthMessage+"\"' onmouseout='swapImage(\"changeMonth\",\"drop1.gif\");this.style.borderColor=\"#3366FF\";window.status=\"\"' onclick='popUpMonth()'></span>&nbsp;"
			sHTML1+="<span id='spanYear' style='border-style:solid;border-width:1;border-color:#3366FF;cursor:pointer' onmouseover='swapImage(\"changeYear\",\"drop2.gif\");this.style.borderColor=\"#88AAFF\";window.status=\""+selectYearMessage+"\"'	onmouseout='swapImage(\"changeYear\",\"drop1.gif\");this.style.borderColor=\"#3366FF\";window.status=\"\"'	onclick='popUpYear()'></span>&nbsp;"
			
			document.getElementById("caption").innerHTML  =	sHTML1

			bPageLoaded=true

			
      popUpCalendar(this, txtDate, 'm/d/yyyy')
      
		}
	}

	function hideCalendar()	{
	
		crossobj.visibility="hidden"
		if (crossMonthObj != null){crossMonthObj.visibility="hidden"}
		if (crossYearObj !=	null){crossYearObj.visibility="hidden"}
		//Closing a popup window when user clicked 'x', close the calendar. Added by D.T.
  	self.closed
  	
	}

	function padZero(num) {

		return (num	< 10)? '0' + num : num ;
	}

	function constructDate(d,m,y)
	{
  dateFormat='dd/mm/yyyy';
		sTmp = dateFormat
		sTmp = sTmp.replace	("dd","<e>")
		sTmp = sTmp.replace	("d","<d>")
		sTmp = sTmp.replace	("<e>",padZero(d))		
		sTmp = sTmp.replace	("<d>",d)
		sTmp = sTmp.replace	("mmm","<o>")
		sTmp = sTmp.replace	("mm","<n>")
		sTmp = sTmp.replace	("m","<m>")
		sTmp = sTmp.replace	("<m>",m+1)
		sTmp = sTmp.replace	("<n>",padZero(m+1))
		sTmp = sTmp.replace	("<o>",monthName[m])

		return sTmp.replace ("yyyy",y)
	}

	function closeCalendar() {
	
			if (window.dialogArguments) 
			{
		    	window.opener = window.dialogArguments;
		  	}
	
		var	sTmp
		hideCalendar();
		
	  for(i=0; i<window.opener.document.forms[0].elements.length; i++)
	  {
      
        if(window.opener.document.forms[0].elements[i].type=="text")
        {   
  			window.opener.document.getElementById(txtDate).value = constructDate(dateSelected,monthSelected,yearSelected)

/*        if(window.opener.document.forms[0].elements[i].name==txtDate)
              { 
                  window.opener.document.forms[0].elements[i].value=constructDate(dateSelected,monthSelected,yearSelected)
                  self.opener.document.forms[0].elements[i].focus()
              }
 */        }     
     }
    
     eval("window.opener.afterDateSelect()");
	 self.close()
		
	}

	/*** Month Pulldown	***/

	function StartDecMonth()
	{
		intervalID1=setInterval("decMonth()",80)
	}

	function StartIncMonth()
	{
		intervalID1=setInterval("incMonth()",80)
	}

	function incMonth () {

		monthSelected++
		if (monthSelected>11) {
			monthSelected=0
			yearSelected++
		}
		constructCalendar()
	}

	function decMonth () {

		monthSelected--
		if (monthSelected<0) {
			monthSelected=11
			yearSelected--
		}
		constructCalendar()
	}

	function constructMonth() {

		popDownYear()
		if (!monthConstructed) {
			sHTML =	""
			for	(i=0; i<12;	i++) {
				sName =	monthName[i];
				if (i==monthSelected){
					sName =	"<B>" +	sName +	"</B>"
				}
				sHTML += "<tr><td id='m" + i + "' onmouseover='this.style.backgroundColor=\"#FFCC99\"' onmouseout='this.style.backgroundColor=\"\"' style='cursor:pointer' onclick='monthConstructed=false;monthSelected=" + i + ";constructCalendar();popDownMonth();event.cancelBubble=true'>&nbsp;" + sName + "&nbsp;</td></tr>"
			}

			document.getElementById("selectMonth").innerHTML = "<table width=70	style='font-family:arial; font-size:11px; border-width:1; border-style:solid; border-color:#a0a0a0;' bgcolor='#FFFFDD' cellspacing=0 onmouseover='clearTimeout(timeoutID1)'	onmouseout='clearTimeout(timeoutID1);timeoutID1=setTimeout(\"popDownMonth()\",100);event.cancelBubble=true'>" +	sHTML +	"</table>"

			monthConstructed=true
		}
	}

	function popUpMonth() {

		constructMonth()
		crossMonthObj.visibility = (dom||ie)? "visible"	: "show"
		
	}

	function popDownMonth()	{

		crossMonthObj.visibility= "hidden"
	}

	/*** Year Pulldown ***/

	function incYear() {

		for	(i=0; i<7; i++){
			newYear	= (i+nStartingYear)+1
			if (newYear==yearSelected)
			{ txtYear =	"&nbsp;<B>"	+ newYear +	"</B>&nbsp;" }
			else
			{ txtYear =	"&nbsp;" + newYear + "&nbsp;" }
			document.getElementById("y"+i).innerHTML = txtYear
		}
		nStartingYear ++;
	}

	function decYear() {
		for	(i=0; i<7; i++){
			newYear	= (i+nStartingYear)-1
			if (newYear==yearSelected)
			{ txtYear =	"&nbsp;<B>"	+ newYear +	"</B>&nbsp;" }
			else
			{ txtYear =	"&nbsp;" + newYear + "&nbsp;" }
			document.getElementById("y"+i).innerHTML = txtYear
		}
		nStartingYear --;
	}

	function selectYear(nYear) {
		yearSelected=parseInt(nYear+nStartingYear);
		yearConstructed=false;
		constructCalendar();
		popDownYear();
	}

	function constructYear() {
		popDownMonth()
		sHTML =	""
		if (!yearConstructed) {

			sHTML =	"<tr><td align='center'	onmouseover='this.style.backgroundColor=\"#FFCC99\"' onmouseout='clearInterval(intervalID1);this.style.backgroundColor=\"\"' style='cursor:pointer'	onmousedown='clearInterval(intervalID1);intervalID1=setInterval(\"decYear()\",30)' onmouseup='clearInterval(intervalID1)'>-</td></tr>"

			j =	0
			nStartingYear =	yearSelected-3
			for	(i=(yearSelected-3); i<=(yearSelected+3); i++) {
				sName =	i;
				if (i==yearSelected){
					sName =	"<B>" +	sName +	"</B>"
				}

				sHTML += "<tr><td id='y" + j + "' onmouseover='this.style.backgroundColor=\"#FFCC99\"' onmouseout='this.style.backgroundColor=\"\"' style='cursor:pointer' onclick='selectYear("+j+");event.cancelBubble=true'>&nbsp;" + sName + "&nbsp;</td></tr>"
				j ++;
			}

			sHTML += "<tr><td align='center' onmouseover='this.style.backgroundColor=\"#FFCC99\"' onmouseout='clearInterval(intervalID2);this.style.backgroundColor=\"\"' style='cursor:pointer' onmousedown='clearInterval(intervalID2);intervalID2=setInterval(\"incYear()\",30)'	onmouseup='clearInterval(intervalID2)'>+</td></tr>"

			document.getElementById("selectYear").innerHTML	= "<table width=44 style='font-family:arial; font-size:11px; border-width:1; border-style:solid; border-color:#a0a0a0;'	bgcolor='#FFFFDD' onmouseover='clearTimeout(timeoutID2)' onmouseout='clearTimeout(timeoutID2);timeoutID2=setTimeout(\"popDownYear()\",100)' cellspacing=0>"	+ sHTML	+ "</table>"

			yearConstructed	= true
		}
	}

	function popDownYear() {
		clearInterval(intervalID1)
		clearTimeout(timeoutID1)
		clearInterval(intervalID2)
		clearTimeout(timeoutID2)
		crossYearObj.visibility= "hidden"
	}

	function popUpYear() {
		var	leftOffset
		constructYear()
		crossYearObj.visibility	= (dom||ie)? "visible" : "show"
        leftOffset = parseInt(crossobj.left) + document.getElementById("spanYear").offsetLeft
        if (ie)
		{
			leftOffset += 6
		}
        
	}

	/*** calendar ***/

	function WeekNbr(today)
    {
		Year = takeYear(today);
		Month = today.getMonth();
		Day = today.getDate();
		now = Date.UTC(Year,Month,Day+1,0,0,0);
		var Firstday = new Date();
		Firstday.setYear(Year);
		Firstday.setMonth(0);
		Firstday.setDate(1);
		then = Date.UTC(Year,0,1,0,0,0);
		var Compensation = Firstday.getDay();
		if (Compensation > 3) Compensation -= 4;
		else Compensation += 3;
		NumberOfWeek =  Math.round((((now-then)/86400000)+Compensation)/7);
		return NumberOfWeek;
	}

	function takeYear(theDate)
	{
		x = theDate.getYear();
		var y = x % 100;
		y += (y < 38) ? 2000 : 1900;
		return y;
	}


	function constructCalendar () {
	
		
		var aNumDays = Array (31,0,31,30,31,30,31,31,30,31,30,31)

		var dateMessage
		var	startDate =	new	Date (yearSelected,monthSelected,1)
		var endDate

		if (monthSelected==1)
		{
			endDate	= new Date (yearSelected,monthSelected+1,1);
			endDate	= new Date (endDate	- (24*60*60*1000));
			numDaysInMonth = endDate.getDate()
		}
		else
		{
			numDaysInMonth = aNumDays[monthSelected];
		}

		datePointer	= 0
		dayPointer = startDate.getDay() - startAt
		
		if (dayPointer<0)
		{
			dayPointer = 6
		}
		
		sHTML =	"<table	width=100%  border=0 style='font-family:verdana;font-size:10px;'><tr>"

		if (showWeekNumber==1)
		{
			sHTML += "<td width=27><b>" + weekString + "</b></td><td width=1 rowspan=7 bgcolor='#d0d0d0' style='padding:0px'><img src='"+imgDir+"divider.gif' width=1></td>"
		}

		for	(i=0; i<7; i++)	{
			sHTML += "<td width='27' align='right'><B>"+ dayName[i]+"</B></td>"
		}
		sHTML +="</tr><tr>"
		
		if (showWeekNumber==1)
		{
			sHTML += "<td align=right>" + WeekNbr(startDate) + "&nbsp;</td>"
		}

		for	( var i=1; i<=dayPointer;i++ )
		{
			sHTML += "<td>&nbsp;</td>"
		}
 	var intSatCnt = 0;      		
    for	( datePointer=1; datePointer<=numDaysInMonth; datePointer++ )
		{
      dayPointer++;
			sHTML += "<td align=right>"
			sStyle=styleAnchor
			if ((datePointer==odateSelected) &&	(monthSelected==omonthSelected)	&& (yearSelected==oyearSelected))
			{ sStyle+=styleLightBorder }

			sHint = ""
			for (k=0;k<HolidaysCounter;k++)
			{
				if ((parseInt(Holidays[k].d)==datePointer)&&(parseInt(Holidays[k].m)==(monthSelected+1)))
				{
					if ((parseInt(Holidays[k].y)==0)||((parseInt(Holidays[k].y)==yearSelected)&&(parseInt(Holidays[k].y)!=0)))
					{
						sStyle+="background-color:#FFDDDD;"
						sHint+=sHint==""?Holidays[k].desc:"\n"+Holidays[k].desc
					}
				}
			}

			var regexp= /\"/g
			sHint=sHint.replace(regexp,"&quot;")

			dateMessage = "onmousemove='window.status=\""+selectDateMessage.replace("[date]",constructDate(datePointer,monthSelected,yearSelected))+"\"' onmouseout='window.status=\"\"' "
      
      if ((dayPointer % 7) == (startAt * 6))
      {
        intSatCnt++;
        if(intSatCnt%2==0)
        {
          sStyle+="background-color:#ffbbff;"
        }
      }
      <% 
      
		//Code from Impl Class....

	    CalendarhelperInterface calendarobj=TagUtilities.calendarinstance();
   
        String strDate = "";
        String strType = "";
        String strOcsn = "";
      
          for (Iterator iter = calendarobj.getHolidayList().iterator(); iter.hasNext();) 
          {
        	    CalendarAttributes calatt = (CalendarAttributes) iter.next();
    	      	strDate=(String)calatt.getCalendarDate();
	    	    strType=(String)calatt.getCalendarType();
	            strOcsn=(String)calatt.getCalendarOccason();
         
          
          
      %>
          var strDate = "<%=strDate%>";
          var lDate = strDate.split("/");
          var lOcc = "<%=strOcsn%>";
          var lDay = lDate[0];
          var lMonth = lDate[1];
          var lYear = lDate[2];
          if ((datePointer==lDay)&&(monthSelected==(lMonth-1))&&(yearSelected==lYear))
          {
            <% 
            
            if(strType.equalsIgnoreCase(strOptType))
            {
            
            %>
              
              sStyle+="background-color:#bbffff;"
              sHint+=sHint==""?lOcc:"\n"+lOcc
            <% 
            }
            else
            {
            
            %>
            
             
              sStyle+="background-color:#ffbbbb;"
              sHint+=sHint==""?lOcc:"\n"+lOcc
            <% 
            }
            %>
          }          
        <% 
        }
        %>
      
      
			
      if ((datePointer==dateNow)&&(monthSelected==monthNow)&&(yearSelected==yearNow))
			{sHTML += "<b><a "+dateMessage+" title=\"" + sHint + "\" style='"+sStyle+"' href='#' onclick='javascript:dateSelected="+datePointer+";closeCalendar();'><font color=#ff0000>&nbsp;" + datePointer + "</font>&nbsp;</a></b>"}
			else if	(dayPointer % 7 == (startAt * -1)+1)
			{sHTML += "<a "+dateMessage+" title=\"" + sHint + "\" style='"+sStyle+"' href='#' onclick='javascript:dateSelected="+datePointer + ";closeCalendar();'>&nbsp;<font color=#909090>" + datePointer + "</font>&nbsp;</a>" }
      else
			{sHTML += "<a "+dateMessage+" title=\"" + sHint + "\" style='"+sStyle+"' href='#' onclick='javascript:dateSelected="+datePointer + ";closeCalendar();'>&nbsp;" + datePointer + "&nbsp;</a>" }

			sHTML += ""
			if ((dayPointer+startAt) % 7 == startAt) { 
				sHTML += "</tr><tr>" 
				if ((showWeekNumber==1)&&(datePointer<numDaysInMonth))
				{
					sHTML += "<td align=right>" + (WeekNbr(new Date(yearSelected,monthSelected,datePointer+1))) + "&nbsp;</td>"
				}
			}
		}
    
    sHTML += "</tr></table>";
    var sLegend = "<table><tr style='font-family:Verdana;font-size:6pt;font-weight:bold;'>";
    sLegend += "<td ><%=LabelBundle.getString("FMS.HolidayLegend")%>&nbsp;:&nbsp;</td>";
    sLegend += "<td ><span align=left class=LegendGenHldy>&nbsp;&nbsp;&nbsp;</span>&nbsp;<%=LabelBundle.getString("FMS.GenHoliday")%>&nbsp;</td>";
    sLegend += "<td ><span align=left class=LegendOptHldy>&nbsp;&nbsp;&nbsp;</span>&nbsp;<%=LabelBundle.getString("FMS.OptHoliday")%>&nbsp;</td>";
    sLegend += "<td ><span align=left class=LegendSatHldy>&nbsp;&nbsp;&nbsp;</span>&nbsp;<%=LabelBundle.getString("FMS.SatHoliday")%>&nbsp;</td>";
    sLegend += "</tr></table></font>";
    document.getElementById("lblLegend").innerHTML   = sLegend
    
		document.getElementById("content").innerHTML   = sHTML
   	document.getElementById("spanMonth").innerHTML = "&nbsp;" +	monthName[monthSelected] + "&nbsp;<IMG id='changeMonth' SRC='"+imgDir+"drop1.gif' WIDTH='12' HEIGHT='10' BORDER=0>"
		document.getElementById("spanYear").innerHTML =	"&nbsp;" + yearSelected	+ "&nbsp;<IMG id='changeYear' SRC='"+imgDir+"drop1.gif' WIDTH='12' HEIGHT='10' BORDER=0>"
  
	}

	function popUpCalendar(ctl,	ctl2, format) {
		var	leftpos=0
		var	toppos=0

		if (bPageLoaded)
		{
			if ( crossobj.visibility ==	"hidden" ) {
				ctlToPlaceValue	= ctl2
				dateFormat=format;

				formatChar = " "
				aFormat	= dateFormat.split(formatChar)
				if (aFormat.length<3)
				{
					formatChar = "/"
					aFormat	= dateFormat.split(formatChar)
					if (aFormat.length<3)
					{
						formatChar = "."
						aFormat	= dateFormat.split(formatChar)
						if (aFormat.length<3)
						{
							formatChar = "-"
							aFormat	= dateFormat.split(formatChar)
							if (aFormat.length<3)
							{
								// invalid date	format
								formatChar=""
							}
						}
					}
				}

				tokensChanged =	0
				if ( formatChar	!= "" )
				{
					// use user's date
					aData =	ctl2.split(formatChar)

					for	(i=0;i<3;i++)
					{
						if ((aFormat[i]=="d") || (aFormat[i]=="dd"))
						{
							dateSelected = parseInt(aData[i], 10)
							tokensChanged ++
						}
						else if	((aFormat[i]=="m") || (aFormat[i]=="mm"))
						{
							monthSelected =	parseInt(aData[i], 10) - 1
							tokensChanged ++
						}
						else if	(aFormat[i]=="yyyy")
						{
							yearSelected = parseInt(aData[i], 10)
							tokensChanged ++
						}
						else if	(aFormat[i]=="mmm")
						{
							for	(j=0; j<12;	j++)
							{
								if (aData[i]==monthName[j])
								{
									monthSelected=j
									tokensChanged ++
								}
							}
						}
					}
				}

				if ((tokensChanged!=3)||isNaN(dateSelected)||isNaN(monthSelected)||isNaN(yearSelected))
				{
					dateSelected = dateNow
					monthSelected =	monthNow
					yearSelected = yearNow
				}

				odateSelected=dateSelected
				omonthSelected=monthSelected
				oyearSelected=yearSelected

				
        constructCalendar (1, monthSelected, yearSelected);
        crossobj.visibility=(dom||ie)? "visible" : "show"
			}
			else
			{
				hideCalendar()
				if (ctlNow!=ctl) {popUpCalendar(ctl, ctl2, format)}
			}
			ctlNow = ctl
		}

	}
</script>

<title>
Calendar
</title>
</head>
<%String str=request.getParameter("requestSent");  %>
<body onload="javascript:init();">
</body>
</html>
<%}catch(Exception e)
{
	e.printStackTrace();
}%>