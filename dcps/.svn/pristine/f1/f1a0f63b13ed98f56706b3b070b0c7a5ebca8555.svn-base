<%

try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page session="true" %>
<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>

<script type="text/javascript"  
    src="common/script/commonfunctions.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/hrms/eis/miscRecoverEdit.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="msg" value="${resValue.msg}" ></c:set>
<c:set var="actionList" value="${resValue.actionList}" ></c:set>
<c:set var="rltBillList" value="${resValue.rltBillList}" ></c:set>

<c:set var="updatePaybillFlg" value="${resValue.updatePaybillFlg}" ></c:set>
<c:set var="paybillMonth" value="${resValue.paybillMonth}" ></c:set>
<c:set var="paybillYear" value="${resValue.paybillYear}" ></c:set>
<c:set var="FromBasicDtlsNew" value="${resValue.FromBasicDtlsNew}" ></c:set>
<c:set var="otherId" value="${resValue.otherId}" ></c:set>

<script type="text/javascript" >
function reloadingParent()
{
        window.parent.opener.location="./hrms.htm?actionFlag=getOtherDataMerged&otherId=${otherId}&edit=Y&empAllRec=false&MergedScreen=YES&PreviewBill=YES";
        window.close();
}
</script>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>



<hdiits:form name="miscRecover" validate="true" method="POST"
	action="./hrms.htm?actionFlag=insertMiscData&edit=Y&miscId=${actionList.miscId}&FromBasicDtlsNew=${FromBasicDtlsNew}&otherId=${otherId}" encType="multipart/form-data">

	<div id="tabmenu">
		<ul id="maintab" class="shadetabs" compact="compact">
			<li class="selected"><a href="#" rel="tcontent1"><font size="2"><b><fmt:message key="MISC.miscUpdate" bundle="${commonLables}"/></b></a></li>
		</ul>
	</div>

	<div class="halftabcontentstyle">
	
<tr>
	     <c:if test="${updatePaybillFlg eq 'y'}" >
	     <a href="./hrms.htm?actionFlag=fillPaybillData&paybillYear=${paybillYear}&paybillMonth=${paybillMonth}&updatePaybillEmpId=${actionList.hrEisEmpMst.orgEmpMst.empId}&searchData=y">Back to update pay bill</a>
	     </c:if>
</tr>
	
	<div id="tcontent1" class="halftabcontent" tabno="0">
	<table width= "65%" align= "center"><br>
		
		
	
	    <tr>
			<td><b><hdiits:caption captionid="MISC.employee" bundle="${commonLables}"/></b></td>
			<td><hdiits:text name="employee" default="${actionList.hrEisEmpMst.orgEmpMst.empPrefix} ${actionList.hrEisEmpMst.orgEmpMst.empFname} ${actionList.hrEisEmpMst.orgEmpMst.empMname} ${actionList.hrEisEmpMst.orgEmpMst.empLname}" captionid="MISC.employee" bundle="${commonLables}"  validation="txt.isrequired"  maxlength="10"  readonly="true" size="20"/> </td>
	    </tr>
	    
	    
	    
	   <tr>
			<td><b><hdiits:caption captionid="MISC.amount" bundle="${commonLables}"/></b></td>
			<td><hdiits:number name="amount" default="${actionList.total_amount}" style="text-align:right" captionid="MISC.amount" bundle="${commonLables}"  validation="txt.isrequired,txt.isnumber"  maxlength="10"  mandatory="true" size="20" onblur="calculateAmt()"/> &nbsp;&nbsp;Recovered Amount : <c:out value="${actionList.tot_recv_amt}"/></td>
	    </tr>
	    
	    <tr>
			<td><b><hdiits:caption captionid="MISC.installment" bundle="${commonLables}"/></b></td>
