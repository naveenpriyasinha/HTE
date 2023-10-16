<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/dppf/calendarDppf.js"></script>
<script type="text/javascript" src="script/payfixation/common.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript" src="script/dppf/Common_1.0.js"></script>
<script type="text/javascript" src="script/common/attachment.js"></script>

<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request" />

<script>
function approveDCPSArrearsCase()
{
	var totalSelectedEmployees= document.getElementById("totalCount").value;
	var flag=0;
	var SixthPC_Id=" ";
	for(var i=1;i<totalSelectedEmployees;i++)
	{
		if(document.getElementById("checkbox"+i).checked == true)
		{
			SixthPC_Id= SixthPC_Id + document.getElementById("checkbox"+i).value + "~";
			flag = 1;
		}
	}
	if(flag == 1)
	{
		ApproveArrearsUsingAjax("ifms.htm?actionFlag=approveSixthPCArrears&SixthPC_Id="+SixthPC_Id);
	}
	else
	{
		alert("Please select a case to Approve!");
	}
}
function ApproveArrearsUsingAjax(url)
{
	xmlHttp=GetXmlHttpObject();

	if (xmlHttp==null)
	{
		alert ("Your browser does not support AJAX!");
		return;
	} 

	uri=url;
	
	xmlHttp.onreadystatechange=approveDataStateChanged;
	xmlHttp.open("POST",uri,false);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(uri);

}

function approveDataStateChanged() 
{ 
	if (xmlHttp.readyState==4)
	{ 
		if(xmlHttp.status==200)
		{
			alert("The Case has been Approved.");
			self.location.href="ifms.htm?actionFlag=loadSixthPCArrearsDDO&StatusFlag=A";
			
			
			
		}
	}
}

function rejectDCPSArrearsCase()
{
	
	var totalSelectedEmployees= document.getElementById("totalCount").value;
	var flag=0;
	var SixthPC_Id=" ";
	for(var i=1;i<totalSelectedEmployees;i++)
	{
		if(document.getElementById("checkbox"+i).checked == true)
		{
			SixthPC_Id= SixthPC_Id + document.getElementById("checkbox"+i).value + "~";
			flag = 1;
			var hidName = document.getElementById("hidName"+i).value;
			var hidCaseStatus = document.getElementById("hidCaseStatus"+i).value;
			if(hidCaseStatus == 'A')
			{
				alert("You Cannot Alter Any Details Of " + hidName + ".This Case has already been Approved.");
				alert("Please Select Only Those Cases which has not been approved.");
				tempFlag = 1;
				return;
			}
			else
			{
				tempFlag = 0;
			}
		}
	}
	if(flag == 1 && tempFlag == 0)
	{
		RejectArrearsUsingAjax("ifms.htm?actionFlag=rejectSixthPCArrears&SixthPC_Id="+SixthPC_Id);
	}
	else
	{
		alert("Please select a case to Reject!");
	}
}


function RejectArrearsUsingAjax(url)
{
	xmlHttp=GetXmlHttpObject();

	if (xmlHttp==null)
	{
		alert ("Your browser does not support AJAX!");
		return;
	} 

	uri=url;
	
	xmlHttp.onreadystatechange=rejectDataStateChanged;
	xmlHttp.open("POST",uri,false);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(uri);

}

function rejectDataStateChanged() 
{ 
	if (xmlHttp.readyState==4)
	{ 
		if(xmlHttp.status==200)
		{
			alert("The Case has been sent back to DDO Asst.");
			self.location.href="ifms.htm?actionFlag=loadSixthPCArrearsDDO&StatusFlag=F";
			
		}
	}
}




function saveAndfrwrdData()
{

	var ForwardToPost = document.frm6PcArrearEntry.ForwardToPost.value;
	var totalSelectedEmployees= document.getElementById("totalCount").value;
	var flag=0;
	var SixthPC_Id=" ";
	var amounts="";
	for(var i=1;i<totalSelectedEmployees;i++)
	{
		if(document.getElementById("checkbox"+i).checked == true)
		{
			SixthPC_Id= SixthPC_Id + document.getElementById("checkbox"+i).value + "~";
			amounts= amounts + document.getElementById("amount"+i).value + "~";
			fromDates= document.getElementById("FromDate").value + "~";
			toDates= document.getElementById("ToDate").value + "~"; 
			flag = 1;
		}
	}
	if(flag == 1)
	{
		
		saveAndfrwrdUsingAjax("ifms.htm?actionFlag=createandfrwrdWF&SixthPC_Id="+SixthPC_Id+"&ForwardToPost="+ForwardToPost+"&amounts="+amounts+"&fromDates="+fromDates+"&toDates="+toDates);
	}
	else
	{
		alert("Please select a case to forward!");
	}

	
}
function saveAndfrwrdUsingAjax(url)
{
	xmlHttp=GetXmlHttpObject();

	if (xmlHttp==null)
	{
		alert ("Your browser does not support AJAX!");
		return;
	} 

	uri=url;
	
	xmlHttp.onreadystatechange=saveAndfrwrdStateChanged;
	xmlHttp.open("POST",uri,false);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(uri);

}

