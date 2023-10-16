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
		if(document.forms[0].cmbSearch.value==3 || document.forms[0].cmbSearch.value==4)
		{
			var dt=document.frmSearch.txtSearch
			if (isDate(dt.value)==false)
			{
				dt.value='';
				dt.focus()
				return;
			}
		}
		if(document.forms[0].cmbSearch2.value==3 || document.forms[0].cmbSearch2.value==4)
		{
			var dt=document.frmSearch.txtSearch2
			if(dt.value != '')
			{
				if (isDate(dt.value)==false)
				{
					dt.value='';
					dt.focus()
					return;
				}
			}
		}
		
		if(document.forms[0].txtSearch.value=="" && document.forms[0].cmbSearch.value==0 && document.forms[0].txtSearch2.value=="" && document.forms[0].cmbSearch2.value==0)
		{
			actionFlag = '${param.actionFlag}';
			disableAllButtons();	   
	        showProgressbar();
			document.forms[0].action ='ifms.htm?actionFlag='+actionFlag;
			document.forms[0].submit();
		}
		/*else if(document.forms[0].txtSearch.value=="")
		{
			alert("Please enter search value");
			document.getElementById("id_txtSearch").focus();
		}*/
		else if(document.forms[0].cmbSearch.value==0)
		{
			alert("Select criteria for search");
			document.getElementById("id_cmbSearch").focus();
		}
		else
		{
			if(document.forms[0].cmbSearch.value==8)
			{
				formatAmount(document.getElementById('id_txtSearch'));
				var amt = parseFloat(document.getElementById('id_txtSearch').value); 
				if(document.getElementById('id_txtSearch').value != amt)
				{
					alert('Invalid Amount');
					document.getElementById('id_txtSearch').value = '';
					document.getElementById('id_txtSearch').focus();
					return;
				}
			}
			if(document.forms[0].cmbSearch2.value==8 && document.getElementById('id_txtSearch2').value !='')
			{
				formatAmount(document.getElementById('id_txtSearch2'));
				var amt = parseFloat(document.getElementById('id_txtSearch2').value); 
				if(document.getElementById('id_txtSearch2').value != amt)
				{
					alert('Invalid Amount');
					document.getElementById('id_txtSearch2').value = '';
					document.getElementById('id_txtSearch2').focus();
					return;
				}
			}
			
			var cmbSearch2 = document.getElementById("cmbSearch2");
			var search2 = '';
			if(cmbSearch2.value != 0)
				search2 = cmbSearch2.options[cmbSearch2.selectedIndex].text;
			
			var search=(cmbSearch.options[cmbSearch.selectedIndex].text);
			disableAllButtons();	   
	        showProgressbar();
			document.forms[0].action ='ifms.htm?actionFlag=searchVoucher&searchby='+search+'&searchby2='+search2;
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
	if(document.forms[0].cmbSearch.value==3 || document.forms[0].cmbSearch.value==4)
	{
		document.getElementById("id_dtControl1").style.visibility = 'visible';
	}
	else
	{
		document.getElementById("id_dtControl1").style.visibility = 'hidden';		
	}
}

