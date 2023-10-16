<%
	try {
%> 
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@page session="true" %>
<%@page import="com.tcs.sgv.common.utils.StringUtility"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Calendar"%><html>




<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="orderList" value="${resValue.orderList}"></c:set>
<c:set var="postExpiryList" value="${resValue.postExpiryList}"></c:set>
<c:set var="currentOrderDate" value="${resValue.currentOrderDate}"></c:set>
<c:set var="currentOrderName" value="${resValue.currentOrderName}"></c:set>
<c:set var="postList" value="${resValue.postList}"></c:set>
<c:set var="postStartDate" value="${resValue.postStartDate}"></c:set>
<c:set var="msg" value="${resValue.msg}"></c:set>


<head>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<fmt:setBundle basename="resources.eis.eisLables_en_US"	var="adminCreatePostLabel" scope="request" />
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
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
	if(chkEmpty(document.getElementById('cmbOrder'),'Order') ){
		document.frmRenewPost.btnGo.disabled=true;
		displayLists();		
	}
	
}
function displayLists()
{

	var oldOrderId = document.getElementById("cmbOrder").value ;
	url = "ifms.htm?actionFlag=loadRenewalOfPost&oldOrderId="+oldOrderId;
	self.location.href = url ;
	
}

function checkRenewalDataBeforeSubmit()
{
	var counterPost = document.getElementById("counterPost").value ;
	var renewalStartDate = document.getElementById("renewalStartDate").value;
	var renewalEndDate = document.getElementById("renewalEndDate").value;
	var renewalPostStartDate = document.getElementById("renewalPostStartDate").value;
	
	
	for(var i=1;i<=counterPost;i++)
	{
		if(document.getElementById("GroupCheck"+i).checked)
		{
			document.getElementById("postIdsToBeAttached").value = document.getElementById("postIdsToBeAttached").value +  document.getElementById("GroupCheck"+i).value + "~" ;
		}
	}
	var postIdsToBeAttached = document.getElementById("postIdsToBeAttached").value;
	
	if(chkEmpty(document.getElementById('cmbNewOrder'),'Order') && chkEmpty(document.getElementById('renewalEndDate'),'Renewal End Date')){
		var newOrderId = document.getElementById('cmbNewOrder').value
		//alert('Inside DisplayList'+postIdsToBeAttached);	
		var isApproved = confirm('Are you sure , You want to Save the Details ? ');
		if(!isApproved)
		{
			return;
		}
		url = "ifms.htm?actionFlag=loadRenewalOfPost&newOrderId="+newOrderId+"&postIdsToBeAttached="+postIdsToBeAttached+"&renewalStartDate="+renewalStartDate+"&renewalPostStartDate="+renewalPostStartDate;
		url+= "&renewalEndDate="+renewalEndDate;
		self.location.href = url ;
	}
	
}

function getRenewalOrderDate()
{
	var fetchDateFlag = "true";
	if(chkEmpty(document.getElementById('cmbNewOrder'),'Order'))
	{
		var newOrderId = document.getElementById('cmbNewOrder').value

		var uri="ifms.htm?actionFlag=loadRenewalOfPost";
		var url = "newOrderId="+newOrderId+"&fetchDateFlag="+fetchDateFlag;
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters: url ,
			        onSuccess: function(myAjax) {
					getDataStateChangedForRenewalPostDate(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...');} 
			          } );
	}
}


function getDataStateChangedForRenewalPostDate(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var renewalOrderDate = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	//alert('renewalOrderDate--'+renewalOrderDate);
	if (renewalOrderDate != null) {
		document.getElementById("renewalStartDate").value=renewalOrderDate;
	}
}

function getSelectedOrderDate()
{
	var fetchDateFlag = "true";
	if(chkEmpty(document.getElementById('cmbOrder'),'Order'))
	{
		var oldOrderId = document.getElementById('cmbOrder').value

		var uri="ifms.htm?actionFlag=loadRenewalOfPost";
		var url = "oldOrderId="+oldOrderId+"&fetchDateFlag="+fetchDateFlag;
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters: url ,
			        onSuccess: function(myAjax) {
					getDataStateChangedForSelectedOrderDate(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...');} 
			          } );
	}
}


