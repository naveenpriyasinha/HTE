<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>


<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script language="JavaScript" src="script/dcps/sixPCArrearAmountSchedule.js"></script>


<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLabels" scope="request"/>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="lLstEmp" value="${resValue.lLstEmp}" />
<c:set var="UserType" value="${resValue.UserType}" />
<c:set var="yearId" value="${resValue.yearId}" />



<hdiits:form name="DCPSArrearAmtSchedule" id="DCPSArrearAmtSchedule" encType="multipart/form-data" validate="true" method="post" >
<input type="hidden" name="UserType" id="UserType" value="${UserType}"></input>
<input type="hidden" name="yearId" id="yearId" value="${yearId}"></input>
<fieldset style="width: 100%" class="tabstyle">
<legend><b>Select Financial Year</b></legend>
<table align="center" width="30%">
   <tr align="center">
	       <td align="right" width="30%"><fmt:message key="CMN.FINANCIALYEAR"
					bundle="${dcpsLabels}"></fmt:message>
		   </td>
		   
		   <td align="center" width="70%" style="padding-left: 45px">
		       <select name="cmbFinancialYear" id="cmbFinancialYear" style="width: 50%" >				
				 <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>  
				 <c:forEach var="yearVar" items="${resValue.lListYears}">	
				 	<c:choose>
				 		<c:when test="${yearId == yearVar.id}">
				 			<option value="${yearVar.id}" selected="selected">
								<c:out value="${yearVar.desc}"></c:out>					
							</option>
						</c:when>
						<c:otherwise>
							<option value="${yearVar.id}">
								<c:out value="${yearVar.desc}"></c:out>					
							</option>
						</c:otherwise>
				 	</c:choose>				
					
				 </c:forEach>
			   </select>
		   </td>
	   </tr>
	 <tr></tr>
	 <tr></tr>
	<tr align="center">
	       <td align="left" width="30%"></td>
	  	   <td align="left" width="70%">
	  	   <c:if test="${UserType == 'DDOAsst'}">
	  			 <hdiits:button name="btnGo" id="btnGo" type="button" captionid="BTN.GO" 
		               bundle="${dcpsLabels}" onclick="displayDataForGivenYear();" style="size:15"/>
		               </c:if>
		                  <c:if test="${UserType == 'DDO'}">
	  			 <hdiits:button name="btnGo" id="btnGo" type="button" captionid="BTN.GO" 
		               bundle="${dcpsLabels}" onclick="displayDataForGivenYearDDO();" style="size:15"/>
		               </c:if>
		   </td>
		 
</tr>
</table>
</fieldset>
<c:set var="counter" value="1" />
<br></br>
<c:if test="${(resValue.lLstEmp != null )}">
<fieldset style="width: 100%" class="tabstyle">
<legend><b> Yearly Process of 6th PC Arrear Amount Deposition To DCPS Print Schedule</b></legend>
	<div align="center">
    <display:table list="${lLstEmp}"  id="vo"   requestURI="" export="" style="width:90%"  pagesize="10">	

		<display:setProperty name="paging.banner.placement" value="bottom" />	
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" title="<input name='chkSelect' type='checkbox' onclick='checkUncheckAll(this,\"chkSelect\");'/>">
			<input type="checkbox" name="chkbxFormVeri" id="chkbxFormVeri" value="${vo[0]}"/>
		
		</display:column>
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true" titleKey="CMN.EMPSRNO" >		
				<c:out value="${counter}"></c:out> 
		</display:column>
		
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true" titleKey="CMN.FINYEAR" >		
			<c:out value="${vo[5]}" />
		</display:column>
		
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true"  titleKey="CMN.DCPSID" >		
				<c:out value="${vo[1]}" />
		</display:column>
		
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true"  titleKey="CMN.EMPLOYEENAME" >		
				<c:out value="${vo[2]}" />
		</display:column>
		
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true"  titleKey="CMN.YEARLYAMOUNT" >		
				<c:out value="${vo[3]}" />
		</display:column>
	
		<c:set var="counter" value="${counter+1}" />
	</display:table>
	</div>
	</fieldset>
</c:if>
	
	<c:if test="${resValue.lLstEmp != null}">
	<br></br>
	<table align="center">
		<tr>
		<td>
			<hdiits:button name="btnPrint" id="btnPrint" type="button"  captionid="BTN.PRINT" bundle="${dcpsLabels}" onclick="printArrearScheduleReport();"/>
		</td>
		</tr>
	</table>
		
	</c:if>
	

</hdiits:form>	
