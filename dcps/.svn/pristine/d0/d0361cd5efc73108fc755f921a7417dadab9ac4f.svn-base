<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@page language="java" import="com.tcs.sgv.core.valueobject.ResultObject;" %>
<fmt:setBundle basename="resources.hod.common.CommonLablesPerson" var="commonLablesPerson" scope="request"/>
<fmt:setBundle basename="resources.hod.common.CommonLablesAddress" var="commonLablesAddress" scope="request"/>
<fmt:setBundle basename="resources.hod.common.CommonLablesContact" var="commonLablesContact" scope="request"/>
<c:set var="personKey" value='<%=request.getParameter("personKey")%>' />
<%
ResultObject result=(ResultObject)request.getAttribute("result");
Map resultMap=(Map)result.getResultValue();
Map myMap=(Map)resultMap.get(request.getParameter("personKey"));
%>

 <c:set var="title" value='<%=request.getParameter("personTitle")%>' />
 <c:set var="personMap" value="<%=myMap%>"> </c:set> 
 <c:set var="personVO" value="${personMap.personVO}"> </c:set> 
  <c:set var="contactVO" value="${personMap.contactVO}"> </c:set>
  
 <c:set var="addressTitle" value="${personMap.addressTitle}"> </c:set>
 <c:set var="contactTitle" value="${personMap.contactTitle}"> </c:set> 
 <c:set var="addressDescription" value="${personMap.addressDescription}"> </c:set>  
 <c:set var="isAjax" value='<%=request.getParameter("isAjaxRequest")%>'> </c:set>  

<fmt:formatDate value="${personVO.dob}" pattern="dd-MMM-yyyy" dateStyle="medium" var="dateOfBirth"/>
<hdiits:fieldGroup  titleCaption="${title}"  mandatory="false" id="fieldset${personKey}">
     
<table width="100%"  id="personTbl${personKey}">

<tr>
<td colspan="2" ><b><hdiits:caption captionid="PS.NAME" bundle="${commonLablesPerson}"/>:</td>
<td colspan="8" align="center" valign="middle" >
<table width="100%" height="100%">
<tr>
<td id="firstName${personKey}" align="left" nowrap>
          <c:if test='${not empty personVO}'>
         <c:out value="${personVO.firstName}"></c:out>
         </c:if>       
</td>
<td id='middleName${personKey}' align="left" nowrap>
  <c:if test='${not empty personVO}'>
<c:out value="${personVO.middleName}"></c:out>
 </c:if>
</td>
 
<td id='lastName${personKey}' align="left" nowrap>
      <c:if test='${not empty personVO}'>
      <c:out value="${personVO.lastName}"></c:out>
      </c:if>
</td>
</tr>
<tr>
<td width="33%" align="left"><b><hdiits:caption captionid="PS.FIRST_NAME" bundle="${commonLablesPerson}"/></b></td><td  width="33%" align="left"><b><hdiits:caption captionid="PS.MID_NAME" bundle="${commonLablesPerson}"/></td><td width="33%" align="left"><b><hdiits:caption captionid="PS.LAST_NAME" bundle="${commonLablesPerson}"/></td><td class="tablecelltext"></td>
</tr>
<tr>
</table>
</td>
</tr>
<tr>
<td  width="10%"><b><hdiits:caption captionid="PS.DtYrOfBirth" bundle="${commonLablesPerson}"/>:</b></td>
<td  id='dateOfBirth${personKey}' width="10%" nowrap>
   <c:if test='${not empty personVO}'>
<c:out value="${dateOfBirth}"></c:out>
   </c:if>
</td>
<td  width="10%"><b><hdiits:caption captionid="PS.AGE" bundle="${commonLablesPerson}"/>:</b></td>
<td  id='age${personKey}' width="10%" nowrap>
 <c:if test='${not empty personVO}'>
<c:out value="${personVO.age}"></c:out>
 </c:if>
</td>
<td  width="10%"><b><hdiits:caption captionid="PS.GENDER" bundle="${commonLablesPerson}"/>:</b></td>
<td  id='gender${personKey}' width="10%" nowrap>
  <c:if test='${not empty personVO}'>
<c:out value="${personVO.cmnLookupMstByGenderLookupid.lookupDesc}"></c:out>
  </c:if>
</td>
<c:choose>
<c:when test="${empty personVO.cmnLookupMstByReligionLookupid.lookupDesc}">
<td  width="10%" ><b><hdiits:caption captionid="PS.RELIGION" bundle="${commonLablesPerson}"/>:</b></td>
<td  id='religion${personKey}' width="10%" nowrap>
 <c:if test='${not empty personVO}'>
<c:out value="${personVO.religionOther}"></c:out>
 </c:if>
</td>
</c:when>
<c:otherwise>
<td  width="10%"><b><hdiits:caption captionid="PS.RELIGION" bundle="${commonLablesPerson}"/>:</b></td>
<td  id='religion${personKey}' width="10%" nowrap>
 <c:if test='${not empty personVO}'>
<c:out value="${personVO.cmnLookupMstByReligionLookupid.lookupDesc}"></c:out>
 </c:if>
