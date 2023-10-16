<% 
try
{
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>
<script type="text/javascript" src="common/script/tagLibValidation.js"></script>
<script type="text/javascript" src="common/script/commonfunctions.js"></script>
<script type="text/javascript" src="script/eis/commonUtils.js"></script>


<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="quaterTypeList" value='${resultObj.resultValue.quaterTypeList}' />
<c:set var="quaterRateTypeList" value='${resultObj.resultValue.quaterRateTypeList}' />
<c:set var="msg" value="${resultObj.resultValue.msg}" ></c:set>
<c:set var="empName" value="${resultObj.resultValue.empName}" ></c:set>

<!-- added by ravysh -->
<c:set var="FromBasicDtlsNew" value="${resultObj.resultValue.FromBasicDtlsNew}" ></c:set>
<c:set var="otherId" value="${resultObj.resultValue.otherId}" > </c:set>

<!-- Added by Ankit
<c:set var="accomodationTypeList" value="${resultObj.resultValue.accomodationTypeList}" ></c:set>
<c:set var="accomodationTypeList" value="${resultObj.resultValue.allocationTypeList}" ></c:set>
 -->
<c:set var="custodianList" value='${resultObj.resultValue.custodianList}'></c:set>
<script>
//alert("in mst");



function toggle()
{
	if(document.getElementById("isVacant").checked==true) {
	   document.getElementById("vacantLetterNo").disabled=false;
	   document.getElementById("letterDate").disabled=false;
	   document.getElementById("vacantDate").disabled=false;
	}
	else
	{
		document.getElementById("vacantLetterNo").disabled=true;
		   document.getElementById("letterDate").disabled=true;
		   document.getElementById("vacantDate").disabled=true;
	}
	
}

function chkFunc()
{


	var userId=document.getElementById("USER_ID_empQuater").value;
	var retValue=true;
	if(userId=="")
	{
		alert("Please search the employee first");
		document.getElementById("quarterType").value='-1';
		window.self.focus();
		return false;
	}
	else
	{
	//document.getElementById("quarterName").value=document.addQuarterDtls.quarterType.options[document.addQuarterDtls.quarterType.selectedIndex].text;
		/*
		Code to avoid Duplicate Entries of Quarter for a particular Employee

		xmlHttp = GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  }  
	
		*/
			checkDuplication();
			
	}

	
/*	this code is for restrict a Fix Pay and contarct employee 
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
		var url = "hrms.htm?actionFlag=getQuarterDtls&edit=N&userId="+userId;  

	    xmlHttp.onreadystatechange = function(){		
			if(xmlHttp.readyState == 4){     			
				if (xmlHttp.status == 200){	
					var LoanNo;
					var XMLDocForAjax=xmlHttp.responseXML.documentElement;
					
					var loanAdvMapping = XMLDocForAjax.getElementsByTagName('empNameMapping');	
					
					if(loanAdvMapping.length != 0) {	
							var emptype = loanAdvMapping[0].childNodes[0].text;		
							if(emptype==300225 || emptype==300018)
							{
							 clearEmployee("empQuater");
							 document.addQuarterDtls.quarterType.value='-1';
  							 self.focus;
							alert("Not Accessible For Fixed and Contractual Employee!!");
							return false;
							}
						
					}
				}
			}
		}
		
		xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));	
		return true;

	}*/
}
function checkDuplication()
{
	var retValue=true;
	var empId=document.getElementById("USER_ID_empQuater").value;
	if(empId=="")
	{
		alert('Employee Not Found');
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
		//alert('EmpId'+empId);
		var url = "hrms.htm?actionFlag=getQuarterDtls&edit=N&userId="+empId;
  	
		xmlHttp.onreadystatechange = function(){
				
			if(xmlHttp.readyState == 4){     			
				if (xmlHttp.status == 200){	
					var LoanNo;
					var XMLDocForAjax=xmlHttp.responseXML.documentElement;
					var qtrExistFlag = XMLDocForAjax.getElementsByTagName('empNameMapping');	
				
					if(qtrExistFlag.length != 0) {
			    //-1 for no emp record ,0 for ban deatils constarint,1 for add allow flag	
					var hraFlag = qtrExistFlag[0].childNodes[1].text;
					if(hraFlag=="0")
					{
						var qtrFlag = qtrExistFlag[0].childNodes[2].text;
						if(qtrFlag=="true")
						{
							//Divert to Main Quarter Page
							alert('Quarter is already alloted to the Employee.');
							url = "./hrms.htm?actionFlag=getQuarterDtls&edit=x";
							document.addQuarterDtls.action=url;
							document.addQuarterDtls.submit();
				
							return false;
						}
						else
							return true;
											
					}
					else
					{
						alert('Employee availed HRA hence quarter cannot be alloted.');
						url = "./hrms.htm?actionFlag=getQuarterDtls&edit=x";
						document.addQuarterDtls.action=url;
						document.addQuarterDtls.submit();
			
						return false;
						
					}
					
					}
					
				}
			}
		}
		
			

		xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));	
	}

}


