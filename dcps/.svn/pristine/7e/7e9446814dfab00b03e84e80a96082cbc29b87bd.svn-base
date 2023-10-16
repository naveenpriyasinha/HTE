<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<%@ page import="com.tcs.sgv.common.constant.Constants"%>
<script>
document.getElementById("banner").src ="images/HomePageImages/FianlHomePG_1_Pension.jpg";
</script>
<script language="JavaScript" src="script/pensionproc/pensionView.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>




<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request"/>
<fmt:setBundle basename="resources.pensionpay.PensionersCornerConstants_ma" var="pensionerCornerLabels" scope="request" />
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>

<style type="text/css">
#modalContainer {
	background-color:transparent;
	position:absolute;
	width:100%;
	height:100%;
	top:200px;
	left:0px;
	z-index:1000;	
}
#alertBox {
	position:relative;
	width:300px;
	min-height:100px;
	margin-top:50px;
	border:1px solid #000;
	background-color:white;
	background-repeat:no-repeat;
	background-position:20px 30px;
}
#modalContainer > #alertBox {
	position:absolute;
}
#alertBox #closeBtn {
	display:block;
	position:relative;
	margin:5px auto;
	padding:3px;
	border:1px solid #000;
	width:70px;
	font:0.7em verdana,arial;
	text-transform:uppercase;
	text-align:center;
	color:#FFF;
	background-color:#BB6D46;
	text-decoration:none;
}
#alertBox h3 {
	margin:0;
	font: 2px;
	height:15px;
	background-color:#BC6A00;
	color:#FFF;
	border-bottom:1px solid #000;
	padding-left:50px;

}
#alertBox p {
	font-size:20px;
	padding-left:5px;
	padding-right:5px;
	margin-left:10px;
}
.important {
	background-color:white;
	padding:2px;
}
code span {
	color:black;
}
</style>







<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>




<hdiits:form name="PensionerView" validate="true" method="post" action=" ">
<br>
<br>
<b><h1 align="center" style="font-size: 30px;"><fmt:message key="PC.PENSIONERSHISTORY" bundle="${pensionerCornerLabels}"></fmt:message></h1>
</b><br>
<br>

<input type="hidden" id="flag"/> 
<input type="hidden" id="fromdate" name="fromdate"></input>
<input type="hidden" id="todate" name="todate"></input>
<b><font size="3px" >
<table  width="100%" height="90%" >
					<tr height="60%">
					
					
		<td   align="left" style="padding-left:3.5%;">
		<fmt:message key="PC.FROMDATE" bundle="${pensionerCornerLabels}"></fmt:message></td>
		<td width="25%"><select id="fromMonth" name="fromMonth" >
<option value = "04">April</option>
<option value = "01">January</option>
<option value = "02">February</option>
<option value = "03">March</option>
<option value = "04">April</option>
<option value = "05">May</option>
<option value = "06">June</option>
<option value = "07">July</option>
<option value = "08">August</option>
<option value = "09">September</option>
<option value = "10">October</option>
<option value = "11">November</option>
<option value = "12">December</option> 
</select>
<select id="fromYear" name="fromYear" onchange ="ToYear()"> 
<option>Select</option>
<option value="2012">2012</option> 
<option value="2013">2013</option> 
<option value="2014">2014</option> 
<option value="2015">2015</option> 
<option value="2016">2016</option> 
<option value="2017">2017</option> 
<option value="2018">2018</option> 
<option value="2019">2019</option> 
<option value="2020">2020</option> 
<option value="2021">2021</option> 
<option value="2022">2022</option> 
<option value="2023">2023</option> 
<option value="2024">2024</option> 
<option value="2025">2025</option> 
</select><label
			class="mandatoryindicator"${varLabelDisabled} >*</label>
		
		
 </td>
		
		<td   align="left" style="padding-left:3.5%;" >
		<fmt:message key="PC.TODATE" bundle="${pensionerCornerLabels}"></fmt:message></td>
		<td width="25%"><select id="toMonth" name="toMonth" >
<option value = "-1">Select</option>
<option value = "01">January</option>
<option value = "02">February</option>
<option value = "03">March</option>
<option value = "04">April</option>
<option value = "05">May</option>
<option value = "06">June</option>
<option value = "07">July</option>
<option value = "08">August</option>
<option value = "09">September</option>
<option value = "10">October</option>
<option value = "11">November</option>
<option value = "12">December</option> 
</select>
<select id="toYear" name="toYear" onchange="validate()"> 
<option value="-1">Select</option>

</select>
	 <label
			class="mandatoryindicator"${varLabelDisabled} >*</label>
			
		
 </td>
				</tr>
				<tr height="70%"><td></td></tr>		
				<tr height="70%">	
								<td  align="left" style="padding-left:3.5%;" >
								<fmt:message key="PC.TREASURY" bundle="${pensionerCornerLabels}"></fmt:message>
