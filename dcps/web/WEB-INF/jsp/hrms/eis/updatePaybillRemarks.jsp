<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>
<fmt:setBundle basename="resources.Payroll" var="commonLable" scope="request"/>
<fmt:setBundle basename="resources.eis.eis_Constants" var="eiscommonLable" scope="request"/>	
<script type="text/javascript"><!--

function getParentComponentData()
{	
	basic = opener.document.getElementById("basic0101").value;
	document.forms[0].basic0101.value =  basic;
	PT = opener.document.getElementById("deduc9570").value;
	document.forms[0].deduc9570.value =  PT;	
	LeaveSal = opener.document.getElementById("ls").value;
	document.forms[0].ls.value =  LeaveSal;
	HRR = opener.document.getElementById("deduc9550").value;
	document.forms[0].deduc9550.value =  HRR;
	
	HRA = opener.document.getElementById("allow0110").value;
	document.forms[0].allow0110.value =  HRA;
	DA = opener.document.getElementById("allow0103").value;
	document.forms[0].allow0103.value =  DA;
	CLA = opener.document.getElementById("allow0111").value;
	document.forms[0].allow0111.value =  CLA;
	WA = opener.document.getElementById("allow1301").value;
	document.forms[0].allow1301.value =  WA;
	MA = opener.document.getElementById("allow0107").value;
	document.forms[0].allow0107.value =  MA;
	TA = opener.document.getElementById("allow0113").value;
	document.forms[0].allow0113.value =  TA;		
	DAGPF = opener.document.getElementById("DAGPF").value;
	document.forms[0].DAGPF.value =  DAGPF;
	
	document.forms[0].txtMARemarks.value =  opener.document.getElementById("txtMARemarks").value;
	document.forms[0].txtWARemarks.value =  opener.document.getElementById("txtWARemarks").value;
	document.forms[0].txtDARemarks.value =  opener.document.getElementById("txtDARemarks").value;
	document.forms[0].txtCLARemarks.value =  opener.document.getElementById("txtCLARemarks").value;
	document.forms[0].txtTARemarks.value =  opener.document.getElementById("txtTARemarks").value;
	document.forms[0].txtHRARemarks.value =  opener.document.getElementById("txtHRARemarks").value;
	document.forms[0].txtPTRemarks.value =  opener.document.getElementById("txtPTRemarks").value;
	document.forms[0].txtHRRRemarks.value =  opener.document.getElementById("txtHRRRemarks").value;
	
	document.forms[0].txtBasicRemarks.value =  opener.document.getElementById("txtBasicRemarks").value;
	document.forms[0].txtLeaveSalRemarks.value =  opener.document.getElementById("txtLeaveSalRemarks").value;
	document.forms[0].txtDAGPFRemarks.value =  opener.document.getElementById("txtDAGPFRemarks").value;
	
	document.forms[0].chkBasic.checked = opener.document.getElementById("chkBasic").checked;
	document.forms[0].chkPT.checked = opener.document.getElementById("chkPT").checked;
	document.forms[0].chkLeaveSal.checked = opener.document.getElementById("chkLeaveSal").checked;
	document.forms[0].chkHRR.checked = opener.document.getElementById("chkHRR").checked;
	document.forms[0].chkHRA.checked = opener.document.getElementById("chkHRA").checked;
	document.forms[0].chkDA.checked = opener.document.getElementById("chkDA").checked;
	document.forms[0].chkCLA.checked = opener.document.getElementById("chkCLA").checked;
	document.forms[0].chkWA.checked = opener.document.getElementById("chkWA").checked;
	document.forms[0].chkMA.checked = opener.document.getElementById("chkMA").checked;
	document.forms[0].chkTA.checked = opener.document.getElementById("chkTA").checked;	
	document.forms[0].chkDAGPF.checked = opener.document.getElementById("chkDAGPF").checked;
	
	enableRemarks();
}
function enableRemarks()
{	
	if (document.forms[0].chkBasic.checked == true)
	{			
			document.forms[0].txtBasicRemarks.readOnly = false;
			document.forms[0].basic0101.readOnly = false;			
	}
	else
	{				
			document.forms[0].basic0101.value=opener.document.getElementById("oldBasic").value;
			document.forms[0].txtBasicRemarks.value='';
			document.forms[0].txtBasicRemarks.readOnly = true;
			document.forms[0].basic0101.readOnly = true;
	}		
	if (document.forms[0].chkPT.checked == true)
	{
			document.forms[0].txtPTRemarks.readOnly = false;
			document.forms[0].deduc9570.readOnly = false;
	}
	else
	{		
			document.forms[0].deduc9570.value=opener.document.getElementById("oldPT").value;
			document.forms[0].txtPTRemarks.value='';
			document.forms[0].txtPTRemarks.readOnly = true;
			document.forms[0].deduc9570.readOnly = true;
	}
	if (document.forms[0].chkLeaveSal.checked == true)
	{
			document.forms[0].txtLeaveSalRemarks.readOnly = false;
			document.forms[0].ls.readOnly = false;
	}
	else
	{
			document.forms[0].ls.value=opener.document.getElementById("oldLeaveSal").value;
			document.forms[0].txtLeaveSalRemarks.value='';
			document.forms[0].txtLeaveSalRemarks.readOnly = true;
			document.forms[0].ls.readOnly = true;
	}
	if (document.forms[0].chkHRR.checked == true)
	{
			document.forms[0].txtHRRRemarks.readOnly = false;
			document.forms[0].deduc9550.readOnly = false;
	}
	else
	{
			document.forms[0].deduc9550.value=opener.document.getElementById("oldHRR").value;
			document.forms[0].txtHRRRemarks.value='';
			document.forms[0].txtHRRRemarks.readOnly = true;
			document.forms[0].deduc9550.readOnly = true;
	}
	
	if (document.forms[0].chkHRA.checked == true)
	{
			document.forms[0].txtHRARemarks.readOnly = false;
			document.forms[0].allow0110.readOnly = false;
	}
	else
	{			
			document.forms[0].allow0110.value=opener.document.getElementById("oldHRA").value;
			document.forms[0].txtHRARemarks.value='';	
			document.forms[0].txtHRARemarks.readOnly = true;
			document.forms[0].allow0110.readOnly = true;
	}
	if (document.forms[0].chkDA.checked == true)
	{
			document.forms[0].txtDARemarks.readOnly = false;
			document.forms[0].allow0103.readOnly = false;
	}
	else
	{
			document.forms[0].allow0103.value=opener.document.getElementById("oldDA").value;
			document.forms[0].txtDARemarks.value='';	
			document.forms[0].txtDARemarks.readOnly = true;
			document.forms[0].allow0103.readOnly = true;
	}
	if (document.forms[0].chkCLA.checked == true)
	{
			document.forms[0].txtCLARemarks.readOnly = false;
			document.forms[0].allow0111.readOnly = false;
	}
	else
	{
			document.forms[0].allow0111.value=opener.document.getElementById("oldCLA").value;
			document.forms[0].txtCLARemarks.value='';	
			document.forms[0].txtCLARemarks.readOnly = true;
			document.forms[0].allow0111.readOnly = true;
	}
	if (document.forms[0].chkWA.checked == true)
	{
			document.forms[0].txtWARemarks.readOnly = false;
			document.forms[0].allow1301.readOnly = false;
	}
	else
	{
			document.forms[0].allow1301.value=opener.document.getElementById("oldWA").value;
			document.forms[0].txtWARemarks.value='';
			document.forms[0].txtWARemarks.readOnly = true;
			document.forms[0].allow1301.readOnly = true;
	}
	if (document.forms[0].chkMA.checked == true)
	{
			document.forms[0].txtMARemarks.readOnly = false;
			document.forms[0].allow0107.readOnly = false;
	}
	else
	{
			document.forms[0].allow0107.value=opener.document.getElementById("oldMA").value;
			document.forms[0].txtMARemarks.value='';
			document.forms[0].txtMARemarks.readOnly = true;
			document.forms[0].allow0107.readOnly = true;
	}
	if (document.forms[0].chkTA.checked == true)
	{
			document.forms[0].txtTARemarks.readOnly = false;
			document.forms[0].allow0113.readOnly = false;
	}
	else
	{
			document.forms[0].allow0113.value=opener.document.getElementById("oldTA").value;
			document.forms[0].txtTARemarks.value='';
			document.forms[0].txtTARemarks.readOnly = true;
			document.forms[0].allow0113.readOnly = true;
	}
	
	
	if (document.forms[0].chkDAGPF.checked == true)
	{
			document.forms[0].txtDAGPFRemarks.readOnly = false;
			document.forms[0].DAGPF.readOnly = false;
	}
	else
	{
			document.forms[0].DAGPF.value=opener.document.getElementById("oldDAGPF").value;
			document.forms[0].txtDAGPFRemarks.value='';
			document.forms[0].txtDAGPFRemarks.readOnly = true;
			document.forms[0].DAGPF.readOnly = true;
	}
	
}
function validateRemarks()
{	
	if (document.forms[0].chkBasic.checked == true)
	{			
			basicRemarks = trim(document.forms[0].txtBasicRemarks.value);
			if(basicRemarks=='')
			{
				alert('Please enter remarks for updating Basic Amount');
				document.forms[0].txtBasicRemarks.focus();
				return false;
			}
			opener.document.updatePaybill.chkBasic.checked=true;			
	}	
	else
	{
			document.forms[0].txtBasicRemarks.value='';
	}
	if (document.forms[0].chkPT.checked == true)
	{			
			ptRemarks=trim(document.forms[0].txtPTRemarks.value);
			if(ptRemarks=='')
			{
				alert('Please enter remarks for updating Professional Tax');
				document.forms[0].txtPTRemarks.focus();
				return false;
			}
			opener.document.updatePaybill.chkPT.checked=true;			
	}	
	else
	{
			document.forms[0].txtPTRemarks.value='';
	}
	if (document.forms[0].chkLeaveSal.checked == true)
	{
			lsRemarks = trim(document.forms[0].txtLeaveSalRemarks.value);
			if(lsRemarks=='')
			{
				alert('Please enter remarks for updating Leave salary');
				document.forms[0].txtLeaveSalRemarks.focus();
				return false;
			}
			opener.document.updatePaybill.chkLeaveSal.checked=true;	
	}
	else
	{
			document.forms[0].txtLeaveSalRemarks.value='';
	}
	if (document.forms[0].chkHRR.checked == true)
	{
			hrrRemarks = trim(document.forms[0].txtHRRRemarks.value);
			if(hrrRemarks=='')
			{
				alert('Please enter remarks for updating HRR');
				document.forms[0].txtHRRRemarks.focus();
				return false;
			}
			opener.document.updatePaybill.chkHRR.checked=true;	
	}
	else
	{
			document.forms[0].txtHRRRemarks.value='';
	}
		
	if (document.forms[0].chkHRA.checked == true)
	{
			hraRemarks=trim(document.forms[0].txtHRARemarks.value);
			if(hraRemarks=='')
			{
				alert('Please enter remarks for updating HRA');
				document.forms[0].txtHRARemarks.focus();
				return false;
			}
			opener.document.updatePaybill.chkHRA.checked=true;
	}
	else
	{
			document.forms[0].txtHRARemarks.value='';
	}
	if (document.forms[0].chkDA.checked == true)
	{
			daRemarks=trim(document.forms[0].txtDARemarks.value);
			if(daRemarks=='')
			{
				alert('Please enter remarks for updating DA');
				document.forms[0].txtDARemarks.focus();
				return false;
			}
			opener.document.updatePaybill.chkDA.checked=true;
	}
	else
	{
			document.forms[0].txtDARemarks.value='';
	}
	if (document.forms[0].chkCLA.checked == true)
	{
			claRemarks=trim(document.forms[0].txtCLARemarks.value);
			if(claRemarks=='')
			{
				alert('Please enter remarks for updating CLA');
				document.forms[0].txtCLARemarks.focus();
				return false;
			}
			opener.document.updatePaybill.chkCLA.checked=true;
	}
	else
	{
			document.forms[0].txtCLARemarks.value='';
	}
	if (document.forms[0].chkWA.checked == true)
	{
			waRemarks=trim(document.forms[0].txtWARemarks.value);
			if(waRemarks=='')
			{
				alert('Please enter remarks for updating WA');
				document.forms[0].txtWARemarks.focus();
				return false;
			}
			opener.document.updatePaybill.chkWA.checked=true;
	}
	else
	{
			document.forms[0].txtWARemarks.value='';
	}
	if (document.forms[0].chkMA.checked == true)
	{
			maRemarks=trim(document.forms[0].txtMARemarks.value);
			if(maRemarks=='')
			{
				alert('Please enter remarks for updating MA');
				document.forms[0].txtMARemarks.focus();
				return false;
			}
			opener.document.updatePaybill.chkMA.checked=true;
	}
	else
	{
			document.forms[0].txtMARemarks.value='';
	}
	
	if (document.forms[0].chkTA.checked == true)
	{
			taRemarks=trim(document.forms[0].txtTARemarks.value);
			if(taRemarks=='')
			{
				alert('Please enter remarks for updating TA');
				document.forms[0].txtTARemarks.focus();
				return false;
			}	
			opener.document.updatePaybill.chkTA.checked=true;
	}
	else
	{
			document.forms[0].txtTARemarks.value='';
	}
	
	if (document.forms[0].chkDAGPF.checked == true)
	{
			dagpfRemarks=trim(document.forms[0].txtDAGPFRemarks.value);
			if(dagpfRemarks=='')
			{
				alert('Please enter remarks for updating DAGPF');
				document.forms[0].txtDAGPFRemarks.focus();
				return false;
			}	
			opener.document.updatePaybill.chkDAGPF.checked=true;
	}	
	else
	{
			document.forms[0].txtDAGPFRemarks.value='';
	}
	
	return true;
}
function updateParentData() 
{	
	successFlag = validateRemarks();	
	if(successFlag)
	{			
			opener.document.updatePaybill.editBasicCompoFlag.value = 'Y';	
			
			if(document.forms[0].chkBasic.checked==false)
			{				
				opener.document.updatePaybill.chkBasic.checked=false;
			}
			if(document.forms[0].chkPT.checked==false)
			{
				opener.document.updatePaybill.chkPT.checked=false;
			}		
			if(document.forms[0].chkLeaveSal.checked==false)
			{
				opener.document.updatePaybill.chkLeaveSal.checked=false;
			}		
			if(document.forms[0].chkHRR.checked==false)
			{
				opener.document.updatePaybill.chkHRR.checked=false;
			}			
			if(document.forms[0].chkHRA.checked==false)
			{
				opener.document.updatePaybill.chkHRA.checked=false;
			}
			if(document.forms[0].chkDA.checked==false)
			{
				opener.document.updatePaybill.chkDA.checked=false;
			}
			if(document.forms[0].chkCLA.checked==false)
			{
				opener.document.updatePaybill.chkCLA.checked=false;
			}
			if(document.forms[0].chkWA.checked==false)
			{
				opener.document.updatePaybill.chkWA.checked=false;
			}
			if(document.forms[0].chkMA.checked==false)
			{
				opener.document.updatePaybill.chkMA.checked=false;
			}
			if(document.forms[0].chkTA.checked==false)
			{
				opener.document.updatePaybill.chkTA.checked=false;
			}			
			if(document.forms[0].chkDAGPF.checked==false)
			{
				opener.document.updatePaybill.chkDAGPF.checked=false;
			}
			
			opener.document.updatePaybill.txtBasicRemarks.value = document.forms[0].txtBasicRemarks.value;
			opener.document.updatePaybill.txtPTRemarks.value = document.forms[0].txtPTRemarks.value;
			opener.document.updatePaybill.txtLeaveSalRemarks.value = document.forms[0].txtLeaveSalRemarks.value;
			opener.document.updatePaybill.txtHRRRemarks.value = document.forms[0].txtHRRRemarks.value;				
			
			opener.document.updatePaybill.txtHRARemarks.value = document.forms[0].txtHRARemarks.value;
			opener.document.updatePaybill.txtDARemarks.value = document.forms[0].txtDARemarks.value;
			opener.document.updatePaybill.txtCLARemarks.value = document.forms[0].txtCLARemarks.value;			
			opener.document.updatePaybill.txtWARemarks.value = document.forms[0].txtWARemarks.value;;			
			opener.document.updatePaybill.txtMARemarks.value = document.forms[0].txtMARemarks.value;			
			opener.document.updatePaybill.txtTARemarks.value = document.forms[0].txtTARemarks.value;				
			opener.document.updatePaybill.txtDAGPFRemarks.value = document.forms[0].txtDAGPFRemarks.value;
			
		
			opener.document.updatePaybill.basic0101.value = document.forms[0].basic0101.value;
			opener.document.updatePaybill.deduc9570.value = document.forms[0].deduc9570.value;
			opener.document.updatePaybill.ls.value = document.forms[0].ls.value;
			opener.document.updatePaybill.deduc9550.value = document.forms[0].deduc9550.value;			
			
			opener.document.updatePaybill.allow0110.value = document.forms[0].allow0110.value;
			opener.document.updatePaybill.allow0103.value = document.forms[0].allow0103.value;
			opener.document.updatePaybill.allow0111.value = document.forms[0].allow0111.value;
			opener.document.updatePaybill.allow1301.value = document.forms[0].allow1301.value;
			opener.document.updatePaybill.allow0107.value = document.forms[0].allow0107.value;
			opener.document.updatePaybill.allow0113.value = document.forms[0].allow0113.value;
				
			opener.document.updatePaybill.DAGPF.value = document.forms[0].DAGPF.value;
			
			
		    self.close();
		    return true;
	}
	else
	{		
		return false;
	}
}
function resetValues()
{
	document.forms[0].basic0101.value=opener.document.getElementById("oldBasic").value;
	document.forms[0].deduc9570.value=opener.document.getElementById("oldPT").value;
	document.forms[0].ls.value=opener.document.getElementById("oldLeaveSal").value;
	document.forms[0].deduc9550.value=opener.document.getElementById("oldHRR").value;
	document.forms[0].allow0110.value=opener.document.getElementById("oldHRA").value;
	document.forms[0].allow0103.value=opener.document.getElementById("oldDA").value;
	document.forms[0].allow0111.value=opener.document.getElementById("oldCLA").value;
	document.forms[0].allow1301.value=opener.document.getElementById("oldWA").value;
	document.forms[0].allow0107.value=opener.document.getElementById("oldMA").value;
	document.forms[0].allow0113.value=opener.document.getElementById("oldTA").value;
	document.forms[0].DAGPF.value=opener.document.getElementById("oldDAGPF").value;

	document.forms[0].txtBasicRemarks.value='';
	document.forms[0].txtPTRemarks.value='';
	document.forms[0].txtLeaveSalRemarks.value='';
	document.forms[0].txtHRRRemarks.value='';
	document.forms[0].txtHRARemarks.value='';	
	document.forms[0].txtDARemarks.value='';	
	document.forms[0].txtCLARemarks.value='';	
	document.forms[0].txtWARemarks.value='';
	document.forms[0].txtMARemarks.value='';
	document.forms[0].txtTARemarks.value='';
	document.forms[0].txtDAGPFRemarks.value='';

	document.forms[0].chkBasic.checked=false;
	document.forms[0].chkPT.checked=false;
	document.forms[0].chkLeaveSal.checked=false;
	document.forms[0].chkHRR.checked=false;
	document.forms[0].chkHRA.checked=false;
	document.forms[0].chkDA.checked=false;
	document.forms[0].chkCLA.checked=false;
	document.forms[0].chkWA.checked=false;
	document.forms[0].chkMA.checked=false;
	document.forms[0].chkTA.checked=false;
	document.forms[0].chkDAGPF.checked=false;
	
}
--></script>