/*
function selectedEconimic()
{

if(document.getElementById("OwnHouse").checked == true)
		{		
			  document.getElementById('quarterRateType').value=document.getElementById('quarterRateType').options[3].value;
			  document.getElementById('quarterRateType').text=document.getElementById('quarterRateType').options[3].text;
			  document.getElementById('quarterRateType').readOnly=true;
			 
	    }
	    
else
		{
			  document.getElementById('quarterRateType').value=document.getElementById('quarterRateType').options[0].value;
			  document.getElementById('quarterRateType').text=document.getElementById('quarterRateType').options[0].text;
			  document.getElementById('quarterRateType').readOnly=false;
	   	}
}
*/
function checkDatefrom()
{
   
        var date = new Date();
var d  = date.getDate();
var day = (d < 10) ? '0' + d : d;
var m = date.getMonth() + 1;
var month = (m < 10) ? '0' + m : m;
var yy = date.getYear();
var year = (yy < 1000) ? yy + 1900 : yy;
var currentDate = day + "/" + month + "/" + year;
    var currDiff = compareDate(document.getElementById("allocStartDate").value,currentDate);
     if(currDiff<0)
    {
    	alert(" Allocation Date cannot be greater than current date.");
    	document.getElementById("allocStartDate").value='';    
    	document.getElementById("allocStartDate").focus();	
    	return false;
    }
   
    if(document.getElementById("allocEndDate").value!='' && document.getElementById("allocStartDate").value!='')
    {    	
    var diff = compareDate(document.getElementById("allocStartDate").value,document.getElementById("allocEndDate").value);
    if(diff<=0)
    {
    	alert(" Vacate Date must be Greater then Allocation Date");
    	document.getElementById("allocEndDate").value='';    
    	document.getElementById("allocEndDate").focus();	
    	return false;
    }
    }
    return;
}
function checkEmpInfo()
	{
		var empId=document.getElementById("Employee_ID_empQuater").value;
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
		
		var url = "hrms.htm?actionFlag=getBankDtlsView&empId="+empId+"&chk=1";
		  
	    xmlHttp.onreadystatechange = function(){		
			if(xmlHttp.readyState == 4){     			
				if (xmlHttp.status == 200){	
					var LoanNo;
					var XMLDocForAjax=xmlHttp.responseXML.documentElement;
					var loanAdvMapping = XMLDocForAjax.getElementsByTagName('empNameMapping');	
					
					if(loanAdvMapping.length != 0) {
					    //-1 for no emp record ,0 for ban deatils constarint,1 for add allow flag	
						LoanNo = loanAdvMapping[0].childNodes[0].text;
						
						if(LoanNo==-1){
							var res=confirm("The information for "+document.getElementById("Employee_Name_empQuater").value+" is not entered into the system.\n Want to Insert the information.");
							
							if(res){
							
							var url = "./hrms.htm?actionFlag=newEmpData&newEntryEmpId="+empId;
							document.addQuarterDtls.action=url;
							document.addQuarterDtls.submit();
							retValue=true;
							}
							else
							{
								retValue=false;
							}
						}
						
					}
				}
			}
		}
		
		xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));	
		return retValue;
	}
}
function validateValueSelfOthers()
{
	//alert("===> "+document.getElementById("rdoRentFree").value);
	//alert("===> checked value of rdoRentFree "+document.getElementById("rdoRentFree").checked);

	//alert("===> "+document.getElementById("rdoRented").value);
	//alert("===> checked value of rdoRented "+document.getElementById("rdoRented").checked);
	
	//alert("===> "+document.getElementById("rdoOther").value);
	//alert("===> checked value of rdoOther "+document.getElementById("rdoOther").checked);
	if(document.getElementById("rdoOther").checked==true)
	{
		document.getElementById('qtrRentAmt').value=0;
		document.getElementById('qtrRentAmt').readOnly=true;

		document.getElementById('qtrSrvcChrgeAmt').value=0;
		document.getElementById('qtrSrvcChrgeAmt').readOnly=true;


		document.getElementById('qtrGarageAmt').value=0;
		document.getElementById('qtrGarageAmt').readOnly=true;
				
	}
	else
	{
		document.getElementById('qtrRentAmt').readOnly=false;
		document.getElementById('qtrSrvcChrgeAmt').readOnly=false;
		document.getElementById('qtrGarageAmt').readOnly=false;
	}
	
	
}
function getVcateDate()
{
	alert("===="+document.getElementById("isVacant").value);
	alert("====>"+document.getElementById("vacantDate").value);
	
	var isvact = document.getElementById("isVacant").value;
	if(document.getElementById("isVacant").checked==true)
	{
		if(document.getElementById("vacantDate").value=="")
		{
			alert("Please enter vacate date");
			document.getElementById("vacantDate").focus();
			return false;
		}
	}
	else
	{
	 return true; 
	}
}
function submitButton_formSubmitButton()
{
	if(document.getElementById("quarterType").value==-1)
	{
		alert("Please select quarter type");
		document.getElementById("quarterType").focus();
		return;
	}
	if(document.getElementById("qtrRentAmt").value=="" && document.getElementById("rdoRented").value == true)
	{
		alert("Please enter rent");
		document.getElementById("qtrRentAmt").focus();
		return;
	}

	if(document.addQuarterDtls.allocStartDate.value == "")
	{
		
			alert("Please enter allocation date.");
			document.addQuarterDtls.allocStartDate.focus();
			return;
	}

	if(document.getElementById("qtrSrvcChrgeAmt").value=="" && document.getElementById("rdoRented").value == true)
	{
		alert("Please enter service charges");
		document.getElementById("qtrSrvcChrgeAmt").focus();
		return;
	}

	if(document.getElementById("qtrGarageAmt").value=="" && document.getElementById("rdoRented").value == true)
	{
		alert("Please enter garage/other charges");
		document.getElementById("qtrGarageAmt").focus();
		return;
	}
	
	if(document.getElementById("isVacant").checked==true)
	{
		if(document.getElementById("vacantDate").value=="")
		{
			alert("Please enter vacate date");
			document.getElementById("vacantDate").focus();
			return;
		}
	}

	//alert("=====> "+document.getElementById("rdoAddressCityquaterAddress").checked);
	if(document.getElementById("cmbCountryquaterAddress").value=="Select")
	{
		alert("Please select Country"); 
		return;
	}
	if(document.getElementById("cmbStatequaterAddress").value=="Select")
		
	{
		alert("Please select State"); 
		return;
	}
	if(document.getElementById("txtOtherHouseNamequaterAddress").value=="" || document.getElementById("txtOtherHouseNamequaterAddress").value==" " )
	{
		alert("Please select HouseNo/QuarterNo"); 
		return;
	}
	if(document.getElementById("txtOtherSocietyNamequaterAddress").value=="" || document.getElementById("txtOtherSocietyNamequaterAddress").value==" " )
	{
		alert("Please select Society/Building/Office"); 
		return;
	}
	if(document.getElementById("txtOtherAreaquaterAddress").value=="" || document.getElementById("txtOtherAreaquaterAddress").value==" " )
	{
		alert("Please select Area"); 
		return;
	}
	
	
	
	
	

	var answer = confirm ("Do you want to submit?");
	  if (answer)
	  {
		  document.addQuarterDtls.action='hrms.htm?actionFlag=insertUpdateQtrDtls&edit=N&FromBasicDtlsNew=${FromBasicDtlsNew}&otherId=${otherId}';
		  document.addQuarterDtls.submit();
	  }
	  
}
function fnonclose()
{
	if(document.forms[0].givenurl!=null)
	{
	var url="./hrms.htm?actionFlag="+document.forms[0].givenurl.value;
	document.forms[0].action=url;
	}
	else
	{
	document.forms[0].action="./hrms.htm?actionFlag=getHomePage";
	}
	document.forms[0].submit();
}
		
