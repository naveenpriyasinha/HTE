<%@page import="com.tcs.sgv.common.utils.StringUtility,  java.util.* "%>
<html>
<head>
<%
try{
%>

<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>

<fmt:setBundle basename="resources.eis.eis_common_lables"
	var="commonLables" scope="page" />

<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="dsgnList" value="${resValue.dsgnList}"></c:set>
<c:set var="list" value="${resValue.bulkList}"></c:set>
<c:set var="size" value="${resValue.size}"></c:set>
<c:set var="flag" value="${resValue.flag}"></c:set>
<c:set var="empID" value="${resValue.empID}"></c:set>
<c:set var="empFlag" value="${resValue.empFlag}"></c:set>
<c:set var="count" value="${resValue.count}"></c:set>
<c:set var="type" value="${resValue.type}"></c:set>
<c:set var="dsgn" value="${resValue.dsgn}"></c:set>
<c:set var="billNo" value="${resValue.billNo}"></c:set>
<c:set var="srNoAllown" value="1"></c:set>
<c:set var="compoId" value="${resValue.compoId}"></c:set>
<c:set var="msg" value="${resValue.msg}"></c:set>
<c:set var="billList" value="${resValue.billList}"></c:set>
<c:set var="billGroupid" value="${resValue.billGroupid}"></c:set>
<c:set var="success" value="Record Updated Successfully..."></c:set>
<%

List dataList = (List) pageContext.getAttribute("list");
int size = 0;
if(dataList != null)
{
	size = dataList.size();
}
pageContext.setAttribute("listSize",size);

%>

<script>
var disempId="";


function selectionForm()
{
	//alert('emp flag '+${empFlag});		
	if('${compoId}' >0)
		{document.getElementById("cmpType").value= '${type}';
		//alert('Hi 1');
		}


	//alert(' dsgn '+'${dsgn}');

	//alert('billGroupid  '+'${billGroupid}');
	if('${dsgn}' >0 || '${billGroupid}'>0)
		{
		//alert('  '+${dsgn});
		
		//alert('after saving selectionfrom calling billGroupid '+'${billGroupid}');

		if('${billGroupid}'>0)
		{
			//alert('checking'+ '${dsgn}');
		document.getElementById("billcombo").value= '${resValue.billGroupid}';
		}
		if('${dsgn}'>0)
		{
		document.getElementById("dsgnCombo").value= '${resValue.dsgn}';
		if('${billNo}' >0)
		{
		document.getElementById("billcombo").value= '${resValue.billNo}';
		}
		}
		

		//if('${dsgn}' >0)
		//{
	//	document.getElementById("dsgnCombo").value= '${resValue.dsgn}';
	//	document.getElementById("billcombo").value= '${resValue.billNo}';
	//	}	
			
		}

	if('${compoId}' >0)
		{document.getElementById("payItemCombo").value= '${compoId}';
		//alert('Hi 3');
		
		
		}
	if('${flag}'=="true" &&  '${empFlag}'=="false")
	{
		//alert('hummmmmmmmmm');
		//alert('vastundi '+ '${billGroupid}'); 
		if('${billGroupid}'>0 && '${dsgn}'>0)
		{
			//alert('going to  if condition');
			getEmployeeFromBillgroup();
		}
		else if('${billGroupid}'>0)
		{
			//alert('going to else if condition');
			getEmployeeFromBillgroup();
		}
		else
		{
			//alert('going to else condition');
			getEmployeeFromDesination();
		}
		
	}
	if('${empFlag}'=="true" && '${count}'==2)
	{
		//alert('Moj Padi gay ');
		var compoType=document.getElementById("cmpType").value;
		  
		  var compoId=document.getElementById("payItemCombo").value;

		  var empId='${empID}';

		  var dsgn='${dsgn}';

		  var billGroupid='${billGroupid}';
		 
		// alert('moj ma '+'${empID}');
		
		 url='./hrms.htm?actionFlag=getBulkEmpl&empId='+empId+'&compoType='+compoType+'&compoId='+compoId+'&dsgn='+dsgn+'&billGroupid='+billGroupid;
		// alert('abhi'+billGroupid);
		 document.frmBF.action = url;
		 
		  //alert(url);
		  document.frmBF.submit();
		  showProgressbar("Please wait...");

			 
	}

	if("${msg}"!='')
	{
		alert('${msg}');
		//getEmployeeFromBillgroup();
	}
}
</script>



<script>
function GetPayItems()
{
	//alert('manish here in GEtPayItems');
	
		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  var compoType=document.getElementById("cmpType").value;
		  //alert(''+yearValue);
		  url= uri+'&compoType='+compoType;
		  var actionf="getPayItms";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
        //alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=stateChanged_FromCompoType;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);		  	
}

function chkValue()
{
	var empId="";

	
	//alert("==> in chkValue..sasdf afsda.."+document.getElementById("cmpType").value+"::"+document.getElementById("payItemCombo").value);
 
	//var compoType=document.getElementById("cmpType").value;
	  //var compoId=document.getElementById("payItemCombo").value;
	  
	 // alert("==> compoType :: "+compoType+"::compoId :: "+compoId);
	if(document.getElementById("cmpType").value=="")
	{
		alert("Please select type of component.");
		document.getElementById("cmpType").focus();
		return false;
	}
	else if(document.getElementById("payItemCombo").value=="")
	{
		alert("Please select pay item.");
		document.getElementById("payItemCombo").focus();
		return false;
	}
	
	else
	{
		
		if(document.getElementById("Employee_ID_EmployeeSearch").value==document.getElementById("Employee_Name_EmployeeSearch").value)
		{	
	       empId=document.getElementById("Employee_ID_EmployeeSearch").value;
	    }
	   else
	   {
			
		empId=document.getElementById("Employee_Name_EmployeeSearch").value;
	   }

	   document.getElementById("Employee_ID_EmployeeSearch").value=empId;


	
		empId=document.getElementById("Employee_ID_EmployeeSearch").value;
		
		
		disempId=empId;
		document.getElementById("hiddenEmpId").value=empId;
		document.getElementById("hiddenEmpFlag").value=true;
		if(empId!="")
		{
			
				document.getElementById("dsgnCombo").disabled=true;
				
		}
	
		//alert("=> empId :: "+empId);
		var compoType=document.getElementById("cmpType").value;
		  
		var compoId=document.getElementById("payItemCombo").value;
	
		  
		 url='./ifms.htm?actionFlag=getBulkEmpl&empId='+empId+'&compoType='+compoType+'&compoId='+compoId;
		 
		  
		 
		  document.frmBF.action = url;
		 
		  //alert(url);
		  document.frmBF.submit();
		  showProgressbar("Please wait...");
		 
	}
	return true;  
	 
	
}

function stateChanged_FromCompoType()
	{
		
		if (xmlHttp.readyState==complete_state)
		{ 		
		
			clearPayItemCombo();
			var payItemCombo = document.getElementById("payItemCombo");
  	        
			//var month = document.getElementById("Month");
			
			//var yearVal =document.getElementById("Year").value;
		
					var XMLDoc=xmlHttp.responseXML.documentElement;			
                    
                    if(XMLDoc==null)
                    {
                      window.status = 'No Records Found.';
                     
                     }
                    else
                    {
                        window.status='';
                        var monthEntries = XMLDoc.getElementsByTagName('allowDeduc-mapping');
                       
	           			for ( var i = 0 ; i < monthEntries.length ; i++ )
		     			{			     			  
			     			
		     				    val=monthEntries[i].childNodes[0].firstChild.nodeValue;    
		     				    text = monthEntries[i].childNodes[1].firstChild.nodeValue; 			     				   
		     				    var y = document.createElement('option');
		     			        y.value=val;
		     			        y.text=text;
		     			        
	 	                        try
	 	   				        {	      				    					
	 	                        	payItemCombo.add(y,null);
		           		        }
		           		
		 						 catch(ex)
		   						 {
		 							payItemCombo.add(y); 
		   						 }
		   		          }
	   		         }
	   		       
 	   }
	
 }

function clearPayItemCombo()
{
	//alert('inside clearPayItemCombo');
	var v=document.getElementById("payItemCombo").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("payItemCombo").options.length -1;
			document.getElementById("payItemCombo").options[lgth] = null;
	}		
}
function getEmployeeFromDesination()
{
	//alert('ddd'+document.getElementById("dsgnCombo").value);
	
	 if(document.getElementById("cmpType").value=="")
		{
			alert("Please select type of component.");
			document.getElementById("cmpType").focus();
			return false;
		}
		
		 if(document.getElementById("payItemCombo").value=="")
		{
			alert("Please select pay item.");
			document.getElementById("payItemCombo").focus();
			return false;
		}
	 //alert('inside getEmployeeFromDesination() method');
		
		  var dsgn=document.getElementById("dsgnCombo").value;
		

		  if(dsgn >0)
		  {
			  
		  }
		  else
		  {
			  dsgn='${dsgn}';
		  }	  
		  var compoType=document.getElementById("cmpType").value;
		 // alert('compo type '+compoType);
		  var compoId=document.getElementById("payItemCombo").value;
		 // alert('compo id '+compoId);
		
		    var billGroupid=document.getElementById("billcombo").value;
		  

		    if(billGroupid >0 && dsgn>0)
		    {
		    	//alert('two going');
		    	
		  		  url='./hrms.htm?actionFlag=getBulkAllDdcBasedonBilDesg&dsgn='+dsgn+'&compoType='+compoType+'&compoId='+compoId+'&billGroupid='+billGroupid;
		  			showProgressbar("Please wait...");
		    }
		    else if(dsgn>0 && dsgn!=' ')
		    {
		    	//alert('two going');
		    	  url='./hrms.htm?actionFlag=getBulkAllDdcBasedonBilDesg&dsgn='+dsgn+'&compoType='+compoType+'&compoId='+compoId;
		    	  showProgressbar("Please wait...");
		    	  
		    }
		    else
		    {
		    	getEmployeeFromBillgroup();
		    }
		  //alert('hi ');
		  document.frmBF.action = url;
		  //alert('hi 2');
		  document.frmBF.submit();
		 // xmlHttp.onreadystatechange=stateChanged_FromCompoType;
			//xmlHttp.open("POST",encodeURI(url),true);
			//xmlHttp.send(null);		
			//alert("===> in desig....")  
			//document.getElementById("dsgnCombo").disable=true;
	  		  

}

