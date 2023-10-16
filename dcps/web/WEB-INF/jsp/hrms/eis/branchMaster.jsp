<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/Address.js"/>">
</script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/commonUtils.js"/>">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
	
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
	
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>	
<script>
function clearBranch()
{
   document.frmBF.txtBranchName.value='';
}
function trim(s) 
{
// Remove leading spaces and carriage returns
//  s = s.replace(/&nbsp;/gi,'');

 while ((s.substring(0,1) == ' ') || (s.substring(0,1) == '\n') || (s.substring(0,1) == '\r'))
  {
    s = s.substring(1,s.length);   
  }

  // Remove trailing spaces and carriage returns

  while ((s.substring(s.length-1,s.length) == ' ') || (s.substring(s.length-1,s.length) == '\n') || (s.substring(s.length-1,s.length) == '\r'))
  {
    s = s.substring(0,s.length-1);
  }
  return s;
}

function chkBranchName(val)
{ 
 if(document.frmBF.cmbBankName.value=='Select')
 {
  alert('Please Enter Bank Name');
  document.frmBF.cmbBankName.focus();
 }
 else
 {
  
  if(!trim(document.frmBF.txtBranchName.value) == '')

 
  var name = document.frmBF.txtBranchName.value;
  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&branchName='+ document.frmBF.txtBranchName.value + '&bank_id=' +document.frmBF.cmbBankName.value;
		  var actionf="chkBranchName";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
         //alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=chk_branchName;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);	
	
 }
}


function chk_branchName()
{
if (xmlHttp.readyState==complete_state)
 { 						
			
					var XMLDoc=xmlHttp.responseXML.documentElement;			
                    var namesEntries = XMLDoc.getElementsByTagName('branch-name');
   //                 alert('Length ' + namesEntries.length + ' ' + namesEntries[0].childNodes[0].text);
                    if(namesEntries.length != 0 && namesEntries[0].childNodes[0].firstChild.nodeValue!='0')
                    {                    
                     alert('Branch Name already exists.');
                     document.frmBF.txtBranchName.value = '';
                     document.frmBF.txtBranchName.focus();
                    }
  }
}
</script>

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
 <c:set var="bankList" value="${resValue.bankList}" />






<hdiits:form name="frmBF" validate="true" method="POST"
	action="hrms.htm?actionFlag=insertBranchData&edit=N" encType="multipart/form-data">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="BR.INSERTBRANCHINFO" bundle="${commonLables}"/> </b></a></li>
	</ul>
	</div>
	
	

	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >  

   <TABLE  width="80%"   align="center"><br>
 	<TR>  									
			<td  width="2%"></td>
			<TD align="left" width="8%"> 
				<b><fmt:message key="BM.bankName" bundle="${commonLables}"/> </b>
			</TD>			
			<TD width="20%">
			 <hdiits:select name="cmbBankName" sort="true" validation="sel.isrequired" id="bankID" size="1"  mandatory="true" 
			caption="Bank Name" onchange="clearBranch()">
		       <hdiits:option value="Select">-------------------Select-------------------</hdiits:option>
		
		       
		     <c:forEach items ="${resValue.bankList}" var="list">
			<hdiits:option value="${list.bankId}"> ${list.bankName} </hdiits:option>
			</c:forEach>  
			</hdiits:select>		       
	</TD>
			
	<td  width="2%"></td>
	   <TD  align="left" width="8%">
			 <b><fmt:message key="BR.NAME" bundle="${commonLables}"/></b>
	  </TD>
			<TD>		
	 <hdiits:text	name="txtBranchName" validation="txt.isrequired" mandatory="true" caption="Branch Name"  id="branchName" size="30" maxlength="40"
				 onblur="chkBranchName(this)" onkeypress="if(event.keyCode == 13) event.returnValue = false;"/>	 
			</TD>
	</TR>
	<tr></tr><tr></tr>
	
	
	
	
	<TR>			
		<td  width="2%"></td>
		<TD align="left" width="8%" > <b><fmt:message key="BR.CODE" bundle="${commonLables}"/></b></TD>
		<TD  width="20%"> <hdiits:number name="txtBranchCode" mandatory="true"  caption="Code" maxlength="20"   size="30" />	
		</td>
		
	
		
		<td  width="2%"></td>
		<TD  align="left"  width="8%"> <b><fmt:message key="BR.ADD" bundle="${commonLables}"/></b></TD>
		<TD>
			<hdiits:textarea rows="3" cols="32" name="txtBranchAdd" maxlength="190" caption ="Address"  >
			</hdiits:textarea>
		</TD>
		
		
			<td  width="2%"></td>
			<TD  align="left" width="8%">
			<b><fmt:message key="BR.MICR" bundle="${commonLables}"/></b>
			</TD>
			
		<TD  width="20%">
		
		 <input type="text"	name="txtMicrCode" id="txtMicrCode" maxlength="20" />
	   
	   </TD>
	   
	   
	   
	   
	</tr>
	
	<tr></tr><tr></tr>
		
	
	<TR>
			
				
		
	</TR>

	</table> 


    
 <br/><br/><br/>
 

	
 
<fmt:setBundle basename="resources.eis.eis_common_lables" var="Lables" scope="page"/>
<hdiits:hidden default="getBranchView" name="givenurl"/> 

	<jsp:include page="../../core/PayTabnavigation.jsp" />
	<br/><br/><br/>
	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		document.frmBF.cmbBankName.focus();
		if("${msg}"!=null&&"${msg}"!='')
		{
			alert("${msg}");
			var url="./hrms.htm?actionFlag=getBranchView";
			document.frmBF.action=url;
			document.frmBF.submit();
		}
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

