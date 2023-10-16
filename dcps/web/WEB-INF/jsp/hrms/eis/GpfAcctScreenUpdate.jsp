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
<c:set var="actionList" value="${resValue.actionList}"> </c:set>
<c:set var="gradeList" value="${resValue.gradeList}"> </c:set>

<c:set var="msg" value="${resValue.msg}" ></c:set>


<script>


function chkuniqueness()
{	
	var gpfAcctNo=document.GPFAcctUpdate.gpfAcctNo.value;
	var retValue=true;
	if(gpfAcctNo=="")
	{
		//alert("Please Enter Account No. first");
		//retValue=false;
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
		var userID=-1;
		var url = "hrms.htm?actionFlag=getGpfDtls&gpfAcctNo="+gpfAcctNo+"&userID=${actionList.userId}&chk=2";  
		
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
							//retValue=false;
							document.GPFAcctUpdate.gpfAcctNo.value='';
							document.GPFAcctUpdate.gpfAcctNo.focus();
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


function onlyAlpha11(control) 
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
 

function submit()
{
 		
       // if(chkuniqueness()==true)
		//{
 		document.GPFAcctUpdate.action="./hrms.htm?actionFlag=insertGpfDtlsData&gpfId=${actionList.userId}&edit=Y"; 
  	    document.GPFAcctUpdate.submit();
		//}
		//else
		//{
		//document.GPFAcctUpdate.action = 'javascript:submit()';
		//} 		
}
</script>


<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<hdiits:form name="GPFAcctUpdate" validate="true" method="POST"
	action="javascript:submit()" encType="text/form-data">

	<div id="tabmenu">
		<ul id="maintab" class="shadetabs" compact="compact">
			<li class="selected"><a href="#" rel="tcontent1"><font size="2"><b><fmt:message key="GPF.EditGPFACCTDTLS" bundle="${commonLables}"/></a></li>
		</ul>
	</div>

	<div class="halftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0">
	<table width= "65%" align= "center"><br>       
	   

	    
	    	   <tr>
			<td><b><fmt:message key="LEV.empName" bundle="${commonLables}"/></td>
			<td><hdiits:text  mandatory="true" name="EmpName" default="${actionList.empName}" captionid="LEV.empName" bundle="${commonLables}" readonly="true"   maxlength="10"   size="22"  /> </td>
	    </tr>
	    
	    
	    <tr>
			<td><b><fmt:message key="ACC.NO" bundle="${commonLables}"/></td>
			<td><hdiits:text name="gpfAcctNo"  onblur="onlyAlpha11(this);chkuniqueness();" default="${actionList.gpfAcctNo}" captionid="ACC.NO" bundle="${commonLables}"   validation="txt.isrequired"  maxlength="40"  mandatory="true"   size="22" /> </td>
	    </tr>
	    
	    <tr>
	    <TD  align="left" class="Label"> <fmt:message key="eis.grade_name" bundle="${commonLables}"/></TD>
	    <td>
	    <hdiits:select name="gradeNo" size="1" id="gradeNo" sort="false"  caption="GradeNo" captionid="gradeNo"
		validation="sel.isrequired" mandatory="true">
	   <hdiits:option value="-1"> --Select-- </hdiits:option>
	    <c:forEach var="gradeList" items="${gradeList}">
	    <c:choose>
	    <c:when test="${gradeList.gradeId eq actionList.orgGradeMst.gradeId}">
         <hdiits:option value="${gradeList.gradeId}" selected="true"><c:out value="${gradeList.gradeName}"> </c:out></hdiits:option>
         </c:when>
         <c:otherwise>
         <hdiits:option value="${gradeList.gradeId}"><c:out value="${gradeList.gradeName}"> </c:out></hdiits:option>
         </c:otherwise>
         </c:choose>
         </c:forEach>
	   </hdiits:select>
	   </td>
	   </tr>
	     
	</table>
 	</div>
<fmt:setBundle basename="resources.payroll.payrollLables_en_US" var="Lables" scope="request"/>
<hdiits:hidden default="getGpfDtls" name="givenurl"/>
	<jsp:include page="../../core/PayTabnavigation.jsp" />
	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		document.GPFAcctUpdate.gpfAcctNo.focus();
		
		if("${msg}"!=null&&"${msg}"!='')
		{
			alert("${msg}");
			var url="./hrms.htm?actionFlag=getGpfDtls";
			document.GPFAcctUpdate.action=url;
			document.GPFAcctUpdate.submit();
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