function saveAndfrwrdStateChanged() 
{ 
	if (xmlHttp.readyState==4)
	{ 
		if(xmlHttp.status==200)
		{
			alert("The Case has been forwarded to DDO Approver.");
			self.location.reload();
		}
	}
}

function checkUncheckAll()
{						
	 var theElement = document.getElementsByName("SelectAll");
	 for(var z=0; z<theElement.length;z++)
	 {		 
		if(!(theElement[z].checked))
		 theElement[z].checked = true;
		else
		 theElement[z].checked = false;
	 }
	     
}	
function saveData()
{
	var totalSelectedEmployees= document.getElementById("totalCount").value;
	var dcpsSixPCIds="";
	var amounts="";
	var flag=0;
	var fromDates ="";
	var toDates ="";
	var url;
		for(var i=1;i<totalSelectedEmployees;i++)
		{
			if(document.getElementById("checkbox"+i).checked)
			{
				dcpsSixPCIds= dcpsSixPCIds + document.getElementById("checkbox"+i).value + "~";
				amounts= amounts + document.getElementById("amount"+i).value + "~";
				fromDates= document.getElementById("FromDate").value + "~";
				toDates= document.getElementById("ToDate").value + "~"; 
			
				flag = 1;
			}
		}
	
	
		xmlHttp = GetXmlHttpObject();
		if(xmlHttp == null)
		{
		
		return;
		}

		if(flag == 1)
		{
			url = 'ifms.htm?actionFlag=saveSixPCArrears&dcpsSixPCIds='+dcpsSixPCIds+'&amounts='+amounts+'&fromDates='+fromDates+'&toDates='+toDates;
		}
		else
		{
			alert('Please Select Any Case!');
		}

		
		xmlHttp.onreadystatechange = function()
		{
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					XMLDoc = xmlHttp.responseXML.documentElement;
					var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');

						var successFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
												
						if (successFlag=='true') {
								alert('Amounts Deposited successfully');
								self.location.href = 'ifms.htm?actionFlag=sixthPCArrearsEntry&StatusFlag=R';
						}
					}
				}
		}
xmlHttp.open("POST",url,true);
xmlHttp.send(url);
}
function saveDataDDO()
{
	
	var totalSelectedEmployees= document.getElementById("totalCount").value;
	var dcpsSixPCIds="";
	var amounts="";
	var tempFlag = 0;
	var flag=0;
	var url;


	
	
		for(var i=1;i<totalSelectedEmployees;i++)
		{
			if(document.getElementById("checkbox"+i).checked)
			{
				
				dcpsSixPCIds= dcpsSixPCIds + document.getElementById("checkbox"+i).value + "~";
				amounts= amounts + document.getElementById("amount"+i).value + "~";
				flag = 1;
				
				var hidName = document.getElementById("hidName"+i).value;
				var hidCaseStatus = document.getElementById("hidCaseStatus"+i).value;
				if(hidCaseStatus == 'A')
				{
					alert("You Cannot Alter Any Details Of " + hidName + ".This Case has already been Approved.");
					alert("Please Select Only Those Cases which has not been approved.");
					tempFlag = 1;
					return;
				}
				else
				{
					tempFlag = 0;
				}
			
			}
	
		}
	
	
		xmlHttp = GetXmlHttpObject();
		if(xmlHttp == null)
		{
			return;
		}

		if(flag == 1 && tempFlag == 0)
		{
			url = 'ifms.htm?actionFlag=saveSixPCArrears&dcpsSixPCIds='+dcpsSixPCIds+'&amounts='+amounts;
		}
		else
		{
			alert('Please Select Any Case!');
		}

		
		xmlHttp.onreadystatechange = function()
		{
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					XMLDoc = xmlHttp.responseXML.documentElement;
					var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');

						var successFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
												
						if (successFlag=='true') {
								alert('Amounts Deposited successfully');
								self.location.href = 'ifms.htm?actionFlag=loadSixthPCArrearsDDO&StatusFlag=F';
						}
					}
				}
		}
xmlHttp.open("POST",url,true);
xmlHttp.send(url);
}
function btnDsply()
{
	
	document.getElementById("").style.display="none";
	document.getElementById("").style.display="none";
	
}
</script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="FromDate" value="31/03/2009"></c:set>
<c:set var="ToDate" value="01/01/2006"></c:set>
<c:set var="Index" value="1"></c:set>
<c:set var="empList" value="${resValue.empList}"></c:set>
<c:set var="UpperUserList" value="${resValue.UserList}"></c:set>
<c:set var="StatusFlag" value="${resValue.StatusFlag}"></c:set>
<c:set var="counter" value="1"></c:set>

