<%
try {
%>

<%@page import="java.util.*"%>
<%@page import="com.tcs.sgv.common.utils.StringUtility"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<head>
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="desigresultSet" value='${resValue.DesignationList}' /> 
<c:set var="empName" value="${resValue.empName}" />
<c:set var="flag" value="${resValue.flag}" />
<c:set var="actionList" value="${resValue.actionList}" />
<script type="text/javascript" src="script/common/address.js"></script>
<script type="text/javascript" src="/script/hod/ps/common.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>
<fmt:formatDate value="${actionList.startDate}" pattern="yyyy-MM-dd HH:mm:SS.S" dateStyle="medium" var="stdate"/>
<fmt:formatDate value="${actionList.endDate}" pattern="yyyy-MM-dd HH:mm:SS.S"  dateStyle="medium" var="enddate"/>
<fmt:formatDate value="${actionList.startDate}" pattern="dd/MM/yyyy"  dateStyle="medium" var="StartDate"/>
<fmt:formatDate value="${actionList.endDate}" pattern="dd/MM/yyyy"  dateStyle="medium" var="EndDate"/>
<c:set var="id" value="${actionList.id}"></c:set>
<script type="text/javascript">
//alert('${msg}');
function chkFunc()
{
	var empId=document.getElementById("Employee_ID_empSearch").value;
	
	if(empId=="")
	{
		alert("Please search the employee first");
		document.frmEmpSgnDetailsMaster.cmbDesignationDesc.value='Select';
		return false;
	}
	else
	{
		return true;
	}
		
}
function endDateValidation()
{
	var startDate=document.frmEmpSgnDetailsMaster.txtStartDate.value;
	var endDate=document.frmEmpSgnDetailsMaster.txtEndDate.value;
	var enddate='${EndDate}';	
	var result=compareDate(startDate,endDate);
	if((result<=0)&&(!endDate=='') )
	  {
		 alert("End Date should be greater than  Start Date");
	     document.frmEmpSgnDetailsMaster.elements("txtEndDate").style.background="#ccccff";
	     document.frmEmpSgnDetailsMaster.elements("txtEndDate").value=enddate;
	     document.frmEmpSgnDetailsMaster.elements("txtEndDate").focus();
	     return false;
	  }
	else 
		return true;
}


function checkStartDate()
{
	var startDate=document.frmEmpSgnDetailsMaster.txtStartDate.value;
	var Id='${id}';
	if((!startDate==''))
	{
		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return false;
		  } 
		  var actionf="chkDate";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+'&startdate='+startDate+'&Id='+Id; 
		  xmlHttp.onreadystatechange=chk_startDate;
		  xmlHttp.open("POST",encodeURI(url),true);
		  xmlHttp.send(null);	
	}
	else
		return true;
}

function chk_startDate()
{
	if (xmlHttp.readyState==complete_state)
	 { 	
		var	startdate='${StartDate}';	
		var XMLDoc=xmlHttp.responseXML.documentElement;			
	    var namesEntries = XMLDoc.getElementsByTagName('start-date');
	    if(namesEntries.length != 0 && namesEntries[0].childNodes[0].text!='0')
	      {                    
	         alert("Start Date overlaps with the previous records,Please enter again!");
	         document.frmEmpSgnDetailsMaster.elements("txtStartDate").style.background="#ccccff";
	         document.frmEmpSgnDetailsMaster.elements("txtStartDate").value=startdate;;
	         document.frmEmpSgnDetailsMaster.elements("txtStartDate").focus();
	         return false;
          }
	    else
		    return true;
	 }
}


function checkEndDate()
{
	var startDate=document.frmEmpSgnDetailsMaster.txtStartDate.value;
	var endDate=document.frmEmpSgnDetailsMaster.txtEndDate.value;
	var enddate='${EndDate}';
	var Id='${id}';
	var result=compareDate(startDate,endDate);
    if((!endDate==''))
	  {
	    xmlHttp=GetXmlHttpObject();
		 if (xmlHttp==null)
		     {
			   alert ("Your browser does not support AJAX!");
			   return false;
			 } 
	    var actionf="chkDate";
		uri='./hrms.htm?actionFlag='+actionf;
		url=uri+'&enddate='+endDate+'&Id='+Id;  
		xmlHttp.onreadystatechange=chk_endDate;
		xmlHttp.open("POST",encodeURI(url),true);
		xmlHttp.send(null);	
	  }
    else
	   return true;
}

