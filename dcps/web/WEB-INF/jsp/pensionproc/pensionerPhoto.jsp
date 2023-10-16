<%@ include file="../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>      
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<fmt:setBundle basename="resources.pension.PensionLabels" var="pensionLabels" scope="request"/>
<script type="text/javascript" src="script/pension/PensionCase.js"></script>
<script type="text/javascript">
	function getPopPrew(lThis)
	{
		var urlValue = window.opener.document.getElementById("hid"+lThis+"Url").value
		document.getElementById('imgCardex').src = urlValue;
		document.getElementById('imgTbl').style.display = '';
	}
	 function winCls()
	 {
	 	try
	 	{
	 	 	if(window.opener.document) 
	 	 	{
	 	 		window.close();
	 	 	}
	 	 		
	 	}catch(err)
	 	{
	 		document.forms[0].action = 'ifms.htm?actionFlag=getHomePage';
			document.forms[0].submit();
	 	}
	 }
</script>
<fieldset style="width:100%" class="tabstyle">
<legend id="headingMsg"><b> Pensioner Photo  </b></legend>
 <table width="100%">
 	<tr>
 		<td colspan="2">
 			<div id="popPrew">
 			</div>
 		</td>
 	</tr>
 </table>
 <table id="imgTbl">
	<tr>
		<td>
			<img src="" id="imgCardex" height="500px" width="500px" >
		</td>
	</tr>
</table>
 <center>
 	<input type="button" name="bntClose" value="Close" onclick="winCls();" class="buttontag"  /> 
 </center>
 <script type="text/javascript">
 	getPopPrew(window.opener.document.getElementById("attachmentName1").value);
 </script>
 </fieldset>