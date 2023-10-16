<%@ include file="../core/include.jsp" %>
<%@ page import="java.util.*"%>
<%@page language="java" import="com.tcs.sgv.core.valueobject.ResultObject;" %>
<c:set var="name" value="${param.addrName}" />
<c:set var="isPopulateOnAjax" value="${param.isPopulateOnAjax}" />
<c:set var="showFieldGroupOnNotAvail" value="${param.showFieldGroupOnNotAvail}" />
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.hod.common.CommonLablesAddress" var="commonLablesAddress" scope="request"/>
<fmt:message bundle="${commonLablesAddress}" key="ADDRESS.TYPE" var="defaultTitle"></fmt:message>

<c:set var="title" value="${param.addressTitle}" />
<c:set var="collapseAddress" value="${param.collapseAddress}" />
<c:choose>
	<c:when test="${empty title}">
	   <c:set var="finalTitleAddress" value="${defaultTitle}"></c:set>
	</c:when>
	<c:otherwise>
	   <c:set var="finalTitleAddress" value="${title}"></c:set>
	</c:otherwise>
</c:choose>

<%
	ResultObject result=(ResultObject)request.getAttribute("result");
	Map resultMap=(Map)result.getResultValue();
	Map myMap=(Map)resultMap.get(request.getParameter("addrName"));
	
	String isPopulateOnAjax = request.getParameter("isPopulateOnAjax") != null ? request.getParameter("isPopulateOnAjax") : "";
	
	if ((myMap != null && myMap.size() > 0) || (isPopulateOnAjax.equals("Y")))
	{	
%>

<c:set var="addressMap" value="<%=myMap%>"> </c:set> 
<c:set var="addressVO" value="${addressMap.addressVO}"> </c:set> 

<hdiits:fieldGroup titleCaption="${finalTitleAddress}" id="fieldGroupAddress${name}" collapseOnLoad="${collapseAddress}">

	<table class="tabtable" id="villagetbl${name}" style="display:none">
		<tr>
			<td class="fieldLabel" width="25%"><hdiits:caption
				captionid="ADDRESS.Housename" bundle="${commonLablesAddress}" /></td>
			<td width="1%">:</td>
			<td class="fieldLabel" width="24%" id="villagetbl${name}HouseName">
				<c:if test="${addressVO.houseName eq ''}">-</c:if>
				<c:if test="${addressVO.houseName ne ''}">${addressVO.houseName}</c:if>
			</td>

			<td class="fieldLabel" width="25%"><hdiits:caption
				captionid="ADDRESS.Faliyu" bundle="${commonLablesAddress}" /></td>
			<td width="1%">:</td>
			<td class="fieldLabel" width="24%" id="villagetbl${name}Faliyu">
				<c:if test="${addressVO.faliyu eq ''}">-</c:if>
				<c:if test="${addressVO.faliyu ne ''}">${addressVO.faliyu}</c:if>
			</td>
		</tr>
		<tr>
			<TD class="fieldLabel" width="25%"><hdiits:caption
				captionid="ADDRESS.Important_land_mark"
				bundle="${commonLablesAddress}" /></TD>
			<td width="1%">:</td>
			<td class="fieldLabel" width="24%" id="villagetbl${name}ImportantLandmark">
				<c:if test="${empty addressVO.impLandmark}">-</c:if>
				<c:if test="${not empty addressVO.impLandmark}">${addressVO.impLandmark.landmarkName}</c:if>
			</td>

			<TD class="fieldLabel" width="25%"><hdiits:caption
				captionid="ADDRESS.Other_details" bundle="${commonLablesAddress}" /></TD>
			<td width="1%">:</td>
			<td id="villagetbl${name}OtherDtls">
				<c:if test="${addressVO.otherDetails eq ''}">-</c:if>
				<c:if test="${addressVO.otherDetails ne ''}">${addressVO.otherDetails}</c:if>
			</td>
		</tr>
		<tr>
			<td class="fieldLabel" width="25%"><hdiits:caption
				captionid="ADDRESS.VILLAGE" bundle="${commonLablesAddress}" /></td>
			<td width="1%">:</td>
			<td class="fieldLabel" width="24%" id="villagetbl${name}Village">${addressVO.cmnVillageMst.villageName}</td>

			<TD class="fieldLabel" width="25%"><hdiits:caption
				captionid="ADDRESS.PINCODE" bundle="${commonLablesAddress}" /></TD>
			<td width="1%">:</td>
			<td id="villagetbl${name}PinCode">
				<c:if test="${addressVO.pincode eq ''}">-</c:if>
				<c:if test="${addressVO.pincode ne ''}">${addressVO.pincode}</c:if>
			</td>
		</tr>
		<tr>
			<td class="fieldLabel" width="25%"><hdiits:caption
				captionid="ADDRESS.Taluka" bundle="${commonLablesAddress}" /></td>
			<td width="1%">:</td>
			<td class="fieldLabel" width="24%" id="villagetbl${name}Taluka">${addressVO.cmnTalukaMst.talukaName}</td>

			<td class="fieldLabel" width="25%"><hdiits:caption
				captionid="ADDRESS.District" bundle="${commonLablesAddress}" /></td>
			<td width="1%">:</td>
			<td class="fieldLabel" width="24%" id="villagetbl${name}District">${addressVO.cmnDistrictMst.districtName}</td>
		</tr>
	</table>

	<table id="citytbl${name}" style="display:none" class="tabtable">
		<tr>
			<td class="fieldLabel" width="25%"><hdiits:caption
				captionid="ADDRESS.Housename" bundle="${commonLablesAddress}" /></td>
			<td width="1%">:</td>
			<td class="fieldLabel" width="24%" id="citytbl${name}HouseName">
				<c:if test="${addressVO.houseName eq ''}">-</c:if>
				<c:if test="${addressVO.houseName ne ''}">${addressVO.houseName}</c:if>
			</td>

			<td class="fieldLabel" width="24%"><hdiits:caption
				captionid="ADDRESS.Society_name" bundle="${commonLablesAddress}" /></td>
			<td width="1%">:</td>
			<td class="fieldLabel" width="25%" id="citytbl${name}SocietyName">
				<c:if test="${addressVO.socBuildName eq ''}">-</c:if>
				<c:if test="${addressVO.socBuildName ne ''}">${addressVO.socBuildName}</c:if>
			</td>
		</tr>

		<tr>
			<td class="fieldLabel" width="25%"><hdiits:caption
				captionid="ADDRESS.Street" bundle="${commonLablesAddress}" /></td>
			<td width="1%">:</td>
			<td class="fieldLabel" width="24%" id="citytbl${name}Street">
				<c:if test="${addressVO.street eq ''}">-</c:if>
				<c:if test="${addressVO.street ne ''}">${addressVO.street}</c:if>
			</td>

			<td class="fieldLabel" width="25%"><hdiits:caption
				captionid="ADDRESS.Area" bundle="${commonLablesAddress}" /></td>
			<td width="1%">:</td>
			<td class="fieldLabel" width="24%" id="citytbl${name}Area">${addressVO.area}</td>
		</tr>

		<tr>
			<TD class="fieldLabel" width="25%"><hdiits:caption
				captionid="ADDRESS.Important_land_mark"
				bundle="${commonLablesAddress}" /></TD>
			<td width="1%">:</td>
			<td class="fieldLabel" width="24%" id="citytbl${name}ImportantLandmark">
				<c:if test="${empty addressVO.impLandmark}">-</c:if>
				<c:if test="${not empty addressVO.impLandmark}">${addressVO.impLandmark.landmarkName}</c:if>
			</td>

			<td class="fieldLabel" width="25%"><hdiits:caption
				captionid="ADDRESS.Other_details" bundle="${commonLablesAddress}" /></td>
			<td width="1%">:</td>
			<td class="fieldLabel" width="24%" id="citytbl${name}OtherDtls">
				<c:if test="${addressVO.otherDetails eq ''}">-</c:if>
				<c:if test="${addressVO.otherDetails ne ''}">${addressVO.otherDetails}</c:if>
			</td>
		</tr>
		<tr>
			<td class="fieldLabel" width="25%"><hdiits:caption
				captionid="ADDRESS.CITY" bundle="${commonLablesAddress}" /></td>
			<td width="1%">:</td>
			<td class="fieldLabel" width="24%" id="citytbl${name}City">${addressVO.cmnCityMst.cityName}</td>

			<TD class="fieldLabel" width="25%"><hdiits:caption
				captionid="ADDRESS.PINCODE" bundle="${commonLablesAddress}" /></TD>
			<td width="1%">:</td>
			<td class="fieldLabel" width="24%" id="citytbl${name}PinCode">
				<c:if test="${addressVO.pincode eq ''}">-</c:if>
				<c:if test="${addressVO.pincode ne ''}">${addressVO.pincode}</c:if>
			</td>
		</tr>
	</table>

	<table id="employeeAddresstbl${name}" style="display:none" class="tabtable">
		<tr>
			<td class="fieldLabel" width="25%"><hdiits:caption
				captionid="ADDRESS.Housename" bundle="${commonLablesAddress}" /></td>
			<td width="1%">:</td>
			<td class="fieldLabel" width="24%" id="employeeAddresstbl${name}HouseName">
				<c:if test="${addressVO.houseName eq ''}">-</c:if>
				<c:if test="${addressVO.houseName ne ''}">${addressVO.houseName}</c:if>
			</td>

			<td class="fieldLabel" width="24%"><hdiits:caption
				captionid="ADDRESS.Society_name" bundle="${commonLablesAddress}" /></td>
			<td width="1%">:</td>
			<td class="fieldLabel" width="25%" id="employeeAddresstbl${name}SocietyName">
				<c:if test="${addressVO.socBuildName eq ''}">-</c:if>
				<c:if test="${addressVO.socBuildName ne ''}">${addressVO.socBuildName}</c:if>
			</td>
		</tr>

		<tr>
			<td class="fieldLabel" width="25%"><hdiits:caption
				captionid="ADDRESS.Street" bundle="${commonLablesAddress}" /></td>
			<td width="1%">:</td>
			<td class="fieldLabel" width="24%" id="employeeAddresstbl${name}Street">
				<c:if test="${addressVO.street eq ''}">-</c:if>	
				<c:if test="${addressVO.street ne ''}">${addressVO.street}</c:if>
			</td>

			<td class="fieldLabel" width="25%"><hdiits:caption
				captionid="ADDRESS.Area" bundle="${commonLablesAddress}" /></td>
			<td width="1%">:</td>
			<td class="fieldLabel" width="24%" id="employeeAddresstbl${name}Area">${addressVO.area}</td>
		</tr>
		<tr>
			<td class="fieldLabel" width="25%"><hdiits:caption
				captionid="ADDRESS.District_City" bundle="${commonLablesAddress}" /></td>
			<td width="1%">:</td>
			<td class="fieldLabel" width="24%" id="employeeAddresstbl${name}District">
				<c:if test="${addressVO.cmnCountryMst.countryCode eq '1'}">
					${addressVO.cmnDistrictMst.districtName}
				</c:if>
				<c:if test="${addressVO.cmnCountryMst.countryCode ne '1'}">
					${addressVO.districtName}
				</c:if>
			</td>

			<td class="fieldLabel" width="25%"><hdiits:caption
				captionid="ADDRESS.Taluka" bundle="${commonLablesAddress}" /></td>
			<td width="1%">:</td>
			<td class="fieldLabel" width="24%" id="employeeAddresstbl${name}Taluka">
				<c:if test="${addressVO.cmnStateMst.stateCode eq '1'}">
					<c:if test="${empty addressVO.cmnTalukaMst}">-</c:if>
					<c:if test="${not empty addressVO.cmnTalukaMst}">${addressVO.cmnTalukaMst.talukaName}</c:if>
				</c:if>
				<c:if test="${addressVO.cmnStateMst.stateCode ne '1'}">
					<c:if test="${addressVO.talukaName eq ''}">-</c:if>	
					<c:if test="${addressVO.talukaName ne ''}">${addressVO.talukaName}</c:if>
				</c:if>
			</td>
		</tr>
		<tr>
			<td class="fieldLabel" width="25%"><hdiits:caption
				captionid="ADDRESS.Other_details" bundle="${commonLablesAddress}" /></td>
			<td width="1%">:</td>
			<td class="fieldLabel" width="24%" id="employeeAddresstbl${name}OtherDtls">
				<c:if test="${addressVO.otherDetails eq ''}">-</c:if>
				<c:if test="${addressVO.otherDetails ne ''}">${addressVO.otherDetails}</c:if>
			</td>
			
			<TD class="fieldLabel" width="25%"><hdiits:caption
				captionid="ADDRESS.PINCODE" bundle="${commonLablesAddress}" /></TD>
			<td width="1%">:</td>
			<td class="fieldLabel" width="24%" id="employeeAddresstbl${name}PinCode">
				<c:if test="${addressVO.pincode eq ''}">-</c:if>
				<c:if test="${addressVO.pincode ne ''}">${addressVO.pincode}</c:if>
			</td>
		</tr>
		
	</table>


	<table class="tabtable" id="othertbl${name}" style="display:none">
		<tr>
			<td class="fieldLabel" width="25%"><hdiits:caption
				captionid="ADDRESS.Housename" bundle="${commonLablesAddress}" /></td>
			<td width="1%">:</td>
			<td class="fieldLabel" width="24%" id="othertbl${name}HouseName">
				<c:if test="${addressVO.houseName eq ''}">-</c:if>
				<c:if test="${addressVO.houseName ne ''}">${addressVO.houseName}</c:if>
			</td>

			<td class="fieldLabel" width="25%"><hdiits:caption
				captionid="ADDRESS.Society_name" bundle="${commonLablesAddress}" /></td>
			<td width="1%">:</td>
			<td class="fieldLabel" width="24%" id="othertbl${name}SocietyName">
				<c:if test="${addressVO.socBuildName eq ''}">-</c:if>
				<c:if test="${addressVO.socBuildName ne ''}">${addressVO.socBuildName}</c:if>
			</td>
		</tr>
		<tr>
			<td class="fieldLabel" width="25%"><hdiits:caption
				captionid="ADDRESS.Area" bundle="${commonLablesAddress}" /></td>
			<td width="1%">:</td>
			<td class="fieldLabel" width="24%" id="othertbl${name}Area">${addressVO.area}</td>

			<td class="fieldLabel" width="25%"><hdiits:caption
				captionid="ADDRESS.CITYVILLAGE" bundle="${commonLablesAddress}" /></td>
			<td width="1%">:</td>
			<td class="fieldLabel" width="24%" id="othertbl${name}CityVillege">${addressVO.cityVilllageName}</td>
		</tr>
		<tr>
			<TD class="fieldLabel" width="25%"><hdiits:caption
				captionid="ADDRESS.PINCODE" bundle="${commonLablesAddress}" /></TD>
			<td width="1%">:</td>
			<td id="othertbl${name}PinCode">
				<c:if test="${addressVO.pincode eq ''}">-</c:if>
				<c:if test="${addressVO.pincode ne ''}">${addressVO.pincode}</c:if>
			</td>

			<td class="fieldLabel" width="25%"><hdiits:caption
				captionid="ADDRESS.Taluka" bundle="${commonLablesAddress}" /></td>
			<td width="1%">:</td>
			<td class="fieldLabel" width="24%" id="othertbl${name}Taluka">
				<c:if test="${addressVO.talukaName eq ''}">-</c:if>
				<c:if test="${addressVO.talukaName ne ''}">${addressVO.talukaName}</c:if>
			</td>
		</tr>
		<tr>
			<td class="fieldLabel" width="25%"><hdiits:caption
				captionid="ADDRESS.District" bundle="${commonLablesAddress}" /></td>
			<td width="1%">:</td>
			<td class="fieldLabel" width="24%" id="tdCmbDistrict${name}">${addressVO.cmnDistrictMst.districtName}</td>

			<td class="fieldLabel" width="24%" id="tdTxtDistrict${name}"
				style="display:none">
				<c:if test="${addressVO.districtName eq ''}">-</c:if>
				<c:if test="${addressVO.districtName ne ''}">${addressVO.districtName}</c:if>
			</td>
		</tr>
	</table>

	<table class="tabtable" id="rdonlyCountry${name}" style="display:none">
		<tr>
			<td width="25%"><hdiits:caption captionid="ADDRESS.State"
				bundle="${commonLablesAddress}" /></td>
			<td width="1%">:</td>
			<td width="24%" id="tdCmbState${name}" style="display:none">${addressVO.cmnStateMst.stateName}</td>

			<td width="24%" id="tdTxtState${name}" style="display:none">${addressVO.stateName}</td>
			<td width="25%"><hdiits:caption captionid="ADDRESS.Country"
				bundle="${commonLablesAddress}" /></td>
			<td width="1%">:</td>
			<td width="25%" id="rdonlyCountry${name}country">${addressVO.cmnCountryMst.countryName}</td>
		</tr>
	</table>
	
</hdiits:fieldGroup>

<script>
	var addrName = '${name}';
	var isPopulateOnAjax = '${isPopulateOnAjax}';

	if (isPopulateOnAjax != 'Y')
	{
		var countryCode = '${addressVO.cmnCountryMst.countryCode}';
		var stateCode = '${addressVO.cmnStateMst.stateCode}';
		var addressType = '${addressVO.cmnLookupMstAddTypeLookupid.lookupName}';
		
		if (addressType == 'City')
			document.getElementById("citytbl${name}").style.display='';
		else if (addressType == 'Village')
			document.getElementById("villagetbl${name}").style.display='';
		else if (addressType == 'EmployeeAddress')
		{
			document.getElementById("employeeAddresstbl${name}").style.display='';
			if(countryCode==1&&stateCode==1)
		    {
		    	document.getElementById('tdTxtState'+addrName).style.display='none';
		    	document.getElementById('tdCmbState'+addrName).style.display='block';
		    }
		    else if(countryCode==1&&stateCode!=1)
		    {
		    	document.getElementById('tdTxtState'+addrName).style.display='none';
		    	document.getElementById('tdCmbState'+addrName).style.display='';
		    } 
		    else
		    {
		    	document.getElementById('tdCmbState'+addrName).style.display='none';
		    	document.getElementById('tdTxtState'+addrName).style.display='';
		    }
		}
		else
			 document.getElementById("othertbl${name}").style.display='';
			 
	    if(countryCode==1&&stateCode==1&&addressType != 'EmployeeAddress')
	    {
	    	document.getElementById('tdTxtState'+addrName).style.display='none';
	    	document.getElementById('tdCmbState'+addrName).style.display='block';
	        document.getElementById('tdTxtDistrict'+addrName).style.display='none';
	        document.getElementById('tdCmbDistrict'+addrName).style.display='';
	    }
	    else if(countryCode==1&&stateCode!=1&&addressType != 'EmployeeAddress')
	    {
	    	document.getElementById('citytbl'+addrName).style.display='none';
	    	document.getElementById('villagetbl'+addrName).style.display='none';
	    	document.getElementById('tdTxtState'+addrName).style.display='none';
	    	document.getElementById('tdCmbState'+addrName).style.display='';
			document.getElementById('othertbl'+addrName).style.display='';
			document.getElementById('tdTxtDistrict'+addrName).style.display='none';
	    } 
	    else if(addressType != 'EmployeeAddress')
	    {
	   		document.getElementById('citytbl'+addrName).style.display='none';
	    	document.getElementById('villagetbl'+addrName).style.display='none';
	    	document.getElementById('othertbl'+addrName).style.display='';
	    	document.getElementById('tdCmbDistrict'+addrName).style.display='none';
	    	document.getElementById('tdCmbState'+addrName).style.display='none';
	    	document.getElementById('tdTxtState'+addrName).style.display='';
	    	document.getElementById('tdTxtDistrict'+addrName).style.display='';
	    }
	    document.getElementById('rdonlyCountry'+addrName).style.display='';
	}
</script>
<%
	}
	else
	{
%>
<c:choose>
	<c:when test="${showFieldGroupOnNotAvail eq true}">
	   <hdiits:fieldGroup titleCaption="${finalTitleAddress}" id="fieldGroupAddress${name}" collapseOnLoad="false">
			<center><hdiits:caption captionid="ADDRESS.NTAVAIL" bundle="${commonLablesAddress}"/></center>
		</hdiits:fieldGroup>
	</c:when>
	<c:otherwise>
	   <center><hdiits:caption captionid="ADDRESS.NTAVAIL" bundle="${commonLablesAddress}"/></center>
	</c:otherwise>
</c:choose>
<%		
	}
%>