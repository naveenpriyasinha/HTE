
<html>
<%@ include file="../core/include.jsp" %>
<script type="text/javascript" src="common/script/ajax_saveData.js"> </script>
<script type="text/javascript" src="<c:url value="/common/script/tabcontent.js"/>"></script>
<script type="text/javascript" src="<c:url value="/common/script/ajax_sample.js"/>"></script>


<script type="text/javascript">
	function createJob()
	{	
		document.getElementById('actionId').value = 1;	
	}
	
	function forward()
	{
		document.getElementById('actionId').value = 2;	
	}
	
	function return_()
	{
		document.getElementById('actionId').value = 3;	
	}
	
	function split()
	{
		document.getElementById('actionId').value = 4;	
	}
</script>

<form method="POST"  action="./ifms.htm" onsubmit="return validate_form(this)" method="post">
	<input type="hidden" name="actionFlag" value="wfAction">
	<input type="hidden" name="actionId" value="1111">

	<table>
		<tr>
			<td>Job Reference ID: </td>
			<td><input type="text" name="jobRefId"></td>
		</tr>
		
		<tr>
			<td>Doc Id: </td>
			<td>
			<select name="docId">
			<option value="1" selected>1</option>
			<option value="2" >2</option>
			</select>
			</td>		
		</tr>
		
		<tr>
			<td>Hir Ref ID: </td>
			<td><input type="text" name="hirRefId"></td>		
		</tr>
		
		<tr>
			<td>CrtRole: </td>
			<td><input type="text" name="crtRole"></td>		
		</tr>
		
		<tr>
			<td>CrtPost: </td>
			<td><input type="text" name="crtPost"></td>		
		</tr>
		
		<tr>
			<td>fromPost: </td>
			<td><input type="text" name="fromPost"></td>		
		</tr>
		
		<tr>
			<td>toPost: </td>
			<td><input type="text" name="toPost"></td>		
		</tr>
		
		<tr>
			<td>toRole: </td>
			<td><input type="text" name="toRole"></td>		
		</tr>
		
		
		
		<tr>
			<td>Hierarchy: </td>
			<td>
				<input type="radio" name="hierarchyFlag" value="1" checked="checked">Normal
				<input type="radio" name="hierarchyFlag" value="2">Mark
			</td>
		</tr>
	</table>

	<table>
		 <tr>
		 	<td><input type="submit" value="Create Job" onClick="createJob()"></td>
			<td><input type="submit" value="Forward" onClick="forward()"></td>		 				
			<td><input type="submit" value="Return" onClick="return_()"></td>
			<td><input type="submit" value="Split" onClick="split()"></td>
		</tr>
 </table>
 


 <jsp:include page="../common/include/tabnavigation.jsp?"/>


 </form>
 
</html>