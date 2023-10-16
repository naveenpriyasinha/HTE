<%try{%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<%@ page import="com.tcs.sgv.common.utils.StringUtility" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<script type="text/javascript"	src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/dppf/calendarDppf.js"></script>
<script type="text/javascript" src="script/payfixation/common.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript"	src="script/common/IFMSCommonFunctions.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript"	src="script/dcps/dcpsTreasury.js"></script>
<script type="text/javascript" src="script/dcps/NewRegistrationForm.js"></script>

<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLabels" scope="request"/>
<fmt:setBundle basename="resources.dcps.DcpsAlerts" var="dcpsAlerts" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="empList" value="${resValue.lListEmployeesFromDummyOffice}"></c:set>

<script>
function getEmpListForDeptnContri()
{
	var payYear = document.getElementById("cmbYear").value ;
	var payMonth = document.getElementById("cmbMonth").value ; 
	var dummyOfficeId = document.getElementById("cmbOfficeCode").value ;
	self.location.href = "ifms.htm?actionFlag=loadChallanForDummy&dummyOfficeId="+dummyOfficeId+"&payMonth="+payMonth+"&payYear="+payYear ;
}
function saveDeputationContri()
{
	var dummyOfficeId = document.getElementById("cmbOfficeCode").value;
	var monthId = document.getElementById("cmbMonth").value;
	var yearId = document.getElementById("cmbYear").value;

	var dcpsEmpIds = "" ;
	
	var contributions = "" ;
	var challanNos = "" ;
	var challanDates = "" ;

	var contributionsEmplr = "" ;
	var challanNosEmplr = "" ;
	var challanDatesEmplr = "" ;

	var totalEmployees = document.getElementById("hdnCounter").value ;
	var finalSelectedEmployee=0;
	
	for(var k=1;k<=totalEmployees;k++)
	{
		if(document.getElementById("checkbox"+k).checked)
		{  
			finalSelectedEmployee = k ;
		}
	}

	if(finalSelectedEmployee == 0)
	{
		alert('Please enter at least one contribution');
		return false; 
	}

	for(var i=1;i<=totalEmployees;i++)
	{
		if(document.getElementById("checkbox"+i).checked)
		{
			if(i==finalSelectedEmployee)
			{
				dcpsEmpIds = dcpsEmpIds + document.getElementById("checkbox"+i).value;
				contributions = contributions + document.getElementById("contribution"+i).value;
				challanNos = challanNos + document.getElementById("txtChallanNo"+i).value;
				challanDates = challanDates +  document.getElementById("txtChallanDate"+i).value ;

				contributionsEmplr = contributionsEmplr + document.getElementById("contributionEmplr"+i).value;
				challanNosEmplr = challanNosEmplr + document.getElementById("txtChallanNoEmplr"+i).value;
				challanDatesEmplr = challanDatesEmplr +  document.getElementById("txtChallanDateEmplr"+i).value ;
			}
			else
			{
				dcpsEmpIds = dcpsEmpIds + document.getElementById("checkbox"+i).value + "~" ;
				contributions = contributions + document.getElementById("contribution"+i).value + "~" ;
				challanNos = challanNos + document.getElementById("txtChallanNo"+i).value + "~" ;
				challanDates = challanDates +  document.getElementById("txtChallanDate"+i).value  + "~" ;

				contributionsEmplr = contributionsEmplr + document.getElementById("contributionEmplr"+i).value + "~";
				challanNosEmplr = challanNosEmplr + document.getElementById("txtChallanNoEmplr"+i).value + "~";
				challanDatesEmplr = challanDatesEmplr +  document.getElementById("txtChallanDateEmplr"+i).value + "~";
			}
		}
	}
	
	
	xmlHttp = GetXmlHttpObject();

	if (xmlHttp == null) {
		alert("Your browser does not support AJAX!");
		return;
	}
	
	var uri = 'ifms.htm?actionFlag=saveContriForDeptn&dummyOfficeId='+dummyOfficeId+'&monthId='+monthId+'&yearId='+yearId +'&dcpsEmpIds='+dcpsEmpIds
				+ '&contributions=' +contributions + '&challanNos=' +challanNos + '&challanDates=' + challanDates 
				+ '&contributionsEmplr=' + contributionsEmplr + '&challanNosEmplr=' + challanNosEmplr + '&challanDatesEmplr=' + challanDatesEmplr ;
   
	xmlHttp.onreadystatechange = function()
	{
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				XMLDoc = xmlHttp.responseXML.documentElement;
				var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');

					var successFlag = XmlHiddenValues[0].childNodes[0].text;
					if(successFlag)
					{
						alert('Challan details saved successfully');
						self.location.href = "ifms.htm?actionFlag=loadChallanForDummy&dummyOfficeId="+dummyOfficeId+"&payMonth="+monthId+"&payYear="+yearId ;
					}
		  	}
		}
	};
	xmlHttp.open("POST", uri, false);
	xmlHttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlHttp.send(uri);
}
</script>

