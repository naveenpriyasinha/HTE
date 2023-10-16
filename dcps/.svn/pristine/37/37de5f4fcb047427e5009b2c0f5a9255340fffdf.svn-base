
<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ page import ="com.tcs.sgv.eis.valueobject.HrEisEmpMst, 
				  java.text.SimpleDateFormat" %>


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


<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="ClassList" value="${resValue.classList}"></c:set>

<c:set var="hrEisEmpMst" value="${resValue.hrEisEmpMst}" ></c:set>
<c:set var="orgGradeMst" value="${resValue.orgGradeMst}" ></c:set>
<c:set var="editList" value="${resValue.editList}" ></c:set>
<c:set var ="GradeMstName" value="Class 2"></c:set>

<c:set var="ReligionList" value="${resValue.religionList}"></c:set>

<c:set var="categoryList" value="${resValue.categoryList}" > </c:set>

<c:set var="salmodeList" value="${resValue.salmodeList}" > </c:set>

<c:set var="emptypeList" value="${resValue.emptypeList}" > </c:set>

<c:set var="recruitList" value="${resValue.recruitList}" > </c:set>
<c:set var="GradeId" value="${resValue.GradeId}"></c:set>
<c:set var="statusList" value="${resValue.statusList}" > </c:set>

<c:set var="saluList" value="${resValue.saluList}" > </c:set>
<c:set var="castList" value="${resValue.castList}" > </c:set>
<c:set var="subCastList" value="${resValue.subCastList}" > </c:set>
<c:set var="languageList" value="${resValue.languageList}" > </c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>

<c:set var="empAllRec" value="${resValue.empAllRec}" ></c:set>
<c:set var="updatePaybillFlg" value="${resValue.updatePaybillFlg}" ></c:set>
<c:set var="paybillMonth" value="${resValue.paybillMonth}" ></c:set>
<c:set var="paybillYear" value="${resValue.paybillYear}" ></c:set>
<c:set var="empId" value="${resValue.empId}" ></c:set>
<c:set var="empAllRecFlag" value="${resValue.empAllRecFlag}" ></c:set>
<c:set var="hrEisProofDtl" value="${resValue.hrEisProofDtl}" ></c:set>
<c:set var="countryNamesList" value="${resValue.countryNamesList}" ></c:set>

<c:set var="FromBasicDtlsNew" value="${resValue.FromBasicDtlsNew}" ></c:set>
<c:set var="otherId" value="${resValue.otherId}" ></c:set>
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
		var gpfAcctNoOld=document.frmBF.gpfAcctNoOld.value;
	if(gpfAcctNo!="" && gpfAcctNo!=" " && gpfAcctNo!=gpfAcctNoOld) 
	{
		var url = "hrms.htm?actionFlag=getGpfDtls&gpfAcctNo="+gpfAcctNo+"&chk=3";  

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
	}
	
		return retValue;
	
		
}
 
 //Ended by Varun For GPF A/c No:- Uniqueness Dt.02-08-2008


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
         diff=compareDate(doj,doc);
			if (diff<=0)
			{
				alert("Confirmation Date should not be less then or equal to Joining Date ");
				document.forms[0].elements['emp_conf'].value="";
				document.forms[0].elements['emp_conf'].focus();
				return false;
			}
         diff=compareDate(doc,dor);
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
				document.forms[0].elements['emp_doj'].value="";
				document.forms[0].elements['emp_doj'].focus();
				return false;
			}
	}
	return true;

}

function validateEmail(field)
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
 }

