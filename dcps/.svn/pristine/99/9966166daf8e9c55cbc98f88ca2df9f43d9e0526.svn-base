
<%

try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page session="true"%>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="enLables" scope="page" />
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>

<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="actionList" value="${resValue.actionList}">
</c:set>
<c:set var="msg" value="${resValue.msg}"></c:set>

<c:set var="empId" value="${resValue.empId}"></c:set>
<c:set var="empAllRec" value="${resValue.empAllRec}"></c:set>
<c:set var="Name" value="${resValue.empName}" > </c:set>

<fmt:setBundle basename="resources.Payroll" var="commonLable" scope="request"/>
<fmt:message var="contractEmpType" key="contract" bundle="${commonLable}" scope="request"> </fmt:message>
<fmt:message var="fixedEmpType" key="fixed" bundle="${commonLable}" scope="request"> </fmt:message>
<fmt:message var="probationEmpType" key="probation" bundle="${commonLable}" scope="request"> </fmt:message>

<%String editMode=request.getParameter("edit");
 session.putValue("edit",editMode);
 %>
<script>

function checkfixpay()
{
	var empId = document.getElementById("Employee_ID_EmpSearch").value;
	document.frmBF.empId.value=empId;
		try {   
				xmlHttp=new XMLHttpRequest();
   		}
		catch(e){    // Internet Explorer    
			try {
	     		xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");   
    	 	}
	    	catch (e) {
		    	try {
	            	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
    	   		}
			    catch (e) {
			        alert("Your browser does not support AJAX!");        
			        return false;        
			    }
			}
		}
		var url = "hrms.htm?actionFlag=getVpfDtlsByEmpId&empId="+empId+"&check=1";  
		
        
    xmlHttp.onreadystatechange = function() {		
		if (xmlHttp.readyState == 4) {     			
			if (xmlHttp.status == 200) {	

			    var initBasic;
			    var emptype;
			    var pfAmount;
				var XMLDocForAjax=xmlHttp.responseXML.documentElement;
				var vpfDetailsMapping = XMLDocForAjax.getElementsByTagName('vpfDetailsMapping');	
				var flag="true";					
				if(vpfDetailsMapping.length != 0) {
					//initBasic=vpfDetailsMapping[0].childNodes[0].text;

					//pfAmount = vpfDetailsMapping[0].childNodes[1].text;	

					emptype= vpfDetailsMapping[0].childNodes[0].text;					
//					if(emptype==300018 || emptype==300225)
					if(emptype=="${contractEmpType}" || emptype=="${fixedEmpType}")
					{
						alert("Not Accessible For Fixed and Contractual Employee!!");
	     			    document.frmBF.vpfAmount.value='';
						clearEmployee("EmpSearch");
						//window.history.back();
						return false;
					}
					}
		
}
}}

				xmlHttp.open("POST", encodeURI(url) , false);    
				xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
				xmlHttp.send(encodeURIComponent(null));	
				return true;


}

