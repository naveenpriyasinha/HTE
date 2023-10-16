<html>


<%
try {
%>
<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="enLables" scope="page"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>


<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="empId" value="${resultValue.empId}" > </c:set>
<c:set var="hrEisEmpMst" value="${resultValue.hrEisEmpMst}" > </c:set>



<script type="text/javascript">


function chkValue()
{
	
	var empId=document.getElementById("Employee_ID_EmpLoanSearch").value;
	if(empId=='')
	{
	alert('Plese Select employee');
	return false;
	}
	else
	{

	var url="./hrms.htm?actionFlag=getempAllRec&actionName=empView&empId=" + empId;
	window.document.getElementById("Frame").style.display='';
     if(document.getElementById("homeImg")!=null)
		document.getElementById("homeImg").style.display='none';
	window.document.getElementById("left").src="./hrms.htm?viewName=treeMenu&empId="+empId;
	//window.document.getElementById("right").src="./hrms.htm?actionFlag=getEmpData&Employee_ID_EmpInfoSearch="+empId+"&empAllRec=Y";
	window.document.getElementById("right").src="./hrms.htm?actionFlag=getOtherData&Employee_ID_EmpOtherSearch=" + empId +"&empAllRec=true";
	
	return true;
	}
}

function setSessionTrue()
{
<%
session.setAttribute("searchFromEmpAllRec","true");
%>
}
function setSessionfalse()
{
<%
session.setAttribute("searchFromEmpAllRec","false");
%>
}




function FramResize()
{
window.document.getElementById("right").height="1000px";
window.document.getElementById("right").width="100%";
window.document.getElementById("serch").style.display='none';
window.document.getElementById("left").style.display='none';
}

function defaultSize()
{
window.document.getElementById("right").height="500px";
window.document.getElementById("right").width="80%"
window.document.getElementById("serch").style.display='';
window.document.getElementById("left").style.display='';
}



</script>

<body onload="setSessionTrue()" onunload="setSessionfalse()" >

 <frameset rows="*" cols="*" border="1" frameborder="1">
 

<hdiits:form name="frmBF" validate="true" method="POST" 
	action="" encType="multipart/form-data">
<table id="serch" width="85%" height="10px" align="center">
		<tr>
			<td>
				<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
						<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
						<jsp:param name="SearchEmployee" value="EmpLoanSearch"/>
						<jsp:param name="formName" value="frmBF"/>
						<jsp:param name="functionName" value="chkValue"/>
					</jsp:include>
			</td>
		</tr>
		<tr >
			<td align="center">
				<fieldset style="background: #eeeeee;">
				<hdiits:button type="button" captionid="populateDetail" bundle="${enLables}"  name="details" onclick="chkValue()"/>	
				<!--   <hdiits:button type="button" captionid="viewAll" bundle="${enLables}"  name="viewAll" onclick="submitForm()"/>	-->			
				</fieldset>
			</td>
		</tr>	
	</table>
	</hdiits:form>
	</body>
	</html>
	<div id="Frame" style="display: none;" class="tabFrame">
  	<frameset> 
 	 <iframe src=""  align="top" name="left"   width="16%" height="500px" id="left"  scrolling="no"></iframe>
  	<iframe src="" name="right"  width="84%" height="500px" id="right" ></iframe>  
	</frameset>
	</div>
	</body>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
</html>
	    								