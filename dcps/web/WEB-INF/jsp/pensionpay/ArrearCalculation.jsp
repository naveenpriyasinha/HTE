<%@ page language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript"	src="script/pensionpay/DynamicRowUpdation.js"></script>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels"
	var="pensionLabels" scope="request" />
<fieldset style="width:100%"  class="tabstyle">
	<legend	id="headingMsg">
			<b><fmt:message key="PPMT.ARREARCALC" bundle="${pensionLabels}"></fmt:message></b>
	</legend>	
<table>
		<tr>			
			<td width="15%">
	          <fmt:message key="PPMT.PPONO" bundle="${pensionLabels}"></fmt:message>
	        </td>
	        
	        <td width="35%">
	         <input type="text" id="txtPpoId" name="txtPpoId"/>	   
	         <label id="mandtryFinal" class="mandatoryindicator">*</label>    
	        </td>			
	        
	        <td width="15%">
	          <fmt:message key="PPMT.DOB" bundle="${pensionLabels}"></fmt:message>
	        </td>
	        <td width="35%">
	           <input type="text" id="txtDob" name="txtDob"/>
	           <img src='images/CalendarImages/ico-calendar.gif'
					        onClick='window_open("txtDob",375,570)'style="cursor: pointer;" ${disabled}/>								       
	        </td> 
		</tr> 
		
		<tr>			
			<td width="15%">
	          <fmt:message key="PPMT.PENSIONERNAME" bundle="${pensionLabels}"></fmt:message>
	        </td>
	        
	        <td width="35%">
	         <input type="text" id="txtName" name="txtName"/>	       
	        </td>	
	        
	        <td width="15%">
	          <fmt:message key="PPMT.DOR" bundle="${pensionLabels}"></fmt:message>
	        </td>
	        <td width="35%">
	           <input type="text" id="txtDor" name="txtDor"/>
	           <img src='images/CalendarImages/ico-calendar.gif'
					        onClick='window_open("txtDor",375,570)'style="cursor: pointer;" ${disabled}/>								       
	        </td>		
		</tr> 
</table>	


<fieldset class="tabstyle" style="width:100%"><legend> <b>
	<fmt:message key="PPMT.PENSIONDTLS" bundle="${pensionLabels}"></fmt:message></b> </legend>	


<div style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 90%; height: 120px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
<table width="95%">
	             <tr>
	             <td width="90%">
	                 <table id="TablePension" align="left" width="100%" cellspacing="0" border="1">
				
			    		<tr class="datatableheader"> 
						   <td width="20%" class="pensionLabels"><fmt:message key="PPMT.REVISEDTYPES" bundle="${pensionLabels}" ></fmt:message></td>	
						   <td width="20%" class="pensionLabels"><fmt:message key="PPMT.OLDBASIC" bundle="${pensionLabels}"></fmt:message></td>
						   <td width="20%" class="pensionLabels"><fmt:message key="PPMT.NEWBASIC" bundle="${pensionLabels}"></fmt:message></td>
						   <td width="20%" class="pensionLabels"><fmt:message key="PPMT.FROMDATE" bundle="${pensionLabels}"></fmt:message></td>
						   <td width="20%" class="pensionLabels"><fmt:message key="PPMT.TODATE" bundle="${pensionLabels}"></fmt:message></td>
						   <td width="1%" class="pensionLabels"><fmt:message key="PPMT.DELETE" bundle="${pensionLabels}"></fmt:message></td>						   						   
					    </tr>
					    
				     </table>
				 </td>
				 <td width="10%" valign="bottom">
				 <hdiits:button name="PensionDtlsAddRow" type="button" caption="ADDROW" bundle="${pensionLabels}" onclick="addRowPension();"  />
				 </td>
				 
				 </tr>
</table> 
</div>
</fieldset>


<fieldset class="tabstyle" style="width:100%"><legend> <b>
	<fmt:message key="PPMT.DP" bundle="${pensionLabels}"></fmt:message></b> </legend>	


<div style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 70%; height: 120px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
<table width="95%">
	             <tr>
	             <td width="90%">
	                 <table id="TableDP" align="left" width="100%" cellspacing="0" border="1">
				
			    		<tr class="datatableheader"> 
						   <td width="20%" class="pensionLabels"><fmt:message key="PPMT.DP" bundle="${pensionLabels}" ></fmt:message></td>	
						   <td width="20%" class="pensionLabels"><fmt:message key="PPMT.FROMDATE" bundle="${pensionLabels}"></fmt:message></td>
						   <td width="20%" class="pensionLabels"><fmt:message key="PPMT.TODATE" bundle="${pensionLabels}"></fmt:message></td>
						   <td width="1%" class="pensionLabels"><fmt:message key="PPMT.DELETE" bundle="${pensionLabels}"></fmt:message></td>						   						   
					    </tr>
					    
				     </table>
				 </td>
				 <td width="10%" valign="bottom">
				 <hdiits:button name="DPDtlsAddRow" type="button" caption="ADDROW" bundle="${pensionLabels}" onclick="addRowDP();"  />
				 </td>
				 
				 </tr>
