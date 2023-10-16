
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="common/script/tagLibValidation.js"></script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>

<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="script/hrms/hr/dept/dept.js"></script>

<fmt:setBundle basename="resources.CommonLables_en_US"	var="commonLables1" scope="request" />
<fmt:setBundle basename="resources.hr.dept.deptLables" var="deptLables" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="map" value="${resValue.map}" > </c:set>	
<c:set var="fileId" value="${resValue.fileId}"></c:set>
<c:set var="objPrPrelimEnqDtl" value="${resValue.objPrPrelimEnqDtl}"></c:set>
<c:set var="objCmnPersonMst" value="${resValue.objCmnPersonMst}"></c:set>
<c:set var="allegDtls" value="${resValue.allegDtls}"></c:set>
<c:set var="objHrPrelimEnqDtls" value="${resValue.objHrPrelimEnqDtls}" > </c:set>
<c:set var="enqDtl" value="${resValue.enqDtl}"></c:set>
<c:set var="type" value="${resValue.type}"></c:set>
<c:set var="branch" value="${resValue.branch_name}" > </c:set>
<c:set var="empDtlmp" value="${resValue.empDtlmp}"></c:set>
<c:set var="incDate" value="${resValue.incDate}"></c:set>
<c:set var="enqAgainstUserId" value="${resValue.enqAgainstUserId}"></c:set>
<c:set var="cannotClose" value="${resValue.cannotClose}"></c:set>
<script>

<fmt:formatDate pattern="dd/MM/yyyy" value="${enqDtl[0].preprelimStartDate}" var="prePrelimStrtDt"/>
<fmt:formatDate pattern="dd/MM/yyyy" value="${empDtlmp.dob}" var="empDtlDob"/>
<fmt:formatDate pattern="dd/MM/yyyy" value="${empDtlmp.dor}" var="empDtlDor"/>

var srId=0; 

</script>

<hdiits:form name="inquiryCaseTracking" validate="true" method="POST" 
	action="hrms.htm?actionFlag=addPrePrelimData" encType="multipart/form-data">
	
	<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" default="${fileId}"/>
	<hdiits:hidden name="enqDtl_Preprelim" id="enqDtl_Preprelim" default="${enqDtl[0].prePrelimStatus}"/>
	<hdiits:hidden name="enqDtl_Prelim" id="enqDtl_Prelim" default="${enqDtl[0].prelimStatus}"/>
	<hdiits:hidden name="enqDtl_Dept" id="enqDtl_Dept" default="${enqDtl[0].deptStatus}"/>
	<hdiits:hidden name="enqDtl_Susp" id="enqDtl_Susp" default="${enqDtl[0].suspensionStatus}"/>
	<hdiits:hidden name="enqDtl_Prosec" id="enqDtl_Prosec" default="${enqDtl[0].prosecStatus}"/>
	<hdiits:hidden name="enqDtl_flag" id="enqDtl_flag" default="${type}"/>
	<hdiits:text  name="user_id1" id="user_id1" style="display:none" /> 
	
<div id="tabmenu">

	<ul id="maintab" class="shadetabs">
			<li class="selected" style="width: 230px;text-align:center;"><a href="#" style="width: 220px;text-align:center;" rel="tcontent1">
			<fmt:message key="dept.inqTrackingSys"/></a>
			</li>
	</ul>
	</div>
	
		
<div class="tabcontentstyle">
<div id="tcontent1" class="tabcontent" tabno="0">

<hdiits:fieldGroup titleCaptionId="dept.empDtls" bundle="${deptLables}" id="empDtlsFldGrp">


<c:if test="${cannotClose=='Y'}">
<font color="RED">
<hdiits:caption captionid="dept.cannotCloseEnq" bundle="${deptLables}"/>
</font>
</c:if>

<table width=100%><tr>
<td align="left" colspan="1" rowspan="1">
<hdiits:caption captionid="dept.empAgainseEnq" bundle="${deptLables}"/> 
<hdiits:image  id="Search1" source="images/search_icon.gif" onclick="SearchEmp(this);"> </hdiits:image>
</td></tr>
	<tr><td><br><hr></td></tr>
	<tr>

