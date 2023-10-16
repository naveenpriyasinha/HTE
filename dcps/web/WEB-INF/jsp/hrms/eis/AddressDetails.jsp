
<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/Familyaddress.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/ChangeAddress.js"/>"></script>

<fmt:setBundle basename="resources.eis.eisLables" var="empEditListCommonLables" scope="request"/>

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="listOfAddress" value="${resValue.listOfVO}" ></c:set>
<c:set var="birthFlag" value="${resValue.birthFlag}" ></c:set>
<c:set var="nativeFlag" value="${resValue.nativeFlag}" ></c:set>
<c:set var="permanentFlag" value="${resValue.permanentFlag}" ></c:set>
<c:set var="currentFlag" value="${resValue.currentFlag}" ></c:set>
<c:set var="cur_key" value="${resValue.cur_key}"></c:set>
<c:set var="per_key" value="${resValue.per_key}"></c:set>
<c:set var="allEmpAddressXmlFile" value="${resValue.allEmpAddressXmlFile}"></c:set> 
<c:set var="empFlag" value="${resValue.empFlag}"></c:set>

<script language="javascript">

	var xmlFileName = '${allEmpAddressXmlFile}';
	var tempPermnent="${per_key}";
	var temp='${cur_key}';
	var flag='';
	var updateFlag='';
	var birthPattern=new RegExp("BA");
	var nativePattern=new RegExp("NA");
	var currentPattern=new RegExp("CA");
	var permanentPattern=new RegExp("PA");
	var flag1=true,flag2=true;
	var comp =new Array();
	comp[0]='rdoAddress'+'birthPlaceAddress';
	comp[1]='rdoAddress'+'nativePlaceAddress';
	comp[2]='radPermanentAddress';
	comp[3]='radCurrentAddress';
	
	
	var AddressDtlsAlertMsgArr=new Array();
	AddressDtlsAlertMsgArr[0]='<fmt:message  bundle="${empEditListCommonLables}" key="EIS.confirmSave"/>';
	AddressDtlsAlertMsgArr[1]='<fmt:message  bundle="${empEditListCommonLables}" key="EIS.selectUpdate"/>';
	AddressDtlsAlertMsgArr[2]='<fmt:message key="eis.selectBirthAddress" bundle="${empEditListCommonLables}"/>';
	AddressDtlsAlertMsgArr[3]='<fmt:message key="eis.selectNativeAddress" bundle="${empEditListCommonLables}"/>';
	AddressDtlsAlertMsgArr[4]='<fmt:message key="eis.selectPermanenttAddress" bundle="${empEditListCommonLables}"/>';
	AddressDtlsAlertMsgArr[5]='<fmt:message key="eis.selectCurrentAddress" bundle="${empEditListCommonLables}"/>';
	AddressDtlsAlertMsgArr[6]='<fmt:message key="eis.AjexMsgAlert" bundle="${empEditListCommonLables}"/>';
</script>