function chkFunc()
{
	var empId=document.getElementById("Employee_ID_EmpSearch").value;
	var retValue=true;
	if(empId=="")
	{
		alert("Please search the employee first");
		retValue=false;
		return false;
	
	}
	else
	{
		try 
		{   
			xmlHttp=new XMLHttpRequest();
	   	}
		catch(e)
		{    
			// Internet Explorer    
			try 
			{
		    	xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");   
	    	} 
		    catch (e) 
		    {
			 	try 
			 	{
		        	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
	    	   	}
				catch (e) 
				{
					alert("Your browser does not support AJAX!");  
					  retValue=false;       
				   return false;   
				 
				}
			}
		}
		var url = "hrms.htm?actionFlag=getVPFView&empId="+empId+"&chk=1";  
	    xmlHttp.onreadystatechange = function(){		
			if(xmlHttp.readyState == 4){     			
				if (xmlHttp.status == 200){	
					var LoanNo;
					var XMLDocForAjax=xmlHttp.responseXML.documentElement;
					var loanAdvMapping = XMLDocForAjax.getElementsByTagName('empNameMapping');	
					
					if(loanAdvMapping.length != 0) {
						
						LoanNo = loanAdvMapping[0].childNodes[0].text;
						
						if(eval(LoanNo)==-2){
						
							var res = confirm("The information for "+document.getElementById("Employee_Name_EmpSearch").value+" is not entered in the system.\n Want to Enter the Record.");
							
							document.frmBF.reset();
							retValue=true;
							if(res){
							
										if( "${empAllRec}"!=null && "${empAllRec}"=='true')
										//alert('in if'+ empId);
										var url ="./hrms.htm?actionFlag=newEmpData&empId=${empId}&empAllRec=Y&newEntryEmpId=${empId}"
										else
										//alert('in else'+ empId);
										var url = "./hrms.htm?actionFlag=newEmpData&newEntryEmpId="+empId;
							document.frmBF.action=url;
							document.frmBF.submit();
							}
							
						}
						else
						{
							
                        	if(eval(LoanNo)==-1)
							{
								var res = confirm("The information for "+document.getElementById("Employee_Name_EmpSearch").value+" is not entered in the system.\n Want to Enter the Record.");
							
								document.frmBF.reset();
								retValue=true;
								if(res){
								
										
										if( "${empAllRec}"!=null && "${empAllRec}"=='true')
										//alert('in if'+ empId);
										var url ="./hrms.htm?actionFlag=fillCombo&empId="+empId+"&empAllRec=true&newEntryEmpId="+empId;
										else
										//alert('in else'+ empId);
										var url = "./hrms.htm?actionFlag=fillCombo&empAllRec=false&empId="+empId+"&newEntryEmpId="+empId;
										
								document.frmBF.action=url;
								document.frmBF.submit();
								}
								return true;
							}
							
							else
							{
								if(eval(LoanNo)>0)
							{
								if("${empAllRec}"!='true')
								{
								var res=confirm("Emp record is already Entered.\n Do you want to update it.");
							 	if(res)
							 	{
								   document.frmBF.action="./hrms.htm?actionFlag=getVpfDtlsById&vpfId="+loanAdvMapping[0].childNodes[1].text+"&edit=Y&empId="+empId;
									document.frmBF.submit();
									retValue=true;
									return true;
									}
							 	else
								{
										
									document.frmBF.reset();
							     	retValue=false;
							     	return false;
							     	
							     }
								}
							      						     	
								else
								{
										
									document.frmBF.reset();
							     	retValue=false;
							     	return false;
							     	
							     }
						     	}
							}
						}
					}
				}
			}
		}
		
		xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));	
		return true;
	}
}

function validateForm()
{	
		var empName = document.getElementById("Employee_ID_EmpSearch").value;
		document.frmBF.empId.value=empName;
	    if(chkFunc()!=true)
	    {
	     return false;
	    }
		else if(empName!=null&&empName!='')
		{
		if(!checkAvailability(document.frmBF.vpfAmount))
		return false;
		else
		return true;
		//document.frmBF.action="./hrms.htm?actionFlag=insertUpdateVPFDtls&edit=N";
		//document.frmBF.submit();
		}
		else
		return true;
}

function beforeSubmit()
{
				
		/*var empName = document.getElementById("Employee_ID_EmpSearch").value;
		document.frmBF.empId.value=empName;
	    if(chkFunc()!=true)
	    {
	     document.frmBF.action = 'javascript:beforeSubmit()';
	    }
		else if(empName!=null&&empName!='')
		{
		checkAvailability(document.frmBF.vpfAmount.value);
		alert("this is testing ");
		var vpfAmount = document.getElementById("vpfAmount").value;
		var zerovpfMonths = document.getElementById("zerovpfMonths").value;
		if("${empAllRec}"=='true')
		document.frmBF.action="./hrms.htm?actionFlag=insertUpdateVPFDtls&edit=N&empAllRec=true&vpfAmount="+vpfAmount+"&zerovpfMonths="+zerovpfMonths;
		else
		document.frmBF.action="./hrms.htm?actionFlag=insertUpdateVPFDtls&edit=N&empAllRec=false&vpfAmount="+vpfAmount+"&zerovpfMonths="+zerovpfMonths";
		
		*/
		var vpfAmount = document.getElementById("vpfAmount").value;
		var zerovpfMonths = document.getElementById("zerovpfMonths").value;
		if("${empAllRec}"=='true')
		document.frmBF.action="./hrms.htm?actionFlag=insertUpdateVPFDtls&edit=N&empAllRec=true&vpfAmount="+vpfAmount+"&zerovpfMonths="+zerovpfMonths;
		else
		document.frmBF.action="./hrms.htm?actionFlag=insertUpdateVPFDtls&edit=N&empAllRec=false&vpfAmount="+vpfAmount+"&zerovpfMonths="+zerovpfMonths;
		document.frmBF.submit();
		//}
}

