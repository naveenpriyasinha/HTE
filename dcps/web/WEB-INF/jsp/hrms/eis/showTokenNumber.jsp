
<%
try {
%>

<%@ include file="../../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="java.util.List"%>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/Address.js"/>">
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<fmt:setBundle basename="resources.eis.eis_common_lables"	var="adminTokenNumberLabel" scope="request" />
<fmt:setBundle basename="resources.Payroll" var="commonLable" scope="request"/>
<fmt:message var="payBill" key="paybillTypeId" bundle="${commonLable}" scope="request"> </fmt:message>
<fmt:message var="arrearBill" key="arrearbillTypeId" bundle="${commonLable}" scope="request"> </fmt:message>
<fmt:message var="supplPaybillFirst" key="supplPaybillFirst" bundle="${commonLable}" scope="request"> </fmt:message>
<fmt:message var="supplPaybillSecond" key="supplPaybillSecond" bundle="${commonLable}" scope="request"> </fmt:message>
<fmt:message var="supplPaybillThird" key="supplPaybillThird" bundle="${commonLable}" scope="request"> </fmt:message>
<fmt:message var="multipleMonthSupplBill" key="multipleMonthSupplBill" bundle="${commonLable}" scope="request"> </fmt:message>
<fmt:setBundle basename="resources.onlinebillprep.CommonAlerts" var="onlinebillcmnAlerts" scope="application"/>
<fmt:message var="created" key="created" bundle="${commonLable}" scope="request"> </fmt:message>
<fmt:message var="approved" key="approved" bundle="${commonLable}" scope="request"> </fmt:message>
<%--added by vaibhav tyagi: start --%>
<fmt:message var="rejected" key="rejected" bundle="${commonLable}" scope="request"> </fmt:message>
<%--added by vaibhav tyagi: start --%>

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="monthList" value="${resValue.monthList}" ></c:set>
<c:set var="yearList" value="${resValue.yearList}" ></c:set>
<c:set var="dataList" value="${resValue.DataList}" ></c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>
<c:set var="curmonth" value="${resValue.curmonth}" ></c:set>
<c:set var="curyear" value="${resValue.curyear}" ></c:set>
<c:set var="billNosList" value="${resValue.BillList}" ></c:set>
<c:set var="curbill" value="${resValue.curbill}" ></c:set>
<c:set var="billtype" value="${resValue.billtype}" ></c:set>
<c:set var="currBillStatus" value="${resValue.currBillStatus}" ></c:set>

<c:set var="msgFwd" value="${resValue.msgFwd}" ></c:set>
<c:set var="msgDiscard" value="${resValue.msgDiscard}" ></c:set>

<c:set var="status" value="${resValue.status}" ></c:set>
<c:set var="month" value="${resValue.month}" ></c:set>
<c:set var="year" value="${resValue.year}" ></c:set>
<c:set var="curYear" value="${resValue.curYear}" ></c:set>
<c:set var="curMonth" value="${resValue.curMonth}" ></c:set>
<c:set var="month1" value="${resValue.from_Month}" ></c:set>
<c:set var="year1" value="${resValue.from_Year}" ></c:set>

<%
List dataList = (List) pageContext.getAttribute("dataList");
int size = 0;
if(dataList != null)
{
	size = dataList.size();
}
pageContext.setAttribute("listSize",size);

%>

<style>
input[type="button" i]:disabled {
    pointer-events: none;
    opacity: 0.6;
}
</style>


<script type="text/javascript">

 
if('${msgFwd}'!= null && '${msgFwd}'!='')
{
	alert('${msgFwd}');
	 
}
</script>





<script type="text/javascript"><!--

if('${status}'!= null && '${status}'!='')
{
	//alert('${month}');
	//alert('${year}');
	//alert('${month1}');
	//alert('${year1}');


	if('${status}'==1)
	
	alert("Change Statement and Payroll Generated Successfully.");
	if('${month}'!=0 ||'${month}'<0)
	{
	//	alert("inside if");
	 window.location= "./hdiits.htm?actionFlag=showTokenNumber&Month=" + '${month}' + "&Year=" + '${year}' ;
	}
	else
	{
	//	alert("inside else");
		window.location= "./hdiits.htm?actionFlag=showTokenNumber&Month=" + '${month1}' + "&Year=" + '${year1}' ;
	}
	if('${status}'==2)
		alert("No Employee Found to generate the Bill.");//No Post/Employee found to generate
	if('${status}'==11)
		alert("Arrear bill generated successfully.");
	if('${status}'==12)
		alert("No Arrear is active in this Month.");
	if('${status}'==13)
		alert("The Arrear is already paid for this month.");
	if('${status}'==99)
		alert("Paybill Already Generated.");
	
	
}


	var SVBILL_SELECTBILL = "<fmt:message key='SVBILL.SELECTBILL' bundle='${onlinebillcmnAlerts}'></fmt:message>";
	function validateForm1()
	{
		var bill = document.TokenNumber.billNo.value;//this will give you the bill sub head id of selected bill number
		 if('${listSize}' == 0)
		 {
	 	 	alert("No Records to Save ! ! !");
	 	 	return false;
		 }
		 document.TokenNumber.action = "./hdiits.htm?actionFlag=updateTokenNumber&listSize=${listSize}&billtype=${billtype}&currBillStatus=${currBillStatus}&month=${curmonth}&year=${curyear}&billNo="+bill+"&Flag="+'S';//S = Save
		 document.TokenNumber.submit();
		 document.TokenNumber.updatebtn.disabled=true; 
	}
	function resetForm1()
	{
		document.getElementById("tokenview").click();
		
	}
	function ShowTokenDetails()
	{
		var Month=document.TokenNumber.selMonth.value; 
		var Year=document.TokenNumber.selYear.value;
		var bill = document.TokenNumber.billNo.value;//this will give you the bill sub head id of selected bill number
		//alert(" bill number selected " +document.TokenNumber.billNo.options[document.TokenNumber.billNo.selectedIndex].text ); // this will directly give you the selected bill number
		var billtype = document.TokenNumber.selType.value;
		var billStatus = document.TokenNumber.selStatus.value;
		if(!Month)
		{
				alert("Please Select The Month");
				return false;
		}
		if(!Year)
		{
			alert("Please Select The Year");
			return false;
		}		
		document.TokenNumber.action = "./hdiits.htm?actionFlag=showTokenNumber&Month=" + Month + "&Year=" + Year + "&billNo="+bill+"&billtype="+billtype+"&billStatus="+billStatus ; 
		document.TokenNumber.submit();
	}
	function chktokenNumber(token, j)
	{
		var currenttoken = document.getElementById("tokenid" + j).value;
		var oldtokenval = document.getElementById("oldval" + j).value;
        var len=currenttoken.length;
		str="0123456789\n"; 
		for(var i=0;i<len;i++)
		{
			 if((str.indexOf(currenttoken.charAt(i))) == -1)
		     {
		       if(currenttoken.charAt(i) != '\n' && currenttoken.charAt(i) != '\r')
		       {
		        alert("Please Enter Proper Token Number");
		        document.getElementById("tokenid" + j).value=oldtokenval;
		        document.getElementById("tokenid" + j).focus();
		        document.getElementById("tokenid" + j).style.background="#ccccff";
		        return false;
		       }
		     }
		 }
		document.getElementById("tokenid" + j).style.background="";
	  return true;
	}
	function openWindow(trnbill,Month,Year)
	{
		
		//window.showModalDialog("./hrms.htm?viewName=ApproveBillWindow&trnbill="+trnbill+"&Month="+Month+"&Year="+Year,"","dialogWidth:900px; dialogHeight:400px; center:yes; status:yes;menubar=yes");

		var urlstyle = 'height=400,width=900,toolbar=no,minimize=no,resizable=no,status=yes,memubar=yes,location=no,scrollbars=yes,top=20,left=200';
		var url = "./hrms.htm?viewName=ApproveBillWindow&trnbill="+trnbill+"&Month="+Month+"&Year="+Year;
		window.open(url,"",urlstyle);


		}
	function approveBill()
	{
		var length = '${listSize}';
		var trnbill  = getBillNo();
		
		var Month=document.TokenNumber.selMonth.value; 
		var Year=document.TokenNumber.selYear.value;
		var bill = document.TokenNumber.billNo.value;
		if(trnbill>0)
		{
			var answer = confirm (" Are You sure, you want to Approve this Bill for Month/Year${curmonth}/${curyear} ?")
			if(answer)
			{	
				showProgressbar("Please wait<br>While Approving Bill ...");
				openWindow(trnbill,Month,Year,bill);
				document.TokenNumber.btnApprove.disabled=true;
				document.TokenNumber.btnDiscard.disabled=true;
				document.TokenNumber.formSubmitButton.disabled=true;
			}
		}
	}
//chk for cons starts
	function chkforCons(){
		
		
		 var length = '${listSize}';
		 var trnbill = getBillNo();
		if(trnbill != "-1" && trnbill != false){
		var uri = 'ifms.htm?actionFlag=isApprovable&billNo='+trnbill+'&month=${curmonth}&year=${curyear}';
	 	var myAjax = new Ajax.Request(uri,
			      {
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function (myAjax) {
			consolidate(myAjax);
					},
			        onFailure: function(){ document.getElementById("btnApprove").disabled="disabled";
				        alert('Something went wrong...');
				        return false;} 
			          } );
		 }
	}
	function consolidate(myAjax){
		
		var XMLDoc = myAjax.responseXML.documentElement;
		var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
		var CreatedDDOCode= XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
		if (CreatedDDOCode=="no") {
			alert('Consolidated Bill is not approved yet.');		
			document.getElementById("btnApprove").disabled="disabled";
			//document.getElementById("txtDDOCode").disabled="disabled";
			return false;
			
		} 
		else
			approveBill();
		
	}

	function isDeletable(){
		
	
		 var length = '${listSize}';
		 var trnbill = getBillNo();
		if(trnbill != "-1" && trnbill != false){
		var uri = 'ifms.htm?actionFlag=isDeletable&billNo='+trnbill+'&month=${curmonth}&year=${curyear}';
	 	var myAjax = new Ajax.Request(uri,
			      {
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function (myAjax) {
			deletable(myAjax);
					},
			        onFailure: function(){ document.getElementById("btnApprove").disabled="disabled";
				        alert('Something went wrong...');
				        return false;} 
			          } );
		 }
	}
	function deletable(myAjax){
		
		var XMLDoc = myAjax.responseXML.documentElement;
		var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
		var CreatedDDOCode= XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
		if (CreatedDDOCode=="no") {
			alert('Consolidation Of Bill is in progress');		
			document.getElementById("btnDiscard").disabled="disabled";
			//document.getElementById("txtDDOCode").disabled="disabled";
			return false;
			
		} 
		else
			discardBill();
		
	}

	//Chk for cons ebds
	function ApproveSaveBill()
	{
		 var length = '${listSize}';
		 var trnbill = getBillNo();
		 if(trnbill != "-1" && trnbill != false)
		 {
				var answer = confirm (" Are You sure, to APPROVE and SAVE this Bill(s)?")
				if(answer)
				{	
					 document.TokenNumber.action = "./hdiits.htm?actionFlag=updateTokenNumber&listSize=${listSize}&month=${curmonth}&year=${curyear}&billNum="+trnbill+"&Flag=" + 'AS';// AS = Approve n Save
					 document.TokenNumber.submit();
					 document.TokenNumber.btnApprovenSave.disabled=true;
					 showProgressbar("Please wait<br>While Saving & Approving Bill(s) ...");
				}
		}
	}
	function getBillNo()
	{
		 var length = '${listSize}';
		 var billNo ; //document.getElementById("tnrbillnoId").value;//this will give you the bill sub head id of selected bill number
		 var check = true;
		 if(length == 0)
		 {
	 	 	alert("No Records to Save ! ! !");
	 	 	return false;
		 }
		 for(var i = 1; i <= length ; i++)
		 {
			 if(document.getElementById("chkboxId"+i).checked)
			 {
				 check = false;
				 if(billNo == null)
					 billNo=document.getElementById("tnrbillnoId"+i).value;
				 else
					 billNo+="," + document.getElementById("tnrbillnoId"+i).value;
			 }
		 }
		 if(check)
		 {
			 alert("Please Select The Radio Button");
			 document.TokenNumber.chkbox.focus();
			 return false;
		 }	
		 return billNo;
	}
	function discardBill()
	{   
		var billNo = getBillNo() ; 
		

		//alert("===> Bill No :: "+billNo);
		if(billNo != "-1" && billNo != false)
		{
			var answer = confirm ("-- Are You sure, you want to Delete this Bill?");
			if(answer)
			{	
			   		xmlHttp=GetXmlHttpObject();
					
					if (xmlHttp==null)
					{
						alert ("Your browser does not support AJAX!");
						return;
					} 
					else
					{	
						var url = run();
						var uri = "ifms.htm?actionFlag=checkPaybillStatus&month=${curmonth}&year=${curyear}&trnBillId="+billNo;
						url = uri + url;           
						xmlHttp.onreadystatechange=checkPaybillStatus;
						xmlHttp.open("POST",uri,true);
						xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
						xmlHttp.send(url);
					}
				}
		}

		//window.location.reload();	
	}
	function checkPaybillStatus()
	{

		var billNo = getBillNo() ; 
		if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200)
				{
					var XMLDoc=xmlHttp.responseXML.documentElement;	
					var paybillMapping = XMLDoc.getElementsByTagName('paybillMapping');
					
					var status=paybillMapping[0].childNodes[0].firstChild.nodeValue;    
       				
       				//alert("Going To Delete for status :: "+status+"::month"+"${curmonth}"+"::year::"+"${curyear}"+"::"+"${curbill}");
       				var billNo= getBillNo();
       				if(status == -1)
       				{
       					alert("There is some problem related to inner paybill");
       				}
       				else if((status == 0)||(status == 2))
       				{
           				//alert("==> bill No before discard :: "+billNo);
       					//document.TokenNumber.hidBillNo.value = billNo;
	   					document.TokenNumber.action = "ifms.htm?actionFlag=discardBill&TokenFlag=YES&month=${curmonth}&year=${curyear}&billNum="+billNo;
	   					document.TokenNumber.submit();
	   					document.TokenNumber.btnDiscard.disabled=true;	
	   					showProgressbar("Please wait<br>While Deleting Bill(s) ...");	
	   					//alert("Record discarded successfully.");
       				}
       				else if(status == 1)
       				{
       					alert("Paybill has been approved. You can not delete Paybill now.");
       				}
       				else if(status == 2)
       				{
       					alert("Paybill has been Rejected. You can not delete Paybill now.");
       				}
				}
			}



		//alert("===> in function.................");
		
	}
	function ShowPayBill(i)
	{

		showProgressbar("Please wait...");
		var newWindow;
		var billtype = document.TokenNumber.selType.value;
		var BillNo = document.getElementById("billNum"+i).value;
		//alert("===> Bill NO :: "+BillNo+":: billTypePara"+"${billtype}"+"::Month::"+"${curmonth}"+"::Year::"+"${curyear}");
	   	var height = screen.height - 100;
	   	var width = screen.width;
	   	var urlstring ;
	   	if('${arrearBill}' == billtype)
   		urlstring = "hrms.htm?actionFlag=reportService&reportCode=5000008&action=generateReport&FromParaPage=TRUE&backBtn=0&billTypePara=${billtype}&Month=${curmonth}&Year=${curyear}&Flag=1&billNo="+BillNo;
		else
	   		urlstring = "hrms.htm?actionFlag=reportService&reportCode=5000008&action=generateReport&FromParaPage=TRUE&backBtn=0&billTypePara=${billtype}&Month=${curmonth}&Year=${curyear}&Flag=1&billNo="+BillNo;
	   	document.TokenNumber.action = urlstring;
		document.TokenNumber.submit();
	}
	function generateBill()
	{
		document.TokenNumber.action = "ifms.htm?actionFlag=createBillFrmScratch&cmbBillType=3";
		document.TokenNumber.submit();
	}
	function selDeselectAll(obj)
	{		
		var selAllChkFlg=obj.checked;
		if(selAllChkFlg == true)
		{
			for (var j=1; j<="${listSize}"; j++)
		    {	
		    	document.TokenNumber.elements('chkboxId'+j).checked=true;
		    }
		}
		else
		{
			for (var j=1; j<="${listSize}"; j++)
		    {	
		    	document.TokenNumber.elements('chkboxId'+j).checked=false;
		    }
		}
	}
	function PaybillparaPage()
	{
		document.TokenNumber.action = "ifms.htm?actionFlag=getPaybillPara";
		document.TokenNumber.submit();
	}
	function alertCheck()
	{
	
	}


