<%try{%>
<%@ page language="java" import=" java.util.ArrayList,java.util.Date,java.util.Calendar"%>
<%

 java.util.Calendar cln = Calendar.getInstance();
	cln.add(Calendar.DAY_OF_MONTH,1);
	cln.set(Calendar.HOUR_OF_DAY,1);

response.setHeader ("Expires", cln.getTime().toString());
%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="java.util.Locale"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.tcs.sgv.web.jsp.tags.CalendarhelperInterface"%>
<%@page import="com.tcs.sgv.web.jsp.tags.TagUtilities"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.tcs.sgv.web.jsp.tags.CalendarAttributes"%>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set> 
<%

CalendarhelperInterface calobj=TagUtilities.calendarinstance();
Date ldateobj = calobj.getCurrDate();

//Date ldateobj = new Date();
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


<%--@page import="com.tcs.sgv.common.utils.StringUtility"--%>
<html>
<head>
<script type="text/javascript" src="<c:url value="/script/common/ContextMenu.js"/>"></script> 
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script> 
<script type="text/javascript" src="<c:url value="/script/common/tagLibValidation.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/modalDialog.js"/>"></script> 
<link rel="stylesheet" href="<c:url value="/themes/${themename}/calendar.css"/>" type="text/css" />

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
//			        ResourceBundle LabelBundleCon = ResourceBundle.getBundle("resources/calendar/CalendarLables", new Locale("en","US"));
			ResourceBundle LabelBundle = ResourceBundle.getBundle("resources/calendar/CalendarLables",new Locale("en","US"));
		
			String strOptType = (String)LabelBundle.getString("MIS.OptionalHldy");
//		   	String strGenType = (String)LabelBundleCon.getString("MIS.GenHldy");
		   	
	%>
 
 
<script language="javascript">
	var	fixedX = -1			// x position (-1 if to appear below control)
	var	fixedY = -1			// y position (-1 if to appear below control)
	var startAt = 1			// 0 - sunday ; 1 - monday
	var showWeekNumber = 1	// 0 - don't show; 1 - show
	var showToday = 1		// 0 - don't show; 1 - show
   
    var imgDir="${contextPath}/themes/${themename}/images/taglib/";

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

//	alert (document.getElementById('txtJavaYear').value);
/*	
    var today = new Date(document.javavalues.txtJavaYear.value, 
                        document.javavalues.txtJavaMonth.value,
                        document.javavalues.txtJavaDay.value,
                        document.javavalues.txtJavaHour.value,
                        document.javavalues.txtJavaMinute.value,
                        document.javavalues.txtJavaSec.value);
*/
/*    var today = new Date(
    					document.getElementById('txtJavaYear').value, 
                        document.getElementById('txtJavaMonth').value,
                        document.getElementById('txtJavaDay').value,
                        document.getElementById('txtJavaHour').value,
                        document.getElementById('txtJavaMinute').value,
                        document.getElementById('txtJavaSec').value);*/

	var today = new Date(
		<%=lStrGetYear%>, 
		<%=lStrGetMonth%>, 
		<%=lStrGetDate%>, 
		<%=lStrGetHrs%>, 
		<%=lStrGetMin%>, 
		<%=lStrGetSec%>);
	var	dateNow	 = today.getDate()
	var	monthNow = today.getMonth()
	var	yearNow	 = today.getFullYear()

 	var	imgsrc = new Array("drop1.gif","drop2.gif","left1.gif","left2.gif","right1.gif","right2.gif")
	var	img	= new Array()
  	var array=new Object;
	// added by :202414
	var INCLUDED_CALENDAR  = '${param.isIncludedCalendar}';
	var dayNamesForToday = null ;
	
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
		// added by : 202414
		var calendarWidth ;
		if (showWeekNumber == 1 )
		{
			if (INCLUDED_CALENDAR == 'Y')
			{
				calendarWidth = 100 ;
			}
			else
			{
				calendarWidth = 260 ;
			}
		}
		else
		{
			calendarWidth = 220 ;
		}
    //document.write ("<div id='calendar'	style='position:absolute;visibility:hidden;'><table	width="+((showWeekNumber==1)?250:220)+" style='font-family:arial;font-size:11px;border-width:1;border-style:solid;border-color:#a0a0a0;font-family:arial; font-size:11px}' bgcolor='#ffffff'><tr bgcolor='#0000aa'><td><table width='"+((showWeekNumber==1)?248:218)+"'><tr><td style='padding:2px;font-family:arial; font-size:11px;'><font color='#ffffff'><B><span id='caption'></span></B></font>      </td><td align=right></td></tr></table></td></tr><tr><td style='padding:5px' bgcolor=#ffffff><span id='content'></span></td></tr>")    
    document.write ("<div id='calendar' style='width:260;'><table id='calendarTollbar' cellspacing=0 width="+calendarWidth+"><tr id='toolbarTR'><td id='toolbarTD' align='left'><span id='caption'></span></td><td id='toolbarCloseTD' align='right'><span id='closeSpan'></span></td></tr><tr><td id='contentTD' colspan='2'><span id='calcontent'></span></td></tr>")
    
       //<a href='javascript:hideCalendar()'><img SRC='"+imgDir+"close.gif' WIDTH='15' HEIGHT='13' BORDER='0' ALT='Close the Calendar'></a>
		if (showToday==1)
		{
			document.write ("<tr id='todayTR'><td id='todayTD' colspan='2'><span id='lblToday'></span></td></tr>")      
		}
		document.write ("<tr id='legendTR'><td id='legendTD' colspan='2'><span id='lblLegend'></span></td></tr>") 	
    document.write ("</table></div><div id='selectMonth'></div><div id='selectYear'></div>");    
	}

	var	monthName =	new	Array("January","February","March","April","May","June","July","August","September","October","November","December")
	// added by 202414
	if (startAt==0)
	{
		if (INCLUDED_CALENDAR == 'Y')
		{	// included calendar
			dayName = new Array	("Su","Mo","Tu","We","Th","Fr","Sa")	
		}
		else// deafult behaviour (pop up calendar)
		{
			dayName = new Array	("Sun","Mon","Tue","Wed","Thu","Fri","Sat")
		}
		dayNamesForToday = new Array	("Sun","Mon","Tue","Wed","Thu","Fri","Sat") ;
	}
	else
	{
		if (INCLUDED_CALENDAR == 'Y')
		{	// included calendar
			dayName = new Array	("Mo","Tu","We","Th","Fr","Sa","Su") ;
		}
		else// deafult behaviour (pop up calendar)
		{
			dayName = new Array	("Mon","Tue","Wed","Thu","Fri","Sat","Sun") ;
		}
		dayNamesForToday = new Array	("Mon","Tue","Wed","Thu","Fri","Sat","Sun") ;
	}
	var	styleAnchor=""
	// var	styleLightBorder="border-style:solid;border-width:1px;border-color:#a0a0a0;"
	function swapImage(srcImg, destImg){
		if (ie)	{
   document.getElementById(srcImg).setAttribute("src",imgDir + destImg) }
	}
  <%
         String lstr="";
		 String lindex = "";
         if(request.getParameter("requestSent")!=null)
         {
          lstr=(String)request.getParameter("requestSent");
          }
          else  if(request.getAttribute("requestSent")!=null)
         {
          lstr=(String)request.getAttribute("requestSent");
          }
         if(request.getParameter("index")!=null)
         {
         	lindex = (String) request.getParameter("index");
         }
         
  %>
         txtDate="<%=lstr%>";
         txtIndex="<%=lindex%>";
	function init()	{
		if (!ns4)
		{
			crossobj=(dom)?document.getElementById("calendar").style : ie? document.all.calendar : document.calendar
			hideCalendar()

			crossMonthObj=(dom)?document.getElementById("selectMonth").style : ie? document.all.selectMonth	: document.selectMonth

			crossYearObj=(dom)?document.getElementById("selectYear").style : ie? document.all.selectYear : document.selectYear

			monthConstructed=false;
			yearConstructed=false;

			if (showToday==1)
			{																																																	  
        document.getElementById("lblToday").innerHTML =	"<a id='lblTodayAnchor' onmousemove='window.status=\""+gotoString+"\"' onmouseout='window.status=\"\"' title='"+gotoString+"'  href='#' onclick='javascript:monthSelected=monthNow;yearSelected=yearNow;constructCalendar();return false;' >"+todayString + " " + dayNamesForToday[(today.getDay()-startAt==-1)?6:(today.getDay()-startAt)]+", " + dateNow + " " + monthName[monthNow].substring(0,3)	+ "	" +	yearNow	+ "</a>"
      }

			sHTML1="<span id='spanLeft'	style='border-style:solid;border-width:1;border-color:#3366FF;cursor:pointer;background:#0000aa;' onmouseover='swapImage(\"changeLeft\",\"left2.gif\");this.style.borderColor=\"#88AAFF\";window.status=\""+scrollLeftMessage+"\"' onclick='javascript:decMonth()' onmouseout='clearInterval(intervalID1);swapImage(\"changeLeft\",\"left1.gif\");this.style.borderColor=\"#3366FF\";window.status=\"\"' onmousedown='clearTimeout(timeoutID1);timeoutID1=setTimeout(\"StartDecMonth()\",500)'	onmouseup='clearTimeout(timeoutID1);clearInterval(intervalID1)'>&nbsp<img id='changeLeft' SRC='"+imgDir+"left1.gif' width=10 height=11 BORDER=0></img>&nbsp</span>&nbsp;"
			sHTML1+="<span id='spanRight' style='border-style:solid;border-width:1;border-color:#3366FF;cursor:pointer;background:#0000aa;'	onmouseover='swapImage(\"changeRight\",\"right2.gif\");this.style.borderColor=\"#88AAFF\";window.status=\""+scrollRightMessage+"\"' onmouseout='clearInterval(intervalID1);swapImage(\"changeRight\",\"right1.gif\");this.style.borderColor=\"#3366FF\";window.status=\"\"' onclick='incMonth()' onmousedown='clearTimeout(timeoutID1);timeoutID1=setTimeout(\"StartIncMonth()\",500)'	onmouseup='clearTimeout(timeoutID1);clearInterval(intervalID1)'>&nbsp<img id='changeRight' SRC='"+imgDir+"right1.gif'	width=10 height=11 BORDER=0></img>&nbsp</span>&nbsp"
			sHTML1+="<span id='spanMonth' style='border-style:solid;border-width:1;border-color:#3366FF;cursor:pointer;background:#0000aa;'	onmouseover='swapImage(\"changeMonth\",\"drop2.gif\");this.style.borderColor=\"#88AAFF\";window.status=\""+selectMonthMessage+"\"' onmouseout='swapImage(\"changeMonth\",\"drop1.gif\");this.style.borderColor=\"#3366FF\";window.status=\"\"' onclick='popUpMonth()'></span>&nbsp;"
			sHTML1+="<span id='spanYear' style='border-style:solid;border-width:1;border-color:#3366FF;cursor:pointer;background:#0000aa;' onmouseover='swapImage(\"changeYear\",\"drop2.gif\");this.style.borderColor=\"#88AAFF\";window.status=\""+selectYearMessage+"\"'	onmouseout='swapImage(\"changeYear\",\"drop1.gif\");this.style.borderColor=\"#3366FF\";window.status=\"\"'	onclick='popUpYear()'></span>&nbsp;"
		    if(newCalendar == true)
			{
				document.getElementById("closeSpan").innerHTML = "<span id='calendar_close' style='padding-right: 1px; padding-left: 1px; margin-right: 2px; border: solid 1px #3366FF; cursor: pointer; background: #0000aa;' onmouseover='document.getElementById(\"closeid\").setAttribute(\"src\",\"${contextPath}/images/close_focus.gif\");this.style.borderColor=\"#88AAFF\";' onmouseout='document.getElementById(\"closeid\").setAttribute(\"src\",\"${contextPath}/images/close_blur.gif\");this.style.borderColor=\"#3366FF\";' onclick='javascript:closeModalWindow();'><img src='${contextPath}/images/close_blur.gif' alt='close' id='closeid' width=10 height=11 BORDER=0></img></span>&nbsp";
			}
			
			document.getElementById("caption").innerHTML  =	sHTML1;

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
		sTmp = sTmp.replace ("yyyy",y);	
	
		return sTmp;
	
	
	
	}
	
	var cntcal=0;
	function closeCalendar() {
		var dateCheck;
		if(newCalendar == true)
		{
	    	setForm(parent.document.forms[0].name);
	    	dateCheck = eval("dateCheckInputsValues()");
		}
		else
		{
			if (window.dialogArguments) 
			{
		    	window.opener = window.dialogArguments;
		  	}
			setForm(window.opener.document.forms[0].name);
			dateCheck = eval("window.opener.dateCheckInputsValues()");
		} 
        dateCheck = dateCheck.replace(/~/g," ");
        var dateCheckIps = dateCheck.split(",");
	  	var name = dateCheckIps[0];
	  	var errMessage = dateCheckIps[1];
	  	var errCaption = dateCheckIps[2];
	  	var minvalue = dateCheckIps[3];
	  	var maxvalue = dateCheckIps[4];
	  	var dtRangeErrMsg = dateCheckIps[5];
	  	
	  	var	sTmp;
			//hideCalendar();
		var temp;
		var formVar;
		if(newCalendar == true)
		{
			formVar = parent;
		}
		else
		{
			formVar = window.opener;
		}
		
		var flag = '';
		  for(i=0; i<formVar.document.forms[0].elements.length; i++)
		  {
	        if(formVar.document.forms[0].elements[i].type=="text")
	        {   
	        	if(formVar.document.forms[0].elements[i].id==txtDate)
	              {
	              	if(txtIndex == "")
             		{   
	                  temp = formVar.document.forms[0].elements[i].value;
	                  formVar.document.forms[0].elements[i].value=constructDate(dateSelected,monthSelected,yearSelected)
	                  cntcal = i;
	                  flag = checkModalDate(name,errMessage,errCaption,minvalue,maxvalue,dtRangeErrMsg);

		      			if(flag)
		      				formVar.document.forms[0].elements[i].focus();
						else
							formVar.document.forms[0].elements[i].value = temp;
					}
					else
	                {
	//					flag = true;
//	                	var temp = formVar.document.getElementsByName(txtDate);
	  //              	temp[txtIndex].value = constructDate(dateSelected,monthSelected,yearSelected)
	    //            	temp[txtIndex].focus() 


		                
						flag = true;
						temp = formVar.document.getElementsByName(txtDate.substring(0,txtDate.length-1));
						formVar.document.getElementById(""+txtDate+"").value= constructDate(dateSelected,monthSelected,yearSelected);
						formVar.document.getElementById(""+txtDate+"").focus(); 
					}
	                break;
	              }
	         }
	     }
		if(flag)
		{
			if(newCalendar == true)
			{
				eval("afterDateSelect()");
				formVar.closeModalWindow();
			}
			else
			{
				eval("window.opener.afterDateSelect()");
				self.close();
			} 
		}
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
//		alert(document.getElementById("spanMonth").offsetTop);
		var left = document.getElementById("calendar").offsetLeft+document.getElementById("spanMonth").offsetLeft;
		var top =  document.getElementById("calendar").offsetTop+document.getElementById("spanMonth").offsetTop+document.getElementById("spanMonth").offsetHeight+1;
		if (INCLUDED_CALENDAR)
		{
			left = left + 4 ;
			top = top + 3 ;
		}
		left = left + 'px' ;
		top = top + 'px' ;
		
		crossMonthObj.left= left ;
		crossMonthObj.top=	top ;
		
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

	function popUpYear() 
	{
		var	leftOffset
		constructYear()
		crossYearObj.visibility	= (dom||ie)? "visible" : "show"
		// added by 202414			
		var leftO  = document.getElementById("calendar").offsetLeft+document.getElementById("spanYear").offsetLeft ;
        var topO = document.getElementById("calendar").offsetTop+document.getElementById("spanYear").offsetTop+document.getElementById("spanYear").offsetHeight+1 ;
        if (INCLUDED_CALENDAR == 'Y')
        {
        	leftO = leftO + 4 ;
        	topO = topO + 3 ;
        }
		leftO = leftO +'px' ;
		topO = topO + 'px' ;
        crossYearObj.left = leftO ;
		crossYearObj.top  = topO; 
        
        
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
		//alert("constructCalendar is called ");
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
		var totalCells = 42;
		
		if (dayPointer<0)
		{
			dayPointer = 6
		}
		
		sHTML =	"<table id='calendarTable' cellspacing=0 cellpadding=1><tr>"

		if (showWeekNumber==1)
		{
			sHTML += "<td width=27 class='weekHeader'>" + weekString + "</td>"
		}

		for	(i=0; i<7; i++)	{
			sHTML += "<th width='27' align='right'>"+ dayName[i]+"</th>"
		}
		sHTML +="</tr><tr>"
		
		if (showWeekNumber==1)
		{
			sHTML += "<td class='weekNumber'>" + WeekNbr(startDate) + "&nbsp;</td>"
		}

		for	( var i=1; i<=dayPointer;i++ )
		{
			sHTML += "<td class='extraCells'>&nbsp;</td>"
		}
 	var intSatCnt = 0;   
 	var sClass = "";   
    for	( datePointer=1; datePointer<=numDaysInMonth; datePointer++ )
		{
		sClass = "";
      dayPointer++;
			sHTML += "<td>"
			// sStyle=styleAnchor
			/* if ((datePointer==odateSelected) &&	(monthSelected==omonthSelected)	&& (yearSelected==oyearSelected))
			{ sStyle+=styleLightBorder } */

			sHint = ""
			for (k=0;k<HolidaysCounter;k++)
			{
				if ((parseInt(Holidays[k].d)==datePointer)&&(parseInt(Holidays[k].m)==(monthSelected+1)))
				{
					if ((parseInt(Holidays[k].y)==0)||((parseInt(Holidays[k].y)==yearSelected)&&(parseInt(Holidays[k].y)!=0)))
					{
						// sStyle+="background-color:#FFDDDD;"
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
          // sStyle+="background-color:#ffbbff;"
          sClass = "LegendSatHldy";
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
              
              // sStyle+="background-color:#bbffff;"
	          sClass = "LegendOptHldy";
              sHint+=sHint==""?lOcc:"\n"+lOcc
            <% 
            }
            else
            {
            
            %>
            
             
              // sStyle+="background-color:#ffbbbb;"
          sClass = "LegendGenHldy";
              sHint+=sHint==""?lOcc:"\n"+lOcc
            <% 
            }
            %>
          }          
        <% 
        }
        %>
      
      space = "";
      if( datePointer < 10 )
      space="&nbsp;";
      
		// ADDED BY : 202414				
		if ((datePointer==dateNow)&&(monthSelected==monthNow)&&(yearSelected==yearNow))
		{
			if (INCLUDED_CALENDAR == 'Y')
			{	// IF CALENDAR JSP INCLUDED 
				sHTML += "<a class='todaysDate "+ sClass +"' "+dateMessage+" title=\"" + sHint + "\" href='#' >&nbsp;" + space + datePointer + "&nbsp;</a>"
			}
			else
			{// DEFAULT BEHAVIOUR
				sHTML += "<a class='todaysDate "+ sClass +"' "+dateMessage+" title=\"" + sHint + "\" href='#' onclick='javascript:dateSelected="+datePointer+";closeCalendar();return false;'>&nbsp;" + space  + datePointer + "&nbsp;</a>"			
			}
		}
		else if	(dayPointer % 7 == (startAt * -1)+1)
		{
			if (INCLUDED_CALENDAR == 'Y')
			{	// IF CALENDAR JSP INCLUDED 
				sHTML += "<a class='sunday "+ sClass +"' "+dateMessage+" title=\"" + sHint + "\" href='#' >&nbsp;" + space + datePointer + "&nbsp;</a>" 
			}
			else
			{// DEFAULT BEHAVIOUR
				sHTML += "<a class='sunday "+ sClass +"' "+dateMessage+" title=\"" + sHint + "\" href='#' onclick='javascript:dateSelected="+datePointer + ";closeCalendar();return false;'>&nbsp;" + space  + datePointer + "&nbsp;</a>" 
			}
		}
		else if(datePointer <= numDaysInMonth)
		{
			if (INCLUDED_CALENDAR == 'Y')
			{	// IF CALENDAR JSP INCLUDED 
				sHTML += "<a class='normalDay "+ sClass +"' "+dateMessage+" title=\"" + sHint + "\" href='#' >&nbsp;" + space + datePointer + "&nbsp;</a>"
			}
			else
			{// DEFAULT BEHAVIOUR
				sHTML += "<a class='normalDay "+ sClass +"' "+dateMessage+" title=\"" + sHint + "\" href='#' onclick='javascript:dateSelected="+datePointer + ";closeCalendar();return false;'>&nbsp;" + space + datePointer + "&nbsp;</a>"
			}
		}
			
			sHTML += "</td>"
			if ((dayPointer+startAt) % 7 == startAt) { 
				// alert(datePointer + ": New Line...");
				sHTML += "</tr><tr>" 
				if ((showWeekNumber==1)&&(datePointer<numDaysInMonth))
				{
					sHTML += "<td class='weekNumber'>" + (WeekNbr(new Date(yearSelected,monthSelected,datePointer+1))) + "&nbsp;</td>"
				}
			}

		}
    		if(datePointer == numDaysInMonth)
			{
				var extracells = "";
				while( dayPointer < totalCells )
				{
					if (((dayPointer+startAt) % 7 == startAt) && extracells != "") { 
						extracells += "</tr><tr>" 
						if (showWeekNumber==1)
						{
							extracells += "<td class='weekNumber'>&nbsp;</td>"
						}
					}
					dayPointer = dayPointer + 1;
					extracells += "<td class='extraCells' align=right>&nbsp;</td>";
				}
				sHTML += extracells;
			}
    
    sHTML += "</tr></table>";
    var sLegend = "<table id='legendTable' cellspacing=1 cellpadding=1><tr id='innerLegendTR'>";
    sLegend += "<td width='25%'><%=LabelBundle.getString("FMS.HolidayLegend")%>:</td>";
    sLegend += "<td width='25%' class=LegendGenHldy><%=LabelBundle.getString("FMS.GenHoliday")%></td>";
    sLegend += "<td width='25%' class=LegendOptHldy><%=LabelBundle.getString("FMS.OptHoliday")%></td>";
    sLegend += "<td width='25%' class=LegendSatHldy><%=LabelBundle.getString("FMS.SatHoliday")%></td>";
    sLegend += "</tr></table></font>";
	document.getElementById("lblLegend").innerHTML   = sLegend
    
		document.getElementById("calcontent").innerHTML   = sHTML
   	document.getElementById("spanMonth").innerHTML = "&nbsp;" +	monthName[monthSelected] + "&nbsp;<img id='changeMonth' SRC='"+imgDir+"drop1.gif' WIDTH='12' HEIGHT='10' BORDER=0></img>"
		document.getElementById("spanYear").innerHTML =	"&nbsp;" + yearSelected	+ "&nbsp;<img id='changeYear' SRC='"+imgDir+"drop1.gif' WIDTH='12' HEIGHT='10' BORDER=0></img>"
  
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
				if (ctlNow!=ctl) {K(ctl, ctl2, format)}
			}
			ctlNow = ctl
		}

	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>
Calendar
</title>
</head>
<%String str=request.getParameter("requestSent");  %>
<body style="background-color: transparent;">
<script type="text/javascript">
	// changed by 202414 
	init ();
</script>
</body>
</html>
<%}catch(Exception e)
{
	e.printStackTrace();
}%>