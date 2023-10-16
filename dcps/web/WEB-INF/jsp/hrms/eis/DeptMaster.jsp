<%
try {

%>
<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript"  
    src="script/common/commonfunctions.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>

<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/commonUtils.js"/>">
</script>	

<c:set var="resultObj" value="${result}" > </c:set>
  <c:out value='${resultObj.resultValue.result}' /> 
<c:set var="resValue" value="${resultObj.resultValue.result}" > </c:set>

<c:if test="${resultObj.resultValue.locale == 'en_US'}">
	<fmt:setLocale value="${sessionScope.locale}"/>
	<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
</c:if>
  <%

%>
${resValue.msg}

<script type="text/javascript" language="JavaScript">

function chk(dname1)
{
	var dname=dname1.value;
	
	if(dname!="")
	if(placeFloat(dname1))
	{
	try
   	{   
   		xmlHttp=new XMLHttpRequest();    
   	}
	catch (e)
	{     
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
		        return false;        
		    }
		}
	}
    var url = "hrms.htm?actionFlag=getDeptView&chk=1&dname="+dname;  
    xmlHttp.onreadystatechange = function()
	{
		
		if (xmlHttp.readyState == 4) 
		{     
			
			if (xmlHttp.status == 200) 
			{
				var XMLDocForAjax=xmlHttp.responseXML.documentElement;
				var deptdetails = XMLDocForAjax.getElementsByTagName('deptdetails');	
				if(deptdetails.length != 0)
				{
						if(deptdetails[0].childNodes[0].text!='null')
						{			
							alert("This Department Name already Exists, Please Enter other Name");
							document.deptMaster.deptname.value='';
							document.deptMaster.deptname.focus();
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
function  chkKey(e)
{
	if(window.event) // IE
	{
		if(e.keyCode==13)
		{
			return false;
		}
		else
		{
			return true;
		}
	
	}
	
	
}

function submitForm(){ 
	win = window.open("common/progressbar.html",'','width=270,height=30,titlebar=0,toolbar=0,left=400,top=300');
	return true;
}
function init()
{
	//alert(${resValue.msg});
	document.forms[0].deptname.focus();
}
</script>
<body onload="init()">
<hdiits:form name="deptMaster" validate="true" method="POST"
	action="./hdiits.htm?actionFlag=AddDepartment" >

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b> <hdiits:caption captionid="HR.EIS.InsertDeptMaster" bundle="${commonLables}"/></b></a></li>
	</ul>
</div>
	
	
<div class="halftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0">
<br>
	<TABLE align=center width="70%">  
	<TR><td></td>
		<TD>
		   <b> <hdiits:caption captionid="HR.EIS.DeptName" bundle="${commonLables}"/></b>
		</TD>			
		<TD>
			<hdiits:text name="deptname" default=""  mandatory="true"
			captionid="HR.EIS.DeptName" bundle="${commonLables}"  onblur="chk(this)" maxlength="30" size="40" validation="txt.isrequired" />
		</TD>	
	</tr><tr></tr>
	<TR><td></td>
		<TD>
		   <b><hdiits:caption captionid="HR.EIS.DeptShortName" bundle="${commonLables}"/></b>
		</TD>			
		<TD>
			<hdiits:text name="deptshortname" default="" mandatory="true"
			 captionid="HR.EIS.DeptShortName" bundle="${commonLables}" maxlength="10" size="40" validation="txt.isrequired" onblur="placeFloat(this)"/>
		</TD>	
	</tr><tr></tr>
	<TR><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<TD> 
		    <b><hdiits:caption captionid="HR.EIS.DeptDesc" bundle="${commonLables}"/></b>
		</TD>			
		<TD>
			<hdiits:textarea name="deptdesc" default=""  mandatory="true"
			 captionid="HR.EIS.DeptDesc" bundle="${commonLables}"  rows="3" cols="43" validation="txt.isrequired" onblur="checkSplChar(this)" ></hdiits:textarea>
		</TD>	
	</tr>
	
	</TABLE>
	<br>
	</div> 
	

	<jsp:include page="../../core/PayTabnavigation.jsp" />
</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />

<script type="text/javascript" language="javascript">

</script>

</hdiits:form>
</body>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>



