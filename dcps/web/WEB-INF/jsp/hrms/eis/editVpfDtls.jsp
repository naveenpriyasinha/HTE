<%

try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page session="true" %>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
	
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="enLables" scope="page"/>

<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="/script/hrms/eis/dateFormat.js"></script>
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="actionList" value="${resValue.hrPayVpfDtls}" > </c:set>
<c:set var="orgEmpMst" value="${resValue.orgEmpMst}" > </c:set>
<c:set var="pfAmount" value="${resValue.pfAmount}" > </c:set>
<c:set var="maxPfAmount" value="${resValue.maxPfAmount}" > </c:set>
<%-- <c:set var="intialBasic" value="${resValue.intialBasic}" > </c:set>
<c:set var="daAmount" value="${resValue.daAmount}" > </c:set> --%>
<c:set var="msg" value="${resValue.msg}" ></c:set> 
<c:set var="empId" value="${resValue.empId}" ></c:set>
<c:set var="empAllRec" value="${resValue.empAllRec}" ></c:set>
<c:set var="updatePaybillFlg" value="${resValue.updatePaybillFlg}" ></c:set>
<c:set var="paybillMonth" value="${resValue.paybillMonth}" ></c:set>
<c:set var="paybillYear" value="${resValue.paybillYear}" ></c:set>
<c:set var="FromBasicDtlsNew" value="${resValue.FromBasicDtlsNew}" ></c:set>
<c:set var="otherId" value="${resValue.otherId}" ></c:set>
${hrPayVpfDtls}
 <c:forEach var="hrPayVpfDtls" items="${hrPayVpfDtls}">
 <c:out value="hrPayVpfDtls.zerovpfMonths"></c:out></c:forEach>
<%String editMode=request.getParameter("edit");
 session.putValue("edit",editMode);
 %>
<script>
function chkKey(e){	
	if(e.keyCode=='13')
		return false;
	else	
		return true;
}
function checkAvailability()
{

if(document.getElementById('zerovpfMonths').checked!=true)
{

	var newVpf=document.getElementById('vpfAmount').value;
//alert("new vpf "+newVpf);
	if(newVpf=='')
	return false;
	else
	{
	//alert("else");
	
	var initialBasic = "${intialBasic}";
	//var da = "${daAmount}";	
	//var daAmount = parseFloat(da);
	//var currentBasic = parseFloat(initialBasic);
	//var dpAmount = currentBasic / 2;
	//var maxAmount = currentBasic + dpAmount +daAmount;	
	var maxPfAmount = "${maxPfAmount}";
	var maxAmount = parseFloat(maxPfAmount);
	var pfValue = "${pfAmount}";
	var pfVal = parseFloat(pfValue);
	//alert("this is testing again");	
	if(newVpf < pfVal){
		alert("VPF Amount must be greater then the PF "+pfVal);
		document.frmBF.vpfAmount.value='';
		document.frmBF.vpfAmount.focus();
		return false;
	}
	else if(newVpf > maxAmount){
			 alert("VPF Amount must be Less then "+maxAmount);
			 document.frmBF.vpfAmount.value='';
		     document.frmBF.vpfAmount.focus();
		     return false;
		}
	else if (newVpf%10==0)
	{
		//alert("this is modulus");
		return true;
	}
	else
	{
		if(confirm("Do you want to Round Off Your VPF Amount?"))
		{
		newVpf=Math.abs(newVpf)+Math.abs(10-(newVpf%10));			            	
		if(	newVpf >	maxAmount)
		{
			newVPF=Math.abs(newVPF)-Math.abs(newVPF%10);
			document.frmBF.vpfAmount.value=newVPF;
		}	
		else
		{            	
		document.frmBF.vpfAmount.value=newVpf;
		}
		}
    	return true;
	}
		}
		}
		else
		{
		//alert("from else");
		return true;
		}

}

