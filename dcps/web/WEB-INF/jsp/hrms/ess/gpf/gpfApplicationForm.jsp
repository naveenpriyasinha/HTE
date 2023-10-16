<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.ess.gpf.gpfLables" var="gpfLables" scope="request"/>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="gpffunc" value="${resValue.GPF_Function}"></c:set>
<c:set var="comboBoxSel" value="${resValue.comboBoxSel}"></c:set>	
<c:set var="pendingReqSub" value="${resValue.pendingReqSub}"></c:set>
<c:set var="pendingReqWithFinal" value="${resValue.pendingReqWithFinal}"></c:set>
<c:set var="approveWithReqFinal" value="${resValue.approveWithReqFinal}"></c:set>
<c:set var="pendingAdvRefund" value="${resValue.pendingAdvRefund}"></c:set> 
<c:set var="gpfAccNo" value="${resValue.gpfAccNo}"></c:set>
<c:set var="hrGpfBalanceDtls" value="${resValue.hrGpfBalanceDtls}"></c:set>
<c:set var="finYrStart" value="${resValue.finYrStart}"></c:set>
<c:set var="date" value="${resValue.todayDate}"></c:set>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<script>

function hideMsgs()
{
document.getElementById("reqPending").style.display='none';
document.getElementById("withReqPending").style.display='none';
document.getElementById("finalWithApprovd").style.display='none';
document.getElementById("advRefundPending").style.display='none';
}

function setAction()
{
var select = document.frmBF.gpfReq;
var value = select.options[select.selectedIndex].value;
var proceed=0;

if(value=="Select")
	{
	proceed=-1;
	}
	
else if(value == "<fmt:message key="GPF.newAcc"/>")
	{
	document.getElementById("comboBoxSel").value=value;
	document.frmBF.action="hrms.htm?actionFlag=viewNewAccReqPage";
	}
else if(value == "<fmt:message key="GPF.changeSubDetails"/>" )
	{
		document.frmBF.action="hrms.htm?actionFlag=gpfChgSubs";
		document.getElementById("comboBoxSel").value=value;
		hideMsgs();
		if("${pendingReqSub}"==-1)
			{
			
			document.getElementById("reqPending").style.display='block';
			proceed=-1;
			}
	}
else if(value == "<fmt:message key="GPF.gpfAdv"/>")
	{
		hideMsgs();
		document.frmBF.action="hrms.htm?actionFlag=gpfAdvApp";
		document.getElementById("comboBoxSel").value=value;
		if("${pendingAdvRefund}"==-1)
			{
			document.getElementById("advRefundPending").style.display='block';
			proceed=-1;
			}
	}
else if(value == "<fmt:message key="GPF.partFinalWithd"/>")
	{
		hideMsgs();
		document.frmBF.action="hrms.htm?actionFlag=gpfWithdrawalApp";
		document.getElementById("comboBoxSel").value=value;
		if("${pendingReqWithFinal}"==-1)
			{
			document.getElementById("withReqPending").style.display='block';
			proceed=-1;
			}
		if("${approveWithReqFinal}"==-1)
			{
			document.getElementById("finalWithApprovd").style.display='block';
			proceed=-1;
			}
	}
if(proceed!=-1)
	{
	document.frmBF.submit();
	}
}


</script>


<hdiits:form name="frmBF" validate="true" method="POST" encType="multipart/form-data" >


<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><fmt:message key="GPF"/></a></li>
	</ul>
</div>

	 
<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">

	<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
	<br>
<c:if test="${gpfAccNo!='-'}">
	<%@ include file="/WEB-INF/jsp/hrms/ess/gpf/gpfBalanceDtls.jsp"%>
</c:if>
 <table width="100%">
<br>
 
<tr bgcolor="#386CB7">
	<td class="fieldLabel" colspan="4">
	<font color="#ffffff">
	<strong><u><fmt:message key="GPF.gpfReq"/></u></strong>
	</font>
	</td>
</tr>

<tr>
	<td width="25%">
    <fmt:message key="GPF.gpfFun"/>
     </td>
      		
      <td width="25%">
      <select name="gpfReq" id="gpfReq" onchange="setAction();">
			<option id="a" value="Select"  ><fmt:message key="GPF.select"/></option>
				<c:forEach var="name" items="${gpffunc}">						
    			<option id="opt" name="opt" value="<c:out value="${name.lookupName}"/>"  >
    				<c:out value="${name.lookupDesc}"/>
    			</option>						
				</c:forEach>
	  </select>
	
	<hdiits:hidden name="comboBoxSel"  caption="comboBoxSel"  /> 
	</td>	
	
	<td width="25%"></td>
	<td width="25%"></td>	
</tr>
</table>

<table width=100%>
<tr>
	<td>
	<br>
	<p id="reqPending" style="display:none">
	<font color="RED"><fmt:message key="GPF.reqPending"/></font></p>
	<p id="withReqPending" style="display:none">
	<font color="RED"><fmt:message key="GPF.partFinalReqPending"/></font></p>
	<p id="finalWithApprovd" style="display:none">
	<font color="RED"><fmt:message key="GPF.finalWithDone"/></font></p>
	<p id="advRefundPending" style="display:none">
	<font color="RED"><fmt:message key="GPF.prevAdvPending"/></font></p>
	</td>
</tr>
</table>
  
 </div>
 </div>

<script type="text/javascript">
	//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
	initializetabcontent("maintab");
</script>

<hdiits:validate controlNames="" locale='<%=(String)session.getAttribute("locale")%>"'/>
</hdiits:form>


<%
} catch (Exception e) 
	{
	e.printStackTrace();
	}
%>

