<%

try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page session="true" %>
<script type="text/javascript"   
	src="common/script/tagLibValidation.js">    
</script>

<script type="text/javascript"  
    src="common/script/commonfunctions.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="leaveList" value="${resValue.leaveList}"> </c:set>
<c:set var="actionList" value="${resValue.actionList}"> </c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>
<c:set var="emptypeid" value="${resValue.empTypeId}"></c:set>

<c:set var="empId" value="${resValue.empId}"></c:set>
<c:set var="empAllRec" value="${resValue.empAllRec}"></c:set>
<c:set var="empAllRecFlag" value="${resValue.empAllRecFlag}"></c:set>

<fmt:setBundle basename="resources.Payroll" var="commonLable" scope="request"/>
<fmt:message var="contractEmpType" key="contract" bundle="${commonLable}" scope="request"> </fmt:message>
<fmt:message var="fixedEmpType" key="fixed" bundle="${commonLable}" scope="request"> </fmt:message>
<fmt:message var="probationEmpType" key="probation" bundle="${commonLable}" scope="request"> </fmt:message>


<script>

function checkleavedate()
{
	var retValue=true;
		try 
		{   
			xmlHttp=new XMLHttpRequest();
	   	}
		catch(e)
		{    
			// Internet Explorer    
			try 
			{
		    	xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");   
	    	} 
		    catch (e) 
		    {
			 	try 
			 	{
		        	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
	    	   	}
				catch (e) 
				{
					alert("Your browser does not support AJAX!");        
				    retValue=false;     
				}
			}
		}
		var leaveFromDate= document.empLeave.leaveFromDate.value;
		var leaveToDate= document.empLeave.leaveToDate.value;
				
		var url = "hrms.htm?actionFlag=checkleavedate&empId=${actionList.hrEisEmpMst.empId}&leaveFromDate="+leaveFromDate+"&leaveToDate="+leaveToDate+"&empLeaveId=${actionList.empLeaveId}";  
	    xmlHttp.onreadystatechange = function(){		
			if(xmlHttp.readyState == 4){     			
				if (xmlHttp.status == 200){	
					var LoanNo;
					var XMLDocForAjax=xmlHttp.responseXML.documentElement;
					var loanAdvMapping = XMLDocForAjax.getElementsByTagName('empNameMapping');	
					
					if(loanAdvMapping.length != 0) {	
						LoanNo = loanAdvMapping[0].childNodes[0].text;	
						
						if(LoanNo==-1)
							retValue=true;
						else
						    retValue=false; 	
					}
				}
			}
		}
		
		xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));	
		return retValue;
	
}


function cmpDate()
{
	 var diff = compareDate(document.empLeave.leaveFromDate.value,document.empLeave.leaveToDate.value);   

	 if(document.empLeave.leaveToDate.value!=null&&document.empLeave.leaveToDate.value!='')
	 {
	 
	 	var MonthDiff=	datediff(document.empLeave.leaveFromDate.value,document.empLeave.leaveToDate.value);
	 	//alert('diff is--->'+diff);
	 	if(diff < 0)
  	 	{
  	  	 	//alert('inside if');
  	  	 	//if(MonthDiff==-1)
  	  	 	//{
   			alert("To Date must be greater than From Date");
	   		document.empLeave.leaveToDate.value='';
   			return false;
  	  	 	//}
  	 	}
	  	else
  	 	{
	  	 	//alert('inside else');
 			datediff(document.empLeave.leaveFromDate.value,document.empLeave.leaveToDate.value);
  	 	}
  	 }

}

