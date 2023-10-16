<%
try {
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/quarter/QuarterAllocationMaster.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/ess/quarter/QuarterComboBox.js"/>"></script>

<fmt:setBundle basename="resources.ess.quarter.QuarterAllocationLables" var="QtrLables" scope="request"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="typeList" value="${resValue.typelist}"></c:set>
<c:set var="jurisList" value="${resValue.jurislist }"></c:set>
<c:set var="distlist" value="${resValue.distlist}"></c:set>
<c:set var="OfficeTypeList" value="${resValue.TypeList}"></c:set>
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

<script type="text/javascript">
	var QtrMstAlertMsgArr=new Array();
	QtrMstAlertMsgArr[0]='<fmt:message bundle="${QtrLables}" key="HRMS.AddressAlert"/>';
	QtrMstAlertMsgArr[1]='<fmt:message bundle="${QtrLables}" key="HRMS.ExistingpoliceLine"/>';
	QtrMstAlertMsgArr[2]='<fmt:message bundle="${QtrLables}" key="HRMS.genQtrValidation"/>';

	var getValue;
	var pager = new Pager('displaydtlsQuarters', 10);
	var noOfPage=0;
</script>


<hdiits:form name="frmAdd" validate="false" method="POST">

	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1" bgColor="#386CB7"><b><fmt:message key="HRMS.SecondScrn"
			bundle="${QtrLables}" /></b></a></li>
		<li class="selected"><a href="#" rel="tcontent2"
			bgColor="#386CB7" onclick="showSecond(this);"><b><fmt:message
			key="HRMS.MstScrn" bundle="${QtrLables}" /></b></a></li>
	</ul>
	</div>
	<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0"><br>
	<table width="100%" align="center">
		<tr>
			<td width="25%"><b><hdiits:caption captionid="HRMS.TypeofOffice1"
				bundle="${QtrLables}" /></b></td>
			<td width="25%"><hdiits:select caption="Type of Office1"
				name="TypeOfOff1" id="TypeOfOff1" captionid="HRMS.TypeofOffice1" bundle="${QtrLables}"
				onchange="getJurisdiction(this,2,0)" mandatory="true" validation="sel.isrequired" sort="false" size="1">
				<hdiits:option value="0"><fmt:message key="HRMS.SELECT" bundle="${QtrLables}" /></hdiits:option>
				<c:forEach var="Office_type" items="${OfficeTypeList}">
					<hdiits:option value="${Office_type.departmentId}">	${Office_type.depName}</hdiits:option>
				</c:forEach>
			</hdiits:select></td>
			<td width="25%"><b><hdiits:caption captionid="HRMS.NameofOffice1"
				bundle="${QtrLables}" /></b></td>
			<td width="25%"><hdiits:select caption="Name of Office1"
				name="Jurisdiction1" id="Jurisdiction1" onchange="getPoliceST(this,2,0)"
				 sort="false" mandatory="true"
				validation="sel.isrequired" size="1" captionid="HRMS.NameofOffice1" bundle="${QtrLables}">
				<hdiits:option value="0"><fmt:message key="HRMS.SELECT"
				bundle="${QtrLables}" /></hdiits:option>
			</hdiits:select></td>
		</tr>
		<tr>
			<td width="25%"><b><hdiits:caption captionid="HRMS.district"
				bundle="${QtrLables}" /></b></td>
			<td width="25%"><hdiits:select caption="district"
				name="district" id="district" captionid="HRMS.district" bundle="${QtrLables}"
				 mandatory="true" validation="sel.isrequired" sort="false" size="1" onchange="getPoliceST(this,2,0)">
				<hdiits:option value="0"><fmt:message key="HRMS.SELECT" bundle="${QtrLables}" /></hdiits:option>
			</hdiits:select></td>
		
			<td width="25%"><b><hdiits:caption captionid="HRMS.PoliceStationHQ1"
				bundle="${QtrLables}" /></b></td>
			<td width="25%"><hdiits:select caption="Police Station1"
				name="Policestation1" id="PoliceStation1" sort="false"
				mandatory="true" validation="sel.isrequired" size="1"
				onchange="getPoliceLine(this,2,2,0)"  captionid="HRMS.PoliceStationHQ1"
				bundle="${QtrLables}">
				<hdiits:option value="0"><fmt:message key="HRMS.SELECT"
				bundle="${QtrLables}" /></hdiits:option>
			</hdiits:select></td>
		</tr>
		<tr>
		 <td width="25%"><b><hdiits:caption captionid="HRMS.PoliceLine"
				bundle="${QtrLables}" /></b></td>
			<td width="25%"><hdiits:select caption="Police Line1" name="policeline1"
				id="policeline1" sort="false" mandatory="true"
				validation="sel.isrequired" size="1" onchange="getNewEntry(this,2)" captionid="HRMS.PoliceLine"
				bundle="${QtrLables}">
				<hdiits:option value="0"><fmt:message key="HRMS.SELECT"
				bundle="${QtrLables}" /></hdiits:option>
				<!--				<hdiits:option value="0"><hdiits:caption captionid="HRMS.newEntry" bundle="${QtrLables}"/></hdiits:option>-->
			</hdiits:select></td>
		 </tr>
	</table>

	<table width="100%" align="center" id="showpoliceline" style="display:none;">
		<tr>
			<td width="25%"><b><hdiits:caption captionid="HRMS.PoliceLine1"
				bundle="${QtrLables}" /></b></td>
			<td width="25%"><hdiits:text maxlength="50" caption="Police Line" name="policeline2"
				id="policeline2"  size="23" captionid="HRMS.PoliceLine1" bundle="${QtrLables}"  validation="txt.isrequired" mandatory="true" onblur="comparePoliceLine()"/></td>
	        <td width="25%">&nbsp;</td>
			<td width="25%">&nbsp;</td>
		</tr>
	</table>
	<table id="addressofPoliceline"  width="100%" style="display:none">
		<tr>
			 <td width="2%">&nbsp;</td>
			<td width="96%" > <br><br>  
			<hdiits:fmtMessage key="HRMS.addressPoliceLine" bundle="${QtrLables}" var="addressPoliceLine" ></hdiits:fmtMessage>
			<jsp:include page="//WEB-INF/jsp/common/address.jsp">
				<jsp:param name="addrName" value="Address1" />
				<jsp:param name="addressTitle" value="${addressPoliceLine}" />
				<jsp:param name="addrLookupName" value="Permanent Address" />
				<jsp:param name="mandatory" value="Y" />
			</jsp:include>
		 
			</td>
			<td width="2%">&nbsp;</td>
		</tr>
	</table>
	<table id="existingAddressPoliceLine" style="display:none;"
		width="100%">
		<tr>
			 <td width="2%">&nbsp;</td>
			<td width="96%" >
			<br><br><jsp:include page="//WEB-INF/jsp/common/address.jsp">
				<jsp:param name="addrName" value="Address2" />
				<jsp:param name="addressTitle" value="${addressPoliceLine}" />
				<jsp:param name="addrLookupName" value="Permanent Address" />
				<jsp:param name="mandatory" value="Y" />
			</jsp:include>
			
			</td>
			<td width="2%">&nbsp;</td>
		</tr>
	</table>

	<br>
		<table id="displayDtls1" style="display:none" border="1" width="100%">
		<tr>
			<td class="fieldLabel" bgcolor="#C9DFFF" width="10%"><b><hdiits:caption
				captionid="HRMS.PoliceStationHQ" bundle="${QtrLables}" /></b></td>
			<td class="fieldLabel" bgcolor="#C9DFFF" width="10%"><b><hdiits:caption
				captionid="HRMS.PoliceLine" bundle="${QtrLables}" /></b></td>
			<td class="fieldLabel" bgcolor="#C9DFFF" width="10%"><b><hdiits:caption
				captionid="HRMS.Action" bundle="${QtrLables}" /></b></td>
		</tr>
	</table>
	<center><hdiits:button name="submitbt1" type="button"
		id="submitbt1" value="submitbt1" captionid="HRMS.Submit" bundle="${QtrLables}" onclick="ValidateLines();"
		style="display:none"></hdiits:button>
		
		<hdiits:button name="deletebtn" type="button" id="deletebtn" value="deletebtn" onclick="deletePoliceLines()"
		captionid="HRMS.delete" bundle="${QtrLables}" style="display:none"/>
	<hdiits:button name="Close1" type="button" value="Close1"  captionid="HRMS.Close" bundle="${QtrLables}" onclick="ClosePage(document.frmAdd);"/>
	</center>
	</div>
	<div id="tcontent2" class="tabcontent" tabno="1">
	<br>
	<table width="100%" align="center">
		<tr>
			<td width="25%"><b>
				<hdiits:caption captionid="HRMS.TypeofOffice1" 	bundle="${QtrLables}" /></b>
			</td>
			<td width="25%">
				<hdiits:select caption="Type of Office" name="TypeOfOff" id="TypeOfOff" onchange="getJurisdiction(this,1,0)" mandatory="true"
					validation="sel.isrequired" sort="false" size="1" captionid="HRMS.TypeofOffice1" bundle="${QtrLables}">
				<hdiits:option value="0"><fmt:message key="HRMS.SELECT" 
				bundle="${QtrLables}" /></hdiits:option>
				<c:forEach var="Office_type" items="${OfficeTypeList}">
					<hdiits:option value="${Office_type.departmentId}">
				${Office_type.depName}</hdiits:option>
				</c:forEach>
			</hdiits:select></td>
			<td width="25%"><b><hdiits:caption captionid="HRMS.NameofOffice"
				bundle="${QtrLables}" /></b></td>
			<td width="25%"><hdiits:select caption="Name of Office"
				name="Jurisdiction" id="Jurisdiction"  captionid="HRMS.NameofOffice"
				bundle="${QtrLables}"
				onchange="getPoliceST(this,1,0)" sort="false" mandatory="true"
				validation="sel.isrequired" size="1">
				<hdiits:option value="0"><fmt:message key="HRMS.SELECT"
				bundle="${QtrLables}" /></hdiits:option>
			</hdiits:select></td>
		</tr>
		<tr>
			<td width="25%"><b><hdiits:caption captionid="HRMS.district"
				bundle="${QtrLables}" /></b></td>
			<td width="25%"><hdiits:select caption="district2"
				name="district2" id="district2" captionid="HRMS.district" bundle="${QtrLables}"
				 mandatory="true" validation="sel.isrequired" sort="false" size="1" onchange="getPoliceST(this,1,0)">
				<hdiits:option value="0"><fmt:message key="HRMS.SELECT" bundle="${QtrLables}" /></hdiits:option>
			</hdiits:select></td>
		
			<td width="25%"> <b><hdiits:caption captionid="HRMS.PoliceStationHQ1"
				bundle="${QtrLables}" /></b></td>
			<td width="25%"><hdiits:select caption="Police Station" name="Policestation"
				id="PoliceStation" sort="false" onchange="getPoliceLine(this,1,1,0)"
				mandatory="true" validation="sel.isrequired" size="1" captionid="HRMS.PoliceStationHQ1"
				bundle="${QtrLables}">
				<hdiits:option value="0"><fmt:message key="HRMS.SELECT"
				bundle="${QtrLables}" /></hdiits:option>
			</hdiits:select></td>
		
		</tr>
		<tr>
			<td width="25%" > <b><hdiits:caption captionid="HRMS.PoliceLine"
				bundle="${QtrLables}" /></b></td>
			<td width="25%">
				<hdiits:select name="policeline"
				id="policeline" mandatory="true" sort="false"
				validation="sel.isrequired" size="1" captionid="HRMS.PoliceLine"
				bundle="${QtrLables}"
				onchange="getQuarter();">
				<hdiits:option value="0"><fmt:message key="HRMS.SELECT"
				bundle="${QtrLables}" /></hdiits:option>
			</hdiits:select></td>
		</tr>
	</table>

	<table width="100%" align="center" >
		<tr>
			<td>
			<table class="tabtable" id="addressofpoliceLines" style="display:none">
				<tr>
				<td width="2%">&nbsp;</td>	
			<td width="96%"> 
			<jsp:include page="//WEB-INF/jsp/common/address.jsp">
						<jsp:param name="addrName" value="Address" />
						<jsp:param name="addressTitle" value="${addressPoliceLine}" />
						<jsp:param name="addrLookupName" value="Permanent Address" />
						<jsp:param name="mandatory" value="Y" />
					</jsp:include> </td>
			<td width="2%">&nbsp;</td>
				</tr>
				
			</table>
			</td>
		</tr>
	</table>
	<div id ="newQuarterLable">
	<hdiits:fieldGroup titleCaptionId="HRMS.show_newQuarter" bundle="${QtrLables}"   mandatory="true" >
	<table width="100%"  id="newQuarter" >
		<tr>
			<td width="25%"><b><hdiits:caption captionid="HRMS.TypeOfHouse" bundle="${QtrLables}" /> </b></td>
			<td width="25%">
				<hdiits:select caption="Type of Quarters" 	captionid="HRMS.TypeOfHouse" bundle="${QtrLables}" name="TypesQrtrs" id="TypesQrtrs" sort="false" mandatory="true"
					validation="sel.isrequired" size="1">
				<hdiits:option value="0"><fmt:message key="HRMS.SELECT" bundle="${QtrLables}" /></hdiits:option>
					<c:forEach var="TypesList" items="${typeList}">
						<hdiits:option value="${TypesList.qtrCode}">${TypesList.quaType} </hdiits:option>
					</c:forEach>
				</hdiits:select>
			</td>
			<td width="25%">&nbsp;</td>
			<td width="25%">&nbsp;</td>
		
		</tr>
		<tr>
			<td width="25%"><b><hdiits:caption captionid="HRMS.NosofQrtrs"
				bundle="${QtrLables}" /></b></td>
			<td width="25%"><hdiits:number name="QrtrsNos" id="QrtrsNos"
				captionid="HRMS.NosofQrtrs" bundle="${QtrLables}" size="14" maxlength="3"  mandatory="true" validation="txt.isrequired,txt.isnumber" /></td>
			<td width="25%">&nbsp;</td>
			<td width="25%">&nbsp;</td>
		
		</tr>
		<tr>
			<td width="25%"><b><hdiits:caption captionid="HRMS.Prefix"
				bundle="${QtrLables}" /></b></td>
			<td width="25%"><hdiits:text name="prefix1" id="prefix1" size="14" maxlength="30"  captionid="HRMS.Prefix" bundle="${QtrLables}" onkeypress="dodacheck(this)" onblur="dodacheck(this)"/></td>
			<td width="50%">
				<hdiits:button name="generate" type="button" captionid="HRMS.Generate" bundle="${QtrLables}" onclick="showQuarterNos()" /> 	<hdiits:text caption="Generated Quarters Names" name="showPrefix" id="showPrefix" 	size="23" mandatory="true" validation="txt.isrequired"
				captionid="showPrefix" readonly="true" />
			</td>
		</tr>
		
	</table>
	<br>
	<table width="100%">
	<tr>
	<td align="center">
	<hdiits:button name="add" id="add" type="button"
		value="add" captionid="HRMS.Add"
		bundle="${QtrLables}" onclick="validateAdd()" />
			<hdiits:button name="CloseBtn" type="button" value="Close"  captionid="HRMS.Close" bundle="${QtrLables}" onclick="ClosePage(document.frmAdd);"/>
    
	<td>
	</td>
	</table>
 
	</hdiits:fieldGroup>
	</div>
	<br>
	<hdiits:fieldGroup titleCaptionId="HRMS.Quarters" bundle="${QtrLables}"   mandatory="true" >	 
	<div id="qtrHeaderTbl" style="display:none">
	<table class="tabtable" width="100%" id="onQuarterShow"
		style="display:none">
		<tr>
			<td width="25%"><b><hdiits:caption captionid="HRMS.TypeOfHouse"
				bundle="${QtrLables}" /> </b></td>
			<td width="25%"><hdiits:select caption="Type of Quarters" captionid="HRMS.TypeOfHouse" name="TypesQrtrs1"   bundle="${QtrLables}"id="TypesQrtrs1" sort="false" mandatory="true"
				validation="sel.isrequired" size="1">
				<hdiits:option value="0"><fmt:message key="HRMS.SELECT" bundle="${QtrLables}" /></hdiits:option>
				<c:forEach var="TypesList1" items="${typeList}"> 
					<hdiits:option value="${TypesList1.qtrCode}">${TypesList1.quaType} </hdiits:option>
				</c:forEach>
			</hdiits:select></td>
	
			<td width="25%">&nbsp;</td>
		    <td width="25%">&nbsp;</td>
		
		</tr>
		<tr>
			<td width="25%"><b><hdiits:caption captionid="HRMS.NameofQuarter"
				bundle="${QtrLables}" /></b></td>
			<td width="25%"><hdiits:text name="QuarterName" id="QuarterName" validation="txt.isrequired" maxlength="33" captionid="HRMS.NameofQuarter" bundle="${QtrLables}"size="23" /></td>
			<td width="25%"><hdiits:button name="updaterowbutton" type="button" id="updaterow" style="display:none" value="updaterow" captionid="HRMS.update" bundle="${QtrLables}"
		onclick="updateRowData()" /></td>
			<td width="25%">&nbsp;</td>
		
		</tr>
	</table>
 
	</div>
	
	<table class="tabtable" id="displaydtlsQuarters" width="100%"
		align="center" border="1" style="display:none" style="border-collapse: collapse;" borderColor="BLACK"  >
		<tr>
			<td class="fieldLabel" bgcolor="#C9DFFF" width="10%" align="center"><b><hdiits:caption captionid="HRMS.PoliceStationHQ" bundle="${QtrLables}" /></b></td>
			<td class="fieldLabel" bgcolor="#C9DFFF" width="10%" align="center"><b><hdiits:caption captionid="HRMS.PoliceLine" bundle="${QtrLables}" /></b></td>
			<td class="fieldLabel" bgcolor="#C9DFFF" width="10%" align="center"><b><hdiits:caption captionid="HRMS.TypeOfHouse" bundle="${QtrLables}" /></b></td>
			<td class="fieldLabel" bgcolor="#C9DFFF" width="10%" align="center"><b><hdiits:caption captionid="HRMS.QuarterName" bundle="${QtrLables}" /></b></td>
			<td class="fieldLabel" bgcolor="#C9DFFF" width="10%" align="center"><b><hdiits:caption captionid="HRMS.Action" bundle="${QtrLables}" /></b></td>
		</tr>
		
	</table>
	<TABLE id="NoRecord" width="100%" style="display:none" border="1">
		<TR>
		   		<TD align="center">
		   		 			<font color="black"><b><fmt:message key="HRMS.NotFound" bundle="${QtrLables}"/></b></font>
		   		</TD>
		   </TR>
	</TABLE>
	</hdiits:fieldGroup>
	<br>
	 <div id="pageNavPosition"></div>
	<hdiits:hidden name="hiddenQuarterId" id="hiddenQuarterId" /> 
	<hdiits:hidden name="hiddenAddressId" id="hiddenAddressId" />
<table width="100%" id="tblBtn1" align="center" style="display:none">		
	<tr>
		<td align="center">
			<hdiits:button name="submitBt" type="button"  id="submitBt" value="submit"
			captionid="HRMS.SubmitNewQuarters" bundle="${QtrLables}" onclick="SubmitBoth()" />
			<hdiits:button name="Close" type="button" value="Close"  captionid="HRMS.Close" bundle="${QtrLables}" onclick="ClosePage(document.frmAdd);"/>
	     </td>
	</tr>
</table>
 

	
	</div>
	</div>
	<hdiits:hidden id="h1" name="h1" />
	<hdiits:hidden id="h2" name="h2" />
	<hdiits:hidden id="h3" name="h3" />
	<hdiits:hidden id="h4" name="h4" />
	<hdiits:hidden id="h5" name="h5" />
	

	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'
		controlNames="TypeOfOff,Jurisdiction,Policestation,policeline,typeQrtrs,showPrefix" />

</hdiits:form>
 <script type="text/javascript"><!--
        
    //--></script>

<script type="text/javascript">
	initializetabcontent("maintab")
	</script>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
