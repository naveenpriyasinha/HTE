
<%
try {
%>
<%@ include file="../../../../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<fmt:setBundle basename="resources.eis.eisLables_en_US" var="DepartmentMST" scope="request" />

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="cmnDistrictMstList" value="${resValue.cmnDistrictMstList}"></c:set>
<c:set var="zpadminOffice" value="${resValue.zpadminOffice}"></c:set>
<c:set var="editFlag" value="${resValue.editFlag}"></c:set>
<c:set var="deptId" value="${resValue.deptId}"></c:set>
<c:set var="zpDepartmentedit" value="${resValue.zpDepartmentedit}"></c:set>
<c:set var="callloadMethod" value="${resValue.callloadMethod}"></c:set>
<c:set var="lLongAdminOffice" value="${resValue.lLongAdminOffice}"></c:set>
<c:set var="allDeptLst" value="${resValue.allDeptLst}"></c:set>
<c:set var="QualificationList" value="${resValue.QualificationList}"></c:set>


<head>
<script type="text/javascript" src="<c:url value="/script/common/ajaxLogoutError.js"/>"></script>
<script type="text/javascript" src="script/common/cmnMstScr.js"></script>
</head>

<hdiits:form name="frmDepartmentMst" validate="true" method="post">
<hdiits:hidden name="deptId" default="${deptId}"/>

<c:if test="${editFlag eq 'Y'}">
<hdiits:hidden name="txtCode"  id="txtCode" default="${zpDepartmentedit.departmentCode}"/>
</c:if>

<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b>Department Master</b></a></li>
		</ul>
	</div>
	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >
	<table width="100%">
		<tr>
			<td width="25%">
	    		<b><hdiits:caption captionid="ADMIN_DEPT_MST" bundle="${DepartmentMST}"/></b>
	    	</td>
	    	<c:if test="${editFlag ne 'Y'}">
			<td width="25%">
			<select name="seladminDept" id="seladminDept"  onchange="">
					<option value="0"><fmt:message key="EIS.Select" bundle="${DepartmentMST}" /></option>
					<c:forEach var="zpadminOffice12121"  items="${zpadminOffice}">
						<c:choose>
							<c:when test="${zpadminOffice12121.officeName eq lLongAdminOffice}">
								<option value="${zpadminOffice12121.officeCode}" selected="selected" disabled="disabled">${zpadminOffice12121.officeName}</option>
							</c:when>
							<c:otherwise>
							   	<option value="${zpadminOffice12121.officeCode}" >${zpadminOffice12121.officeName}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>				
			</td>
			</c:if> 
			<c:if test="${editFlag eq 'Y'}">
			<td width="25%">
				<select name="seladminDept" id="seladminDept"  onchange="" disabled="disabled">
				
					<option value="0" ><fmt:message key="EIS.Select" bundle="${DepartmentMST}" /></option>
						<c:forEach var="zpadminOffice12"  items="${zpadminOffice}">
						
						<c:choose>
						
							<c:when test="${zpadminOffice12.officeCode eq zpDepartmentedit.admintypecode}">
								<option value="${zpadminOffice12.officeCode}" selected="selected" disabled="disabled">${zpadminOffice12.officeName}</option>
							</c:when>
							</c:choose>
					</c:forEach>
				</select>				
			</td>
			</c:if> 
			<td width="25%"></td>
			<td width="25%"></td>
		</tr>
	
		<tr>
			<td width="25%">
	    		<b><hdiits:caption captionid="DEPT_NAME" bundle="${DepartmentMST}"/></b>
	    	</td>
	    	<c:if test="${editFlag ne 'Y'}">
			<td width="25%">
			<hdiits:select  name="cmbdeptName"
				id="cmbdeptName"  onchange="DeptCodecopytoCode(this);">
				<hdiits:option value="-1"><fmt:message key="EIS.Select" bundle="${DepartmentMST}" /></hdiits:option>
				 <c:forEach items="${allDeptLst}" var="allDeptLst">
						<option value="<c:out value="${allDeptLst[0]}"/>">
							<c:out value="${allDeptLst[1]}"/>
						</option>
				</c:forEach> 
				</hdiits:select>	
				<input type="hidden" value="" name="txtdeptName" id="txtdeptName"/>		
			</td>
			</c:if>
			<c:if test="${editFlag eq 'Y'}">
			<td width="25%">
				<select  name="cmbdeptName"
				id="cmbdeptName"  onchange="DeptCodecopytoCode(this);" >
			
				 <c:forEach items="${allDeptLst}" var="allDeptLst">
						<c:if test="${zpDepartmentedit.departmentCode eq allDeptLst[0]}">
						<option value="<c:out value="${allDeptLst[0]}"/>">
							<c:out value="${allDeptLst[1]}"/>
						</option>
						</c:if>
				</c:forEach> 
				</select>				
			</td>
			</c:if>
	    	<td width="25%"></td>
			<td width="25%"></td>
				
		</tr>
	
		<tr>
			<td width="15%" align="left"><fmt:message key="ADMIN_QUALIFICATION"
				bundle="${DepartmentMST}"></fmt:message></td>
			<td width="20%" align="left"><select name="qualification" id="qualification" ${varDisabled}>
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			<c:forEach var="qualification" items="${resValue.QualificationList}">
							<option value="${qualification}">${qualification}</option>
		
	
			</c:forEach>
			</select>
			<input type="hidden" value="" name="txtqualification" id="txtqualification"/>
			</td>
			</tr>
		
		
		<tr>
			<td width="25%">
	    		<b><hdiits:caption captionid="CODE" bundle="${DepartmentMST}"/></b>
	    	</td>
	    	<c:if test="${editFlag ne 'Y'}">
			<td width="25%">
			<input type="text" name='txtCode' id="txtCode" readonly="readonly" />
					
			</td>
			</c:if>
			<c:if test="${editFlag eq 'Y'}">
			<td width="25%">
				<input type="text" name='txtCode' id="txtCode" value="${zpDepartmentedit.departmentCode}" readonly="readonly" />		
			</td>
			</c:if>
	    	<td width="25%"></td>
			<td width="25%"></td>
				
		</tr>
	</table>
	<br><br>
	<table align="center">
		<tr>
			<td align="center">
			<c:if test="${editFlag ne 'Y'}">
				<hdiits:button name="btnSave" id="btnSave" type="button" captionid="eis.save" bundle="${DepartmentMST}" onclick="saveDepartData();"/>
			</c:if>
			<c:if test="${editFlag eq 'Y'}">
				<hdiits:button name="btnSave" id="btnSave" type="button" captionid="eis.save" bundle="${DepartmentMST}" onclick="saveDepartmentDataedit();"/>
			</c:if>
				<hdiits:button name="btnClose" id="btnClose" type="button" captionid="eis.close" bundle="${DepartmentMST}" onclick="CloseScreen()"/>
			</td>
		</tr>
	</table>
	<br>
	
	
	</div>

<hdiits:validate locale="${locale}" controlNames="" />
</hdiits:form>


<c:if test="${callloadMethod eq 'Y'}">
<script>
	alert('${callloadMethod}');
	executeOnLoad();
</script>	
</c:if>

<%
}catch(Exception e) {e.printStackTrace();}
%>
<script type="text/javascript">

