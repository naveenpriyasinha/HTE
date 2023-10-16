<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.tcs.sgv.eis.valueobject.PayslipBasicDetailsVO"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page import="java.util.*;"%>

<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript" src="<c:url value="script/common/tabcontent.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>
<script type="text/javascript" src="common/script/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/OuterPaybillPara.js"/>"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>
<c:set var="paybillColumns" value="${resultValue.paybillColumns}"></c:set>
<c:set var="paybillColumnSize" value="${resultValue.paybillColumnSize}"></c:set>
<c:set var="billList" value="${resultValue.billList}"></c:set>
<c:set var="empList" value="${resultValue.empList}"></c:set>
<c:set var="yearList" value="${resultValue.yearList}" ></c:set>
<c:set var="monthList" value="${resultValue.monthList}" ></c:set>
<c:set var="searchData" value="${resultValue.searchData}" ></c:set>
<c:set var="counter" value="${0}"></c:set>
<c:set var="Month" value="${resultValue.Month}" ></c:set>
<c:set var="Year" value="${resultValue.Year}" ></c:set>
<c:set var="empId" value="${resultValue.empId}" ></c:set>


<script>
function selectionedValue()
{
	if('${empId}' > 0)
	{
		document.NetMismatch.EmpDtlCombo.value = '${empId}';
	}

	if('${Month}' > 0)
	{
		document.NetMismatch.cmbFromMonth.value = '${Month}';
	}

	if('${Year}' > 0)
	{
		document.NetMismatch.cmbFromYear.value = '${Year}';
	}

	
}
function getData()
{
	var empno_index = document.NetMismatch.EmpDtlCombo.selectedIndex;
	var empno = document.NetMismatch.EmpDtlCombo.options[empno_index].value;

	var selMonth_index = document.NetMismatch.cmbFromMonth.selectedIndex;
	var selMonth = document.NetMismatch.cmbFromMonth.options[selMonth_index].value; 

	var selYear_index = document.NetMismatch.cmbFromYear.selectedIndex;
	var selYear = document.NetMismatch.cmbFromYear.options[selYear_index].value;


	if(empno == -1)
	{
		alert("Please select Employee Name");
		document.getElementById("EmpDtlCombo").focus();
		return false;
	}
	else if(selYear == -1)
	{
		alert("Please select For Year");
		document.getElementById("cmbFromYear").focus();
		return false;
	}
	else if(selMonth == -1)
	{
		alert("Please select For Month");
		document.getElementById("cmbFromMonth").focus();
		return false;
	}
	
	var url='./hrms.htm?actionFlag=NetMismatchScreen&EmpId='+empno+'&FromJSP=Y&Month='+selMonth+'&Year='+selYear;
	document.NetMismatch.action = url;
	document.NetMismatch.submit();
	showProgressbar("Please wait...");
}
function onclosefunction()
{
		window.location="hrms.htm?actionFlag=validateLogin";
}

</script>
<hdiits:form method="Post" name="NetMismatch" validate="true" >
<fieldset class="tabstyle">
<legend>
	<b>Net Mismatch Criteria</b>
</legend>
	<table width="100%" align="center">
		<tr>
			<td align="center" width="25%"  id="FromYear1" >
				For Year
			</td>
			<td align="left" width="25%" id="FromYear2">
				<hdiits:select name="selFromYear" size="1" sort="false" id="cmbFromYear" onchange="GetFromMonths()"> 
	   				<hdiits:option value="-1"> --Select-- </hdiits:option>
	 				<c:forEach items="${yearList}" var="FromyearList">
 	     				<hdiits:option value="${FromyearList.lookupShortName}"> ${FromyearList.lookupDesc} </hdiits:option>
	    			</c:forEach>
	   			</hdiits:select>
			</td>
			<td  align="center" width="25%" id="FromMonth1">
				For Month
			</td>
			<td  align="left" width="25%" id="FromMonth2">
				<hdiits:select name="selFromMonth" size="1" sort="false" id="cmbFromMonth" > 
	   				<hdiits:option value="-1"> --Select-- </hdiits:option>
	 				<c:forEach items="${monthList}" var="FrommonthList">
 	     				<hdiits:option value="${FrommonthList.lookupShortName}"> ${FrommonthList.lookupDesc} </hdiits:option>
	    			</c:forEach>
	   			</hdiits:select>
			</td>
		</tr>
		<tr>
			<td align="center" width="20%" >
				Employee List
			</td>
