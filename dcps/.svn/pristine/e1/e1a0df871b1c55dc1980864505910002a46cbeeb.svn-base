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
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="paybillMonth" value="${resValue.paybillMonth}" ></c:set>
<c:set var="paybillYear" value="${resValue.paybillYear}" ></c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>


<script type="text/javascript">
function SearchData()
{
	month = document.reverLoanScreen.paybillMonth.value;
    year = document.reverLoanScreen.paybillYear.value;
    xmlHttp=GetXmlHttpObject();
    if(month == -1)
    {
        alert("Please select Month");
        return;
    }
    else if(year == -1)
    {
        alert("Please select Year");
        return;
    }
    else
    {
		url='./ifms.htm?actionFlag=revertLoanInst&month='+month+'&year='+year+'&revertFlag=false';
		xmlHttp.onreadystatechange=fillPaybillCmb;
		xmlHttp.open("POST",encodeURI(url),true);
		xmlHttp.send(null);	
    }
}

function fillPaybillCmb()
{
	if (xmlHttp.readyState==complete_state)
	{
		clearCombo("paybillBill");
		var paybillBillCmb = document.getElementById("paybillBill");
		var XMLDoc=xmlHttp.responseXML.documentElement;		
		
		if(XMLDoc==null)
        {
			
			window.status = 'No Records Found.';
		}
		else
        {
			window.status='';
            var billEntries = XMLDoc.getElementsByTagName('bill-data');
   			for ( var i = 0 ; i < billEntries.length ; i++ )
 			{
   				val = billEntries[i].childNodes[0].firstChild.nodeValue;     
				text = billEntries[i].childNodes[1].firstChild.nodeValue;    		     				   
				var y = document.createElement('option');
			    y.value=val;
			    y.text=text;
			    y.title=text;
			    try
			    {	      				    					
			    	paybillBillCmb.add(y,null);
   		        }
   				catch(ex)
				{
   					paybillBillCmb.add(y); 
				}
 			}
        }
	} 
}

function clearCombo(id)
{
	var v = document.getElementById(id).length;
	for(var i = 1; i < v; i++)
	{
		lgth = document.getElementById(id).options.length -1;
		document.getElementById(id).options[lgth] = null;
	}		
}

function revertData()
{
	var month = document.reverLoanScreen.paybillMonth.value;
    var year = document.reverLoanScreen.paybillYear.value;
	var billNo = document.reverLoanScreen.paybillBill.value;
    if(month == -1)
    {
        alert("Please select Month");
        return;
    }
    else if(year == -1)
    {
        alert("Please select Year");
        return;
    }
    else if (billNo == -1)
    {
    	 alert("Please select Bill");
         return;
    }
    else
    {
		var url="./ifms.htm?actionFlag=revertLoanInst&month="+month+"&year="+year+"&billNo="+billNo+'&revertFlag=true';
    	document.reverLoanScreen.action=url;
    	document.reverLoanScreen.submit();
    	showProgressbar("Please wait...");
    }
}
</script>
<body>
<hdiits:form name="reverLoanScreen" validate="true" method="POST"	encType="multipart/form-data">
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>Revert Installments</b></a></li>
	</ul>
</div>
<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >
<font color="red"><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Bill Needs to be Re Generated Once the Loan Installments get Reverted.</b></font>
<br/><br/>
<table width="60%" align="center">
	<tr>
		<td width="30%" align="center">
			<b>	Month</b>
		</td>
		<td width="30%" align="left">
			<hdiits:select name="paybillMonth" size="1" sort="false" caption="Month" id="paybillMonth" mandatory="true" validation="sel.isrequired" onchange="SearchData();">
				<hdiits:option value="-1"> --Select-- </hdiits:option>
				<c:forEach items="${paybillMonth}" var="month">
					<c:choose>
           				<c:when test="${paybillMonth == month.lookupShortName}">
			 					<hdiits:option selected="true"  value="${month.lookupShortName}"> ${month.lookupDesc}</hdiits:option>
						</c:when>
						<c:otherwise>
			 					<hdiits:option  value="${month.lookupShortName}"> ${month.lookupDesc} </hdiits:option>
						</c:otherwise>
					</c:choose>		
				</c:forEach>
			</hdiits:select>
		</td>
	</tr><tr/><tr/><tr/><tr/><tr/>	
	<tr>
		<td width="30%" align="center">
			<b>Year</b>
		</td>
		<td width="30%" align="left">
			<hdiits:select name="paybillYear" size="1" sort="false" caption="Year" id="selYear" mandatory="true" validation="sel.isrequired" onchange="SearchData();">
				<hdiits:option value="-1"> --Select-- </hdiits:option>
					<c:forEach items="${paybillYear}" var="year">
						<c:choose>
           					<c:when test="${paybillYear == year.lookupShortName}">
			 						<hdiits:option selected="true"  value="${year.lookupShortName}"> ${year.lookupDesc}</hdiits:option>
							</c:when>
							<c:otherwise>
			 						<hdiits:option  value="${year.lookupShortName}"> ${year.lookupDesc} </hdiits:option>
							</c:otherwise>
						</c:choose>		
					</c:forEach>
			</hdiits:select>
		</td>
	</tr><tr/><tr/><tr/><tr/><tr/>
	<tr>
		<td  width="30%" align="center">
			<b>Generated Bill</b>
		</td>
		<td width="30%" align="left">
			<select name="paybillBill" size="1"  style="width:500px"  id="paybillBill" onchange="">
				<option value="-1"> --Select-- </option>
			</select>
		</td>
	</tr><tr/><tr/><tr/><tr/><tr/>
	<tr>
		<td  align="center" width="60%" colspan="2">
			<hdiits:button name="revert" style="text-align:center;width:180px" id="revert"	type="button" caption="Revert Loan Insrtallments" onclick="revertData();" />
		</td> 
	</tr>
</table>
</div>
</hdiits:form>
<script type="text/javascript">
initializetabcontent("maintab");
if("${msg}" != "")
{
	alert("${msg}");
	var url="./ifms.htm?actionFlag=revertLoanInst";
	document.reverLoanScreen.action=url;
	document.reverLoanScreen.submit();
}
</script>
</body>
<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>