function DeptCodecopytoCode() 
{
	document.getElementById('txtCode').value = document.getElementById('cmbdeptName').value;
	 var cmbObj= document.getElementById('cmbdeptName');
	 var comVal= cmbObj.options[cmbObj.selectedIndex].text;
	 
	 document.getElementById('txtdeptName').value= comVal;
	}
</script>
<script>
initializetabcontent("maintab");



function executeOnLoad()
{	
	var seladminDeptId = document.getElementById("seladminDept").options[document.getElementById("seladminDept").options.selectedIndex].value;	
	var urlstyle="ifms.htm?actionFlag=countOfficeType&seladminDeptId="+seladminDeptId+"&callloadMethod=N";				
    document.frmDepartmentMst.action=urlstyle;
    document.frmDepartmentMst.submit();	
}
function saveDepartData(){

	var seladminDept = document.getElementById('seladminDept').value;
	var txtdeptName = document.getElementById('txtdeptName').value;
   var txtCode=document.forms["frmDepartmentMst"]["txtCode"].value;
	
	
	if (seladminDept == '0') {
				alert("Please Select Admin Department Type office Name");
				document.getElementById('seladminDept').focus();
				return false;
		
			}
	if (txtdeptName==null || txtdeptName=="")
	  {
		alert("Please Enter Department Name");
		
		return false;
	  }

	/*if (txtCode==null || txtCode=="")
	  {
		alert("Please Enter Admin Department Type office Code");
		document.getElementById('txtCode').focus();
		return false;
	  }*/
	
	 var urlstyle="ifms.htm?actionFlag=SaveDepartmentMst";
     document.forms[0].action=urlstyle;
     document.forms[0].submit();
}
function saveDepartmentDataedit(){

	var seladminDept = document.getElementById('seladminDept').value;
	var txtdeptName = document.forms["frmDepartmentMst"]["txtdeptName"].value;
	var txtCode=document.forms["frmDepartmentMst"]["txtCode"].value;
	
	
	if (seladminDept == '0') {
				alert("Please Select Admin Department Type office Name");
				document.getElementById('seladminDept').focus();
				return false;
		
			}
	if (txtdeptName==null || txtdeptName=="")
	  {
		alert("Please Enter Department Name");
		document.getElementById('txtdeptName').focus();
		return false;
	  }

	/*if (txtCode==null || txtCode=="")
	  {
		alert("Please Enter Admin Department Type office Code");
		document.getElementById('txtCode').focus();
		return false;
	  }*/
	
	 var urlstyle="ifms.htm?actionFlag=SaveDepartmentMst&edit=Y&deptId=${deptId}";
     document.forms[0].action=urlstyle;
     document.forms[0].submit();
}

function CloseScreen(){
	var urlstyle="ifms.htm?actionFlag=validateLogin";
    document.forms[0].action=urlstyle;
    document.forms[0].submit();
}
function getAdminCode()
{
	
	var AdminCode= document.getElementById("seladminDept").value;
	
	document.forms["frmDepartmentMst"]["txtCode"].value=AdminCode;
	
}

function getAdminDeptTypeofficeCode()
{
	var seladminDeptId = document.getElementById("seladminDept").options[document.getElementById("seladminDept").options.selectedIndex].value;
	alert("cfxsv"+seladminDeptId);
	var urlstyle="ifms.htm?actionFlag=countOfficeType&seladminDeptId="+seladminDeptId+"&callloadMethod=N";
	alert("urlstyle >> " + urlstyle);
    document.forms[0].action=urlstyle;
    document.forms[0].submit();
}
</script>