function resetForm()
{
	if(confirm("All entered values will be cleared, please confirm!") == true)
	{
		document.forms[0].reset();
	}			  				  	
}

function disableTextBoxes()
{
	
	if(document.getElementById("rdoRentFree").checked==true)
	{
		document.getElementById('qtrRentAmt').value=0;
		document.getElementById('qtrRentAmt').readOnly=true;

		document.getElementById('qtrSrvcChrgeAmt').value=0;
		document.getElementById('qtrSrvcChrgeAmt').readOnly=true;


		document.getElementById('qtrGarageAmt').value=0;
		document.getElementById('qtrGarageAmt').readOnly=true;
				
	}
	else
	{
		document.getElementById('qtrRentAmt').readOnly=false;
		document.getElementById('qtrRentAmt').value=0;
		document.getElementById('qtrSrvcChrgeAmt').readOnly=false;
		document.getElementById('qtrSrvcChrgeAmt').value=0;
		document.getElementById('qtrGarageAmt').readOnly=false;
		document.getElementById('qtrGarageAmt').value=0;
	}
}


</script>


<body>
<hdiits:form name="addQuarterDtls" validate="" action="" method="post">
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="qtr.quarterMasterAdd" bundle="${commonLables}"/></b></a></li>
	</ul>
</div>
	
	
<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >
	<br/>

