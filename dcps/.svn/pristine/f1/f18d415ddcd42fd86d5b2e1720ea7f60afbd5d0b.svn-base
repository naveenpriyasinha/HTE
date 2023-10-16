<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script>
document.getElementById("banner").src ="images/HomePageImages/FianlHomePG_1_12.jpg";
</script>

<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLabels" scope="request" />
<fmt:setBundle basename="resources.dcps.DcpsAlerts" var="dcpsAlerts"	scope="request" />

<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"></script>


<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/dppf/calendarDppf.js"></script>
<script type="text/javascript" src="script/payfixation/common.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/common/IFMSCommonFunctions.js"></script>
<script type="text/javascript" src="script/dcps/srkaMasters.js"></script>
<script type="text/javascript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/dcps/dcpsDDO.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>

	<c:set var="resultObj" value="${result}"></c:set>
	<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
	<c:set var="sevenPCEmpDetailsList" value="${resValue.sevenPCEmpDetailsList}"></c:set>
	<c:set var="msg" value="${resValue.msg}"></c:set>
	<c:set var="inactivemsg" value="${resValue.inactivemsg}"></c:set>
    <c:set var="sucessMsg" value="${resValue.sucessMsg}"></c:set>
    <c:set var="doj" value="${resValue.doj}"></c:set> 
    <c:set var="dob" value="${resValue.dob}"></c:set> 
    <c:set var="empSevarthID" value="${resValue.empSevarthID}"></c:set> 
    <c:set var="gradeIdDetailsList" value="${resValue.gradeIdDetailsList}"></c:set>
    


<c:set var="PayCommisionRows" scope="page"
	value="style='display:none'"></c:set>

<c:set var="PayCommisionRows1" scope="page"
	value="style='display:none'"></c:set>
	
<c:set var="PayCommisionRows2" scope="page"
	value="style='display:none'"></c:set>

<c:set var="PayCommisionRows3" scope="page"
	value="style='display:none'"></c:set>

<c:set var="PayCommisionRows4" scope="page"
	value="style='display:inline'"></c:set>

<c:set var="PayCommisionRows9" scope="page"
	value="style='display:inline'"></c:set>
	
<c:set var="PayCommisionRows10" scope="page"
	value="style='display:none'"></c:set>
	
<c:set var="PayCommisionRows13" scope="page"
	value="style='display:none'"></c:set>	
<script>
function showEmpSelectionListForSevenPC(){
//	alert("in fun");
	var txtSevaarthId =  document.getElementById("txtSevaarthId").value.trim();
	var txtEmployeeName = document.getElementById("txtEmployeeName").value.trim();
	if(txtSevaarthId == "" && txtEmployeeName == "")
	{
		alert('Please enter search criteria');
		return;
	}
	var url ="ifms.htm?actionFlag=loadEmpDetailsForSevenPCNew&txtSevaarthId="+txtSevaarthId+"&txtEmployeeName="+txtEmployeeName;
	//alert("url--"+url);
	showProgressbar('Please Wait<br>Your request is in progress...');
	document.Revision7PC.action = url ;
	enableAjaxSubmit(true);
	document.Revision7PC.submit();
	
}
</script>
	
<script type="text/javascript">
if('${sucessMsg}'!= null && '${sucessMsg}'!='') 
{
	alert('${sucessMsg}');
	resetForm1();
}
</script> 

<script type="text/javascript">
if('${msg}'!= null && '${msg}'!='') 
{
	alert('${msg}');
	resetForm1();
}
</script> 
<script type="text/javascript">
if('${inactivemsg}'!= null && '${inactivemsg}'!='') 
{
	alert('${inactivemsg}');
	resetForm1();
}
</script>

	<hdiits:form name="Revision7PC" id="Revision7PC"
		encType="multipart/form-data" validate="true" method="post">
		
		<input type="hidden" name="newBasicPerScaleMatrix11" id="newBasicPerScaleMatrix11" value="${resValue.newBasicPerScaleMatrix1}">
		<input type="hidden" name="newBasicPerScaleMatrix21" id="newBasicPerScaleMatrix21" value="${resValue.newBasicPerScaleMatrix2}">
		<input type="hidden" name="newBasicPerScaleMatrix31" id="newBasicPerScaleMatrix31" value="${resValue.newBasicPerScaleMatrix3}">
		<input type="hidden" name="newBasicPerScaleMatrix41" id="newBasicPerScaleMatrix41" value="${resValue.newBasicPerScaleMatrix4}">
		<input type="hidden" name="empSevarthID" id="empSevarthID" value="${resValue.empSevarthID}">
		<input type="hidden" name="extLevelId" id="extLevelId" value="${resValue.extLevelId}">
	
	    <input type="hidden" name="matrixBasicValue" id="matrixBasicValue" >
		<input type="hidden" name="newDate" id="newDate">
	    <input type="hidden" name="returnValue" id="returnValue">
	    <input type="hidden" name="type" id="type">
	  
		<div align="center">
	<fieldset style="width: 100%" class="tabstyle"><legend
		id="headingMsg"> <b><fmt:message
		key="CMN.DDOEMPSELECTSEARCH" bundle="${dcpsLabels}"></fmt:message></b> </legend>
	<table width="100%" align="center" cellpadding="4" cellspacing="4">
			<tr align="center">
				<td width="25%" align="left" ><fmt:message
					key="CMN.SEVARTHID" bundle="${dcpsLabels}" /></td>
				<td width="50%" align="left"><input type="text"
					id="txtSevaarthId" style="text-transform: uppercase" size="30"
					name="txtSevaarthId"/></td>
			</tr>
			<tr align="center">
				<td width="25%" align="left"><fmt:message key="CMN.EMPNAME"
					bundle="${dcpsLabels}" /></td>
				<td width="50%" align="left"><input type="text"
					id="txtEmployeeName" size="30" style="text-transform: uppercase"
					name="txtEmployeeName" />
				<span id="roleIndicatorRegion" style="display: none"> <img src="./images/busy-indicator.gif" /></span></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><label style="color: red"><fmt:message
					key="MSG.SEARCH" bundle="${dcpsLabels}" /></label></td>
			</tr>
	</table>
	<div style="width: 50; text-align: center; align: center"><hdiits:button
		name="btnSearch" style="align:center" type="button"
		captionid="CMN.SEARCH" bundle="${dcpsLabels}"
		onclick="showEmpSelectionListForSevenPC();" /> </div>
	</fieldset>
	
	
			          
<c:if test="${sevenPCEmpDetailsList != null}">
<div id="PayCommisionRows4"${PayCommisionRows4}">    
<table>
	<tr>
	              <td align="left">Increment on Hold Employee</td>
	                 <td align="left"><input type="radio" id="punishmentEmp" name = "punishmentEmp" onclick="getPunishmentEmpDetails();"/></td>
			         <!--  <td  align="left">Option With Promotion</td> 
			           <td align="left"><input type="radio" id="optWidPromoEmp" name = "optWidPromoEmp" onclick="getOptionPromoEmpDetails();"/></td> -->
			           <td  align="left">Regular Employee</td> 
			           <td align="left"><input type="radio" id="Other" name = "Other" onclick="getRegularEmpDetails();"/></td>
			           <td  align="left">For Option Other Than 01/01/2016</td> 
			           <td align="left"><input type="radio" id="Other" name = "Other" onclick="getVikalpEmpDetails();"/></td>
			        </tr>
			         </table></div><br><br>
			         
			  <div id="PayCommisionRows3"${PayCommisionRows3}">       
			 <fieldset class="tabstyle">
					<legend>
					<b>Revision of 6PC to 7PC</b>
				</legend>
					<c:set var="hdnCounter" value="0" />

						<display:table list="${sevenPCEmpDetailsList}" id="vo" requestURI="" export=""
							pagesize="1" style="width:70%" offset="1">

							<display:setProperty name="paging.banner.placement" value="top" />

                  <%-- <display:column headerClass="datatableheader" class="oddcentre"
								style="text-align:left;" sortable="true" title="Date">
								<c:out value="01/01/2016" />
							</display:column> --%>


							<display:column headerClass="datatableheader" class="oddcentre"
								style="text-align:left;" sortable="true"
								titleKey="CMN.EMPLOYEENAME">
								<c:out value="${vo[0]}" />
							</display:column>

							<display:column headerClass="datatableheader" class="oddcentre"
								style="text-align:left;" sortable="true"
								titleKey="CMN.SEVARTHID">
								<c:out value="${vo[1]}" />
							</display:column>

							<display:column headerClass="datatableheader" class="oddcentre"
								style="text-align:left;" sortable="true" title="DDO Code">
								<c:out value="${vo[2]}" />
								<input type="hidden" id="ddoCode${vo_rowNum}" value="${vo[2]}" />
							</display:column>

                    <display:column headerClass="datatableheader" class="oddcentre"
								style="text-align:left;" sortable="true" title="Grade Pay">
								<c:out value="${vo[3]}" />
							</display:column>
							 
							
							<display:column headerClass="datatableheader" class="oddcentre"
								style="text-align:left;" sortable="true" title="6TH PC(Pay in PB+GP)">
								<c:out value="${vo[4]}" />
							</display:column>


	                      <display:column headerClass="datatableheader" class="oddcentre"
								 sortable="true" title="Level">
								<select name="leveldrpdwn" id="leveldrpdwn"  onchange="getLevelIDData()">
							<option value="-1">-- Select --</option>   
				
							<c:forEach var="levelid1" items="${gradeIdDetailsList}">
				    	   	<option value="${levelid1[0]}"> <c:out value="${levelid1[0]}"></c:out>
							</option>
						</c:forEach>     
						</select>
