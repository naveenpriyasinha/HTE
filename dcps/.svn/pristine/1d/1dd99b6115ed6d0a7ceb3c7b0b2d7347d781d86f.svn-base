<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>


<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page import="java.util.List"%>
<%@page import="com.tcs.sgv.eis.valueobject.HrPayPaybill"%>
<%@page import="com.tcs.sgv.eis.valueobject.HrPayEdpCompoMpg"%>
<%@page import="com.tcs.sgv.allowance.valueobject.PaybillOutputVO"%>
<%@page import="java.lang.reflect.Method"%>
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

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="resultPaybillVo" value="${resValue.resultPaybillVo}" ></c:set>
<c:set var="hrAdvList" value="${resValue.hrAdvList}" ></c:set>
<c:set var="hrAllowList" value="${resValue.hrAllowList}" ></c:set>
<c:set var="hrDeducList" value="${resValue.hrDeducList}" ></c:set>
<c:set var="hrMiscRecoveryList" value="${resValue.hrMiscRecoveryList}" ></c:set>
<c:set var="hrLoanList" value="${resValue.hrLoanList}" ></c:set>
<c:set var="otherId" value="${resValue.otherId}" ></c:set>
<c:set var="empAllRec" value="${resValue.empAllRec}" ></c:set>


<script>
			  function onBackfn()
			  {		
			  	
						if(document.forms[0].givenurl!=null)
						{
						var url="./hrms.htm?actionFlag="+document.forms[0].givenurl.value;
						
						window.location=url;
						//document.forms[0].action=url;
						}
						else
						{
						window.location="./hrms.htm?actionFlag=getHomePage";
						//document.forms[0].action="./hrms.htm?actionFlag=getHomePage";
						}
						//document.forms[0].submit();
			  }
			  function closeWindow()
				{
					window.close();
				}
			  
</script>
	
<hdiits:form name="previewbill" validate="">
<table width = "100%">
<tr>
	<td colspan = "3" align ="center"><hr width="100%">
				<font size="3"><u><b><c:out value="${resultPaybillVo.hrEisEmpMst.orgEmpMst.empFname} ${resultPaybillVo.hrEisEmpMst.orgEmpMst.empMname} ${ resultPaybillVo.hrEisEmpMst.orgEmpMst.empLname}"/></b></u></font>
			<hr width="100%">
	</td>
</tr>

<tr>
	<td aling ="center" width="33%">
		<b>Allowances (A):</b><hr width="100%">
	</td>
	<td aling ="center" width="33%">
		<b>Deducutions (B):</b><hr width="100%">
	</td>
	<td aling ="center" width="33%">
		<b>Loans (C):</b><hr width="100%">
	</td>
	
</tr>
<%

HrPayPaybill payBillOut = (HrPayPaybill)pageContext.getAttribute("resultPaybillVo");

long basicSalary = Math.round(payBillOut.getBasic0101())+Math.round(payBillOut.getBasic0102());
long leaveSalary = Math.round(payBillOut.getLs());
long leaveEncashment = Math.round(payBillOut.getLe());
long dp = Math.round(payBillOut.getAllow0119())+Math.round(payBillOut.getAllow0120());
long incomeTax = Math.round(payBillOut.getIt());
long surcharge = Math.round(payBillOut.getSurcharge());
long grossAmount = Math.round(payBillOut.getGrossAmt());
long totalDeduc = Math.round(payBillOut.getTotalDed());
long netAmount = Math.round(payBillOut.getNetTotal());




