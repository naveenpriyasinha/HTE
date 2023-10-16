<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<script>

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
function AttachGroup()
{
	var employeeSelected = false ;
	var billGroupSelected = false ;
	var xmlHttp=null;
	xmlHttp = GetXmlHttpObject();
	
	if (xmlHttp == null)
	{
		alert("Your browser does not support AJAX!");
		return;
	}
		var lCheckGroup = document.getElementsByName("GroupCheck");		
		var lCheckGroupLength = lCheckGroup.length;		
		var lCheck = "";		
		var i;
		for (i = 0; i < lCheckGroupLength; i++) 
		{
			if (document.getElementsByName("GroupCheck")[i].checked) 
			{
				employeeSelected = true;
				if(i==lCheckGroupLength-1)
				{
					lCheck = lCheck + lCheckGroup[i].value;
				}
				else
				{	
				lCheck = lCheck + lCheckGroup[i].value + ",";
				}	
						
			}
		}
		if(!(employeeSelected))
		{
			alert('Please select employee to attach to billgroup');
			return false;
		}
		
		var lRadioGroup = document.getElementsByName("GroupRadio");		
		var lRadioGroupLength = lRadioGroup.length;	
		var lRadio = "";
		var j;
		for (j = 0; j < lRadioGroupLength; j++) 
		{
			if (document.forms[0].GroupRadio[j].checked) 
			{
				billGroupSelected = true;
				lRadio = lRadio + lRadioGroup[j].value ;
			}
		}
		
		if(!(billGroupSelected))
		{
			alert('Please select bill group to attach employee');
			return false;
		}	
		
	
		var url = "ifms.htm?actionFlag=attachBillGroup&lRadio=" + lRadio
				+ "&lCheck=" + lCheck;		
		xmlHttp.open("POST", url, true);
		xmlHttp.send(url);

		xmlHttp.onreadystatechange = function() 
		{
			if (xmlHttp.readyState == 4) 
			{
				if (xmlHttp.status == 200) 
				{
					XMLDoc = xmlHttp.responseXML.documentElement;
					var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
					var test_Id = XmlHiddenValues[0].childNodes[0].text;
					if(test_Id)
					{
					alert("Employee successfully attached to the billgroup");
					}
					self.location.href = 'ifms.htm?actionFlag=dcpsBillGroup';	
				}
	     	}
					
		};
}

