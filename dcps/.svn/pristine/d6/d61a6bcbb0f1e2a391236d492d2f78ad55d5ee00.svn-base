<html>
<head>
<%
try{
%>

<%@ include file="../../../../core/include.jsp" %>
<%--added by vaibhav tyagi: start--%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%--added by vaibhav tyagi: end--%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
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
	//alert(document.getElementById(divId).innerHTML);
	document.getElementById(divId).innerHTML='<a href="#" onclick="popupDtls('+field+','+srno+');">'+field+'</a>';
	//alert(divId);
}

</script>

<%--added by vaibhav tyagi: start--%>
<script type="text/javascript">
function filterSchool(){
	var district= document.getElementById("cmbDistrict").value;
	var taluka= document.getElementById("cmbTaluka").value;
	var adminType= document.getElementById("cmbAdminType").value;
	var url;

		url="ifms.htm?actionFlag=loadDdoOfficeData&taluka="+taluka+"&district="+district+"&adminType="+adminType;
		document.DDOOfficeView.action= url;
		document.DDOOfficeView.submit();
		showProgressbar("Please wait<br>While Your Request is in Progress ...");
}
</script>
<style>
table#row * {
    font-size: 11px;
    padding: 0;
}
</style>
<%--added by vaibhav tyagi: end--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="actionList" value="${resultValue.actionList}" > </c:set>
<c:set var="zpDDOOfficelst" value="${resultValue.zpDDOOfficelst}" > </c:set>

<%--added by vaibhav tyagi:start --%>
<c:set var="districtList" value="${resultValue.districtList}"/>
<c:set var="adminTypeList" value="${resultValue.adminTypeList}"/>
<c:set var="talukaList" value="${resultValue.talukaList}"/>
<c:set var="districtSelected" value="${resultValue.districtSelected}"/>
<c:set var="adminTypeSelected" value="${resultValue.adminTypeSelected}"/>
<c:set var="talukaSelected" value="${resultValue.talukaSelected}"/>
<%--added by vaibhav tyagi:end --%>


<%--START added by samadhan--%>
<c:set var="lStrDdoCode" value="${resultValue.lStrDdoCode}"/>
<c:set var="uniqeInstituteId" value="${resultValue.uniqeInstituteId}"/>

<%--END added by samadhan--%>
</head>
<body>
<input type="hidden" id="hdnDDOCode" name="hdnDDOCode" value="${lStrDdoCode}">
<input type="hidden" id="hdnUniqeInstituteId" name="hdnUniqeInstituteId" value="${uniqeInstituteId}">
<form method="POST" name="DDOOfficeView" action="./hrms.htm?actionFlag=loadDdoOfficeData">
		<%--Added by vaibhav : start --%>
	<fieldset class="tabstyle"><legend> <b>Filter Institute</b> </legend>
<table align="center">
<tr>
<td><c:out value="Admin Type"></c:out></td>
<td><select name="cmbAdminType"
			id="cmbAdminType" style="width: 85%,display: inline;">
			<option title="Select All" value="-1"><c:out value="Select"></c:out></option>

			<c:forEach var="adminTypeListForSchools" items="${resultValue.adminTypeList}">
			<c:choose> 
			<c:when test="${adminTypeSelected==adminTypeListForSchools[0]}">
							<option value="${adminTypeListForSchools[0]}" title="${adminTypeListForSchools[1]}" selected="selected">
						<c:out value="${adminTypeListForSchools[1]}"/></option>
			</c:when>
			<c:otherwise>
						<option value="<c:out value="${adminTypeListForSchools[0]}"/>" title="${adminTypeListForSchools[1]}">
						<c:out value="${adminTypeListForSchools[1]}"/></option>
						</c:otherwise>
						</c:choose>
					</c:forEach>	
		</select></td>
<td><c:out value="Distrct"></c:out></td>