<%-- 								<c:out value="${vo[5]}" /> --%>
<%-- value="${levelid1.id} --%>
							</display:column>
							
							 <display:column headerClass="datatableheader" class="oddcentre"
								style="text-align:left;" sortable="true" title="7TH PC Basic">
  								<select name="baic7thpdwn" id="baic7thpdwn">
								<option value="-1">-- Select --</option>   
						
<%-- 						<c:forEach var="levelid1" items="${gradeIdDetailsList}"> --%>
<%-- 					       	<option value="${levelid1[0]}"> <c:out value="${levelid1[2]}"></c:out> --%>
<!-- 						</option> -->
<%-- 					</c:forEach>      --%>
							</select>									
<%-- 								<c:out value="${vo[6]}" /> --%>
<%-- 								<input type="hidden" id="sevenPCBasic" name = "sevenPCBasic" disabled="disabled" value="${vo[6]}"/> --%>
							</display:column>
							
						</display:table>
						<br>
						<%-- <table>
						<tr>
						<td colspan="4" align="center" width="80%">
<hdiits:button name="btnCalc" id="btnCalc" type="button" captionid="BTN.CALICULATE7PC" bundle="${dcpsLabels}" style="width:200px;" onclick="checkEmpRegOrPromot()"/>
</td>
</tr>
</table> --%>
</td>
<!-- 					<table> -->
<!-- 			     <tr> -->
<!-- 			  	 <td width="60%" align="center"> -->
<!-- 			  	 <label style="color: red"> -->
<!-- 			         Note1- If there is no change in displayed screen then enter Order No. Order Date and save the details.<br> -->
<!-- 			         Note2- click on 'Option/Promotion/Timebound/Demotion' button only when employee got promoted or exercise option.</label><br><br> -->
<%-- 			          <display:table list="" id="vo" requestURI="" export="" --%>
<%-- 							 style="width:40%"> --%>
<%-- 							<display:setProperty name="paging.banner.placement" value="top" /> --%>
<%-- 							   <display:column headerClass="datatableheader" class="oddcentre" --%>
<%-- 								style="text-align:left;" sortable="true" title="Date"> --%>
<%-- 								<c:out value="01/07/2016"/> --%>
<!-- 								  <input type="text" id="date1" value="01/07/2016" readonly="readonly"/><br> -->
<!-- 								 <input type="text" id="date2" value="01/01/2017" readonly="readonly" /><br> -->
<!-- 								 <input type="text" id="date3" value="01/07/2017" readonly="readonly" /><br> -->
<!-- 								 <input type="text" id="date4" value="01/01/2018" readonly="readonly" /><br> -->
<!-- 								 <input type="text" id="date5" value="01/07/2018" readonly="readonly"/><br> -->
<!-- 								 <input type="text" id="date6" value="01/01/2019" readonly="readonly"/>  -->
<%-- 							</display:column> --%>
<%-- 							<display:column headerClass="datatableheader" class="oddcentre" --%>
<%-- 								style="text-align:left;" sortable="true" --%>
<%-- 								title="7TH PC Basic"> --%>
<%-- 							   <input type="text" id="newBasicPerScaleMatrix1" value="${resValue.newBasicPerScaleMatrix1}" onkeypress="digitFormat(this);" onblur="checkEmpType(1);" disabled="disabled"/><br> --%>
<!-- 							   <input type="text" id="newBasicPerScaleMatrix2" onkeypress="digitFormat(this);" onblur="checkEmpType(2);"  disabled="disabled"/><br> -->
<%-- 							   <input type="text" id="newBasicPerScaleMatrix3" value="${resValue.newBasicPerScaleMatrix2}"  onkeypress="digitFormat(this);" onblur="checkEmpType(3);" disabled="disabled"/><br> --%>
<!-- 							   <input type="text" id="newBasicPerScaleMatrix4"  onkeypress="digitFormat(this);" onblur="checkEmpType(4);"  disabled="disabled"/><br> -->
<%-- 							   <input type="text" id="newBasicPerScaleMatrix5" value="${resValue.newBasicPerScaleMatrix3}"  onkeypress="digitFormat(this);" onblur="checkEmpType(5);"  disabled="disabled"/><br> --%>
<%-- 							   <input type="text" id="newBasicPerScaleMatrix6" value="${resValue.newBasicPerScaleMatrix3}"  onkeypress="digitFormat(this);" onblur="checkEmpType(6);"  disabled="disabled"/><br> --%>
							
<%-- 								</display:column> --%>
<%--                         	</display:table><br> --%>
<%-- 			       <hdiits:button name="btnCalc" id="btnCalc" type="button" captionid="BTN.CALICULATE7PCC" bundle="${dcpsLabels}" style="width:400px;" onclick="checkEmpRegOrPromot()"/> --%>
			       
			        
<%-- 			          <td id="PayCommisionRows9"${PayCommisionRows9}"><input type="radio" id="DeputWidPromoEmp" name = "DeputWidPromoEmp" onclick="getPromoWidDeputEmpDetails();" value="-1"/> --%>
<!-- 			             If Promoted After 01/01/2019</td> -->
<!-- 	                    </td>    -->
<!-- 			         </tr> -->
			      
<!-- 			          </table> -->
			          
			         <%--   <table>
						<tr>
						<td>