<hdiits:form name="frmBF" validate="true" method="POST" action=""  encType="multipart/form-data">
	

	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="updateAddressMaster" bundle="${empEditListCommonLables}"></fmt:message></b></a></li>
		</ul>
	</div>
	
	<div class="tabcontentstyle">
		<div id="tcontent1" class="tabcontent" tabno="0">
			<table class="tabtable" width="100%" style="display:none" id="errorMessage">
				<tr>
					<td width="35%"></td>
					<td class="fieldLabel" width="40%" colspan="4">
						<hdiits:text name="errorBox" id="errorBox" readonly="readonly" style="color: #E41B17;width:100%;border:0; bgcolor: #FFFFFF; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: #FFFFFF;"></hdiits:text>
					</td>
					<td width="25%"></td>
				</tr>
			</table>
			
			<table class="tabtable" >
				<tr><td colspan="10"><%@ include file="empInfo/EmployeeInfo.jsp"%></td></tr>
				<!-- <tr bgcolor="#386CB7" >
					<td  class="fieldLabel" colspan="10" align="center"><font color="#ffffff"><strong><u><fmt:message key="Address_Details" bundle="${empEditListCommonLables}"/></u></strong></font></td>	
				</tr>
				<tr><td class="fieldLabel" colspan="2"></td>
					<td colspan="5"></td>
				</tr> -->
			</table>
			
			
				<table id="birthDiv"  align="center" width="100%">
					<!--<tr bgcolor="#386CB7"  id="birthTR"  >
						<td  class="fieldLabel" colspan="10" align="center"><font color="#ffffff"><strong><u><fmt:message key="eis.BIRTH_PLACE" bundle="${empEditListCommonLables}"/></u></strong></font></td>	
					</tr>-->
					<tr id="readOnyBirthPlaceAddressTrId" style="">
						<td class="fieldLabel" colspan="1"></td>
						<td class="fieldLabel" colspan="0" align="left" width="100%" >
						<hdiits:fmtMessage key="eis.BIRTH_PLACE" bundle="${empEditListCommonLables}" var="birthPlace" />
						<jsp:include page="../../common/viewAddress.jsp">
							<jsp:param name="addrName" value="birthPlaceAddressReadOnly" />
							<jsp:param name="addressTitle" value="${birthPlace}" />
						</jsp:include>
						</td>
						
					</tr>
					<tr id="birthPlaceAddressTrId" align="center" style="display: none;">			
						<td class="fieldLabel" colspan="1"></td>
						<td class="fieldLabel" colspan="0" align="left" width="100%" >
							<jsp:include page="../../common/address.jsp">
								<jsp:param name="addrName" value="birthPlaceAddress" />
								<jsp:param name="addressTitle" value="${birthPlace}" />
								<jsp:param name="addrLookupName" value="Permanent Address" />
								<jsp:param name="mandatory" value="Y" />	
							</jsp:include>
						</td>
						<td class="fieldLabel"  colspan="2"></td>
					</tr>
				</table>
				
				<table width="100%" align="center" >
					<tr align="center">
						<td align="center">
							<hdiits:button name="birthUpdate" id="birthUpdate" type="button" captionid="EIS.Update" bundle="${empEditListCommonLables}" onclick="updateBirthAddress()"/>
							<hdiits:button name="birthReset"  id="birthReset" type="button"  captionid="EIS.Reset" bundle="${empEditListCommonLables}" onclick="resetBirthAddress();"/>
						</td>
					</tr>
				</table>
			<br>
			<table id="native"  align="center" width="100%" >
	 			<!--<tr bgcolor="#386CB7" > 
					<td  class="fieldLabel" colspan="10" align="center"><font color="#ffffff"><strong><u><fmt:message key="eis.NATIVE_PLACE" bundle="${empEditListCommonLables}"/></u></strong></font></td>			
				</tr>	-->
				<tr id="readOnyNativePlaceAddressTrId">
					<td class="fieldLabel" colspan="1"></td>
					<td class="fieldLabel" colspan="0" align="left" width="100%" >
					<hdiits:fmtMessage key="eis.NATIVE_PLACE" bundle="${empEditListCommonLables}" var="nativePlace" />
					<jsp:include page="../../common/viewAddress.jsp">
							<jsp:param name="addrName" value="nativePlaceAddressReadOnly" />
							<jsp:param name="addressTitle" value="${nativePlace}" />
					</jsp:include>
					</td>
				</tr>					
				<tr  align="center" id="nativePlaceAddressTrId" style="display: none;">			
					<td class="fieldLabel" colspan="1"></td>
					<td class="fieldLabel" colspan="0" align="left" width="100%"> 
						<jsp:include page="../../common/address.jsp">
							<jsp:param name="addrName" value="nativePlaceAddress" />
							<jsp:param name="addressTitle" value="${nativePlace}" />
							<jsp:param name="addrLookupName" value="Permanent Address" />
							<jsp:param name="readOnly" value="Y" />							
						</jsp:include>
					</td>
					<td class="" colspan="2"></td>
				</tr>
			</table>
		<table width="100%" align="center" >
			<tr align="center">
				<td align="center">
					<hdiits:button name="nativeUpdate" id="nativeUpdate" type="button" captionid="EIS.Update" bundle="${empEditListCommonLables}" onclick="updateNativeAddress()" />
					<hdiits:button name="nativeReset"  id="nativeReset" type="button"  captionid="EIS.Reset" bundle="${empEditListCommonLables}" onclick="resetNativeAddress();"/>
				</td>
			</tr>
		</table>
		<br>				