--></script>

<hdiits:form name="TokenNumber" validate="true" method="POST" action="javascript:validateForm1()">

	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b> <fmt:message
			key="Discard.Head" bundle="${adminTokenNumberLabel}" />
		</b></a></li>
	</ul>
	</div>
	<br/><br/>	
	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >
	<br/>
	
	<table width="85%" border="1" bordercolor="black"  align="center" id="searchTable" rules="">
	<tr>
		<td  align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"> MONTH </td>
		<td  align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"> YEAR </td>
		<td  align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"> BILL NUMBER </td>
		<td  align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"> Bill Type </td>
		<td  align = "center" style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold"> Bill Status </td>
	</tr>
	<tr>
		<td align = "center">
			<hdiits:select name="selMonth" size="1" sort="false" caption="Month" id="selMonth" validation="sel.isrequired" > 
					<hdiits:option value="">-------Selected--------</hdiits:option>
					<c:forEach items="${monthList}" var="month">
						<c:choose>
							<c:when test="${month.lookupShortName==curmonth}">
								<hdiits:option value="${month.lookupShortName}" selected="true" > ${month.lookupDesc} </hdiits:option>
							</c:when>
							<c:otherwise>
								<hdiits:option value="${month.lookupShortName}"> ${month.lookupDesc} </hdiits:option>
							</c:otherwise>
						</c:choose>
				</c:forEach>
		   </hdiits:select>
      </td>
      <td align = "center">
      		<hdiits:select name="selYear" size="1" sort="false" caption="Year" id="selYear" validation="sel.isrequired" > 
				<hdiits:option value="">-------Selected--------</hdiits:option>
					<c:forEach items="${yearList}" var="year">
						<c:choose>
							<c:when test="${year.lookupShortName == curyear}">
								<hdiits:option value="${year.lookupShortName}" selected="true" > ${year.lookupDesc} </hdiits:option>
							</c:when>
							<c:otherwise>
								<hdiits:option value="${year.lookupShortName}"> ${year.lookupDesc} </hdiits:option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
		   	</hdiits:select>
      </td>
      <td align = "center">
      		<hdiits:select name="billNo"  id="billNo" size="1" caption="Bill No" sort="false" onchange= "ShowTokenDetails()" > 
				<hdiits:option value="" selected="true">-------Selected--------</hdiits:option>
			<c:forEach items="${billNosList}" var="billNosList">
			<c:choose>
				<c:when test="${billNosList.dcpsDdoBillGroupId == curbill}">
						<hdiits:option value="${billNosList.dcpsDdoBillGroupId}" selected="true" > ${billNosList.dcpsDdoBillGroupId}</hdiits:option>
				</c:when>
					<c:otherwise>
						<hdiits:option value="${billNosList.dcpsDdoBillGroupId}"> ${billNosList.dcpsDdoBillGroupId}</hdiits:option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			</hdiits:select>		       
     </TD>
     <td align = "center">
			<hdiits:select name="selBillType" size="1" sort="false" caption="BillType" id="selType" validation="sel.isrequired"> 
					<hdiits:option value="">-------Selected--------</hdiits:option>
					<hdiits:option value="${payBill}">PayBill</hdiits:option>
			<!-- 		<hdiits:option value="${arrearBill}">ArrearBill</hdiits:option>
					<hdiits:option value="${supplPaybillFirst}">Supplementary PayBill First</hdiits:option>
					<hdiits:option value="${supplPaybillSecond}">Supplementary Paybill Second</hdiits:option>
					<hdiits:option value="${supplPaybillThird}">Supplementary Paybill Third</hdiits:option>
					<hdiits:option value="${multipleMonthSupplBill}">All Supplementary Bills</hdiits:option>
					 -->
			</hdiits:select>
      </td>
       <td align = "center">  
			<hdiits:select name="selBillStatus" size="1" sort="false" caption="BillStatus" id="selStatus"> 
					<hdiits:option value="">-------Selected--------</hdiits:option>
					<hdiits:option value="${created}" >Created</hdiits:option>
					<hdiits:option value="${approved}">Approved</hdiits:option>
					<%--added by vaibhav tyagi :start --%>
					<hdiits:option value="${rejected}">Rejected</hdiits:option>
					<%--added by vaibhav tyagi :end --%>
			</hdiits:select>
      </td>
    </tr>