function getEmployeeFromBillgroup()
{
	//alert("getEmployeeFromBillgroup.");
	//document.getElementById("dsgnCombo").value=-1;
	
	 if(document.getElementById("cmpType").value=="")
		{
			alert("Please select type of component.");
			document.getElementById("cmpType").focus();
			return false;
		}
		
		 if(document.getElementById("payItemCombo").value=="")
		{
			alert("Please select pay item.");
			document.getElementById("payItemCombo").focus();
			return false;
		}
			
	 //alert('inside getEmployeeFromDesination() method');
		
		 var dsgn=document.getElementById("dsgnCombo").value;
		// alert('designation id is '+dsgn);
		 
		  var billGroupid=document.getElementById("billcombo").value;
		 // alert('billGroupid id is '+billGroupid);
		 // alert('billGroupid working '+billGroupid);

		 // if(billGroupid >0)
		//  {
			  
		//  }
		//  else
		//  {
		//	  billGroupid='${billGroupid}';
		//  }	  
		  var compoType=document.getElementById("cmpType").value;
		// alert('compo type '+compoType);
		  var compoId=document.getElementById("payItemCombo").value;
		// alert('compo id '+compoId);
		
		  
		  
		    if(billGroupid >0 && dsgn>0)
		    {
		    	//alert('for both only');
		  url='./hrms.htm?actionFlag=getBulkAllDdcBasedonBilDesg&billGroupid='+billGroupid+'&compoType='+compoType+'&compoId='+compoId+'&dsgn='+dsgn;
		  showProgressbar("Please wait...");
		    }

		    else if(billGroupid!=-1)
		    {
		     if(billGroupid >0 || '${billGroupid}'>0 || '${dsgn}'>0 )
		    {
		    	
		    //	alert('combo billGroupid'+billGroupid);
		    	//alert('after saving billGroupid'+'${billGroupid}');
		    //	alert('after saving dsgn'+'${dsgn}');

		    	if( '${billGroupid}'>0 && '${dsgn}'>0)
		    	{
		    		//alert('for both after saving only');
		    		billGroupid = '${billGroupid}';
		    		dsgn ='${dsgn}';
		    	//	alert('checking'+dsgn)
		    		url='./hrms.htm?actionFlag=getBulkAllDdcBasedonBilDesg&billGroupid='+billGroupid+'&compoType='+compoType+'&compoId='+compoId+'&dsgn='+dsgn;
		    		showProgressbar("Please wait...");
		    	}
		    	else if('${billGroupid}'>0)
		    	{
		    		//alert('for bill only');
		    		
			    	if(billGroupid >0)
			    	{
		    		billGroupid=billGroupid;
			    	}
			    	else
			    	{
			    		billGroupid='${billGroupid}';
			    	}
			    	url='./hrms.htm?actionFlag=getBulkAllDdcBasedonBilDesg&billGroupid='+billGroupid+'&compoType='+compoType+'&compoId='+compoId;
			    	showProgressbar("Please wait...");
		    	}

		    	else
		    	{
		    		url='./hrms.htm?actionFlag=getBulkAllDdcBasedonBilDesg&billGroupid='+billGroupid+'&compoType='+compoType+'&compoId='+compoId;
		    		showProgressbar("Please wait...");
		    	}
		    	
		    	
		    }
		    }
		    else
		    {
			    //alert('for dsgn only');
		    	getEmployeeFromDesination();
		    }
		  
		  document.frmBF.action = url;
		  document.frmBF.submit();
		
	  		  

}