<td>

	<table id="empDtlTable" style="background: #eeeeee;padding: 2px;display: none" width="100%">
		<tr>
			 <td width=25%> <hdiits:caption captionid="dept.Name" bundle="${deptLables}"/></td>
			 <td width=25% ><hdiits:text id="empName" name="empName" readonly="true"/></td>
			 <td width=25%> <hdiits:caption captionid="dept.dob" bundle="${deptLables}"/></td>
			 <td width=25% id="dob"><hdiits:text  name="dateOfBirth" caption="Date of Retirement" readonly="true"/> </td>
		</tr>
		<tr>
			 <td width=25%> <hdiits:caption captionid="dept.dor" bundle="${deptLables}"/></td>
			 <td width="25%" id="dor"><hdiits:text name="retireDt" caption="Date of Retirement" readonly="true"/></td>
			 <td width=25%> <hdiits:caption captionid="dept.presPay" bundle="${deptLables}"/></td>
			 <td width=25% ><hdiits:number id="presentPay" name="prePay" readonly="true"/><label id="paylab"> </label> </td>
		</tr>
		<tr>
			 <td width=25%> <hdiits:caption captionid="dept.payScale" bundle="${deptLables}"/></td>
			 <td width=25%><hdiits:number id="payScale" name="payScale" readonly="true"/></td>
			 <td width=25%><hdiits:caption captionid="dept.nextIncDt" bundle="${deptLables}"/></td>
			 <td width=25%><hdiits:text name="nextIncDt"  caption="Next Date of Increment" readonly="true"/></td>
		</tr>
		
	</table>
 </td></tr>
 <tr><td>

 <table id="empDtlTable2" style="background: #eeeeee;padding: 2px;display: none" width="100%">
		<tr>
			 <td width=25%> <hdiits:caption captionid="dept.Name" bundle="${deptLables}"/></td>
			 <td width=25% ><hdiits:text id="empName2" default="${empDtlmp.empName}" name="empName2"  readonly="true"/></td>
			
			 <td width=25%> <hdiits:caption captionid="dept.dob" bundle="${deptLables}"/></td>
			 <td width=25%><hdiits:text  name="dateOfBirth2" id="dateOfBirth2" default="${empDtlDob}"  readonly="true"/> </td>
		</tr>
		<tr>
			 <td width=25%> <hdiits:caption captionid="dept.dor" bundle="${deptLables}"/></td>
			 <td width=25%><hdiits:text name="retireDt2" id="retireDt2" default="${empDtlDor}"  readonly="true"/></td>
		
			 <td width=25%> <hdiits:caption captionid="dept.presPay" bundle="${deptLables}"/></td>
			 <td width=25% ><hdiits:number id="presentPay2" default="${empDtlmp.presentpay}" name="prePay2"  readonly="true"/></td>
		</tr>
		<tr> 
			 <td width=25%> <hdiits:caption captionid="dept.payScale" bundle="${deptLables}"/></td>
			 <td width=25%><hdiits:number id="payScale2" default="${empDtlmp.payScale}" name="payScale2" readonly="true"/></td>
		
			 <td width=25%> <hdiits:caption captionid="dept.nextIncDt" bundle="${deptLables}"/></td>
			 <td width=25% ><hdiits:text name="nextIncDt2" id="nextIncDt2" readonly="true"/></td>
		</tr>
	</table>
 </td></tr>
</table>
</hdiits:fieldGroup>

