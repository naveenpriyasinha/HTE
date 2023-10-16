<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    


<fmt:setBundle basename="resources.billproc.billproc_en_US" var="billprocLabels" scope="application"/>


<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.tcs.sgv.core.valueobject.FrmServiceMst"%>
<%@page import="com.tcs.sgv.common.valueobject.TrnBillRegister"%> 
    
<%@page import="com.tcs.sgv.common.utils.DateUtility,com.tcs.sgv.common.valueobject.CmnLookupMst"%>

<html>
<head>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/validation.js"></script>
<script type="text/javascript" src="script/billproc/validation.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<script language="javascript">
	    var fromFwd="-1";     // from which fwd comes
	    
	    function  searching1(resFlg)
		{
			
			if(document.frmCntrDisptchBills.srch0.value!='')
			{
				if(!isDate(document.frmCntrDisptchBills.srch0.value))
				{
				return false;
				}
			}
			
			if(document.frmCntrDisptchBills.srch1.value!='')
			{
				if(!isDate(document.frmCntrDisptchBills.srch1.value))
				{
				return;
				}
			}
			
			
			if(document.frmCntrDisptchBills.srch2.value!='')
			{
				if(!(validations(document.frmCntrDisptchBills.srch2.value)))
				{
				 	document.frmCntrDisptchBills.srch2.value='';
					document.frmCntrDisptchBills.srch2.focus();
					return false;
				}
			}	
			if(document.frmCntrDisptchBills.srch3.value!='')
			{
				if(!(validations(document.frmCntrDisptchBills.srch3.value)))
				{
					document.frmCntrDisptchBills.srch3.value='';				
					document.frmCntrDisptchBills.srch3.focus();
					return false;
				}
			}	
		
				document.frmCntrDisptchBills.action ='ifms.htm?actionFlag=acceptCheques&StatusFlag=<%=(String)request.getParameter("StatusFlag")%>&ReceiveFlag='+resFlg ;
				document.frmCntrDisptchBills.method ='post';
				document.frmCntrDisptchBills.submit();
		}
	    
	    
	    
		function  searching(resFlg)
		{
		document.frmCntrDisptchBills.displ.value = document.frmCntrDisptchBills.txtSearch.value;
			if(document.frmCntrDisptchBills.txtSearch.value=='')
			{
				alert("Search Text is Empty");
				return;
			}
			if(document.frmCntrDisptchBills.cmbSearch.value =='p.billDate')
			{
			  
			  var str = document.frmCntrDisptchBills.txtSearch.value;
			  
			  if(!isDate(str))
					{
						return;
					}
			  var arr =  str.split('/');
			  //document.frmCntrDisptchBills.action='ifms.htm?actionFlag=getCheques&StatusFlag=CHEQPRNT&txtSearch='+arr[2]+'-'+arr[1]+'-'+arr[0];
			  
			  document.frmCntrDisptchBills.action ='ifms.htm?actionFlag=acceptCheques&StatusFlag=<%=(String)request.getParameter("StatusFlag")%>&ReceiveFlag='+resFlg + '&txtSearch='+arr[2]+'-'+arr[1]+'-'+arr[0] ;
			}
			else
			{
				document.frmCntrDisptchBills.action ='ifms.htm?actionFlag=acceptCheques&StatusFlag=<%=(String)request.getParameter("StatusFlag")%>&ReceiveFlag='+resFlg ;
			}
			
			//document.frmCntrDisptchBills.action ='ifms.htm?actionFlag=acceptCheques&StatusFlag=CHEQDISPCNTR&ReceiveFlag='+resFlg ;
			document.frmCntrDisptchBills.method ='post';
			document.frmCntrDisptchBills.submit();
		}
		function showBill(url)
		{
			window.open(url,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,top=100,left=150,width=742,height=500"); 
		}
		function checkUncheckAll(theElement) {
	     var theForm = theElement.form, z = 0;
		 for(z=0; z<theForm.length;z++){
	
	      if(theForm[z].type == 'checkbox' && theForm[z].name != 'chkSelect'){
		  theForm[z].checked = theElement.checked;
		  }
	     }
	     for(z=0; z<theForm.length;z++){
	      
	      if(theForm[z].type == 'select-one'){
		  //alert("Name"+ theForm[z].name +"VALUE"+theForm[z].value);
		  }
	     }
	     }
		
		function ReceiveCheques(type)
		{
			fromFwd="sendToCntr";
		    var flag =0;
		    for(i=0;i<document.frmCntrDisptchBills.elements.length;i++)
		    {
		        if(document.frmCntrDisptchBills.elements[i].type=="checkbox" && document.frmCntrDisptchBills.elements[i].name!='chkSelect')
		        {	
		          if(document.frmCntrDisptchBills.elements[i].checked == true)
		          {
		            flag =1;
		            break;
		          }
		        }
		    }
		    if(flag ==0)
		    {
		       alert('Select atleast one checkbox');
		       return false;
		    }
		    var statusFlag = '';
		    if(type == 'Custody')
		    {
		    	statusFlag = '<%=request.getParameter("StatusFlag")%>';
		    }
		    else
		    {
		    	statusFlag = 'CHQ_CNTR';
		    }
		    
			document.frmCntrDisptchBills.action ='ifms.htm?actionFlag=acceptCheques&StatusFlag='+statusFlag+'&ReceiveFlag=0&Receive=Yes';
			
			document.frmCntrDisptchBills.submit();
		}
		function generateAdvice()
		{
			var arr = new Array();
		    var indx  =0;
		    var flag =0;
		    for(i=0;i<document.frmCntrDisptchBills.elements.length;i++)
		    {
		        if(document.frmCntrDisptchBills.elements[i].type=="checkbox" && document.frmCntrDisptchBills.elements[i].name !='chkSelect')
		        {	
		          if(document.frmCntrDisptchBills.elements[i].checked == true)
		          {
		            arr[indx]  = document.frmCntrDisptchBills.elements[i].value;
		            indx = indx+1;
		            flag =1;
		          }
		        }
		    }
		    if(flag ==0)
		    {
		       alert(' Select atleast one checkbox ');
		       return false;
		    }
		    document.frmCntrDisptchBills.actionFlag.value='generateVouchAdvc';
		    urlStr = 'ifms.htm?actionFlag=generateVouchAdvc&chkbox='+arr;
		    window.open(urlStr,'_blank','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=no,copyhistory=no,top=200,left=300,width=450,height=400')
		    //alert(arr);
			//insertdt();  
		}	
		
		function DispatchCheques()
		{
			fromFwd="DispChq";
			var arr = new Array();
		    var indx  =0;
		    var flag =0;
		    for(i=0;i<document.frmCntrDisptchBills.elements.length;i++)
		    {

		        if(document.frmCntrDisptchBills.elements[i].type=="checkbox" && document.frmCntrDisptchBills.elements[i].name !='chkSelect')
		        {	
		          if(document.frmCntrDisptchBills.elements[i].checked == true)
		          {
		            arr[indx]  = document.frmCntrDisptchBills.elements[i].value;
		            //alert(arr[indx]);
		            indx = indx+1;
		            flag =1;
		          }
		        }
		    }
		    if(flag ==0)
		    {
		       alert(' Select atleast one checkbox ');
		       return false;
		    }

		    var bill = arr[0].split("~");
			//window.open('ifms.htm?actionFlag=getHyrUsers&BillNo='+bill[0]+'&page=savedBillsUpdate','','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,top=100,left=150,width=500,height=400');
			//window.open('ifms.htm?actionFlag=getHyrUsers&BillNo='+bill[0]+'&page=savedBillsUpdate&BillArr='+arr,'','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,top=250,left=350,width=500,height=400');
			//document.frmCntrDisptchBills.action ='ifms.htm?actionFlag=getHyrUsers&BillNo='+bill[0]+'&page=savedBillsUpdate&BillArr='+arr;
			//alert(document.frmCntrDisptchBills.action);
			//document.frmCntrDisptchBills.submit();
			return arr;
			
		}		
		
		function sendToCounter(type)
		{
			var i,j=0,ale='n';
			for(i=0;i<document.frmCntrDisptchBills.elements.length;i++)
			    {

			        if(document.frmCntrDisptchBills.elements[i].type=="checkbox" && document.frmCntrDisptchBills.elements[i].name != 'chkSelect')
			        {	
			          
			          if(document.frmCntrDisptchBills.elements[i].checked == true)
			          {
			          	var exm = eval('document.frmCntrDisptchBills.exempted' +j);
			          	if(exm.value=='N')
			          	{
			          		ale='y';
			          		break;
			          	}
			          }
			          j++;
			         }
			       }  
			          	
			if(ale=='y')
			{
				if(!confirm('Are You Sure To Forwarding Non exempted Bill?'))
				return false;
			}
			fromFwd="sendToCntr";
			var arr = new Array();
		    var indx  =0;
		    var flag =0;
		    for(i=0;i<document.frmCntrDisptchBills.elements.length;i++)
		    {
		        if(document.frmCntrDisptchBills.elements[i].type=="checkbox" && document.frmCntrDisptchBills.elements[i].name!='chkSelect')
		        {	
		          if(document.frmCntrDisptchBills.elements[i].checked == true)
		          {
		            arr[indx]  = document.frmCntrDisptchBills.elements[i].value;
		            indx = indx+1;
		            flag =1;
		          }
		        }
		    }
		    if(flag ==0)
		    {
		       alert('Select atleast one checkbox');
		       return false;
		    }
		    var bill = arr[0].split("~");
			url = 'ifms.htm?actionFlag=getHyrUsers&statusFlag=CHQ_CNTR&BillNo='+bill[0]+'&BillArr='+arr ;
			if(type == 'Forward')
		    {
		    	url = url+'&sendTo=H';
		    }
		    else if(type=='Peer')
		    {
		    	url = url+'&sendTo=P';		    	
		    }
		    window.open(url,'','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,top=250,left=350,width=500,height=400');

		}
		
		function displ()
		{
			if(fromFwd=="DispChq")
			{
			document.frmCntrDisptchBills.actionFlag.value="sendToBook";
			}
			else if (fromFwd=="sendToCntr")
			{
			document.frmCntrDisptchBills.actionFlag.value="sendToCounter";
			}
			var arr = new Array();
		    var indx  =0;
		    var flag =0;
		    for(i=0;i<document.frmCntrDisptchBills.elements.length;i++)
		    {

		        if(document.frmCntrDisptchBills.elements[i].type=="checkbox" && document.frmCntrDisptchBills.elements[i].name!='chkSelect')
		        {	
		          if(document.frmCntrDisptchBills.elements[i].checked == true)
		          {
		            arr[indx]  = document.frmCntrDisptchBills.elements[i].value;
		            indx = indx+1;
		            flag =1;
		          }
		        }
		    }
		    if(flag ==0)
		    {
		       alert(' Select atleast one checkbox ');
		       return false;
		    }
			//insertdt();
			if(fromFwd=="sendToCntr")
			{
			document.frmCntrDisptchBills.action = 'ifms.htm?actionFlag=sendToCounter&StatusFlag=<%=(String)request.getParameter("StatusFlag")%>&ReceiveFlag=1';
			}
			else if(fromFwd=="DispChq")
			{
			document.frmCntrDisptchBills.action= 'ifms.htm?actionFlag=sendToDDO&StatusFlag=<%=(String)request.getParameter("StatusFlag")%>&ReceiveFlag=1';

			}
			document.frmCntrDisptchBills.submit();
			
			if("CUSTCON2"=="<%=(String)request.getParameter("StatusFlag")%>" || "CUSTCON1"=="<%=(String)request.getParameter("StatusFlag")%>")
			{
			//document.frmCntrDisptchBills.action="ifms.htm?actionFlag=acceptCheques&StatusFlag=<%=(String)request.getParameter("StatusFlag")%>&ReceiveFlag=0";
			//document.frmCntrDisptchBills.submit();
			}
			
		}
		
		function returnBill()
		{
			var arr = new Array();
		    var indx  =0;
		    var flag =0;
		    for(i=0;i<document.frmCntrDisptchBills.elements.length;i++)
		    {

		        if(document.frmCntrDisptchBills.elements[i].type=="checkbox" && document.frmCntrDisptchBills.elements[i].name!='chkSelect')
		        {	
		          if(document.frmCntrDisptchBills.elements[i].checked == true)
		          {
		            arr[indx]  = document.frmCntrDisptchBills.elements[i].value;
		            indx = indx+1;
		            flag =1;
		          }
		        }
		    }
		    if(flag ==0)
		    {
		       alert(' Select atleast one checkbox ');
		       return false;
		    }
		    document.frmCntrDisptchBills.actionFlag.value="sendToCounter";
		    document.frmCntrDisptchBills.action = 'ifms.htm?actionFlag=sendToCounter&ReturnBill=Yes&StatusFlag=<%=(String)request.getParameter("StatusFlag")%>&ReceiveFlag=1&BillArr='+arr;
		   
		    document.frmCntrDisptchBills.submit();
		}
		
		function validations(data)
			{
				if(data=='.')
				{
					alert("Amount Not Valid");
					return false;
				}
				var numaric = data;
				for(var j=0; j<numaric.length; j++)
				{
 				    var hh = numaric.charAt(j);
				    if(hh >= '0' && hh <= '9'  || hh=='.' )
		  			{
				    }
					else	
					{
						alert("Amount Not Valid");
					    return false;
		            }
				}
				return true;				
			}		
			function showDtPic()
  			{
  				if(document.frmCntrDisptchBills.cmbSearch.value=="p.billDate")
  				{
  					document.getElementById("dtpicker").style.display="inline";
  				}
  				else
  				{
  					document.getElementById("dtpicker").style.display="none";
  				}
  			}
  			function onSearch(e)
  			{
  				
  				if(e.keyCode==13)
  				{
  					
					if(document.frmCntrDisptchBills.txtSearch.value=='')
					{
						//document.frmCntrDisptchBills.parentUrl1.value = "";
						alert("Search Text is Empty");
						return false;
					}
					else
					{
						searching(<%=request.getParameter("ReceiveFlag") %>);
  					}
  				}
  			}
  			
  			function showBills(BillNo, BillStatus)
			{
				var newWindow;
		    	var height = screen.height - 100;
		    	var width = screen.width;
		    	var urlstring = "ifms.htm?actionFlag=getBillData&isChq=1&billNo=" + BillNo + "&billStatus=" + BillStatus;
		    	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
		    	newWindow = window.open(urlstring, "frmViewOnlineBill", urlstyle);
			}
			
			function showBill(url)
			{
				window.open(url,"_blank","toolbar=no,location=no,directories=no,status=yes,menubar=no,scrollbars=yes,resizable=no,top=10,left=10,width=1010,height=670");
			//window.open(url);
			}
		</script>
</head>
<%@page import="java.util.ResourceBundle"%>
<%
	ResourceBundle buttonBundle = ResourceBundle.getBundle("resources/billproc/billproc_en_US");
%>

<body>
<hdiits:form name="frmCntrDisptchBills" validate="true" method="post">
	<div id="statusBar" style="width:50%; background-color: #BADCFD ;display:none;font: bold 10px Arial"></div>		
	<div id="progressImage" style="display:none"></div>		
	<input type="hidden" value="" name="actionFlag" id="actionFlag" >
	<input type="hidden" name="toPost">
	<input type="hidden" name="toUser">
	<input type="hidden" name="SendTo" value="-1">
	<input type="hidden"  name="displ"  />
	
	
  <c:set var="resultObj" value="${result}" > </c:set>
	  <c:set var="resValue" value="${resultObj.resultValue}"> </c:set>		
	  
	  <c:set var="billList" value="${resValue.BillChequeList}"> </c:set>
	 
<table width="100%" align="center">	
	<tr class="TableHeaderBG">
		<td align="center">
			<b><% 
			if(request.getParameter("StatusFlag")!=null && request.getParameter("StatusFlag").equals("CUSTCON1"))
			{ %> <fmt:message key="ACCEPTCHEQUE.COUNTERTITLE" bundle="${billprocLabels}"></fmt:message> <%} 
			else if(request.getParameter("StatusFlag")!=null && request.getParameter("StatusFlag").equals("CUSTCON2"))
			{ %> <fmt:message key="ACCEPTCHEQUE.CUSTODYTITLE" bundle="${billprocLabels}"></fmt:message> <%} 
			else
			{
			if(request.getParameter("ReceiveFlag").equals("0")) { %> <fmt:message key="ACCEPTCHEQUE.ACCEPT" bundle="${billprocLabels}"></fmt:message> <%} else { %> <fmt:message key="ACCEPTCHEQUE.DISPATCH" bundle="${billprocLabels}"></fmt:message> <% } 
			}
			%></b>
		</td>
	</tr>

	<tr><td>&nbsp;</td></tr>
			<tr>
				<td>
				<%
					if(request.getParameter("StatusFlag")!=null && request.getParameter("StatusFlag").equals("CUSTCON2"))
					{
				%>
					
			<table align="center" width="90%" class="groupTable" >
				<tr class="datatableheader">
					<td colspan="4"  align="center">
						<fmt:message key="CHQCOMMON.CHEQUESEARCH" bundle="${billprocLabels}"></fmt:message> 
					</td>
				</tr>
				<tr>
					<td align="left" width="7%">
						 &nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CHQCOMMON.FROMDATE" bundle="${billprocLabels}"></fmt:message>
					</td>
					<td align="left" width="30%">
						:&nbsp;<input name="srch0"  type="text" size="20"  value="<% if(request.getParameter("srch0")!=null  ) {  out.print(request.getParameter("srch0")); }  %>"> <img src="images/CalendarImages/ico-calendar.gif" width="20" onClick=window_open("srch0",375,570) >
					</td>
					<td align="left" width="7%"> 
						<fmt:message key="CHQCOMMON.TODATE" bundle="${billprocLabels}"></fmt:message>
					</td>
					<td align="left" width="36%">
						:&nbsp;<input name="srch1"  type="text" size="20" value="<% if(request.getParameter("srch1")!=null  ) {  out.print(request.getParameter("srch1")); }  %>"> <img src="images/CalendarImages/ico-calendar.gif" width="20" onClick=window_open("srch1",375,570) >
					</td>
				</tr>
				
				<tr>
					<td align="left" width="25%">
						&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CHQCOMMON.GRATERAMOUNT" bundle="${billprocLabels}"></fmt:message>
					</td>
					<td align="left" width="25%">
						:&nbsp;<input name="srch2" type="text" value="<% if(request.getParameter("srch2")!=null  ) {  out.print(request.getParameter("srch2")); }  %>">
					</td>
					<td align="left" width="25%">
						<fmt:message key="CHQCOMMON.LESSAMOUNT" bundle="${billprocLabels}"></fmt:message>
					</td>
					<td align="left" width="25%">
						:&nbsp;<input name="srch3" type="text" value="<% if(request.getParameter("srch3")!=null  ) {  out.print(request.getParameter("srch3")); }  %>">
					</td>
				</tr>
				
				<tr>
					<td align="left" width="25%">
						&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CHQCOMMON.DDONO" bundle="${billprocLabels}"></fmt:message>
					</td>
					<td align="left" width="25%">
						:&nbsp;<input name="srch4" type="text" value="<% if(request.getParameter("srch4")!=null  ) {  out.print(request.getParameter("srch4")); }  %>">
					</td>
					<td align="left" width="25%">
						<fmt:message key="CHQCOMMON.BILLCONTROLNO" bundle="${billprocLabels}"></fmt:message>
					</td>
					<td align="left" width="25%">
						:&nbsp;<input name="srch5" type="text" value="<% if(request.getParameter("srch5")!=null  ) {  out.print(request.getParameter("srch5")); }  %>">
					</td>
				</tr>

				<tr>
					<td align="left" width="25%">
						&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CHQCOMMON.TOKENNO" bundle="${billprocLabels}"></fmt:message>
					</td>
					<td align="left" width="25%">
						:&nbsp;<input name="srch6" type="text" value="<% if(request.getParameter("srch6")!=null  ) {  out.print(request.getParameter("srch6")); }  %>">
					</td>
					<td align="left" width="25%">
						<fmt:message key="CHQCOMMON.CHEQUENO" bundle="${billprocLabels}"></fmt:message>
					</td>
					<td align="left" width="25%">
						:&nbsp;<input name="srch7" type="text" value="<% if(request.getParameter("srch7")!=null  ) {  out.print(request.getParameter("srch7")); }  %>" >
					</td>
				</tr>
					
				<tr>
					<td align="left" width="25%">
						&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="CNTR.EXEMPTED" bundle="${billprocLabels}"></fmt:message> ?
					</td>
					<td align="left" width="25%">
						<input type="radio" name="srch8" value="Y" <% if(request.getParameter("srch8")!=null && request.getParameter("srch8").equals("Y")  ) {  out.print("checked"); }  %> > 
						<fmt:message key="CMN.YES" bundle="${billprocLabels}"></fmt:message> 

						<input type="radio" name="srch8" value="N" <% if(request.getParameter("srch8")!=null && request.getParameter("srch8").equals("N")  ) {  out.print("checked"); }  %> >
						<fmt:message key="CMN.NO" bundle="${billprocLabels}"></fmt:message> 
					</td>
					<td align="left" width="25%"></td>
					<td align="left" width="25%"></td>
				</tr>

				<tr class="groupTable">
					<td colspan="4"  align="center">
						<input type="button" class="buttontag" name="srcSubmit" value='<%=buttonBundle.getString("CHQCOMMON.SEARCH")%>' onclick="javascript:searching1(<%=request.getParameter("ReceiveFlag") %>);">
					</td>
				</tr>
			</table>
						
				<%
					}
					else
					{
				%>	
					<table align="center" width="90%" align="left">
								<tr>
									<td width="50%">
									</td>
									<td width="8%" align="left">
									<fmt:message key="CHQCOMMON.SEARCH" bundle="${billprocLabels}"></fmt:message>
									:
									</td>
									<td >
										<input type="text" name="txtSearch" onkeypress="javascript:onSearch(event);" class="TextCss" value="<% if(request.getParameter("displ")!=null  ) {  out.print(request.getParameter("displ")); }  %>"  size="15"/>
										<div id="dtpicker" style="display:none"><img src="images/CalendarImages/ico-calendar.gif"   width="20" onClick=window_open("txtSearch",375,570) ></div>
									</td>
							<td width="*">
							<select name="cmbSearch" onchange="showDtPic()" >
							<option value="" ><fmt:message key="CHQCOMMON.SELECT" bundle="${billprocLabels}"></fmt:message></option>
							<option value="chkNo" <% if(request.getParameter("cmbSearch")!=null  && request.getParameter("cmbSearch").equals("chkNo") ) {  out.print("selected"); }  %> >
							<fmt:message key="CHQCOMMON.CHEQUENO" bundle="${billprocLabels}"></fmt:message>

							</option>
							<option value="p.billCntrlNo" <% if(request.getParameter("cmbSearch")!=null  && request.getParameter("cmbSearch").equals("p.billCntrlNo") ) {  out.print("selected"); }  %> >
							<fmt:message key="CHQCOMMON.BILLCONTROLNO" bundle="${billprocLabels}"></fmt:message>

							</option>
							<option value="mb.subjectDesc" <% if(request.getParameter("cmbSearch")!=null  && request.getParameter("cmbSearch").equals("mb.subjectDesc") ) {  out.print("selected"); }  %> >
							<fmt:message key="CHQCOMMON.BILLTYPE" bundle="${billprocLabels}"></fmt:message>

							</option>
							
							<option value="p.billDate" <% if(request.getParameter("cmbSearch")!=null  && request.getParameter("cmbSearch").equals("p.billDate") ) {  out.print("selected"); }  %>  >
							<fmt:message key="CHQCOMMON.BILLDATE" bundle="${billprocLabels}"></fmt:message>
							</option>	
														
							<option value="odm.ddoNo" <% if(request.getParameter("cmbSearch")!=null  && request.getParameter("cmbSearch").equals("odm.ddoNo") ) {  out.print("selected"); }  %> >
							<fmt:message key="CHQCOMMON.DDONO" bundle="${billprocLabels}"></fmt:message>
							</option>									
							<option value="ddoName" <% if(request.getParameter("cmbSearch")!=null  && request.getParameter("cmbSearch").equals("ddoName") ) {  out.print("selected"); }  %> >
							<fmt:message key="CHQCOMMON.DDONAME" bundle="${billprocLabels}"></fmt:message>

							</option>									
							<option value="p.budmjrHd" <% if(request.getParameter("cmbSearch")!=null  && request.getParameter("cmbSearch").equals("p.budmjrHd") ) {  out.print("selected"); }  %> >
							<fmt:message key="CHQCOMMON.MAJORHEAD" bundle="${billprocLabels}"></fmt:message>

							</option>								
							
							<option value="p.tokenNum"  <% if(request.getParameter("cmbSearch")!=null  && request.getParameter("cmbSearch").equals("p.tokenNum") ) {  out.print("selected"); }  %>>
							<fmt:message key="CHQCOMMON.TOKENNO" bundle="${billprocLabels}"></fmt:message>

							</option>																			
						</select>

					</td>
					<td width="2%">
						<a href="#" onclick="javascript:searching(<%=request.getParameter("ReceiveFlag") %>);"><img src="common/images/search.gif" align="right" height="16" width="16"  /></a>
					</td>
				</tr>
				<script>
	  	if(document.frmCntrDisptchBills.txtSearch.value!=null && document.frmCntrDisptchBills.cmbSearch.value=="p.billDate")
  				{
  					document.getElementById("dtpicker").style.display="inline";
  				}
	  	</script>
			</table>
			<%
					}
			%>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>	
			<tr><td>&nbsp;</td></tr>
	<tr>
		<td>
		<%
							int i=0;
							int j=0;
						%>
		<display:table list="${billList}" pagesize="12" requestURI="ifms.htm?actionFlag=getHyrUsers&page=savedBillsUpdate"
				   id="VO" excludedParams="ajax" varTotals="totals"  partialList="" style="width:100%">
				   
					<display:setProperty name="paging.banner.placement" value="bottom"/>    					
					<display:column class="oddcentre" title="<input name='chkSelect' type='checkbox' onclick='checkUncheckAll(this);'/>" headerClass="datatableheader" >
						<table>
						<tr><td>
					<input type="hidden" name="exempted<%=j%>" value="${VO.billVO.exempted}">
					<input name="chkbox" value="${VO.billVO.billNo}~${VO.billVO.tokenNum}~${VO.billVO.budmjrHd}" type="checkbox" > 						
						<c:forEach var="cheques" items="${VO.chequeList}">
						
						<input name="chequeId<%=i%>" type="hidden" value="${cheques.chequeId}">
						<script>
						if(<%=j%> == 0)
						{
							document.forms[0].chkbox.value = document.forms[0].chkbox.value +"~"+ eval("document.forms[0].chequeId"+<%=i%>).value;						
						}
						else
						{
							document.forms[0].chkbox[<%=j%>].value = document.forms[0].chkbox[<%=j%>].value +"~"+ eval("document.forms[0].chequeId"+<%=i%>).value;	
						}
						</script>				
						<%
							i++;
						%>
						</c:forEach>
						</td></tr>
						</table>
					</display:column>	    
					
					<display:column class="oddcentre" titleKey="CMN.CHEQUE_NO" sortable="true" headerClass="datatableheader" style="text-align:center">							
					<table>
					<c:forEach var="cheques" items="${VO.chequeList}">
					<tr><td>
					<c:out value="${cheques.chequeNo}"></c:out>
					</td></tr>
					</c:forEach>
					</table>
					</display:column>		
					<display:column class="oddcentre" titleKey="CMN.CHEQUE_AMOUNT" sortable="true" headerClass="datatableheader"  style="text-align:center">							
					<table>
					<c:forEach var="cheques" items="${VO.chequeList}">
					<tr><td>
					<c:out value="${cheques.chequeAmt}"></c:out>
					</td></tr>
					</c:forEach>
					</table>
					</display:column>				
					<display:column  class="oddcentre" titleKey="CMN.BILL_CONTROL_NO" sortable="true" headerClass="datatableheader"  style="text-align:center">							
					<%
						if(request.getParameter("ReceiveFlag") != null && request.getParameter("ReceiveFlag").equals("1"))
						{
					%>
					<table>	
							<tr><td>
							<c:choose>
									<c:when test="${VO.billVO.phyBill==1}">
											<c:set var="URLLink" value="ifms.htm?actionFlag=showBill&sessionFlag=1&isChq=1&BillNo=${VO.billVO.billNo}"  ></c:set>											
											<a href="javascript:void(0)" onclick="javascript:showBill('${URLLink}')">
												<c:out value="${VO.billVO.billCntrlNo}"/>
											</a>
										</c:when>
										<c:otherwise>
											<c:set var="URLLink" value="ifms.htm?actionFlag=getBillData&billNo=${VO.billVO.billNo}&isChq=1&billStatus=BSNT_TO"  ></c:set>
											<a href="javascript:void(0)" onclick="javascript:showBills('${VO.billVO.billNo}','CHQ_CSTDN')">
												<c:out value="${VO.billVO.billCntrlNo}"/>
											</a>
										</c:otherwise>
									</c:choose>		
									</td></tr>	   							
	   					</table>
	   				 <%
						}
						else
						{	   				 
	   				 %>
	   				 <c:out value="${VO.billVO.billCntrlNo}"/>
	   				 <%
						}
	   				 %>
					</display:column>
					<display:column property="billVO.tokenNum" class="oddcentre" titleKey="CMN.TOKEN_NO" sortable="true" headerClass="datatableheader"  style="text-align:center"/>
					<display:column property="billVO.refNum" class="oddcentre" titleKey="CMN.RefNumber" sortable="true" headerClass="datatableheader" style="text-align:center"/>
					<display:column property="billVO.billGrossAmount" class="oddcentre" titleKey="CMN.BILL_AMOUNT" sortable="true" headerClass="datatableheader"   style="text-align:center"/>
					<display:column property="billVO.subjectDesc" class="oddcentre" titleKey="CMN.BILL_TYPE" sortable="true" headerClass="datatableheader"   />
					<display:column property="billVO.lookupName" class="oddcentre" titleKey="CMN.BILL_CATEGORY" sortable="true" headerClass="datatableheader"  style="text-align:center" />					
					<display:column property="billVO.inwardDt" class="oddcentre" format="{0,date,dd/MM/yyyy}" titleKey="CMN.BILL_DATE" sortable="true" headerClass="datatableheader"  />
				    
				    <display:column property="billVO.employeeName" class="oddcentre" titleKey="CMN.DDO_NAME" sortable="true" headerClass="datatableheader"  />
				    <display:column property="billVO.budmjrHd" class="oddcentre" titleKey="CMN.MAJOR_HEAD" sortable="true" headerClass="datatableheader"  style="text-align:center" />				
				    <display:column property="billVO.audName" class="oddcentre" titleKey="CMN.AUDITOR_NAME" sortable="true" headerClass="datatableheader" />			   	    	
				    <display:footer media="html">
				  </display:footer>
			<%
				j++;
			%>
	  </display:table>  
   		</td>
  	</tr>
  	<tr>
  		<td>
   		<br>
   			<center>
   			
   			<%
	   			ResultObject resObj = (ResultObject)request.getAttribute("result");
				Map map = (Map)resObj.getResultValue();
				String resultPage = (String)map.get("ResultPage");
				String receiveFlag = request.getParameter("ReceiveFlag");
   			if(request.getParameter("StatusFlag")!=null && (request.getParameter("StatusFlag").equals("CUSTCON1") || request.getParameter("StatusFlag").equals("CUSTCON2")))
			{
   				if(receiveFlag!= null && receiveFlag.equals("1"))
   				{
   			%>
   				<hdiits:button type="button" name="btnSndRtn" value='<%=buttonBundle.getString("COMMON.RETURN")%>'  onclick="returnBill()"></hdiits:button>   			
				<hdiits:button type="button" name="btnSndCntr" value='<%=buttonBundle.getString("COMMON.SENDTOPEER")%>'  onclick="sendToCounter('Peer')"></hdiits:button>   			
 				<hdiits:button type="button" name="btnDispatch" value='<%=buttonBundle.getString("COMMON.FORWARD")%>' onclick="sendToCounter('Forward')"></hdiits:button>	  			
   			<%
   				}
   				else
   				{
   				%>
   	   				<hdiits:button type="button" name="btnReceived" value='<%=buttonBundle.getString("COMMON.RECEIVE")%>' onclick="ReceiveCheques('Custody')"></hdiits:button>
   	   			<%		
   				}
   			}
   			else
   			{
   				
   				if(resultPage!= null && resultPage.equals("AcceptCheques"))
   				{   					
   			%>
   			
 	  			<hdiits:button type="button" name="btnDispatch" classcss="bigbutton" value='<%=buttonBundle.getString("ACCEPTCHEQUE.DISPCHQANDBILLS")%>' onclick="generateAdvice()"></hdiits:button>
   			<%
   				}
   				else
   				{
   			%>
   				<hdiits:button type="button" name="btnReceived" value='<%=buttonBundle.getString("COMMON.RECEIVE")%>' onclick="ReceiveCheques('Counter')"></hdiits:button>
   			<%
   				}
   			}
   			%>	
   			</center>
   		</td>
   </tr>
</table>

 	
</hdiits:form>

</body>
</html>