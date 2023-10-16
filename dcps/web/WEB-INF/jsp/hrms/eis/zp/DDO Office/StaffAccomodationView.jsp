
<html>
<head>
<%
try{
%>

<%@ include file="../../../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:setBundle basename="resources.eis.eisLables_en_US" var="DistrictOfficeLables" scope="request" />
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript" >

function setDDOdtls(myAjax,field,srno){
	
	var divId=srno.toString()+field.toString();
	var XMLDoc = myAjax.responseXML.documentElement;
	var officeNameObj = XMLDoc.getElementsByTagName('officeName');
	var officeNameval = officeNameObj[0].firstChild.nodeValue;
	var dsgDtlObj = XMLDoc.getElementsByTagName('dsgDtl');
	var dsgDtlval = dsgDtlObj[0].firstChild.nodeValue;
	document.getElementById(divId).innerHTML='<a href="#" onclick="hideDtls('+field+','+srno+')">'+field+'<br>'+dsgDtlval+','+officeNameval+'</a>';
	
		
}
function hideDtls(field,srno){

	var divId=srno.toString()+field.toString();
	alert(document.getElementById(divId).innerHTML);
	document.getElementById(divId).innerHTML='<a href="#" onclick="popupDtls('+field+','+srno+');">'+field+'</a>';
	alert(divId);
}
function test(field,srno)
{
	var testvalue1=srno.toString()+field.toString();
	alert(document.getElementById(testvalue1));
	
}	
</script>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="actionList" value="${resultValue.actionList}" > </c:set>
<c:set var="quaterApprovalLst" value="${resultValue.quaterApprovalLst}" > </c:set>

<title>Insert title here</title>
</head>

<body>
<form method="POST" name="StaffAccomodationView" >
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b>Staff Approval Status</b></a></li>
		</ul>
	</div>
	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >	  
	  <br>
		  <c:set var="srno" value="1" > </c:set>
		  <display:table name="${quaterApprovalLst}" requestURI="" pagesize="${pageSize}" sort="list" id="row" export="false" style="width:100%" >
			  <display:column class="tablecelltext" title="DDO AST ID"  headerClass="datatableheader" style="text-align: center;font-size:12px;" ><div id="${srno}${row[3]}"><a href="#">${row[3]}</a></div></display:column> 
			  <display:column class="tablecelltext" title="Employee ID" headerClass="datatableheader" style="text-align: center;font-size:12px;"><div id="${srno}${row[2]}"><a href="#" onclick="test('${row[2]}','${srno}')">${row[2]}</a></div></display:column>	
			   <display:column class="tablecelltext" title="Status" headerClass="datatableheader" style="text-align: center;font-size:12px;"><div id="${srno}${row[4]}"><a href="#">${row[4]}</a></div></display:column>	
			  	  		  	  <display:setProperty name="export.pdf" value="false" />
		  	   <c:set var="srno" value="${srno+1}" > </c:set>
  		  </display:table>
		 <br>&nbsp;
	  	
	  	 <br/><br/>
	
	<script type="text/javascript">
	
		initializetabcontent("maintab")
	</script>
</div>
<%
}
  	  catch(Exception e)
  	  {
  		  
  		  e.printStackTrace();
  	  }
%>
</body>
</body>
</html>