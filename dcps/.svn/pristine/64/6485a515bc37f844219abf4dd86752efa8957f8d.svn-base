<%
try {
%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ page import ="com.tcs.sgv.eis.valueobject.HrEisBranchMst, 
                  com.tcs.sgv.eis.valueobject.HrEisBankMst,
				  java.text.SimpleDateFormat" %>
 <script type="text/javascript" src="<c:url value="/script/hrms/eis/Address.js"/>">
</script>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request" />
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
<script type="text/javascript" src="<c:url value="/script/hrms/eis/commonUtils.js"/>">
</script>	

<script type="text/javascript">
function chkMicrCode()
{
	var MicrCode = document.getElementById("txtMicrCode").value;

	if(MicrCode.length != 0)
	{
		showProgressbar();
		var url='ifms.htm?actionFlag=chkMicrCode';
		var uri = '&MICRcode='+MicrCode;
		var myAjax = new Ajax.Request(url,
			       {
			        method: 'post',
			        asynchronous: true,
			        parameters:uri,
			        onSuccess: function (myAjax) {
						chk_MicrCode(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...');} 
			          } );
	}
}

function chk_MicrCode(myAjax)
{
	hideProgressbar();
	var XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var branchName = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if(branchName == "N"){
		var msg = 'This MICR Code does not belong to any branch. Do you want to add new MICR code?';
		if(confirm(msg)){
			
		}else{
			document.getElementById("txtMicrCode").value = "";
			document.getElementById("txtMicrCode").focus();
		}
	}else{		
		var branchAddr = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
		var msg1 = 'This MICR Code belongs to '+branchName+' with address :'+branchAddr;
		if(confirm(msg1)){
			
		}else{
			document.getElementById("txtMicrCode").value = "";
			document.getElementById("txtMicrCode").focus();
		}
	}
}
</script>

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="actionList" value="${resValue.actionList}" > </c:set> 
<c:set var="bankList" value="${resValue.bankList}" />
<c:set var="bankid" value="${resValue.bankid}" />
<c:set var="micrCode" value="${resValue.micrCode}" />
<c:set var="finalBrnchId" value="${resValue.finalBrnchId}" ></c:set>






<hdiits:form name="frmBF" validate="true" method="POST"
	action="hrms.htm?actionFlag=insertBranchMasterData&edit=Y" encType="multipart/form-data">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="BR.UPDATEBRANCHINFO" bundle="${commonLables}"/></b></a></li>
	</ul>
	</div>
	
	

	<div  id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin">    
   <TABLE width="80%" align="center"><br/>
 	<TR> 
 	<td  width="2%"></td>
 	<TD   align="left" width="20%">
 		<b><fmt:message key="BM.bankName" bundle="${commonLables}"/></b> 
 	</TD>		
	<TD  width="20%">
		<hdiits:select name="cmbBankName" default="${bankid}" validation="sel.isrequired"  id="bankID" sort="false"  mandatory="true" 
			size="1" caption="Bank Name" onchange="clearBranch()">
			<hdiits:option value="-1">-------------------Select-------------------</hdiits:option>
			
			<c:forEach items ="${resValue.bankList}" var="list">
			<c:choose>
			<c:when test="${list.bankCode == actionList.bankCode}">
			  
 			   <hdiits:option  value="${list.bankId}" selected="true">${list.bankName}</hdiits:option>
			  </c:when>
			  <c:otherwise>
			  
			<hdiits:option value="${list.bankId}"> ${list.bankName} </hdiits:option>
			</c:otherwise>
			</c:choose>
			</c:forEach>
			</hdiits:select>
			 <hdiits:hidden  default ="${actionList.branchId}" name="txtBranchID" caption="Branch ID"  />
	</TD>	
			<td  width="2%"></td>
			 <TD  align="left" width="20%"><b><fmt:message key="BR.NAME" bundle="${commonLables}"/></b>
			 </TD>
		<TD>			 
			<hdiits:text name="txtBranchName" default ="${actionList.branchName}" caption="Branch Name"  validation="txt.isrequired" id="branchName" size="30" maxlength="40"  mandatory="true"	 />	
		</TD>	
				  <hdiits:hidden id="bankname" name="bankname" default="${actionList.branchName}"/>
	</TR><tr></tr><tr></tr>
	
	
	
	<TR>	<td  width="2%"></td>
			<TD  align="left" width="20%"><b><fmt:message key="BR.ADD" bundle="${commonLables}"/></b>
			</TD>
			<TD>
				<hdiits:textarea  rows="3" cols="32" name="txtBranchAdd" maxlength="190" caption ="Address" default ="${actionList.branchAddress}">
				</hdiits:textarea>
			</TD>
				 
			<td  width="2%"></td>
			<TD  align="left" width="20%"><b><fmt:message key="PPMT.MICRCODE" bundle="${pensionLabels}"/></b>
			</TD>
			
		<TD  width="20%">
		
		 <hdiits:number	name="txtMicrCode" default ="${micrCode}" mandatory="true"  caption="MICR Code" validation="txt.isrequired,txt.isnumber" onblur="chkMicrCode();" maxlength="20" size="30" />
	   			<hdiits:hidden id="hidMicrCode" name="hidMicrCode" default="${micrCode}"/>
	   </TD>	
</tr><tr></tr><tr></tr>
	<TR>
			
				
	   <td  width="2%"></td>
		<TD  align="left"  width="20%"> <b><fmt:message key="PPMT.IFSCCODE" bundle="${pensionLabels}"/></b></TD>
		<TD>
			<hdiits:text name="txtIfscCode" id="txtIfscCode" default ="${actionList.ifscCode}"  mandatory="true" maxlength="20" validation="txt.isrequired" caption ="IFSC Code"  />		
		</TD>
		<td  width="2%"></td>
			<TD  align="left" width="20%">
			<b><fmt:message key="PPMT.CONTACT" bundle="${pensionLabels}"/></b>
			</TD>
			
		<TD  width="20%">
		
		 <hdiits:number	name="txtContactNo" default ="${actionList.contact}" caption="Contact No"  maxlength="20" />
	   
	   </TD>
	</TR>

	<TR>
	   <td  width="2%"></td>
			<TD align="left" width="20%"> 
				<b><fmt:message key="PPMT.TREASURYNAME" bundle="${pensionLabels}"/> </b>
			</TD>
	   <TD width="20%">
			 <select name="cmbTreasury" id="cmbTreasury" size="1" >
		       
		     <c:forEach items ="${resValue.treasuryList}" var="list1">		     
		     <c:choose>
		     	<c:when test="${list1.id == actionList.locationCode}">
		     		<option value="${list1.id}" selected="selected">${list1.desc}</option>
		     	</c:when>
		    	 <c:otherwise>	
					<option value="${list1.id}"> ${list1.desc} </option>
				</c:otherwise>
				</c:choose>
			</c:forEach>			
			
			</select><label class="mandatoryindicator" ${varImageDisabled}>*</label>
		</TD>
	</TR>
	
	</table> 
    
 <br/><br/><br/>
 
<hdiits:hidden default="getBranchView" name="givenurl"/> 
	
 
<fmt:setBundle basename="resources.payroll.payrollLables_en_US" var="Lables" scope="request"/>
	<table class="tabNavigationBar">
	<tr align="center">
		<td class="tabnavtdcenter" id="tabnavtdcenter"><hdiits:formSubmitButton
			name="formSubmitButton" value="Save" type="button"
			captionid="eis.save" bundle="${Lables}" /> 
		<hdiits:button name="Close" value="Close" type="button" captionid="eis.close"
			bundle="${Lables}" onclick="onBackfn();" /><%-- <hdiits:button
			name="Back" value="Back" type="button" captionid="eis.back"
			bundle="${Lables}" onclick="onBackfn();" />  <hdiits:resetbutton type="button" value="Reset" name="reset" />  --%>
		<hdiits:button name="Reset" type="button" value="Reset"
			onclick="resetForm()" /> <!-- <input type="reset" value="Reset"/>-->
		<script language="javaScript">             
              //if (navDisplay)
              //{
				//document.write('<input type="button" value="Reset" onClick="resetForm()">');
				//document.write('<input type="button" value="Previous" onClick="goToPrevTab()">');
				//document.write('<input type="button" value="Next" onClick="goToNextTab()">');
			 // }

			  /*function resetForm()
			  {
			  	if(confirm("All values will be reseted please confirm !") == true)
			  	{
			  		document.forms[0].reset();
			  	}

			  }*/
			  function onclosefn()
			  {
						document.forms[0].action="ifms.htm?actionFlag=getHomePage";
						document.forms[0].submit();
			  }
			  function onBackfn()
			  {						
						if(document.forms[0].givenurl!=null)
						{
						var url="ifms.htm?actionFlag=getBranchMasterDtls";
						document.forms[0].action=url;
						}
						else
						{
						document.forms[0].action="ifms.htm?actionFlag=getHomePage";
						}
						document.forms[0].submit();
			  }
			  function resetForm()
			  {
				  document.forms[0].cmbBankName.value = "-1";
				  document.forms[0].txtBranchName.value = "";				  
				  document.forms[0].txtBranchAdd.value = "";
				  document.forms[0].txtMicrCode.value = "";
				  document.forms[0].txtIfscCode.value = "";
				  document.forms[0].txtContactNo.value = "";
				  document.forms[0].cmbTreasury.value = "-1";				  
			  }

				</script> <!-- Message in gujarati is still remains....Jaspal.. --></td>
	</tr>
</table>
	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
				document.frmBF.cmbBankName.focus();
		if("${finalBrnchId}"=='Y'){
			alert('Newly Generated Branch Id for this MICR Code already exist.');
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

