<%
try {
%>
<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<script type="text/javascript"  src="common/script/tagLibValidation.js"></script>
<script type="text/javascript"  src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/OuterPaybillPara.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>
	
 	
	<c:set var="resultObj" value="${result}" > </c:set>
	<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>
	<c:set  var="headresultSet" value='${resultValue.headresultSet}' /> 
    <c:set var="msg" value="${resultValue.msg}" ></c:set>
    <c:set var="result" value="${resultValue.result}" ></c:set>
    <c:set var="deptList" value="${resultValue.deptList}" ></c:set>
    <c:set var="hrPayBillHeadMpg" value="${resultValue.hrPayBillHeadMpg}" ></c:set>
    <c:set var="demandNoList" value="${resultValue.demandNoList}" ></c:set>
    <c:set var="mjrHeadList" value="${resultValue.mjrHeadList}" ></c:set>
    <c:set var="subMjrHeadList" value="${resultValue.subMjrHeadList}" ></c:set>
    <c:set var="minorHeadList" value="${resultValue.minorHeadList}" ></c:set>
    <c:set var="subHeadList" value="${resultValue.subHeadList}" ></c:set>
    <c:set var="SchemeCode" value="${resultValue.SchemeCode}" ></c:set>
    <c:set var="SchemeName" value="${resultValue.SchemeName}" ></c:set>
    <c:set var="budSubHeadId" value="${resultValue.budSubHeadId}" ></c:set>
	<c:set var="ChargeTypeName" value="${resultValue.ChargeTypeName}" ></c:set>
	<c:set var="PlanName" value="${resultValue.PlanName}" ></c:set>
    
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>


<hdiits:form name="billHeadMaster" validate="true" method="POST" action="./hrms.htm?actionFlag=getDepartmentList&edit=Y" encType="multipart/form-data">


	
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="SH.SchemeUpdate" bundle="${commonLables}"/></b></a></li>
	</ul>
	</div>
	
	
<div class="halftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0">
	<br/><br/>
	<TABLE align="center" width="80%">  
	<TR>
		<TD  align="left" class="Label" width="15%">
		 <b><fmt:message key="PR.DEPT" bundle="${commonLables}"/></b></TD>
    	<TD	width ="25%">
    	<hdiits:select style="width:90%"  name="cmbDept" size="1" sort="false" caption="Department" captionid="Dept" validation="sel.isrequired" mandatory="true" onchange="GetDemandNo();" >
	    <hdiits:option value="-1">---------------------------Select----------------------------</hdiits:option>
	  			       <c:forEach items="${deptList}" var="deptList">
								<c:choose>
									<c:when test="${hrPayBillHeadMpg.locationCode==deptList.locId}">
										<option selected="true" value='<c:out value="${hrPayBillHeadMpg.locationCode}"/>' >
										<c:out  value="${deptList.locName}"/></option>
									</c:when>
									<c:otherwise>
										<option value='<c:out value="${deptList.locationCode}"/>'>
										<c:out value="${deptList.locName}"/></option>
									</c:otherwise>
								</c:choose>								
				    	</c:forEach>		
	   	</hdiits:select>
	   </TD>
	</TR>
	<tr></tr><tr></tr><tr></tr><tr></tr>
	<tr>
		<TD align="left" class="Label" width = "15%">
		<b><fmt:message key="BHM.SCHEMECODE" bundle="${commonLables}"/></b></TD>
		<td align="left" width ="25%">
		<hdiits:number style="width:87%" mandatory="true" id = "schemeCode" name="schemeCode" caption="schemeCode" validation="txt.isrequired"  size="1" default ="${SchemeCode}" 	onblur="chkbillHeadunique();" maxlength="50" />
		</td>
	
	    <TD align="left" width = "15%">
		<b><fmt:message key="BHM.SCHEMENAME" bundle="${commonLables}"/></b></TD>
		<TD width ="25%">
	    <hdiits:text style="width:87%" mandatory="true" id = "schemeName" name="schemeName" caption="schemeName" validation="txt.isrequired"  size="1" default ="${SchemeName}"	 maxlength="200" />
	    </td>
	</tr>
	
	<tr></tr><tr></tr><tr></tr><tr></tr>

<tr>		
			
	<TD  align="left" class="Label" width="15%">
			<b><fmt:message key="PR.DEMAND" bundle="${commonLables}"/></b></TD>
	<td align="left" width ="25%">
	 <hdiits:select  style="width:90%" name="cmbDemand" id="cmbDemand" size="1" mandatory="true" caption="Demand No" captionid="DmdNo"
		validation="sel.isrequired" sort="false" onchange="GetMjrHeadNo();">
	   <hdiits:option value="-1">---------------------------Select----------------------------</hdiits:option>
	  			       <c:forEach items="${demandNoList}" var="demandNoList">
								<c:choose>
									<c:when test="${hrPayBillHeadMpg.demandNo==demandNoList.demandNo}">
										<option selected="true" value='<c:out value="${hrPayBillHeadMpg.demandNo}"/>' >
										<c:out  value="${demandNoList.demandNo}"/></option>
									</c:when>
									<c:otherwise>
										<option value='<c:out value="${demandNoList.demandNo}"/>'>
										<c:out value="${demandNoList.demandNo}"/></option>
									</c:otherwise>
								</c:choose>								
				    	</c:forEach>		
	 </hdiits:select>
	 </td>
	
	
	<TD align="left" width="15%"> 
		<b><fmt:message key="PR.MJRHEAD" bundle="${commonLables}"/></b></TD>
	<TD width ="25%">
	 <hdiits:select  style="width:90%" name="cmbMjrHead" caption="Major Head" captionid="MjrHeadNo" id="mjrHead" size="1" sort="false" mandatory="true" validation="sel.isrequired"  onchange="GetSubMjrHeadNo();">
	   <hdiits:option value="-1">---------------------------Select----------------------------</hdiits:option>
	  			       <c:forEach items="${mjrHeadList}" var="mjrHeadList">
								<c:choose>
									<c:when test="${hrPayBillHeadMpg.mjrHead==mjrHeadList.mjrHead}">
										<option selected="true" value='<c:out value="${hrPayBillHeadMpg.mjrHead}"/>' >
										<c:out  value="${mjrHeadList.mjrHead}"/></option>
									</c:when>
									<c:otherwise>
										<option value='<c:out value="${mjrHeadList.mjrHead}"/>'>
										<c:out value="${mjrHeadList.mjrHead}"/></option>
									</c:otherwise>
								</c:choose>								
				    	</c:forEach>		
	</hdiits:select>
	</TD>
	</tr>
<tr></tr><tr></tr><tr></tr><tr></tr>
	<tr>
	<TD align="left" class="Label" width="15%"> 
		<b><fmt:message key="PR.SUBMJRHEAD" bundle="${commonLables}"/></b></TD>
	<TD align="left" width ="25%">
	  <hdiits:select  style="width:90%" name="cmbSubMjrHead" id="cmbSubMjrHead" size="1" caption="Sub Major Head"
		 captionid="subMjrHeadNo"
		validation="sel.isrequired" mandatory="true" sort="false" onchange="GetMnrHeads();">
	    <hdiits:option value="-1">---------------------------Select----------------------------</hdiits:option>
	  			       <c:forEach items="${subMjrHeadList}" var="subMjrHeadList">
								<c:choose>
									<c:when test="${hrPayBillHeadMpg.subMjrHead==subMjrHeadList.subMjrHead}">
										<option selected="true" value='<c:out value="${hrPayBillHeadMpg.subMjrHead}"/>' >
										<c:out  value="${subMjrHeadList.subMjrHead}"/></option>
									</c:when>
									<c:otherwise>
										<option value='<c:out value="${subMjrHeadList.subMjrHead}"/>'>
										<c:out value="${subMjrHeadList.subMjrHead}"/></option>
									</c:otherwise>
								</c:choose>								
				    	</c:forEach>		
	  </hdiits:select>
	  </TD>
	
	
	<TD align="left" align="right" width ="15%"> 
		<b><fmt:message key="PR.MNRHEAD" bundle="${commonLables}"/></b></TD>
	<td width ="25%">
	 <hdiits:select  style="width:90%" name="cmbMnrHead" id="cmbMnrHead" size="1" sort="false"  caption="Minor Head" captionid="MnrHeadNo"
		mandatory="true" validation="sel.isrequired" onchange="GetSubHeads2();GetChange();">
	   <hdiits:option value="-1">---------------------------Select----------------------------</hdiits:option>
	  			       <c:forEach items="${minorHeadList}" var="minorHeadList">
								<c:choose>
									<c:when test="${hrPayBillHeadMpg.minorHead==minorHeadList.minorHead}">
										<option selected="true" value='<c:out value="${hrPayBillHeadMpg.minorHead}"/>' >
										<c:out  value="${minorHeadList.minorHead}"/></option>
									</c:when>
									<c:otherwise>
										<option value='<c:out value="${minorHeadList.minorHead}"/>'>
										<c:out value="${minorHeadList.minorHead}"/></option>
									</c:otherwise>
								</c:choose>								
				    	</c:forEach>		
	 </hdiits:select>
	 </td>
	</tr>
	<tr></tr><tr></tr><tr></tr><tr></tr>
	<tr>
	<TD align="left" width ="15%" >
		 <b><fmt:message key="PR.SUBHEAD" bundle="${commonLables}"/></b></TD>
	<td align="left" width ="30%"><hdiits:select name="head" id="cmbSubHead"  size="1" sort="false" style="width:90%" caption="Sub Head" captionid="OM.headName" mandatory="true" validation="sel.isrequired" onchange="GetHeadChargable();">
	  <hdiits:option value="-1">---------------------------Select----------------------------</hdiits:option>
	  			       <c:forEach items="${subHeadList}" var="subHeadList">
								<c:choose>
									<c:when test="${hrPayBillHeadMpg.subHeadId==subHeadList.subHeadId}">
										<option selected="true" value='<c:out value="${hrPayBillHeadMpg.subHeadId}"/>' >
										<c:out  value="${subHeadList.subHeadName}"/></option>
									</c:when>
									<c:otherwise>
										<option value='<c:out value="${subHeadList.subHeadId}"/>'>
										<c:out value="${subHeadList.subHeadName}"/></option>
									</c:otherwise>
								</c:choose>								
				    	</c:forEach>		
	 </hdiits:select>
	 </td>
	
	
	<TD align="left" width ="15%"> 
		<b><fmt:message key="PR.DTLHEAD" bundle="${commonLables}"/></b></TD>
	<td width ="30%"><hdiits:select name="cmbDtlHead" id="cmbDtlHead" size="1" sort="false" style="width:90%">
	
	  <hdiits:option value="-1">---------------------------Select----------------------------</hdiits:option>
	 </hdiits:select>
	 </td>
	 <tr></tr><tr></tr><tr></tr><tr></tr>
	<tr>
	
		<TD  align="left" width ="15%"  class="fieldLabel" >
		<b><hdiits:caption id="area" captionid="SH.SCHEMETYPE" bundle="${commonLables}"></hdiits:caption></b>
		</TD>

  	<td>
  	
  	<hdiits:radio name="SchmeType" id="Charged" readonly="${readOnly}"  value="Charged"   captionid="SH.CHARGED"  default ="${ChargeTypeName}" bundle="${commonLables}"   />
  	
    <hdiits:radio name="SchmeType" id="Voted"  readonly="${readOnly}" value="Voted"   captionid="SH.VOTED" default ="${ChargeTypeName}"  bundle="${commonLables}"    />
    </td>
		
		
		<TD  align="left" width ="15%"  class="fieldLabel" >
		<b><hdiits:caption id="area" captionid="SH.ISPLANED" bundle="${commonLables}"></hdiits:caption></b>
		</TD>

  	<td>
  	<hdiits:radio name="IsPlane" id="ece" readonly="${readOnly}"  value="Plan"   captionid="SH.PLAN" bundle="${commonLables}" default ="${PlanName}" />
  	
    <hdiits:radio name="IsPlane" id="Non-Plan"  readonly="${readOnly}" value="Non-Plan"   captionid="SH.NONPLAN"  bundle="${commonLables}" default ="${PlanName}"  />
    </td>
		
       
</tr>
	 
	 <tr>
		<td  align="left" width="16%"  ><b><fmt:message key="SH.HEADCHARGABLE" bundle="${commonLables}"/><b></td> 
			<td align="left" width="34%" ><hdiits:text style="width:250px"  id = "headChargable" name="headChargable" caption="charge."  maxlength="250" size="250" mandatory="false"  readonly="true"/></td>
	</tr>
	
</tr> 


<!-- Added By Varun For Multiple Add -->

 
	<hdiits:hidden name="Demand" id="Demand" default="document.frmPaybillPara.cmbDemand.value"/>
	<hdiits:hidden name="MjHd" id="MjHd" default="document.frmPaybillPara.cmbMjrHead.value"/>
	<hdiits:hidden name="SubMjHd" id="SubMjHd" default="document.frmPaybillPara.cmbSubMjrHead.value"/>
    <hdiits:hidden name="MnrHd" id="MnrHd" default="document.frmPaybillPara.cmbMnrHead.value"/>
  	<hdiits:hidden name="txtSbHd" id="txtSbHd" default="document.frmPaybillPara.cmbSubHead.value"/>
	
		
	<tr><td colspan="2">&nbsp;</td></tr>
	</TABLE>
	</div> 
 	

	<hdiits:hidden default="schemeViewListOnTheScreen" name="givenurl"/>

	<jsp:include page="../../core/PayTabnavigation.jsp" />
</div>
<script type="text/javascript">
		initializetabcontent("maintab");	


		if("${groupId}" != '')
		{
			document.billHeadMaster.SchmeType.value='${schemetype}';
		} 
	</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />


<input type="hidden" value="${budSubHeadId}" name="budSubHeadId">
</hdiits:form>
<script>
 
GetHeadChargable();
</script>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>


