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
<script type="text/javascript"
	src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="empName" value="${resValue.empList}"> </c:set>
<c:set var="leaveList" value="${resValue.leaveList}"> </c:set>
<c:set var="gradeList" value="${resValue.gradeList}"> </c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>

<script>


function chkFunc()
{
	var empId=document.getElementById("Employee_ID_EmpSearch").value;
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
		var url = "hrms.htm?actionFlag=getGpfDtls&empId="+empId+"&chk=1";  
		
	    xmlHttp.onreadystatechange = function(){		
			if(xmlHttp.readyState == 4){     			
				if (xmlHttp.status == 200){	
					var gpfNo;
					var XMLDocForAjax=xmlHttp.responseXML.documentElement;
					
					var loanAdvMapping = XMLDocForAjax.getElementsByTagName('empNameMapping');	
					
					if(loanAdvMapping.length != 0) {	
						gpfNo = loanAdvMapping[0].childNodes[0].text;	
							var emp =  loanAdvMapping[0].childNodes[0].text;	
							//added by samir for contractual and fixe scale employee
							var emptype = loanAdvMapping[0].childNodes[1].text;		
							if(emptype==300225 || emptype==300018)
							{
							clearEmployee("EmpSearch");
							document.gpfentry.gpfAcctNo.value='';
							alert("Not Accessible For Fixed and Contractual Employee!!");
							return;
							}
							//end
						
						if(gpfNo==-1){
							alert("The information for "+document.getElementById("Employee_Name_EmpSearch").value+" is already entered into the system");
							retValue=false;
							document.gpfentry.reset();
							
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
		var gpfAcctNo=document.gpfentry.gpfAcctNo.value;
		var url = "hrms.htm?actionFlag=getGpfDtls&gpfAcctNo='"+gpfAcctNo+"'&chk=2";  
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
							document.gpfentry.gpfAcctNo.value='';
							document.gpfentry.gpfAcctNo.focus();
							
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

function validateForm()
{	
	if(!chkuniqueness())
	{
	return false;
	}
	else
	return true;
}
 
  function onlyAlpha1(control) 
{              
              var iChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789/  ";
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
                  alert('Please Enter Proper Name');
                  control.value="";
                  control.focus();
                  return false;
              }
              //return true;              
}
 

function beforeSubmit()
{
				
        //if(chkFunc()==true)
		//{
		var empName = document.getElementById("Employee_ID_EmpSearch").value;
		document.gpfentry.empName.value=empName;
		
		document.gpfentry.action="./hrms.htm?actionFlag=insertGpfDtlsData&edit=N";
		document.gpfentry.submit();
		//}
		//else
		//{
		//window.location.reload();
		//document.gpfentry.action = 'javascript:beforeSubmit()';
		//}
}
</script>
<body>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>


<hdiits:form name="gpfentry" validate="true" method="POST"
	action="javascript:beforeSubmit()" encType="text/form-data">

	<div id="tabmenu">
		<ul id="maintab" class="shadetabs" compact="compact">
			<li class="selected"><a href="#" rel="tcontent1"><font size="2"><b><fmt:message key="GPF.ADDGPFACCTDTLS" bundle="${commonLables}"/></a></li>
		</ul>
	</div>

	<div class="halftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0">
	<table width= "65%" align= "center"><br>
		
		
		<TD class="fieldLabel" colspan="2">
					<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
						<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
						<jsp:param name="SearchEmployee" value="EmpSearch"/>
						<jsp:param name="formName" value="empLeave"/>
						<jsp:param name="functionName" value="chkFunc"/>
					</jsp:include>
		 </td>
		 <td>
         <hdiits:hidden id="empName" name = "empName" default="" />
	    </td>
	   
	   <tr>
			<td><b><fmt:message key="ACC.NO" bundle="${commonLables}"/></td>
			<td><hdiits:text mandatory="true"    name="gpfAcctNo" default="" captionid="ACC.NO" bundle="${commonLables}"  validation="txt.isrequired"  maxlength="40"   size="22"  onblur="onlyAlpha1(this);"/> </td>
	    </tr>
	    
	    <tr>
	    <TD  align="left" class="Label"> <fmt:message key="eis.grade_name" bundle="${commonLables}"/></TD>
	    <td>
	    <hdiits:select name="gradeNo" size="1" id="gradeNo" sort="false"  caption="GradeNo" captionid="gradeNo"
		validation="sel.isrequired" mandatory="true">
	   <hdiits:option value="-1"> --Select-- </hdiits:option>
	    <c:forEach var="gradeList" items="${gradeList}">
         <hdiits:option value="${gradeList.gradeId}"><c:out value="${gradeList.gradeName}"> </c:out></hdiits:option>
         </c:forEach>
	   </hdiits:select>
	   </td>
	   </tr>
	    
	     
	</table>
 	</div>
 	 	<hdiits:jsField  name="validate" jsFunction="validateForm()" /> 
<fmt:setBundle basename="resources.eis.eis_common_lables" var="Lables" scope="request"/>
<hdiits:hidden default="getGpfDtls" name="givenurl"/>
	<jsp:include page="../../core/PayTabnavigation.jsp" />
	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		
	
		
		if("${msg}"!=null&&"${msg}"!='')
		{
			alert("${msg}");
			var url="./hrms.htm?actionFlag=getGpfDtls";
			document.gpfentry.action=url;
			document.gpfentry.submit();
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