function getEmployeeFromPayitem()
{
	//document.getElementById("billcombo").value = -1;
	
	
	
	var rav= '${empID}';
		 //alert("employee id is "+rav);

		 
	 if(document.getElementById("cmpType").value=="")
		{
			alert("Please select type of component.");
			document.getElementById("cmpType").focus();
			return false;
		}
		
		 if(document.getElementById("payItemCombo").value=="")
		{
			alert("Please select pay item.");
			document.getElementById("payItemCombo").focus();
			return false;
		}
	 //alert('inside getEmployeeFromDesination() method');
		
		var compoType=document.getElementById("cmpType").value;
		  
		  var compoId=document.getElementById("payItemCombo").value;
		
		
		if(rav >0)
		{

		//	alert('hummmmmmmmmmmm');
			 var url='./hrms.htm?actionFlag=getBulkAllDdcBasedonBilDesg&empId='+rav+'&compoType='+compoType+'&compoId='+compoId;
			 //+'&dsgn='+dsgn+'&billGroupid='+billGroupid
				 
				 document.frmBF.action = url;
				 
				  //alert(url);
				  document.frmBF.submit();
				  showProgressbar("Please wait...");
					return true;
			
		}
		
		 var billGroupid=0;

		 
		  var dsgn=0;

		  if(billGroupid==0 || dsgn==0)
		  {
				

				 billGroupid=document.getElementById("billcombo").value;
				  dsgn=document.getElementById("dsgnCombo").value;

				 // alert('billGroupid'+billGroupid);
				//  alert('dsgn id '+dsgn);
			  if(billGroupid>0)
			  {
				  getEmployeeFromBillgroup();
				  return true;
			  }
			 // else if(dsgn>0)
			 // {
			//	  getEmployeeFromDesination();
			//	  return true;
			 // }
			  else
			  {
				alert("Please select Bill No.");
				return false;
			  }
		  }


		  //if(dsgn==0)
		 //{
			// if(document.getElementById("billcombo").value=="")
			//{
				//alert("Please select Bill No");
				//document.getElementById("billcombo").focus();
				//return false;
			//}

		 //}
		

		 //if(billGroupid==0)
		 //{
		// if(document.getElementById("dsgnCombo").value=="")
			//{
				//alert("Please select Designation");
				//document.getElementById("dsgnCombo").focus();
				//return false;
			//}
			
		 //}
		 
}
function submitForm()
{
	
	//alert("inside submit form");
	if(!confirm("Are you sure , you want to Update Details ?")){
		return false;
	}
	  if(document.getElementById("cmpType").value=="")
		{
			alert("Please select type of component.");
			return;
		}
		else if(document.getElementById("payItemCombo").value=="")
		{
			alert("Please select pay item.");
			return;
		}
	
	document.getElementById("Save").disabled=true;
			
	//alert("going to save");
/*	document.getElementById("hiddenEmpId").value= '${empID}';
	if('${empFlag}'=="true" )
	{
		document.getElementById("hiddenEmpFlag").value=true;
		document.getElementById("eCode").value=1000;
	}
	*/
	var checked="";
	var uncheckd="";
	var chkBoxArr=document.getElementsByName("selcheckBoxAllow"); 
	var chkLength=chkBoxArr.length;
	//alert('Inside deselect n Length'+chkLength);

	var compoId=document.getElementById("payItemCombo").value;
	var compoType=document.getElementById("cmpType").value;
    var billGroupid=document.getElementById("billcombo").value;
	//alert('billGroupid'+billGroupid);
	for(var i=1;i<=chkLength;i++)
	{ 
		//alert("inside for  i "+i);
		var a = document.getElementById('edit'+i).value;
		if( chkBoxArr[i-1].checked == true)
	 	{
		 		checked=checked + a + "," ;
	 	}
		else if(chkBoxArr[i-1].checked == false)
		 {
				 uncheckd= uncheckd + a + ",";
	 	 }
	}

	document.getElementById("hiddenEmpIdsChecked").value = checked;
	document.getElementById("hiddenEmpIdsUnChecked").value = uncheckd;
	
	var URL='./hrms.htm?actionFlag=insertAllwDdct&compoId='+compoId+'&compoType='+compoType+'&billGroupid='+billGroupid;
		 
	document.newBulkForm.action = URL;
	
	document.newBulkForm.submit();
	showProgressbar("Please wait...");
		
}

