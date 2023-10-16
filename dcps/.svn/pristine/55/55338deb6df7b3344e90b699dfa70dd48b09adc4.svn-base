<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>

<script type="text/javascript"  
    src="common/script/commonfunctions.js">
</script>

<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>">
</script>
<script type="text/javascript" 
	src="script/hod/ps/common.js">
</script>
<script type="text/javascript" 
	src="script/common/person.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>">
</script>

<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="empName" value="${resValue.empList}"> </c:set>
<c:set var="employeeName" value="${resValue.empName}"> </c:set>


<c:set var="resultObj1" value="${result}" > </c:set>
<c:set var="resValue1" value="${resultObj1.resultValue}" > </c:set>	
<c:set var="deptName" value="${resValue1.deptList}"> </c:set>
<c:set var="cityList" value="${resValue1.cityList}"> </c:set>
<c:set var="quaterTypeList" value="${resValue1.quaterTypeList}"> </c:set> 


<c:set var="resultObj2" value="${result}" > </c:set>
<c:set var="resValue2" value="${resultObj2.resultValue}" > </c:set>	
<c:set var="gradeName" value="${resValue2.gradeList}"> </c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>

<c:set var="empId" value="${resValue.empId}" ></c:set>
<c:set var="empAllRec" value="${resValue.empAllRec}" ></c:set>

<!-- added by ravysh for merged screen -->
<c:set var="MergedScreen" value="${resValue.MergedScreen}" ></c:set>
<c:set var="empname" value="${resValue.empName}"> </c:set>


<!-- <c:out value="${gradeName}"> </c:out>  -->
<fmt:setBundle basename="resources.eis.eis_Constants" var="enConstants" scope="request"/>
<fmt:message var="p1" key="AISGradeCode" bundle="${enConstants}" scope="request" />

<%
String empIdStr="";
long empId=0;
if(request.getParameter("newEntryEmpId")!=null)
{
	//System.out.println("in if ");
		empIdStr=request.getParameter("newEntryEmpId").toString();
		if(!empIdStr.equalsIgnoreCase("") || !empIdStr.equalsIgnoreCase(" "))
		{
			empId=Long.parseLong(empIdStr);	
		}
		//System.out.println("the emp id is "+empId);
		pageContext.setAttribute("newEntryEmpId",empId);
}
else
{
	System.out.print("in else ");	
	pageContext.setAttribute("newEntryEmpId",0);
}
%>



<script type="text/javascript" language="JavaScript">
var gradeId=0;
<!--
var flag=0;
var empTypeId=0;
function chkKey(e) 
{
	
	if(e.keyCode=="13")
	{
		return false;
	}
	else
	{
		return true;
	}
}

// Start By Urvin shah To Clear the Combos 

function clearCombo(cmbName)
{	
	
	var v=document.getElementById(cmbName).length;
	
	for(i=0;i<v-1;i++)
	{	
			
			lgth = document.getElementById(cmbName).options.length -1;
			document.getElementById(cmbName).options[lgth] = null;
	}		
}

// Ended By Urvin shah.

