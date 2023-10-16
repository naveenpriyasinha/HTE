
<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="addPyUsrVo" value="${resValue.addPyUsrVo}"></c:set>
<c:set var="dateList" value="${resValue.dateList}"></c:set>
<c:set var="leavePersonDesg" value="${resValue.leavePersonDesg}"></c:set>

<c:set var="EffectiveLeave" value="${resValue.EffectiveLeave}"></c:set>
<c:set var="map" value="${resValue.map}"></c:set>
<c:set var="dt" value="${resValue.dt}"></c:set>

<c:set var="LeavePersonSal" value="${resValue.LeavePersonSal}"></c:set>
<c:set var="TotalLeaveCharge" value="${resValue.TotalLeaveCharge}"></c:set>

<c:set var="approvedDays" value="${resValue.approvedDays}"></c:set>
<c:set var="pendingDays" value="${resValue.pendingDays}"></c:set>
<c:set var="approveCancelDays" value="${resValue.approveCancelDays}"></c:set>
<c:set var="pendingCancelDays" value="${resValue.pendingCancelDays}"></c:set>

<c:set var="toLev" value="${resValue.toLev}"></c:set>
<c:set var="ltotApprLev" value="${resValue.ltotApprLev}"></c:set>
<c:set var="ltotPenLev" value="${resValue.ltotPenLev}"></c:set>
<c:set var="ltotApprCancel" value="${resValue.ltotApprCancel}"></c:set>
<c:set var="ltotPenCancel" value="${resValue.ltotPenCancel}"></c:set>
<c:set var="inchargeDtlRlt_ReqId" value="${resValue.inchargeDtlRlt_ReqId}"></c:set>

<fmt:setBundle basename="resources.hr.addPay.AdditionalPayAlertMessage"
	var="alertLables" scope="request" />
<html>

<head></head>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/tagLibValidation.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/leave/DateVal.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/leave/DateDifference.js"/>"></script>

<script type="text/javascript">

 
function submitForm_formSubmitButton(formNameValue)
{
	var validData = validateForm_AdditionalPay();
	if( validData==true )
	{
	window.document.forms[formNameValue].submit();
	}
	endProcess();
}
function check(checkId)
{		

	//var x=document.getElementById("FromDate");	
	//x.value=checkId;
}
function validateForm_AdditionalPay()
{
	if(!mandatory_AddPayAttachment('<fmt:message  bundle="${alertLables}" key="AttachmentReq"/>'))
	{ 
	return false;
	}

	if(!checkAttachmentValue_AddPayAttachment('<fmt:message  bundle="${alertLables}" key="clickOnAttachment"/>'))
	{ 
	return false;
	}
	
 	return true;
 		
}


function getDate1()
   {
   		var dt= new Date();
   		var dd1=dt.getDate();
   		if(dd1<10)
   		{
   		dd1="0"+dd1;
   		}
   		var mm1=dt.getMonth()+1;
   		var yy1=dt.getYear();
   		var dt1=dd1+'/'+mm1+'/'+yy1;	
		document.getElementById("date2").innerHTML=dt1;
       
   }    
   
    function getDateDiff()
   {
   		
       var Date1=document.getElementById("ToDate").innerHTML.split("-");
	   var Date2=document.getElementById("frmDate").options[eval(document.getElementById("frmDate").value-1)].innerHTML.split("-");
    	
    	var diff;	
     
        Date1=Date1[0]+"/"+Date1[1]+"/"+Date1[2];
        Date2=Date2[0]+"/"+Date2[1]+"/"+Date2[2];
        toDate=new Date(Date1);
        fromDate=new Date(Date2); 
        diffrDate = dateDifference(fromDate,toDate);
        diff=dateDifference(fromDate,toDate);
    
      document.getElementById("ChrPeriod").innerHTML=dateDifference(fromDate,toDate);
      
      return diff;
   }
   
   function ValidSubmitDateDiff(tst,pendingLeave)
   {
   
   	if(pendingLeave > 0)
   	{
   		alert('<fmt:message  bundle="${alertLables}" key="pendingLeaveMsg"/>');   	
   	   return;
   	}
    //submitButton_formSubmitButton();
  
  
  	var Date1 = new Array();
        Date1= getDateAndTimeFromDateObj(document.getElementById("endDate").value);
	 
   if(getDateDiffInString(document.getElementById('curntDate').value, Date1[0])==false)  
   
   { 
   alert('<fmt:message  bundle="${alertLables}" key="DateValid"/>');
     return;
   } 
  
    if(!validateForm_AdditionalPay())
    {
  
    }
    else
    {
      if(tst<30)
      { 
      	alert('<fmt:message  bundle="${alertLables}" key="period30Days"/>');  
      	document.getElementById('frmSubmit').disabled=true;  
      	 
       document.AdditionalPay.action = "hrms.htm?actionFlag=payInfo";
        document.AdditionalPay.submit(); 
       
      }
      else
      {      
      	document.getElementById('frmSubmit').disabled=true; 
      	document.getElementById('frmClose').disabled=true; 
      
      		
        document.AdditionalPay.action = "hrms.htm?actionFlag=insertAdditionPayDetail";
        document.AdditionalPay.submit(); 
      }
      }
   }
