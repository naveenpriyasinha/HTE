
<% 
try
{
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<fmt:setBundle basename="resources.ess.quarter.QuarterAllocationLables" var="QtrLables" scope="request" />
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/quarter/quarterAllot.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/eis/commonUtils.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/quarter/QuarterEmpMapping.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/quarter/QuarterComboBox.js"/>"></script>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="DeptList" value="${resValue.DeptList}"></c:set>
<c:set var="EmpList" value="${resValue.EmpList}"></c:set>


<c:set var="allDep" value="${resValue.Department}"></c:set>
<c:set var="allLoc" value="${resValue.Locaton}"></c:set>
<c:set var="allDsgn" value="${resValue.Disgnation}"></c:set>
<c:set var="selectLoc" value="${resValue.selectLoc}"></c:set>
<c:set var="selectDep" value="${resValue.selectDep}"></c:set>
<c:set var="selectDsgn" value="${resValue.selectDsgn}"></c:set>
<c:set var="bIsMultiple" value="${resValue.bIsMultiple}"></c:set>
<c:set var="empName" value="${resValue.empName}"></c:set>
<c:set var="depCode" value="${resValue.depCode}"></c:set>
<c:set var="dsgnCode" value="${resValue.dsgnCode}"></c:set>
<c:set var="locCode" value="${resValue.locCode}"></c:set>
<c:set var="SearchFieldName" value="${resValue.strSearchFieldName}"></c:set>
<c:set var="typeList" value="${resValue.typelist}"></c:set>

<c:set var="lookupRate" value="${resValue.lookupRate}"></c:set>
<fmt:setBundle basename="resources.ess.utils.search.search" var="comLable" scope="request" />


<script type="text/javascript" language="JavaScript">
	var selectDsgn = '${dsgnCode}';
	var selectLoc = '${locCode}';
	var selectDep = '${depCode}';
	var updateRowFlag = true;
	var alertArrayMsg = new Array();
	alertArrayMsg[0]= '<fmt:message bundle="${QtrLables}" key="HRMS.selQtrAlert"/>' ; 
	alertArrayMsg[1]= '<fmt:message bundle="${QtrLables}" key="HRMS.selUserAlert"/>';
	alertArrayMsg[2]= '<fmt:message bundle="${QtrLables}" key="HRMS.updateRecord"/>';
	var imgQtrExpandId="QuarterExpandAgent";
	var imgUserExpandId="UserExpandAgent";
	var counter=1;
	var frmReload=true;
	var glblRowID;
	var arrayQuarterId = new Array();
	var globalCnt =0;
	var UserId;
	var userName;
	
</script>

<body style="overflow-x:hidden;">
<hdiits:form name="form1" validate="true" method="post" onload="demo()" action="" encType="multipart/form-data">
<hdiits:fieldGroup titleCaptionId="HRMS.qtrEmpMapping" bundle="${QtrLables}"   mandatory="true" id ="qtrEmpMapping">
<table width="100%"  id="expndClpsTblId">
<tr align="left">
	<td  class="fieldLabel" align="left" width="50%">
		<a href="javascript:void(0);" onclick="expandCollapse('Quarter',imgQtrExpandId);"><img id="QuarterExpandAgent" src="images/collapse.gif" /></a>
	</td>
	<td  class="fieldLabel" align="left" width="50%">
		<a href="javascript:void(0);" onclick="expandCollapse('User',imgUserExpandId);"><img id="UserExpandAgent" src="images/collapse.gif" /></a>
	</td>
</tr>
</table>
<table width="100%">
	<tr>
		<td width="50%">
			<TABLE width="100%" id="QuarterTableId" style="display: ">
				<TR>
					<TD width="50%">
						<b><hdiits:caption captionid="HRMS.TypeOfOffice" bundle="${QtrLables}" /></b>
					</TD>
					<TD width="50%">
						<b><hdiits:caption captionid="HRMS.NameofOffice1" bundle="${QtrLables}" /></b>
					</TD>
				</TR>
				<TR>
					<TD width="50%">	
						<hdiits:select  captionid="HRMS.TypeOfOffice" bundle="${QtrLables}" name="TypeOfOff" id="TypeOfOff" size ="1" sort="false" onchange="getJurisdiction(this,1,3)">
						<hdiits:option value="0" ><fmt:message key="HRMS.SELECT"
								bundle="${QtrLables}" /></hdiits:option>
						<c:forEach var="dept" items="${DeptList}">
							
							<hdiits:option value="${dept.departmentId}"><c:out value="${dept.depName}"/></hdiits:option>
						</c:forEach>
						</hdiits:select>
					</TD>
					<TD width="50%">	
						<hdiits:select  captionid="HRMS.NameofOffice1" id="Jurisdiction"  bundle="${QtrLables}" name="Jurisdiction" size ="1" sort="false" onchange="getPoliceST(this,1,3)">
						<hdiits:option value="0" ><fmt:message key="HRMS.SELECT"
								bundle="${QtrLables}" /></hdiits:option>
						</hdiits:select>
					</TD>
				</TR>
				<TR>
					<td width="50%"><b><hdiits:caption captionid="HRMS.district" bundle="${QtrLables}" /></b></td>
	
					<TD width="50%">
						<b><hdiits:caption captionid="HRMS.PoliceStationHQ1" bundle="${QtrLables}" /></b>
					</TD>
					
				</TR>
				<TR>
					<td width="50%">
						<hdiits:select caption="district" name="district2" id="district2" captionid="HRMS.district" bundle="${QtrLables}"
				 				 validation="sel.isrequired" sort="false" size="1" onchange="getPoliceST(this,1,3)">
								<hdiits:option value="0"><fmt:message key="HRMS.SELECT" bundle="${QtrLables}" /></hdiits:option>
						</hdiits:select>
					</td>
					<TD width="50%">
						<hdiits:select  captionid="HRMS.PoliceStationHQ1" bundle="${QtrLables}" id="Policestation" name="Policestation" size ="1" sort="false"  onchange="getPoliceLine(this,1,1,2)">
						<hdiits:option value="0" ><fmt:message key="HRMS.SELECT"
								bundle="${QtrLables}" /></hdiits:option>
						</hdiits:select>
					</TD>
					
				</TR>
				<tr>
					<TD width="50%">
						<b><hdiits:caption captionid="HRMS.PoliceLine" bundle="${QtrLables}" /></b>
					</TD>
				 	<td width="50%"><hdiits:caption captionid="HRMS.TypeofQua" bundle="${QtrLables}" /></td>
				</tr>
				<tr>
					<TD width="50%">
						<hdiits:select  captionid="HRMS.PoliceLine" bundle="${QtrLables}" id="PoliceLine" name="PoliceLine" size ="1" sort="false">
						<hdiits:option value="0" ><fmt:message key="HRMS.SELECT"
								bundle="${QtrLables}" /></hdiits:option>
						</hdiits:select>
					</TD>
				 	<td width="50%"><hdiits:select  captionid="HRMS.TypeofQua" bundle="${QtrLables}" validation="sel.isrequired" id="TypeOfQuater" name="TypeOfQuater" size ="1" sort="false">
						<hdiits:option value="0" ><fmt:message key="HRMS.SELECT" bundle="${QtrLables}" /></hdiits:option>
						<c:forEach var="TypesList1" items="${typeList}"> 
								<hdiits:option value="${TypesList1.qtrCode}">${TypesList1.quaType} </hdiits:option>
						</c:forEach>
						</hdiits:select> 
					</td>
					
				</tr>
				<tr>
					<td width="50%"><hdiits:caption captionid="HRMS.QtrName" bundle="${QtrLables}" /></td>
					<td width="50%">&nbsp;</td>
				</tr>
				<tr>
					<td width="50%"><hdiits:text  name="txtQuarterName" id="txtQuarterName" size="20" maxlength="30"/></td>
					<td width="50%">&nbsp;</td>
				</tr>
				
			</TABLE>
			<TABLE width="100%" id="QtrSrchBtnId" style="display: ">
				<TR>
					<td width="50%" align="center">
						<hdiits:button name="DisplayQuaters"  type="button" captionid="HRMS.SrchQtr" bundle="${QtrLables}" onclick="getQtrList();"/>
					</td>
				</tr>
			</TABLE>
			<TABLE width="100%" height="100%">
			<TR>
			 	<TD width="50%">
					<iframe  scrolling="auto" name="QtrList" id="QtrList"  style="allowtransparency:true" src="hrms.htm?actionFlag=getQuarters&PoliceLineId=0&quarterTypeCode=0"  frameborder="0" width="100%" height="150px" align="right"></iframe>
				</td>
			</TR>
			</TABLE>
		</td>
		
		<td width="50%">
			<TABLE width="100%"  id="UserTableId" style="display: ">
				<tr>
					<td width="50%">
						<hdiits:caption captionid="department" bundle="${comLable}" />
					</td>
					<td width="50%">
						<hdiits:caption captionid="location" bundle="${comLable}" />
					</td>
				</tr>
				<tr>
					<td width="50%">
						<hdiits:select name="depId" id="depId" size="1" caption="drop_down"
						onchange="showOfficeName('location','depId','locId');" tabindex="2" sort="false">
						<hdiits:option value="0"><fmt:message key="select" bundle="${comLable}" /></hdiits:option>
						 
						<c:forEach var="DepObj" items="${allDep}">
								<c:if test="${DepObj.departmentId == selectDep}">
								<hdiits:option value="${DepObj.depCode}" selected="true">${DepObj.depName}  </hdiits:option>
							</c:if>
							<c:if test="${DepObj.departmentId != selectDep}">
								<hdiits:option value="${DepObj.depCode}">${DepObj.depName}  </hdiits:option>
							</c:if>	
						</c:forEach>
			
						</hdiits:select>
					</td>
					<td width="50%">
						<hdiits:select name="locId" id="locId" size="1" tabindex="3" sort="false">
							<hdiits:option value="0"><fmt:message key="select" bundle="${comLable}" /></hdiits:option>
						</hdiits:select>
					</td>
				</tr>
				<tr>
					<td width="50%">
						<hdiits:caption captionid="designation" bundle="${comLable}" />
					</td>
					<td width="50%">
						<hdiits:caption captionid="name" bundle="${comLable}" />
					</td>
				</tr>
				<tr>
					<td width="50%">
						<hdiits:select name="dsgnId" size="1" caption="dsgnId" mandatory="false" tabindex="4" sort="false">
							<hdiits:option value="0"><fmt:message key="select" bundle="${comLable}" /></hdiits:option>
							<c:set var="selected" value="" />
							<c:forEach var="DsgnObj" items="${allDsgn}">
								<c:if test="${DsgnObj.dsgnId == selectDsgn}">
									<hdiits:option value="${DsgnObj.dsgnCode}" selected="true"> ${DsgnObj.dsgnName} </hdiits:option>
								</c:if>
								<c:if test="${DsgnObj.dsgnId != selectDsgn}">
									<hdiits:option value="${DsgnObj.dsgnCode}"> ${DsgnObj.dsgnName} </hdiits:option>
								</c:if>	
							</c:forEach>
						</hdiits:select>
					</td>
					<td width="50%">
						<hdiits:text name="empName" size="20" tabindex="6" default="${empName}"/>
					</td>
				</tr>
				<tr>
				 	<td width="50%">&nbsp;&nbsp;</td>
					<td width="50%">&nbsp;&nbsp;</td>
				</tr>
				<tr>
				 	<td width="50%">&nbsp;&nbsp;</td>
					<td width="50%">&nbsp;&nbsp;</td>
				</tr>
				<tr>
				 	<td width="50%">&nbsp;&nbsp;</td>
					<td width="50%">&nbsp;&nbsp;</td>
				</tr>
				<tr>
				 	<td width="50%">&nbsp;&nbsp;</td>
					<td width="50%">&nbsp;&nbsp;</td>
				</tr>
				<tr>
				 	<td width="50%">&nbsp;&nbsp;</td>
					<td width="50%">&nbsp;&nbsp;</td>
				</tr>
			</TABLE>
			
			<TABLE width="100%"  id="UserSrchBtnId" style="display: " >
				<TR>
					<td width="50%" align="center">
						<br>&nbsp;&nbsp;<hdiits:button name="go" captionid="HRMS.SrchEmp" bundle="${QtrLables}" type="button" tabindex="6" onclick="getUserList()" />
					</td>
				</tr>
			</TABLE>
			<TABLE width="100%" height="100%">
			<TR>
			 	<TD width="50%">
					<iframe  scrolling="auto" name="UserList" style="allowtransparency:true" id="UserList" onload="onLoadiFrame()" src="" frameborder="0"  width="100%" height="150px" align="right"  marginheight="0" marginwidth="0"></iframe>
				</td>
			</TR>
			</TABLE>
		</td>
		
	</tr>
</table>
<br>
<table width="100%">
	<tr>	
		
		<TD width="25%"><b><hdiits:caption captionid="HRMS.AllocatnDte" bundle="${QtrLables}" /></b></TD>
		<TD width="25%">
			<hdiits:dateTime   validation="txt.isdt,txt.isrequired" name="alloStartDt"   mandatory="true"  captionid="HRMS.AllocatnDte" bundle="${QtrLables}"  maxvalue="31/12/9999"></hdiits:dateTime>
		</TD>
		<td width="25%"><hdiits:caption captionid="HRMS.lookupRate" bundle="${QtrLables}" /></td>
		<td width="25%"><hdiits:select  captionid="HRMS.lookupRate" bundle="${QtrLables}" validation="sel.isrequired" id="lookupRate" name="lookupRate" size ="1" sort="false" mandatory="true">
					<hdiits:option value="0" ><fmt:message key="HRMS.SELECT"
							bundle="${QtrLables}" /></hdiits:option>
					<c:forEach var="lookupValues" items="${lookupRate}">
						<hdiits:option  value="${lookupValues.lookupName}" ><c:out value="${lookupValues.lookupDesc}"/></hdiits:option>
					</c:forEach>
					</hdiits:select>
		</td>
	</tr>
	
</table>
<table width="100%">
<tr>
		
		<td align="center">
		<hdiits:button name="allocateBtn" id="allocateBtn" type="button" captionid="HRMS.Allocate" bundle="${QtrLables}" onclick="addUserQuarterDtls()"></hdiits:button> 
		<hdiits:button name="upDateBtn" id="upDateBtn" type="button" style="display:none"  captionid="HRMS.update" bundle="${QtrLables}" onclick="updateQuarterDtls()"></hdiits:button></td>
		
	</tr>
</table>
</hdiits:fieldGroup>
<hdiits:fieldGroup titleCaptionId="HRMS.lstEmpAllocatedQtr" bundle="${QtrLables}"   mandatory="true" id="lstEmpAllocatedQtr">		
<table class="tabtable" id="AllocTbl"  border="1"   style="border-collapse: collapse;" borderColor="black" >
 	
 	<tr>
	<br>
	  <td align="center"  class="datatableheader" ><hdiits:caption captionid="HRMS.EmpName" bundle="${QtrLables}" /></td>
	  <td align="center"  class="datatableheader"><hdiits:caption captionid="HRMS.Designation" bundle="${QtrLables}" /></td>
	  <td align="center"  class="datatableheader"><hdiits:caption captionid="HRMS.TypeofQua" bundle="${QtrLables}" /></td>
	  <td align="center"  class="datatableheader"><hdiits:caption captionid="HRMS.QtrName" bundle="${QtrLables}" /></td>
	  <td align="center"  class="datatableheader"><hdiits:caption captionid="HRMS.AllocatnDte" bundle="${QtrLables}" /></td>
	  <td align="center"  class="datatableheader"><hdiits:caption captionid="HRMS.lookupRate" bundle="${QtrLables}" /></td>
	  <td align="center"  class="datatableheader"><hdiits:caption captionid="HRMS.Action" bundle="${QtrLables}" /></td>
	  
	</tr>
</table>
</hdiits:fieldGroup>
<table width="100%">
<tr>
<br>
<td align="center">
<hdiits:button name="submitBt" type="button" captionid="HRMS.Submit" bundle="${QtrLables}" onclick="submitDtls()"/>
	
</td>
</tr>
	
</table>

<hdiits:hidden name="multiple" id="multiple" default="${bIsMultiple}"></hdiits:hidden>
<hdiits:hidden name="SearchFieldName" id="SearchFieldName" default="${SearchFieldName}"></hdiits:hidden>
<hdiits:hidden name="totalCounter" id="totalCounter" default="0"/> 
<hdiits:hidden name="h1" id="h1" />
<hdiits:hidden name="h2" id="h2"/>
<hdiits:hidden name="h3" id="h3"/>
	
<script type="text/javascript">
	setWindowName(window, document.form1);
	fromLocationCombo = "${selectLoc}";
	showOfficeName('location','depId','locId');
	
</script>	
<script type="text/javascript">
		    //initializetabcontent("maintab")
	  </script>
		<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>

</hdiits:form>
</body>
<%
}
catch(Exception e){
	e.printStackTrace();
}
%>	