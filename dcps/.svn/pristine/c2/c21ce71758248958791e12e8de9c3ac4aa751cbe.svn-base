<%
try {
%>
<%@ include file="../../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript"  
    src="script/common/commonfunctions.js">
</script>

  
<fmt:setBundle basename="resources.eis.eisLables_en_US" var="empSearchLables" scope="request"/>


<script type="text/javascript">
function openAppWindow(actionFlag)
			{
				//alert("Value is:-"+document.getElementById('USER_ID_EmpPayFixationSearch').value);				
			//var userId = document.getElementById("hdnUserId").value;
//				var empId = document.frmEmployeeAppHome.txtEmployeeName.value;
			var userId=document.getElementById('USER_ID_EmpPayFixationSearch').value;
		    //alert("The user Id is:-"+userId);
				if(userId != ''){
					var href = "hrms.htm?actionFlag="+  actionFlag + "&userId="+ userId;// + "&empId="+empId;				
					objChildWindow = window.open(href,"Country","toolbar=no, location=no, directories=no,status=yes, menubar=no, scrollbars=yes, resizable=yes, top=10, left=10, width=980, height=660, copyhistory=no");									
				}
				else {
					alert("Select an Employee");
					return false;
				}
			/*	if(document.getElementById('USER_ID_EmpPayFixationSearch') == '' )
					
					return true;
				}
				else {

					return flase;
				}
			  */  	
			    // displayModalDialog (url ,title ,"toolbar=no, location=no, directories=no,status=yes, menubar=no, scrollbars=yes, resizable=yes, top=50, left=100, width="+ width +", height="+ height +", copyhistory=no");
			}
			
	/*function chkValue(){
		var userId=document.getElementById('USER_ID_EmpPayFixationSearch').value;
	    alert("The user Id is:-"+userId);
		if(userId != ''){
					var href = "hrms.htm?actionFlag="+  actionFlag + "&userId="+ userId;// + "&empId="+empId;				
					objChildWindow = window.open(href,"Country","toolbar=no, location=no, directories=no,status=yes, menubar=no, scrollbars=yes, resizable=yes, top=10, left=10, width=980, height=660, copyhistory=no");									
				}
				else {
					alert("Select an Employee");
					return false;
				}
	}*/			
</script>

<hdiits:form name="employeeSearchView" validate="true" method="POST" encType="text/form-data">
	<table  width="85%" align="center">
		<tr>
			<td >
				<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
						<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
						<jsp:param name="SearchEmployee" value="EmpPayFixationSearch"/>
						<jsp:param name="formName" value="EmpPayFixationSearch"/>
					</jsp:include>
			</td>
		</tr>
		<tr >
			<td align="center">
				<fieldset style="background: #eeeeee;">
				<hdiits:submitbutton type="button" captionid="populateDetail" bundle="${enLables}"  name="details" onclick="javascript:openAppWindow('showEmpPayfixation&workFlowEnabled=false')"/>	
				
				</fieldset>
			</td>
		</tr>	
	</table>
	</hdiits:form>
	<table>
	<%--	<tr>		
			<td align="center"><a href="#" onclick="javascript:openAppWindow('showIncrEmpList&workFlowEnabled=false')"><fmt:message key="EIS.PayIncrement" bundle="${empSearchLables}"/></a></td>	
		</tr> --%>
		<tr>
			<td align="center" id="empName"><a href="#" onclick="javascript:openAppWindow('showEmpPayfixation&workFlowEnabled=false')"><fmt:message key="EIS.PayFixation" bundle="${empSearchLables}"/></a></td>	
		</tr>
	</table>
	
	
</body>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>


	