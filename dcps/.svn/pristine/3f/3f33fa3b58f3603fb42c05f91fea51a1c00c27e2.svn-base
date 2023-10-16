<%
try {
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.hr.acr.acr_AlertMessages" var="commonLables1" scope="request"/>

<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript"  
    src="script/common/commonfunctions.js">
</script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>

<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>


<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
	
	

<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>


<script>
function validation()
{
		
		var a=document.getElementById("selectDesgn");
		var b=document.getElementById("selectAuthority");
		
		if(a.selectedIndex == 0)
		{
		
			alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.SelectDesgn"/>');
			document.getElementById("selectDesgn").focus();
			return;
		}
		//else if(c.selectedIndex == 0)
		//{
			//alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.SelectLocation"/>');
			//document.getElementById("selectLocation").focus();
			
				//return;
		//}
		else if(b.selectedIndex == 0)
		{
			alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.SelectAuthority"/>');
			document.getElementById("selectAuthority").focus();
			return;
		}
		
		else
		{
		submit1();
		}

}

function submit1()
{


	document.HierarchyGroupMst.method="POST";

	document.HierarchyGroupMst.action="./hrms.htm?actionFlag=ACRPointGroupSubmit";
	
	document.HierarchyGroupMst.submit();
	
}

function closeButtonHandler()
{
	document.HierarchyGroupMst.method="POST";

	document.HierarchyGroupMst.action="./hrms.htm?actionFlag=getHomePage";
	
	document.HierarchyGroupMst.submit();
}
</script>



<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	


<c:set var="locList" value="${resValue.locList}"></c:set>
<c:set var="desgnList" value="${resValue.desgnList}"></c:set>
<c:set var="year" value="${resValue.year}"></c:set>




<fmt:setBundle basename="resources.hr.acr.acr" var="commonLables" scope="request"/>




<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="HR.ACR.ACR" bundle="${commonLables}"/></b></a></li>
	</ul>
</div>
<div >
	 <div id="tcontent1"  tabno="0">





<table class="tabtable">
<tr bgcolor="#386CB7">
						<td class="fieldLabel" width="100%" align="center">
						<font class="Label3" align="center">
						
							<u>
							<font class="Label3" align="center" color="white">
								<b><hdiits:caption captionid="HR.ACR.SetGroup" bundle="${commonLables}"/></b>
							</font>
							</u>
						</font>
						</td>
					</tr>
					
</table>
<hdiits:form name="HierarchyGroupMst" method="POST" validate="true"  encType="multipart/form-data" action="./hrms.htm?actionFlag=ACRPointGroupSubmit">

	

		
		
			<table id="default" width="100%">
			<tr colspan="4">
				<td width="25%" align="left">

					<b><hdiits:caption captionid="HR.ACR.Designation" bundle="${commonLables}" id="Designation"/>:</b>
				</td>
				<td width="25%" align="left">
				
				<hdiits:select captionid="HR.ACR.Designation" bundle="${commonLables}" name="selectDesgn" id="selectDesgn" mandatory="true" sort="true" > 
				<option value="-1"><hdiits:caption captionid="HR.ACR.Select" bundle="${commonLables}"/></option>
			
			
				<c:forEach var="desgnList" items="${desgnList}" >
			
				<option value="<c:out value="${desgnList.dsgnCode}" />">
			
			
					<c:out value="${desgnList.dsgnName}"/>
			
				</option>
				
				</c:forEach>
			
				
			</hdiits:select>	
				
				</td>
			
			
			<td width="25%" align="left">

					<b><hdiits:caption captionid="HR.ACR.Authority" bundle="${commonLables}" id="Authority"/>:</b>
				</td>
				<td width="25%" align="left">
				
				<hdiits:select captionid="HR.ACR.Authority" bundle="${commonLables}" name="selectAuthority" id="selectAuthority"  mandatory="true" sort="true" > 
				<option value="-1"><hdiits:caption captionid="HR.ACR.Select" bundle="${commonLables}"/></option>
			<option value="0"><hdiits:caption captionid="HR.ACR.SelfAppraisal" bundle="${commonLables}"/></option>
			<option value="1"><hdiits:caption captionid="HR.ACR.Reporter" bundle="${commonLables}"/></option>
			
			<option value="2"><hdiits:caption captionid="HR.ACR.Reviewer" bundle="${commonLables}"/></option>
			<option value="3"><hdiits:caption captionid="HR.ACR.Approver" bundle="${commonLables}"/></option>
			
			
				
			
				
			</hdiits:select>	
				
				</td>
			</tr>
			
			
				
			</table>
			<br><br><br><br><br>
			
					<table align="center">
				<tr align="center" colspan="2">
							<td>
								<hdiits:button captionid="HR.ACR.Start" bundle="${commonLables}" name="Submit" type="button"  onclick="validation();" />
		
							</td>
							<td>
								<hdiits:button captionid="HR.ACR.Close" bundle="${commonLables}" name="Close" type="button"  onclick="closeButtonHandler();" />
		
							</td>
							
				</tr>
			
			</table>
			
		
		


</hdiits:form>	
	<jsp:include page="../core/PayTabnavigation.jsp" />
	 
	<script type="text/javascript">
		initializetabcontent("maintab")
		</script>
<hdiits:validate locale="${locale}" controlNames="" /></div>
</div>



<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

	