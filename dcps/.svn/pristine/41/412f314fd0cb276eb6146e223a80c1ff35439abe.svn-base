<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="enLables" scope="page"/>


<script type="text/javascript"
	src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	

<c:set var="nonGovDeducTypes" value="${resValue.nonGovDeducTypes}" > </c:set>
<c:set var="nonGovDeducPeriods" value="${resValue.nonGovDeducPeriods}" > </c:set>
<c:set var="hrPayNonGovDeduction" value="${resValue.hrPayNonGovDeduction}"> </c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>
<fmt:setBundle basename="resources.eis.eis_Constants" var="enConstants" scope="request"/>
<fmt:message var="p1" key="POR" bundle="${enConstants}" scope="request" />
<fmt:message var="p2" key="LIC" bundle="${enConstants}" scope="request" />
<fmt:message var="p3" key="karmcharyBank" bundle="${enConstants}" scope="request" />

<c:set var="empId" value="${resValue.empId}"></c:set>
<c:set var="empAllRec" value="${resValue.empAllRec}"></c:set>
<c:set var="empAllRecFlag" value="${resValue.empAllRecFlag}"></c:set>

<script>
//alert("==> in Non Govt Deduction.........");
function chkKey(e){	
	if(e.keyCode=="13") {
		return false;
	}
	else {		
		return true;
	}
}

function dateDifferent()
{
var t1=document.frmBF.startDate.value;
var t2=document.frmBF.endDate.value;

var _Diff=0;
   //Total time for one day
        var one_day=1000*60*60*24; 
//Here we need to split the inputed dates to convert them into standard format

        var x=t1.split("/");     
        var y=t2.split("/");
  //date format(Fullyear,month,date) 

        var date1=new Date(x[2],(x[1]-1),x[0]);
  
        var date2=new Date(y[2],(y[1]-1),y[0])
        var month1=x[1]-1;
        var month2=y[1]-1;
        
        //Calculate difference between the two dates, and convert to days
               
        _Diff=Math.ceil((date2.getTime()-date1.getTime())/(one_day)); 
        if(_Diff<0)
        {
        document.frmBF.startDate.value='';
        document.frmBF.endDate.value='';
        document.frmBF.startDate.focus();
 
        alert('Start Date Shuld be Greaqter Than End Date');
		}
        
if(${hrPayNonGovDeduction.hrPayDeducTypeMst.deducTypeCode}==${p1})
{
        if(_Diff<1095 && _Diff>0)
        {
        document.frmBF.startDate.value='';
        document.frmBF.endDate.value='';
        alert('This Scheme is Minimun for 3 Years');
        }

        else if(_Diff>1826 && _Diff!=0)
        {
        document.frmBF.startDate.value='';
        document.frmBF.endDate.value='';
        document.frmBF.startDate.focus();
        alert('This Scheme is Maximum for 5 Years');
        
        }
//_Diff gives the diffrence between the two dates.
}
}
function dateDifferent1()
{
var CURDATE = new Date();
		var CURYear = CURDATE.getYear();
		var CURMonth = CURDATE.getMonth();
		var CURDate = CURDATE.getDate();

var t1=document.frmBF.startDate.value;
//var t2=document.frmBF.endDate.value;
var _Diff=0;
   //Total time for one day
        var one_day=1000*60*60*24; 
//Here we need to split the inputed dates to convert them into standard format

        var x=t1.split("/");     
        //var y=t2.split("/");
  //date format(Fullyear,month,date) 

        var date1=new Date(x[2],(x[1]-1),x[0]);
  
        var date2=new Date(CURYear,CURMonth,CURDate);
       
        var month1=x[1]-1;
        var month2=CURMonth;
        
        //Calculate difference between the two dates, and convert to days
               
        _Diff=Math.ceil((date2.getTime()-date1.getTime())/(one_day)); 
    
        if(_Diff<1095 && _Diff!=0)
        {
           

        
         
      		document.frmBF.accno.disabled=true;
			document.frmBF.endDate.disabled=true;
        	document.frmBF.startDate.disabled=true;
        	document.frmBF.deducAmount.disabled=true;
  
  /*        	alert(acno);
        	document.frmBF.accno.value=acno;
        	alert(document.frmBF.accno.value);
			document.frmBF.endDate.value=edate;
        	document.frmBF.startDate.value=sdate;
        	document.frmBF.deducAmount.value=amount;
	var acno=document.frmBF.accno.value
			var edate=document.frmBF.endDate.value
        	var sdate=document.frmBF.startDate.value
        	var amount=document.frmBF.deducAmount.value
      	document.frmBF.accno.value="${resValue.hrPayNonGovDeduction.nonGovDeducAccNo}";
			document.frmBF.endDate.value="${resValue.hrPayNonGovDeduction.nonGovDeducEfftEndDt}";
        	document.frmBF.startDate.value="${resValue.hrPayNonGovDeduction.nonGovDeducEfftStartDt}";
        	document.frmBF.deducAmount.value="${hrPayNonGovDeduction.nonGovDeducAmount}" ;*/
        }
        
//_Diff gives the diffrence between the two dates.

return true;
}
function validation()
{	
	chkuniqueness();
	document.frmBF.accno1.value=document.frmBF.accno.value;
}