<hdiits:form name="frmChallanEntry" encType="multipart/form-data" validate="true" method="post">
<fieldset class="tabstyle">
<legend><b><fmt:message key="CMN.CHALLANENTRYFORM" bundle="${dcpsLabels}"></fmt:message> </b></legend>

<table id="tbl1" width="80%" align="center">
	<tr>
		<td width="33%" align="left" ><fmt:message key="CMN.OFFICENAME" bundle="${dcpsLabels}"></fmt:message>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<select name="cmbOfficeCode" id="cmbOfficeCode" style="width:60%" onChange="">
								<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
							<c:forEach var="OfficeList" items="${resValue.OFFICELIST}">		
								<c:choose>
									<c:when test="${resValue.dummyOfficeId == OfficeList.id}">
									<option value="${OfficeList.id}" selected="selected"><c:out value="${OfficeList.desc}"></c:out></option>
									</c:when>			
									<c:otherwise>
									<option value="${OfficeList.id}"><c:out value="${OfficeList.desc}"></c:out></option>
									</c:otherwise>
								</c:choose>
				 			</c:forEach>
			</select>
		</td>
		<td width="33%" align="left" ><fmt:message key="CMN.PAYMONTH" bundle="${dcpsLabels}"></fmt:message>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<select name="cmbMonth" id="cmbMonth" style="width:50%"  onChange="" >
						<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
								<c:forEach var="month" items="${resValue.MONTHS}" >
											<c:choose>
													<c:when test="${resValue.monthId == month.id}">
																	<option value="${month.id}" selected="selected"><c:out 
																							value="${month.desc}"></c:out></option>
													</c:when>
													<c:otherwise>
																	<option value="${month.id}"><c:out 
																							value="${month.desc}"></c:out></option>
													</c:otherwise>						
											</c:choose>
								</c:forEach>
			</select>
		</td>
		<td width="33%" align="left" ><fmt:message key="CMN.PAYYEAR" bundle="${dcpsLabels}"></fmt:message>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<select name="cmbYear" id="cmbYear" style="width:50%"   onChange="" >
						<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
								<c:forEach var="year" items="${resValue.YEARS}" >
											<c:choose>
													<c:when test="${resValue.yearId == year.id}">
																	<option value="${year.id}" selected="selected"><c:out 
																							value="${year.desc}"></c:out></option>
													</c:when>
													<c:otherwise>
																	<option value="${year.id}"><c:out 
																							value="${year.desc}"></c:out></option>
													</c:otherwise>						
											</c:choose>
								</c:forEach>
			</select>
		</td>
	</tr>