<hdiits:form name="frm6PcArrearEntry"  id="frm6PcArrearEntry" encType="multipart/form-data" validate="true" method="post" >
<input type="hidden" id="ForwardToPost" name="ForwardToPost" value="${UpperUserList[0]}"/>
<input type ="hidden" name="hidStatus" id="hidStatus" value="${StatusFlag}"></input>
<fieldset style="width: 100%" class="tabstyle">
	<legend id="headerMsg">
		<b>  6th PC Arrear Amount Deposition To DCPS Contribution Entry Form </b>
	</legend> 
<table width="100%">
<tr>
<td>
<display:table list="${empList}" id="vo" cellpadding="4" style="width:100%" pagesize="10">    			      			        			      			      			  		         		         				
	              	 
	              <display:setProperty name="paging.banner.placement" value="bottom" />
	              <display:column style="text-align: center;" class="tablecelltext" title="Sr.No" headerClass="datatableheader"
		            value="${Index}" > 	
		           <c:set var="Index" value="${Index+1}"> </c:set>	           	          
	              </display:column>
	              	
	              <display:column class="tablecelltext"  style="text-align:center" title= "Employee Pension ID" headerClass="datatableheader"
	               value="${vo[0]}">			      
		          </display:column>		          		         
	              	              	              
	              <display:column style="text-align: center;" class="tablecelltext" title="Employee Name" headerClass="datatableheader"
		           value="${vo[2]}"> 		           	          
	              </display:column>
	              
	              <display:column style="text-align: center;" class="tablecelltext" title="From Date" headerClass="datatableheader"
 								  value="${FromDate}"> 		           	          
	              </display:column>
	              
	              <display:column style="text-align: center;" class="tablecelltext" title="To Date" headerClass="datatableheader"
		           				 value="${ToDate}"> 		           	          
	              </display:column>
	              
	              <display:column style="text-align: center;" class="tablecelltext" title="Enter Total Amount to be deposited to DCPS due to 6th Pay" headerClass="datatableheader">
		                <input type="text" id="amount${counter}" name="amount${counter}" value="${vo[3]}"/> 		           	          
	              </display:column>
	              
	               <display:column style="text-align:center;" class="tablecelltext" title="SixthPC Arrears Status" headerClass="datatableheader" value="${vo[10]}">
	               		<%-- <c:if test="${vo[5] == 'D'}"><c:out value="Draft"></c:out></c:if>
	               		<c:if test="${vo[5] == 'F'}"><c:out value="Draft"></c:out></c:if>
	               		<c:if test="${vo[5] == 'R'}"><c:out value="Rejected"></c:out></c:if> 		
	               		<c:if test="${vo[5] == 'A'}"><c:out value="Approved"></c:out></c:if> 		  --%>		           	          
	              </display:column>
	              
	              <display:column class="tablecelltext" style="text-align:center" title="<input name='chkSelect' type='checkbox' onclick='checkUncheckAll(this,\"chkSelect\");'/>" headerClass="datatableheader">
			      <input type="checkbox" id="checkbox${counter}" name="SelectAll" value="${vo[4]}" />
			      <input type ="hidden" name="hidName" id="hidName${counter}" value="${vo[2]}"></input>
		          <input type ="hidden" name="hidCaseStatus" id="hidCaseStatus${counter}" value="${vo[5]}"></input>
		          <input type ="hidden" name="FromDate" id="FromDate" value="${FromDate}"></input>
		          <input type ="hidden" name="ToDate" id="ToDate" value="${ToDate}"></input>
		          </display:column>
		          
		          <c:set var="counter" value="${counter+1}"></c:set>
		          	              	              	              	            
</display:table>
<input type="hidden" id="totalCount" name="totalCount" value="${counter}"/> 	
</td>
</tr>

<tr>
<td align="right">
<c:if test="${StatusFlag == 'D'  || StatusFlag == 'R'}">
		<hdiits:button name="btnSaveData" id="btnSaveData" type="button" captionid="BTN.SAVE" 
		               bundle="${dcpsLables}" onclick="saveData();" />
		<hdiits:button name="btnForwardToDdo" id="btnForwardToDdo" type="button" captionid="BTN.FORWARDTODDO" 
		               bundle="${dcpsLables}"  style="width:20%" onclick="saveAndfrwrdData();"/>
</c:if>
<c:if test="${StatusFlag == 'F' || StatusFlag == 'A'}">
		<hdiits:button name="btnSaveData" id="btnSaveData" type="button" captionid="BTN.SAVE" 
		               bundle="${dcpsLables}" onclick="saveDataDDO();" />
		<hdiits:button name="btnReject" id="btnReject" type="button" captionid="BTN.REJECT" 
		               bundle="${dcpsLables}"  onclick="rejectDCPSArrearsCase();"/>
		<hdiits:button name="btnApprove" id="btnApprove" type="button" captionid="BTN.APPROVE" 
		               bundle="${dcpsLables}" onclick="approveDCPSArrearsCase();" />
</c:if>
</td>
</tr>


</table>

</fieldset>
</hdiits:form>	     