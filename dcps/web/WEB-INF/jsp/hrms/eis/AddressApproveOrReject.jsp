
<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setBundle basename="resources.eis.eisLables" var="empEditListCommonLables" scope="request"/>
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="birthFlag" value="${resValue.birthFlag}" ></c:set>
<c:set var="nativeFlag" value="${resValue.nativeFlag}" ></c:set>
<c:set var="permanentFlag" value="${resValue.permanentFlag}" ></c:set>
<c:set var="currentFlag" value="${resValue.currentFlag}" ></c:set>
<c:set var="birthUpdateFlag" value="${resValue.birthUpdateFlag}" ></c:set>
<c:set var="nativeUpdateFlag" value="${resValue.nativeUpdateFlag}" ></c:set>
<c:set var="permanentUpdateFlag" value="${resValue.permanentUpdateFlag}" ></c:set>
<c:set var="currentUpdateFlag" value="${resValue.currentUpdateFlag}" ></c:set>


<hdiits:form name="frmBF" validate="true" method="POST" action="" encType="multipart/form-data">
	
	<input type="hidden" name="reqId" value="${resValue.reqId }" > 
	
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="updateAddressMaster" bundle="${empEditListCommonLables}"></fmt:message></b></a></li>
		</ul>
	</div>
	
	<div class="tabcontentstyle">
		<div id="tcontent1" class="tabcontent" tabno="0">
			<%@ include file="empInfo/EmployeeInfo.jsp"%>
			<!--<table class="tabtable" >
				<tr><td colspan="10"></td></tr>
					 <tr bgcolor="#386CB7">
						<td  class="fieldLabel" colspan="10" align="center"><font color="#ffffff">
							<strong><u><fmt:message bundle="${empEditListCommonLables}" key="Address_Details"/></u></strong></font>
						</td>	
					</tr>
					<tr>
						<td class="fieldLabel" colspan="2"></td>
						<td colspan="5"></td>
					</tr> 
			</table>-->
			
			<c:if test="${birthFlag eq 'true' or birthUpdateFlag eq 'true'}">	
				<hdiits:fieldGroup id="birthAddress_dtl_id" titleCaptionId="eis.BIRTH_PLACE" bundle="${empEditListCommonLables}" collapseOnLoad="true">
					<table id="birthDiv" align="center" width="100%">
						
						<!--<tr bgcolor="#386CB7" id="birthTR">
							<td  class="" colspan="10"><font color="#ffffff">
							<strong><u><fmt:message bundle="${empEditListCommonLables}" key="eis.BIRTH_PLACE"/></u></strong></font></td>	
						</tr>-->
						
						<c:if test="${birthUpdateFlag eq 'true'}">
							<!--<tr bgcolor="#386CB7"  align="center"> 
								<td  class="fieldLabel" colspan="10"><font color="#ffffff"><strong><u><fmt:message bundle="${empEditListCommonLables}" key="eis.ApprovedBirth"/></u></strong></font></td>			
							</tr>-->
							<tr>
								<td class="fieldLabel" colspan="1"></td>
								<hdiits:fmtMessage key="eis.ApprovedBirth" bundle="${empEditListCommonLables}" var="aprBirthPlace" />
								<td class="fieldLabel" colspan="0" align="left" width="90%">
									<jsp:include page="../../common/viewAddress.jsp">
										<jsp:param name="addrName" value="birthPlaceAddress" />
										<jsp:param name="addressTitle" value="${aprBirthPlace}" />
									</jsp:include>
								</td>
								<td class="fieldLabel"  colspan="2"></td>
							</tr>
						</c:if>
					
						<tr>			
							<c:if test="${birthFlag eq 'true'}">	
								<!--<tr bgcolor="#386CB7"  align="center" width="100%"> 
										<td  class="fieldLabel" colspan="10">
											<font color="#ffffff"><strong><u><fmt:message bundle="${empEditListCommonLables}" key="eis.RequestedBirth"/></u></strong></font>
										</td>			
								</tr>-->
									<td class="fieldLabel" colspan="1"></td>
									<hdiits:fmtMessage key="eis.RequestedBirth" bundle="${empEditListCommonLables}" var="reqBirthPlace" />
									<td class="fieldLabel" colspan="0" align="left" width="90%" >
											<jsp:include page="../../common/viewAddress.jsp">
												<jsp:param name="addrName" value="birthPlaceAddressUpdate" />
												<jsp:param name="addressTitle" value="${reqBirthPlace}" />
											</jsp:include>					
									</td>
									<td class="fieldLabel"  colspan="2"></td>
							</c:if>
					
					   </tr>
					</table>
				</hdiits:fieldGroup>
			</c:if>
			<br>
			<c:if test="${nativeFlag eq 'true' or nativeUpdateFlag eq 'true'}">
				<hdiits:fieldGroup id="nativeAddress_dtl_id" titleCaptionId="eis.NATIVE_PLACE" bundle="${empEditListCommonLables}" collapseOnLoad="true">
					<table id="native" align="center"  width="100%" >
						 <!--<tr bgcolor="#386CB7" > 
							<td  class="fieldLabel" colspan="10">
								<font color="#ffffff">
								<strong><u><fmt:message bundle="${empEditListCommonLables}" key="eis.NATIVE_PLACE"/></u></strong></font>
							</td>			
						</tr>-->	
						<c:if test="${nativeUpdateFlag eq 'true'}">
							<!--<tr bgcolor="#386CB7" align="center"> 
								<td  class="fieldLabel" colspan="10"><font color="#ffffff"><strong><u><fmt:message bundle="${empEditListCommonLables}" key="eis.ApprovedNative"/></u></strong></font></td>			
							</tr>-->
							<tr>
								<td class="fieldLabel" colspan="1"></td>
								<hdiits:fmtMessage key="eis.ApprovedNative" bundle="${empEditListCommonLables}" var="aprNativePlace" />
								<td class="fieldLabel" colspan="0" align="left" width="90%" >
									<jsp:include page="../../common/viewAddress.jsp">
										<jsp:param name="addrName" value="nativePlaceAddress" />
										<jsp:param name="addressTitle" value="${aprNativePlace}" />
									</jsp:include>
								</td>
								<td class="fieldLabel"  colspan="2"></td>
							</tr>
						</c:if>					
						<c:if test="${nativeFlag eq 'true'}">
							<!--<tr bgcolor="#386CB7"  align="center"> 
								<td  class="fieldLabel" colspan="10"><font color="#ffffff"><strong><u><fmt:message bundle="${empEditListCommonLables}" key="eis.RequestedNative"/></u></strong></font></td>			
							</tr>-->
							<tr id="native1" >			
									<td class="fieldLabel" colspan="1"></td>
									<hdiits:fmtMessage key="eis.RequestedNative" bundle="${empEditListCommonLables}" var="reqNativePlace" />
									<td class="fieldLabel" colspan="0" width="90%" align="left"> 
										<jsp:include page="../../common/viewAddress.jsp">
											<jsp:param name="addrName" value="nativePlaceAddressUpdate" />
											<jsp:param name="addressTitle" value="${reqNativePlace}" />
										</jsp:include>
									</td>
									<td class="fieldLabel" colspan="2"></td>
							</tr>					
						</c:if>		
						
					</table>
				</hdiits:fieldGroup>
			</c:if>
			<br>
			<c:if test="${permanentFlag eq 'true' or permanentUpdateFlag eq 'true'}">
				<hdiits:fieldGroup id="permanentAddress_dtl_id" titleCaptionId="PERMANENT_PLACE" bundle="${empEditListCommonLables}" collapseOnLoad="true">
				<table id="permanant"  align="center" width="100%" >
					<!--
					<tr bgcolor="#386CB7" >
						<td class="fieldLabel" colspan="10"><font color="#ffffff"><strong><u><fmt:message bundle="${empEditListCommonLables}" key="PERMANENT_PLACE"/></u></strong></font></td>
					</tr>
					-->
				
					<c:if test="${permanentUpdateFlag eq 'true'}">
						<!--<tr bgcolor="#386CB7"  align="center">
							<td class="fieldLabel" colspan="10"><font color="#ffffff"><strong><u><fmt:message bundle="${empEditListCommonLables}" key="eis.ApprovedPermanent"/></u></strong></font></td>
						</tr>-->
						<tr>
							<td class="fieldLabel" colspan="1"></td>
							<hdiits:fmtMessage key="eis.ApprovedPermanent" bundle="${empEditListCommonLables}" var="aprPermanentPlace" />
							<td class="fieldLabel" colspan="0" align="left" width="90%" >
								<jsp:include page="../../common/viewAddress.jsp">
									<jsp:param name="addrName" value="permanentPlaceAddress" />
									<jsp:param name="addressTitle" value="${aprPermanentPlace}" />
								</jsp:include>
							</td>
							<td class="fieldLabel"  colspan="2"></td>
						</tr>
				    </c:if>
					<c:if test="${permanentFlag eq 'true'}">
							<!--<tr bgcolor="#386CB7"  align="center">
								<td class="fieldLabel" colspan="10"><font color="#ffffff"><strong><u><fmt:message bundle="${empEditListCommonLables}" key="eis.RequestedPermanent"/></u></strong></font></td>
							</tr>-->
							<tr id="row_personPermanentAddress">
								<td class="fieldLabel" colspan="1"></td>
								<hdiits:fmtMessage key="eis.RequestedPermanent" bundle="${empEditListCommonLables}" var="reqPermanentPlace" />
								<td class="fieldLabel" colspan="0" align="left" width="90%" >
									<jsp:include page="../../common/viewAddress.jsp">
										<jsp:param name="addrName" value="permanentPlaceAddressUpdate" />
										<jsp:param name="addressTitle" value="${reqPermanentPlace}" />
									</jsp:include>
								</td>	
								<td class="fieldLabel" colspan="2"> </td>	
							</tr>	
					</c:if>
				</table>
				</hdiits:fieldGroup>
			</c:if>
			<br>
			<c:if test="${currentFlag eq 'true' or currentUpdateFlag eq 'true'}">
				<hdiits:fieldGroup id="currentAddress_dtl_id" titleCaptionId="CURRENT_PLACE" bundle="${empEditListCommonLables}">
					<table id="current"  width="100%" >
						<!-- 
						 <tr bgcolor="#386CB7" >
							<td class="fieldLabel" colspan="10">
								<font color="#ffffff"><strong><u><fmt:message bundle="${empEditListCommonLables}" key="CURRENT_PLACE"/></u></strong></font>
							</td>
						</tr>
						-->
						
						<c:if test="${currentUpdateFlag eq 'true'}">
						<!-- 	<tr bgcolor="#386CB7"  align="center">
								<td class="fieldLabel" colspan="10"><font color="#ffffff"><strong><u><fmt:message bundle="${empEditListCommonLables}" key="eis.ApprovedCurrent"/></u></strong></font></td>
							</tr>-->
							<tr> 
								<td class="fieldLabel" colspan="1"></td>
								<hdiits:fmtMessage key="eis.ApprovedCurrent" bundle="${empEditListCommonLables}" var="aprCurrentPlace" />
								<td class="fieldLabel" colspan="0" align="left" width="90%" >
									<jsp:include page="../../common/viewAddress.jsp">
										<jsp:param name="addrName" value="currentPlaceAddress" />
										<jsp:param name="addressTitle" value="${aprCurrentPlace}" />
									</jsp:include>
								</td>
								<td class="fieldLabel"  colspan="2"></td>
							</tr>
						</c:if>
						
						<c:if test="${currentFlag eq 'true'}">
							<!--<tr bgcolor="#386CB7"  align="center">
								<td class="fieldLabel" colspan="10"><font color="#ffffff"><strong><u><fmt:message bundle="${empEditListCommonLables}" key="eis.RequestedCurrent"/></u></strong></font>
							</tr>-->
							<tr id="row_personCurrentAddress">
								<td class="fieldLabel" colspan="1"></td>
								<hdiits:fmtMessage key="eis.RequestedCurrent" bundle="${empEditListCommonLables}" var="reqCurrentPlace" />
								<td class="fieldLabel" colspan="0" align="left" width="90%" >
									<jsp:include page="../../common/viewAddress.jsp">
										<jsp:param name="addrName" value="currentPlaceAddressUpdate" />
										<jsp:param name="addressTitle" value="${reqCurrentPlace}" />
									</jsp:include>						
								</td>	
								<td class="fieldLabel" colspan="2"> </td>
							</tr>
						</c:if>
					</table>
				</hdiits:fieldGroup>
			</c:if>

			<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
			
			<script type="text/javascript">
					//Start Tab Content script for UL with id="maintab" Separate multiple ids each  a comma.
					initializetabcontent("maintab");
			</script>
			
 		</div>	
	</div>	

</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>