<!--			<td colspan="8" class="Label" width="80%" align="left">-->
<!--				<select style="width:450px" name="BillGrpCombo" id="BillGrpCombo" mandatory="true" validation="sel.isrequired">-->
<!--					<option value="-1">----------------------Select----------------------</option>-->
<!--					<c:forEach items="${billList}" var="billList">-->
<!--						<option value="${billList.dcpsDdoBillGroupId}" title="${billList.dcpsDdoBillDescription}"><c:out value="${billList.dcpsDdoBillDescription}"> </c:out></option>-->
<!--					</c:forEach>-->
<!--				</select>-->
<!--			</td>-->
			<td colspan="8" class="Label" width="80%" align="left">
				<select style="width:450px" name="EmpDtlCombo" id="EmpDtlCombo" mandatory="true" validation="sel.isrequired">
					<option value="-1">----------------------Select----------------------</option>
					<c:forEach items="${empList}" var="empList">
						<option value="${empList.empId}" title="${empList.empName}"><c:out value="${empList.empName}"> </c:out></option>
					</c:forEach>
				</select>
			</td>
			
		</tr>
		<tr>
			<td colspan="9" width="100%" align="center">
				<hdiits:button name="Search" style="text-align:center;" id="Search"	type="button" caption="Search" onclick="getData()" /> 
				<hdiits:button name="btnClose1" style="text-align:center;" type="button" caption="Close" onclick="onclosefunction()" />
			</td>
		</tr>
	</table>
</fieldset >
<%
if(pageContext.getAttribute("searchData") != null)
{
%>
<br/>
<br/>
<fieldset class="tabstyle">
<legend>
	<b>Net Mismatch Reuslt</b>
</legend>
	<table width="80%" align="center" border="1" bordercolor="black">
		<tr>
			<c:forEach items="${paybillColumns}" var="paybillColumns">
				<td id="tdID${counter}">
					${paybillColumns}
				</td>
				<c:set var="counter" value="${counter+1}"></c:set>
	    	</c:forEach>
	    	<td>
	    		Total
	    	</td>
		</tr>
		<%
			long Total = 0;
			List searchData = (List) pageContext.getAttribute("searchData");
			int searchDataSize = searchData.size();
			int paybillColumnSize = Integer.parseInt(pageContext.getAttribute("paybillColumnSize").toString());
			Object[] cellObj ;
			Long[] colTotal = new Long[paybillColumnSize+1];
			for(int noRec = 0; noRec<searchDataSize ; noRec++)
			{
				Total = 0;
				cellObj = (Object[])searchData.get(noRec);
		%>
				<tr>
				<c:set var="counter" value="${0}"></c:set>
		<%
					for(int noCol = 0 ; noCol < paybillColumnSize ; noCol++)
					{
						if(noCol!=0)
						{
							Total += Long.parseLong(cellObj[noCol].toString());
							if(colTotal[noCol]!=null)
								colTotal[noCol] += Long.parseLong(cellObj[noCol].toString());
							else
								colTotal[noCol] = Long.parseLong(cellObj[noCol].toString());
						}
		%>
						<td id="tdID${counter}">
							<%=cellObj[noCol].toString() %>
						</td>
						<c:set var="counter" value="${counter+1}"></c:set>
		<%			}
					if(Total>0)
					{
		%>
						<td><b><%=Total%></b></td>
		<%			}
					else
					{
		%>
						<td><%=Total%></td>
		<%			}
		%>
				</tr>
		<%	
			}
		%>
	</table>
</fieldset >
<%} %>
</hdiits:form>
<script type="text/javascript">
//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
initializetabcontent("maintab")
</script>
<script type="text/javascript">
selectionedValue();
</script>
<%
} 
catch (Exception e) 
{
	e.printStackTrace();
}
%>