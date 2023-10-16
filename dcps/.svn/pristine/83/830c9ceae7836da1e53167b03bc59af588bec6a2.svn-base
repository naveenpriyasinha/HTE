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


function approveDtls(field){
	var hierarchy=document.getElementById("hierarchy").value;
	var divId=field.toString();
	//alert(document.getElementById(divId).innerHTML);
	//document.getElementById(divId).innerHTML='<a href="#" onclick="popupDtls('+field+','+srno+');">'+field+'</a>';
	var url="./hrms.htm?actionFlag=updateApproveStatus&ddocode="+divId+"&hierarchy="+hierarchy;
	document.DDOOfficeView.action = url;
	document.DDOOfficeView.submit();
	showProgressbar("Please wait<br>While Your Request is in Progress ...");
	//alert(url);
}

function rejectDtls(field){
	var hierarchy=document.getElementById("hierarchy").value;
	var divId=field.toString();
	//alert(document.getElementById(divId).innerHTML);
	//document.getElementById(divId).innerHTML='<a href="#" onclick="popupDtls('+field+','+srno+');">'+field+'</a>';
	var url="./hrms.htm?actionFlag=updateRejectStatus&ddocode="+divId+"&hierarchy="+hierarchy;
	document.DDOOfficeView.action = url;
	document.DDOOfficeView.submit();
	showProgressbar("Please wait<br>While Your Request is in Progress ...");
//	alert(url);
}

function viewReports()
{
	var url="ifms.htm?viewName=DDOOfficeStatusView";
	document.DDOOfficeView.action = url;
	document.DDOOfficeView.submit();
	showProgressbar("Please wait<br>While Your Request is in Progress ...");
}
</script>
<script type="text/javascript">
function approveRejectDtls(DDOCode)
{
	var hierarchy=document.getElementById("hierarchy").value;
	//alert('inside approveRejectDtls'+DDOCode+' hierarchy'+hierarchy);
	var url="./hrms.htm?actionFlag=approveRejectDtls&ddocode="+DDOCode+"&hierarchy=2";
	//alert(url);
	document.DDOOfficeView.action = url;
	document.DDOOfficeView.submit();
}

</script>

<script type="text/javascript" >
function popupDtls(field,srno){
	
	var uri = "ifms.htm?actionFlag=popUpDtls&ddoCode="+field;
//	alert(uri);
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: true,
		     
		        onSuccess: function (myAjax) {
       
		        	setDDOdtls(myAjax,field,srno);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}
function setDDOdtls(myAjax,field,srno){
	//alert("hiii i m roshan");
	var divId=srno.toString()+field.toString();
	var XMLDoc = myAjax.responseXML.documentElement;
	var officeNameObj = XMLDoc.getElementsByTagName('officeName');
	var officeNameval = officeNameObj[0].firstChild.nodeValue;
	//var dsgDtlObj = XMLDoc.getElementsByTagName('dsgDtl');
	//var dsgDtlval = dsgDtlObj[0].firstChild.nodeValue;
	//alert("hiii i m roshan"+dsgDtlObj);
	//alert("hiii i m roshan"+dsgDtlval);
	document.getElementById(divId).innerHTML='<a href="#" onclick="hideDtls('+field+','+srno+')">'+officeNameval+'</a>';
	
		
}
function hideDtls(field,srno){

	var divId=srno.toString()+field.toString();
	//alert(document.getElementById(divId).innerHTML);
	document.getElementById(divId).innerHTML='<a href="#" onclick="popupDtls('+field+','+srno+');">'+field+'</a>';
	//alert(divId);
}

</script>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="actionList" value="${resultValue.actionList}" > </c:set>
<c:set var="zpDDOOfficelst" value="${resultValue.zpDDOOfficelst}" > </c:set>
<c:set var="hierarchy" value="${resultValue.hierarchy}" > </c:set>
</head>
<body>
<form method="POST" name="DDOOfficeView">
<input type="hidden" value="${hierarchy}" name ="hierarchy" id="hierarchy"/>
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b>DDO OFFICE</b></a></li>
		</ul>
	</div>
	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >	  
	  
		  <br>
		  <c:set var="srno" value="1" > </c:set>
		  <display:table name="${zpDDOOfficelst}" requestURI="" pagesize="${pageSize}" sort="list" id="row" export="false" style="width:100%" >
			  <display:column class="tablecelltext" title="DDO Code"  headerClass="datatableheader" style="text-align: center;font-size:12px;" ><div id="${srno}${row[5]}"><a href="#" onclick="popupDtls('${row[5]}','${srno}');">${row[5]}</a></div></display:column> 
			  <display:column class="tablecelltext" title="Level2 DDO Code" headerClass="datatableheader" style="text-align: center;font-size:12px;"><div id="${srno}${row[6]}"><a href="#" onclick="popupDtls('${row[6]}','${srno}');">${row[6]}</a></div></display:column>	

			  <display:column class="tablecelltext" title="Hierarchy Level" headerClass="datatableheader" style="text-align: center;font-size:12px;">${row[16]}</display:column>
			  <%--<display:column class="tablecelltext" title="Status" headerClass="datatableheader" style="text-align: center;font-size:12px;"><div id="approve"><a href="#" onclick="approveDtls('${row[5]}');">Approve</a></div>/<div id="reject"><a href="#" onclick="rejectDtls('${row[5]}');">Reject</a></div></display:column>--%>
			  <%-- edited by samadhan --%>
			  <display:column class="tablecelltext" title="Status" headerClass="datatableheader" style="text-align: center;font-size:12px;"><div id="approve"><a href="#" onclick="approveRejectDtls('${row[5]}');">Click Here to Approve or Reject</a></div></display:column>
		  	  <display:setProperty name="export.pdf" value="false" />
		  	   <c:set var="srno" value="${srno+1}" > </c:set>
  		  </display:table>
		 <br>&nbsp;
	  	 <hdiits:button name="ViewReports" type="button" captionid="EIS.VIEWREPORTS" bundle="${commonLables}" onclick="viewReports()"></hdiits:button>
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
</html>