function validateForm()
{

if(!chkuniqueness())
	{
	return false;
	}
	else{

		
 var uri = "./hrms.htm?actionFlag=";
 var url = uri + "insertEmpData&edit=Y&employeeId=${hrEisEmpMst.empId}";
    if(document.frmBF.email.value!="")
    {
    if(!validateEmail(document.frmBF.email))
    {  
          
          document.frmBF.email.value="";
          document.frmBF.email.focus();
          return false;
    }
	 else
	 {
	 return true;
	 //document.frmBF.action = url;
	 //document.frmBF.submit();
	 }
    }
 else
 {
 return true;
 //document.frmBF.action = url;
 //document.frmBF.submit();
 }
 }
}
function validateForm1()
{
	var FromBasicDtlsNew="${FromBasicDtlsNew}";
  var uri = "./hrms.htm?actionFlag=";
  if('${empAllRec}'=='true')
    var url = uri + "insertEmpData&edit=Y&employeeId=${hrEisEmpMst.empId}&empAllRec=Y";
  else 
   var url = uri + "insertEmpData&edit=Y&employeeId=${hrEisEmpMst.empId}&otherId=${otherId}&FromBasicDtlsNew="+FromBasicDtlsNew;
 //alert('URL for submit ' + url);

 document.frmBF.action = url;
 document.frmBF.submit();
}
function PANCardNo(Pan)
{	
	var PanNo = document.getElementById(Pan).value;
	var len=PanNo.length;
	str="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 /\\-\n"; 
	for(var i=0;i<len;i++)
	{
	     if((str.indexOf(PanNo.charAt(i))) == -1)
	     {
	       if(PanNo.charAt(i) != '\n' && PanNo.charAt(i) != '\r')
	       {
	        alert("Please Enter Proper Number");
	        document.getElementById(Pan).focus();
	        document.getElementById(Pan).style.background="#ccccff";
	        return false;
	       }
	     }
	     document.getElementById(Pan).style.background="";
	 }
  return true;
}
</script>

<hdiits:form name="frmBF" validate="true" method="POST"
	action="javascript:validateForm1()" encType="multipart/form-data">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="updateEmpMaster" bundle="${commonLables}"/></b></a></li>
	</ul>
	</div>
	
	
<div class="tabcontentstyle">


	<div id="tcontent1" class="tabcontent" tabno="0">

    
<table class="tabtable" >
<tr>
	     <c:if test="${updatePaybillFlg eq 'y'}" >
	     <a href="./hrms.htm?actionFlag=fillPaybillData&paybillYear=${paybillYear}&paybillMonth=${paybillMonth}&updatePaybillEmpId=${resValue.hrEisEmpMst.orgEmpMst.empId}&searchData=y">Back to update pay bill</a>
	     </c:if>
</tr>
<tr bgcolor="#386CB7">
<td class="fieldLabel" colspan="10">
 <font color="#ffffff">
<strong>Personal Details</strong></font></td></tr>
<tR><td></td></tR>
<tR><td></td></tR>

 <TR>
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="Salutation" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<TD><hdiits:text
				name="Salutation"
				 caption="Salutation" readonly="true" maxlength="50" captionid="Salutation" bundle="${commonLables}" validation ="txt.isrequired" default ="${hrEisEmpMst.orgEmpMst.empPrefix}" />
				 
			
			</TD>
</TR>
 <TR>
			<td class="fieldLabel" >
			<b><hdiits:caption captionid="FIRST_NAME" bundle="${commonLables}" ></hdiits:caption></b>
			</TD>
			
			<TD> <hdiits:text
				name="emp_first_name"
				 caption="First Name" readonly="true" maxlength="50" validation ="txt.isrequired" default ="${hrEisEmpMst.orgEmpMst.empFname}" /></TD>	
				 
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="MIDDLE_NAME"  bundle="${commonLables}" ></hdiits:caption></b>
			</TD>
			<TD> <hdiits:text
				name="emp_middle_name"
				 caption="emp_middle_name"  maxlength="50" readonly="true" default ="${hrEisEmpMst.orgEmpMst.empMname}"/></TD>	
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="LAST_NAME" bundle="${commonLables}" ></hdiits:caption></b>
			</TD>
			<TD> <hdiits:text
				name="emp_last_name"
				caption="emp_last_name" readonly="true" maxlength="50" validation ="txt.isrequired" default ="${hrEisEmpMst.orgEmpMst.empLname}"/></TD>	
</tr>

