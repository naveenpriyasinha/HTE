<%
try {
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
<script type="text/javascript" src="script/hrms/eis/commonUtils.js"></script>


<c:set var="resultObj" value="${result}" > </c:set>
<c:set  var="hrEssQtrMst" value='${resultObj.resultValue.hrEssQtrMst}' />
<c:set  var="empName" value='${resultObj.resultValue.empName}' />
<c:set  var="empId" value='${resultObj.resultValue.empId}' />
<c:set  var="sevarthId" value='${resultObj.resultValue.sevarthId}' />
<c:set  var="quaterTypeList" value='${resultObj.resultValue.quaterTypeList}' />
<c:set  var="quaterRateTypeList" value='${resultObj.resultValue.quaterRateTypeList}' />
<c:set var= "Quartername" value='${resultObj.resultValue.Quartername }' />
<c:set var= "QuarterAllocStartDate" value='${resultObj.resultValue.QuarterAllocStartDate }'/>
<c:set var= "QuarterAllocEndDate" value='${resultObj.resultValue.QuarterAllocEndDate }'/>
<c:set var= "QuarterType" value='${resultObj.resultValue.QuarterType }'/>
<c:set var= "RateType" value='${resultObj.resultValue.RateType }'/>
<c:set var= "QuarterAllocEndDate" value='${resultObj.resultValue.QuarterAllocEndDate }'/>
<c:set var= "EmpFname" value='${resultObj.resultValue.EmpFname }'/>
<c:set var= "EmpMname" value='${resultObj.resultValue.EmpMname }'/>
<c:set var= "EmpLname" value='${resultObj.resultValue.EmpLname }'/>
<c:set var= "QuarterId" value='${resultObj.resultValue.QuarterId }'/>
<c:set var= "UserId" value='${resultObj.resultValue.UserId }'/>
<c:set var= "ownHouseFlag" value='${resultObj.resultValue.ownHouseFlag}'/>

<c:set var="custodianList" value='${resultObj.resultValue.custodianList}'></c:set>


<c:set var="msg" value="${resultObj.resultValue.msg}" ></c:set>

<!-- added by ravysh -->
<c:set var="FromBasicDtlsNew" value="${resultObj.resultValue.FromBasicDtlsNew}" ></c:set>
<c:set var="otherId" value="${resultObj.resultValue.otherId}" ></c:set>

<script>






function toggle()
{
	if(document.getElementById("isVacant").checked==true) 
	{
	   document.getElementById("vacantLetterNo").readOnly=false;
	   //document.getElementById("vacantLetterNo").value = "";
	   document.getElementById("letterDate").readOnly=false;
	   //document.getElementById("letterDate").value = "";
	   document.getElementById("vacantDate").readOnly=false;
	   //document.getElementById("vacantDate").value = "";
	}
	else
	{
		document.getElementById("vacantLetterNo").readOnly=true;
		document.getElementById("vacantLetterNo").value = "";
		document.getElementById("letterDate").readOnly=true;
		document.getElementById("letterDate").value = "";
		document.getElementById("vacantDate").readOnly=true;
		document.getElementById("vacantDate").value = "";
	}
	
}

function chkFunc()
{
/*	document.getElementById("quarterName").value=document.EditQuarterDtls.quarterType.options[document.EditQuarterDtls.quarterType.selectedIndex].text;

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
		return true;
	
	this code is for restrict a Fix Pay and contarct employee 
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
    	alert(" Allocation Date can be greater than today date.");
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

function beforeSubmit()
{
	document.EditQuarterDtls.action="./hrms.htm?actionFlag=insertUpdateQtrDtls&quarterId=${resultObj.resultValue.QuarterId}&edit=Y&USER_ID_empQuater=${UserId}"
	document.EditQuarterDtls.submit();
}

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

function validateValue()
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
		document.getElementById('qtrRentAmt').disbled=true;

		document.getElementById('qtrSrvcChrgeAmt').value=0;
		document.getElementById('qtrSrvcChrgeAmt').readOnly=true;
		document.getElementById('qtrSrvcChrgeAmt').disbled=true;


		document.getElementById('qtrGarageAmt').value=0;
		document.getElementById('qtrGarageAmt').readOnly=true;
		document.getElementById('qtrGarageAmt').disbled=true;
				
	}
	else
	{
		document.getElementById('qtrRentAmt').readOnly=false;
		document.getElementById('qtrSrvcChrgeAmt').readOnly=false;
		document.getElementById('qtrGarageAmt').readOnly=false;
	}
	toggle();
	
}
function getVcateDate()
{
	alert("====");
	if(document.getElementById("isVacant").checked==false)
	{
		if(document.getElementById("isVacant").value=="")
		{
			alert("Please enter vacate date");
			document.getElementById("vacantDate").focus();
		}
	}
}
function submitButton_formSubmitButton()
{

	//alert("===> rdoRentFree ::"+document.getElementById("rdoRentFree").checked);
	//alert("===> rdoRented ::"+document.getElementById("rdoRented").checked);
	
	//alert("===> rdoSelf::"+document.getElementById("rdoSelf").checked);
	//alert("===> rdoOther::"+document.getElementById("rdoOther").checked);
	
	
	if(document.getElementById("quarterType").value==-1)
	{
		alert("Please select quarter type");
		document.getElementById("quarterType").focus();
		return;
	}
	if(document.getElementById("qtrRentAmt").value=="")
	{
		alert("Please enter rent");
		document.getElementById("qtrRentAmt").focus();
		return;
	}

	if(document.getElementById("qtrSrvcChrgeAmt").value=="")
	{
		alert("Please enter service charges");
		document.getElementById("qtrSrvcChrgeAmt").focus();
		return;
	}

	if(document.getElementById("qtrGarageAmt").value=="")
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
	//if(document.getElementById("rdoAddressCityquaterAddress").checked==false)
	//{
	//	alert("Please select address type"); 
	//	return;
	//}
	
	//if(document.getElementById("txtAreaquaterAddress").value=="")
	//{
	//	alert("Please enter area");
	//	return;
	//}
	//if(document.getElementById("cmbCityquaterAddress").value=="Select")
	//{
	//	alert("Please select city");
	//	return;
	//}
	
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
		  document.EditQuarterDtls.action='./hrms.htm?actionFlag=insertUpdateQtrDtls&quarterId=${resultObj.resultValue.hrEssQtrMst.quarterId}&edit=Y&USER_ID_empQuater=${UserId}&FromBasicDtlsNew=${FromBasicDtlsNew}&otherId=${otherId}';
		  document.EditQuarterDtls.submit();
	  }
	  
}
function fnonclose()
{
	if(document.forms[0].givenurl!=null)
	{
	var url="./hrms.htm?actionFlag="+document.forms[0].givenurl.value+"&elementId=9000225";
	
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
		document.getElementById('qtrSrvcChrgeAmt').readOnly=false;
		document.getElementById('qtrGarageAmt').readOnly=false;
	}
}

</script>


<body>

<hdiits:form name="EditQuarterDtls" validate="true" action="" method="post" encType="text/form-data">
	
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="qtr.quarterMasterUpdate" bundle="${commonLables}"/></b></a></li>
	</ul>
</div>
	
	<br/>
<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >
	<br/>

<TABLE WIDTH="85%" ALIGN="CENTER">
	<TR>
	<TD>

	<br/>

	<TABLE width="50%" align="center" border="1" RULES=NONE FRAME=BOX BORDERCOLOR="BD6C03">
				<tr>
					<td  align="left" ><b><fmt:message key="OT.empName" bundle="${commonLables}" /></b></td>
					<TD  align="left" ><hdiits:text name="EmpName" size="20" maxlength="20" readonly="true" caption="Employee Name" onblur="checkSplChar(this)" default="${empName}" title="${empName}"  /></TD>
				<!-- 	<td width="5%" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				 	<td  width="16%" align="left" >	</td>
					<td width="30%" align="left"></td> -->
				</tr>
				<tr>
					<td  align="left" style="display:none;"><b><fmt:message key="OT.empId" bundle="${commonLables}" /></b></td>
					<TD  align="left" style="display:none;"><hdiits:text name="empId" size="20" maxlength="20" readonly="true" caption="Employee Id" onblur="checkSplChar(this)" default="${empId}" /></TD>
			
					<td  align="left" ><b><fmt:message key="OT.sevarthId" bundle="${commonLables}" /></b></td>
					<TD  align="left"><hdiits:text name="sevarthId" size="20" maxlength="20" readonly="true" caption="Sevarth Id" onblur="checkSplChar(this)" default="${sevarthId}" /></TD>
				<!-- 	<td width="5%" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				 	<td  width="16%" align="left" >	</td>
					<td width="30%" align="left"></td> -->
				</tr>
				
				<tr>
					<td  align="left" ><b><fmt:message key="OT.quaterId" bundle="${commonLables}" /></b></td>
					<TD  align="left" ><hdiits:text name="QtrId" size="20" maxlength="20" readonly="true" caption="Quater Id" onblur="checkSplChar(this)" default="${QuarterId}" /></TD>
				<!--	<td width="5%" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<TD  width="16%" align="left" >	</td>
					<td width="30%" align="left"></td>-->
				
				</tr>
				
 <!--
	<Td align="right" width="15%" style="display:none"><b><fmt:message key="qtr.OwnHouse" bundle="${commonLables}"/></b></td>
	<td width="15%" style="display:none"><input type="checkbox" id="OwnHouse" name="OwnHouse" value="1" onclick="selectedEconimic();" >
	</Td>
   Ended By Varun For Quarter Rule 
	
		<TD align="right" width="15%" style="display:none"><b><fmt:message key="qtr.quarterRateType" bundle="${commonLables}"/></b></TD>

				<TD width="15%" style="display:none"><hdiits:select captionid="qtr.quarterRateType" bundle="${commonLables}" name="quarterRateType" id="quarterRateType" size ="1" sort="false" >
			<hdiits:option value="-1" >-------------Select--------------</hdiits:option>
				<c:forEach var="quaterRateTypeList" items="${quaterRateTypeList}">
				<c:choose>
						<c:when test="${quaterRateTypeList.lookupId eq hrEssQtrMst.rateTypeLookup.lookupId}">
						<option value='<c:out value="${quaterRateTypeList.lookupId}"/>' selected="true">
						 	<c:out value="${quaterRateTypeList.lookupDesc}"/>
						</c:when>
						<c:otherwise>
							<option value='<c:out value="${quaterRateTypeList.lookupId}"/>' >
						<c:out value="${quaterRateTypeList.lookupDesc}"/>
						</c:otherwise> 
				</c:choose>  
			    </c:forEach> 
	    	 	</hdiits:select>
               
		</TD>	
		<td width="8%" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
 -->
					
	
	</table>	
	
	<br> <br>
	
	<table width="50%" align="center" border="1" RULES=NONE FRAME=BOX BORDERCOLOR="BD6C03" >
		<tr>
			<TD  align="left" >
			<b><fmt:message key="Quarter.Type"  bundle="${commonLables}"/></b>
			</td>
		<%-- 	<td  align="left" >
			<hdiits:hidden name="rdotypeOfAccom"  default="${hrEssQtrMst.quarterGovtType.lookupId}"/>
			<hdiits:hidden name="rdoQuarterAlloted"  default="${hrEssQtrMst.quarterAllocatedToType.lookupId}"/>
		--%>
			<td align="left">
			<c:choose>
			<c:when test="${hrEssQtrMst.quarterGovtType.lookupId=='10001198124'}" >
				<hdiits:radio name="rdotypeOfAccom" id="rdoRentFree" value="true" default="true" captionid="Quarter.rentfree" bundle="${commonLables}" onclick="disableTextBoxes()"/>
				<hdiits:radio name="rdotypeOfAccom" id="rdoRented" value="false"  captionid="Quarter.rented"  bundle="${commonLables}" onclick="disableTextBoxes()"/>
			</c:when> 
			<c:otherwise>
				<hdiits:radio name="rdotypeOfAccom" id="rdoRentFree" value="true"  captionid="Quarter.rentfree" bundle="${commonLables}" onclick="disableTextBoxes()"/>
				<hdiits:radio name="rdotypeOfAccom" id="rdoRented" value="false"  default="false" captionid="Quarter.rented"  bundle="${commonLables}" onclick="disableTextBoxes()"/>
			</c:otherwise>
     		</c:choose>
			</td>
		</tr>
		<tr></tr>
		
		<tr>
			
			<td align="left">
			<b><fmt:message key="Quarter.Allowto" bundle="${commonLables}"/></b> 
			</td>
			<td align="left">
			<c:choose>
			<c:when test="${hrEssQtrMst.quarterAllocatedToType.lookupId=='110040'}" >
				<hdiits:radio name="rdoQuarterAlloted" id="rdoSelf" value="true" default="true"  captionid="Quarter.self" bundle="${commonLables}" onclick="validateValue()" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<hdiits:radio name="rdoQuarterAlloted" id="rdoOther" value="false"  captionid="Quarter.Others"  bundle="${commonLables}" onclick="validateValue()" />
			</c:when> 
			<c:otherwise>
				<hdiits:radio name="rdoQuarterAlloted" id="rdoSelf"  value="true"   captionid="Quarter.self" bundle="${commonLables}" onclick="validateValue()" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<hdiits:radio name="rdoQuarterAlloted" id="rdoOther"  value="false"  default="false" captionid="Quarter.Others"  bundle="${commonLables}" onclick="validateValue()" />
			</c:otherwise>
			</c:choose>
			</td>
		</tr>
		<tr>
			<TD align="left" width="20%"><b><fmt:message key="qtr.quaterType" bundle="${commonLables}"/></b></TD>
			<TD width="15%"><hdiits:select captionid="qtr.quaterType" bundle="${commonLables}" validation="sel.isrequired" name="quarterType" id="quarterType" size ="1" sort="false"  mandatory="true"  onchange="chkFunc()">
			<hdiits:option value="-1" >---------------------Select-------------------</hdiits:option>
		     	<c:forEach var="quaterTypeList" items="${quaterTypeList}">
				   <c:choose>
					 <c:when test="${quaterTypeList.quaId eq hrEssQtrMst.hrQuaterTypeMst.quaId}">
						<option value='<c:out value="${quaterTypeList.quaId}"/>' selected="true">
						 <c:out value="${quaterTypeList.quaType}"/> <c:out value="( ${quaterTypeList.quaRent} )"></c:out>
					</c:when>
					  <c:otherwise>
						 <option value='<c:out value="${quaterTypeList.quaId}"/>' >
							<c:out value="${quaterTypeList.quaType}"/><c:out value="( ${quaterTypeList.quaRent} )"></c:out>
						</c:otherwise> 
				</c:choose>  
				
		    	</c:forEach> 
			
			</hdiits:select>
			</TD>
		</tr>
		<tr/><tr/>
		
		
		<tr>
			<TD align="left" width="20%"><b><fmt:message key="qtr.custodianType" bundle="${commonLables}"/></b></TD>
			<TD width="15%"><hdiits:select captionid="qtr.custodianType" bundle="${commonLables}" validation="sel.isrequired" name="custodianType" id="custodianType" size ="1" sort="false"  mandatory="false" >
			<hdiits:option value="-1" >---------------------Select-------------------</hdiits:option>
		     	<c:forEach var="custodianList" items="${custodianList}">
				   <c:choose>
					 <c:when test="${custodianList.custodianId eq hrEssQtrMst.hrCustodianTypeMst.custodianId}">
						<option value='<c:out value="${custodianList.custodianId}"/>' selected="true">
						 <c:out value="${custodianList.custodianDesc}"/>
					</c:when>
					  <c:otherwise>
						 <option value='<c:out value="${custodianList.custodianId}"/>' >
							<c:out value="${custodianList.custodianDesc}"/>
						</c:otherwise> 
				</c:choose>  
				
		    	</c:forEach> 
			
			</hdiits:select>
			</TD>
		</tr>
		<TR >
			<TD align="left" width="20%"><b><fmt:message key="qtr.allocationDate" bundle="${commonLables}"/></b></TD>
			<TD width="30%">
				<hdiits:dateTime  name="allocStartDate" caption="qtr.allocationDate"  default="${hrEssQtrMst.allocationStartDate}"  disabled="true" minvalue="" bundle="${commonLables}"  ></hdiits:dateTime>
		</TD>
	</tr>
	</table>
	
	<br> 
	<TABLE width="85%" align="center" border="1" RULES=NONE FRAME=BOX BORDERCOLOR="BD6C03">
	<TBODY>
	<!-- <TR>
		<TD align="right" width="10%" style="display:none"><b><fmt:message key="qtr.allocationDate" bundle="${commonLables}"/></b></TD>
		<TD width="15%" style="display:none">
			 			
		
		</TD>
		<TD align="right" width="15%" style="display:none"><b><fmt:message key="qtr.possessionDate" bundle="${commonLables}"/></b></TD>
		<TD width="15%" style="display:none">
			<hdiits:dateTime  name="possessionDate"  caption="qtr.possessionDate" default="${hrEssQtrMst.possessionDate}" onblur="checkDatefrom()"  bundle="${commonLables}" minvalue="" ></hdiits:dateTime>
		</TD>
		<TD align="right" width="25%" style="display:none"><b><fmt:message key="qtr.vacatDate" bundle="${commonLables}"/></b></TD>
		<TD width="15%" style="display:none">
			<hdiits:dateTime name="allocEndDate"   captionid="qtr.vacatDate"   onblur="checkDatefrom()" default="${hrEssQtrMst.allocationEndDate}" minvalue=""  ></hdiits:dateTime> 
		</TD>
				<TD align="right" width="20%" style="display: none;"><b><fmt:message key="qtr.quaterName" bundle="${commonLables}"/></b></TD>
		<TD width="20%" style="display: none;">
			<hdiits:text name="quarterName"  size="20" maxlength="20" caption="Quater Name" default =" "   />
		</TD>
	</TR>-->
	
	<tr bgcolor="#9F5C04"  id="birthTR"  >
	<td  class="" colspan="10"><font color="#ffffff">
	<strong>Rent Details</strong></font></td>
	
	<tr>
	<TD><b><fmt:message key="Quarter.Rentrs" bundle="${commonLables}"/></b><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></TD>
	<td> <hdiits:number id="qtrRentAmt" name="qtrRentAmt" caption="Rent(Rs.)" mandatory="true" captionid="Quarter.Rentrs" bundle="${commonLables}" default="${hrEssQtrMst.quarterRent}"/> 
	</td>
	<TD><b><fmt:message key="Quarter.ServiceChg" bundle="${commonLables}"/></b><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></TD>
	<td> <hdiits:number id="qtrSrvcChrgeAmt" name="qtrSrvcChrgeAmt" mandatory="true"  caption="Service Charge(Rs.)" default="${hrEssQtrMst.serviceCharge}" 
	captionid="Quarter.ServiceChg" bundle="${commonLables}"/> 
	</td>
	</tr>
	<tr>
	<TD align="left"><b><fmt:message key="Quarter.Garage" bundle="${commonLables}"/></b><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></TD>
	<td> <hdiits:number id="qtrGarageAmt" name="qtrGarageAmt" mandatory="true" default="${hrEssQtrMst.garageCharge}" caption="Garage/Other Charges(Rs.)" captionid="Quarter.Garage" bundle="${commonLables}"/> 
	</td>
	</tr>
	
	
	<tr></tr>
	<tr bgcolor="#9F5C04"  id="birthTR"  >
	<td  class="" colspan="10"><font color="#ffffff">
	<strong>Vacate Quarter Details</strong></font></td>
	
	<tr>
	<TD colspan="2"><b><fmt:message key="Quarter.checkbox" bundle="${commonLables}"/></b>
		<c:choose>
			<c:when test="${hrEssQtrMst.allocationEndDate != null}" >
				<input type="checkbox" name="isVacant" id="taAvailed" value="0" onclick="toggle()" checked>
			</c:when> 
			<c:otherwise>
				<input type="checkbox" name="isVacant" id="taAvailed" value="0" onclick="toggle()" unchecked>
			</c:otherwise>
		</c:choose>
	</TD>
	</tr>
	
	<tr>
	<TD><b><fmt:message key="Quarter.vacateletter" bundle="${commonLables}"/></b></TD>
	<td> <hdiits:text id="vacantLetterNo" name="vacantLetterNo" caption="Vacating Letter No."  default="${hrEssQtrMst.vacantLetterNo}" captionid="Quarter.vacateletter" bundle="${commonLables}"/> 
	</td>
	
	<TD align="left"><b><fmt:message key="Quarter.ldate" bundle="${commonLables}"/></b></TD>
	<td> <hdiits:dateTime  name="letterDate" caption="Quarter letter date"  default="${hrEssQtrMst.vacantOrderDate}" minvalue="" bundle="${commonLables}"  ></hdiits:dateTime> 
	</td>
	</tr>
	<tr>
	<TD align="left"><b><fmt:message key="Quarter.Date" bundle="${commonLables}"/></b></TD>
	<td> <hdiits:dateTime  name="vacantDate" caption="Quarter Date"  default="${hrEssQtrMst.allocationEndDate}" minvalue="" bundle="${commonLables}" mandatory="true" ></hdiits:dateTime> 
	</td>
	</tr>
	
	<br><br>
	
	<tr></tr><tr/>
	<tr bgcolor="#9F5C04"   id="birthTR"  >
	<td  class="" colspan="10"><font color="#ffffff">
	<strong>Quarter Address</strong></font></td>	
	</tr><tr/><tr/><tr/><tr/>
<c:if test="${msg ne 1}">
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
   <%--<hdiits:jsField name="quatername" jsFunction="chkFunc()"/>	--%> 
   <div>
   <hdiits:hidden default="getQuarterDtls&edit=x" name="givenurl"/>
<fmt:setBundle basename="resources.payroll.payrollLables_en_US" var="Lables" scope="request"/>

<!-- added by ravysh --> 	
<c:if test="${FromBasicDtlsNew eq 'YES'}">
<hdiits:hidden default="YES" name="closeWindow"/>
</c:if>
 
<%--- 	<jsp:include page="../../core/PayTabnavigation.jsp" />
--%>	
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
	
</div>

	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
			
			disableTextBoxes();
		toggle();
		if ('${resultObj.resultValue.ownHouseFlag}' == '1')
		{
		document.getElementById("OwnHouse").checked = true;
		document.getElementById("vacantLetterNo").readOnly=true;
		   document.getElementById("letterDate").readOnly=true;
		   document.getElementById("vacantDate").readOnly=true;
		document.getElementById('quarterRateType').value=document.getElementById('quarterRateType').options[3].value;
		document.getElementById('quarterRateType').text=document.getElementById('quarterRateType').options[3].text;
		document.getElementById('quarterRateType').options[0].readOnly=true;
	    }
		
		
		else
		{
		document.getElementById("OwnHouse").checked = false;
		
		}
		
		//document.getElementById('rdoQuarterAlloted').text='${hrEssQtrMst.quarterGovtType}';
		//document.getElementById('rdotypeOfAccom').value='${hrEssQtrMst.quarterAllocatedToType}';
		if("${msg}"!=null&&"${msg}"!='')
		{
			
			
			alert("${msg}");
			
			if("${FromBasicDtlsNew}"=="YES")
			{
				
				window.opener.location.href="./hrms.htm?actionFlag=getOtherDataMerged&otherId=${otherId}&edit=Y&empAllRec=false&MergedScreen=YES&PreviewBill=YES&elementId=9000225";
				 window.close();
			}else{
			
			var url="./hrms.htm?actionFlag=getQuarterDtls&edit=x&elementId=9000225";
			document.EditQuarterDtls.action=url;
			document.EditQuarterDtls.submit();

			}
			}
		
		validateValue();
		
	</script>	
	<hdiits:validate controlNames="tesxt" locale='<%=(String)session.getAttribute("locale")%>' />
	
	<hdiits:jsField jsFunction="validateValue()" name="forvalidation" />
	<hdiits:jsField jsFunction="getVcateDate()" name="forvalidationVacatedate" />


</hdiits:form>
</body>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

		