function chkuniqueness()
{
	var gpfAcctNo=trim(document.getElementById("accnoid").value);
	var oldaccno = document.getElementById("oldval").value;
	if(gpfAcctNo!="")
	{
	var nonGovDeducType = document.getElementById('deducType').value;
	//alert("Type is:-"+nonGovDeducType);
	if(("${resValue.hrPayNonGovDeduction.nonGovDeducAccNo}"!=gpfAcctNo))
	{
	var retValue=true;
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
	
		var url = "hrms.htm?actionFlag=getNonGovDeducData&gpfAcctNo="+gpfAcctNo+"&chk=1&deducType="+nonGovDeducType;
		
	    xmlHttp.onreadystatechange = function(){
			if(xmlHttp.readyState == 4){    			
				if (xmlHttp.status == 200){
					var gpfNo;
					var XMLDoc=xmlHttp.responseXML.documentElement;			
				    var loanAdvMapping = XMLDoc.getElementsByTagName('empNameMapping');
					if(loanAdvMapping.length != 0 && loanAdvMapping[0].childNodes[0].text!='0')
				    { 
							alert("This Account No. is already entered in the system");
							document.frmBF.accno.value='';
							document.getElementById("accnoid").style.background="#ccccff";
							document.getElementById("accnoid").value = oldaccno;
							document.getElementById("accnoid").focus();
							
						}
					}
				}
			}
		xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));	
		return retValue;
	}
	else
	document.getElementById("accnoid").style.background="";
 }
}

function submit()
{

     var uri = "./hrms.htm?actionFlag=";
  if("${empAllRec}"=='true')
    var url = uri + "insertUpdateNonGovDeductionMaster&nonGovDeducId=${hrPayNonGovDeduction.nonGovDeducId}&edit=Y&empType=${emptypeid}&empAllRec=true";
  else
   var url = uri + "insertUpdateNonGovDeductionMaster&nonGovDeducId=${hrPayNonGovDeduction.nonGovDeducId}&edit=Y&empAllRec=false";

 document.frmBF.action = url;
 document.frmBF.submit();
 
 
 		//document.empLeave.action="./hrms.htm?actionFlag=insertEmpLeaveDtls&empLeaveId=${actionList.empLeaveId}&edit=Y&empType=${emptypeid}"; 
  	    //document.empLeave.submit();
}

</script>


