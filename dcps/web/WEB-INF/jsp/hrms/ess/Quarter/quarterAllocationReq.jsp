<% 
try
{
%>

<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/quarter/QuarterAllocReq.js"/>"></script>
<fmt:setBundle basename="resources.ess.quarter.QuarterAllocationLables" var="QtrLables" scope="request"/>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/quarter/QuarterComboBox.js"/>"></script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="PrevQtrList" value="${resValue.PrevQtrList}"></c:set>
<c:set var="DeptList" value="${resValue.DeptList}"></c:set>
<c:set var="QtrTypesList" value="${resValue.QuarterTypes}"></c:set>
<c:set var="QuarterTypeIds" value="${resValue.QuarterTypeIds}"></c:set>
<c:set var="TypeQrtrs" value="${resValue.TypeQrtrs}"></c:set>
<c:set var="PrevFlag" value="${resValue.PrevFlag}"></c:set>

<script type="text/javascript" language="JavaScript"><!-- 
 --></script>
<hdiits:form name="quartrAlloc" validate="true" action="" method="">

<%@ include file="//WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
	
<hdiits:fieldGroup titleCaptionId= "HRMS.SearchForAccommodation" bundle="${QtrLables}" id="srchAccm">
	<br>
	
	<table width="100%">
		<tr>
			<td width="25%">
				<hdiits:caption captionid="HRMS.TypeofOffice" bundle="${QtrLables}" />
			</td>
			<td width="25%">	
				<hdiits:select  captionid="HRMS.TypeofOffice" bundle="${QtrLables}" validation="sel.isrequired" name="TypeOfOff" id="TypeOfOff" size ="1" sort="false" mandatory="true"   onchange="getJurisdiction(this,1,1)" >
				<hdiits:option value="0" ><fmt:message key="HRMS.SELECT" 	bundle="${QtrLables}" /></hdiits:option>
				<c:forEach var="dept" items="${DeptList}">
					<hdiits:option  value="${dept.departmentId}" ><c:out value="${dept.depName}"/></hdiits:option>
				</c:forEach>
		 		</hdiits:select> 
			</td>
			
			<td width="25%">
				<hdiits:caption captionid="HRMS.NameofOffice1" bundle="${QtrLables}" />
			</td>
			
			<td width="25%">
				<hdiits:select  captionid="HRMS.NameofOffice1" bundle="${QtrLables}" validation="sel.isrequired" mandatory="true" id="Jurisdiction" name="Jurisdiction" size ="1"   sort="false"  onchange="getPoliceST(this,1,1)">
					<hdiits:option value="0" ><fmt:message key="HRMS.SELECT"
								bundle="${QtrLables}" /></hdiits:option>
				</hdiits:select> 
			</td>
		</tr>
		<tr>
			<td width="25%"><b><hdiits:caption captionid="HRMS.district" bundle="${QtrLables}" /></b></td>
			<td width="25%"><hdiits:select caption="district" name="district2" id="district2" captionid="HRMS.district" bundle="${QtrLables}"
				 	mandatory="true" validation="sel.isrequired" sort="false" size="1" onchange="getPoliceST(this,1,1)">
				<hdiits:option value="0"><fmt:message key="HRMS.SELECT" bundle="${QtrLables}" /></hdiits:option>
				</hdiits:select>
			</td>
			<td width="25%">
					<b><hdiits:caption captionid="HRMS.PoliceStationHQ1" bundle="${QtrLables}" /></b>
			</td>
			<td width="25%">
				<hdiits:select  captionid="HRMS.PoliceStationHQ1" bundle="${QtrLables}" mandatory="true" validation="sel.isrequired" name="Policestation" id="Policestation" size ="1"   sort="false" onchange="getPoliceLine(this,1,1,2)">
					<hdiits:option value="0" ><fmt:message key="HRMS.SELECT"
					bundle="${QtrLables}" /></hdiits:option>
				</hdiits:select>
			</td>
		</tr>
		<tr>
			<td width="25%">	
			<b><hdiits:caption captionid="HRMS.PoliceLine" bundle="${QtrLables}" /></b>
		</td>
	
		<td width="25%">
			<hdiits:select  captionid="HRMS.PoliceLine" bundle="${QtrLables}" mandatory="true"    validation="sel.isrequired" name="PoliceLine" id="PoliceLine"  size ="1"   sort="false"   >
				<hdiits:option value="0" ><fmt:message key="HRMS.SELECT"
				bundle="${QtrLables}" /></hdiits:option>
			</hdiits:select>
		</td>
			
		</tr>
	</table>	

	<table width="100%">
		<tr align="right">
			<td width="50%" align="right">
				<hdiits:button name="search" type="button" value="Search" captionid="HRMS.Search" bundle="${QtrLables}"  onclick="searchQuarter()"/>
			</td>
			<td width="50%" align="left">
				<hdiits:button name="close" type="button" value="Close" captionid="HRMS.Close" bundle="${QtrLables}" onclick="hidetbl()" style="display:none"/>
			</td>
		</tr>
	</table>
	
	<table  class="tabtable" id="table123"  style="display:none"  align="center" width="100%">
 	 
		  <tr>
		  <td width="25%" ><hdiits:caption captionid="HRMS.PoliceStationHQ" bundle="${QtrLables}" /></td>
		  <td width="25%" id="txtPoliceHQ"><label></label></td>
		  <td width="25%" ><hdiits:caption captionid="HRMS.PoliceLine" bundle="${QtrLables}" /></td>
		  <td width="25%" id="txtPoliceLine"><label></label></td>
		 
		 </tr>
  	</table>
  <br>
  <table class="tabtable" id="waittingAppTbl"  border="1" style="display:none" style="border-collapse: collapse;" borderColor="black" >
  <tr>
  	 	<td align="center" bgcolor="#C9DFFF" class="fieldLabel" rowspan="2"><hdiits:caption captionid="HRMS.TypeOfHouse" bundle="${QtrLables}" /></td>
  	 	<td align="center" bgcolor="#C9DFFF" class="fieldLabel" rowspan="2"><hdiits:caption captionid="HRMS.TotalWaitting" bundle="${QtrLables}"/></td>
  	 	<td align="center" bgcolor="#C9DFFF" class="fieldLabel" colspan="2"><hdiits:caption captionid="HRMS.TotalQuarters" bundle="${QtrLables}" /></td> 
    </tr>   
  	<tr>
  	  <td align="center" bgcolor="#C9DFFF" class="fieldLabel"><hdiits:caption captionid="HRMS.FilledHouse" bundle="${QtrLables}"/></td>
  	  <td align="center" bgcolor="#C9DFFF" class="fieldLabel"><hdiits:caption captionid="HRMS.EmptyHouse" bundle="${QtrLables}"/></td>
  	</tr>
  		
  </table>
  </hdiits:fieldGroup>
   <br>
 <hdiits:fieldGroup titleCaptionId= "HRMS.PreviousAllotedQuarters" bundle="${QtrLables}" id="prevAllotedQtr" collapseOnLoad="true">
	<table class="tabtable" id="table123" border="1" style="border-collapse: collapse;" borderColor="BLACK" >
	
	<tbody>
	<c:if test="${PrevFlag == 0}">
		<tr>
			<td class="fieldLabel" align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="HRMS.PoliceStationHQ" bundle="${QtrLables}"/></b></td>
			<td class="fieldLabel" align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="HRMS.PoliceLine" bundle="${QtrLables}" /></b></td>
			<td class="fieldLabel" align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="HRMS.QtrType" bundle="${QtrLables}" /></b></td>
			<td class="fieldLabel" align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="HRMS.NameofQuarter" bundle="${QtrLables}" /></b></td>
			<td class="fieldLabel" align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="HRMS.AllocatnDte" bundle="${QtrLables}" /></b></td>
			<td class="fieldLabel" align="center" bgcolor="#C9DFFF"><b><hdiits:caption captionid="HRMS.TentetiveEndDate" bundle="${QtrLables}" /></b></td>
		</tr>
		
		<c:forEach var="preQtrList" items="${PrevQtrList}">
		<tr>
			<td class="fieldLabel"><c:out value="${preQtrList.hrEssQtrMstQtrId.cmnLocationMstByPoliceStId.locName}"/></td>
			<td class="fieldLabel"><c:out value="${preQtrList.hrEssQtrMstQtrId.namePoliceLine}"/></td>
			<td class="fieldLabel"><c:out value="${preQtrList.hrQuaterTypeMst.quaType}"/></td>
			<td class="fieldLabel"><c:out value="${preQtrList.quarterName}"/></td>
			<td class="fieldLabel"><fmt:formatDate value="${preQtrList.allocationStartDate}" pattern="dd-MM-yyyy"/></td>
			<td class="fieldLabel"><fmt:formatDate value="${preQtrList.allocationEndDate}" pattern="dd-MM-yyyy"/></td>
		</tr>
		</c:forEach>
		</c:if>
		
		<c:if test="${PrevFlag == 1}">
		   <tr>
		   		<td align="center">
		   		 			<font color="black"><b><fmt:message key="HRMS.NotFound" bundle="${QtrLables}"/></b></font>
		   		</td>
		   </tr>
		</c:if>
	</tbody>
	</table>
	</hdiits:fieldGroup>
	<br>
<hdiits:fieldGroup titleCaptionId= "HRMS.AppDetails" bundle="${QtrLables}" id="AppDetails"  mandatory="true">
	<table width="100%">
	<tr>
	<td width="25%">
		<b><hdiits:caption captionid="HRMS.TypeofOffice" bundle="${QtrLables}" /></b>
	</td>
	<td width="25%">	
		<hdiits:select  captionid="HRMS.TypeofOffice" bundle="${QtrLables}" validation="sel.isrequired" name="TypeOfOff1" id="TypeOfOff1" size ="1"   sort="false" onchange="getJurisdiction(this,2,1)" mandatory="true">
		<hdiits:option value="0" ><fmt:message key="HRMS.SELECT"
				bundle="${QtrLables}" /></hdiits:option>
		<c:forEach var="dept" items="${DeptList}">
			<hdiits:option value="${dept.departmentId}"><c:out value="${dept.depName}"/></hdiits:option>
		</c:forEach>
		</hdiits:select>
	</td>
	<td width="25%">
		<b><hdiits:caption captionid="HRMS.NameofOffice1" bundle="${QtrLables}" /></b>
	</td>
	<td width="25%">	
		<hdiits:select  captionid="HRMS.NameofOffice1" bundle="${QtrLables}" validation="sel.isrequired" name="Jurisdiction1" id="Jurisdiction1" size ="1"   sort="false" onchange="getPoliceST(this,2,1)" mandatory="true" >
		<hdiits:option value="0" ><fmt:message key="HRMS.SELECT"
				bundle="${QtrLables}" /></hdiits:option>
		</hdiits:select>
	</td>
</tr>
	<tr>
			<td width="25%"><b><hdiits:caption captionid="HRMS.district" bundle="${QtrLables}" /></b></td>
			<td width="25%"><hdiits:select caption="district" name="district" id="district" captionid="HRMS.district" bundle="${QtrLables}"
				 	mandatory="true" validation="sel.isrequired" sort="false" size="1" onchange="getPoliceST(this,2,1)">
				<hdiits:option value="0"><fmt:message key="HRMS.SELECT" bundle="${QtrLables}" /></hdiits:option>
				</hdiits:select>
			</td>
			<td width="25%">
				<b><hdiits:caption captionid="HRMS.PoliceStationHQ1" bundle="${QtrLables}" /></b>
			</td>
			<td width="25%">
				<hdiits:select  captionid="HRMS.PoliceStationHQ1" bundle="${QtrLables}" validation="sel.isrequired" name="Policestation1" id="Policestation1" size ="1"   sort="false"  onchange="getPoliceLine(this,2,1,2)" mandatory="true">
				<hdiits:option value="0" ><fmt:message key="HRMS.SELECT"
						bundle="${QtrLables}" /></hdiits:option>
				</hdiits:select>
			</td>
	</tr>
	<tr>
		<td width="25%">
		<b><hdiits:caption captionid="HRMS.PoliceLine" bundle="${QtrLables}" /></b>
		</td>
		<td width="25%">
			<hdiits:select  captionid="HRMS.PoliceLine" bundle="${QtrLables}" validation="sel.isrequired" name="PoliceLine1" id="PoliceLine1" size ="1" sort="false"   onchange="getQuarterTypes()" mandatory="true">
			<hdiits:option  value="0" ><fmt:message key="HRMS.SELECT"
				bundle="${QtrLables}" /></hdiits:option>
		</hdiits:select>
		</td>
	</tr>
	</table>

	<table id="PoliceLineAdr" width="100%" style="display:none">
		<tr>
		<td width="2%">&nbsp;</td>
			<td width="96%">
				<hdiits:fmtMessage key="HRMS.addressPoliceLine" bundle="${QtrLables}" var="addressPoliceLine" ></hdiits:fmtMessage>
				<jsp:include page="//WEB-INF/jsp/common/address.jsp">
						<jsp:param name="addrName" value="PoliceLineAddress" />
						<jsp:param name="addressTitle" value="${addressPoliceLine}" />
						<jsp:param name="addrLookupName" value="Permanent Address" />
						<jsp:param name="mandatory" value="Y" />						
				</jsp:include>
			</td>
		<td width="2%">&nbsp;</td>
		
		</tr>
	</table>


<table width="100%" >
	<tr>
	<td  width="25%">
		<b><hdiits:caption captionid="HRMS.TypeOfHouse" bundle="${QtrLables}" /></b>
	</td>
	<td width="25%" >
		<hdiits:select  captionid="HRMS.TypeOfHouse" bundle="${QtrLables}" validation="sel.isrequired" name="TypeOfHouse" id="TypeOfHouse" size ="1"   sort="false" onchange="CheckQuarter()" mandatory="true">
		<hdiits:option value="0" ><fmt:message key="HRMS.SELECT"
				bundle="${QtrLables}" /></hdiits:option>
		</hdiits:select>
	</td>
	<td width="25%" >
		<b><hdiits:caption captionid="HRMS.Remarks" bundle="${QtrLables}" /></b>
	</td>
	<td width="25%" >
		<hdiits:textarea mandatory="true" rows="5"
				cols="28" name="Remarks" tabindex="29" id="Remarks"
				validation="txt.isrequired" captionid="HRMS.Remarks" 
				bundle="${QtrLables}" maxlength="199" />
		<input name="h1" type="hidden" />
		<input name="h2" type="hidden"/>
		<input name="h3" type="hidden"/>
		<input name="policeStId" type="hidden"  />
		<input name="policeLine123" type="hidden" />
	</td>
	</tr>
	
	<tr>
		<TABLE width="100%" id="alertUser" >
			<TBODY>
			<TR>
				<TD align="left"><font color="red"><strong>
					<fmt:message key="HRMS.UserShd" bundle="${QtrLables}"/> <U><c:out value="${TypeQrtrs}"/></U>   <fmt:message key="HRMS.typeQtr" bundle="${QtrLables}"/>
					</strong></font></TD>
			</TR>
			</TBODY>
		</TABLE>
	</TR>
	<TR>

  		<TABLE  id="agreement1" style="display:none" align="center" width="100%">
  				<TR><TD>
				<jsp:include page="/WEB-INF/jsp/hrms/common/declaration.jsp" />
				<!--   <hdiits:checkbox name="agreement" value="0" onclick="showSubmit()"/> <b><hdiits:caption captionid="HRMS.Agreement" bundle="${QtrLables}"  /></b> -->
				</TD>
			</TR>
		</TABLE>
	</TR>

</TABLE>
</hdiits:fieldGroup>
<br>
<center>
<hdiits:button id="Submit1" name="Submit1" type="button" value="Submit" captionid="HRMS.Submit" bundle="${QtrLables}"  style="display:none" onclick="validateData()"/>
<hdiits:button name="Close" type="button" value="Close"  captionid="HRMS.Close" bundle="${QtrLables}" onclick="ClosePage(document.quartrAlloc);"/>
</center>

<script type="text/javascript">
		    //initializetabcontent("maintab")
	  </script>
		<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>

</hdiits:form>	


<%
}
catch(Exception e){
	e.printStackTrace();
}
%>	