</table>
<br/>
<table align="center">
	<tr>
		<td>
			<hdiits:button name="btnGo" id="btnGo" type="button"  captionid="BTN.GO" bundle="${dcpsLabels}"
																		onclick="getEmpListForDeptnContri();" />
			<hdiits:button  name="btnBack" id="btnBack" type="button"  captionid="BTN.BACK"
								bundle="${dcpsLabels}" onclick="ReturnLoginPage();"/> 
		</td>
	</tr>
</table>
<br/>

<c:if test="${empList != null}">
<fieldset class="tabstyle">
<legend><b><fmt:message key="CMN.EMPLOYEESDUMMY" bundle="${dcpsLabels}"></fmt:message> </b></legend>
	<input type="hidden" name="hdnCounter" id="hdnCounter" value="0"/>
	<div align="center">
	<div style="overflow:auto;height:100%;width: 100%" align="center" >
		<display:table list="${empList}"  id="vo" requestURI="" export="" style="width:100%,border: thin;"  pagesize="10" >
		<display:setProperty name="paging.banner.placement" value="bottom" />		
		
				<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;"   sortable="true" titleKey="CMN.NAME" >
						 <label id="name${vo_rowNum}"><b>${vo[0]}</b></label>
				</display:column>
				
				<display:column headerClass="datatableheader" class="tablecelltext" style="text-align:center;"   sortable="true" titleKey="CMN.DCPSID" >
						<label id="dcpsId${vo_rowNum}"><b>${vo[2]}</b></label>
				</display:column>
				
				<display:column headerClass="datatableheader" class="tablecelltext" style="text-align:center;"   sortable="true" titleKey="CMN.CHALLANNOEMP" >
				
				 	<c:choose>
				  					<c:when test="${vo[6]==0}">
										<input type="text" id="txtChallanNo${vo_rowNum}"
											size="15" name="txtChallanNo${vo_rowNum}" value="" />
									</c:when>
									<c:otherwise>
										<input type="text" id="txtChallanNo${vo_rowNum}"
											size="15" name="txtChallanNo${vo_rowNum}" value="${vo[3]}" readonly="readonly" />
									</c:otherwise>
					</c:choose>
					
				</display:column>
				
				<display:column headerClass="datatableheader" class="tablecelltext" style="text-align:center;"   sortable="true" titleKey="CMN.CHALLANDATEEMP" >
				
				<c:choose>
				  					<c:when test="${vo[6]==0}">
				  					<input type="text" name="txtChallanDate${vo_rowNum}" id="txtChallanDate${vo_rowNum}" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
											onBlur="validateDate(this);" value="" /> 
											<img src='images/CalendarImages/ico-calendar.gif' 
											onClick='window_open("txtChallanDate${vo_rowNum}",375,570)'
											style="cursor: pointer;" />
				  					</c:when>
				  					<c:otherwise>
				  					<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[4]}" var="challanDate"/>
									<input type="text" name="txtChallanDate${vo_rowNum}" id="txtChallanDate${vo_rowNum}" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
											onBlur="validateDate(this);" value="${challanDate}" readonly="readonly" /> 
											<img src='images/CalendarImages/ico-calendar.gif' 
											onClick='window_open("txtChallanDate${vo_rowNum}",375,570)'
											style="cursor: pointer;" />
				  					</c:otherwise>
				</c:choose>
				
				</display:column>
				
			    <display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;"  sortable="true"  titleKey="CMN.CONTRIBUTIONEMP" >
			    <c:choose>
				  					<c:when test="${vo[6]==0}">
				  		  			  <input type="text" size="10" id="contribution${vo_rowNum}" name="contribution" value="" onkeypress= "amountFormat(this);"/>
				  		  			</c:when>
				  		  			<c:otherwise>
				  		  				<input type="text" size="10" id="contribution${vo_rowNum}" name="contribution" value="${vo[5]}" onkeypress= "amountFormat(this);" readonly="readonly"/>
				  		  			</c:otherwise>
				</c:choose>
				</display:column>  	
				
					<display:column headerClass="datatableheader" class="tablecelltext" style="text-align:center;"   sortable="true" titleKey="CMN.CHALLANNOEMPLR" >
				
				 	<c:choose>
				  					<c:when test="${vo[6]==0}">
										<input type="text" id="txtChallanNoEmplr${vo_rowNum}"
											size="15" name="txtChallanNoEmplr${vo_rowNum}" value="" />
									</c:when>
									<c:otherwise>
										<input type="text" id="txtChallanNoEmplr${vo_rowNum}"
											size="15" name="txtChallanNoEmplr${vo_rowNum}" value="${vo[7]}" readonly="readonly" />
									</c:otherwise>
					</c:choose>
					
				</display:column>	
				
				<display:column headerClass="datatableheader" class="tablecelltext" style="text-align:center;"   sortable="true" titleKey="CMN.CHALLANDATEEMPLR" >
				
				<c:choose>
				  					<c:when test="${vo[6]==0}">
				  					<input type="text" name="txtChallanDateEmplr${vo_rowNum}" id="txtChallanDateEmplr${vo_rowNum}" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
											onBlur="validateDate(this);" value="" /> 
											<img src='images/CalendarImages/ico-calendar.gif' 
											onClick='window_open("txtChallanDateEmplr${vo_rowNum}",375,570)'
											style="cursor: pointer;" />
				  					</c:when>
				  					<c:otherwise>
				  					<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[8]}" var="challanDateEmplr"/>
									<input type="text" name="txtChallanDateEmplr${vo_rowNum}" id="txtChallanDateEmplr${vo_rowNum}" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
											onBlur="validateDate(this);" value="${challanDateEmplr}" readonly="readonly" /> 
											<img src='images/CalendarImages/ico-calendar.gif' 
											onClick='window_open("txtChallanDateEmplr${vo_rowNum}",375,570)'
											style="cursor: pointer;" />
				  					</c:otherwise>
				</c:choose>
				
				</display:column>
				
				<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;"  sortable="true"  titleKey="CMN.CONTRIBUTIONEMPLR" >
			    <c:choose>
				  					<c:when test="${vo[6]==0}">
				  		  			  <input type="text" size="10" id="contributionEmplr${vo_rowNum}" name="contributionEmplr" value="" onkeypress= "amountFormat(this);"/>
				  		  			</c:when>
				  		  			<c:otherwise>
				  		  				<input type="text" size="10" id="contributionEmplr${vo_rowNum}" name="contributionEmplr" value="${vo[9]}" onkeypress= "amountFormat(this);" readonly="readonly"/>
				  		  			</c:otherwise>
				</c:choose>
				</display:column>  	
				
				<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;"  sortable="true">
				  		
				  				<c:choose>
				  					<c:when test="${vo[6]==0}">
				  						 <input type="checkbox" id="checkbox${vo_rowNum}" name="checkbox" value="${vo[1]}" />
				  					</c:when>
				  					<c:otherwise>
				  		  			  <input type="checkbox" id="checkbox${vo_rowNum}" name="checkbox" value="${vo[6]}" disabled="disabled"  />
								    </c:otherwise>  
								</c:choose>
								
								      <script>
										document.getElementById("hdnCounter").value=Number(document.getElementById("hdnCounter").value) + 1;
						 			  </script>
				</display:column> 		
				
		</display:table>
		</div>
		</div>

<table align="center" width="33%" >
	<tr>
		<td align="center" style="padding-left:15px;"><hdiits:button name="btnSubmit" id="btnSubmit" type="button"  style="width:30%;" 
							captionid="BTN.SUBMIT" bundle="${dcpsLabels}"
							onclick="saveDeputationContri();" />
		</td>
		<td></td>
	</tr>
</table>

</fieldset>
</c:if>


</fieldset>
</hdiits:form>
<%}catch(Exception e)
{
	e.printStackTrace();
}%>