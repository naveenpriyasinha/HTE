<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="TickerMessage" value="${resValue.TickerMessage}"></c:set>

<hdiits:form name="frmTickerMessage" encType="multipart/form-data"
	validate="true" method="post">
	
<table cellpadding="0" cellspacing="0" width="100%" style="background: #EEDECC;">
	<tr height="7" >
		<td width="75%" align="left">			  		  
			<marquee id="tickerMarquee" behavior="scroll" direction="left" scrollamount="3" onmousedown="this.stop();" onmouseup="this.start();"><span style="font-size: 12pt; font-family:verdana; color:#613803;">${TickerMessage}</span></marquee>
		</td>
	</tr>
</table>
</hdiits:form>