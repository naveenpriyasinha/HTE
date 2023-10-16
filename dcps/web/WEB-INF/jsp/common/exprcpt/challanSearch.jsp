
<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<fmt:setBundle basename="resources.rcptacct.rcptacct_en_US" var="rcptacctLabels" scope="request"/>

<c:set var="resultObj" value="${result}"> </c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>		

<script> keyPreview =1 ;</script>     
<script language="javascript">
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
		
			var cmbSearch2 = document.getElementById('cmbSearch2'); 
			if(document.forms[0].cmbSearch.value==2)
			{
				var dt=document.forms[0].txtSearch
				if (isDate(dt.value)==false)
				{
					dt.value='';
					dt.focus()
					return;
				}
			}
			if(document.forms[0].txtSearch.value=="" && document.forms[0].cmbSearch.value==0)
				alert("Enter search value & select criteria");
			else if(document.forms[0].txtSearch.value=="")
				alert("Please enter search value");
			else if(document.forms[0].cmbSearch.value==0)
				alert("Select criteria for search");
			else
			{
				if(document.forms[0].cmbSearch.value==4)
				{
					formatAmount(document.getElementById('id_txtSearch'));
					var amt = parseFloat(document.getElementById('id_txtSearch').value); 
					if(isNaN(amt))
					{
						alert('Invalid Amount');
						return;
					}
				}
			}
		
		if(viewName == "subTrsryPostedChallanList" || viewName == "subTrsyRecordChallanList")
		{
			if(document.forms[0].cmbSearch2.value==2)
			{
				var dt=document.forms[0].txtSearch2
				if (isDate(dt.value)==false)
				{
					dt.value='';
					dt.focus()
					return;
				}
			}
			if(document.forms[0].txtSearch2.value=="" && document.forms[0].cmbSearch2.value==0)
				alert("Enter search value & select criteria");
			else if(document.forms[0].txtSearch2.value=="")
				alert("Please enter search value");
			else if(document.forms[0].cmbSearch2.value==0)
				alert("Select criteria for search");
			else
			{
				if(document.forms[0].cmbSearch2.value==4)
				{
					formatAmount(document.getElementById('id_txtSearch2'));
					var amt = parseFloat(document.getElementById('id_txtSearch2').value); 
					if(isNaN(amt))
					{
						alert('Invalid Amount');
						return;
					}
				}
			}
		}
			var search=(cmbSearch.options[cmbSearch.selectedIndex].text);
			
			var viewName = document.getElementById("viewPage").value;
			var search2 = "";
			if(viewName == "subTrsryPostedChallanList" || viewName == "subTrsyRecordChallanList")
				search2 = (cmbSearch2.options[cmbSearch2.selectedIndex].text);
			
			var posted = document.getElementById('postedSearch').value;
			
			if(document.forms[0].txtSearch.value !="" && document.forms[0].cmbSearch.value != 0)
			{
			document.getElementById('challan_search').action ='ifms.htm?actionFlag=searchReceipt&searchby='+search+'&searchby2='+search2+'&posted='+posted;
			document.forms[0].submit();
			}
}

</script>	
<script language = "Javascript">

var dtCh= "/";
var minYear=1900;
var maxYear=2100;

function isInteger(s){
	var i;
    for (i = 0; i < s.length; i++){   

        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }

    return true;
}

