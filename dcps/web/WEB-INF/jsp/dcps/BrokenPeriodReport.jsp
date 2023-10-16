<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="viewMap" value="${resValue.viewMap}"></c:set>
<c:set var="count" value="0"  />
<c:set var="pagecount" value="${resValue.page}"  />
<c:set var="officeName" value="${resValue.officeName}"  />
<c:set var="ddoCode" value="${resValue.ddoCode}"  />
<c:set var="yearFtr" value="${resValue.yearFtr}"  />
<c:set var="monthFtr" value="${resValue.monthFtr}"  />
<c:set var="pagesize" value="${resValue.pageSize}"  />

<c:set var="beginCount" value="${resValue.beginCount}"  />
<script type="text/javascript">

	function printReport() 
	{

		document.getElementById('Print').style.visibility = 'hidden'; // hide
		//document.getElementById('Back').style.visibility = 'hidden'; // hide   
		document.getElementById('Save').style.visibility = 'hidden'; // hide   
		window.print();
		document.getElementById('Print').style.visibility = 'visible'; // show 
		//document.getElementById('Back').style.visibility = 'visible'; // show 
		document.getElementById('Save').style.visibility = 'visible'; // show 

		
	}
		function doSaveAs() 
	{
		document.getElementById('Print').style.visibility = 'hidden'; // hide
		//document.getElementById('Back').style.visibility = 'hidden'; // hide   
		document.getElementById('Save').style.visibility = 'hidden'; // hide

		
		document.execCommand("SaveAs");
		document.getElementById('Print').style.visibility = 'visible'; // show 
		//document.getElementById('Back').style.visibility = 'visible'; // show 
		document.getElementById('Save').style.visibility = 'visible'; // show 
	}
</script>
<body>
<br><br>
<c:set var="counter" value="0"></c:set>
	<c:set var="pageNo" value="1"  />
<c:forEach var="pageno" begin="${beginCount}" end="${pagecount}">
<h1 align="center" style="font-size: 20px;">Broken Period Summary Report</h1><br>

<table style="font-size: 14px; border-collapse: collapse;"
	class="datatable" border="2" width="100%" cellpadding="2" cellspacing="2">
	
		<c:forEach var="entry" items="${viewMap}">

			<c:set var="startIndex" value="${((pageNo-1)*pagesize)+1}" />
			<c:set var="endIndex" value="${pageNo*pagesize}"  />
			<c:choose>
				<c:when test="${count%2 eq 0}">
					
					<tr class="even">
						
							<c:choose>

								<c:when
									test="${entry.key == 'Broken-Id'||entry.key == 'Allowances'||entry.key == 'Deductions'||entry.key == 'Blank'}">
									<c:if test="${entry.key == 'Broken-Id'}">
										<td width="10%" class="oddcentre"><b>Employee Details</b></td>
									</c:if>
									<c:if test="${entry.key == 'Allowances'}">
										<td width="10%" class="oddcentre"><b>Allowances</b></td>
									</c:if>
									<c:if test="${entry.key == 'Deductions'}">
										<td width="10%" class="oddcentre"><b>Deductions</b></td>
									</c:if>
									<c:if test="${entry.key == 'Blank'}">
										<td width="10%" class="oddcentre">&nbsp;</td>
									</c:if>

									<c:forEach var="monthValue" items="${entry.value}" >
										<c:set var="counter" value="${(counter+1)}" />
									<c:if test="${counter>=startIndex&&counter<=endIndex}">
										<td align="right" width="10%" class="oddcentre">&nbsp;</td>
										
									</c:if>
									</c:forEach>
								</c:when>
								<c:otherwise>
								
									<td width="10%" class="oddcentre"><b>${entry.key}</b></td>
									<c:forEach var="monthValue" items="${entry.value}" >
									<c:set var="counter" value="${(counter+1)}" />
									<c:if test="${counter>=startIndex&&counter<=endIndex}">
										<td align="right" width="10%" class="oddcentre">${monthValue.value}</td>
										
									</c:if>
									</c:forEach>
									
								</c:otherwise>
							</c:choose>
						

					</tr>
					<c:set var="count" value="${count + 1}" scope="page" />
				</c:when>

				<c:otherwise>
					<tr class="odd">
						<c:choose>

								<c:when
									test="${entry.key == 'Broken-Id'||entry.key == 'Allowances'||entry.key == 'Deductions'||entry.key == 'Blank'}">
									<c:if test="${entry.key == 'Broken-Id'}">
										<td width="10%" class="oddcentre"><b>Employee Details</b></td>
									</c:if>
									<c:if test="${entry.key == 'Allowances'}">
										<td width="10%" class="oddcentre"><b>Allowances</b></td>
									</c:if>
									<c:if test="${entry.key == 'Deductions'}">
										<td width="10%" class="oddcentre"><b>Deductions</b></td>
									</c:if>
									<c:if test="${entry.key == 'Blank'}">
										<td width="10%" class="oddcentre">&nbsp;</td>
									</c:if>

									<c:forEach var="monthValue" items="${entry.value}">
										<c:set var="counter" value="${(counter+1)}" />
									<c:if test="${counter>=startIndex&&counter<=endIndex}">
										<td align="right" width="10%" class="oddcentre">&nbsp;</td>
										
									</c:if>
									</c:forEach>
								</c:when>
								<c:otherwise>
								
									<td width="10%" class="oddcentre"><b>${entry.key}</b></td>
									<c:forEach var="monthValue" items="${entry.value}" >
									<c:set var="counter" value="${(counter+1)}" />
									<c:if test="${counter>=startIndex && counter<=endIndex}">
										<td align="right" width="10%" class="oddcentre">${monthValue.value}</td>
										
									</c:if>
									</c:forEach>
									
								</c:otherwise>
							</c:choose>
					</tr>
					
					<c:set var="count" value="${count + 1}" scope="page" />
				</c:otherwise>
			</c:choose>
<c:set var="counter" value="0"></c:set>
		</c:forEach>
		<c:set var="pageNo" value="${pageNo+1}"  />
		
	
</table>
<DIV style="page-break-after: always"><br><br><br></DIV>
	<table>
		<tr>
			<td>SALARY FOR THE MONTH & YEAR:${monthFtr}&nbsp;${yearFtr} </td>
		</tr>
		<tr>
		<td>${ddoCode}-OFFICE NAME:${officeName}</td>
		</tr>
		<tr ><td height="25px"></td></tr>
		<tr>
		<td> *Generated From Shalarth,GoM</td>
		</tr>
	</table>

</c:forEach>
<div style="width:50;text-align: center;align:center">

					
							
						
								<hdiits:button name="Print" id="Print" classcss="bigbutton" type="button" value="Print" onclick="printReport();" />
							
							
							
			                 	<hdiits:button name="Save" id="saveButton" classcss="bigbutton" type="button" value="Save" onclick="doSaveAs();" />
			                 
			             </div>
</body>