</td><td width="25%">							
										 <select name="cmbTreasuryCode" id="cmbTreasuryCode" style="width:275px;"  onChange="" >
											<c:forEach var="Treasuries" items="${resValue.TREASURIES}" >
											<option value="${Treasuries.id}" title="${Treasuries.desc}">
											<c:out value=" ${Treasuries.desc}"></c:out></option>
											</c:forEach>
											</select>
									<label class="mandatoryindicator">*</label>
								</td></tr>	
							<tr height="70%"></tr><tr height="70%"></tr>
					<tr height="75%">
					
					<td   align="left" style="padding-left:3.5%;">
				<fmt:message key="PC.BANK" bundle="${pensionerCornerLabels}"></fmt:message>
			</td><td width="25%"><select name="cmbBankName" id="cmbBankName" style="width:250px" onChange="" >
				<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="banknames" items="${resValue.banknames}">
					
							<option value="${banknames.id}"><c:out
								value=" ${banknames.desc}"></c:out></option>
					
				</c:forEach>
			</select>
			<label class="mandatoryindicator" ${varLabelDisabled}>*</label>
			 </td>
		
			<td  align="left" style="padding-left:3.5%;" >
<fmt:message key="PC.BANKBRANCH" bundle="${pensionerCornerLabels}"></fmt:message>
</td><td width="25%">
		<select name="cmbBranchName" id="cmbBranchName" style="width: 240px" onchange=""  >
				<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" /></option><c:forEach var="branchnames" items="${resValue.branchnames}">
						
								<option value="${branchnames.id}"><c:out
									value="${branchnames.desc}"></c:out></option>
					</c:forEach>
			
			</select>
			<label class="mandatoryindicator" ${varLabelDisabled}>*</label>
			</td>
					</tr>	<tr height="70%"></tr><tr height="70%"></tr>
									<tr height="60%">
							<td  align="left" style="padding-left:3.5%;" ><fmt:message key="PC.PENSIONERNAME" bundle="${pensionerCornerLabels}"></fmt:message>
							 </td><td width="30%"> <input style="width:85%"  type="text" name="pension" id="pension" class="form-autocomplete" onchange="" ></input>
							 <label
			class="mandatoryindicator"${varLabelDisabled} >*</label>
							<span id="roleIndicatorRegion" style="display: none"> <img src="./images/busy-indicator.gif" /></span></td>
						
						<td  align="left" style="padding-left:3.5%;" ><fmt:message key="PC.ACCOUNTNO" bundle="${pensionerCornerLabels}"></fmt:message>
				</td><td width="30%"> <input type="text" name="account" id="account" maxlength="20" onchange=""/><label
			class="mandatoryindicator"${varLabelDisabled} >*</label>
				</td>	
					
				</tr>	<tr height="70%"></tr>
				<tr height="60%">
				
				<td  align="left" style="padding-left:3.5%;" ><fmt:message key="PC.PPONO" bundle="${pensionerCornerLabels}"></fmt:message>
				</td><td width="25%"> <input type="text" readonly="readonly"  name="PPONo" id="PPONo"/>
				</td>
				</tr> </table><br><table align="center">
						<tr>
								<td id="go" align="center" style="padding-left: 50px">
<hdiits:button name="btnGo" id="btnGo" type="button" style="width:240px" captionid="PC.GENREP" bundle="${pensionerCornerLabels}" onclick= "chkValidation();"/>
</td>
</tr>
	      </table></font></b>
	    <br><br>  <font color="#DC4040" size="3px"><fmt:message key="PC.ALERT1" bundle="${pensionerCornerLabels}"></fmt:message>
	    <br> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<fmt:message key="PC.ALERT2" bundle="${pensionerCornerLabels}"></fmt:message>
	    <br>
	   &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <fmt:message key="PC.ALERT3" bundle="${pensionerCornerLabels}"></fmt:message>
	   <br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <fmt:message key="PC.ALERT4" bundle="${pensionerCornerLabels}"></fmt:message>
	   <br>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <fmt:message key="PC.ALERT5" bundle="${pensionerCornerLabels}"></fmt:message>
	    </font>
	      
</hdiits:form>


<ajax:select source="cmbTreasuryCode" target="cmbBankName"
	eventType="change"
	baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getBankForTreasury"
	parameters="cmbTreasuryCode={cmbTreasuryCode}">

</ajax:select>
	

<ajax:select source="cmbBankName" target="cmbBranchName"
	eventType="change"
	baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getBranchForBank"
	parameters="cmbTreasuryCode={cmbTreasuryCode},cmbBankName={cmbBankName}">
</ajax:select>

<ajax:autocomplete source="pension" target="PPONo"
	
	baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getNameForAutoComplete"
	parameters="searchKey={pension},bankId={cmbBankName},branchId={cmbBranchName},cmbTreasuryCode={cmbTreasuryCode}" className="autocomplete" 
   minimumCharacters="3" indicator="roleIndicatorRegion" >
</ajax:autocomplete>



<!--


<ajax:select source="pension" target="PPONo"
	eventType="change"
	baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getPpoNumber"
	parameters="pensionerCode={pension}">
</ajax:select>

-->