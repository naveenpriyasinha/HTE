<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"scope="request" />

<script>
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
	alert('total Count-->'+totalSelectedEmployees) ;
	var dcpsSixPCIds="";
	var amounts="";
		for(var i=1;i<totalSelectedEmployees;i++)
		{
			if(document.getElementById("checkbox"+i).checked)
			{
				dcpsSixPCIds= dcpsSixPCIds + document.getElementById("checkbox"+i).value + "~";
				amounts= amounts + document.getElementById("amount"+i).value + "~";
			}
		}
	//alert('All DCPSSixPCId-->'+dcpsSixPCId);
	//alert('All totalAmounts-->'+totalAmount);
	
		xmlHttp = GetXmlHttpObject();
		if(xmlHttp == null)
		{
		alert(url);
		return;
		}

		url = 'ifms.htm?actionFlag=saveSixPCArrears&dcpsSixPCIds='+dcpsSixPCIds+'&amounts='+amounts;

		
		xmlHttp.onreadystatechange = function()
		{
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					XMLDoc = xmlHttp.responseXML.documentElement;
					var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');

						var successFlag = XmlHiddenValues[0].childNodes[0].text;
												
						if (successFlag=='true') {
								alert('Amounts Deposited successfully');
								self.location.href = 'ifms.htm?actionFlag=sixthPCArrearsEntry';
						}
					}
				}
		}
xmlHttp.open("POST",url,true);
xmlHttp.send(url);
}
</script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="FromDate" value="31/03/2009"></c:set>
<c:set var="ToDate" value="01/01/2006"></c:set>
<c:set var="Index" value="1"></c:set>
<c:set var="empList" value="${resValue.empList}"></c:set>

<c:set var="counter" value="1"></c:set>
<hdiits:form name="frm6PcArrearEntry" action="" id="frm6PcArrearEntry" encType="multipart/form-data" validate="true" method="post">
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
	              
	              <display:column class="tablecelltext" style="text-align:center" title= "Select" headerClass="datatableheader">
			      <input type="checkbox" id="checkbox${counter}" name="SelectAll" value="${vo[4]}" />
		          </display:column>
		          
		          <c:set var="counter" value="${counter+1}"></c:set>
		          	              	              	              	            
</display:table>
<input type="hidden" id="totalCount" name="totalCount" value="${counter}"/> 	
</td>
</tr>

<tr>
<td align="right">
		<hdiits:button name="btnSaveData" id="btnSaveData" type="button" captionid="BTN.SAVE" 
		               bundle="${dcpsLables}" onclick="saveData();" />
		<hdiits:button name="btnForwardToDdo" id="btnForwardToDdo" type="button" captionid="BTN.FORWARDTODDO" 
		               bundle="${dcpsLables}" onclick="" style="width:20%"/>
		<hdiits:button name="btnSelectAll" id="btnSelectAll" type="button" captionid="BTN.SELECTALL" 
		               bundle="${dcpsLables}" onclick="checkUncheckAll();" />

</td>
</tr>


</table>
</hdiits:form>	     