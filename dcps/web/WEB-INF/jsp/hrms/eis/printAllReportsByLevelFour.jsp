
<%
try {
%>

<%@ include file="../../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="java.util.List"%>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/Address.js"/>">
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<fmt:setBundle basename="resources.eis.eis_common_lables"	var="adminTokenNumberLabel" scope="request" />
<fmt:setBundle basename="resources.Payroll" var="commonLable" scope="request"/>
<fmt:message var="payBill" key="paybillTypeId" bundle="${commonLable}" scope="request"> </fmt:message>
<fmt:message var="arrearBill" key="arrearbillTypeId" bundle="${commonLable}" scope="request"> </fmt:message>
<fmt:message var="supplPaybillFirst" key="supplPaybillFirst" bundle="${commonLable}" scope="request"> </fmt:message>
<fmt:message var="supplPaybillSecond" key="supplPaybillSecond" bundle="${commonLable}" scope="request"> </fmt:message>
<fmt:message var="supplPaybillThird" key="supplPaybillThird" bundle="${commonLable}" scope="request"> </fmt:message>
<fmt:message var="multipleMonthSupplBill" key="multipleMonthSupplBill" bundle="${commonLable}" scope="request"> </fmt:message>
<fmt:setBundle basename="resources.onlinebillprep.CommonAlerts" var="onlinebillcmnAlerts" scope="application"/>
<fmt:message var="created" key="created" bundle="${commonLable}" scope="request"> </fmt:message>
<fmt:message var="approved" key="approved" bundle="${commonLable}" scope="request"> </fmt:message>
<script src="http://code.jquery.com/jquery-1.4.3.min.js" type="text/javascript"></script> 

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="monthList" value="${resValue.monthList}" ></c:set>
<c:set var="yearList" value="${resValue.yearList}" ></c:set>
<c:set var="dataList" value="${resValue.DataList}" ></c:set>
<c:set var="curmonth" value="${resValue.Month}" ></c:set>
<c:set var="curyear" value="${resValue.Year}" ></c:set>
<c:set var="billNosList" value="${resValue.BillList}" ></c:set>
<c:set var="curbill" value="${resValue.billNo}" ></c:set>



<c:set var="Month" value="${resValue.Month}" ></c:set>
<c:set var="Year" value="${resValue.Year}" ></c:set>
<c:set var="billNo" value="${resValue.billNo}" ></c:set>
<c:set var="SeriesList" value="${resValue.SeriesList}" ></c:set>
<c:set var="SeriesListForClassIVSize" value="${resValue.SeriesListForClassIVSize}" ></c:set>
<c:set var="SeriesListForClassIV" value="${resValue.SeriesListForClassIV}" ></c:set>
<c:set var="gisList" value="${resValue.gisList}" ></c:set>
<c:set var="locId" value="${resValue.locId}" ></c:set>


<c:set var="netTotalAmountForInner" value="${resValue.netTotalAmountForInner}" ></c:set>
<c:set var="gpfAbstract" value="${resValue.gpfAbstract}" ></c:set>
<c:set var="gpfOtherThanClassIV" value="${resValue.gpfOtherThanClassIV}" ></c:set>
<c:set var="gpfClassIV" value="${resValue.gpfClassIV}" ></c:set>
<c:set var="formXII" value="${resValue.formXII}" ></c:set>
<c:set var="incomeTax" value="${resValue.incomeTax}" ></c:set>
<c:set var="professionTax" value="${resValue.professionTax}" ></c:set>
<c:set var="HRR" value="${resValue.HRR}" ></c:set>
<c:set var="coHsgSoc" value="${resValue.coHsgSoc}" ></c:set>
<c:set var="coHsgSocInterest" value="${resValue.coHsgSocInterest}" ></c:set>
<c:set var="gis" value="${resValue.gis}" ></c:set>
<c:set var="centralGis" value="${resValue.centralGis}" ></c:set>
<c:set var="gisIps" value="${resValue.gisIps}" ></c:set>
<c:set var="gisIas" value="${resValue.gisIas}" ></c:set>
<c:set var="gisIfs" value="${resValue.gisIfs}" ></c:set>
<c:set var="computerAdv" value="${resValue.computerAdv}" ></c:set>
<c:set var="hbaHousePrin" value="${resValue.hbaHousePrin}" ></c:set>
<c:set var="mcaLand" value="${resValue.mcaLand}" ></c:set>
<c:set var="hbaLand" value="${resValue.hbaLand}" ></c:set>
<c:set var="FA" value="${resValue.FA}" ></c:set>
<c:set var="PA" value="${resValue.PA}" ></c:set>
<c:set var="TA" value="${resValue.TA}" ></c:set>
<c:set var="excesPayRecInt" value="${resValue.excesPayRecInt}" ></c:set>
<c:set var="hbaHouseInt" value="${resValue.hbaHouseInt}" ></c:set>
<c:set var="mcaLandInt" value="${resValue.mcaLandInt}" ></c:set>
<c:set var="hbaLandInt" value="${resValue.hbaLandInt}" ></c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>
<c:set var="netTotalAmountForInner" value="${resValue.netTotalAmountForInner}" ></c:set>
<c:set var="accPolicy" value="${resValue.accPolicy}" ></c:set>


<%
List dataList = (List) pageContext.getAttribute("dataList");
int size = 0;
if(dataList != null)
{
	size = dataList.size();
}
pageContext.setAttribute("listSize",size);


%>



<script>




function expandCollapseFormain(subdiv,maindiv)
{
	//alert('coming 	'+document.getElementById(id));
	//$(document).ready(function()
	//		{
	//	var src = ($("#mainData").attr("src") === "images/tree_images/radio_off.gif") ? "images/tree_images/radio_on.gif" : "images/tree_images/radio_off.gif";       
	//	$("#mainData").attr("src", src);

	//	$("#mainDiv").toggle("slow");
	//});
	//return false;
	if(document.getElementById(maindiv).checked == true){
		//alert('Inside If');
		document.getElementById(subdiv).style.display='';
	}
	else{
		//alert('Inside Else');
		document.getElementById(subdiv).style.display='none';;
	}
	//alert('coming after'+document.getElementById(id));

	
} 

function expandCollapseForPrincipleLoans(subdiv,maindiv)
{
	//alert('coming');
	/*$(document).ready(function()
			{
		var src = ($("#princData").attr("src") === "images/tree_images/radio_off.gif") ? "images/tree_images/radio_on.gif" : "images/tree_images/radio_off.gif";       
		$("#princData").attr("src", src);

		$("#princDiv").toggle("slow");
	});
	return false;*/
	if(document.getElementById(maindiv).checked == true){
		//alert('Inside If');
		document.getElementById(subdiv).style.display='';
	}
	else{
		//alert('Inside Else');
		document.getElementById(subdiv).style.display='none';;
	}
} 

function expandCollapseForInterestLoans(subdiv,maindiv)
{
	//alert('coming');
	/*$(document).ready(function()
			{
		var src = ($("#historyData").attr("src") === "images/tree_images/radio_off.gif") ? "images/tree_images/radio_on.gif" : "images/tree_images/radio_off.gif";       
		$("#historyData").attr("src", src);

		$("#historyDiv").toggle("slow");
	});
	return false;*/
	if(document.getElementById(maindiv).checked == true){
		//alert('Inside If');
		document.getElementById(subdiv).style.display='';
	}
	else{
		//alert('Inside Else');
		document.getElementById(subdiv).style.display='none';;
	}
} 

function expandCollapseForCohsg(subdiv,maindiv)
{
	//alert('class iv');
	/*$(document).ready(function()
			{
		var src = ($("#cohsgData").attr("src") === "images/tree_images/radio_off.gif") ? "images/tree_images/radio_on.gif" : "images/tree_images/radio_off.gif";       
		$("#cohsgData").attr("src", src);

		$("#cohsgDiv").toggle("slow");
	});
	return false;*/
	if(document.getElementById(maindiv).checked == true){
		//alert('Inside If');
		document.getElementById(subdiv).style.display='';
	}
	else{
		//alert('Inside Else');
		document.getElementById(subdiv).style.display='none';;
	}
} 

function expandCollapseForFormB(subdiv,maindiv)
{
	//alert('class iv');
	/*$(document).ready(function()
			{
		var src = ($("#formbData").attr("src") === "images/tree_images/radio_off.gif") ? "images/tree_images/radio_on.gif" : "images/tree_images/radio_off.gif";       
		$("#formbData").attr("src", src);

		$("#formbDiv").toggle("slow");
	});
	return false;*/
	if(document.getElementById(maindiv).checked == true){
		//alert('Inside If');
		document.getElementById(subdiv).style.display='';
	}
	else{
		//alert('Inside Else');
		document.getElementById(subdiv).style.display='none';;
	}
} 

function expandCollapseForGpfOtherThanClassiv(subdiv,maindiv)
{
	//alert('class iv');
	/*$(document).ready(function()
			{
		var src = ($("#gpfotherData").attr("src") === "images/tree_images/radio_off.gif") ? "images/tree_images/radio_on.gif" : "images/tree_images/radio_off.gif";       
		$("#gpfotherData").attr("src", src);

		$("#gpfotherDiv").toggle("slow");
	});
	return false;*/
	if(document.getElementById(maindiv).checked == true){
		//alert('Inside If');
		document.getElementById(subdiv).style.display='';
	}
	else{
		//alert('Inside Else');
		document.getElementById(subdiv).style.display='none';;
	}
} 



function expandCollapseForClassiv(subdiv,maindiv)
{
	//alert('class iv');
	/*$(document).ready(function()
			{
		var src = ($("#classivData").attr("src") === "images/tree_images/radio_off.gif") ? "images/tree_images/radio_on.gif" : "images/tree_images/radio_off.gif";       
		$("#classivData").attr("src", src);

		$("#classivDiv").toggle("slow");
	});
	return false;*/
	if(document.getElementById(maindiv).checked == true){
		//alert('Inside If');
		document.getElementById(subdiv).style.display='';
	}
	else{
		//alert('Inside Else');
		document.getElementById(subdiv).style.display='none';;
	}
} 


function expandCollapseForgis(subdiv,maindiv)
{
	//alert('class iv');
	/*$(document).ready(function()
			{
		var src = ($("#gisData").attr("src") === "images/tree_images/radio_off.gif") ? "images/tree_images/radio_on.gif" : "images/tree_images/radio_off.gif";       
		$("#gisData").attr("src", src);

		$("#gisDiv").toggle("slow");
	});
	return false;*/
	if(document.getElementById(maindiv).checked == true){
		//alert('Inside If');
		document.getElementById(subdiv).style.display='';
	}
	else{
		//alert('Inside Else');
		document.getElementById(subdiv).style.display='none';;
	}
} 

function expandCollapseForother(subdiv,maindiv)
{
	//alert('class iv');
	/*$(document).ready(function()
			{
		var src = ($("#otherData").attr("src") === "images/tree_images/radio_off.gif") ? "images/tree_images/radio_on.gif" : "images/tree_images/radio_off.gif";       
		$("#otherData").attr("src", src);

		$("#otherDiv").toggle("slow");
	});
	return false;*/
	if(document.getElementById(maindiv).checked == true){
		//alert('Inside If');
		document.getElementById(subdiv).style.display='';
	}
	else{
		//alert('Inside Else');
		document.getElementById(subdiv).style.display='none';;
	}
} 


if('${msg}'!=null && '${msg}'!='')
{
	alert('${msg}');
}

function ShowReports()
{

	//alert('ShowPayBill');
	var Month=document.printAllReports.selMonth.value; 
	var Year=document.printAllReports.selYear.value;
	var bill = document.printAllReports.billNo.value;

	
	if(!Month)
	{
			alert("Please Select The Month");
			return false;
	}
	if(!Year)
	{
		alert("Please Select The Year");
		return false;
	}
	if(!bill)
	{
		alert("Please Select The Bill Description");
		return false;
	}		
	document.printAllReports.action = "./hdiits.htm?actionFlag=viewAllReports&Month=" + Month + "&Year=" + Year + "&billNo="+bill; 
	document.printAllReports.submit();
	showProgressbar("Please wait...");
}



function ShowPayBill(value)
{
var reportCode=value;

   	var urlstring ;
    if(value==5000008)
    	    {
      
   		urlstring = "hrms.htm?actionFlag=reportService&reportCode=5000008&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&billNo=${billNo}&$FLAG=${billNo} ";
    	    }

    
    else if(value==5000033)
    {
    	//alert('Inside Pagewise');
    	urlstring = "hrms.htm?actionFlag=reportService&reportCode=5000033&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&billNo=${billNo}&$FLAG=${billNo} ";
    }
    
    else if(value==5000012)
    {
    	//alert('Inside Groupwise');
    	urlstring = "hrms.htm?actionFlag=reportService&reportCode=5000012&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&Bill No=${billNo}&$FLAG=${billNo} ";
    }

    
    else if(value==5000014)
    {
    	//alert('Inside GPF Abstract');
    	urlstring = "hrms.htm?actionFlag=reportService&reportCode=5000014&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&Bill No=${billNo}&$FLAG=${billNo} ";
    }

    
    else if(value==5000026)
    {
    	//alert('Inside Form XII');
    	urlstring = "hrms.htm?actionFlag=reportService&reportCode=5000026&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&Bill No=${billNo}&$FLAG=${billNo} ";
    }
    
    else if(value==5000009)
    {
    	//alert('Inside Acquittance Roll');
    	urlstring = "hrms.htm?actionFlag=reportService&reportCode=5000009&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&Bill No=${billNo}&Department=${locId} ";
    }
    

    else if(value==5000002)
    {
    	//alert('Inside Income Tax');
    	urlstring = "hrms.htm?actionFlag=reportService&reportCode=5000002&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&Bill No=${billNo}&$FLAG=${billNo} ";
    }

    else if(value==5000003)
    {
    	//alert('Inside Profession Tax');
    	urlstring = "hrms.htm?actionFlag=reportService&reportCode=5000003&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&Bill No=${billNo}&$FLAG=${billNo} ";
    }

    else if(value==8000057)
    {
    	//alert('Inside PLI');
    	urlstring = "hrms.htm?actionFlag=reportService&reportCode=8000057&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&Bill No=${billNo}&$FLAG=${billNo} ";
    }
    
    else if(value==5000004)
    {
    	//alert('Inside Bank Statement');
    	
    	urlstring = "hrms.htm?actionFlag=reportService&reportCode=5000004&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&Bill No=${billNo}&$FLAG=${billNo} ";
    }
    
  
    else if(value==5000016 || value==50000166)
    {
    	
    	if(value==5000016)
    	{
    		//alert('Inside Principle');
    	urlstring = "hrms.htm?actionFlag=reportService&reportCode=5000016&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&Bill No=${billNo}&Type=P ";
    	}
    	else
    	{
    	urlstring = "hrms.htm?actionFlag=reportService&reportCode=5000016&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&Bill No=${billNo}&Type=I ";
    	}
    }

    else if(value==5000010)
    {
	urlstring = "hrms.htm?actionFlag=reportService&reportCode=5000010&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&BillNo=${billNo}&Series="+gpfSeries ;
    	
    }

    else if(value==50000100)
    {
    	//alert('Inside GPF ClassIV');
    	var BillNo = document.getElementById("billNum"+i).value;
    	//alert(BillNo);
	urlstring = "hrms.htm?actionFlag=reportService&reportCode=5000010&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&BillNo=${billNo}&Class Type="+ClassType+"&Series="+gpfforclassivSeries ;
    	
    }

    else if (value==8000200)
    {
    	//alert('Hiiii')
    	urlstring = "hrms.htm?actionFlag=reportService&reportCode=8000200&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&BillNo=${billNo}" ;
    	//alert('Bye')
    }
    
    else if(value==5000013 || value==50000131 || value==50000132)
    {

    	if(value==5000013)
		{
    		//alert('FA');
    	urlstring = "hrms.htm?actionFlag=reportService&reportCode=5000013&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&Bill No=${billNo}&Formb Type=FA ";
		}
		else if(value==50000131)
		{
			//alert('TA');
		urlstring = "hrms.htm?actionFlag=reportService&reportCode=5000013&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&Bill No=${billNo}&Formb Type=TA";
		}
		else
		{
			//alert('PA');
		urlstring = "hrms.htm?actionFlag=reportService&reportCode=5000013&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&Bill No=${billNo}&Formb Type=PA";
		}
		
    }


    else if(value==5000027 || value==50000271 || value==50000272 || value==50000273 || value==50000274 || value==50000275 || value==50000276)
    {

    	if(value==5000027)
		{
    		//alert('Computer Adv');
    	urlstring = "hrms.htm?actionFlag=reportService&reportCode=5000027&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&Bill No=${billNo}&Department=${locId}&LoanID=58 ";
		}
    	else if(value==50000271)
		{
    		//alert('HBA Land');
    		urlstring = "hrms.htm?actionFlag=reportService&reportCode=5000027&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&Bill No=${billNo}&Department=${locId}&LoanID=51 ";
		}
    	else if(value==50000272)
		{
    		//alert('MCA');
    		urlstring = "hrms.htm?actionFlag=reportService&reportCode=5000027&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&Bill No=${billNo}&Department=${locId}&LoanID=56 ";
		}
    	else if(value==50000274)
		{
    		//alert('HBA AIS');
    		urlstring = "hrms.htm?actionFlag=reportService&reportCode=8000059&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&Bill No=${billNo}&Department=${locId}&LoanID=64 ";
		}
    	else if(value==50000275)
		{
    		//alert('MCA AIS');
    		urlstring = "hrms.htm?actionFlag=reportService&reportCode=8000059&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&Bill No=${billNo}&Department=${locId}&LoanID=65 ";
		}
		else if(value==50000276)
		{
    		//alert('Oth Veh Adv');
    		urlstring = "hrms.htm?actionFlag=reportService&reportCode=5000027&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&Bill No=${billNo}&Department=${locId}&LoanID=57 ";
		}
    	else
		{
    		//alert('HBA For Construction');
    		urlstring = "hrms.htm?actionFlag=reportService&reportCode=5000027&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&Bill No=${billNo}&Department=${locId}&LoanID=67 ";
		}
        }
        

    else if(value==5000006 || value==50000061 || value==50000062 || value==50000063 || value==50000064 || value==50000065 || value==50000066 || value==50000067 || value==50000068 || value==50000069)
    {

    	if(value==5000006)
		{
    		//alert('HBA For Construction');
    		urlstring = "hrms.htm?actionFlag=reportService&reportCode=5000006&dynamicReport=True&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&Bill No=${billNo}&Department=${locId}&HBA/CA=73 ";
		}
		else if(value==50000061)
		{
			//alert('M.C.A');
			urlstring = "hrms.htm?actionFlag=reportService&reportCode=5000006&dynamicReport=True&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&Bill No=${billNo}&Department=${locId}&HBA/CA=70 ";
		}
		else if(value==50000062)
		{
			//alert('FA');
			urlstring = "hrms.htm?actionFlag=reportService&reportCode=5000006&dynamicReport=True&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&Bill No=${billNo}&Department=${locId}&HBA/CA=59 ";
		}
		else if(value==50000063)
		{
			//alert('TA');
			urlstring = "hrms.htm?actionFlag=reportService&reportCode=5000006&dynamicReport=True&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&Bill No=${billNo}&Department=${locId}&HBA/CA=53 ";
		}
		else if(value==50000064)
		{
			//alert('PA');
			urlstring = "hrms.htm?actionFlag=reportService&reportCode=5000006&dynamicReport=True&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&Bill No=${billNo}&Department=${locId}&HBA/CA=52 ";
		}
		else if(value==50000065)
		{
			//alert('Excess Payment');
			urlstring = "hrms.htm?actionFlag=reportService&reportCode=5000006&dynamicReport=True&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&Bill No=${billNo}&Department=${locId}&HBA/CA=68 ";
		}
		else if(value==50000067)
		{
			//alert('HBA AIS');
			urlstring = "hrms.htm?actionFlag=reportService&reportCode=8000059&dynamicReport=True&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&Bill No=${billNo}&Department=${locId}&LoanID=71 ";
		}
		else if(value==50000068)
		{
			//alert('MCA AIS');
			urlstring = "hrms.htm?actionFlag=reportService&reportCode=8000059&dynamicReport=True&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&Bill No=${billNo}&Department=${locId}&LoanID=69 ";
		}
		else if(value==50000069)
		{
			//alert('Oth Veh Adv Int');
			urlstring = "hrms.htm?actionFlag=reportService&reportCode=5000006&dynamicReport=True&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&Bill No=${billNo}&Department=${locId}&HBA/CA=78 ";
		}
		else
		{
			//alert('HBA For Land');
			urlstring = "hrms.htm?actionFlag=reportService&reportCode=5000006&dynamicReport=True&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&Bill No=${billNo}&Department=${locId}&HBA/CA=72 ";
		}
		
    	
        }
    else if(value==5000011)
    {
    	//alert('Inside HRR');
    	urlstring = "hrms.htm?actionFlag=reportService&reportCode=5000011&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&Bill No=${billNo}&$FLAG=${billNo} ";
    }
    
    else if(value==1005 || value==1004 || value==1002 || value==1001 || value==1000)
    {
    	if(value==1005)
		{
    		//alert('GIS');
    	urlstring="hrms.htm?actionFlag=getGisData&month=${Month}&year=${Year}&billNo=${billNo}&GIS=1005 ";
		}
    	else if(value==1004)
		{
    	//	alert('CGIS');
    	urlstring="hrms.htm?actionFlag=getGisData&month=${Month}&year=${Year}&billNo=${billNo}&GIS=1004 ";
		}
    	else if(value==1002)
		{
    		//alert('GIS(IFS)');
    	urlstring="hrms.htm?actionFlag=getGisData&month=${Month}&year=${Year}&billNo=${billNo}&GIS=1002 ";
		}
    	else if(value==1001)
		{
    		//alert('GIS(IAS)');
    	urlstring="hrms.htm?actionFlag=getGisData&month=${Month}&year=${Year}&billNo=${billNo}&GIS=1001 ";
		}
    	else 
		{
    		//alert('GIS(IPS)');
    	urlstring="hrms.htm?actionFlag=getGisData&month=${Month}&year=${Year}&billNo=${billNo}&GIS=1000 ";
		}
    	//var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,status=yes,menubar=no,location=no,scrollbars=yes,top=20,left=200';
    	//window.open(urlstring,"",urlstyle);
    }


    else if(value==900)
    {

    	//alert('Inside Outer'+value);
    	urlstring="hrms.htm?actionFlag=getOuterData&month=${Month}&year=${Year}&billNo=${billNo} ";
    	// urlstyle = 'height=600,width=1400,toolbar=no,minimize=no,resizable=yes,status=yes,menubar=no,location=no,scrollbars=yes,top=20,left=200';
    	//window.open(urlstring,"",urlstyle);
    }

   // if(value!=900 || value!=1005 || value!=1004 || value!=1002 || value!=1001 || value!=1000)
    //{
    	//alert('Inside Other');
    	var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
     	window.open(urlstring,"",urlstyle);
    	
    //}
}

function ShowPayBillForOtherThanClassIV(i)
{
	//alert('Inside GPF OtherThan ClassIV');
	var gpfNum = document.getElementById("gpfNum"+i).value;
	//alert(gpfNum);
	var urlstring = "hrms.htm?actionFlag=reportService&reportCode=5000010&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&BillNo=${billNo}&Series="+gpfNum ;
	var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
  	window.open(urlstring,"",urlstyle);

}
function ShowPayBillForClassIV(i)
{
	//alert('Inside GPF ClassIV');
	var gpfNum = document.getElementById("gpfClassIVNum"+i).value;
	//alert(gpfNum);
	var ClassType='ClassIV';
	var urlstring = "hrms.htm?actionFlag=reportService&reportCode=5000010&action=generateReport&FromParaPage=TRUE&customReportPage=ReportingFrameworkReportPagePopup&backBtn=0&Month=${Month}&Year=${Year}&BillNo=${billNo}&Class Type="+ClassType+"&Series="+gpfNum ;
	var urlstyle = 'height=600,width=1400,toolbar=no,minimize=yes,resizable=yes,header=no,status=no,menubar=no,directories=no,fullscreen=no,location=no,scrollbars=yes,top=20,left=200';
  	window.open(urlstring,"",urlstyle);
}
function closeFunction()
{
	//alert("in close...");
	document.printAllReports.action='./hrms.htm?actionFlag=getHomePage';
	document.printAllReports.submit();
}
 
</script>

<hdiits:form name="printAllReports" validate="true" method="POST" action=" ">

	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>All Reports
		</b></a></li>
	</ul>
	</div>
		
	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >
	<br/>
	<table width="85%" border="1" bordercolor="black"  align="center" id="searchTable" rules="">
	<tr>
		<td  align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"> MONTH </td>
		<td  align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"> YEAR </td>
		
	</tr>
	<tr>
		<td align = "center">
			<hdiits:select name="selMonth" size="1" sort="false" caption="Month" id="selMonth" validation="sel.isrequired" mandatory="true"> 
					<hdiits:option value="">-------Selected--------</hdiits:option>
					<c:forEach items="${monthList}" var="month">
						<c:choose>
							<c:when test="${month.lookupShortName==curmonth}">
								<hdiits:option value="${month.lookupShortName}" selected="true" > ${month.lookupDesc} </hdiits:option>
							</c:when>
							<c:otherwise>
								<hdiits:option value="${month.lookupShortName}"> ${month.lookupDesc} </hdiits:option>
							</c:otherwise>
						</c:choose>
				</c:forEach>
		   </hdiits:select>
      </td>
      <td align = "center">
      		<hdiits:select name="selYear" size="1" sort="false" caption="Year" id="selYear" validation="sel.isrequired" mandatory="true"> 
				<hdiits:option value="">-------Selected--------</hdiits:option>
					<c:forEach items="${yearList}" var="year">
						<c:choose>
							<c:when test="${year.lookupShortName == curyear}">
								<hdiits:option value="${year.lookupShortName}" selected="true" > ${year.lookupDesc} </hdiits:option>
							</c:when>
							<c:otherwise>
								<hdiits:option value="${year.lookupShortName}"> ${year.lookupDesc} </hdiits:option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
		   	</hdiits:select>
      </td>
      </tr>
      </table>
     <table width="85%" border="1" bordercolor="black"  align="center" id="searchTable" rules="">
      <tr>
      <td  align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"> BILL DESCRIPTION </td>
      </tr>
      <tr>
      <td align = "center">
      		<hdiits:select name="billNo"  id="billNo" size="1" caption="Bill No" sort="false" onchange= "ShowTokenDetails()" mandatory="true"> 
				<hdiits:option value="" selected="true">-------Selected--------</hdiits:option>
			<c:forEach items="${billNosList}" var="billNosList">
			<c:choose>
				<c:when test="${billNosList.dcpsDdoBillGroupId == curbill}">
						<hdiits:option value="${billNosList.dcpsDdoBillGroupId}" selected="true" > ${billNosList.dcpsDdoBillDescription}</hdiits:option>
				</c:when>
					<c:otherwise>
						<hdiits:option value="${billNosList.dcpsDdoBillGroupId}"> ${billNosList.dcpsDdoBillDescription}</hdiits:option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			</hdiits:select>		       
     </td>
    </tr>
</table>

	<br>
	<center style="width: 100%">
	<hdiits:button name="btn" value="showReports" caption="Show Reports" style="width:15%"
	id="tokenview" captionid="Show Reports" onclick="ShowReports()" type="button"/>
 
	</center>
	
	<br><br>
	
	<c:if test="${listSize gt 0 && netTotalAmountForInner gt 0}">
	
<display:table requestURI="" style="width:100%" name="${dataList}" id="row" length="50">
		<display:column style="text-align: center;font-size:12px;"  class="tablecelltext" title="Report Name" headerClass="datatableheader">
			

<c:if test="${row.netTotalAmountForInner gt 0}">


 
	<div style="line-height: 22px;border-top: 1px solid #ccc;width: 100%" align="left">	
				&nbsp;&nbsp;&nbsp;
				<%--<img name="mainData${count}" id="mainData" src="images/tree_images/radio_off.gif" onclick="expandCollapseFormain('mainDiv${count}')"></img>
				 --%>
				 <input type="checkbox" id ="mainData${count}" name = "mainData${count}" onclick="expandCollapseFormain('mainDiv${count}','mainData${count}')"> 			
			<font size="3" >Main Reports</font>
			</div>
 

		<div id="mainDiv${count}" style="display:none"  align="left">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="javascript:void(0)" onclick="ShowPayBill(${"900"})" >
					<font size="3">Outer</font>
			</a>
				
			<br>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="javascript:void(0)" onclick="ShowPayBill(${"5000008"})">
				<font size="3">Inner</font>
			</a>	
		
		   <br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="javascript:void(0)" onclick="ShowPayBill(${"5000033"})">
				<font size="3">PageWise</font>
			</a>
				
		   <br>
		   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="javascript:void(0)" onclick="ShowPayBill(${"5000012"})">
				<font size="3">GroupWise</font>
			</a>

		   <br>
		   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="javascript:void(0)" onclick="ShowPayBill(${"5000004"})">
			    <font size="3">Bank Statement</font>
			</a>
			
			<br>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="javascript:void(0)" onclick="ShowPayBill(${"5000009"})">
			    <font size="3">Aquittance Roll</font>
		    </a>
		  
		    <br>
	
<c:if test="${row.gpfAbstract gt 0}">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="javascript:void(0)" onclick="ShowPayBill(${"5000014"})">
				<font size="3">GPF Abstract</font>
			</a>
			
		<br>
	
</c:if>


	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<c:if test="${row.formXII gt 0 && SeriesListForClassIVSize gt 0}">
			<a href="javascript:void(0)" onclick="ShowPayBill(${"5000026"})">
				<font size="3">FormXII</font>
			</a>
			<br />
   </c:if>
   
   
  </div>
</c:if>

&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
 
	
<c:if test="${row.computerAdv gt 0 or row.hbaHousePrin gt 0 or row.mcaLand gt 0 or row.hbaLand gt 0 or row.hbaAIS gt 0 or row.mcaAIS gt 0 or row.vehAdv gt 0}">

			<div style="line-height: 22px;border-top: 1px solid #ccc;width: 100%" align="left">	
			 &nbsp;&nbsp;&nbsp;
			<%-- <img name="princData${count}" id="princData" src="images/tree_images/radio_off.gif" onclick="expandCollapseForPrincipleLoans()"></img> --%>
			<input type="checkbox" id ="princData${count}" name = "princData${count}" onclick="expandCollapseForPrincipleLoans('princDiv${count}','princData${count}')">			
			<font size="3" >	 Principle Loans & Advances </font>
			</div>
 

		   <div id="princDiv${count}" style="display:none"  align="left">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<br>
				
				<c:if test="${row.computerAdv gt 0}">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="ShowPayBill(${"5000027"})"><font size="3">Computer Adv</font></a>
				<br>
				</c:if>
				
				<c:if test="${row.hbaLand gt 0}">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="ShowPayBill(${"50000271"})"><font size="3">HBA Land</font></a>
				<br>
				</c:if>
				
				<c:if test="${row.mcaLand gt 0}">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="ShowPayBill(${"50000272"})"><font size="3">MCA</font></a>
				<br>
				</c:if>
				
				<c:if test="${row.hbaHousePrin gt 0}">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="ShowPayBill(${"50000273"})"><font size="3">HBA For Construction</font></a>
				<br>
				</c:if>
				
				<c:if test="${row.hbaAIS gt 0}">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="ShowPayBill(${"50000274"})"><font size="3">HBA AIS</font></a>
				<br>
				</c:if>
				
				<c:if test="${row.mcaAIS gt 0}">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="ShowPayBill(${"50000275"})"><font size="3">MCA AIS</font></a>
				<br>
				</c:if>
				
				<c:if test="${row.vehAdv gt 0}">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="ShowPayBill(${"50000276"})"><font size="3">Oth Veh Adv</font></a>
				<br>
				</c:if>				
				</div>
				
			<br />
</c:if>


			

<c:if test="${row.hbaHouseInt gt 0 or row.mcaLandInt gt 0 or row.FA gt 0 or row.TA gt 0 or row.PA gt 0 or row.excesPayRecInt gt 0 or row.hbaLandInt gt 0 or row.hbaAisInt gt 0 or row.mcaAisInt gt 0 or row.vehAdvInt gt 0}">


	<div style="line-height: 22px;border-top: 1px solid #ccc;width: 100%" align="left">		
	 &nbsp;&nbsp;&nbsp;		
			<font size="3"  >
	<%--	<img name="historyData${count}" id="historyData" src="images/tree_images/radio_off.gif" onclick="expandCollapseForInterestLoans()"></img> --%>
			<input type="checkbox" id ="historyData${count}" name = "historyData${count}" onclick="expandCollapseForPrincipleLoans('historyDiv${count}','historyData${count}')">
				Interest Loans & Advances</font>
			</div>
 		
				<div id="historyDiv${count}" style="display:none"  align="left">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<br>
				<c:if test="${row.hbaHouseInt gt 0}">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="ShowPayBill(${"5000006"})"><font size="3">HBA For Construction</font></a>
				<br>
				</c:if>
				
				<c:if test="${row.mcaLandInt gt 0}">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="ShowPayBill(${"50000061"})"><font size="3">M.C.A</font></a>
				<br>
				</c:if>
				
				<c:if test="${row.FA gt 0}">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="ShowPayBill(${"50000062"})"><font size="3">FA</font></a>
				<br>
				</c:if>
				
				<c:if test="${row.TA gt 0}">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="ShowPayBill(${"50000063"})"><font size="3">TA</font></a>
				<br>
				</c:if>
				
				<c:if test="${row.PA gt 0}">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="ShowPayBill(${"50000064"})"><font size="3">PA</font></a>
				<br>
				</c:if>
				
				<c:if test="${row.excesPayRecInt gt 0}">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="ShowPayBill(${"50000065"})"><font size="3">Exc.PayRc</font></a>
				<br>
				</c:if>
				
				<c:if test="${row.hbaLandInt gt 0}">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="ShowPayBill(${"50000066"})"><font size="3">HBA For Land</font></a>
				<br>
				</c:if>
				
				<c:if test="${row.hbaAisInt gt 0}">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="ShowPayBill(${"50000067"})"><font size="3">HBA AIS</font></a>
				<br>
				</c:if>
				
				<c:if test="${row.mcaAisInt gt 0}">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="ShowPayBill(${"50000068"})"><font size="3">MCA AIS</font></a>
				<br>
				</c:if>

				<c:if test="${row.vehAdvInt gt 0}">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="ShowPayBill(${"50000069"})"><font size="3">Oth Veh Adv</font></a>
				</c:if>
				
				</div>
			
			<br />
</c:if>

<c:if test="${row.coHsgSoc gt 0 or row.coHsgSocInterest gt 0}">

			<div style="line-height: 22px;border-top: 1px solid #ccc;width: 100%" align="left">	
			 &nbsp;&nbsp;&nbsp;
		<%--	<img name="cohsgData${count}" id="cohsgData" src="images/tree_images/radio_off.gif" onclick="expandCollapseForCohsg()"></img> --%>
			<input type="checkbox" id ="cohsgData${count}" name = "cohsgData${count}" onclick="expandCollapseForCohsg('cohsgDiv${count}','cohsgData${count}')">
			<font size="3" > Co.Op.Hsg.Soc. </font>
			</div>
 
				
				<div id="cohsgDiv${count}" style="display:none"  align="left">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<br>
				<c:if test="${row.coHsgSoc gt 0}">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="ShowPayBill(${"5000016"})"><font size="3">Principle</font></a>
				<br>
				</c:if>
				
				<c:if test="${row.coHsgSocInterest gt 0}">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="ShowPayBill(${"50000166"})"><font size="3">Interest</font></a>
				</c:if>
			</div>
			<br />
</c:if>

<c:if test="${row.FA gt 0 or row.TA gt 0 or row.PA gt 0}" >


				<div style="line-height: 22px;border-top: 1px solid #ccc;width: 100%" align="left">		
				 &nbsp;&nbsp;&nbsp;
			<%--<img name="formbData${count}" id="formbData" src="images/tree_images/radio_off.gif" onclick="expandCollapseForFormB()"></img> --%>
				<input type="checkbox" id ="formbData${count}" name = "formbData${count}" onclick="expandCollapseForFormB('formbDiv${count}','formbData${count}')">		
				<font size="3" > Form B Consolidated </font>
				</div>
 

			<div id="formbDiv${count}" style="display:none"  align="left">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<br>
				<c:if test="${row.FA gt 0}">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="ShowPayBill(${"5000013"})"><font size="3">FA</font></a>
				<br>
				</c:if>
				
				<c:if test="${row.TA gt 0}">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="ShowPayBill(${"50000131"})"><font size="3">TA</font></a>
				<br>
				</c:if>
				
				<c:if test="${row.PA gt 0}">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="ShowPayBill(${"50000132"})"><font size="3">PA</font></a>
				</c:if>
				
				</div>
				
			<br />
</c:if>



			<c:if test="${row.gpfOtherThanClassIV gt 0}" >
			
			
			<c:set var = "i" value = "1" ></c:set>
			<div style="line-height: 22px;border-top: 1px solid #ccc;width: 100%" align="left">	
			 &nbsp;&nbsp;&nbsp;
		<%--<img name="gpfotherData${count}" id="gpfotherData" src="images/tree_images/radio_off.gif" onclick="expandCollapseForGpfOtherThanClassiv()"></img> --%>
			<input type="checkbox" id ="gpfotherData${count}" name = "gpfotherData${count}" onclick="expandCollapseForGpfOtherThanClassiv('gpfotherDiv${count}','gpfotherData${count}')">
				<font size="3"  >GPF Other than Class-IV</font>
		   </div>
		 

		  <div id="gpfotherDiv${count}" style="display:none"  align="left">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<br>
			 
				<c:forEach items="${SeriesList}" var="list">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<hdiits:hidden name = "gpfNum${i}" id = "gpfNum${i}" default ="${list.pfSeries}"/>
						<a href="javascript:void(0)" onclick="ShowPayBillForOtherThanClassIV(${i})"><font size="3"><c:out value="${list.pfSeries}" /></font></a>
					 
					<br />
					<c:set var="i" value="${i+1}"></c:set>
				</c:forEach>
				
		 </div>
			
			
			<br />
			
			</c:if>
			
			
			<c:if test="${row.gpfClassIV gt 0 && SeriesListForClassIVSize gt 0}">
			
			<c:set var = "i" value = "1" ></c:set>
			<div style="line-height: 22px;border-top: 1px solid #ccc;width: 100%" align="left">	
			 &nbsp;&nbsp;&nbsp;
		<%-- 	<img name="classivData${count}" id="classivData" src="images/tree_images/radio_off.gif" onclick="expandCollapseForClassiv()"></img> --%>
			<input type="checkbox" id ="classivData${count}" name = "classivData${count}" onclick="expandCollapseForClassiv('classivDiv${count}','classivData${count}')">
				<font size="3"  >GPF Class-IV</font>
				</div>
				 
				
				 <div id="classivDiv${count}" style="display:none"  align="left">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<br>
			
				<c:forEach items="${SeriesListForClassIV}" var="list">
				 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<hdiits:hidden name = "gpfClassIVNum${i}" id = "gpfClassIVNum${i}" default ="${list.pfSeries}"/>
						<a href="javascript:void(0)" onclick="ShowPayBillForClassIV(${i})"><font size="3"><c:out value="${list.pfSeries}" /></font></a>
					 
					<br />
					<c:set var="i" value="${i+1}"></c:set>
				</c:forEach>
				
				
				
				
		 </div>
			
			
			
			<br />
		</c:if>
		
		
	
<c:if test="${row.gis gt 0 or row.centralGis gt o or row.gisIps gt 0 or row.gisIas gt o or row.gisIfs gt o}">


<div style="line-height: 22px;border-top: 1px solid #ccc;width: 100%" align="left">	
 &nbsp;&nbsp;&nbsp;
	<%--<img name="gisData${count}" id="gisData" src="images/tree_images/radio_off.gif" onclick="expandCollapseForgis()"></img> --%>			
	<input type="checkbox" id ="gisData${count}" name = "gisData${count}" onclick="expandCollapseForgis('gisDiv${count}','gisData${count}')">
				 <font size="3"  >GIS Report</font>
				 </div>
				 
				
				 <div id="gisDiv${count}" style="display:none"  align="left">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<br>
				<c:if test="${row.gis gt 0}">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="ShowPayBill(${"1005"})"><font size="3">GIS</font></a>
				<br>
				</c:if>
				
				<c:if test="${row.centralGis gt 0}">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="ShowPayBill(${"1004"})"><font size="3">CGIS</font></a>
				<br>
				</c:if>
				
				<c:if test="${row.gisIas gt 0}">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="ShowPayBill(${"1001"})"><font size="3">GIS(IAS)</font></a>
				<br>
				</c:if>
				
				<c:if test="${row.gisIps gt 0}">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="ShowPayBill(${"1000"})"><font size="3">GIS(IPS)</font></a>
				<br>
				</c:if>
				
				<c:if test="${row.gisIfs gt 0}">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="ShowPayBill(${"1002"})"><font size="3">GIS(IFS)</font></a>
				</c:if>
				
				</div>
			<br />
</c:if>




<c:if test="${row.HRR gt 0 or row.incomeTax gt 0 or row.professionTax gt 0 or row.pli gt 0 or row.accPolicy gt 0}">


			<div style="line-height: 22px;border-top: 1px solid #ccc;width: 100%" align="left">	
			 &nbsp;&nbsp;&nbsp;	
			<%-- <img name="otherData${count}" id="otherData" src="images/tree_images/radio_off.gif" onclick="expandCollapseForother()"></img> --%>
			<input type="checkbox" id ="otherData${count}" name = "otherData${count}" onclick="expandCollapseForother('otherDiv${count}','otherData${count}')">
			<font size="3"  >Other Reports</font>
			</div>
		 
				
				
				 <div id="otherDiv${count}" style="display:none"  align="left">
				 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<br>
					
					<c:if test="${row.HRR gt 0}">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="ShowPayBill(${"5000011"})">
				<font size="3">HRR</font></a>
				<br />
			    </c:if>
				
				<c:if test="${row.incomeTax gt 0}">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0)" onclick="ShowPayBill(${"5000002"})"><font size="3">Income Tax</font></a>
				<br />
			    </c:if>

				<!--<c:if test="${row.professionTax gt 0}">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="javascript:void(0)" onclick="ShowPayBill(${"5000003"})"><font size="3">Profession Tax</font></a>
							<br />
				</c:if>
				
				--><c:if test="${row.pli gt 0}">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="javascript:void(0)" onclick="ShowPayBill(${"8000057"})"><font size="3">PLI</font></a>
							<br />
				</c:if>
				
					<c:if test="${row.accPolicy gt 0}">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="javascript:void(0)" onclick="ShowPayBill(${"8000200"})"><font size="3">Accident Policy</font></a>
								<br />
				</c:if>	

			</div>
			<br />
</c:if>

	
		</display:column>
		
		
</display:table>
</c:if> 
  <c:if test="${listSize eq 0 or netTotalAmountForInner eq 0}">
<center> No Records To Display ! ! !</center>
</c:if>	



<br>
	<center>
 
	<hdiits:button name="closeButton" value="closeButton" caption="Close" 
		id="closeButton" captionid="Close" onclick="closeFunction()" type="button"/>

	</center>
<br>
<br>

<hdiits:validate locale="${locale}" controlNames="" />

<script type="text/javascript">
	initializetabcontent("maintab")
</script>
</div>
</hdiits:form>


<%
}catch(Exception e) {e.printStackTrace();}
%>
	