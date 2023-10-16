
<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%
System.out.println("\n\n-------------------------------------------in ddoinformation.jsp------------------------------------------------------------\n\n");
%>

<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>
<script type="text/javascript"  
    src="common/script/commonfunctions.js">
</script>
<style>
input[type="button" i]:disabled {
    pointer-events: none;
    opacity: 0.6;
}
</style>

<script>
function save()
{
   
}
function reset()
{
	
}
function close()
{
	
}
function search()
{
   
}
</script>

<c:set var="resultObj" value="${result}" ></c:set>
<c:set var="resValue" value="${resultObj.resultValue}" ></c:set>	
<c:set var="adminList" value="${resValue.adminList}" ></c:set>
<c:set var="deptList" value="${resValue.deptList}" ></c:set>
<c:set var="desigList" value="${resValue.desigList}" ></c:set>
<c:set var="dateList" value="${resValue.dateList}" ></c:set>

<fmt:setLocale value="${sesssionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="enLables" scope="page"/>

<hdiits:form name="DDOInformation" validate="true" method="POST"
 action="hrms.htm?actionFlag=getddoinfo&actionName=getddoinfo&elementId=300708"  encType="multipart/form-data">
	 
	
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>DDO Information</b></a></li>
		
	</ul>
	</div>
	
<br>
   <hdiits:fieldGroup titleCaption="Parent Department" >
    <TABLE width="100%" border="1" align="center">
   	<tr>	
	<TD width="10%" > <b><hdiits:caption caption="Administrative Department" captionid="admin"/></b></TD>
	<td width="10%" >
	<hdiits:select name="Administrative Department" size="1" sort="false" caption="admin" id="seladminid" mandatory="true" validation="sel.isrequired">
	<hdiits:option value="-1"> School Education And Sports Department </hdiits:option>
	
	<c:forEach items="${resValue.adminList}" var="admin">

           	 <hdiits:option value="${admin}"> ${admin} </hdiits:option>
	
	</c:forEach>
	
	
	</hdiits:select>
	</td>
	</tr>
	
	<tr>
	<TD width="10%" > <b><hdiits:caption caption="Field Depratment/HOD Department" captionid="dept"/></b></TD>
	<td width="10%" >
		<hdiits:select name="Field Depratment/HOD Department" size="1" sort="false" caption="dept" id="seldepartment" mandatory="true" validation="sel.isrequired">
	<hdiits:option value="-1"> Directorate of Sports And Youth Service</hdiits:option>
	
	
	<c:forEach items="${resValue.deptList}" var="dept">

           	 <hdiits:option value="${dept}"> ${dept} </hdiits:option>
	
	</c:forEach>
	
	
	
	</hdiits:select>
	</td>
	
   </tr>

   </TABLE>
   </hdiits:fieldGroup>

<hdiits:fieldGroup titleCaption="Signing Authority" > 
<TABLE width="100%" border="1" align="center">
<tr>
<TD >
   <B><hdiits:caption captionid="Name of DDO" caption="Name of DDO"/> :</b>   
</td>
<td>   
   <hdiits:text  readonly="true"  id="txtEmpName" name="txtEmpName"  caption ="txtEmpName" maxlength="100"  default="ATUL P. GUPTE"/>
</TD>
<td></td>
</tr>
<Tr>
	   <TD width="10%" > <b><hdiits:caption caption="Designation" captionid="Designation"/></b></TD>
	<td width="10%" >
		<hdiits:select name="Designationcmb" size="1" sort="false" caption="Designation" id="Designationid" mandatory="true" validation="sel.isrequired">
	<hdiits:option value="-1"> Deputy Director </hdiits:option>
	
	
	<c:forEach items="${resValue.desigList}" var="desig">
	
           	 <hdiits:option value="${desig}"> ${desig} </hdiits:option>
	
	</c:forEach>
	
	
	</hdiits:select>
	</td>
 	