<TABLE WIDTH="100%" ALIGN="CENTER">
<TR>
<TD>


<table width="85%" align="center" border="1"><br/>
		<tr>
		<TD  align="center" colspan="4">
					<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
						<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
						<jsp:param name="SearchEmployee" value="empQuater"/>
						<jsp:param name="formName" value="empQuater"/>
						<jsp:param name="functionName" value="checkEmpInfo"/>
					</jsp:include>
		 </td>
		 <td>
	         <hdiits:hidden id="empId" name = "empId" />
        
	    </td>				
		</tr>
</table>
</br>
<table width="50%" align="center" border="1" RULES=NONE FRAME=BOX BORDERCOLOR="BD6C03" >
	  <tr style="background-color:#bebebe">
			<TD align="left" >
			<b><fmt:message key="Quarter.Type" bundle="${commonLables}"/></b>
			</TD>
			<td align="left" >
				<hdiits:radio name="rdotypeOfAccom" id="rdoRentFree" value="true"  captionid="Quarter.rentfree" bundle="${commonLables}" onclick="disableTextBoxes()"/>
				<hdiits:radio name="rdotypeOfAccom" id="rdoRented" value="false"  default="false" captionid="Quarter.rented"  bundle="${commonLables}" onclick="disableTextBoxes()"/>
			</td>
		</tr>
		<tr></tr>
		<tr></tr>
		<tr></tr>
	<!-- 		<td width="8%" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td> -->
		<tr style="background-color:#bebebe">
			<TD align="left" >
			<b><fmt:message key="Quarter.Allowto" bundle="${commonLables}"/></b> </td>
			<td align="left">
				<hdiits:radio name="rdoQuarterAlloted" id="rdoSelf" value="true" default="true" captionid="Quarter.self" bundle="${commonLables}" onclick="validateValueSelfOthers()"/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<hdiits:radio name="rdoQuarterAlloted" id="rdoOther" value="false" captionid="Quarter.Others"  bundle="${commonLables}" onclick="validateValueSelfOthers()"/>
			</td>
		</tr>
		<tr></tr>
		<tr></tr>
		<tr></tr>
		<TR style="background-color:#bebebe">
		<!--<TD align="left" width="20%"><b><fmt:message key="qtr.quaterType" bundle="${commonLables}"/></b></TD>
		<TD width="30%"><hdiits:select captionid="qtr.quaterType" bundle="${commonLables}" validation="sel.isrequired" name="quarterType" id="quarterType" size ="1" sort="false"  mandatory="true"  onchange="chkFunc()">
			<hdiits:option value="-1" >---------------Select Quarter Type---------------</hdiits:option>
				<c:forEach var="quaterTypeList" items="${resultObj.resultValue.quaterTypeList}"> 
					<option value='<c:out value="${quaterTypeList.quaId}"/>' >
						<c:out value="${quaterTypeList.quaType}"/> <c:out value="( ${quaterTypeList.quaRent} )"/>
		    	</c:forEach>
			</hdiits:select>
		</TD>-->
		
		<td align="left" width="20%"><b><fmt:message key="qtr.quaterType" bundle="${commonLables}"/></b></td>
									
		<td width="30%"> 
				<input type="text" captionid="qtr.quaterType" bundle="${commonLables}" validation="sel.isrequired" name="quarterType" id="quarterType" size ="30%" sort="false"  mandatory="true"  onchange="chkFunc()"/>
				<label class="mandatoryindicator">*</label>	
		</td>
		
		
		
		<tr></tr>
				<tr></tr>
				<tr></tr>
				
				
				<TR style="background-color:#bebebe">
				
				<!--<TD align="left" width="20%"><b><fmt:message key="qtr.custodianType" bundle="${commonLables}"/></b></TD>
				<TD width="30%"><hdiits:select captionid="qtr.custodianType" bundle="${commonLables}" validation="sel.isrequired" name="custodianType" id="custodianType" size ="1" sort="false"  mandatory="false" >
					<hdiits:option value="-1" >---------------Select Custodian Type---------------</hdiits:option>
						<c:forEach var="custodianList" items="${resultObj.resultValue.custodianList}"> 
							<option value='<c:out value="${custodianList.custodianId}"/>' >
								<c:out value="${custodianList.custodianDesc}"/>
				    	</c:forEach>
					</hdiits:select>
				</TD>
				
				
				
				--><td align="left" width="20%"><b><fmt:message key="qtr.custodianType" bundle="${commonLables}"/></b></td>
									
		<td width="30%"> 
				<input type="text" captionid="qtr.custodianType" bundle="${commonLables}" validation="sel.isrequired" name="custodianType" id="custodianType" size ="30%" sort="false"  mandatory="false"/>
				
		</td>

   <!-- Addded By Varun For Quarter Rule -->  
	<Td align="right" width="20%" style="display:none"><b><fmt:message key="qtr.OwnHouse" bundle="${commonLables}"/></b></td>
	<td width="15%"  style="display:none"><input type="checkbox" id="OwnHouse" name="OwnHouse" value="1" onclick="selectedEconimic();">
	</Td>
   <!-- Ended By Varun For Quarter Rule -->  

	<!--  <TD align="right" width="20%"  style="display:none"><b><fmt:message key="qtr.quarterRateType" bundle="${commonLables}"/></b></TD>
		<TD width="20%"  style="display:none"><hdiits:select captionid="qtr.quarterRateType" bundle="${commonLables}" name="quarterRateType" id="quarterRateType" size ="1" sort="false">
			<hdiits:option value="-1" >-------------Select--------------</hdiits:option>
				<c:forEach var="quaterRateTypeList" items="${resultObj.resultValue.quaterRateTypeList}">
					<option value='<c:out value="${quaterRateTypeList.lookupId}"/>' >
						<c:out value="${quaterRateTypeList.lookupDesc}"/>
		    	</c:forEach>
			</hdiits:select>
		</TD>	-->	
	</tr>
	<tr></tr>
				<tr></tr>
				<tr></tr>
	<TR style="background-color:#bebebe">
		<TD align="left" width="20%"><b><fmt:message key="qtr.allocationDate" bundle="${commonLables}"/></b></TD>
		<TD width="30%">
			<hdiits:dateTime   name="allocStartDate" caption="qtr.allocationDate"  onblur="checkDatefrom()"  minvalue="" bundle="${commonLables}" mandatory="true" ></hdiits:dateTime>
		</TD>
	</tr>
