<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript"
	src="script/dcps/NewRegistrationFormZP.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script language="JavaScript" src="script/dcps/dcpsChanges.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/login/md5.js"/>"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>




<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLabels"
	scope="request" />
<fmt:setBundle basename="resources.dcps.DcpsAlerts" var="dcpsAlerts"
	scope="request" />

<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="VOList" value="${resValue.DESELECTEMPLIST}"></c:set>
<c:set var="ViewList" value="${resValue.VIEWSELECTEMPLIST}"></c:set>
<c:set var="postExpFlag" value="${resValue.postExpFlag}"></c:set>
<c:set var="postEndDate" value="${resValue.postEndDate}"></c:set>
<c:set var="ddoCode" value="${resValue.ddoCode}"></c:set>
<c:set var="empList" value="${resValue.empList}"></c:set>
<c:set var="Empsize" value="${resValue.Empsize}"></c:set>
<c:set var="action" value="${resValue.action}"></c:set>
<c:set var="actions" value="${resValue.actions}"></c:set>
<c:set var="check" value="${resValue.check}"></c:set>
<c:set var="treasuryno" value="${resValue.treasuryno}"></c:set>
<c:set var="treasury" value="${resValue.treasury}"></c:set>
<c:set var="hdnCounter" value="1" />

<hdiits:form name="DCPSForwardedFormsList" id="DCPSForwardedFormsList"
	encType="multipart/form-data" validate="true" method="post">

	<div align="center">

	<table border="1" bordercolor="black" align="center" id="searchTable">
		<tr>

			<td
				style="background-color: #F7E7D7; color: rgb(202, 97, 12); font-size: small; font-style: normal; font-weight: bold">
			Validate Period:</td>

			<td><select id="treasuryList">
				<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="treaList" items="${resValue.treasury}">


					<c:choose>
						<c:when test="${treaList.id == treasuryno}">
							<option value="${treaList.id}" selected="selected"><c:out
								value="${treaList.desc}"></c:out></option>
						</c:when>


						<c:otherwise>
							<option value="${treaList.id}"><c:out
								value="${treaList.desc}"></c:out></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select> <label class="mandatoryindicator">*</label></td>



		</tr>
		<tr>
			<td colspan="4" align="center">
			<center><input type="button" onclick="getListOfEmp();"
				class="buttontag" value="View Records" /></center>
			</td>
		</tr>
	</table>
	
	<c:if test="${check==true}">
	<c:choose>
		<c:when test="${ViewList!= null }">

	<fieldset style="width: 100%" class="tabstyle"><legend
		id="headingMsg"> <b>EMPLOYEE DETAILS</b> </legend> <% int srno=1; %> <display:table
		list="${ViewList}" id="voo" cellpadding="5" style="width:100%"
		requestURI="">


		<display:column headerClass="datatableheader" class="oddcentre"
			style="text-align:center;width:8%" sortable="true"
			title="Employee Name">
			<input type="hidden" id="empName" name="empName" />
			<c:out value="${voo[0]}"></c:out>
			<input type="hidden" id="txtSevaarthId" value="${voo[2]}" />
			<input type="hidden" id="valueI" name="valueI" value="<%=srno %>">
		</display:column>

		<display:column headerClass="datatableheader" class="oddcentre"
			style="text-align:center;width:8%" sortable="true" title="DCPS Id">
			<label id="dcpsIdd">${voo[1]}</label>
		</display:column>
				<display:column headerClass="datatableheader" class="oddcentre"
			style="text-align:center;width:8%" sortable="true" title="PRAN No">
			<label id="pranNo">${voo[11]}</label>
		</display:column>
		
		<display:column headerClass="datatableheader" class="oddcentre"
			style="text-align:center;width:8%" sortable="true" title="Period">
			<label id="PPeriod">${voo[9]}</label>
		</display:column>


	<display:column headerClass="datatableheader" class="oddcentre"
			style="text-align:center;width:8%" sortable="true"
			title="Employee Contribution">
			<input type="hidden" id="Contribution" name="Contribution" />
			<c:out value="${voo[4]}"></c:out>
		</display:column>

		<display:column headerClass="datatableheader" class="oddcentre"
			style="text-align:center;width:8%" sortable="true"
			title="Employer Contribution">
			<input type="hidden" id="Contribution1" name="Contribution1" />
			<c:out value="${voo[5]}"></c:out>
		</display:column>
		
		<display:column headerClass="datatableheader" class="oddcentre"
			style="text-align:center;width:8%" sortable="true"
			title="Interest on Employee Contribution">
			<input type="hidden" id="empInt" name="empInt" />
			<c:out value="${voo[6]}"></c:out>
		</display:column>
		
		<display:column headerClass="datatableheader" class="oddcentre"
			style="text-align:center;width:8%" sortable="true"
			title="Interest on Employer Contribution">
			<input type="hidden" id="employerInt" name="employerInt" />
			<c:out value="${voo[7]}"></c:out>
		</display:column>
		

	<display:column headerClass="datatableheader" class="oddcentre"
			style="text-align:center;width:8%" sortable="true" title="Total">
			<input type="hidden" id="Total" name="Total" />
			<c:out value="${voo[8]}"></c:out>
		</display:column>
		
		<display:column headerClass="datatableheader" class="oddcentre"
			style="text-align:center;width:8%" sortable="true" title="Remarks">
			<input type="text" id="remarks${voo_rowNum}" name="remarks" readonly="readonly" value="${voo[12]}"/>
