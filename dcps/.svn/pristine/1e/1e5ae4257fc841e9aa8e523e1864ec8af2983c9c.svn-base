<%
try {
%>

<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%@page import="java.util.Map"%>

<script type="text/javascript" src="<c:url value="/script/common/base64.js"/>">
</script>

<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>

<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>

<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>

<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
	
<fmt:setBundle basename="resources.hr.transfer.transferLabels"  var="transferLables" scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="TransferVO" value="${resValue.transfer}" />
<c:set var="PostingHistory" value="${resValue.PostingHistory}"></c:set>
<c:set var="rsn" value="${resValue.ReasonForTransfer}" />
<c:set var="apploc" value="${resValue.apploc}" />
<c:set var="statusFlag" value="${resValue.statusFlag}" />


<script type="text/javascript">





function addPostingDataInTable(tableId,dispFieldA)
	{
		document.getElementById(tableId).style.display='';
	
		var trow=document.getElementById(tableId).insertRow();
		trow.align="center";
		trow.insertCell(0).innerHTML = dispFieldA[0];
		
		trow.insertCell(1).innerHTML = dispFieldA[1];	
		trow.insertCell(2).innerHTML = dispFieldA[2];	
		trow.insertCell(3).innerHTML = dispFieldA[3];
		trow.insertCell(4).innerHTML = dispFieldA[4];
		trow.insertCell(5).innerHTML = dispFieldA[5];
		}
</script>
<hdiits:form name="listforTransReqByEmp" validate="true" method="post" action="./hrms.htm?actionFlag=onReqIntimateSetTrnLoc" encType="text/form-data">


	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1">
		 <hdiits:caption captionid="trn.header" bundle="${transferLables}" captionLang="single"/></a></li>
	</ul>
	</div>
	<div class="tabcontentstyle" style="height: 100%">
	<div id="tcontent1" class="tabcontent" tabno="0"><br>
	
	
<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>

<hdiits:fieldGroup titleCaptionId="trn.prvPstDt" bundle="${transferLables}"   > 
	<br>
	

	<table width="100%" border="1" name="prevPostDtl" id="prevPostDtl">
		<tr class="datatableheader">
			<td width="20%"><hdiits:caption captionid="trn.pstNm"
				bundle="${transferLables}"></hdiits:caption></td>
			<td width="20%"><hdiits:caption captionid="trn.locType"
				bundle="${transferLables}"></hdiits:caption></td>
			<td width="20%"><hdiits:caption captionid="trn.trnLoc"
				bundle="${transferLables}"></hdiits:caption></td>

			<td width="10%"><hdiits:caption captionid="trn.frmDt"
				bundle="${transferLables}"></hdiits:caption></td>
			<td width="10%"><hdiits:caption captionid="trn.toDt"
				bundle="${transferLables}"></hdiits:caption></td>
			<td width="20%"><hdiits:caption captionid="trn.period"
				bundle="${transferLables}"></hdiits:caption></td>
		</tr>
	</table>
<br>
	<br>
	

	</hdiits:fieldGroup>
	
	<br>
	<hdiits:fieldGroup titleCaptionId="trn.lstoflocbyemp" bundle="${transferLables}"  mandatory="true" > 
	
	<table width="100%" id="ListofEmpForTransReq" name="ListofEmpForTransReq" border="1">
		<tr class="datatableheader">

			
			<td width="40%"><hdiits:caption captionid="trn.locto"
				bundle="${transferLables}"></hdiits:caption></td>
			<td width="40%"><hdiits:caption captionid="trn.locType"
				bundle="${transferLables}"></hdiits:caption></td>
			<td width="5%"><hdiits:caption captionid="trn.prty"
				bundle="${transferLables}"></hdiits:caption></td>
				<td width="15%"><hdiits:caption captionid="trn.reqDate"
				bundle="${transferLables}"></hdiits:caption></td>
			
		</tr>



	</table>
	
	<br>
	
	<br>
	<br>
	<table >
		<tr>
			<td width="25%"><hdiits:caption captionid="trn.rsnOfTrs"
				bundle="${transferLables}"></hdiits:caption></td>
			<td width="25%"><hdiits:textarea name="rsnOfTrs"
				default="${rsn}" captionid="trn.rsnOfTrs" readonly="true"
				bundle="${transferLables}" ></hdiits:textarea></td>
			
		</tr>
		<tr  style="display:none" id="apprdetails">
			<td width="25%"><hdiits:caption captionid="trn.approvedloc"
				bundle="${transferLables}"></hdiits:caption></td>
			<td width="25%">${apploc}</td>
			
		</tr>
	</table>
	
	
	</hdiits:fieldGroup>
	



<c:if test="${not empty TransferVO}">
		<c:forEach items="${TransferVO}" var="TransferVO" varStatus="x">
			
			<c:set var="transfertoLoc" value="${TransferVO.locName}" />
			<c:set var="priority" value="${TransferVO.priority}" />
			<c:set var="deptName" value="${TransferVO.depName}" />
			
			
			<fmt:formatDate var="reqDate" value="${TransferVO.transferDate}" pattern="dd/MM/yyyy"/>
			
			<script type="text/javascript">
			
			      							
	 				//document.formTransfer.reqTransferDate.value=dtarray[0];	
			var j=0;
				    	
			 var trow= document.getElementById('ListofEmpForTransReq').insertRow();
				    	 
			
			 var cell0=trow.insertCell(j);
			cell0.align="center";				
			cell0.innerHTML='${transfertoLoc}';
			var cell1=trow.insertCell(j+1);
			cell1.align="center";
			cell1.innerHTML='${deptName}';
			var cell2=trow.insertCell(j+2);
			cell2.align="center";
	   		cell2.innerHTML='${priority}';
	   		var cell3=trow.insertCell(j+3);
			cell3.align="center";            
	   		cell3.innerHTML="${reqDate}";
	   										
	   		
	   										
	   	j=0;
	   										
	  if('${statusFlag}'=="2")
	  {
	  document.getElementById('apprdetails').style.display='';
	  }
	   		
	   								
	   										
	   									
</script>
		</c:forEach>

	</c:if>
	
<c:if test="${not empty PostingHistory}">
	<c:forEach items="${PostingHistory}" var="PostingHistory" varStatus="x">



<c:set var="dsgnName" value="${PostingHistory.postName}"/>
<c:set var="depName" value="${PostingHistory.depName}"/>
<c:set var="locName" value="${PostingHistory.locName}"/>
<c:set var="frmDate" value="${PostingHistory.fromDate}"/>
<c:set var="toDate" value="${PostingHistory.toDate}"/>
<c:set var="period" value="${PostingHistory.period}"/>
<script type="text/javascript">

var displayFieldA  = new Array('${dsgnName}','${depName}','${locName}','${frmDate}','${toDate}','${period}');

addPostingDataInTable('prevPostDtl',displayFieldA);
</script>
</c:forEach>
	
	</c:if>
	
	
	
	
	
	</div>
	</div>
	
	
	<script type="text/javascript">		
		initializetabcontent("maintab")
		
		
	</script>
	
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
