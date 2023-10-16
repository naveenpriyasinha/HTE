<%
try {
%>

<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>


<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%@page import="java.util.Map"%>



<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>

<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>

<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>

<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
	
<fmt:setBundle basename="resources.hr.transfer.transferLabels"  var="transferLables" scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="TransferVO" value="${resValue.TransferVO}" />
<c:set var="TransferVO1" value="${resValue.TransferVO}" />
<c:set var="rsn" value="${resValue.ReasonForTransfer}" />

<c:set var="fileid" value="${resValue.fileId}" />
<c:set var="PostingHistory" value="${resValue.PostingHistory}"></c:set>
<c:set var="finaldecision" value="${resValue.finaldecision}"></c:set>
<c:set var="nativePlaceName" value="${resValue.nativePlaceName}"></c:set>
<c:set var="askedNative" value="${resValue.askedNative}"></c:set>
<c:set var="displayTree" value="${resValue.displayTree}"></c:set>

<%
	ResultObject result=(ResultObject)request.getAttribute("result");
	Map resultMap=(Map)result.getResultValue();
	String setStringinSession = (String)resultMap.get("tree");
	//System.out.println("transferTree" + setStringinSession);
			 
	session.setAttribute("tree1",setStringinSession);
%>

<script type="text/javascript">

var increment=1;
var increment1=1;


function SearchEmp(){

		var href='./hrms.htm?actionFlag=getEmpSearchSelData&multiple=false';
		window.open(href,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
        
      
}

function empSearch(from){
	for(var i=0; i<from.length; i++){
			var empArray = new Array();
				empArray = from[i].split("~"); 
				 
				document.getElementById('toPostID').value=empArray[4];
				document.listforTransReqByEmp.action="hrms.htm?actionFlag=sendIntimation";
 	 			document.listforTransReqByEmp.submit();
				 }
				 }





function onloadframe()
{
hideProgressbar();
}

function checkMulti(j){

var chkBx=document.getElementsByName('serialNo');
for(var i=0;i<chkBx.length;i++)
{
	//alert(chkBx[i].value);
	
if(chkBx[i].checked=true)
{
chkBx[i].checked=false;


}

 if(chkBx[i].value==j)
{
chkBx[i].checked=true;
}
}
}

function decisionvalidation()
{
var dec1=document.getElementById('decisionRemarks1');
if(dec1!=null)
{
dodacheck(dec1);
}
var dec2=document.getElementById('decisionRemarks2');
if(dec2!=null)
{
dodacheck(dec2);
}
var dec3=document.getElementById('decisionRemarks3');
if(dec3!=null)
{
dodacheck(dec3);
}

var dec1=document.getElementById('decisionCombo1');
if(dec1!=null)
{
  if(dec1.selectedIndex==1)
  {
  if(document.listforTransReqByEmp.approvedDate1.value=="")
  {
 alert('<fmt:message bundle="${transferLables}" key="trnalert.mandAppDate"/>');
 return false;
 }
  }
  return true;
  }
  var dec2=document.getElementById('decisionCombo2');
if(dec2!=null)
{
  if(dec2.selectedIndex==1)
  {
   if(document.listforTransReqByEmp.approvedDate2.value=="")
  {
 alert('<fmt:message bundle="${transferLables}" key="trnalert.mandAppDate"/>');
 return false;
 }
  }
  return true;
  }
   var dec3=document.getElementById('decisionCombo3');
   if(dec3!=null)
   {
  if(dec3.selectedIndex==1)
  {
   if(document.listforTransReqByEmp.approvedDate3.value=="")
  {
  alert('<fmt:message bundle="${transferLables}" key="trnalert.mandAppDate"/>');
  return false;
  }
  return true;
  }
}
return true;
}


var mikExp = /[$\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\`\~\=\|]/;
function dodacheck(val) {
var strPass = val.value;
var strLength = strPass.length;
var lchar = val.value.charAt((strLength) - 1);
if(lchar.search(mikExp) != -1) {
var tst = val.value.substring(0, (strLength) - 1);
val.value = tst;
   }
}
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
	<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" /> 
	<hdiits:hidden name="fileId" id="fileId" default="${fileid}" />
	
<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
<hdiits:fieldGroup >
<table width="100%">
<tr width="100%">
<td width="25%"><hdiits:caption captionid="trn.nativePlace"
				bundle="${transferLables}" captionLang="single"/></td>
<td width="25%">${nativePlaceName}</td>
<td width="25%"></td>
<td width="25%"></td>
</tr>
</table>
</hdiits:fieldGroup>
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
	
	<table id="nativePlace">
	
	</table>
	</hdiits:fieldGroup>
	
	<br>
	<hdiits:fieldGroup titleCaptionId="trn.lstoflocbyemp" bundle="${transferLables}"  mandatory="true" > 
	
	<table width="100%" id="ListofEmpForTransReq" name="ListofEmpForTransReq" border="1">
		<tr class="datatableheader">

			<td width="5%"></td>
			<td width="15%"><hdiits:caption captionid="trn.locto"
				bundle="${transferLables}"></hdiits:caption></td>
			<td width="15%"><hdiits:caption captionid="trn.locType"
				bundle="${transferLables}"></hdiits:caption></td>
			<td width="5%"><hdiits:caption captionid="trn.prty"
				bundle="${transferLables}"></hdiits:caption></td>
				<td width="15%"><hdiits:caption captionid="trn.reqDate"
				bundle="${transferLables}"></hdiits:caption></td>
			<td width="15%"><hdiits:caption captionid="trn.urDecision"
				bundle="${transferLables}"></hdiits:caption></td>
			<td width="15%"><hdiits:caption captionid="trn.appDate"
				bundle="${transferLables}"/></td>
			<td width="15%"><hdiits:caption captionid="trn.Remark"
				bundle="${transferLables}"/></td>	
		</tr>



	</table>
	
	<br>
	
	<br>
	<br>
	<table align="left">
		<tr>
			<td width="25%"><hdiits:caption captionid="trn.rsnOfTrs"
				bundle="${transferLables}"></hdiits:caption></td>
			<td width="25%"><hdiits:textarea name="rsnOfTrs"
				default="${rsn}" captionid="trn.rsnOfTrs" readonly="true"
				bundle="${transferLables}"></hdiits:textarea></td>
			<td width="25%"></td>
			<td width="25%"></td>
		</tr>
	</table>
	
	<br>
	</hdiits:fieldGroup>
	<br>
	<br>
	<br>
<table align="center" width="100%">
<tr>
<td width="35%" >
			<div id="tree-div" style="display:none;" >
			<%@ include file="/WEB-INF/jsp/hrms/hr/transfer/transferforTree.jsp"%>
			</div>
</td>
<td width="65%"><iframe  onload="onloadframe()" scrolling="no" name="decisiontable" id="decisiontable" src="" frameborder="0" frameborder="0" width="100%"  align="left" >
				</iframe></td>

</tr>
</table>

<table width="100%" id="searchTable" >
<tr >
<hdiits:hidden name="toPostID" id="toPostID"/>

<td width="100%" align="center">
<hdiits:button name="srcEmp" type="button" captionid="trn.srchsendEmp" bundle="${transferLables}" onclick="SearchEmp()" id="srcEmp"></hdiits:button> 
</td>
</tr>

</table>

<c:if test="${not empty TransferVO}">
		<c:forEach items="${TransferVO}" var="TransferVO" varStatus="x">
			<c:set var="serialNo" value="${TransferVO.serialNo}" />
			<c:set var="transfertoLoc" value="${TransferVO.locName}" />
			<c:set var="priority" value="${TransferVO.priority}" />
			<c:set var="deptName" value="${TransferVO.depName}" />
			<c:set var="dt" value="${TransferVO.approvedDate}" />
			
			<fmt:formatDate var="reqDate" value="${TransferVO.transferDate}" pattern="dd/MM/yyyy"/>
			
			<script type="text/javascript">
			var dtArray = new Array();
											
			   dtarray= getDateAndTimeFromDateObj('${dt}');
			      							
	 				//document.formTransfer.reqTransferDate.value=dtarray[0];	
			var j=0;
				    	
			 var trow= document.getElementById('ListofEmpForTransReq').insertRow();
				    	 
			trow.insertCell(j).innerHTML = '<hdiits:checkbox id="'+${serialNo}+'" name="serialNo" value="'+${serialNo}+'"  onclick="checkMulti(this.id)"/>';
			 var cell0=trow.insertCell(j+1);
			cell0.align="center";				
			cell0.innerHTML='${transfertoLoc}';
			var cell1=trow.insertCell(j+2);
			cell1.align="center";
			cell1.innerHTML='${deptName}';
			var cell2=trow.insertCell(j+3);
			cell2.align="center";
	   		cell2.innerHTML='${priority}';
	   		var cell3=trow.insertCell(j+4);
			cell3.align="center";            
	   		cell3.innerHTML="${reqDate}";
	   										
	   		var cell4=trow.insertCell(j+5);
											
			cell4.align="center";
			var str="<select name='decisionCombo"+increment+"' class='mandatorycontrol' size='1' alertLables='drop_down' id='decisionCombo"+increment+"'  mandatory='true'  > ";
	   										
			str+="<option value='NA&"+'${serialNo}'+"'>Not Applicable</option>";
											
			str+="<option value='Y&"+'${serialNo}'+"'>Agree</option>";
			str+="<option value='N&"+'${serialNo}'+"'>Disagree</option>";
											
			str+="</select>";
			str+="<label class='mandatoryindicator'>*</label>";
	   		cell4.innerHTML+=str;
	   										
	   	   cell5=trow.insertCell(j+6);
		 cell5.align="center";
		//cell5.innerHTML="<input type='text' name='approvedDate"+increment+"'  class='mandatorycontrol'  onkeypress='return checkDateNumber()' id='approvedDate"+increment+"' class='texttag'  onBlur='departurechkdate(this)' value='"+dtarray[0]+"'  maxlength=10 size=10/>";
		//cell5.innerHTML+="<img style='cursor:hand' id='img_approvedDate"+increment+"' src=themes/defaulttheme/images/taglib/ico-calendar.gif width=20 onClick=window_open('approvedDate"+increment+"',375,570,'','approvedDate"+increment+"','Please~enter~valid~$CPTN,Departure~Date') >";
											//cell5.innerHTML+="&nbsp;&nbsp;<label class='mandatoryindicator'>*</label>";
		//cell5.innerHTML="<input type='text' name='approvedDate"+increment+"'  onkeypress='return checkDateNumber()'  class='texttag'  onBlur='return checkDate('approvedDate"+increment+"','Please enter valid $CPTN','Approved Date','0','31/12/2099','Please enter $CPTN less than 31/12/2099')' value=''  maxlength=10 size=10/>";
//cell5.innerHTML+="<img style='cursor:hand' id='img_approvedDate"+increment+"' src=themes/defaulttheme/images/taglib/ico-calendar.gif width=20 onClick=window_open('approvedDate"+increment+"',375,570,'','approvedDate"+increment+",Please~enter~valid~$CPTN,Approved Date,0,31/12/2099,Please~enter~$CPTN~less~than~31/12/2099') >";

cell5.innerHTML="<input type='text' name='approvedDate"+increment+"'  onkeypress='return checkDateNumber()'  class='texttag'  onBlur='return checkDate('approvedDate"+increment+"','Please enter valid $CPTN','Approved Date','0','31/12/2099','Please enter $CPTN less than 31/12/2099')' value='"+dtarray[0]+"'  maxlength=10 size=10 readonly='true' />";
cell5.innerHTML+="<img style='cursor:hand' id='img_approvedDate"+increment+"' src=themes/defaulttheme/images/taglib/ico-calendar.gif width=20 onClick=window_open('approvedDate"+increment+"',375,570,'','approvedDate"+increment+",Please~enter~valid~$CPTN,Pay~Fixation~Date,0,31/12/2099,Please~enter~$CPTN~less~than~31/12/2099') >";
	   	var cell6=trow.insertCell(j+7);
		cell6.align="center";
		cell6.innerHTML="<textarea rows='2' name='decisionRemarks"+increment+"' id='decisionRemarks"+increment+"'  cols='10' onkeypress='dodacheck(this)'/>";
	   	//cell6.innerHTML+="&nbsp;&nbsp;<label class='mandatoryindicator'>*</label>";
	   										
	   	j=0;
	   										
	   	increment=increment+1;
	   		
	   if('${priority}'=="1")
	   {
	   	document.getElementById(${serialNo}).checked = true;
	   }									
	   										
	   									
</script>
		</c:forEach>

	</c:if>
	<script type="text/javascript">
	
	
	if('${askedNative}'!="0")
	{
	var kedNative='${askedNative}';
	 var rownativePlace= document.getElementById('nativePlace').insertRow();
	 
	
	 rownativePlace.insertCell(0).innerHTML='<font color="red"><b> <fmt:message bundle="${transferLables}" key="trn.transferredto" />'+" "+kedNative+" "+'<fmt:message bundle="${transferLables}" key="trn.transferredtonat"/> </b></font>';
	}
	</script>
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
	
	
	<c:if test="${not empty TransferVO1}">
		<c:forEach items="${TransferVO1}" var="TransferVO1" varStatus="x">
			<c:set var="serialNo1" value="${TransferVO1.serialNo}" />
			<c:set var="decision" value="${TransferVO1.decision}" />
			<c:set var="decRemark" value="${TransferVO1.remark}" />
			
			
			<script type="text/javascript">
											
	   										
	   			var dec='decisionCombo'+increment1;
	   										
	   			var value12='${decision}'+"&"+'${serialNo1}';
	   			var rema='decisionRemarks'+increment1;
	   			document.getElementById(dec).value=value12;
	   			document.getElementById(rema).value='${decRemark}';
	   			increment1=increment1+1;
	   									
	   										
	   									
</script>
		</c:forEach>

	</c:if>
	
	<script type="text/javascript">
	
	if('${finaldecision}'=='Y')
	{
	
	
	var dec1=document.getElementById('decisionRemarks1');
if(dec1!=null)
{

dec1.disabled=true;
}
var dec2=document.getElementById('decisionRemarks2');
if(dec2!=null)
{

dec2.disabled=true;
}
var dec3=document.getElementById('decisionRemarks3');
if(dec3!=null)
{

dec3.disabled=true;
}

var com1=document.getElementById('decisionCombo1');

if(com1!=null)
{

com1.disabled=true;
}
var com2=document.getElementById('decisionCombo2');
if(com2!=null)
{

com2.disabled=true;
}
var com3=document.getElementById('decisionCombo3');
if(com3!=null)
{
com3.disabled=true;
}
var app1=document.getElementById('approvedDate1');
if(app1!=null)
{
app1.disabled=true;
}
var app2=document.getElementById('approvedDate2');
if(app2!=null)
{
app2.disabled=true;
}
var app3=document.getElementById('approvedDate3');
if(app3!=null)
{
app3.disabled=true;
}

	}
	if('${displayTree}'=="1")
	{
	document.getElementById('tree-div').style.display='';
	}
	</script>
	
	</div>
	</div>
	<hdiits:jsField jsFunction="decisionvalidation()" name="decisionvalidate"/>
	
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