function dateDifferent1()
{
		var CURDATE = new Date();
		var t1=new Date();
		var CURYear = CURDATE.getYear();
		var CURMonth = CURDATE.getMonth();
		var CURDate = CURDATE.getDate();
		var CURTime = CURDATE.getTime();
		

t1="${orgEmpMst.empSrvcExp}";

//var t2=document.frmBF.endDate.value;
var _Diff=0;
   //Total time for one day
        var one_day=1000*60*60*24; 
//Here we need to split the inputed dates to convert them into standard format

        var x=t1.split("-");
        var y=x[2].split(" ")


      //  var date1=new Date(y[0],(x[1]-1),x[0]);
    /*    var date1=new Date(x[0],(x[1]-1),y[0]);
  		alert(date1);
        var date2=new Date(CURYear,CURMonth,CURDate);
        alert(date2); */
        
        var date1=new Date(x[0],(x[1]-1),y[0]);
 
        var date2=new Date(CURYear,CURMonth,CURDate);

        var month1=x[1]-1;
        
        var month2=CURMonth;
        
        //Calculate difference between the two dates, and convert to days
               
        _Diff=Math.ceil((date1.getTime()-date2.getTime())/(one_day)); 
        
        if(_Diff<=180 && _Diff>90 && _Diff!=0)
        {
	//        alert("_Diff"+_Diff);
      //  alert(document.getElementById('zerovpfMonths').checked);
        if(document.getElementById('zerovpfMonths').checked!=true){
        //alert('ll');
        document.getElementById('vpfcheck').style.display='';
        return false;
		}else
		{
		alert('User Stops His VPF so you Cannto Change VPF Value'); 
		document.getElementById('vpfAmount').readOnly=true;
	
		if("${empAllRec}"=='false')
		{
        	 	var url="./hrms.htm?actionFlag=getVPFView";
         		document.frmBF.action=url;
				document.frmBF.submit(); 
		}
		}
       
        }
        else if(_Diff<90 && _Diff!=0)
        {
            document.frmBF.zerovpfMonths.checked=true;
            document.getElementById('vpfAmount').readOnly=true;
          
        alert("Your Retirement Date is within 3 Months so You can not enter VPF");
		if("${empAllRec}"=='false')
		{
           		var url="./hrms.htm?actionFlag=getVPFView";
       

			document.frmBF.action=url;
			document.frmBF.submit(); 
			}
			
			return false;
        }
return true;
}

function submit1()
{
     var uri = "./hrms.htm?actionFlag=";
  if("${empAllRec}"=='true')
    var url = uri + "insertUpdateVPFDtls&edit=Y&vpfId=${actionList.payVpfId}&empAllRec=true";
  else
   var url = uri + "insertUpdateVPFDtls&edit=Y&vpfId=${actionList.payVpfId}&empAllRec=false&paybillMonth=${paybillMonth}&paybillYear=${paybillYear}&&updatePaybillFlg=${updatePaybillFlg}&FromBasicDtlsNew=${FromBasicDtlsNew}&otherId=${otherId}";

//alert(url);
 document.frmBF.action = url;
 
// alert(url);
 document.frmBF.submit();
 
 

}
function reloadingParent()
{
        window.parent.opener.location="./hrms.htm?actionFlag=getOtherDataMerged&otherId=${otherId}&edit=Y&empAllRec=false&MergedScreen=YES&PreviewBill=YES";
        window.close();
}
</script>



<hdiits:form name="frmBF" validate="true" method="POST"
	action="javascript:submit1()" encType="multipart/form-data">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs" compact="compact">
			<li class="selected"><a href="#" rel="tcontent1"><font size="2"><b><fmt:message key="VPF.updateMaster" bundle="${enLables}"/></a></li>
	</div>
<table><tr><td><hdiits:hidden name="updatePaybillFlg" default="${updatePaybillFlg}" />
			     <hdiits:hidden name="paybillMonth" default="${paybillMonth}" />
			     <hdiits:hidden name="paybillYear" default="${paybillYear}" />