function getDateDiffInString(strDate1,strDate2)
{ 
     //alert(strDate1+"===="+strDate2);
		strDate1 = strDate1.split("/"); 		
		starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
		starttime = new Date(starttime.valueOf()); 
		//End date split to UK date format 
		strDate2 = strDate2.split("/"); 
		endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 
		endtime = new Date(endtime.valueOf()); 												
		if(starttime  >=  endtime) 
		{
			return true;
			}
	else
	{	return false;}
}
  function LeaveTableDetail()
 {
 	if(document.getElementById("leaveDetail1").value=="V")
 	{
 		document.getElementById("LeaveDetails").style.display="";
 		document.getElementById("leaveDetail1").value="^"
 	}
 	else
 	{
 		document.getElementById("LeaveDetails").style.display="none";
 		document.getElementById("leaveDetail1").value="V";
 	}
 	
 }
 
 function LeaveTbl()
 {
    if(document.AdditionalPay.tabValue.value=="V")
 	{
 		document.getElementById("LeaveDetails").style.display="";
 		document.getElementById("img1").src="/hrms/images/redUp1.gif";
 		document.AdditionalPay.tabValue.value="^";
 	}
 	else
 	{
 		document.getElementById("LeaveDetails").style.display="none";
 		document.getElementById("img1").src="/hrms/images/greenDown.gif";
 		document.AdditionalPay.tabValue.value="V";
 	}
 	
 } 
  
 function getEmpDetail()
 {
 	 var ddt=document.getElementById('FromDate').value;
      if(ddt== -1)
      {
      	alert('<fmt:message  bundle="${alertLables}" key="selectFromDate"/>');      	
      }
      else
      {
      	document.AdditionalPay.action = "hrms.htm?actionFlag=getLeaveId";
               document.AdditionalPay.submit(); 
      }
 } 
 
 	function closePage()
	{
		document.AdditionalPay.action="hdiits.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
 		document.AdditionalPay.submit();
	}
 
</script>

<fmt:setBundle basename="resources.hr.addPay.AdditionalPayLabels"
	var="AddPay" scope="request" />


<hdiits:form name="AdditionalPay" validate="true" method="POST"
	encType="multipart/form-data">

	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"> <hdiits:caption
			captionid="AddPay.AddPayDetail" bundle="${AddPay}" captionLang="single"/></a></li>
	</ul>
	</div>
	<div class="tabcontentstyle" style="height: 100%">

	<div id="tcontent1" class="tabcontent" tabno="0"><br>

	
<hdiits:fieldGroup bundle="${AddPay}" titleCaptionId="AddPay.EmpDetail" id="empDtls" collapseOnLoad="false">	

	<table id="LeavePersonDetails" width="100%" align="center">


		<td width="50%">
		
		<hdiits:fieldGroup bundle="${AddPay}" titleCaptionId="AddPay.DetailApplicant" >
		<table id="InchargePerson" width="100%" align="center" frame="">

			<tr>
				<td width="50%"><b><hdiits:caption captionid="AddPay.Name"
					bundle="${AddPay}" /></b></td>
				<td width="50%"><c:out value="${map.name}" /></td>
			</tr>

			<tr>
				<td width="50%"><b><hdiits:caption captionid="AddPay.post"
					bundle="${AddPay}" /></b></td>
				<td width="50%"><c:out value="${map.postName}" /></td>
			</tr>


			<tr>
				<td width="50%"><b><hdiits:caption
					captionid="AddPay.Designation" bundle="${AddPay}" /></b></td>
				<td width="50%"><c:out value="${map.designation}" /></td>
			</tr>

			<tr>
				<td width="50%"><b><hdiits:caption
					captionid="AddPay.Department" bundle="${AddPay}" /></b></td>
				<td width="50%"><c:out value="${map.department}" /></td>
			</tr>

		</table>
		
		</hdiits:fieldGroup>
		</td>


		<td width="50%">
		
		<hdiits:fieldGroup bundle="${AddPay}" titleCaptionId="AddPay.DetailAddPostHrld">
		<table id="LeavePerson" width="100%" align="center">

			<tr>
				<td width="50%"><b><hdiits:caption
					captionid="AddPay.PostHeldInLue" bundle="${AddPay}" /></b>&nbsp;&nbsp;</td>
				<td width="50%" id="PostHeldInLue"><c:out
					value="${addPyUsrVo.leavePerUsrName}" />
			</tr>

			<tr>
				<td width="50%"><b><hdiits:caption captionid="AddPay.APH"
					bundle="${AddPay}" /></b>&nbsp;&nbsp;</td>
				<td width="50%" id="APH"><c:out value="${addPyUsrVo.postName}" />
				</td>
			</tr>

			<tr>
				<td width="50%"><b><hdiits:caption
					captionid="AddPay.Designation" bundle="${AddPay}" /></b></td>
				<td width="50%"><c:out value="${leavePersonDesg}" /></td>
			</tr>

			<tr>
				<td width="50%"><b><hdiits:caption
					captionid="AddPay.Department" bundle="${AddPay}" /></b>&nbsp;&nbsp;</td>
				<td width="50%" id="dept"><c:out value="${addPyUsrVo.deptName}" /></td>
			</tr>

		</table>
		</hdiits:fieldGroup>
		</td>

		 
	</table>
