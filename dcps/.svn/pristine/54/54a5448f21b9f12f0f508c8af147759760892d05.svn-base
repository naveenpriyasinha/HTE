<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<script type="text/javascript" src="common/script/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>

<c:set var="resultObject" value="${result}" />
<c:set var="resultValue" value="${resultObject.resultValue}" />
<c:set var="months" value="${resultValue.Trng_Month}"></c:set>
<c:set var="empInfoList" value="${resultValue.list}"></c:set>
<c:set var="designame" value="${resultValue.desigName}"></c:set>
<c:set var="counter" value="${resultValue.counter}"></c:set>
<c:set var="monthName" value="${resultValue.monthName}"></c:set>
<c:set var="monthLookUp" value="${resultValue.monthLookupId}"></c:set>

<fmt:setBundle basename="resources.hr.review.MedicalReviewAlertLabels" var="alertLables" scope="request" />
<fmt:setBundle basename="resources.hr.review.MedicalReviewLabels" var="commonLables1" scope="request" />

<script type="text/javascript">
 
	function validateForm()
	{
		var validData=true;
		if ( document.forms.MedicalRev1.monthId.value == "Select")
	    {
	    	alert('<fmt:message  bundle="${alertLables}" key="SelectMonth"/>');
	        document.forms.MedicalRev1.monthId.focus();
	        validData = false;
	        return;
	    }
	    
	    if( validData==true)
		{
			document.MedicalRev1.action = "hrms.htm?actionFlag=getEmpForReview";
			window.document.MedicalRev1.submit();
			var submit=document.getElementById("submitForm");
		    submit.disabled=true;
		}
		endProcess();
	}
	
	function callOnSubmit()
	{
		var validData=false;
		var lenOfRadio=document.forms.MedicalRev1.hiddenEmpId.length;
	
		 if( typeof lenOfRadio!="number")
		 {
			lenOfRadio=[lenOfRadio];
	
			 for (var i=0;i<lenOfRadio.length;i++)
			{
				if (document.forms.MedicalRev1.hiddenEmpId.checked==true)
				{
					validData=true;
	 		 		break;
				}
			}
	 	}
		else
		{
	 		for(var i=0;i<lenOfRadio;i++)
		 	{
		 		if(document.forms.MedicalRev1.hiddenEmpId[i].checked == true)
		 		 {	
	 		 		validData=true;
	 		 		break;
		 		 }
	   		}
	  	}
	  	
		if (validData==true)
		{
			document.MedicalRev1.action = "hrms.htm?actionFlag=showEmpReviewPage";
			window.document.MedicalRev1.submit();
			var submit=document.getElementById("submitForm");
			submit.disabled=true;
		}
		else
	      {
	      	alert('<fmt:message  bundle="${alertLables}" key="CheckEmployee"/>');
	      }
	}
 
  	function closeWindow()
	{
		var urlstyle="hdiits.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
		document.MedicalRev1.action=urlstyle;
		document.MedicalRev1.submit();
	}