<hdiits:form name="frmBF" validate="true" method="POST"
	action="javascript:submit()" encType="text/form-data">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs" compact="compact">
			<li class="selected"><a href="#" rel="tcontent1"><font size="2"><b><hdiits:caption captionid="NGD.updateNonGovDeductMaster" bundle="${enLables}"></hdiits:caption></a></li>
		</ul>
	</div>
	<br/>

	<div class="halftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >
	<table width="75%" align="center"><br/>
		<tr><td>&nbsp;</td>
			<td align="left" width="20%" ><font size="2"><b><hdiits:caption captionid="NGD.empName" bundle="${enLables}"></hdiits:caption> </b> </td>
			<td align="left" width="30%" ><hdiits:text name="empId" default="${hrPayNonGovDeduction.hrEisEmpMst.orgEmpMst.empFname} ${hrPayNonGovDeduction.hrEisEmpMst.orgEmpMst.empMname} ${hrPayNonGovDeduction.hrEisEmpMst.orgEmpMst.empLname} " size="25" caption="Deduction Name" maxlength="100" readonly="true"/>
		<%--	<hdiits:select name="empId"	
			    captionid="NGD.empName"
				mandatory="true"
			    sort="false"
			    readonly="true"
			    validation="sel.isrequired">
			    <hdiits:option value="-1">------Select---------</hdiits:option>
			    <c:forEach items="${empList}" var="list">
			    <c:choose>
			    <c:when test="${hrPayNonGovDeduction.hrEisEmpMst.empId == list.empId}">
			    	<hdiits:option value="${list.empId}" selected="true">${hrPayNonGovDeduction.hrEisEmpMst.orgEmpMst.empFname} ${hrPayNonGovDeduction.hrEisEmpMst.orgEmpMst.empMname} ${hrPayNonGovDeduction.hrEisEmpMst.orgEmpMst.empLname}</hdiits:option>
			    </c:when>	
			    <c:otherwise>
				    <hdiits:option value="${list.empId}">${hrPayNonGovDeduction.hrEisEmpMst.orgEmpMst.empFname} ${hrPayNonGovDeduction.hrEisEmpMst.orgEmpMst.empMname} ${hrPayNonGovDeduction.hrEisEmpMst.orgEmpMst.empLname}</hdiits:option>
			    </c:otherwise>
		    	</c:choose>
			    </c:forEach>
			    </hdiits:select>
			     --%>
			    </td>
		<td>&nbsp;</td>
			<td><font size="2"><b><hdiits:caption captionid="NGD.deductionType" bundle="${enLables}"></hdiits:caption> </b> </td>
			<td><hdiits:text name="deductionType" id="deductionType" default="${hrPayNonGovDeduction.hrPayDeducTypeMst.deducDisplayName} " size="25" caption="Deduction Name" maxlength="100" readonly="true"/>
			<hdiits:hidden name="deducType" id="deducType" default="${hrPayNonGovDeduction.hrPayDeducTypeMst.deducCode}"/>
		<%--	<hdiits:select name="deductionType"
			    captionid="NGD.deductionType"
			    mandatory="true"
			    sort="false"
			    readonly="true"
			    validation="sel.isrequired">
			    <hdiits:option value="-1">------Select---------</hdiits:option>
			    <c:forEach items="${nonGovDeducTypes}" var="deducTypes">
			    <c:choose>
			    <c:when test="${hrPayNonGovDeduction.hrPayDeducTypeMst.deducCode == deducTypes.deducCode}">
			    	<hdiits:option value="${deducTypes.deducCode}" selected="true">${deducTypes.deducDisplayName}</hdiits:option>
			    </c:when>
			    <c:otherwise>
			    	<hdiits:option value="${deducTypes.deducCode}">${deducTypes.deducDisplayName}</hdiits:option>
			    </c:otherwise>	
			    </c:choose>	
			    </c:forEach>
			    </hdiits:select>  --%>
			    </td>
		</tr>
		
			
		
		<tr><td>&nbsp;</td>
			<td align="left" width="20%" > <font size="2"><b><hdiits:caption captionid="NGD.startDate" bundle="${enLables}"></hdiits:caption> </b> </font></td>
			<td align="left" width="30%" > 
			
			<hdiits:dateTime name="startDate" mandatory="true"  caption="NGD.startDate" bundle="${enLables}" validation="txt.isrequired,txt.isdt" default="${resValue.hrPayNonGovDeduction.nonGovDeducEfftStartDt}" onblur="dateDifferent();document.frmBF.startDate1.value=document.frmBF.startDate.value;" minvalue="" /> </td>
			<td></td>
			<td align="left" width="20%" > <font size="2"><b><hdiits:caption captionid="NGD.endDate" bundle="${enLables}" ></hdiits:caption> </b> </font></td>
			<td align="left" width="30%" > 
			<hdiits:dateTime captionid="NGD.endDate"   bundle="${enLables}" name="endDate" validation="txt.isdt" default="${resValue.hrPayNonGovDeduction.nonGovDeducEfftEndDt}" onblur="dateDifferent();document.frmBF.endDate1.value=document.frmBF.endDate.value;" minvalue=""/> </td>				
		</tr>
		
		
		

		<tr><td></td>
		<td align="left" width="20%" ><font size="2"><b><hdiits:caption captionid="NGD.amount" bundle="${enLables}"></hdiits:caption> </b> </td>
			<td align="left" width="30%" >
			 <c:choose>
			
			    <c:when test="${hrPayNonGovDeduction.hrPayDeducTypeMst.deducTypeCode==p2}" >
			    			<hdiits:number name="deducAmount" captionid="NGD.amount" bundle="${enLables}"  size="13"  maxlength="8" style="text-align:right" mandatory="true"
							default="${hrPayNonGovDeduction.nonGovDeducAmount}" onblur="document.frmBF.deducAmount1.value=document.frmBF.deducAmount.value;" />
			    </c:when>
			    <c:when test="${hrPayNonGovDeduction.hrPayDeducTypeMst.deducTypeCode==p3}" >
			    			<hdiits:number name="deducAmount" captionid="NGD.amount" bundle="${enLables}"  size="13"  maxlength="8" style="text-align:right" mandatory="true"
							default="${hrPayNonGovDeduction.nonGovDeducAmount}" onblur="document.frmBF.deducAmount1.value=document.frmBF.deducAmount.value;"/>
			    </c:when>
			    	
			    <c:otherwise>
			<hdiits:number name="deducAmount" captionid="NGD.amount" bundle="${enLables}"  size="13"  
			default="${hrPayNonGovDeduction.nonGovDeducAmount}"  style="text-align:right" maxlength="8"  mandatory="true" onblur="document.frmBF.deducAmount1.value=document.frmBF.deducAmount.value;" />
			    </c:otherwise>
		    	</c:choose>
		    </td>	
			<td></td>
	<%-- 	<tr><td></td>
			<td align="left" width="20%" ><font size="2"><b><hdiits:caption captionid="NGD.accNo" bundle="${enLables}"></hdiits:caption> </b></font> </td>
			<td align="left" width="30%" >
			<hdiits:text name="accno" id = "accnoid" captionid="NGD.accNo" bundle="${enLables}"  size="14"  
			default="${resValue.hrPayNonGovDeduction.nonGovDeducAccNo}" maxlength="20" onblur="validation()" />
			<hdiits:hidden name = "oldpaccno" default ="${resValue.hrPayNonGovDeduction.nonGovDeducAccNo}" id = "Oldval"/>
		</tr>
	--%>
	<tr>
			<td> 
			<hdiits:hidden name="deductionPeriod" id="deductionPeriod" default="300162"/>
			<hdiits:hidden name="accno1" id="accno1" default="${resValue.hrPayNonGovDeduction.nonGovDeducAccNo}"/>
			<hdiits:hidden name="deducAmount1" id="deducAmount1" default="${hrPayNonGovDeduction.nonGovDeducAmount}"  />
			</td>
	</tr>
		<%--	<td align="left" width="20%" ><font size="2"><b><font size="2"><b><hdiits:caption captionid="NGD.period" bundle="${enLables}"></hdiits:caption> </b> </font></td>
		 	<td align="left" width="30%" ><hdiits:select name="deductionPeriod"
			    captionid="NGD.period"  bundle="${enLables}"
				mandatory="true"
				sort="false"
			    validation="sel.isrequired">
			    <hdiits:option value="-1">------Select---------</hdiits:option>
			    <c:forEach items="${nonGovDeducPeriods}" var="deducPeriod">
			    <c:choose>
			    <c:when test="${hrPayNonGovDeduction.cmnLookupMst.lookupId  == deducPeriod.lookupId}">
			    	<hdiits:option value="${deducPeriod.lookupId}" selected="true">${deducPeriod.lookupDesc}</hdiits:option>
			    </c:when>
			    <c:otherwise>
			    	<hdiits:option value="${deducPeriod.lookupId}">${deducPeriod.lookupDesc}</hdiits:option>
			    </c:otherwise>	
			    </c:choose>	
			    </c:forEach>
			    </hdiits:select></td>  --%>
		</tr>
	</table>
	<div id="dd1" style="display: none;" >
	<table>
			<tr><td>&nbsp;</td>
		
			<td align="left" width="20%" > <font size="2"><b><hdiits:caption captionid="NGD.startDate" bundle="${enLables}"></hdiits:caption> </b> </font></td>
			<td align="left" width="30%" > 
			
			<hdiits:dateTime name="startDate1" mandatory="true"  caption="NGD.startDate" bundle="${enLables}" validation="txt.isdt" default="${resValue.hrPayNonGovDeduction.nonGovDeducEfftStartDt}" onblur="dateDifferent();" minvalue="" /> </td>
			<td></td>
			<td align="left" width="20%" > <font size="2"><b><hdiits:caption captionid="NGD.endDate" bundle="${enLables}" ></hdiits:caption> </b> </font></td>
			<td align="left" width="30%" > 
			<hdiits:dateTime captionid="NGD.endDate"   bundle="${enLables}" name="endDate1" validation="txt.isdt" default="${resValue.hrPayNonGovDeduction.nonGovDeducEfftEndDt}" onblur="dateDifferent();" minvalue="" /> </td>				
		
		</tr>
	</table>
	</div>
 	
 	 	<c:choose>