function chkKey(e){	
	if(e.keyCode=='13')
		return false;
	else	
		return true;
}
function checkAvailability(newVpf)
{

	
	var empId=document.getElementById("Employee_ID_EmpSearch").value;
	if(document.getElementById("Employee_ID_EmpSearch").value!='')
	if(checkfixpay()==false)
	return;
	else
	{	
	var vpfAmount=newVpf.value;	
	var vpfAmt=newVpf.value;	
	document.frmBF.empId.value=empId;
	if(vpfAmount!="")
	{
		try {   
				xmlHttp=new XMLHttpRequest();
   		}
		catch(e){    // Internet Explorer    
			try {
	     		xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");   
    	 	}
	    	catch (e) {
		    	try {
	            	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
    	   		}
			    catch (e) {
			        alert("Your browser does not support AJAX!");        
			        return false;        
			    }
			}
		}
		var url = "hrms.htm?actionFlag=getVpfDtlsByEmpId&empId="+empId+"&check=2";  
		
        
    xmlHttp.onreadystatechange = function() {		
		if (xmlHttp.readyState == 4) {     			
			if (xmlHttp.status == 200) {	

			    var initBasic;
			    var emptype;
			    var pfAmount;
			    var isEmpAvail;
			    var isGpfAcc;
				var XMLDocForAjax=xmlHttp.responseXML.documentElement;
				var vpfDetailsMapping = XMLDocForAjax.getElementsByTagName('vpfDetailsMapping');	
				var flag="true";					
				if(vpfDetailsMapping.length != 0) {	
					isEmpAvail = vpfDetailsMapping[0].childNodes[1].text;					
					if(isEmpAvail==-1){
						var res = confirm("The information for Basic Detail"+document.getElementById("Employee_Name_EmpSearch").value+" is not entered in the system");
		                //window.location.reload();	
		                document.frmBF.vpfAmount.value='';
		                if(res){
								
										
										if( "${empAllRec}"!=null && "${empAllRec}"=='true')
										//alert('in if'+ empId);
										var url ="./hrms.htm?actionFlag=fillCombo&empId="+empId+"&empAllRec=true&newEntryEmpId="+empId;
										else
										//alert('in else'+ empId);
									var url = "./hrms.htm?actionFlag=fillCombo&empAllRec=false&empId="+empId+"&newEntryEmpId="+empId;
								document.frmBF.action=url;
								document.frmBF.submit();
								}
								//return;
		                
						//clearEmployee("EmpSearch");
						
						//return false;		                		                
					}					
					isGpfAcc = vpfDetailsMapping[0].childNodes[2].text;
					if(isGpfAcc==0){
						alert(document.getElementById("Employee_Name_EmpSearch").value+" has not opened the GPF Account");
		                //window.location.reload();	
		                document.frmBF.vpfAmount.value='';
						clearEmployee("EmpSearch");
						document.frmBF.Employee_srchNameText_EmpSearch.value='';
						return false;		                		                
					}
					initBasic=vpfDetailsMapping[0].childNodes[3].text;
					pfAmount = vpfDetailsMapping[0].childNodes[4].text;
					var maxAmount = parseFloat(initBasic);
					var pfAmt = parseFloat(pfAmount);

					/*alert("The Initial Basic is:-"+initialBasic);
					alert("The Value of PF Amount is :-"+pfAmt);										
					alert("The Value of VPF Amount is :-"+vpfAmount);					*/
						if(vpfAmount < pfAmt){
							alert("VPF Amount must be greater then "+pfAmt);
							document.frmBF.vpfAmount.value='';
							document.frmBF.vpfAmount.focus();
							return false;
						}
						else if(vpfAmount > maxAmount){
								 alert("VPF Amount must be Less then "+maxAmount);
								 document.frmBF.vpfAmount.value='';
							     document.frmBF.vpfAmount.focus();
							     return false;
							}
						else if (vpfAmount%10==0)
						{
							return false;
						}
						else
						{
							if(confirm("Do you want to Round Off Your VPF Amount?"))
							{
								
								vpfAmount=Math.abs(vpfAmount)+Math.abs(10-(vpfAmount%10));
								if(	vpfAmount >	maxAmount)
								{
									vpfAmt=Math.abs(vpfAmt)-Math.abs(vpfAmt%10);
									document.frmBF.vpfAmount.value=vpfAmt;
								}	
								else
								{            	
								document.frmBF.vpfAmount.value=vpfAmount;
								}
							}
					    	return true;
						}
				}
				
			}
		}
	}
	
	xmlHttp.open("POST", encodeURI(url) , false);    
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));	
	return true;
	}
	}
		else
		{
		 alert('Please select an Employee')
		 newVpf.value='';
		 return false;
		}
	
	
}