function onclosefunction()
{
	document.getElementById("btnClose1").disabled=true;
		window.location="hrms.htm?actionFlag=getHomePage";
}

function deselectvalue()
{

	//alert('function called deselectvalue :: '+disempId+"::");
	//alert("hidden value of emp :: "+"${empID}");

	var fordisempId = "${empID}";
	  if(fordisempId!="")
		{
			//alert("in not null empId");
			document.getElementById("dsgnCombo").disabled=true;
		}
}

function getAmount()
{
	
	//alert('value coming'+document.getElementById("amount").value);
	//alert("getAmount called:::");
	var counter = document.getElementById("listSize").value;

	//alert('counter'+counter);
	var chkBoxArr=document.getElementsByName("selcheckBoxAllow"); 
	var chkAllParent=document.getElementsByName("selcheckBoxAllowSelectAll");
	
	var chkLength=chkBoxArr.length;
	//alert('chkLength'+chkLength);
	var amount=document.getElementById("amount").value;
	
	if(amount > 0)
	{
		//chkAllParent[0].checked=true;
		for(var i=0;i<chkLength;i++)
			{ 
				//chkBoxArr[i].checked=true;
				
				if( chkBoxArr[i].checked == true)
			 	{
			 		document.getElementById('newAmount'+i).value=amount;
			 	}
			}
	} 
}
	
