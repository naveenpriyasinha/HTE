
<%
	try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="msg" value="${resValue.msg}"></c:set>
<c:set var="salRevMstVOList" value="${resValue.salRevMstVOList}"></c:set>
<c:set var="billNoList" value="${resValue.billNoList}"></c:set>
<c:set var="arrearReceiptDataList"
	value="${resValue.arrearReceiptDataList}"></c:set>
<c:set var="signEmpName" value="${resValue.signEmpName}"></c:set>
<c:set var="desigName" value="${resValue.desigName}"></c:set>
<c:set var="deptName" value="${resValue.deptName}"></c:set>
<c:set var="cmnLocationMstObj" value="${resValue.cmnLocationMstObj}"></c:set>



<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="resources.eis.eis_common_lables" var="enLables"
	scope="page" />


<hdiits:form name="arrearReceiptPage" validate="true" method="POST"
	encType="multipart/form-data">
<% 
int size=0;
%>

	<div class="halftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0">
	<c:forEach
		var="arrearReceiptDataList" items="${arrearReceiptDataList}">
		<TABLE width="90%" align="center" >
			<tr>
				<td align="center"><b>GOVERNMENT OF GUJARAT</b></td>
			</tr>
			<tr>
				<td align="center"><font size="3"><b>${arrearReceiptDataList[1]} Slip</b></font></td>
			</tr>
			<tr>
				<td align="center">&nbsp;</td>
			</tr>
		</table>
		<TABLE width="90%" align="center" >
			<tr>
				<td width="19%">Department </td>
				<td width="7%" align="left">: </td>
				<td width="30%">${cmnLocationMstObj.locName}</td>
				<td width="14%"><font size="2"><b>PSR No.</b></font></td>
				<td width="6%" align="left">: </td>
				<td width="24%"><font size="2"><b>${arrearReceiptDataList[0]}</b></font></td>
				
				
			</tr>
			<tr>
				<td width="19%">Month </td>
				<td width="7%" align="left">: </td>
				<td width="30%">
				<c:choose>
				   <c:when test="${arrearReceiptDataList[3]>9}">
				     ${arrearReceiptDataList[3]}
				   </c:when>
				   <c:otherwise>
				     0${arrearReceiptDataList[3]}
				   </c:otherwise>
                 </c:choose>
				
				${monthOfArrear}
				-${arrearReceiptDataList[4]}</td>
				<td width="14%">Bill No. </td>
				<td width="6%" align="left">: </td>
				<td width="24%">${arrearReceiptDataList[7]}</td>
			</tr>
			<tr>
				<td width="19%">Employee Name </td>
				<td width="7%" align="left">: </td>
				<td width="30%">${arrearReceiptDataList[6]}</td>
				<td width="14%">GPF A/C No. </td>
				<td width="6%" align="left">: </td>
				<td width="24%">${arrearReceiptDataList[2]}</td>
			</tr>
			<tr>
				<td width="19%">Designation </td>
				<td width="7%" align="left">: </td>
				<td width="30%">${arrearReceiptDataList[13]}</td>
				<td width="14%">PAN A/C No. </td>
				<td width="6%" align="left">: </td>
				<td width="24%">${arrearReceiptDataList[5]}</td>
			</tr>
			<tr>
				<td colspan="5">&nbsp;</td>
			</tr>
		</table>
		<TABLE width="90%" align="center" >
			<tr>
				<td align="left" width="14%">Token No. :</td>
				<td align="left" width="24%">
				<c:choose>
				   <c:when test="${arrearReceiptDataList[14]>0}">
				     ${arrearReceiptDataList[14]}
				   </c:when>
				   <c:otherwise>
				     &nbsp;
				   </c:otherwise>
                 </c:choose>				
				</td>
				<td align="left" width="14%">Token Date :</td>
				<td align="left" width="24%">
				
				<c:choose>
				   <c:when test="${arrearReceiptDataList[15]!=null && arrearReceiptDataList[15]!=''}">
				   <fmt:formatDate var="tokenDate" pattern="dd/MM/yyyy" value="${arrearReceiptDataList[15]}"/>
				     ${tokenDate}
				   </c:when>
				   <c:otherwise>
				     &nbsp;
				   </c:otherwise>
                 </c:choose>				
				</td>
				<td align="left" width="14%">Token Amount :</td>
				<td align="left" width="22%">
				<c:choose>
				   <c:when test="${arrearReceiptDataList[16]>0}">
				     ${arrearReceiptDataList[16]}
				   </c:when>
				   <c:otherwise>
				     NIL
				   </c:otherwise>
                 </c:choose>				
				</td>
			</tr>
			
			<tr><td colspan="6">&nbsp;</td></tr>
			
		</table>
		<TABLE width="90%" align="center" cellpadding="5" border="1">
			<tr>
				<td width="20%" align="center">Amount of arrears</td>
				<td width="15%" align="center">Income Tax</td>
				<td width="15%" align="center">Prof. Tax</td>
				<td width="15%" align="center">In GPF</td>
				<td width="15%" align="center">In Cash</td>
				<td width="20%" align="center">Net Amount</td>
			</tr>
			<tr>
				<td width="20%" align="right">${arrearReceiptDataList[8]}</td>
				<td width="15%" align="right">${arrearReceiptDataList[9]}</td>
				<td width="15%" align="right">${arrearReceiptDataList[10]}</td>
				<td width="15%" align="right">${arrearReceiptDataList[11]}</td>
				<td width="15%" align="right">${arrearReceiptDataList[12]}</td>
				<td width="20%" align="right">
				   <c:choose>
				   <c:when test="${arrearReceiptDataList[12] ne null && arrearReceiptDataList[12]>0}">
				   ${arrearReceiptDataList[12]}
				   </c:when>
				   <c:otherwise>
				   NIL
				   </c:otherwise>
                   </c:choose>
				</td>
			</tr>
		</table>

		<table width="100%">
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>
				<table align="left" width="65%">
					<tr>
						<td align="center">&nbsp;</td>
					</tr>
					<tr>
						<td align="center">&nbsp;</td>
					</tr>
					<tr>
						<td align="center">&nbsp;</td>
					</tr>
					<tr>
						<td align="center">&nbsp;</td>
					</tr>
				</table>
				<table align="right" width="35%">
					<tr>
						<td align="center">&nbsp;</td>
					</tr>
					<tr>
						<td align="center">${signEmpName}</td>
					</tr>
					<tr>
						<td align="center">${desigName},</td>
					</tr>
					<tr>
						<td align="center">${deptName}</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>

		</table>
   <%
   size++;
   if(size%2==0)
   {
   %>
   <DIV style="page-break-after:always"> &nbsp; </DIV>
    
   <%
   }
   %>
	</c:forEach>
	</div>

	</div>
	<script type="text/javascript">
	//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
	initializetabcontent("maintab")
	
    </script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String) session.getAttribute("locale")%>' />
</hdiits:form>

<%
	} catch (Exception e) {
		e.printStackTrace();
	}
%>

