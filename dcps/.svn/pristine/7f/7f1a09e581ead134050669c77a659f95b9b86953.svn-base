<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<script type="text/javascript" src="script/common/common.js"></script>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="lListEmp" value="${resValue.lListEmp}"></c:set>
<c:set var="Index" value="1"> </c:set>
<c:set var="UpperUserList" value="${resValue.UserList}"></c:set>
<c:set var="StatusFlag" value="${resValue.StatusFlag}"></c:set>
<c:set var="counter" value="1"></c:set>
<c:set var="StatusFlag" value="${resValue.StatusFlag}"></c:set>
<c:set var="UserType" value="${resValue.UserType}"></c:set>


<script>
function approveDCPSArrearsYearlyTO()
{
	
	var totalSelectedEmployees= document.getElementById("totalCount").value;
	var SixthPC_Id=" ";
	var flag=0;
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
		ApproveArrearsYearlyTOUsingAjax("ifms.htm?actionFlag=approveSixthPCArrearsYearlyByTO&SixthPC_Id="+SixthPC_Id);
	}
	else
	{
		alert("Please select a case to Approve!");
	}
}
function ApproveArrearsYearlyTOUsingAjax(url)
{
	
	xmlHttp=GetXmlHttpObject();

	if (xmlHttp==null)
	{
		alert ("Your browser does not support AJAX!");
		return;
	} 

	uri=url;
	
	xmlHttp.onreadystatechange=approveDataTOStateChanged;
	xmlHttp.open("POST",uri,false);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(uri);

}

function approveDataTOStateChanged() 
{ 

	if (xmlHttp.readyState==4)
	{ 
		if(xmlHttp.status==200)
		{
			alert("The Case has been Approved.");
			self.location.href="ifms.htm?actionFlag=loadsixthPCYearlyInstallmentTO&UserType=TO&StatusFlag=A";
		}
	}
}
function rejectDCPSArrearsYearlyTO()
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
		
		RejectArrearsYearlyTOUsingAjax("ifms.htm?actionFlag=rejectSixthPCArrearsYearlyByTO&SixthPC_Id="+SixthPC_Id);
	}
	else
	{
		alert("Please select a case to Reject!");
	}
}


function RejectArrearsYearlyTOUsingAjax(url)
{
	xmlHttp=GetXmlHttpObject();

	if (xmlHttp==null)
	{
		alert ("Your browser does not support AJAX!");
		return;
	} 

	uri=url;
	
	xmlHttp.onreadystatechange=rejectDataTOStateChanged;
	xmlHttp.open("POST",uri,false);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(uri);

}

function rejectDataTOStateChanged() 
{ 
	if (xmlHttp.readyState==4)
	{ 
		if(xmlHttp.status==200)
		{
			alert("The Case has been sent back to DDO Asst.");
			self.location.href="ifms.htm?actionFlag=loadSixthPCArrearsYearlyTO&UserType=TO&StatusFlag=F";
		}
	}
}


function approveDCPSArrearsYearlyDDO()
{
	var totalSelectedEmployees= document.getElementById("totalCount").value;
	var ForwardToPost = document.DCPSSixPCArrearsEntryForm.ForwardToPost.value;
	
	var SixthPC_Id=" ";
	var flag=0;
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
		ApproveArrearsYearlyUsingAjax("ifms.htm?actionFlag=approveSixthPCArrearsYearlyByDDO&SixthPC_Id="+SixthPC_Id+"&ForwardToPost="+ForwardToPost);
	}
	else
	{
		alert("Please select a case to Approve!");
	}
}
function ApproveArrearsYearlyUsingAjax(url)
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
			alert("The Case has been Forwarded To Treasury Officer for Approval.");
			self.location.href="ifms.htm?actionFlag=loadSixthPCArrearsYearlyDDO&UserType=DDO&StatusFlag=F";
		}
	}
}
function rejectDCPSArrearsYearlyDDO()
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
		
		RejectArrearsYearlyUsingAjax("ifms.htm?actionFlag=rejectSixthPCArrearsYearlyByDDO&SixthPC_Id="+SixthPC_Id);
	}
	else
	{
		alert("Please select a case to Reject!");
	}
}


function RejectArrearsYearlyUsingAjax(url)
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
			self.location.href="ifms.htm?actionFlag=loadSixthPCArrearsYearlyDDO&UserType=DDO&StatusFlag=F";
		}
	}
}

function FrwrdDCPSArrearsYearlyUsingAjax(url)
{
	xmlHttp=GetXmlHttpObject();

	if (xmlHttp==null)
	{
		alert ("Your browser does not support AJAX!");
		return;
	} 

	uri=url;
	xmlHttp.onreadystatechange=FrwrdDCPSArrearsYearlyStateChanged;
	xmlHttp.open("POST",uri,false);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(uri);

}

