<%
try {
%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="//WEB-INF/jsp/core/include.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="resources.ess.wll.wll_AlertMessages" var="commonLables1" scope="request"/>

<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script>




//function for twity buttton
function showTable(){	
	
	document.getElementById("previousDetails").style.display='';	
	var row = document.getElementById("previousDetails1");	
	
	if (row.style.display == '')  
	{
		row.style.display = 'none';
		document.getElementById("previousDetails").style.display='none';	
		document.getElementById("img1").src="./images/greenDown.gif";
 			
	}
	else
	{
		 row.style.display = '';
		 document.getElementById("img1").src="./images/redUp1.gif";
		 
 		
	}
}


function checkval()
{
  var z=document.getElementById('Installment');
  
  if(z.value == 'null' || z.value =='')
  {
   
   alert('<fmt:message  bundle="${commonLables1}" key="HR.ESS.Installment"/>');
   
   //alert('Enter Installment amount'); 
   document.getElementById('Installment').select();
   document.getElementById('Installment').focus();
   return;
  }


}




function checkamt()
{

var x=document.getElementById('AmountSanct');

//var amount=document.getElementById('Amount');

var Installment1=document.getElementById('reqamt');

/*
if(eval(x.value) < eval(amount.value))
{
  alert('The required amount should be less than entitled amount');
  document.getElementById('Amount').select();
   document.getElementById('Amount').focus();
  return ; 
}*/
if(eval(x.value) > eval(Installment1.value))
{
     alert('<fmt:message  bundle="${commonLables1}" key="HR.ESS.sanct_req"/>');
  //alert('The Sanctioned Amount should be less than Required amount');
 // document.getElementById('AmountSanct').select();
   document.getElementById('AmountSanct').focus();
  return ;
}
}


function checknum()
{

var x=document.getElementById('AmountSanct');

//var amount=document.getElementById('Amount');

var Installment=document.getElementById('Installment');
/*
if(eval(x.value) < eval(amount.value))
{
  alert('The required amount should be less than entitled amount');
  document.getElementById('Amount').select();
   document.getElementById('Amount').focus();
  return ; 
}*/
if(parseFloat(x) < parseFloat(Installment))
{
   alert('<fmt:message  bundle="${commonLables1}" key="HR.ESS.ist_sanct"/>');
  //alert('The installment should be less than Sanctioned amount');
  document.getElementById('Installment').select();
   document.getElementById('Installment').focus();
  return ;
}
}

function Close1()
{	
	method="POST";
	document.welfareentry.action="./hrms.htm?actionFlag=getDocListOfWorkList";
	//alert(document.welfareentry.action);
	document.welfareentry.submit();
}

function Approve()
{	
	//alert('Value>>>> '+parent.document.getElementById('wfAction').value);
	//alert('Reject  >>>> '+parent.document.getElementById('reject').value); 
	
	if(parent.document.getElementById('reject').value == "no")
	{
	
		var z=document.getElementById('Installment');
		var Installment1=document.getElementById('reqamt');
		var x=document.getElementById('AmountSanct');
		
		if(x.value==""||x.value==null)
		{
			  alert('<fmt:message  bundle="${commonLables1}" key="HR.ESS.Sactioned"/>');
			  document.getElementById('AmountSanct').select();
		      document.getElementById('AmountSanct').focus();
		      return false ;
		}
	 	else if(eval(x.value) > eval(Installment1.value))
		{
		     alert('<fmt:message  bundle="${commonLables1}" key="HR.ESS.sanct_req"/>');
		  	 //alert('The Sanctioned Amount should be less than Required amount');
		 	 document.getElementById('AmountSanct').select();
		   	 document.getElementById('AmountSanct').focus();
		 	 return false;
		}
	   else if(z != null || z != undefined)  
	   {
	     if(z.value =='')
	      {
			   alert('<fmt:message  bundle="${commonLables1}" key="HR.ESS.Installment"/>');
			   //alert('Enter Installment amount'); 
			   document.getElementById('Installment').select();
			   document.getElementById('Installment').focus();
			   return false;
	       }
	       else if(eval(x.value) < eval(z.value))
	       {
			  alert('<fmt:message  bundle="${commonLables1}" key="HR.ESS.ist_sanct"/>');
			  //alert('The installment should be less than Sanctioned amount');
			  document.getElementById('Installment').select();
			  document.getElementById('Installment').focus();
			  return false;
	       }
	   }   
	   return true;
	}
	return true;
	
}

function Return()
{	
	method="POST";
	document.welfareentry.action="./hrms.htm?actionFlag=WelfareReturn";
	//alert(document.welfareentry.action);
	document.welfareentry.submit();
}


function openEMICalc()
{	
	method="POST";
	
	
	url="./hrms.htm?viewName=EmiCalc";
	 window.open(url, "", "width=800,height=600,status=no,resizable=no,top=100,left=400");
	
	//document.welfareentry.action="./hrms.htm?viewName=EmiCalc";
	
	//alert(document.welfareentry.action);
	//document.welfareentry.submit();
}

</script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>		
<c:set var="MyList" value="${resValue.actionList}"> </c:set>
<c:set var="SelfList" value="${resValue.SelfList}"> </c:set>
<c:set var="DepDtsl" value="${resValue.DepDtsl}"></c:set>
<c:set var="closeFlag" value="${resValue.closeFlag}"></c:set>
<c:set var="setFlag" value="${resValue.setFlag}"></c:set>
<c:set var="Approve" value="${resValue.approve}"> </c:set>
<c:set var="Mst" value="${MyList.Mst}"> </c:set>
<c:set var="Dtls" value="${MyList.Dlts}"> </c:set>
<c:set var="Tgl" value="${MyList.Tgl}"> </c:set>
<c:set var="toggleDataList" value="${resValue.toggleDataList}"> </c:set>
<c:set var="nameDataList" value="${resValue.nameDataList}"> </c:set>

<c:set var="toggleFlag" value="${resValue.toggleFlag}"> </c:set>
<c:set var="forwardFlag" value="${resValue.forwardFlag}"> </c:set>
<c:set var="cancelFlag" value="${resValue.cancelflag}"></c:set>



<fmt:setBundle basename="resources.ess.wll.wll" var="commonLables" scope="request"/>



<hdiits:form name="welfareentry" validate="true" method="POST" action="./hdiits.htm?actionFlag=WelfareCommonForwardOrApprove" encType="multipart/form-data">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>
					<hdiits:caption captionid="HR.EIS.Welfare" bundle="${commonLables}" captionLang="single"/>
				</b></a></li>
	</ul>
</div>
	<c:if test="${cancelFlag ne null}">
		<c:if test="${cancelFlag eq 'Y'}">
			<font color="red"><strong><center><fmt:message key="HR.EIS.RequestCancel" bundle="${commonLables}"></fmt:message></center></strong></font>
		</c:if>
	</c:if>

<div class="halftabcontentstyle">
	 <div id="tcontent1" class="tabcontent" tabno="0">

<jsp:include page="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp">
<jsp:param name="empImage" value="N"/></jsp:include>

 <c:forEach var="k" items="${SelfList}">     
	<c:if test="${k != 'Self'}">


<table class="tabtable">


				<tr>
						<td width="100%">
						<font>
							<hdiits:fieldGroup titleCaptionId="HR.EIS.DependentDetails" bundle="${commonLables}" expandable="true"></hdiits:fieldGroup>
						</font>
						</td>
				</tr>
					<tr>
			<td colspan="4">
			<table border="1" cellpadding="0" cellspacing="0" BGCOLOR="WHITE"
				class="TableBorderLTRBN" width="100%">
				<tr>
				

                </tr>
                
   
	<hdiits:table id="show1" width="100%">
    <tr></tr>
		<hdiits:tr>
		<td width="25%"><b><hdiits:caption captionid="HRMS.Dep.Name" bundle="${commonLables}"/>:</b></td>
	<td width="25%"><c:forEach var="resValue121" items="${DepDtsl}"><c:out value="${resValue121[1]}"></c:out></c:forEach></td>
	
	<td width="25%"><b><hdiits:caption captionid="HRMS.Dep.Relation" bundle="${commonLables}"/>:</b></td>
	
	
	<td width="25%"><c:forEach var="resValue121" items="${DepDtsl}"><c:out value="${resValue121[3]}" ></c:out></c:forEach></td>
		
		</hdiits:tr>
		
		<hdiits:tr>
		<td width="25%"><b><hdiits:caption captionid="HRMS.Dep.DateOfBirth" bundle="${commonLables}"/>:</b></td>
	<td width="25%"><c:forEach var="resValue121" items="${DepDtsl}"><c:out value="${resValue121[4]}" ></c:out></c:forEach></td>
	
	<td width="25%"></td>
	<td width="25%"></td>
		
		</hdiits:tr>
		
		</hdiits:table>
		


  </c:if>
  </c:forEach>
  
  
 
	
	
	
	
	
	
	<table id="previousDetails" class="tabtable" style="display: none" style="border-collapse: collapse;">
	
	<tr>
		<td colspan="6" align="center">
		<font>
			<hdiits:fieldGroup titleCaptionId="HR.EIS.PreviousDetails" bundle="${commonLables}" expandable="true"></hdiits:fieldGroup>
		</font>
		</td>
	</tr>
	
	</table>
<br>
<table id="previousDetails1" class="tabtable" style="display: none" border="1" style="border-collapse: collapse;" borderColor="BLACK">
	
	<c:choose>
<c:when test="${toggleFlag eq 1 }">

		<tr colspan="4">
				<td width="10%" class=fieldLabel align="center" bgcolor="#C9DFFF">

					<b><hdiits:caption captionid="HR.EIS.userId" bundle="${commonLables}"/> </b>
				</td>
				
				<td width="10%" class=fieldLabel align="center" bgcolor="#C9DFFF">

					<b><hdiits:caption captionid="HR.EIS.SanctionAmt" bundle="${commonLables}"/> </b>
				</td>
				
				<td width="10%" class=fieldLabel align="center" bgcolor="#C9DFFF">

					<b><hdiits:caption captionid="HR.EIS.SanctionInstAmt" bundle="${commonLables}"/> </b>
				</td>
				
				<td width="10%" class=fieldLabel align="center" bgcolor="#C9DFFF">

					<b><hdiits:caption captionid="HR.EIS.Date" bundle="${commonLables}"/> </b>
				</td>
				

		</tr>
            	
            	
			<c:forEach var="toggleDataList" items="${toggleDataList}" varStatus="x">
			<c:set var="nameDataList" value="${resValue.nameDataList[x.index]}"/>
			
			<tr colspan="4">
	
	
  				<td width="10%">
  					<b><c:out value="${nameDataList.empFname}"/></b> 
  					&nbsp;
  					<b><c:out value="${nameDataList.empLname}"/></b> 
  				</td>
  			
  			
  				
    			
				<c:choose>
				<c:when test="${toggleDataList.sanctAmt eq 0 }">
				<td width="10%">
				-
				</td>
				</c:when>
				<c:otherwise>
				<td width="10%">
    				<c:out value="${toggleDataList.sanctAmt}"/>
    			</td>
				</c:otherwise>
				</c:choose>


				<c:choose>
				<c:when test="${toggleDataList.sanctInstAmt eq 0 }">
				<td width="10%">
				-
				</td>
				</c:when>
				<c:otherwise>
				<td width="10%">
				<c:out value="${toggleDataList.sanctInstAmt}"/>
				</td>
				</c:otherwise>
				</c:choose>
				 
				
	
<fmt:formatDate value="${toggleDataList.updatedDate}" pattern="dd/MM/yyyy"
	dateStyle="medium" var="date" />
<c:set var="date" value="${date}" ></c:set>

				<td width="10%">
				<c:out value="${date}"/>
				</td>


				</tr>
		

			</c:forEach>
		</c:when>
				<c:otherwise>	
		
		<tr>
		<td>
		
			<b><hdiits:caption captionid="HR.EIS.NORecord" bundle="${commonLables}" id="NORecord"/></b></td>
		</tr>
	</c:otherwise>
				</c:choose>
</table>
		
<BR>
		
	
	
	
	
	
	
<table class="tabtable">


<c:forEach var="i" items="${MyList.Dlts}">
			<hdiits:hidden name="RequestId" default="${i.welfareDltsId}" />
		
		</c:forEach>

<tr >
	<td colspan="6" align="center">
	<font>
		<hdiits:fieldGroup titleCaptionId="HR.EIS.WelfareApp" bundle="${commonLables}"></hdiits:fieldGroup>
	</font>
	</td>
</tr>
<tr>
	<td width="25%"><b><hdiits:caption captionid="HR.EIS.WelfareSch" bundle="${commonLables}"/>:</b></td>
	<td width="25%">
		
		<c:forEach var="j" items="${Mst}">
		 <c:forEach var="i" items="${j}">
			<c:out value="${i.welfareScheme}" ></c:out>
			
		</c:forEach>
	    </c:forEach>
	</td>
	
	
			<td width="25%"><b><hdiits:caption captionid="HR.EIS.AmountEnt" bundle="${commonLables}" />:</b></td>
    
    <td width="25%">
		
		<c:forEach var="i" items="${MyList.Dlts}">
			<c:out value="${i.welfareMaxAmtDtls}" ></c:out>
			<hdiits:hidden name="reqamt" id="reqamt" default="${i.welfareMaxAmtDtls}" />
		
		</c:forEach>
	</td>
    
    
</tr>



<tr>
	<td width="25%"><b><hdiits:caption captionid="HR.EIS.SchType" bundle="${commonLables}"/>:</b></td>
	 <td width="25%">
		<c:forEach var="k" items="${Mst}">
		<c:forEach var="i" items="${k}">
			<c:out value="${i.welfareType}" ></c:out>
		</c:forEach>
		</c:forEach>
	</td>
	
     
	<td width="25%"><b><hdiits:caption captionid="HR.EIS.SchDes" bundle="${commonLables}" />:</b></td>
	<td width="25%">
		<c:forEach var="l" items="${Mst}">
		<c:forEach var="i" items="${l}">
			<c:out value="${i.welfareDes}" ></c:out>
		</c:forEach>
		</c:forEach>
	</td>
	
	
</tr>
<tr>
	<td width="25%"><b><hdiits:caption captionid="HR.EIS.AmountReq" bundle="${commonLables}" />:</b></td>
	<td width="25%">
	
		<c:forEach var="i" items="${MyList.Dlts}">
			<c:out value="${i.welfareAmtReq}" ></c:out>
		
		</c:forEach>
	</td>
	
	
	<td width="25%"><b><hdiits:caption captionid="HR.EIS.Reason" bundle="${commonLables}" />:</b></td>
	<td width="25%">
		
		<c:forEach var="i" items="${MyList.Dlts}">
			<c:out value="${i.welfareAllow}" ></c:out>
	
		</c:forEach>
	</td>
</tr>
<c:forEach var="k" items="${Mst}">
		<c:forEach var="i" items="${k}">
			
		
	<c:if test="${i.welfareType!='Assistance'}"> 
<tr>
	<td width="25%"><b><hdiits:caption captionid="HR.EIS.Installment" bundle="${commonLables}" id="Installment"/>:</b> </td>
	<td width="25%">

		 <c:forEach var="i" items="${MyList.Dlts}">
		
			<c:out value="${i.welfareInstAmt}" ></c:out>
			
		</c:forEach>
	 
	   
	</td>
	
	<td width="25%"><b><hdiits:caption captionid="HR.EIS.RateofInt" bundle="${commonLables}" />:</b></td>
	<td width="25%">
		
		<c:forEach var="i" items="${MyList.Dlts}">
			<c:out value="${i.welfareRateInt}" ></c:out>
		</c:forEach>
	</td>
	
</tr>
</c:if>
</c:forEach>
</c:forEach>

<tr>
	 	
	<c:if test="${setFlag!='Y' }">
	<td width="25%"><b><hdiits:caption captionid="HR.EIS.AmountSanct" bundle="${commonLables}" id="AmountSanct"/>:</b></td>
	
	<td width="25%">
	
	<c:if test="${Approve=='Y'}">
	<c:forEach var="i" items="${MyList.Dlts}">
    <c:out value="${i.welfareSanctAmt}" ></c:out>
	</c:forEach>
	</c:if>
	
	 
    <c:if test="${Approve!='Y'}">
	 <hdiits:number  captionid="Installment" name="AmountSanct" mandatory="true" id="AmountSanct" validation="txt.isrequired,txt.isnumber" maxlength="7" onblur="checknum()"  />
		<hdiits:image id="img1" source="./images/greenDown.gif" onclick="showTable();"> </hdiits:image> 
	</c:if>
	
	</td>
	<c:forEach var="k" items="${Mst}">
		<c:forEach var="i" items="${k}">
			
		
	<c:if test="${i.welfareType!='Assistance'}"> 
	 
	<td width="25%"><b><hdiits:caption captionid="HR.EIS.SacInstallment" bundle="${commonLables}" id="Installment"  />:</b></td>
    <td width="25%">
    <c:if test="${Approve=='Y'}">
    
	<c:forEach var="i" items="${MyList.Dlts}">
	
    <c:out value="${i.welfareSanctInstAmt}" ></c:out>
    
	</c:forEach>
	</c:if>
    <c:if test="${Approve!='Y'}">
	<hdiits:number captionid ="Installment" name="Installment" id="Installment" validation="txt.isrequired,txt.isnumber" onblur="checkamt()" mandatory="true"/>
     
     </c:if> 
     </c:if>
     </c:forEach>
	</c:forEach>
    </td>
    </c:if>
    </tr>
  <tr>
<td width="25%">
</td>
<td width="25%" colspan ="2" align ="center"><a onclick="openEMICalc()" href="#"><hdiits:caption id ="" captionid="HR.EIS.EmiCal1" bundle="${commonLables}"/></a></td>

<td width="25%"></td>
</tr>   

    
    
    
    
<tr><td colspan ="4" align= "center">
               <jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
			<jsp:param name="attachmentName" value="welfareAttach" />
			<jsp:param name="formName" value="welfareentry" />
			<jsp:param name="attachmentType" value="Document" />
			<jsp:param name="multiple" value="N" />                
			</jsp:include></td> </tr>
<tr><td colspan ="4" align= "center">
   
	
	
	
		
	
	
 
  
  
 <c:if test="${setFlag=='Y' || closeFlag=='true'}">
<hdiits:button name="Closey1" type="button" caption="Close" onclick="window.close()"  />
 </c:if>
 
 </tr>
	
</table>

<script>
				document.getElementById('target_uploadwelfareAttach').style.display='none';
				document.getElementById('formTable1welfareAttach').firstChild.firstChild.style.display='none';
	</script>


 <hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" default="${resValue.reqId}"/>	


<hdiits:jsField jsFunction="Approve()" name="Approve"/>
<jsp:include page="//WEB-INF/jsp/core/tabnavigation.jsp" >
	<jsp:param name="disableSubmit" value="true"/>
</jsp:include>

<hdiits:validate  locale='<%=(String)session.getAttribute("locale")%>' />

	

	</div> </table></td>
	</tr>
	</table>
	</div>
	</div>
	<script type="text/javascript">
		initializetabcontent("maintab")
	</script>
	


</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>







	