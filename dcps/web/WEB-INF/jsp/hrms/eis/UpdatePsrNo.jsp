
<%
try {
%>

<%@ include file="../../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="java.util.List"%>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/Address.js"/>">
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<fmt:setBundle basename="resources.eis.eis_common_lables"	var="adminUpdatePsrLabel" scope="request" />

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="dataList" value="${resValue.recordList}" > </c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>
<%
List dataList = (List) pageContext.getAttribute("dataList");
int size = dataList.size();
pageContext.setAttribute("listSize",size);
%>

<script type="text/javascript">
	if('${msg}'!= null && '${msg}'!='')
		alert("Record Updation " + '${msg}');
	
	function submitFormAuto()
	{
		if(chkValue())
		{
			var empId = document.getElementById("Employee_ID_viewSearch").value;
			document.UpdatePsrNo.action="./hdiits.htm?actionFlag=showPsrBillNumber&empId="+empId;
			document.UpdatePsrNo.submit();
		}		
		return true;
	}
	function chkValue()
	{
		var empId = document.getElementById("Employee_ID_viewSearch").value;
		if(empId=="")
		{			
			alert("Please search the employee first");
			return false;
		}
		else
		{			
			return true;
		}	
	}
	
	function validateForm1()
	{
		 var listsize = '${listSize}';
		 if(listsize == 0)
	 	 	alert("No Records to Save ! ! !");
		 document.UpdatePsrNo.action = "./hdiits.htm?actionFlag=UpdatePsrNumber&listSize=${listSize}";
		 document.UpdatePsrNo.submit();
	}
	
	function chkPsrNumber(num, i)
	{
		  var currentpsr = document.getElementById("psr" + i).value;
		  var oldpsr = document.getElementById("oldval" + i).value;
		  if(document.getElementById("ValidationChkBox").checked)
		  {
			  if(!(currentpsr)== '')
			  {
				  xmlHttp=GetXmlHttpObject();
				  if (xmlHttp==null)
				  {
					  alert ("Your browser does not support AJAX!");
					  return;
				  } 
				  var url; 
				  var uri='';
				  if(currentpsr != oldpsr)
				  {
					  url= uri+'&NewPsrNumber='+ currentpsr;
					  uri='./hrms.htm?actionFlag=AjaxPsrNumber' ;
					  url=uri+url; 
					  xmlHttp.onreadystatechange= function() 
						{
							if (xmlHttp.readyState==complete_state)
							 { 						
									var XMLDoc=xmlHttp.responseXML.documentElement;			
								    var namesEntries = XMLDoc.getElementsByTagName('Psr-Number');
								    if(namesEntries.length != 0 && namesEntries[0].childNodes[0].text!='0')
								    {                    
									      alert('Psr Number already exists.');
									      document.getElementById("psr" + i).style.background="#ccccff";
									      document.getElementById("psr" + i).value=oldpsr;
									      document.getElementById("psr" + i).focus();
								   }
							  }
						}
					  
					  xmlHttp.open("POST",encodeURI(url),true);
					  xmlHttp.send(null);
				  }
			  }
		  if((currentpsr)== '')
		  {
			  alert("PSR Field Can Not Be Set Blank !!!");
			  document.getElementById("psr" + i).style.background="#ccccff";
			  document.getElementById("psr" + i).value=oldpsr;
			  document.getElementById("psr" + i).focus();
		  }
		}			  
	 }
	function setStyle(x)
	{
	document.getElementById(x).style.background="#ccccff";
	}
	function searchvalues()
	{
		var value = document.getElementById("value").value;
		var option_selected = document.getElementById("option_selected").value;
		if(option_selected == "PsrNo")
		{
			if(check4number(value))
			{
				document.UpdatePsrNo.action='hdiits.htm?actionFlag=showPsrBillNumber&PsrNo='+value;
				document.UpdatePsrNo.submit();
			}	
		}
		if(option_selected == "BillNo")
		{
			if(check4number(value))
			{
				document.UpdatePsrNo.action='hdiits.htm?actionFlag=showPsrBillNumber&BillNo='+value;
				document.UpdatePsrNo.submit();
			}	
		}	
		if(option_selected == "Dsgn")
		{
			document.UpdatePsrNo.action='hdiits.htm?actionFlag=showPsrBillNumber&Dsgn='+value;
			document.UpdatePsrNo.submit();
		}
		if(option_selected == "")
			alert("Please Select Criteria For Search");
	}
	function check4number(n)
	{	
		var flag = true;
		if(isNaN(n) == true)
	    {
			flag = false;
			alert ("This Value Is Not A Number. \nPlease Enter A Valid Field Value.");
	    }
	    if(n == "")
	    {
	    	flag = false;
			alert ("The Field is blank ");
		}
	    return flag;
	}
	function EnterkeyPressed(e,form)
	{	
	  
	  var whichCode = (window.Event) ? e.which : e.keyCode;
		if ((e.keyCode==13))
		{
			searchvalues();
		}
	}   
		
</script>

<hdiits:form name="UpdatePsrNo" validate="true" method="POST"
	action="javascript:validateForm1()">

	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b> <hdiits:caption
			captionid="admin.UpdatePsr" bundle="${adminUpdatePsrLabel}"></hdiits:caption>
		</b></a></li>
	</ul>
	</div>
<table  width="85%" align="center" name="searchTable" id="searchTable">
		<tr>
			<td >
				<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
						<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
						<jsp:param name="SearchEmployee" value="viewSearch"/>
						<jsp:param name="formName" value="UpdatePsrNo"/>
						<jsp:param name="functionName" value="submitFormAuto"/>
					</jsp:include>
			</td>
		</tr>
	</table><br><br>

	<table width="85%" border="3" bordercolor=#ccccff bgcolor="white" align="center" name="searchTable" id="searchTable">
		<TR><td align = "center"> Search By </td>
 			<td align = "center"><select type = "menu" name = "search_criteria" caption="search_criteria"
				captionid="search_criteria" id = "option_selected">
					<option value = "BillNo">Bill Number</option>
					<option value = "PsrNo">Psr Number</option>
					<option value = "Dsgn">Designation</option>
					</select>
			</td>
			<td align="center"><input type="text" name="srchText" captionid="search_criteria" onfocus="setStyle(this.id)" id="value" onKeyPress= "return EnterkeyPressed(event,document.forms[0])"></td>
			<td width="34%" align="left"><hdiits:button type="button" captionid="Search_Employee"
				value="&nbsp;&nbsp;Search&nbsp;&nbsp;" name="search" onclick="searchvalues()" /></td>
		</tr>
	</table>
	<br><br>
	
<c:if test="${listSize ne 0}">
<hdiits:checkbox name='ValidationChkBox' id='ValidationChkBox' value = "" /> <b>Validation Require ?</b>
<br><br>
</c:if>	
<c:if test="${listSize gt 0}">
<div>
	<table width = 100%  border="2"   align = "center">
		<TR>
			
			<TD bordercolor =  "#98AFC7"><b>Employee name(Designation)</b></td>
			<TD bordercolor =  "#98AFC7"><b> PSR Number</b></td>
			<TD bordercolor =  "#98AFC7"><b>Employee name(Designation)</b></td>
			<TD bordercolor =  "#98AFC7"><b> PSR Number</b></td>
			<TD bordercolor =  "#98AFC7"><b>Employee name(Designation)</b></td>
			<TD bordercolor =  "#98AFC7"><b> PSR Number</b></td>
					
		</TR>
		<TR>
			<table width = 100%" border="1"  align = "center">
				<c:set value="0" var="k"></c:set>
				<td></td><td></td><td></td><td></td><td></td>
				<c:forEach var="row" items="${dataList}">
				<!--<c:set var="k" value="${k+1}"></c:set>-->
				<c:if test="${k%3  == 1}">
				<TR>
				</c:if>
					<td width = "23%" bordercolor =  "#98AFC7" >
						<hdiits:hidden name = "oldpsr${k}" default ="${row.psrNo}" id = "Oldval${k}"/>
						${k}. <b>  ${row.empFullName}</b>(${row.dsgnName })	
					</td>
					<td width = "10%" bordercolor =  "#98AFC7" >
						<hdiits:number default ="${row.psrNo}" id = "psr${k}" name="txtpsr${k}" captionid="txtpsr"  caption ="txtpsr" maxlength="10" size="10" 
								onblur="chkPsrNumber(this.id, ${k})"/>
					    <hdiits:hidden name = "id${k}" default = "${row.psrId}"/>
					</td>
				<c:if test="${k%3  == 0}">		
					</TR>
				</c:if>	
				<c:if test="${k eq listSize}">		
					</TR>
				</c:if>	
				</c:forEach>
			</table>
		 </TR>
	</table>
</div>
</c:if>	
<c:if test="${listSize eq 0}">
<center> No Records To Display ! ! !</center>
</c:if>	
<br><br><br>
<tr><td>
<fmt:setBundle basename="resources.payroll.payrollLables_en_US" var="Lables" scope="request"/>
			<jsp:include page="../../core/PayTabnavigation.jsp" />
			<c:if test="${listSize != 0}">
				 <hdiits:hidden default="showPsrBillNumber" name="givenurl"/>
			</c:if>	 
			<c:if test="${listSize == 0}">
				 <hdiits:hidden default="getHomePage" name="givenurl"/>
			</c:if>
			</td></tr>
			
<hdiits:validate locale="${locale}" controlNames="" />
<script type="text/javascript">
	initializetabcontent("maintab")
</script>
	<script type="text/javascript">

document.frmAdminCrtPost.srchText.focus();
	
</script>
</hdiits:form>

<%
}catch(Exception e) {e.printStackTrace();}
%>
	