<td><select name="cmbDistrict"
			id="cmbDistrict" style="width: 85%,display: inline;">
			<option title="Select All" value="-1"><c:out value="Select"></c:out></option>

			<c:forEach var="distList" items="${resultValue.districtList}">
			<c:choose> 
			<c:when test="${districtSelected==distList[0]}">
							<option value="${distList[0]}" title="${distList[1]}" selected="selected">
						<c:out value="${distList[1]}"/></option>
			</c:when>
			<c:otherwise>
						<option value="<c:out value="${distList[0]}"/>" title="${distList[1]}">
						<c:out value="${distList[1]}"/></option>
						</c:otherwise>
						</c:choose>
					</c:forEach>
					
					
		</select></td>

<td><c:out value="Taluka"></c:out></td>

<td><select name="cmbTaluka"
			id="cmbTaluka" style="width: 85%,display: inline;">
			<option title="Select All" value="-1"><c:out value="Select"></c:out></option>

			<c:forEach var="talukaListForDist" items="${resultValue.talukaList}">
			<c:choose> 
			<c:when test="${talukaSelected==talukaListForDist[0]}">
							<option value="${talukaListForDist[0]}" title="${talukaListForDist[1]}" selected="selected">
						<c:out value="${talukaListForDist[1]}"/></option>
			</c:when>
			<c:otherwise>
						<option value="<c:out value="${talukaListForDist[0]}"/>" title="${talukaListForDist[1]}">
						<c:out value="${talukaListForDist[1]}"/></option>
						</c:otherwise>
						</c:choose>
					</c:forEach>	
		</select></td>



		</tr>
	<tr>	
<td colspan="6" align="center"><input id="btnFilter" class="buttontag" type="button" size="5" maxlength="5"
		value="Filter" onclick="filterSchool();"
		name="btnFilter" style="width: 120px;" /></td>