</td></tr></table>	
	<div class="halftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0">
	<table width="50%" align="center"><br>
	    <tr>
			<td>
			     <c:if test="${updatePaybillFlg eq 'y'}" >
			     
			     <a href="./hrms.htm?actionFlag=fillPaybillData&paybillYear=${paybillYear}&paybillMonth=${paybillMonth}&updatePaybillEmpId=${orgEmpMst.empId}&searchData=y">Back</a>
			     </c:if>
			     
		</tr>
		<tr>
			<td><font size="2"><b><fmt:message key="OT.empName" bundle="${enLables}"/> </b> </td>
			<td><hdiits:text name="empName" caption="empId" default="${orgEmpMst.empFname} ${orgEmpMst.empMname} ${orgEmpMst.empLname}" validation="txt.isrequired" readonly="true" /></td>
			
		</tr>
		<tr >
			<td><font size="2"><b><font size="2"><b><fmt:message key="VPF.vpfAmount" bundle="${enLables}"/></b> </font></td>
			<td><hdiits:number name="vpfAmount" id="vpfAmount" style="text-align:right" caption="vpfAmount"  default="${actionList.vpfAmt}" validation="txt.isrequired,txt.isnumber" onblur="return checkAvailability()"  mandatory="true" maxlength="6"/></td>
			<td> <hdiits:jsField name="validateVPF" jsFunction="checkAvailability()"/>
		</tr>
		
		<tr>
				<td colspan="2"><jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
        	    	    	<jsp:param name="attachmentName" value="vpfAttachment" />
            	    		<jsp:param name="formName" value="frmBF" />
	            	    	<jsp:param name="attachmentType" value="Document" />
				    		<jsp:param name="mandatory" value="N"/>   
				   </jsp:include></td>
		</tr>
		
		<tr id="vpfcheck" style="display:none">
			<td><font size="2"><b><font size="2"><b><fmt:message key="VPF.ZEROVPFMONTHS" bundle="${enLables}"/></b> </font></td>
			<td>
			<c:choose>

			<c:when test="${actionList.zerovpfMonths ne 3}">
				<input type="checkbox" name="zerovpfMonths"  value="1" checked="checked" readonly="readonly">
			</c:when>
			<c:otherwise>
				<input id="zerovpfMonths"  type="checkbox" name="zerovpfMonths"  value="0">
			</c:otherwise>
			</c:choose>
			</td>
			<td> &nbsp;</td>
		</tr>
	</table>
 	</div>
 	 		  	 	<c:choose>
<c:when test="${empAllRec eq true }">
<hdiits:hidden default="getVPFView&Employee_ID_EmpLoanSearch=${empId}&empAllRec=Y" name="givenurl"/>
</c:when>
<c:otherwise>
<hdiits:hidden default="getVPFView" name="givenurl"/>
</c:otherwise>
</c:choose>
<fmt:setBundle basename="resources.payroll.payrollLables" var="Lables" scope="request"/>
	<jsp:include page="../../core/PayTabnavigation.jsp" /></div>
	<br><br>
	 <c:if test="${FromBasicDtlsNew ne null && not empty FromBasicDtlsNew}">
		<center>
		<hdiits:button type="button" name="closewindow" value="closewindow" caption="Close Window" 
		id="closewindow" captionid="close window" onclick="window.close()"/>
		</center>
	</c:if>
	
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")

		if("${msg}"==null||"${msg}"=='')
		{
			if(dateDifferent1()==true)
			{
				document.frmBF.vpfAmount.focus();
			}
		}
		if("${msg}"!=null&&"${msg}"!='')
		{
					alert("${msg}");
					if("${msg}"!=null&&"${msg}"!='' && "${empAllRec}"=='added')
					{
						var url="./hrms.htm?actionFlag=getVPFView&Employee_ID_EmpLoanSearch=${empId}&empAllRec=Y";
					}
					else if("${FromBasicDtlsNew}" != null && "${FromBasicDtlsNew}" == "YES")
					{
						reloadingParent();
					}
					else
					var url="./hrms.htm?actionFlag=getVPFView";
					document.frmBF.action=url;
					document.frmBF.submit();
		}
		</script>
	<hdiits:validate controlNames="text"
		locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

	