</table>

	<br>
	<center style="width: 100%">
	<hdiits:button name="btn" value="showtokennumber" caption="Show Pay bills" style="width: 15%"
	id="tokenview" captionid="Show Token Number" onclick="ShowTokenDetails()" type="button"/>
<!-- 	
	&nbsp;	&nbsp;	&nbsp;
	<hdiits:button name="genBill" value="GeneratedBill" caption="Generate Bill" 
		id="btnGenerate" captionid="Genarate Bill" onclick="generateBill()" type="button"/>
		 -->
	</center>
	
	<br><br>
	<%--
<c:if test="${listSize ne 0}">
<hdiits:checkbox name='selectAllChkBox' id='selectAllChkBox' value = "" onclick="selDeselectAll(this)" /> <b>Select All</b>
</c:if>	
    --%>

<c:if test="${listSize gt 0}">		
	<c:set var = "i" value = "1" ></c:set>
		<display:table pagesize="${listSize}" name="${dataList}" id="row" requestURI="" style="width:100%" >
		<%--              To display CheckBox                                                      --%>
		<display:column  style="text-align: center;font-size:12px;" title="Check Box" headerClass="datatableheader" >
		<c:choose>
		<c:when test="${row.billStatus eq 'APPROVED'}">		
		<%--
			<input name="chkbox" id = "chkboxId${i}" type="checkbox" align="middle" disabled>
		--%>
		<input name="chkbox" id="chkboxId${i}" type="radio" align="middle" align="middle" disabled="true"/>
			
			<hdiits:hidden name = "tnrbillno${i}" id = "tnrbillnoId${i}" default ="${row.billGrpid}"/>
			</c:when>
			<c:otherwise>	
			<%--		
			<input name="chkbox" id = "chkboxId${i}" type="checkbox" align="middle" >
			--%>
			<input name="chkbox" id="chkboxId${i}" type="radio" align="middle" align="middle" onclick="chkStatus('${i}')"/>
			<hdiits:hidden name = "tnrbillno${i}" id = "tnrbillnoId${i}" default ="${row.billGrpid}"/>
			</c:otherwise>
			</c:choose>
		</display:column>	
		
		<display:column style="text-align: center;font-size:12px;" class="tablecelltext" title="Bill Id" headerClass="datatableheader"> 
		  				
		  				<hdiits:hidden name = "billNum${i}" id = "billNum${i}" default ="${row.billGrpid}"/>
		  				<a href="javascript:void(0)" onclick="ShowPayBill(${i})">${row.billGrpid}
		 </display:column>
		 
		 <display:column style="text-align: center;font-size:12px;" class="tablecelltext" title="Bill Description" headerClass="datatableheader"> 
		  				${row.billDescip}</display:column>	
		  				
		<display:column style="text-align: center;font-size:12px;" class="tablecelltext" title="Scheme Code" headerClass="datatableheader"> 
		  				${row.schemeCode}</display:column>
		  				
		<display:column style="text-align: center;font-size:12px;" class="tablecelltext" title="Scheme Name" headerClass="datatableheader"> 
		  				${row.schemeName}</display:column>		
		
		 <%--Added by saurabh--%> 					
	    <display:column style="text-align: center;font-size:12px;" class="tablecelltext" title="Sub Scheme Code" headerClass="datatableheader"> 
		  				${row.subSchemeCode}</display:column>		  					
		 <%--Ended by saurabh--%> 
		
		 <%--                 To display  Bill gross amount number                                                   --%>
		<display:column style="text-align: center;font-size:12px;" class="tablecelltext" title='<span>Bill Gross Amount<Font size="3" face="Rupee Foradian"> (<Font size="3" face="Rupee Foradian">`</Font>)</Font> </span>' headerClass="datatableheader"> 
		  				${row.grossTotalAmount}</display:column>	
		  				
		<%--                 To display  Net amount number                                                   --%>
		<display:column style="text-align: center;font-size:12px;" class="tablecelltext" title='<span>Net Amount<Font size="3" face="Rupee Foradian"> (<Font size="3" face="Rupee Foradian">`</Font>)</Font> </span>' headerClass="datatableheader"> 
		  				${row.netTotalAmount}</display:column>
		
		<display:column style="text-align: center;" class="tablecelltext" title="Status" headerClass="datatableheader">
		<hdiits:hidden name = "billStatus${i}" id = "billStatus${i}" default ="${row.billStatus}"/> 
		  				${row.billStatus}</display:column>
		  				
		<display:setProperty name="export.pdf" value="true" />
		<!--<c:set var="i" value="${i+1}"></c:set>-->
	</display:table>		
