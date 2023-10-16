<%
try {
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.hr.acr.acr_AlertMessages" var="commonLables1" scope="request"/>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript" src="<c:url value="/script/common/commonfunctions.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/tagLibValidation.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/base64.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript">
var disableAllObjFlag=false;
function disableAcrPage()
{
	var totalElements= document.forms[0].elements.length
	for(i=0;i<totalElements;i++)
	{	
		if(document.forms[0].elements[i].type=='radio'
			|| document.forms[0].elements[i].type=='select-multiple' || document.forms[0].elements[i].type=='button'
			|| document.forms[0].elements[i].type=='checkbox'
			|| document.forms[0].elements[i].type=='select' || document.forms[0].elements[i].type=='select-one')
		{
			document.forms[0].elements[i].disabled=true;
		}
		else if (document.forms[0].elements[i].type=='text' || document.forms[0].elements[i].type=='textarea')
		{
			document.forms[0].elements[i].readOnly=true;
		}
	}
}
function submit1()
{
	document.ACRFillPage.method="POST";
	document.ACRFillPage.action="./hrms.htm?actionFlag=ACRSubmittedByHost&flag=AD";
	document.ACRFillPage.submit();
}
function callLastJSFunction()
{	
	return true;
}	
function validateForm()
{
	var counter = document.getElementById('count').value;
	for(i=0;i<counter;i++)
	{			
		var a=document.getElementById("Value"+i);		
		if(trim(a.value)=="")
		{	
			alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.Mandatory"/>');
			return false;
		}
	}
	showProgressbar("Please Wait...");	
	submit1();
}
function showTable(a){	
	
	
	var row = document.getElementById("previousData"+a);	
	if (row.style.display == '')  
	{
		row.style.display = 'none';
		document.getElementById("img"+a).src="./images/greenDown.gif";
	}
	else
	{
		 row.style.display = '';
		 document.getElementById("img"+a).src="./images/redUp1.gif";
	}
}
function isValidData(txtarea)
{
	var len,str,str1,i;
	len=txtarea.value.length;
	str=txtarea.value;
	//add or remove characters into this string to be checked 
	str1="`~!#$%^&*+|"
	for(i=0;i<len;i++)
	{
		
		if((str1.indexOf(str.charAt(i)))!=-1)
		{
			alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.SpecialCharsNotAllowed"/>');
			txtarea.focus();
			return;
		}
	}
	return;
}
function closeButtonHandler()
{

	document.ACRFillPage.method="POST";
	document.ACRFillPage.action="./hrms.htm?actionFlag=getDocListOfWorkList";
	document.ACRFillPage.submit();
}
</script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="pointFlag" value="${resValue.pointFlag}"></c:set>
<c:set var="requestId" value="${resValue.requestId}"></c:set>
<c:set var="pointCount" value="${resValue.pointCount}"></c:set>
<c:set var="previousPointFlag" value="${resValue.previousPointFlag}"></c:set>
<c:set var="previousPointData" value="${resValue.previousPointData}"></c:set>
<c:set var="selfAppraisalFlag" value="${resValue.selfAppraisalFlag}"></c:set>
<c:set var="reportFlag" value="${resValue.reportFlag}"></c:set>
<c:set var="fileId" value="${resValue.fileId}"></c:set>
<c:set var="userMap" value="${resValue.userMap}"></c:set>
<c:set var="pointDtlMap" value="${resValue.pointDtlMap}"></c:set>
<c:set var="pointValueMap" value="${resValue.pointValueMap}"></c:set>
<c:set var="allKeyList" value="${resValue.allKeyList}"></c:set>
<c:set var="rolePointValue" value="${resValue.rolePointValue}"></c:set>
<c:set var="selectedYear" value="${resValue.year}"></c:set>
<c:set var="corrId" value="${resValue.corrId}"></c:set>
<c:set var="authority" value="${resValue.authority}"></c:set>
<c:set var="pointList" value="${resValue.pointList}"></c:set>
<c:set var="selfAppPoint" value="${resValue.selfPoint}"></c:set>
<c:set var="selfPointValue" value="${resValue.selfPointValue}"></c:set>
<c:set var="ratingLst" value="${resValue.ratingLst}"></c:set>
<c:set var="read" value="${resValue.readOnly}"></c:set>
<c:set var="showCorr" value="${resValue.showCreateCorrBtn}"></c:set>
<c:set var="disableAllMsg" value="${resValue.disableAllMsg}"></c:set>
<c:set var="disableAll" value="${resValue.disableAll}"></c:set>
<c:set var="fileCorrOpen" value="${resValue.fileCorrOpen}"></c:set>
<c:set var="allOfficerDataAvilabel" value="0"></c:set>
<c:set var="fileCorrStatus" value="${resValue.fileCorrStatus}"></c:set>
<fmt:setBundle basename="resources.hr.acr.acr" var="commonLables" scope="request"/>
<hdiits:form name="ACRFillPage" action="./hrms.htm?actionFlag=ACRSubmittedByHost" method="POST" validate="true"  encType="multipart/form-data" >
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="HR.ACR.ACRForYear" bundle="${commonLables}"/> : <c:out value="${selectedYear} - ${selectedYear+1}"/></b></a></li>
		<c:if test="${fileCorrStatus eq '1'}"><li class="selected"><a href="#" rel="tcontent2"><b>Corr status</b></a></li></c:if>
	</ul>
</div>
<div>
	 <div id="tcontent1" class="tabcontent" tabno="0">	 		
			<jsp:include page="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"></jsp:include>
			<br><br>
			<c:if test="${disableAll eq '1'}">
				<script type="text/javascript">
					disableAllObjFlag=true;
				</script>
				<table id="recordInserted" align="center" border="1" bgcolor="white" bordercolor="black" width="100%">
				<tr>
					<td colspan="10" align="center" >
					<font color="red"><b>
						<c:out value="${disableAllMsg}"></c:out>						
					</b></font>				
					</td>
				</tr>
				</table>
				<br><br>
			</c:if>
			<jsp:include page="wfCommonInclude.jsp"></jsp:include>	
		<br><br>
		<hdiits:fieldGroup collapseOnLoad="false" id="acr_file_field5" titleCaptionId="HR.ACR.GiveRating" bundle="${commonLables}">								
			<table width="100%" border="0" border="1" borderColor="BLACK" style="border-collapse: collapse;">					
				<tr>				
				<td class="fieldLabel"><hdiits:caption captionid="HR.ACR.Rating" bundle="${commonLables}"></hdiits:caption></td>
				<td class="fieldLabel">
				<hdiits:select name="ratingCmb" id="ratingCmb" captionid="HR.ACR.Rating" bundle="${commonLables}" validation="sel.isrequired" mandatory="true" default="${resValue.selectedRating}">
					<hdiits:option value="Select">--<fmt:message key="HR.ACR.Select" bundle="${commonLables}"></fmt:message>--</hdiits:option>
					<c:forEach var="rating" items="${ratingLst}">
    					<option value="<c:out value="${rating.lookupName}"/>">
						<c:out value="${rating.lookupDesc}"/></option>						
					</c:forEach>
				</hdiits:select>
				</td>
				<td class="fieldLabel"></td>
				<td class="fieldLabel"></td>
				</tr>
			</table>
		</hdiits:fieldGroup>
		<br><br>	
		<table width="100%">
		<tr>
		<td align="center" colspan="4">
		<c:if test="${resValue.acrReportFlag ne 1 and fileCorrOpen ne 1}">
			<c:if test="${showCorr eq 'Y' and allOfficerDataAvilabel eq 0}">			
				<hdiits:submitbutton type="button" name="btnCorr" captionid="HR.ACR.BtnCreateCorr" bundle="${commonLables}" onclick="validateForm();" />
			</c:if>
			<c:if test="${showCorr eq 'N'}">			
				<b><fmt:message key="HR.ACR.CorrSend" bundle="${commonLables}" /></b>
			</c:if>
			<c:if test="${showCorr eq 'C2'}">			
				<b><fmt:message key="HR.ACR.ConvertFile" bundle="${commonLables}" /></b>
			</c:if>
			<c:if test="${showCorr eq 'C1'}">			
				<b><fmt:message key="HR.ACR.FileCreated" bundle="${commonLables}" /></b>
			</c:if>
		</c:if>
		</td>
		</tr>
		</table>
		<c:if test="${empty allKeyList}">
			<script>
				document.getElementById("acr_file_field3").style.display="none";
			</script>
		</c:if>
		<hdiits:hidden name="fileId" id="fileId" default="${fileId}"></hdiits:hidden>
		<hdiits:hidden name="corrId" id="corrId" default="${resValue.corrId}"/>	
		<hdiits:hidden name="selectedYear" id="selectedYear" default="${resValue.year}"/>							
		<hdiits:jsField jsFunction="callLastJSFunction()" name="callLastJSFunction" />		
		<jsp:include page="../../../core/tabnavigation.jsp" />	
		<hdiits:validate locale="${locale}" controlNames="" />
		<script type="text/javascript">	
			if('${read}'=='Y')
			{
				disableAcrPage();
			}
			if(disableAllObjFlag==true)
			{
				disableAcrPage();
			}			
			if('${fileCorrOpen}'=='1')
			{
				disableAcrPage();
			}
			if('${resValue.acrReportFlag}'=='1'){disableAcrPage();}
		</script>		
	</div>	
	<c:if test="${fileCorrStatus eq '1'}">
		<div id="tcontent2" class="tabcontent" tabno="1">
			<jsp:include page="fileCorrStatus.jsp"></jsp:include>
		</div>	
	</c:if>	
</div>	
</hdiits:form>	
<script type="text/javascript">	
			initializetabcontent("maintab")						
</script>			
	<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