</script>

	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption
			captionid="EMPLOYEE.REVIEW" bundle="${commonLables1}" captionLang="single"/></b></a></li>
	</ul>
	</div>
	
	<div class="halftabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">

	<table class="tabhdiits:table" bordercolor="#386CB7" width="100%">
		<tr>
			<td>
				<hdiits:form name="MedicalRev1" validate="true" method="POST" encType="multipart/form-data">

			<div id="resig" name="resig">
			<table class="tabtable">
				<tr>
					<td colspan="1">
						<table width="100%" height="50%">
							<tr >
								<td  colspan="4">
									<font>
										<hdiits:fieldGroup titleCaptionId="HRMS.EmployeeSearch" bundle="${commonLables1}"></hdiits:fieldGroup>
									</font>
			 					</td>
							</tr>
						</table>
					</td>
				</tr>

				<tr id="medv">
					<td>
						<strong><u><font size="1.7" style=" color: black;"><hdiits:caption captionid="HRMS.SelectEmpFromCombo" bundle="${commonLables1}"/></font></u></strong>
					</td>
				</tr>

				<tr>
					<td colspan="1">
						<table border="0" width="100%">
							<tr>
								<td align="left" width="25%"> <hdiits:caption captionid="HRMS.Month" bundle="${commonLables1}"/></td>
								<td width="25%">
									<hdiits:select name="Month" bundle="${commonLables1}" captionid="HRMS.Month" validation="txt.isrequired" id="monthId" mandatory="true" sort="false">
										<hdiits:option value="Select"><fmt:message key="COMMON.DROPDOWN.SELECT"	bundle="${commonLables1}"/></hdiits:option>

										<c:forEach var="monthDesc" items="${months}">
											<option value="${monthDesc.lookupId}">${monthDesc.lookupDesc}</option>
										</c:forEach>
									</hdiits:select>
								</td>
								
								<td width="25%">
									<hdiits:button name="Search" type="button" caption="Search" captionid="HRMS.Search" bundle="${commonLables1}" onclick="validateForm()"/>
									<hdiits:button value="Close" name="Close2" type="button" id="closeForm2" captionid="HRMS.Close" bundle="${commonLables1}" onclick="closeWindow()"  />
								</td>
								
								<td width="25%"></td>
							</tr>
							
							<tr>
								<td  width=20% > 
   									<font color="#4AA02C">
    								<b>
    									<c:if test="${counter==1}">
    										${counter} <hdiits:caption captionid="HRMS.Record" bundle="${commonLables1}"/> 
    									</c:if>

				    					<c:if test="${counter>=2}">
    										${counter}  <hdiits:caption captionid="HRMS.Record" bundle="${commonLables1}"/> 
				    					</c:if>
				    				</b>
    								</font>
		   						</td>
							</tr>
						</table>
					</td>
				</tr>
				
				<c:if test="${counter==0}">	
					<table width="100%"  align="center">
						<br><br><br>
						<hr  align="center" width=" 50%">
						<table width="100%" align="center">
							<tr ></tr>
							<tr><th align="center"><hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/></th></tr>
							<tr></tr>
						</table>
						<hr align="center" width="50%">
					</table>
				</c:if>
				
				
				<tr>
					<td>
					<table border=1 bordercolor="black" width="100%" style="border-collapse: collapse" align="center"  id="empList" style="display:none">
						<tr  bgcolor="#C6DEFF" >
							<td align="center" ><hdiits:caption captionid="SELECT" bundle="${commonLables1}"/></td>
							<td align="center" ><hdiits:caption captionid="HRMS.SrNo" bundle="${commonLables1}"/></td>
							<td align="center" ><hdiits:caption captionid="HRMS.EmpName" bundle="${commonLables1}"/></td>
							<td align="center" ><hdiits:caption captionid="HRMS.EmpDesig" bundle="${commonLables1}"/></td>
							<td align="center" ><hdiits:caption captionid="DATE.BIRTH" bundle="${commonLables1}"/></td>
							<td align="center" ><hdiits:caption captionid="DATE.JOINING" bundle="${commonLables1}"/></td>
							<td align="center" ><hdiits:caption captionid="DATE.COMPLETION" bundle="${commonLables1}"/></td>
							<td align="center" ><hdiits:caption captionid="DATE.RETIREMENT" bundle="${commonLables1}"/></td>
							<td align="center" ><hdiits:caption captionid="HRMS.age" bundle="${commonLables1}"/></td>
						</tr>
						
						<c:forEach var="obj" items="${empInfoList}">

							<tr id="fullName">
								<td align="center" >
									<hdiits:radio name="hiddenEmpId" validation="sel.ischeck" id="hiddenEmpId" value="${obj.empId}-${obj.dsgnCode}-${obj.age}" errCaption="Check BOX 1" captionid="chk_box" />
									<label class='mandatoryindicator' id='mandatoryindicator_${obj.empId}'  style=" color: #000000; display:none;">*</label>
								</td>

								<td align="center" >
									<c:out value="${obj.srNo}"></c:out>
								</td>
								
								<td align="center" >
									<c:if test="${obj.fullName ne null}">
										<c:out value="${obj.fullName}"></c:out>
									</c:if>
									<c:if test="${obj.fullName eq null}">
										<hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/>
									</c:if>
								</td>
								
								<td align="center" >
									<c:if test="${obj.designation ne null}">
										<c:out value="${obj.designation}"></c:out></c:if>
									<c:if test="${obj.designation eq null}">
										<hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/>
									</c:if>
								</td>
								
								<td align="center" >
									<c:if test="${obj.dob ne null}">
										<fmt:formatDate value="${obj.dob}" pattern="dd/MM/yyyy"/>
									</c:if>
									<c:if test="${obj.dob eq null}">
										<hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/>
										</c:if>
								</td>
									
								<td align="center" >
									<c:if test="${obj.doj ne null}">
										<fmt:formatDate	value="${obj.doj}" pattern="dd/MM/yyyy"/>
									</c:if>
									<c:if test="${obj.doj eq null}">
										<hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/>
									</c:if>
								</td>
									
								<td align="center" >
									<c:if test="${obj.serviceCompletion ne null}">
										<fmt:formatDate	value="${obj.serviceCompletion}" pattern="dd/MM/yyyy" /></c:if>
									<c:if test="${obj.serviceCompletion eq null}">
										<hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/>
									</c:if>
								</td>
									
								<td align="center" >
									<c:if test="${obj.expDate ne null}">
										<fmt:formatDate	value="${obj.expDate}" pattern="dd/MM/yyyy" /></c:if>
									<c:if test="${obj.expDate eq null}">
										<hdiits:caption captionid="HRMS.NoRecord" bundle="${commonLables1}"/>
									</c:if>
								</td>
								
								<td align="center" ><c:out value="${obj.age}"></c:out></td>
							</tr>
						</c:forEach>
					</table>
					</td>
				</tr>
				
					<table width="100%">		
						<tr id="disabledMsg" style="display:none">
							<td>
								<label class='mandatoryindicator' style=" color: #000000;" >*</label><font size="1.5"><hdiits:caption captionid="HRMS.disabled" bundle="${commonLables1}"/></font>
							</td>
						</tr>	
					</table>
			</table>
			</div>


		<script type="text/javascript">
			
			if("${counter}"!=0 && "${counter}"!="")
			{
				var lenOfRadio=document.forms.MedicalRev1.hiddenEmpId.length;
 				if( typeof lenOfRadio!="number")
 				{
 					var val=document.forms.MedicalRev1.hiddenEmpId.value;
					var array=val.split("-");
					if(array[2]<='50' && (array[1]=='PC' || array[1]=='pc' || array[1]=='HC' || array[1]=='hc' || array[1]=='ASI' || array[1]=='asi'))
 					{
 						document.forms.MedicalRev1.hiddenEmpId.disabled="true";
					 	document.getElementById("disabledMsg").style.display="";
					 	document.getElementById('mandatoryindicator_'+array[0]).style.display="";
					}
 				}
 				else
				{
				 	for(var i=0;i<lenOfRadio;i++)
				 	{
					 	var val=document.forms.MedicalRev1.hiddenEmpId[i].value;
						var array=val.split("-");
						if(array[2]<='50' && (array[1]=='PC' || array[1]=='pc' || array[1]=='HC' || array[1]=='hc' || array[1]=='ASI' || array[1]=='asi'))
					 	{
						 	document.forms.MedicalRev1.hiddenEmpId[i].disabled="true";
						  	document.getElementById("disabledMsg").style.display="";
						  	document.getElementById('mandatoryindicator_'+array[0]).style.display="";
					 	}
				 	}
				}
 			}
 			
			initializetabcontent("maintab")
			
		</script>
		
		<hdiits:validate controlNames="tesxt" locale='<%=(String)session.getAttribute("locale")%>' />
				
		<br><br>
		
		<table align="center" width="100%">
			<tr>
				<td align="center">
					<hdiits:button name="Submit" type="button" caption="Submit" id="submitForm" onclick="callOnSubmit()" style="display:none" />
					<hdiits:button value="Close" name="Close1" type="button" id="closeForm1" onclick="closeWindow()" style="display:none" />
				</td>
			</tr>
		</table>
		
	</hdiits:form> 
	
	<c:if test="${monthName ne null}">
		<script>
			var monthName=document.getElementById("monthId");
			var idOfMonth=document.getElementById("monthId").value;
			//monthName.options[monthName.selectedIndex].text="${monthName}";
			document.getElementById("monthId").value="${monthLookUp}";
			monthName.selected="true";
		</script>
	</c:if>
		
	
	<c:forEach var="listEmp" items="${empInfoList}">
		<c:if test="${listEmp.empId!=0}">
			<script>
				var list =document.getElementById("empList");
				var submit =document.getElementById("submitForm");
				var reset =document.getElementById("closeForm1");
				list.style.display="";
				submit.style.display="";
				reset.style.display="";
			</script>
		</c:if>
	</c:forEach>
		
		
	</td>
	</tr>
</table>

</div>
</div>

<script type="text/javascript">
	initializetabcontent("maintab")
</script>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>