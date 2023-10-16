<%try{ %>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/address.js"/>"></script>	
<script type="text/javascript" src="<c:url value="script/common/tabcontent.js"/>"></script>		
<script type="text/javascript" src="script/hrms/ess/reimb/hr_remb_validations.js"></script>
<script type="text/javascript" src="script/hrms/ess/reimb/hrEssReimburseOffice.js"></script>
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="admissible_amt" value="${resValue.admissible_amt}" ></c:set>
<c:set var="max_no_of_newspaper" value="${resValue.max_no_of_newspaper}"></c:set>
<c:set var="max_call_limit" value="${resValue.max_call_limit}"></c:set>
<c:set var="cmnLookupMst" value="${resValue.cmnLookupMst}"></c:set>
<c:set var="childLocationList" value="${resValue.childLocationList}"></c:set>
<c:set var="jspPath" value="${resValue.jspPath}"></c:set>
<c:set var="currentDate" value="${resValue.currentDate}"></c:set>
<c:set var="billType" value="${resValue.billType}"></c:set>
<c:set var="billNo" value="${resValue.billNo}"></c:set>
<c:set var="billDate" value="${resValue.billDate}"></c:set>
<c:set var="billAmount" value="${resValue.billAmount}"></c:set>
<c:set var="locationCode" value="${resValue.locationCode}"></c:set>
<c:set var="remarks" value="${resValue.remarks}"></c:set>
<c:set var="newsPaperList" value="${resValue.newsPaperList}"></c:set>
<c:set var="magazineList" value="${resValue.magazineList}"></c:set>
<c:set var="contactTypeList" value="${resValue.contactTypeList}"></c:set>
<c:set var="agencyList" value="${resValue.agencyList}"></c:set>
<c:set var="propertyTypeList" value="${resValue.propertyTypeList}"></c:set>
<c:set var="tdBGColor" value="#C9DFFF"></c:set>
<c:set var="tableBGColor" value="#F0F4FB"></c:set>

<fmt:setBundle basename="resources.ess.reimb.reimbLables" var="enLables" scope="request"/>


<script type="text/javascript">

var alertmsg = '<fmt:message bundle="${enLables}" key="RM.plsInsertBill" />';

</script>

<hdiits:form name="Reimburse" validate="true" method="POST" action="./hrms.htm?actionFlag=InsertOfficedtls"  encType="multipart/form-data">
<div id="tabmenu">
		<ul id="maintab" class="shadetabs" compact="compact">
		<li class="selected">
		<a href="#" rel="tcontent1"><b>
		<hdiits:caption captionid="RM.CreateReq" bundle="${enLables}"></hdiits:caption></b></a>
		</li> 
	</ul>
	</div>
	
	<div class="tabcontentstyle">
		<div id="tcontent1" class="tabcontent" tabno="0">
				<%@ include file="../../eis/empInfo/EmployeeInfo.jsp"%>
				
	<table align="center" width="100%">
		   
			 <tr bgcolor="#386CB7">
					<td class="fieldLabel" colspan="4" align="center">
					<font color="#ffffff"><strong><u>					
					<fmt:message bundle="${enLables}" key="RM.BillDetails"/>
					</u></strong></font></td>
				</tr>
			
			<tr>
				<td class="fieldLabel" width="25%">
				    <hdiits:caption captionid="RM.Rtype" bundle="${enLables}"/>
		    	</td>
		    
		    	<td class="fieldLabel" width="25%">
			    	<hdiits:select name="reimbtype" id="reimbtype" captionid="RM.Rtype"  bundle="${enLables}" validation="sel.isrequired" onchange="getBillTypeDetail(this)" sort="false" mandatory="true">
								<hdiits:option value="Select"><fmt:message bundle="${enLables}" key="RM.Select"/></hdiits:option>
				    	<c:forEach items="${cmnLookupMst}" var="bType">
								<c:if test="${bType.lookupName ne 'Property_Bill' }">
								<hdiits:option value="${bType.lookupName}">${bType.lookupDesc}</hdiits:option>
					   			</c:if>
					   	</c:forEach>
					</hdiits:select>
              
			 	</td>
			  
		     	<td class="fieldLabel" width="25%">
  		     		    <hdiits:caption captionid="RM.ApplyDate" bundle="${enLables}"/>  	
				</td>  		     				    	     
		    
  		     	<td class="fieldLabel" width="25%">
  		     		<hdiits:caption captionid="${currentDate}" caption="${currentDate}"/>
				    <hdiits:hidden name="txtappdate" caption="${currentDate}" id="txtappdate"/>  		     	
  		     	</td>
		    
			</tr>
			
			<tr>
				<td class="fieldLabel" width="25%">
			    	<hdiits:caption captionid="RM.BillNo" bundle="${enLables}"/>
		    	</td>
		    
		    	<td class="fieldLabel" width="25%">
			    	<hdiits:text  name="txtbno" id="txtbno" captionid="RM.BillNo" bundle="${enLables}" validation="txt.isnumber,txt.isrequired"  size="17" maxlength="16" mandatory="true" default="${billNo}"></hdiits:text>
				</td>
			  
		     	<td class="fieldLabel" width="25%">
			     	<hdiits:caption captionid="RM.BillDate" bundle="${enLables}"/>
  		     	</td>
  		     
  		     	<td class="fieldLabel" width="25%">
  		     		<hdiits:dateTime name="dtbilldate"  captionid="RM.BillDate" bundle="${enLables}" validation="txt.isdt,txt.isrequired" mandatory="true"></hdiits:dateTime>
		     	</td>
		    	<script type="text/javascript">
		    			document.getElementById('dtbilldate').value = '${billDate}';
		    	</script>
			</tr>
			
			<tr>
				<td class="fieldLabel" width="25%">
			    	<hdiits:caption captionid="RM.Amt" bundle="${enLables}"></hdiits:caption>
		    	</td>
		    
		    	<td class="fieldLabel" width="25%">
			    	<hdiits:text name="txtamount" default="${billAmount}"  maxlength="7"  id="txtamount" captionid="RM.Amt" bundle="${enLables}" size="17" validation="txt.isrequired,txt.isflt"  mandatory="true"  />
			  	</td>
			  
		     	<td class="fieldLabel" width="25%">
		     		<hdiits:caption captionid="RM.Office" bundle="${enLables}"/>
		     	</td>
  		     
  		     	<td class="fieldLabel" width="25%">
  		     		<hdiits:select name="selLocation" id="selLocation" captionid="RM.Office" bundle="${enLables}" mandatory="true" validation="sel.isrequired" sort="false">
  		     			<hdiits:option value="Select"><fmt:message bundle="${enLables}" key="RM.Select"/></hdiits:option>
  		     				<c:forEach items="${childLocationList}" var="childLocation">
  		     					<hdiits:option value="${childLocation.locationCode}">${childLocation.locName}</hdiits:option>
				    		</c:forEach>
				    		
  		     		</hdiits:select>
  		     		
  		     	</td>
			</tr>
		</table>
		<table align="center" width="100%">
		<c:if test="${billType eq 'Canteen_Bill' or billType eq 'Miscellenous_Bill' or billType eq 'Select'}"></c:if>
		<c:if test="${billType eq 'Telephone_Bill'}">
			<jsp:include page="../../ess/reimb/TelephoneBill.jsp" >
			<jsp:param name="contactTypeList" value="${contactTypeList}"/>
			</jsp:include>
		</c:if>
		
		<c:if test="${billType eq 'NewsPaper_Bill'}">
			
			<jsp:include page="../../ess/reimb/NewspaperBill.jsp" >
			<jsp:param name="agencyList" value="${agencyList}"/>
			<jsp:param name="newsPaperList" value="${newsPaperList}"/>
			<jsp:param name="magazineList" value="${magazineList}"/>
			</jsp:include>
		</c:if>
		
		<c:if test="${billType eq 'Electricity_Bill'}">
			<jsp:include page="../../ess/reimb/ElectricityBill.jsp" ></jsp:include>
		</c:if>
		
		<c:if test="${billType eq 'Property_Bill'}">
			<jsp:include page="../../ess/reimb/PropertyBill.jsp" >
			<jsp:param name="propertyTypeList" value="${propertyTypeList}"/>
			</jsp:include>
		</c:if>
		
		<tr>
		<td class="fieldLabel" width="25%"><hdiits:caption captionid="RM.Remarks" bundle="${enLables}"/></td>
		<td class="fieldLabel" width="25%"><hdiits:textarea maxlength="240" cols="30" id="txtremarks" name="txtremarks" default="${remarks}" captionid="RM.Remarks" bundle="${enLables}" rows="3" onkeypress="return validateTextArea(event,false)" ></hdiits:textarea></td>
		<td class="fieldLabel" width="25%"></td>
		<td class="fieldLabel" width="25%"></td>
		</tr>
		<tr>
			     <td class="fieldLabel" width="100%" colspan="4">
				   <jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
            	    	<jsp:param name="attachmentName" value="bill" />
                		<jsp:param name="formName" value="Reimburse" />
	                	<jsp:param name="attachmentType" value="Document" />
				    	<jsp:param name="mandatory" value="Y"/>              
    				</jsp:include>
            	</td>
            	
			</tr>

			<tr>
				<td class="fieldLabel" align="center" colspan="4">
					<hdiits:button name="btnadd" type="button" caption="Add" onclick="addBill()"/>
				</td>
				
			</tr>
						
		 </table>
		
		 <table width="100%"  id="officeDetails" align="center" style="border-collapse: collapse; display:none; background-color:${tableBGColor}" border=1 borderColor="black" class="datatable"  >
			<tr class="datatableheader" style="background-color:${tdBGColor}" align="center" >
				<td><hdiits:caption captionid="RM.SrNo" bundle="${enLables}" /></td>
				<td><hdiits:caption captionid="RM.BillNo" bundle="${enLables}" /></td>
				<td><hdiits:caption captionid="RM.BillDate" bundle="${enLables}" /></td>
				<td><hdiits:caption captionid="RM.Amt" bundle="${enLables}"/></td>
				<td><hdiits:caption captionid="RM.Location" bundle="${enLables}"/></td>
				
				<c:if test="${billType eq 'Telephone_Bill' or billType eq 'Electricity_Bill' or billType eq 'NewsPaper_Bill'}">
				
					<td><hdiits:caption captionid="RM.Fromdate" bundle="${enLables}" /></td>
					<td><hdiits:caption captionid="RM.Todate" bundle="${enLables}" /></td>
				
				</c:if>
				
				<c:if test="${billType eq 'Telephone_Bill'}">
					
					<td><hdiits:caption captionid="RM.contactType" bundle="${enLables}"/></td>
					<td><hdiits:caption captionid="RM.contactNo" bundle="${enLables}"/></td>
					<td><hdiits:caption captionid="RM.noOfCall" bundle="${enLables}"/></td>
				
				</c:if>
				
				<c:if test="${billType eq 'Telephone_Bill' or billType eq 'Electricity_Bill'}">
					
					<td><hdiits:caption captionid="RM.billDueDt" bundle="${enLables}"/></td>
				
				</c:if>
				
				<c:if test="${billType eq 'NewsPaper_Bill'}">
					
					<td><hdiits:caption captionid="RM.agencyName" bundle="${enLables}"/></td>
					<td><hdiits:caption captionid="RM.scrapItem" bundle="${enLables}" /></td>
					<td><hdiits:caption captionid="RM.admAmt" bundle="${enLables}" /></td>
					<td><hdiits:caption captionid="RM.newsPaper" bundle="${enLables}" /></td>
					<td><hdiits:caption captionid="RM.magazine" bundle="${enLables}" /></td>
				</c:if>
				
				<c:if test="${billType eq 'Electricity_Bill'}">
					
					<td><hdiits:caption captionid="RM.meterNo" bundle="${enLables}"/></td>
					<td><hdiits:caption captionid="RM.noOfUnit" bundle="${enLables}"/></td>
				
				</c:if>
				
				<c:if test="${billType eq 'Property_Bill'}">
					
					<td><hdiits:caption captionid="RM.nameOfProperty" bundle="${enLables}"/></td>
					<td><hdiits:caption captionid="RM.ownerOfProperty" bundle="${enLables}"/></td>
					<td><hdiits:caption captionid="RM.typeOfProperty" bundle="${enLables}"/></td>
					<td><hdiits:caption captionid="RM.taxPaidOfYear" bundle="${enLables}"/></td>
					<td><hdiits:caption captionid="RM.taxInvoiceNo" bundle="${enLables}"/></td>
					<td><hdiits:caption captionid="RM.dueDtOfPay" bundle="${enLables}"/></td>
					<td><hdiits:caption captionid="RM.actualDtOfPay" bundle="${enLables}"/></td>
					<td><hdiits:caption captionid="RM.taxAmount" bundle="${enLables}"/><br>
						<font size="1px"><fmt:message bundle="${enLables}" key="RM.paidBeforeDueDt"></fmt:message></font>
					</td>
					<td><hdiits:caption captionid="RM.taxAmount" bundle="${enLables}"/><br>
						<font size="1px"><fmt:message bundle="${enLables}" key="RM.paidAfterDueDt"></fmt:message></font>
					</td>
				
				</c:if>
				
				<td><hdiits:caption captionid="RM.Remarks" bundle="${enLables}"/></td>
				<td><hdiits:caption captionid="RM.action" bundle="${enLables}"/></td>
			</tr>
		</table>		
	
	
	</div>
	
	<hdiits:hidden name="srNo" id="srNo" default="1"/>
	<hdiits:hidden name="newspaperList" id="newspaperList"/>
	<hdiits:hidden name="magazineList" id="magazineList"/>
	<hdiits:hidden name="billType" id="billType"/>
	
	<script type="text/javascript">

	document.getElementById('reimbtype').value = '${billType}';
	document.getElementById('selLocation').value = '${locationCode}';
	var navDisplay=false;
	</script>

	<hdiits:jsField jsFunction="checkContentExist()" name="checkContentExist"/>
 	<jsp:include page="../../../core/tabnavigation.jsp" >
 	<jsp:param name="disableReset" value="true"/>
 	<jsp:param name="closeWindow" value="no"/>
 	<jsp:param name="closeURL" value="hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome" />
 	</jsp:include>
  	
  	<hdiits:validate controlNames="text" locale='<%=(String)session.getAttribute("locale")%>' />
  	</div>
  	
	<script type="text/javascript">
		initializetabcontent("maintab")
	</script>


<hdiits:hidden name="intiatorFlag" default="office" />


</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
				