%>
<tr>
	<td aling ="center" width="33%"  valign="top">
		<table width = "100%">
			<tr>
				<td  aling ="center" width="65%">
					<b>Basic :</b>
				</td>
				<td  aling ="center">
					<%=basicSalary %>
				</td>
			</tr>
			<tr>
				<td  aling ="center" width="65%">
					<b>Leave Salary :</b>
				</td>
				<td  aling ="center">
					<%= leaveSalary%>
				</td>
			</tr>
			<tr>
				<td  aling ="center" width="65%">
					<b>Leave Encashment :</b>
				</td>
				<td  aling ="center">
					<%= leaveEncashment%>
				</td>
			</tr>
			<tr>
				<td  aling ="center">
					<b>Dearness Pay :</b>
				</td>
				<td  aling ="center">
					<%=dp %>
				</td>
			</tr>
			
			<% List hrAllowList = (List)pageContext.getAttribute("hrAllowList");
				if(hrAllowList!=null && hrAllowList.size()>0)
				{
					String shortName = "";
					String edpCode = "";
					String mthdName = "";
					long basic;
					for(int i=0;i<hrAllowList.size();i++)
					{
						
						HrPayEdpCompoMpg edpCompMpg = (HrPayEdpCompoMpg)hrAllowList.get(i);
						
						shortName=edpCompMpg.getRltBillTypeEdp().getEdpShortName();
						edpCode = edpCompMpg.getRltBillTypeEdp().getEdpCode();
						 mthdName = "getAllow"+edpCode;

						 Class pay = payBillOut.getClass();
							Method payMthd = pay.getMethod(mthdName, null);
							basic = Math.round((Double)payMthd.invoke(payBillOut, null));
							
							
					
			%>
				<tr>
					<td  aling ="center">
					
						<b><%= shortName %> :</b>
					</td>
					<td  aling ="center">
						<%= basic %>
					</td>
				</tr>
	
			<%}} %>
			
		</table>
	</td>
	<td aling ="center" width="33%"  valign="top">
		<table width = "100%">
		
		<% List hrDeducList = (List)pageContext.getAttribute("hrDeducList");
				if(hrDeducList!=null && hrDeducList.size()>0)
				{
					String shortName = "";
					String edpCode = "";
					String mthdName = "";
					long basic;
					for(int i=0;i<hrDeducList.size();i++)
					{
						
						HrPayEdpCompoMpg edpCompMpg = (HrPayEdpCompoMpg)hrDeducList.get(i);
						
						shortName=edpCompMpg.getRltBillTypeEdp().getEdpShortName();
						edpCode = edpCompMpg.getRltBillTypeEdp().getEdpCode();
						 mthdName = "getDeduc"+edpCode;

						 Class pay = payBillOut.getClass();
							Method payMthd = pay.getMethod(mthdName, null);
							basic = Math.round((Double)payMthd.invoke(payBillOut, null));
							
							
					
			%>
		
				<tr>
					<td  aling ="center" width="65%">
					
						<b><%= shortName %> :</b>
					</td>
					<td  aling ="center">
						<%= basic %>
					</td>
				</tr>
			
			<%}} %>
		
			<tr>
					<td  aling ="center">
					
						<b>InCome TAX :</b>
					</td>
					<td  aling ="center">
						<%=incomeTax %>
					</td>
				</tr>
				<tr>
					<td  aling ="center">
					
						<b>Surcharge :</b>
					</td>
					<td  aling ="center">
						<%=surcharge %>
					</td>
				</tr>
			<% List hrMiscRecoveryList = (List)pageContext.getAttribute("hrMiscRecoveryList");
				if(hrMiscRecoveryList!=null && hrMiscRecoveryList.size()>0)
				{
					String shortName = "";
					String edpCode = "";
					String mthdName = "";
					long basic;
					for(int i=0;i<hrMiscRecoveryList.size();i++)
					{
						
						HrPayEdpCompoMpg edpCompMpg = (HrPayEdpCompoMpg)hrMiscRecoveryList.get(i);
						
						shortName=edpCompMpg.getRltBillTypeEdp().getEdpShortName();
						edpCode = edpCompMpg.getRltBillTypeEdp().getEdpCode();
						 mthdName = "getDeduc"+edpCode;

						 Class pay = payBillOut.getClass();
							Method payMthd = pay.getMethod(mthdName, null);
							basic = Math.round((Double)payMthd.invoke(payBillOut, null));
							
							
					
			%>
		
				<tr>
					<td  aling ="center">
					
						<b><%= shortName %> :</b>
					</td>
					<td  aling ="center">
						<%= basic %>
					</td>
				</tr>
			
			<%}} %>
			
		</table>
	</td>
	<td aling ="center" width="33%"  valign="top">
		<table width = "100%">
			
			<% List hrLoanList = (List)pageContext.getAttribute("hrLoanList");
				if(hrLoanList!=null && hrLoanList.size()>0)
				{
					String shortName = "";
					String edpCode = "";
					String mthdName = "";
					String intMthdName = "";
					long basic;
					long basicInt;
					for(int i=0;i<hrLoanList.size();i++)
					{
						
						HrPayEdpCompoMpg edpCompMpg = (HrPayEdpCompoMpg)hrLoanList.get(i);
						
						shortName=edpCompMpg.getRltBillTypeEdp().getEdpShortName();
						edpCode = edpCompMpg.getRltBillTypeEdp().getEdpCode();
						 mthdName = "getLoan"+edpCode;
						 intMthdName="getLoanInt"+edpCode;

						 Class pay = payBillOut.getClass();
							Method payMthd = pay.getMethod(mthdName, null);
							basic = Math.round((Double)payMthd.invoke(payBillOut, null));
							
							Method intPayMthd = pay.getMethod(intMthdName, null);
							basicInt = Math.round((Double)intPayMthd.invoke(payBillOut, null));
							
							
					
			%>
		
				<tr>
					<td  aling ="center"  width="65%">
					
						<b><%= shortName %> :<br>
						<%= shortName %> Interest :
						</b>
					</td>
					<td  aling ="center">
						<%= basic %><br>
						<%= basicInt %>
					</td>
				</tr>
			
			<%}} %>
			
			
			
			<% List hrAdvList = (List)pageContext.getAttribute("hrAdvList");
				if(hrAdvList!=null && hrAdvList.size()>0)
				{
					String shortName = "";
					String edpCode = "";
					String mthdName = "";
					long basic;
					for(int i=0;i<hrAdvList.size();i++)
					{
						
						HrPayEdpCompoMpg edpCompMpg = (HrPayEdpCompoMpg)hrAdvList.get(i);
						
						shortName=edpCompMpg.getRltBillTypeEdp().getEdpShortName();
						edpCode = edpCompMpg.getRltBillTypeEdp().getEdpCode();
						 mthdName = "getAdv"+edpCode;

						 Class pay = payBillOut.getClass();
							Method payMthd = pay.getMethod(mthdName, null);
							basic = Math.round((Double)payMthd.invoke(payBillOut, null));
							
						if(edpCode.equals("9670"))
						{
							 mthdName = "getAdvIV"+edpCode;

							 Class pay1 = payBillOut.getClass();
								Method payMthd1 = pay.getMethod(mthdName, null);
								long basic1 = Math.round((Double)payMthd1.invoke(payBillOut, null));
							basic=basic+basic1;
						}
					
			%>
		
				<tr>
					<td  aling ="center">
					
						<b><%= shortName %> :</b>
					</td>
					<td  aling ="center">
						<%= basic %>
					</td>
				</tr>
			
			<%}} %>
			
		</table>
	</td>
	
	
