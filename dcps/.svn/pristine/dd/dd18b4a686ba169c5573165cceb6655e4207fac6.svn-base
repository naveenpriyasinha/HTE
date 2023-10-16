<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>
<fmt:setBundle basename="resources.gpf.gpfLabels" var="gpfLables" scope="request" />
<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request" />
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="actionList" value="${resultValue.actionList}" > </c:set>

<script type="text/javascript">
function searchReq()
{
	var bank_name = document.getElementById("txtBankName").value;
	if(bank_name.length > 0){
		var uri = 'ifms.htm?actionFlag=searchBank&bankName='+bank_name;
		document.bankMstView.action = uri ;
		enableAjaxSubmit(true);
		document.bankMstView.submit();
	}else{
		alert("Please Enter Bank Name");
		document.getElementById("txtBankName").focus();
	}
}

function getAllRecords()
{
	var uri = 'ifms.htm?actionFlag=getBankMasterDtls';
	document.bankMstView.action = uri ;
	enableAjaxSubmit(true);
	document.bankMstView.submit();
}
</script>

<form method="POST" name="bankMstView" action="ifms.htm?viewName=BankMaster">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="BM.bankMaster" bundle="${commonLables}"/></b></a></li>
		</ul>
	</div>
	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >	  
	 <table width="100%">
		<tr>
			<td width="30%">
			</td>
			<td width="10%">
				<b><fmt:message key="PPMT.SEARCHBANK" bundle="${pensionLabels}"></fmt:message></b>
			</td>
			<td width="30%">
				<fmt:message key="PPMT.BANKNAME" bundle="${pensionLabels}"></fmt:message>
				 <input type="text" id="txtBankName" name="txtBankName" style="width: 70%"/>
				 <span id="roleIndicatorRegion" style="display: none"> <img src="./images/busy-indicator.gif" /></span>
			</td>
		
			<td width="10%">
				<hdiits:button type="button" captionid="BTN.SEARCH" bundle="${gpfLables}" id="btnGo" name="btnGo" onclick="searchReq();" ></hdiits:button>
			</td>
			
			<td width="15%">
				<hdiits:button type="button" captionid="BTN.GETALL" bundle="${gpfLables}" id="btnGetAll" name="btnGetAll" onclick="getAllRecords();" ></hdiits:button>
			</td>		
		</tr>	
	 </table>
	 <div align="center"> <h1><c:out value="${resultValue.msg}" />  </h1> </div> <br>&nbsp;
		  <a href= "ifms.htm?viewName=BankMaster">  Add new Entry </a>  
		  <br>
		  <display:table name="${actionList}" requestURI="" pagesize="50" sort="list" id="row" export="false" style="width:60%">
			  <display:column property="bankId" class="tablecelltext" title="Bank Id"  headerClass="datatableheader" style="text-align: left;font-size:12px;" />    
			  <display:column class="tablecelltext" title="Bank Name" headerClass="datatableheader" style="text-align: left;font-size:12px;">
			  <a href="ifms.htm?actionFlag=getBankDetails&bankid=${row.bankId}&edit=Y">${row.bankName}  </a>
			  </display:column>	
			  
			  
		  	  <display:setProperty name="export.pdf" value="false" />
  		  </display:table>
		 <br>&nbsp;
	  	 <a href= "ifms.htm?viewName=BankMaster">  Add new Entry </a>  
	  	 <br/><br/>
	
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab");
	</script>
</div>

<ajax:autocomplete source="txtBankName" target="txtBankName"
	baseUrl="ifms.htm?actionFlag=getBankNameForAutoComplete"
	parameters="bankName={txtBankName}" className="autocomplete" minimumCharacters="3" indicator="roleIndicatorRegion" />