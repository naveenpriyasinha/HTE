<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="TickerMessage" value="${resValue.tickerMessage}"></c:set>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>

<script>
var messages = new Array();
function ClearData(){
	document.getElementById("txtMessage1").value = '';
	document.getElementById("txtMessage2").value = '';
	document.getElementById("txtMessage3").value = '';
}
function PreviewTickerMessage(){
	document.getElementById('tableMarquee').style.visibility='visible';
	messages[0] = document.getElementById("txtMessage1").value;
	messages[1] = document.getElementById("txtMessage2").value;
	messages[2] = document.getElementById("txtMessage3").value;
	var i;
	var message=messages[0]+" ** "+messages[1]+" ** "+messages[2];;
	/*for(i=0;i<3;i++){
		if(messages[i]!=null){
			if(i=0){
				message = message + messages[i];
			}
			else{
				message = message + " ** " +messages[i];
			}
		}
	}*/
	document.getElementById('tickerMarquee').innerHTML = '<span style="font-size: 12pt; font-family:verdana; color:#613803;">'+message+'</span>';
}
function SubmitMessages()
{
	SaveData();
	
	return true;

}
function SaveData()
{
	messages[0] = document.getElementById("txtMessage1").value;
	messages[1] = document.getElementById("txtMessage2").value;
	messages[2] = document.getElementById("txtMessage3").value;
	//Save Data Using Ajax
	var xmlHttp=null;
	xmlHttp = GetXmlHttpObject();
	
	if (xmlHttp == null) {
		alert("Your browser does not support AJAX!");
		return;
	}
	
	var url = "ifms.htm?actionFlag=saveTickerMessage&message1="+messages[0]+"&message2="+messages[1]+"&message3="+messages[2];
    xmlHttp.open("POST",url,true);
	xmlHttp.send(url);

	xmlHttp.onreadystatechange = function()
	{
		if(xmlHttp.readyState == 4)
		{
			if (xmlHttp.status == 200) {
				XMLDoc = xmlHttp.responseXML.documentElement;
				var XMLEntries  = XMLDoc.getElementsByTagName('XMLDOC');
				var successFlag = XMLEntries[0].childNodes[0].text;
				if(successFlag)
				{
					alert("Ticker Message Created");
					self.location.reload();
				}
			}
		}
	};
}

</script>

<fmt:setBundle	basename="resources.dcps.dcpsLabels" var="DCPSLables" scope="request" />
<hdiits:form name="frmTickerMessageEntry" encType="multipart/form-data"
	validate="true" method="post">
	
<br/>

<fieldset class="tabstyle">

<table id="table1" width=60%" align="center">	
	<tr>
		<td width="10%" align="left" >	
		</td>
		<td width="10%" align="left" ><fmt:message key="CMN.MESSAGE1" bundle="${DCPSLables}"></fmt:message>	
		</td>
		<td width="40%" align="left">
			<input type="text"  size="50%" name='txtMessage1' id="txtMessage1" style="text-align: left" />
		</td>
		
	</tr>
	<tr>
		<td width="10%" align="left" >	
		</td>
		<td width="10%" align="left" ><fmt:message key="CMN.MESSAGE2" bundle="${DCPSLables}"></fmt:message>	
		</td>
		<td width="40%" align="left">
			<input type="text"  size="50%" name='txtMessage2' id="txtMessage2" style="text-align: left" />
		</td>
		
	</tr>
	<tr>
		<td width="10%" align="left" >	
		</td>
		<td width="10%" align="left" ><fmt:message key="CMN.MESSAGE3" bundle="${DCPSLables}"></fmt:message>	
		</td>
		<td width="40%" align="left">
			<input type="text"  size="50%" name='txtMessage3' id="txtMessage3" style="text-align: left" />
		</td>
		
	</tr>
</table>
</br>

</fieldset>

</br>
<center>
<hdiits:button type="button" captionid="CMN.PREVIEW" bundle="${DCPSLables}" id="btnPreview" name="btnPreview" onclick="PreviewTickerMessage();"></hdiits:button>
<hdiits:button type="button" captionid="BTN.SUBMIT" bundle="${DCPSLables}" id="btnSubmit" name="btnSubmit" onclick="SubmitMessages();"></hdiits:button>
<hdiits:button type="button" captionid="BTN.CLEAR" bundle="${DCPSLables}" id="btnClear" name="btnClear" onclick="ClearData();"></hdiits:button></center>
</br>
<table id="tableMarquee" cellpadding="0" cellspacing="0" width="100%" style="background: #EEDECC;visibility:hidden;">
	<tr height="7" >
		<td width="75%" align="left">			  		  
			<!-- HTML codes by Quackit.com -->
			<marquee id="tickerMarquee" behavior="scroll" direction="left" scrollamount="3" onmousedown="this.stop();" onmouseup="this.start();"></marquee>
		</td>
</tr>
</table>
</hdiits:form>