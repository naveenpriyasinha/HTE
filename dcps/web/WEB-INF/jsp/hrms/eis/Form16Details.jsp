<%

try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page session="true" %>

<script type="text/javascript" src="common/script/commonfunctions.js"></script>
<script type="text/javascript" src="common/script/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript" src="script/common/commonCalculations.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/hrms/eis/ITOtherDetails.js"></script>
<script type="text/javascript" src="script/hrms/eis/PayTabNavigation.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/cmbBoxFillUp.js"/>"></script>
	

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>
<c:set var="dataList" value="${resValue.dataList}" />

<script type="text/javascript">

function updateForm16Details(){
 var yearValue = document.ITForm16Details.FinYear.value;
 var empId = document.getElementById("Employee_ID_Form16Details").value;

 	//alert("Year : " + yearValue + " and EMpl : " + empId);
	if(empId == ''){
		alert("Please Select Employee First");
	}
 	if(yearValue != 'Select' && empId != ''){
 		document.ITForm16Details.action = "./hrms.htm?actionFlag=updateForm16Details&directUpdate=N&year="+yearValue+"&empId="+empId;
 		document.ITForm16Details.submit();
 	}
 	return true;
}


</script>
<body >

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="enLables" scope="page"/>


 
<hdiits:form name="ITForm16Details" validate="true" method="POST"
	 encType="text/form-data">
	
	
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs" compact="compact">
			<li class="selected"><a href="#" rel="tcontent1"><font size="2"><b><hdiits:caption captionid="IT.UpdateOtherDetails" bundle="${enLables}"></hdiits:caption></b></font></a></li>
		</ul>
	</div>
	<div class="exhalftabcontentstyle">
	
	<div id="tcontent1" class="halftabcontent" tabno="0">
	<hdiits:a href="./hrms.htm?actionFlag=getForm16Details" bundle="${enLables}" captionid="eis.desig_add_nw_entry" name="insertForm16ITDetails" id="insertForm16ITDetails"></hdiits:a>
	    <br>
	    <table align="center">
	    <tr>
		<TD class="fieldLabel" colspan="2">
					<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
						<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
						<jsp:param name="SearchEmployee" value="Form16Details"/>
						<jsp:param name="formName" value="ITForm16Details"/>
						<jsp:param name="functionName" value="updateForm16Details"/>
					</jsp:include>
		 </td>
		 <td>
        <hdiits:hidden id="empId" name = "empId"  />
	    </td>
	    </tr>
	    <tr>   </tr>
	    <tr>   </tr>
	    </table>
	    <table align="center">
		<tr>
			<td width="10%" >&nbsp;</td>
			<td width="20%"><font size="2"><b><hdiits:caption captionid="IT.FinYr" bundle="${enLables}"></hdiits:caption> </b></font> </td>
			<td width="20%">
				<hdiits:select captionid="IT.FinYr" bundle="${enLables}" mandatory="true" validation="sel.isrequired"  name="FinYear"   id="FinYear" size="1" lookupName="FinancialYear" onchange="updateForm16Details()">
				       			<hdiits:option value="Select">------Select------</hdiits:option>
				</hdiits:select>
			</td>
			<td width="5%"></td>
		</tr>
		
		<tr> </tr> <tr> </tr>
		</table>
		
		<display:table name="${dataList}" requestURI=""  pagesize="${pageSize}" id="row" export="true" style="width:100%">
			  <display:column class="tablecelltext" title="Employee Name" headerClass="datatableheader"> 
				<a href="./hrms.htm?actionFlag=updateForm16Details&form16DtlId=${row.form16DtlId}&directUpdate=Y" id="updateForm16Record" name="updateForm16Record" >	
					${row.hrEisEmpMst.orgEmpMst.empFname} ${row.hrEisEmpMst.orgEmpMst.empMname} ${row.hrEisEmpMst.orgEmpMst.empLname} 
				</a>		  
			  </display:column>
			  <display:column class="tablecelltext" title="Other Allowances" headerClass="datatableheader" value="${row.otherAllow}" > </display:column>
			  <display:column class="tablecelltext" title="foreign Allowances" headerClass="datatableheader" value="${row.foreignAllow}" > </display:column>
			  <display:column class="tablecelltext" title="Tax Paid By Challan" headerClass="datatableheader" value="${row.challanTax}" > </display:column>
			  <display:column class="tablecelltext" title="Tax Deducted in Arrears" headerClass="datatableheader" value="${row.arrearTax}" > </display:column>
			  
			  <display:column class="tablecelltext" title="Travel Allowance" headerClass="datatableheader" value="${row.travelAllow}" > </display:column>
			  <display:column class="tablecelltext" title="Professional Tax" headerClass="datatableheader" value="${row.profTax}" > </display:column>
			  <display:column class="tablecelltext" title="HBA interest" headerClass="datatableheader" value="${row.hbaIntrest}" > </display:column>
			  <display:column class="tablecelltext" title="G.P.F / C.P.F" headerClass="datatableheader" value="${row.gpfCpf}" > </display:column>
			  <display:column class="tablecelltext" title="Government Insurance" headerClass="datatableheader" value="${row.govtInsurance}" > </display:column>
			  <display:column class="tablecelltext" title="Repayment of HBA" headerClass="datatableheader" value="${row.repayHba}" > </display:column>

			  <display:column class="tablecelltext" title="Financial Year" headerClass="datatableheader" value="${row.finYrId}" > </display:column>
		  	  <display:setProperty name="export.pdf" value="true" />
  		</display:table>
 	</div>  
 	<br>
	
	
 	<br>

 	</div>


		
	<script type="text/javascript">
	//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		window.FILL_COMBO_BOX_TAB_WISE = false;
		
		</script>
	
</hdiits:form>
</body>
<%
	} catch (Exception e) {
		e.printStackTrace();
	}
%>