<tR><td></td></tR>
 <TR>
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="GENDER" bundle="${commonLables}"></hdiits:caption><font color="red">*</font></b>
			</TD>
			
			<TD>
				<hdiits:radio caption="Male" name="gender" value="M" validation="sel.isradio"  errCaption ="Gender" default="${hrEisEmpMst.empGender}"></hdiits:radio>
				<hdiits:radio caption="Female" name="gender" value="F" validation="sel.isradio" default="${hrEisEmpMst.empGender}"></hdiits:radio>
			</tD>
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="DATE_OF_BIRTH" bundle="${commonLables}" ></hdiits:caption></b>
			</TD>
			
			<TD><hdiits:dateTime captionid="DATE_OF_BIRTH" bundle="${commonLables}" name="emp_dob" onblur="checkConfDate()" validation ="txt.isrequired,txt.isdt"  mandatory ="true"  default="${hrEisEmpMst.orgEmpMst.empDob}"  /></TD>	
		
			
		
		
		<%--	<TD class="fieldLabel" >
			<b><hdiits:caption captionid="BIRTH_PLACE" bundle="${commonLables}"   ></hdiits:caption></b>
			</TD>
			<TD> <hdiits:text
				name="emp_birth_place"
				 caption="emp_birth_place"  maxlength="20" validation ="txt.isname" default ="${hrEisEmpMst.empBirthPlace}" captionid="emp_birth_place"/></TD>				
				 --%>
</TR>
<tR><td></td></tR>

 <TR>
		<%--	<TD class="fieldLabel" >
			<b><hdiits:caption captionid="NATIVE_PLACE" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<TD> <hdiits:text
				name="emp_native_place"
				 caption="emp_native_place"  maxlength="20" validation ="txt.isname" default ="${hrEisEmpMst.empNativePlace}" captionid="emp_native_place"/>
				 </TD>	--%>
				 
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
				<c:choose>
			    		<c:when test="${hrEisEmpMst.cmnLanguageMst.lookupId==motherTounge.lookupId}">
		    					<option value="${motherTounge.lookupId}" selected="true"> ${motherTounge.lookupDesc} </option>
		    			</c:when>
		    			<c:otherwise>
		    				<option value="${motherTounge.lookupId}"> ${motherTounge.lookupDesc} </option>
		    			</c:otherwise>		
		    	</c:choose>		
				</c:forEach>		
			</hdiits:select>
			
			<%-- <hdiits:text
				name="emp_mother_tongue"
				 caption="emp_mother_tongue"  maxlength="10"   validation ="txt.isname" default ="${hrEisEmpMst.empMotherTongue}" captionid="emp_mother_tongue"/>
				 --%>
				 
				 </TD>
				 <TD class="fieldLabel" >
			<b><hdiits:caption captionid="PANNO" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
				<TD> <hdiits:text name="empPAN" id = "panno" captionid="PANNO" bundle="${commonLables}"  maxlength="20"  default="${hrEisProofDtl.proofNum}" onblur ="PANCardNo(this.id)" /></TD>										
