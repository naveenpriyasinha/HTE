<%@ include file="/WEB-INF/jsp/core/include.jsp"%>   
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<fmt:setBundle basename="resources.hr.rta.RtaCaption" var="commonLables" scope="request"/>

<% try
{ %>

<hdiits:form name="rta" validate="true">

<table class="tabtable">



<tr bgcolor="#386CB7">
<td class="fieldLabel" colspan="5">
<font color="#ffffff">
<strong><u><hdiits:caption bundle="${commonLables}" captionid="HRMS.rtaoptions" /></u></strong>
		</font>
		</td>
		</tr>
	
<tr>
<td >		
</td>

<td>
<hdiits:radio captionid="HRMS.reqfortravelallow" name="reqfortravelallow" value="1" mandatory="false" onclick="showbutton(this,1);" tabindex="1"/>
<hdiits:caption bundle="${commonLables}" captionid="HRMS.reqfortravelallow" /></td>
<td>
<hdiits:radio captionid="HRMS.reimbursement" name="reimbursement" value="0" mandatory="false" default="0"  onclick="showbutton(this,1);" tabindex="2"/>
<hdiits:caption bundle="${commonLables}" captionid="HRMS.reimbursement" />
</td>

<td align="left" id="leavedetail" style="display:none">
<hdiits:button type="button" name="leaveDetail"   captionid="HRMS.EnterLeaveDetail1"  bundle="${LeaveCaption}" onclick="return applyLeaveDetail();" tabindex="3"/>
</td>
<td colspan="3">
 <a href="#" onclick="viewSubmittedReq();">View Submitted Request</a> 
</td>
<td >
</td>

</tr>		
	
  
 
 <tr>
 <td><b><hdiits:caption captionid="HRMS.totalpayablerta" bundle="${commonLables}" id="totalpayablerta" />:</b></td>
    <td>
      <hdiits:text captionid ="HRMS.totalpayablerta" name="totalpayablerta" id="totalpayable" mandatory="true" validation="txt.isrequired" onblur="checkamt()" tabindex="3" maxlength="10"/>
   </td>  
   
   


 
 <td><hdiits:caption captionid="HRMS.remarks" bundle="${commonLables}" /></td>
<td>
<hdiits:textarea mandatory="false" rows="2" cols="17"  
                                    name="remarks" tabindex="4" id="c_remarks" 
                                    validation ="txt.isrequired"   caption="remarks" maxlength="2000" />
</td>
	

</tr>
	
	
 
 
 <tr><td colspan ="4" align= "center">	
 
	

<hdiits:formSubmitButton name="Submit" type="button" caption="Submit" tabindex="5"/>
	<input type="button" value="Close" name="Closey" tabindex="6"></td>

 </tr>
 
 </table>

<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
 </hdiits:form>

 <%
}catch(Exception e){
	e.printStackTrace();
}

%>
 