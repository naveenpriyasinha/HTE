<%
try
{ 
%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<c:set var="resultObj"	value="${result}"> </c:set> 
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="countList" value="${resultMap.countList}"></c:set>

<script type="text/javascript">
	var countList1 = '${countList}';
	var countList2 = countList1.split('[');
	var countList3 = countList2[1].split(']');
	var countList = countList3[0].split(', ');
	var count = 0;
</script>
<fmt:setBundle basename="resources.workflow.workFlowLables" var="commonLables" scope="request"/>

	<table align="center">
		<tr>
			<td>
				New File :
			</td>
			<td>
				<script type="text/javascript">
					if(countList[count] != 0)
						document.write('<a href="' + countList[count+1] + '">' + countList[count] + '</a>');
					else
						document.write(countList[count]);
					count = count + 2;
				</script>
			</td>
		</tr>
		<tr>
			<td>
				New Correspondence :
			</td>
			<td>
				<script type="text/javascript">
					if(countList[count] != 0)
						document.write('<a href="' + countList[count+1] + '">' + countList[count] + '</a>');
					else
						document.write(countList[count]);
					count = count + 2;
				</script>
			</td>
		</tr>
		<tr>
			<td>
				New Other Job :
			</td>
			<td>
				<script type="text/javascript">
					if(countList[count] != 0)
						document.write('<a href="' + countList[count+1] + '">' + countList[count] + '</a>');
					else
						document.write(countList[count]);
					count = count + 2;
				</script>
			</td>
		</tr>
		<tr>
			<td>
				New Intimation :
			</td>
			<td>
				<script type="text/javascript">
					if(countList[count] != 0)
						document.write('<a href="' + countList[count+1] + '">' + countList[count] + '</a>');
					else
						document.write(countList[count]);
					count = count + 2;
				</script>
			</td>
		</tr>
	</table>
	
<% 
}
catch(Exception e)
{
	e.printStackTrace();
}
%>