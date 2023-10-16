<%

try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page session="true" %>
<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>

<script type="text/javascript"  
    src="common/script/commonfunctions.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="msg" value="${resValue.msg}" ></c:set>
<c:set var="rltBillList" value="${resValue.rltBillList}" ></c:set>
<c:set var="empName" value="${resValue.empName}" ></c:set>
 <script><!--
function chkFunc()
{
	
	var empId=document.getElementById("Employee_ID_EmpSearch").value;
	document.miscRecover.empName.value=empId;
	var retValue=true;
	if(empId=="")
	{
		alert("Please search the employee first");
		retValue=false;
		
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
				}
			}
		}
		var url = "hrms.htm?actionFlag=getMiscData&empId="+empId+"&chk=1&date="+document.getElementById("date").value;  
	    xmlHttp.onreadystatechange = function(){		
			if(xmlHttp.readyState == 4){     			
				if (xmlHttp.status == 200){	
					
					var LoanNo;
					var XMLDocForAjax=xmlHttp.responseXML.documentElement;
					var eisEmpMapping = XMLDocForAjax.getElementsByTagName('eisEmpMapping');	
					if(eisEmpMapping.length != 0) {	
						eisEmpNO = eisEmpMapping[0].childNodes[0].text;	
						emptype=eisEmpMapping[0].childNodes[1].text;
					/*if(emptype==300018 || emptype==300225)
					{
						alert("Not Accessible For Fixed and Contractual Employee!!");
	     			    document.miscRecover.amount.value='';
						clearEmployee("EmpSearch");
						//window.history.back();
						window.self.focus();
						return false;
					}*/
						
						if(eisEmpNO==0){
							var res=confirm("The information for "+document.getElementById("Employee_Name_EmpSearch").value+" is not entered into the system.\n Want To enter Information.");
							document.miscRecover.amount.value='';
							window.self.focus();
							clearEmployee("EmpSearch");
							document.miscRecover.reset();
							if(res){
							
										var url = "./hrms.htm?actionFlag=newEmpData&empAllRec=false&empId=0&newEntryEmpId="+empId;
										document.miscRecover.action=url;
										document.miscRecover.submit();
											retValue=true;
							       		
										}
										else
										{
											retValue=false;
							        	
										}
							
							
							
						}
						if(eisEmpNO==1){
							var res=confirm("The Basic Detail for "+document.getElementById("Employee_Name_EmpSearch").value+" is not entered into the system.\n Want To enter Information.");
							document.miscRecover.amount.value='';
							window.self.focus();
							clearEmployee("EmpSearch");
							document.miscRecover.reset();
							if(res){
							
										var url = "./hrms.htm?actionFlag=fillCombo&empId=0&empAllRec=false&newEntryEmpId="+empId;
										document.miscRecover.action=url;
										document.miscRecover.submit();
											retValue=true;
							       		 return true; 
										}
										else
										{
											retValue=false;
							        		return false; 
										}
							
							
							
							
						}
						
						/*if(eisEmpNO=='y' || eisEmpNO=='Y'){
							alert("The Misclaneous Recovery for "+document.getElementById("Employee_Name_EmpSearch").value+" is entered into the system for the month "+document.getElementById("date").value);
							retValue=false;
							
						}*/
					}
				}
			}
		}
		
		xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));	
		return retValue;
	}
	return retValue;
}




function beforeSubmit()
{
				
		/*cmpDate();*/
		
		//if(chkFunc()==true)
		{
		var empName = document.getElementById("Employee_ID_EmpSearch").value;
		document.miscRecover.empName.value=empName;
		
		document.miscRecover.action="./hrms.htm?actionFlag=insertMiscData&edit=N";
		document.miscRecover.submit();
		}
		/*else
		{
		document.miscRecover.action = 'javascript:beforeSubmit()';
		}*/
}

function chkDateCompare()
{
  
    var sysdate= new Date();
   
    if(document.getElementById("date").value!='' )
    { 
    	
    	var dateday=sysdate.getDate();
    	var datemonth = sysdate.getMonth()+1;
    	var dateYear= sysdate.getFullYear();
    	
    	if(datemonth<10)
    	{
    		datemonth="0"+datemonth;
    	}
    	
    	var dateString = dateday + "/" + datemonth+ "/" +dateYear;
    	
    var diff = compareDate(document.getElementById("date").value,dateString);
    if(diff<0)
    {
    	alert("Recovery Date must be greater than or equal to Current Date");
    	document.getElementById("date").value='';    
    	document.getElementById("date").focus();	
    	return false;
    }
    }
    return;
}

