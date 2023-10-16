<%@ taglib prefix="func" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="personOnLeaveSameDesignation" value="${resultValue.personOnLeaveSameDesignation}"/>
<c:set var="personOnLeaveAllDesignation" value="${resultValue.personOnLeaveAllDesignation}"/>
<c:set var="totalStrengthOfEmp" value="${resultValue.totalStrengthOfEmp}"/>
<hdiits:fieldGroup bundle="${leaveCaption}"  expandable="true" collapseOnLoad="true" titleCaptionId="HRMS.employeeLeaveInfo" id="empOnLeaveInfo">
<table width="100%" style="border-collapse: collapse;"  borderColor="BLACK"  border="1" 		 bgcolor="white">
<tr  bgcolor="#C9DFFF">
<td align="center">
<hdiits:caption captionid="HRMS.SrNo" bundle="${leaveCaption}"/>
</td>
<td align="center">
<hdiits:caption captionid="HRMS.totalapprovedstrength" bundle="${leaveCaption}"/>
</td>
<td align="center">

<hdiits:caption captionid="HRMS.onleavesamedesignation" bundle="${leaveCaption}"/>
</td>
<td align="center">
<hdiits:caption captionid="HRMS.presentsamedesignation" bundle="${leaveCaption}"/>
</td>
<td align="center">  
<hdiits:caption captionid="HRMS.onleavealldesignation" bundle="${leaveCaption}"/>
</td>
</tr>
<tr>
<td align="center">1</td>
<td align="center">${func:length(totalStrengthOfEmp)}
</td>
<td align="center">${func:length(personOnLeaveSameDesignation)}</td>
<td align="center">${func:length(totalStrengthOfEmp) - func:length(personOnLeaveSameDesignation)}</td>
<td align="center">${func:length(personOnLeaveAllDesignation)}</td>
</tr>
<tr>
<td colspan="5" align="left">
<hdiits:caption captionid="HRMS.Note" bundle="${leaveCaption}"/>
<hdiits:caption captionid="HRMS.applicantInclusive" bundle="${leaveCaption}"/>
</td>
</tr>
</table>
</hdiits:fieldGroup>
		