function datediff(paramFirstDate,paramSecondDate)
{

	var DD = paramFirstDate.substr(0,2);
	var MM = paramFirstDate.substr(3,2)-1;
	var YY = paramFirstDate.substr(6,4);
	
	var dd = paramSecondDate.substr(0,2);
	var mm = paramSecondDate.substr(3,2)-1;
	var yy = paramSecondDate.substr(6,4);
	
	var monthdiff = eval(mm - MM);
	var diff = Date.UTC(yy,mm,dd) - Date.UTC(YY,MM,DD);
	//var diff = toDate.getTime() - fromDate.getTime();
	var day = (diff)/(24*60*60*1000)+1;



	//if((MM==9 || MM==4 || MM==6 || MM==11) && monthdiff==1)
		document.empLeave.leaveTaken.value=Math.round(day);
	//else if(monthdiff == 0)
	//	document.empLeave.leaveTaken.value=Math.round(day)+1;
	//else
	//	document.empLeave.leaveTaken.value=Math.round(day)+2;

	return monthdiff;
}

function validateForm()
{	
	
	if(checkleavedate()!=true)
     {
   		alert("Leave Dates are overlapping");
   		document.empLeave.action = 'javascript:submit()';
  	 }
  	 else
	return true;
}

function submit()
{
    var uri = "./hrms.htm?actionFlag=";
  if("${empAllRec}"=='true')
    var url = uri + "insertEmpLeaveDtls&empLeaveId=${actionList.empLeaveId}&edit=Y&empType=${emptypeid}&empAllRec=true";
  else
    var url = uri + "insertEmpLeaveDtls&empLeaveId=${actionList.empLeaveId}&edit=Y&empType=${emptypeid}&empAllRec=false";

 document.empLeave.action = url;
 document.empLeave.submit();

 		//document.empLeave.action="./hrms.htm?actionFlag=insertEmpLeaveDtls&empLeaveId=${actionList.empLeaveId}&edit=Y&empType=${emptypeid}"; 
  	    //document.empLeave.submit();
}

function setbalance()
{
	if(document.empLeave.leaveType.value==14)
	{
		document.empLeave.availBal.value=0;
		document.empLeave.availBal.disabled=true;
	}
	else
	{
		document.empLeave.availBal.disabled=false;
	}
}

function test()
{
if(confirm('Do you want to cancel Leave?'))
{
 document.forms[0].isDelete.checked=true;
 }
else
{
 document.forms[0].isDelete.checked=false;
 }
}


