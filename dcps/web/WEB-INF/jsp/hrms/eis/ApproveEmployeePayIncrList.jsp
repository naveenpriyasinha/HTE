<%
try{
%>
<%@page import="java.util.List"%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="resources.eis.eis_common_lables" var="enLables" scope="page" />
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resultValue" value="${resultObj.resultValue}"></c:set>

<c:set var="fixList" value="${resultValue.fixList}"></c:set>
<c:set var="fixListsize" value="${resultValue.fixListsize}"></c:set>
<c:set var="msg" value="${resultValue.msg}"></c:set>
<c:set var="save" value="${resultValue.save}"></c:set>
<c:set var="yearList" value="${resultValue.yearList}"></c:set>
<c:set var="currYear" value="${resultValue.currYear}"></c:set>
<%
List dataList = (List) pageContext.getAttribute("fixList");
int size = 0;
if(dataList != null)
{
	size = dataList.size();
}
pageContext.setAttribute("listSize",size);

%>

<script type="text/javascript">



function getCurrentYear()
{
	var tempDate=new Date();
	var tempYear=tempDate.getFullYear();
	return tempYear;
}

function getCurrentMonth()
{
	var tempDate=new Date();
	var tempMon=tempDate.getMonth();
	return tempMon;
}


function getOrderNo()
{	
	var length = '${listSize}';
	var orderNo=0;
	var check = true;
	if(length.value==0)
	{
 		return false;
	}
	for(var i = 1; i <= length ; i++)
	{
		if(document.getElementById("radioId"+i).checked)
		{
			check = false;
			orderNo=document.getElementById("tnrOrderNoId"+i).value;
			break;
		}
	}
	if(check)
	{
		alert("Please Select The Radio Button");
		return false;
	}	
	return orderNo;
}

function getDDOCode()
{	
	var length = '${listSize}';
	var ddoCode=0;
	var check = true;
	if(length.value==0)
	{
 		return false;
	}
	for(var i = 1; i <= length ; i++)
	{
		if(document.getElementById("radioId"+i).checked)
		{
			check = false;
			ddoCode=document.getElementById("ddoCode"+i).value;
			break;
		}
	}
	if(check)
	{
		alert("Please Select The Radio Button");
		return false;
	}	
	return ddoCode;
}
function verifyOrder()
{
	var ans = confirm(" Are you sure you want to Verify?");
	if(ans)
	{
		orderNo = getOrderNo() ; 
		ddoCode=getDDOCode();
		//alert(orderNo);
		//document.EmpIncr.save.value = "Verify";
		url = "./hrms.htm?actionFlag=showPayIncDataForNewOrderNo&orderNo="+orderNo+"&save=Verify&ddoCodeOfOrder="+ddoCode;
		//alert(url);
		document.EmpIncr.action = url;
		document.EmpIncr.submit();
		showProgressbar("Please wait...");
	}
}
function rejectOrder()
{

	var ans = confirm("Are you sure you want to Reject?");
	if(ans)
	{
		
		orderNo = getOrderNo() ; 
		ddoCode=getDDOCode();
		//document.EmpIncr.save.value = "Reject";
		url = "./hrms.htm?actionFlag=showPayIncDataForNewOrderNo&orderNo="+orderNo+"&save=Reject&ddoCodeOfOrder="+ddoCode;
		document.EmpIncr.action = url;
		document.EmpIncr.submit();
		showProgressbar("Please wait.....");
	}
}
//added by roshan 
function modifyOrder()
{
	var ans = confirm("Are you sure you want to Edit?");
	if(ans)
	{
		orderNo = getOrderNo() ; 
		//document.EmpIncr.save.value = "Reject";
		ddoCode=getDDOCode();
		url = "./hrms.htm?actionFlag=showPayIncDataForNewOrderNo&orderNo="+orderNo+"&save=modify&ddoCodeOfOrder="+ddoCode;
		document.EmpIncr.action = url;
		document.EmpIncr.submit();
		showProgressbar("Please wait.....");
	}
}
//ended by roshan
function fun_cancel()
{
	window.location="hrms.htm?actionFlag=validateLogin";
}

function getHistory(orderNo,locId)
{
	//alert("orderNo="+orderNo+" locId="+locId);
	var urlstyle = 'height=1800,width=800,toolbar=no,minimize=no,resizable=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=20,left=200';
	var url = './hdiits.htm?actionFlag=getIncPrintReport&locId='+locId+'&OrderNo='+orderNo;
	window.open(url,"",urlstyle);
}


function filterIncrement(){
	var year= document.getElementById("year").value;
	
	var url;

		url="./hrms.htm?actionFlag=getIncrementDataForReptDDO&year="+year;
		document.EmpIncr.action= url;
		document.EmpIncr.submit();
}



</script>
<body>
<hdiits:form name="EmpIncr" validate="true" method="POST" encType="multipart/form-data">

	<%--Added by roshan --%>
	<fieldset class="tabstyle"><legend> <b>Filter Increment by Year</b> </legend>
<table align="center">
<tr>
<td><c:out value="Year"></c:out></td>

<td><select name="year"
			id="year" style="width: 85%,display: inline;">
			<option title="Select" value="-1"><c:out value="-----Select------"></c:out></option>

			<c:forEach var="yearList" items="${yearList}">
			<c:choose> 
			<c:when test="${currYear==yearList.lookupShortName}">
							<option value="${yearList.lookupShortName}" title="${yearList.lookupDesc}" selected="selected">
						<c:out value="${yearList.lookupDesc}"/></option>
			</c:when>
			<c:otherwise>
						<option value="<c:out value="${yearList.lookupShortName}"/>" title="${yearList.lookupDesc}">
						<c:out value="${yearList.lookupDesc}"/></option>
						</c:otherwise>
						</c:choose>
					</c:forEach>
					
					
		</select></td>	
<td colspan="4" align="center"><input id="btnFilter" class="buttontag" type="button" size="5" maxlength="5"
		value="Filter" onclick="filterIncrement();"
		name="btnFilter" style="width: 120px;" /></td>
</tr>
</table>
</fieldset>
<%--Added by roshan --%>





<table width="100%">
		
	</table>
	<table align="right" width="100%">
		<tr>
			<td width='85%'>
				<font size='2' color='red'>
					Note <b>:</b> Annual Increment details entered through this form will be reflected in the Service Book
	 	 		</font>
			</td>
			<td align="center">
				<hdiits:button type="button" name="RejectOrder" id="RejectOrder" value="Reject Order" onclick="rejectOrder();"  />
			</td>
			<%---adeed by roshan
			<td>
				<hdiits:button type="button" name="editOrder" id="editOrder" value="Edit Order" onclick="modifyOrder();"  />
			</td>---%>
			<%---adeed by roshan---%>
			<td >
				<hdiits:button type="button" name="VerifyOrder" id="VerifyOrder" value="Verify Order" onclick="verifyOrder();"  />
			</td>
		</tr>	
	</table>
	<br/>
	<br/>
	<table width="60%" cellpadding="0" cellspacing="0">
		<tr>
			<td width="100%">
				<table width="100%" id='tab_new_order'  align="left"  style="display: none" >
					<tr> 
						<td width="20%">
							<b>Increment Certificate Order No</b>
						</TD>
						<td width="20%">	 
			    			 <input type="text" size='25' maxlength='50' id='incrementCertificateOrderNo' name='incrementCertificateOrderNo' onKeyPress="" title="Should be numbers ,alphabates and / only">
			    		</td>
						<TD  align="left"  width="20%">
							<b>Order Date</b>
						</td>			
						<td width="20%" align="left">
							<hdiits:dateTime name="orderDate"  mandatory="true" validation="txt.isdt,txt.isrequired" captionid="orderDate" />
						</td>
						<td width="20%" >
				    	 	&nbsp;
						</td>
					</tr>
				</table>
			</td>
		</tr>
		
	</table>
	
	<div style="overflow: scroll;" id="tcontent1" class="halftabcontent">
		<div style="height: 200px;" align="center"> 
			<h1>
				<c:set var = "i" value = "1" ></c:set>
				<display:table name="${fixList}" requestURI="" pagesize="" id="orderList" export="false" >
					<display:column title="" headerClass="datatableheader" style="text-align: center;">
		  				<c:choose>
							<c:when test="${orderList[3] eq 'Verified' || orderList[3] eq 'Rejected'}">		
								<input name="radio" id="radioId${i}" type="radio" align="middle" align="middle" disabled="true"/>
								<hdiits:hidden name = "tnrOrderNo${i}" id = "tnrOrderNoId${i}" default ="${orderList[0]}"/>
							</c:when>
							<c:otherwise>	
								<input name="radio" id="radioId${i}" type="radio" align="middle" align="middle"/>
								<hdiits:hidden name = "tnrOrderNo${i}" id = "tnrOrderNoId${i}" default ="${orderList[0]}"/>
							</c:otherwise>
						</c:choose>
					</display:column>
		  			<display:column  class="tablecelltext" title="Increment Order No"  headerClass="datatableheader" style="text-align: center;" >
		    			${orderList[0]}     
		    		</display:column>
		    		
		    		<display:column  class="tablecelltext" title="DDO Code"  headerClass="datatableheader" style="text-align: center;" >
		    			${orderList[4]} 
		    			<hdiits:hidden name = "ddoCode${i}" id = "ddoCode${i}" default ="${orderList[4]}"/>    
		    		</display:column>
		    		
		    		<display:column  class="tablecelltext" title="DDO Office"  headerClass="datatableheader" style="text-align: center;" >
		    			${orderList[5]}     
		    		</display:column>
		    		
		    		
		  			<display:column class="tablecelltext" title="Increment Order Date" headerClass="datatableheader" style="text-align: center;">
		  			${orderList[2]}    
		  			</display:column>	
              			
	  				<display:column  class="tablecelltext" title="Status" headerClass="datatableheader" style="text-align: center;">
	  				${orderList[3]}    
	  				</display:column>
					<display:column title="Action" headerClass="datatableheader" style="text-align: center;">
		 	 	  		<a href="javascript:getHistory('${orderList[0]}','${orderList[1]}')"><b>Print Report</b></a>
		  			</display:column>
					<display:column  headerClass="datatableheader" style="display:none">
		  				<input type="text" name="disableFlag" id="disableFlag" value="${orderList[3]}"></input>
		  			</display:column>
		  			<display:setProperty name="export.pdf" value="true" />
			  	  	<c:set var="i" value="${i+1}"></c:set>
 		  		</display:table>
    		</h1>
  		</div>
  	</div>
  	
	
	
</hdiits:form>
<script type="text/javascript">
showMsg();
</script>
</body>
<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>