function getDataStateChangedForSelectedOrderDate(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var selectedOrderDate = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	//alert('renewalOrderDate--'+renewalOrderDate);
	if (selectedOrderDate != null) {
		document.getElementById("selectedOrderDate").value=selectedOrderDate;
	}
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
	document.frmRenewPost.action='./hrms.htm?actionFlag=showAdminPostDtl&elementId=9000219';
	document.frmRenewPost.submit();
}



</script>

</head>
<body>
	<hdiits:form name="frmRenewPost"	action="hrms.htm?actionFlag=SubmitRenewPostData" validate="true" id="renewPost"
	method="post" encType="multipart/form-data" >
	
	<fieldset class="tabstyle">
		<div style="padding-left:175px">
			<table width="70%" align="center" >
				<tr/><tr/><tr/>
				<tr>
				<td align=center style="width:30%"><b>Select GR No. to be Renewed :</b>
				</td>
				<td align="left" style="width:40%">
					<select name="cmbOrder" id="cmbOrder"  onChange="getSelectedOrderDate()" style="width:80%">
						<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
							<c:forEach var="OrderObj" items="${resValue.postExpiryList}" >
								<c:choose>
									<c:when test="${resValue.oldOrderId == OrderObj[0]}">
									<option value="<c:out value="${OrderObj[0]}"/>" selected="selected"><c:out
										value="${OrderObj[1]}" /></option>										
									</c:when>
									<c:otherwise>
										<option value="<c:out value="${OrderObj[0]}" />" title="${OrderObj[1]}"><c:out
										value="${OrderObj[1]}" /></option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</select>
					<label class="mandatoryindicator">*</label>	
				</td>	
				<td width="10%" align="left"><input type="text" size="10" name = "selectedOrderDate" id="selectedOrderDate" value="" readonly="readonly">
				</td>		
				</tr>	
			</table>
		</div>	
		<br/>
		<div align="center">
			<hdiits:button name="btnGo" id="btnGo" type="button" captionid="PC.GO" bundle="${commonLables}" onclick="fetchPostListUnderOrder()" />
			<hdiits:button	name="btnBackWithGo" id="btnBackWithGo" type="button" captionid="eis.back" bundle="${commonLables}" onclick="ReturnLoginPage();" />
		</div>
		<br/><br/>
	</fieldset>	
	
	
	<br/>
	<c:if test="${(resValue.oldOrderId != null)}">
	<fieldset class="tabstyle">
		<legend>
		<b>Renewal Of Post Details</b>
		</legend>
		<br/><br/>
		<table>	<tr/> </table>
		<input type="hidden" name="counterPost" id="counterPost" value="0"/>
		<table width="90%"  >
			<tr>
				<td width="40%" valign="top">
					<c:choose>
         				<c:when test="${postList != null}">
         					<div style="width:100%;overflow:auto;height:400px;" >
         						<display:table list="${postList}" id="tablePost" requestURI=""  export="false" style="width:100%" partialList="true" 
								 offset="1" excludedParams="ajax" sort="external" defaultsort="1" defaultorder="ascending" cellpadding="5" size="${resValue.totalRecords}">
								 <display:setProperty name="paging.banner.placement" value="bottom"/>
									 <display:column class="tablecelltext" style="text-align:center" headerClass="datatableheader" title= "<input type='checkbox' name='SelectAll' onclick='checkUncheckAll(this)'/>" >
				        				<input type="checkbox" name="GroupCheck" id="GroupCheck${tablePost_rowNum}" value="${tablePost.orgPostMst.postId}" />
				        				<script>
										document.getElementById("counterPost").value=Number(document.getElementById("counterPost").value) + 1;
										</script>
			            			</display:column>
			            			<display:column style="text-align: left;" class="tablecelltext" title="Posts (All)" headerClass="datatableheader" sortable="true"> 		
			           					<label id="postName${tablePost_rowNum}"><b>${tablePost.postName}</b><b>T</b></label>
		                			</display:column>
								 </display:table>
         					</div>
         				</c:when>
         				<c:otherwise>
         						<table id="tablePost" title="" border="2" bordercolor="gray" cellpadding="3"  width="85%" >
             						<tr>
             							<td width="20%" align="center" ><input type='checkbox' name='SelectAll' onclick='checkUncheckAll(this)'/></td>
             							<td width="80%" align="center" style="color: rgb(200,100,20);font-size: small;font-style: normal" ><b><u>Posts (All)</u></b></td>
             						</tr>
             					</table>
         				</c:otherwise>
         			</c:choose>
				</td>
				<td width="10%">
				</td>
				<td width="40%" valign="top" align="left">
					<fieldset>
					<legend>Order Details
					</legend>
					<table width="100%" >
						<tr/><tr/>
						<tr>
							<td width="40%" valign="middle" align="left"><b> Old GR :</b>
							</td>
							<td width="60%" align="left"><input type="text" size="30" name = "currentOrder" id="currentOrder" value="${resValue.currentOrderName}" readonly="readonly"/>
							</td>
						</tr>
						<tr/><tr/>
						<tr>
							<td width="40%" valign="middle" align="left"><b> Old GR Date :</b>
							</td>
							<td width="60%" align="left"><input type="text" size="10" name = "currentOrderDate" id="currentOrderDate" value="${resValue.currentOrderDate}" readonly="readonly"/>
							&nbsp;&nbsp;&nbsp;(DD/MM/YYYY)
							</td>
						</tr>
						<!--
						
						<tr>
							<td width="40%" valign="middle" align="left"><b> Old GR Date :</b>
							</td>
							<td width="60%" align="left"><input type="text" size="10" name = "currentOrderDate" id="currentOrderDate" value="${resValue.currentOrderDate}" readonly="readonly"/>
							&nbsp;&nbsp;&nbsp;(DD/MM/YYYY)
							</td>
						</tr>
						
						-->
						
						<tr/><tr/>
						
						<tr>
							<td width="40%" valign="middle" align="left"><b> New GR No:</b>
							</td>
							<td width="60%" valign="middle" align="left">
							<select name="cmbNewOrder" id="cmbNewOrder"  onChange="getRenewalOrderDate()" style="width:80%">
								<option value="-1" selected="selected"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
									<c:forEach var="OrderObj" items="${resValue.orderList}" >
										<option value="<c:out value="${OrderObj.orderId}"/>"  title="${OrderObj.orderName}"><c:out	value="${OrderObj.orderName}" />
										</option>
									</c:forEach>
							</select>
							<label class="mandatoryindicator">*</label>	
							</td>
						</tr>
						
						<tr/><tr/>
						<tr>
							<td width="40%" valign="middle" align="left"><b> New GR Date :</b>
							</td>
							<td width="60%" align="left"><input type="text" size="10" name = "renewalStartDate" id="renewalStartDate" value="" readonly="readonly" maxlength="10" />
							&nbsp;&nbsp;&nbsp;(DD/MM/YYYY)
							</td>
						</tr>
						<tr/><tr/>
						<tr>
							<td width="40%" valign="middle" align="left"><b> Renewal Post Start Date :</b>
							</td>
							<td width="60%" align="left"><input type="text" size="10" name = "renewalPostStartDate" id="renewalPostStartDate" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
															onBlur="validateDate(this);" value="${postStartDate}" /> &nbsp;
															<img src='images/CalendarImages/ico-calendar.gif' 
															onClick='window_open("renewalPostStartDate",375,570)'
															style="cursor: pointer;"/>
							<label class="mandatoryindicator">*</label>	
							</td>
						</tr>
						<tr/><tr/>
						<tr>
							<td width="40%" valign="middle" align="left"><b> Renewal Post End Date :</b>
							</td>
							<td width="60%" align="left"><input type="text" size="10" name = "renewalEndDate" id="renewalEndDate" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
															onBlur="validateDate(this);" value="" /> &nbsp;
															<img src='images/CalendarImages/ico-calendar.gif' 
															onClick='window_open("renewalEndDate",375,570)'
															style="cursor: pointer;"/>
							<label class="mandatoryindicator">*</label>	
							</td>
						</tr>
						<tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/>
						<tr>
							<td width="40%"></td>
							<td width="20%">
								<hdiits:button name="btnSave" id="btnSave" type="button" captionid="eis.save" bundle="${commonLables}" onclick="checkRenewalDataBeforeSubmit()" />
							</td>
							<td width="40%"></td>
						</tr>
					</table>
					</fieldset>
				</td>
			</tr> 
		</table>
		
	</fieldset>
	</c:if>
	<table width="100%">
		<tr>
			<td width="50%"><input type="hidden" id="postIdsToBeAttached" name="postIdsToBeAttached" value="" size="200" ></td>
		</tr>
</table>
	</hdiits:form>
			
<script type="text/javascript">
checkSuccess();
</script>
</body>




</html>
<%
	} catch (Exception ex) {
		ex.printStackTrace();
	}
%>