function checkAvailability() {
	
	var newEmpId=document.getElementById("Employee_ID_EmpOtherDtls").value;
	
	if(newEmpId==-1)
	  return true;
	 
	if(newEmpId!="") {
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
		var url = "hrms.htm?actionFlag=checkEmpNameAvailability&newEmpId="+newEmpId;  	
    
    xmlHttp.onreadystatechange = function() {
    	
		if (xmlHttp.readyState == 4) {     
			if (xmlHttp.status == 200) {	
				var XMLDocForAjax=xmlHttp.responseXML.documentElement;				
				var empNameMapping = XMLDocForAjax.getElementsByTagName('empNameMapping');	
				//alert("the value is"+empNameMapping.length);
				var flag="true";				
				if(empNameMapping.length != 0) {		

						if(empNameMapping[0].childNodes[0].text=="-1")
						{			
							alert("Emp record is not Inserted in the system.\nYou have to enter employee information first.");
							if( "${empAllRec}"!=null && "${empAllRec}"=='true')
							window.location="./hrms.htm?actionFlag=newEmpData&empId=${empId}&empAllRec=Y&newEntryEmpId=${empId}"
							else
							window.location="./hrms.htm?actionFlag=newEmpData&newEntryEmpId="+newEmpId;
							return false; 
						}
						else if(empNameMapping[0].childNodes[0].text>0)
						{			
								if("${empAllRec}"!='true')
								{
								var res=confirm("Emp record is already Entered.\n Do you want to update it.");
							 	if(res){
								 if("${MergedScreen}"!='YES')	
								window.location="./hrms.htm?actionFlag=getOtherData&otherId="+empNameMapping[0].childNodes[0].text+"&edit=Y"; 
								 else
									 window.location="./hrms.htm?actionFlag=getOtherData&MergedScreen=YES&otherId="+empNameMapping[0].childNodes[0].text+"&edit=Y";	 
								 	}
							 	else{
							 		if("${MergedScreen}"!='YES')
								window.location="./hrms.htm?actionFlag=getOtherData";
							 		else
							 			window.location="./hrms.htm?actionFlag=getOtherData&MergedScreen=YES";		
							 	}
							 	}
								else if("${empAllRec}"!=null && "${empAllRec}"=='true')
								window.location="./hrms.htm?actionFlag=getOtherData&otherId="+empNameMapping[0].childNodes[0].text+"&edit=Y&empAllRec=true"; 
						}
					else
					{
						
						getgradedesgpostfromemp();
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



function chkval()
{
	
	if(document.frmOtherMst.sgdMapId.value== -1){
		return true;
	}
	if(document.frmOtherMst.initialBasic.value!='')
	{
		if(document.frmOtherMst.initialBasic.value < 1)
		{
			alert("Current basic must be positive");
			document.frmOtherMst.initialBasic.value="";
			document.frmOtherMst.initialBasic.focus();
			return false;
		}

		
		var intialBasic=parseFloat(document.frmOtherMst.initialBasic.value);
	
		var scaleValue=0;
	
		if(!document.frmOtherMst.sgdMapId.disabled){
			scaleValue=document.forms[0].sgdMapId.options[document.forms[0].sgdMapId.selectedIndex].text;
		}
	
		var initialValue=0;
		var temp='';
		if(scaleValue!=0)
		{
			initialValue=parseFloat(scaleValue.substring(0,scaleValue.indexOf("-")));
			var temp=scaleValue.substring(scaleValue.indexOf("-")+1);
		}
	
		
		var incrementValue = parseFloat(temp.substring(0,temp.indexOf("-")));

		var endValue= parseFloat(temp.substring(temp.indexOf("-")+1));

	if(!document.frmOtherMst.sgdMapId.disabled){
	
		
		//alert(temp);
		var sixPayScale=temp.substring(0,temp.indexOf("("));
		var gpString=temp.substring(temp.indexOf("(")+1);
		gpString=gpString.substring(0,gpString.indexOf(")"))
		//alert(sixPayScale);
		//alert(gpString);
		var maxBasicSixPay=eval(sixPayScale)+eval(gpString);
		//alert(maxBasicSixPay);
		var minBasicSixPay=initialValue+eval(gpString);
		//alert(minBasicSixPay);
		if(sixPayScale>initialValue)
		{
			if(intialBasic < minBasicSixPay){
				alert("Please Enter Higher value of Current basic then scale's Initial Value");
				document.frmOtherMst.initialBasic.value="";
				document.frmOtherMst.initialBasic.focus();
				return false;
			}
			if(intialBasic > maxBasicSixPay){
				alert("Please Enter Lower value of Current basic then scale's End Scale's Value");
				document.frmOtherMst.initialBasic.value="";
				document.frmOtherMst.initialBasic.focus();
				return false;
			}
				
			return true;
		}
		temp=temp.substring(temp.indexOf("-")+1);
		if(temp.indexOf("-")>0) // this if loop is for higher scale  
		{
		var higherIncVal=temp.substring(temp.indexOf("-")+1);
		higherIncVal=higherIncVal.substring(0,higherIncVal.indexOf("-"));
		temp=temp.substring(temp.indexOf("-")+1);
		var higherEndVal=temp.substring(temp.indexOf("-")+1);

		// Amount is straight away equals start ,middle or last values return
        if(intialBasic == initialValue||intialBasic == endValue||intialBasic == higherEndVal){
			return true;
		}
		
		if(intialBasic < initialValue){
			alert("Please Enter Higher value of Current basic then scale's Initial Value");
			document.frmOtherMst.initialBasic.value="";
			document.frmOtherMst.initialBasic.focus();
			return false;
		}
		if(intialBasic > higherEndVal){
			alert("Please Enter Lower value of Current basic then scale's End Scale's Value");
			document.frmOtherMst.initialBasic.value="";
			document.frmOtherMst.initialBasic.focus();
			return false;
		}
		
		// this code is same as normal scale as values is less than higherscale range
		if(intialBasic<=endValue)
		{
		var tempValue = intialBasic - initialValue;
		if(incrementValue!=0 && tempValue%incrementValue == 0){
				return true;		
			}
			if(incrementValue!=0 && tempValue%incrementValue != 0){
				alert("Please Enter value By Considering the Increment Value");
				document.frmOtherMst.initialBasic.value="";
				document.frmOtherMst.initialBasic.focus();
				return false;	
			}
		}			
		else // validation if scale is lying in higher scale range
		{
		var tempValue = intialBasic - endValue;
		if(higherIncVal!=0 && tempValue%higherIncVal == 0){
				return true;		
			}
			if(higherIncVal!=0 && tempValue%higherIncVal != 0){
				alert("Please Enter value By Considering the Increment Value");
				document.frmOtherMst.initialBasic.value="";
				document.frmOtherMst.initialBasic.focus();
				return false;	
			}
		}
		}//  end if loop is for higher scale 
		else  // this else loop is for normal scale retained as it is  
		{
	
		if(intialBasic < initialValue){
			alert("Please Enter Higher value of Current basic then scale's Initial Value");
			document.frmOtherMst.initialBasic.value="";
			document.frmOtherMst.initialBasic.focus();
			return false;
		}	
		if(intialBasic > endValue){
			alert("Please Enter Lower value of Current basic then scale's End Scale's Value");
			document.frmOtherMst.initialBasic.value="";
			document.frmOtherMst.initialBasic.focus();
			return false;
		}
		
		var tempValue = intialBasic - initialValue;
			if(incrementValue!=0 && tempValue%incrementValue == 0){
				return true;		
			}
			if(incrementValue!=0 && tempValue%incrementValue != 0){
				alert("Please Enter value By Considering the Increment Value");
				document.frmOtherMst.initialBasic.value="";
				document.frmOtherMst.initialBasic.focus();
				return false;	
			}
		}	
		}
	}
}

function getgradedesgpostfromemp()
{
    var empId=document.getElementById("Employee_ID_EmpOtherDtls").value;
    document.frmOtherMst.empGrade.value='';
    //document.frmOtherMst.desig.value='';
    document.frmOtherMst.empPost.value='';


		  xmlHttp=GetXmlHttpObject();
		var v=document.getElementById("scale").length;
		for(i=1;i<v;i++)
		{
			lgth = document.getElementById("scale").options.length -1;
			document.getElementById("scale").options[lgth] = null;
		}	
		  if (xmlHttp==null)
		 
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url=''; 
		  var uri='';
		  document.frmOtherMst.txtEmpName.value=document.getElementById("Employee_Name_EmpOtherDtls").value;
		  
		  url= uri+'&newEmpId='+ empId;
		  
		  var actionf="getOtherDetailsByEmp";
		  
		  uri='./hrms.htm?actionFlag='+actionf;
		  
		  url=uri+url;
		  
		  xmlHttp.onreadystatechange=getgradedesgpost;
		  xmlHttp.open("POST",encodeURI(url),true);
		  xmlHttp.send(null);
		  		  	
}
//function getgradedesgpostfromlink(empId,empTypeId)
function getgradedesgpostfromlink(empId)
{
    document.getElementById("textTable").style.display='';
    document.getElementById("searchTable").style.display='none';
    document.getElementById("TableOtherDtls").style.display='';
	document.getElementById('table_of_rows').style.display='none';
	document.getElementById('tableBack').style.display='';
    document.getElementById("emp_id").value=empId;
    document.frmOtherMst.txtEmpName.value=document.getElementById("link"+empId).innerHTML;
    document.frmOtherMst.empGrade.value='';
    //document.frmOtherMst.desig.value='';
    document.frmOtherMst.empPost.value='';


	
		  xmlHttp=GetXmlHttpObject();
		var v=document.getElementById("scale").length;
		
		for(i=1;i<v;i++)
		{
			lgth = document.getElementById("scale").options.length -1;
			document.getElementById("scale").options[lgth] = null;
		}
		  if (xmlHttp==null)
		 
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url=''; 
		  var uri='';
		  
		  url= uri+'&newEmpId='+ empId;
		  
		  var actionf="getOtherDetailsByEmp";
		  
		  uri='./hrms.htm?actionFlag='+actionf;
		  
		  url=uri+url;
		 	xmlHttp.onreadystatechange=getgradedesgpost;
		  xmlHttp.open("POST",encodeURI(url),false);
		  xmlHttp.send(null);
		  disableFields(empTypeId);	
		  document.getElementById("scale").focus();
		  	
}

function disableFields(empTypeId) 
{

if(empTypeId==300018 || empTypeId==300225)
    {
    
    	var cmbName="scale";
    	//alert('alert if');
    	clearCombo(cmbName);
    	/*if(empTypeId==300018)
	    	document.getElementById("incometax").disabled=true;*/
	   	//document.getElementById("is_handicapped").disabled=true;//change because handicapped rule is same for all type of employees
    	//document.getElementById("quaterType").disabled=true;
    	document.getElementById("medAllowance").disabled=true;
    	document.getElementById("uniformAvailed").disabled=true;
    	document.getElementById("sis1979Flag").disabled=true;
    	document.getElementById("FamilyPlannig").disabled=true;
    	//document.frmOtherMst.scale(document.frmOtherMst.scale.selectedIndex).text="0-0-0";
    	//document.frmOtherMst.scale(document.frmOtherMst.scale.selectedIndex).value="0";
    	document.getElementById("scale").value="0";
    	document.getElementById("scale").disabled=true;
    	//document.getElementById("empCity").value="0";
    	//document.getElementById("empCity").disabled=true;
    	flag=1;
       	//by manoj for tra mapping
    	document.getElementById("traTable").style.display='none';
    	document.getElementById("taAvailed").checked=false;
    	//end by manoj for tra mapping
    	
    }
    else
    {
    	//alert('alert else'); 
    		
    	//document.getElementById("incometax").disabled=false;
    	//document.getElementById("is_handicapped").disabled=false;//change because handicapped rule is same for all type of employees
    	//document.getElementById("quaterType").disabled=false;
    	document.getElementById("medAllowance").disabled=false;
    	document.getElementById("uniformAvailed").disabled=false;
    	document.getElementById("sis1979Flag").disabled=false;
    	document.getElementById("FamilyPlannig").disabled=false;
    	document.getElementById("scale").disabled=false;
    	document.frmOtherMst.scale(document.frmOtherMst.scale.oprions).value="";
    	document.frmOtherMst.scale(document.frmOtherMst.scale.oprions).text="--------------Select--------------";
		
		if(eval(flag)==1)
		{			
			clearCombo("scale");
			getgradedesgpost();
			flag=0;
    	}
    	document.frmOtherMst.initialBasic.value='';
    	//by manoj for tra mapping
    	document.getElementById("taAvailed").checked=true;
    	if(document.getElementById("taAvailed").checked==true)
    	{
    		document.getElementById("traTable").style.display='';
    	}
    	//end by manoj for tra mapping
    	//document.getElementById("empCity").value="";
    	//document.getElementById("empCity").disabled=false;
    }
	if(gradeId==${p1})
	{
	
	     //alert('grade match');
	     document.getElementById("sis1979Flag").disabled=true;
	     document.getElementById("isSis").style.visibility ='visible';
	     document.getElementById("sisLabel").style.visibility ='visible';
	}
	else
	{     
	      //alert('in else');
	     document.getElementById("isSis").style.visibility='hidden';
	     document.getElementById("sisLabel").style.visibility ='hidden';
	
	}
}


function getgradedesgpost()
	{	
		if (xmlHttp.readyState==complete_state)
		{ 
		    
			var scaleLst = document.getElementById("scale");
			
					var XMLDoc=xmlHttp.responseXML.documentElement;
					
				    var entries = XMLDoc.getElementsByTagName('otherDetailsByEmp-mapping');
				    var scaleList = XMLDoc.getElementsByTagName('Scale-mapping');
				    var emptypeid = XMLDoc.getElementsByTagName('empTypeId');
				    
				    empTypeId=emptypeid[0].childNodes[0].text;
				  
				    document.getElementById("empGrade").value = entries[0].childNodes[0].text;
				    document.getElementById("empDesg").value = entries[0].childNodes[1].text;				    
				    document.getElementById("post").value = entries[0].childNodes[2].text;				    
					gradeId = entries[0].childNodes[3].text;
					if(gradeId==${p1})
					document.getElementById("sis1979Flag").disabled=true;
									    
					var val=0;
					var text='';
				
				    for ( var i = 0 ; i < scaleList.length ; i++ ) {
     				    val=scaleList[i].childNodes[0].text;    
     				    text = scaleList[i].childNodes[1].text; 
     				    var y = document.createElement('option')   
     			        y.value=val;
     			        y.text=text;	
     			        
     			        try {      				    					
                            scaleLst.add(y,null);
           			    }
 				        catch(ex) {
   			 		       scaleLst.add(y); 
   			   	        }	                
	                 }
  }
  	    document.getElementById("textTable").style.display='';
   document.getElementById("searchTable").style.display='none';
    document.getElementById("TableOtherDtls").style.display='';
	document.getElementById('table_of_rows').style.display='none';
	document.getElementById('tableBack').style.display='';
	
  disableFields(empTypeId);
  
					 
}

//addOrUpdateRecord('ChkEmp', 'chkEmpDetail', new Array('Employee_ID_EmpSearch'));
//addOrUpdateRecord('onreadyStateChange function name', 'service name', new Array('value to be passed with url'));

function chkValue()
{
	var empId=document.getElementById("Employee_ID_EmpOtherDtls").value;
	if(empId=="")
	{
		alert("Please search the employee first");
		return false;
	}
	else
	{
		//getgradedesgpostfromemp();
		checkAvailability();
		return true;
	}
	
}
function clearTable()
{
	document.getElementById("Reset").click();
	document.getElementById('searchTable').style.display='';
	document.getElementById('TableOtherDtls').style.display='none';
	document.getElementById('tableBack').style.display='none';
	document.getElementById('table_of_rows').style.display='';
	
	
	
}

function setStatusBar()
{
 window.status = 'Payroll Application';
}


//by manoj for tra mapping
function taAvailed()
{
	return document.getElementById("taAvailed").checked;
}
function vehiAvailed()
{
	return document.getElementById("chkVehicalAvailed").checked;
}

function toggleIsAvailedHRA()
{
	if( document.frmOtherMst.chkBxIsAvailedHRA.checked==true)
	{
		document.frmOtherMst.chkBxIsAvailedHRA.value=1;
	}
	else
	{
		document.frmOtherMst.chkBxIsAvailedHRA.value=0;
	}
}

function beforeSubmit()
{
	if("${MergedScreen}"!='YES'){
		if("${empAllRec}"=='true')
		document.frmOtherMst.action="./hrms.htm?actionFlag=insertOtherData&empAllRec=true";
		else
		document.frmOtherMst.action="./hrms.htm?actionFlag=insertOtherData&empAllRec=false";
	}
	else{
		if("${empAllRec}"=='true')
			document.frmOtherMst.action="./hrms.htm?actionFlag=insertOtherData&MergedScreen=YES&empAllRec=true";
			else
			document.frmOtherMst.action="./hrms.htm?actionFlag=insertOtherData&MergedScreen=YES&empAllRec=false";
	}
		document.frmOtherMst.submit();
		//}
}
//end by manoj for tra mapping
//Added By Mrugesh for Family Planning
function enableFamilyPln()
{
	var familyPln=document.frmOtherMst.FamilyPlannig.value;
	if(document.frmOtherMst.FamilyPlannig.checked==true)
	{
		var scaleAmt = document.frmOtherMst.sgdMapId.options[document.frmOtherMst.sgdMapId.selectedIndex].text;
		scaleAmt = scaleAmt.split('-');
		var familyPlnAmt;
		var currBasic = document.frmOtherMst.initialBasic.value;
		//alert(scaleAmt.length);
		//alert(scaleAmt[1]);
		if(scaleAmt.length == 3)
			familyPlnAmt = scaleAmt[1];
		else if(scaleAmt.length == 5)
		{
			if(currBasic > 0)
			{
				if(eval(currBasic) > eval(scaleAmt[2])) 
					familyPlnAmt = scaleAmt[3];
				else
					familyPlnAmt = scaleAmt[1];
			}
			else
				familyPlnAmt = 0;
		}
		else
			familyPlnAmt = 0;
		document.frmOtherMst.FamilyPlnAmt.value=familyPlnAmt;
		document.getElementById("FamilyPlnAmt").style.display='';
		
	}
	else
		document.getElementById("FamilyPlnAmt").style.display='none';

}
//Ended by Mrugesh
--></script>
<body>
<hdiits:form name="frmOtherMst" id="frmOtherMst" validate="true" method="POST"
             action="javascript:beforeSubmit()" >	

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="OT.insertOtherDetailInfo" bundle="${commonLables}"/></b></a></li>
    </ul>
</div>

<div class=halftabcontentstyle >
	<div id="tcontent1" class="halftabcontent" tabno="0">
	
	
	
	<c:choose>
	<c:when test="${empAllRec!='true'}">
		<c:set value="display:show" var="displayStyle"/>
		</c:when>
		<c:otherwise>
			<c:set value="display:none" var="displayStyle"/>
			<hdiits:hidden name ="Employee_ID_EmpSearch" default="${empId}"/>
			
		</c:otherwise>
		</c:choose>
		
	<table  width="85%" align="center" style="${displayStyle}" id="searchTable">	<tr>

		<tr>
			<td >
				<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
						<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
						<jsp:param name="SearchEmployee" value="EmpOtherDtls"/>
						<jsp:param name="formName" value="frmOtherMst"/>
						<jsp:param name="functionName" value="chkValue"/>
					</jsp:include>
			</td>
		</tr>
		<tr style="display:none">
			<td align="center">
				<fieldset style="background: #eeeeee;">

				<hdiits:button type="button" captionid="eis.close" bundle="${commonLables}"  name="back" onclick="history.go(-1);return false;"/>	
				</fieldset>
			</td>
		</tr>	
	</table>
	<table  width="85%" align="center" id="textTable" style="display:none">
		<tr>
			</tr>
	</table>
<table id="table_of_rows" width="100%"><tr><td>
  	<display:table name="${empName}" requestURI="" pagesize="15" defaultsort="1" defaultorder="descending" id="row" export="true" style="width:100%">
	  <display:column property="empId" class="tablecelltext" title="Employee Id"  headerClass="datatableheader" style="text-align:center"/>    
	  <display:column class="tablecelltext" title="Employee Name" headerClass="datatableheader" style="text-align:center"> 
	 <hdiits:hidden name="emp_id"/><a href="javascript:getgradedesgpostfromlink(${row.orgEmpMst.empId})" id="link${row.orgEmpMst.empId}">${row.orgEmpMst.empPrefix} ${row.orgEmpMst.empFname} ${row.orgEmpMst.empMname} ${row.orgEmpMst.empLname}   </a>	  </display:column>	

	<fmt:formatDate var="Dob" pattern="dd/MM/yyyy" value="${row.orgEmpMst.empDob}"/>
	<fmt:formatDate var="Doj" pattern="dd/MM/yyyy" value="${row.orgEmpMst.empDoj}"/>
	
	<display:column value="${Dob}" class="tablecelltext" title="Date of Birth"  headerClass="datatableheader" style="text-align:center"/>    
	<display:column value="${Doj}" class="tablecelltext" title="Date of Joining"  headerClass="datatableheader" style="text-align:center"/>    
	</display:table>  
	</td></tr></table>
	<table style="display:none" id="tableBack" width="100%"><tr><td>
		<a href="javascript:clearTable();">Back</a>
	</td></tr></table>
	<fieldset style="background: #ffffff;width:85%" align="center" id="TableOtherDtls" style="display:none"><legend>Employee Details</legend>
<table width="85%" align="center">
	
	<tr>
			<td></td>
			<td><b><hdiits:caption captionid="EMPMAP.empName" bundle="${commonLables}"></hdiits:caption></b>
			</td>
			<td ><hdiits:text name="txtEmpName" id="txtEmpName" captionid="EMPMAP.empName" bundle="${commonLables}" readonly="true" size="25"/>
			</td>
			<TD class="fieldLabel" >
		<b><hdiits:caption captionid="OT.empDesg"  bundle="${commonLables}"></hdiits:caption></b>
		</TD>
		<td><hdiits:text name="empDesg" default="${desigName}" caption="empDesg" readonly="true" size="25"/></td>
		</tr>
	<tr><TD></TD>
		
		<TD class="fieldLabel" >
		<b><hdiits:caption captionid="OT.empGrade" bundle="${commonLables}"></hdiits:caption></b>
		</TD>
		<td><hdiits:text name="empGrade" id="empGrade" caption="empGrade"  readonly="true" size="25"/>
			
		</td>
		<TD class="fieldLabel" >
		<b><hdiits:caption captionid="hr.other.post"  bundle="${commonLables}"   ></hdiits:caption></b>
		</TD>
		<td><hdiits:text name="empPost" id="post" caption="empPost" readonly="true" size="25"/>  		
		</td>
		
	</tr>
	
	<tr> </tr> <tr> </tr>
	
	
	<tr><TD></TD>
		<TD class="fieldLabel" >
		<b><hdiits:caption captionid="OT.empScale" bundle="${commonLables}"></hdiits:caption></b>
		</TD>
		<td><hdiits:select id="scale" name="sgdMapId" size="1"
                       captionid="OT.empScale"   sort="false"
                       validation="sel.isrequired"
                       mandatory="true" 
                       bundle="${commonLables}" onchange="document.frmOtherMst.initialBasic.value=''">
                       
        <hdiits:option value="">---------------Select---------------</hdiits:option>
        </hdiits:select >
        </td>
		<td><b><hdiits:caption captionid="currentBasic" bundle="${commonLables}"/></b></td>
		<td><hdiits:number name="initialBasic" default="" caption="initialBasic" style="text-align:right" validation="txt.isrequired,txt.isnumber" mandatory="true"    maxlength="10"   size="25" /> </td>
		
		
	</tr>
	<tr>
		<TD>&nbsp;</TD>
		<TD class="fieldLabel" >
		<b><hdiits:caption captionid="OT.city" bundle="${commonLables}"></hdiits:caption></b>
		</TD>
		<td><hdiits:select name="empCity" id="empCity" size="1"
                       captionid="OT.city"  sort="false"
                       validation="sel.isrequired"
                       mandatory="true" 
                       bundle="${commonLables}" > <c:out value="cityList"/>
        <hdiits:option value="">---------------Select---------------</hdiits:option>
       
        <c:forEach var="cityList" items="${cityList}">
        <hdiits:option value="${cityList.cityId}"><c:out value="${cityList.cityName}"> </c:out></hdiits:option>
        </c:forEach>
        </hdiits:select >
		</td>
		<!-- Modified By Mrugesh for hiding income-tax textbox -->
		<!-- <td>
			<b><hdiits:caption captionid="OT.incomeTax" bundle="${commonLables}"/></b>
		</td>
		<td colspan=3>
			<hdiits:number name="incometax" id="incometax" default="" captionid="OT.incomeTax" bundle="${commonLables}"  validation="txt.isnumber"  maxlength="10"   size="25"/>
		</td> -->
				<td width="16%" align="left"   colspan="1"><b><hdiits:caption id="effctivedate" captionid="effctivedate" bundle="${commonLables}"/></b></td>
		<td  width="30%" align="left" colspan="2"><hdiits:dateTime caption="effctivedate"  name="effctivedate"  captionid="incrementDate" bundle="${commonLables}"  validation="txt.isdt"/> </td>
		
		<td> 
				<input type="checkbox" name="isSis" id="isSis" value="1" checked>
				<span id="sisLabel">
			<b><hdiits:caption captionid="OT.ISSIS" bundle="${commonLables}"></hdiits:caption></b>
			</span>
	</td>
		<td colspan=3>
			<hdiits:hidden name="incometax" id="incometax"></hdiits:hidden>
		</td>
		<!-- Ended By Mrugesh -->
		<%-- Added by Ankit Bhatt --%>
	
	
	<%-- Ended By Ankit Bhatt --%>
	</tr>
	<tr><TD></TD>
		<TD class="fieldLabel">
		<b><hdiits:caption captionid="OT.Phy_handi" bundle="${commonLables}"></hdiits:caption></b>
		</TD>
		<td><hdiits:radio captionid="OT.YES" caption="YES" bundle="${commonLables}" value="TRUE"  name="is_handicapped" id="is_handicapped"/>
		<hdiits:radio captionid="OT.NO" caption="NO" bundle="${commonLables}" value="FALSE" default="FALSE" name="is_handicapped" id="is_handicapped"/>
		</td>
		<td width="16%" align="left"   colspan="1"><b><hdiits:caption id="incrementDate" captionid="incrementDate" bundle="${commonLables}"/></b></td>
		<td  width="30%" align="left" colspan="2"><hdiits:dateTime caption="incrementDate"  name="IncrementDate"  captionid="incrementDate" bundle="${commonLables}"  validation="txt.isdt"/> </td>
		
		<%-- Commented By Paurav as it was showing extra city Label 
		<TD class="fieldLabel" >
		<b><hdiits:caption captionid="OT.city" bundle="${commonLables}"></hdiits:caption></b>
		</TD> --%>
	</tr>
	<tr><TD></TD>
		
		
	</tr>
	<!-- <tr id="cmbQuaterType"  ><TD></TD>
	
		<TD class="fieldLabel" >
		<b><hdiits:caption captionid="OT.quaterType" bundle="${commonLables}"></hdiits:caption></b>
		</TD>
		<td><hdiits:select id="quaterType" name="quaterType" size="1"
                       captionid="OT.quaterType"  sort="false" 
                       bundle="${commonLables}">
                       
        <hdiits:option value="">---------------Select---------------</hdiits:option>
        <c:forEach var="list" items="${quaterTypeList}">
         <hdiits:option value="${list.quaId}"><c:out value="${list.quaType}"> </c:out></hdiits:option>
         </c:forEach>
        </hdiits:select >
		</td>
	</tr>  -->
		<%-- Added By Paurav --%>
	<tr><td colspan="5">&nbsp;</td></tr>
	 <tr>
	 	<TD>&nbsp;</TD>
		<td align="left" colspan="2">
			<%-- <hdiits:checkbox name="medAllowance" value="1" /> --%>
			<input type="checkbox" name="medAllowance" id="medAllowance" value="1" checked>
			<b><hdiits:caption captionid="OT.Medical" bundle="${commonLables}"></hdiits:caption></b>
		</TD>
		<td align="left" colspan="2">
			<%-- <hdiits:checkbox name="uniformAvailed" value="3"/> --%>
			<input type="checkbox" name="uniformAvailed" id="uniformAvailed" value="1">
			<b><hdiits:caption captionid="OT.Uniform" bundle="${commonLables}"></hdiits:caption></b>
		</TD>
		
	</tr>
	<%-- Ended By Paurav --%>
	<tr><td colspan="5">&nbsp;</td></tr>
	 <tr>
	    <TD>&nbsp;</TD>
		<td align="left" colspan="2">
			<input type="checkbox" name="sis1979Flag" id="sis1979Flag" value="1" >
			<b><hdiits:caption captionid="OTHER.SIS1979" bundle="${commonLables}"></hdiits:caption></b>
		</TD>
		<td align="left" colspan="2">
			<input type="checkbox" name="FamilyPlannig" id="FamilyPlannig" value="1" onclick="javascript:enableFamilyPln()">
			<b><hdiits:caption captionid="OTHER.FamilyPlanning" bundle="${commonLables}"></hdiits:caption></b>&nbsp;
			
			<hdiits:text caption="Family Planning" validation="txt.isnumber" style="display:none" id="FamilyPlnAmt" name="FamilyPlnAmt"></hdiits:text>
		</TD>
			
		
	</tr>
	<tr>
        <TD>&nbsp;</TD>
		<td align="left" colspan="2">
			<input type="checkbox" id="isAvailedHRA" name="chkBxIsAvailedHRA" value="1" checked onclick="javascript:toggleIsAvailedHRA()">
			<b><hdiits:caption captionid="OT.IsAvailedHRA" bundle="${commonLables}"></hdiits:caption></b>&nbsp;
		</td>
	</tr>
	
	<!-- added by manoj for tra mapping -->
	<tr style="display:none">
			<td colspan="5" >
				<input type="checkbox" name="taAvailed" id="taAvailed" value="0">
			</td>
	</tr>
	<tr id="traTable" style="display:none">
	<td colspan="5">
		<table  width="100%" >
		<tr>
			<td>
				<jsp:include page="/WEB-INF/jsp/hrms/eis/HrEmpTraMpgMaster.jsp"></jsp:include>
			</td>
		</tr>
		</table>	
	</td>
	</tr>
	<%-- Ended By manoj for tra mapping --%>
        	<fmt:setBundle basename="resources.eis.eis_common_lables"   var="Lables" scope="page"/>
        	<c:if test="${MergedScreen ne 'YES'}">
        	<c:choose>
<c:when test="${empAllRec eq true }">
<hdiits:hidden default="getOtherData&Employee_ID_EmpOtherSearch=${empId}&empAllRec=true" name="givenurl"/>
</c:when>
<c:otherwise>
<hdiits:hidden default="getOtherData" name="givenurl"/>
</c:otherwise>
</c:choose>
</c:if>

<c:if test="${MergedScreen eq 'YES'}">
        	<c:choose>
<c:when test="${empAllRec eq true }">
<hdiits:hidden default="getOtherDataMerged&MergedScreen=YES&Employee_ID_EmpOtherSearch=${empId}&empAllRec=true" name="givenurl"/>
</c:when>
<c:otherwise>
<hdiits:hidden default="getOtherDataMerged&MergedScreen=YES" name="givenurl"/>
</c:otherwise>
</c:choose>
</c:if>

	<tR><td colspan="6"><jsp:include page="../../core/PayTabnavigation.jsp" /></td></tR>
	</table>
	</fieldset>
	</div>
	
	
	</div>
	<script type="text/javascript">
		initializetabcontent("maintab")
		if("${empname}"!=null&&"${empname}"!='')
		{
				document.frmOtherMst.Employee_srchNameText_EmpOtherDtls.value="${empname}";
				GoToNewPageEmpOtherDtls();
				
		}
		
	//	alert('Emp Id is ' + ${empId} + ' ' + document.getElementById("Employee_ID_EmpOtherDtls").value);
   	    if(("${empId}"!=null||"${empId}"!='') && "${empAllRec}"!=null && "${empAllRec}"=='true')
   	    {
   	    
		  document.getElementById("Employee_ID_EmpOtherDtls").value = '${empId}';
//		  alert(' ' + document.getElementById("Employee_ID_EmpOtherDtls").value);
		  chkValue();
		}
		else
		{
			//if(("${msg}"==null||"${msg}"==''))
			//GoToNewPageEmpOtherDtls();
		
			//var empTypeId=1;
			//temp(empTypeId);
			if("${msg}"!=null&&"${msg}"!='')
			{
					alert("${msg}");
					if("${empId}"!=null && "${empId}"!=0 && "${empAllRec}"!=null && "${empAllRec}"=='true')
					{
					var url="./hrms.htm?actionFlag=getOtherData&Employee_ID_EmpOtherSearch=${empId}&empAllRec=true";
					}
					else{
					if("${MergedScreen}"!='YES'){
					var url="./hrms.htm?actionFlag=getOtherData";
					}else {
					var url="./hrms.htm?actionFlag=getOtherDataMerged&MergedScreen=YES";
					}}
					document.frmOtherMst.action=url;
					document.frmOtherMst.submit();
			}
			}
		
//		setStatusBar();
		
	</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
	
	

<c:if test="${newEntryEmpId ne 0 }">
<script>
document.getElementById("Employee_ID_EmpOtherDtls").value="${newEntryEmpId}";
chkValue();
document.frmOtherMst.txtEmpName.value= '${employeeName}'
enableFamilyPln();


</script>
</c:if>
</hdiits:form>
</body>
<%
		} catch (Exception e) {
			e.printStackTrace();

	}
%>