<TD width="10%" > <b><hdiits:caption caption="With Effect From Date" captionid="DateId"/></b></TD>
	<td width="10%" >
		<hdiits:select name="Datecmb" size="1" sort="false" caption="With Effect From Date" id="Dateid" mandatory="true" validation="sel.isrequired">
	<hdiits:option value="-1"> 04/01/2010 </hdiits:option>
	
	<c:forEach items="${resValue.dateList}" var="date">

           	 <hdiits:option value="${date}"> ${date} </hdiits:option>
	
	</c:forEach>
		
	
	</hdiits:select>
	</td>
</tr>

</TABLE>
</hdiits:fieldGroup>
<!-- End of Search -->


<hdiits:fieldGroup titleCaption=""  >
  <TABLE width="100%" border="1" align="center">

<tr>
<TD >
   <B><hdiits:caption captionid="Talno" caption="TAN No."/></b>   
</td>
<td>
   <hdiits:text  readonly="true"  id="txtNOName" name="txtNOName"  caption ="txtNOName" maxlength="100"  default="AASD000187"/>
</TD>
<TD>
(Note:All DDOs are required to apply and get TAN No as this will be used in printing paybill.)
</TD>
  </tr>
<tr>
<TD >
   <B><hdiits:caption captionid="ito" caption="ITO/Ward/Circle"/></b>   
</td>
<td>
   <hdiits:text  readonly="true"  id="txtitoName" name="txtitoName"  caption ="txtitoName" maxlength="100"  default="PUNE"/>
</TD>

</tr>

</TABLE>
</hdiits:fieldGroup>
   
<hdiits:fieldGroup  titleCaption="Bank Details" >
     <TABLE width="100%" border="1" align="center">
<tr>
<TD >
   <B><hdiits:caption captionid="Bank Name" caption="Bank Name"/> :</b>   
 </td>
 <td>
   <hdiits:text  readonly="true"  id="txtbnkName" name="txtbnkName"  caption ="txtbnkName" maxlength="100"  default="BANK OF MAHARASHTRA"/>
</TD>
<TD><hdiits:button name="Search Bank" type="button" onclick="search()" value="Search Bank"/>

 </TD>	
</tr>
<tr>
<TD >
   <B><hdiits:caption captionid="Branch Name" caption="Branch Name"/> :</b>   
</td>
<td>   
   <hdiits:text  readonly="true"  id="txtbranchName" name="txtbranchName"  caption ="txtbranchName" maxlength="150"  default="PUNE-PIMPRI"/>
</TD>
<TD >
   <B><hdiits:caption captionid="Bank A/C No." caption="Bank A/C No."/> :</b>   
</td>
<td> 
   <hdiits:text  readonly="true"  id="txtaccName" name="txtaccName"  caption ="txtaccName" maxlength="150"  default="000000000122"/>
</TD>
</tr>

</TABLE>
</hdiits:fieldGroup>

<hdiits:fieldGroup titleCaption=""  >
  <TABLE width="100%" border="1" align="center">

<tr>
   	 <TD >
   <B><hdiits:caption captionid="remarks" caption="Remarks (If Any)"/> :</b>   
</td>
<td>
   <hdiits:text  readonly="true"  id="txtreName" name="txtreName"  caption ="txtreName" maxlength="150"  default="FOR DEMO TEST"/>
</TD>
  </tr>

</TABLE>
</hdiits:fieldGroup>
     
    
<hdiits:fieldGroup titleCaption=""  >
  <TABLE width="100%" border="1" align="center">

<tr align="center">
   	 <td align="center" ><hdiits:button name="SAVE" type="button" onclick="save()" value="SAVE" />
    	    <hdiits:button name="RESET" type="button" onclick="reset()" value="RESET" />
     		<hdiits:button name="CLOSE" type="button" onclick="close()" value="CLOSE" /></td>
  </tr>

</TABLE>
</hdiits:fieldGroup>
<hdiits:jsField  name="validate" jsFunction="checkAmountValue()" />

</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

