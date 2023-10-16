<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript"
	src="script/dcps/NewRegistrationFormZP.js"></script>
<script type="text/javascript"><!--


function dataValidateForm()
{
var x=document.forms["myForm"]["fname"].value;
if (x==null || x=="")
  {
  alert("First name must be filled out");
  return false;
  }
}




function fieldDisplay()
{
	var appn = document.getElementById("empAppointment").value;

	if(appn == '1')
	{
		document.getElementById("durationDate").style.display="block";
	}
	else
	{
		document.getElementById("durationDate").style.display="none";
	}
	
}

function dateValidation() 
{
	
	var startDate = document.getElementById("startDate").value;
	var endDate = document.getElementById("endDate").value;
	var appn = document.getElementById("empAppointment").value;
	

	
	if(appn == '1')
	{
		if (startDate == "" || startDate == null) 
		{
			alert('Please fill the Sanction From Date');
			return false;
		}

		else if (endDate == "" || endDate == null) 
		{
			alert('Please fill the Sanction End Date');
			return false;
		}
	}
	else
	{
		return 0;
	}
	
}


function NumericFormat(formfield)
{	
	var val;
	
    if((window.event.keyCode>64 && window.event.keyCode<91) || window.event.keyCode>47 && window.event.keyCode<58) 
	{
		val=formfield.value;
		if(val[1]!=null)
		{
			if(val[1].length>1)
			{
				window.event.keyCode=0;
			}
		}
	}
	else
	{
		window.event.keyCode=0;
	}
}
function compareDatesForEmp(fieldName1,fieldName2,alrtStr,flag)
{
	
	var Date1 = fieldName1.value;
	var Date2 = fieldName2.value;
    var la_Date1 = new Array();
    la_Date1 = Date1.split("/");
    var day1=parseFloat(la_Date1[0]);
    var month1=parseFloat(la_Date1[1]);
    var year1=parseFloat(la_Date1[2]);

    var la_Date2 = new Array();
    la_Date2 = Date2.split("/");
    var day2=parseFloat(la_Date2[0]);
    var month2=parseFloat(la_Date2[1]);
    var year2=parseFloat(la_Date2[2]);

    if (year2 == year1 && month2 == month1 && day2 == day1)
    {
    	if(flag == '=')
    	{
    		alert(alrtStr);
    	    fieldName2.focus();
    	    fieldName2.value="";
    	}
    	else
    	{
	        return true;
	    }
    }
    else if( year2 > year1 )
    {
        return true;
    }
    else if( year2 < year1 && flag != '=')
    {
        alert(alrtStr);
        if(flag == '<')
        {
    	    fieldName2.focus();
    	    fieldName2.value="";
    	}
    	else if(flag == '>')
    	{
    	    fieldName2.focus();
    	    fieldName2.value="";
    	}
    }
    else if (flag != '=')
    {
        if( month2 > month1 )
        {
            return true;
        }
        else if( month2 < month1 )
        {     
             alert(alrtStr);
             if(flag == '<')
	        {
    		    fieldName2.focus();
    		    fieldName2.value="";
	    	}
    		else if(flag == '>')
    		{
	    	    fieldName2.focus();
	    	    fieldName2.value="";
    		}
        }
        else
        {
            if( day2 > day1 )
            {
                return true;
            }
            else if( day2 < day1 )
            {
                 alert(alrtStr);
                 if(flag == '<')
			     {
    			    fieldName2.focus();
    			    fieldName2.value="";
			   	}
		    	else if(flag == '>')
    			{
    			    fieldName2.focus();
    			    fieldName2.value="";
		    	}
            }
        }
    }
    return true ;
}

function ratePerHours(){
	//alert("Hello");
	var cmbPayCommission = document.getElementById("cmbPayCommission").value;
	//alert("cmbPayCommission: "+cmbPayCommission);
	var empAppointment = document.getElementById("empAppointment").value;
	var ratePerHour = document.getElementById("ratePerHour");
	//alert("empAppointment: "+empAppointment);
	if((cmbPayCommission == "700337" || cmbPayCommission == "700016") && empAppointment == "4"){ // changed by Tejashree
		//var ratePerHour = document.getElementById("ratePerHour");
		ratePerHour.readOnly=false; 
	}else{
		ratePerHour.readOnly=true;
		document.getElementById("ratePerHour").value="";
	}
}