</c:if>	
<c:if test="${listSize eq 0}">
<center> No Records To Display ! ! !</center>
</c:if>	
<hdiits:hidden name="hidBillNo" />
<br>
<br>

	<center>
	
				 <hdiits:hidden default="getHomePage" name="givenurl"/>
	</center>
<br>
	<center>
	<!--  <hdiits:button name="btnApprovenSave" value="Approve_N_Save" caption="Save and Approve Bill" 
	id="btnApprove" captionid="Approve Bill" onclick="ApproveSaveBill()" type="button"/>
	&nbsp;	&nbsp;	&nbsp;-->
	<hdiits:button name="btnApprove" value="SubmitBill" caption="Voucher Entry" 
		id="btnApprove" captionid="Voucher Entry" onclick="chkforCons();" type="button" />
		&nbsp;	&nbsp;	&nbsp; 
	<hdiits:button name="btnDiscard" value="DiscardBill" caption="Delete Bill" 
		id="btnDiscard" captionid="Discard Bill" onclick="isDeletable()" type="button"/>
		&nbsp;	&nbsp;	&nbsp; 
		<hdiits:button name="formSubmitButton" onclick="PaybillparaPage()" type="button" caption="Generate Bill" captionid="Generate" bundle="${genLables}" />
		&nbsp;	&nbsp;	&nbsp;
		<hdiits:button name="formForwardButton" id="formForwardButton" onclick="forwardToLvlTwo()" type="button" caption="Forward" captionid="Forward" bundle="${genLables}" />
	</center>