<hdiits:fieldGroup titleCaptionId="PERMANENT_PLACE" bundle="${empEditListCommonLables}">	
<table id="permanantRadio"  width="100%">
	<!--<tr bgcolor="#386CB7" >	<td class="fieldLabel" colspan="10" align="center"><font color="#ffffff"><strong><u><fmt:message key="PERMANENT_PLACE" bundle="${empEditListCommonLables}"/></u></strong></font></td></tr>-->
	<tr> 
		<td class="fieldLabel" width="50%" align="right">
			<hdiits:radio name="radPermanentAddress" id="radPermanentAddress" value="Y" onclick="showHideAddress(1,'Y')" default="N"  captionid="eis.Yes" bundle="${empEditListCommonLables}"/>
			<hdiits:radio name="radPermanentAddress" id="radPermanentAddress" value="N" onclick="showHideAddress(1,'N')" default="N"  captionid="eis.No" bundle="${empEditListCommonLables}"/>
		</td>
		<td class="fieldLabel" align="left">
			<hdiits:caption captionid="eis.SAME_AS_NATIVE_Address" bundle="${empEditListCommonLables}"/>
			<!--<fmt:message key="eis.SAME_AS_NATIVE_Address" bundle="${empEditListCommonLables}"/>-->
		</td>
	</tr>
	</table>
	<table id="permanant" align="center"  width="100%">
	<tr id="readOnyPermanentAddressTrId" style="">
		<td class="fieldLabel" colspan="1"></td>
		<td class="fieldLabel" colspan="0" align="left" width="100%" >
		<hdiits:fmtMessage key="PERMANENT_PLACE" bundle="${empEditListCommonLables}" var="permanentPlace" />
		<jsp:include page="../../common/viewAddress.jsp">
				<jsp:param name="addrName" value="permanentAddressReadOnly" />
		</jsp:include>
		</td>
	</tr>
	<tr id="permanentAddressTrId" align="center" style="display: none;">
		<td class="fieldLabel" colspan="1"> </td>
			<td class="fieldLabel" colspan="0" align="left" width="100%"> 
			<jsp:include page="../../common/address.jsp">
					<jsp:param name="addrName" value="permanentPlaceAddress" />
					<jsp:param name="addrLookupName" value="Permanent Address" />
					<jsp:param name="mandatory" value="Y" />
					<jsp:param name="readOnly" value="Y" />	
			</jsp:include>
		</td>	
		<td class="fieldLabel" colspan="2"> </td>	
	</tr>
</table>
<table width="100%" align="center" >
	<tr align="center">
		<td align="center"><hdiits:button name="permanentUpdate" id="permanentUpdate" type="button" captionid="EIS.Update" bundle="${empEditListCommonLables}" onclick="updatePermanentAddress()"/>
		<hdiits:button name="permanentReset"  id="permanentReset" type="button"  captionid="EIS.Reset" bundle="${empEditListCommonLables}" onclick="resetPermanentAddress();"/></td>
	</tr>	
