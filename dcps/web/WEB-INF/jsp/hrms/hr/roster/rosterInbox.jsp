
<%try{%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="resources.hr.roster.rosterMst" var="caption" scope="request" />
<script type="text/javascript" src="common/script/tagLibValidation.js">
</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<script type="text/javascript" src="script/common/commonfunctions.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/hrms/hr/rosterScript/rosterInbox.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
	
<c:set var="resValue" value="${result.resultValue}"></c:set>	
<c:set var="rosterdtl" value="${resValue.rosterlist}"></c:set>	
<c:set var="empcategory" value="${resValue.category}"></c:set>	
<c:set var="locId" value="${resValue.locId}"></c:set>	
<c:set var="departId" value="${resValue.departId}"></c:set>	
<c:set var="pkVal" value="${resValue.pkVal}"></c:set>	
<c:set var="dsgnId" value="${resValue.dsgnId}"></c:set>	
<c:set var="requestId" value="${resValue.requestId}"></c:set>	
	

<script type="text/javascript">

	//gloabal Variable;
	function SearchEmp(form){
	var butName='Search'+srId;
	//document.getElementById('search').value='search'+srId;
	var n=form.name;
	srId=n.substring(6,n.length);
	
	document.getElementById('empSearchName').value='empName'+srId;
	document.getElementById('deptname').value='lblDept'+srId;
	document.getElementById('doj').value='lblDoj'+srId;
	var href='./hrms.htm?actionFlag=getRosterAllEmpSearch';
	window.open(href,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
	
}
	

</script>

<hdiits:form name="Roster" validate="true" method="POST" >
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"> <hdiits:caption captionid="Hr_recrallocation" bundle="${caption}" /> </a></li>
	</ul>
	</div>
	

 <div class="tabcontentstyle" style="height: 100%">


	<div id="tcontent1" class="tabcontent" tabno="0">
<hdiits:hidden name="empSearchName" id="empSearchName"/>
<hdiits:hidden name="deptname" id="deptname"/>
<hdiits:hidden name="doj" id="doj"/>
<hdiits:hidden name="search" id="search"/>
<hdiits:hidden name="totalLength" id="totalLength"/> 

<table border="3" bordercolor="black" id="rostertb" >

		<%-- First row for header--%>
		<tr>  
		<td valign="bottom" colspan="4" rowspan="1" ><hdiits:caption	captionid="Hr_resservedseat" bundle="${caption}"/>	</td>	
		<td valign="bottom" rowspan="2" ><hdiits:caption	captionid="Hr_lastrecruitment" bundle="${caption}"/></td>
		<td valign="bottom" rowspan="2"><hdiits:caption	captionid="Hr_serialno" bundle="${caption}"/></td>
		<td align="center" valign="bottom">	7%	</td>
		<td valign="bottom" align="center">	14%</td>
		<td valign="bottom" align="center">	27%	</td>
		<td valign="bottom" align="center">	4%</td>
		<td valign="bottom" rowspan="2"><hdiits:caption captionid="Hr_nameofseats" bundle="${caption}"/></td>
	</tr>
	
	
	<%-- Second row for header--%>
	<tr>
		<td><c:out value="SC"/></td>
		<td><c:out value="ST"/></td>
		<td><c:out value="SEBC"/></td>
		<td><c:out value="PH"/></td>		
		<td><c:out value="SC"/></td>
		<td><c:out value="ST"/></td>
		<td><c:out value="SEBC"/></td>
		<td><c:out value="PH"/></td>						
	</tr>
	
	
		<%--  	Row Data started	--%>
	
	<c:forEach var="rlist" items="${resValue.rosterlist}">
	<script>
		counterRoster=counterRoster+1;
	</script>
		<tr>
			<td>&nbsp</td>	
			<td>&nbsp</td>					
			<td>&nbsp</td>		
			<td>&nbsp</td>		
			<td>&nbsp</td>	
			<td><hdiits:hidden name="serial${rlist.rosterSrNo}" default="${rlist.rosterSrNo}"/><c:out value="${rlist.rosterSrNo}"/></td>
			<td><hdiits:hidden name="scval${rlist.rosterSrNo}" default="${rlist.calScSeat}"/><LABEL id="lscval${rlist.rosterSrNo}"/><c:out value="${rlist.calScSeat}"/></td>
			<td><hdiits:hidden name="stval${rlist.rosterSrNo}" default="${rlist.calStSeat}"/><LABEL id="lstval${rlist.rosterSrNo}"/><c:out value="${rlist.calStSeat}"/></td>
			<td><hdiits:hidden name="sebcval${rlist.rosterSrNo}" default="${rlist.calSebcSeat}"/><LABEL id="lsebcval${rlist.rosterSrNo}"/><c:out value="${rlist.calSebcSeat}"/></td>				
			<td><hdiits:hidden name="phval${rlist.rosterSrNo}" default="${rlist.calPhSeat}"/><LABEL id="lphval${rlist.rosterSrNo}"/><c:out value="${rlist.calPhSeat}"/></td>	
	
			
			<td>
			
			</td>				
	
		<script type="text/javascript">
			totalLength=totalLength+1;
		</script>
	</c:forEach>
	
		<%--  Last row for the surplus--%>

		<script type="text/javascript">
			document.getElementById('totalLength').value = totalLength;
		</script>
		

	 
	<tr>
			<td>&nbsp</td>		
			<td>&nbsp</td>
			<td>&nbsp</td>		
			<td>&nbsp</td>
			<td>&nbsp</td>		
			<td>&nbsp</td>
			<td><LABEL id="scsur1"/></td>		
				<td><LABEL id="stsur2"/></td>	
				<td><LABEL id="sebcsur3"/></td>	
				<td><LABEL id="phsur4"/></td>
				<td>&nbsp</td>	
			</tr>
	<tr>
		<td width="50%"><hdiits:caption captionid="Hr_remark" bundle="${caption}"/><hdiits:checkbox name="remarks" value="remarks" onclick="disableTextArea()"/></td>
		<td width="50%"><hdiits:textarea name="remarksTxt" id="remarksTxt" readonly="true" /></td>
	</tr>
	<tr>
			<td>
			<input name="sub" value="Approve" id="Approve" type="button" onclick="submitDtls()"/>
			</td>
			<td>
			<input type="button" name="reject" id="reject" value="Reject" onclick="rejectRoster()" >
			</td>
			<td>
			<input type="button" name="done" id="done" value="Allot" onclick="allotApplicant()" >
			</td>
	</tr>
	
		
</table>
<!-- Hidden Parameters -->
	<hdiits:hidden name="vacancyNo" id="vacancyNo" />
	<hdiits:hidden name="locId" id="locId"  default="${locId}"/>	
	<hdiits:hidden name="dsgnId" id="dsgnId" default="${dsgnId}" />	
	<hdiits:hidden name="departId" id="departId" default="${departId}" />	
	<hdiits:hidden name="pkVal" id="pkVal" default="${pkVal}" />	
	<hdiits:hidden name="RequestId" id="RequestId" default="${requestId}" />	
	<center>
	<jsp:include page="../../../core/tabnavigation.jsp" />
	</center>
	<hdiits:validate controlNames="text" locale='<%=(String)session.getAttribute("locale")%>' />
	</div>
	</div>
</hdiits:form>
<script>
document.getElementById('vacancyNo').value=counterRoster;
	alert(${requestId});

			document.getElementById('scsur1').innerHTML = parseInt(document.getElementById('lscval'+totalLength).innerHTML);
		    document.getElementById('stsur2').innerHTML = parseInt(document.getElementById('lstval'+totalLength).innerHTML);
			document.getElementById('sebcsur3').innerHTML = parseInt(document.getElementById('lsebcval'+totalLength).innerHTML);
			document.getElementById('phsur4').innerHTML = parseInt(document.getElementById('lphval'+totalLength).innerHTML);										
			
			
	initializetabcontent("maintab");
			
	
	
</script>

<%
} catch (Exception e) 
	{
		e.printStackTrace();
	}
%>