</TR>
<tR><td></td></tR>

 <TR>
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="RELIGION" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<TD>
			<hdiits:select  captionid="RELIGION" bundle="${commonLables}" name="Religion"  sort="false" onchange="getCasts()"> 
			<hdiits:option value="-1">-----------Select-----------</hdiits:option>
			<c:forEach var="religion" items="${ReligionList}">
				<c:choose>
   					<c:when test="${hrEisEmpMst.cmnLookupMstByEmpReligionId.lookupId==religion.lookupId}">
    						<option value=<c:out value="${religion.lookupId}" /> selected="true">
							<c:out value="${religion.lookupDesc}"/></option>
   					</c:when>
   					<c:otherwise>
   						<option value=<c:out value="${religion.lookupId}"/>>
						<c:out value="${religion.lookupDesc}"/></option>
   					</c:otherwise>
				</c:choose> 
	    					
	    					
			</c:forEach>		
		
			</hdiits:select>
		
		
		
		
		
	<!--  		</TD>	
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="CASTE"  bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<TD>
			<hdiits:select  captionid="CASTE" id="empCastId" bundle="${commonLables}" sort="false" name="emp_caste_id" onchange="getSubCasts()"> 
			<hdiits:option value="-1">-----------Select-----------</hdiits:option>
			<c:forEach var="cast" items="${castList}">
				<c:choose>
					<c:when test="${hrEisEmpMst.cmnLookupMstByEmpCasteId.lookupId==cast.lookupId}">
	    				<option value="${cast.lookupId}" selected="true"> ${cast.lookupDesc} </option>
	    			</c:when>
	    			<c:otherwise>
	    				<option value="${cast.lookupId}"> ${cast.lookupDesc} </option>
	    			</c:otherwise>
				</c:choose>							
			</c:forEach>		
		
			</hdiits:select>
			
		<%--	<hdiits:text
				name="emp_caste_id"
				 caption="emp_caste_id"  maxlength="10"  validation ="txt.isname"  default ="${hrEisEmpMst.empCasteId}" captionid="emp_caste_id"/>
			--%>
			</TD>
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="SUBCASTE" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<TD>
			<hdiits:select  captionid="SUBCASTE" id="empSubCastId" bundle="${commonLables}" sort="false" name="emp_sub_caste" > 
			<hdiits:option value="-1">-----------Select-----------</hdiits:option>
			<c:forEach var="subCast" items="${subCastList}">
				<c:choose>
					<c:when test="${hrEisEmpMst.cmnLookupMstByEmpSubCasteId.lookupId==subCast.lookupId}">
	    				<option value="${subCast.lookupId}" selected="true"> ${subCast.lookupDesc} </option>
					</c:when>							
					<c:otherwise>
							<option value="${subCast.lookupId}" > ${subCast.lookupDesc} </option>
					</c:otherwise>
				</c:choose>							
			</c:forEach>		
		
			</hdiits:select>-->
			
			<%-- <hdiits:text
				name="emp_sub_caste"
				 caption="emp_sub_caste"  maxlength="10"  validation ="txt.isname" default ="${hrEisEmpMst.empSubCaste}" captionid="emp_sub_caste"/>
				 --%>
				 </TD>
			
		   <hdiits:hidden  id="empCastId" name="emp_caste_id" default="1" />
		   <hdiits:hidden  id="empSubCastId" name="emp_sub_caste" default="1"  />
		   
		   <TD  class="fieldLabel"><fmt:message key="GPF.ACC_NO" bundle="${commonLables}"/></TD>
			<TD><hdiits:hidden  name="gpfAcctNoOld" default="${hrEisEmpMst.orgEmpMst.empGPFnumber}" />
				<hdiits:text  onblur = "PANCardNo(this.id)" id = "gpf"  name="gpfAcctNo" default="${hrEisEmpMst.orgEmpMst.empGPFnumber}" captionid="ACC.NO" bundle="${commonLables}" readonly=""  maxlength="40"   size="22" /> </TD>
			
	
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="CLASS" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<TD>
			<hdiits:select  captionid="CLASS" bundle="${commonLables}" sort="false" name="gradeName" id="gradeName" default="${orgGradeMst.gradeId }"> 
			<%--<hdiits:option value="-1">-----------Select-----------</hdiits:option>--%>
			<c:forEach var="class" items="${ClassList}">
	    					<!-- <option value=<c:out value="${class.gradeId}"/>>  -->
	    					
	    					<c:choose>
		    					<c:when test="${class.gradeId==orgGradeMst.gradeId}">
		    						<option value=<c:out value="${class.gradeId}" /> selected="true">
									<c:out value="${class.gradeName}"/></option>
		    					</c:when>
		    					<c:otherwise>
		    						<option value=<c:out value="${class.gradeId}"/>>
									<c:out value="${class.gradeName}"/></option>
		    					</c:otherwise>
	    					</c:choose>
	    				<%--- 	<c:if test="${orgGradeMst.gradeName == GradeMstName}">selected="selected"</c:if>>---%>
							<!--<c:out value="${class.gradeName}"/></option>-->
			</c:forEach>	
		
			</hdiits:select>
			
			</TD>		
</TR>