function validateForm()
{
if(chkFunc()==true)
return true
else
return false

}

function calculateAmt()
{
	var miscInst = document.miscRecover.installment.value;
	if(miscInst!=null && miscInst!='')
	{
		if(miscInst==0)
		{
			alert('Installment No must be greater than Zero');
			document.miscRecover.installment.value='';
			document.miscRecover.installment.focus();
			return false;
		}
		var totAmt=document.miscRecover.amount.value;
		var installmentNo = document.miscRecover.installment.value;
		var installmentAmount = totAmt/installmentNo;
		document.miscRecover.installmentAmt.value=installmentAmount;
	}
}

function cmpDate()
{
	 var diff = compareDate(document.miscRecover.date.value,document.miscRecover.endDate.value);   

	 if(document.miscRecover.endDate.value!=null && document.miscRecover.endDate.value!='')
	 {
	 
	 	var MonthDiff=	datediff(document.miscRecover.date.value,document.miscRecover.endDate.value);
	 	if(diff < 0  || MonthDiff==-1)
  	 	{
   			alert("To Date must be greater than From Date");
	   		document.miscRecover.endDate.value='';
   			return false;
  	 	}
	  	
  	 }

}

function chkRecoveredAmt()
{
	var totAmt = document.miscRecover.amount.value;
	var recvAmt = document.miscRecover.recoveredAmt.value;
	
	if(recvAmt!=null && recvAmt!='')
	{
		if(eval(recvAmt) > eval(totAmt))
		{
			alert('Recovered Amount must not greater than Total Amount');
			document.miscRecover.recoveredAmt.value='';
			return false;
		}
	}
}

function chkRecoveredInst()
{
	var totInst = document.miscRecover.installment.value;
	var recvInst = document.miscRecover.recoveredInst.value;
	
	if(recvInst!=null && recvInst!='')
	{
		if(eval(recvInst) > eval(totInst))
		{
			alert('Recovered Installment must not greater than Total Installment');
			document.miscRecover.recoveredInst.value='';
			return false;
		}
	}
}

function chkInstallmentAmt()
{
	if(chkFunc()==true)
	{
		var totAmt = document.miscRecover.amount.value;
	    var instAmt = document.miscRecover.installmentAmt.value;
	    if(eval(instAmt) > eval(totAmt))
	    {
	    	alert('Installment amount must not greater than Total Amount');
	    	document.miscRecover.installmentAmt.value='';
	    	return false;
	    }
	    else
	    	return true;
	}
}
--></script>

<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>