function selDeselectAllowances(obj)
{		
	//alert("selDeselectAllowances:::");

	var chkBoxArr=document.getElementsByName("selcheckBoxAllow"); 
	var counter = document.getElementById("listSize").value;
	var chkLength=chkBoxArr.length;
	var chkAllParent=document.getElementsByName("selcheckBoxAllowSelectAll");

  if(chkAllParent[0].checked)
  {
	  //alert('Parent is true');
	  for(var i=0;i<counter;i++)
		{
			chkBoxArr[i].checked = true;
		}
  }
  else
  {
	 //alert('Parent is false');
	  for(var i=0;i<counter;i++)
		{
			chkBoxArr[i].checked = false;
  		}
   }
	
}

function deselect()
{

	var chkBoxArr=document.getElementsByName("selcheckBoxAllow"); 
	var chkLength=chkBoxArr.length;
	//alert('Inside deselect n Length'+chkLength);
	for(var i=0;i<chkLength;i++)
	{ 
		//alert("inside for  i "+i);
		if( chkBoxArr[i].checked == true)
	 	{
		 	//alert("inside for if "+chkBoxArr[i].value+"  i"+i);
			
	 	}
		 if(chkBoxArr[i].checked == false)
		 {
			 //alert("inside for else "+chkBoxArr[i].value+"  i"+i);
	 	 }
	}
}

/*function onDsgnChange()
{
	alert('function called');
	if(document.getElementById("cmpType") == null !! document.getElementById("cmpType").value == "")
	{
	alert("Please select the Type of Component first, then select the designation");
	document.getElementById("cmpType").focus();
	return false;
	
	}
	
	if(document.getElementById("payItemCombo") == null !! document.getElementById("payItemCombo").value == "")
	{
	
	alert("Please select the Pay -Item first then select the designation");
	document.getElementById("payItemCombo").focus();
	return false;
	}
	return true;
	 
}
function onPayItemChange()
{
	if(document.getElementById("cmpType") == null !! document.getElementById("cmpType").value == "")
	{
	alert("Please select the Type of Component first, then select the Pay - Item ");
	document.getElementById("cmpType").focus();
	return false;
	
	}
	 return true;
}
*/
</script>

</head>


