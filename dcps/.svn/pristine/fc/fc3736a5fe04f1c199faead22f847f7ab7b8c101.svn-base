<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>    
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<script type="text/javascript">

function reset()
{
	//document.getElementById("cmbDepartment").value = "Department";
	document.getElementById("cmbRegion").value = "Region";
	//document.getElementById("cmbDistrict").value = "District";
	//document.getElementById("cmbOffice").value = "Office";
	document.getElementById("cmbYear").value = "Year";
	document.getElementById("cmbMonth").value = "Month";
}

function generateReport()
{
	var year=document.getElementById("cmbYear").value;
	var month1=document.getElementById("cmbMonth").value;

	if(year != 'Year' && month1 != 'Month'){
		//var department=document.getElementById("cmbDepartment").value ;
		//var region=document.getElementById("cmbRegion").value;
		//var district=document.getElementById("cmbDistrict").value ;
		//var office=document.getElementById("cmbOffice").value ;
		var finYear=document.getElementById("cmbYear").value ;
		var month=document.getElementById("cmbMonth").value;
		var treaCode= document.getElementById("hidTreaCode").value;
		var ddoOffice= document.getElementById("hidDDOOffice").value;
		var region=treaCode.charAt(0);
		
		showProgressbar();
		var url = 'ifms.htm?actionFlag=reportService&reportCode=160354&action=generateReport&region='+region+'&finYear='+finYear+'&month='+month+'&ddoOffice='+ddoOffice;
		document.stateLevelReport.action = url ;
		document.stateLevelReport.submit();
		hideProgressbar();
	}else{
		alert('Please Select Financial Year And Month');
	}
}

function getCurMonthYear()
{
	var date = new Date();
	var day = date.getDate();
	var mon = date.getMonth()+1;
	var year = date.getFullYear();	

	document.getElementById("cmbYear").value = year;
	
	if(mon==1){
		document.getElementById("cmbMonth").value = "1:January";
	}
	else if(mon==2){
		document.getElementById("cmbMonth").value = "2:February";
	}else if(mon==3){
		document.getElementById("cmbMonth").value = "3:March";
	}
	else if(mon==4)
		document.getElementById("cmbMonth").value = "4:April";
				else if(mon==5)
					document.getElementById("cmbMonth").value = "5:May";
					else if(mon==6)
						document.getElementById("cmbMonth").value = "6:June";
						else if(mon==7)
							document.getElementById("cmbMonth").value = "7:July";
							else if(mon==8)
								document.getElementById("cmbMonth").value = "8:August";
								else if(mon==9)
									document.getElementById("cmbMonth").value = "9:September";
									else if(mon==10)
										document.getElementById("cmbMonth").value = "10:October";
										else if(mon==11)	
											document.getElementById("cmbMonth").value = "11:November";
										else if(mon==12)	
											document.getElementById("cmbMonth").value = "12:December";
}
</script>

<c:set var="resultObj" value="${result}"/>
<c:set var="resValue" value="${resultObj.resultValue}"/>
<input type="hidden" name='hidTreaCode' id='hidTreaCode' value="${resValue.tryCode}">
<input type="hidden" name='hidDDOOffice' id='hidDDOOffice' value="${resValue.tryCode}:${resValue.tryName}">
<hdiits:form name="stateLevelReport" encType="multipart/form-data" validate="true" method="post">
<table width="100%">
		<tr style='display:none'>		
			<td width="15%" >
				Region :
			</td>
			<td width="35%" >
				<select id="cmbRegion" name="cmbRegion" tabindex="10" style="width: 50%">
					<option value="Region" selected="selected">------Select Region------</option>
					<option value="1">Konkan</option>
					<option value="2">Pune</option>
					<option value="3">Aurangabad</option>
					<option value="4">Nagpur</option>
					<option value="5">Nasik</option>
					<option value="6">Amravati</option>
					<option value="7">Mumbai</option>
				</select>
			</td>
			<td width="15%">				
			</td>
			<td width="35%">				
			</td>
		</tr>
		<tr>		
			<td width="15%">
				Year :
			</td>
			<td width="35%">
				<select id="cmbYear" name="cmbYear" tabindex="10" style="width: 50%">
					<option value="Year" selected="selected">------Select Year------</option>
					<c:forEach items ="${resValue.yearList}" var="Year">
						<option value="${Year.desc}">${Year.desc}</option>
					</c:forEach>
				</select><label class="mandatoryindicator">*</label>
			</td>
			<td width="15%">
				Month :
			</td>
			<td width="35%">
				<select id="cmbMonth" name="cmbMonth" tabindex="10" style="width: 50%">
					<option value="Month" selected="selected">------Select Month------</option>
					<c:forEach items ="${resValue.monthList}" var="Month">
						<option value="${Month.id}:${Month.desc}">${Month.desc}</option>	     	
					</c:forEach>
				</select><label class="mandatoryindicator">*</label>
			</td>
		</tr>
</table>
<center>
	<hdiits:button name="btnGenerateReport" id="btnGenerateReport" type="button" caption="Generate Report" style="width: 15%" onclick="generateReport()"/>
	<hdiits:button name="btnReset" id="btnReset" type="button" caption="Reset" onclick="reset();"/>
</center>
</hdiits:form>

<script>
getCurMonthYear();
</script>