function chk_endDate()
{
	if (xmlHttp.readyState==complete_state)
	 { 	
		var enddate='${EndDate}';					
		var XMLDoc=xmlHttp.responseXML.documentElement;			
	    var namesEntries = XMLDoc.getElementsByTagName('end-date');
	    if(namesEntries.length != 0 && namesEntries[0].childNodes[0].text!='0')
	      {                    
	        alert("End Date overlaps with the previous records,Please enter again!");
	        document.frmEmpSgnDetailsMaster.elements("txtEndDate").style.background="#ccccff";
	        document.frmEmpSgnDetailsMaster.elements("txtEndDate").value=enddate;
	        document.frmEmpSgnDetailsMaster.elements("txtEndDate").focus();
	        return false;
	      }
	    else
		  return true;
	  }
}

function submitForm()
{
 var uri = "./hrms.htm?actionFlag=";
 var url = uri + document.frmEmpSgnDetailsMaster.txtAction.value;
 document.frmEmpSgnDetailsMaster.action = url;
 document.frmEmpSgnDetailsMaster.submit();
	
}

function validateForm() 
{
 if(${flag!='edit'})
 document.frmEmpSgnDetailsMaster.cmbEmpName.value=document.getElementById("Employee_ID_empSearch").value;

 if(${flag!='edit'}&& chkFunc()!=true)
 {
   return false;
 }
 else if(endDateValidation())
   return true;
 else 
    return false;
 }
</script>
</head>

<body>
<hdiits:form name="frmEmpSgnDetailsMaster" validate="true" method="POST"  action="javascript:submitForm()" encType="multipart/form-data">
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
	 <c:choose>
		 <c:when test="${flag eq 'edit'}">
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="ESD.update_emp_sgn_dtls" bundle="${commonLables}"/> </b></a></li>
		</c:when>
         <c:otherwise>
         <li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="ESD.add_emp_sgn_dtls" bundle="${commonLables}"/> </b></a></li>
         </c:otherwise>          
        </c:choose>
    </ul>
</div>
<div class="halftabcontentstyle">
<div id="tcontent1" class="halftabcontent" tabno="0">  

<table align="center" width="85%">
<tr><td colspan="4">&nbsp;</td></tr>
   <c:if test="${flag eq 'edit'}">
<hdiits:hidden name="Id" default="${actionList.id}"/>   
</c:if>
   <tr>
        <c:set var="readonly" value="false"/>
		<c:choose>
			<c:when test="${flag=='edit'}">
			<td> <b><fmt:message key="EMP.NAME" bundle="${commonLables}"/> </b></td>			
			<td><c:set var="readonly" value="true"/><hdiits:text name="empName" readonly="true" default="${actionList.hrEisEmpMst.orgEmpMst.empFname} ${actionList.hrEisEmpMst.orgEmpMst.empMname} ${actionList.hrEisEmpMst.orgEmpMst.empLname}"/></td>
           </c:when>
           <c:otherwise>
		 <td class="fieldLabel" colspan="4">
							<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
								<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
								<jsp:param name="SearchEmployee" value="empSearch"/>
								<jsp:param name="formName" value="frmEmpSgnDetailsMaster"/>
								<jsp:param name="functionName" value="chkFunc"/>
					        </jsp:include>
					   <hdiits:hidden id="cmbEmpName" name = "cmbEmpName"/>
	    </td>	
        </c:otherwise>          
   </c:choose>
</tr>
<tr><td colspan="4">&nbsp;</td></tr>
 