<body>
	<frame name="frm1">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message
			key="Bulk.AlDdct" bundle="${commonLables}" /></b></a></li>
		</ul>
	</div>
	<br/>
<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin" >
<hdiits:form name="frmBF" validate="true" method="POST" action=""
	encType="multipart/form-data">



	<table width="85%" align="center" border="1" style="display:none">
		<tr>
			<td align="center" colspan="4"><jsp:include
				page="/WEB-INF/jsp/ess/SearchEmployeeForBulk.jsp">
				<jsp:param name="searchEmployeeTitle" value="Search Employee" />
				<jsp:param name="SearchEmployee" value="EmployeeSearch" />
				<jsp:param name="formName" value="frmViewEmployeeList" />
				<jsp:param name="functionName" value="chkValue" />
			</jsp:include></td>
		</tr>
	</table>
<div >
<table width="70%" align="center" border="0" class="BlukEmplTB2">
	<tr>
		<td colspan="4" style="padding-left: 280px" ><p style="font-family:verdana;color:red;">Note:Please Select Type Of Component and Pay Item First.</p></td>
		
	</tr>
	<tr/><tr/><tr/><tr/>
	
		
		<tr>
			<td class="Label" width="30%" align="justify">
				<b><hdiits:caption
				captionid="bulk.componentType" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<td width="20%" align="justify"><hdiits:select name="cmpType" id="cmpType" mandatory="true"
				onchange="GetPayItems();" style="width:150px">
				<hdiits:option value="">------Select-------</hdiits:option>
				<hdiits:option value="2500134">Allowances</hdiits:option>
				<hdiits:option value="2500135">Deductions</hdiits:option>
			</hdiits:select></td>
			
			<td class="Label" width="30%" align="center"><b><hdiits:caption
				captionid="bulk.payItem" bundle="${commonLables}"></hdiits:caption></b>
			</td>
			<td class="Label" width="20%" align="justify">
				<hdiits:select name="payItemCombo"
					id="payItemCombo" mandatory="true" onchange="getEmployeeFromPayitem()" style="width:210px">

					<hdiits:option value="">-------Select------</hdiits:option>
					<c:if test="${type == 2500134}">
						<c:forEach items="${resValue.payItemList}" var="payItemList">
							<hdiits:option value="${payItemList.allowCode}">${payItemList.allowDisplayName}</hdiits:option>
						</c:forEach>
					</c:if>
					<c:if test="${type == 2500135}">
						<c:forEach items="${resValue.payItemList}" var="payItemList">
							<hdiits:option value="${payItemList.deducCode}">${payItemList.deducDisplayName}</hdiits:option>
						</c:forEach>
					</c:if>
				</hdiits:select>
			</td>
			
		</tr>
		<tr/><tr/><tr/><tr/>
		<tr>
			<td class="Label" width="30%" align="justify">Bill Group</td>
			<td class="Label" colspan="" width="20%" align="justify">
				<select name="billcombo" id="billcombo" onchange="getEmployeeFromBillgroup()"  style="width:380px">
						<option value="-1" >----------------------Select----------------------</option>
				 		<c:forEach items="${resValue.billList}" var="billList">
							<option value="${billList.dcpsDdoBillGroupId}" title="${billList.dcpsDdoBillDescription}"><c:out value="${billList.dcpsDdoBillDescription}"> </c:out></option>
						</c:forEach>
				</select>
			</td>
			<td width="30%" align="justify">
			</td >
			<td width="20%" align="justify">
			</td>
		</tr>
		<tr/><tr/><tr/><tr/>
		
		<tr style="display:none">

				<td class="Label" width="30%" align="justify"><b><hdiits:caption
				captionid="bulk.empByDesignation" bundle="${commonLables}"></hdiits:caption></b>
				</td>
				<td class="Label" width="20%" align="justify">
				<hdiits:select name="dsgnCombo" id="dsgnCombo"
					mandatory="false" onchange="getEmployeeFromDesination()" style="width:225px">
					<hdiits:option value="">-----------Select-----------</hdiits:option>
					<hdiits:option value="1">-All Designation</hdiits:option>
		
						<c:forEach items="${resValue.dsgnList}" var="dsgnList">
							<hdiits:option value="${dsgnList.dsgnCode}">${dsgnList.dsgnName}</hdiits:option>
						</c:forEach>
					</hdiits:select>
				</td>
				<td width="30%" align="justify">
				</td>
				<td width="20%" align="justify">
				</td>
			</tr>  	
			
			<tr/><tr/><tr/><tr/>	
			
		
	</table>
	
	</div>