</table>
	<br> 
<TABLE width="85%" align="center" border="1" RULES=NONE FRAME=BOX >
<TBODY>
	
	<TR>
<!--		<TD align="right" width="15%"  ><b><fmt:message key="qtr.allocationDate" bundle="${commonLables}"/></b></TD>-->
<!--		<TD width="15%"  >-->
<%-- 			<hdiits:dateTime  name="allocStartDate" caption="qtr.allocationDate"  onblur="checkDatefrom()"  minvalue="" bundle="${commonLables}"  ></hdiits:dateTime>--%>
<!--		</TD>-->
		<TD align="right" width="15%"  style="display:none"><b><fmt:message key="qtr.possessionDate" bundle="${commonLables}"/></b></TD>
		<TD width="15%"  style="display:none">
			<hdiits:dateTime  name="possessionDate" caption="qtr.possessionDate"  onblur="checkDatefrom()" minvalue="" bundle="${commonLables}"  ></hdiits:dateTime>
		</TD>
		<TD align="right" width="15%"   style="display:none"><b><fmt:message key="qtr.vacatDate" bundle="${commonLables}"/></b></TD>
		<TD width="15%"   style="display:none">
			<hdiits:dateTime name="allocEndDate"   caption="qtr.vacatDate" minvalue="" onblur="checkDatefrom()" bundle="${commonLables}"></hdiits:dateTime>
		</TD>