<table width=100% id="deptInquiry1">

	<tr><td></td></tr>
	
	<tr>
		<td width=100%  style="color: #000000; font-family: Verdana; font-size: 12px; background-color: #C6DEFF;">
		<b><hdiits:caption captionid="dept.prePrilim" bundle="${deptLables}"/></b>
		
		<a href="javascript:void(0);" onclick="dispPrePrelim();"><img id="expandTicket" src="images/expand.gif" /></a>
		<a href="javascript:void(0);" onclick="hidePrePrelim();"><img id="collapseTicket" src="images/collapse.gif" style="display:none"/></a>
		</td>
	</tr>
	
	<tr> 
		<td width=50%>
		<hdiits:caption captionid="dept.prePrelimStrtDt" bundle="${deptLables}"/>
		
		<hdiits:dateTime name="enqStartDate"  maxvalue="31/12/2099 00:00:00" captionid="dept.prePrelimStrtDt" bundle="${deptLables}" mandatory="true"/>
		</td>

		<script>
		document.inquiryCaseTracking.enqStartDate.value="${prePrelimStrtDt}";
		</script>
		
		<td width=25%>	</td>
		<td width=20%>	</td>
		<td width=5%>	</td>
	</tr>
	
</table>
<%@ include file="../departmentEnquiry/PrePreliminaryInquiry.jsp"%>
<br>

<c:if  test="${enqDtl[0].prelimStatus=='NI' && enqDtl[0].prePrelimStatus=='P'}">
<table width=100%>
	<tr>
		<td width=50%>	
		<hdiits:checkbox  name="chkBoxInitPrelim" value="yes" caption="" onclick="prelimInitStatus();"/>
		<hdiits:submitbutton name="prelimInitBtn" id="prelimInitBtn" type="button" captionid='dept.initPrelimEnq' bundle="${deptLables}" readonly="true"/>
		</td>
		<td width=25%></td>
		<td width=25%>	</td>
	</tr>
</table>
</c:if>

<table width=100%  id="deptInquiry2" style="display:none">
 <c:if  test="${enqDtl[0].prelimStatus!='NI'}">
	<tr style="color: #000000; font-family: Verdana; font-size: 12px; background-color: #C6DEFF;">
		<td>
    	<b><hdiits:caption captionid="dept.prilim" bundle="${deptLables}"/></b>
    	<a href="javascript:void(0);" onclick="dispPrelim();"><img id="expandTicket2" src="images/expand.gif" /></a>
		<a href="javascript:void(0);" onclick="hidePrelim();"><img id="collapseTicket2" src="images/collapse.gif" style="display:none"/></a>
		</td>
    </tr>
  </c:if>
  
    <c:if test="${enqDtl[0].prelimStatus!='NI'}">
    <%@ include file="../departmentEnquiry/PreliminaryInquiry.jsp"%>
	</c:if>

 </table>
 
 <c:if test="${enqDtl[0].deptStatus=='NI' && enqDtl[0].prelimStatus=='P'}">
<table width=100%>
	<tr>
		<td width=50%>	
		<hdiits:checkbox  name="chkBoxInitDept" value="yes" caption=" " onclick="deptInitStatus();"/>
		<hdiits:submitbutton type="button" name="deptInitBtn" id="deptInitBtn" captionid='dept.initDeptEnq' bundle="${deptLables}" readonly="true"/>
		</td>
			
		<td width=25%>	</td>
		<td width=25%>	</td>
	</tr>
</table>
</c:if>

 <br>
 <c:if test="${enqDtl[0].deptStatus!='NI'}">
 <table width=100%  id="deptInquiry3" style="display:none">
  	<tr style="color: #000000; font-family: Verdana; font-size: 12px; background-color: #C6DEFF;">
		<td width=100%>
		<b><hdiits:caption captionid="dept.inquiryStatus" bundle="${deptLables}"/></b>
		<a href="javascript:void(0);" onclick="dispDept();"><img id="expandTicket3" src="images/expand.gif" /></a>
		<a href="javascript:void(0);" onclick="hideDept();"><img id="collapseTicket3" src="images/collapse.gif" style="display:none"/></a>
		</td>
	</tr>
 </table>
 </c:if>
 <br>

	<c:if test="${enqDtl[0].deptStatus!='NI'}">
	<%@include file="../departmentEnquiry/deptEnquiry.jsp" %>
	</c:if>
 