function test()
{
if(confirm('Do you want to stop deduction before 6 months?'))
{
 document.forms[0].zerovpfMonths.checked=true;
 }
else
{
 document.forms[0].zerovpfMonths.checked=false;
}
}
</script>


<hdiits:form name="frmBF" validate="true" method="post"
	action="javascript:beforeSubmit()" encType="text/form-data">


	<div id="tabmenu">
	<ul id="maintab" class="shadetabs" compact="compact">
		<li class="selected"><a href="#" rel="tcontent1"><font
			size="2"><b><fmt:message key="VPF.insertMaster"
			bundle="${enLables}" /></a></li>
		<!-- <li class="selected"><a href="#" rel="tcontent2">INSERT FORM2</a></li> -->
	</ul>
	</div>
	<div class="halftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0"><c:choose>
		<c:when test="${empAllRec!='true'}">
			<c:set value="display:show" var="displayStyle" />
		</c:when>
		<c:otherwise>
			<c:set value="display:none" var="displayStyle" />
			<hdiits:hidden name="Employee_ID_EmpSearch" default="${empId}" />

		</c:otherwise>
	</c:choose>

	<table width="85%" align="center" style="${displayStyle}">
		<tr>
			<TD class="fieldLabel" colspan="2"><jsp:include
				page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
				<jsp:param name="searchEmployeeTitle" value="Search Employee" />
				<jsp:param name="SearchEmployee" value="EmpSearch" />
				<jsp:param name="formName" value="frmBF" />
				<jsp:param name="functionName" value="chkFunc" />
			</jsp:include></td>
			<td><hdiits:hidden id="empId" name="empId" /></td>

		</tr>
	</table>

	<table width="75%" align="center">
		<tr>
			<td colspan="2">&nbsp;</td>
		</tr>

		<tr>
			<td width="30%"><b><font size="2"><b><fmt:message
				key="VPF.vpfAmount" bundle="${enLables}" /></b>&nbsp;&nbsp;</font></td>
			<td width="40%"><hdiits:number mandatory="true" name="vpfAmount"
				caption="vpf Amount" validation="txt.isrequired"
				onblur="return checkAvailability(this)" maxlength="6" /></td>
		</tr>
		<tr>

			<td width="30%"><b><font size="2"><b><fmt:message
				key="VPF.ZEROVPFMONTHS" bundle="${enLables}" /></b>&nbsp;&nbsp;</font></td>
			<td width="40%"><input type="checkbox" name="zerovpfMonths" 
				onclick="test()" value="0"></td>
		</tr>
		
			<tr>
				<td colspan="2"><jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
        	    	    	<jsp:param name="attachmentName" value="vpfAttachment" />
            	    		<jsp:param name="formName" value="frmBF" />
	            	    	<jsp:param name="attachmentType" value="Document" />
				    		<jsp:param name="mandatory" value="N"/>   
				   </jsp:include></td>
		</tr>
		
		
	</table>
	</div>
	<c:choose>
		<c:when test="${empAllRec eq true }">
			<hdiits:hidden
				default="getVPFView&Employee_ID_EmpLoanSearch=${empId}&empAllRec=Y"
				name="givenurl" />
		</c:when>
		<c:otherwise>
			<hdiits:hidden default="getVPFView" name="givenurl" />
		</c:otherwise>
	</c:choose> <hdiits:jsField name="validate" jsFunction="validateForm()" /> 
	<fmt:setBundle basename="resources.eis.eis_common_lables" var="Lables" scope="page" />
	<jsp:include page="../../core/PayTabnavigation.jsp" /></div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		
		if("${empId}"!=null && "${empId}"!=0 && "${empAllRec}"!=null && "${empAllRec}"=='true')
   	    {
   	      document.getElementById('searchEmpDiv').style.visibility = 'hidden';
		  document.getElementById('Employee_ID_EmpSearch').value = "${empId}";
		}
		else
		{
			
		if("${msg}"!=null && "${msg}"!='')
		{
					alert("${msg}");
					if("${empId}"!=null && "${empId}"!=0  && "${empAllRec}"!=null && "${empAllRec}"=='added')
					{
					var url="./hrms.htm?actionFlag=getVPFView&Employee_ID_EmpLoanSearch=${empId}&empAllRec=Y";
					}
					else
					var url="./hrms.htm?actionFlag=getVPFView";
					
					document.frmBF.action=url;
					document.frmBF.submit();
		}
		}		
		if('${Name}'!= null && '${Name}'!= "")
		{	
				document.frmBF.Employee_srchNameText_EmpSearch.value='${Name}';
				GoToNewPageEmpSearch();
		}
		</script>
	<hdiits:validate controlNames="text"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