--></script>
<style> /*  added contents by pratik 03-08-23 */
div#tcontent2 input, div#tcontent2 select {
    max-width: 250px;
}
</style>

<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"
	scope="request" />
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="EMPVO" value="${resValue.lObjEmpData}"></c:set>
<c:set var="EMPOFFICEVO" value="${resValue.lObjDdoOfficeVO}"></c:set>
<c:set var="EMPPAYROLLVO" value="${resValue.lObjEmpPayrollData}"></c:set>
<c:set var="ddoCode" value="${resValue.DDOCODE}"></c:set>
<c:set var="draftFlag" value="${resValue.DraftFlag}"></c:set>
<c:set var="ParentDeptIdByDefault"
	value="${resValue.ParentDeptIdByDefault}"></c:set>
<c:set var="UserList" value="${resValue.UserList}" />
<c:set var="empList" value="${resValue.empList}"></c:set>

<c:set var="officeList" value="${resValue.OfficeList}" />
<c:set var="DdoOfficeVO" value="${resValue.DdoOfficeVO}" />
<c:set var="DdoOfficeOrNot" value="${resValue.DdoOfficeOrNot}" />


<c:if test="${resValue.EditForm != null && resValue.EditForm == 'N'}">
	<c:set var="varDisabled" scope="page" value="disabled='disabled'"></c:set>
	<c:set var="varReadOnly" scope="page" value="readOnly = 'readOnly'"></c:set>

	<c:set var="varImageDisabled" scope="page" value="style='display:none'"></c:set>
	<c:set var="varLabelDisabled" scope="page"
		value="style='display: none;' "></c:set>
</c:if>

<c:if test="${resValue.EditForm == null && resValue.EditForm != 'N'}">
	<c:set var="varRemarksDisabled" scope="page"
		value="style='display:none'"></c:set>
</c:if>


<!--Added by Mayuresh-->

<c:if test="${EMPVO != null && EMPVO.empAppointment == '1'}">
	<c:set var="varDurationDate" scope="page"
		value="style='display:inline'"></c:set>
</c:if>



<c:set var="varPayInPayBandAndGradePayRow" scope="page"
	value="style='display:none'"></c:set>
<c:if test="${EMPVO != null && EMPVO.payCommission == '700016'}">
	<c:set var="varPayInPayBandAndGradePayRow" scope="page"
		value="style='display:contents'"></c:set> <!--added contents by pratik 03-08-23  -->
</c:if>



<fieldset class="tabstyle"><legend> <b><fmt:message
	key="CMN.CADREINPARENTDEPT" bundle="${dcpsLables}"></fmt:message></b> </legend>
	<input type="hidden" name="hidPFDByDefault" id="hidPFDByDefault" value="${resValue.ParentDeptIdByDefault}" />
<table width="100%" align="center" cellpadding="4" cellspacing="4">

	<tr>

		

		<td width="15%" align="left"><fmt:message
			key="CMN.PARENTFIELDDEPT" bundle="${dcpsLables}"></fmt:message></td>

		<td width="35%" align="left"><select name="listParentDept"
			id="listParentDept" style="width: 360px; display: block;"
			onchange="enableReasonForPFD();" disabled="disabled">
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			<c:forEach var="ParentDept" items="${resValue.listParentDept}">
				<c:choose>
					<c:when
						test="${EMPVO.parentDept==ParentDept.id || ParentDeptIdByDefault == ParentDept.id }">
						<option value="${ParentDept.id}" selected="selected"><c:out
							value="${ParentDept.desc}"></c:out></option>
					</c:when>
					<c:otherwise>
						<option value="${ParentDept.id}" title="${ParentDept.desc}"><c:out
							value="${ParentDept.desc}"></c:out></option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
		</td>

		<!--<td width="15%" align="left"><fmt:message
			key="CMN.CHANGEPARENTDEPT" bundle="${dcpsLables}"></fmt:message></td>
		<td width="20%" align="left"><input type="checkbox"
			name="cbChangeParentDept" id="cbChangeParentDept"
			value="ChangeParentDept" onclick="hideUnhideParentList();"${varDisabled} >
		</td>

	-->

	</tr>

	<tr${varLabelDisabled}>
		<td colspan="2" align="left"
			style="font-size: smaller; font-style: italic"><fmt:message
			key="MSG.CHANGEPFD" bundle="${dcpsLables}"></fmt:message></td>
		<td colspan="2"></td>
	</tr>

	<tr>
		<td width="15%" align="left"><fmt:message
			key="CMN.REASONFORCHANGEINPFD" bundle="${dcpsLables}"></fmt:message></td>
		<td width="35%"><input name="reasonForChangeInPFD" type="text"
			id="reasonForChangeInPFD" size="48" style="width: 48" readonly="readonly"
			${varDisabled} 

