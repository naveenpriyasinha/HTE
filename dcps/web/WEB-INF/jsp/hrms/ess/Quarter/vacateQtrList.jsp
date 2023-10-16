<% 
try
{
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<fmt:setBundle basename="resources.ess.quarter.QuarterAllocationLables" var="QtrLables" scope="request" />
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="common/script/commonfunctions.js"></script>

<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/quarter/QuarteraddRecord1.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/quarter/quarterAllot.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/eis/commonUtils.js"/>"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="flag" value="${resValue.flag}"></c:set>
<c:set var="qtrList" value="${resValue.qtrList}"></c:set>
<c:set var="jspFlag" value="${resValue.jspFlag}"></c:set>

<script type="text/javascript" language="JavaScript"> 
function getSelectedValue(qid)
{
		//alert(qid.value);
//		document.getElementById('QtrIdH').value='';
		document.getElementById('QtrIdH').value=qid.value;
}
function submitReq()
{
	var selectedQtr = document.getElementById('QtrIdH').value;
	//alert(document.getElementById('QtrIdH').value);
	
	if (window.dialogArguments) 
    {
    	window.opener = window.dialogArguments;
  	}

	window.opener.getSelectedQtr(selectedQtr);
	window.close();
}
</script>

<hdiits:form name="form1" validate="" action="hrms.htm?actionFlag=insertQtrAlloReq" method="post">
<br>
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><fmt:message key="HRMS.vacQtrLst" bundle="${QtrLables}"/></a></li>
	</ul>
</div>
<div class="tabcontentstyle">
<div id="tcontent1" class="tabcontent" tabno="0">

<TABLE WIDTH="100%" ALIGN="CENTER">
<TR>
<TD>

<c:if test="${flag == 1 }" >
	<TABLE width="100%" border="1">
		<TR align="center">
			<TD><b><fmt:message key="HRMS.NotFound" bundle="${QtrLables}" /></b></TD>
		</TR>
	</TABLE>
	<TABLE width="100%">
		<TR><TD width="50%" align="center">
			<hdiits:button name="close" type="button" value="Close"  captionid="HRMS.Close" bundle="${QtrLables}" onclick="window.close();"/>
		</TD></TR>
	</TABLE>
</c:if>



<c:if test="${flag == 0 }" >
	<c:if test="${jspFlag == '0' }">
	 <hdiits:fieldGroup titleCaptionId= "HRMS.selectQtrToAllocate" bundle="${QtrLables}" id="selectQtrToAllocate">
		<!--  <TABLE width="100%">
			<TBODY>
  	  			<TR bgColor=#386cb7>
    			<TD align="center" class=fieldLabel colSpan=5><FONT color=#ffffff ><STRONG><U><fmt:message key="HRMS.selectQtrToAllocate" bundle="${QtrLables}"/>
      			</U></STRONG></FONT>
      			</TD>
      			</TR>
     		</TBODY>
		</TABLE>-->
		
		<br>

	<display:table list="${resValue.qtrList}" id="qtrList" style="width:100%" pagesize="10" requestURI="" defaultsort="1"  defaultorder="ascending">
 	  	<display:setProperty name="paging.banner.placement" value="bottom"/>
			<display:column class="tablecelltext" titleKey="HRMS.Select"  headerClass="datatableheader" sortable="false" style="text-align: center"><hdiits:radio name="qtrId" id="qtrId" value="${qtrList.quarterId}/${qtrList.quarterName}/${qtrList.hrEssQtrMstQtrId.cmnLocationMstByPoliceStId.locName}/${qtrList.hrEssQtrMstQtrId.namePoliceLine}" onclick="getSelectedValue(this)" /></display:column>
			<display:column class="tablecelltext" titleKey="HRMS.PoliceStationHQ" sortable="false" headerClass="datatableheader"  >${qtrList.hrEssQtrMstQtrId.cmnLocationMstByPoliceStId.locName}</display:column>		
			<display:column class="tablecelltext" titleKey="HRMS.PoliceLine"  sortable="false" headerClass="datatableheader" >${qtrList.hrEssQtrMstQtrId.namePoliceLine} </display:column>  	
			<display:column class="tablecelltext" titleKey="HRMS.QuarterName"  sortable="false" headerClass="datatableheader" >${qtrList.quarterName} </display:column>  
 	</display:table>  
 	<TABLE id="submit" width="100%" >
	<TR>
		<TD width="50%" align="center">
			<hdiits:button name="Ok" type="button" value="Ok"  captionid="HRMS.Ok" bundle="${QtrLables}" onclick="submitReq()"/>
		</TD>
	</TR>
	</TABLE>
	</hdiits:fieldGroup>
 	</c:if>
 	<c:if test="${jspFlag == '1' }">
 		
 		<display:table list="${resValue.qtrList}" id="qtrList" style="width:100%" pagesize="10" requestURI="" defaultsort="1"  defaultorder="ascending">
 	  	<display:setProperty name="paging.banner.placement" value="bottom"/>
			<display:column class="tablecelltext" titleKey="HRMS.PoliceStationHQ" sortable="false" headerClass="datatableheader"  >${qtrList.hrEssQtrMstQtrId.cmnLocationMstByPoliceStId.locName}</display:column>		
			<display:column class="tablecelltext" titleKey="HRMS.PoliceLine"  sortable="false" headerClass="datatableheader" >${qtrList.hrEssQtrMstQtrId.namePoliceLine} </display:column>  	
			<display:column class="tablecelltext" titleKey="HRMS.QuarterName"  sortable="false" headerClass="datatableheader" >${qtrList.quarterName} </display:column>  
 		</display:table>  
 		
 		<TABLE id="submit" width="100%" >
			<TR>
			<TD width="50%" align="center">
				<hdiits:button name="close" type="button" value="Close"  captionid="HRMS.Close" bundle="${QtrLables}" onclick="window.close();"/>
			</TD>
			</TR>
		</TABLE>
 		
 	</c:if>
</c:if>	

</TD>
</TR>
</TABLE>
<script type="text/javascript">
	initializetabcontent("maintab");
</script>
<input name="QtrIdH" type="hidden" maxlength="70"/>
</div></div>
</hdiits:form>


<%
}
catch(Exception e){
	e.printStackTrace();
}
%>