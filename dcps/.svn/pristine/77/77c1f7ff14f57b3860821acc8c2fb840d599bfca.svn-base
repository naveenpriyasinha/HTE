<% 
try
{
%>

<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@page buffer="256kb" autoFlush="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script type="text/javascript" src="<c:url value="/script/hrms/ess/quarter/Quarteraddress.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="script/common/prototype-1.3.1.js"></script>
<script type="text/javascript" src="script/leave/ajax_saveData.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="<c:url value="/script/eis/commonUtils.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/base64.js"/>"></script>
<fmt:setBundle basename="resources.ess.quarter.QuarterAllocationLables" var="QtrLables" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="hrModEmpRlt" value="${resValue.hrModEmpRlt}"></c:set>
<c:set var="EmpDetailsVO" value="${resValue.EmpDetailsVO}"></c:set>
<c:set var="QtrTxn" value="${resValue.QtrTxn}"></c:set>
<c:set var="qtrMst" value="${resValue.qtrMst}"></c:set>
<c:set var="ApproveStartDT" value="${resValue.ApproveStartDT}"></c:set>
<c:set var="QtrMpgLst" value="${resValue.QtrMpgLst}"></c:set>
<c:set var="totalFilled" value="${resValue.totalFilled}"></c:set>
<c:set var="totalvacant" value="${resValue.totalvacant}"></c:set>
<c:set var="pervQtrLst" value="${resValue.pervQtrLst}"></c:set>
<c:set var="prevFlag" value="${resValue.prevFlag}"></c:set>
<c:set var="vacateQtrLst" value="${resValue.vacateQtrLst}"></c:set>
<c:set var="quarterList" value="${resValue.quarterList}"></c:set>
<c:set var="fillQtrTypeLst" value="${resValue.fillQtrTypeLst}"></c:set>
<c:set var="pendQtrList" value="${resValue.pendQtrList}"></c:set>
<c:set var="lookupRate" value="${resValue.lookupRate}"></c:set>
					

<script type="text/javascript" language="JavaScript">

function loadData()
{ 
	   //document.getElementById('alloStartDt').value='${ApproveStartDT}';
}
</script>

<hdiits:form name="quartrAlloc" validate="true" action="" method="">


	<%@ include file="//WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
		
<hdiits:fieldGroup titleCaptionId="HRMS.PreviousAllotedQuarters" bundle="${QtrLables}" id="PrevAllotedQtr" >
<br>
<TABLE class=tabtable id=""  style="border-collapse: collapse;" borderColor="BLACK"  border=1>
	<TBODY>
	<c:if test="${prevFlag == 0}">
		<TR>
			<TD class=fieldLabel align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="HRMS.PoliceStationHQ" bundle="${QtrLables}"/></b></TD>
			<TD class=fieldLabel align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="HRMS.PoliceLine" bundle="${QtrLables}" /></b></TD>
			<TD class=fieldLabel align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="HRMS.TypeofOffice1" bundle="${QtrLables}" /></b></TD>
			<TD class=fieldLabel align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="HRMS.QuarterName" bundle="${QtrLables}" /></b></TD>
			<TD class=fieldLabel align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="HRMS.AllocatnDte" bundle="${QtrLables}" /></b></TD>
			<TD class=fieldLabel align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="HRMS.TentetiveEndDate" bundle="${QtrLables}" /></b></TD>
		</TR>
		<c:forEach var="preQtrList" items="${pervQtrLst}">
		<TR>
			<TD class=fieldLabel align="center"><c:out value="${preQtrList.hrEssQtrMstQtrId.cmnLocationMstByPoliceStId.locName}"/></TD>
			<TD class=fieldLabel align="center"><c:out value="${preQtrList.hrEssQtrMstQtrId.namePoliceLine}"/></TD>
			<TD class=fieldLabel align="center"><c:out value="${preQtrList.hrQuaterTypeMst.quaType}"/></TD>
			<TD class=fieldLabel align="center"><c:out value="${preQtrList.quarterName}"/></TD>
			<TD class=fieldLabel align="center"><fmt:formatDate value="${preQtrList.allocationStartDate}" pattern="dd-MM-yyyy"/></TD>
			<TD class=fieldLabel align="center"><fmt:formatDate value="${preQtrList.allocationEndDate}" pattern="dd-MM-yyyy"/></TD>
		</TR>
		</c:forEach>
	</c:if>
		<c:if test="${prevFlag == 1}">
		   <TR>
		   		<TD align="center">
		   			
		   			<font color="red"><b><fmt:message key="HRMS.NotFound" bundle="${QtrLables}"/></b></font>
		   		</TD>
		   </TR>
			
		</c:if>
	</TBODY>
</TABLE>	
</hdiits:fieldGroup>	
<br>	
<hdiits:fieldGroup titleCaptionId="HRMS.AppDetails" bundle="${QtrLables}" id="AppDetails" >
<br>
<TABLE width="100%">
<TBODY>
	
	<TR>
		<TD width="25%"><b><hdiits:caption captionid="HRMS.PoliceStationHQ" bundle="${QtrLables}" /></b></TD>
		<TD width="25%"><c:out value="${QtrTxn.hrEssQtrMst.cmnLocationMstByPoliceStId.locName}"/></TD>
		<TD width="25%"><b><hdiits:caption captionid="HRMS.PoliceLine" bundle="${QtrLables}" /></b></TD>
		<TD width="25%"><c:out value="${QtrTxn.hrEssQtrMst.namePoliceLine}"/></TD>
	</TR>
	<TR>
		<TD width="25%"><b><hdiits:caption captionid="HRMS.TypeOfHouse" bundle="${QtrLables}" /></b></TD>
		<TD width="25%"><c:out value="${QtrTxn.hrQuaterTypeMst.quaType}"/></TD>
		<TD width="25%"><b><hdiits:caption captionid="HRMS.Remarks" bundle="${QtrLables}" /></b></TD>
		<TD width="25%"><c:out value="${QtrTxn.remarks}"/></TD>
	</TR>

</TBODY>
</TABLE>
</hdiits:fieldGroup>
<br>
<hdiits:fieldGroup titleCaptionId="HRMS.SearchResult" bundle="${QtrLables}" id="searachDtls">
<TABLE  class=tabtable id=table123  border=1 style="border-collapse: collapse;" style ="display:none" borderColor="BLACK" >
  <TBODY>
  <TR>
    <TD rowSpan="2" align="center"><b><hdiits:caption captionid="HRMS.PoliceStationHQ" bundle="${QtrLables}" /></b></TD>
    <TD rowSpan="2" align="center"><b><hdiits:caption captionid="HRMS.PoliceLine" bundle="${QtrLables}" /></b></TD>
    <TD colSpan="2" align="center"><b><hdiits:caption captionid="HRMS.TotalQuarters" bundle="${QtrLables}" /></b></TD>
    
   
  </TR>
   </TBODY>
  </TABLE>
     
  <TABLE class=tabtable id=waittingAppTbl  border=1 style="border-collapse: collapse;" borderColor="BLACK"  >
   <tr>
   	<td align="center" bgcolor="#C9DFFF" class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.TypeOfHouse" bundle="${QtrLables}" /></b>	</td>
   	<td align="center" bgcolor="#C9DFFF" class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.TotalWaitting" bundle="${QtrLables}"/></b> </td>
   	<td align="center" bgcolor="#C9DFFF" class="fieldLabel" align="center"><b><hdiits:caption captionid="HRMS.VacantQtr" bundle="${QtrLables}"/></b> </td>
   </tr>
  
  <c:forEach items="${quarterList}" var="quarterValue" varStatus="x">
		<c:set var="fillQtrTypeValue" value="${vacateQtrLst[x.index]}"></c:set>
		<c:set var="pendQtrValue" value="${pendQtrList[x.index]}"></c:set>
		<TR>
	  		<TD align="center"><c:out value="${quarterValue.quaType}"/></TD>
	  		<TD align="center"><c:out value="${pendQtrValue}"/></TD>
	  		<TD align="center"><c:out value="${fillQtrTypeValue}"/></TD>
  		</TR>
    </c:forEach>
  </TABLE>
  </hdiits:fieldGroup>
<br>
<hdiits:fieldGroup titleCaptionId="HRMS.Allocate" bundle="${QtrLables}" id="titleTbl" >

<TABLE width="100%" align="center" id="detailTbl">
	<TR >
		<TD align="left"><b><hdiits:caption captionid="HRMS.PoliceStationHQ" bundle="${QtrLables}" /></b></TD>
		<TD><c:out value="${qtrMst.hrEssQtrMstQtrId.cmnLocationMstByPoliceStId.locName}"/></TD>
		<TD align="left"><b><hdiits:caption captionid="HRMS.PoliceLine" bundle="${QtrLables}" /></b></TD>
		<TD><c:out value="${qtrMst.hrEssQtrMstQtrId.namePoliceLine}"/></TD>
	</TR>
	<TR >
		<TD><b><hdiits:caption captionid="HRMS.TypeOfHouse" bundle="${QtrLables}" /> </b></TD>
		<TD><c:out value="${qtrMst.hrQuaterTypeMst.quaType}"/></TD>
		<TD align="left"><b><hdiits:caption captionid="HRMS.QuarterName" bundle="${QtrLables}" /></b></TD>
		<TD> <c:out value="${qtrMst.quarterName}"/></TD>
		
	</TR>
	<TR>	
		<TD align="left"><b><hdiits:caption captionid="HRMS.AllocatnDte" bundle="${QtrLables}" /></b></TD>
		<TD>
			<c:out value="${ApproveStartDT}"/>
			
		</TD>
		<td width="25%"><hdiits:caption captionid="HRMS.lookupRate" bundle="${QtrLables}" /></td>
		<td width="25%"><c:out value="${qtrMst.rateTypeLookup.lookupDesc}"></c:out>		</td>
	</TR>
</TABLE>
</hdiits:fieldGroup>
<table id="tabNavigationTbl" style="display:none" >
 <tr> <td>
		<jsp:include page="/WEB-INF/jsp/core/tabnavigation.jsp" >
			<jsp:param name="disableReset" value="true"/> 
			<jsp:param name="disableNext" value="true"/>
		</jsp:include>
 </td>
</tr>
</table>
<script>
	loadData();
</script>
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>

</hdiits:form>




<%
}
catch(Exception e){
	e.printStackTrace();
}
%>	