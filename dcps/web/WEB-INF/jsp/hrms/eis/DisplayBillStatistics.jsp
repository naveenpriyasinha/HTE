<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>


<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="totalRecords" value="${resValue.totalRecords}"></c:set>
<c:set var="totalRecordsMst" value="${resValue.totalRecordsMst}"></c:set>
<c:set var="totalRecordsBill" value="${resValue.totalRecordsBill}"></c:set>
<c:set var="billNoList" value="${resValue.billNoList}"></c:set>
<c:set var="MasterPostCountList" value="${resValue.lPostCountList}"></c:set>
<c:set var="BillPostCountList" value="${resValue.lBillPostCountList}"></c:set>

<html>
<head>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/base64.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script>
function chkEmpty(ctrl,msg)
{
	var str = ctrl.value;
	if(str=="" || str == "-1")
	{
		alert(msg +" Cannot be Empty.");
		ctrl.focus();		
		return false;
	}		
	else
		return true;
}
function fetchPostListUnderOrder()
{
	
	if(chkEmpty(document.getElementById('cmbBillGroup'),'Bill Group') ){
		displayLists();		
	}
	
}
function displayLists()
{
	var billGroupIdPassed = document.getElementById("cmbBillGroup").value ;
	url = "ifms.htm?actionFlag=loadBillStatisticsData&billGroupIdPassed="+billGroupIdPassed;
	self.location.href = url ;
	
}

function checkSuccess()
{
	if("${msg}"!='')
	{
		alert('${msg}');
		url = "ifms.htm?actionFlag=loadRenewalOfPost";
		self.location.href = url ;
	}
}
function ReturnLoginPage()
{
	self.location.href = "ifms.htm?actionFlag=validateLogin";
}
</script>
</head>

<body>
<hdiits:form name="frmBillStatistics" action="" id="frmBillStatistics" encType="multipart/form-data" validate="true" method="post">
<fieldset class="tabstyle">
<legend>
	<b><fmt:message key="NG.BillGroup" bundle="${commonLables}"></fmt:message></b>
</legend>
<c:set var="totalPosts" value="0"></c:set>
<div style="padding-left:175px">
<table width="70%" align="center" >
		<tr>
			<td align="left" style="width:20%"><fmt:message key="PR.BILLNO"
							bundle="${commonLables}"></fmt:message>
							</td>
			<td align="left" style="width:80%">
								<select name="cmbBillGroup" id="cmbBillGroup"  onChange="" style="width:80%">
													<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
														<c:forEach var="billgroup" items="${resValue.billNoList}" >
																			<c:choose>
																					<c:when test="${resValue.billGroupIdPassed == billgroup[0]}">
																					<option value="${billgroup[0]}" selected="selected" ><c:out 
																												value="${billgroup[1]}"></c:out></option>
																					</c:when>
																					<c:otherwise>
																					<option value="${billgroup[0]}" title="${billgroup[1]}"><c:out 
																												value="${billgroup[1]}"></c:out></option>
																					</c:otherwise>
																			</c:choose>
																			
														</c:forEach>
								</select>
								<label class="mandatoryindicator">*</label>	
			</td>
		</tr>
		<tr/>
		<tr/>
		
			
		
</table>
</div>
<br/>
<div align="center">
			<hdiits:button name="btnGo" id="btnGo" type="button" captionid="PC.GO" bundle="${commonLables}" onclick="fetchPostListUnderOrder()" />
			<hdiits:button	name="btnBackWithGo" id="btnBackWithGo" type="button" captionid="eis.back" bundle="${commonLables}" onclick="ReturnLoginPage();" />
</div>

<br/>
</fieldset >
<c:if test="${(resValue.billGroupIdPassed != 0)}">
<fieldset class="tabstyle">
<legend>
	<b><fmt:message key="eis.Statistics" bundle="${commonLables}"></fmt:message></b>
