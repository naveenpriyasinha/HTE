<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/dcps/InterestCalculation.js"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>   

<fmt:setBundle	basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request" />

<hdiits:form name="frmInterestCalculation" encType="multipart/form-data" validate="true" method="post">

<fieldset class="tabstyle"><legend> <fmt:message key="CMN.INTERSTCALCULATION" bundle="${dcpsLables}"></fmt:message></legend>

<table align="center" width="70%" border="0" >
		<tr>
			<td align="right" colspan="3" width="50%" style="padding-right: 20px" ><fmt:message key="CMN.YEAR" bundle="${dcpsLables}"></fmt:message>
			</td>
			<td align="left" colspan="3" width="50%" style="padding-left: 20px">
			<select name="cmbYear" id="cmbYear" onchange="reloadBelowDetails();" >
					<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>	
					<c:forEach var="year" items="${resValue.YEARS}" >
								<c:choose>
													<c:when test="${resValue.selectedYear == year.id}">
														<option value="${year.id}" selected="selected"><c:out 
																value="${year.desc}"></c:out></option>
									</c:when>
									<c:otherwise>
														<option value="${year.id}"><c:out 
																value="${year.desc}"></c:out></option>
									</c:otherwise>						
							    </c:choose>
					</c:forEach>
			</select>	
			</td>
		</tr>
		
		<tr></tr>
		
		<tr>
			<td align="right" ><fmt:message key="CMN.FORTREASURY" bundle="${dcpsLables}"></fmt:message></td>
			<td align="left" >
				<c:choose>
					<c:when test="${resValue.TreasuryChecked == 'Y'}">
						<input type="radio"	id="radioForTreasury" name="radioForIntrstCalc" value="Treasury" onclick="loadBelowDetails();" checked="checked"/>
					</c:when>
					<c:otherwise>
						<input type="radio"	id="radioForTreasury" name="radioForIntrstCalc" value="Treasury" onclick="loadBelowDetails();" />
					</c:otherwise>
				</c:choose>
			</td>
			
			<td align="right"><fmt:message key="CMN.FORDDO" bundle="${dcpsLables}"></fmt:message></td>
			<td align="left">
				<c:choose>
					<c:when test="${resValue.DDOChecked == 'Y'}">
						<input type="radio"	id="radioForDDO" name="radioForIntrstCalc" value="DDO" onclick="loadBelowDetails();" checked="checked" />
					</c:when>
					<c:otherwise>
						<input type="radio"	id="radioForDDO" name="radioForIntrstCalc" value="DDO" onclick="loadBelowDetails();" />		
					</c:otherwise>
				</c:choose>
			</td>
			
			<td align="right"><fmt:message key="CMN.FOREMPLOYEE" bundle="${dcpsLables}"></fmt:message></td>
			<td align="left">
				<c:choose>
					<c:when test="${resValue.EMPChecked == 'Y'}">
						<input type="radio"	id="radioForEmp" name="radioForIntrstCalc" value="EMP" onclick="loadBelowDetails();" checked="checked"  />
					</c:when>
					<c:otherwise>
						<input type="radio"	id="radioForEmp" name="radioForIntrstCalc" value="EMP" onclick="loadBelowDetails();" />			
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		
</table>
</fieldset>

<c:choose>
	<c:when test="${resValue.TreasuryChecked == 'Y'}">
		<jsp:include page="/WEB-INF/jsp/dcps/CalculateInterestTreasury.jsp" />
	</c:when>
	<c:when test="${resValue.DDOChecked == 'Y'}">
		<jsp:include page="/WEB-INF/jsp/dcps/CalculateInterestDDO.jsp" />
	</c:when>
	<c:when test="${resValue.EMPChecked == 'Y'}">
		<jsp:include page="/WEB-INF/jsp/dcps/CalculateInterestEmp.jsp" />
	</c:when>
	<c:otherwise></c:otherwise>
</c:choose>

<c:if test="${resValue.TreasuryChecked == 'Y' || resValue.DDOChecked == 'Y' ||  resValue.EMPChecked == 'Y' }">
<table width="70%" align="center">
<tr>
<td>
<table align = "center" width = "30%">
	<tr>
		<td>
			<hdiits:button type="button" captionid="BTN.VIEWREPORT" bundle="${dcpsLables}" name="btnViewReport" id="btnViewReport" style="display:none" />
		</td>
		<td>
			<hdiits:button type="button" captionid="BTN.PROCESS" bundle="${dcpsLables}" name="btnProcess" id="btnProcess" onclick="calculateInterestForYear();"/>			
		</td>
		
	</tr>
</table>
</td>
</tr>
</table>
</c:if>

</hdiits:form>