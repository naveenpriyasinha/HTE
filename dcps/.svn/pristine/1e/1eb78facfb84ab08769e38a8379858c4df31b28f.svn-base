<%
	try {
%>
  
<%@ include file="../../../core/include.jsp" %>
<%@page import="java.util.*"%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@page import="com.tcs.sgv.common.constant.Constants"%>

<fmt:setBundle basename="resources.hr.payincrement.PayInc" var="EPAYINLables" scope="request" />
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="common/script/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/modalDialog.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script> 
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>

<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="arMonth" value="${resultValue.arMonth}"></c:set>
<c:set var="arYear" value="${resultValue.arYear}"></c:set>
<c:set var="year" value="${resultValue.year}"></c:set>
<c:set var="month" value="${resultValue.month}"></c:set>
<c:set var="billNoList" value="${resultValue.billNoList}"></c:set>
<c:set var="empNames" value="${resultValue.empNames}"></c:set>
<c:set var="billNoStr" value="${resultValue.billNo}"></c:set>
<c:set var="incrtype" value="${resultValue.incrtype}"></c:set>


<c:set var="fixList" value="${resultValue.fixList}"></c:set>
<c:set var="fixListsize" value="${resultValue.fixListsize}"></c:set>

<c:set var="fixListWithGo" value="${resultValue.fixListWithGo}"></c:set>
<c:set var="fixListsizeWithGo" value="${resultValue.fixListsizeWithGo}"></c:set>

<c:set var="fixListWithGoUpdate" value="${resultValue.fixListWithGoUpdate}"></c:set>
<c:set var="fixListsizeWithGoUpdate" value="${resultValue.fixListsizeWithGoUpdate}"></c:set>
<c:set var="updateOrderNo" value="${resultValue.updateOrderNo}"></c:set>
<c:set var="updateOrderDate" value="${resultValue.updateOrderDate}"></c:set>


<c:set var="incrementOrderNo" value="${resultValue.incrementOrderNo}"></c:set>
<c:set var="incrementorderDate" value="${resultValue.incrementorderDate}"></c:set>

<c:set var="msg" value="${resultValue.msg}"></c:set>
<c:set var="save" value="${resultValue.save}"></c:set>
<c:set var="disableFlag" value="${resultValue.disableFlag}"></c:set>

<%
List dataList = (List) pageContext.getAttribute("fixList");
int size = 0;
if(dataList != null)
{
	size = dataList.size();
}
pageContext.setAttribute("listSize",size);

%>


<script type="text/javascript">

	if('${msg}'!=null && '${msg}'!="")
	{
	alert('${msg}');	
	}

	function disableViewList()
	{
		//alert('Inside Disable View List');
		document.getElementById('tab_new_order').style.display="";
		document.getElementById("btnViewAll").disabled=true;
		document.frmEmpPyaIncr.currentYearOrders.disabled=true;
		document.frmEmpPyaIncr.previousYearOrders.disabled=true;
		document.getElementById("addFromLeftToRight").disabled=true;
		document.getElementById("addFromRightToLeft").disabled=true;
		document.frmEmpPyaIncr.savebutton.disabled=true;
		document.frmEmpPyaIncr.cancel.disabled=true;
		document.getElementById("orderList").disabled=true;
		
	}

	function getListOfEmployeesWithGo()
 	{ 
		//document.frmEmpPyaIncr.go.disabled=true;
		
		if(document.getElementById("incrementCertificateOrderNo").value == "")
		{
			alert("Please Enter Order Number");
			document.frmEmpPyaIncr.incrementCertificateOrderNo.focus();
			return false;
		}

		if(document.frmEmpPyaIncr.orderDate.value=="")
		{
			alert("Order Date Should not be left blank !!!");
			document.frmEmpPyaIncr.orderDate.focus();
			return false;
			
		}

		if(document.frmEmpPyaIncr.orderDate.value!="")
		{
			document.frmEmpPyaIncr.go.disabled=true;
			
			order_dt_entered = document.frmEmpPyaIncr.orderDate.value.split("/");
			var date = new Date();
			var curr_date = date.getDate();
			var curr_month = date.getMonth();
			var currYear = date.getFullYear();
		
			if(parseInt(order_dt_entered[2]) != parseInt(currYear))
			{
				alert("Order Date should be of the current year !!!");
				document.frmEmpPyaIncr.orderDate.value='';
				document.frmEmpPyaIncr.orderDate.focus();
				
				return false;
			}
		}

		if(document.frmEmpPyaIncr.incrementCertificateOrderNo.value!='' && document.frmEmpPyaIncr.orderDate.value!='')
		{
		    var orderNo   = document.getElementById("incrementCertificateOrderNo").value;
		  //   alert('Order No '+orderNo);
			var orderdate =document.frmEmpPyaIncr.orderDate.value;
		    var save= "N" ;
			document.frmEmpPyaIncr.action="hrms.htm?actionFlag=showpayIncrementDataListForNewOrderNo&save="+save+"&orderNo="+orderNo+"&orderdate="+orderdate;
			document.frmEmpPyaIncr.submit();
			showProgressbar("Please wait<br>Your Request is in progress...");
        	return true;
	    }

		 else
	  	 {
			return true;
	  	 }

	}

   function checkUncheckAll(theElement)
   {
   		var theForm = theElement.form, z = 0;	
   		 for(z=0; z<theForm.length;z++)
   	 		{		 
   	  		    if(theForm[z].type == 'checkbox' && theForm[z].name == 'GroupCheck' )
   				  {
   					  theForm[z].checked = theElement.checked;
   				  }
       		 }   
   }	
   function checkUncheckAllOrder(theElement)
   {
   		var theForm = theElement.form, z = 0;	
   		 for(z=0; z<theForm.length;z++)
   		 {		 
   		      if(theForm[z].type == 'checkbox' && theForm[z].name == 'GroupCheckOrder')
   			  {
   				  theForm[z].checked = theElement.checked;
   			  }
       	 }   
   }
   function validateTextDate(obj)
	{
		 var specialchar= new Array(33,64,35,36,37,94,38,42,41,40,95,43,61,43,96,126,63);
		 var i;
		 for(i=0;i<specialchar.length;i++)
		 {
			 if(event.keyCode == specialchar[i])
			 {
				 alert("Only 0-9,/ character are allowed....");
				 event.returnValue=false;
				 
			 }
		 }

	}

   function currerntRadio()
	{

		if(document.getElementById('currentYearOrders').checked == true)
		{
			document.frmEmpPyaIncr.previousYearOrders.checked=false;
			document.getElementById("orderList").disabled=false;
		}
		
	}
	
	function previousRadio()
	{
		if(document.getElementById('previousYearOrders').checked == true)
		{
			document.frmEmpPyaIncr.currentYearOrders.checked=false;
			document.getElementById("orderList").disabled=true;
		}
	}

	function validOrderNo()
	{
		var specialChar = new Array(96,126,33,35,36,37,94,38,40,41,42,43,44,45,46,58,59,61,63,60,62,34,39,92,95,64,91,93,123,124,125);
		for (var i = 0;i<specialChar.length;i++)
		{
			if( (event.keyCode == specialChar[i]) )
			{
				alert("Only  a-z  A-Z  0-9  /  characters are allowed...!");
				event.returnValue=false;
			}
		}
	}

	function getHistory(l)
	{
		var urlstyle = 'height=1000,width=800,toolbar=no,minimize=no,resizable=no,status=yes,memubar=yes,location=no,scrollbars=yes,top=20,left=200';
		var url = './hdiits.htm?actionFlag=getIncrementPrintReport&incrementOrderNo='+l;
		window.open(url,"",urlstyle);
	}

	function validateTexrDate(name,value,i)
	{
		//alert('Inside Function validateTexrDate');
		var specialChar = new Array(96,126,33,35,36,37,94,38,40,41,42,43,44,45,46,58,59,61,63,60,62,34,39,92,95,64,91,93,123,124,125);
		 for(var k=0;k<specialChar.length;k++)
		 {
			
			 if(event.keyCode == specialChar[k])
			 {
				 alert("Only 0-9,/ character are allowed....");
				 event.returnValue=false;
				 
			 }
		 }
	}

	function validateDate(obj)
	{
		 var specialchar= new Array(33,64,35,36,37,94,38,42,41,40,95,43,61,43,96,126,63);
		 for(i=0;i<specialchar.length;i++)
		 {
			 if(event.keyCode == specialchar[i])
			 {
				 alert("Only 0-9,/ character are allowed....");
				 event.returnValue=false;
				 
			 }
		 }
	}

	function fun_attach()
	{

		var chkBoxArr=document.getElementsByName("GroupCheck"); 
		var chkLength=chkBoxArr.length;

		for(var i=0;i<chkLength;i++){ 
			if(chkBoxArr[i].checked)
			{
				AddRowInEmpListOfOrderTable();
				return true; 
			}
		} 
		alert("Please select an employee to attach to Bill group");
		return false;

	}

	function AddRowInEmpListOfOrderTable()
	{
		var color1 = "#F0F0F0";
		var color2 = "#7B68EE";

		var counterEmp = document.getElementById("counterEmp").value ;
		var counterEmpOrder = document.getElementById("counterEmpOrder").value ;
	//	alert('Total Records in Emp Table and Order Table'+document.getElementById("counterEmp").value+'And'+document.getElementById("counterEmpOrder").value);

		var empListTable = document.getElementById("empListTable");
		var empListOfOrderTable =document.getElementById("empListOfOrderTable");
		
	//	alert('Table Object'+empListOfOrderTable);

		var Cell1;
		var Cell2;
		var Cell3;
		var Cell4;
		var Cell5;
		var Cell6;
		var Cell7;
		var	Cell8;
		var Cell9;
		
	//	var Cell11;
		var percent3=0;
		var basicTemp=document.createElement('input');
		var newBasic="";
		var tempScaleEndAmt=document.createElement('input');
		var counter = 1 ;

		var empIdsToBeAddedToOrderTable = new Array() ;
		var empNamesToBeAddedToOrderTable = new Array() ;
		var empBasicToBeAddedToOrderTable = new Array() ;
		var empWEFToBeAddedToOrderTable = new Array() ;
		var empNextIncDateToBeAddedToOrderTable = new Array() ;
		var empRemarksToBeAddedToOrderTable = new Array() ;
		var empScaleEndAmtToBeAddedToOrderTable=new Array();
		var empIncOrderDateBackup=new Array();
		var selectedEmpIds = new Array();
		var totalSelectedEmpIds=0;
		var empOrigBasicOrder=new Array();
		
		for(var i=1;i<=counterEmp;i++)
		{
			if(document.getElementById("GroupCheck"+i).checked)
			{
				//alert('Inside if of Group Check'+i);
				basicTemp.value=document.getElementById("origBasic"+i).innerText;
				var oldBasic=basicTemp.value;
				tempScaleEndAmt.value=document.getElementById("scaleEndAmt"+i).innerText;
				var scaleEndAmt=tempScaleEndAmt.value;
				percent3 =eval((parseInt(oldBasic)/100) * 3);
				newBasic = "";
				percent3 = parseInt(percent3);
				if((percent3)%10!=0)
				{
					percent3=eval(parseInt(percent3)/10);
					percent3=eval(parseInt(percent3) + 1);
					percent3=eval(parseInt(percent3) * 10);
					
				}
				newBasic = Math.round((eval(percent3) + (parseInt(oldBasic))));
				
		//		alert('After Getting all calc newBasic and Scale Amount are:'+parseInt(newBasic)+' and '+parseInt(scaleEndAmt));
				if(parseInt(newBasic) > parseInt(scaleEndAmt))
				{
					alert('Pay in Pay band has exceeded the Upper limit of the Pay Band.');
					document.getElementById("GroupCheck"+i).checked=false;

					break;
				}
				else
				{
			//		alert('Inside if of Scale Amount');
					empIdsToBeAddedToOrderTable[counter]=document.createElement('input');
					empIdsToBeAddedToOrderTable[counter].value = document.getElementById("GroupCheck"+i).value ;
			//		alert('Value1'+empIdsToBeAddedToOrderTable[counter].value);
					
					empNamesToBeAddedToOrderTable[counter]=document.createElement('input');
					empNamesToBeAddedToOrderTable[counter].value = document.getElementById("empName"+i).innerText;
			//		alert('Value2'+empNamesToBeAddedToOrderTable[counter].value);

					empBasicToBeAddedToOrderTable[counter]=document.createElement('input');
					empBasicToBeAddedToOrderTable[counter].value = newBasic ;
			//		alert('Value3'+empBasicToBeAddedToOrderTable[counter].value);

					empIncOrderDateBackup[counter]=document.createElement('input');
					empIncOrderDateBackup[counter].value = document.getElementById("incOrderDate"+i).innerText ;
			//		alert('Backup of incOrderDate'+empIncOrderDateBackup[counter].value);
					
					
					empWEFToBeAddedToOrderTable[counter]=document.createElement('input');
					var tempDate=document.getElementById("nextIncrDt"+i).innerText;
					//alert('tempDate--'+tempDate.value);
					var tempYear=tempDate.split("/")[2];
					//alert('tempYear--'+tempYear.value);
					tempYear=parseInt(tempYear)-1;
					//alert('tempYear--'+tempYear.value);
					if(tempDate=="")
					{
						tempYear=getCurrentYear();
					}	
					tempDate=""+"01"+"/"+"07"+"/"+tempYear;
					//alert('tempDate--'+tempDate.value);
					empWEFToBeAddedToOrderTable[counter].value = tempDate ;
			//		alert('Value4'+empWEFToBeAddedToOrderTable[counter].value);

					empNextIncDateToBeAddedToOrderTable[counter]=document.createElement('input');
					empNextIncDateToBeAddedToOrderTable[counter].value=document.getElementById("nextIncrDt"+i).innerText ;
					if(empNextIncDateToBeAddedToOrderTable[counter].value=="")
					{
						var currYear=getCurrentYear();
						currYear+=1;
						empNextIncDateToBeAddedToOrderTable[counter].value="01/07/"+currYear;
					}
			//		alert('Value5'+empNextIncDateToBeAddedToOrderTable[counter].value);

					empScaleEndAmtToBeAddedToOrderTable[counter]=document.createElement('input');
					empScaleEndAmtToBeAddedToOrderTable[counter].value=document.getElementById("scaleEndAmt"+i).innerText ;
			//		alert('Value6'+empScaleEndAmtToBeAddedToOrderTable[counter].value);

					empRemarksToBeAddedToOrderTable[counter]=document.createElement('input');					
					empRemarksToBeAddedToOrderTable[counter].value=document.getElementById("remarks"+i).innerText ;
			//		alert('Value7'+empRemarksToBeAddedToOrderTable[counter].value);
					
					empOrigBasicOrder[counter]=document.createElement('input');					
					empOrigBasicOrder[counter].value=document.getElementById("origBasic"+i).innerText ;
					
					totalSelectedEmpIds= totalSelectedEmpIds + 1 ; 
					//alert('Value8--'+totalSelectedEmpIds);
					selectedEmpIds[counter] = i ;
					counter++ ;

				}
				
			}
		}
		//alert('Record Fetched for No Of Emp---'+totalSelectedEmpIds);

		for(i=1;i<=totalSelectedEmpIds;i++)
		{
			counterEmpOrder = Number(counterEmpOrder) + 1 ;
			//alert('counterEmpOrder--'+counterEmpOrder);
			var tempLength=empListOfOrderTable.rows.length;
		//	alert('Before Cell Declaration'+tempLength);
			var newRow = empListOfOrderTable.insertRow(tempLength);
		//	alert('Row Inserted');
			newRow.style.backgroundColor = color1;
			newRow.style.borderColor = color2;
		//	alert('Style Defined');
			Cell1 = newRow.insertCell(0);
			Cell2 = newRow.insertCell(1);
			Cell3 = newRow.insertCell(2);
			Cell4 = newRow.insertCell(3);
			Cell5 = newRow.insertCell(4);
			Cell6 = newRow.insertCell(5);
			Cell7 = newRow.insertCell(6);
			Cell8 = newRow.insertCell(7);
			Cell9 = newRow.insertCell(8);
			Cell1.align="center";
		   	Cell2.align="center";
		   	Cell3.align="center";
		   	Cell4.align="center";
		   	Cell5.align="center";
		   	Cell6.align="center";
		   	Cell7.align="center";
		   	

	//	    alert('Cells Declared and adding1'+empIdsToBeAddedToOrderTable[i].value);
		 	Cell1.innerHTML = '<input type="checkbox" name="GroupCheckOrder" id="GroupCheckOrder'+counterEmpOrder+'" value="'+empIdsToBeAddedToOrderTable[i].value+'" />' ;
	//	 	alert('Step1 done and now adding'+empNamesToBeAddedToOrderTable[i].value);
		 	Cell2.innerHTML = '<label id="empNameOrder'+counterEmpOrder+'">'+empNamesToBeAddedToOrderTable[i].value+'</label>';
	//	 	alert('Step2 done and adding'+empNamesToBeAddedToOrderTable[i].value);
		    Cell3.innerHTML = '<label id="empBasicOrder'+counterEmpOrder+'">'+empBasicToBeAddedToOrderTable[i].value+'</label>';
	//	    alert('Step3 done and adding'+empWEFToBeAddedToOrderTable[i].value);		    
		//  Cell4.innerHTML = '<label id="empWEFOrder'+counterEmpOrder+'">'+empWEFToBeAddedToOrderTable[i].value+'</label>' ;
		    var WEF = document.createElement('input');
			WEF.type = 'text';
			WEF.size='8';
			WEF.id = 'empWEFOrder' + counterEmpOrder;
			WEF.name = 'empWEFOrder' + counterEmpOrder;
			WEF.readOnly = "";
			WEF.value = empWEFToBeAddedToOrderTable[i].value;
			Cell4.appendChild(WEF);
			  
	//	    alert('Step4 done and adding'+empNextIncDateToBeAddedToOrderTable[i].value);
			Cell5.innerHTML = '<label id="nextIncrDtOrder'+counterEmpOrder+'">'+empNextIncDateToBeAddedToOrderTable[i].value+'</label>';
	//	    alert('Step5 done and adding'+empRemarksToBeAddedToOrderTable[i].value);
		//  Cell6.innerHTML = '<label id="remarksOrder'+counterEmpOrder+'">'+empRemarksToBeAddedToOrderTable[i].value+'</label>';
	//	    alert('Step6 done and adding'+empScaleEndAmtToBeAddedToOrderTable[i].value);
			var remarks=document.createElement('input');
			remarks.type = 'text';
			remarks.id = 'remarksOrder' + counterEmpOrder;
			remarks.name = 'remarksOrder' + counterEmpOrder;
			remarks.size='8';
			remarks.readOnly = "";
			remarks.value = "Enter Remarks";
			Cell6.appendChild(remarks);
			Cell7.innerHTML = '<label id="scaleEndAmtOrder'+counterEmpOrder+'" style="display:none">'+empScaleEndAmtToBeAddedToOrderTable[i].value+'</label>';
		 	Cell8.innerHTML='<label id="IncOrderDateOrder'+counterEmpOrder+'" style="display:none" >'+empIncOrderDateBackup[i].value+'</label>';
		 	Cell9.innerHTML='<label id="origBasicOrder'+counterEmpOrder+'" style="display:none" >'+empOrigBasicOrder[i].value+'</label>';
		 	
					 	
			document.getElementById("counterEmpOrder").value = Number(document.getElementById("counterEmpOrder").value) + 1 ;
	//		alert('Step7 done and adding'+empIncOrderDateBackup[i].value);
	
		    document.getElementById("empIdstoBeAttached").value = document.getElementById("empIdstoBeAttached").value +  empIdsToBeAddedToOrderTable[i].value + "~" ;
		    document.getElementById("empIdstoBeDetached").value = document.getElementById("empIdstoBeDetached").value.replace(empIdsToBeAddedToOrderTable[i].value + "~","");

		    document.getElementById("empBasicSalarytoBeAttached").value = document.getElementById("empBasicSalarytoBeAttached").value +  empBasicToBeAddedToOrderTable[i].value + "~" ;
		    document.getElementById("empBasicSalarytoBeDetached").value = document.getElementById("empBasicSalarytoBeDetached").value.replace(empBasicToBeAddedToOrderTable[i].value + "~","");

		    document.getElementById("empWEFtoBeAttached").value = document.getElementById("empWEFtoBeAttached").value +  empWEFToBeAddedToOrderTable[i].value + "~" ;
		    document.getElementById("empWEFtoBeDetached").value = document.getElementById("empWEFtoBeDetached").value.replace(empWEFToBeAddedToOrderTable[i].value + "~","");

		    document.getElementById("empNextIncrDatetoBeAttached").value = document.getElementById("empNextIncrDatetoBeAttached").value +  empNextIncDateToBeAddedToOrderTable[i].value + "~" ;
		    document.getElementById("empNextIncrDatetoBeDetached").value = document.getElementById("empNextIncrDatetoBeDetached").value.replace(empNextIncDateToBeAddedToOrderTable[i].value + "~","");

		    document.getElementById("empRemarkstoBeAttached").value = document.getElementById("empRemarkstoBeAttached").value +  empRemarksToBeAddedToOrderTable[i].value + "~" ;
		    document.getElementById("empRemarkstoBeDetached").value = document.getElementById("empRemarkstoBeDetached").value.replace(empRemarksToBeAddedToOrderTable[i].value + "~","");


		    document.getElementById("empIncOrderDatetoBeAttached").value = document.getElementById("empIncOrderDatetoBeAttached").value +  empIncOrderDateBackup[i].value + "~" ;
		    document.getElementById("empIncOrderDatetoBeDetached").value = document.getElementById("empIncOrderDatetoBeDetached").value.replace(empIncOrderDateBackup[i].value + "~","");

		    document.getElementById("origBasictoBeAttached").value = document.getElementById("origBasictoBeAttached").value +  empOrigBasicOrder[i].value + "~" ;
		    document.getElementById("origBasictoBeDetached").value = document.getElementById("origBasictoBeDetached").value.replace(empOrigBasicOrder[i].value + "~","");
		    

		//    alert('empBasicSalarytoBeAttached'+document.getElementById("empBasicSalarytoBeAttached").value);
		//    alert('empBasicSalarytoBeDetached'+document.getElementById("empBasicSalarytoBeDetached").value);
		//    alert('WEF Attached'+ document.getElementById("empWEFtoBeAttached").value);
		//    alert('WEF Detached'+ document.getElementById("empWEFtoBeDetached").value);
		    
		//	   alert('Insertion in Order List Done for Emp'+empIdsToBeAddedToOrderTable[i].value);
		}
		//alert('CounterEmp--'+counterEmp);
		for(i=counterEmp;i>=1;i--)
		{
			//alert('Inside Hiding Loop with value of i----'+i);
			if(document.getElementById("GroupCheck"+i).checked)
			{
				//alert('Matching Value--'+i);
				empListTable.rows[eval(i)].style.display = 'none' ;
				document.getElementById("GroupCheck"+i).checked = false ;
			}
		}
	}
	


	function fun_detach()
	{

		var chkBoxArr=document.getElementsByName("GroupCheckOrder"); 
		var chkLength=chkBoxArr.length;
	//	alert('Length'+chkLength);
		for(var i=0;i<chkLength;i++){ 
			if(chkBoxArr[i].checked)
			{
				AddRowInEmpListTable();
				return true; 
			}
		} 
		alert("Please select an employee to attach to Bill group");
		return false;

	}


	function AddRowInEmpListTable()
	{
		//alert('Before Varibale Dec');
		var color1 = "#F0F0F0";
		var color2 = "#7B68EE";
		
		var counterEmp = document.getElementById("counterEmp").value ;
		var counterEmpOrder = document.getElementById("counterEmpOrder").value ;
	//	alert('Total Records in Emp Table and Order Table'+document.getElementById("counterEmp").value+'And'+document.getElementById("counterEmpOrder").value);
		
		var empIdsToBeAddedToEmpTable = new Array() ;
		var empNamesToBeAddedToEmpTable = new Array() ;
		var empBasicToBeAddedToEmpTable = new Array() ;
		var empWEFToBeAddedToEmpTable = new Array() ;
		var empScaleEndAmtToBeAddedToEmpTable = new Array() ;
		var empNextIncDateToBeAddedToEmpTable= new Array();
		var empRemarksToBeAddedToEmpTable = new Array() ;
		var empIncOrderDateBackupOrder = new Array() ;
		var empOrigBasic=new Array();
		
		var selectedEmpIds = new Array();
		var totalSelectedEmpIds=0;
		var newRow;
		var Cell0;
		var Cell1;
		var Cell2;
		var Cell3;
		var Cell4;
		var Cell5;
		var Cell6;
		var Cell7;
		var Cell8;
		var percent3=0;
		var newBasic;
		var tempScaleEndAmt=document.createElement('input');
		var counter = 1 ;
		var basicTemp=document.createElement('input');;
		
		var empListTable = document.getElementById("empListTable");
		var empListOfOrderTable =document.getElementById("empListOfOrderTable");
	//	alert('Object of empListOfOrderTable'+empListOfOrderTable);

	//	alert('After Varibale Dec');
		
		for(var i=1;i<=counterEmpOrder;i++)
		{
			if(document.getElementById("GroupCheckOrder"+i).checked)
			{
			//	alert('Inside checkbox validation if--'+i);
				basicTemp.value=document.getElementById("origBasicOrder"+i).innerText;
			//	alert('New Basic'+basicTemp);
				var oldBasic=basicTemp.value;
				tempScaleEndAmt.value=document.getElementById("scaleEndAmt"+i).innerText;
				var scaleEndAmt=tempScaleEndAmt.value;
				
				/*percent3 =eval((parseInt(oldBasic)/100) * 3);
				newBasic = "";
				percent3 = parseInt(percent3);
				if((percent3)%10!=0)
				{
					percent3=eval(parseInt(percent3)/10);
					percent3=eval(parseInt(percent3) + 1);
					percent3=eval(parseInt(percent3) * 10);
					
				}*/
				newBasic = parseInt(oldBasic);

		//		alert('New Basic Calculated'+parseInt(newBasic));
		//		alert('Selected Emp Id with checkbox'+document.getElementById("GroupCheckOrder"+i).value);
				
				empIdsToBeAddedToEmpTable[counter]=document.createElement('input');
				empIdsToBeAddedToEmpTable[counter].value = document.getElementById("GroupCheckOrder"+i).value ;
		//		alert('Value1'+empIdsToBeAddedToEmpTable[counter].value);
					

				empNamesToBeAddedToEmpTable[counter]=document.createElement('input');
				empNamesToBeAddedToEmpTable[counter].value = document.getElementById("empNameOrder"+i).innerText;
		//		alert('Value2'+empNamesToBeAddedToEmpTable[counter].value);
					
				empBasicToBeAddedToEmpTable[counter]=document.createElement('input');
				empBasicToBeAddedToEmpTable[counter].value = document.getElementById("empBasicOrder"+i).innerText ;
			//	alert('Value3'+empBasicToBeAddedToEmpTable[counter].value);
			
				empWEFToBeAddedToEmpTable[counter]=document.createElement('input');
				empWEFToBeAddedToEmpTable[counter].value = document.getElementById("empWEFOrder"+i).value ;
		//		alert('Value4'+empWEFToBeAddedToEmpTable[counter].value);
					
				empNextIncDateToBeAddedToEmpTable[counter]=document.createElement('input');
				empNextIncDateToBeAddedToEmpTable[counter].value=document.getElementById("nextIncrDtOrder"+i).innerText ;
		//		alert('Value5'+empNextIncDateToBeAddedToEmpTable[counter].value);
				
				empScaleEndAmtToBeAddedToEmpTable[counter]=document.createElement('input');
				empScaleEndAmtToBeAddedToEmpTable[counter].value=document.getElementById("scaleEndAmtOrder"+i).innerText ;
		//		alert('Value6'+empScaleEndAmtToBeAddedToEmpTable[counter].value);
				
				
				empRemarksToBeAddedToEmpTable[counter]=document.createElement('input');					
				empRemarksToBeAddedToEmpTable[counter].value=document.getElementById("remarks"+i).innerText ;
		//		alert('Value7'+empRemarksToBeAddedToEmpTable[counter].value);

				empIncOrderDateBackupOrder[counter]=document.createElement('input');
				empIncOrderDateBackupOrder[counter].value = document.getElementById("IncOrderDateOrder"+i).innerText ;
		//		alert('Value4'+empIncOrderDateBackupOrder[counter].value);
					
				empOrigBasic[counter]=document.createElement('input');
				empOrigBasic[counter].value = document.getElementById("origBasicOrder"+i).innerText ;
				
				totalSelectedEmpIds= totalSelectedEmpIds + 1 ; 
			//	alert('All values calculated and Value8'+totalSelectedEmpIds);
				
					selectedEmpIds[counter] = i ;
					counter++ ;
				
			}
		}

		//alert('Record Fetched for No Of Emp---'+totalSelectedEmpIds);
		
		for(i=1;i<=totalSelectedEmpIds;i++)
		{
			counterEmp = Number(counterEmp) + 1 ;
			newRow = empListTable.insertRow(empListTable.rows.length);
			newRow.style.backgroundColor = color1;
			newRow.style.borderColor = color2;

			
			Cell1 = newRow.insertCell(-1);
			Cell7 = newRow.insertCell(-1);
			Cell2 = newRow.insertCell(-1);
			Cell3 = newRow.insertCell(-1);
			Cell4 = newRow.insertCell(-1);
			Cell5 = newRow.insertCell(-1);
			Cell6 = newRow.insertCell(-1);
			Cell0 = newRow.insertCell(-1);
			Cell8 = newRow.insertCell(-1);

			Cell0.align="center";
			Cell1.align="center";
			Cell2.align="center";
		   	Cell3.align="center";
		   	Cell4.align="center";
		   	Cell5.align="center";
		   	Cell6.align="center";
			Cell7.align="center";
			Cell8.align="center";

	
	//		alert('empIdsToBeAddedToEmpTable'+empIdsToBeAddedToEmpTable[i].value);
		   	Cell1.innerHTML = '<input type="checkbox" name="GroupCheck" id="GroupCheck'+counterEmp+'" value="'+empIdsToBeAddedToEmpTable[i].value+'" />' ;
		   	Cell7.innerHTML = '<label id="userId'+counterEmp+'">'+empIdsToBeAddedToEmpTable[i].value+'</label>' ;
		   	Cell2.innerHTML = '<label id="empName'+counterEmp+'">'+empNamesToBeAddedToEmpTable[i].value+'</label>' ;
		    Cell3.innerHTML = '<label id="empBasic'+counterEmp+'">'+empOrigBasic[i].value+'</label>' ;
		    Cell4.innerHTML = '<label id="incOrderDate'+counterEmp+'">'+empIncOrderDateBackupOrder[i].value+'</label>' ;
		    Cell5.innerHTML = '<label id="nextIncrDt'+counterEmp+'" style="display:none" >'+empNextIncDateToBeAddedToEmpTable[i].value+'</label>' ;
		    Cell6.innerHTML = '<label id="scaleEndAmt'+counterEmp+'" style="display:none" >'+empScaleEndAmtToBeAddedToEmpTable[i].value+'</label>' ;
		    Cell0.innerHTML = '<label id="remarks'+counterEmp+'">'+empRemarksToBeAddedToEmpTable[i].value+'</label>' ;
		    Cell8.innerHTML = '<label id="origBasic'+counterEmp+'" style="display:none">'+empOrigBasic[i].value+'</label>';
		    
		    document.getElementById("counterEmp").value = Number(document.getElementById("counterEmp").value) + 1 ;
	//	    alert('Record Successfully Inserted in Emp Table And Count is'+document.getElementById("counterEmp").value);
	//	    alert('Total Records in Emp Table and Order Table'+document.getElementById("counterEmp").value+'And'+document.getElementById("counterEmpOrder").value);
		    
		    document.getElementById("empIdstoBeDetached").value = document.getElementById("empIdstoBeDetached").value +  empIdsToBeAddedToEmpTable[i].value + "~" ;
		    document.getElementById("empIdstoBeAttached").value = document.getElementById("empIdstoBeAttached").value.replace(empIdsToBeAddedToEmpTable[i].value + "~","") ;

		    document.getElementById("empBasicSalarytoBeDetached").value = document.getElementById("empBasicSalarytoBeDetached").value +  empBasicToBeAddedToEmpTable[i].value + "~" ;
		    document.getElementById("empBasicSalarytoBeAttached").value = document.getElementById("empBasicSalarytoBeAttached").value.replace(empBasicToBeAddedToEmpTable[i].value + "~","") ;

		    document.getElementById("empWEFtoBeDetached").value = document.getElementById("empWEFtoBeDetached").value +  empWEFToBeAddedToEmpTable[i].value + "~" ;
		    document.getElementById("empWEFtoBeAttached").value = document.getElementById("empWEFtoBeAttached").value.replace(empWEFToBeAddedToEmpTable[i].value + "~","") ;

		    document.getElementById("empNextIncrDatetoBeDetached").value = document.getElementById("empNextIncrDatetoBeDetached").value +  empNextIncDateToBeAddedToEmpTable[i].value + "~" ;
		    document.getElementById("empNextIncrDatetoBeAttached").value = document.getElementById("empNextIncrDatetoBeAttached").value.replace(empNextIncDateToBeAddedToEmpTable[i].value + "~","") ;

		    document.getElementById("empRemarkstoBeDetached").value = document.getElementById("empRemarkstoBeDetached").value +  empRemarksToBeAddedToEmpTable[i].value + "~" ;
		    document.getElementById("empRemarkstoBeAttached").value = document.getElementById("empRemarkstoBeAttached").value.replace(empRemarksToBeAddedToEmpTable[i].value + "~","") ;

		    document.getElementById("empIncOrderDatetoBeDetached").value = document.getElementById("empIncOrderDatetoBeDetached").value +  empIncOrderDateBackupOrder[i].value + "~" ;
		    document.getElementById("empIncOrderDatetoBeAttached").value = document.getElementById("empIncOrderDatetoBeAttached").value.replace(empIncOrderDateBackupOrder[i].value + "~","");

		    document.getElementById("origBasictoBeDetached").value = document.getElementById("origBasictoBeDetached").value +  empOrigBasic[i].value + "~" ;
		    document.getElementById("origBasictoBeAttached").value = document.getElementById("origBasictoBeAttached").value.replace(empOrigBasic[i].value + "~","");

		//    alert('empBasicSalarytoBeAttached'+document.getElementById("empBasicSalarytoBeAttached").value);
		 //   alert('empBasicSalarytoBeDetached'+document.getElementById("empBasicSalarytoBeDetached").value);

	//	    alert('empIdstoBeDetached'+document.getElementById("empIdstoBeDetached").value);
	//	    alert('WEF Attached'+ document.getElementById("empWEFtoBeAttached").value);
	//	    alert('WEF Detached'+ document.getElementById("empWEFtoBeDetached").value);
		    
		}


		for(i=counterEmpOrder;i>=1;i--)
		{
		//	alert('Inside Hiding Loop with value of i'+i);
		//	alert('value i+1---'+(i+1));

			if(document.getElementById("GroupCheckOrder"+i).checked)
			{
		//		alert('Matching Record Number---Emp Id---'+i+'---'+document.getElementById("GroupCheckOrder"+i).value);
			
				document.getElementById("GroupCheckOrder"+i).checked = false ;
		//		alert('Unchecking Stmt');

				if(document.getElementById("sizeOfOrderList").value>0)
				{
					//code for existing order
					empListOfOrderTable.rows[eval(i)].style.display = 'none' ;
					
				}
				else
				{
					//code for new order 
					empListOfOrderTable.rows[eval(i)+1].style.display = 'none' ;
				}
				
				
			//	alert('Hiding Stmt');
			}
		}
			
	}


	

	function saveData()
	{
	//	alert('Inside save data');
		
		if(document.getElementById("empIdstoBeDetached").value == "" && document.getElementById("empIdstoBeAttached").value == ""){
			alert("No data is Saved, as no change has been made");
			return;
		}
		
		var empIdstoBeDetached = document.getElementById("empIdstoBeDetached").value ;
		var empIdstoBeAttached = document.getElementById("empIdstoBeAttached").value ;
		var empBasicSalarytoBeAttached=document.getElementById("empBasicSalarytoBeAttached").value ;
		var empInputTagElements=new Array();
		var empNextIncrDatetoBeAttached=document.getElementById("empNextIncrDatetoBeAttached").value ;
		var empRemarkstoBeAttached=document.getElementById("empRemarkstoBeAttached").value;
		var empIncOrderDatetoBeAttached=document.getElementById("empIncOrderDatetoBeAttached").value ;
		var empWEFtoBeAttached=document.getElementById("empWEFtoBeAttached").value ;
		var empOrigBasictoBeAttached=document.getElementById("origBasictoBeAttached").value ;
		
		
		var orderDate = document.getElementById('incrementorderDate').value;
		var orderNo=document.getElementById('incrementOrderNo').value;
		var tempDay;
		var tempMonth;
		var tempYear;
		var stdDay="01";
		var stdMonth="07";

		var counterEmpOrder1 = document.getElementById("counterEmpOrder").value ;
		var empListOfOrderTable = document.getElementById("empListOfOrderTable");

		var save = document.getElementById("save").value;
		var WEFFlag=false;
		var currYear;
		
		var elements=empListOfOrderTable.getElementsByTagName("input");
		//alert('counterEmpOrder---'+counterEmpOrder1+'elements with input tag---'+elements.length);
	//	alert('Before Checking save Flag');
		
		var j=0;
		if(save=='N')
		{
		
			//code for new Order
			//alert('value of save'+document.getElementById('save').value);
			for(var i=0;i<elements.length;i+=3)
			{
			//	alert(elements[i].value);
				empInputTagElements[j]=document.createElement('input');
				empInputTagElements[j].value=elements[i].value;
				j+=1;
			//	alert(elements[i+1].value);
				empInputTagElements[j]=document.createElement('input');
				empInputTagElements[j].value=elements[i+1].value;
				j+=1;
			//	alert(elements[i+2].value);
				empInputTagElements[j]=document.createElement('input');
				
				tempDay=elements[i+1].value.split("/")[0];
				tempMonth=elements[i+1].value.split("/")[1];
				tempYear=elements[i+1].value.split("/")[2];
				currYear=getCurrentYear();

		//		alert('comparing values--'+tempDay+tempMonth+tempYear+currYear);
				if(tempDay==stdDay && tempMonth==stdMonth && tempYear==currYear)
				{
					WEFFlag=true;
				}
				else 
				{
					if(elements[i+2].value=="")
					{
						WEFFlag=false;
						break;
					}
					else
						WEFFlag=true;
				}	
				
				if(elements[i+2].value!="" || elements[i+2].value=="Enter Remarks")
					empInputTagElements[j].value=elements[i+2].value;
				
				else
					empInputTagElements[j].value="Enter Remarks";
				j+=1;
			//	alert('empInputTagElements j'+empInputTagElements+j);
			
			}
		}

	//	alert('After Checking save Flag is not NNNN');
		
		if(save=='view')
		{
			j=0;
			//code for existing order
			//alert('value of save'+document.getElementById('save').value);
			for(i=0;i<elements.length;i+=3)
			{
			//	alert(elements[i].value);
				empInputTagElements[j]=document.createElement('input');
				empInputTagElements[j].value=elements[i].value;
				j+=1;
			//	alert(elements[i+1].value);
				empInputTagElements[j]=document.createElement('input');
				
				empInputTagElements[j].value=elements[i+1].value;
			//	alert('WEF DATE'+elements[i+1].value);
				j+=1;
			//	alert(i+2);
			//	alert(elements[i+2].value);
				
				tempDay=elements[i+1].value.split("/")[0];
				tempMonth=elements[i+1].value.split("/")[1];
				tempYear=elements[i+1].value.split("/")[2];
				currYear=getCurrentYear();

				//alert('comparing values--'+tempDay+tempMonth+tempYear+currYear);

				if(tempDay==stdDay && tempMonth==stdMonth && tempYear==currYear)
				{
					WEFFlag=true;
				}
				else 
				{
					if(elements[i+2].value=="" || elements[i+2].value=="Enter Remarks")
					{
						WEFFlag=false;
						break;
					}
					else
						WEFFlag=true;
				}
				
				empInputTagElements[j]=document.createElement('input');
				if(elements[i+2].value!="")
					empInputTagElements[j].value=elements[i+2].value;
				else
					empInputTagElements[j].value="Enter Remarks";
				j+=1;
			
		//		alert('empInputTagElements j'+empInputTagElements+j);
			}
		//	alert('Exiting If');
		}
		document.getElementById("inputTagElements").value ="";
		for(i=0;i<j;i++)
		{
			document.getElementById("inputTagElements").value = document.getElementById("inputTagElements").value +  empInputTagElements[i].value + "~" ;
		}
		var inputTagElements=document.getElementById("inputTagElements").value;
	//	alert('Order No&Date'+orderNo+'-'+orderDate);
				
		save="Yes";
		

	//	alert('empIdstoBeDetached inside save data'+empIdstoBeDetached);
		if(WEFFlag==true)
		{
			document.frmEmpPyaIncr.savebutton.disabled=true;
			uri = "hrms.htm?actionFlag=showpayIncrementDataListForNewOrderNo";
			url="orderNo="+ orderNo
				+ "&orderdate=" + orderDate
				+"&save="+save
				+"&empIdstoBeAttached="+empIdstoBeAttached
				+"&empBasicSalarytoBeAttached="+empBasicSalarytoBeAttached
				+"&empWEFtoBeAttached="+empWEFtoBeAttached
				+"&empNextIncrDatetoBeAttached="+empNextIncrDatetoBeAttached
				+"&empRemarkstoBeAttached="+empRemarkstoBeAttached
				+"&empIdstoBeDetached="+empIdstoBeDetached
				+"&empIncOrderDatetoBeAttached="+empIncOrderDatetoBeAttached
				+"&inputTagElements="+inputTagElements
				+"&empOrigBasictoBeAttached="+empOrigBasictoBeAttached;

			//document.frmEmpPyaIncr.submit();

	//		alert('URI Declared');
				
			var myAjax = new Ajax.Request(uri,
				       {
				        method: 'post',
				        asynchronous: false,
				        parameters:url,
				        onSuccess: function(myAjax) {
						getDataStateChangedForModifyOrder(myAjax);
						},
			    	    onFailure: function(){ alert('Something went wrong...')} 
			        	  } );
		}
		else
			alert('WEF Date has been changed, So Kindly Insert WEF in proper format OR Enter Remarks at appropriate Place');
		
			
			
	}

	function getDataStateChangedForModifyOrder(myAjax)
	{
		alert('Data Successfully Inserted.');
		/*XMLDoc = myAjax.responseXML.documentElement;
		var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
		var test_Id = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
		
		if(test_Id)
		{
			alert("Order Modified Successfully");
		}*/
		document.frmEmpPyaIncr.action = "./hrms.htm?actionFlag=showpayIncrementDataListForNewOrderNo";	
		document.frmEmpPyaIncr.submit();
	}

	function fun_cancel() 
	{
		document.frmEmpPyaIncr.action="./hrms.htm?actionFlag=getHomePage";
		document.frmEmpPyaIncr.submit();

	}
	function getCurrentYear()
	{
		var tempDate=new Date();
		var tempYear=tempDate.getFullYear();
//		alert('Got Current  Year'+tempYear);
		return tempYear;
	}

	function getCurrentMonth()
	{
		var tempDate=new Date();
		var tempMon=tempDate.getMonth();
//		alert('Got Current  Year'+tempYear);
		return tempMon;
	}
	function getOrderNo()
	{	
		 var length = '${listSize}';
		 var orderNo=0;
		 var check = true;
		if(length.value==0)
		 {
	 	 	return false;
		 }
		for(var i = 1; i <= length ; i++)
		 {
			 //alert('Inside For');
			 if(document.getElementById("radioId"+i).checked)
			 {
				  check = false;
				  orderNo=document.getElementById("tnrOrderNoId"+i).value;
				  break;
			 }
		 }
		 if(check)
		 {
			 alert("Please Select The Radio Button");
			 document.TokenNumber.chkbox.focus();
			 return false;
		 }	
		 
		  return orderNo;
	}

	function verifyOrder()
	{
		var orderNo = getOrderNo() ; 
			
		if(orderNo=="")
		{
			alert('Order Not Found');
			retValue=false;
					
		}
		else
		{
			try 
			{   
				xmlHttp=new XMLHttpRequest();
			}
			catch(e)
			{    
			// Internet Explorer    
				try 
				{
	    			xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");   
				} 
	    		catch (e) 
	    		{
		 			try 
		 			{
	       	 			xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
		   			}
					catch (e) 
					{
						alert("Your browser does not support AJAX!");        
					    retValue=false;     
					}
				}
			}
			var tempMon=getCurrentMonth();
			var tempYear=getCurrentYear();
			var answer = confirm (" Are You sure ? Verify Order will reflect from next Paybill Generation.");
			
			if(answer)
			{
				var url = "ifms.htm?actionFlag=verifyOrderDetails&orderNo="+orderNo;
	  	
				xmlHttp.onreadystatechange = function(){		
					if(xmlHttp.readyState == 4){     			
						if (xmlHttp.status == 200){	
							var LoanNo;
							var XMLDocForAjax=xmlHttp.responseXML.documentElement;
							var statusFlag = XMLDocForAjax.getElementsByTagName('paybillMapping');	
					
							if(statusFlag.length != 0)
							{
				   				var statFlag = statusFlag[0].childNodes[0].text;
								if(statFlag=="1")
								{
									alert("Order is Successfully Verified.");
									url = "./hrms.htm?actionFlag=showpayIncrementDataListForNewOrderNo";
									document.frmEmpPyaIncr.action=url;
									document.frmEmpPyaIncr.submit();
					
									return false;
								}
							else
							{
								alert("There is some problem related to inner paybill");
								return true;
							}
							}
						}
					}
				}
			
			
				

				xmlHttp.open("POST", encodeURI(url) , false);    
				xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
				xmlHttp.send(encodeURIComponent(null));	
			}
			else
				{}
			}

	}

	
	var iteration = 100000;
	var counterLength = '';
	var j = 0;
	
  </script>
   
  

 


	<hdiits:form name="frmEmpPyaIncr" validate="true" method="post" action="" >
		<div id="tabmenu">
			<ul id="maintab" class="shadetabs" >
				<li class="selected" ><a href="#" rel="tcontent1"><b><fmt:message key="EMP_PAY_INCR" bundle="${EPAYINLables}"/></b></a></li>
			</ul>
		</div>
		<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >
			<table align="right" >
				<tr>
					<td width='85%'>
						 <font size='2' face='arial' color='red'>
							<!-- Note <b>:</b> Annual Increment details entered through this form will be reflected in the Service Book -->
	 		 			</font>
					</td>
					<td align="center" >
	 					<hdiits:button name="btnSearch" type="button" captionid="PC.ADDNEWORDER" bundle="${commonLables}" style="width:70%" onclick="disableViewList();"/>
					</td>
					<td align="center" style="display:none" >
						<hdiits:button  name="btnViewAll" type="button" captionid="PC.FORWARD" bundle="${commonLables}" />
					</td>
					<td>
						<hdiits:button type="button" name="Verify Order" value="Verify Order" onclick="return verifyOrder();"  />
					</td>
				</tr>	
			</table>	
		 	<br/>
  			<table width="60%" id='tab_new_order'  align="left" style="display: none" >
				<tr> 
					<td  width="2%"></td>
					<TD  align="left" width="8%"> </TD>
					<td><b><fmt:message key="PC.Increment" bundle="${commonLables}"/></b></TD>
			
					<%-- <td ><hdiits:number name="incrementCertificateOrderNo"   mandatory="true"  caption="PC.Increment" validation="txt.isrequired,txt.isnumber"   maxlength="10"   size="25" onkey/>	</td>			 
				    --%>
		   			<td>	 
	    				 <input type="text" size='25' maxlength='50' id='incrementCertificateOrderNo' name='incrementCertificateOrderNo' tabindex='1' onKeyPress="validOrderNo(this);" title="Should be numbers ,alphabates and / only">
           			</td>

					
		   			<td  width="2%"></td>
					<TD  align="left"  width="8%"><b><fmt:message key="PC.ORDERDATE" bundle="${commonLables}"/></b></td>			
					<td width="22%" align="left"><hdiits:dateTime name="orderDate" mandatory="true" validation="txt.isdt,txt.isrequired" captionid="PC.ORDERDATE" bundle="${commonLables}"/>
						</td>
						
						
					<td>
			     	<hdiits:button name="go" type="button" captionid="PC.GO" readonly="false" bundle="${commonLables}" onclick="getListOfEmployeesWithGo()"/>
					</td>
				</tr>
			</table>
				<br/>
			<table style="display: none">
				<tr align="center">
					<td>
						<hdiits:radio readonly="false"  captionid="PC.CURRENT"  caption="YES" bundle="${commonLables}" value="TRUE"  name="currentYearOrders" default="TRUE" onclick="currerntRadio()"  />
					</td>
					<td align="right">
						<hdiits:radio readonly="false" captionid="PC.PREVIOUS" caption="NO" bundle="${commonLables}" value="FALSE"  name="previousYearOrders" onclick="previousRadio()"/>
					</td>
				</tr>
			</table>
				<br/>
				<br/>
				<br/>
			
			
			<div style="overflow: scroll;" id="tcontent1" class="halftabcontent">
				<div style="height: 200px;" align="center"> 
	 				<h1>
	 				<c:set var = "i" value = "1" ></c:set>
						<display:table name="${fixList}" requestURI="" pagesize="" id="orderList" export="false" >
						
							<display:column title="" headerClass="datatableheader" style="text-align: center;">
			  				<c:choose>
							<c:when test="${orderList.status eq 'Verified'}">		
								<input name="radio" id="radioId${i}" type="radio" align="middle" align="middle" disabled="true"/>
								<hdiits:hidden name = "tnrOrderNo${i}" id = "tnrOrderNoId${i}" default ="${orderList.incrementOrderNo}"/>
							</c:when>
							<c:otherwise>	
								<input name="radio" id="radioId${i}" type="radio" align="middle" align="middle"/>
								<hdiits:hidden name = "tnrOrderNo${i}" id = "tnrOrderNoId${i}" default ="${orderList.incrementOrderNo}"/>
							</c:otherwise>
							</c:choose>
			  				</display:column>
			  				
			  				<display:column  class="tablecelltext" title="Increment Order No"  headerClass="datatableheader" style="text-align: center;" >
			    				<a href="./hrms.htm?actionFlag=showpayIncrementDataListForNewOrderNo&orderNo=${orderList.incrementOrderNo}&orderdate=${orderList.incrementOrderDate}&save=view">${orderList.incrementOrderNo}  </a>    
			    			</display:column>
			  				<display:column property="incrementOrderDate" class="tablecelltext" title="Increment Order Date" headerClass="datatableheader" style="text-align: center;">
			  				</display:column>	
               				<display:column property="countOfEmployees"  class="tablecelltext" title="No.of employees in order" headerClass="datatableheader" style="text-align: center;">
			  				</display:column>
			  				<display:column property="status" title="Status" headerClass="datatableheader" style="text-align: center;">
			  				</display:column>
			   				<display:column title="" headerClass="datatableheader" style="text-align: center;">
			 		  		<%--  <a href="./hrms.htm?actionFlag=getIncrementPrintReport&incrementOrderNo=${row.incrementOrderNo}">
			 		   		--%>
			 	 	  		<a href="javascript:getHistory('${orderList.incrementOrderNo}')">
			 	 	  		<b>Print Report</b></a>
			  				</display:column>
			  				
			  				<display:column  headerClass="datatableheader" style="display:none">
			  				<input type="text" name="disableFlag" id="disableFlag" value="${orderList.status}"></input>
			  				</display:column>
			  				  	  		
				  	  		<display:setProperty name="export.pdf" value="true" />
				  	  		<c:set var="i" value="${i+1}"></c:set>
  		  				</display:table>
    				</h1>
  				</div>
  			</div>
				<input type="hidden" name="counterEmp" id="counterEmp" value="0"/>
	 			<input type="hidden" name="counterEmpOrder" id="counterEmpOrder" value="0"/>
	 			<hdiits:hidden name="save" id="save" default="${resultValue.save}"/>
	 			<hdiits:hidden name="incrementOrderNo" id= "incrementOrderNo" default="${resultValue.incrementOrderNo}"/>
	 			<hdiits:hidden name="incrementorderDate" id= "incrementorderDate" default="${resultValue.incrementorderDate}"/>
						
			<table id="parentOrderList" style="width:100%" border=0 rules="none">
				<tr width="100%">
					<td width="45%">
						<c:choose>
 							<c:when test="${fixListsizeWithGo gt 0}">
								<div style="height:200px;width:100%;overflow:scroll;border:1px solid #000;">
								<display:table list="${fixListWithGo}" name="${fixListWithGo}" class= "cllabel" id="empListTable" uid="empListTable" requestURI="" pagesize="" export="false" cellpadding='0' cellspacing='0' sort="external"  >
									<display:column class="tablecelltext" style="text-align:center" headerClass="datatableheader" title= "<input type='checkbox' name='SelectAll' onclick='checkUncheckAll(this)'/>">
										<input type="checkbox" name="GroupCheck" id="GroupCheck${empListTable_rowNum}" value="${empListTable.userid}" align="middle" onclick="disableRemoveButton(); enableAddButton();">
										<script>
										document.getElementById("counterEmp").value=Number(document.getElementById("counterEmp").value) + 1;
										</script>
									</display:column>
									<display:column class="tablecelltext" style="text-align:center" title="User Id" headerClass="datatableheader">
										<label id="userId${empListTable_rowNum}">${empListTable.userid}</label>
									</display:column>
									<display:column class="tablecelltext" style="text-align:center" title="Emp Name" headerClass="datatableheader">
										<label id="empName${empListTable_rowNum}">${empListTable.empName}</label>
									</display:column>
									<display:column class="tablecelltext" style="text-align:center" title='<span>Basic Salary<Font size="2" face="Rupee Foradian"> (<Font size="2" face="Rupee Foradian">`</Font>)</Font> </span>' headerClass="datatableheader">
										<label id="empBasic${empListTable_rowNum}">${empListTable.basic}</label>
									</display:column>
									<display:column class="tablecelltext" style="text-align:center" title="Inc Order Date" headerClass="datatableheader">
										<label id="incOrderDate${empListTable_rowNum}">${empListTable.incrementOrderDate}</label>
									</display:column>
									<display:column class="tablecelltext" style="display:none"   headerClass="datatableheader">
										<label id="nextIncrDt${empListTable_rowNum}">${empListTable.nextincrementDate}</label>
									</display:column>
									<display:column class="tablecelltext" style="display:none"  headerClass="datatableheader">
										<label id="scaleEndAmt${empListTable_rowNum}">${empListTable.scaleEndAmount}</label>
									</display:column>			
									<display:column class="tablecelltext" style="display:none" >
										<label id="remarks${empListTable_rowNum}">${empListTable.remarks}</label>
									</display:column>
									<display:column  headerClass="datatableheader" style="display:none" >
			  							<label id="origBasic${empListTable_rowNum}">${empListTable.basic}</label>
			  						</display:column>
									<display:caption style="text-align:center;color: rgb(200,100,20)" ><b><h2>
									List of Employees due for Increment</h2></b> </display:caption>
									</display:table>
				 				</div>
	 						 </c:when>	
				 	 	<c:otherwise>
 			 				<div style="height:200px;width:100%;overflow:scroll;border:1px solid #000;">
  							<TABLE border='0' RULES="NONE"  align="center" class= "cllabel" cellpadding='' id="empListTable" cellspacing='0' width="100%" style="border-bottom:1px solid #396DA5;">
  								<tr>
  									<caption style="text-align:center" ><b><h2>
									List of Employees due for Increment</h2></b> 
									</caption>
								</tr> 
								<TR class= "cllabel">
									<TD width='20%' align='center' class= "cllabel" style="color: rgb(200,100,20);font-size: small;font-style: normal;font-weight : bold" >Check Box</TD>
									<TD width='20%' align='center' class= "cllabel" style="color: rgb(200,100,20);font-size: small;font-style: normal;font-weight : bold" >User Id</TD>
									<TD width='20%' align='center' class= "cllabel" style="color: rgb(200,100,20);font-size: small;font-style: normal;font-weight : bold" ><font size='2'>Emp Name</font></TD>
									<TD width='20%' align='center' class="cllabel" style="color: rgb(200,100,20);font-size: small;font-style: normal;font-weight : bold" >Basic Salary<br><FONT SIZE='1'>(Pay in PB+GP)</font><Font size="2" face='Rupee Foradian'> (<Font size="2 " face='Rupee Foradian'>`</Font>)</TD>
									<TD width='20%' align='center' class= "cllabel" style="color: rgb(200,100,20);font-size: small;font-style: normal;font-weight : bold" >Inc Order Date</TD>
								</TR>
							</table>	
							</div>
						</c:otherwise>	
					</c:choose>
				</td>	
			
				<td width="5">
						<table align="left">
							<tr>
	    						<td>
									<hdiits:button  name="addFromLeftToRight" title="At least one employee should be selected for attachment"  type="button" captionid="PC.ADDTOLIST" bundle="${commonLables}" onclick="fun_attach();" ></hdiits:button>
									<br/>
									<hdiits:button  name="addFromRightToLeft" type="button" captionid="PC.REMOVE" bundle="${commonLables}" onclick="fun_detach()"></hdiits:button>
									<br/>
								</td>
							</tr>
						</table>
				</td>
				
				<td width="45%">
					<c:choose>
						<c:when test="${fixListsizeWithGoUpdate gt 0}">
							<div style="height:200px;width:100%;overflow:scroll;border:1px solid #000;">
  							<display:table list="${fixListWithGoUpdate}" class= "cllabel" name="${fixListWithGoUpdate}" id="empListOfOrderTable" uid="empListOfOrderTable" requestURI="" pagesize=""  export="false" cellpadding='0' cellspacing='0' >
  								<display:column class="tablecelltext" style="text-align:center" headerClass="datatableheader" >
  								<c:choose>
								<c:when test="${empListOfOrderTable.status eq 'Verified'}">	
									<input type="checkbox" name="GroupCheckOrder" id = "GroupCheckOrder${empListOfOrderTable_rowNum}" value="${empListOfOrderTable.userid}" disabled="true" align="middle" >
								</c:when>
								
								<c:otherwise>	
									<input type="checkbox" name="GroupCheckOrder" id = "GroupCheckOrder${empListOfOrderTable_rowNum}" value="${empListOfOrderTable.userid}" align="middle" onclick="disableAddButton(); enableRemoveButton();">
									<script>
									document.getElementById("counterEmpOrder").value=Number(document.getElementById("counterEmpOrder").value) + 1;
									</script>
								</c:otherwise>
								</c:choose>
								</display:column>
								<display:column class="tablecelltext" style="text-align:center" title="Emp Name" headerClass="datatableheader">
									<label id="empNameOrder${empListOfOrderTable_rowNum}">${empListOfOrderTable.empName}</label>
								</display:column>
								<display:column class="tablecelltext" style="text-align:center" title='<span>Basic Salary<Font size="2" face="Rupee Foradian"> (<Font size="2" face="Rupee Foradian">`</Font>)</Font> </span>' headerClass="datatableheader">
									<label id="empBasicOrder${empListOfOrderTable_rowNum}">${empListOfOrderTable.newBasic}</label>
								</display:column>
								<display:column class="tablecelltext" style="text-align:center" title="WEF Date" headerClass="datatableheader">
									<input type="text" id="empWEFOrder${empListOfOrderTable_rowNum}" name="empWEFOrder${empListOfOrderTable_rowNum}"  size="8" value="${empListOfOrderTable.withEffectiveDate}"/>
								</display:column>
								<display:column class="tablecelltext" style="text-align:center" title="Next Increment Date" headerClass="datatableheader">
									<label id="nextIncrDtOrder${empListOfOrderTable_rowNum}">${empListOfOrderTable.nextincrementDate}</label>
								</display:column>
								<display:column class="tablecelltext" style="text-align:center" title="Remarks" headerClass="datatableheader">
									<input type="text" id="remarksOrder${empListOfOrderTable_rowNum}" name="remarksOrder${empListOfOrderTable_rowNum}" size="8"  value="${empListOfOrderTable.remarks}" />
								</display:column>
								<display:column class="tablecelltext" style="display:none" headerClass="datatableheader">
									<label id="scaleEndAmtOrder${empListOfOrderTable_rowNum}">${empListOfOrderTable.scaleEndAmount}</label>
								</display:column>
								<display:column class="tablecelltext" style="display:none"  headerClass="datatableheader">
									<label id="IncOrderDateOrder${empListOfOrderTable_rowNum}">${empListOfOrderTable.incrementOrderDate}</label>
								</display:column>
								<display:column  headerClass="datatableheader" style="display:none" >
			  						<label id="origBasicOrder${empListOfOrderTable_rowNum}">${empListOfOrderTable.basic}</label>
			  					</display:column>
					
								<display:caption style="text-align:center;color: rgb(200,100,20)" ><b><h2>
								List of Employees Release for Increment</h2></b> </display:caption>
							</display:table>
       						</div>
       					</c:when>
       				<c:otherwise>
       						<div style="height:200px;width:100%;overflow:scroll;border:1px solid #000;">
								<TABLE border='0' rules="none" align="center" id="empListOfOrderTable" name="empListOfOrderTable" class= "cllabel" cellpadding='0' cellspacing='0' width="100%" style="border-bottom:1px solid #396DA5;">
									<tr>
  										<caption style="text-align:center" ><b><h2>
										List of Employees Release for Increment </h2></b> 
										</caption>
									</tr>  
									<TR class= "cllabel">
										<TD width='10%' align='center' class= "cllabel" style="color: rgb(200,100,20);font-size: small;font-style: normal;font-weight : bold" >Check Box</TD>
										<TD width='20%' align='center' class= "cllabel" style="color: rgb(200,100,20);font-size: small;font-style: normal;font-weight : bold" >Emp Name</TD>
										<TD width='20%' align='center' class= "cllabel" style="color: rgb(200,100,20);font-size: small;font-style: normal;font-weight : bold" ><font size='2'>Basic Salary</font><br><FONT SIZE='1'>(Pay in PB+GP)</font><Font size="2" face='Rupee Foradian'> (<Font size="2 " face='Rupee Foradian'>`</Font>)</TD>
										<TD width='20%' align='center' class= "cllabel" style="color: rgb(200,100,20);font-size: small;font-style: normal;font-weight : bold" > WEF Date</TD>
										<TD width='15%' align='center' class= "cllabel" style="color: rgb(200,100,20);font-size: small;font-style: normal;font-weight : bold" > Next Incr Date</TD>
										<TD width='15%' align='center' class= "cllabel" style="color: rgb(200,100,20);font-size: small;font-style: normal;font-weight : bold" >Remarks</TD>
									</TR>
		   					 	</table>	
		       				</div>
		     		 </c:otherwise> 
					</c:choose>
				</td>
    		</tr>				   
				
		</table>
			
			
			<table width="100%">
				<tr>
					<td width="50%"><input type="hidden" id="empIdstoBeDetached" name="empIdstoBeDetached" value="" size="100" ></td>
					<td width="50%"><input type="hidden" id="empIdstoBeAttached" name="eEmpIdstoBeAttached" value="" size="100" ></td>
					<td width="50%"><input type="hidden" id="empBasicSalarytoBeAttached" name="empBasicSalarytoBeAttached" value="" size="100" ></td>
					<td width="50%"><input type="hidden" id="empBasicSalarytoBeDetached" name="empBasicSalarytoBeDetached" value="" size="100" ></td>
					<td width="50%"><input type="hidden" id="empWEFtoBeAttached" name="empWEFtoBeAttached" value="" size="100" ></td>
					<td width="50%"><input type="hidden" id="empWEFtoBeDetached" name="empWEFtoBeDetached" value="" size="100" ></td>
					<td width="50%"><input type="hidden" id="empNextIncrDatetoBeAttached" name="empNextIncrDatetoBeAttached" value="" size="100" ></td>
					<td width="50%"><input type="hidden" id="empNextIncrDatetoBeDetached" name="empNextIncrDatetoBeDetached" value="" size="100" ></td>
					<td width="50%"><input type="hidden" id="empRemarkstoBeAttached" name="empRemarkstoBeAttached" value="" size="100" ></td>
					<td width="50%"><input type="hidden" id="empRemarkstoBeDetached" name="empRemarkstoBeDetached" value="" size="100" ></td>
					<td width="50%"><input type="hidden" id="empIncOrderDatetoBeAttached" name="empIncOrderDatetoBeAttached" value="" size="100" ></td>
					<td width="50%"><input type="hidden" id="empIncOrderDatetoBeDetached" name="empIncOrderDatetoBeDetached" value="" size="100" ></td>
					<td width="50%"><input type="hidden" id="origBasictoBeAttached" name="origBasictoBeAttached" value="" size="100" ></td>
					<td width="50%"><input type="hidden" id="origBasictoBeDetached" name="origBasictoBeDetached" value="" size="100" ></td>
					<td width="50%"><input type="hidden" id="sizeOfOrderList" name="sizeOfOrderList" value="${fixListsizeWithGoUpdate}" size="100" ></td>
					<td width="50%"><input type="hidden" id="inputTagElements" name="inputTagElements" value="" size="100" ></td>
				</tr>
			</table>
				<br/>
				
			<table align="right">	
					<tr>			
						<td>
							<hdiits:button  name="savebutton" type="button" captionid="PC.SAVE" readonly="false" bundle="${commonLables}" onclick="saveData()"/>
						</td>
						<td>
							<hdiits:button name="cancel" type="button" captionid="PC.CANCEL" readonly="false" bundle="${commonLables}" onclick="fun_cancel();"/>
						</td>
					</tr>
			</table>
			<br/>
				&nbsp;&nbsp;  	&nbsp;&nbsp;  
    		
				
			
			
			<script type="text/javascript">
				//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
				initializetabcontent("maintab")
			</script>
	 		
 		</div>	
 	</hdiits:form>
 	
 	

<%
}
  	  catch(Exception e)
  	  {
  		    		  e.printStackTrace();
  	  }
%>



	
