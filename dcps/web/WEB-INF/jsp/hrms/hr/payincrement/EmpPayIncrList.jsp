<%@ include file="../../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 

<fmt:setBundle basename="resources.hr.payincrement.PayInc" var="EPAYINLables" scope="request" />

<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/hr/payincrement/EmpPayIncrement.js"/>"></script>

<script type="text/javascript">
	var empPayIncrListAlert = new Array();
	empPayIncrListAlert[0]='<fmt:message  bundle="${EPAYINLables}" key="YEAR_ALERT"/>';
	empPayIncrListAlert[1]='<fmt:message  bundle="${EPAYINLables}" key="MONTH_ALERT"/>';
	empPayIncrListAlert[2]='<fmt:message  bundle="${EPAYINLables}" key="EMP_SEL_ALERT"/>';
</script>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="arMonth" value="${resultValue.arMonth}"></c:set>
<c:set var="arYear" value="${resultValue.arYear}"></c:set>
<c:set var="year" value="${resultValue.year}"></c:set>
<c:set var="month" value="${resultValue.month}"></c:set>

<hdiits:form name="frmEmpPyaIncr" validate="true" method="post">
<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected">
				<a href="#" rel="tcontent1"><b><fmt:message key="EMP_PAY_INCR" bundle="${EPAYINLables}"/></b></a>
			</li>
		</ul>
	</div>
	<div class="tabcontentstyle">
    <div id="tcontent1" class="tabcontent" tabno="0">
<table align="center">
<tr>
	<td>
		<hdiits:caption captionid="MONTH" bundle="${EPAYINLables}" />
	</td>
	<td>
	<hdiits:select name="selMonth"  id="selMonth" size="1" sort="false" captionid="MONTH" bundle="${EPAYINLables}">
		<hdiits:option value="-1"><fmt:message key="SELECT" bundle="${EPAYINLables}"/></hdiits:option>
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
		<hdiits:caption captionid="YEAR" bundle="${EPAYINLables}" />
	</td>
	<td>
	<hdiits:select name="selYear"  id="selYear" size="1" sort="false" captionid="YEAR" bundle="${EPAYINLables}">
		<hdiits:option value="0"><fmt:message key="SELECT" bundle="${EPAYINLables}"/></hdiits:option>
			
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
		<hdiits:button  name="btnSearch" type="button" captionid="SEARCH" bundle="${EPAYINLables}" onclick="searchForIncr()"/>
	</td>
</tr>
</table>

<fieldset class="tabstyle">
<c:set var="i" value="1" />
	<legend  id="headingMsg"><hdiits:caption captionid="SEL_EMP" bundle="${EPAYINLables}" /></legend>

	<display:table list="${resultValue.AllEmpPayIncrDtlsList}" id="row" requestURI="" pagesize="10"  style="width:100%" offset="1">
		<display:setProperty name="paging.banner.placement" value="bottom"/>
		
		<fmt:formatDate var="lastIncrDate" pattern="dd/MM/yyyy" value="${row.lastIncrDate}" type="date"/>				
		<fmt:formatDate var="NextIncreamentDate" pattern="dd/MM/yyyy" value="${row.nextIncrDate}" type="date"/>	
		<fmt:formatDate var="EffectiveDate" pattern="dd/MM/yyyy" value="${row.effectiveDate}" type="date"/>			
					
		<display:column class="tablecelltext" headerClass="datatableheader" style="text-align: center" titleKey="SLCT" sortable="true">
			<hdiits:radio name="radioPayFixId" id="radioPayFixId" onclick="checkRadioType()" value="${row.userId}°${row.payFixId}°${i}"/>
		</display:column> 
		
		<display:column class="tablecelltext" titleKey="SR_NO" headerClass="datatableheader" style="text-align: center" sortable="true" >${i}</display:column>
		
		<display:column class="tablecelltext" titleKey="EMP_NAME" headerClass="datatableheader" style="text-align: center" sortable="true" >${row.empName}</display:column>
		
		<display:column class="tablecelltext" titleKey="CRNT_PAY_SCALE" headerClass="datatableheader" style="text-align: center" sortable="true" >${row.currentPayScale}</display:column>
		
		<display:column class="tablecelltext" titleKey="CRNT_PAY" headerClass="datatableheader" style="text-align: center" sortable="true" >${row.currentPay}</display:column>
		
		<display:column class="tablecelltext" titleKey="LST_INCR_DATE" headerClass="datatableheader" style="text-align: center;" sortable="true" >${lastIncrDate}</display:column>
						
		<display:column class="tablecelltext" titleKey="DUE_INCR_DATE" headerClass="datatableheader" style="text-align: center;" sortable="true">${NextIncreamentDate}</display:column>	
		
		<display:column class="tablecelltext" titleKey="EFC_INCR_DATE" headerClass="datatableheader" style="text-align: center;" sortable="true" >${EffectiveDate}</display:column>	        
		
		<c:if test="${i ge 10}">
			<c:set var="i" value="0" />
		</c:if>
		
		<c:set var="i" value="${i+1}" />
		<display:footer media="html"></display:footer>		
		
	</display:table>
</fieldset>

<table align="center">
<tr>
	<td>
		<br></br><hdiits:button name="btnOK" type="button" captionid="OK" readonly="false" bundle="${EPAYINLables}" onclick="selectEmloyees()"/>
	</td>
	<td>
		<br></br><hdiits:button name="btnClose" type="button" captionid="CLOSE" bundle="${EPAYINLables}" onclick="closeWindow()"></hdiits:button>
	</td>
</tr>
</table>	

<script type="text/javascript">

var radioValue="";
var radioUserId=0;
var radioPayFixId=0;
var radioLWP=0;
var radioDueDate=null;
var radioEffDate=null;
var disRowId="";

function selectEmloyees()
{
	if(radioValue!="")
	{	
		radioValueArr=radioValue.split("°");
		disRowId = radioValueArr[2];
		radioUserId = radioValueArr[0];
		radioPayFixId = radioValueArr[1];
		
		//radioLWP=document.getElementById("row").getElementsByTagName('TR')[disRowId].childNodes[7].childNodes[0].innerHTML;
		
		radioEffDate=document.getElementById("row").getElementsByTagName('TR')[disRowId].childNodes[7].innerHTML;
		radioDueDate=document.getElementById("row").getElementsByTagName('TR')[disRowId].childNodes[6].innerHTML;
		
		displayModalDialog("hrms.htm?actionFlag=showEmpNextPayIncr&UserId="+ radioUserId +"&PayFixId="+ radioPayFixId +"&lwp="+ radioLWP +"&duedate="+ radioDueDate +"&effDate="+ radioEffDate +"" ,"PayIncreament" ,"toolbar=no, location=no, directories=no,status=yes, menubar=yes, scrollbars=yes, resizable=yes, top=100, left=100, width="+ 850 +", height="+ 600 +", copyhistory=no");
	}
	else
	{
		alert(empPayIncrListAlert[2]);
	}
}
	
initializetabcontent("maintab");

</script>
</div></div>
</hdiits:form>


