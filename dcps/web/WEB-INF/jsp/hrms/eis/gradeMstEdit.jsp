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
<script type="text/javascript"
	src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>
<c:set var="actionList" value="${resValue.actionList}" ></c:set>
<c:set var="grade_name" value="${actionList.gradeName}" ></c:set>
<c:set var="grade_desc" value="${actionList.gradeDesc}" ></c:set>
 <c:set var="msg" value="${resValue.msg}" ></c:set>
<script>
window.self.onerror = function() { return true; }

function chk(gname1)
{

	var gname=gname1.value;
	
	if(gname!="")
	{
  if(!checkSplChar(gname1))
  {   
   document.frmBF.grade_name.value = '';
   document.frmBF.grade_name.focus();
  }
  else
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
    var url = "hrms.htm?actionFlag=getGradeData&chk=1&editname=${grade_name}&gname="+gname;  

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


<fmt:setBundle basename="resources.eis.eis_common_lables" var="enLables" scope="page"/>


<hdiits:form name="frmBF" validate="true" method="POST"
	action="hrms.htm?actionFlag=insertGradeData&edit=Y&gradeid=${actionList.gradeId}" encType="multipart/form-data">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="eis.updateGrade_dtl" bundle="${enLables}"></hdiits:caption></b></a></li>
		
	</ul>
	</div>
	
	
<div class="halftabcontentstyle">


	<div id="tcontent1" class="halftabcontent" tabno="0">

    <br>
   <TABLE width="60%" align="center">
 
 
 <TR><td> </td>
			<TD width=22%><b><hdiits:caption captionid="eis.grade_name" bundle="${enLables}"></hdiits:caption> </b> </TD>
			
			<TD> <hdiits:text
				name="grade_name"
				 captionid="eis.grade_name"  mandatory="true"  default="${grade_name}" bundle="${enLables}" size="20" maxlength="30" validation="txt.isrequired" onblur="chk(this)"  onkeypress="return chkKey(event)"/></TD>	
				 
	</TR>

 <TR>			 
	</TR><tr></tr>
<TR>	<td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<td><b><hdiits:caption captionid="eis.grade_desc" bundle="${enLables}"></hdiits:caption>  </b></td>
			<td ><hdiits:textarea rows="3"  mandatory="true"  cols="33" name="grade_desc" default="${grade_desc}" maxlength="80"  captionid="eis.grade_desc" bundle="${enLables}" validation="txt.isrequired" onblur="checkSplChar(this)"  ></hdiits:textarea>
</TR>
 <TR>			 
	</TR><tr></tr>

      
      
      
      
         
	</table> 
    
 </div>
 
<fmt:setBundle basename="resources.payroll.payrollLables_en_US" var="Lables" scope="request"/>
	<jsp:include page="../../core/PayTabnavigation.jsp" /></div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		if("${msg}"!=null&&"${msg}"!='')
		{
			alert("${msg}");
			var url="./hrms.htm?actionFlag=getGradeData";
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

