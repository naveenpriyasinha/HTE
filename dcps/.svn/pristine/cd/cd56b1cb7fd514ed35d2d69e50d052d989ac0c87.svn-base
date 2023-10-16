<%-- 
/*
 * FILENAME       : PrintPensionBook.jsp
 * VERSION        : 1.0
 * AUTHOR         : Hardik Josjhi
 * DATE           : 1st April 2008
 * REV. HISTORY :
 * Purpose        : To print Pension Book
 * REV. HISTORY :
 *
 *-----------------------------------------------------------------------------
 * DATE         AUTHOR                  DESCRIPTION
 * 08-Nov-2005  602651                  Total code replaced with the code from
 *                                      Report.jsp for printing the report on
 *                                      this jsp.
 *-----------------------------------------------------------------------------
*/

 --%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<META content="MSHTML 6.00.6000.16608" name=GENERATOR>
<fmt:setBundle basename="resources.hr.acr.acr" var="commonLables" scope="request" />
<fmt:setBundle basename="resources.hr.acr.acr_AlertMessages" var="commonLables1" scope="request" />
<script type="text/javascript" src="common/script/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="script/common/base64.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
		<script type="text/javascript" src="<c:url value="script/common/base64.js"/>"></script>
		<script type="text/javascript" src="<c:url value="/script/common/tabcontent.js"/>"></script>
        <script Language = JavaScript>
        hideProgressbar();
        function fn_print()
        {
            window.print();
            window.close();
        }
        </script>
   
	    
    <body onload="if(document.body) document.body.oncontextmenu=new Function('return false;');fn_print();"  onKeyDown="onKeyDownEvent (event);">    
	<hdiits:form name="f1" action="" method="post" validate="true" encType="multipart/form-data">    	   
		<c:if test="${mst eq 1}">
			<jsp:include page="./printPensionBook/pension book front.jsp" />	  
		</c:if>
		<c:set var="resultObj" value="${result}"></c:set>
		<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
		<c:set var="orgEmpMst" value="${resultValue.orgEmpMst}"></c:set>
		<c:set var="hrEmpReqData" value="${resultValue.hrEmpReqData}"></c:set>
		<c:set var="orgDesignationMst" value="${resultValue.orgDesignationMst}"></c:set>
		<c:set var="orgPostDetailsRlt" value="${resultValue.orgPostDetailsRlt}"></c:set>
		<c:set var="orgDepartmentMst" value="${resultValue.orgDepartmentMst}"></c:set>
		<c:set var="familyLst" value="${resultValue.familyLst}"></c:set>		
		
		<c:out value="${orgEmpMst.empLname} ${orgEmpMst.empFname} ${orgEmpMst.empMname}"></c:out><BR>
		<c:out value="${orgDesignationMst.dsgnName}"></c:out><BR>
		<c:out value="${orgDepartmentMst.depName}"></c:out><BR>		
		<c:out value="${orgPostDetailsRlt.cmnLocationMst.locAddr1},${orgPostDetailsRlt.cmnLocationMst.locAddr2}"></c:out><BR>
		<c:out value="${orgPostDetailsRlt.cmnLocationMst.locPin}"></c:out><BR>
		<c:out value="${orgPostDetailsRlt.cmnLocationMst.locName}"></c:out><BR>		
		<fmt:formatDate value="${orgEmpMst.empDob}" pattern="dd/MM/yyyy" /><BR>
		<fmt:formatDate value="${orgEmpMst.empDoj}" pattern="dd/MM/yyyy" /><BR>
		<c:out value="${hrEmpReqData.cmnLookupMst.lookupDesc}"></c:out><BR>
		<fmt:formatDate value="${orgEmpMst.empSrvcExp}" pattern="dd/MM/yyyy" /><BR>
		<c:out value="${resultValue.pensionAddress}"></c:out><BR>
		<c:out value="${orgEmpMst.empGPFnumber}"></c:out><BR>
		<BR>		
		<table>
			<c:forEach items="${familyLst}" var="familyObj" varStatus="x">
				<tr>
					<td>${x.index+1}</td>
					<td>${familyObj.fmFirstName} ${familyObj.fmMiddleName} ${familyObj.fmLastName}</td>
					<td><fmt:formatDate value="${familyObj.fmDateOfBirth}" pattern="dd/MM/yyyy" /></td>
					<td>${familyObj.cmnLookupMstByFmRelation.lookupDesc}</td>
					<td>${familyObj.fmRemarks}</td>
				</tr>
			</c:forEach>
		</table>
		<BR><BR>
	</hdiits:form>
    </body>    
    <script type="text/javascript">
    	hideProgressbar();
    	if(document.body) document.body.oncontextmenu=new Function('return false;');
	    window.print();	   
    </script>