value="${EMPVO.reasonChangePFD}" /></td>
	</tr>

	<tr>
		<td width="15%" align="left"><fmt:message key="CMN.CADRE"
			bundle="${dcpsLables}"></fmt:message></td>
		<td width="35%" align="left"><select name="cmbCadre"
			id="cmbCadre" style="width: 360px"
			onChange="populateGroup();populateDesig();"${varDisabled} >

			<c:forEach var="cadre" items="${resValue.CADRELIST}">

				<c:choose>
					<c:when test="${EMPVO.cadre == cadre.id}">
						<option value="${cadre.id}" selected="selected"><c:out
							value="${cadre.desc}"></c:out></option>
					</c:when>
					<c:otherwise>
						<option value="${cadre.id}"><c:out value="${cadre.desc}"></c:out></option>


					</c:otherwise>


				</c:choose>
			</c:forEach>
		</select> <!--<label class="mandatoryindicator"${varLabelDisabled} >*</label>-->

		</td>



		<td width="15%" align="left"><fmt:message key="CMN.GROUP"
			bundle="${dcpsLables}"></fmt:message></td>
		<td width="35%" align="left"><input type="text" id="txtGroup"
			size="48" name="txtGroup" value="${resValue.GroupName}"
			readonly="readonly" /></td>

	</tr>


	<tr>
		<td width="15%" align="left"><fmt:message
			key="CMN.SUPERANNUATIONAGE" bundle="${dcpsLables}"></fmt:message></td>
		<td width="35%" align="left"><input type="text"
			id="txtSuperAnnuation" size="48" name="txtSuperAnnuation"
			value="${resValue.SuperAnnAge}" readonly="readonly" /></td>

		<td width="15%" align="left"><fmt:message
			key="CMN.SUPERANNUATIONDATE" bundle="${dcpsLables}"></fmt:message></td>
		<td width="35%" align="left"><input type="text" size="48"
			name="txtSuperAnnDate" id="txtSuperAnnDate" maxlength="10"
			onkeypress="digitFormat(this);dateFormat(this);" readonly="readonly"
			onBlur="validateDate(txtSuperAnnDate);compareDates(this,currDate1,'Date of retiring should be less than current date.','<');"
			value="${resValue.SuperAnnDate}" /></td>

	</tr>

	<tr>

		<td width="15%" align="left"><fmt:message key="CMN.PAYCOMMISSION"
			bundle="${dcpsLables}"></fmt:message></td>

		<c:set var="varDisablePayScale" scope="page" value="" />
		<c:set var="varDisbleLabelPayScale" scope="page" value="" />
		<c:if test="${EMPVO!=null}">
			<c:if test="${EMPVO.payCommission == '700337'}">
				<c:set var="varDisablePayScale" scope="page"
					value="disabled='disabled'" />
				<c:set var="varDisbleLabelPayScale" scope="page"
					value="style='display: none;' " />
			</c:if>
		</c:if>
		<c:if test="${resValue.EditForm != null && resValue.EditForm == 'N'}">
			<c:set var="varDisablePayScale" scope="page"
				value="disabled='disabled'" />
			<c:set var="varDisbleLabelPayScale" scope="page"
				value="style='display: none;' " />
		</c:if>

		<td width="35%" align="left"><select name="cmbPayCommission"
			id="cmbPayCommission" style="width: 360px;"
			onchange="GetScalePostfromDesg(); checkForPIPBAndGradePay();ratePerHours();"${varDisabled} >
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			<c:forEach var="PayCommission" items="${resValue.listPayCommission}">
				<c:choose>
					<c:when test="${EMPVO!=null}">
						<c:choose>
							<c:when test="${EMPVO.payCommission == PayCommission.lookupId}">
								<option value="${PayCommission.lookupId}" selected="selected"
									title="${PayCommission.lookupDesc}"><c:out
									value="${PayCommission.lookupDesc}"></c:out></option>
							</c:when>
							<c:otherwise>
								<c:choose>
					<c:when test="${PayCommission.lookupId=='700015'|| PayCommission.lookupId=='700016'|| PayCommission.lookupId=='700337' || PayCommission.lookupId=='10001198186'}">
						<option value="${PayCommission.lookupId}"
							title="${PayCommission.lookupDesc}"><c:out
							value="${PayCommission.lookupDesc}"></c:out></option>
					</c:when>
					</c:choose>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
					<c:choose>
					<c:when test="${PayCommission.lookupId=='700015'|| PayCommission.lookupId=='700016'|| PayCommission.lookupId=='700337' || PayCommission.lookupId=='10001198186'}">
						<option value="${PayCommission.lookupId}"
							title="${PayCommission.lookupDesc}"><c:out
							value="${PayCommission.lookupDesc}"></c:out></option>
					</c:when>
					</c:choose>
					</c:otherwise>
					
				</c:choose>

			</c:forEach>
		</select> <label class="mandatoryindicator"${varLabelDisabled}>*</label></td>

		<!--<td width="15%" align="left">Appointment</td>
			
		<td width="20%" align="left">
					<select name="empAppointment" id="empAppointment" style="width: 60%"  onchange="fieldDisplay();">
						<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
							<option value="10001198181">Tenure</option>
							<option value="10001198182">Adhoc</option>
							<option value="10001198183">Contract</option>
							<option value="10001198184">Clock Hour Basis</option>
							<option value="10001198185">Honorary</option>
							
					</select>
		</td>

	-->



		<td width="15%" align="left">Appointment</td>
		<td width="35%" align="left"><select name="empAppointment"
			id="empAppointment" style="width: 360px" onchange="fieldDisplay();ratePerHours();"${varDisabled} >
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			<c:forEach var="empAppointment" items="${resValue.Appointments}">
				<c:choose>
					<c:when test="${EMPVO!=null}">
						<c:choose>
							<c:when test="${empAppointment[0]==EMPVO.empAppointment}">
								<option value="${empAppointment[0]}" selected="selected"><c:out
									value="${empAppointment[1]}" /></option>
							</c:when>
							<c:otherwise>
								<option value="${empAppointment[0]}"
									title="${empAppointment[0]}"><c:out
									value="${empAppointment[1]}" /></option>
							</c:otherwise>
						</c:choose>
					</c:when>

					<c:otherwise>
						<option value="${empAppointment[0]}" title="${empAppointment[0]}"><c:out
							value="${empAppointment[1]}" /></option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select></td>

	</tr>
 
 
 
 
 
 
 
	<tr>
		<td width="15%" align="left">Rate Per Hours</td>
		<td width="15%" align="left">
		    <c:if test="${(EMPVO.payCommission  == 700337 || EMPVO.payCommission  == 700016) && EMPVO.empAppointment == 4}"><!-- changed by tejahsree for testing-->
		    
			     <input type="text" id="ratePerHour" name="ratePerHour"  value="${EMPVO.ratePerHour}" ${varDisabled}>
			</c:if>
			<c:if test="${(EMPVO.payCommission  != 700337 || EMPVO.payCommission  != 700016 )|| EMPVO.empAppointment != 4 }"><!-- changed by tejahsree for testing-->
			  
			     <input type="text" id="ratePerHour" name="ratePerHour" readonly="true" value="${EMPVO.ratePerHour}" ${varDisabled}>
			</c:if>
		</td>
	</tr>
	
	


	<tr id="durationDate" ${varDurationDate} style="display: none">
		<td width="15%" align="left">Sanction From Date</td>
		<td class="fieldLabel" width="35%"><input type="text" size="20" 
			name="startDate" id="startDate" maxlength="10"
			onkeypress="digitFormat(this);dateFormat(this);"
			onBlur="validateDate(this);" value="${resValue.sanctionStart}" ${varDisabled}/>
		&nbsp; <img src='images/CalendarImages/ico-calendar.gif'
			onClick='window_open("startDate",375,570)' style="cursor: pointer;" />
		<label class="mandatoryindicator"${varLabelDisabled}>*</label></td>

		<td width="15%" align="left">Sanction To Date</td>
		<td class="fieldLabel" id="endDate2" width="35%"><input type="text" size="20"
			name="endDate" id="endDate" maxlength="10"
			onkeypress="digitFormat(this);dateFormat(this);"
			onBlur="validateDate(this);" value="${resValue.sanctionEnd}" ${varDisabled}/>
		&nbsp; <img src='images/CalendarImages/ico-calendar.gif'
			onClick='window_open("endDate",375,570)' style="cursor: pointer;" />
		<label class="mandatoryindicator"${varLabelDisabled}>*</label></td>
	</tr>

	<tr>
		<td width="15%" align="left"><fmt:message key="CMN.DESIGNATION"
			bundle="${dcpsLables}"></fmt:message></td>

		<td width="35%" align="left"><select name="cmbDesignation"
			id="cmbDesignation" style="width: 360px"
			onchange="GetPostfromDesg('${resValue.UserType }'); dateValidation();"${varDisabled} >

			<c:forEach var="designationFirstVar"
				items="${resValue.lLstDesignation}">
				<c:choose>
					<c:when test="${EMPVO!=null}">
						<c:out value="${designationFirstVar.id}"></c:out>
						<c:choose>
							<c:when test="${EMPVO.designation == designationFirstVar.id}">
								<option value="${designationFirstVar.id}" selected="selected"><c:out
									value="${designationFirstVar.desc}"></c:out></option>
							</c:when>
							<c:otherwise>
								<option value="${designationFirstVar.id}"><c:out
									value="${designationFirstVar.desc}"></c:out></option>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<option value="${designationFirstVar.id}"><c:out
							value="${designationFirstVar.desc}"></c:out></option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select> <label class="mandatoryindicator"${varLabelDisabled}>*</label></td>

		<td width="15%" align="left"><fmt:message key="CMN.PAYSCALE"
			bundle="${dcpsLables}"></fmt:message></td>
		<td width="35%" align="left"><select name="cmbPayScale"
			id="cmbPayScale" style="width: 360px"
			onChange="validateBasicPay();checkForPIPBAndGradePay();validatePIPBAndChangeBasic();"${varDisablePayScale} 