</script>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<hdiits:form name="empLeave" validate="true" method="POST"
	action="javascript:submit()" encType="text/form-data">

	<div id="tabmenu">
		<ul id="maintab" class="shadetabs" compact="compact">
			<li class="selected"><a href="#" rel="tcontent1"><font size="2"><b><fmt:message key="LEV.edit" bundle="${commonLables}"/></b></a></li>
		</ul>
	</div>

	<div class="halftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0">
	<table width= "65%" align= "center"><br>
		
		
		<tr>
		<TD class="fieldLabel" >
		<b><hdiits:caption captionid="LEV.empName" bundle="${commonLables}"></hdiits:caption>
		</TD>
		
		
		<b><td>
                       <hdiits:hidden  name="empName"  default="${actionList.hrEisEmpMst.empId}"></hdiits:hidden>
                     <hdiits:text  name="empNameStr" 
                       captionid="LEV.empName" bundle="${commonLables}"   
                       readonly="true" default="${actionList.hrEisEmpMst.orgEmpMst.empFname} ${actionList.hrEisEmpMst.orgEmpMst.empMname} ${actionList.hrEisEmpMst.orgEmpMst.empLname}"></hdiits:text>
                       
                       
        </td>
	    </tr>
	    
	    
	    <tr>
			<td><b><hdiits:caption captionid="LEV.fromDate" bundle="${commonLables}"/></td>
			<td><hdiits:dateTime mandatory="true" captionid="LEV.fromDate" bundle="${commonLables}" name="leaveFromDate" default="${actionList.leaveFromDate}" validation="txt.isrequired,txt.isdt" onblur="cmpDate()"/></TD>	
		</tr> 
		
		
		<tr>
			<td><b><hdiits:caption captionid="LEV.toDate" bundle="${commonLables}"/></td>
			<td><hdiits:dateTime mandatory="true"  captionid="LEV.toDate" bundle="${commonLables}" name="leaveToDate" default="${actionList.leaveToDate}" validation="txt.isrequired,txt.isdt" onblur="cmpDate()"/></TD>	
		</tr> 
	    
	    
	    
	    <tr>
		<TD class="fieldLabel" >
		<b><hdiits:caption captionid="LEV.type" bundle="${commonLables}"></hdiits:caption>
		</TD>
		
		
		<b><td><hdiits:select  mandatory="true" name="leaveType" size="1"
                       captionid="LEV.type" bundle="${commonLables}"   
                       validation="sel.isrequired" onchange="setbalance()"
                       id="leaveType" sort="false">
                      
        <hdiits:option value="choose">--Select--</hdiits:option>
         
         <c:forEach var="leaveList" items="${leaveList}">
          <c:choose>
        
         <c:when test="${leaveList.leavecode == actionList.hrEssLeaveMst.leavecode}">
             <hdiits:option value="${leaveList.leavecode}" selected="true"><c:out value="${leaveList.leaveTypeName}"> </c:out></hdiits:option>
         </c:when>
         <c:otherwise>
         
         	<hdiits:option value="${leaveList.leavecode}"><c:out value="${leaveList.leaveTypeName}"> </c:out></hdiits:option>
         </c:otherwise>
         </c:choose>
         </c:forEach>
		 
		 </hdiits:select > 
		 <c:if test="${emptypeid eq fixedEmpType or emptypeid eq contractEmpType}">
		 	<script type="text/javascript">
		   		document.empLeave.leaveType.value=1;//Hard-Coded for fix-pay employee only get CL
	       		document.getElementById("leaveType").disabled=true;
			</script>
		</c:if>
        </td>
	    </tr>
	    
	   
	   <tr>
			<td><b><hdiits:caption captionid="LEV.availBal" bundle="${commonLables}"/></td>
			<td><hdiits:number  mandatory="true" name="availBal" default="${actionList.leaveAvailBal}" captionid="LEV.availBal" bundle="${commonLables}"  validation="txt.isrequired,txt.isnumber"  maxlength="3"   size="22" onblur="cmpDate()" /> </td>
	    </tr>
	    
	    
	    <tr>
			<td><b><hdiits:caption captionid="LEV.taken" bundle="${commonLables}"/></td>
			<td><hdiits:number name="leaveTaken" default="${actionList.leaveTaken}" captionid="LEV.taken" bundle="${commonLables}" readonly="true"  validation="txt.isrequired,txt.isnumber"  maxlength="10"   size="22" /> </td>
	    </tr>
	    <tr>
	    	<td></td><td><b><hdiits:checkbox name="isDelete" id="isDelete" value="1" captionid="LEV.cancel" bundle="${commonLables}" onclick="test()"/></b></td>
	    </tr>
	    
	    
	     
	</table>
 	</div>
 	 	<hdiits:jsField  name="validate" jsFunction="validateForm()" /> 
 	 	 	<c:choose>
  	<c:when test="${empAllRec eq true }">
<hdiits:hidden default="getEmpLeaveData&Employee_ID_EmpLoanSearch=${empId}&empAllRec=Y" name="givenurl"/>
</c:when>
<c:otherwise>
<hdiits:hidden default="getEmpLeaveData" name="givenurl"/>
</c:otherwise>
</c:choose>
<fmt:setBundle basename="resources.payroll.payrollLables" var="Lables" scope="request"/>
	<jsp:include page="../../core/PayTabnavigation.jsp" />
	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		if("${msg}"!=null&&"${msg}"!='' && "${empAllRec}"=='false')
		{
			alert("${msg}");
			var url="./hrms.htm?actionFlag=getEmpLeaveData";
			document.empLeave.action=url;
			document.empLeave.submit();
		}
		else if("${msg}"!=null&&"${msg}"!='' && "${empAllRec}"=='added')
		{
            alert("${msg}");
			var url="./hrms.htm?actionFlag=getEmpLeaveData&Employee_ID_EmpLoanSearch=${empId}&empAllRec=Y";

			document.empLeave.action=url;
			document.empLeave.submit();
		}
		
	</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