</table>
</hdiits:fieldGroup>
<br>
<hdiits:fieldGroup titleCaptionId="CURRENT_PLACE" bundle="${empEditListCommonLables}">
	<table id="currentRadio"  width="100%">
	<!--	<tr bgcolor="#386CB7" >
			<td class="fieldLabel" colspan="10" align="center"><font color="#ffffff"><strong><u><fmt:message key="CURRENT_PLACE" bundle="${empEditListCommonLables}"/></u></strong></font></td>
		</tr>-->
		<tr>  
			<td class="fieldLabel" width="50%" align="right">
				<hdiits:radio name="radCurrentAddress" id="radCurrentAddress" value="Y" onclick="showHideAddress(2,'Y')" default="N" captionid="eis.Yes" bundle="${empEditListCommonLables}"/>
				<hdiits:radio name="radCurrentAddress" id="radCurrentAddress" value="N" onclick="showHideAddress(2,'N')" default="N" captionid="eis.No" bundle="${empEditListCommonLables}"/>
			</td>
			<td class="fieldLabel" align="left">
				<hdiits:caption captionid="eis.SAME_AS_PERMANENT_Address" bundle="${empEditListCommonLables}"/>
				<!--<fmt:message key="eis.SAME_AS_PERMANENT_Address" bundle="${empEditListCommonLables}"/>-->
			</td>
		</tr>
		</table>
		<table id="current"  width="100%">
		<tr id="readOnyCurrentPlaceAddressTrId" style="">
			<td class="fieldLabel" colspan="1"></td>
			<td class="fieldLabel" colspan="0" align="left" width="100%" >
				<hdiits:fmtMessage key="CURRENT_PLACE" bundle="${empEditListCommonLables}" var="currentPlace" />
			<jsp:include page="../../common/viewAddress.jsp">
					<jsp:param name="addrName" value="currentPlaceAddressReadOnly" />
			</jsp:include>
			</td>
		</tr>
		<tr id="currentPlaceAddressTrId" align="center" style="display: none;">
			<td class="fieldLabel" colspan="1"> </td>
			<td class="fieldLabel" colspan="0" align="left" width="100%" > 	
				<jsp:include page="../../common/address.jsp">
					<jsp:param name="addrName" value="currentPlaceAddress" />
					<jsp:param name="addrLookupName" value="Present Address" />
					<jsp:param name="mandatory" value="Y" />
					<jsp:param name="readOnly" value="Y" />
				</jsp:include>
			</td>	
			<td class="fieldLabel" colspan="2"> </td>
		</tr>
	</table>
	<table width="100%" align="center" >
		<tr align="center">
			<td align="center">
			<hdiits:button name="currentUpdate" id="currentUpdate" type="button" captionid="EIS.Update" bundle="${empEditListCommonLables}" onclick="updateCurrentPlaceAddress()"/>
			<hdiits:button name="currentReset"  id="currentReset" type="button"  captionid="EIS.Reset" bundle="${empEditListCommonLables}" onclick="resetCurrentPlaceAddress();"/></td>
		</tr>
	</table>
</hdiits:fieldGroup>
	<BR>
<table align="center" width="100%" cellpadding="5" cellspacing="5">    
	<tr align="center">
		<td>
			<hdiits:button name="btnSubmit" type="button" captionid="EIS.Submit" bundle="${empEditListCommonLables}" onclick="validateForm()"/>
			<hdiits:button name="Close" type="button" captionid="EIS.CloseButton" bundle="${empEditListCommonLables}" onclick="closeWindow()"></hdiits:button>
		</td>
	</tr>  
</table>  
 
<hdiits:validate locale="${locale}" />

<script type="text/javascript">
	//changed by Sunil - start
	var empFlag='${empFlag}';
	var str='<fmt:message bundle="${empEditListCommonLables}" key="EIS.empMstAlert"/>';
	if(!empFlag || empFlag=='' )
	{
		document.getElementById('errorMessage').style.display='';				
		document.getElementById('errorBox').value=str;
		document.frmBF.btnSubmit.disabled=true;	
	}
	//changed by Sunil - end
	onChangeAddressFormLoad();
	initializetabcontent("maintab");
</script>

</div></div>

</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

	    								