<TR>
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="CATEGORY" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<TD>
			<hdiits:select  captionid="CATEGORY" bundle="${commonLables}" name="Category"  sort="false"   > 
			<hdiits:option value="-1">-----------Select-----------</hdiits:option>
			<c:forEach var="category" items="${categoryList}">
	    					<c:choose>
		    					<c:when test="${hrEisEmpMst.cmnLookupMstByEmpCategoryId.lookupId==category.lookupId}">
		    						<option value="${category.lookupId}" selected="true"> ${category.lookupDesc} </option>
		    					</c:when>
		    					<c:otherwise>
		    						<option value="${category.lookupId}"> ${category.lookupDesc} </option>
		    					</c:otherwise>
	    					</c:choose>
	    					
	    					
	    					
			</c:forEach>
			</hdiits:select>		
			</TD>
	
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="HOBBY" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
						<td colspan ="4"><hdiits:textarea rows="3" cols="50" name="emp_hobby" caption ="Description" default ="${hrEisEmpMst.empHobby}" onblur="checkSplChar(this)"  maxlength="50"></hdiits:textarea></td>
</TR>
<tR>
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="email" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<TD>
			<hdiits:text
				name="email"  maxlength="49"   default ="${hrEisEmpMst.email}"  captionid="email"/></TD>
			

	<TD class="fieldLabel" >
			<b><hdiits:caption captionid="contactNo" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<TD>
			<hdiits:number name="contactNo"  maxlength="14"   default ="${hrEisEmpMst.contactNo}" captionid="contactNo" bundle="${commonLables}"  />
			</TD></tR>

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
			<fmt:formatDate var="joinDate" pattern="dd/MM/yyyy" value="${hrEisEmpMst.orgEmpMst.empDoj}"/>
			<TD> <hdiits:text captionid="DATE_OF_JOINING"  bundle="${commonLables}" name="emp_doj"  validation ="txt.isrequired,txt.isdt"  mandatory ="true"  onblur="checkConfDate()"  default ="${joinDate}" readonly="true"/></TD>
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="CONFIRMATION_DUE_DATE" bundle="${commonLables}"></hdiits:caption></b>								
			</TD>
			<fmt:formatDate var="empConfDueDt" pattern="dd/MM/yyyy" value="${hrEisEmpMst.empConfDueDt}" />
			<TD> <hdiits:text captionid="CONFIRMATION_DUE_DATE" bundle="${commonLables}" name="emp_conf"  default ="${empConfDueDt}" readonly="true" onblur="checkConfDate()"/></TD>	
			 <TD class="fieldLabel" >
			<b><hdiits:caption captionid="Retirement_date" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<fmt:formatDate var="retireDate" pattern="dd/MM/yyyy" value="${hrEisEmpMst.orgEmpMst.empSrvcExp}"/>
			<TD> <hdiits:text captionid="Retirement_date"  bundle="${commonLables}" name="emp_leave_dt" onblur="checkConfDate()" default ="${retireDate}" readonly="true"/></TD>
			
