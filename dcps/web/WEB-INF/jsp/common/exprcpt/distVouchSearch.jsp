 <%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!-- For Taglib -->
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%> 
<fmt:setBundle basename="resources.expacct.expacct" var="expacctLabels" scope="request"/>

<script type="text/javascript" src="script/exprcpt/Common.js"> </script> 
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>">
</script>
<script> keyPreview =1 ;
</script>
<script type="text/javascript">
function formatAmount(ele)
{
	if(ele.value == "")
		return;
	var amt = ele.value;
	var finalamt = "";
	for(var i = 0; i< amt.length ; i++)
	{
		if(amt.charAt(i) != ",")
		{
			finalamt = finalamt + amt.charAt(i);
		}
	}
	ele.value = finalamt;
}
function search(cmbSearch)
{
		if(document.forms[0].cmbSearch.value==1)
		{
			var dt=document.frmSearch.txtSearch
			if (isDate(dt.value)==false)
			{
				dt.value='';
				dt.focus()
				return;
			}
		}
		if(document.forms[0].txtSearch.value=="" & document.forms[0].cmbSearch.value==0)
		{
			actionFlag = '${param.actionFlag}';
			document.forms[0].action ='ifms.htm?actionFlag='+actionFlag;
			document.forms[0].submit();
		}
		else if(document.forms[0].txtSearch.value=="")
		{
			alert("Please enter search value");
			document.getElementById("id_txtSearch").focus();
		}
		else if(document.forms[0].cmbSearch.value==0)
		{
			alert("Select criteria for search");
			document.getElementById("id_cmbSearch").focus();
		}
		else
		{
			var search=(cmbSearch.options[cmbSearch.selectedIndex].text);
			document.forms[0].action ='ifms.htm?actionFlag=distVouhsForDetPost&distVouch='+'${param.distVouch}'+'&searchby='+search;
			document.forms[0].submit();
		}
}
	
</script>  
<script language = "Javascript">
/**
 * DHTML date validation script for dd/mm/yyyy. Courtesy of SmartWebby.com (http://www.smartwebby.com/dhtml/)
 */
// Declaring valid date character, minimum year and maximum year
var dtCh= "/";
var minYear=1900;
var maxYear=2100;

function isInteger(s){
	var i;
    for (i = 0; i < s.length; i++){   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    // All characters are numbers.
    return true;
}

function stripCharsInBag(s, bag){
	var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.
    for (i = 0; i < s.length; i++){   
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}

function daysInFebruary (year){
	// February has 29 days in any year evenly divisible by four,
    // EXCEPT for centurial years which are not also divisible by 400.
    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
function DaysArray(n) {
	for (var i = 1; i <= n; i++) {
		this[i] = 31
		if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
		if (i==2) {this[i] = 29}
   } 
   return this
}

function isDate(dtStr){
	var daysInMonth = DaysArray(12)
	var pos1=dtStr.indexOf(dtCh)
	var pos2=dtStr.indexOf(dtCh,pos1+1)
	var strDay=dtStr.substring(0,pos1)
	var strMonth=dtStr.substring(pos1+1,pos2)
	var strYear=dtStr.substring(pos2+1)
	strYr=strYear
	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
	}
	month=parseInt(strMonth)
	day=parseInt(strDay)
	year=parseInt(strYr)
	if (pos1==-1 || pos2==-1){
		alert("The date format should be : dd/mm/yyyy")
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Please enter a valid month")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Please enter a valid day")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Please enter a valid 4 digit year between "+minYear+" and "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Please enter a valid date")
		return false
	}
return true
}
function visibleDateControl1()
{
	if(document.forms[0].cmbSearch.value==1)
	{
		document.getElementById("id_dtControl1").style.visibility = 'visible';
	}
	else
	{
		document.getElementById("id_dtControl1").style.visibility = 'hidden';		
	}
}


</script>

<%
HttpSession hs = request.getSession();
%>
<hdiits:form name="frmSearch" validate="true" method="post">
	<% if(request.getParameter("viewPage")==null) 
	{
		%>
		<input type="hidden" name="viewPage" value='<%=hs.getAttribute("PAGE")%>' />
		<input type="hidden" name="distVouch" value='<%=request.getParameter("distVouch")%>' />	
	<% 
	}
	else 
	{ 
		%>
		<input type="hidden" name="viewPage" value='<%=request.getParameter("viewPage")%>'/>
		<input type="hidden" name="distVouch" value='<%=request.getParameter("distVouch")%>' />		
	<% } %>
	<table align="center" width="99%" >
			<tr>
				<td width="65%">
				</td>
				<td width="8%" align="right" class=Label2>
					Search :
				</td>
				<td width="*">
						<select name="cmbSearch" id="id_cmbSearch" tabindex="1" onchange="visibleDateControl1()">
							<option value="0">-----Select-----</option>
							<option value="1">
								<hdiits:caption captionid="CMN.VOUCHERDATE" bundle="${expacctLabels}"/>
							</option>
							<option value="2">
								<hdiits:caption captionid="CMN.MAJORHEAD" bundle="${expacctLabels}"/>
							</option>	
						</select>
				</td>
				<td width="3%">
				</td>
				<td width="23%">
					<hdiits:text name="txtSearch" id="id_txtSearch" size="15" default="${param.txtSearch}" maxlength="10" tabindex="2"/>
					<img src='images/CalendarImages/ico-calendar.gif' width='20' id="id_dtControl1" style="visibility:hidden" onClick='window_open("txtSearch",375,570)'/>
					<script>
						 var cmbSearch = document.getElementById("id_cmbSearch");
						 var valSearch = '${param.searchby}'
						 if(valSearch !=null && valSearch != '')
						 {
							 for(var i=0;i<cmbSearch.length;i++)
							 {
							 	if(valSearch == cmbSearch.options[i].text)
							 	{
							 		cmbSearch.value = cmbSearch.options[i].value;
							 		break;
							 	}
							 } 
						}    	
						
					</script>
				</td>
				<td width="20%">
					<input type="button" class="searchButton" name="btnSearch" onclick="search(cmbSearch)" tabindex="3"/>
					<%-- <img src="common/images/search.gif" align="right" height="16" width="16" onclick="search(cmbSearch)" />--%>
				</td>
				</tr>
	</table>
	</hdiits:form>
	<style>
	.searchButton
			{
			border:none;
			border-color:blue;
			background-image:url(images/search_button.gif);
			height: 26px;
			width: 66px;
	</style>
	<script language="Javascript">
		document.getElementById("id_txtSearch").focus();
	</script>