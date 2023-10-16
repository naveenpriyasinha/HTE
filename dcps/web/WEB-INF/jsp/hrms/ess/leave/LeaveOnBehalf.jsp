<%
try {
%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<fmt:setBundle basename="resources.ess.leave.AlertMessages"	var="alertLables" scope="request" />
<fmt:setBundle basename="resources.ess.leave.LeaveCaption"	var="LeaveCaption" scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="leaveUserSearch" value="${resultValue.leaveUserSearch}"></c:set>	
<style type="text/css">    
            .pg-normal {
                color: black;
                font-weight: normal;
                text-decoration: none;    
                cursor: pointer;    
            }
            .pg-selected {
                color: black;
                font-weight: bold;        
                text-decoration: underline;
                cursor: pointer;
            }
</style>
	
<script>
function SearchEmp()
{
	var href='hdiits.htm?actionFlag=getEmpSearchSelData&multiple=false';
	window.open(href,'chield','width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
}

var empArray = new Array();

function empSearch(from)		
{	
	for(var i=0; i<from.length; i++)
	{
	empArray[i] = from[i].split("~"); 
	records=empArray[i];	
	}
	for(var j=0; j<from.length; j++)
	{
		var single = empArray[j];

		
		var empName = single[1];
		var userid = single[2];				
		var desigName = single[7];
		var depName=single[11];	
		
		document.forms[0].action="hdiits.htm?actionFlag=viewLeaveTypes&flag=1&beHalfUserId="+userid;
		document.forms[0].submit();	
		//2rd cell
	 	//empName
		//3th cell
	  //	depName
		//4th cell
	  //	desigName
	}
}
/*  Employee Search code End  */
function submitAdminLeaveMemberAdd()
{
	document.getElementById('Add').disabled=true;
	document.getElementById('Update').disabled=true;
	frmAdminLeaveMemberAdd.submit();
}
</script>
	<hdiits:form name="frmAdminLeaveMemberAdd" validate="true" method="post" action="" encType="multipart/form-data">
	<table id="adminLeaveTable" width="100%" align="center" >
		<tr align="center">
			<td><b><hdiits:caption	captionid="HRMS.AddEmpForBal" bundle="${LeaveCaption}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b><hdiits:button id="search" type="button" name="search" tabindex="21"
				captionid="HRMS.Search" bundle="${LeaveCaption}" onclick="SearchEmp();" />
			</tr>
	</table>	
	</hdiits:form>	
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>