>
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			<c:forEach var="PayScaleList" items="${resValue.PayScaleList}">
				<c:choose>

					<c:when test="${EMPVO.payScale == PayScaleList.id}">
						<option value="${PayScaleList.id}" selected="selected"><c:out
							value="${PayScaleList.desc}"></c:out></option>
					</c:when>
					<c:otherwise>
						<option value="${PayScaleList.id}"><c:out
							value="${PayScaleList.desc}"></c:out></option>
					</c:otherwise>


				</c:choose>

			</c:forEach>

		</select> <label class="mandatoryindicator" id="lableMandatoryForPayscale"${varDisbleLabelPayScale}  >*</label></td>
	</tr>

	<tr id="payInPayBandAndGradePayRow"${varPayInPayBandAndGradePayRow} >

		<td width="15%" align="left"><fmt:message key="CMN.PAYINPAYBAND"
			bundle="${dcpsLables}"></fmt:message></td>
		<td width="35%" align="left"><input type="text"
			id="txtPayInPayBand" size="48" name="txtPayInPayBand"
			onBlur="validatePIPBAndChangeBasic();"
			onkeypress="digitFormat(this);" value="${EMPVO.payInPayBand}" ${varDisabled}  />
		<label class="mandatoryindicator" id="lableMandatoryForPayInPayBand"${varLabelDisabled}>*</label>
		</td>

		<td width="15%" align="left"><fmt:message key="CMN.GRADEPAY"
			bundle="${dcpsLables}"></fmt:message></td>
		<td width="35%" align="left"><input type="text" size="48"
			name="txtGradePay" id="txtGradePay" maxlength="10"
			onBlur="validatePIPBAndChangeBasic();"
			onkeypress="digitFormat(this);" ${varDisabled}
			onBlur=""
			value="${EMPVO.gradePay}" readonly="readonly"/> <label class="mandatoryindicator"
			id="lableMandatoryForGradePay"${varLabelDisabled}>*</label></td>
	<tr>
		<td width="15%" align="left"><fmt:message key="CMN.BASICPAY"
			bundle="${dcpsLables}"></fmt:message></td>
		<td width="35%" align="left"><input type="text" id="txtBasicPay"
			size="48" name="txtBasicPay" value="${EMPVO.basicPay }" maxlength="7"
			onblur="validatePIPBAndChangeBasic();validateBasicPay();" ${varDisabled}  />
		<label class="mandatoryindicator"${varLabelDisabled}>*</label></td>



		<td width="15%" align="left"><fmt:message key="CMN.CURRENTPOST"
			bundle="${dcpsLables}"></fmt:message></td>


		<td width="35%" align="left"><select name="cmbCurrentPost"
			id="cmbCurrentPost" style="width: 360px" onChange=""${varDisabled}>
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			<c:choose>

				<c:when
					test="${EMPPAYROLLVO.postId!=null || resValue.CurrentPostList == null}">
					<c:choose>

						<c:when
							test="${EMPPAYROLLVO.postId!=null || resValue.CurrentPostList == null}">
							<option value="${EMPPAYROLLVO.postId}" selected="selected"><c:out
								value="${resValue.postDesc}"></c:out></option>
							<c:forEach var="CurrentPostList"
								items="${resValue.CurrentPostList}">
								<option value="${CurrentPostList.id}"><c:out
									value="${CurrentPostList.desc}"></c:out></option>
							</c:forEach>
						</c:when>

						<c:otherwise>
							<c:forEach var="CurrentPostList"
								items="${resValue.CurrentPostList}">
								<option value="${CurrentPostList.id}"><c:out
									value="${CurrentPostList.desc}"></c:out></option>
							</c:forEach>
						</c:otherwise>
					</c:choose>

				</c:when>
				<c:otherwise>
					<c:forEach var="CurrentPostList"
						items="${resValue.CurrentPostList}">
						<c:choose>

							<c:when
								test="${EMPPAYROLLVO.postId!=null || resValue.CurrentPostList == null}">
								<option value="${EMPPAYROLLVO.postId}" selected="selected"><c:out
									value="${resValue.postDesc}"></c:out></option>
							</c:when>


							<c:otherwise>

								<option value="${CurrentPostList.id}"><c:out
									value="${CurrentPostList.desc}"></c:out></option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</select> <label class="mandatoryindicator"${varLabelDisabled} >*</label></td>


	</tr>

	<tr>
		<td width="15%" align="left"><fmt:message key="CMN.CURRENTOFFICE"
			bundle="${dcpsLables}"></fmt:message></td>

		<td width="35%" align="left"><select name="cmbCurrentOffice"
			id="cmbCurrentOffice" style="width: 360px"
			onChange="getOfficeDetails();"${varDisabled} >
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			<c:forEach var="officeName" items="${resValue.lLstOfficesForPost}">
				<c:choose>
					<c:when test="${EMPVO.currOff == officeName.id}">
						<option value="${officeName.id}" selected="selected"><c:out
							value="${officeName.desc}"></c:out></option>
					</c:when>
					<c:otherwise>
						<option value="${officeName.id}"><c:out
							value="${officeName.desc}"></c:out></option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select> <label class="mandatoryindicator"${varLabelDisabled} >*</label></td>

		<td width="15%" align="left"><fmt:message
			key="CMN.DESIGNATIONFIRSTAPNTMNT" bundle="${dcpsLables}"></fmt:message></td>

		<td width="35%" align="left" style="padding-top: 30px;"><input
			type="text" name="cmbFirstDesignation" id="cmbFirstDesignation"
			size="48" value="${EMPVO.firstDesignation}" ${varDisabled}/> <span
			id="roleIndicatorRegion" style="display: none"> <img
			src="./images/busy-indicator.gif" /></span>

		<table>
			<tr${varLabelDisabled}>
				<td align="left" style="font-size: smaller; font-style: italic"><fmt:message
					key="MSG.POST" bundle="${dcpsLables}"></fmt:message></td>
			</tr>
		</table>
		</td>
	</tr>









	<tr>
		<!--<td width="15%" align="left"><fmt:message key="CMN.DOJPARENTDEPT"
			bundle="${dcpsLables}"></fmt:message></td>
			
			
			-->

		<td width="15%" align="left">Date of initial appointment in
		parent Institute</td>

		<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"
			value="${EMPVO.appointmentDate}" var="dojParentDept" />

		<td width="35%" align="left" style="font-size: smaller"><input
			type="hidden" name="currDate1" id="currDate1"
			value="${resValue.lDtCurDate}" /> <input type="text" size="20"
			name="txtJoinParentDeptDate" id="txtJoinParentDeptDate"
			maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
			onBlur="validateDate(txtJoinParentDeptDate);compareDates(this,document.getElementById('currDate1'),'Date of joining parent dept should be less 