function DetachGroup(totalEmployees)
{

	var totalEmployeesInBillGroup = totalEmployees ;
	var dcpsEmpIds = "";
	var totalSelectedEmployees= 0;
	totalEmployeesInBillGroup=5 ; //to be deleted later
	lastEmployeeChecked = 0;
	
	for(var i=1;i<=totalEmployeesInBillGroup;i++)
	{
		//alert("checkbox"+i);
		if(document.getElementById("checkbox"+i).checked)
		{
			alert("checkbox"+i+"checked");
			totalSelectedEmployees ++ ;
			lastEmployeeChecked = i ;
		}
	}
	//alert('lastEmployeeChecked'+lastEmployeeChecked);
	//alert('totalSelectedEmployees'+totalSelectedEmployees);

	for(var i=1;i<=totalEmployeesInBillGroup;i++)
	{
		if(document.getElementById("checkbox"+i).checked)
		{
			//alert("checkbox"+i+"checked");
			if(i==lastEmployeeChecked)
			{
				dcpsEmpIds = dcpsEmpIds + document.getElementById("checkbox"+i).value;
			}
			else
			{
				dcpsEmpIds = dcpsEmpIds + document.getElementById("checkbox"+i).value + "~";
			}
		}
	}

	//alert('dcpsEmpIds-->'+dcpsEmpIds);
	xmlHttp = GetXmlHttpObject();
	if(xmlHttp == null)
	{
	alert(url);
	return;
	} 

	url = 'ifms.htm?actionFlag=detachBillGroup&dcpsEmpIds='+dcpsEmpIds;
	//alert('url-->'+url);
	
	xmlHttp.onreadystatechange = function()
	{
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				XMLDoc = xmlHttp.responseXML.documentElement;
				var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');

					var successFlag = XmlHiddenValues[0].childNodes[0].text;
											
					if (successFlag=='true') {
							alert('Employee successfully detached from the billgroup');
							self.location.href = 'ifms.htm?actionFlag=dcpsBillGroup';	
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
<c:set var="totalRecords" value="${resValue.totalRecords}"></c:set>
<c:set var="empList" value="${resValue.empList}"></c:set>
<c:set var="billgroupList" value="${resValue.billgroupList}"></c:set>
<c:set var="showgroupList" value="${resValue.showgroupList}"></c:set>
<c:set var="MstEmpObjList" value="${resValue.MstEmpObjList}"></c:set>


<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request" />
<fieldset class="tabstyle">
<legend>
	<b><fmt:message key="CMN.ATTACHLIST" bundle="${dcpsLables}"></fmt:message></b>
</legend>
<c:set var="totalEmplyeesAttached" value="0"></c:set>
<table width="80%" align="center">
     <tr>
         <td width="100%">
               <display:table list="${showgroupList}"  id="vo" cellspacing="4"  requestURI="" export="" style="width:90%"  pagesize="5">	
               <display:setProperty name="paging.banner.placement" value="bottom" /> 			      			        			      			      			            
		         
		          <display:column class="tablecelltext" style="text-align:center" headerClass="datatableheader" title= "<input type='checkbox' name='SelectAll' onclick='checkUncheckAll(this)'/>" >
			      <input type="checkbox" id="checkbox${vo_rowNum}"  name="empIdsforDeselect" value="${vo.dcpsEmpId}" />
		          </display:column>	
		         
				  <display:column style="text-align: center;" class="tablecelltext" title="Employee ID" headerClass="datatableheader"
		           sortable="true" value="${vo.dcpsEmpId}"> 
	              </display:column>	  
	                   
	              <display:column style="text-align: center;" class="tablecelltext" title="Employee Name" headerClass="datatableheader"
		           sortable="true" value="${vo.name}"> 
	              </display:column>	
	                                  		       		         
		          <display:column style="text-align: center;" class="tablecelltext" title="BillGroup ID" headerClass="datatableheader"
		           sortable="true" value="${vo.billGroupId}"> 
	              </display:column>
	              
	              <c:set var="totalEmplyeesAttached" value="${vo_rowNum}"></c:set>
	              
               </display:table>	     
         </td>
     </tr>
</table>
<br/>
<br/>
<table align="center">
	<tr>
	<td width="20%" align="center">
	         <hdiits:button type="button" captionid="BTN.DETACH" bundle="${dcpsLables}" id="btnDetach" name="btnDetach" onclick="DetachGroup('${totalEmplyeesAttached}')" ></hdiits:button>
	</td>
	</tr>
	<tr><td>&nbsp;</td></tr>
</table>
</fieldset>
<br/>
<fieldset class="tabstyle">
<legend>
	<b><fmt:message key="CMN.ATTACHEMPLOYEE" bundle="${dcpsLables}"></fmt:message></b>
</legend>
<hdiits:form name="frmDCPSAttachBill" action="" id="frmDCPSAttachBill" encType="multipart/form-data" validate="true" method="post">
<tr>&nbsp;</tr>
<tr>&nbsp;</tr>
<tr>&nbsp;</tr>
<table>
<tr>
</tr>
</table>
<table width="100%">     
     <tr>
         <td width="40%" valign="top">
             <display:table list="${empList}" id="vo" requestURI="" export="" cellpadding="4" style="width:100%" pagesize="10">    
             <display:setProperty name="paging.banner.placement" value="bottom" />			      			        			      			  
    			  <display:column class="tablecelltext" style="text-align:center" headerClass="datatableheader" title= "<input type='checkbox' name='SelectAll' onclick='checkUncheckAll(this)'/>" >
			      <input type="checkbox" name="GroupCheck" value="${vo.dcpsEmpId}" />
		          </display:column>		          
		         
		          <display:column style="text-align: center;" class="tablecelltext" title="Employee Name" headerClass="datatableheader"
		           sortable="true" value="${vo.name}"> 		           	          
	              </display:column>
             </display:table>	               	
         </td>                  
         
         <td width="20%" align="center">
         <hdiits:button type="button" captionid="BTN.ATTACH" bundle="${dcpsLables}" id="btnAttach" name="btnAttach" onclick="AttachGroup()" ></hdiits:button>
         </td>
         
         <td width="40%" valign="top">
             <display:table list="${billgroupList}" requestURI="" export="" id="vo" cellpadding="4" style="width:100%" pagesize="10">    			      			        			      			      			  		          		         
		          <display:column class="tablecelltext" style="text-align:center" headerClass="datatableheader">
			      <input type="radio" name="GroupRadio" value="${vo.dcpsDdoBillGroupId}" />
		          </display:column>
		          
		          <display:column style="text-align: center;" class="tablecelltext" title="Bill Group ID" headerClass="datatableheader"
		           sortable="true" value="${vo.dcpsDdoBillGroupId}"> 
	              </display:column>
             </display:table>	        	
         </td>
    </tr>
     
</table>
</hdiits:form>
</fieldset>