</tr>
</table>
</fieldset>
<%--Added by vaibhav : end --%>
	
	
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b>DDO OFFICE</b></a></li>
		</ul>
	</div>
	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >	  
	 <div align="center"> <h1>
	 
	   <c:out value="${resultValue.msg}" />  </h1> </div> <br>&nbsp;
		  <a href= "./hrms.htm?actionFlag=loadDDOAddnewScreen">Add new Entry</a>  
		  <br>
		  <c:set var="srno" value="1" > </c:set>
		  <display:table name="${zpDDOOfficelst}" requestURI="" pagesize="${pageSize}" sort="list" id="row" export="false" style="width:100%" >
			  <%--commented by vaibhav tyagi --%>
			  <%--<display:column class="tablecelltext" title="DDO Code"  headerClass="datatableheader" style="text-align: center;font-size:12px;" ><div id="${srno}${row[5]}"><a href="#" onclick="popupDtls('${row[5]}','${srno}');">${row[5]}</a></div></display:column> 
			  <display:column class="tablecelltext" title="Level2 DDO Code" headerClass="datatableheader" style="text-align: center;font-size:12px;"><div id="${srno}${row[6]}"><a href="#" onclick="popupDtls('${row[6]}','${srno}');">${row[6]}</a></div></display:column>	
			  <display:column class="tablecelltext" title="Level3 DDO Code" headerClass="datatableheader" style="text-align: center;font-size:12px;"><div id="${srno}${row[7]}"><a href="#" onclick="popupDtls('${row[7]}','${srno}');">${row[7]}</a></div></display:column>
			  <display:column class="tablecelltext" title="Level4 DDO Code" headerClass="datatableheader" style="text-align: center;font-size:12px;"><div id="${srno}${row[8]}"><a href="#" onclick="popupDtls('${row[8]}','${srno}');">${row[8]}</a></div></display:column>
			  <display:column class="tablecelltext" title="Hierarchy Level" headerClass="datatableheader" style="text-align: center;font-size:12px;">${row[16]}</display:column>
			  <display:column class="tablecelltext" title="Status" headerClass="datatableheader" style="text-align: center;font-size:12px;">
			 <c:if test="${row[17]==0}">
			  <label>Pending</label>
			  </c:if>
			  <c:if test="${row[17]==1}">
			  <label>Approved</label>
			  </c:if>
			  <c:if test="${row[17]==-1}">
			  <label>Rejected</label>
			  </c:if>
			  </display:column>
			  --%>
			  <%--added by vaibhav tyagi :start--%>
			  <display:column class="tablecelltext" title="DDO Code"  headerClass="datatableheader" style="text-align: center;font-size:12px;" ><div id="${srno}${row[0]}"><a href="#" onclick="popupDtls('${row[0]}','${srno}');">${row[0]}</a></div></display:column> 
			  <display:column class="tablecelltext" title="Level2 DDO Code" headerClass="datatableheader" style="text-align: center;font-size:12px;"><div id="${srno}${row[1]}"><a href="#" onclick="popupDtls('${row[1]}','${srno}');">${row[1]}</a></div></display:column>	
			  <display:column class="tablecelltext" title="Level3 DDO Code" headerClass="datatableheader" style="text-align: center;font-size:12px;">
			   <c:choose>
			  <c:when test="${row[2] !=null}">
			  <div id="${srno}${row[2]}"><a href="#" onclick="popupDtls('${row[2]}','${srno}');">${row[2]}</a></div>
			 
			  </c:when>
			  <c:otherwise>
			   <label>NA</label>
			  </c:otherwise>
			  </c:choose>
			  
			  
			  </display:column>
			  <display:column class="tablecelltext" title="Level4 DDO Code" headerClass="datatableheader" style="text-align: center;font-size:12px;">
			  <c:choose>
			  <c:when test="${row[3] !=null}">
			  <div id="${srno}${row[3]}"><a href="#" onclick="popupDtls('${row[3]}','${srno}');">${row[3]}</a></div>
			 
			  </c:when>
			  <c:otherwise>
			   <label>NA</label>
			  </c:otherwise>
			  </c:choose>
			  </display:column>
			  
			  <display:column class="tablecelltext" title="Hierarchy Level" headerClass="datatableheader" style="text-align: center;font-size:12px;">${row[4]}</display:column>
			  <display:column class="tablecelltext" title="Status" headerClass="datatableheader" style="text-align: center;font-size:12px;">
			  <%--added by vaibhav tyagi :end--%>
			  <c:if test="${row[5]==0}">
			  <label>Pending</label>
			  </c:if>
			  <c:if test="${row[5]==1}">
			  <label>Approved</label>
			  </c:if>
			  <c:if test="${row[5]==-1}">
			  <label>Rejected</label>
			  </c:if>
			  </display:column>
			  
		  	  <display:setProperty name="export.pdf" value="false" />
		  	   <c:set var="srno" value="${srno+1}" > </c:set>
  		  </display:table>
		 <br>&nbsp;
	  	 <!-- <a href= "./hrms.htm?actionFlag=loadDDOAddnewScreen">Add new Entry</a>  --> 
	  	 <br/><br/>
	
	<script type="text/javascript">
	
		initializetabcontent("maintab")
	</script>
<%--START added by samadhan--%>
	<script type="text/javascript">
var DDOCode = document.getElementById("hdnDDOCode").value;
var instituteId = document.getElementById("hdnUniqeInstituteId").value;
if(DDOCode != '')
{
	alert('For DDO Code: '+DDOCode+', system generated unique institute Id: '+instituteId+'. Please note the details for future use.');
}
</script>
<%--END added by samadhan--%>
</div>
<%
}
  	  catch(Exception e)
  	  {
  		  
  		  e.printStackTrace();
  	  }
%>
<%--added by vaibhav tyagi: start--%>
<ajax:select source="cmbDistrict" 
		target="cmbTaluka" 
		baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getTalukaFromDistrictId"
		eventType="change" 
		parameters="districtId={cmbDistrict}">
</ajax:select>
<%--added by vaibhav tyagi: end--%>
</body>
</html>