<%--			<td><hdiits:number name="installment" default="${actionList.installment}" captionid="MISC.installment" bundle="${commonLables}"  validation="txt.isrequired,txt.isnumber"  maxlength="3"  mandatory="true" size="20" onblur="calculateAmt()"/> &nbsp;&nbsp;Recovered Installments : <c:out value="${actionList.tot_recv_inst}"/></td> --%>
			<td><hdiits:number name="installment" default="${actionList.installment}" captionid="MISC.installment" bundle="${commonLables}"  validation="txt.isrequired,txt.isnumber"  maxlength="3"  mandatory="true" size="20" /> &nbsp;&nbsp;Recovered Installments : <c:out value="${actionList.tot_recv_inst}"/></td>			
	    </tr>
	    
	    <tr>
			<td><b><hdiits:caption captionid="MISC.installmentAmt" bundle="${commonLables}"/></b></td>
			<td><hdiits:number name="installmentAmt" default="${actionList.recoverAmt}"  style="text-align:right" captionid="MISC.installmentAmt" bundle="${commonLables}"  validation="txt.isrequired,txt.isnumber"  maxlength="10" readonly="" mandatory="true" size="20" onblur="chkInstallmentAmt()"/>  </td>
	    </tr>
	    
	    
	    
	    <tr>
			<td><b><hdiits:caption captionid="MISC.reason" bundle="${commonLables}"/></b></td>
			<td><hdiits:textarea cols="50" rows="3" name="reason" default="${actionList.reason}" captionid="MISC.reason" bundle="${commonLables}"  validation="txt.isrequired" mandatory="true" onblur="checkSplChar(this)"  maxlength="100" /> </td>
	    </tr>
	    <fmt:formatDate var="miscDate" pattern="dd/MM/yyyy" value="${actionList.recoverDate}"/>
	    <tr>
			<td><b><hdiits:caption captionid="MISC.startDate" bundle="${commonLables}"/></b></td>
			<td><hdiits:text readonly="true" captionid="MISC.startDate" bundle="${commonLables}" name="date"  mandatory="true" default="${miscDate}" validation="txt.isrequired,txt.isdt" /></TD>	
		
		</tr>
		
				
		<tr>
			<td><b><hdiits:caption captionid="MISC.endDate" bundle="${commonLables}"/></b></td>
			<td><hdiits:dateTime captionid="MISC.endDate" bundle="${commonLables}" name="endDate"  onblur="cmpDate();" default="${actionList.recoverEndDate}" validation="txt.isdt" /></TD>	
		</tr>
		
		<tr>
		<TD class="fieldLabel" >
		<hdiits:caption captionid="MISC.recoveryType" bundle="${commonLables}"></hdiits:caption>
		</TD>
		<td><hdiits:select name="recoveryType" size="1"
		               id="recoveryType"
                       captionid="MISC.recoveryType"   validation="sel.isrequired"
                       mandatory="true" 
                       bundle="${commonLables}" onchange="" sort="false">
        <hdiits:option value="">----Select----</hdiits:option>
        
		 <c:forEach var="rltBillList" items="${rltBillList}">
		 <c:choose>
		 <c:when test="${rltBillList.typeEdpId == actionList.edpTypeId}">
         	<hdiits:option value="${rltBillList.typeEdpId}" selected="true"><c:out value="${rltBillList.edpShortName}(${rltBillList.edpCode})"> </c:out></hdiits:option>
         </c:when>
         <c:otherwise>
         	<hdiits:option value="${rltBillList.typeEdpId}"><c:out value="${rltBillList.edpShortName}(${rltBillList.edpCode})"> </c:out></hdiits:option>
         </c:otherwise>
         </c:choose>
         </c:forEach>
         
		 </hdiits:select > 
		 
		  <tr>
			<td><hdiits:caption captionid="EL.activateFlag" bundle="${commonLables}"/></td>
			<td>
			
			<c:choose>
				<c:when test="${actionList.miscActivateFlag eq 1}" >
					<hdiits:radio captionid="OT.YES"  caption="YES" bundle="${commonLables}" onclick="updateActivate(this)" value="1" default="1" name="radActivateFlag" id="radActivateFlag" />
					<hdiits:radio captionid="OT.NO" caption="NO" bundle="${commonLables}" onclick="updateActivate(this)" value="0"   name="radActivateFlag" id="radActivateFlag"/>
				</c:when> 
				<c:otherwise>
					<hdiits:radio captionid="OT.YES"  caption="YES" bundle="${commonLables}" onclick="updateActivate(this)" value="1"  name="radActivateFlag"  />
					<hdiits:radio captionid="OT.NO" caption="NO" bundle="${commonLables}" onclick="updateActivate(this)" value="0" default="0" name="radActivateFlag" />
				</c:otherwise>
     		</c:choose>
			
			<hdiits:hidden name="miscActivateFlag" default="${actionList.miscActivateFlag}"/>
			
			
			
			</TD>	
		</tr> 
	     	     <TR>
	     	     <td colspan="10"><b>Order Upload:-</b>
					   <jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
        	    	    	<jsp:param name="attachmentName" value="orderId" />
            	    		<jsp:param name="formName" value="miscRecover" />
	            	    	<jsp:param name="attachmentType" value="Document" />
				    		<jsp:param name="multiple" value="N" />  
				    		<jsp:param name="mandatory" value="N"/>              
	    				</jsp:include>
	</td></tr>	
	</table>
 	</div>
 	 	<hdiits:hidden default="getMiscData" name="givenurl"/>
 <fmt:setBundle basename="resources.payroll.payrollLables_en_US" var="Lables" scope="request"/> 		
	<jsp:include page="../../core/PayTabnavigation.jsp" />
	</div>
	<br>		
		 <c:if test="${FromBasicDtlsNew ne null && not empty FromBasicDtlsNew}">
			<center>
			<hdiits:button type="button" name="closewindow" value="closewindow" caption="Close Window" 
			id="closewindow" captionid="close window" onclick="window.close()"/>
			</center>
		</c:if>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		if("${msg}"!=null&&"${msg}"!='')
		{
			alert("${msg}");
			if("${FromBasicDtlsNew}" != null && "${FromBasicDtlsNew}" == "YES")
			{
				reloadingParent();
			}
			var url="./hrms.htm?actionFlag=getMiscData";
			document.miscRecover.action=url;
			document.miscRecover.submit();
		}
		
	</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