</table> 
</div>
</fieldset>


<fieldset class="tabstyle" style="width:100%"><legend> <b>
	<fmt:message key="PPMT.CVP" bundle="${pensionLabels}"></fmt:message></b> </legend>	


<div style="float: inherit; border: 1px solid #000000; background-color: transparent; width: 70%; height: 120px; overflow: scroll; overflow-x: scroll; overflow-y: scroll;">
<table width="95%">
	             <tr>
	             <td width="90%">
	                 <table id="TableCVP" align="left" width="100%" cellspacing="0" border="1">
				
			    		<tr class="datatableheader"> 
						   <td width="24%" class="pensionLabels"><fmt:message key="PPMT.CVPOLDAMT" bundle="${pensionLabels}" ></fmt:message></td>	
						   <td width="24%" class="pensionLabels"><fmt:message key="PPMT.CVPNEWAMT" bundle="${pensionLabels}"></fmt:message></td>						   
						   <td width="24%" class="pensionLabels"><fmt:message key="PPMT.FROMDATE" bundle="${pensionLabels}"></fmt:message></td>
						   <td width="24%" class="pensionLabels"><fmt:message key="PPMT.TODATE" bundle="${pensionLabels}"></fmt:message></td>
						   <td width="1%" class="pensionLabels"><fmt:message key="PPMT.DELETE" bundle="${pensionLabels}"></fmt:message></td>						   						   
					    </tr>
					    
				     </table>
				 </td>
				 <td width="10%" valign="bottom">
				 <hdiits:button name="CVPDtlsAddRow" type="button" caption="ADDROW" bundle="${pensionLabels}" onclick="addRowCVP();"  />
				 </td>
				 
				 </tr>
</table> 
</div>
</fieldset>

<fieldset class="tabstyle" style="width:100%"><legend> <b>
	<fmt:message key="PPMT.REEMPDTLS" bundle="${pensionLabels}"></fmt:message></b> </legend>
	
	<table width="100%">
		   <tr>
		   	   <td width="15%">
	              <fmt:message key="PPMT.FROMDATE" bundle="${pensionLabels}"></fmt:message>
	           </td>
	           <td width="35%">
	                <input type="text" id="txtFromDateReemp" name="txtFromDateReemp"/>
	                  <img src='images/CalendarImages/ico-calendar.gif'
					        onClick='window_open("txtFromDateReemp",375,570)'style="cursor: pointer;" ${disabled}/>	       
		       </td>
		       
		       <td width="15%">
	              <fmt:message key="PPMT.TODATE" bundle="${pensionLabels}"></fmt:message>
	           </td>
	           <td width="35%">
	                <input type="text" id="txtToDateReemp" name="txtToDateReemp"/>
	                  <img src='images/CalendarImages/ico-calendar.gif'
					        onClick='window_open("txtToDateReemp",375,570)'style="cursor: pointer;" ${disabled}/>	       
		       </td>
		   </tr>	
		   
		   <tr>
		   		<td width="15%">
	              <fmt:message key="PPMT.CALCDAINPEN" bundle="${pensionLabels}"></fmt:message>
	            </td>
	            <td width="35%">
		          	<fmt:message key="PPMT.YES" bundle="${pensionLabels}"></fmt:message>
			        <input type="radio"id="radioCalcPen" name="radioCalcPen" value="Y" />
			        <fmt:message key="PPMT.NO" bundle="${pensionLabels}"></fmt:message>
			        <input type="radio"id="radioCalcPen" name="radioCalcPen" value="N" />		         
		        </td>
		        
		        <td width="15%">
		        </td>
		        <td width="35%">
		        </td>
		   </tr>
	</table>
		
</fieldset>
</fieldset>
<table align="center">
		<tr>
			<td>
				<hdiits:button name="btnCalculateArrear" type="button" caption="Calculate Arrears" bundle="${pensionLabels}" onclick=""  style="width : 100%"/>
			</td>
			<td>
				<hdiits:button name="btnClose" type="button" caption="Close" bundle="${pensionLabels}" onclick=""  />
			</td>
		</tr>
</table>		