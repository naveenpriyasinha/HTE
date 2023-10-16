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
<script type="text/javascript"
	src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>
<script>
function chk(gname)
{
	var gname=gname.value;

	if(gname!="")
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
    var url = "hrms.htm?actionFlag=getGradeData&chk=1&gname="+gname;  

    xmlHttp.onreadystatechange = function()
	{
		
		if (xmlHttp.readyState == 4) 
		{     
			
			if (xmlHttp.status == 200) 
			{
				
				
				
				var XMLDocForAjax=xmlHttp.responseXML.documentElement;
				var gradedetails = XMLDocForAjax.getElementsByTagName('gradedetails');	
				//var crimeconveyange = XMLDoc.getElementsByTagName('crimeConveyange');	
				//var crimemst = XMLDoc.getElementsByTagName('crimeMst');	
				
				if(gradedetails.length != 0)
				{
						
						if(gradedetails[0].childNodes[0].text!='null')
						{			
							alert("Grade Name already Exists, Please Enter other Name");
							document.frmBF.grade_name.value='';
							document.frmBF.grade_name.focus();
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

<fmt:setLocale value="${sesssionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="enLables" scope="page"/>


<hdiits:form name="frmBF" validate="true" method="POST"
	action="hrms.htm?actionFlag=insertGradeData" encType="multipart/form-data">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="eis.insertGrade_dtl" bundle="${enLables}"></hdiits:caption></b></a></li>
		
	</ul>
	</div>
	
	
<div class="halftabcontentstyle">


	<div id="tcontent1" class="halftabcontent" tabno="0">
<br>
    
   <TABLE width="60%" align="center">
 
 
 <TR><TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>
			<TD width=35%><b><hdiits:caption captionid="eis.grade_name" bundle="${enLables}"></hdiits:caption> : </b></TD>
			
			<TD> <hdiits:text
				name="grade_name"
				 captionid="eis.grade_name" mandatory="true" bundle="${enLables}"  maxlength="30" size="30" validation="txt.isrequired" onblur="chk(this)" onkeypress="return chkKey(event)"/></TD>	
				 
	</TR>

 <TR>	</TR> <TR>	</TR>
<TR> <TD></TD>
			<td><b><hdiits:caption captionid="eis.grade_desc" bundle="${enLables}"></hdiits:caption> :</b> </td>
			<td ><hdiits:textarea rows="3" cols="32" name="grade_desc" mandatory="true" captionid="eis.grade_desc" bundle="${enLables}" validation="txt.isrequired" onblur="checkSplChar(this)"  ></hdiits:textarea>
</TR>

      
      
      
      
         
	</table> 
    
 </div>
 

	
 
 

	<jsp:include page="../../core/PayTabnavigation.jsp" /></div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