<c:if test="${enqDtl[0].deptStatus=='P' && enqDtl[0].suspensionStatus=='NI'}">
 <table width=100%>
	<tr>
		<td width=50%>	
		<hdiits:checkbox  name="chkBoxInitSusp" value="yes" caption=" " onclick="suspInitStatus();"/>
		<hdiits:submitbutton type="button" name="suspInitBtn" id= "suspInitBtn" captionid='dept.initSuspEnq' bundle="${deptLables}" readonly="true"/>
		</td>
			
		<td width=25%>	</td>
		<td width=25%>	</td>
	</tr>
 </table>
</c:if>

 <c:if test="${enqDtl[0].suspensionStatus!='NI'}">
<table width=100%  id="deptInquiry4" style="display:none">
 	<tr style="color: #000000; font-family: Verdana; font-size: 12px; background-color: #C6DEFF;">
		<td width="100%">
		<b><hdiits:caption captionid="dept.ordreInfo" bundle="${deptLables}"/></b>
		<a href="javascript:void(0);" onclick="dispSusp();"><img id="expandTicket4" src="images/expand.gif" /></a>
		<a href="javascript:void(0);" onclick="hideSusp();"><img id="collapseTicket4" src="images/collapse.gif" style="display:none"/></a>
		</td>
	</tr>
 </table>

	<%@ include file="../departmentEnquiry/suspension.jsp"%>
	<%@ include file="../departmentEnquiry/SuspensionReview.jsp"%> 
</c:if>

 <br>
 <c:if test="${enqDtl[0].prosecStatus!='NI'}">
 <table width=100%  id="deptInquiry5" style="display:none">
 	<tr style="color: #000000; font-family: Verdana; font-size: 12px; background-color: #C6DEFF;">
		<td width=100%>
		<b><hdiits:caption captionid="dept.pros" bundle="${deptLables}"/></b>
		<a href="javascript:void(0);" onclick="dispPros();"><img id="expandTicket5" src="images/expand.gif" /></a>
		<a href="javascript:void(0);" onclick="hidePros();"><img id="collapseTicket5" src="images/collapse.gif" style="display:none"/></a>
		</td>
	</tr>
 </table>

 <%@ include file="../departmentEnquiry/Prosecution.jsp"%> 
 </c:if>

 <br>
 <c:if test="${enqDtl[0].prosecStatus=='NI' && enqDtl[0].suspensionStatus=='P'}">
 <table width=100%>
	<tr>
		<td width=50%>	
		<hdiits:checkbox  name="chkBoxInitProsec" value="yes" caption=" " onclick="prosecInitStatus();"/>
		<hdiits:submitbutton type="button" name="prosecInitBtn" id="prosecInitBtn" captionid='dept.initProsecEnq' bundle="${deptLables}" readonly="true"/>
		</td>
			
		<td width=25%>	</td>
		<td width=25%>	</td>
	</tr>
 </table>
</c:if>

<table width=100%>
	
	<tr>
		<td>	
			<hr>
		</td>
	</tr>

	<tr>
		<td align="center">
		<hdiits:checkbox  name="chkBoxCloseEnq" value="yes" caption=" " onclick="closeBtnStatus();"/><hdiits:caption captionid="dept.closeEnq" bundle="${deptLables}"/>
		</td>
	</tr>

	<tr>
		<td align="center">
		
		<hdiits:submitbutton type="button" name="close" id="close" captionid="dept.closeEnq" bundle="${deptLables}" readonly="true"/>
		
		
		</td>
	</tr>

	<tr>
		<br>
		<td align="center">
		<hdiits:submitbutton  type="button" name="closeWin" id="closeWin" captionid='dept.closeWin' bundle="${deptLables}" onclick="closewindow();"/>
		<br>
		</td>
	</tr>
</table>

 <%@ include file="../departmentEnquiry/deptWFStatus.jsp"%> 

<hdiits:hidden name="xmlKey"/>
</div>
</div>

<script type="text/javascript">
		initializetabcontent("maintab");
	
</script>
	
	<hdiits:jsField jsFunction="validateDept1()" name="validateDept1"/>

	<jsp:include page="/WEB-INF/jsp/core/tabnavigation.jsp" >
	<jsp:param name="disableReset" value="true"/> 
	
	</jsp:include>

	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>

<script>

