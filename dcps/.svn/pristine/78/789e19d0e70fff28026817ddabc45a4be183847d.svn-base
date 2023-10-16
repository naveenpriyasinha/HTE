<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<html>
<head>
<%
try{
%>

<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.gpf.gpfLabels" var="gpfLables" scope="request" />
<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request" />


<script type="text/javascript" src="/script/common/commonfunctions.js"></script>

<script type="text/javascript">

function searchReq()
{
	var bank_name = document.getElementById("txtBankName").value;
	var branch_name = document.getElementById("txtBranchName").value;
	
	if(bank_name.length > 0 || branch_name.length > 0){
		var uri = 'ifms.htm?actionFlag=searchBranchName&bankName='+bank_name+'&branchName='+branch_name;
		document.branchMstView.action = uri ;
		enableAjaxSubmit(true);
		document.branchMstView.submit();
	}else{
		alert("Please Enter Bank Name Or Branch Name");
		document.getElementById("txtBranchName").focus();
	}
}

function getAllRecords()
{
	var uri = 'ifms.htm?actionFlag=getBranchMasterDtls';
	document.branchMstView.action = uri ;
	enableAjaxSubmit(true);
	document.branchMstView.submit();
}

</script>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="actionList" value="${resultValue.actionList}" > </c:set>
<c:set var="bankList" value="${resultValue.bankList}" />
<c:set var="listSize" value="${resultValue.listSize}" > </c:set>
<c:set var="bankIDlist" value="${resultValue.bankIDlist}" > </c:set>

</head>
<body>
<form method="POST" name="branchMstView" action="ifms.htm?viewName=BranchMaster">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="BR.BRANCHMASTER" bundle="${commonLables}"/></b></a></li>
		</ul>
	</div>
	
	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >	  
	
	<div align="center"> <h1>
	   <c:out value="${resultValue.msg}"/> <br> </h1> 
	</div>
	<table width="100%">
		<tr>			
			<td width="10%">
				<b><fmt:message key="PPMT.SEARCHBRANCH" bundle="${pensionLabels}"></fmt:message></b>
			</td>
			<td width="30%">
				<fmt:message key="PPMT.BANKNAME" bundle="${pensionLabels}"></fmt:message>
				 <input type="text" id="txtBankName" name="txtBankName" style="width: 70%"/>
				 <span id="roleIndicatorRegionBank" style="display: none"> <img src="./images/busy-indicator.gif" /></span>
			</td>
			<td width="30%">
				<fmt:message key="PPMT.BRANCHNAME" bundle="${pensionLabels}"></fmt:message>
				 <input type="text" id="txtBranchName" name="txtBranchName" style="width: 70%"/>
				 <span id="roleIndicatorRegionBranch" style="display: none"> <img src="./images/busy-indicator.gif" /></span>
			</td>
			<td width="10%">
				<hdiits:button type="button" captionid="BTN.SEARCH" bundle="${gpfLables}" id="btnGo" name="btnGo" onclick="searchReq();" ></hdiits:button>
			</td>
			
			<td width="15%">
				<hdiits:button type="button" captionid="BTN.GETALL" bundle="${gpfLables}" id="btnGetAll" name="btnGetAll" onclick="getAllRecords();" ></hdiits:button>
			</td>		
		</tr>	
	 </table> 
		<br>&nbsp;
		  <a href= "ifms.htm?actionFlag=getBranchMasterData">  Add new Entry </a>
		  <br> <br>
		  
		<c:if test="${resultValue.listSize ne 0}">
		  <display:table name="${actionList}" requestURI="" pagesize="50" id="row" export="false" style="width:70%">
			  
			<display:column class="tablecelltext" title="Branch Name" headerClass="datatableheader" style="font-size:12px;">
				<a href="ifms.htm?actionFlag=getMasterBranchData&branchId=${row[0]}&bankCode=${row[2]}&edit=Y">${row[1]}</a>
			</display:column>	
			  
			<display:column class="tablecelltext" title="Bank Name" headerClass="datatableheader" style="font-size:12px;" value="${row[4]}">
			</display:column>

			<display:column class="tablecelltext" title="MICR Code" headerClass="datatableheader" value="${row[3]}" style="font-size:12px;"></display:column>	
				<display:setProperty name="export.pdf" value="false" />
		</display:table>
	   </c:if>

		 <br>&nbsp;
	   	 <a href= "ifms.htm?actionFlag=getBranchMasterData"> Add new Entry </a>
	
	<br/><br/>
	
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab");
	</script>

</div>
<%
}
  	  catch(Exception e)
  	  {
  		  e.printStackTrace();
  	  }
%>
</form>
</body>
</html>

<ajax:autocomplete source="txtBankName" target="txtBankName"
	baseUrl="ifms.htm?actionFlag=getBankNameForAutoComplete"
	parameters="bankName={txtBankName}" className="autocomplete" minimumCharacters="3" indicator="roleIndicatorRegionBank" />
	
<ajax:autocomplete source="txtBranchName" target="txtBranchName"
	baseUrl="ifms.htm?actionFlag=getBranchNameForAutoComplete"
	parameters="branchName={txtBranchName},bankName={txtBankName}" className="autocomplete" minimumCharacters="3" indicator="roleIndicatorRegionBranch" />