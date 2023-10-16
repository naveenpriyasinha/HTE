<%

try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page session="true" %>
<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>

<script type="text/javascript"  
    src="common/script/commonfunctions.js">
</script>
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
	src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>
	
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="msg" value="${resValue.msg}" ></c:set>
<c:set var="rltBillList" value="${resValue.rltBillList}" ></c:set>
<c:set var="salRevMstVO" value="${resValue.salRevMstVO}" ></c:set>
<c:set var="edit" value="${resValue.edit}" ></c:set>
<c:set var="mandatory" value="${resValue.mandatory}" ></c:set>
<c:set var="salRevId" value="${resValue.salRevId}" ></c:set>

 <script><!--

<%! int flag=1;%>
function beforeSubmit()
{		
		if('${edit}'=='Yes')
		{	
			document.addSalRevMst.action="./hrms.htm?actionFlag=updateSalRevMst";
			document.addSalRevMst.submit();
		}
		else
		{		
			document.addSalRevMst.action="./hrms.htm?actionFlag=insertSalRevMst&edit=N";
			document.addSalRevMst.submit();
		}
}

function chkDateCompare()
{
   /* var sysdate= new Date();
   
    //if(document.addSalRevMst.revPayOutDate.value!=''  )
    //{ 
    	
    	var dateday=sysdate.getDate();
    	var datemonth = sysdate.getMonth()+1;
    	var dateYear= sysdate.getFullYear();
    	
    	if(datemonth<10)
    	{
    		datemonth="0"+datemonth;
    	}    	
    	if(dateday<10)
    	{
    		dateday="0"+dateday;
    	}
    	
    	var dateString = dateday + "/" + datemonth+ "/" +dateYear;    
    	var diff = compareDate(dateString,document.addSalRevMst.revPayOutDate.value);
   
	    if(diff<0)
	    {
	    	alert("Pay-Out Date must be greater than or equal to Current Date");
	    	//document.addSalRevMst.revPayOutDate.value='';    
	    	document.addSalRevMst.revPayOutDate.focus();	
	    	return false;
	    }
	    else */
		    return true;
    //}
	    
    //return true;
}




function calculateAmt()
{
	var miscInst = document.miscRecover.installment.value;
	if(miscInst!=null && miscInst!='')
	{
		if(miscInst==0)
		{
			alert('Installment No must be greater than Zero');
			document.miscRecover.installment.value='';
			document.miscRecover.installment.focus();
			return false;
		}
		var totAmt=document.miscRecover.amount.value;
		var installmentNo = document.miscRecover.installment.value;
		var installmentAmount = totAmt/installmentNo;
		document.miscRecover.installmentAmt.value=installmentAmount;
	}
}

function cmpDate()
{
var flag = 1;


if(document.addSalRevMst.effFromDate.value != "")
{
var effFromDate =document.addSalRevMst.effFromDate.value;
}
else{
alert('Please Enter Effective Frome Date');
flag=0;
return;

}

if( document.addSalRevMst.effToDate.value != ""){
var effToDate =document.addSalRevMst.effToDate.value;
}
else{
alert('Please Enter Effective To Date');

flag=0;
return;
}

	 var diff = compareDate(document.addSalRevMst.effFromDate.value,document.addSalRevMst.effToDate.value);   

	 if(document.addSalRevMst.effToDate.value!=null && document.addSalRevMst.effToDate.value!='')
	 {
	 
	 	var MonthDiff=	datediff(document.addSalRevMst.effFromDate.value,document.addSalRevMst.effToDate.value);
	 	if(diff < 0  || MonthDiff==-1)
  	 	{
   			alert("Effectiv To Date must be greater than Effective From Date");
	   		//document.addSalRevMst.effToDate.value='';
	   		document.addSalRevMst.effToDate.focus();
	   		
   			return false;
  	 	}
	 	else
		 	return true;
	  	
  	 }
	 else
		 return true;
  	 
  	 
  	 
  	

}