<!-- 		<TD align="right" width="15%" style="display: none;"><b><fmt:message key="qtr.quaterName" bundle="${commonLables}"/></b></TD>
<TD width="15%" style="display: none;">
			<hdiits:text name="quarterName" size="20" maxlength="20" caption="Quater Name"  default=" "  />
		</TD>
	--></TR>
	
	<tr></tr>
	<tr bgcolor="#9F5C04"  id="birthTR"  >
	<td  class="" colspan="10"><font color="#ffffff">
	<strong>Rent Details</strong></font></td>
	
	<tr>
	<TD><b><fmt:message key="Quarter.Rentrs" bundle="${commonLables}"/></b><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></TD>
	<td> <hdiits:number id="qtrRentAmt" name="qtrRentAmt" caption="Rent(Rs.)" captionid="Quarter.Rentrs" bundle="${commonLables}" mandatory="true" validation="txt.isrequired"/> 
	</td>
	<TD><b><fmt:message key="Quarter.ServiceChg" bundle="${commonLables}"/></b><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></TD>
	<td> <hdiits:number id="qtrSrvcChrgeAmt" name="qtrSrvcChrgeAmt" caption="Service Charge(Rs.)" captionid="Quarter.ServiceChg" bundle="${commonLables}"  mandatory="true" validation="txt.isrequired"/> 
	</td>
	</tr>
	<tr>
	<TD align="left"><b><fmt:message key="Quarter.Garage" bundle="${commonLables}"/></b><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></TD>
	<td> <hdiits:number id="qtrGarageAmt" name="qtrGarageAmt" caption="Garage/Other Charges(Rs.)" captionid="Quarter.Garage" bundle="${commonLables}"  mandatory="true" validation="txt.isrequired"/> 
	</td>
	</tr>
	
	
	<tr></tr>
	<tr bgcolor="#9F5C04"   id="birthTR"  >
	<td  class="" colspan="10"><font color="#ffffff">
	<strong>Vacate Quarter Details</strong></font></td>
	
	<tr>
	<TD colspan="2"><b><fmt:message key="Quarter.checkbox" bundle="${commonLables}"/></b>
	<input type="checkbox" name="isVacant" id="taAvailed" value="0" onclick="toggle()" unchecked disabled="disabled"> 
	</TD>
	</tr>
	<tr>
	<TD><b><fmt:message key="Quarter.vacateletter" bundle="${commonLables}"/></b></TD>
	<td> <hdiits:text id="vacantLetterNo" name="vacantLetterNo" caption="Vacating Letter No." captionid="Quarter.vacateletter" bundle="${commonLables}" disable="true"/> 
	</td>
	
	<TD align="left"><b><fmt:message key="Quarter.ldate" bundle="${commonLables}"/></b></TD>
	<td> <hdiits:dateTime  name="letterDate" caption="Quarter.ldate"  minvalue="" bundle="${commonLables}"  disabled="true"></hdiits:dateTime> 
	</td>
	</tr>
	<tr>
	<TD align="left"><b><fmt:message key="Quarter.Date" bundle="${commonLables}"/></b></TD>
	<td> <hdiits:dateTime  name="vacantDate" caption="Quarter.Date"  minvalue="" bundle="${commonLables}"  mandatory="true" disabled="true"></hdiits:dateTime> 
	</td>
	</tr>
	
	
		
	<tr></tr>
	<tr bgcolor="#9F5C04"   id="birthTR"  >
	<td  class="" colspan="10"><font color="#ffffff">
	<strong>Quarter Address</strong></font></td>	
	</tr>