</hdiits:fieldGroup>

	
<hdiits:fieldGroup bundle="${AddPay}" titleCaptionId="AddPay.chrgPeriodDetails" id="chrgPeriodDtls" collapseOnLoad="false">	
	<table width="100%" align="center">
		<tr>
			<td width="25%"><b><hdiits:caption
				captionid="AddPay.AppDate" bundle="${AddPay}" /></b></td>
			<td id="date2" width="25%"><script>getDate1();</script></td>

			<td width="25%"><b><hdiits:caption
				captionid="AddPay.ChrPeriod" bundle="${AddPay}" /></b>&nbsp;&nbsp;</td>
			<td width="25%" id="ChrPeriod"><c:out
				value="${TotalLeaveCharge}" /> <hdiits:caption
				captionid="AddPay.Days" bundle="${AddPay}" /></td>

		</tr>

		<c:forEach var="dateListItr" items="${dateList}">
			<tr>
				<td width="25%"><b><hdiits:caption
					captionid="AddPay.FrmDate" bundle="${AddPay}" /></b>&nbsp;&nbsp;</td>
				<td width="25%"><fmt:formatDate
					value="${dateListItr.startDate}" pattern="dd/MM/yyyy" /></td>
				<td width="25%"><b><hdiits:caption
					captionid="AddPay.ToDate" bundle="${AddPay}" /></b>&nbsp;&nbsp;</td>
				<td width="25%"><fmt:formatDate value="${dateListItr.endDate}"
					pattern="dd/MM/yyyy" /></td>
			</tr>
			<input type="hidden" name="endDate" value="${dateListItr.endDate}"
				pattern="dd/MM/yyyy " />
		</c:forEach>

	</table>
</hdiits:fieldGroup>
	<input type="hidden" name="curntDate" value="${dt}" />

	