</hdiits:form>
</frame>

<frame name="frm2">
<hdiits:form name="newBulkForm" validate="true" method="POST" action=""
	encType="multipart/form-data">

	<c:set var="counter" value="${0}" />
	<input type="hidden" name="size" id="size" value="${size}">
	<input type="hidden" name="type" id="type" value="${type}">
	<input type="hidden" name="compoId" id="compoId" value="${compoId}">





<table width="80%" height="30%" align="center" cellpadding="0" cellspacing="0" style="border-collapse: separate;" >
	 <tr>
	 	<td height="30%" width="80%"  valign="top">
	 		<div style="height: 300px;width: 100%;overflow: auto;margin: 0px;padding: 0px;">
	 		<table style="width: 98%;border:single;overflow-y: auto;overflow-x: hidden; " border="1" bordercolor="black" align="center">
					<tr>
						<th style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold" width="30%" align="center"><b>Employee Name</b></th>	
						<th style="background-color:#F7E7D7 ;color: rgb(202,97,12);font-size: small;font-style: normal;font-weight : bold;" width="5%" align="center">
								<b><font color="black">
								<hdiits:checkbox id="selcheckBoxAllowSelectAll"	name="selcheckBoxAllowSelectAll" value="1"
									onclick="selDeselectAllowances(this)" /> 
								</font></b>
						</th>
					</tr>
					<c:set var="srNoDeduction" value="0"></c:set>
			
					<c:forEach var="row" items="${list}">
						<c:set var="counter" value="${counter+1}" />
			
						<tr>
							<td title="Employee Name"><input type="hidden"
								name="emp${counter}" id="emp${counter}" value="${row.empId}" /> 
								<input
								type="hidden" name="edit${row.empId}" id="edit${counter}"
								value="${row.empId}" /> <c:out value="${row.employeeName}"></c:out>
							</td>
							
			<c:choose>
						<c:when test="${row.edit eq 1}">
							<td title="Select" align="center"><hdiits:checkbox
								id="selcheckBoxAllow${srNoDeduction}" name="selcheckBoxAllow"
								value="" default="" readonly=""/>
							</td>
						</c:when>
						<c:otherwise>
								<td title="Select" align="center"><hdiits:checkbox
								id="selcheckBoxAllow${srNoDeduction}" name="selcheckBoxAllow"
								value="1" default="" readonly=""  />
								</td>
						</c:otherwise>
			</c:choose>
			
						</tr>
			
			
						
					</c:forEach>
			 
					
				</table>
	 		</div>
		</td>
	</tr>
</table>

</frame>
		<input type="hidden" value="" 	id="hiddenEmpIdsChecked" name="hiddenEmpIdsChecked">
		<input type="hidden" value="" 	id="hiddenEmpIdsUnChecked" name="hiddenEmpIdsUnChecked">

	<hdiits:hidden name="hiddenEmpId" />
	<hdiits:hidden name="hiddenEmpFlag" />

	<script type="text/javascript"> 
		initializetabcontent("maintab");
	</script>
	<script type="text/javascript">
	selectionForm();
	deselectvalue();
	</script>
	<%--<jsp:include page="../../core/PayTabnavigation.jsp" /> --%>

	</div>

	<div align="center"><%-- <hdiits:formSubmitButton name="Submit" id="Submit" type="button" caption="Submit" onclick="submitForm()" /> --%>
		<hdiits:button name="Save" style="text-align:center;" id="Save" type="button" caption="Save" onclick="submitForm()" /> 
		
		<hdiits:button name="btnClose1" style="text-align:center;" type="button" id="btnClose1" captionid="eis.close" bundle="${commonLables}" onclick="onclosefunction()" />
	</div>

	<hdiits:hidden default="${resValue.bulkList}" id="hdnDeductionSize"
		name="hdnDeductionSize"></hdiits:hidden>
	<hdiits:hidden default="${listSize}" id="listSize" name="listSize"></hdiits:hidden>
	
</hdiits:form>



<%
}
  	  catch(Exception e)
  	  {
  		  
  		  e.printStackTrace();
  	  }
%>
<script type="text/javascript">
if('${msg}'=='${success}')
{
	//alert('A');
	getEmployeeFromBillgroup();
}
</script>
</body>
</html>