<c:when test="${empAllRec eq true }">
<hdiits:hidden default="getNonGovDeductionMaster&Employee_ID_NonGovEmpSearch=${empId}&empAllRec=Y" name="givenurl"/>
</c:when>
<c:otherwise>
<hdiits:hidden default="getNonGovDeductionMaster" name="givenurl"/>
</c:otherwise>
</c:choose>
<br/><br/><br/>
 	<fmt:setBundle basename="resources.payroll.payrollLables" var="Lables" scope="request"/>
 	<jsp:include page="../../core/PayTabnavigation.jsp" />
 	<br/><br/><br/>
 	</div>
 	
 	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		if("${msg}"!=null&&"${msg}"!='' && "${empAllRec}"=='false')
		{

			alert("${msg}");
			var url="./hrms.htm?actionFlag=getNonGovDeductionMaster";
			document.frmBF.action=url;
			document.frmBF.submit();
		}
		//else if("${hrPayNonGovDeduction.hrPayDeducTypeMst.deducTypeCode}"=="${p1}")
		//{
		//dateDifferent1();
		//}
		else if("${msg}"!=null&&"${msg}"!='' && "${empAllRec}"=='added')
		{
            alert("${msg}");
			var url="./hrms.htm?actionFlag=getNonGovDeductionMaster&Employee_ID_NonGovEmpSearch=${empId}&empAllRec=Y";

			document.frmBF.action=url;
			document.frmBF.submit();
		}
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>	