<hdiits:form name="miscRecover" validate="true" method="POST"
	action="javascript:beforeSubmit()" encType="multipart/form-data">

	<div id="tabmenu">
		<ul id="maintab" class="shadetabs" compact="compact">
			<li class="selected"><a href="#" rel="tcontent1"><font size="2"><b><fmt:message key="MISC.miscAdd" bundle="${commonLables}"/></b></a></li>
		</ul>
	</div>

	<div class="halftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0">
	<table width= "65%" align= "center"><br>
		<tr>
			<TD class="fieldLabel" colspan="2">
					<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
						<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
						<jsp:param name="SearchEmployee" value="EmpSearch"/>
						<jsp:param name="formName" value="miscRecover"/>
						<jsp:param name="functionName" value="chkFunc"/>
					</jsp:include>
		 </td>
		  <td>
         <hdiits:hidden id="empName" name = "empName" default="" />
	    </td>
	    
		<tr>
			<td class="fieldLabel" >
				<hdiits:caption captionid="MISC.recoveryType" bundle="${commonLables}"   ></hdiits:caption>
			</td>
		
			<td>
				<hdiits:select 	name="recoveryType" size="1"
		               			id="recoveryType"
                       	captionid="MISC.recoveryType"   validation="sel.isrequired"
                       	mandatory="true" 
                       	bundle="${commonLables}" onchange="">
        	<hdiits:option 	value="">----Select----</hdiits:option>
		 				
		 				<c:forEach var="rltBillList" items="${rltBillList}">
   						<hdiits:option value="${rltBillList.typeEdpId}"><c:out value="${rltBillList.edpShortName}(${rltBillList.edpCode})"> </c:out></hdiits:option>
     				</c:forEach>
		 		</hdiits:select >
	 		</td>
 		</tr>
 		
	   <tr>
			<td><b><hdiits:caption captionid="MISC.amount" bundle="${commonLables}"/></b></td>
			<td><hdiits:number name="amount" default=""  style="text-align:right" captionid="MISC.amount" bundle="${commonLables}"  validation="txt.isrequired,txt.isnumber"  maxlength="10"  mandatory="true" size="20" onblur=""/>  </td>
	    </tr>
	    
	    <tr>
			<td><b><hdiits:caption captionid="MISC.installment" bundle="${commonLables}"/></b></td>
			<td><hdiits:number name="installment" default="" captionid="MISC.installment" bundle="${commonLables}"  validation="txt.isrequired,txt.isnumber"  maxlength="3"  mandatory="true" size="20" onblur="" />  </td>
	    </tr>
	    
	    <tr>
			<td><b><hdiits:caption captionid="MISC.installmentAmt" bundle="${commonLables}"/></b></td>
			<td><hdiits:number name="installmentAmt" default=""  style="text-align:right" captionid="MISC.installmentAmt" bundle="${commonLables}"  validation="txt.isrequired,txt.isnumber"  maxlength="10" readonly="" mandatory="true" size="20" onblur="chkInstallmentAmt()"/>  </td>
	    </tr>
	    
	    <tr>
			<td><b><hdiits:caption captionid="MISC.recoveredAmt" bundle="${commonLables}"/></b></td>
			<td><hdiits:number name="recoveredAmt" default=""  style="text-align:right" captionid="MISC.recoveredAmt" bundle="${commonLables}"  validation="txt.isnumber"  maxlength="10"  mandatory="" size="20" onblur="chkRecoveredAmt()" />  </td>
	    </tr>
	    
	    <tr>
			<td><b><hdiits:caption captionid="MISC.recoveredInst" bundle="${commonLables}"/></b></td>
			<td><hdiits:number name="recoveredInst" default="" captionid="MISC.recoveredInst" bundle="${commonLables}"  validation="txt.isnumber"  maxlength="3"  mandatory="" size="20" onblur="chkRecoveredInst()" />  </td>
	    </tr>
	    

	   
	    <tr>
			<td><b><hdiits:caption captionid="MISC.reason" bundle="${commonLables}"/></b></td>
			<td><hdiits:textarea cols="50" rows="3" name="reason" default="" captionid="MISC.reason" bundle="${commonLables}"  validation="txt.isrequired" onblur="checkSplChar(this)"   mandatory="true" maxlength="100"/> </td>
	    </tr>
	    
	    <tr>
			<td><b><hdiits:caption captionid="MISC.startDate" bundle="${commonLables}"/></b></td>
			<td><hdiits:dateTime captionid="MISC.startDate" bundle="${commonLables}" name="date"  mandatory="true" onblur="chkDateCompare();" validation="txt.isrequired,txt.isdt" /></TD>	
			</tr>
		
			<tr>
			<td><b><hdiits:caption captionid="MISC.endDate" bundle="${commonLables}"/></b></td>
			<td><hdiits:dateTime captionid="MISC.endDate" bundle="${commonLables}" name="endDate"  onblur="cmpDate();" validation="txt.isdt" /></TD>	
			</tr>

		<tr></tr> 		
    <tr>
	     <td colspan="4"><b>Order Upload:-</b>
					   <jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
        	    	    	<jsp:param name="attachmentName" value="orderId" />
            	    		<jsp:param name="formName" value="miscRecover" />
	            	    	<jsp:param name="attachmentType" value="Document" />
				    		<jsp:param name="multiple" value="N" />  
				    		<jsp:param name="mandatory" value="N"/>              
	    				</jsp:include>
	</td></tr>	
	</table>
 	</div>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="Lables" scope="page"/> 
 	 	<hdiits:hidden default="getMiscData" name="givenurl"/>
<hdiits:jsField  name="validate" jsFunction="validateForm()" />	
	<jsp:include page="../../core/PayTabnavigation.jsp" />
	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
	
		if("${empName}"!=null&&"${empName}"!='')
		{
				document.miscRecover.Employee_srchNameText_EmpSearch.value="${empName}";
			    GoToNewPageEmpSearch();
		}
		
		if("${msg}"!=null&&"${msg}"!='')
		{
			alert("${msg}");
			var url="./hrms.htm?actionFlag=getMiscData";
			document.miscRecover.action=url;
			document.miscRecover.submit();
		}
	</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
</body>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

