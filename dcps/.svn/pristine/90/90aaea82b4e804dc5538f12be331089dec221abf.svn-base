
<%
	try {
%>
<%@ include file="../../../../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<fmt:setBundle basename="resources.eis.eisLables_en_US"
	var="ReportingDDO" scope="request" />
	<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="MonthlookUpList" value="${resValue.MonthlookUpList}"></c:set>
<c:set var="YearLookUpList" value="${resValue.YearLookUpList}"></c:set>
<c:set var="schemeList" value="${resValue.schemedatalstone}"></c:set>
<c:set var="ddoDtls" value="${resValue.ddoDtls}"></c:set>
<c:set var="ddoListSize" value="${resValue.ddoListSize}"></c:set>


<head>
<script type="text/javascript"
	src="<c:url value="/script/common/ajaxLogoutError.js"/>"></script>
<script type="text/javascript" src="script/common/cmnMstScr.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script>
function selectAllDDO(){
	var ddoLstSize='${ddoListSize}';
	if(document.getElementById("chkSelectAll").checked==true){
		for(var i=1; i<=ddoLstSize;i++){
			document.getElementById("ddoList"+i).checked=true;
		}
	}
	else{
		for(var j=1; j<=ddoLstSize;j++){
			document.getElementById("ddoList"+j).checked=false;
		}
	}
}


/* function getSubSchemeDetails() 
{ 
        var txtSchemeCode = document.getElementById("schemecode").value; 
        
                var uri = 'ifms.htm?actionFlag=getSubSchemeDetails'; 
                var level=2; 
                var url = 'txtSchemeCode='+txtSchemeCode+'&level='+level; 
                
                
                var myAjax = new Ajax.Request(uri, 
                               { 
                                method: 'post', 
                                asynchronous: false, 
                                parameters:url, 
                                onSuccess: function(myAjax) { 
                                                getDataStateChangedForPopUpSchemes(myAjax); 
                                        }, 
                                onFailure: function(){ alert('Something went wrong...')} 
                                  } ); 
        
}  */



function getSubSchemeDetails() 
{ 
        var txtSchemeCode = document.getElementById("schemecode").value; 
        
                var uri = 'ifms.htm?actionFlag=getSubSchemeDetails'; 
                var level=2; 
                var url = 'txtSchemeCode='+txtSchemeCode+'&level='+level; 
                
                
                var myAjax = new Ajax.Request(uri, 
                               { 
                                method: 'post', 
                                asynchronous: false, 
                                parameters:url, 
                                onSuccess: function(myAjax) { 
                                                getDataStateChangedForPopUpSchemes(myAjax); 
                                        }, 
                                onFailure: function(){ alert('Something went wrong...')} 
                                  } ); 
        
} 

function getDataStateChangedForPopUpSchemes(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	
	var cmbSchemeName = document.getElementById('cmbSubSchemeName');
	cmbSchemeName.options.length = 0;
	var optn = document.createElement("OPTION");
	optn.text = "-- Select --";
	optn.value = "-1";
	cmbSchemeName.options.add(optn);

	var totalSchemes = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	
	var optnScheme ;

	var count=1;
	while(count<=(3*totalSchemes))
	{
		optnScheme = document.createElement("OPTION");
		optnScheme.text = XmlHiddenValues[0].childNodes[count].firstChild.nodeValue;
		optnScheme.title= XmlHiddenValues[0].childNodes[count+1].firstChild.nodeValue;
		optnScheme.value = XmlHiddenValues[0].childNodes[count+1].firstChild.nodeValue;
		document.getElementById("cmbSubSchemeName").options.add(optnScheme);
		demandCode = XmlHiddenValues[0].childNodes[count+2].firstChild.nodeValue;
		count=count+3;
	}
}



</script>
</head>

<hdiits:form name="frmReportingDDO" validate="true" method="post">

	<hdiits:hidden name="seladminDept" id="seladminDept" default="2" />
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>Forward
		Pay Bill</b></a></li>
	</ul>
	</div>
	<br />
	<br />
	<div id="tcontent1"
		style="background-color: #E8E3E3; border-style: inset; border-color: #B24700; border-width: thin">
	<br />
	<table width="100%">
		<tr>
			<td width="25%"><b><hdiits:caption captionid="PR.MONTH"
				bundle="${ReportingDDO}" /></b></td>

			<td width="25%"><select name="selmonth" id="selmonth"
				onchange="">
				<option value="-1"><fmt:message key="EIS.Select"
					bundle="${ReportingDDO}" /></option>
				<c:forEach var="MonthlookUpList" items="${MonthlookUpList}">

					<option value="<c:out value="${MonthlookUpList.lookupShortName}"/>">
					<c:out value="${MonthlookUpList.lookupDesc}" /></option>
				</c:forEach>

			</select></td>

			<td width="25%" colspan="1"><b><hdiits:caption
				captionid="PR.YEAR" bundle="${ReportingDDO}" /></b></td>
			<td width="25%"><select name="selyear" id="selyear" onchange="">
				<option value="-1"><fmt:message key="EIS.Select"
					bundle="${ReportingDDO}" /></option>

				<c:forEach var="YearLookUpList" items="${YearLookUpList}">

					<option value="<c:out value="${YearLookUpList.lookupName}"/>">
					<c:out value="${YearLookUpList.lookupDesc}" /></option>
				</c:forEach>

			</select></td>


		</tr>
		<tr>
			<td width="25%"><b>Scheme Code</b></td>
			<td width="25%"><select name="schemecode" id="schemecode"
				onchange="getSubSchemeDetails();">
				<option value="-1">--Select----</option>

				<c:forEach var="schemeList" items="${schemeList}">

					<option value="<c:out value="${schemeList[1]}"/>"
						title="${schemeList[2]}"><c:out value="(${schemeList[1]})${schemeList[2]}" /></option>
				</c:forEach>

			</select></td>
			 <td width="100%" align="left"><fmt:message key="CMN.SUBSCHEMENAME"
				bundle="${dcpsLables}"></fmt:message></td>
		<td width="100%" align="left">
		
		<select name="cmbSubSchemeName"
				id="cmbSubSchemeName" size="1px"  >
				<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" /></option>
				
		</select></td>
			
			
			
			

			<td width="25%"></td>
			<td width="25%"></td>
		</tr>

		<tr>
			<td width="25%"><!--//commented by vaibhav tyagi
					
					<b>DDO Code</b>
	    		--> <%//added by vaibhav tyagi:start%> <b>Institution Details</b> <%//added by vaibhav tyagi:end%>
			</td>
			<td width="100%">
			<%//added by vaibhav tyagi:start%> 
		
			 <c:set var="srno" value="1" > </c:set> 
			<display:table name="${ddoDtls}"
				requestURI="" pagesize="1000" sort="list" id="ddo" export="false" 
				style="width:100%">
				
				<display:column class="tablecelltext" title="<input name='chkSelect'  type='checkbox' onclick='checkUncheckAll(this)'/>"
					headerClass="datatableheader"
					style="text-align: center;font-size:12px;">
					<div id="${ddo[0]}"><hdiits:checkbox id="ddoList"
						name="ddoList" value="${ddo[0]}" /></div>
				</display:column>
				
				
				
				<display:column class="tablecelltext" title="DDO Code"
					headerClass="datatableheader"
					style="text-align: center;font-size:12px;">
			   ${ddo[0]}
		  </display:column>
				<display:column class="tablecelltext" title="<b>DDO Office Name</b>"
					headerClass="datatableheader"
					style="text-align: center;font-size:12px;">
			   ${ddo[1]}
		  </display:column>
	
		  <c:set var="srno" value="${srno+1}" > </c:set>
		
			</display:table>
			 <!--<table width="400px">
				<tr>
					<td><b>Select</b></td>
					<td><b>DDO Code</b></td>
					<td><b>DDO Office Name</b></td>
				<tr>
					<c:forEach var="ddo" items="${ddoDtls}">
						<tr> 
							<td><hdiits:checkbox id="ddoList" name="ddoList"
								value="${ddo[0]}" /></td>
							<td><c:out value="${ddo[0]}" /></td>
							<td><c:out value="${ddo[1]}" /></td>
						<tr>
					</c:forEach>
			</table>
			--> <%//added by vaibhav tyagi:end%> <%//commented by vaibhav tyagi
					/*
						<c:forEach var="ddo"  items="${ddoDtls}">
								<hdiits:checkbox id="ddoList" name="ddoList" value="${ddo[0]}"/>&nbsp;<c:out value="${ddo[0]} [${ddo[1]}] " />&nbsp;
								
					
					 */%>
			</td>
			<%//commented by vaibhav tyagi
					/*
							<td width="25%"></td>
							<td width="25%"></td>
					 */%>
		</tr>
	</table>
	<br>
	<br>

	<table align="center">
		<tr>
			<td align="center"><hdiits:button name="btnconsolidate"
				id="btnconsolidate" type="button" value="Search"
				onclick="ConsolidateSummaryPage();" /></td>
		</tr>
	</table><!--
	<table align="center" width="100%">
											<tr>
												<td style="font-style:italic;color: red ;padding-left: 1%" colspan="5" >
*Note : As per instruction given by department you can not consolidate bill for'Vocational Admin Office'.</td>
											</tr>	
										</table>
	--></div>
	<br>

	<hdiits:validate locale="${locale}" controlNames="" />


</hdiits:form>





<%
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<script>
	initializetabcontent("maintab");

	function ConsolidateSummaryPage() {

		var len = document.frmReportingDDO.ddoList.length;
		var isChecked = false;
		for ( var i = 0; i < len; i++) {

			if (document.frmReportingDDO.ddoList[i].checked) {
				isChecked = true;
				break;

			}

		}
		if (document.frmReportingDDO.ddoList.checked)
			isChecked = true;
		if (!isChecked) {
			alert("Please select atleast one DDO Code");
			return false;
		}

		if (document.frmReportingDDO.selmonth.value == '-1') {
			alert('Please select Month.');
			document.frmReportingDDO.selmonth.focus();
			return false;
		}
		if (document.frmReportingDDO.selyear.value == '-1') {
			alert('Please select Year.');
			document.frmReportingDDO.selyear.focus();
			return false;
		}

		if (document.frmReportingDDO.schemecode.value == '-1') {
			alert('Please select Scheme.');
			document.frmReportingDDO.schemecode.focus();
			return false;
		}

		if (document.frmReportingDDO.ddoList.checked == false) {
			alert('Please select DDO CODE.');
			document.frmReportingDDO.ddoList.focus();
			return false;
		}

		var urlstyle = "ifms.htm?actionFlag=ShowSummaryPageDtls";
		document.frmReportingDDO.action = urlstyle;
		document.frmReportingDDO.submit();
		showProgressbar("Please wait<br>Your Request is in progress ...");
	}

	function CloseScreen() {
		var urlstyle = "ifms.htm?actionFlag=validateLogin";
		document.forms[0].action = urlstyle;
		document.forms[0].submit();
		
	}

	function popUpBillDetails() {

	}


	
</script>