<%-- 			<c:out value="${voo[12]}"></c:out>	 --%>
		
		</display:column>


 <c:choose>
		<c:when test="${voo[10]== 1}">
			<display:column headerClass="datatableheader" class="oddcentre"
			style="text-align:center;width:8%" sortable="true" title="Approve">
			<c:out value="Verified"></c:out>
		</display:column>
		
			<display:column headerClass="datatableheader" class="oddcentre"
			style="text-align:center;width:8%" sortable="true" title="Reject">
		<input type="button" id="reject" value="Reject" onclick="cannotReject();"/>
		
			
		</display:column>
		
		</c:when>
<c:otherwise>

		<display:column headerClass="datatableheader" class="oddcentre"
			style="text-align:center;width:8%" sortable="true" title="Approve">  
			<input type="button" id="Verify" value="Approve" onclick="verifyData('${voo[2]}','${voo[3]}');" />

		
		</display:column>


		<display:column headerClass="datatableheader" class="oddcentre"
			style="text-align:center;width:8%" sortable="true" title="Reject">
			<input type="button" id="Reject${voo_rowNum}" value="Reject" onclick="rejectData('${voo[2]}','${voo[3]}','${voo_rowNum}');" />
	
		</display:column>
</c:otherwise>
	</c:choose>




		<c:set var="hdnCounter" value="${hdnCounter+1 }" />
		<%srno=srno+1; %>

	</display:table> <input type="hidden" id="Period" name="Period" value="${voo[3] }" />

	</fieldset>
	</c:when>
		<c:otherwise>
			<center><c:out value="No Records found "></c:out></center>

		</c:otherwise>
	</c:choose>
</c:if>
	<input type="hidden" id="hdnCounter" name="hdnCounter"
		value="${hdnCounter}" />
	<div align="center"><!--<hdiits:button	name="btnReject" id="btnReject" type="button"
											captionid="BTN.REJECTT" bundle="${dcpsLabels}"
											onclick="rejectData();"/>								
								
											--></div>



	</div>





</hdiits:form>




<script>
	function validate() {
		var treasList = document.getElementById("treasuryList").value.trim();
		if (treasList == -1) {
			alert('Please Select Validate Period');
			return false;
		}
		return true;
	}

	function verifyData(sevaarthId, period) {

		//alert('In verifydata');
		//alert(sevaarthId);
		//alert(period);
		//var finalSelectedEmp = 0;
		//var totalEmp = document.getElementById("hdnCounter").value ;
		//alert("I amin Script funvction"+totalEmp);
		//var iValue=document.getElementById("valueI").value;
		//alert('Value of serial number'+iValue);
		//var Empsize = document.getElementById("Empsize").value ;

		//var totalEmp=Number(20);
		//alert('Empsize is'+Empsize);
		//alert("hii");
		var totalSelectedEmp = 0;
		var txtSevaarthId = "";
		var txtPeriod = "";
		var empContri = "";
		var employerContri = "";
		//alert("Value of employeeList is"+Empsize);
		var empInterest = "";
		var employerInterest = "";
		var total = "";
		var k = "";

		//txtSevaarthId = txtSevaarthId + document.getElementById("txtSevaarthId").value ;
		//alert("txtSevaarthId"+txtSevaarthId);
		//txtPeriod= txtPeriod + document.getElementById("Period").value ;
		//alert("Period"+txtPeriod);

		var answer = confirm("Are you sure, you want to Approve the details?");
		if (answer) {
			//alert("hiiii");
			//alert("txtSevaarthId"+txtSevaarthId);
			//alert("txtPeriod"+txtPeriod);
			//document.getElementById("actionFlag").value  = "verifyLegacyData";

			//document.getElementById("txtSevaarthId").value  = txtSevaarthId;
			//alert("txtSevaarthId111"+txtSevaarthId);

			var url = "ifms.htm";

			url = "./hrms.htm?actionFlag=verifyDcpsLegacyData&txtSevaarthId="
					+ sevaarthId + "&Period11=" + period;
		//	alert(url);
			document.DCPSForwardedFormsList.action = url;
			document.DCPSForwardedFormsList.submit();

		}

	}
