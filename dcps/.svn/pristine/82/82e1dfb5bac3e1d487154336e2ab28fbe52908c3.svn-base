<html>
<head>
<%
try {
%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../../core/include.jsp"%>


<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="monthList" value="${resValue.monthList}" ></c:set>
<c:set var="yearList" value="${resValue.yearList}" ></c:set>
<c:set var="msg" value="${resultObj.resultValue.msg}" ></c:set>

<fmt:setLocale value="${sessionScope.locale}"/>
 <fmt:setBundle basename="resources.eis.eis_common_lables" var="genLables" scope="page"/>
<script>
	function validationForm()
	{	

	
	 if(document.frmPaybillPara.cmbDemand.value=='-1')
	 {
	  alert('Please select Bill No.');
	  document.frmPaybillPara.cmbDemand.focus();
	  alert("document.frmPaybillPara.cmbDemand.focus();");
	  return false;	 
	 }
	 else if(document.frmPaybillPara.cmbMjrHead.value=='-1')
	 {
	  alert('Please select major Head');
	  document.frmPaybillPara.cmbMjrHead.focus();
	  alert("document.frmPaybillPara.cmbMjrHead.focus();");
	  return false;	 
	 }
	 else if(document.frmPaybillPara.cmbSubMjrHead.value=='-1')
	 {
	  alert('Please select Sub Major Head');
	  document.frmPaybillPara.cmbSubMjrHead.focus();
	  alert("document.frmPaybillPara.cmbSubMjrHead.focus();");
	  return false;	 
	 }
	 else if(document.frmPaybillPara.cmbMnrHead.value=='-1')
	 {
	  alert('Please select Minor Head');
	  document.frmPaybillPara.cmbMnrHead.focus();
	  return false;	 
	 }
	 else if(document.frmPaybillPara.cmbSubHead.value=='-1')
	 {
	  alert('Please select Sub Head');
	  document.frmPaybillPara.cmbSubHead.focus();
	  return false;	 
	 }

	 
	/* else if(document.frmPaybillPara.cmbCat.value=='-1')
	 {
	  alert('Please select Category');
	  document.frmPaybillPara.cmbCat.focus();
	  return false;	 
	 }*/
	 else
	 return true;
    }
    
 function chkForBillType()
 {
	
	//alert("Salleeeeeeee");
	var billType = document.getElementById("billType").value;
	//alert("billType"+billType);
 	if(billType == "" || billType == "-1")
	{
 		//alert("Hiiiiiiiiiii");
 		alert("Please Select The Bill Type");
	 	document.getElementById("billType").focus();
	}
	else if(validationForm())
 	{
		//alert('chkForBillType');
	 	if(billType == "paybill")
	 	{
	 		document.frmPaybillPara.action = "./hrms.htm?actionFlag=generatePaybill";
	 		document.frmPaybillPara.submit();
	 		document.frmPaybillPara.formSubmitButton.disabled=true;
	 		document.frmPaybillPara.Back.disabled=true;
	 		document.frmPaybillPara.Reset.disabled=true; 
			//alert("Kamineeeeeeee");
	 		showProgressbar("Please wait<br>While Generating Bill ...");
	 		
	 		
	 	}
	 	if(billType == "arrears" )
	 	{
	 		document.frmPaybillPara.action = "./hrms.htm?actionFlag=generateArrearbill";
	 		document.frmPaybillPara.submit();
	 	}
	 }
 }  

 
 function onBackfn()
 {
 	//alert("in close...");
 	document.frmPaybillPara.action='./hrms.htm?actionFlag=getHomePage';
 	document.frmPaybillPara.submit();
 }

 	 
</script>
<hdiits:form name="frmPaybillPara" id="frmPaybillPara" validate="true" method="POST" 
	action="#" encType="text/form-data" >
	
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b>
			<hdiits:caption captionid="PB.GeneratePaybill" style="" bundle="${genLables}"/></b></a></li>
			<!-- <li class="selected"><a href="#" rel="tcontent2">INSERT FORM2</a></li> -->
		</ul>
	</div>
	<br/>
	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >
	<table align="center" cellsapcing="2" cellpadding="2" width="100%" border = '0'>
	<tr>
		<!-- <td align="right">
		&nbsp;
		</td>
		<td align="right" width="43%">
		Bill Type :
		</td>  -->
		
	
		<td  width="57%">
			<hdiits:select name="billType" id="billType" sort="false" style="width:60px;display:none;" mandatory="false">
				<hdiits:option value="-1" >Select</hdiits:option>
				<hdiits:option value="paybill"  selected="true">Paybill</hdiits:option>
				<hdiits:option value="arrears">Arrears</hdiits:option>
			</hdiits:select>
		
		</td>
	</tr>
	
	<tr><jsp:include page="/WEB-INF/jsp/hrms/eis/InnerbillPara.jsp" /></tr>
	
	</table>
 	</div>
 	
 	
 	
 	
	<%--<jsp:include page="../../core/PayTabnavigation.jsp" />--%>
	<div>
<table class="tabNavigationBar">
	<tr align="center">
		<td class="tabnavtdcenter" id="tabnavtdcenter" >
		    <hdiits:button name="formSubmitButton" id="formSubmitButton" onclick="chkForBillType()" type="button" captionid="Generate" bundle="${genLables}" />
		    <hdiits:button name="Back" type="button" captionid="eis.close" bundle="${genLables}" onclick="history.go(-1);onBackfn();return false;"/>
		    <hdiits:button name="Reset" type="button" value="Reset" onclick="resetForm()"/>
            <script language="javaScript">             
			  
			  function resetForm()
			  {
			  	if(confirm("All the entered values will be cleard ,Please confirm!") == true)
			  	{
			  		document.forms[0].reset();
			  		<c:if test="${param.afterReset gt ''}">
						${param.afterReset};
					</c:if>
					window.location.reload();
				
			  	}
			  				  	
			  }
			  
			</script>
		</td>
	</tr>
</table>	

    <hdiits:jsField  name="validate" jsFunction="validationForm()" /> 

	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		
		
		if("${msg}"!=null&&"${msg}"!='')
		{
			alert("${msg}");
			//closeBill();
			//window.close();
		}		
		initializetabcontent("maintab");
		</script>
	<hdiits:hidden name="Demand" id="Demand" default="document.frmPaybillPara.cmbDemand.value"/>
	<hdiits:hidden name="MjHd" id="MjHd" default="document.frmPaybillPara.cmbMjrHead.value"/>
	<hdiits:hidden name="SubMjHd" id="SubMjHd" default="document.frmPaybillPara.cmbSubMjrHead.value"/>
    <hdiits:hidden name="MnrHd" id="MnrHd" default="document.frmPaybillPara.cmbMnrHead.value"/>
  	<hdiits:hidden name="txtSbHd" id="txtSbHd" default="document.frmPaybillPara.cmbSubHead.value"/>
  	<hdiits:hidden name="SubHd" id="SubHd" default="document.frmPaybillPara.cmbDtlHead.value"/>
  	<hdiits:hidden name="DtlHd" id="DtlHd" default="00"/>
  	
  	<hdiits:hidden name="grade" id="grade" default="document.frmPaybillPara.cmbSelGrade.value"/>
  	
  	<hdiits:validate controlNames="text"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
<script type="text/javascript">
document.frmPaybillPara.formSubmitButton.disabled=true; 
</script>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

</html>
	