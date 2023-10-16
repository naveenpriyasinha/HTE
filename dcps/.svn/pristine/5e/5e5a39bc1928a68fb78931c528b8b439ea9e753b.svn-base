
<%
try {
%>

<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="ReqPayVoGen" value="${resValue.ReqPayVoGen}"></c:set>
<c:set var="LeavePersonSal" value="${resValue.LeavePersonSal}"></c:set>
<c:set var="requestId" value="${resValue.requestId}"></c:set>
<c:set var="cFlag" value="${resValue.cFlag}"></c:set>
<c:set var="dateList" value="${resValue.dateList}"></c:set>

<c:set var="approvedDays" value="${resValue.approvedDays}"></c:set>
<c:set var="pendingDays" value="${resValue.pendingDays}"></c:set>
<c:set var="approveCancelDays" value="${resValue.approveCancelDays}"></c:set>
<c:set var="pendingCancelDays" value="${resValue.pendingCancelDays}"></c:set>

<c:set var="toLev" value="${resValue.toLev}"></c:set>
<c:set var="ltotApprLev" value="${resValue.ltotApprLev}"></c:set>
<c:set var="ltotPenLev" value="${resValue.ltotPenLev}"></c:set>
<c:set var="ltotApprCancel" value="${resValue.ltotApprCancel}"></c:set>
<c:set var="ltotPenCancel" value="${resValue.ltotPenCancel}"></c:set>
<c:set var="fileId" value="${resValue.fileId}" />

<fmt:setBundle basename="resources.hr.addPay.AdditionalPayAlertMessage"
	var="alertLables" scope="request" />
<fmt:setBundle basename="resources.hr.addPay.AdditionalPayLabels" var="AddPay"
	scope="request" />

<script type="text/javascript">

function verify()
    {
    	
		if(document.getElementById("UserCal").checked==true)
		{
			 var element = document.getElementById('UserSancAmt').value;        
         	  var fpnum = /^-{0,1}\d*\.{0,1}\d*$/g;
			if (fpnum.test(element))
			{
		
				if(element=='')
				{
				alert('<fmt:message  bundle="${alertLables}" key="userVal"/>');
				return false;
				}
				else
				{			
						if(charvalidate()) {								
						return true;
								
						}
						else {
						alert('<fmt:message  bundle="${alertLables}" key="Explanation_specialCharacter"/>');
							return false;
						}				
				}
			} 
			else 
			{
				alert('<fmt:message  bundle="${alertLables}" key="numericVal"/>');
				return false;
			}
		}
		else
		{
			if(charvalidate()) {	
						return true;	
						}
						else {
							alert('<fmt:message  bundle="${alertLables}" key="Explanation_specialCharacter"/>');
							return false;
						}	
		}    
       

    }
function getDate1()
   {
   		
   		var dt= new Date();
   		var dd1=dt.getDate();
   		var mm1=dt.getMonth()+1;
   		var yy1=dt.getYear();
   		var dt1=dd1+'/'+mm1+'/'+yy1;
	
		document.getElementById("date2").innerHTML=dt1;
 
   }  
  function Submit_Approval()
  {
  		if(verify())
  		{
  			document.getElementById('frmApprove1').disabled=true;
  			document.getElementById('frmForward').disabled=true;
  			
		 	document.AdditionalPayRequest.action = "hrms.htm?actionFlag=setAdd_PayAppr";
    	    document.AdditionalPayRequest.submit(); 
        }
       
        
  }
  
  function Submit_Forward()
  {
  		if(verify())
  		{
  			document.getElementById('frmApprove1').disabled=true;
  			document.getElementById('frmForward').disabled=true;
  			
		 	document.AdditionalPayRequest.action = "hrms.htm?actionFlag=setAddPayForwardAct";
    	    document.AdditionalPayRequest.submit(); 
        }
       
        
  }
  

function changeSystem()
{
	//if(document.AdditionalPayRequest.SystemComp.checked==true)
	{
	document.AdditionalPayRequest.UserSancAmt.disabled=true;
	document.AdditionalPayRequest.UserSancAmt.style.backgroundColor='aliceblue';

	document.getElementById("UserCal").checked=false;
	document.getElementById("SysCal").checked=true;

	document.AdditionalPayRequest.sysAmount.disabled=false;
	document.AdditionalPayRequest.sysAmount.style.backgroundColor='white';
	} 
}
function changeUser()
{
	if(document.AdditionalPayRequest.UserCal.checked==true)
	{
	document.AdditionalPayRequest.sysAmount.disabled=true;
	document.AdditionalPayRequest.sysAmount.style.backgroundColor='aliceblue';

	document.getElementById("SysCal").checked=false;
	document.getElementById("UserCal").checked=true;
	
	
 	document.AdditionalPayRequest.UserSancAmt.disabled=false;
	document.AdditionalPayRequest.UserSancAmt.style.backgroundColor='white';
	}
}  
 
 function LeaveTbl()
 {
    if(document.AdditionalPayRequest.tabValue.value=="V")
 	{
 		document.getElementById("LeaveDetails").style.display="";
 		document.getElementById("img1").src="/hrms/images/redUp1.gif";
 		document.AdditionalPayRequest.tabValue.value="^";
 	}
 	else
 	{
 		document.getElementById("LeaveDetails").style.display="none";
 		document.getElementById("img1").src="/hrms/images/greenDown.gif";
 		document.AdditionalPayRequest.tabValue.value="V";
 	}
 	
 } 
 
 	function  charvalidate()
		{		
		     var iChars = "`~!@#$%^&*()_-+={}[]|\:;<>,-?/'"+"\"";
		     var element=document.getElementById('Explanation').value;
		     
		     var cnt=0;
         	  var fpnum =/^\d*$/g;         	           	   
         	   spec1=iChars.split(''); 
         	   
         	   if(element !=' ' || element!= null)   
         	   {   	   
         	   		den=element.split(''); 
         	  		for(i=0;i<element.length;i++)
         	  		{     
         	  		  	for(j=0;j<iChars.length;j++)
         	     		{         	     			
                      		if(den[i]==spec1[j] )
                      		{
                       		 cnt +=1;
                      		}    	     
         	     		}
         	   		}
         	   }
         	   
         	   if(cnt==0) 	   {
         	    	return true;
         	   }
         	   else{
         	   	return false;
         	   }
         } 
        
        
 </script>

<hdiits:form name="AdditionalPayRequest" method="POST" validate="true"  action="./hrms.htm?actionFlag=setAddPayForwardAct" encType="text/form-data">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption
				captionid="AddPay.addPayRequest" bundle="${AddPay}" captionLang="single"/></b></a></li>

	</ul>
	</div>
	<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent">
<table id="empDtl"  width="100%" align ="center">

<TR bgcolor="#386CB7" >
<td width="100%" align="center" height="20px"><b><u><FONT color="WHITE"><fmt:message  key ="AddPay.EmpDetail" bundle="${AddPay}"/></FONT></u>
<tr>
</tr>
</table>

	<table id="LeavePersonDetails" width="100%" align="center">
		
		<tr>
		
			<td width="50%">
			<fieldset >
	<legend style="font-size: larger;font-color:red;"  ><b><fmt:message key="AddPay.DetailApplicant" bundle="${AddPay}"/></b></legend>
		
			<table id="InchargePerson" width="100%" align="center" frame="">
				<tr>
					<td width="50%"><b><hdiits:caption captionid="AddPay.Name"
						bundle="${AddPay}" /></b></td>

					<td width="50%"><c:out value="${ReqPayVoGen.empInchargeName}" /></td>
				</tr>

				<tr>
					<td width="50%"><b><hdiits:caption captionid="AddPay.post"
						bundle="${AddPay}" /></b></td>
					<td width="50%"><c:out value="${ReqPayVoGen.postName}" /></td>
				</tr>

				<tr>
					<td width="50%"><b><hdiits:caption
						captionid="AddPay.Designation" bundle="${AddPay}" /></b></td>
					<td width="50%"><c:out value="${ReqPayVoGen.designation}" /></td>
				</tr>

				<tr>
					<td width="50%"><b><hdiits:caption
						captionid="AddPay.Department" bundle="${AddPay}" /></b></td>
					<td width="50%"><c:out value="${ReqPayVoGen.department}" /></td>
				</tr>

				<tr>
					<td width="50%" height="20"></td>
				</tr>


			</table>
		</fieldset>
			</td>


			<td width="50%">
			<fieldset >
	<legend style="font-size: larger;font-color:red;"  ><b><fmt:message key="AddPay.DetailApplicant" bundle="${AddPay}"/></b></legend>
		
			<table id="LeavePerson" width="100%" align="center">

				<tr>
					<td width="50%"><b><hdiits:caption
						captionid="AddPay.PostHeldInLue" bundle="${AddPay}" /></b>&nbsp;&nbsp;</td>
					<td width="50%" id="PostHeldInLue"><c:out
						value="${ReqPayVoGen.empLeavePersonName}" />
				</tr>

				<tr>
					<td width="50%"><b><hdiits:caption captionid="AddPay.APH"
						bundle="${AddPay}" /></b>&nbsp;&nbsp;</td>
					<td width="50%" id="APH"><c:out
						value="${ReqPayVoGen.leavePersonPost}" /></td>
				</tr>

				<tr>
					<td width="50%"><b><hdiits:caption
						captionid="AddPay.Designation" bundle="${AddPay}" /></b>&nbsp;&nbsp;</td>
					<td width="50%" id="desgn"><c:out
						value="${ReqPayVoGen.leavePersonDesignation}" /></td>
				</tr>

				<tr>
					<td width="50%"><b><hdiits:caption
						captionid="AddPay.Department" bundle="${AddPay}" /></b>&nbsp;&nbsp;</td>
					<td width="50%" id="dep"><c:out
						value="${ReqPayVoGen.leavePersonDepartment}" /></td>
				</tr>

				<tr>
					<td width="50%"><b><hdiits:caption
						captionid="AddPay.BasSalary" bundle="${AddPay}" /></b>&nbsp;&nbsp;</td>
					<td width="50%" id="BasSalary"><c:out
						value="${LeavePersonSal}" /></td>
				</tr>

			</table>
		</fieldset>
			</td>

		</tr>
	</table>

	<table width="100%" align="center">
		<TR bgcolor="#386CB7">
			<td width="100%" align="center" height="20px"><b><u><FONT
				color="WHITE"><fmt:message key="AddPay.chrgPeriodDetails" bundle="${AddPay}" /></FONT></u> </b></td>
		</TR>
	</table>

	<table width="100%" align="center">
		<tr>
			<td width="25%"><b><hdiits:caption
				captionid="AddPay.AppDate" bundle="${AddPay}" /></b></td>
			<td id="date2" width="25%"><script>getDate1();</script></td>

			<td width="25%"><b><hdiits:caption captionid="AddPay.ChrPeriod" bundle="${AddPay}" /></b>&nbsp;&nbsp;</td>
			<td width="25%" id="ChrPeriod"><c:out
				value="${ReqPayVoGen.chrgPeriod}" />  <hdiits:caption captionid="AddPay.Days" bundle="${AddPay}" />
				<input type="hidden" name="requestId" value="${requestId}"> 
				<input type="hidden" name="tabValue" value="^"></td>
				 <hdiits:hidden	name="fileId" id="fileId" default="${fileId}" />
			
		</tr>
		
		<c:forEach var="dateListItr" items="${dateList}">
	<tr>
		<td width="25%"><b><hdiits:caption captionid="AddPay.FrmDate" bundle="${AddPay}"/></b>&nbsp;&nbsp;</td>
		<td width="25%"><fmt:formatDate value="${dateListItr.startDate}" pattern="dd/MM/yyyy"/></td>
		<td width="25%"><b><hdiits:caption captionid="AddPay.ToDate" bundle="${AddPay}" /></b>&nbsp;&nbsp;</td>
		<td width="25%"><fmt:formatDate value="${dateListItr.endDate}" pattern="dd/MM/yyyy"/></td>
	</tr>
</c:forEach>
	</table>

	<table width="100%" align="center">
		<TR bgcolor="#386CB7">
			<td width="100%" align="center" height="20px"><b><u><FONT
				color="WHITE"><fmt:message key="AddPay.LeaveDetails" bundle="${AddPay}" /></FONT></u> </b></td>
		</TR>
	</table>

	<table id="AddPAyRequest" width="100%" align="center">
		<tr></tr>


		<tr>	
			<td width="25%"><b><hdiits:caption  captionid="AddPay.TotLeave" bundle="${AddPay}" /></b></td>
			<td width="25%" id="TotLeave"><c:out value="${toLev}" /></td>
			<td width="25%"><b><hdiits:caption
				captionid="AddPay.EffPeriod" bundle="${AddPay}" /></b>&nbsp;&nbsp;</td>
			<td width="25%" id="EffPeriod"><c:out
				value="${ReqPayVoGen.effectivePeriod}" />  <hdiits:caption captionid="AddPay.Days" bundle="${AddPay}" /> </td>
				
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
			<td width="6%" align="center"><c:out value="${ltotPenCancel}" /></td>
		</tr>

	</table>

	<table width="100%" align="center" id="reqDetail">
		<TR bgcolor="#386CB7">
			<td width="100%" align="center" height="20px"><b><u><FONT
				color="WHITE"><fmt:message key="AddPay.AddPayReqDetail" bundle="${AddPay}" /></FONT></u> </b></td>
		</TR>
	</table>


	<table id="AddPAyRequest1" width="100%" align="center">
		<tr></tr>


		<tr>
			<td width="25%"><b><hdiits:radio name="AmtCal" id="SysCal"
				captionid="AddPay.SysCal" bundle="${AddPay}" value="S"
				  readonly="true"/></b> &nbsp;&nbsp;</td>
		</tr>
		<tr>
			<td width="25%"><hdiits:caption captionid="AddPay.SanctionAmt"
				bundle="${AddPay}" />&nbsp;&nbsp;</td>
			<td width="25%"><input type="text" name="sysAmount"
				id="sysAmount" value="${ReqPayVoGen.sysAmount}"  >
			</td>
		</tr>

		<tr>
			<td width="25%"><b><hdiits:radio name="AmtCal" id="UserCal"
				captionid="AddPay.UserSanctionAmt" bundle="${AddPay}" value="U"
			readonly="true" /></b> &nbsp;&nbsp;</td>
		</tr>

		<tr>
			<td width="25%"><hdiits:caption captionid="AddPay.SanctionAmt"
				bundle="${AddPay}" />&nbsp;&nbsp;</td>

			<td width="25%"><hdiits:text captionid="AddPay.UserSanctionAmt"
				name="UserSancAmt" id="UserSancAmt" bundle="${AddPay}"  
/></td>
			<script>changeSystem();</script>
		</tr>

		<tr>
			<td width="25%"><b><hdiits:caption
				captionid="AddPay.Explanation" bundle="${AddPay}" /></b>&nbsp;&nbsp;</td>

			<td width="50%"><hdiits:textarea captionid="AddPay.Explanation" id="Explanation"
				bundle="${AddPay}" name="Explanation"
				default="${ReqPayVoGen.explanation}" /></td>
		</tr>

		<tr>
			<td width="25%"><b><hdiits:caption
				captionid="AddPay.Application" bundle="${AddPay}" /></b>&nbsp;&nbsp;</td>
			<td id="Application" width="25%"></td>

			<td width="25%">&nbsp;&nbsp;</td>
			<td id="chOrder" width="25%"></td>
		</tr>
	</table>

	<table id="ApprDetail" width="100%" align="center">

		<c:set var="vl" value="${ReqPayVoGen.compFlag}"></c:set>
		<c:if test="${vl=='S'}">
			<tr>
				<td width="25%"><b><hdiits:caption
					captionid="AddPay.SysCal" bundle="${AddPay}" /></b></td>
				<td width="25%"><c:out value="${ReqPayVoGen.sysAmount}" /></td>
				<td width="25%"></td>
				<td width="25%"></td>
			<tr>
			<tr>
				<td width="25%"><b><hdiits:caption
					captionid="AddPay.Explanation" bundle="${AddPay}" /></b>&nbsp;&nbsp;</td>
				<td width="25%"><hdiits:textarea captionid="AddPay.Explanation"
					bundle="${AddPay}" name="Explanation1"
					default="${ReqPayVoGen.explanation}"   /></td>
			</tr>
		</c:if>
		<c:if test="${vl=='U'}">
			<tr>
				<td width="25%"><b><hdiits:caption
					captionid="AddPay.SysCal" bundle="${AddPay}" /></b></td>
				<td width="25%"><c:out value="${ReqPayVoGen.sysAmount}" /></td>
				<td width="25%"><b><hdiits:caption
					captionid="AddPay.UserSanctionAmt" bundle="${AddPay}" /></b></td>
				<td width="25%"><c:out value="${ReqPayVoGen.userAmt}" /></td>
			</tr>
			<tr>
				<td width="25%"><b><hdiits:caption
					captionid="AddPay.Explanation" bundle="${AddPay}" /></b>&nbsp;&nbsp;</td>
				<td width="25%"><hdiits:textarea captionid="AddPay.Explanation"
					bundle="${AddPay}" name="Explanation2" readonly="true"
					default="${ReqPayVoGen.explanation}"  /></td>
			</tr>
		</c:if>
	<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" />
	</table>

	<table id="PayAttmnt">
		<tr>
			<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
				<jsp:param name="attachmentName" value="AddPayAttachment" />
				<jsp:param name="formName" value="AdditionalPayRequest" />
				<jsp:param name="attachmentType" value="Document" />
				<jsp:param name="multiple" value="N" />
			</jsp:include>
		</tr>
	</table>

	<script type="text/javascript">
				document.getElementById('target_uploadAddPayAttachment').style.display='none';
				document.getElementById('formTable1AddPayAttachment').firstChild.firstChild.style.display='none';
			</script></div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
			
		document.getElementById("LeaveDetails").style.display="none";		
		</script>
	
	
	
	


	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>

	<script type="text/javascript">
    initializetabcontent("maintab")
</script></div>

</hdiits:form>

<%
		} catch (Exception e) {

		e.printStackTrace();
	}
%>