function visibleDateControl2()
{
	if(document.forms[0].cmbSearch2.value==3 || document.forms[0].cmbSearch2.value==4)
	{
		document.getElementById("id_dtControl2").style.visibility = 'visible';
	}
	else
	{
		document.getElementById("id_dtControl2").style.visibility = 'hidden';
	}
}
function processF12()
	{
	if(event.keyCode == 123)
		{
			try{	
			cmbSearch =document.getElementById("cmbSearch");
				search(cmbSearch);
			}catch(e){}
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
		<input type="hidden" name="viewPage" value="<%=hs.getAttribute("PAGE")%>" >
	<% 
	}
	else 
	{ 
		%>
		<input type="hidden" name="viewPage" value="<%=request.getParameter("viewPage")%>" >
		<input type="hidden" name="posted" value="<%=request.getParameter("posted")%>" >		
	<% } %>
	<table align="center" width="95%">
			<tr>
				<td width="20%">
				</td>
				<td width="8%" align="right" class=Label2>
					Search :
				</td>
				<td width="*">
						<select name="cmbSearch" id="id_cmbSearch" tabindex="1" onchange="visibleDateControl1()">
							<option value="0">-----Select-----</option>
							<%-- <option value="1">
								<hdiits:caption captionid="CMN.VOUCHAR_NO" bundle="${expacctLabels}"/>
							</option>
							<option value="2">
								<hdiits:caption captionid="CMN.VOUCHERDATE" bundle="${expacctLabels}"/>
							</option>--%>
							<option value="1">
								Bill Ref No
							</option>
							<option value="2" selected="selected">
								Token No
							</option>
							<option value="3">
								<hdiits:caption captionid="CMN.VOUCHERDATE" bundle="${expacctLabels}"/>
							</option>
							<option value="4">
								Inwarded Date
							</option>
							<option value="5">
								<hdiits:caption captionid="CMN.BILLTYPE" bundle="${expacctLabels}"/>
							</option>
							<option value="6">
								<hdiits:caption captionid="CMN.CARDEXNO" bundle="${expacctLabels}"/>
							</option>
							<option value="7">
								<hdiits:caption captionid="CMN.MAJORHEAD" bundle="${expacctLabels}"/>
							</option>	
							<option value="8">
								Net Amount
							</option>										
						</select>
				</td>
				<td width="3%">
				
				</td>
				<td width="30%">
					<hdiits:text name="txtSearch" id="id_txtSearch" size="15" default="${param.txtSearch}" maxlength="40" tabindex="2"/>
					<img src='images/CalendarImages/ico-calendar.gif' width='20' id="id_dtControl1" style="visibility: hidden;" onClick='window_open("txtSearch",375,570)'/>
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
				<td width="5%" align="center" class="Label2">
					And 
				</td>
				<td width="*">
						<select name="cmbSearch2" id="id_cmbSearch2" tabindex="3" onchange="visibleDateControl2()">
							<option value="0">-----Select-----</option>
							<%-- <option value="1">
								<hdiits:caption captionid="CMN.VOUCHAR_NO" bundle="${expacctLabels}"/>
							</option>
							<option value="2">
								<hdiits:caption captionid="CMN.VOUCHERDATE" bundle="${expacctLabels}"/>
							</option>--%>
							<option value="1">
								Bill Ref No
							</option>
							<option value="2" >
								Token No
							</option>
							<option value="3">
								<hdiits:caption captionid="CMN.VOUCHERDATE" bundle="${expacctLabels}"/>
							</option>
							<option value="4">
								Inwarded Date
							</option>
							<option value="5">
								<hdiits:caption captionid="CMN.BILLTYPE" bundle="${expacctLabels}"/>
							</option>
							<option value="6">
								<hdiits:caption captionid="CMN.CARDEXNO" bundle="${expacctLabels}"/>
							</option>
							<option value="7" selected="selected">
								<hdiits:caption captionid="CMN.MAJORHEAD" bundle="${expacctLabels}"/>
							</option>	
							<option value="8">
								Net Amount
							</option>										
						</select>
				</td>
				<td width="3%">
				</td>
				<td width="30%">
					<hdiits:text name="txtSearch2" id="id_txtSearch2" size="15" default="${param.txtSearch2}" tabindex="4"/>
					<img src='images/CalendarImages/ico-calendar.gif' width='20' id="id_dtControl2" style="visibility: hidden;" onClick='window_open("txtSearch2",375,570)'/>
					<script>
						 var cmbSearch = document.getElementById("id_cmbSearch2");
						 var valSearch = '${param.searchby2}'
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
					<hdiits:button name="btnSearch" id="id_btnSearch" type="button" value="Search [F12]" onclick="search(cmbSearch);" tabindex="5"/>
					<%-- <input type="button" class="searchButton" name="btnSearch" onclick="search(cmbSearch)" tabindex="5"/> --%>
					<%-- <img src="common/images/search.gif" align="right" height="16" width="16" onclick="search(cmbSearch)" />--%>
				</td>
			</tr>
			
	</table>
	</hdiits:form>
	
	<script language="Javascript">
		document.getElementById("id_txtSearch").focus();
	</script>