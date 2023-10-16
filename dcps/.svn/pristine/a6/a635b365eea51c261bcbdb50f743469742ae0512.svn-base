
<%
try {
%>

<%@ include file="../../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="java.util.List"%>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/Address.js"/>">
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<fmt:setBundle basename="resources.eis.eis_common_lables"	var="adminITGPFDetails" scope="request" />
<fmt:setBundle basename="resources.Payroll" var="commonLable" scope="request"/>
<fmt:message var="permanent" key="permanent" bundle="${commonLable}" scope="request"> </fmt:message>
<fmt:message var="fixed" key="fixed" bundle="${commonLable}" scope="request"> </fmt:message>
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="curbill" value="${resValue.curbill}" ></c:set>
<c:set var="billNosList" value="${resValue.BillList}" ></c:set>
<c:set var="dataList" value="${resValue.DataList}" ></c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>
<%
List dataList = (List) pageContext.getAttribute("dataList");
int size = 0;
if(dataList != null)
{
	size = dataList.size();
}
pageContext.setAttribute("listSize",size);

%>

<script type="text/javascript">
	function validateForm1()
	{
		var listsize = '${listSize}';
		var billNo = document.updateITGpfdetails.billNo.value;
		 if(listsize == 0)
	 	 	alert("No Records to Save ! ! !");
		 document.updateITGpfdetails.action = "./hdiits.htm?actionFlag=UpdateITGpfDetails&listSize=${listSize}&billNo=${curbill}";
		 document.updateITGpfdetails.submit();
	}
	function resetForm1()
	{
		document.getElementById("ITGpfDetailsId").click();
		
	}
	function showITGpfDetails()
	{
		var billNo = document.updateITGpfdetails.billNo.value;
		document.updateITGpfdetails.action = "./hdiits.htm?actionFlag=showITGpfDetails&billNo="+billNo; 
		document.updateITGpfdetails.submit();
	}
	function chkgpfdeducted(id,i)
	{
		 var currentgpf = document.getElementById("GpfId" + i).value;
		 var oldgpf = document.getElementById("OldvalGpf" + i).value;
		 var empType = document.getElementById("empType" + i).value;
		 if(empType == "${fixed}")
		 {
			 alert("This Is a Fixed Type Employee ");
			 document.getElementById("GpfId" + i).style.background="#ccccff";
			 document.getElementById("GpfId" + i).value=oldgpf;
			 document.getElementByName("txtGpf" + i).disable=true;
		 }
	}
	
</script>

<hdiits:form name="updateITGpfdetails" validate="true" method="POST" action="javascript:validateForm1()">

	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b> <hdiits:caption
			captionid="UpdateITGPFDetails" bundle="${adminITGPFDetails}"></hdiits:caption>
		</b></a></li>
	</ul>
	</div>
	
	<br><br>
	<table width="85%" colspan = 3 border="3" bordercolor=#ccccff  bgcolor="white" align="center" id="searchTable">
	<tr>	  <td align = "center" width = "33%"><b> Bill &nbsp; &nbsp; Number &nbsp; &nbsp; :   </b> 
			  </td>
		      <td align = "center" width = "33%">
	      		<hdiits:select name="billNo"  id="billNo" size="1" caption="Bill No" sort="false" onchange= "showITGpfDetails()" > 
					<hdiits:option value="" selected="true">-------Selected--------</hdiits:option>
				<c:forEach items="${billNosList}" var="billNosList">
				<c:choose>
					<c:when test="${billNosList.billHeadId == curbill}">
							<hdiits:option value="${billNosList.billHeadId}" selected="true" > ${billNosList.billId}</hdiits:option>
					</c:when>
						<c:otherwise>
							<hdiits:option value="${billNosList.billHeadId}"> ${billNosList.billId}</hdiits:option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				</hdiits:select>		       
	    	 </TD>
	    	 <td align = "center" width = "33%"> <hdiits:button type="button" name="btn" value="showITGpfDetails" caption="Show IT - GPF Details" 
				id="ITGpfDetailsId" captionid="Show IT - GPF Details" onclick="showITGpfDetails()"/> 
			</td>
     </tr>
     </table>
	<br><br>
<c:if test="${listSize gt 0}">
<div>
	<table  border="2"  align = "center">
		<tr>
			
			<TD><b>Employee name</b></td>
			<TD  align = "center" ><b>GPF </b></td>
			<TD  align = "center" ><b>IT </b></td>
			<TD><b>Employee name</b></td>
			<TD  align = "center" ><b>GPF </b></td>
			<TD  align = "center" ><b>IT </b></td>
			
			<TD><b>Employee name</b></td>
			<TD  align = "center" ><b>GPF </b></td>
			<TD  align = "center" ><b>IT </b></td>
		</tr>
		<c:set value="0" var="k"></c:set>
		<c:forEach var="row" items="${dataList}">
		<!--<c:set var="k" value="${k+1}"></c:set>-->
		<c:if test="${k%3  == 1}">
			<TR>
		</c:if>
		<td width = "23%">
						${k}. <b>  ${row.empName}</b>(${row.psrNo})(${row.dsgn})	
					</td>
					<td >
						<hdiits:number default ="${row.gpf}" id = "GpfId${k}" name="txtGpf${k}" captionid="txtgpf"  caption ="txtgpf" maxlength="8" size="5" 
								onblur="chkgpfdeducted(this.id, ${k})"/>
						<hdiits:hidden name = "oldGpf${k}" default ="${row.gpf}" id = "OldvalGpf${k}"/>
						<hdiits:hidden name = "pkGpfid${k}" default = "${row.gpfPk}"/>
						<hdiits:hidden name = "empId${k}" default = "${row.empId}"/>
						<hdiits:hidden name = "empType${k}" id = "empType${k}" default = "${row.empType}"/>
					</td>
					<td >
						<hdiits:number default ="${row.IT}" id = "IT${k}" name="txtIT${k}" captionid="txtIT"  caption ="txtIT" maxlength="8" size="5" 
								onblur="chkITdeducted(this.id, ${k})"/>
						<hdiits:hidden name = "oldIT${k}" default ="${row.IT}" id = "OldvalIT${k}"/>
						<hdiits:hidden name = "pkITid${k}" default = "${row.itPk}"/>
					</td>
					
		<c:if test="${k%3  == 0}">		
			</TR>
		</c:if>	
		<c:if test="${k eq listSize}">		
			</TR>
		</c:if>	
		</c:forEach>
	</table>
</div>
</c:if>

<c:if test="${listSize eq 0}">
<center> No Records To Display ! ! !</center>
</c:if>	
		<br>
	
	
	
	
	
	
	
	<center>
		<jsp:include page="../../core/PayTabnavigation.jsp" />
				 <hdiits:hidden default="getHomePage" name="givenurl"/>
	</center>
<br>
	
	
	
	
	
	
		
<hdiits:validate locale="${locale}" controlNames="" />
<script type="text/javascript">
	initializetabcontent("maintab")
</script>
</hdiits:form>

<script type="text/javascript">
if('${msg}'!= null && '${msg}'!='')
{
	alert('${msg}');
	resetForm1();
}

</script>

<%
}catch(Exception e) {e.printStackTrace();}
%>
	