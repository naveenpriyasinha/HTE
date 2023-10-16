
<%
try {
%>

<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="cmnCancellationlist"
	value="${resultValue.CmnCancellationlist}">
</c:set>
<c:set var="reqType" value="${resultValue.reqType}">
</c:set>
<fmt:setBundle basename="resources.common.cancellation.CommonCancellation" var="CmnCancel" scope="request"/>	
<script language="JavaScript1.2">

function disabletext(e){
return false
}

function reEnable(){
return true
}

//if the browser is IE4+
document.onselectstart=new Function ("return false")

//if the browser is NS6
if (window.sidebar){
document.onmousedown=disabletext
document.onclick=reEnable
}


</script>
<script>
function openPopup(actionFlag,docType,jobId){

var href=null;
if(docType==2)
{
 href='./hrms.htm?actionFlag='+actionFlag+"&corrId="+jobId;
}
else if (docType==3){
href='./hrms.htm?actionFlag='+actionFlag+"&fileId="+jobId;
}

window.open(href,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
}



 function submitcanc()
      {
     
      method="POST";
	var i= document.getElementById("cancelIndex").value;
	 var jobId=0;
	 var reason="0";
	 if('${reqType}'=='ApproveCancel')
	 {
	 	if(document.getElementById('rsnReason')!=null){
	 	 dodacheck(document.getElementById('rsnReason'));
				 reason=document.getElementById('rsnReason').value.trim();
			 	if(reason.length==0)
			 	{
					 alert('<fmt:message bundle="${CmnCancel}" key="Canalrt.rsnCancell"/>');
			 		return false;
				 } 
			}
	 }
	 for(var j=0;j<i;j++){
	 
	 if(document.getElementById("checkJobID"+j) != null){
	 	 	if(document.getElementById("checkJobID"+j).checked){
	 	      jobId=document.getElementById("checkJobID"+j).value;
	  			parent.window.submitPage(jobId,reason);
		 	}
		 }
	 }
	 if(jobId==0)
	 {
	 alert('<fmt:message bundle="${CmnCancel}" key="Canalrt.selJob"/>');
	 }
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
</script>
<style type="text/css">
.tablecell{
   border-bottom: solid 1px #DEDEDE;
	border:1;
	border-color:black;
	font-size: 11px;
	font-style: normal;
	font-weight: normal;
	background: white;
	text-align: left;
	padding: 1px;
	
}
.tablerow{
  border-top: solid 1px #333333;
	border-left: solid 1px #666666;
	border-right: solid 0px #888888;
	border-bottom: solid 1px #666666; 
	font-size: 11px;
	font-style: normal;
	font-weight: bold;
	text-align: center;
	background: #C9DFFF;
	color: black;
	padding: 1px;
	
	
}

</style>


<hdiits:form name="CancellationRecord" validate="true"
	encType="text/form-data" method="POST">
	<c:set var="i" value="0" />

<c:if test="${not empty cmnCancellationlist}">
	<display:table id="CancRecord" export="true" style="width:100%"
		requestURI="" defaultsort="2" defaultorder="descending"
		name="${cmnCancellationlist}" pagesize="5"
		decorator="com.tcs.sgv.common.cancellation.decorator.CancellationDecorator">
		<display:column class="tablecell" headerClass="tablerow"
			style="text-align: center">
			<hdiits:radio name="checkJobID" id="checkJobID${i}"
				value="${CancRecord.jobSeqId}" />
		</display:column>
		<display:column property="link2" class="tablecell" media="html"
			 style="text-align: center;" headerClass="tablerow" titleKey="Can.reqno" ></display:column>
		<display:column class="tablecell" 
			headerClass="tablerow" value="${CancRecord.appDate}"
			style="text-align: center" titleKey="Can.appdate">
		</display:column>
		<display:column class="tablecell" 
			headerClass="tablerow" value="${CancRecord.status}"
			style="text-align: center" titleKey="Can.Status">
		</display:column>
		<display:column class="tablecell" 
			headerClass="tablerow" value="${CancRecord.empName}"
			style="text-align: center" titleKey="Can.penappbyemp">
		</display:column>
		<c:set var="i" value="${i+1}" />

	</display:table>

	<hdiits:hidden name="cancelIndex" id="cancelIndex" default="${i}" />
	

		<table width="100%" align="center">
			<c:if test="${reqType eq 'ApproveCancel'}">
				<tr id="Reason">
					<td align="center" width="25%"><hdiits:textarea
						name="rsnReason" id="rsnReason" maxlength="50" onkeypress="dodacheck(this)" mandatory="true"/></td>
				</tr>
			</c:if>

			<tr>
				<td align="center"><hdiits:button name="applycanc"
					type="button" captionid="Can.appcan" onclick="submitcanc()"
					id="applycanc" bundle="${CmnCancel}"/></td>
			</tr>
		</table>
	</c:if>
	
	<c:if test="${ empty cmnCancellationlist}">
	<table width="100%" align="center" 	style="margin: 5px 0px;  font-family: arial; color: #333333;border: solid 1px #6B2700; width: 50%; clear: center;">
	<tr>
		<td align="center">
		<hdiits:caption captionid="Can.recntfnd" bundle="${CmnCancel}"/>
		</td>
	</tr>
		

</table>
</c:if>
</hdiits:form>



<script type="text/javascript">


</script>

<%
	}

	catch (Exception e) {
		e.printStackTrace();
	}
%>
