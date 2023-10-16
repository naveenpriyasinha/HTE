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
function popupDtls(field,srno){
	
	var uri = "ifms.htm?actionFlag=popUpDtls&ddoCode="+field;
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
	
	var divId=srno+field;
	var XMLDoc = myAjax.responseXML.documentElement;
	var officeNameObj = XMLDoc.getElementsByTagName('officeName');
	var officeNameval = officeNameObj[0].firstChild.nodeValue;
	var dsgDtlObj = XMLDoc.getElementsByTagName('dsgDtl');
	var dsgDtlval = dsgDtlObj[0].firstChild.nodeValue;
	document.getElementById(divId).innerHTML='<a href="#" onclick="hideDtls('+field+','+srno+')">'+field+'<br>'+dsgDtlval+','+officeNameval+'</a>';
	
		
}
function hideDtls(field,srno){

	var divId=srno.toString()+field.toString();
	
	document.getElementById(divId).innerHTML='<a href="#" onclick="popupDtls('+field+','+srno+')">'+field+'</a>';
}



</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="lvl2DDOlist" value="${resultValue.lvl2DDO}" > </c:set>
<fmt:setBundle basename="resources.eis.zp.zpDDOOffice.ZpDDOOfficeLabels_en_US" var="ZpDDOOfficeLabels" scope="request"/>
</head>
<body>
<form method="POST" name="lvl2DDOList" action="">
	
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="ZP.lvl2DDO" bundle="${ZpDDOOfficeLabels}"/></b></a></li>
		</ul>
	</div>
	
	<c:set var="srno" value="1" > </c:set>
	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >	  
	 <div align="center"> <h1>
	   <c:out value="${resultValue.msg}" />  </h1> </div> <br>&nbsp;
		  <a href= "./hrms.htm?actionFlag=loadReportingDdoOffice"> Defining Level2 DDO for offices having no DDO Code </a>   
		  <br>
		  <display:table name="${lvl2DDOlist}" requestURI="" pagesize="10" sort="list" id="row" export="false" style="width:100%">
			  <display:column class="tablecelltext" title="DDO Code"  headerClass="datatableheader" style="text-align: center;font-size:12px;" >
			<div id="${srno}${row[0]}"><a href="#" onclick="popupDtls('${row[0]}','${srno}');"> ${row[0]}</a></div>
			    </display:column>	  
			  <display:column class="tablecelltext" title="Office Code" headerClass="datatableheader" style="text-align: center;font-size:12px;">
			   ${row[1]}
		  </display:column>	
			  
			  
		  	  <display:setProperty name="export.pdf" value="false" />
		  	   <c:set var="srno" value="${srno+1}" > </c:set>
  		  </display:table>
		 <br>&nbsp;
	  	 <%--<a href= "./hrms.htm?actionFlag=loadReportingDdoOffice"> Defining Level2 DDO for offices having no DDO Code </a>--%>  
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