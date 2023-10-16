<%
try {
%>

<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="java.util.List,com.tcs.sgv.eis.util.*"%>
<%@page import="com.tcs.sgv.eis.valueobject.HrPayPayslip"%>
<%@page import="com.tcs.sgv.eis.valueobject.HrPayPaybill"%>
<%@page import="com.tcs.sgv.eis.valueobject.HrForm16CustomVO"%>
<%@page import="java.util.Map"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/eis/Address.js"/>">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>

<c:set var="year" value="${resValue.year}" > </c:set>
<c:set var="startDate" value="${resValue.startDate}" > </c:set>
<c:set var="endDate" value="${resValue.endDate}" > </c:set>
<c:set var="office_add" value="${resValue.office_add}" > </c:set>
<c:set var="strTanNum" value="${resValue.strTanNum}" > </c:set>
<c:set var="strITO" value="${resValue.strITO}" > </c:set>
<c:set var="strQuarter1" value="${resValue.strQuarter1}" > </c:set>
<c:set var="strQuarter2" value="${resValue.strQuarter2}" > </c:set>
<c:set var="strQuarter3" value="${resValue.strQuarter3}" > </c:set>
<c:set var="strQuarter4" value="${resValue.strQuarter4}" > </c:set>

<c:set var="hrEisEmpMst" value="${resValue.hrEisEmpMst}" > </c:set>
<c:set var="designation" value="${resValue.designation}" > </c:set>
<c:set var="department" value="${resValue.department}" > </c:set>
<c:set var="locAdd1" value="${resValue.locAdd1}" > </c:set>
<c:set var="locAdd2" value="${resValue.locAdd2}" > </c:set>
<c:set var="locCity" value="${resValue.locCity}" > </c:set>
<c:set var="locPin" value="${resValue.locPin}" > </c:set>

<c:set var="panNo" value="${resValue.panNo}" > </c:set>

<c:set var="hrEisDdo" value="${resValue.hrEisDdo}" > </c:set>
<c:set var="ddoDesignation" value="${resValue.ddoDesignation}" > </c:set>
<c:set var="ddoDepartment" value="${resValue.ddoDepartment}" > </c:set>
<c:set var="ddoLocAdd1" value="${resValue.ddoLocAdd1}" > </c:set>
<c:set var="ddoLocAdd2" value="${resValue.ddoLocAdd2}" > </c:set>
<c:set var="ddoLocCity" value="${resValue.ddoLocCity}" > </c:set>
<c:set var="ddoLocPin" value="${resValue.ddoLocPin}" > </c:set>

<c:set var="arrStr" value="${resValue.arrStr}" > </c:set>
<c:set var="payBillList" value="${resValue.payBillList}" > </c:set>
<c:set var="hrForm16CustomVO" value="${resValue.hrForm16CustomVO}" > </c:set>
<c:set var="hrForm16ArgsList" value="${resValue.hrForm16ArgsList}" > </c:set>

<c:set var="form16DataNew" value="${resValue.form16DataNew}" > </c:set>

<c:set var="signDataName" value="${resValue.signDataName}" > </c:set>
<c:set var="signDsgnName" value="${resValue.signDsgnName}" > </c:set>
<c:set var="signDeptName" value="${resValue.signDeptName}" > </c:set>
<c:set var="signcityPin" value="${resValue.signcityPin}" > </c:set>
<c:set var="signcityName" value="${resValue.signcityName}" > </c:set>
<c:set var="todayDate" value="${resValue.todayDate}" > </c:set>
<c:set var="signDataFullName" value="${resValue.signDataFullName}" > </c:set>
<c:set var="loginLocId" value = "${resValue.baseLoginMap.locationId}" />  
<style>
	.padding{padding-left:10px;padding-top:10px;padding-bottom:5px;};
	.paddingright{padding-right:30px;};
</style>

<%
List form16DataNewList = (List)pageContext.getAttribute("form16DataNew");
long loginLocId=(Long)pageContext.getAttribute("loginLocId");
long form16Size=0;
long size=0;
long lDeducArgs=0;
if(	form16DataNewList!=null)
{
	form16Size = form16DataNewList.size();
}

%>

<c:forEach items="${form16DataNew}" var="form16DataNew">
<c:set var="hrForm16ArgsList" value="${resValue.hrForm16ArgsList}" > </c:set>
<c:set var="taxDeducDtl" value="${form16DataNew.taxDeducDtl}" > </c:set>

<%
Map taxDeducDtlNew= (Map)pageContext.getAttribute("taxDeducDtl");

int j=0;
%>
	<table width="100%" align="center" cellpadding="0" cellspacing="0" border="0" bordercolor="black">
		<tr>
		<td colspan="1" align="right" style="border-left-style:solid;border-left-width:2px;border-left-color:black;border-top-style:solid;border-top-width:2px;border-top-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black;border-right-color:white;border-right-width:0px">
				&nbsp;
			</td>
			<td colspan="3" align="center" style="border-left-style:solid;border-left-width:0px;border-left-color:black;border-top-style:solid;border-top-width:2px;border-top-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black;border-right-color:white;border-right-width:0px">
				<b><font size = 2>FORM NO 16</font></b><br>
				[See Rule 31 (1)(a)]<br>
				Certificate under section 203 of the Income Tax Act, 1961 for tax deducted<br>
				at Source from Income chargeable under the Head "Salaries".
			</td>
			<td colspan="1" align="right" style="border-right-style:solid;border-right-width:2px;border-right-color:black;border-top-style:solid;border-top-width:2px;border-top-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black;border-left-color:white;border-left-width:0px">
				<b>${form16DataNew.psrSeq}</b>&nbsp;
			</td>
			
		</tr>
		<tr>
			<td width="55%" class="padding" colspan="2" valign="top" style="border-left-style:solid;border-left-width:2px;border-left-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black;border-right-style:solid;border-right-color:black;border-right-width:2px">
				<!--<c:forEach var="arr" items="${arrStr}">
        			<c:out value="${arr}"> </c:out><br>
        		</c:forEach>-->
        	<!-- 	<c:out value="${hrEisDdo.empPrefix} ${hrEisDdo.empFname} ${hrEisDdo.empMname} ${hrEisDdo.empLname} "></c:out><br>
				<c:if test="${ddoDesignation ne null}">
					<c:out value="${ddoDesignation}"> </c:out><br>
				</c:if>
				<c:if test="${ddoDepartment ne null}">
					<c:out value="${ddoDepartment}"> </c:out><br>
				</c:if>
				<c:if test="${ddoLocAdd1 ne null}">
					<c:out value="${ddoLocAdd1}"> </c:out><br>
				</c:if>
				<c:if test="${ddoLocAdd2 ne null}">
					<c:out value="${ddoLocAdd2}"> </c:out><br>
				</c:if>
				<c:if test="${ddoLocCity ne null}">
					<c:out value="${ddoLocCity}"> </c:out>
				</c:if>
				<c:if test="${ddoLocPin ne null}">
					&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${ddoLocPin}"> </c:out><br>
				</c:if> -->
				<b><c:out value="S R PANDEY "></c:out><br></b>
				<c:out value="Under Secretary "></c:out><br>
				<c:out value="${signDeptName} "></c:out><br>
				<c:out value="Block 7, 2nd Floor "></c:out><br>
				
				<c:out value="${signcityName} "></c:out><br>
				<c:out value="${signcityPin} "></c:out><br>
				
			</td>
			<td valign="top" colspan="3" class="padding" style="border-left-style:solid;border-left-width:0px;border-left-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black;border-right-style:solid;border-right-color:black;border-right-width:2px">
				<b> <FONT size = 2><c:out value="${form16DataNew.empName} "></c:out></FONT></b><br>
				<b><c:out value="${form16DataNew.desgnName}"> </c:out></b><br><br>
				
				<!--<c:if test="${designation ne null}">-->
					
				<!--</c:if>
				<c:if test="${department ne null}">
					<c:out value="${department}"> </c:out><br>
				</c:if>
				<c:if test="${locAdd1 ne null}">
					<c:out value="${locAdd1}"> </c:out><br>
				</c:if>
				<c:if test="${locAdd2 ne null}">
					<c:out value="${locAdd2}"> </c:out><br>
				</c:if>
				<c:if test="${locCity ne null}">
					<c:out value="${locCity}"> </c:out>
				</c:if>
				<c:if test="${locPin ne null}">
					&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${locPin}"> </c:out><br>
				</c:if>
				
				<br>
				<b>PAN : </b><c:out value="${panNo}"> </c:out><br><br>-->
			
				<!--  	<c:out value="General Administration Department"> </c:out><br>
				
				
					
					<c:out value="Gandhinagar"> </c:out>
				
					&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="123456"> </c:out><br> -->
				
				
				<br>
				<b>PAN : <FONT size = 2><c:out value="${form16DataNew.panCard}"> </c:out></FONT></b><br><br>
			</td>
		</tr>
		<tr>
			<td width="55%" class="padding" colspan="2" style="border-left-style:solid;border-left-width:2px;border-left-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black;border-right-style:solid;border-right-color:black;border-right-width:2px">
				TAN :  <FONT size = 2><c:out value="${strTanNum}"></c:out></FONT>	<br>
				ITO : <c:out value="${strITO}"></c:out>	<br>
			</td>
			<td align="center" colspan="2" rowspan="2" valign="top" class="padding" style="border-left-style:solid;border-left-width:0px;border-left-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black;border-right-style:solid;border-right-color:black;border-right-width:0px">
				Period 
			</td>
			<td align="center" width="15%" rowspan="2" valign="top" class="padding" style="border-left-style:solid;border-left-width:2px;border-left-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black;border-right-style:solid;border-right-color:black;border-right-width:2px"> 
				Assessment Year 
			</td>
		</tr>
		<tr>
			<td class="padding" colspan="2" style="padding-right:30px" style="border-left-style:solid;border-left-width:2px;border-left-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black;border-right-style:solid;border-right-color:black;border-right-width:2px">
				Acknowledgement Nos. of all quarterly statements
				of TDS under sub-section (3) of section 200
				as provided by TIN facilitation center or NSDL website.
			</td>
		</tr>
		<tr>
			<td width="25%" align="center" class="padding" style="border-left-style:solid;border-left-width:2px;border-left-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black;border-right-style:solid;border-right-color:black;border-right-width:2px">
				Quarter
			</td>
			<td align="center" width="30%" class="padding" style="border-left-style:solid;border-left-width:0px;border-left-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black;border-right-style:solid;border-right-color:black;border-right-width:2px">
				Acknowledgement No.
			</td>
			<td align="center"  width="15%" class="padding" style="border-left-style:solid;border-left-width:0px;border-left-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black;border-right-style:solid;border-right-color:black;border-right-width:2px">
				FROM 
			</td>
			<td align="center"  width="15%" class="padding" style="border-left-style:solid;border-left-width:0px;border-left-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black;border-right-style:solid;border-right-color:black;border-right-width:0px">
				TO 
			</td>
			<td align="center" width="15%" rowspan="5" valign="middle"  height="20px" class="padding" style="border-left-style:solid;border-left-width:2px;border-left-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black;border-right-style:solid;border-right-color:black;border-right-width:2px">
				<c:out value="${year}"></c:out>
			</td>
		</tr>
		<tr>
			<td width="25%" class="padding" style="border-left-style:solid;border-left-width:2px;border-left-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black;border-right-style:solid;border-right-color:black;border-right-width:2px">
				Quarter-1
			</td>
			<td width="30%" class="padding" style="border-left-style:solid;border-left-width:0px;border-left-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black;border-right-style:solid;border-right-color:black;border-right-width:2px">
				<c:out value="${strQuarter1}"></c:out>
			</td>
			<td align="center"  width="15%" height="80%" rowspan=4 class="padding" style="border-left-style:solid;border-left-width:0px;border-left-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black;border-right-style:solid;border-right-color:black;border-right-width:2px">
				<fmt:formatDate var="sDate" pattern="dd/MM/yyyy" value="${startDate}"/>
				<c:out value="${sDate}"></c:out>
			</td>
			<td align="center"  width="15%" height="80%" rowspan=4 class="padding" style="border-left-style:solid;border-left-width:0px;border-left-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black;border-right-style:solid;border-right-color:black;border-right-width:0px">
				<fmt:formatDate var="eDate" pattern="dd/MM/yyyy" value="${endDate}"/>
				<c:out value="${eDate}"></c:out>
			</td>
		</tr>
		<tr>
			<td width="25%"  class="padding" style="border-left-style:solid;border-left-width:2px;border-left-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black;border-right-style:solid;border-right-color:black;border-right-width:2px">
				Quarter-2
			</td>
			<td  width="30%" class="padding" style="border-left-style:solid;border-left-width:0px;border-left-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black;border-right-style:solid;border-right-color:black;border-right-width:2px">
				<c:out value="${strQuarter2}"></c:out>
			</td>
		</tr>
		<tr>
			<td width="25%"  class="padding" style="border-left-style:solid;border-left-width:2px;border-left-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black;border-right-style:solid;border-right-color:black;border-right-width:2px">
				Quarter-3
			</td>
			<td width="30%" class="padding" style="border-left-style:solid;border-left-width:0px;border-left-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black;border-right-style:solid;border-right-color:black;border-right-width:2px">
				<c:out value="${strQuarter3}"></c:out>
			</td>
		</tr>
		<tr>
			<td width="25%" class="padding" style="border-left-style:solid;border-left-width:2px;border-left-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black;border-right-style:solid;border-right-color:black;border-right-width:2px">
				Quarter-4
			</td>
			<td width="30%" class="padding" style="border-left-style:solid;border-left-width:0px;border-left-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black;border-right-style:solid;border-right-color:black;border-right-width:2px">
				<c:out value="${strQuarter4}"></c:out>&nbsp;
			</td>
		</tr>
		<tr>
			<td align="center" class="padding" colspan="5" style="border-left-style:solid;border-left-width:2px;border-left-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black;border-right-style:solid;border-right-color:black;border-right-width:2px">
				<b>DETAILS OF SALARY PAID AND ANY OTHER INCOME AND TAX DEDUCTED</b>
			</td>
		</tr>
		
			<td align="center" class="padding" colspan="5" style="padding:0px">
				<table cellpadding="5" cellspacing="0" border="0" width="100%">
					<tr>
						<td width="55%" align="left"  style="border-left-style:solid;border-left-width:2px;border-left-color:black;border-right-style:solid;border-right-color:black;border-right-width:2px">
							&nbsp;
						</td>
						<td width="15%" align="right" class="paddingright"  style="border-right-style:solid;border-right-color:black;border-right-width:2px">
							Rs.
						</td>
						<td width="15%" align="right" class="paddingright"  style="border-right-style:solid;border-right-color:black;border-right-width:2px">
							Rs.
						</td>
						<td width="15%" align="right" class="paddingright" style="border-right-style:solid;border-right-color:black;border-right-width:2px">
							Rs.
						</td>
					</tr>
					<%--  billow TD - TR is for Empty Row  --%>
					<tr>
						<td width="55%" align="left" style="border-left-style:solid;border-left-width:2px;border-left-color:black;border-right-style:solid;border-right-color:black;border-right-width:2px">
							&nbsp;
						</td>
						<td width="15%" align="right" class="paddingright"  style="border-right-style:solid;border-right-color:black;border-right-width:2px">
							&nbsp;
						</td>
						<td width="15%" align="right" class="paddingright"  style="border-right-style:solid;border-right-color:black;border-right-width:2px">
							&nbsp;
						</td>
						<td width="15%" align="right" class="paddingright" style="border-right-style:solid;border-right-color:black;border-right-width:2px">
							&nbsp;
						</td>
					</tr>
					
					
					<c:set var="Sec80CTotal" value="${form16DataNew.deduc9Bi + form16DataNew.deduc9Bii +form16DataNew.deduc9Biii +form16DataNew.deduc9Biv +form16DataNew.deduc9Bv +form16DataNew.deduc9Bvi +form16DataNew.deduc9Bvii +form16DataNew.deduc9Bviii +form16DataNew.deduc9Bix +form16DataNew.deduc9Bx +form16DataNew.deduc9Bxi +form16DataNew.deduc9Bxii + form16DataNew.deduc9Bxiii}"/>
					<c:forEach items="${hrForm16ArgsList}" var="hrForm16ArgsList">
       					<tr>
       					
					        <td width="55%" align="left" style="border-left-style:solid;border-left-width:2px;border-left-color:black;border-right-style:solid;border-right-color:black;border-right-width:2px">
								${hrForm16ArgsList.displayName}:
							</td> 
					  
							<c:choose>
								<c:when test="${hrForm16ArgsList.valueInCol eq 7 }">
									<c:set var="val1" value="${form16DataNew[hrForm16ArgsList.voProperty]}"/>
									<c:set var="val2" value="${form16DataNew[hrForm16ArgsList.voProperty]}"/>
									<c:set var="val4" value="${form16DataNew[hrForm16ArgsList.voProperty]}"/>
								</c:when>
								<c:when test="${hrForm16ArgsList.valueInCol eq 6 }">
									<c:set var="val1" value="-9999"/>
									<c:set var="val2" value="${Sec80CTotal}"/>
						<%--		<c:set var="val2" value="${form16DataNew[hrForm16ArgsList.voProperty]}"/>--%>
									<c:set var="val4" value="${form16DataNew[hrForm16ArgsList.voProperty]}"/>
								</c:when>
								<c:when test="${hrForm16ArgsList.valueInCol eq 5 }">
									<c:set var="val1" value="${form16DataNew[hrForm16ArgsList.voProperty]}"/>
									<c:set var="val2" value="-9999"/>
									<c:set var="val4" value="${form16DataNew[hrForm16ArgsList.voProperty]}"/>
								</c:when>
								<c:when test="${hrForm16ArgsList.valueInCol eq 4 }">
									<c:set var="val1" value="-9999"/>
									<c:set var="val2" value="-9999"/>
									<c:set var="val4" value="${form16DataNew[hrForm16ArgsList.voProperty]}"/>
								</c:when>
								<c:when test="${hrForm16ArgsList.valueInCol eq 3 }">
									<c:set var="val1" value="${form16DataNew[hrForm16ArgsList.voProperty]}"/>
									<c:set var="val2" value="${form16DataNew[hrForm16ArgsList.voProperty]}"/>
									<c:set var="val4" value="-9999"/>
								</c:when>
								<c:when test="${hrForm16ArgsList.valueInCol eq 2 }">
									<c:set var="val1" value="-9999"/>
									<c:set var="val2" value="${form16DataNew[hrForm16ArgsList.voProperty]}"/>
									<c:set var="val4" value="-9999"/>
								</c:when>
								<c:when test="${hrForm16ArgsList.valueInCol eq 1 }">
									<c:set var="val1" value="${form16DataNew[hrForm16ArgsList.voProperty]}"/>
									<c:set var="val2" value="-9999"/>
									<c:set var="val4" value="-9999"/>
								</c:when>
								<c:when test="${hrForm16ArgsList.valueInCol eq 0 }">
									<c:set var="val1" value="-9999"/>
									<c:set var="val2" value="-9999"/>
									<c:set var="val4" value="-9999"/>
								</c:when>
							</c:choose>
							<c:set var="limitedValue" value="100000"/>
							
							
						<td width="15%" align="right" class="paddingright" style="border-right-style:solid;border-right-color:black;border-right-width:2px">
								
								<c:choose>
									<c:when test="${val1 eq -9999 }">
										&nbsp;
									</c:when>
									<c:otherwise>
										<c:out value="${val1}"/>
									</c:otherwise>
								</c:choose>
							</td>
					        <td width="15%" align="right" class="paddingright"  style="border-right-style:solid;border-right-color:black;border-right-width:2px">
								
								<c:choose>
									<c:when test="${val2 eq -9999 }">
										&nbsp;
									</c:when>
									<c:otherwise>
										<c:out value="${val2}"/>
									</c:otherwise>
								</c:choose>
							</td>
					        <td width="15%"  align="right" class="paddingright" style="border-right-style:solid;border-right-color:black;border-right-width:2px">
								 
								 <c:choose>
									<c:when test="${val4 eq -9999 }">
										&nbsp;
									</c:when>
									<c:otherwise>
										<c:out value="${val4}"/>
									</c:otherwise>
								</c:choose>
							 </td>
						
					   </tr>
					   
					   <c:if test="${hrForm16ArgsList.argId eq 29 }">
					   <tr>
					   <td colspan="4" style="border-top-style:solid;border-top-color:black;border-top-width:2px;border-bottom-style:solid;border-bottom-color:black;border-bottom-width:2px">
					   &nbsp;
					   </td>
					   </tr>
					   </c:if>
				    </c:forEach>
							
		</table>
	</td>
			
			
		</tr>
		<tr>
			<td align="center" colspan="5">
				<table cellpadding="4" cellspacing="0" border="0" width="100%">
					<tr>
						<td colspan="5" align="center" style="border-right-style:solid;border-right-width:2px;border-right-color:black;border-top-style:solid;border-top-width:2px;border-top-color:black;border-left-style:solid;border-left-width:2px;border-left-color:black">
							<b>DETAILS OF TAX DEDUCTED AND DEPOSITED INTO CENTRAL GOVT. ACCOUNT</b>
						</td>
					</tr>
					<tr>
						<td align="right" width="3%" style="border-right-style:solid;border-right-width:2px;border-right-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black;border-top-style:solid;border-top-width:2px;border-top-color:black;border-left-style:solid;border-left-width:2px;border-left-color:black">
							Sr.
						</td>
						<td align="center" width="20%"  style="border-right-style:solid;border-right-width:2px;border-right-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black;border-top-style:solid;border-top-width:2px;border-top-color:black">
							Total Tax Deposited Rs.
						</td>
						<td width="30%" align="left" style="border-right-style:solid;border-right-width:2px;border-right-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black;border-top-style:solid;border-top-width:2px;border-top-color:black">
							BSR Code of Bank Branch/PAO
						</td>
						<td align="center" width="20%" style="border-right-style:solid;border-right-width:2px;border-right-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black;border-top-style:solid;border-top-width:2px;border-top-color:black">
							Date on which tax deposited
						</td>
						<td align="center" width="25%" style="border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black;border-top-style:solid;border-top-width:2px;border-top-color:black;border-right-style:solid;border-right-width:2px;border-right-color:black">
							Transfer voucher / challan identification no.
						</td>
					</tr>
					
					
					
					
					<%
					if(loginLocId==300022){
					for(int i=3;i<=12;i++){ 
					
					j++;%>	
						
					<tr>
						<td align="right" style="border-right-style:solid;border-right-width:2px;border-right-color:black;border-left-style:solid;border-left-width:2px;border-left-color:black">
							<%=j %>
						</td>
						<td align="right" class="paddingright" style="border-right-style:solid;border-right-width:2px;border-right-color:black">
						<%= taxDeducDtlNew.get("month_tax"+i)%>
						</td>
						<td align="left" style="border-right-style:solid;border-right-width:2px;border-right-color:black">
							<%if(!(taxDeducDtlNew.get("month_tax"+i)).toString().equals("0")){%>
								<%=taxDeducDtlNew.get("bank_name"+i)%>
								<%}else{%>
								&nbsp;
								<%} %>
						</td>
						<td align="left" style="border-right-style:solid;border-right-width:2px;border-right-color:black">
							<%
								if(i!=12){
								if(!(taxDeducDtlNew.get("month_tax"+i)).toString().equals("0")){%>
									<%=taxDeducDtlNew.get("bank_date"+(i+1))%>
								<%}else{%>
								&nbsp;
								
								<%}} else if(i==12){
								if(!(taxDeducDtlNew.get("month_tax"+i)).toString().equals("0")){%>
								<%=taxDeducDtlNew.get("bank_date1")%>
								<%
								}else{%>
								&nbsp;
								<%} }%>&nbsp;
						</td>
						<td align="center" style="border-right-style:solid;border-right-width:2px;border-right-color:black">
							&nbsp;
						</td>
					</tr>
					<%} %>
					<%for(int i=1;i<=2;i++){ 
					j++;%>	
						
					<tr>
						<td align="right" style="border-right-style:solid;border-right-width:2px;border-right-color:black;border-left-style:solid;border-left-width:2px;border-left-color:black">
							<%=j %>
						</td>
						<td align="right" class="paddingright" style="border-right-style:solid;border-right-width:2px;border-right-color:black">
						<%= taxDeducDtlNew.get("month_tax"+i)%>
						</td>
						<td align="left" style="border-right-style:solid;border-right-width:2px;border-right-color:black">
							<%if(!(taxDeducDtlNew.get("month_tax"+i)).toString().equals("0")){%>
							<%=taxDeducDtlNew.get("bank_name"+i)%>
							<%}else{%>
								&nbsp;
						<%} %>
						</td>
						<td align="left" style="border-right-style:solid;border-right-width:2px;border-right-color:black">
							<%if(!(taxDeducDtlNew.get("month_tax"+i)).toString().equals("0")){%>
							<%=taxDeducDtlNew.get("bank_date"+(i+1))%>&nbsp;
							<%}else{%>
								&nbsp;
								<%} %>
						</td>
						<td align="center" style="border-right-style:solid;border-right-width:2px;border-right-color:black">
							&nbsp;
						</td>
					</tr>
					<% 
					}}%>
					<%
					if(loginLocId==300024){
					for(int i=4;i<=12;i++){ 
					
					j++;%>	
						
					<tr>
						<td align="right" style="border-right-style:solid;border-right-width:2px;border-right-color:black;border-left-style:solid;border-left-width:2px;border-left-color:black">
							<%=j %>
						</td>
						<td align="right" class="paddingright" style="border-right-style:solid;border-right-width:2px;border-right-color:black">
						<%= taxDeducDtlNew.get("month_tax"+i)%>
						</td>
						<td align="left" style="border-right-style:solid;border-right-width:2px;border-right-color:black">
								<%if(!(taxDeducDtlNew.get("month_tax"+i)).toString().equals("0")){%>
								<%=taxDeducDtlNew.get("bank_name"+i)%>
								<%}else{%>
								&nbsp;
								<%} %>
						</td>
						<td align="left" style="border-right-style:solid;border-right-width:2px;border-right-color:black">
							<%if(!(taxDeducDtlNew.get("month_tax"+i)).toString().equals("0")){%>
							<%=taxDeducDtlNew.get("bank_date"+i)%>&nbsp;
							<%}else{%>
								&nbsp;
								<%} %>
						</td>
						<td align="center" style="border-right-style:solid;border-right-width:2px;border-right-color:black">
							&nbsp;
						</td>
					</tr>
					<%}%>
					<%for(int i=1;i<=3;i++){ 
					j++;%>	
						
					<tr>
						<td align="right" style="border-right-style:solid;border-right-width:2px;border-right-color:black;border-left-style:solid;border-left-width:2px;border-left-color:black">
							<%=j %>
						</td>
						<td align="right" class="paddingright" style="border-right-style:solid;border-right-width:2px;border-right-color:black">
						<%= taxDeducDtlNew.get("month_tax"+i)%>
						</td>
						<td align="left" style="border-right-style:solid;border-right-width:2px;border-right-color:black">
							<%if(!(taxDeducDtlNew.get("month_tax"+i)).toString().equals("0")){%>
							<%=taxDeducDtlNew.get("bank_name"+i)%>
							<%}else{%>
								&nbsp;
								<%} %>
						</td>
						<td align="left" style="border-right-style:solid;border-right-width:2px;border-right-color:black">
							<%if(!(taxDeducDtlNew.get("month_tax"+i)).toString().equals("0")){%>
							<%=taxDeducDtlNew.get("bank_date"+i)%>&nbsp;
							<%}else{%>
								&nbsp;
								<%} %>
						</td>
						<td align="center" style="border-right-style:solid;border-right-width:2px;border-right-color:black">
							&nbsp;
						</td>
					</tr>
					<%} 
					}%>
					
					
						
					<tr>
						<td align="right" style="border-right-style:solid;border-right-width:2px;border-right-color:black;border-left-style:solid;border-left-width:2px;border-left-color:black">
							13
						</td>
						<td align="right" class="paddingright" style="border-right-style:solid;border-right-width:2px;border-right-color:black">
						
						
							<%=taxDeducDtlNew.get("month_tax13")%>
							
							
							
						</td>
						<td align="left" style="border-right-style:solid;border-right-width:2px;border-right-color:black">
							<%if(!(taxDeducDtlNew.get("month_tax13")).toString().equals("0")){%>
							<%=taxDeducDtlNew.get("bank_name13")%>
							<%}else%>
								&nbsp;
						</td>
						<td align="left" style="border-right-style:solid;border-right-width:2px;border-right-color:black">
							<%if(!(taxDeducDtlNew.get("month_tax13")).toString().equals("0")){%>
							<%=taxDeducDtlNew.get("bank_date13")%>&nbsp;
							<%}else{%>
								&nbsp;
								<%} %>
						</td>
						<td align="center" style="border-right-style:solid;border-right-width:2px;border-right-color:black">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td align="right" style="border-right-style:solid;border-right-width:2px;border-right-color:black;border-left-style:solid;border-left-width:2px;border-left-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black">
							14
						</td>
						<td align="right" class="paddingright" style="border-right-style:solid;border-right-width:2px;border-right-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black">
						
						
							<%=taxDeducDtlNew.get("month_tax14")%>
							
						</td>
						<td align="left" style="border-right-style:solid;border-right-width:2px;border-right-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black">
							<%if(!(taxDeducDtlNew.get("month_tax14")).toString().equals("0")){%>
							<%=taxDeducDtlNew.get("bank_name14")%>
							<%}else%>
								&nbsp;
						</td>
						<td align="left" style="border-right-style:solid;border-right-width:2px;border-right-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black">
							<%if(!(taxDeducDtlNew.get("month_tax14")).toString().equals("0")){%>
							<%=taxDeducDtlNew.get("bank_date14")%>&nbsp;
							<%}else{%>
								&nbsp;
								<%} %>
						</td>
						<td align="center" style="border-right-style:solid;border-right-width:2px;border-right-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black">
							<c:choose>
								<c:when test="${form16DataNew['challanNumber'] ne ''}">
									${form16DataNew["challanNumber"]}
								</c:when>
								<c:otherwise>
									&nbsp;
								</c:otherwise>
							</c:choose>
							
						</td>
					</tr>
				
				
				</table>
			</td> 
		</tr>
		<tr>
			<td colspan="5">
				<table cellpadding="10" cellspacing="0" border="0" width="100%">
					<tr>
						<td colspan="2" style="border-right-style:solid;border-right-width:2px;border-right-color:black;border-left-style:solid;border-left-width:2px;border-left-color:black">
							I <b><u>
							<b><c:out value="${signDataFullName} "></c:out></b>
				
							</u></b> working in the capacity of <b><u>
							
								<c:out value="Under Secretary"> </c:out>
							
							</u>
														
							</b> do 
							hereby certify that a sum of Rs. 
							<c:if test="${form16DataNew['strTaxPayRefund'] == 'Nil'}">
							<b><u>${form16DataNew["strTaxPayRefund"]}</u></b>
							</c:if>
							<c:if test="${form16DataNew['strTaxPayRefund'] != 'Nil'}">
							<b><u>${form16DataNew["strTaxPayRefund"]} Only</u></b>
							</c:if>
							<br> has been deducted at source and paid to the credit of the Central Government. 
							I further certify that the information given above is true and correct based on the books of account, documents and other available
							records.
				
						</td>
					</tr>
					<tr>
						<td width="50%" style="border-left-style:solid;border-left-width:2px;border-left-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black">
							<br><br>
							<br>
							Place:<u>${signcityName}</u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
							Date:<u> 30/04/2010 </u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;				
						<br>
						<b>${form16DataNew.psrSeq}</b>&nbsp;&nbsp;<b>${form16DataNew.empName}</b>
						</td>
						<td  width="50%" style="border-right-style:solid;border-right-width:2px;border-right-color:black;border-bottom-style:solid;border-bottom-width:2px;border-bottom-color:black">
						<br><br>
							Signature of the person responsible for deduction of Tax<br>
							Full Name : <B><c:out value="${signDataFullName} "></c:out></B><br>
							Designation : 
												<c:out value="Under Secretary"> </c:out><br>
											
							${signDeptName}
							
						</td>
					</tr>
				</table>
			</td>
		</tr>	
	</table>
	<%
   size++;
   if(size<form16Size)
   {
   %>
   <DIV style="page-break-after:always"> &nbsp; </DIV> 
<%} %>
 </c:forEach>	
<%
    if(size==0)
   {
   %>
     <br>
     <br>
     <table width="100%">
     <tr>
       <td align="center">
       <b>
        No Records found
       </b> 
        </td>
     </tr>
     </table>
    
  <%
   } 
  %>	
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>