function FrwrdDCPSArrearsYearlyStateChanged() 
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
function displayDataForGivenYear()
{
	self.location.href='ifms.htm?actionFlag=sixthPCYearlyInstallment&UserType=DDOAsst&StatusFlag=D&yearId='+document.getElementById("cmbFinancialYear").value;

}

function displayDataForGivenYearDDO()
{
	self.location.href='ifms.htm?actionFlag=sixthPCYearlyInstallmentDDO&UserType=DDO&StatusFlag=F&yearId='+document.getElementById("cmbFinancialYear").value;

}
function displayDataForGivenYearTO()
{
	
	//document.getElementById("tblDDO").style.display="block";
	self.location.href='ifms.htm?actionFlag=loadSixthPCArrearsYearlyTO&UserType=TO&StatusFlag=F&yearId='+document.getElementById("cmbFinancialYear").value;

}
function displayDataForGivenDDO()
{
	if(document.getElementById("cmbFinancialYear").value == -1)
	{
		alert('Please Select Financial Year Again To Confirm Request!');
		self.location.reload();
		
	}
	else
	{
		self.location.href='ifms.htm?actionFlag=loadSixthPCArrearsYearlyTO&UserType=TO&StatusFlag=F&yearId='+document.getElementById("cmbFinancialYear").value;
		
	}
	
}

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

function addYearlyArrears(flagvalue)
{
	var saveOrForwardFlag = flagvalue ;
	var dcpsEmpIds = "";
	var dcpsIds = "";
	var amounts = "";
	var dcpsSixPCYearlyIds = "";
	var finYearId= document.getElementById("finYearId").value;
	var totalSelectedEmployees= document.getElementById("totalCount").value;
	var flag = 0;
	var url;
	for(var i=1;i<totalSelectedEmployees;i++)
	{
		if(document.getElementById("checkbox"+i).checked)
		{
			if(i==totalSelectedEmployees-1)
			{
				dcpsEmpIds = dcpsEmpIds + document.getElementById("dcpsempId"+i).value;
				dcpsIds = dcpsIds + document.getElementById("dcpsId"+i).value ;
				amounts = amounts + document.getElementById("amount"+i).value ;
				dcpsSixPCYearlyIds = dcpsSixPCYearlyIds + document.getElementById("checkbox"+i).value  ;
				flag=1;
			}
			else 
			{
				
			dcpsEmpIds = dcpsEmpIds + document.getElementById("dcpsempId"+i).value + "~";
			dcpsIds = dcpsIds + document.getElementById("dcpsId"+i).value + "~";
			amounts = amounts + document.getElementById("amount"+i).value + "~";
			dcpsSixPCYearlyIds = dcpsSixPCYearlyIds + document.getElementById("checkbox"+i).value + "~" ;
			flag=1;
			
			}
		}
	}

	
	xmlHttp = GetXmlHttpObject();
	if(xmlHttp == null)
	{
	return;
	} 
	if(flag == 1)
	{
		url = 'ifms.htm?actionFlag=saveSixPCArrearsYearly&dcpsEmpIds='+dcpsEmpIds+'&dcpsIds='+dcpsIds+'&amounts='+amounts+'&finYearId='+finYearId+'&dcpsSixPCYearlyIds='+dcpsSixPCYearlyIds;
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

					var successFlag = XmlHiddenValues[0].childNodes[0].text;
											
					if (successFlag=='true') {

						if(saveOrForwardFlag == 1)
						{
							alert('Amounts Deposited successfully');
							self.location.href = 'ifms.htm?actionFlag=sixthPCYearlyInstallment&UserType=DDOAsst&StatusFlag=D';
						}
						if(saveOrForwardFlag == 2)
						{
							var ForwardToPost = document.DCPSSixPCArrearsEntryForm.ForwardToPost.value;
							var urlforward = "ifms.htm?actionFlag=forwardDCPSArrearsYearly&dcpsSixPCYearlyIds="+dcpsSixPCYearlyIds+"&ForwardToPost="+ForwardToPost ;
							FrwrdDCPSArrearsYearlyUsingAjax(urlforward);
						}
					}
				}
			}
	};
xmlHttp.open("POST",url,true);
xmlHttp.send(url);
}

</script>

<hdiits:form name="DCPSSixPCArrearsEntryForm" id="DCPSSixPCArrearsEntryForm" encType="multipart/form-data" validate="true" method="post" >
	<input type="hidden" id="ForwardToPost" name="ForwardToPost" value="${UpperUserList[0]}"/>
<table width="100%">
  


   <tr>
   <td></td>
   <td>
 <fieldset style="width: 100%" class="tabstyle">
