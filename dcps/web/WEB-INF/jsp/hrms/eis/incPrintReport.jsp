<%
	try {
%>

<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 

<%@page import="java.io.*, java.util.Date, java.util.Enumeration, java.text.SimpleDateFormat,java.util.Calendar" %> 
<%@page import="com.tcs.sgv.eis.service.* "%>
<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	
  
<c:set var="printReportCustomList" value="${resultValue.printReportCustomList}"></c:set>
<c:set var="reportlistsize" value="${resultValue.reportlistsize}"></c:set>
<c:set var="verificationTime" value="${resultValue.verificationTime}"></c:set>
<c:set var="officename" value="${resultValue.officename}"></c:set>



<style type="text/css">
.Label5 {
	font-family: Arial, Helvetica, sans-serif;
	text-decoration: none;
	font-size: 12px;
	font-weight: normal;
	color: Black;
	border-bottom: 1px solid #000000;
	border-right: 1px solid #000000;
	border-top: 0px solid #000000;
	border-left: 0px solid #000000;
}

.frmlrbBorder {
	border-bottom: 1px solid #000000;
	border-right: 1px solid #000000;
	border-top: 0px solid #000000;
	border-left: 1px solid #000000;
}

.frmlftBorder {
	border-left: 1px solid #000000;
}

.TopBorder {
	border-top: 1px solid #000000;
	border-left: 1px solid #000000;
}

border-bottom {
	border-bottom: 1px solid #000000;
}

.RightBorder {
	border-right: 1px solid #000000;
}

.TopBottomBorder {
	border-top: 1px solid #000000;
	border-bottom: 1px solid #000000;
}
</style>
<script type="text/javascript">
function hidemenu()
{
	document.getElementById('mlmenu').style.display="none";
	document.getElementById('Table_01').style.display="none";
	
	
	document.getElementById('footer').style.display="none";
	//document.getElementById('currentApplication').style.display="none";
	
}

function prindDoc(divName)
{
	//PrintPage
	var printContents = document.getElementById(divName).innerHTML;
    var originalContents = document.body.innerHTML;

    document.body.innerHTML = printContents;

    window.print();

    document.body.innerHTML = originalContents;
}
</script>
<div id="PrintPage">
<hdiits:form name="ViewOuterFirst" validate="true" method="POST" action="" encType="multipart/form-data" >
<table cellpadding="0" cellspacing="0" border="0" frame="box" align="CENTER" width="100%">
	<tr>
		<td align="center">
			FORM M.T.R. 21<br>(See rule 266)<br><b>Increment Certificate</b>
		</td>
	</tr>
	<tr>
		<td>
			&nbsp;
		</td>
	</tr>
	<tr>
		<td align="left">
			(1) Certified that Government servants named below have been the incumbents of the appointments indicated for not less than ......... years since that date in column(4) after deducting
			periods of absence from duty not counting for increment and of absence on leave without pay and in the case of those holding the posts in officiating capacity, all other kinds of leave
			during which he would not have continued to officiate in that post and that they have earned the prescribed periodical increments from the date cited :-
		</td>
	</tr>
	<tr>
		<td>
			&nbsp;
		</td>
	</tr>
	<tr>
		<td align="left">
			(2) Certified that the Government Servant/Servants named below has/have earned/will earn periodical increments from the date cited , for reasons stated in the explanatory memo, attached
			here to :-
		</td>
	</tr>
	<tr>
		<td>
			&nbsp;
		</td>
	</tr>
</table>

<table cellpadding="0" cellspacing="0" >
	<tr>
		<td style="border-right: 1px solid black;border-left: 1px solid black;border-top: 1px solid black;" width="1%" align="center">Sr No.</td>
		<td style="border-right:  1px solid black; border-top:  1px solid black;" width="10%" align="center">Name of incumbent</td>
		<td style="border-right:  1px solid black; border-top:  1px solid black;" width="10%" align="center">Whether Substantiative or officiating</td>
		<td style="border-right:  1px solid black; border-top:  1px solid black;" width="10%" align="center">Present Pay (PAy in PB+GP)</td>
		<td style="border-right:  1px solid black; border-top:  1px solid black;" width="10%" align="center">Date from Which the present pay</td>
		<td style="border-right:  1px solid black; border-top:  1px solid black;" width="10%" align="center">Amount of present increment</td>
		<td style="border-right:  1px solid black; border-top:  1px solid black;" width="10%" align="center">Date of present increment</td>
		<td style="border-right:  1px solid black; border-top:  1px solid black;" width="10%" align="center">Pay after present increment (PAy in PB+GP)</td>
		<td style="border-right:  1px solid black; border-top:  1px solid black;"  width="10%" align="center">
			Absence from duty not counting for increment
		</td>
		<td style="border-right:  1px solid black; border-top:  1px solid black;" width="19%" align="center">
			Leave without pay and in the case of those holding the posts in officiating capacity all other kinds of leave during which He/They would not have continued to officiate in the post
		</td>
	</tr>
	<tr>
		<td style="border-right: 1px solid black;border-left: 1px solid black;border-bottom: 1px solid black;" width="1%" align="center">&nbsp;</td>
		<td style="border-bottom: 1px solid black; border-right:  1px solid black;" width="10%" align="center">&nbsp;</td>
		<td style="border-bottom: 1px solid black; border-right:  1px solid black;" width="10%" align="center">&nbsp;</td>
		<td style="border-bottom: 1px solid black; border-right:  1px solid black;" width="10%" align="center">&nbsp;</td>
		<td style="border-bottom: 1px solid black; border-right:  1px solid black;" width="10%" align="center">&nbsp;</td>
		<td style="border-bottom: 1px solid black; border-right:  1px solid black;" width="10%" align="center">&nbsp;</td>
		<td style="border-bottom: 1px solid black; border-right:  1px solid black;" width="10%" align="center">&nbsp;</td>
		<td style="border-bottom: 1px solid black; border-right:  1px solid black;" width="10%" align="center">&nbsp;</td>
		<td style="border-bottom: 1px solid black; border-right:  1px solid black;border-top:  1px solid black;" width="10%" align="center">
			<table cellpadding="0" cellspacing="0"  width="100%">
				<tr>
					<td align="center" width="50%" style=" border-right: solid; border-color: black; border-width: 1px">From</td>
					<td align="center" width="50%">To</td>
				</tr>
			</table>
		</td>
		<td style="border-bottom: 1px solid black; border-right:  1px solid black;border-top:  1px solid black;" width="10%" align="center">
			<table cellpadding="0" cellspacing="0"  width="100%">
				<tr>
					<td align="center" width="50%" style=" border-right: solid; border-color: black; border-width: 1px">From</td>
					<td align="center" width="50%">To</td>
				</tr>
			</table>
		</td>
	</tr>
	
	<tr>
		<td align="center" style="border-left: 1px solid black; border-bottom: 1px solid black; border-right: 1px solid black;">&nbsp;</td>
		<td align="center" style="border-bottom: 1px solid black; border-right: 1px solid black;">1</td>
		<td align="center" style="border-bottom: 1px solid black; border-right: 1px solid black;">2</td>
		<td align="center" style="border-bottom: 1px solid black; border-right: 1px solid black;">3</td>
		<td align="center" style="border-bottom: 1px solid black; border-right: 1px solid black;">4</td>
		<td align="center" style="border-bottom: 1px solid black; border-right: 1px solid black;">5 = 3% of 3</td>
		<td align="center" style="border-bottom: 1px solid black; border-right: 1px solid black;">6</td>
		<td align="center" style="border-bottom: 1px solid black; border-right: 1px solid black;">7</td>
		<td align="center" style="border-bottom: 1px solid black; border-right: 1px solid black;" >
			<table cellpadding="0" cellspacing="0"  width="100%">
				<tr>
					<td align="center" width="50%" style=" border-right: solid; border-color: black; border-width: 1px">8</td>
					<td align="center" width="50%" >9</td>
				</tr>
			</table>
		</td>
		<td align="center" style="border-bottom: 1px solid black; border-right: 1px solid black;">
			<table cellpadding="0" cellspacing="0" width="100%">
				<tr align="center" >
					<td  align="center" width="50%" style=" border-right: solid; border-color: black; border-width: 1px">10</td>
					<td align="center" width="50%">11</td>
				</tr>
			</table>
		</td>
	</tr>
	<c:if test="${reportlistsize gt 0}">
		<c:set var = "i" value = "1" ></c:set>
	    <c:forEach items="${printReportCustomList}" var="name"> 
			<tr>
				<td style="border-bottom: solid; border-color: black; border-width: 1px" align="center"><c:out value="${i}"/></td>
				<td style="border-bottom: solid; border-color: black; border-width: 1px" align="center"><c:out value="${name.empDsgnName}"/></td>
				<td style="border-bottom: solid; border-color: black; border-width: 1px" align="center">&nbsp;</td>
				<td style="border-bottom: solid; border-color: black; border-width: 1px" align="center"><c:out value="${name.basic}"/> </td>
				<!--<td style="border-bottom: solid; border-color: black; border-width: 1px" align="center"><c:out value="${name.withEffectiveDate}"/> </td>-->
				<%-- <td style="border-bottom: solid; border-color: black; border-width: 1px" align="center"><c:out value="01/07/2015"/> </td> --%>
				<%-- <td style="border-bottom: solid; border-color: black; border-width: 1px" align="center"><c:out value="01/07/2018"/> </td> --%>
				<!-- Added By Tejashree-->
				<td style="border-bottom: solid; border-color: black; border-width: 1px" align="center">
				<c:set var="test" value="${name.withEffectiveDate}"/>
				<% SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
						Date date = sdf.parse(pageContext.getAttribute("test").toString());
						Calendar cal = Calendar. getInstance();
						cal. setTime(date);
						cal.add(Calendar.YEAR, -1) ;
						        Date currentDatePlusOne = cal.getTime();
						         out.println(sdf.format(currentDatePlusOne)); %>
			    </td>
			    <!-- Ended By Tejashree -->
				<td style="border-bottom: solid; border-color: black; border-width: 1px" align="center"><c:out value="${name.percentageAmount}"/> </td>
				<!--<td style="border-bottom: solid; border-color: black; border-width: 1px" align="center"><c:out value="${name.incrementOrderDate}"/> </td>-->
				<td style="border-bottom: solid; border-color: black; border-width: 1px" align="center"><c:out value="${name.withEffectiveDate}"/> </td>
				<td style="border-bottom: solid; border-color: black; border-width: 1px" align="center"><c:out value="${name.newBasic}"/> </td>
				<td style="border-bottom: solid; border-color: black; border-width: 1px" align="center">&nbsp;</td>
			    <td style="border-bottom: solid; border-color: black; border-width: 1px" align="center">&nbsp;</td>
			</tr>
			<c:set var = "i" value = "${i+1}" />
	   </c:forEach>
	</c:if>	
	<tr>
		<td colspan="10">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td colspan="10">
			<table cellpadding="0" cellspacing="0" border="0" frame="box" align="CENTER" width="100%" >
				<tr>
					<td align="left" >
						Note.1 - When increment claim is the first to carry an officer over an efficiency bar columns(5),(6)and (7) should be filled in red ink.
					</td>
				</tr>
	   		</table>
		</td>
	</tr>
	<tr>
		<td colspan="10">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td colspan="10">
	    	<table cellpadding="0" cellspacing="0" border="0" frame="box" align="CENTER" width="100%">
				<tr>
					<td>
						Note.2 - The figure(1 or (2) should be placed against each name according as the reason(1) or (2) applies.The explanatory memo should be submitted in any case in which reason (2) applies
					</td>
				</tr>
	    	</table>
		</td>
	</tr>
	<tr>
		<td colspan="10">
			&nbsp;
		</td>
	</tr>
	
	<tr>
		<td colspan="10">
			<table width="100%">
				<tr>
					<td align="left">
						Verification Date and Time &nbsp;<b><c:out value="${verificationTime}"/></b>
					</td>
					<td align="right">
						<table width="100%">
							<tr>
								<td align="center">
									Head Of Office
								</td>
							</tr>
							<tr>
								<td align="center">
									<b><c:out value="${officename}"/></b>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<table width="100%" id="buttonTab">
	<tr>
		<td align="right"><input name="Close" type="button" Value="Close" onclick=window.close();></input></td>
		<td align="left"><input type="button" value="Print" onClick="prindDoc('PrintPage');" ></input></td>
	</tr>
</table>
<script type="text/javascript">
	hidemenu();
</script>
</hdiits:form>
</div>
<%
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