</TR>
<tR><td></td></tR>
<TR>
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="SALARY_MODE" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<TD>
			<hdiits:select  captionid="SALARY_MODE" bundle="${commonLables}" name="SalMode" sort="false"  > 
			<hdiits:option value="">-----------Select-----------</hdiits:option>
			<c:forEach var="salmode" items="${salmodeList}">
	    					<c:choose>
		    					<c:when test="${hrEisEmpMst.empSalDisbMd==salmode.lookupId}">
		    						<option value=<c:out value="${salmode.lookupId}" /> selected="true">
									<c:out value="${salmode.lookupDesc}"/></option>
		    					</c:when>
		    					<c:otherwise>
		    						<option value=<c:out value="${salmode.lookupId}"/>>
									<c:out value="${salmode.lookupDesc}"/></option>
		    					</c:otherwise>
	    					</c:choose>
	    					
			</c:forEach>	
			</hdiits:select>	
			</TD>
		
			<TD class="fieldLabel" >
			<B><hdiits:caption captionid="EMP_TYPE" bundle="${commonLables}"></hdiits:caption></B>
			</TD>
			<TD>
			<hdiits:select  captionid="EMP_TYPE" bundle="${commonLables}" name="EmpType" sort="false" validation="sel.isrequired"  mandatory="true"  > 
			<hdiits:option value="">-----------Select-----------</hdiits:option>
		  <c:forEach var="emptype" items="${emptypeList}">
	    					<c:choose>
		    					<c:when test="${hrEisEmpMst.empType==emptype.lookupId}">
		    						<option value=<c:out value="${emptype.lookupId}" /> selected="true">
									<c:out value="${emptype.lookupDesc}"/></option>
		    					</c:when>
		    					<c:otherwise>
		    						<option value=<c:out value="${emptype.lookupId}"/>>
									<c:out value="${emptype.lookupDesc}"/></option>
		    					</c:otherwise>
	    					</c:choose>
			</c:forEach>
			</hdiits:select>	
			</TD>
		
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="EMP_RECRUIT_SRC" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<TD>
			<hdiits:select  captionid="EMP_RECRUIT_SRC" bundle="${commonLables}" name="EmpRecSrc" sort="false" > 
			<hdiits:option value="">-----------Select-----------</hdiits:option>
			<c:forEach var="emprecsrc" items="${recruitList}">
	    					<c:choose>
		    					<c:when test="${hrEisEmpMst.empRecruitSrc==emprecsrc.lookupId}">
		    						<option value=<c:out value="${emprecsrc.lookupId}" /> selected="true">
									<c:out value="${emprecsrc.lookupDesc}"/></option>
		    					</c:when>
		    					<c:otherwise>
		    						<option value=<c:out value="${emprecsrc.lookupId}"/>>
									<c:out value="${emprecsrc.lookupDesc}"/></option>
		    					</c:otherwise>
	    					</c:choose>
			</c:forEach>	
			</hdiits:select>	
			</TD>
	</TR>
	<!-- <TR>
			<TD class="fieldLabel" >
			<b><hdiits:caption captionid="STATUS" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<TD>
			<hdiits:select  captionid="STATUS" bundle="${commonLables}" name="EmpStatus" sort="false"  > 
			<hdiits:option value="">-----------Select-----------</hdiits:option>
			<c:forEach var="empstatus" items="${statusList}">
	    					<c:choose>
		    					<c:when test="${hrEisEmpMst.empStatusId==empstatus.lookupId}">
		    						<option value=<c:out value="${empstatus.lookupId}" /> selected="true">
									<c:out value="${empstatus.lookupDesc}"/></option>
		    					</c:when>
		    					<c:otherwise>
		    						<option value=<c:out value="${empstatus.lookupId}"/>>
									<c:out value="${empstatus.lookupDesc}"/></option>
		    					</c:otherwise>
	    					</c:choose>
			</c:forEach>	
			</hdiits:select>	
			</TD>
     </TR>  -->
<tR><td></td></tR>					
      
      
      
      
      
         
	</table> 
    
 </div>
 
<hdiits:jsField  name="validate" jsFunction="validateForm()" />  
<fmt:setBundle basename="resources.payroll.payrollLables" var="Lables" scope="request"/>

<c:choose>
<c:when test="${empAllRec eq true }">
<hdiits:hidden default="getEmpData&empId=${resValue.hrEisEmpMst.empId}&edit=Y&empAllRec=Y" name="givenurl"/>
</c:when>

<c:otherwise>
<hdiits:hidden default="getEmpData" name="givenurl"/>
</c:otherwise>
</c:choose>
<c:if test="${FromBasicDtlsNew eq 'YES'}">
<hdiits:hidden default="YES" name="closeWindow"/>
</c:if>
	<jsp:include page="../../core/PayTabnavigation.jsp" /></div>
	
	
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		document.frmBF.contactNo.focus();
		if("${msg}"!=null&&"${msg}"!='' && "${empAllRec}"=='false')
		{
			
			alert("${msg}");
			if("${FromBasicDtlsNew}"=="YES")
			{
				window.parent.opener.location="./hrms.htm?actionFlag=getOtherDataMerged&otherId=${otherId}&edit=Y&empAllRec=false&MergedScreen=YES&PreviewBill=YES";
				window.close();
			}else{
			var url="./hrms.htm?actionFlag=getEmpData";
			document.frmBF.action=url;
			document.frmBF.submit();
			}
			}
		else if("${msg}"!=null&&"${msg}"!='' && "${empAllRec}"=='true')
		{
            alert("${msg}");
			var url="./hrms.htm?actionFlag=getEmpData&empId=${empId}&edit=Y&empAllRec=Y";
//			alert('url to submit ' + url);
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

	    								