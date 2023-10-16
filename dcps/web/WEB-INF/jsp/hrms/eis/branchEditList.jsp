<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ page import ="com.tcs.sgv.eis.valueobject.HrEisBranchMst, 
                  com.tcs.sgv.eis.valueobject.HrEisBankMst,
				  java.text.SimpleDateFormat" %>
 <script type="text/javascript" src="<c:url value="/script/hrms/eis/Address.js"/>">
</script>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/commonUtils.js"/>">
</script>	
	<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="actionList" value="${resValue.actionList}" > </c:set> 
<c:set var="bankList" value="${resValue.bankList}" />
<c:set var="bankid" value="${resValue.bankid}" />
<c:set var="micrCode" value="${resValue.micrCode}" />






<hdiits:form name="frmBF" validate="true" method="POST"
	action="hrms.htm?actionFlag=insertBranchData&edit=Y" encType="multipart/form-data">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="BR.UPDATEBRANCHINFO" bundle="${commonLables}"/></b></a></li>
	</ul>
	</div>
	
	

	<div  id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin">    
   <TABLE width="80%" align="center"><br/>
 	<TR> 
 	<td  width="2%"></td>
 	<TD   align="left" width="8%">
 		<b><fmt:message key="BM.bankName" bundle="${commonLables}"/></b> 
 	</TD>		
	<TD  width="20%">
		<hdiits:select name="cmbBankName" default="${bankid}" validation="sel.isrequired"  id="bankID" sort="false"  mandatory="true" 
			size="1" caption="Bank Name" onchange="clearBranch()">
			<hdiits:option>-------------------Select-------------------</hdiits:option>
			
			<c:forEach items ="${resValue.bankList}" var="list">
			<c:choose>
			<c:when test="${list.bankCode == actionList.bankCode}">
			  <% //System.out.println("In If Loop Bank Name");			  %>
 			   <hdiits:option  value="${list.bankId}" selected="true">${list.bankName}</hdiits:option>
			  </c:when>
			  <c:otherwise>
			  <% //System.out.println("In Bank Name else Loop"); %>
			<hdiits:option value="${list.bankId}"> ${list.bankName} </hdiits:option>
			</c:otherwise>
			</c:choose>
			</c:forEach>
			</hdiits:select>
			 <hdiits:hidden  default ="${actionList.branchId}" name="txtBranchID" caption="Branch ID"  />
	</TD>	
			<td  width="2%"></td>
			 <TD  align="left" width="8%"><b><fmt:message key="BR.NAME" bundle="${commonLables}"/></b>
			 </TD>
		<TD>			 
			<hdiits:text name="txtBranchName" default ="${actionList.branchName}" caption="Branch Name"  validation="txt.isrequired" id="branchName" size="30" maxlength="40"  mandatory="true"	 />	
		</TD>	
				  <hdiits:hidden id="bankname" name="bankname" default="${actionList.branchName}"/>
	</TR><tr></tr><tr></tr>
	
	
	
	<TR><td  width="2%"></td>
			<TD  align="left" width="8%"><b><fmt:message key="BR.CODE" bundle="${commonLables}"/></b></TD>
			<TD  width="20%"> <hdiits:number	name="txtBranchCode"  mandatory="true" default ="${actionList.branchCode}"	validation="txt.isrequired,txt.isnumber"	caption="Code"  maxlength="20" size="30" />
			</TD>
					 
			<td  width="2%"></td>
			<TD  align="left" width="8%"><b><fmt:message key="BR.ADD" bundle="${commonLables}"/></b>
			</TD>
			<TD>
				<hdiits:textarea  rows="3" cols="32" name="txtBranchAdd" maxlength="190" caption ="Address" default ="${actionList.branchAddress}">
				</hdiits:textarea>
			</TD>
				 
			
</tr><tr></tr><tr></tr>
	<TR>
			<td  width="2%"></td>
			<TD  align="left" width="8%"><b><fmt:message key="BR.MICR" bundle="${commonLables}"/></b>
			</TD>
			
		<TD  width="20%">
		
		 <hdiits:number	name="txtMicrCode" default ="${micrCode}" mandatory="true"  caption="MICR Code" validation="txt.isrequired,txt.isnumber" maxlength="20" size="30" />
	   
	   </TD>	
				
		
	</TR>

	</table> 
    
 <br/><br/><br/>
 
<hdiits:hidden default="getBranchView" name="givenurl"/> 
	
 
<fmt:setBundle basename="resources.payroll.payrollLables_en_US" var="Lables" scope="request"/>
	<jsp:include page="../../core/PayTabnavigation.jsp" />
	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
				document.frmBF.cmbBankName.focus();
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