var enqAgainst="${enqAgainstUserId}";
var prePrelim="${enqDtl[0].prePrelimStatus}";
var prelim="${enqDtl[0].prelimStatus}";
var dept="${enqDtl[0].deptStatus}";
var suspen="${enqDtl[0].suspensionStatus}";
var prosec="${enqDtl[0].prosecStatus}";
var enqFlag="${enqDtl[0].flag}";
var alertDisp=new Array(50);

display();

alertDisp[0]="<fmt:message key='dept.enterEmpDtls'/>";
alertDisp[1]="<fmt:message key='dept.enterprePrelimStrtDt'/>";
alertDisp[2]="<fmt:message key='dept.enterSourceEnquiry'/>";
alertDisp[3]="<fmt:message key='dept.enterLastName'/>";
alertDisp[4]="<fmt:message key='dept.enterFirstName'/>";
alertDisp[5]="<fmt:message key='dept.addressInvalid'/>";
alertDisp[6]="<fmt:message key='dept.enterEnqOffDtls'/>";
alertDisp[7]="<fmt:message key='dept.enterOffPhone'/>";
alertDisp[8]="<fmt:message key='dept.selectEmp'/>"
alertDisp[9]="<fmt:message key='dept.enterAppDt'/>";
alertDisp[10]="<fmt:message key='dept.selectBranch'/>";
alertDisp[11]="<fmt:message key='dept.selectDept'/>";
alertDisp[12]="<fmt:message key='dept.enterAlleg'/>";
alertDisp[13]="<fmt:message key='dept.enterEnqOffDtls'/>";
alertDisp[14]="<fmt:message key='dept.enterEnqOff'/>";
alertDisp[15]="<fmt:message key='dept.enqOffLastName'/>";
alertDisp[16]="<fmt:message key='dept.enqOffFirstName'/>";
alertDisp[17]="<fmt:message key='dept.enqOffPhone'/>";
alertDisp[18]="<fmt:message key='dept.presentOffDtls'/>";
alertDisp[19]="<fmt:message key='dept.presentOff'/>";
alertDisp[20]="<fmt:message key='dept.presentLastName'/>";
alertDisp[21]="<fmt:message key='dept.presentFirstName'/>";
alertDisp[22]="<fmt:message key='dept.presentOffPhone'/>";
alertDisp[23]="<fmt:message key='dept.presidOffDtl'/>";
alertDisp[24]="<fmt:message key='dept.presidOff'/>";
alertDisp[25]="<fmt:message key='dept.presidLastName'/>";
alertDisp[26]="<fmt:message key='dept.presidFirstName'/>";
alertDisp[27]="<fmt:message key='dept.presidOfficePhone'/>";
alertDisp[28]="<fmt:message key='dept.frmDtInvalid'/>";
alertDisp[29]="<fmt:message key='dept.toDtInvalid'/>";
alertDisp[30]="<fmt:message key='dept.selectDiv'/>";
alertDisp[31]="<fmt:message key='dept.allowanceSubs'/>";
alertDisp[32]="<fmt:message key='dept.applidate'/>";
alertDisp[33]="<fmt:message key='dept.causeProsec'/>";
alertDisp[34]="<fmt:message key='dept.fromDtlessThanToDt'/>";
alertDisp[35]="<fmt:message key='dept.fromDtlessThanToDt'/>";
alertDisp[36]="<fmt:message key='dept.firstNameLastName'/>";
alertDisp[37]="<fmt:message key='dept.validNo'/>";
alertDisp[38]="<fmt:message key='dept.firstNameLastName'/>";
alertDisp[39]="<fmt:message key='dept.mobileLength'/>";
alertDisp[40]="<fmt:message key='dept.mnthInvalid'/>";
alertDisp[41]="<fmt:message key='dept.splCharNoTxt'/>";
alertDisp[42]="<fmt:message key='dept.phoneLength'/>";
alertDisp[43]="<fmt:message key='dept.splCharTxt'/>";
alertDisp[44]="<fmt:message key='dept.emailInvalid'/>";

</script>

</hdiits:form>