function stripCharsInBag(s, bag){
	var i;
    var returnString = "";

    for (i = 0; i < s.length; i++){   
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}

function daysInFebruary (year)
{
    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
function DaysArray(n) 
{
	for (var i = 1; i <= n; i++) 
	{
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



</script>


<hdiits:form name="challan_search" validate="true" action="ifms.htm?actionFlag=searchReceipt" method="post">
	
<input type="hidden" name="viewPage" value="<%=request.getParameter("viewName")%>" >
<input type="hidden" name="postedSearch" value="<%=request.getParameter("postedSearch")%>" >
<div onkeydown="processF12();">
<table align="center" width="90%">
			<tr>
				<td width="65%">
				</td>
				<td width="8%" align="right">
					Search :
				</td>
				<td >
						<select name="cmbSearch" id="id_cmbSearch" tabindex="1">
							<option value="0">-----Select-----</option>
							<option value="1">
								<fmt:message key="CMN.CHALLAN_NO" bundle="${rcptacctLabels}"></fmt:message>
							</option>
							<option value="2">
								<fmt:message key="CMN.RCPT_DATE" bundle="${rcptacctLabels}"></fmt:message>
							</option>
							<option value="3">
								<fmt:message key="CMN.MAJORHEAD" bundle="${rcptacctLabels}"></fmt:message>
							</option>
							<option value="4">
								<fmt:message key="CDP.AMOUNT" bundle="${rcptacctLabels}"></fmt:message>
							</option>
							<option value="5">
								<fmt:message key="CDP.PARTY_NAME" bundle="${rcptacctLabels}"></fmt:message>
							</option>
							<option value="6">
								<fmt:message key="CDP.TIN_NO" bundle="${rcptacctLabels}"></fmt:message>
							</option>
							<option value="7">
								<fmt:message key="CDP.BRANCHCODE" bundle="${rcptacctLabels}"></fmt:message>
							</option>
						<!--  	<option value="8">
								<fmt:message key="CDP.SALES_TAX_NO" bundle="${rcptacctLabels}"></fmt:message>
							</option>
							
							<option value="9">
								<fmt:message key="CMN.LOC_CODE" bundle="${rcptacctLabels}"></fmt:message>
							</option>
							-->
							<option value="8">
								<fmt:message key="CDP.RCVD_BY_BANK" bundle="${rcptacctLabels}"></fmt:message>
							</option>
					</select>
					<c:choose>
						<c:when test="${resValue.searchBy!=null}">
							<script type="text/javascript">
								
								
								var cmb = document.getElementById("id_cmbSearch");
								for(i = 0 ; i < cmb.options.length;i++)
								{
									if(cmb.options[i].text == '${resValue.searchBy}')
									{
										cmb.value = cmb.options[i].value;
									}
									
								}
							</script>
						</c:when>
						<c:otherwise>
							<script type="text/javascript">
								document.getElementById("id_cmbSearch").value = "7";
							</script>
						</c:otherwise>
					</c:choose>
				</td>
				<td width="3%">
				</td>
				<td width="18%">
					<input type="text" name="txtSearch" id="id_txtSearch" size="15" onkeypress="restrictKey();" tabindex="1"/>
				</td>
				
				<c:choose>
					<c:when test="${resValue.searchVal!=null}">
						<script type="text/javascript">
							document.getElementById("id_txtSearch").value = '${resValue.searchVal}';
						</script>
					</c:when>
					<c:otherwise>
						<script type="text/javascript">
							document.getElementById("id_txtSearch").value = "";
						</script>
					</c:otherwise>
				</c:choose>
				
				
						<td width="8%" align="right" id="searchTD1" style="display: none;">
							AND :
						</td>
						<td id="searchTD2" style="display: none;">
								<select name="cmbSearch2" id="id_cmbSearch2" tabindex="1">
									<option value="0">-----Select-----</option>
									<option value="1">
										<fmt:message key="CMN.CHALLAN_NO" bundle="${rcptacctLabels}"></fmt:message>
									</option>
									<option value="2">
										<fmt:message key="CMN.RCPT_DATE" bundle="${rcptacctLabels}"></fmt:message>
									</option>
									<option value="3">
										<fmt:message key="CMN.MAJORHEAD" bundle="${rcptacctLabels}"></fmt:message>
									</option>
									<option value="4">
										<fmt:message key="CDP.AMOUNT" bundle="${rcptacctLabels}"></fmt:message>
									</option>
									<option value="5">
										<fmt:message key="CDP.PARTY_NAME" bundle="${rcptacctLabels}"></fmt:message>
									</option>
									<option value="6">
										<fmt:message key="CDP.TIN_NO" bundle="${rcptacctLabels}"></fmt:message>
									</option>
									<option value="7">
										<fmt:message key="CDP.BRANCHCODE" bundle="${rcptacctLabels}"></fmt:message>
									</option>
								<!-- 	<option value="8">
										<fmt:message key="CDP.SALES_TAX_NO" bundle="${rcptacctLabels}"></fmt:message>
									</option>
									
									<option value="9">
										<fmt:message key="CMN.LOC_CODE" bundle="${rcptacctLabels}"></fmt:message>
									</option>
									 -->
									<option value="8">
										<fmt:message key="CDP.RCVD_BY_BANK" bundle="${rcptacctLabels}"></fmt:message>
									</option>
							</select>
							<c:choose>
								<c:when test="${resValue.searchBy!=null}">
									<script type="text/javascript">
										
										
										var cmb = document.getElementById("id_cmbSearch2");
										for(i = 0 ; i < cmb.options.length;i++)
										{
											if(cmb.options[i].text == '${resValue.searchBy}')
											{
												cmb.value = cmb.options[i].value;
											}
											
										}
									</script>
								</c:when>
								<c:otherwise>
									<script type="text/javascript">
										document.getElementById("id_cmbSearch2").value = "7";
									</script>
								</c:otherwise>
							</c:choose>
						</td>
						<td width="3%">
						</td>
						<td width="18%" id="searchTD3" style="display: none;">
							<input type="text" name="txtSearch2" id="id_txtSearch2" size="15" onkeypress="restrictKey();" tabindex="1"/>
						</td>
						
						<c:choose>
							<c:when test="${resValue.searchVal!=null}">
								<script type="text/javascript">
									document.getElementById("id_txtSearch2").value = '${resValue.searchVal}';
								</script>
							</c:when>
							<c:otherwise>
								<script type="text/javascript">
									document.getElementById("id_txtSearch2").value = "";
								</script>
							</c:otherwise>
						</c:choose>
				
				
				
				
				<td width="20%">
					<hdiits:button name="btnSearch" type="button" value="Search [F12]" onclick="search(cmbSearch)" tabindex="1"/>
					<%--  <img src="common/images/search.gif" align="right" height="16" width="16" onclick="search(cmbSearch)"/> --%>
				</td>
				</tr>
			</table>
			</div>
	</hdiits:form>	
	
<script type="text/javascript">

function processF12()
{
if(event.keyCode == 123)
	{
		try{
			search(document.getElementById("id_cmbSearch"));
		}catch(e){}
	}
}


	var viewName = document.getElementById("viewPage").value;
	
	if(viewName == "subTrsryPostedChallanList" || viewName == "subTrsyRecordChallanList")
	{
		document.getElementById('searchTD1').style.display = 'inline';
		document.getElementById('searchTD2').style.display = 'inline';
		document.getElementById('searchTD3').style.display = 'inline';
	}
function restrictKey()
{
	if(window.event.keyCode == 44)
	{
		window.event.keyCode=0;
	}
}
function formateWholeDate(ele)
{	
	var str1="0";
	var str5="200";
	var str6="20";
	var str7="2";
	var val = ele.value;

	if(ele.value == "")
		return true;
	var valArr=val.split("/");
	if(valArr.length!=3)
	{	
		alert('Invalid Date');
		ele.focus();
		return false;
	}
	
	var str2=valArr[0];
	var str3=valArr[1];
	var str4=valArr[2];

	if(valArr[0]!="" && valArr[1]!="" && valArr[2]!="")
	{	
		if (str2<=31 && str3<=12)
		{
			if(valArr[0].length==1)
				valArr[0] = str1.concat(str2);
			else if(valArr[0].length==2)
				valArr[0]=valArr[0];
	
			if(valArr[1].length==1)
				valArr[1] = str1.concat(str3);
			else if(valArr[1].length==2)
				valArr[1]=valArr[1];
	
			if(valArr[2].length==1)
				valArr[2]=str5.concat(str4);
			else if(valArr[2].length==2)
				valArr[2]=str6.concat(str4);
			else if(valArr[2].length==3)
				valArr[2]=str7.concat(str4);
			else
				valArr[2]=valArr[2];
		}
		else
		{
			alert("Enter valid date");
			ele.focus();
			return false;
		}
	}	
	else
	{
		alert("Enter valid date");
		ele.focus();
		return false;
	}
	ele.value = (valArr[0] + "/" + valArr[1] + "/" + valArr[2]);
	return true;	
}

</script>
<script type="text/javascript">
//	document.getElementById('id_cmbSearch').value = "7";
	document.getElementById('id_cmbSearch').focus();
</script>