</script>


<script>
function getListOfEmp(){
	
	if(validate())
	{
		showProgressbar();

      var treasList=document.getElementById("treasuryList").value.trim();
      //alert("treasList"+treasList);
      if (treasList!=null)   	 
  	     {  
         	//alert("222222222222222222");getLegacyEmpsContri
		   document.DCPSForwardedFormsList.action = "ifms.htm?actionFlag=getLegacyEmpsContri&treasno="+treasList;
		   document.DCPSForwardedFormsList.submit();
  	     }
	
	}
}

	function rejectData(sevaarthId, period,rowNo) {

		//alert('In rejectData');
		//alert(sevaarthId);
		//alert(period);


		document.getElementById("remarks"+rowNo).readOnly=false;
		
		var finalSelectedEmp = 0;
		var totalEmp = document.getElementById("hdnCounter").value;
		//alert("I amin Script funvction"+totalEmp);
		var iValue = document.getElementById("valueI").value;

		var totalSelectedEmp = 0;
		var txtSevaarthId = "";
		var txtPeriod = "";
		var empContri = "";
		var employerContri = "";
		//alert("Value of employeeList is"+Empsize);
		var empInterest = "";
		var employerInterest = "";
		var total = "";
		var k = "";
		//txtSevaarthId = txtSevaarthId + document.getElementById("txtSevaarthId"+k).value ;
		//txtPeriod= txtPeriod + document.getElementById("Period"+k).value ;
		//alert("Period"+txtPeriod);
		var remarks =document.getElementById("remarks"+rowNo).value;
		if(remarks ==null || remarks==""){
	alert("Please enter the remarks");
	return false;
		}
		

		var answer = confirm("Are you sure, you want to Reject the details?");
		if (answer) {
			//alert("hiiii");
			//alert("txtSevaarthId"+txtSevaarthId);
			//alert("txtPeriod"+txtPeriod);
			//document.getElementById("actionFlag").value  = "verifyLegacyData";

			//document.getElementById("txtSevaarthId").value  = txtSevaarthId;
			//alert("txtSevaarthId111"+txtSevaarthId);

			var url = "ifms.htm";

			url = "./hrms.htm?actionFlag=rejectLegacyData&txtSevaarthId="
					+ sevaarthId + "&Period12=" + period+"&remarks="+remarks;
			//alert(url);
			document.DCPSForwardedFormsList.action = url;
			document.DCPSForwardedFormsList.submit();

		}

	}

	function createTxTfile(flag, flagFile) {

		document.getElementById("createtxtButn").disabled = true;
		//alert("1");
		//	  var FINtype=document.getElementById("finTypelst").value.trim();
		var treasList = document.getElementById("treasuryList").value.trim();
		if (treasList != null) {
			var url = "";
			var uri = "ifms.htm?actionFlag=createNewFilesForSRKANSDL&flag="
					+ flag + "&flagFile=" + flagFile + "&treasno=" + treasList;
			;

			//window.location.href=uri;
			document.DCPSForwardedFormsList.action = uri;
			document.DCPSForwardedFormsList.submit();
			//hideProgressbar();
			//alert("2");
		}

		return true;

	}
</script>














<script>
	var action = '${action}';

	if (action == "success") {

		alert("Data is rejected Successfully");
		url = "./hrms.htm?actionFlag=verifyDcpsLegacyData";
		self.location.href = url;

	}
</script>


<script>
	var actions = '${actions}';

	if (actions == "successfull") {

		alert("Data is verified Successfully");
		url = "./hrms.htm?actionFlag=verifyDcpsLegacyData";
		self.location.href = url;

	}
</script>


<script>
function cannotReject()
{
alert("You cannot Reject data since it is already verified");
	}
</script>


