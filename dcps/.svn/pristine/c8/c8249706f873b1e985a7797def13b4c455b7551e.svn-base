<%
try {
%>
<%@ include file="../../../core/include.jsp"%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>


<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>

<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>	
<script type="text/javascript" src="<c:url value="script/common/address.js"/>"></script>	
<script type="text/javascript" src="<c:url value="script/common/tabcontent.js"/>"></script>
<script type="text/javascript" src="script/hrms/ess/reimb/hr_remb_validations.js"></script>

<fmt:setBundle basename="resources.ess.reimb.reimbLables" var="enLables" scope="request"/>

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="sbBtnValue" value="${resValue.sbBtnValue}"></c:set>
<c:set var="agencyVO_firsttime" value="${resValue.agencyVO_firsttime}" scope="session"></c:set>
<c:set var="flagforparent" value="${resValue.flagforparent}"></c:set>
<c:set var="agencyid" value="${resValue.agencyid}"></c:set>
<c:set var="agencyname" value="${resValue.agencyname}"></c:set>
<c:set var="agencyName" value="${resValue.agencyName}"></c:set>
<c:set var="phoneNumber" value="${resValue.phoneNumber}"></c:set>
<c:set var="mobile" value="${resValue.mobile}"></c:set>
<c:set var="fax" value="${resValue.fax}"></c:set>
<c:set var="email" value="${resValue.email}"></c:set>

<script type="text/javascript">

function addagencycombo()
{
	if('${agencyname}'!="" || '${agencyname}'!=null)
		window.opener.document.getElementById("agency_name").value='${agencyname}';
	if('${agencyid}'!="" || '${agencyid}'!=null)	
 		window.opener.document.getElementById("agency_id").value='${agencyid}';
 	window.opener.setAgencyValue();
 	window.close(); 		
}

</script>

<hdiits:form name="AgencyMaster" validate="true" method="POST"  encType="multipart/form-data" action="hrms.htm?actionFlag=InsertAgencyDtls">
<c:choose>
	<c:when test="${resValue.sbBtnValue eq 'Submit'}" >	
		<c:set var="submitBtnValue" value='${resValue.sbBtnValue}' />	
		<hdiits:hidden name="hdnFirstTimeFlag" id="firstTimeFlag" default="false" />
		<fmt:message var="submitBtnValue" key="RM.Submit" bundle="${enLables}" />
	</c:when>
	<c:otherwise>
		<fmt:message var="submitBtnValue" key="RM.Next" bundle="${enLables}" />
		<hdiits:hidden name="hdnFirstTimeFlag" id="firstTimeFlag" default="true" />
	</c:otherwise>
</c:choose>
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message bundle="${enLables}" key="RM.Agency"/></b></a></li>
		</ul>
	</div>
	
	<div class="tabcontentstyle">
		<div id="tcontent1" class="tabcontent" tabno="0">
			<table>
				<tr>
					<td></td>
					
					<td class="label_font" width="20%">
			    		<hdiits:caption captionid="RM.AgencyName" bundle="${enLables}"/>
		    		</td>
		    		
		    		<td class="label_font" width="2%">
			    		<hdiits:caption captionid="colon1" caption=":"/>
		    		</td>
		    
		    		<td class="label_font" width="20%">
			    	<hdiits:text  name="txtagname" id="txtagname" captionid="RM.AgencyName" bundle="${enLables}" validation="txt.isrequired" size="17" maxlength="50" mandatory="true" default="${agencyName}"></hdiits:text>
					</td>
					
					<td width="5%"></td>
		    
		     		<td width="20%" class="label_font"></td>
		     		
		     		<td width="2%" class="label_font"></td>
		     		
		     		<td width="20%" class="label_font"></td>
					<td></td>
				</tr>
				
				<tr></tr>
				<tr>
					<td></td>
					
					<td width="20%" colspan="7">
						<jsp:include page="/WEB-INF/jsp/common/address.jsp">
            	    	<jsp:param name="addrName" value="AgencyAdd" />
                		<jsp:param name="addressTitle" value="Agency Address" />
	                	<jsp:param name="addrLookupName" value="Present Address" />
				    	<jsp:param name="mandatory" value="Yes"/>              
    					</jsp:include>
					</td>
				</tr>
				
				<tr></tr>
				<tr>
					<td></td>
					
					<td class="label_font" width="20%">
			    		<hdiits:caption captionid="RM.TelNo" bundle="${enLables}"/>
		    		</td>
		    		
		    		<td class="label_font" width="2%">
			    		<hdiits:caption captionid="colon1" caption=":"/>
		    		</td>
		    
		    		<td class="label_font" width="20%">
			    		<hdiits:text  name="txttelno" id="txttelno" captionid="RM.TelNo" bundle="${enLables}" validation="txt.isnumber" size="17" maxlength="8" default="${phoneNumber}"></hdiits:text>
					</td>
					
					<td width="5%"></td>
		    
		     		<td width="20%" class="label_font">
		     			<hdiits:caption captionid="RM.Mobile" bundle="${enLables}"/>
		     		</td>
		     		
		     		<td width="2%" class="label_font">
		     			<hdiits:caption captionid="colon1" caption=":"/>
		     		</td>
		     		
		     		<td width="20%" class="label_font">
		     			<hdiits:text  name="txtmobile" id="txtmobile" captionid="RM.Mobile" bundle="${enLables}" validation="txt.isnumber" size="17" maxlength="12" default="${mobile}"></hdiits:text>
		     		</td>
					<td></td>
				</tr>
				
				<tr>
					<td></td>
					
					<td class="label_font" width="20%">
			    		<hdiits:caption captionid="RM.Fax" bundle="${enLables}"/>
		    		</td>
		    		
		    		<td class="label_font" width="2%">
			    		<hdiits:caption captionid="colon1" caption=":"/>
		    		</td>
		    
		    		<td class="label_font" width="20%">
			    		<hdiits:text  name="txtfax" id="txtfax" captionid="RM.Fax" bundle="${enLables}" validation="txt.isnumber" size="17" maxlength="8" default="${fax}"></hdiits:text>
					</td>
					
					<td width="5%"></td>
		    
		     		<td width="20%" class="label_font">
		     			<hdiits:caption captionid="RM.Email" bundle="${enLables}"/>
		     		</td>
		     		
		     		<td width="2%" class="label_font">
		     			<hdiits:caption captionid="colon1" caption=":"/>
		     		</td>
		     		
		     		<td width="20%" class="label_font">
		     			<hdiits:text  name="txtemail" id="txtemail" captionid="RM.Email" bundle="${enLables}" validation="txt.email" size="17" default="${email}"></hdiits:text>
		     		</td>
					<td></td>
				</tr>
				
				</table>
		</div>
		<div id="tcontent2" class="tabcontent" tabno="1">
 		</div>
 	
	<script type="text/javascript">var navDisplay=false;</script>
	<jsp:include page="../../../core/tabnavigation.jsp" >
			<jsp:param name="submitText" value="${submitBtnValue}"/>
	</jsp:include>			
  	</div>
	
	<script type="text/javascript">

		<% /* Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma. */ %>

		initializetabcontent("maintab")
		if('${flagforparent}'=="true")
		{
			addagencycombo();
		}
	</script>

	<hdiits:validate controlNames="tesxt" locale="${sessionScope.locale}" />
	</hdiits:form>
	<c:if test="${resValue.sbBtnValue eq 'Submit'}">
		<c:choose>
			<c:when test="${resValue.localeVal eq 'en_US'}">
				<% request.getSession().setAttribute("locale", "gu");%>
			</c:when>
			<c:otherwise>
				<% request.getSession().setAttribute("locale", "en_US");%>
			</c:otherwise>
		</c:choose>	
	</c:if>
	<%
			} catch (Exception e) {
			e.printStackTrace();
		}
	%>