<fmt:setLocale value="${sesssionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="enLables" scope="page"/>


<hdiits:form name="updatePaybillRemarks" validate="true" method="POST"  
	encType="multipart/form-data">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>Update Paybill Remarks</b></a></li>
		
	</ul>
	</div>
	
	
<div class="halftabcontentstyle">


	<div id="tcontent1" class="halftabcontent" tabno="0">
<br>
    
    <hdiits:fieldGroup  titleCaption="Paybill Details" >
    <TABLE width="100%" border="0" align="center">
    <TR>
			<TD align="center" colspan="6" ><b><hdiits:hidden  name="paybillGrpId" default ="${paybillVO.paybillGrpId}"  />  </b></td> 
	</TR>
    </TABLE>
    <TABLE width="100%" border="1" align="center"> 
    <TR>
			<TD> <hdiits:checkbox name="chkBasic" value="Basic" onclick="enableRemarks()" /> </TD>			 
			<TD><b><hdiits:caption captionid="eis.updatepaybill.Basic" bundle="${enLables}"></hdiits:caption> : </b></TD>
			<TD><hdiits:number style="text-align:right" readonly="true" default ="${paybillVO.basic0101+paybillVO.basic0102}" name="basic0101" captionid="eis.updatepaybill.Basic"  bundle="${enLables}"  maxlength="8" size="20" validation="txt.isrequired"  /></TD>			
			<td><b>Remarks:</b></td>
			<TD><hdiits:textarea name="txtBasicRemarks" readonly="true" maxlength="500"  cols="50" rows="3" onblur="checkSplChar(this)" ></hdiits:textarea> </TD>
   </TR>
   <TR>
			<TD> <hdiits:checkbox name="chkPT" value="PT" onclick="enableRemarks()" /> </TD>			 
			<TD ><b><hdiits:caption captionid="eis.updatepaybill.ProfessionalTax" bundle="${enLables}"></hdiits:caption> : </b></TD>
			<TD > <hdiits:number style="text-align:right" readonly="true" default ="${paybillVO.deduc9570}"  name="deduc9570" captionid="eis.updatepaybill.ProfessionalTax"  bundle="${enLables}"  maxlength="8" size="20" validation="txt.isrequired"  /></TD>			
			<td><b>Remarks:</b></td>
			<TD><hdiits:textarea name="txtPTRemarks" readonly="true" maxlength="500"  cols="50" rows="3" onblur="checkSplChar(this)" ></hdiits:textarea> </TD>
   </TR>  
   
   <TR>
			<TD> <hdiits:checkbox name="chkLeaveSal" value="LeaveSal" onclick="enableRemarks()" /> </TD>			 
			<TD ><b><hdiits:caption captionid="eis.updatepaybill.LeaveSalary" bundle="${enLables}"></hdiits:caption> : </b></TD>
			<TD > <hdiits:number style="text-align:right" readonly="true" default ="${paybillVO.ls}"  name="ls" captionid="eis.updatepaybill.LeaveSalary"  bundle="${enLables}"  maxlength="8" size="20" validation="txt.isrequired"  /></TD>			
			<td><b>Remarks:</b></td>
			<TD><hdiits:textarea name="txtLeaveSalRemarks" readonly="true" maxlength="500"  cols="50" rows="3" onblur="checkSplChar(this)" ></hdiits:textarea> </TD>
   </TR>
   <TR>
			<TD> <hdiits:checkbox name="chkHRR" value="HRR" onclick="enableRemarks()" /> </TD>			 
			<TD><b><hdiits:caption captionid="eis.updatepaybill.HRR_RENTB" bundle="${enLables}"></hdiits:caption></b></TD>
			<TD> <hdiits:number style="text-align:right" readonly="true" default ="${paybillVO.deduc9550+paybillVO.deduc9560}"  name="deduc9550" captionid="eis.updatepaybill.HRR"  bundle="${enLables}"  maxlength="8" size="20" validation="txt.isrequired"  /></TD>			
			<td><b>Remarks:</b></td>
			<TD><hdiits:textarea name="txtHRRRemarks" readonly="true" maxlength="500"  cols="50" rows="3" onblur="checkSplChar(this)" ></hdiits:textarea> </TD>
   </TR>
  
   
     <TR>
			<TD> <hdiits:checkbox name="chkHRA" value="HRA" onclick="enableRemarks()" /> </TD>			 
			<TD><b><hdiits:caption captionid="eis.updatepaybill.HRA" bundle="${enLables}"></hdiits:caption> : </b></TD>
			<TD> <hdiits:number style="text-align:right" readonly="true" default ="${paybillVO.allow0110}"  name="allow0110" captionid="eis.updatepaybill.HRA"  bundle="${enLables}"  maxlength="8" size="20" validation="txt.isrequired"  /></TD>			
			<td><b>Remarks:</b></td>
			<TD><hdiits:textarea name="txtHRARemarks" readonly="true" maxlength="500"  cols="50" rows="3" onblur="checkSplChar(this)" ></hdiits:textarea> </TD>
   </TR>
    <TR>
			<TD> <hdiits:checkbox name="chkDA" value="DA" onclick="enableRemarks()" /> </TD>			 
			<TD><b><hdiits:caption captionid="eis.updatepaybill.DA" bundle="${enLables}"></hdiits:caption> : </b></TD>
			<TD> <hdiits:number style="text-align:right" readonly="true" default ="${paybillVO.allow0103}"   name="allow0103" captionid="eis.updatepaybill.DA"  bundle="${enLables}"  maxlength="8" size="20" validation="txt.isrequired"  /></TD>			
			<td><b>Remarks:</b></td>
			<TD><hdiits:textarea name="txtDARemarks" readonly="true" maxlength="500"  cols="50" rows="3" onblur="checkSplChar(this)" ></hdiits:textarea> </TD>
   </TR>
   <TR>
			<TD> <hdiits:checkbox name="chkCLA" value="CLA" onclick="enableRemarks()" /> </TD>			 
			<TD ><b><hdiits:caption captionid="eis.updatepaybill.CLA" bundle="${enLables}"></hdiits:caption> : </b></TD>
			<TD > <hdiits:number style="text-align:right" readonly="true" default ="${paybillVO.allow0111}"   name="allow0111" captionid="eis.updatepaybill.CLA"  bundle="${enLables}"  maxlength="8" size="20" validation="txt.isrequired"  /></TD>			
			<td><b>Remarks:</b></td>
			<TD><hdiits:textarea name="txtCLARemarks" readonly="true" maxlength="500"  cols="50" rows="3" onblur="checkSplChar(this)" ></hdiits:textarea> </TD>
   </TR>
   <TR>
			<TD> <hdiits:checkbox name="chkWA" value="WA" onclick="enableRemarks()" /> </TD>			 
			<TD ><b><hdiits:caption captionid="eis.updatepaybill.WA" bundle="${enLables}"></hdiits:caption> : </b></TD>
			<TD> <hdiits:number style="text-align:right" readonly="true" default ="${paybillVO.allow1301}"   name="allow1301" captionid="eis.updatepaybill.WA"  bundle="${enLables}"  maxlength="8" size="20" validation="txt.isrequired"  /></TD>			
			<td><b>Remarks:</b></td>
			<TD><hdiits:textarea name="txtWARemarks" readonly="true" maxlength="500"  cols="50" rows="3" onblur="checkSplChar(this)" ></hdiits:textarea> </TD>
   </TR>
   <TR>
			<TD> <hdiits:checkbox name="chkMA" value="MA" onclick="enableRemarks()" /> </TD>			 
			<TD ><b><hdiits:caption captionid="eis.updatepaybill.MA" bundle="${enLables}"></hdiits:caption> : </b></TD>
			<TD > <hdiits:number style="text-align:right" readonly="true" default ="${paybillVO.allow0107}"   name="allow0107" captionid="eis.updatepaybill.MA"  bundle="${enLables}"  maxlength="8" size="20" validation="txt.isrequired"  /></TD>			
			<td><b>Remarks:</b></td>
			<TD><hdiits:textarea name="txtMARemarks" readonly="true" maxlength="500"  cols="50" rows="3" onblur="checkSplChar(this)" ></hdiits:textarea> </TD>
   </TR>
	<TR>
			<TD> <hdiits:checkbox name="chkTA" value="TA" onclick="enableRemarks()" /> </TD>			 
			<TD><b><hdiits:caption captionid="eis.updatepaybill.TransAllw" bundle="${enLables}"></hdiits:caption> : </b></TD>
			<TD> <hdiits:number style="text-align:right" readonly="true" default ="${paybillVO.allow0113}"   name="allow0113" captionid="eis.updatepaybill.TransAllw"  bundle="${enLables}"  maxlength="8" size="20" validation="txt.isrequired"  /></TD>			
			<td><b>Remarks:</b></td>
			<TD><hdiits:textarea name="txtTARemarks" readonly="true" maxlength="500"  cols="50" rows="3" onblur="checkSplChar(this)" ></hdiits:textarea> </TD>
   </TR>
	
   
     <TR>
			<TD> <hdiits:checkbox name="chkDAGPF" value="DAGPF" onclick="enableRemarks()" /> </TD>			 
			<TD ><b><hdiits:caption captionid="eis.updatepaybill.DAGPF" bundle="${enLables}"></hdiits:caption> : </b></TD>
			<TD > <hdiits:number style="text-align:right" readonly="true" default ="${paybillVO.deduc9998+paybillVO.deduc9999}" name="DAGPF" captionid="eis.updatepaybill.DAGPF"  bundle="${enLables}"  maxlength="8" size="20"   /></TD>			
			<td><b>Remarks:</b></td>
			<TD><hdiits:textarea name="txtDAGPFRemarks" readonly="true" maxlength="500"  cols="50" rows="3" onblur="checkSplChar(this)" ></hdiits:textarea> </TD>
   </TR>  
   <tr>
   <Td colspan="5" align="center">
   	<hdiits:submitbutton name="btnOk" type="button" value="OK" onclick="return updateParentData();" />
	<hdiits:button name="btnClose" type="button" onclick="window.close();" value="Close" />
	<hdiits:button name="btnReset" type="button" onclick="resetValues();" value="Reset" />
   </Td>
   </tr>

	</table>
 
    </hdiits:fieldGroup>
 </div>
 

	
	
	</div>

	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		getParentComponentData();
		initializetabcontent("maintab");
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