<hdiits:fieldGroup bundle="${AddPay}" titleCaptionId="AddPay.LeaveDetails" id="leaveDtls" collapseOnLoad="false">	
	<table id="AddPAyRequest" width="100%" align="center">
		<tr></tr>

		<tr>
			<td width="25%"><b><hdiits:caption
				captionid="AddPay.TotLeave" bundle="${AddPay}" /></b></td>
			<td width="25%" id="TotLeave"><c:out value="${toLev}" /></td>
			<td width="25%"><b><hdiits:caption
				captionid="AddPay.EffPeriod" bundle="${AddPay}" /></b>&nbsp;&nbsp;</td>
			<td width="25%" id="EffPeriod"><c:out value="${EffectiveLeave}" />
			<hdiits:caption captionid="AddPay.Days" bundle="${AddPay}" /> <input
				type="hidden" name="EffPeriod" value="${EffectiveLeave}"></td>

		</tr>

		<tr>
			<td width="25%"></td>
			<td width="25%"></td>
		</tr>
		<tr>
			<td width="25%"></td>
			<td width="25%"></td>
		</tr>
	</table>
	<br>
	<table width="100%" align="center" border="1" id="LeaveDetails"
		style="display: none;">
		<tr class="tabcontentstyle">
			<td width="6%" align="center"><b><hdiits:caption
				captionid="AddPay.Leaves" bundle="${AddPay}" /></b></td>
			<td width="6%" align="center"><b><hdiits:caption
				captionid="AddPay.CasualLeave" bundle="${AddPay}" /></b></td>
			<td width="6%" align="center"><b><hdiits:caption
				captionid="AddPay.Res_Holi_Leave" bundle="${AddPay}" /></b></td>
			<td width="6%" align="center"><b><hdiits:caption
				captionid="AddPay.Earned_Leave" bundle="${AddPay}" /></b></td>
			<td width="6%" align="center"><b><hdiits:caption
				captionid="AddPay.HalfPay_Leave" bundle="${AddPay}" /></b></td>
			<td width="6%" align="center"><b><hdiits:caption
				captionid="AddPay.Sick_Leave" bundle="${AddPay}" /></b></td>

			<td width="6%" align="center"><b><hdiits:caption
				captionid="AddPay.Paternity_Leave" bundle="${AddPay}" /></b></td>

			<td width="6%" align="center"><b><hdiits:caption
				captionid="AddPay.Extra_Ordinary_Leave" bundle="${AddPay}" /></b></td>

			<td width="6%" align="center"><b><hdiits:caption
				captionid="AddPay.TotLeave" bundle="${AddPay}" /></b></td>
		</tr>

		<tr>
			<td width="6%" class="tabcontentstyle" align="center"><b><hdiits:caption
				captionid="AddPay.apprLeaves" bundle="${AddPay}" /></b></td>
			<c:forEach var="appr" items="${approvedDays}">
				<td width="6%" align="center"><c:out value="${appr}" /></td>
			</c:forEach>
			<td width="6%" align="center"><c:out value="${ltotApprLev}" /></td>
		</tr>

		<tr>
			<td width="6%" class="tabcontentstyle" align="center"><b><hdiits:caption
				captionid="AddPay.penLeaves" bundle="${AddPay}" /></b></td>
			<c:forEach var="pending" items="${pendingDays}">
				<td width="6%" align="center"><c:out value="${pending}" /></td>
			</c:forEach>
			<td width="6%" align="center"><c:out value="${ltotPenLev}" /></td>
		</tr>

		<tr>
			<td width="6%" class="tabcontentstyle" align="center"><b><hdiits:caption
				captionid="AddPay.apprCancelLev" bundle="${AddPay}" /></b></td>
			<c:forEach var="apprCancel" items="${approveCancelDays}">
				<td width="6%" align="center"><c:out value="${apprCancel}" /></td>
			</c:forEach>
			<td width="6%" align="center"><c:out value="${ltotApprCancel}" /></td>
		</tr>

		<tr>
			<td width="6%" class="tabcontentstyle" align="center"><b><hdiits:caption
				captionid="AddPay.penCancelLev" bundle="${AddPay}" /></b></td>
			<c:forEach var="penCancel" items="${pendingCancelDays}">
				<td width="6%" align="center"><c:out value="${penCancel}" /></td>
			</c:forEach>
			<td width="6%" align="center"><c:out value="${ltotPenCancel}" />
			<input type="hidden" name="BasSalary" value="${LeavePersonSal}">
			<input type="hidden" name="leavePersonUserId"
				value="${addPyUsrVo.leavePerUsrId}"> <input type="hidden"
				name="hrInchgDtlId" value="${addPyUsrVo.hrInchgDtlId}"></td>
		</tr>

	</table>
</hdiits:fieldGroup>
	
	<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
		<jsp:param name="attachmentName" value="AddPayAttachment" />
		<jsp:param name="formName" value="AdditionalPay" />
		<jsp:param name="attachmentType" value="Document" />
		<jsp:param name="multiple" value="N" />
		<jsp:param name="mandatory" value="Y" />
	</jsp:include>

	<table id="frmSub" align="center" width="100%">
		<tr>
			<td align="center"><hdiits:button name="formSubmitButton"
				id="frmSubmit" type="button" captionid="AddPay.Submit"
				bundle="${AddPay}"
				onclick="ValidSubmitDateDiff('${TotalLeaveCharge}','${ltotPenLev}')" />
			<b><hdiits:button name="frmClose" id="frmClose" type="button"
				captionid="AddPay.close" bundle="${AddPay}" onclick="closePage();" /></b>
			</td>
		</tr>
	</table>

	<hdiits:hidden name="inchargeDtlRltReqId" default="${inchargeDtlRlt_ReqId}"/>
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
	<script type="text/javascript">		
		initializetabcontent("maintab")
	</script></div>
	</div>
</hdiits:form>

</html>
<%
	}

	catch (Exception e) {
		e.printStackTrace();
	}
%>


