<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="//WEB-INF/jsp/core/include.jsp" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="SearchFieldName" value="${resultValue.strSearchFieldName}"></c:set>
<c:set var="arMonth" value="${resultValue.arMonth}"></c:set>
<c:set var="arYear" value="${resultValue.arYear}"></c:set>
<c:set var="year" value="${resultValue.year}"></c:set>
<c:set var="month" value="${resultValue.month}"></c:set>
<c:set var="todaysDate" value="${resultValue.todaysDate}"></c:set>
<c:set var="langId" value="${resultValue.langId}"></c:set>
<fmt:formatDate var="formattedDate" pattern="dd/MM/yyyy" value="${todaysDate}" type="date"/>

<fmt:setBundle basename="resources.hr.probation.Probation" var="CNFMLables" scope="request" />
<fmt:setBundle basename="resources.hr.probation.AlertMessages" var="CNFMAlerts" scope="request" />

<script type="text/javascript" src="<c:url value="/script/hrms/hr/probation/probation.js"/>"></script>
		
<script language="javascript">
var empConfirmationSearchAlert = new Array();
empConfirmationSearchAlert[0]="<fmt:message bundle='${CNFMAlerts}' key='CNFM.SelectOne'/>";
</script>  

<hdiits:form name="frmEmpConfirmSearch" validate="true" method="post">
<br>
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><fmt:message key="CNFM.Search" bundle="${CNFMLables}"/></a></li>
	</ul>
</div>
<div class="tabcontentstyle">
<div id="tcontent1" class="tabcontent" tabno="0">
<table align="center">
<tr>
	<td>
		<hdiits:caption captionid="CNFM.Month" bundle="${CNFMLables}" />
	</td>
	<td>
	<hdiits:select name="selMonth"  id="selMonth" size="1" sort="false">
		<hdiits:option value="0"><fmt:message key="CNFM.Dropdown.Select" bundle="${CNFMLables}"/></hdiits:option>
			<c:set var="counter" value="0"></c:set>
				<c:forEach var="monthValue" items="${arMonth}">		
				
					<c:choose>
						 <c:when test="${counter == month}">
						
						 <option value="<c:out value="${counter}"/>" selected="selected"> 
		    			 <c:out value="${monthValue.lookupDesc}"/></option>
		    			 </c:when>
		    			 
			   			 <c:otherwise>
			   			 <option  value="<c:out value="${counter}"/>"> 
		    			 <c:out value="${monthValue.lookupDesc}"/></option>	
			   			 </c:otherwise>
		   			</c:choose>					
					
					<c:set var="counter" value="${counter+1}"></c:set>
				</c:forEach>
	</hdiits:select>
	</td>
	<td>
		<hdiits:caption captionid="CNFM.Year" bundle="${CNFMLables}" />
	</td>
	<td>
	<hdiits:select name="selYear"  id="selYear" size="1" sort="false">
		<hdiits:option value="0"><fmt:message key="CNFM.Dropdown.Select" bundle="${CNFMLables}"/></hdiits:option>
			
				<c:forEach var="yearValue" items="${arYear}">	
				
					<c:choose>
						 <c:when test="${yearValue == year}">
						
						 <option value="<c:out value="${yearValue}"/>" selected="selected"> 
		    			 <c:out value="${yearValue}"/></option>
		    			 </c:when>
		    			 
			   			 <c:otherwise>
			   			 <option  value="<c:out value="${yearValue}"/>"> 
		    			 <c:out value="${yearValue}"/></option>	
			   			 </c:otherwise>
		   			</c:choose>	
					
				</c:forEach>
	</hdiits:select>
	</td>
	<td>
		<hdiits:button  name="btnSearch" type="button" captionid="CNFM.SearchBtn" bundle="${CNFMLables}" onclick="searchForPast()"/>
	</td>