<hdiits:button name="btnCalc" id="btnCalc" type="button" captionid="BTN.CALICULATE7PCC" bundle="${dcpsLabels}" style="width:400px;" onclick="checkEmpRegOrPromot()"/>
</td>
</tr>
</table>
 --%>
			          <table>
			          <tr id="PayCommisionRows"${PayCommisionRows}" align="left">
			          <td align="left" ><fmt:message
					              key="CMN.REMARKS" bundle="${dcpsLabels}" /></td>
						        <td align="left">
								 <input type="text" id="remarks" name = "remarks" disabled="disabled"/>
			          </td> 
			          <br>
						<td align="left" ><fmt:message
					   key="CMN.LEVEL" bundle="${dcpsLabels}" /></td>
						<td align="left"><select name="level"
							id="level" disabled="disabled" onchange="getLevelData();">
								<option value=""><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
								<c:forEach var="gradePayList" items="${resValue.gradePayList}">

									<option value="${gradePayList[1]}" title="${gradePayList[1]}">
										<c:out value="${gradePayList[1]}"></c:out>
									</option>

								</c:forEach>
						</select><label class="mandatoryindicator"${varLabelDisabled}>*</label></td>
						
			          <td id="PayCommisionRows10"${PayCommisionRows10} align="center">Basic As per Pay Matrix</td>
					  <td id="PayCommisionRows13"${PayCommisionRows13} align="left">
					  <select name="bunchingBasic" id="bunchingBasic" style="width:140px"  disabled="disabled" onchange="get7PCNewBasic();" >
									<option value="-1">--Select--</option>
								</select><label class="mandatoryindicator"${varLabelDisabled}>*</label>
					 </td>
					 <td width="14%" align="left">WEF Date</td>
                      <input type="hidden" name="currDate1" id="currDate1" value="${resValue.lDtCurDate}" /> 
			       <td width="20%" align="left" colspan="3"><input type="text"
				id="txtWEFDate" style="text-transform: uppercase"
				size="10" onkeypress="digitFormat(this);dateFormat(this);" onblur="validateDate(txtWEFDate);compareWithSysDate(document.getElementById('currDate1'),this);"
				name="txtWEFDate" value="" /><img
				src='images/CalendarImages/ico-calendar.gif' width='20'
			      onClick=window_open("txtWEFDate", 375, 570); style="cursor: pointer;" /> <label
				class="mandatoryindicator">*</label></td>
			         </tr>
			         
			         <tr id="PayCommisionRows1"${PayCommisionRows1}">
			          <td width="40%" align="left" ><fmt:message
					              key="CMN.REMARKS" bundle="${dcpsLabels}" /></td>
						        <td align="left">
								 <input type="text" id="remarks1" name = "remarks1" disabled="disabled"/>
			          </td> 
			          <br>
						<td width="25%" align="left" ><fmt:message
					   key="CMN.LEVEL" bundle="${dcpsLabels}" /></td>
						<td align="left"><select name="demotionLevel"
							id="demotionLevel" disabled="disabled" onchange="getLevelData();">
								<option value=""><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
								<c:forEach var="gradePayDemotionList" items="${resValue.gradePayDemotionList}">

									<option value="${gradePayDemotionList[1]}" title="${gradePayDemotionList[1]}">
										<c:out value="${gradePayDemotionList[1]}"></c:out>
									</option>

								</c:forEach>
						</select><label class="mandatoryindicator"${varLabelDisabled}>*</label></td>
			         </tr>
			         
			      </table>
			      	<table width="38%" align="left" cellpadding="0" border="0">
<%-- 			      	<tr id="PayCommisionRows2"${PayCommisionRows2}"> --%>
<%-- 			          <td width="30%" align="left" ><fmt:message --%>
<%-- 					              key="CMN.REMARKS" bundle="${dcpsLabels}" /></td> --%>
<!-- 						        <td align="left"> -->
<!-- 								 <input type="text" id="remarks2" name = "remarks2" disabled="disabled"/> -->
<!-- 			          </td> -->
<!-- 			          </tr> -->
		
		<tr>
			<td width="14%" align="left">Order No.</td>
			<td width="20%" align="left" colspan="3"><input type="text"
				id="txtAuthorityLetterNo" style="text-transform: uppercase"
				size="30" name="txtAuthorityLetterNo" value="" onblur="" /> <label
				class="mandatoryindicator">*</label></td>
		</tr>
		<tr>
			<td width="14%" align="left">Order Date</td>
            <input type="hidden" name="currDate1" id="currDate1" value="${resValue.lDtCurDate}" /> 
			<td width="20%" align="left" colspan="3"><input type="text"
				id="txtAuthorityLetterDate" style="text-transform: uppercase"
				size="10" onkeypress="digitFormat(this);dateFormat(this);" onblur="validateDate(txtAuthorityLetterDate);compareWithSysDate(document.getElementById('currDate1'),this);"
				name="txtAuthorityLetterDate" value="" /><img
				src='images/CalendarImages/ico-calendar.gif' width='20'
			      onClick='window_open("txtAuthorityLetterDate", 375, 570)'; style="cursor: pointer;" /> <label
				class="mandatoryindicator">*</label></td>
				
		</tr>
		<tr>
			<td align="left">  Remarks </td>  <!-- style="text-align: justify;" -->
			<td align="left"><textarea rows="3" cols="25" name="txtAreaRemarks" id="txtAreaRemarks"></textarea></td>
		</tr>			
	</table>
			      
			      </fieldset>
		
				
           	<table>
					<tr>
						<td width="15%" align="left"></td>
						<td>
   <input type="button" name="btnSave" id="btnSave" value = "Save"   onclick="saveDetails();"/>
</td>
					</tr>

				</table>
				</div>
	</c:if>
		</div>

