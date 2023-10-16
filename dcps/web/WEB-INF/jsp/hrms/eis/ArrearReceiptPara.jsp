
<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="msg" value="${resValue.msg}"></c:set>
<c:set var="salRevMstVOList" value="${resValue.salRevMstVOList}"></c:set>
<c:set var="billNoList" value="${resValue.billNoList}"></c:set>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="resources.eis.eis_common_lables" var="enLables"
	scope="page" />
	


<script>
function generateReceipt()
{

	var salRevOrder = document.arrearReceipt.salRevOrder.value; 
	var billNo= document.arrearReceipt.billNo.value;
    var orgEmpId=document.getElementById("Employee_ID_EmpSearch").value;
	if(document.arrearReceipt.salRevOrder.value=="0")
	{
       alert('Please select Order');
       document.arrearReceipt.salRevOrder.focus();
       return false;
	}	
	var url = "./hrms.htm?actionFlag=generateArrearReceipt&orgEmpId="+orgEmpId+"&salRevOrder="+salRevOrder+"&billNo="+billNo;
    window.open(url,"generateledgerform","toolbar=yes, location=no, directories=no,status=yes, menubar=yes, scrollbars=yes, resizable=yes, resize = no, width = 800, height = 550, copyhistory=no, top=80,left=80");


}	
</script>

<hdiits:form name="arrearReceipt" validate="true" method="POST"	>

	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>Arrear Receipt</b></a></li>

	</ul>
	</div>


	<div class="halftabcontentstyle">


	<div id="tcontent1" class="halftabcontent" >
	<TABLE width="80%" align="center" cellpadding="5">


		<TR><td colspan="4">&nbsp;</td></TR>
		
		<TR>
			<TD><b><hdiits:caption captionid="pay.salRevOrderName"
				bundle="${enLables}"></hdiits:caption>
			    </b>
			</TD>
			<TD><hdiits:select id="salRevOrder" name="salRevOrder" size="1"
				sort="false" validation="sel.isrequired" mandatory="true"
				captionid="pay.salRevOrderName" bundle="${commonLables}">
				<hdiits:option value="0" selected="true"> --Select-- </hdiits:option>
				<c:if test="${salRevMstVOList ne null }">
					<c:forEach var="salRevMst" items="${salRevMstVOList}">
						<hdiits:option value="${salRevMst.salRevId}">
							<fmt:formatDate var="orderDate" value="${salRevMst.revOrderDate}"
								pattern="dd/MM/yyyy" dateStyle="medium" />
							<c:out value="${salRevMst.revOrderNo}(${orderDate})">
							</c:out>
						</hdiits:option>
					</c:forEach>
				</c:if>
			</hdiits:select>
			</TD>

			<TD><b><hdiits:caption captionid="OHP.BillNo"
				bundle="${enLables}"></hdiits:caption> </b>
			</TD>
			<TD><hdiits:select name="billNo" size="1" id="billNo"
				sort="false" caption="BillNo" captionid="OHP.BillNo"
				validation="sel.isrequired" mandatory="true" bundle="${commonLables}" >
				<hdiits:option value="0" selected="true"> --Select-- </hdiits:option>
				<c:forEach var="billList" items="${billNoList}">
					<hdiits:option value="${billList.billHeadId}">
						<c:out value="${billList.billId}">
						</c:out>
					</hdiits:option>
				</c:forEach>
			</hdiits:select>
			</TD>
		</tr>

		<TR><td colspan="4">&nbsp;</td></TR>

		<tr>
			<TD class="fieldLabel" colspan="4"><jsp:include
				page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
				<jsp:param name="searchEmployeeTitle" value="Search Employee" />
				<jsp:param name="SearchEmployee" value="EmpSearch" />
				<jsp:param name="formName" value="empArrearReceipt" />
				<jsp:param name="functionName" value="chkFunc" />
			</jsp:include></td>
		</tr>

		<TR><td colspan="4">&nbsp;</td></TR>

		<TR ><td align="center" colspan="4" >
		<hdiits:button name="btnSearch" value="btnSearch" caption="Submit" 
		id="btnSearch" captionid="Submit" onclick="generateReceipt()" type="button"/>
		</td></TR>

	</table>

	</div>

	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
        document.arrearReceipt.salRevOrder.focus();
		
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