</tr>
</table>
	
	<fieldset class="tabstyle">
		<legend  id="headingMsg"><fmt:message key="CNFM.SelEmp" bundle="${CNFMLables}" /></legend>
			
			<display:table list="${resultValue.arCurrentProbationEndList}" id="emplList" requestURI="" pagesize="10"  export="true" style="width:100%" offset="1">	
				<display:setProperty name="paging.banner.placement" value="bottom"/>
					
					<c:forEach var="orgEmpMst" items="${emplList.orgUserMstByUserId.orgEmpMsts}" varStatus="x">
					<c:set var="empLangId" value="${orgEmpMst.cmnLanguageMst.langId}" />
						<c:if test="${langId == empLangId}">
							<c:set var="empFname" value="${orgEmpMst.empFname}" />
							<c:set var="empMname" value="${orgEmpMst.empMname}" />
							<c:set var="empLname" value="${orgEmpMst.empLname}" />
						</c:if>	
					</c:forEach>
					
					<fmt:formatDate var="probationStartDate" pattern="dd/MM/yyyy" value="${emplList.probationStartDate}" type="date"/>				
					<fmt:formatDate var="probationEndDate" pattern="dd/MM/yyyy" value="${emplList.probationEndDate}" type="date"/>				
					<fmt:formatDate var="confirmationDate" pattern="dd/MM/yyyy" value="${emplList.confirmationDate}" type="date"/>
					
					<display:column class="oddcentre" headerClass="datatableheader" style="text-align:center">
						<input type="checkbox" name="chkBox" id="chkBox" value="${emplList.hrEmpProbationDtlPk}°${emplList.fileId}°${emplList.orgUserMstByUserId.userId}°${empFname} ${empMname} ${empLname}°${probationStartDate}°${probationEndDate}°${emplList.lwp}°${emplList.isConfirmed}°${confirmationDate}°${emplList.remarks}"/>								
					</display:column> 
					
					<display:column style="text-align: center;" class="tablecelltext" titleKey="CNFM.EmpName" headerClass="datatableheader" sortable="true" >${empFname} ${empMname} ${empLname}</display:column>
						
					<display:column style="text-align: center;" class="tablecelltext" titleKey="CNFM.PStartDate" headerClass="datatableheader" sortable="true" >${probationStartDate}</display:column>
						
					<display:column style="text-align: center;" class="tablecelltext" titleKey="CNFM.PEndDate" headerClass="datatableheader" sortable="true" >${probationEndDate}</display:column>	    
					
					
					<display:column style="text-align: center;" class="tablecelltext" titleKey="CNFM.Ext" headerClass="datatableheader" sortable="true" >
						<script>
							if('${emplList.isConfirmed}' == 'E')
								document.write("<fmt:message key='CNFM.Extended' bundle='${CNFMLables}'/>");
							else if('${emplList.isConfirmed}' == 'C')
								document.write("<fmt:message key='CNFM.Confirmed' bundle='${CNFMLables}'/>");
							else 
								document.write("--");
						</script>
					</display:column>
					
					<display:column style="text-align: center;" class="tablecelltext" titleKey="CNFM.CnfmDate" headerClass="datatableheader" sortable="true" >${confirmationDate}</display:column>
				
					<display:column style="text-align: center;" class="tablecelltext" titleKey="CNFM.Remarks" headerClass="datatableheader" sortable="true" >${emplList.remarks}</display:column>
					
						
				<display:footer media="html"></display:footer>		
			</display:table>

			
	</fieldset>
	
	<center>
		<hdiits:button  name="btnSelect" type="button" captionid="CNFM.Ok" bundle="${CNFMLables}" onclick="selectEmloyees()"  />
		<hdiits:button  name="btnClose" type="button" captionid="CNFM.Close" bundle="${CNFMLables}" onclick="window.close();"/>
	</center>

</div></div>	
</hdiits:form>
	
<script type="text/javascript">
	var todayDate = "${formattedDate}";
	setWindowName(window, document.frmEmpConfirmSearch);
	initializetabcontent("maintab");
</script>	
								