<input type="hidden" name="hidSearchFromDDOSelection" id="hidSearchFromDDOSelection" value="searchFromDDOSelection" /> 
	<input type="hidden" name="hidsearchByDDOAsst" id="hidsearchByDDOAsst" value="searchByDDOAsst" />


	</hdiits:form>
	
	<script>
	/* Punishment empoyee */
	function getPunishmentEmpDetails(){
		//alert("in punishment");
		var sevarthId = document.getElementById("empSevarthID").value;
		var extLevelId = document.getElementById("extLevelId").value;
	
		var url ="ifms.htm?actionFlag=getPunishmentEmpDetails&sevarthId="+sevarthId+"&levelId="+extLevelId;
	    // alert("url--"+url);
			showProgressbar('Please Wait<br>Your request is in progress...');
			document.Revision7PC.action = url ;
			enableAjaxSubmit(true);
			document.Revision7PC.submit();
	}
	
	/* Viklp empoyee */
	 function getVikalpEmpDetails(){
		
	    //alert("in fun");
		var url = "ifms.htm?viewName=chkForViklpEmpRevision";
		//alert("url--"+url);
		var  returnvalue = window.showModalDialog(url, "","dialogHeight:200px;dialogWidth:350px;scroll:No;resizable:no;status:no;resizable:no");
	   //alert("DateStr--"+returnvalue);
		if(returnvalue){
		var sevarthId = document.getElementById("empSevarthID").value;
		var extLevelId = document.getElementById("extLevelId").value;
		var url ="ifms.htm?actionFlag=getViklpEmpDetails&sevarthId="+sevarthId+"&levelId="+extLevelId+"&DateStr="+returnvalue;
	    //alert("url--"+url);
			showProgressbar('Please Wait<br>Your request is in progress...');
			document.Revision7PC.action = url ;
			enableAjaxSubmit(true);
			document.Revision7PC.submit();
	}else{
		
		return false;
	}
	}
	function getRegularEmpDetails(){
		//alert("in getRegularEmpDetails");
		 document.getElementById("PayCommisionRows3").style.display = "inline";
		 document.getElementById("PayCommisionRows4").style.display = "none";
		 
	}
 
	function checkEmpRegOrPromot(){
	//	alert("in fun");
		var url = "ifms.htm?viewName=chkForEmpRevision";
		var  returnvalue = window.showModalDialog(url, "","dialogHeight:100px;dialogWidth:450px;scroll:No;resizable:no;status:no;resizable:no");
		//alert("returnvalue--"+returnvalue);
		if(returnvalue == "Yes")
		{
		//	alert("in yes");
			document.getElementById("level").disabled=false;
			var level = document.getElementById("level").value;
			 document.getElementById("returnValue").value = returnvalue; 
			 document.getElementById("PayCommisionRows1").style.display = "none";
			 document.getElementById("PayCommisionRows").style.display = "inline";
			 document.getElementById("remarks1").value = "";
			 document.getElementById("remarks").value = "";
			 document.getElementById("remarks2").value = "";
			 document.getElementById("PayCommisionRows2").style.display = "none";
			 
			if(level == ""){
				
				alert("please select level"); 
			}
			}else if(returnvalue == "No")
			{
				document.getElementById("level").disabled=true;
				document.getElementById("level").value = "";
				document.getElementById("demotionLevel").disabled=true;
				document.getElementById("demotionLevel").value = "";
			    document.getElementById("returnValue").value = returnvalue;
			    document.getElementById("newBasicPerScaleMatrix1").disabled=false;
			    document.getElementById("newBasicPerScaleMatrix2").disabled=false;
			    document.getElementById("newBasicPerScaleMatrix3").disabled=false;
			    document.getElementById("newBasicPerScaleMatrix4").disabled=false;
			    document.getElementById("newBasicPerScaleMatrix5").disabled=false;
			    document.getElementById("newBasicPerScaleMatrix6").disabled=false;
			    document.getElementById("remarks1").value = "";
			    document.getElementById("remarks").value = "";
			    document.getElementById("remarks2").value = "";
			    document.getElementById("PayCommisionRows2").style.display = "inline";
			    document.getElementById("PayCommisionRows1").style.display = "none";
				document.getElementById("PayCommisionRows").style.display = "none";
	       }
			else if(returnvalue == "Demotion")
			{
				//alert("in else demotion --"+returnvalue);
				
			    document.getElementById("demotionLevel").disabled=false;
			    var level = document.getElementById("demotionLevel").value;
			    document.getElementById("returnValue").value = returnvalue; 
			    document.getElementById("PayCommisionRows1").style.display = "inline";
				document.getElementById("PayCommisionRows").style.display = "none";
				document.getElementById("remarks1").value = "";
				document.getElementById("remarks").value = "";
				document.getElementById("remarks2").value = "";
				document.getElementById("PayCommisionRows2").style.display = "none";
				 
				if(level == ""){
					
					alert("please select level"); 
				}
	       }
		
		 document.getElementById("DeputWidPromoEmp").disabled=true; 
	}
	
	function getLevelIDData(){
		
		var haction= "LOADLEVELDATA";
	var	 level_id = document.getElementById("leveldrpdwn").value;
	 var uri="ifms.htm?actionFlag=loadEmpDetailsForSevenPCNew";
	 //var uri="ifms.htm?actionFlag=getGradePay&level="+level;
// 	 alert(""+uri);
// 	alert(level_id);
	var url  = "&haction=LOADLEVELDATA&level_id="+level_id;
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: true,
		        parameters:url,
		        onSuccess: function(myAjax) {
		        	 /*  var XMLDoc = myAjax.responseXML.documentElement;
		   		   alert('XMLDoc='+XMLDoc); */
		   		document.getElementById("baic7thpdwn").options.length = 0;
		        	getDetails(myAjax);
// 			          alert("gradePay -- "+gradePay);
		        },
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
	}
	
	function getDetails(myAjax)
	{
		
		
// 		alert('hello');
		   var XMLDoc = myAjax.responseXML.documentElement;
// 		   alert('XMLDoc='+XMLDoc);
		   var item = XMLDoc.getElementsByTagName('item');
// 		   alert('item='+item.length);
        
		   for(var i = 0; i <= item.length; i++){
		   var resStr = item[i].childNodes[0].firstChild.nodeValue;
		   setsevenpayvalue(resStr);}
// 		    alert("resStr---"+resStr);}
// 		 XMLDoc = myAjax.responseXML.documentElement;
//     	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
//     	var level ;
//         var totalLevels = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
    
//     	var optnScheme ;
//     	var count=1; 
		
		
	}
	
	function setsevenpayvalue(resStr) {
		  var x = document.getElementById("baic7thpdwn");
		  var option = document.createElement("option");
		  option.text = resStr;
		  x.add(option);
		}
	function removesevenpayvalue(){
	var select = document.getElementById("baic7thpdwn");
	var length = select.options.length;
	for (i = 0; i < length; i++) {
	  select.options[i] = null;
	}
	}
	
	
	
	function getLevelData(){
		 var type = document.getElementById("type").value;
		
		
		if(type == "Regular"){
			populateBunchingUpBasic();
				 /* var uri="ifms.htm?actionFlag=getGradePay&level="+level;
					var url="";
					//alert("uri--"+uri);
					var myAjax = new Ajax.Request(uri,
						       {
						        method: 'post',
						        asynchronous: false,
						        parameters:url,
						        onSuccess: function(myAjax) {
						        	XMLDoc = myAjax.responseXML.documentElement;
							    	 XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
							    	 var gradePay = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
							          //alert("gradePay -- "+gradePay);
						    		  document.getElementById("gradePay").value = gradePay;
						    		  
								},
						        onFailure: function(){ alert('Something went wrong...')} 
						          } );
				 */
			
		}else{
		 document.getElementById("newBasicPerScaleMatrix1").disabled=false;
		 document.getElementById("newBasicPerScaleMatrix2").disabled=false;
		 document.getElementById("newBasicPerScaleMatrix3").disabled=false;
		 document.getElementById("newBasicPerScaleMatrix4").disabled=false;
		 document.getElementById("newBasicPerScaleMatrix5").disabled=false;
		 document.getElementById("newBasicPerScaleMatrix6").disabled=false;
		}
	}
	function checkEmpType(count){
		      //alert("in fun---"+count);
		      
		    var returnvalue =   document.getElementById("returnValue").value ; 
		  //  alert("returnvalue--"+returnvalue);
		    if(returnvalue == "No"){
		    	
		    	checkValueExixts(count);
				
		    }else if(returnvalue == "Yes"){
		    	
		    	checkValueExistsPromoted(count);
		    }
            else if(returnvalue == "Demotion"){
		    	
            	//alert("in fun Demotion--");
            	checkValueExistsPromoted(count);
		    }
				
	}
	//Demotion
		
	
	//Promoted Employee 
	function checkValueExistsPromoted(count){
		var sevarthId = document.getElementById("empSevarthID").value;
		var returnvalue =   document.getElementById("returnValue").value ; 
		//alert("in fun returnvalue--"+returnvalue);
		var level = "";
		 if(returnvalue == "Demotion"){
			 
			 level = document.getElementById("demotionLevel").value;
			 
		 }else if(returnvalue == "Yes"){
			 
			 level = document.getElementById("level").value;
		 }
		// alert("level--"+level);
		var matrixValue = "";
		
		var newBasicPerScaleMatrix1 = document.getElementById("newBasicPerScaleMatrix1").value;
		var newBasicPerScaleMatrix2 = document.getElementById("newBasicPerScaleMatrix2").value;
		var newBasicPerScaleMatrix3 = document.getElementById("newBasicPerScaleMatrix3").value;
		var newBasicPerScaleMatrix4 = document.getElementById("newBasicPerScaleMatrix4").value;
		var newBasicPerScaleMatrix5 = document.getElementById("newBasicPerScaleMatrix5").value;
		var newBasicPerScaleMatrix6 = document.getElementById("newBasicPerScaleMatrix6").value;
		
		
			if(count == 1){
	  			
	  			matrixValue = newBasicPerScaleMatrix1;
	  		
	  		}
			else if(count == 2){
  			
  			matrixValue = newBasicPerScaleMatrix2;
  		
  		}else if(count == 3){
  			matrixValue = newBasicPerScaleMatrix3;
  			
  		}else if(count == 4){
  			matrixValue = newBasicPerScaleMatrix4;
  			
  		}else if(count == 5){
  			matrixValue = newBasicPerScaleMatrix5;
  			
  		}else if(count == 6){
  			
  			matrixValue = newBasicPerScaleMatrix6;
  		}
	//	alert("count promoted--"+count);
		
		//alert("matrixValue pr--"+matrixValue);
		
		var extBasicMatrixValue = document.getElementById("newBasicPerScaleMatrix11").value;
		//alert("extBasicMatrixValue--"+extBasicMatrixValue);
		
		var uri ="ifms.htm?actionFlag=checkMatrixBasicValue&sevarthId="+sevarthId+"&levelId="+level+"&matrixValue="+matrixValue+"&extBasicMatrixValue="+extBasicMatrixValue;	
		//alert("uri no---"+uri);
		var resUrl  = "";
		myAjax = new Ajax.Request(
				uri,
				{
					method :'post',
					asynchronous :false,
					parameters :resUrl,					
				        onSuccess: function (myAjax) {
				        	//alert("success");
					   checkPromotMatrixDetails(myAjax,count);
					  	},
				        onFailure: function(){ 
					        alert('Something went wrong...');
					        return false;} 
				          } );
		
	
	}
	 function checkPromotMatrixDetails(myAjax,count){
		// alert("hello in checkMatrixDetails remarks--"+count);
		   var XMLDoc = myAjax.responseXML.documentElement;
		   var item = XMLDoc.getElementsByTagName('XMLDOC');
		   var resStr = item[0].childNodes[0].firstChild.nodeValue;
		   var matrixBasicValue = item[0].childNodes[1].firstChild.nodeValue;
		   
		//	alert("resStr--"+resStr+"--newBasicPerScaleMatrix--"+matrixBasicValue);
			var remarks = "";
			 var level = "";
			var newBasicPerScaleMatrix11 = document.getElementById("newBasicPerScaleMatrix11").value;
			var newBasicPerScaleMatrix21 = document.getElementById("newBasicPerScaleMatrix21").value;
			var newBasicPerScaleMatrix31 = document.getElementById("newBasicPerScaleMatrix31").value;
			var newBasicPerScaleMatrix41 = document.getElementById("newBasicPerScaleMatrix41").value;
			
			if(resStr == 0){
				alert("Please enter correct value as per matrix");
				//alert("count 2 = "+count);
                if(count == 1){
		  			
					document.getElementById("newBasicPerScaleMatrix1").value="";
		  		
		  		}else if(count == 2){
		  			
					document.getElementById("newBasicPerScaleMatrix2").value="";
		  		
		  		}else if(count == 3 ){
		  			
		  			document.getElementById("newBasicPerScaleMatrix3").value="";
		  			
		  		}else if(count == 4 ){
		  			
		  			document.getElementById("newBasicPerScaleMatrix4").value="";
		  			
		  		}else if(count == 5){
		  			document.getElementById("newBasicPerScaleMatrix5").value="";
		  			
		  		}else if(count == 6){
		  			
		  			document.getElementById("newBasicPerScaleMatrix6").value="";
		  		}
			}else if(resStr == 1){
			//	alert("in res str = 1--"+resStr);
					 var returnvalue =   document.getElementById("returnValue").value ;
					//alert("returnvalue--"+returnvalue);
					 if(returnvalue == "Demotion"){
						 remarks = "Demotion";
						 level = document.getElementById("demotionLevel").value;
						 //alert(level);
						 
						 if(count == 1 && (matrixBasicValue > newBasicPerScaleMatrix11)){
								alert("Please enter correct value as per matrix");
								document.getElementById("newBasicPerScaleMatrix1").value="";
							}else if(count == 2 && (matrixBasicValue > newBasicPerScaleMatrix11)){
								alert("Please enter correct value as per matrix");
								document.getElementById("newBasicPerScaleMatrix2").value="";
							}
							else if(count == 3 && (matrixBasicValue > newBasicPerScaleMatrix21) ){
								alert("Please enter correct value as per matrix");
								document.getElementById("newBasicPerScaleMatrix3").value="";
								
							}else if(count == 4 && (matrixBasicValue > newBasicPerScaleMatrix21)){
								alert("Please enter correct value as per matrix");
								document.getElementById("newBasicPerScaleMatrix4").value="";
							}
							else if(count == 5 && (matrixBasicValue > newBasicPerScaleMatrix31)){
								alert("Please enter correct value as per matrix");
								document.getElementById("newBasicPerScaleMatrix5").value="";
								
							} else{ 
					         	var sevarthId = document.getElementById("empSevarthID").value;
					         	
								var uri ="ifms.htm?actionFlag=getNewRevisedBasicDetails&sevarthId="+sevarthId+"&levelId="+level+"&matrixBasicValue="+matrixBasicValue+"&count="+count;	
								//alert("uri no---"+uri);
								var resUrl  = "";
								myAjax = new Ajax.Request(
										uri,
										{
											method :'post',
											asynchronous :false,
											parameters :resUrl,					
										        onSuccess: function (myAjax) {
										        	getMatrixDetails(myAjax,remarks,matrixBasicValue);
											  	},
										        onFailure: function(){ 
											        alert('Something went wrong...');
											        return false;} 
										          } );	
							
				
						      }
					 }else if(returnvalue == "Yes"){
						 
					remarks = "Promotion/Timebound";
					 level = document.getElementById("level").value;
						
		         	var sevarthId = document.getElementById("empSevarthID").value;
		         	
					var uri ="ifms.htm?actionFlag=getNewRevisedBasicDetails&sevarthId="+sevarthId+"&levelId="+level+"&matrixBasicValue="+matrixBasicValue+"&count="+count;	
					//alert("uri no---"+uri);
					var resUrl  = "";
					myAjax = new Ajax.Request(
							uri,
							{
								method :'post',
								asynchronous :false,
								parameters :resUrl,					
							        onSuccess: function (myAjax) {
							        	getMatrixDetails(myAjax,remarks,matrixBasicValue);
								  	},
							        onFailure: function(){ 
								        alert('Something went wrong...');
								        return false;} 
							          } );	
				
	
			      }
	 }
			
	 }
 
	 function getMatrixDetails(myAjax,remarks,matrixBasicValue){
			
			var XMLDoc = myAjax.responseXML.documentElement;
			var item = XMLDoc.getElementsByTagName('XMLDOC');
			
			var levelInMAtrix = item[0].childNodes[0].firstChild.nodeValue;
			var newBasicPerScaleMatrix1 = item[0].childNodes[1].firstChild.nodeValue;
			var newBasicPerScaleMatrix2= item[0].childNodes[2].firstChild.nodeValue;	
			var newBasicPerScaleMatrix3= item[0].childNodes[3].firstChild.nodeValue;
			var count= item[0].childNodes[4].firstChild.nodeValue;
		//alert("count->"+count)
			   if(count == 1){
					
					document.getElementById("newBasicPerScaleMatrix3").value =  newBasicPerScaleMatrix1;
					document.getElementById("newBasicPerScaleMatrix5").value =  newBasicPerScaleMatrix2;	
					document.getElementById("newBasicPerScaleMatrix6").value =  newBasicPerScaleMatrix2;	
					document.getElementById("newBasicPerScaleMatrix2").value =  "";
					document.getElementById("newBasicPerScaleMatrix4").value =  "";
					
				}
			 else if(count == 2){
				
				document.getElementById("newBasicPerScaleMatrix4").value =  newBasicPerScaleMatrix1;
				document.getElementById("newBasicPerScaleMatrix6").value =  newBasicPerScaleMatrix2;
				document.getElementById("newBasicPerScaleMatrix3").value =  "";
				document.getElementById("newBasicPerScaleMatrix5").value =  "";
				//document.getElementById("newBasicPerScaleMatrix1").value =  "";
				
			}else if(count == 5){
				//document.getElementById("newBasicPerScaleMatrix1").value =  "";
				//document.getElementById("newBasicPerScaleMatrix2").value =  "";
				//document.getElementById("newBasicPerScaleMatrix3").value =  "";
				//document.getElementById("newBasicPerScaleMatrix4").value =  "";
				document.getElementById("newBasicPerScaleMatrix6").value =  matrixBasicValue;	
				
			}else if(count == 4){
			
				//document.getElementById("newBasicPerScaleMatrix1").value =  "";
				//document.getElementById("newBasicPerScaleMatrix2").value =  "";
				//document.getElementById("newBasicPerScaleMatrix3").value =  "";
				document.getElementById("newBasicPerScaleMatrix5").value =  "";
				//document.getElementById("newBasicPerScaleMatrix6").value =  newBasicPerScaleMatrix2;
				document.getElementById("newBasicPerScaleMatrix6").value =  newBasicPerScaleMatrix1;//test
				
			}else if(count == 3){
				//document.getElementById("newBasicPerScaleMatrix1").value =  "";
				//document.getElementById("newBasicPerScaleMatrix2").value =  "";
				document.getElementById("newBasicPerScaleMatrix4").value =  "";
				document.getElementById("newBasicPerScaleMatrix5").value =  newBasicPerScaleMatrix1;
				document.getElementById("newBasicPerScaleMatrix6").value =  newBasicPerScaleMatrix1;
				
			}
			
			if(document.getElementById("returnValue").value == "Yes"){
			document.getElementById("remarks").value = remarks;
			document.getElementById("level").disabled=true;
			document.getElementById("newBasicPerScaleMatrix1").disabled=true; //test
			document.getElementById("newBasicPerScaleMatrix2").disabled=true; //test
			document.getElementById("newBasicPerScaleMatrix3").disabled=true; //test
			document.getElementById("newBasicPerScaleMatrix4").disabled=true; //test
			document.getElementById("newBasicPerScaleMatrix5").disabled=true; //test
			document.getElementById("newBasicPerScaleMatrix6").disabled=true; //test
			document.getElementById("btnCalc").disabled=true; //test
			
			}else if(document.getElementById("returnValue").value == "No"){
				document.getElementById("remarks2").value = remarks;
				document.getElementById("newBasicPerScaleMatrix1").disabled=true; //test
				document.getElementById("newBasicPerScaleMatrix2").disabled=true; //test
				document.getElementById("newBasicPerScaleMatrix3").disabled=true; //test
				document.getElementById("newBasicPerScaleMatrix4").disabled=true; //test
				document.getElementById("newBasicPerScaleMatrix5").disabled=true; //test
				document.getElementById("newBasicPerScaleMatrix6").disabled=true; //test
				document.getElementById("btnCalc").disabled=true; //test
			    document.getElementById("level").disabled=true;
			    document.getElementById("demotionLevel").disabled=true;
			}
			else if(document.getElementById("returnValue").value == "Demotion"){
				document.getElementById("remarks1").value = remarks;
				document.getElementById("newBasicPerScaleMatrix1").disabled=true; //test
				document.getElementById("newBasicPerScaleMatrix2").disabled=true; //test
				document.getElementById("newBasicPerScaleMatrix3").disabled=true; //test
				document.getElementById("newBasicPerScaleMatrix4").disabled=true; //test
				document.getElementById("newBasicPerScaleMatrix5").disabled=true; //test
				document.getElementById("newBasicPerScaleMatrix6").disabled=true; //test
				document.getElementById("btnCalc").disabled=true; //test
				
			document.getElementById("demotionLevel").disabled=true;
			}
		} 	
			  	
	 
	
	 /* ---------------------------------------- */
		 
	//Regular Employee 
	function checkValueExixts(count){
		
		var newBasicPerScaleMatrix1 = document.getElementById("newBasicPerScaleMatrix1").value;
		var newBasicPerScaleMatrix2 = document.getElementById("newBasicPerScaleMatrix2").value;
		var newBasicPerScaleMatrix3 = document.getElementById("newBasicPerScaleMatrix3").value;
		var newBasicPerScaleMatrix4 = document.getElementById("newBasicPerScaleMatrix4").value;
		var newBasicPerScaleMatrix5 = document.getElementById("newBasicPerScaleMatrix5").value;
		var newBasicPerScaleMatrix6 = document.getElementById("newBasicPerScaleMatrix6").value;
		var matrixValue = "";
		if(count == 1){
  			
  			matrixValue = newBasicPerScaleMatrix1;
  		
  		}else if(count == 2){
  			
  			matrixValue = newBasicPerScaleMatrix2;
  		
  		}else if(count == 3){
  			matrixValue = newBasicPerScaleMatrix3;
  			
  		}else if(count == 4){
  			matrixValue = newBasicPerScaleMatrix4;
  			
  		}else if(count == 5){
  			matrixValue = newBasicPerScaleMatrix5;
  			
  		}else if(count == 6){
  			
  			matrixValue = newBasicPerScaleMatrix6;
  		}
		//alert("count--"+count);
		
		//alert("matrixValue--"+matrixValue);
		var sevarthId = document.getElementById("empSevarthID").value;
		var extLevelId = document.getElementById("extLevelId").value;
		var extBasicMatrixValue = document.getElementById("newBasicPerScaleMatrix11").value;
		var uri ="ifms.htm?actionFlag=checkMatrixBasicValue&sevarthId="+sevarthId+"&levelId="+extLevelId+"&matrixValue="+matrixValue+"&extBasicMatrixValue="+extBasicMatrixValue;	
		//alert("uri no---"+uri);
		var resUrl  = "";
		myAjax = new Ajax.Request(
				uri,
				{
					method :'post',
					asynchronous :false,
					parameters :resUrl,					
				        onSuccess: function (myAjax) {
				        	//alert("success");
					   checkMatrixDetails(myAjax,count);
					  	},
				        onFailure: function(){ 
					        alert('Something went wrong...');
					        return false;} 
				          } );
		
	} 
	 function checkMatrixDetails(myAjax,count){
		 //alert("hello in checkMatrixDetails remarks--"+count);
		   var XMLDoc = myAjax.responseXML.documentElement;
		 
			var item = XMLDoc.getElementsByTagName('XMLDOC');
		
			var resStr = item[0].childNodes[0].firstChild.nodeValue;
		
			var matrixBasicValue = item[0].childNodes[1].firstChild.nodeValue;
			
			//alert("resStr--"+resStr+"--newBasicPerScaleMatrix--"+matrixBasicValue);
			var remarks = "";
			var newBasicPerScaleMatrix11 = document.getElementById("newBasicPerScaleMatrix11").value;
			var newBasicPerScaleMatrix21 = document.getElementById("newBasicPerScaleMatrix21").value;
			var newBasicPerScaleMatrix31 = document.getElementById("newBasicPerScaleMatrix31").value;
			var newBasicPerScaleMatrix41 = document.getElementById("newBasicPerScaleMatrix41").value;
			if(resStr == 0){
				alert("Please enter correct value");
				//alert("count 2 = "+count);
                if(count == 1){
		  			
					document.getElementById("newBasicPerScaleMatrix1").value="";
		  		
		  		}else if(count == 2){
		  			
					document.getElementById("newBasicPerScaleMatrix2").value="";
		  		
		  		}else if(count == 3 ){
		  			
		  			document.getElementById("newBasicPerScaleMatrix3").value="";
		  			
		  		}else if(count == 4 ){
		  			
		  			document.getElementById("newBasicPerScaleMatrix4").value="";
		  			
		  		}else if(count == 5){
		  			document.getElementById("newBasicPerScaleMatrix5").value="";
		  			
		  		}else if(count == 6){
		  			
		  			document.getElementById("newBasicPerScaleMatrix6").value="";
		  		}
			}else if(resStr == 1){
				
				if(count == 1 && (matrixBasicValue != newBasicPerScaleMatrix21)){
					alert("Please enter correct value as per matrix");
					document.getElementById("newBasicPerScaleMatrix1").value="";
				}else if(count == 2 && (matrixBasicValue != newBasicPerScaleMatrix21)){
					alert("Please enter correct value as per matrix");
					document.getElementById("newBasicPerScaleMatrix2").value="";
				}
				else if(count == 3 && (matrixBasicValue != newBasicPerScaleMatrix31) ){
					alert("Please enter correct value as per matrix");
					document.getElementById("newBasicPerScaleMatrix3").value="";
					
				}else if(count == 4 && (matrixBasicValue != newBasicPerScaleMatrix31)){
					alert("Please enter correct value as per matrix");
					document.getElementById("newBasicPerScaleMatrix4").value="";
				}
				else if(count == 5 && (matrixBasicValue != newBasicPerScaleMatrix41)){
					alert("Please enter correct value as per matrix");
					document.getElementById("newBasicPerScaleMatrix5").value="";
				}else{
				//alert("in else");
		           remarks = "Option"; // Regular
		         	var sevarthId = document.getElementById("empSevarthID").value;
				    var extLevelId = document.getElementById("extLevelId").value;
					var uri ="ifms.htm?actionFlag=getNewRevisedBasicDetails&sevarthId="+sevarthId+"&levelId="+extLevelId+"&matrixBasicValue="+matrixBasicValue+"&count="+count;	
					//alert("uri no---"+uri);
					var resUrl  = "";
					myAjax = new Ajax.Request(
							uri,
							{
								method :'post',
								asynchronous :false,
								parameters :resUrl,					
							        onSuccess: function (myAjax) {
								   getMatrixDetails(myAjax,remarks,matrixBasicValue);
								  	},
							        onFailure: function(){ 
								        alert('Something went wrong...');
								        return false;} 
							          } );	
			}	
		}
	 }
   
	/*  function getMatrixDetails(myAjax,remarks,matrixBasicValue){
			
			var XMLDoc = myAjax.responseXML.documentElement;
			var item = XMLDoc.getElementsByTagName('XMLDOC');
			
			var levelInMAtrix = item[0].childNodes[0].firstChild.nodeValue;
			var newBasicPerScaleMatrix1 = item[0].childNodes[1].firstChild.nodeValue;
			var newBasicPerScaleMatrix2= item[0].childNodes[2].firstChild.nodeValue;	
			var newBasicPerScaleMatrix3= item[0].childNodes[3].firstChild.nodeValue;
			var count= item[0].childNodes[4].firstChild.nodeValue;
		//alert("count->"+count)
			   if(count == 1){
					
					document.getElementById("newBasicPerScaleMatrix3").value =  newBasicPerScaleMatrix1;
					document.getElementById("newBasicPerScaleMatrix5").value =  newBasicPerScaleMatrix2;	
					document.getElementById("newBasicPerScaleMatrix6").value =  newBasicPerScaleMatrix2;	
					
				}
			 else if(count == 2){
				
				document.getElementById("newBasicPerScaleMatrix4").value =  newBasicPerScaleMatrix1;
				document.getElementById("newBasicPerScaleMatrix6").value =  newBasicPerScaleMatrix2;	
				
			}else if(count == 5){
				document.getElementById("newBasicPerScaleMatrix6").value =  matrixBasicValue;	
				
			}else if(count == 4){
			
				//document.getElementById("newBasicPerScaleMatrix6").value =  newBasicPerScaleMatrix2;
				document.getElementById("newBasicPerScaleMatrix6").value =  newBasicPerScaleMatrix1;//test
				
			}else if(count == 3){
				document.getElementById("newBasicPerScaleMatrix5").value =  newBasicPerScaleMatrix1;
				document.getElementById("newBasicPerScaleMatrix6").value =  newBasicPerScaleMatrix1;
				
			}
			document.getElementById("remarks").value = remarks;
			if(document.getElementById("returnValue").value == "Yes"){
			document.getElementById("level").disabled=false;
			document.getElementById("newBasicPerScaleMatrix1").disabled=true; //test
			document.getElementById("newBasicPerScaleMatrix2").disabled=true; //test
			document.getElementById("newBasicPerScaleMatrix3").disabled=true; //test
			document.getElementById("newBasicPerScaleMatrix4").disabled=true; //test
			document.getElementById("newBasicPerScaleMatrix5").disabled=true; //test
			document.getElementById("newBasicPerScaleMatrix6").disabled=true; //test
			}else if(document.getElementById("returnValue").value == "No"){
				document.getElementById("newBasicPerScaleMatrix1").disabled=true; //test
				document.getElementById("newBasicPerScaleMatrix2").disabled=true; //test
				document.getElementById("newBasicPerScaleMatrix3").disabled=true; //test
				document.getElementById("newBasicPerScaleMatrix4").disabled=true; //test
				document.getElementById("newBasicPerScaleMatrix5").disabled=true; //test
				document.getElementById("newBasicPerScaleMatrix6").disabled=true; //test
			document.getElementById("level").disabled=true;
			}
		} 	
			   */	

	
	
	function getNewStateEmpBasicData(){
		var sevarthId = document.getElementById("empSevarthID").value;
		var level = document.getElementById("level").value;
		var matrixBasicValue = document.getElementById("matrixBasicValue").value;
		var newDate = document.getElementById("newDate").value;
		remarks = "Promotion/Timebound";
		
	    uri ="ifms.htm?actionFlag=getNewRevisedBasicDetails&sevarthId="+sevarthId+"&levelId="+level+"&matrixBasicValue="+matrixBasicValue+"&newDate="+newDate;	
	  	resUrl  = "";
		myAjax = new Ajax.Request(
				uri,
				{
					method :'post',
					asynchronous :false,
					parameters :resUrl,					
				        onSuccess: function (myAjax) {
					   getMatrixDetails(myAjax,remarks);
						},
				        onFailure: function(){ 
					        alert('Something went wrong...');
					        return false;} 
				          } );
		
	}

	function saveDetails(){
		//alert("in save");
		var level  = null;
// 		var txtAuthorityLetterNo=encodeURI(document.getElementById("txtAuthorityLetterNo").value);
// 		var returnvalue = document.getElementById("returnValue").value;
// 		var type = document.getElementById("type").value;
//  		var txtAuthorityLetterDate ="";
//  		var newBasicPerScaleMatrix6 = "";
//  		if(type == ''){
//  		 txtAuthorityLetterDate = document.getElementById("txtAuthorityLetterDate").value;
// 		if(txtAuthorityLetterNo == "" || txtAuthorityLetterDate == "")
// 		{
// 			alert('Please fill the Authority Details.') ;
// 			hideProgressbar();
// 			if(document.getElementById("btnSave") != null)
// 			{
// 				document.getElementById("btnSave").disabled = false;
// 			}
// 			return false;
// 		}else if(returnvalue == 'Yes'){
// 			 level = document.getElementById("level").value;
// 			 remarks = "Promotion/Timebound";
// 			 newBasicPerScaleMatrix6 = document.getElementById("newBasicPerScaleMatrix6").value;
			
			 
// 		}else if(returnvalue == 'Demotion'){
			
// 			 level = document.getElementById("demotionLevel").value;
// 			 remarks = "Demotion";
// 			 newBasicPerScaleMatrix6 = document.getElementById("newBasicPerScaleMatrix6").value;
		
			
// 		}else if(returnvalue == 'No'){
// 			level = document.getElementById("extLevelId").value;
// 			 remarks = "Option"; //Regular aks
// 			 document.getElementById("remarks2").value = remarks;
// 			 newBasicPerScaleMatrix6 = document.getElementById("newBasicPerScaleMatrix6").value;
// 			 txtAuthorityLetterDate = document.getElementById("txtAuthorityLetterDate").value;
			 
// 		}else{
// 			//alert("in else ");
// 			 document.getElementById("PayCommisionRows2").style.display = "inline";
// 			level = document.getElementById("extLevelId").value;
// 			 remarks = "Option"; //Regular aks
// 			 document.getElementById("remarks2").value = remarks;
// 			 newBasicPerScaleMatrix6 = document.getElementById("newBasicPerScaleMatrix6").value;
// 		}
//  		}else if(type == 'Regular'){
// 			 level = document.getElementById("level").value;
// 			var bunchingBasic = document.getElementById("bunchingBasic").value;
// 			 txtAuthorityLetterDate = document.getElementById("txtWEFDate").value;
// 			var txtAuthLetterDate = document.getElementById("txtAuthorityLetterDate").value;
// 		//	alert("bunchingBasic---"+bunchingBasic);
// 			 if(level == ""){
				 
// 				 alert("please select level");
				 
// 			 }else if(bunchingBasic == "-1"){
				 
// 				 alert("please select 7PC Basic");
				 
// 			 }else if(txtAuthorityLetterDate == ""){
				 
// 				 alert("Please enter WEF Date");
// 			 }else if(txtAuthorityLetterNo == "" || txtAuthLetterDate == "")
// 			{
// 					alert('Please fill the Authority Details.') ;
// 					hideProgressbar();
// 					if(document.getElementById("btnSave") != null)
// 					{
// 						document.getElementById("btnSave").disabled = false;
// 					}
// 					return false;
// 			}else{
// 			 newBasicPerScaleMatrix6 = document.getElementById('bunchingBasic').options[document.getElementById('bunchingBasic').selectedIndex].text;
// 			// alert("newBasicPerScaleMatrix6---"+newBasicPerScaleMatrix6);
// 			 remarks = "Promotion";
// 			 }
// 		 }
        var remarks = document.getElementById("txtAreaRemarks").value;
        var txtAuthorityLetterDate = document.getElementById("txtAuthorityLetterDate").value;
        alert("Order Date ="+txtAuthorityLetterDate);
        var txtAuthorityLetterNo=encodeURI(document.getElementById("txtAuthorityLetterNo").value);
        alert("Order No ="+txtAuthorityLetterNo);
		var sevarthId = document.getElementById("empSevarthID").value;	
	      alert("sevarthId="+sevarthId);
		var e = document.getElementById("baic7thpdwn");
		var newBasicPerScaleMatrix6 = e.options[e.selectedIndex].text;
	      alert("New 7th PC Basic ="+newBasicPerScaleMatrix6);
	    var f = document.getElementById("leveldrpdwn");
	    var level = f.options[f.selectedIndex].value;
	    alert("Level ="+level);
	    
		var url ="ifms.htm?actionFlag=saveStateEMPRevisedBasicDtls&sevarthId="+sevarthId+"&txtAuthorityLetterNo="+txtAuthorityLetterNo+"&txtAuthorityLetterDate="+txtAuthorityLetterDate+"&level="+level+"&newBasicPerScaleMatrix6="+newBasicPerScaleMatrix6+"&remarks="+remarks+"&viewFlag=1";
	     //alert("url--"+url);
		showProgressbar('Please Wait<br>Your request is in progress...');
		document.Revision7PC.action = url ;
		enableAjaxSubmit(true);
		document.Revision7PC.submit();
	} 
	
	//For After joining 01-01-2019 Option/Promotion case
	
	function getPromoWidDeputEmpDetails()
	{
		 var type ="Regular";
		 document.getElementById("PayCommisionRows").style.display = "inline";
		 document.getElementById("level").disabled=false;
		 document.getElementById("type").value = type;
		 document.getElementById("btnCalc").disabled=true; //test
	}
	function populateBunchingUpBasic(){
		//alert("hello");
		var answer = confirm ("Do you want to proceed for stepping up for this employee?");
		if(answer){
			//alert("in if");
			var newBasicAsPerMatrix=document.getElementById("newBasicPerScaleMatrix6").value;
			//alert("newBasicAsPerMatrix--"+newBasicAsPerMatrix);
			var level = document.getElementById('level').value;	
			//alert("level--"+level);
			  if(level != "")
				{
		    var uri ="ifms.htm?actionFlag=getBunchingStateMatrixDetails&level="+level+"&newBasicAsPerMatrix="+newBasicAsPerMatrix;	
		 	//alert("uri-"+uri)
			var url = "";
			myAjax = new Ajax.Request(
					uri,
					{
						method :'post',
						asynchronous :false,
						parameters :url,					
					        onSuccess: function (myAjax) {
					        	//alert("success");
						getBunchingMatrixDetails(myAjax);
							},
					        onFailure: function(){ 
						        alert('Something went wrong...');
						        return false;} 
					          } );
		}else
		{
			var bunchingBasic = document.getElementById('bunchingBasic');
			bunchingBasic.options.length = 0;
			var optn = document.createElement("OPTION");
			optn.text = "-- Select --";
			optn.value = "-1";
			bunchingBasic.options.add(optn);
		}
		
		}
		
	}
	function getBunchingMatrixDetails(myAjax){
		
		XMLDoc = myAjax.responseXML.documentElement;
    	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
    	var level ;
        var totalLevels = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
        //alert("totalLevels---"+totalLevels);
    	var optnScheme ;
    	var count=1;
    	
        level =  document.getElementById('bunchingBasic');
       // alert("basic---"+level);
        level.options.length = 0;
    	var optn = document.createElement("OPTION");
    	optn.text = "-- Select --";
    	optn.value = "-1";
    	level.options.add(optn);
    	while(count<=(2*totalLevels))
		{
			optnScheme = document.createElement("OPTION");
			optnScheme.text = XmlHiddenValues[0].childNodes[count].firstChild.nodeValue;
			optnScheme.title= XmlHiddenValues[0].childNodes[count+1].firstChild.nodeValue;
			optnScheme.value = XmlHiddenValues[0].childNodes[count+1].firstChild.nodeValue;
			document.getElementById("bunchingBasic").options.add(optnScheme);
			count=count+2;
		}
          document.getElementById("PayCommisionRows10").style.display = "inline"; 
		  document.getElementById("PayCommisionRows13").style.display = "inline";  
		  document.getElementById("bunchingBasic").disabled=false;
		  
	}
	
	function get7PCNewBasic(){
		//alert("in fun "+document.getElementById("bunchingBasic").value);
	
		if(document.getElementById("bunchingBasic").value!=-1 && document.getElementById("bunchingBasic").value !=""){
	
			document.getElementById("remarks").value = "Promotion";
		
		}
	
	}
	//date validation
	function compareWithSysDate(DOB,DOJ)
	{
		//alert('inside dateComapare');
		var Date1 = DOB.value;
		var Date2=DOJ.value;
		if(Date1 == '')
		{
			//alert('Please enter Current Date.');
			//DOB.value='';
			DOB.focus();
			return false;
		}
		if(Date2 == '')
		{
			//alert('Please enter date of joining.');
			//DOJ.value='';
			DOJ.focus();	
			return false;
		}
		//alert('inside dateComapare'+Date1);
		//alert('inside dateComapare'+Date2);
		var la_Date1 = new Array();
	    la_Date1 = Date1.split("/");
	    var day1=parseFloat(la_Date1[0]);
	    var month1=parseFloat(la_Date1[1]);
	    var year1=parseFloat(la_Date1[2]);
	    //alert('inside dateComapare 5');
	    var la_Date2 = new Array();
	    la_Date2 = Date2.split("/");
	    var day2=parseFloat(la_Date2[0]);
	    var month2=parseFloat(la_Date2[1]);
	    var year2=parseFloat(la_Date2[2]);
	    //alert('inside dateComapare 6');
	   // year1=year1+60;
	    //var retiringYear=year1+60;
	    var alrtStr='Order Date Should not be greater than Current date.';
	    var flag='>';

	    if (year2 == year1 && month2 == month1 && day2 == day1)
	    { //alert('inside dateComapare 7');
	    	if(flag == '=')
	    	{
	    		alert(alrtStr);
	    		DOJ.focus();
	    		DOJ.value="";
	    	}
	    	else
	    	{
		       return true;
		   }
	    }
	    else if( year2 < year1 )
	    { //alert('inside dateComapare 8');
	        return true;
	    }
	    else if( year2 > year1 && flag != '=')
	    { //alert('inside dateComapare 9');
	        alert(alrtStr);
	        if(flag == '<')
	        {
	        	DOJ.focus();
	        	DOJ.value="";
	    	}
	    	else if(flag == '>')
	    	{
	    		DOJ.focus();
	    		DOJ.value="";
	    	}
	    }
	    else if (flag != '=')
	    { //alert('inside dateComapare 10');
	        if( month2 < month1 )
	        { //alert('inside dateComapare 19');  
	            return true;
	        }
	        else if( month2 > month1 )
	        {   //alert('inside dateComapare 20');  
	             alert(alrtStr);
	             DOJ.value="";
	             DOJ.focus();
	             return false;
	             
	        }
	        else
	        {// alert('inside dateComapare 11');
	            if( day2 < day1 )
	            {
	                return true;
	            }
	            else if( day2 > day1 )
	            { //alert('inside dateComapare 24');  
	                 alert(alrtStr);
	                 DOJ.value="";
	                 DOJ.focus();
	                 return false;

	                 
	            }
	        } //alert('inside dateComapare 12');
	    }                                                                                                                                                                                                                                                                                                                                                                          
	    return false ;
		}

	 document.getElementById("PayCommisionRows3").style.display = "inline";
	 document.getElementById("PayCommisionRows4").style.display = "none";

	</script>
	
<ajax:autocomplete source="txtEmployeeName" target="txtEmployeeName"
	baseUrl="ifms.htm?actionFlag=getEmpNameForAutoCompleteDCPS"
	parameters="searchKey={txtEmployeeName},searchBy={hidsearchByDDOAsst}" className="autocomplete" minimumCharacters="3" indicator="roleIndicatorRegion" />
<ajax:autocomplete source="txtDesignation" target="txtDesignation"
	baseUrl="ifms.htm?actionFlag=getFirstDesignationForAutoComplete"
	parameters="searchKey={txtDesignation},searchBy={hidSearchFromDDOSelection}" className="autocomplete" minimumCharacters="3" indicator="roleIndicatorRegion2" />