</legend>
<br/><br/>
<div style="padding-left:140px;padding-right: 140px">
	<fieldset class="tabstyle">
	<legend>
	<b>Comparison of Posts And Users (Master Table Against Paybill Table)</b>
	</legend>		
	<br/>
	<table width="50%" align="center" border="0" rules="none">
		<tr>
			<td align="Center" style="width:15%;font-size:12px"><b>Posts In PSR Table</b></td>
			<td align="Center" style="width:5%"><b>${resValue.lPsrPostCount}</b></td>
			<td style="width:10%"/>
			<td align="Center" style="width:15%;font-size:12px"><b>Posts In Paybill Table</b></td>
			<td align="Center" style="width:5%"><b>${resValue.lBillPostCount}</b></td>
		</tr>
		<tr/><tr/>
		<tr>
			<td align="Center" style="width:15%"><b>Users In UserPost Table</b></td>
			<td align="Center" style="width:5%"><b>${resValue.lUserPostCount}</b></td>
			<td style="width:10%"/>
			<td align="Center" style="width:15%"><b>Users In Paybill Table</b></td>
			<td align="Center" style="width:5%"><b>${resValue.lBillUserCount}</b></td>
		</tr>
		<tr/><tr/>
		<tr>
			<td align="Center" style="width:15%"><b>Post End Cases</b></td>
			<td align="Center" style="width:5%"><b>${resValue.lPostEndCount}</b></td>
			<td style="width:10%"/>
			<td align="Center" style="width:15%"><b>Users Service End Cases</b></td>
			<td align="Center" style="width:5%"><b>${resValue.lUserServEndCount}</b></td>
		</tr>
	</table>
	<br/>
	</fieldset>
</div>
<br/><br/>
<div style="padding-left:140px;padding-right: 140px">
	<fieldset class="tabstyle">
	<legend>
	<b>Designationwise Post Segregation (Master Table Against Paybill Table)</b>
	</legend>	
	<br/>
	<table width="70%" align="center">
		<tr>
			<td>
				<c:choose>
         			<c:when test="${MasterPostCountList != null}">
         				<div style="width:100%;overflow:auto;height:400px;" >
         						<display:table list="${MasterPostCountList}" id="tablePost" requestURI=""  export="false" style="width:100%" partialList="true" 
								 offset="1" excludedParams="ajax" sort="external" defaultsort="1" defaultorder="ascending" cellpadding="5" size="${resValue.totalRecordsMst}">
								 <display:setProperty name="paging.banner.placement" value="bottom"/>
									 <display:column class="tablecelltext" style="text-align:center" headerClass="datatableheader" title= "Designation" sortable="" >
										 <label id="desig"><b>${tablePost[0]}</b></label>
				        			</display:column>
			            			<display:column style="text-align: Center;" class="tablecelltext" title="Post Count(PSR)" headerClass="datatableheader" sortable=""> 		
			           					<label id="count"><b>${tablePost[1]}</b></label>     
			           				</display:column>
								 </display:table>
         				</div>
         			</c:when>
         			<c:otherwise>
         						<table id="tablePost" title="" border="2" bordercolor="gray" cellpadding="3"  width="85%" >
             						<tr>
             							<td width="20%" align="center" >Designation</td>
             							<td width="80%" align="center" style="color: rgb(200,100,20);font-size: small;font-style: normal" ><b><u>Post Count(PSR)</u></b></td>
             						</tr>
             					</table>
         			</c:otherwise>
         		</c:choose>
			</td>
			<td>
				<c:choose>
         			<c:when test="${BillPostCountList != null}">
         				<div style="width:100%;overflow:auto;height:400px;" >
         						<display:table list="${BillPostCountList}" id="tableBillPost" requestURI=""  export="false" style="width:100%" partialList="true" 
								 offset="1" excludedParams="ajax" sort="external" defaultsort="1" defaultorder="ascending" cellpadding="5" size="${resValue.totalRecordsBill}">
								 <display:setProperty name="paging.banner.placement" value="bottom"/>
									 <display:column class="tablecelltext" style="text-align:center" headerClass="datatableheader" title= "Designation" sortable="" >
										 <label id="desigBill"><b>${tableBillPost[0]}</b></label>
				        			</display:column>
			            			<display:column style="text-align: Center;" class="tablecelltext" title="Post Count(Bill)" headerClass="datatableheader" sortable=""> 		
			           					<label id="countBill"><b>${tableBillPost[1]}</b></label>     
			           				</display:column>
								 </display:table>
         				</div>
         			</c:when>
         			<c:otherwise>
         						<table id="tableBillPost" title="" border="2" bordercolor="gray" cellpadding="3"  width="85%" >
             						<tr>
             							<td width="20%" align="center" >Designation</td>
             							<td width="80%" align="center" style="color: rgb(200,100,20);font-size: small;font-style: normal" ><b><u>Post Count(Bill)</u></b></td>
             						</tr>
             					</table>
         			</c:otherwise>
         		</c:choose>
			
			</td>
		</tr>		
	</table>
	<br/>
	</fieldset>
</div>
</fieldset>
</c:if>
</hdiits:form>
</body>
