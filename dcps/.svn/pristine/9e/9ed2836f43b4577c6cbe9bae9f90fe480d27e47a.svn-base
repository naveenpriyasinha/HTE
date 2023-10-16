<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<script type="text/JavaScript">

function checkUncheckAll(theElement)
{
	var theForm = theElement.form, z = 0;	
	 for(z=0; z<theForm.length;z++)
	 {		 
	      if(theForm[z].type == 'checkbox' && theForm[z].name != 'SelectAll')
		  {
			  theForm[z].checked = theElement.checked;
		  }
     }   
}	
function generateOrder()
{
	var flag = 0;
	var id;	
	var tranID;	
	var reqType;
	var gpfAccNo;
	var sancAmount;
	var empName;
	var joiningDate;
	var superAnnDate;
	var basicSalary;	
	var orderNo;
	var totalRequests= document.getElementById("totalCount").value ;
	
	for(var k=1;k<totalRequests;k++)
	{
		if(document.getElementById("chkRequest"+k).checked )
		{
			tranID = document.getElementById("hidTranId"+k).value;
			reqType = document.getElementById("hidReqType"+k).value;
			gpfAccNo = document.getElementById("hidGpfAccNo"+k).value;
			sancAmount = document.getElementById("hidAmountSanctioned"+k).value;
			empName = document.getElementById("hidEmpName"+k).value;				
			joiningDate = document.getElementById("hidAppDate"+k).value;
			superAnnDate = document.getElementById("hidAppDate"+k).value;
			basicSalary = document.getElementById("hidBasicPay"+k).value;
			orderNo = document.getElementById("hidOrderNo"+k).value;
			flag++;
		}
	}
	
	if(flag == 0)
	{
		alert('Please select at least one request');
		return;
	}
	else if(flag>1)
	{
		alert('Please Select Only One Request at a time');
		return;
	}
	else if(flag==1)
	{
		url = "ifms.htm?actionFlag=reportService&reportCode=800007&action=generateReport&reqType="+reqType+"&gpfAccNo="+gpfAccNo+
		"&sancAmount="+sancAmount+"&empName="+empName+"&basicSalary="+basicSalary+"&orderNo="+orderNo+"&joiningDate="+joiningDate+"&superAnnDate="+superAnnDate+"&asPopup=TRUE";
		window_new_update(url);
	}
}

function window_new_update(url)
{
	var newWindow = null;
   	var height = screen.height - 150;
   	var width = screen.width;
   	var urlstring = url;
   	var urlstyle = "height="+height+",width="+width+",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
   	newWindow = window.open(urlstring, "GenerateOrder", urlstyle);
}

</script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="ApprovedList" value="${resValue.lnaApprovedList}"></c:set>
<c:set var="counter" value="0"></c:set>
<fmt:setBundle basename="resources.lna.lnaLabels" var="lnaLabels" scope="request" />

<hdiits:form name="frmLNAApprovedReq" encType="multipart/form-data"  validate="true" method="post">
<fieldset class="tabstyle" ><legend><fmt:message key="CMN.APPROVEDREQ" bundle="${lnaLabels}" ></fmt:message></legend>
<display:table list="${ApprovedList}"  id="vo" requestURI="" pagesize="10" style="width:100%" size="${resValue.totalRecords}" sort="list" defaultorder="descending" cellpadding="5">

		<display:setProperty name="paging.banner.placement" value="bottom" />
		
		<display:column style="height:35;text-align: left;" class="oddcentre" titleKey="CMN.TID" sortable="true" headerClass="datatableheader" value="${vo[0]}">			
		</display:column>
		
		<display:column style="height:35;text-align: left;" class="oddcentre" titleKey="CMN.APPDATE" headerClass="datatableheader" >
		<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[1]}" var="appDate"/>
		<c:out value="${appDate}"></c:out> 
		<input type="hidden" name="hidAppDate${counter}" id="hidAppDate${counter}" value="${appDate}"/>
		</display:column>
		
		<display:column style="text-align: left;" titleKey="CMN.EMPLOYEECODE" class="oddcentre" headerClass="datatableheader" value="${vo[2]}"> 
			<input type="hidden" id="hidSevaarthId${counter}" name="hidSevaarthId${counter}" value="${vo[2]}" />
		</display:column>
		
		<display:column style="text-align: left;" titleKey="CMN.EMPNAME" class="oddcentre" headerClass="datatableheader" >
		<c:out value="${vo[3]}"></c:out>
		<input type="hidden" name="hidEmpName${counter}" id="hidEmpName${counter}" value="${vo[3]}"/>
		</display:column>

		<display:column style="text-align: left;" titleKey="CMN.REQTYPE" class="oddcentre" headerClass="datatableheader">
			<c:if test="${vo[5] == '800028'}"><c:out value="Computer Advance"></c:out></c:if>
			<c:if test="${vo[5] == '800029'}"><c:out value="House Building Advance"></c:out></c:if>
		    <c:if test="${vo[5] == '800030'}"><c:out value="Vehicle Advance"></c:out></c:if>
		    <input type="hidden" name="hidReqType${counter}" id="hidReqType${counter}" value="${vo[5]}"/> 		
		 </display:column>	
		 <display:column style="text-align: left;" titleKey="CMN.SUBTYPE" class="oddcentre" headerClass="datatableheader" value="${vo[6]}">
		 </display:column>
		
		<display:column style="text-align: left;" titleKey="CMN.SANCAMT" class="oddcentre" headerClass="datatableheader" >
		<c:out value="${vo[4]}"></c:out>
		
		</display:column>
		
		<display:column style="text-align:center" class="oddcentre" title= "<input type='checkbox' name='SelectAll' onclick='checkUncheckAll(this)'/>" headerClass="datatableheader">
			<input type="checkbox" name="chkRequest${counter}" id="chkRequest${counter}" value="${vo[0]}"/>			
		</display:column>
		
		<c:set var="counter" value="${counter+1}"></c:set>
</display:table>
<input type="hidden" id="totalCount" name="totalCount" value="${counter}"/>
</fieldset>
<br>
<br>

<center><hdiits:button type="button" captionid="BTN.GENERATEORDER" bundle="${lnaLabels}" id="btnGenerateOrder" name="btnGenerateOrder" onclick="" style="width:180px"></hdiits:button></center>
</hdiits:form>