<br>
<br>
<table width="100%">
	<tr>
		<td align="left">
			<a href= "./hrms.htm?actionFlag=loadBillStatisticsData" id="insertOneLink" style="height: 50px;width: 100px;text-decoration:none;cursor: default;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
		</td>
		<td align="right">
			<a href= "./hrms.htm?actionFlag=NetMismatchScreen" id="insertSecondLink" style="height: 50px;width: 100px;text-decoration:none;cursor: default;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
		</td>
	</tr>
</table>
 

<hdiits:validate locale="${locale}" controlNames="" />

<script type="text/javascript">
	initializetabcontent("maintab")
</script>
</div>
</hdiits:form>

<script type="text/javascript">
document.TokenNumber.selBillType.value="${billtype}";
document.TokenNumber.selBillStatus.value="${currBillStatus}";
if('${msg}'!= null && '${msg}'!='')
{
	alert('${msg}');
	resetForm1();
}

if('${msgDiscard}'!= null && '${msgDiscard}'!='')
{
	alert('${msgDiscard}');

	var url="./ifms.htm?actionFlag=showTokenNumber";
	document.TokenNumber.action=url;
	document.TokenNumber.submit();
}
</script>

<script type="text/javascript">
function forwardToLvlTwo(){
	 
	var month=document.getElementById("selMonth").value;
	var year=document.getElementById("selYear").value;
	var length = '${listSize}';
	var trnbill = getBillNo();
 
	if(trnbill != "-1" && trnbill != false){
	var answer=confirm('Do you wish to forward paybill to Level 2 DDO?');
	if(answer){
	var uri = 'ifms.htm?actionFlag=fwdBillToLvlTwo&billNo='+trnbill+'&month='+month+'&year='+year;
	document.TokenNumber.action=uri;
	document.TokenNumber.submit();
	showProgressbar("Please wait<br>While Forwading Bill(s) ...");
	alert('Paybill Forwarded Successfully');
	}
	else{
		return false;
	}
  }
}
</script>

<script type="text/javascript">
function chkStatus(id){
	var status= document.getElementById("billStatus"+id).value;
	if(status=="REJECTED"){
		document.getElementById("btnApprove").disabled=true;
		document.getElementById("btnDiscard").disabled=false;
		document.getElementById("formSubmitButton").disabled=false;
		document.getElementById("formForwardButton").disabled=true;
	}
	else if(status=="CREATED"){
		document.getElementById("btnApprove").disabled=false;
		document.getElementById("btnDiscard").disabled=false;
		document.getElementById("formSubmitButton").disabled=false;
		document.getElementById("formForwardButton").disabled=false;
	}
	else if(status=="FORWARDED"){
		document.getElementById("btnApprove").disabled=true;
		document.getElementById("btnDiscard").disabled=true;
		document.getElementById("formSubmitButton").disabled=true;
		document.getElementById("formForwardButton").disabled=true;
	}
	else if(status=="CONSOLIDATED"){
		document.getElementById("btnApprove").disabled=false;
		document.getElementById("btnDiscard").disabled=true;
		document.getElementById("formSubmitButton").disabled=true;
		document.getElementById("formForwardButton").disabled=true;
	}
}
</script>

<%
}catch(Exception e) {e.printStackTrace();}
%>

	