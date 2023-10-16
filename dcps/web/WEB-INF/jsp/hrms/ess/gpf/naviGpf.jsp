<script>

function activateSave()
	{
	frm=document.forms[0];
	
	document.getElementById("save").disabled="false";
	frm.save.readonly=false;
	frm.save.disabled=false;
	}
	
function deactivateSave(frm)
	{
	document.getElementById("save").disabled="true";
	}
function closewindow()
	{
		var urlstyle="hdiits.htm?actionFlag=getHomePage";
		document.forms[0].action=urlstyle;
		document.forms[0].submit();
	}
</script>

<table class="tabNavigationBar">
	<tr align="center">
		<td class="tabnavtdcenter" id="tabnavtdcenter" >
	     		      
    <hdiits:button type="button" name="save" id="save" captionid="GPF.save" readonly="true" bundle="${gpfLables}" onclick="chkAmt();"/>
	<hdiits:button type="button" name="close" id="close" captionid="GPF.close"  bundle="${gpfLables}"  onclick="closewindow();" />
	<hdiits:resetbutton name="resetGpf" type="button" value="Reset"/>	
		     
            <script language="javaScript">             
              if (navDisplay)
              {
			//	document.write('<input type="button" value="<fmt:message key="GPF.reset"/>" onClick="resetForm()">');
			  }
			  
			  function resetForm()
			  {
			  	if(confirm("<fmt:message key="GPF.resetConfirm"/>") == true)
			  	{
			  		document.forms[0].reset();
			  		document.getElementById("save").disabled="true";
			  	}
			  				  	
			  }
		</script>
		</td>
	</tr>
</table>
