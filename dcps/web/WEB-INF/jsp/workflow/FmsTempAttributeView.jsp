<%
try {
%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/attachment.js"></script>
<script type="text/javascript" src="script/common/address.js"></script>

<fmt:setBundle basename="resources.workflow.FMS_TEMPLables" var="fmsTempLables" scope="request"/>
<c:set var="resultObj"	value="${result}"></c:set>
<c:set var="resultMap"	value="${resultObj.resultValue}"></c:set> 
<c:set var="fmsList"	value="${resultMap.fmsList}"></c:set> 

<hdiits:form name="frmFmsTmpAttrib" id="frmFmsTmpAttrib" validate="true" encType="multipart/form-data" method="POST" action="./hdiits.htm">
<hdiits:hidden name="actionFlag" default="insertTempAttribDetails" />
<hdiits:jsField name="jsenterData" jsFunction="enterData()"/>	
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected">
			<a href="#" rel="tcontent1">
				 <hdiits:caption captionid="FMS.INSERT" bundle="${fmsTempLables}" />
			</a>
		</li>	
		<li class="selected">
			<a href="hdiits.htm?viewName=fms-TempAttrib" rel="tcontent1">
				 <hdiits:caption captionid="FMS.VIEW" bundle="${fmsTempLables}" />
			</a>
		</li>	
	</ul>
</div>
<div class="tabcontentstyle"><!-------------------------------------------------------- tb1 --------------------------------------------------->
	<div id="tcontent1" class="tabcontent">
	<br><br>
		<b><font style="font-size: 4;font-style: normal"><center><hdiits:caption captionid="FMS.VIEWDETAILS" bundle="${fmsTempLables}" /></center></font></b> 
	<br><br>
	<hr>
	<table align="center" border="0" class="tabtable">
		<tr>
			<td align="center" style="border:none">
				<hdiits:caption	captionid="FMS.ATTRIBUTENAME" bundle="${fmsTempLables}" />
			</td>	
		</tr>				
		<tr>
			<td align="center" style="border:none">
				<hdiits:caption	captionid="FMS.ENGLISH" bundle="${fmsTempLables}" />
			</td>				
			<td align="left" style="border:none">
				<hdiits:text name="txtAttribNameEng" captionid="FMS.ENGATTRIBUTENAME" bundle="${fmsTempLables}"  mandatory="true" validation="txt.isrequired,txt.isname"/>
			</td>	
			<td align="center" style="border:none">
				<hdiits:caption	captionid="FMS.DESC" bundle="${fmsTempLables}" />
			</td>	
			<td align="left" style="border:none">
				<hdiits:text name="txtDescEng" captionid="FMS.ENGDESC" bundle="${fmsTempLables}"  mandatory="true" validation="txt.isrequired,txt.isname"/>
			</td>	
		</tr>
		<tr>
			<td align="center" style="border:none">
				<hdiits:caption	captionid="FMS.GUJARATI" bundle="${fmsTempLables}" />
			</td>	
			<td align="left" style="border:none">
				<hdiits:text name="txtAttribNameGuj" captionid="FMS.GUJATTRIBUTENAME" bundle="${fmsTempLables}"  mandatory="true" validation="txt.isrequired,txt.isname"/>
			</td>	
			<td align="center" style="border:none">
				<hdiits:caption	captionid="FMS.DESC" bundle="${fmsTempLables}" />
			</td>	
			<td align="left" style="border:none">
				<hdiits:text name="txtDescGuj" captionid="FMS.GUJDESC" bundle="${fmsTempLables}"  mandatory="true" validation="txt.isrequired,txt.isname"/>
			</td>	
		</tr>
		<tr>				
			<td align="center" style="border:none">
				<hdiits:caption	captionid="FMS.DATATYPE" bundle="${fmsTempLables}" />
			</td>				
			<td align="left" style="border:none">
				<hdiits:select name="selDataType" id="selDataTypeid" captionid="FMS.DATATYPE" bundle="${fmsTempLables}" validation="sel.isrequired"  mandatory="true" onchange="chkDateTime()">
					<option value="Select">Select</option>
 					<option value="Integer" selected="true"><c:out value="INTEGER" /></option>
 					<option value="Varchar"><c:out value="VARCHAR" /></option>
 					<option value="DateTime"><c:out value="DATETIME" /></option>
				</hdiits:select>			
		    </td>	
		    <td align="center" style="border:none" id="captionlenid">
				<hdiits:caption	captionid="FMS.LENGTH" bundle="${fmsTempLables}" />
			</td>				
			<td align="left" style="border:none">
		   	 <hdiits:number name="txtLength" id="txtlenid" captionid="FMS.LENGTH" bundle="${fmsTempLables}" maxlength="4"  validation="txt.isnumber" />
			</td>			
		</tr>
	</table>		
	<hr>		
	</div>	
	<jsp:include page="../core/tabnavigation.jsp" />		
</div>
<script language="javascript"><!--
	function chkDateTime()
	{
		alert("value=="+document.forms[0].selDataType.value);
		if(document.forms[0].selDataType.value =='DateTime')
		{				
				document.getElementById('txtlenid').style.display='none';	
				document.getElementById('captionlenid').style.display='none';		
		}
		else if(document.forms[0].selDataType.value!='DateTime')
		{				
				document.getElementById('txtlenid').style.display='';	
				document.getElementById('captionlenid').style.display='';
		}
	}	
	
	function enterData()
	{
		if(confirm("Not Entered The AttributeName , Do You Want To Assign The Default Value?")==true)
		{	
			if(document.forms[0].txtAttribNameEng.value == "")
			{
				document.forms[0].txtAttribNameEng.value="";
				alert("English AttributeName Not  Entered, So assigned "+document.forms[0].txtAttribNameGuj.value+"_Eng");			
				document.forms[0].txtAttribNameEng.value =document.forms[0].txtAttribNameGuj.value+"_Eng";
			}
			else if(document.forms[0].txtAttribNameGuj.value == "")
			{
				document.forms[0].txtAttribNameGuj.value="";
				alert("Gujarati AttributeName Not Entered, So assigned "+document.forms[0].txtAttribNameEng.value+"_Guj");			
				document.forms[0].txtAttribNameGuj.value =document.forms[0].txtAttribNameEng.value+"_Guj";
			}
		}
		if(confirm("Not Entered The Description , Do You Want To Assign The Default Value?")==true)
		{	
			if(document.forms[0].txtDescEng.value == "" )	
			{	
				 document.forms[0].txtDescEng.value="";
				 alert("English Description Not Entered, So assigned "+ document.forms[0].txtDescGuj.value+"_Eng");			
				 document.forms[0].txtDescEng.value=document.forms[0].txtDescGuj.value+"_Eng";
			}
			else if(document.forms[0].txtDescGuj.value == "")
			{
				document.forms[0].txtDescGuj.value="";
				alert("Gujarati Description Not  Entered, So assigned "+document.forms[0].txtDescEng.value+"_Guj");	
				document.forms[0].txtDescGuj.value=document.forms[0].txtDescEng.value+"_Guj";		
			}	
	   }		   				
	}	
</script>

<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
</script>

<hdiits:validate locale="<%=(String)session.getAttribute("locale")%>" />
</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