than current date.','<');
			compareDates(txtJoiningDate,txtJoinParentDeptDate,'Date of joining parent dept should be greater than Joining Date','<');"
			value="${dojParentDept}" ${varReadOnly}  /> <img
			src='images/CalendarImages/ico-calendar.gif' width='20'
			onClick='window_open("txtJoinParentDeptDate",375,570)'
			style="cursor: pointer;" ${varImageDisabled} /> <!--<label class="mandatoryindicator"${varLabelDisabled} >*</label>
			--><fmt:message key="CMN.DATEFORMAT" bundle="${dcpsLables}"></fmt:message>
		</td>



		<!--<td width="15%" align="left"><fmt:message key="CMN.OFFICEADDRESS"
			bundle="${dcpsLables}"></fmt:message></td>
			
			
			-->

		<td width="15%" align="left">Institution Address</td>
		<td width="35%" align="left"><input type="text"
			id="txtOfficeAddress" size="48" name="txtOfficeAddress"
			readOnly="readonly" value="${resValue.dcpsDdoOfficeAddress1}" /></td>
	</tr>

	<tr>
		<td width="15%" align="left"><fmt:message key="CMN.TELEPHONE1"
			bundle="${dcpsLables}"></fmt:message></td>
		<td width="35%" align="left"><input type="text"
			id="txtOfficeContactNo1" size="48" name="txtOfficeContactNo1"
			onkeypress="digitFormat(this);"
			onblur="checkLength(txtOfficeContactNo1,'Office contact number');"
			readOnly="readonly" value="${EMPOFFICEVO.dcpsDdoOfficeTelNo1}" /></td>
		<td width="15%" align="left"><fmt:message key="CMN.TELEPHONE2"
			bundle="${dcpsLables}"></fmt:message></td>
		<td width="35%" align="left"><input type="text"
			id="txtOfficeContactNo2" size="48" name="txtOfficeContactNo2"
			onkeypress="digitFormat(this);"
			onblur="checkLength(txtOfficeContactNo2,'Office contact number');"
			readOnly="readonly" value="${EMPOFFICEVO.dcpsDdoOfficeTelNo2}" /></td>
	</tr>

	<tr>
		<td width="15%" align="left"><fmt:message key="CMN.MOBILE"
			bundle="${dcpsLables}"></fmt:message></td>








		<td width="35%" align="left"><input type="text"
			id="txtOfficeMobile" size="48" name="txtOfficeMobile"
			onkeypress="digitFormat(this);"
			onblur="checkLength(txtOfficeMobile,'Office contact number');"
			readOnly="readonly" value="${EMPOFFICEVO.dcpsDdoOfficeFax}" /></td>




		<!--<td width="15%" align="left"><fmt:message key="CMN.OFFICEEMAILID"
			bundle="${dcpsLables}"></fmt:message></td>
			
			
			-->
		<td width="15%" align="left">Institution Email ID</td>
		<td width="35%" align="left"><input type="text"
			name="txtOfficeEmailId" id="txtOfficeEmailId"
			value="${EMPOFFICEVO.dcpsDdoOfficeEmail}" size="48"
			readOnly="readonly" /></td>
	</tr>

	<tr>


		<td width="15%" align="left"><fmt:message key="CMN.CITYCLASS"
			bundle="${dcpsLables}"></fmt:message></td>
		<td width="35%" align="left"><input type="text"
			id="txtOfficeCityClass" size="48" name="txtOfficeCityClass"
			readOnly="readonly" value="${EMPOFFICEVO.dcpsDdoOfficeCityClass}" />
		</td>


		<td width="15%" align="left"><fmt:message key="CMN.DOJPOST"
			bundle="${dcpsLables}"></fmt:message></td>
		<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"
			value="${EMPPAYROLLVO.currPostJoiningDate}" var="dojPost" />
		<td width="35%" align="left" style="font-size: smaller"><input
			type="text" name="txtJoinPostDate" id="txtJoinPostDate" size="20"
			maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
			
			<%--
			onBlur="validateDate(txtJoinPostDate);compareDates(this,document.getElementById('currDate1'),'Date of joining post should be less than current date.','<');
			compareDates(txtJoiningDate,txtJoinPostDate,'Date of joining current post should be greater than date of joining','<');"
			--%>
			onBlur="validateDate(this);compareDatesForEmp(this,document.getElementById('currDate1'),'Date of joining post should be less than current date.','<');
			compareDatesForEmp(document.getElementById('txtJoiningDate'),this,'Date of joining post should be greater than Joining Date','<');"
			
			value="${dojPost}" ${varReadOnly}  /> <img
			src='images/CalendarImages/ico-calendar.gif' width='20'
			onClick='window_open("txtJoinPostDate",375,570)'
			style="cursor: pointer;" ${varImageDisabled} /> <label
			class="mandatoryindicator"${varLabelDisabled} >*</label><fmt:message
			key="CMN.DATEFORMAT" bundle="${dcpsLables}"></fmt:message></td>

	</tr>

	<tr>
		<td width="15%" align="left"><fmt:message key="CMN.REMARKS"
			bundle="${dcpsLables}"></fmt:message></td>
		<td width="20%"><input name='txtRemarks' id="txtRemarks" rows="4"
			cols="50" ${varDisabled} value="${EMPVO.remarks}" /></td>


		<td width="15%" align="left"><fmt:message key="CMN.TYPEEMP"
			bundle="${dcpsLables}"></fmt:message></td>
		<td width="20%"><c:choose>
			<c:when
				test="${EMPVO.typeEmp=='Teaching' || EMPVO.typeEmp=='Non teaching'}">
				<c:if test="${EMPVO.typeEmp=='Teaching'}">
					<input type="radio" name="Teaching" id="Teaching" value="Teaching"
						checked="checked" ${varDisabled}/>Teaching
        <input type="radio" name="Teaching" id="Teaching"
						value="Non teaching" ${varDisabled} />Non teaching
		</c:if>
				<c:if test="${EMPVO.typeEmp=='Non teaching'}">
					<input type="radio" name="Teaching" id="Teaching" value="Teaching" ${varDisabled}/>Teaching
        <input type="radio" name="Teaching" id="Teaching"
						value="Non teaching" checked="checked" ${varDisabled} />Non teaching
		</c:if>
			</c:when>
			<c:otherwise>
				<input type="radio" name="Teaching" id="Teaching" value="Teaching" ${varDisabled}/>Teaching
        <input type="radio" name="Teaching" id="Teaching"
					value="Non teaching" ${varDisabled} />Non teaching
		</c:otherwise>
		</c:choose></td>


	</tr>

	<tr>
		<td width="15%" align="left"><fmt:message key="CMN.INDNO"
			bundle="${dcpsLables}"></fmt:message></td>
		<td width="35%"><input type="text" name='txtinduvisalno' size="48"
			id="txtinduvisalno" value="${EMPVO.indivusalno}"
			onkeypress="alphanumeric(this);" ${varDisabled}/> <label
			class="mandatoryindicator"${varLabelDisabled} >*</label></td>

		<td width="15%" align="left"><fmt:message key="CMN.INDDATE"
			bundle="${dcpsLables}"></fmt:message></td>
		<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy"
			value="${EMPVO.indivisualDate}" var="dojPost" />

		<td width="35%" align="left" style="font-size: smaller"><input
			type="text" name='txtinduvisalDate' id="txtinduvisalDate" size="20"
			onkeypress="digitFormat(this);dateFormat(this);" onblur="validateDate(this);" value="${dojPost}" ${varDisabled}/>
		<img src='images/CalendarImages/ico-calendar.gif' width='20'
			onClick='window_open("txtinduvisalDate",375,570)'
			style="cursor: pointer;" ${varImageDisabled} /> <label
			class="mandatoryindicator"${varLabelDisabled} >*</label> <fmt:message
			key="CMN.DATEFORMAT" bundle="${dcpsLables}"></fmt:message></td>
	</tr>

	<tr>
		<td colspan="4" style="font-size: smaller; font-style: italic"${varLabelDisabled}><fmt:message
			key="MSG.APPROVAL" bundle="${dcpsLables}"></fmt:message></td>
	</tr>
</table>
</fieldset>


<ajax:autocomplete source="cmbFirstDesignation"
	target="cmbFirstDesignation"
	baseUrl="ifms.htm?actionFlag=getFirstDesignationForAutoComplete"
	parameters="searchKey={cmbFirstDesignation}" className="autocomplete"
	minimumCharacters="2" indicator="roleIndicatorRegion" />


