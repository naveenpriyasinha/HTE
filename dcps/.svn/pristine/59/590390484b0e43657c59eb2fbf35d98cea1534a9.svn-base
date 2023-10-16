<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page session="true" %>
<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
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
<!-- Added By Varun For GPF A/C Only : and / allow  Dt.02-08-2008-->
<script type="text/javascript"
	src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>
<!-- Ended By Varun For GPF A/C Only : and / allow  Dt.02-08-2008-->

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="empList" value="${resValue.empList}" > </c:set>
<c:set var="actionList" value="${resValue.actionList}" > </c:set>	
<c:set var="ReligionList" value="${resValue.religionList}"></c:set>
<c:set var="ClassList" value="${resValue.classList}"></c:set>
<c:set var="GradeName" value="${resValue.GradeName}"></c:set>
<c:set var="categoryList" value="${resValue.categoryList}" > </c:set>
<c:set var="salmodeList" value="${resValue.salmodeList}" > </c:set>
<c:set var="emptypeList" value="${resValue.emptypeList}" > </c:set>
<c:set var="recruitList" value="${resValue.recruitList}" > </c:set>
<c:set var="saluList" value="${resValue.saluList}" > </c:set>
<c:set var="empAllRec" value="${resValue.empAllRec}" ></c:set>
<c:set var="empId" value="${resValue.empId}" ></c:set>
<!-- Start Added By Urvin shah-->
<c:set var="castList" value="${resValue.castList}" > </c:set>
<c:set var="subCastList" value="${resValue.subCastList}" > </c:set>
<c:set var="languageList" value="${resValue.languageList}" > </c:set>
<c:set var="countryNamesList" value="${resValue.countryNamesList}"></c:set>
<c:set var="empName" value="${resValue.empName}" > </c:set>

<!-- End-->

<c:set var="msg" value="${resValue.msg}" ></c:set>


<%
String empIdStr="";
long empId=0;

if(request.getParameter("newEntryEmpId")!=null)
{
		empIdStr=request.getParameter("newEntryEmpId").toString();
		if(!empIdStr.equalsIgnoreCase("") || !empIdStr.equalsIgnoreCase(" "))
		{
			empId=Long.parseLong(empIdStr);	
		}
		pageContext.setAttribute("newEntryEmpId",empId);
}
else
{
	pageContext.setAttribute("newEntryEmpId",0);
}
%>
<script>

function clearSubCastCombo()
{
	var v=document.getElementById("empSubCastId").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("empSubCastId").options.length -1;
			document.getElementById("empSubCastId").options[lgth] = null;
	}		
}

function clearCastCombo()
{
	var v=document.getElementById("empCastId").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("empCastId").options.length -1;
			document.getElementById("empCastId").options[lgth] = null;
	}		
}


function getCasts()
{
	
		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&religionId='+ document.frmBF.Religion.value;
		  var actionf="getCastSubCastData";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
		 // alert('url is:-' + url);	  		  		  
		  xmlHttp.onreadystatechange=stateChangedReligion;
		  xmlHttp.open("POST",encodeURI(url),true);
		  xmlHttp.send(null);		  	
}