function countMonths()
{

if(document.addSalRevMst.effFromDate.value != "" && document.addSalRevMst.effToDate.value != "")
{
var effFromDate =document.addSalRevMst.effFromDate.value;
var effToDate =document.addSalRevMst.effToDate.value;

var y = effFromDate.split("/");
var z = effToDate.split("/");
var Frommonth = null;
var Fromyear = null;
var Tomonth = null;
var Toyear = null;
var diffMonths=null;
var diffYear = null;
date1 = new Date();
date2 = new Date();
diff  = new Date();
  for(var s= 0; s < y.length; s++)
{
	   FromDate = y[0];
	   Frommonth = y[1];
	   Fromyear = y[2];
	                   

}
var newFromDate = Frommonth + "/" + FromDate + "/" + Fromyear;
newFromDatetemp = new Date(newFromDate);
date1.setTime(newFromDatetemp.getTime());


 for(var k= 0; k < z.length; k++)
{
	   ToDate = z[0];
	   Tomonth = z[1];
	   Toyear = z[2];
	                   

}
var newToDate = Tomonth + "/" + ToDate + "/" + Toyear;
newToDatetemp = new Date(newToDate);
date2.setTime(newToDatetemp.getTime());
diff.setTime(Math.abs(date1.getTime() - date2.getTime()));
timediff = diff.getTime();

days = Math.floor(timediff / (1000 * 60 * 60 * 24  ));

diffMonths=Math.round(days/30);
var revFreqMnthPaid = document.addSalRevMst.revFreqMntPaid.value;
if(revFreqMnthPaid > diffMonths)
{
//alert('Please Enter Revision Frequency Months Paid considering Effective Dates!!');
return;
}
var revInstallments = Math.round((diffMonths/revFreqMnthPaid));



document.addSalRevMst.revInstallments.value=revInstallments;
	

}
}










function checkrevSalOrderAvailability()
{

if(document.addSalRevMst.orderName.value == '' || document.addSalRevMst.orderName.value == null)
		{
		alert('Please Enter Order Name');
		document.addSalRevMst.orderName.focus();
		return;
		}
	
	
if(document.addSalRevMst.orderDate.value=='' || document.addSalRevMst.orderDate.value==null)
	{
		alert('Please Enter Order Date');
		
		return;
	
	}
	
	
	var newOrderName=trim(document.addSalRevMst.orderName.value);
		
	var orderDate=document.addSalRevMst.orderDate.value;
	
	if(newOrderName!=""&&orderDate!="")
	{
		try {   
				xmlHttp=new XMLHttpRequest();
   		}
		catch(e){    // Internet Explorer    
			try {
	     		xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");   
    	 	}
	    	catch (e) {
		    	try {
	            	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
    	   		}
			    catch (e) {
			        alert("Your browser does not support AJAX!");        
			        return false;        
			    }
			}
		}
		
		var url = "hrms.htm?actionFlag=checkrevSalOrderAvailability&newOrderName="+newOrderName+"&date="+orderDate;  	


    xmlHttp.onreadystatechange = function() {	

		if (xmlHttp.readyState == 4) {     			
			if (xmlHttp.status == 200) {			
				var XMLDocForAjax=xmlHttp.responseXML.documentElement;
				var orderNameMapping = XMLDocForAjax.getElementsByTagName('orderNameMapping');	

				var flag="true";				
				if(orderNameMapping.length != 0) {		

						if(orderNameMapping[0].childNodes[0].text==flag)
						{			
							if('${edit}'!='Yes')
							{	
								alert("Order Name is already Exists, Please Enter other Name");
								//document.addSalRevMst.orderName.value='';
								//document.addSalRevMst.orderDate.value='';
								document.addSalRevMst.orderName.focus();
							}							
						}
				}
				
			}
		}
	}
	
	xmlHttp.open("POST", encodeURI(url) , false);    
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));	
	return true;
	}
	
return true;
}
	  	
function chkCashPercent()
{
	if(document.addSalRevMst.DACashPercenatage.value > 100)
	{
	alert('Please Enter proper Cash percentage');
	document.addSalRevMst.DACashPercenatage.focus();
	return false;
	}
}	
	
--></script>

<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>