</td>
</c:otherwise>
</c:choose>
<td  width="10%"><b><hdiits:caption captionid="PS.CASTE" bundle="${commonLablesPerson}"/>:</b></td>
<td  id='caste${personKey}' width="10%" nowrap>
  <c:if test='${not empty personVO}'>
 <c:out value="${personVO.personCaste}"></c:out>
 </c:if>
</td>

</tr>
<tr>
<td ><b><hdiits:caption captionid="PS.CATEGORY" bundle="${commonLablesPerson}"/>:</b></td>
<td  id='category${personKey}' nowrap>
<c:if test='${not empty personVO}'>
<c:out value="${personVO.cmnLookupMstByCategory.lookupDesc}"></c:out>
</c:if>
</td>
<c:choose>
<c:when test="${empty personVO.cmnLookupMstByOccupationLookupid.lookupDesc}">
<td ><b><hdiits:caption captionid="PS.OCCUPATION" bundle="${commonLablesPerson}"/>:</b></td>
<td  id='occupation${personKey}' nowrap>
<c:if test='${not empty personVO}'>
<c:out value="${personVO.occupationOther}"></c:out>
</c:if>
</td>
</c:when>
<c:otherwise>
<td><b><hdiits:caption captionid="PS.OCCUPATION" bundle="${commonLablesPerson}"/>:</b></td>

<td  id='occupation${personKey}' nowrap>
<c:if test='${not empty personVO}'>
<c:out value="${personVO.cmnLookupMstByOccupationLookupid.lookupDesc}"></c:out>
</c:if>
</td>
</c:otherwise>
</c:choose>
<td nowrap><b><hdiits:caption captionid="PS.MARIED" bundle="${commonLablesPerson}"/>:</b></td>
<td  id='maritalStatus${personKey}' nowrap>
<c:if test='${not empty personVO}'>
<c:out value="${personVO.cmnLookupMstByMaritalLookupid.lookupDesc}"></c:out>
</c:if>
</td>


<td ><b><hdiits:caption captionid="PS.WHETHERADULT" bundle="${commonLablesPerson}"/>:</b></td>
<td  id='isAdult${personKey}' nowrap>
 <c:if test='${not empty personVO}'>
<c:out value="${personVO.isAdult}"></c:out>
 </c:if>
</td>

<td ><b><hdiits:caption captionid="PS.NATIONALITY" bundle="${commonLablesPerson}"/>:</b></td>
<td id='nationality${personKey}' nowrap>
 <c:if test='${not empty personVO}'>
<c:out value="${personVO.cmnNationalityMst.nationalityName}"></c:out>
 </c:if>
</td>
</tr>
</table>
<br>
<input type="hidden" name="personKeys"   value="${personKey}"/>		
<table width="100%"  id="addressTbl${personKey}" >
<tr>
<td  id='addressTitle${personKey}' width="10%" nowrap><b>
 <c:if test='${not empty addressTitle}'>
 <c:out value="${addressTitle}"></c:out>:
 </c:if>
 </b>
</td>
<td id='addressDescription${personKey}' align='left' nowrap>
<c:if test='${not empty addressDescription}'>
 <c:out value="${addressDescription}"></c:out>
 </c:if>
</td>
</tr>

</table>
<br>
<table width="100%"  id="contactTbl${personKey}" >
<tr>
<td  id='contactTitle${personKey}' width="12%" nowrap><b>
   <c:if test='${not empty contactTitle}'>
<c:out value="${contactTitle}"></c:out>:
 </c:if></b>
</td><td colspan="7"></td>
</tr>
<tr>
<td width="12%"><b><hdiits:caption captionid="CONTACT.HOMEPHONE" bundle="${commonLablesContact}"/></b></td>
<td width="14%" id='residencePhone${personKey}' align='left' nowrap>
<c:if test='${not empty contactVO}'>
<c:out value="${contactVO.residencePhone}"></c:out>
 </c:if>
</td>
<td width="13%" ><b><hdiits:caption captionid="CONTACT.OFFICEPHONE" bundle="${commonLablesContact}"/></b></td>
<td width="14%" id='officePhone${personKey}' align='left' nowrap>
<c:if test='${not empty contactVO}'>
<c:out value="${contactVO.officePhone}"></c:out>
 </c:if>
</td>
<td width="8%"><b><hdiits:caption captionid="CONTACT.MOBILENO" bundle="${commonLablesContact}"/></b></td>
<td width="14%" id='mobile${personKey}' align='left' nowrap>
<c:if test='${not empty contactVO}'>
<c:out value="${contactVO.mobile}"></c:out>
 </c:if>
</td>
<td width="8%" ><b><hdiits:caption captionid="CONTACT.EMAIL" bundle="${commonLablesContact}"/></b></td>
<td width="17%" id='email${personKey}' align='left' nowrap>
<c:if test='${not empty contactVO}'>
<c:out value="${contactVO.email}"></c:out>
 </c:if>
</td>
</tr>
</table>
</hdiits:fieldGroup>
    <c:if test="${isAjax eq 'Yes'}">
<script language="javascript">
   
  document.getElementById('personTbl${personKey}').style.display='none';
  document.getElementById('addressTbl${personKey}').style.display='none';
  document.getElementById('contactTbl${personKey}').style.display='none';
  document.getElementById('fieldset${personKey}').style.display='none';
</script>
    </c:if>