<tr><td colspan="4">&nbsp;</td></tr>	
<tr>
		<td ><b> <fmt:message key="eis.desig_name" bundle="${commonLables}"/></b></td>	
				
		<td><hdiits:select  mandatory="true" sort="false" captionid="eis.desig_name" bundle="${commonLables}" validation="sel.isrequired"  name="cmbDesignationDesc" size ="1">
				<hdiits:option value="select" selected="true">------------------------Select-----------------------</hdiits:option>
						<c:forEach var="desigresultSet" items="${resValue.DesignationList}">
						<c:choose>
		                  <c:when test="${desigresultSet.dsgnId eq actionList.orgDesignationMst.dsgnId}">
    			          <hdiits:option value="${actionList.orgDesignationMst.dsgnId}" selected="true"> ${actionList.orgDesignationMst.dsgnName} </hdiits:option>
    	                  </c:when>
    	                  <c:otherwise>
			              <hdiits:option value="${desigresultSet.dsgnId}"> ${desigresultSet.dsgnName} </hdiits:option>
			              </c:otherwise>
			            </c:choose>
				    	</c:forEach>		
			</hdiits:select>
		</td>
	</tr>
	<tr><td colspan="4">&nbsp;</td>
	<c:choose>
	<c:when test="${flag eq 'edit'}">
      <hdiits:hidden name="txtAction" default="insertEmployeeSignatureDtls&edit=Y"/>
     </c:when>
    <c:otherwise>
      <hdiits:hidden name="txtAction" default="insertEmployeeSignatureDtls&edit=N"/>
    </c:otherwise>
    </c:choose>
	</tr>
	<tr>
	<td align="left" width="15%" > <b><fmt:message key="ESD.startDate" bundle="${commonLables}"/></b></td>
	<td align="left" width="30%">
	<c:choose>
			<c:when test="${flag ne 'insert'}">
				<hdiits:dateTime name="txtStartDate" mandatory="true" validation="txt.isdt,txt.isrequired" captionid="ESD.startDate" bundle="${commonLables}" default="${stdate}" onblur="javascript:checkStartDate()"/>	 
			</c:when>
			<c:otherwise>
				<hdiits:dateTime name="txtStartDate" mandatory="true"  validation="txt.isdt,txt.isrequired" captionid="ESD.startDate" bundle="${commonLables}" onblur="javascript:checkStartDate()"/>	 		
			</c:otherwise>
	</c:choose>
	</td>
	<td align="left" width="15%" > <b><fmt:message key="ESD.endDate" bundle="${commonLables}"/></b></td>
	<td align="left" width="30%">
	<c:choose>
			<c:when test="${flag ne 'insert'}">
				<hdiits:dateTime name="txtEndDate"   validation="txt.isdt" captionid="ESD.endDate" bundle="${commonLables}" default="${enddate}" onblur="javascript:checkEndDate()"/>	 
			</c:when>
			<c:otherwise>
				<hdiits:dateTime name="txtEndDate"  validation="txt.isdt" captionid="ESD.endDate" bundle="${commonLables}" onblur="javascript:checkEndDate()"/>	 		
			</c:otherwise>
	</c:choose>
	</td>	
	</tr>
</table>
<br>
</div>
<hdiits:jsField  name="validate" jsFunction="validateForm()" /> 
	 <hdiits:hidden default="getEmployeeSgnDetailsView" name="givenurl"/>
<c:choose>
 		<c:when test="${resValue.flag eq 'edit'}">
 		
 			<fmt:setBundle basename="resources.payroll.payrollLables_en_US" var="Lables" scope="request"/>
			<jsp:include page="../../core/PayTabnavigation.jsp" />
		</c:when>
	  <c:otherwise>
			<fmt:setBundle basename="resources.eis.eis_common_lables" var="Lables" scope="page"/>
	        <jsp:include page="../../core/PayTabnavigation.jsp" />
	  </c:otherwise>
</c:choose>
</div>
<script type="text/javascript">
      initializetabcontent("maintab")
		//alert("brook was here");
		
		if("${empName}"!=null&&"${empName}"!='')
		{
				document.frmEmpSgnDetailsMaster.Employee_srchNameText_empSearch.value="${empName}";
			    GoToNewPageempSearch();
		}
	/* if("${msg}"!=null && "${msg}"!='')
		{
			alert("${msg}");
			var url="./hrms.htm?actionFlag=getEmployeeSgnDetailsView";
			document.frmEmpSgnDetailsMaster.action=url;
			document.frmEmpSgnDetailsMaster.submit();
		}*/
		
			
	    document.frmEmpSgnDetailsMaster.cmbDesignationDesc.focus();
			
		
</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
</body>
<%
	} catch (Exception e)
	{
		e.printStackTrace();
	}
%>