<legend><b>Select Year</b></legend>
   <table align="center" width="100%">
   <tr align="center">
	       <td align="right" width="50%"><fmt:message key="CMN.FINANCIALYEAR"
					bundle="${dcpsLables}"></fmt:message>
		   </td>
		   
		   <td align="left" width="50%" >
		       <select name="cmbFinancialYear" id="cmbFinancialYear" style="width: 20%" >				
				 <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>  
				 <c:forEach var="yearVar" items="${resValue.lListYears}">					
						<option value="${yearVar.id}">
								<c:out value="${yearVar.desc}"></c:out>					
						</option>
				 </c:forEach>
			   </select>
		   </td>
	   </tr>
	 <tr></tr>
	 <tr></tr>
	  
	   <tr> 
	    <td align="center"  colspan="2" >
	  	   <c:if test="${UserType == 'DDO' && (StatusFlag == 'F' || StatusFlag == 'R' )}">
	  			 <hdiits:button name="btnGo" id="btnGo" type="button" captionid="BTN.GO" 
		               bundle="${dcpsLables}" onclick="displayDataForGivenYearDDO();" style="size:25"/>
		   </c:if>
		    <c:if test="${UserType == 'TO' &&  (StatusFlag == 'F' || StatusFlag == 'A')}">
	  			 <hdiits:button name="btnGo" id="btnGo" type="button" captionid="BTN.GO" 
		               bundle="${dcpsLables}" onclick="displayDataForGivenYearTO();" style="size:25"/>
		   </c:if>
		    <c:if test="${UserType == 'DDOAsst' && (StatusFlag == 'D' || StatusFlag == 'R')}">
	  			 <hdiits:button name="btnGo" id="btnGo" type="button" captionid="BTN.GO" 
		               bundle="${dcpsLables}" onclick="displayDataForGivenYear();" style="size:25"/>
		   </c:if>
		   </td>
		 <td>&nbsp;</td>
   </tr>
   </table>
  
   <br></br>
   </fieldset>
   </td><td>
   </td>
   </tr>
   <tr>
   
   <td>
   </td>
   
   </tr>

   <tr>
 
   <td></td>
   
   
   <td id="displayTable" >
   
   <c:if test="${lListEmp!=null}">
    <fieldset style="width: 100%" class="tabstyle">
	<legend id="headerMsg">
		<b>Yearly Process of 6th PC Installment Amount Deposition to DCPS Verification </b>
	</legend>
   <div id="tblDDO">
   <c:if test="${resValue.lLstAllForms != null}"> 
   <table align="center">
  	<tr>
  	<td align="left"><fmt:message key="CMN.DDOName" bundle="${dcpsLables}"></fmt:message></td>
  	<td align="center">
  	 <select name="cmbDDO" id="cmbDDO" onchange="displayDataForGivenDDO();">
  	  <option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>  	
  	 		 <c:forEach var="DDO" items="${resValue.lLstAllForms}">					
						<option value="${DDO.id}">
								<c:out value="${DDO.desc}"></c:out>					
						</option>
				 </c:forEach>
			   </select></td>
  	</tr>
  	 </table> 
  	 </c:if>	
  </div>
  <br></br>
    <display:table list="${lListEmp}" id="vo" cellpadding="4" style="width:100%" pagesize="10">    			      			        			      			      			  		         		         				
	              	 
	              <display:setProperty name="paging.banner.placement" value="bottom" />
	              <display:column style="text-align: center;" class="tablecelltext" title="Sr.No" headerClass="datatableheader"
		            value="${Index}" > 	
		           <c:set var="Index" value="${Index+1}"> </c:set>	           	          
	              </display:column>
	              	
	              <display:column class="tablecelltext" style="text-align:center" title= "Financial Year" headerClass="datatableheader"
	               value="${vo[0]}">			      
		          </display:column>
	              	
	              <display:column class="tablecelltext" style="text-align:center" title= "Employee Pension ID" headerClass="datatableheader"
	               value="${vo[1]}">
		          </display:column>		          		         
	              	              	              
	              <display:column style="text-align: center;" class="tablecelltext" title="Employee Name" headerClass="datatableheader"
		           value="${vo[2]}"> 		           	          
	              </display:column>
	              
	              <display:column style="text-align: center;" class="tablecelltext" title="Total Arrear Amount" headerClass="datatableheader" 
		          value="${vo[3]}"> 		           	          
	              </display:column>
	              
	              <display:column style="text-align: center;" class="tablecelltext" title="Paid Arrear Amount till Date" headerClass="datatableheader"
	               value="${vo[4]}">
		               		           	          
	              </display:column>
	              
	              <display:column class="tablecelltext" style="text-align:center" title= "Amount to be deposited to Employee's Account For Current Financial Year" headerClass="datatableheader">
	                  <input type="text" id="amount${counter}" name="amount${counter}" value="${vo[5]}"/> 	      
		          </display:column>
		          
		          <display:column style="text-align: center;" class="tablecelltext" title="SixthPC Arrears Status" headerClass="datatableheader" >
	               
	               		<c:if test="${vo[10] == 'D'}"><c:out value="Draft"></c:out></c:if>
	               		<c:if test="${vo[10] == 'F'}"><c:out value="Draft"></c:out></c:if>
	               		<c:if test="${vo[10] == 'R'}"><c:out value="Rejected"></c:out></c:if> 		
	               		<c:if test="${vo[10] == 'A'}"><c:out value="Approved"></c:out></c:if> 		 
		               		           	          
	              </display:column>
		          <display:column class="tablecelltext" style="text-align:center" title= "<input type='checkbox' name='SelectAll' onclick='checkUncheckAll(this)'/>" >
			      <input type="checkbox" id="checkbox${counter}" name="checkbox" value="${vo[6]}" />
		          <input type="hidden" name="dcpsempId${counter}" id="dcpsempId${counter}" value="${vo[7]}">
		 		  <input type="hidden" name="dcpsId${counter}" id="dcpsId${counter}" value="${vo[1]}">
		 		  <input type="hidden" name="dcpsSixPcId${counter}" id="dcpsSixPcId${counter}" value="${vo[9]}">
		 		   <input type ="hidden" name="hidName" id="hidName${counter}" value="${vo[2]}"></input>
		          <input type ="hidden" name="hidCaseStatus" id="hidCaseStatus${counter}" value="${vo[10]}"></input>
				  </display:column>	
				    
	              
		  <c:set var="counter" value="${counter+1}"></c:set>              	              	              	            
     </display:table>
      </fieldset>
     </c:if>
     <input type="hidden" id="totalCount" name="totalCount" value="${counter}"/>
     <input type="hidden" id="finYearId" name="finYearId" value="${vo[8]}"/>
       	
     </td>
    
     
     <td></td>
     </tr>

		<tr>
		<td></td>
		<td align="right">
		<br></br>
		<c:if test="${lListEmp!=null}"> 
		<c:if test="${UserType == 'DDOAsst' && (StatusFlag == 'D' || StatusFlag == 'R')}">
		<table>
				<tr>
				<td align="center" id="AddArrearsBtn">
				<hdiits:button name="btnAddArrear" id="btnAddArrear" type="button" captionid="BTN.ADDARREARSTOEMPLOYEESACCOUNT" 
						               bundle="${dcpsLables}" onclick="addYearlyArrears(1);" style="width:100%;"/>
					         </td>
				<td align="right" id="ForwardToDDOBtn">
						<hdiits:button name="btnForwardToDdo" id="btnForwardToDdo" type="button" captionid="BTN.FORWARDTODDO" 
						               bundle="${dcpsLables}" onclick="addYearlyArrears(2);" style="width:100%"/></td>
						
				</tr>
				</table>
				</c:if>
		<c:if test="${UserType == 'DDO' && (StatusFlag == 'F' || StatusFlag == 'R')}">
		<table>
				<tr>
				
				<td align="right" id="ForwardToDDOBtn">
				 
						<hdiits:button name="btnForwardToDdo" id="btnForwardToDdo" type="button" captionid="BTN.FORWARD" 
						               bundle="${dcpsLables}" onclick="approveDCPSArrearsYearlyDDO();" style="width:100%"/></td>
						               <td align="right" id="ForwardToDDOBtn">
				 
						<hdiits:button name="btnRejectToDdoAsst" id="btnRejectToDdoAsst" type="button" captionid="BTN.REJECT" 
						               bundle="${dcpsLables}" onclick="rejectDCPSArrearsYearlyDDO();" style="width:100%"/></td>
					
				</tr>
				</table>
				</c:if>
		<c:if test="${UserType == 'TO' && (StatusFlag == 'F' || StatusFlag == 'A' )}">
		<table>
				<tr>
				
				
				
				<td align="right" id="ForwardToDDOBtn">
				 
						<hdiits:button name="btnApprove" id="btnApprove" type="button" captionid="BTN.APPROVE" 
						               bundle="${dcpsLables}" onclick="approveDCPSArrearsYearlyTO();" style="width:100%"/></td>
						               <td align="right" id="ForwardToDDOBtn">
				 
						<hdiits:button name="btnRejectToDdo" id="btnRejectToDdo" type="button" captionid="BTN.REJECT" 
						               bundle="${dcpsLables}" onclick="rejectDCPSArrearsYearlyTO();" style="width:100%"/></td>
					
				<!--<td>	<hdiits:button name="btnApproveAll" id="btnApproveAll" type="button" captionid="BTN.APPROVEALL" 
						               bundle="${dcpsLables}" onclick="approveDCPSArrearsYearlyTO();checkUncheckAll(this,'checkbox');" style="width:100%"/>
				</td>
				--></tr>
				</table>
		</c:if>
		
		</c:if>		
		</td>
		</tr>
</table>	    

</hdiits:form>