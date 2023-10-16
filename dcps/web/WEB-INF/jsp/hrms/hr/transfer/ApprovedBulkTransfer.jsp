

<%
try {
%>

<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script type="text/javascript" src="<c:url value="/script/common/base64.js"/>">
</script>

       
<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>


<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>

<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>

<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>

<fmt:setBundle basename="resources.hr.transfer.transferLabels" var="transferLables" scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="transType" value="${resValue.transferType}"></c:set>
<c:set var="fileid" value="${resValue.fileId}"/>

<c:set var="xmlFileName" value="${resValue.xmlFileName}" />

<c:set var="TransferVO" value="${resValue.TransferVO}" />
<c:set var="level1" value="${resValue.level1}"> </c:set>
<c:set var="idforlevel1" value="${resValue.idforlevel1}"> </c:set>
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="trn.tranRequest" bundle="${transferLables}" captionLang="single"></hdiits:caption>
		 </a></li>
	</ul>
	</div>
	<div class="tabcontentstyle" style="height: 100%">
	<div id="tcontent1" class="tabcontent" tabno="0">
<hdiits:form name="AppBulkTransfer" validate="true" method="post" action="" encType="text/form-data">					    	 				
<script type="text/javascript">
var counter=0;
	function addDBDataInTableAftTransfer(tableId,hiddenField,dispFieldA)
	{
		//	alert ('addDBDataInTable called....');
		
		document.getElementById(tableId).style.display='';
		
		
		
		var trow=document.getElementById(tableId).insertRow();
		trow.id = 'row' + hiddenField + counter;
		var i=0;
	
	
		//trow.insertCell(i+1).innerHTML = dispFieldA[i];
		
		trow.insertCell(i).innerHTML = dispFieldA[i];		
		trow.insertCell(i+1).innerHTML = dispFieldA[i+1];	
		trow.insertCell(i+2).innerHTML = dispFieldA[i+2];	
		trow.insertCell(i+3).innerHTML = dispFieldA[i+3];	
		trow.insertCell(i+4).innerHTML = dispFieldA[i+4];	
		trow.insertCell(i+5).innerHTML = dispFieldA[i+5];	
		//trow.insertCell(i+6).innerHTML = dispFieldA[i+6];

	var end=dispFieldA[i+6].split('^');
	var no=eval(end.length)/eval(3);
	var f=0;
	 var td1=document.createElement("td");
	  td1.cellpadding="0";
	   td1.cellspacing="0";
	   
	   var y=document.createElement("table");
	   y.width="100%";
	   y.border="1";
	   y.cellpadding="0";
	   y.cellspacing="0";
	   										
	   y.className="TableBorderLTRBN";
	   for(var b=0;b<no;b++)
	   	{
	   										
	   	var tr=document.createElement("tr");
	   										
	   	var td12=document.createElement("td");
	   td12.width="40%";
	   	td12.align="center";
	   	td12.innerHTML=end[f];
	   	f=f+1;
	   										
	   var td2=document.createElement("td");
	   	td2.width="30%";
	   	td2.align="center";
	   	td2.innerHTML=end[f];
	   f=f+1;
	   										
	   	var td3=document.createElement("td");
	   	td3.align="center";
	   	td3.width="30%";
	   	td3.innerHTML=end[f];
	   	f=f+1;
	   	tr.appendChild(td12);
	   	tr.appendChild(td2);
	   	tr.appendChild(td3);
	   	y.appendChild(tr);
	   	}
	   									
	   td1.appendChild(y);
	   trow.insertCell(i+6).innerHTML =td1.innerHTML;
	   										
	   	f=0;
			
		//trow.insertCell(i+8).innerHTML = dispFieldA[i+7];
		trow.insertCell(i+7).innerHTML = dispFieldA[i+7];
		trow.insertCell(i+8).innerHTML = dispFieldA[i+8];
		trow.insertCell(i+9).innerHTML = dispFieldA[i+9];
		
			
		
		counter++;
		
		
	}
	</script>
	
	
		<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" />
		<hdiits:hidden name="fileId" id="fileId" default="${fileid}"/>
		<hdiits:hidden name="serialno" id="serialno" default=""/>
		
	

	<hdiits:fieldGroup titleCaptionId="trn.lstOfTrnEmp" bundle="${transferLables}"  > 
	<br>
	
	
	<div id="pageNavPosition"></div>
	<table width="100%" id="AfterTransEmpDt" name="AfterTransEmpDt"
		border="1" cellpadding="0" cellspacing="0"  class="TableBorderLTRBN" style="display: none">
		<tr class="datatableheader" id='Heading'>

			
			<td width="10%">
			   <hdiits:caption captionid="trn.empNm" bundle="${transferLables}" captionLang="single"></hdiits:caption>
			</td>
			<td width="10%">
			   <hdiits:caption captionid="trn.designation" bundle="${transferLables}" captionLang="single"></hdiits:caption>
			</td>
			<td width="10%">
			    <hdiits:caption captionid="trn.prstpostplac" bundle="${transferLables}" captionLang="single"></hdiits:caption>
			</td>
			<td width="5%">
			    <hdiits:caption captionid="trn.dateposting"	bundle="${transferLables}" captionLang="single"></hdiits:caption>
			</td>
			<td width="5%">
			    <hdiits:caption captionid="trn.nativeplace" bundle="${transferLables}" captionLang="single"></hdiits:caption>
			</td>
			<td width="5%">
			    <hdiits:caption captionid="trn.dtRetire" bundle="${transferLables}" captionLang="single"></hdiits:caption>
			</td>
			<td width="20%">
			    <table width="100%" border="1" cellpadding="0" cellspacing="0"  class="TableBorderLTRBN">
			    <tr >
			        <td colspan="3" width="100%"><hdiits:caption captionid="trn.posthistory" bundle="${transferLables}" captionLang="single"></hdiits:caption></td>
			    </tr>
			    <tr >
			        <td colspan="1" align="left" width="40%"><hdiits:caption captionid="trn.postplace" bundle="${transferLables}" captionLang="single"></hdiits:caption></td>
			        <td colspan="1" align="left" width="30%"><hdiits:caption captionid="trn.frmdate" bundle="${transferLables}" captionLang="single"></hdiits:caption></td>
			        <td colspan="1" align="left" width="30%"><hdiits:caption captionid="trn.todate" bundle="${transferLables}" captionLang="single"></hdiits:caption></td>
			    </tr>
			    </table>
			</td>
			<td width="10%"><hdiits:caption captionid="trn.locto"
				bundle="${transferLables}" captionLang="single"></hdiits:caption></td>
			<td width="10%"><hdiits:caption captionid="trn.trnType"
				bundle="${transferLables}" captionLang="single"></hdiits:caption></td>
	  		<td width="5%">
			    <hdiits:caption captionid="trn.transferDate" bundle="${transferLables}" captionLang="single"></hdiits:caption>
			</td>
			
		</tr>
	
	
	</table>
	</hdiits:fieldGroup>
	
	<c:if test="${not empty TransferVO}">
	<c:forEach items="${TransferVO}" var="TransferVO" varStatus="x">




<c:set var="name" value="${TransferVO.empfName}"/>
<c:set var="desig" value="${TransferVO.dsgnName}"/>
<c:set var="currLocation" value="${TransferVO.currentLocation}"/>
<fmt:formatDate var="dop" value="${TransferVO.dateofposting}" pattern="dd/MM/yyyy"/>
<c:set var="nativeplace" value="${TransferVO.nativePlace}"/>

<fmt:formatDate var="dor" value="${TransferVO.dateR}" pattern="dd/MM/yyyy"/>
<c:set var="postihistory" value="${TransferVO.postinghistory}"/>
<c:set var="transfertoLoc" value="${TransferVO.locName}"/>
<c:set var="transferType" value="${TransferVO.transTypelookupName}"/>
<fmt:formatDate var="transDate" value="${TransferVO.transferDate}" pattern="dd/MM/yyyy"/>
<script type="text/javascript">

 

var displayFieldA  = new Array('${name}','${desig}','${currLocation}','${dop}','${nativeplace}','${dor}','${postihistory}','${transfertoLoc}','${transferType}','${transDate}');

addDBDataInTableAftTransfer('AfterTransEmpDt','ApprovedTransferPunch',displayFieldA);
</script>
</c:forEach>
	
	</c:if>
			</div>
			
	
	
	</div>
	

	<script type="text/javascript">		
		initializetabcontent("maintab")
	</script>

	<hdiits:validate controlNames="text" locale='<%=(String)session.getAttribute("locale")%>' />

</hdiits:form>
 

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