<script type="text/javascript" language="javascript">


    
	if("${msg}"!=null&&"${msg}"!='')
		{
			alert("${msg}");

			if("${FromBasicDtlsNew}"=="YES")
			{
				
				window.opener.location.href="./hrms.htm?actionFlag=getOtherDataMerged&otherId=${otherId}&edit=Y&empAllRec=false&MergedScreen=YES&PreviewBill=YES";
				window.close();
			}else{
			
			var url="./hrms.htm?actionFlag=getQuarterDtls&edit=x";
			document.addQuarterDtls.action=url;
			document.addQuarterDtls.submit();
			}
			}
		

</script>
<tr/><tr/>
<c:if test="${msg ne ''}">
<tr>
	<td align="center" colspan="10">
		<table border="1" class="fieldLabel"   width="50%">
			<tr align="center">			
				<td class="fieldLabel" > 
						<jsp:include page="/WEB-INF/jsp/common/address.jsp">
							<jsp:param name="addrName" value="quaterAddress" />
							<jsp:param name="addressTitle" value="Quater Addresses" />
							<jsp:param name="addrLookupName" value="Present Address" />
							<jsp:param name="readOnly" value="Y" />	
						</jsp:include>
				</td>
		
			</tr>
		</table>
	</td>
	</tr>
</c:if>
</TBODY>
</TABLE>
	



</TD>
</TR>


</TABLE>
</div> 
<div>
    <hdiits:jsField name="quatername" jsFunction="chkFunc()"/>	
    <hdiits:jsField jsFunction="getVcateDate()" name="forvalidationVacatedate" />
    
    
<fmt:setBundle basename="resources.eis.eis_common_lables" var="Lables" scope="page"/>
 <hdiits:hidden default="getQuarterDtls&edit=x" name="givenurl"/>
 
 <c:if test="${FromBasicDtlsNew eq 'YES'}">
<hdiits:hidden default="YES" name="closeWindow"/>
</c:if>

		<table class="tabNavigationBar" border="0">
 				<tr>
 					<td width="30%">
					<td align="justify" width="2%">
						<hdiits:button name="btnsave" value="Save" caption="Save"	id="btnsave" captionid="Save" onclick="submitButton_formSubmitButton()" type="button"/>
					</td>
					<td align="justify" width="2%" >
						<hdiits:button name="btnclose" value="Close" caption="Close"	id="btnclose" captionid="Close" onclick="fnonclose()" type="button"/>
					</td>
					<td align="justify" width="2%" >
						<hdiits:button name="btnreset" value="Reset" caption="Reset"	id="btnreset" captionid="Reset" onclick="resetForm()" type="button"/>
					</td>
					<td width="30%">
				</tr>
		</table>	
	
	<hdiits:validate locale="${locale}" />
	
</div>

	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		
		disableTextBoxes();
		
		if("${empName}"!=null&&"${empName}"!='')
       {  
		  document.addQuarterDtls.Employee_srchNameText_empQuater.value="${empName}";
		  GoToNewPageempQuater();
        }
		document.getElementById("vacantLetterNo").disabled=true;
		   document.getElementById("letterDate").disabled=true;
		   document.getElementById("vacantDate").disabled=true;
		   
		   toggle();
	</script>	
	<hdiits:validate controlNames="tesxt" locale='<%=(String)session.getAttribute("locale")%>' />



</hdiits:form>
</body>

<%
}
catch(Exception e){
	e.printStackTrace();
}
%>	


