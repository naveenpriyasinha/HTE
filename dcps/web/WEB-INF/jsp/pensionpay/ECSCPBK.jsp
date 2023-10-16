<% try{%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@page import="com.tcs.sgv.common.constant.Constants"%>
<fmt:setBundle basename="resources.pensionpay.PensionLabels" var="pensionLabels" scope="request"/>
<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />
<script type="text/javascript"  src="script/pensionpay/Common.js"></script>
<script type="text/javascript"  src="script/pensionpay/GenerateMonthlyPensionBill.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript">
function generatebtnGenECSCPBK()
{
	var lLocCode = document.getElementById("cmbTrsyOffice").value;
	var lYear = document.getElementById("cmbForYear").value;
	var lMonth = document.getElementById("cmbForMonth").value;
	if((lLocCode != null && lLocCode.length > 0) && lLocCode != "-1")
	{
		if((lYear != null && lYear.length > 0))
		{
			if(lMonth != null && lYear.length > 0)
			{
				
				if(lMonth < 10)
				{
					lMonth = "0"+lMonth;
				}
				var lMonthYear = lYear+lMonth;
				//alert("location code is :"+lLocCode+"Month year is :"+lMonthYear);
				var params = "&forMonth="+lMonthYear+"&locCode="+lLocCode;
				url = "ifms.htm?actionFlag=generateECSCPBKFile";  
				url = url+params;
// 				var myAjax = new Ajax.Request(url,
// 					       {
// 					        method: 'post',
// 					        asynchronous: false,
// 					        parameters: params,
// 					        onSuccess: function(myAjax) {
// 										responseRejectMonthlyBill(myAjax,lMonthYearDesc);
// 							},
// 					        onFailure: function(){ alert('Something went wrong...');} 
// 					          } );
				
				
				var newWindow;
				var height = screen.height - 100;
				var width = screen.width;
				var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
				newWindow = window.open(url, "ECSCPBKFile", urlstyle);
			}
			else
			{
				alert("Please select month.");
			}
		}
		else
		{		
			alert("Please select year.");
		}
	}
	else
	{
		alert("Please select treasury.");
	}
	
}
</script>
<hdiits:form name="ECSCPBK" method="post" validate="">
<fieldset style="width:100%"  class="tabstyle">
	<legend	id="headingMsg">
			<b><fmt:message key="ECSCPBK.HDNG" bundle="${pensionLabels}"></fmt:message></b>
	</legend>
	<table>
		<tr>
			<td width="20%">
				<fmt:message key="MNTH.YEAR" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="30%">
				<select name="cmbForYear" id="cmbForYear"  tabindex="2" >
					<c:forEach var="SgvcFinYearMstVO" items="${resValue.SgvcFinYearMstVOArray}">																		
						<c:if test="${SgvcFinYearMstVO.finYearCode == resValue.CurrentYear }">
							<option value="${SgvcFinYearMstVO.finYearCode}" selected="selected" > <c:out value="${SgvcFinYearMstVO.finYearCode}"></c:out> </option>
						</c:if>
						<c:if test="${SgvcFinYearMstVO.finYearCode != resValue.CurrentYear }">
							<option value="${SgvcFinYearMstVO.finYearCode}"> <c:out value="${SgvcFinYearMstVO.finYearCode}"></c:out> </option>
						</c:if> 																		 
					</c:forEach>
				</select>
			</td>
			<td width="20%">
				<fmt:message key="MNTH.MONTH" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="30%">
				<select name="cmbForMonth" id="cmbForMonth"  tabindex="1" >
					<c:forEach var="SgvaMonthMstVO" items="${resValue.SgvaMonthMstVOArray}">												 					
						<c:if test="${SgvaMonthMstVO.monthNo == resValue.CurrentMonth }">
							<option value="${SgvaMonthMstVO.monthNo}" selected="selected" > <c:out value="${SgvaMonthMstVO.monthName}"> </c:out> </option>
						</c:if>
						<c:if test="${SgvaMonthMstVO.monthNo != resValue.CurrentMonth }">
							<option value="${SgvaMonthMstVO.monthNo}" > <c:out value="${SgvaMonthMstVO.monthName}"> </c:out> </option> 
						</c:if>  
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td width="20%">
				<fmt:message key="ECSCPBK.LOCNAME" bundle="${pensionLabels}"></fmt:message>
			</td>
			<td width="30%">
			<select name="cmbTrsyOffice" id="cmbTrsyOffice">
				<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="treasuryList" items="${resValue.lLstTreasury}">
				    <option value="${treasuryList.id}" title="${treasuryList.desc}">
						<c:out value="${treasuryList.desc}"></c:out>									
					</option>
            	</c:forEach>
		    </select>
			</td>
			<td width="20%">
			</td>
			<td width="30%">
			</td>
		</tr>
	</table>
</fieldset>
<br/>
<div style="text-align:center">
	<hdiits:button name="btnGenECSCPBK" id="btnGenECSCPBK" type="button"  captionid="ECSCPBK.BTNGENERATE" bundle="${pensionLabels}" onclick="generatebtnGenECSCPBK()" style="width:300px;"/>
	<hdiits:button name="btnClose" id="btnClose" type="button"  captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick="winCls();"/>
</div>
</hdiits:form>	
<% }catch(Exception e)
{
	e.printStackTrace();
}%>