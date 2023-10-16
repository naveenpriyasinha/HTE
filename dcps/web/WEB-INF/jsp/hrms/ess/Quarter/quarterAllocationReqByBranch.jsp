<% 
try
{
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="resources.ess.quarter.QuarterAllocationLables" var="QtrLables" scope="request" />
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/quarter/QuarterAllocReqByBranch.js"/>"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/quarter/QuarterComboBox.js"/>"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="PrevQtrList" value="${resValue.PrevQtrList}"></c:set>
<c:set var="DeptList" value="${resValue.DeptList}"></c:set>
<c:set var="Request" value="${resValue.requestDetails}"></c:set>
<c:set var="EmpDetVO" value="${resValue.EmpDet}" />
<c:set var="RequestId" value="${resValue.requestId}" />
<c:set var="Flag" value="${resValue.StatusFlag}" />
<c:set var="totFilled" value="${resValue.totalFilled}" />
<c:set var="totEmpty" value="${resValue.totalEmpty}" />
<c:set var="filledLst" value="${resValue.QtrLst}" />
<c:set var="vacant" value="${resValue.vacantQtr}" />
<c:set var="QuarterType" value="${resValue.QuarterType}" />
<c:set var="PrevFlag" value="${resValue.PrevFlag}"></c:set>
<c:set var="QtrTxn" value="${resValue.QtrTxn}"></c:set>
<c:set var="parentLocID" value="${resValue.parentLocID}"></c:set>
<c:set var="AllparentLoc" value="${resValue.AllparentLoc}"></c:set>
<c:set var="deptId" value="${resValue.deptId}"></c:set>
<c:set var="AllPoliceST" value="${resValue.AllPoliceST}"></c:set>
<c:set var="PoliceSTId" value="${resValue.PoliceSTId}"></c:set>
<c:set var="AllPoliceLine" value="${resValue.AllPoliceLine}"></c:set>
<c:set var="PoliceLine" value="${resValue.PoliceLine}"></c:set>
<c:set var="ApprovePoliceSt" value="${resValue.ApprovePoliceSt}"></c:set>
<c:set var="ApprovePoliceLine" value="${resValue.ApprovePoliceLine}"></c:set>
<c:set var="ApproveQtrName" value="${resValue.ApproveQtrName}"></c:set>
<c:set var="ApproveStartDT" value="${resValue.ApproveStartDT}"></c:set>
<c:set var="ApproveQtrId" value="${resValue.ApproveQtrId}"></c:set>
<c:set var="lookupRate" value="${resValue.lookupRate}"></c:set>
<c:set var="ApproveRateType" value="${resValue.ApproveRateType}"></c:set>

<c:set var="districtId" value="${resValue.districtId}"></c:set>
<c:set var="distListId" value="${resValue.distListId}"></c:set>
<c:set var="distName" value="${resValue.distName}"></c:set>
<script type="text/javascript">
	var quarterAlertArray = new Array();
	quarterAlertArray[0]='<fmt:message bundle="${QtrLables}" key="HRMS.selectPoliceLine"/>';
	
</script>
<script type="text/javascript" language="JavaScript"><!--


function loadTypeOffice()
{
 
	 
	document.getElementById('TypeOfOff').value=document.getElementById('deptId').value;
    document.getElementById('Jurisdiction').value='${parentLocID}';
    document.getElementById('district2').value='${districtId}';
   
    document.getElementById('Policestation').value='${PoliceSTId}';	
    document.getElementById('PoliceLine').value='${PoliceLine}';	
    document.getElementById('PoliceSTTxt').value='${ApprovePoliceSt}';
    document.getElementById('PoliceLineTxt').value='${ApprovePoliceLine}';
    document.getElementById('QuarterNameTxt').value='${ApproveQtrName}';
    document.getElementById('alloStartDt').value='${ApproveStartDT}';
    document.getElementById('QtrId').value='${ApproveQtrId}';
    if('${ApproveRateType}'!="")
    	document.getElementById('lookupRate').value='${ApproveRateType}';
}

 


--></script>
	
<hdiits:form name="form1" validate="true" action="hrms.htm?actionFlag=forwardForQtrReq" method="post" >

	<TABLE WIDTH="100%" >
	<tr>
		<td>
			<%@ include file="//WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
		</td>
	</tr>
	</table>
	<br>
	<hdiits:fieldGroup titleCaptionId= "HRMS.PreviousAllotedQuarters" bundle="${QtrLables}" id="prevAllotedDtls" collapseOnLoad="true" >
	<TABLE class="tabtable" id=""   border="1" style="border-collapse: collapse;" borderColor="BLACK">
	<TBODY>
	<c:if test="${PrevFlag == 0}">
		<TR>
			<TD class="fieldLabel" align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="HRMS.PoliceStationHQ" bundle="${QtrLables}"/></b></TD>
			<TD class="fieldLabel" align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="HRMS.PoliceLine" bundle="${QtrLables}" /></b></TD>
			<TD class="fieldLabel" align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="HRMS.QtrType" bundle="${QtrLables}" /></b></TD>
			<TD class="fieldLabel" align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="HRMS.QuarterName" bundle="${QtrLables}" /></b></TD>
			<TD class="fieldLabel" align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="HRMS.AllocatnDte" bundle="${QtrLables}" /></b></TD>
			<TD class="fieldLabel" align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="HRMS.TentetiveEndDate" bundle="${QtrLables}" /></b></TD>
		</TR>
		<c:forEach var="preQtrList" items="${PrevQtrList}">
		<TR>
			<TD class="fieldLabel"><c:out value="${preQtrList.hrEssQtrMstQtrId.cmnLocationMstByPoliceStId.locName}"/></TD>
			<TD class="fieldLabel"><c:out value="${preQtrList.hrEssQtrMstQtrId.namePoliceLine}"/></TD>
			<TD class="fieldLabel"><c:out value="${preQtrList.hrQuaterTypeMst.quaType}"/></TD>
			<TD class="fieldLabel"><c:out value="${preQtrList.quarterName}"/></TD>
			<TD class="fieldLabel"><fmt:formatDate value="${preQtrList.allocationStartDate}" pattern="dd-MM-yyyy"/></TD>
			<TD class="fieldLabel"><fmt:formatDate value="${preQtrList.allocationEndDate}" pattern="dd-MM-yyyy"/></TD>
		</TR>
		</c:forEach>
		</c:if>
		<c:if test="${PrevFlag == 1}">
		   <TR>
		   		<TD align="center">
		   			<font color="black"><b><fmt:message key="HRMS.NotFound" bundle="${QtrLables}"/></b></font>
		   		</TD>
		   </TR>
			
		</c:if>
	</TBODY>
</TABLE>
</hdiits:fieldGroup>
<br>
<hdiits:fieldGroup titleCaptionId= "HRMS.AppDetails" bundle="${QtrLables}" id="appDetailsHdr" >
<br>
<TABLE width="100%">
<TBODY>
	<c:forEach var="requestDtls" items="${Request}">
	<TR>
		<TD width="25%"><b><hdiits:caption captionid="HRMS.PoliceStationHQ" bundle="${QtrLables}" /></b></TD>
		<TD width="25%"><c:out value="${requestDtls.hrEssQtrMst.cmnLocationMstByPoliceStId.locName}"/></TD>
		<TD width="25%"><b><hdiits:caption captionid="HRMS.PoliceLine" bundle="${QtrLables}" /></b></TD>
		<TD width="25%"><c:out value="${requestDtls.hrEssQtrMst.namePoliceLine}"/></TD>
	</TR>
	<TR>
		<TD width="25%"><b><hdiits:caption captionid="HRMS.TypeOfHouse" bundle="${QtrLables}" /></b></TD>
		<TD width="25%"><c:out value="${requestDtls.hrQuaterTypeMst.quaType}"/></TD>
		<TD width="25%"><b><hdiits:caption captionid="HRMS.Remarks" bundle="${QtrLables}" /></b></TD>
		<TD width="25%"><c:out value="${requestDtls.remarks}"/></TD>
	</TR>
	</c:forEach>
</TBODY>
</TABLE>


<TABLE width="100%" id="alertUser" >
	<TBODY>
	<TR >
		<TD align="left"><font color="red"><strong>
			<fmt:message key="HRMS.UserCan" bundle="${QtrLables}"/>   <U><c:out value="${QuarterType}"/></U>   <fmt:message key="HRMS.eligibleQtr" bundle="${QtrLables}"/>
		</strong></font></TD>
	</TR>
	</TBODY>
</TABLE>
</hdiits:fieldGroup>
<br>
<hdiits:fieldGroup titleCaptionId= "HRMS.SearchForAccommodation" bundle="${QtrLables}" id="searchForAcc">
<br>
<TABLE width="100%">
<TR>
	<TD width="25%">
		<b><hdiits:caption captionid="HRMS.TypeOfOffice" bundle="${QtrLables}" /></b>
	</TD>
	<TD width="25%">	
		<hdiits:select  captionid="HRMS.TypeOfOffice" bundle="${QtrLables}" id="TypeOfOff" mandatory="true" name="TypeOfOff" size ="1" sort="false"  onchange="getJurisdiction(this,1,2)">
		<hdiits:option value="0" ><fmt:message key="HRMS.SELECT"
				bundle="${QtrLables}" /></hdiits:option>
		<c:forEach var="dept" items="${DeptList}">
			<hdiits:option  value="${dept.departmentId}" ><c:out value="${dept.depName}"/></hdiits:option>
		</c:forEach>
		</hdiits:select> 
		
	</TD>
	<TD width="25%">
		<b><hdiits:caption captionid="HRMS.NameofOffice1" bundle="${QtrLables}" /></b>
	</TD>
	<TD width="25%">
		<hdiits:select  captionid="HRMS.NameofOffice1" bundle="${QtrLables}"  id="Jurisdiction"  mandatory="true" name="Jurisdiction" size ="1" sort="false"  onchange="getPoliceST(this,1,2)">
		<hdiits:option value="0" ><fmt:message key="HRMS.SELECT"
				bundle="${QtrLables}" /></hdiits:option>
		<c:forEach var="ParentLoc" items="${AllparentLoc}">
			<hdiits:option  value="${ParentLoc.departmentId}" ><c:out value="${ParentLoc.depName}"/></hdiits:option>
		</c:forEach>
		</hdiits:select>
	</TD>
</TR>
<TR>
	<td width="25%"><b><hdiits:caption captionid="HRMS.district" bundle="${QtrLables}" /></b></td>
	<td width="25%">
			<hdiits:select caption="district" name="district2" id="district2" captionid="HRMS.district" bundle="${QtrLables}"
				 	mandatory="true" sort="false" size="1" onchange="getPoliceST(this,1,2)">
	  			<hdiits:option value="0"><fmt:message key="HRMS.SELECT" bundle="${QtrLables}" /></hdiits:option>
			 <c:forEach items="${distListId}" var="distListIdVal" varStatus="x">
			<c:set var="distNameVal" value="${distName[x.index]}"></c:set>
				<hdiits:option  value="${distListIdVal}" ><c:out value="${distNameVal}"/></hdiits:option>
			</c:forEach>	
				</hdiits:select>
	</td>
	<TD width="25%">
		<b><hdiits:caption captionid="HRMS.PoliceStationHQ1" bundle="${QtrLables}" /></b>
	</TD>
	<TD width="25%">
		<hdiits:select  captionid="HRMS.PoliceStationHQ1" bundle="${QtrLables}" mandatory="true"   id="Policestation" name="Policestation" size ="1" sort="false" onchange="getPoliceLine(this,1,1,2)">
		<hdiits:option value="0" ><fmt:message key="HRMS.SELECT"
				bundle="${QtrLables}" /></hdiits:option>
		<c:forEach var="Loc" items="${AllPoliceST}">
			<hdiits:option  value="${Loc.locationCode}" ><c:out value="${Loc.locName}"/></hdiits:option>
		</c:forEach>
		</hdiits:select>
	</TD>
</TR>
<tr>
	<TD width="25%">
		<b><hdiits:caption captionid="HRMS.PoliceLine" bundle="${QtrLables}" /></b>
	</TD>
	<TD width="25%">
		<hdiits:select  captionid="HRMS.PoliceLine" bundle="${QtrLables}"  id="PoliceLine" mandatory="true" name="PoliceLine" size ="1" sort="false"  onchange="getQuarterTypes()">
		<hdiits:option value="0" ><fmt:message key="HRMS.SELECT"
				bundle="${QtrLables}" /></hdiits:option>
		<c:forEach var="PoliceLine" items="${AllPoliceLine}">
			<hdiits:option  value="${PoliceLine.quarterId}" ><c:out value="${PoliceLine.namePoliceLine}"/></hdiits:option>
		</c:forEach>
		
		</hdiits:select>
	</TD>

</tr>
</TABLE>	

<TABLE width="100%" align="center" id="AddTbl">
		<TR>
			<TD>
				<hdiits:fmtMessage key="HRMS.addressPoliceLine" bundle="${QtrLables}" var="addressPoliceLine" ></hdiits:fmtMessage>
				<jsp:include page="//WEB-INF/jsp/common/address.jsp">
						<jsp:param name="addrName" value="PoliceLineAddress" />
						<jsp:param name="addressTitle" value="${addressPoliceLine}" />
						<jsp:param name="addrLookupName" value="Permanent Address" />
						<jsp:param name="mandatory" value="Y" />						
				</jsp:include>
			</TD>
		</TR>
	</TABLE>
	
<TABLE width="100%">
	<TR align="RIGHT">
		<TD width="50%" align="right">
			<hdiits:button name="search" type="button" value="Search" captionid="HRMS.Search" bundle="${QtrLables}" onclick="searchQuarter()"/>
		</TD>
		<TD width="50%" align="left" >
			<hdiits:button name="close" type="button" value="Close" captionid="HRMS.Close" bundle="${QtrLables}" onclick="hidetbl()" />
		</TD>
	</TR>
</TABLE>
<TABLE  class="tabtable" id="table123"   style="display:none" >
   <tr>
		<td width="25%" ><hdiits:caption captionid="HRMS.PoliceStationHQ" bundle="${QtrLables}" /></td>
		  <td width="25%" id="txtPoliceHQ"><label></label></td>
		  <td width="25%" ><hdiits:caption captionid="HRMS.PoliceLine" bundle="${QtrLables}" /></td>
		  <td width="25%" id="txtPoliceLine"><label></label></td>
		 
		 </tr>
		 	
  </TABLE>
  <BR>
  <TABLE class="tabtable" id="waittingAppTbl"  border="1" style="border-collapse: collapse;" borderColor="BLACK">
    <tr>
  	 	<td align="center" bgcolor="#C9DFFF" class="fieldLabel" rowspan="2"><hdiits:caption captionid="HRMS.TypeOfHouse" bundle="${QtrLables}" /></td>
  	 	<td align="center" bgcolor="#C9DFFF" class="fieldLabel" rowspan="2"><hdiits:caption captionid="HRMS.TotalWaitting" bundle="${QtrLables}"/></td>
  	 	<td align="center" bgcolor="#C9DFFF" class="fieldLabel" colspan="2"><hdiits:caption captionid="HRMS.TotalQuarters" bundle="${QtrLables}" /></td> 
    </tr>   
 
  	 
  	 <tr>
  	  <td align="center" bgcolor="#C9DFFF" class="fieldLabel"><hdiits:caption captionid="HRMS.FilledHouse" bundle="${QtrLables}"/></td>
  	  <td align="center" bgcolor="#C9DFFF" class="fieldLabel"><hdiits:caption captionid="HRMS.EmptyHouse" bundle="${QtrLables}"/></td>
  	</tr>
   
  <c:forEach var="waitting" items="${resValue.vacantQtr}">
  	<TR>
  		<td align="center"><c:out value="${waitting.qtrType}" /> <fmt:message key="HRMS.TypeofQua" bundle="${QtrLables}" /></td>
  		 <c:if test="${waitting.noOfwaittingApp == 0}">

  		    	<td align="center"><font size="3" color="black"><c:out value="${waitting.noOfwaittingApp}" /></font></td>
  		 </c:if>
  		 
  		 <c:if test="${waitting.noOfwaittingApp > 0}">
  		   	 <TD align="center"><a href=javascript:void('showList') onclick=javascript:showList('${waitting.qtrtypeCode}')><font size="3" color="blue"><c:out value="${waitting.noOfwaittingApp}" /></font></a></TD>
  		 </c:if>
  		
  		<td align="center"><FONT color="black" size="3"><c:out value="${waitting.noOfFilledQtr}"/> </FONT></td>
  	<c:if test="${waitting.noOfVacateQtr == 0}">
  	    
  	     <TD align="center"><font size="3" color="black"><c:out value="${waitting.noOfVacateQtr}" /></font></TD>
  	
  	</c:if>
  	
  	<c:if test="${waitting.noOfVacateQtr > 0}">
  	    
  	     <TD align="center"><a href=javascript:void('showQtrList') onclick=javascript:showQtrList('${waitting.qtrtypeCode}')><font size="3" color="+blue+"><c:out value="${waitting.noOfVacateQtr}" /></font></a></TD>
  	
  	</c:if>
  	
  		
  		
  	</TR>
  </c:forEach>
  </TABLE>
  </hdiits:fieldGroup>
<br>
 <div id="titleTbl" >
<hdiits:fieldGroup titleCaptionId= "HRMS.Allocate" bundle="${QtrLables}" id="titleTblHdr" mandatory="true">
	<TABLE width="100%" align="center" id="detailTbl">
	<TR >
		<TD width="25%"><b><hdiits:caption captionid="HRMS.PoliceStationHQ" bundle="${QtrLables}" /></b></TD>
		<TD width="25%"><hdiits:text name="PoliceSTTxt" disable="true" /></TD>
		<TD width="25%"><b><hdiits:caption captionid="HRMS.PoliceLine" bundle="${QtrLables}" /></b></TD>
		<TD width="25%"><hdiits:text name="PoliceLineTxt" disable="true" /></TD>
	</TR>
	<TR >
		<TD  width="25%"><b><hdiits:caption captionid="HRMS.QuarterName" bundle="${QtrLables}" /></b></TD>
		<TD width="25%">
			<hdiits:text  name="QuarterNameTxt"  caption="QuarterName" captionid="HRMS.QuarterName" bundle="${QtrLables}"  mandatory="true" validation="txt.isrequired" readonly="true"/>
		</TD>
		<TD  width="25%"><b><hdiits:caption captionid="HRMS.AllocatnDte" bundle="${QtrLables}" /></b></TD>
		<TD width="25%">
			<hdiits:dateTime   validation="txt.isrequired" name="alloStartDt"  mandatory="true" caption="AlloStartDt" captionid="HRMS.AllocatnDte" bundle="${QtrLables}"  maxvalue="31/12/9999"></hdiits:dateTime>
		</TD>
	</TR>
	<TR >
		<TD width="25%"><b><hdiits:caption captionid="HRMS.lookupRate" bundle="${QtrLables}" /></b></TD>
		<td width="25%"><hdiits:select  captionid="HRMS.lookupRate" bundle="${QtrLables}" validation="sel.isrequired" id="lookupRate" name="lookupRate" size ="1" sort="false" mandatory="true">
					<hdiits:option value="0" ><fmt:message key="HRMS.SELECT"
							bundle="${QtrLables}" /></hdiits:option>
					<c:forEach var="lookupValues" items="${lookupRate}">
						<hdiits:option  value="${lookupValues.lookupName}" ><c:out value="${lookupValues.lookupDesc}"/></hdiits:option>
					</c:forEach>
					</hdiits:select>
		</td>
		<TD width="25%">&nbsp;</TD>
		<TD width="25%">&nbsp;</TD>
	</TR>
	
</TABLE>
</hdiits:fieldGroup>
<table id="tabNavigationTbl" style="display:none" >
 <tr> <td>
		<jsp:include page="/WEB-INF/jsp/core/tabnavigation.jsp" >
			<jsp:param name="disableReset" value="true"/> 
			<jsp:param name="disableNext" value="true"/>
		</jsp:include>
 </td>
</tr>
</table>
</div>
 	
		<script type="text/javascript">
		    //initializetabcontent("maintab")
	    </script>
		<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
		
 

	
	<script>
		//loadTypeOffice();
		//getQuarterTypes();
	</script>
	

<input name="RequestIds" type="hidden" value="${RequestId}"/>
<input name="QtrId" type="hidden" />
<input name="Flag" type="hidden" value="${Flag}"/>
<input name="h1" type="hidden" />
<input name="h2" type="hidden" />
<input name="h3" type="hidden" />
<input name="deptId" id="deptId" type="hidden" value="${deptId}"/>

<c:forEach var="requestDtls" items="${Request}">
 <input name="policeStId" type="hidden" value="${requestDtls.hrEssQtrMst.cmnLocationMstByPoliceStId.locationCode}" />
 <input name="policeLine123" type="hidden" value="${requestDtls.hrEssQtrMst.quarterId}" />
</c:forEach>

<script>
		loadTypeOffice();
		getQuarterTypes();
		</script>
</hdiits:form>
<%
}
catch(Exception e){
	e.printStackTrace();
}
%>	