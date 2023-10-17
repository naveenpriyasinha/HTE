<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
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

<script type="text/javascript" src="<c:url value="/script/hrms/eis/commonUtils.js"/>">
</script>	

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="enLables" scope="page"/>


<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	

<script>
function chk(dname1)
{
	var dname=dname1.value;
	
	if(dname!="")
	if(checkSplChar(dname1))
	{
	try
   	{   
   		xmlHttp=new XMLHttpRequest();    
   	}
	catch (e)
	{    // Internet Explorer    
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
    var url = "hrms.htm?actionFlag=getDesigData&chk=1&dname="+dname;  
   
    xmlHttp.onreadystatechange = function()
	{
		
		if (xmlHttp.readyState == 4) 
		{     
			
			if (xmlHttp.status == 200) 
			{
				
				
				
				var XMLDocForAjax=xmlHttp.responseXML.documentElement;
				var desigdetails = XMLDocForAjax.getElementsByTagName('desigdetails');	
				//var crimeconveyange = XMLDoc.getElementsByTagName('crimeConveyange');	
				//var crimemst = XMLDoc.getElementsByTagName('crimeMst');	
				
				if(desigdetails.length != 0)
				{
						
						if(desigdetails[0].childNodes[0].text!='null')
						{			
							alert("Designation Name already Exists, Please Enter other Name");
							document.frmBF.desg_name.value='';
							document.frmBF.desg_name.focus();
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
</script>




<hdiits:form name="frmBF" validate="true" method="POST"
	action="hrms.htm?actionFlag=insertDesigData" encType="multipart/form-data">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="eis.insertDesg_dtl" bundle="${enLables}"></hdiits:caption></b></a></li>
		
	</ul>
	</div>
	
	
<div class="halftabcontentstyle">


	<div id="tcontent1" class="halftabcontent" tabno="0">

    <br>
   <TABLE width="60%" align="center">
 
 
 <TR><td></td>
			<TD ><b><hdiits:caption captionid="eis.desig_name" bundle="${enLables}"></hdiits:caption>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  </b></TD>
			
			<TD> <hdiits:text
				name="desg_name"
				 captionid="eis.desig_name" bundle="${enLables}"  maxlength="25" size="30" mandatory="true"  validation="txt.isrequired" onkeypress="return chkKey(event)" onblur="chk(this)"/></TD>	
				 
	</TR>
<TR>	</TR><TR>	</TR>
 <TR>		<td></td>
			<TD><b><hdiits:caption captionid="eis.desig_shrt_name" bundle="${enLables}"></hdiits:caption>  </b></TD>
			<TD> <hdiits:text
				name="desg_short_name"
				captionid="eis.desig_shrt_name" bundle="${enLables}"  maxlength="15" size="15" mandatory="true"  validation="txt.isrequired"  onblur="checkSplChar(this)"/></TD>	
</tr>

      
      
      
      
         
	</table> 
    <br>
 </div>
 
<fmt:setBundle basename="resources.eis.eis_common_lables" var="Lables" scope="request"/>
	<jsp:include page="../../core/PayTabnavigation.jsp" /></div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		if("${msg}"!=null&&"${msg}"!='')
		{
			alert("${msg}");
			var url="./hrms.htm?actionFlag=getDesigData";
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
