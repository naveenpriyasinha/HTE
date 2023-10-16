<%
try {
%>
<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript"  
    src="script/common/commonfunctions.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>

<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>">
	</script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultofmapping" value="${resultofmapping}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="dataList" value="${resValue.resultSet}"/>
<c:out value="${resValue.result}"/>
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<script type="text/javascript" language="JavaScript">
function submit()
{
document.SGDMAP.action="./hdiits.htm?actionFlag=GradeDesignationScaleMaster";
document.SGDMAP.submit();
}

function submitForm(){ 
	win = window.open("common/progressbar.html",'','width=270,height=30,titlebar=0,toolbar=0,left=400,top=300');
	return true;
}
function searchData()
{
	
	document.forms[0].action="./hdiits.htm?actionFlag=getGradDesgScaleMap";
	document.forms[0].submit();

}
</script>
<hdiits:form name="SGDMAP" validate="true" method="POST"
	action="./hdiits.htm?actionFlag=GradeDesignationScaleMaster" >

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#"  rel="tcontent2"><b> <fmt:message key= "eis.gradeDesgScaleMaster" bundle="${commonLables}"/></b></a></li>
	</ul>
</div>
	
	
<div class="tabcontentstyle">
	  
	<div id="tcontent2" class="tabcontent" tabno="1">
	   	   <br>
	      <a href= "./hrms.htm?actionFlag=GradeDesignationScaleMaster">  Add new Entry </a>
		  <br><br><br>
		  
<table  width="85%" border="3" bordercolor="BLACK" bgcolor="white" align="center" name="searchTablePost" id="searchTablePost">		
	<tr><td align="center" colspan="3"><font size="4" face="VERDANA" color="navy">
			<b> Designation Wise Search</b></font></td>
	</tr>
	<tr>
		<td align = "right">
			<hdiits:caption caption="SEARCH DESIGNATION&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" captionid="srch_Dsgn"/></td>
		<td align = "center">
			<hdiits:text name="Dsgn_srchText_" captionid="DsgnSrch"/>
		</td>
		<td width="34%" align="left">
   			<hdiits:button  type="button" captionid="Desg_Employee"  value="&nbsp;&nbsp;&nbsp;&nbsp;search &nbsp;&nbsp;&nbsp;&nbsp;"  name="search" onclick="searchData()"/>
		</td>
	</tr>
</table>
				
				
				<br><br>
			
<display:table name="${dataList}" requestURI=""  pagesize="${pageSize}" id="row" export="true" style="width:100%">
<display:column class="tablecelltext" title="Group Name" headerClass="datatableheader" value="${row.hrEisGdMpg.orgGradeMst.gradeName}" > </display:column>	
<display:column class="tablecelltext" title="Designation Name" headerClass="datatableheader" value="${row.hrEisGdMpg.orgDesignationMst.dsgnName}" > </display:column>	
			  
			  <c:choose>
				  <c:when test="${row.hrEisScaleMst.scaleHigherIncrAmt ne 0}">
				  		<c:set var="scale" value="${row.hrEisScaleMst.currencyStartAmount}-${row.hrEisScaleMst.scaleIncrAmt}-${row.hrEisScaleMst.currencyEndAmount}-${row.hrEisScaleMst.scaleHigherIncrAmt}-${row.hrEisScaleMst.currencyHigherEndAmount}"/>
				  </c:when>
				   <c:otherwise>
				  		<c:set var="scale" value="${row.hrEisScaleMst.currencyStartAmount}-${row.hrEisScaleMst.scaleIncrAmt}-${row.hrEisScaleMst.currencyEndAmount}"/>
				  </c:otherwise>
			  </c:choose>
			  
			  <display:column class="tablecelltext" title="Scale" headerClass="datatableheader" value="${scale}" > </display:column>	
			  <display:column class="tablecelltext" title="Grade Pay" headerClass="datatableheader" value="${row.hrEisScaleMst.scaleGradePay}" > </display:column>	
		  	  <display:setProperty name="export.pdf" value="true" />
  		  </display:table>
		 <br>
	   	 <a href= "./hrms.htm?actionFlag=GradeDesignationScaleMaster">  Add new Entry </a>
	</div>
	</div>


</div>
	<script type="text/javascript">
		initializetabcontent("maintab")
		
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />



</hdiits:form>

<%
		} catch (Exception e) 
		{
		e.printStackTrace();
	}
%>