function stateChangedReligion()
	{
	
		if (xmlHttp.readyState==complete_state)
		{ 		
			clearSubCastCombo();		    			
  		    clearCastCombo();
  		   
			var castList = document.getElementById("empCastId");
			
					var XMLDoc=xmlHttp.responseXML.documentElement;			
                    
                    if(XMLDoc==null)
                    {
                      window.status = 'No Records Found.';
                     }
                    else
                    {
                        window.status='';
                        var castMapping = XMLDoc.getElementsByTagName('Cast-mapping');
                       // alert("The CastMapping Length is:-"+castMapping.length);
	           			for ( var i = 0 ; i < castMapping.length ; i++ )
	     				{
	     				    val=castMapping[i].childNodes[0].text;    
	     				    text = castMapping[i].childNodes[1].text; 	     				    
	     				    var y = document.createElement('option')   
	     			        y.value=val;
	     			        y.text=text;
	     			        
 	                        try
 	   				        {	      				    					
                               castList.add(y,null);
	           		        }
	           		
	 						 catch(ex)
	   						 {
	   			 	  		    castList.add(y); 
	   						 }
	   		          }
	   		         }
 	   }
 }
 
 //Added by Varun For GPF A/c No:- Uniqueness Dt.02-08-2008
 function chkuniqueness()
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
		var gpfAcctNo=document.frmBF.gpfAcctNo.value;

		var url = "hrms.htm?actionFlag=getGpfDtls&gpfAcctNo='"+gpfAcctNo+"'&chk=3";  

		xmlHttp.onreadystatechange = function(){		
			if(xmlHttp.readyState == 4){     			
				if (xmlHttp.status == 200){	
					var gpfNo;
					var XMLDocForAjax=xmlHttp.responseXML.documentElement;
					
					var loanAdvMapping = XMLDocForAjax.getElementsByTagName('gpfAccNo');
					if(loanAdvMapping.length != 0) {	
						gpfNo = loanAdvMapping[0].childNodes[0].text;	

						if(gpfNo==-1){
							alert("This GPF Account No. is already entered in the system");
							document.frmBF.gpfAcctNo.value='';
							document.frmBF.gpfAcctNo.focus();
							
									retValue=false;
							        return false; 
						     	}  
								else
								{
							     	retValue=true;
							     	return true; 
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
 
 //Ended by Varun For GPF A/c No:- Uniqueness Dt.02-08-2008

function getSubCasts()
{


			
		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&castId='+ document.frmBF.emp_caste_id.value;
		  var actionf="getCastSubCastData";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
		  //alert('url is:-' + url);	  		  		  
		  xmlHttp.onreadystatechange=stateChangedCast;
		  xmlHttp.open("POST",encodeURI(url),true);
		  xmlHttp.send(null);		  	
}

function stateChangedCast()
	{
	
		if (xmlHttp.readyState==complete_state)
		{ 		
			clearSubCastCombo();		    			
  		   // clearCastCombo();
  		   
			var subCastList = document.getElementById("empSubCastId");
			
					var XMLDoc=xmlHttp.responseXML.documentElement;			
                    
                    if(XMLDoc==null)
                    {
                      window.status = 'No Records Found.';
                     }
                    else
                    {
                        window.status='';
                        var subCastMapping = XMLDoc.getElementsByTagName('subCast-mapping');
                       // alert("The CastMapping Length is:-"+subCastMapping.length);
	           			for ( var i = 0 ; i < subCastMapping.length ; i++ )
	     				{
	     				    val=subCastMapping[i].childNodes[0].text;    
	     				    text = subCastMapping[i].childNodes[1].text; 
	     				    
	     				    var y = document.createElement('option')   
	     			        y.value=val;
	     			        y.text=text;
	     			        
 	                        try
 	   				        {	      				    					
                               subCastList.add(y,null);
	           		        }
	           		
	 						 catch(ex)
	   						 {
	   			 	  		    subCastList.add(y); 
	   						 }
	   		          }
	   		         }
 	   }
 }





function checkConfDate()
{
	if (document.forms[0].elements['emp_doj']!=null && document.forms[0].elements['emp_conf']!=null && document.forms[0].elements['emp_conf'].value!="" && document.forms[0].elements['emp_leave_dt']!=null) 
	{	
		var dob = document.forms[0].elements['emp_dob'].value;
		var doj =  document.forms[0].elements['emp_doj'].value;
			
		
		var dor=document.forms[0].elements['emp_leave_dt'].value;
		var doc=document.forms[0].elements['emp_conf'].value;
		var diff=compareDate(dob,doc);
			if (diff<=0)
			{
				alert("Confirmation Date should not be less then or equal to Birth Date");
				document.forms[0].elements['emp_conf'].value="";
				document.forms[0].elements['emp_conf'].focus();
				return false;
			}
         
          var diff=compareDate(doj,doc);
			if (diff<=0)
			{
				alert("Confirmation Date should not be less then or equal to Joining Date ");
				document.forms[0].elements['emp_conf'].value="";
				document.forms[0].elements['emp_conf'].focus();
				return false;
			}
         var diff=compareDate(doc,dor);
			if (diff<=0)
			{
				alert("Confirmation Date should not be greater then or equal to Retirement Date");
				document.forms[0].elements['emp_conf'].value="";
				document.forms[0].elements['emp_conf'].focus();
				return false;
			}
         var diffdoj=compareDate(doj,dob);
			if (doj!=''&&diffdoj>=0)
			{
				alert("Joining Date should  be greater then Date of Birth");
				document.forms[0].elements['emp_doj'].value=" ";
				document.forms[0].elements['emp_doj'].focus();
				return false;
			}
	}
	return true;

}
	
/*function validateEmail(field)
   {
		var field_value=field.value;
        var periodpos="";
		var atpos="";
        var rule_num="0123456789qwertyuiopasdfghjklzxcvbnmASDFGHJKLPOIUYTREWQzXCVBNM.@_-";
		if(field_value!="")
		{
		
		for(var i=0;i<field_value.length;i++)
		{
        var cchar=field_value.charAt(i);
        if(rule_num.indexOf(cchar)==-1)
        {
          alert("Enter Valid Charecters in Email Field");
          field.focus();
          return false;
        }
    } 
	 atpos=field_value.indexOf("@",1)
  if(atpos==-1)
	 {
                alert("Enter your email id in format like info@tcs.com");
		field.focus();
		return false;
	 }
	 periodpos=field_value.lastIndexOf(".")
	 if(periodpos==-1)
	 {
	        alert("should have @y.com etc");
		field.focus();
	        return false;
       	 }

	if(!((periodpos + 3 == field_value.length) || (periodpos + 4 == field_value.length)))
	{
		alert("Enter just 3 chars after the symbol.,like .com etc");
		return false;
	}
	}
 return true;
 }*/

function validateForm()
{


if(!chkuniqueness())
	{
	return false;
	}
	else{
	 var uri = "./hrms.htm?actionFlag=";

	 if("${empAllRec}"=='true')
	 {

	 var url = uri + "insertEmpData&edit=N&empId=${empId}&empAllRec=Y";
	 	 	
	 }
	else
 	{
 	 var url = uri + "insertEmpData&edit=N";
 	
	}
	/* if(document.frmBF.email.value!="")
    {
    if(!validateEmail(document.frmBF.email))
    {  
          
          document.frmBF.email.value="";
          document.frmBF.email.focus();
    }
	 else
	 {
	
	 document.frmBF.action = url;
	 document.frmBF.submit();
	 }
    }
 else
 {
*/

document.frmBF.action = url;
 document.frmBF.submit();
 //}
 
}
}

function getEmployeeDetails()
{
	addOrUpdateRecord('ChkEmp', 'chkEmpDetail', new Array('Employee_ID_EmpSearch'));
}
function ChkEmp()
{
	var empId=document.getElementById("Employee_ID_EmpSearch").value;
	if (xmlHttp.readyState == 4) {     			
			if (xmlHttp.status == 200) {			
				
				var XMLDocForAjax=xmlHttp.responseXML.documentElement;
               
				var empMapping = XMLDocForAjax.getElementsByTagName('emp-mapping');	
					
					if(empMapping.length != 0) {	
							var emp = empMapping[0].childNodes[0].text;		
								if(emp>0)
								{
									var res=confirm("Information for the employee is already entered\nWant To update the record.");
									if(res)
										window.location="./hrms.htm?actionFlag=getEmpData&empId="+emp+"&edit=Y";
								}
								/*else if(emp==0)
								{
								   alert("Selected employee belongs to other department ")
								}*/
								else
								{
									getNewEmployeeDetails();
								}	
							}
								
			}
		}
}
function getNewEmployeeDetails()
{
		addOrUpdateRecord('empDetails', 'getOrgEmployeeDetails', new Array('Employee_ID_EmpSearch'));
}
function getNewEmployeeDetailsFromLink(emp_id)
{
		document.getElementById("emp_id").value=emp_id;
		addOrUpdateRecord('empDetails', 'getOrgEmployeeDetails', new Array('emp_id'));
		document.getElementById("emp_id").value="";
}
function empDetails() {

        //alert("Function Called");	
       	if (xmlHttp.readyState == 4) {    
		
			if (xmlHttp.status == 200) {	
			 		
				
				var XMLDocForAjax=xmlHttp.responseXML.documentElement;
               
               
				var empMapping = XMLDocForAjax.getElementsByTagName('empMapping');	
					
				var flag="true";
				//alert("1"+empMapping[0].childNodes[1].text);
				//alert("2"+empMapping[0].childNodes[2].text);
				//alert("3"+empMapping[0].childNodes[10].text);
				
					document.frmBF.emp_first_name.value=empMapping[0].childNodes[0].text;
					
					document.frmBF.emp_middle_name.value=empMapping[0].childNodes[1].text;
					
					document.frmBF.emp_last_name.value=empMapping[0].childNodes[2].text;
					
					document.frmBF.emp_dob.value=empMapping[0].childNodes[3].text;
					
					document.frmBF.emp_doj.value=empMapping[0].childNodes[4].text;
					
					
					document.frmBF.emp_leave_dt.value=empMapping[0].childNodes[5].text;
					
					document.frmBF.Salutation.value=empMapping[0].childNodes[6].text;				
					
					document.frmBF.employeeId.value=empMapping[0].childNodes[7].text;	

					document.frmBF.userID.value=empMapping[0].childNodes[8].text;	
					
					
					
					document.frmBF.emp_conf.value=empMapping[0].childNodes[9].text;
					//alert("document.frmBF.gradeName.value:::"+document.frmBF.gradeName.value);
					document.frmBF.gradeName.value=empMapping[0].childNodes[10].text;
				//	alert("document.frmBF.gradeName.value:::2::"+document.frmBF.gradeName.value);
						
					document.getElementById('tblEmpInfo').style.display='';
					
					document.getElementById('table_of_search').style.display='none';
					document.getElementById('table_of_rows').style.display='none';
					document.getElementById('link').style.display='';
					document.frmBF.contactNo.focus();
								
			}
		}
	}
function showEmpName()
{
 childWindow = window.open("hrms.htm?actionFlag=getOrgEmpData","OrgEmployeeNames","toolbar=no, location=no, directories=no,status=no, menubar=no, scrollbars=yes, resizable=yes, resize = no, width = 800, height = 550, copyhistory=no, top=80,left=80");
 if (childWindow.opener == null) childWindow.opener = self;
}
function chkValue()
{
	var empId=document.getElementById("Employee_ID_EmpSearch").value;
	if(empId=="")
	{
		alert("Please search the employee first");
		return false;
	}
	else
	{
		getEmployeeDetails();
		return true;
		//getNewEmployeeDetails();
	}
	
}
function clearTable()
{
	document.getElementById("Reset").click();
	document.getElementById('tblEmpInfo').style.display='none';
	document.getElementById('link').style.display='none';
	document.getElementById('table_of_rows').style.display='';
	

}

function onlyAlpha111(control) 
{              
              var iChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789/ ";
              var value="";
              var valid=true;

              for (var i=0; i<control.value.length;i++) 
               {              
                  if (iChars.indexOf(control.value.charAt(i))!=-1) 
                 {

                    value=value+control.value.charAt(i);
                 }               
                 else
                 {                  
                    valid=false;
                 }
              }                   
              if(!valid)
              {
                  alert("Please Enter Proper Name\nAlso check for the Spaces and Capital letters");
                  control.value="";
                  control.focus();
                  return false;
              }
              //return true;              
}

function checkAvailability()
{
	
	if (document.getElementById('gpfAcctNo').value=="")
	{
		document.getElementById('gradeName').value='';
		return false;
	}
	else
	{
		return true;
	}
}

</script>
<body>
<c:set var="statusList" value="${resValue.statusList}" > </c:set>

<hdiits:form name="frmBF" validate="true" method="POST"
	action="javascript:validateForm()"  encType="multipart/form-data" >
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="insertEmpMaster" bundle="${commonLables}"></hdiits:caption></b></a></li>
	</ul>
	</div>
<div class="halftabcontentstyle">
<div id="tcontent1" class="tabcontent" tabno="0">

 		
	<c:choose>
	<c:when test="${empAllRec!='true'}">
		<c:set value="display:show" var="displayStyle"/>
		</c:when>
		<c:otherwise>
			<c:set value="display:none" var="displayStyle"/>
			<hdiits:hidden name ="Employee_ID_EmpSearch" default="${empId}"/>
			
		</c:otherwise>
		</c:choose>
		
	<table  width="85%" align="center" style="${displayStyle}" id="table_of_search">	<tr>	

		<tr>
			<td >
				<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
						<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
						<jsp:param name="SearchEmployee" value="EmpSearch"/>
						<jsp:param name="formName" value="frmBF"/>
						<jsp:param name="functionName" value="chkValue"/>
					</jsp:include>
			</td>
		</tr>
		<tr style="display:none">
			<td align="center">
				<fieldset style="background: #eeeeee;">
				<hdiits:button type="button" captionid="populateDetail" bundle="${commonLables}"  name="details" onclick="chkValue()"/>	
				<hdiits:button type="button" captionid="eis.close" bundle="${commonLables}"  name="back" onclick="history.go(-1);return false;"/>	
				</fieldset>
			</td>
		</tr>	
	</table>
	<hdiits:hidden name="emp_id"/>

<table id="table_of_rows" width="100%"><tr><td>
  	<display:table name="${empList}" requestURI="" pagesize="15" defaultsort="1" defaultorder="descending" id="row" export="true" style="width:100%">
	  <display:column property="empId" class="tablecelltext" title="Employee Id"  headerClass="datatableheader" />    
	  <display:column class="tablecelltext" title="Employee Name" headerClass="datatableheader" > 
	  <a href="javascript:getNewEmployeeDetailsFromLink(${row.empId})" id="link${row.empId}">${row.empFname} ${row.empMname} ${row.empLname}  </a>	  </display:column>	
	</display:table>  
	</td></tr></table>
	<a href="javascript:clearTable()" style="display:none" id="link">Back</a>
<table id="tblEmpInfo" name="tblEmpInfo" style="display:none" width="100%">
<tr bgcolor="#386CB7">
<td class="fieldLabel" colspan="10">

 <font color="#ffffff">
<strong>Personal Details</strong></font></td></tr>
<tR><td></td></tR>
<tR><td></td></tR>

 <TR >
		   <hdiits:hidden name="employeeId"  />
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="Salutation" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<TD>
				<hdiits:text name="Salutation" captionid="Salutation" bundle="${commonLables}" maxlength="30" readonly="true" validation ="txt.isrequired" size="10" />	
			</TD>
</TR>
<tr>				
			<td class="fieldLabel" >
			<b><hdiits:caption captionid="FIRST_NAME" bundle="${commonLables}" ></hdiits:caption></b>
			</TD>
			
			<TD> <hdiits:text
				name="emp_first_name"
				 captionid="FIRST_NAME" bundle="${commonLables}" maxlength="30" readonly="true" validation ="txt.isrequired" size="20" /></TD>	
				 
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="MIDDLE_NAME" bundle="${commonLables}" ></hdiits:caption></b>
			</TD>
			<TD> <hdiits:text
				name="emp_middle_name"
				 captionid="MIDDLE_NAME" bundle="${commonLables}" maxlength="30" readonly="true" size="20" /></TD>	
			<TD class="fieldLabel" >
<b>			<hdiits:caption captionid="LAST_NAME" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<TD> <hdiits:text
				name="emp_last_name"
				captionid="LAST_NAME" bundle="${commonLables}"  maxlength="30" readonly="true" validation ="txt.isrequired" size="20" /></TD>	
</tr>
<tR><td></td></tR>
 <TR>
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="GENDER"  bundle="${commonLables}" ></hdiits:caption><font color="red">*</font></b>
			</TD>
			
			<TD><hdiits:radio caption='Male'  bundle="${commonLables}" name="gender" value="M" default="M" validation="sel.isradio" errCaption ="Gender"></hdiits:radio>
				<hdiits:radio caption='Female' bundle="${commonLables}" name="gender" value="F" validation="sel.isradio" ></hdiits:radio>
			</tD>
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="DATE_OF_BIRTH" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<TD><hdiits:dateTime captionid="DATE_OF_BIRTH" bundle="${commonLables}" name="emp_dob"   validation ="txt.isrequired,txt.isdt"  mandatory ="true"  /></TD>	
			
			
			<%--<TD class="fieldLabel" >
			<b><hdiits:caption captionid="BIRTH_PLACE" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<TD> <hdiits:text
				name="emp_birth_place"
				 captionid="BIRTH_PLACE" bundle="${commonLables}"  maxlength="20"   validation ="txt.isname" />
				 
				 
				 </TD>				
--%></TR>
<tR><td></td></tR>
 <TR>
			<%--<TD class="fieldLabel" >
			<b><hdiits:caption captionid="NATIVE_PLACE" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<TD> <hdiits:text
				name="emp_native_place"
				 captionid="NATIVE_PLACE" bundle="${commonLables}"  maxlength="30"  validation ="txt.isname" /></TD>	
			--%>
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="NATIONALITY" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<TD>
			<hdiits:select captionid="NATIONALITY" bundle="${commonLables}" style="width:150px;" sort="false" name="emp_nationality">
			  <hdiits:option value="-1">----Select----</hdiits:option>
			    <c:forEach var="countryNames" items="${countryNamesList}">
				 <c:choose>
				  <c:when test="${countryNames.countryId eq hrEisEmpMst.cmnCountryMstByEmpNationality.countryId }">
				    <hdiits:option value="${countryNames.countryId}" selected="true">${countryNames.countryName}</hdiits:option>
				  </c:when>
					<c:otherwise>
					<hdiits:option value="${countryNames.countryId}" >${countryNames.countryName}</hdiits:option>
					</c:otherwise>
					</c:choose>
				</c:forEach>
			</hdiits:select>
			</TD>
			
				
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="MOTHER_TONGUE" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<TD>
			<hdiits:select  captionid="MOTHER_TONGUE" bundle="${commonLables}" sort="false" name="emp_mother_tongue" > 
			<hdiits:option value="-1">-----------Select-----------</hdiits:option>
				<c:forEach var="motherTounge" items="${languageList}">
					<option value="${motherTounge.lookupId}"> ${motherTounge.lookupDesc} </option>
				</c:forEach>		
		
			</hdiits:select>
			
			 <%-- <hdiits:text
				name="emp_mother_tongue"
				 captionid="MOTHER_TONGUE" bundle="${commonLables}"  maxlength="15"   validation ="txt.isname" /> 
				 --%>
				 </TD>	
				 <TD class="fieldLabel" >
			<b><hdiits:caption captionid="PANNO" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<TD> <hdiits:text name="empPAN" captionid="PANNO" bundle="${commonLables}"   maxlength="20"  /></TD>		 				
						
			</TR>
<tR><td></td></tR>

 <TR>
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="RELIGION" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<TD>
			<hdiits:select  captionid="RELIGION" bundle="${commonLables}" sort="false" name="Religion" onchange="getCasts()"> 
			<hdiits:option value="-1">-----------Select-----------</hdiits:option>
			<c:forEach var="religion" items="${ReligionList}">
	    					<option value=<c:out value="${religion.lookupId}"/>>
							<c:out value="${religion.lookupDesc}"/></option>
			</c:forEach>		
		
			</hdiits:select>
		
		
		   <hdiits:hidden  id="empCastId" name="emp_caste_id" default="1" />
		   <hdiits:hidden  id="empSubCastId" name="emp_sub_caste" default="1"  />
		
		
			</TD>	
			
			<TD  class="fieldLabel"><fmt:message key="GPF.ACC_NO" bundle="${commonLables}"/></TD>
			<TD><hdiits:text   name="gpfAcctNo" id="gpfAcctNo" default=""  captionid="ACC.NO" bundle="${commonLables}"    onblur="onlyAlpha111(this);"  maxlength="40"   size="22" /> </TD>
			
			
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="CLASS" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<TD>
			<hdiits:hidden name ="GradeName" />
			<hdiits:select  captionid="CLASS" bundle="${commonLables}" sort="false" style="width:150px;" name="gradeName" id="gradeName" readonly="false" > 
			<hdiits:option value="-1">-----------Select-----------</hdiits:option>
			<c:forEach var="class" items="${ClassList}">
	    					<option value=<c:out value="${class.gradeId}"/>>
							<c:out value="${class.gradeName}"/></option>
			</c:forEach>		
	
			</hdiits:select>
			
			</TD>
			
			
	<!--  		<TD class="fieldLabel" >
			<b><hdiits:caption captionid="CASTE" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<TD>
			
			<hdiits:select  captionid="CASTE" id="empCastId" bundle="${commonLables}" sort="false" name="emp_caste_id" onchange="getSubCasts()"> 
			<hdiits:option value="-1">-----------Select-----------</hdiits:option>
			<c:forEach var="cast" items="${castList}">
	    					<option value=<c:out value="${cast.lookupId}"/>>
							<c:out value="${cast.lookupDesc}"/></option>
			</c:forEach>		
		
			</hdiits:select>
			
			
			
			<%--
			<hdiits:text
				name="emp_caste_id"
				 captionid="CASTE" bundle="${commonLables}"  maxlength="10"    validation ="txt.isname"  />
				 --%>
				 </TD>
			
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="SUBCASTE" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<TD>
			<hdiits:select  captionid="SUBCASTE" id="empSubCastId" bundle="${commonLables}" sort="false" name="emp_sub_caste" > 
			<hdiits:option value="-1">-----------Select-----------</hdiits:option>
			<c:forEach var="subCast" items="${subCastList}">
	    					<option value=<c:out value="${subCast.lookupId}"/>>
							<c:out value="${subCast.lookupDesc}"/></option>
			</c:forEach>		
		
			</hdiits:select> --> 
			
			
			<%--
			<hdiits:text
				name="emp_sub_caste"
				 captionid="SUBCASTE" bundle="${commonLables}"  maxlength="20"  validation ="txt.isname"  />
				 
				 </TD>--%>
			
			
					
</TR>
<TR>
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="CATEGORY" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<TD>
			<hdiits:select  captionid="CATEGORY" bundle="${commonLables}" name="Category" sort="false"   > 
			<hdiits:option value="-1">-----------Select-----------</hdiits:option>
			<c:forEach var="category" items="${categoryList}">
	    					<option value=<c:out value="${category.lookupId}"/>>
							<c:out value="${category.lookupDesc}"/></option>
			</c:forEach>
			</hdiits:select>		
			</TD>
	
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="HOBBY" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
						<td colspan ="4"><hdiits:textarea rows="3" cols="50" name="emp_hobby" captionid="HOBBY" bundle="${commonLables}" onblur="checkSplChar(this)"  maxlength="30"></hdiits:textarea></td>
</TR>
<tR>
			<TD class="fieldLabel" >
			<b><hdiits:caption caption="E-Mail" captionid="email" bundle="${commonLables}"  ></hdiits:caption></b>
			</TD>
			<TD>
			<hdiits:text
				name="email" caption="E-Mail"
				 captionid="email" bundle="${commonLables}"  maxlength="60"   validation ="txt.email" /></TD>
			
		<TD class="fieldLabel" >
			<b><hdiits:caption captionid="contactNo" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<TD>
			<hdiits:number
				name="contactNo"  maxlength="14"   captionid="contactNo" bundle="${commonLables}" /></TD>
</tR>
<tr></tr>
<tr></tr>
<tr bgcolor="#386CB7">
<td class="fieldLabel" colspan="10">

 <font color="#ffffff"> 
<strong>Official Details</strong></font></td></tr>
<tR><td></td></tR>
<tR><td></td></tR>

<TR>
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="DATE_OF_JOINING" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<TD> <hdiits:text captionid="DATE_OF_JOINING" bundle="${commonLables}" name="emp_doj"  validation ="txt.isrequired,txt.isdt" onblur="checkConfDate()" mandatory ="true"   readonly="true" /></TD>
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="CONFIRMATION_DUE_DATE" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			
			
		<TD> <hdiits:text captionid="CONFIRMATION_DUE_DATE" bundle="${commonLables}" name="emp_conf" readonly="true" onblur="checkConfDate()"/></TD>					
					<TD class="fieldLabel" >
			<b><hdiits:caption captionid="Retirement_date" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<TD> <hdiits:text captionid="Retirement_date" bundle="${commonLables}"  validation ="txt.isrequired,txt.isdt" onblur="checkConfDate()" mandatory ="true"  name="emp_leave_dt"  readonly="true"/></TD>
</TR>
<tR><td></td></tR>
<tR><td></td></tR>
<TR>
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="SALARY_MODE" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<TD>
			<hdiits:select  captionid="SALARY_MODE" bundle="${commonLables}" name="SalMode" sort="false" > 
			<hdiits:option value="">-----------Select-----------</hdiits:option>
			<c:forEach var="salmode" items="${salmodeList}">
	    					<option value=<c:out value="${salmode.lookupId}"/>>
							<c:out value="${salmode.lookupDesc}"/></option>
			</c:forEach>	
			</hdiits:select>	
			</TD>
		
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="EMP_TYPE" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<TD>
			<hdiits:select  captionid="EMP_TYPE" bundle="${commonLables}" name="EmpType" sort="false" validation="sel.isrequired"   mandatory ="true" > 
			<hdiits:option value="">-----------Select-----------</hdiits:option>
			<c:forEach var="emptype" items="${emptypeList}">
	    					<option value='<c:out value="${emptype.lookupId}"/>'>
							<c:out value="${emptype.lookupDesc}"/></option>
			</c:forEach>	
			</hdiits:select>	
			</TD>
		
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="EMP_RECRUIT_SRC" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<TD>
			<hdiits:select  captionid="EMP_RECRUIT_SRC" bundle="${commonLables}" name="EmpRecSrc" sort="false"  > 
			<hdiits:option value="">-----------Select-----------</hdiits:option>
			<c:forEach var="emprecsrc" items="${recruitList}">
	    					<option value=<c:out value="${emprecsrc.lookupId}"/>>
							<c:out value="${emprecsrc.lookupDesc}"/></option>
			</c:forEach>	
			</hdiits:select>	
			</TD>
			<TD> <hdiits:hidden   name="userID"/></TD>		 				
	</TR><tR><td></td></tR>
	<tR><td></td></tR>
	<!-- <TR>
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="STATUS" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<TD>
			<hdiits:select  captionid="STATUS" bundle="${commonLables}" name="EmpStatus" sort="false" > 
			<hdiits:option value="">-----------Select-----------</hdiits:option>
			<c:forEach var="emprecsrc" items="${statusList}">
	    					<option value=<c:out value="${emprecsrc.lookupId}"/>>
							<c:out value="${emprecsrc.lookupDesc}"/></option>
			</c:forEach>	
			</hdiits:select>	
			</TD>
     </TR>  -->
      	 
 <fmt:setBundle basename="resources.eis.eis_common_lables" var="Lables" scope="page"/> 
 <c:choose>
<c:when test="${empAllRec eq true }">
<hdiits:hidden default="getEmpData&Employee_ID_EmpInfoSearch=${empId}&empAllRec=Y" name="givenurl"/>
</c:when>
<c:otherwise>
<hdiits:hidden default="getEmpData" name="givenurl"/>
</c:otherwise>
</c:choose>
<tR><td colspan="6"><jsp:include page="../../core/PayTabnavigation.jsp" /></td></tR>					
</table> 
    
 </div>
</div>

	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")	
    	if("${msg}"!=null && "${msg}"!='')
		{
			alert("${msg}" );
			if( "${empAllRec}"=='true')
			var url="./hrms.htm?actionFlag=getEmpData&Employee_ID_EmpInfoSearch=${empId}&empAllRec=Y";
			else
			var url="./hrms.htm?actionFlag=getEmpData";
			document.frmBF.action=url;
			document.frmBF.submit();
		}
		if('${empName}'!= null && '${empName}'!= "")
		{	
				document.frmBF.Employee_srchNameText_EmpSearch.value='${empName}';
				GoToNewPageEmpSearch();
		}
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
<script>
if(document.forms[0].gpfAcctNo.value=="")
{
	//document.getElementsByName("gradeName").disable=true;
	//document.forms[0].gradeName.value="-1";
//	document.forms[0].gradeName.disabled=true;
}	
else
{
	//document.forms[0].gradeName.disabled=false;
}
</script>

</hdiits:form>
</body>
<c:if test="${newEntryEmpId ne 0 }">

<script>
getNewEmployeeDetailsFromLink("${newEntryEmpId}");
</script>

</c:if>	
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>