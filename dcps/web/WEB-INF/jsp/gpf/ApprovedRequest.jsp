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
	var arrChkBox = document.getElementsByName("chkRequest");
	var currRecord = "";
	
	for(var k=0;k<arrChkBox.length;k++)
	{
		if(arrChkBox[k].checked == true)
		{
			currRecord = (arrChkBox[k].id.split("_"))[1];
			tranID = document.getElementById("hidTranId"+currRecord).value;
			reqType = document.getElementById("hidReqType"+currRecord).value;
			gpfAccNo = document.getElementById("hidGpfAccNo"+currRecord).value;
			sancAmount = document.getElementById("hidAmountSanctioned"+currRecord).value;
			empName = document.getElementById("hidEmpName"+currRecord).value;				
			joiningDate = document.getElementById("hidAppDate"+currRecord).value;
			superAnnDate = document.getElementById("hidAppDate"+currRecord).value;
			basicSalary = document.getElementById("hidBasicPay"+currRecord).value;
			orderNo = document.getElementById("hidOrderNo"+currRecord).value;
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
<c:set var="ApprovedList" value="${resValue.gpfApprovedList}"></c:set>
<c:set var="counter" value="1"></c:set>
<fmt:setBundle basename="resources.gpf.gpfLabels" var="gpfLables" scope="request" />

<hdiits:form name="frmGPFApprovedReq" encType="multipart/form-data"  validate="true" method="post">
<fieldset class="tabstyle" ><legend><fmt:message key="CMN.APPROVEDREQ" bundle="${gpfLables}" ></fmt:message></legend>
<display:table list="${ApprovedList}"  id="vo" requestURI="" pagesize="10" style="width:100%" size="${resValue.totalRecords}" sort="list" defaultsort="1" defaultorder="descending" cellpadding="5">

		<display:setProperty name="paging.banner.placement" value="bottom" />
		
		<display:column style="height:35;text-align: left;" class="oddcentre" titleKey="CMN.TID" headerClass="datatableheader" value="${vo[0]}">			
		</display:column>

		<display:column style="text-align: left;" titleKey="CMN.REQTYPE" class="oddcentre" headerClass="datatableheader">
			<c:if test="${vo[5] == 'CS'}"><c:out value="Change Subscription"></c:out>
			<input type="hidden" name="hidReqType${counter}" id="hidReqType${counter}" value="Change Subscription"/>
			</c:if>
			<c:if test="${vo[5] == 'RA'}"><c:out value="Refundable Advance"></c:out>
			<input type="hidden" name="hidReqType${counter}" id="hidReqType${counter}" value="Refundable"/>
			</c:if>
		    <c:if test="${vo[5] == 'NRA'}"><c:out value="Non-Refundable Advance"></c:out>
		    <input type="hidden" name="hidReqType${counter}" id="hidReqType${counter}" value="Non-Refundable"/>
		    </c:if> 		
		    <c:if test="${vo[5] == 'FW'}"><c:out value="Final Payment"></c:out>
		    <input type="hidden" name="hidReqType${counter}" id="hidReqType${counter}" value="Final"/>
		    </c:if> 	 
		</display:column>
				
		<display:column style="height:35;text-align: left;" class="oddcentre" titleKey="CMN.APPDATE" headerClass="datatableheader" >
		<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[1]}" var="appDate"/>
		<c:out value="${appDate}"></c:out> 
		<input type="hidden" name="hidAppDate${counter}" id="hidAppDate${counter}" value="${appDate}"/>
		</display:column>
		
		<display:column style="text-align: left;" titleKey="CMN.SEVAARTHID" class="oddcentre" headerClass="datatableheader" value="${vo[2]}"> 
			<input type="hidden" id="hidSevaarthId${counter}" name="hidSevaarthId${counter}" value="${vo[2]}" />
		</display:column>
		
		<display:column style="text-align: left;" titleKey="CMN.SUBSNAME" class="oddcentre" headerClass="datatableheader" >
		<c:out value="${vo[3]}"></c:out>
		<input type="hidden" name="hidEmpName${counter}" id="hidEmpName${counter}" value="${vo[3]}"/>
		</display:column>

		<display:column style="text-align: left;" titleKey="CMN.GPFACCNO" class="oddcentre" headerClass="datatableheader" >
		<c:out value="${vo[4]}"></c:out>
		 <input type="hidden" name="hidGpfAccNo${counter}" id="hidGpfAccNo${counter}" value="${vo[4]}"/>
		 <input type="hidden" name="hidOrderNo${counter}" id="hidOrderNo${counter}" value="${vo[8]}"/>
		</display:column>
		
		<display:column style="text-align:center" class="oddcentre" title= "<input type='checkbox' name='SelectAll' onclick='checkUncheckAll(this)'/>" headerClass="datatableheader">
			<input type="checkbox" name="chkRequest" id="chkRequest_${counter}" value="${vo[0]}"/>
			<input type="hidden" id="hidTranId${counter}" name="hidTranId${counter}" value="${vo[0]}" />
			<input type="hidden" name="hidBasicPay${counter}" id="hidBasicPay${counter}" value="${vo[6]}"/>
			<input type="hidden" name="hidAmountSanctioned${counter}" id="hidAmountSanctioned${counter}" value="${vo[7]}"/>			
		</display:column>
		
		<c:set var="counter" value="${counter+1}"></c:set>
</display:table>
<input type="hidden" id="totalCount" name="totalCount" value="${counter}"/>
</fieldset>
<br>
<br>

<center><hdiits:button type="button" captionid="BTN.GENERATEORDER" bundle="${gpfLables}" id="btnGenerateOrder" name="btnGenerateOrder" onclick="generateOrder();" style="width:180px"></hdiits:button></center>
</hdiits:form>