</tr>

<tr>
	<td  aling ="center"><br><br>
	<table width="100%">
	<tr>
		<td width="65%"><hr width="100%">
		<b>Total Allowance [ A ] :</b><hr width="100%">
		</td>
		<td><hr width="100%"><b><%=grossAmount %></b><hr width="100%">
		</td>
	</tr>
	
	</table>
	
	</td>
	<td  aling ="center"><br><br>
	<table width="100%">
	<tr>
		<td width="65%"><hr width="100%">
		<b>Total Deductions [ B ] :</b><hr width="100%">
		</td>
		<td><hr width="100%"><b><%=totalDeduc %></b><hr width="100%">
		</td>
	</tr>
	
	</table>
	
	</td>
	<td  aling ="center"><br><br>
	<table width="100%">
	<tr>
		<td width="65%"><hr width="100%">
		<b>Net Amount [ A - B - C ] :</b><hr width="100%">
		</td>
		<td><hr width="100%"><b><%=netAmount %></b><hr width="100%">
		</td>
	</tr>

	 	<c:choose>
 	<c:when test="${empAllRec eq true }">
<hdiits:hidden default="getOtherData&Employee_ID_EmpOtherSearch=${resultPaybillVo.hrEisEmpMst.orgEmpMst.empId}&empAllRec=true" name="givenurl"/>
</c:when>
<c:otherwise>
<hdiits:hidden default="getOtherData&otherId=${otherId}&edit=Y&empAllRec=false" name="givenurl"/>
</c:otherwise>
</c:choose>
				
	</table>


	</td>
	
</tr>
<tr> <td align="center" colspan="3">		
	<hdiits:button name="Close" value="Close" type="button" captionid="eis.close"	bundle="${Lables}" onclick="closeWindow();" /></td></tr>
</table>

</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>