<hdiits:form name="addSalRevMst" validate="true" method="POST"
	action="javascript:beforeSubmit()" encType="multipart/form-data">
	<hdiits:hidden name="lSalRevId" default="${salRevId}"/>

	<div id="tabmenu">
		<ul id="maintab" class="shadetabs" compact="compact">
			<li class="selected"><a href="#" rel="tcontent1"><font size="2"><b><fmt:message key="SRM.addSalRevMst" bundle="${commonLables}"/></b></a></li>
		</ul>
	</div>

	<div class="halftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0">
	<table width= "95%" align= "center"><br>
		
		

	    <tr bgcolor="#386CB7">
				<td class="fieldLabel" colspan="4">
				 <font color="#ffffff">
					<strong>Revision Order Details</strong></font>
				</td>
			</tr><tr></tr>
	   <tr>
			<td  class="Label" width="15%"><b><fmt:message key="SRM.OrderName" bundle="${commonLables}"/></b></td>
			<td><hdiits:text name="orderName" default="${salRevMstVO.revOrderNo}" captionid="SRM.OrderName" bundle="${commonLables}"  validation="txt.isrequired"  maxlength="100"  mandatory="true" size="20" />  </td>
	    	    	    
			<td  class="Label" width ="15%"><b><fmt:message key="SRM.OrderDate" bundle="${commonLables}"/></b></td>
		<td width="60%">	<hdiits:dateTime captionid="SRM.OrderDate" bundle="${commonLables}" name="orderDate" default="${salRevMstVO.revOrderDate}" mandatory="true" validation="txt.isrequired,txt.isdt" /></td>
	    </tr>
	    
	     <tr>
			<td class="Label" width="25%"><b><fmt:message key="SRM.EffFromDate" bundle="${commonLables}"/></b></td>
			<td><hdiits:dateTime captionid="SRM.EffFromDate" default="${salRevMstVO.revEffcFrmDate}" bundle="${commonLables}" name="effFromDate"  mandatory="true" validation="txt.isrequired,txt.isdt" /></TD>	
	   	        
			<td class="Label" width ="25%"><b><fmt:message key="SRM.EffToDate" bundle="${commonLables}"/></b></td>
			<td width="60%"><hdiits:dateTime captionid="SRM.EffToDate" default="${salRevMstVO.revEffcToDate}" bundle="${commonLables}" name="effToDate"  mandatory="true"  validation="txt.isrequired,txt.isdt" /></TD>	
	    </tr>
	    
	    <tr>
			<td class="Label" width="15%"><b><fmt:message key="SRM.revFreqMntPaid" bundle="${commonLables}"/></b></td>
			<td><hdiits:number name="revFreqMntPaid" default="${salRevMstVO.revFreqMthPaid}" captionid="SRM.revFreqMntPaid" bundle="${commonLables}"  validation="txt.isrequired,txt.isnumber"  maxlength="10" readonly="" mandatory="true" size="20" onblur="countMonths()"/>  </td>
	   
	    	<td class="Label" width="15%"><b><fmt:message key="SRM.revInstallments" bundle="${commonLables}"/></b></td>
			<td width="60%"><hdiits:number name="revInstallments" default="${salRevMstVO.revInstallments}" captionid="SRM.revInstallments" bundle="${commonLables}"  validation="txt.isnumber"  maxlength="2"   mandatory="true" size="20"  />  </td>
	    </tr>
	    <tr bgcolor="#386CB7">
				<td class="fieldLabel" colspan="4">
				 <font color="#ffffff">
					<strong><b>Revision Type and PayOutDate</b></strong></font>
				</td>
			</tr><tr></tr>
	   <tr>
		<TD class="fieldLabel" class="Label" width="15%" >
		<b><fmt:message key="SRM.revType" bundle="${commonLables}"/></b>
		</TD>
		<td><hdiits:select name="revType" size="1"  
		               id="revType"
                       captionid="SRM.revType"   
                       mandatory="true" default="${salRevMstVO.rltBillTypeEdp.typeEdpId}"
                       bundle="${commonLables}" onchange="" validation="sel.isrequired">
        <hdiits:option value="">----Select----</hdiits:option>
        
		 <c:forEach var="rltBillList" items="${rltBillList}">
         <hdiits:option value="${rltBillList.typeEdpId}"><c:out value="${rltBillList.edpShortName}(${rltBillList.edpCode})"> </c:out></hdiits:option>
         </c:forEach>
		 </hdiits:select > 
		 </td>
		 
	   
	    
	    
	    
			<td class="Label" width="15%"><b><fmt:message key="SRM.revPayOutDate" bundle="${commonLables}"/></b></td>
			<td width="60%">
			<c:if test="${salRevMstVO.pblIndependentFlg eq null}">
				<hdiits:dateTime captionid="SRM.revPayOutDate" default="${salRevMstVO.revPayOutDate}"  bundle="${commonLables}" name="revPayOutDate"  mandatory="true" showCurrentDate="true" validation="txt.isrequired,txt.isdt" />
			</c:if>
			<c:if test="${salRevMstVO.pblIndependentFlg ne null}">
				<hdiits:dateTime captionid="SRM.revPayOutDate" default="${salRevMstVO.revPayOutDate}"  bundle="${commonLables}" name="revPayOutDate"  mandatory="true" validation="txt.isrequired,txt.isdt" />
			</c:if>
			</TD>	
		</tr>
		
		
			<tr>
				<td class="Label">
				<b><fmt:message key="SRM.revStatus" bundle="${commonLables}"/></b></td>
				<td class="fieldLabel" >
			   	
					<c:if test="${salRevMstVO.revStatus eq null}">
						<hdiits:radio name="rdoActiveFlag"  id="rdoActive" value="1"  default="1" captionid="SRM.Active" bundle="${commonLables}" />
						<hdiits:radio name="rdoActiveFlag"  id="rdoActive" value="0"  captionid="SRM.Deactive" bundle="${commonLables}" />
					</c:if>
					<c:if test="${salRevMstVO.revStatus ne null}">
						<hdiits:radio name="rdoActiveFlag"  id="rdoActive" value="1"  default="${salRevMstVO.revStatus}" captionid="SRM.Active" bundle="${commonLables}" />					
						<hdiits:radio name="rdoActiveFlag"  id="rdoActive" value="0"  default="${salRevMstVO.revStatus}" captionid="SRM.Deactive" bundle="${commonLables}" />
					</c:if>
				</td>
				<td class="Label">	<b><fmt:message key="SRM.pblIndependentFlag" bundle="${commonLables}"/></b></td>				
				<td class="fieldLabel">
						<c:if test="${salRevMstVO.pblIndependentFlg eq null}">
							<hdiits:radio name="rdoPblIndependentFlag"  id="rdoPblIndeFlagYes" value="Y"  captionid="SRM.pblIndependentYes" bundle="${commonLables}" />
							<hdiits:radio name="rdoPblIndependentFlag"  id="rdoPblIndeFlagNo" value="N"  default="N" captionid="SRM.pblIndependentNo" bundle="${commonLables}" />
						</c:if>
						<c:if test="${salRevMstVO.pblIndependentFlg ne null}">
							<hdiits:radio name="rdoPblIndependentFlag"  id="rdoPblIndeFlagYes" value="Y"  default="${salRevMstVO.pblIndependentFlg}" captionid="SRM.pblIndependentYes" bundle="${commonLables}" />					
							<hdiits:radio name="rdoPblIndependentFlag"  id="rdoPblIndeFlagNo" value="N"  default="${salRevMstVO.pblIndependentFlg}" captionid="SRM.pblIndependentNo" bundle="${commonLables}" />
						</c:if>
				</td>
			</tr>
		
		<tr>
			<td><b><fmt:message key="SRM.revReason" bundle="${commonLables}"/></b></td>
			<td><hdiits:textarea cols="40" rows="3" name="revReason" default="${salRevMstVO.revReason}" captionid="SRM.revReason" bundle="${commonLables}"  validation="txt.isrequired"    mandatory="true" maxlength="100"/> </td>
			<td  class="Label" width="15%"><b><fmt:message key="SRM.daCashPercent" bundle="${commonLables}"/></b></td>
			<td><hdiits:number name="DACashPercenatage" default="${salRevMstVO.cashPercentage}" captionid="SRM.daCashPercent" bundle="${commonLables}" mandatory="true" validation="txt.isrequired" floatAllowed="true"  maxlength="100" size="20" onblur="chkCashPercent(this)"/>  </td>
	    </tr>
		
		  
	     <tr>
	     <td colspan="4">	    
	     	<div id="attachExcel" > 	
	     	<b>Order Upload:-</b>
					   <jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
        	    	    	<jsp:param name="attachmentName" value="salRevId" />
            	    		<jsp:param name="formName" value="addSalRevMst" />
	            	    	<jsp:param name="attachmentType" value="Document" />
				    		<jsp:param name="multiple" value="N" />  
				    		<jsp:param name="mandatory" value="${mandatory}"/>              
	    				</jsp:include>
	    	</div>	    	
	</td></tr>	
	</table>
 	</div>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="Lables" scope="page"/> 
 	 	<hdiits:hidden default="getSalRevData" name="givenurl"/>
	<hdiits:jsField jsFunction="checkrevSalOrderAvailability()" name="jsCheckOrder"/>
	<hdiits:jsField jsFunction="cmpDate()" name="jsCmpDate"/>
	<hdiits:jsField jsFunction="chkDateCompare()" name="jsCompareDate"/>
	<jsp:include page="../../core/PayTabnavigation.jsp" />
	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		
		if('${edit}'=='Yes')
		{
			document.getElementById('attachExcel').style.display='none';
		}			
		if("${msg}"!=null&&"${msg}"!='')
		{
			alert("${msg}");
			var url="./hrms.htm?actionFlag=getSalRevData";
			document.addSalRevMst.action=url;
			document.addSalRevMst.submit();
		}
	</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
</body>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

