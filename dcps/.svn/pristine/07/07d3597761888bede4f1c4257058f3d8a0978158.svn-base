
<%
try {
%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<fmt:setBundle basename="resources.eis.eisLables_en_US"
	var="adminCreatePostLabel" scope="request" />
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"
	scope="request" />

<%@page import="java.util.List"%>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="dataList" value="${resValue.recordList}"></c:set>
<c:set var="userpostlist" value="${resValue.userpostlist}"></c:set>
<c:set var="fieldList" value="${resValue.fieldCaptionList}"></c:set>
<%--<c:set var="orgPostDtlList" value="${resValue.orgPostDtlList}"></c:set>--%>
<c:set var="orderList" value="${resultObj.resultValue.orderList}"></c:set>
<c:set var="datasize" value="${resValue.recordsize}"></c:set>

<c:set var="empName" value="${resValue.empName}"></c:set>
<c:set var="designation" value="${resValue.Designation}"></c:set>
<c:set var="billList" value="${resValue.billList}"></c:set>
<c:set var="total" value="${resValue.total}"></c:set>
<c:set var="permtotal" value="${resValue.permtotal}"></c:set>
<c:set var="temptotal" value="${resValue.temptotal}"></c:set>
<c:set var="pervacantcount" value="${resValue.pervacantcount}"></c:set>
<c:set var="tempvacantcount" value="${resValue.tempvacantcount}"></c:set>
<c:set var="perfilledcount" value="${resValue.perfilledcount}"></c:set>
<c:set var="tempfilledcount" value="${resValue.tempfilledcount}"></c:set>
<c:set var="vacanttotal" value="${resValue.vacanttotal}"></c:set>
<c:set var="filledtotal" value="${resValue.filledtotal}"></c:set>
<c:set var="flagSearch" value="${resValue.flag}"></c:set>
<c:set var="postTypeLst" value="${resValue.postTypeLst}"></c:set>

<%
List dataList = (List) pageContext.getAttribute("dataList");
int size = dataList.size();

pageContext.setAttribute("listSize",size);
%>
<html>
<head>


<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>

<script type="text/javascript" src="script/common/common.js"></script>


<script type="text/javascript">



function checkboxfunction()
{     

	
	var size = document.getElementById('datavalue').value;
	 showProgressbar("Please wait...");
	if(document.getElementById("mainchkbx").checked==true)
	{
	for ( var i = 1; i <= size; i++) 
    {
	document.getElementById("chkbx" + i).checked=true;
	
	var chkBox=document.getElementById('chkbx'+i);
	var postType=document.getElementById('postType'+i);
	var endDate=document.getElementById('endDate'+i);
	//var index=Number(0)+i;
	var newPost=document.getElementById('newPost'+i);
	testFunction(chkBox,postType,newPost,endDate,i);
	}
	}
	else if(document.getElementById("mainchkbx").checked==false)
		for ( var j = 1; j <= size; j++) 
	    {
			
		document.getElementById("chkbx" + j).checked=false;
		chkBox=document.getElementById('chkbx'+j);
		postType=document.getElementById('postType'+j);
		endDate=document.getElementById('endDate'+j);
		newPost=document.getElementById('newPost'+j);
		testFunction(chkBox,postType,newPost,endDate,j);
		}
     
	 hideProgressbar("Please wait...");
}


	


function EnterkeyPressed(e,form)
{	
  
  var whichCode = (window.Event) ? e.which : e.keyCode;
	if ((e.keyCode==13))
	{
		searchvalues();
	}
}   


	function GetPostfromOrder() {
		//clearPostList();

		xmlHttp = GetXmlHttpObject();
		if (xmlHttp == null) {
			alert("Your browser does not support AJAX!");
			return;
		}

		var url = '';
		var uri = '';
		url = uri + '&orderId=' + document.frmAdminCrtPost.order.value;
		var actionf = "GetPostfromOrder";
		uri = './hrms.htm?actionFlag=' + actionf;
		url = uri + url + "&editFlag=0";
		xmlHttp.onreadystatechange = GetPostsfromOrder;
		xmlHttp.open("POST", encodeURI(url), true);
		xmlHttp.send(null);
	}

	function GetPostsfromOrder() {
		if (xmlHttp.readyState == complete_state) {
			var post = document.getElementById("post");

			var XMLDoc = xmlHttp.responseXML.documentElement;
			var entries = XMLDoc.getElementsByTagName('post-mapping');
			var val = 0;
			var text = '';
			for ( var i = 0; i < entries.length; i++) {
				val = entries[i].childNodes[0].text;
				text = entries[i].childNodes[1].text;
				var y = document.createElement('option')
				y.value = val;
				y.text = text;
			
				try {
					post.add(y, null);
				} catch (ex) {
					post.add(y);
				}

			}
		}
	}

	function clearOrderList() {
		var v = document.getElementById("order").length;
		for (i = 1; i <= v; i++) {
			lgth = document.getElementById("order").options.length - 1;
			document.getElementById("order").options[lgth] = null;
		}
	}

	function clearPostList() {
		var v = document.getElementById("post").length;
		for (i = 1; i <= v; i++) {
			lgth = document.getElementById("post").options.length - 1;
			document.getElementById("post").options[lgth] = null;
		}
	}

	function getOrderList() {
		alert('function called');
		xmlHttp = GetXmlHttpObject();
		if (xmlHttp == null) {
			alert("Your browser does not support AJAX!");
			return;
		}

		var url;
		var uri = '';
		url = uri + '&cmbDept=' + document.forms[0].cmbDept.value;
		var actionf = "getOrderListByLocation";
		uri = './hrms.htm?actionFlag=' + actionf;
		url = uri + url;
		//alert(' ' + url);	  		  		  
		xmlHttp.onreadystatechange = deptChanged;
		xmlHttp.open("POST", encodeURI(url), true);
		xmlHttp.send(null);
	}

	function deptChanged() {
		if (xmlHttp.readyState == complete_state) {

			clearOrderList();

			var order = document.getElementById("order");

			var XMLDoc = xmlHttp.responseXML.documentElement;

			if (XMLDoc == null) {
				window.status = 'No Records Found.';
			} else {
				window.status = '';
				var orderList = XMLDoc.getElementsByTagName('order-mapping');
				for ( var i = 0; i < orderList.length; i++) {
					val = orderList[i].childNodes[0].text;
					text = orderList[i].childNodes[1].text;
					//     				    alert('Village val is:   ' + val + 'and text is:-   ' + text);
					var y = document.createElement('option');
					y.value = val;
					y.text = text;

					try {
						order.add(y, null);
					}

					catch (ex) {
						order.add(y);
					}
				}
			}
		}
	}
	function addNewEntry() 
	{
		document.getElementById("actionFlag").value="addAdminPostDtl";
		//document.forms[0].action = 'hdiits.htm?actionFlag=addAdminPostDtl';
		document.forms[0].action = 'hdiits.htm';
		document.forms[0].submit();
	}
	function test(reqId) 
	{
		document.getElementById("actionFlag").value="editAdminOrgPostData";
		document.getElementById("reqId").value=reqId;
		//document.forms[0].action = "hdiits.htm?actionFlag=editAdminOrgPostData&reqId="+ reqId;
		document.forms[0].action = "hdiits.htm";
		document.forms[0].submit();
	}
	function editData() 
	{
		var postId = document.frmAdminCrtPost.post.value;
		if (postId == "" || postId == null) {
			alert('Please Select Post to Edit!!!');
			return;
		} else {
			document.getElementById("actionFlag").value="editAdminOrgPostData";
			document.getElementById("reqId").value=postId;
			//document.forms[0].action = "hdiits.htm?actionFlag=editAdminOrgPostData&reqId="+ postId;
			document.forms[0].action = "hdiits.htm";
			document.forms[0].submit();
		}
	}	
	function deleteData() 
	{
		var isChecked = false;
		for ( var i = 0; i < document.forms[0].deletedata.length; i++) {
			if (document.forms[0].deletedata[i].checked) {
				isChecked = true;
			}
		}
		if (isChecked) {
			
			var answer = confirm("Are you sure want to delete the selected data?");
			if (answer) {
				document.getElementById("actionFlag").value="deleteAdminOrgPostData";
				//document.forms[0].action = 'hdiits.htm?actionFlag=deleteAdminOrgPostData';
				document.forms[0].action = "hdiits.htm";
				document.forms[0].submit();
			}
		} else {
			alert("Please select the checkbox");
		}
	}
	function searchData() 
	{
		document.getElementById("actionFlag").value="showAdminPostDtl";
		//document.forms[0].action = 'hdiits.htm?actionFlag=showAdminPostDtl';
		document.forms[0].action = "hdiits.htm";
		document.forms[0].submit();
	}
	function submitFormAuto()
	{
		if(chkValue())
		{
			var empId = document.getElementById("Employee_ID_viewPostSearch").value;
			document.getElementById("actionFlag").value="showAdminPostDtl";
			document.getElementById("empId").value=empId;
			//document.forms[0].action='hdiits.htm?actionFlag=showAdminPostDtl&empId='+empId;
			document.forms[0].action = "hdiits.htm";
			document.forms[0].submit();
			//document.frmAdminCrtPostsearch.submit();
		}
		return true;
	}
	function chkValue()
	{
		var empId = document.getElementById("Employee_ID_viewPostSearch").value;
		if(empId=="")
		{			
			alert("Please search the employee first");
			return false;
		}
		else
		{			
			return true;
		}	
	}	
	function setStyle(x)
	{
	document.getElementById(x).style.background="#ccccff";
	}
	function searchvalues()
	{
		var value = document.getElementById("value").value;
		var option_selected = document.getElementById("option_selected").value;
		if(option_selected == "Post")
		{
			document.getElementById("actionFlag").value="showAdminPostDtl";
			document.getElementById("Post").value=value;
			//document.forms[0].action = 'hdiits.htm?actionFlag=showAdminPostDtl&Post=' + value;
			document.forms[0].action = "hdiits.htm";
			document.forms[0].submit();
		}
		if(option_selected == "PsrNo")
		{
			if(check4number(value))
			{
				document.getElementById("actionFlag").value="showAdminPostDtl";
				document.getElementById("PsrNo").value=value;
				//document.forms[0].action='hdiits.htm?actionFlag=showAdminPostDtl&PsrNo='+value;
				document.forms[0].action = "hdiits.htm";
				document.forms[0].submit();
			}	
		}
		if(option_selected == "BillNo")
		{
			if(check4number(value))
			{
				document.getElementById("actionFlag").value="showAdminPostDtl";
				document.getElementById("BillNo").value=value;
				//document.forms[0].action='hdiits.htm?actionFlag=showAdminPostDtl&BillNo='+value;
				document.forms[0].action = "hdiits.htm";
				document.forms[0].submit();
			}	
		}	
		if(option_selected == "Dsgn")
		{
			document.getElementById("actionFlag").value="showAdminPostDtl";
			document.getElementById("Dsgn").value=value;
			//document.forms[0].action='hdiits.htm?actionFlag=showAdminPostDtl&Dsgn='+value;
			document.forms[0].action = "hdiits.htm";
			document.forms[0].submit();
		}
		if(option_selected == "")
			alert("Please Select Criteria For Search");
	}
	function check4number(n)
	{	
		var flag = true;
		if(isNaN(n) == true)
	    {
			flag = false;
			alert ("This Value Is Not A Number. \nPlease Enter A Valid Field Value.");
	    }
	    if(n == "")
	    {
	    	flag = false;
			alert ("The Field is blank ");
		}
	    return flag;
	}
		
	function searchFunction()
	{
		//var flagSearch = document.getElementById("flagSearch").value;
		var flagSearch=1;
		var url;

		
		var FieldDept = document.getElementById('cmbFieldDept').value;
		var DdoDtl = document.getElementById('cmbDdoDtl').value;

		if(FieldDept == "-1"){
			alert('Please select a Field Department');
			document.getElementById("cmbFieldDept").focus();
			return false;
		}
		if(DdoDtl == "-1"){
			alert('Please select a DDO Office');
			document.getElementById("cmbDdoDtl").focus();
			return false;
		}
		
		var dsgnId = document.getElementById('designationCmb').value;
		var billGrpId = document.getElementById('billCmb').value;
		var posttype=document.getElementById('postTypeCmb').value;

		if(dsgnId>0 && billGrpId>0 && posttype > 0)
		{
			document.getElementById("actionFlag").value="searchChangePostDtls";
			document.getElementById("dsgnId").value=dsgnId;
			document.getElementById("billGrpId").value=billGrpId;
			document.getElementById("FieldDept").value=FieldDept;
			document.getElementById("DdoDtl").value=DdoDtl;
			document.getElementById("postType").value=posttype;
			document.getElementById("flagSearch").value=flagSearch;
			//var url="./ifms.htm?actionFlag=searchChangePostDtls&dsgnId="+dsgnId+"&billGrpId="+billGrpId+"&FieldDept="+FieldDept+"&DdoDtl="+DdoDtl+"&postType="+posttype+"&flagSearch="+flagSearch;			
			url="./ifms.htm";
			document.frmAdminCrtPost.action=url;
			document.frmAdminCrtPost.submit();
			showProgressbar("Please wait...");
			return true;
		}
		else if(dsgnId>0 && billGrpId>0)
		{

			document.getElementById("actionFlag").value="searchChangePostDtls";
			document.getElementById("dsgnId").value=dsgnId;
			document.getElementById("billGrpId").value=billGrpId;
			document.getElementById("FieldDept").value=FieldDept;
			document.getElementById("DdoDtl").value=DdoDtl;
			document.getElementById("flagSearch").value=flagSearch;
			//url="./ifms.htm?actionFlag=searchChangePostDtls&dsgnId="+dsgnId+"&billGrpId="+billGrpId+"&FieldDept="+FieldDept+"&DdoDtl="+DdoDtl+"&flagSearch="+flagSearch;
			url="./ifms.htm";
		
			document.frmAdminCrtPost.action=url;
			document.frmAdminCrtPost.submit();
			showProgressbar("Please wait...");
			return true;
		}
		else if(dsgnId>0 && posttype>0)
		{
			document.getElementById("actionFlag").value="searchChangePostDtls";
			document.getElementById("dsgnId").value=dsgnId;
			document.getElementById("FieldDept").value=FieldDept;
			document.getElementById("DdoDtl").value=DdoDtl;
			document.getElementById("postType").value=posttype;
			document.getElementById("flagSearch").value=flagSearch;
			
			//var url="./ifms.htm?actionFlag=searchChangePostDtls&dsgnId="+dsgnId+"&FieldDept="+FieldDept+"&DdoDtl="+DdoDtl+"&postType="+posttype+"&flagSearch="+flagSearch;
			url="./ifms.htm";
		
			document.frmAdminCrtPost.action=url;
			document.frmAdminCrtPost.submit();
			showProgressbar("Please wait...");
			return true;
		}
		else if(billGrpId>0 && posttype>0)
		{
			document.getElementById("actionFlag").value="searchChangePostDtls";
			document.getElementById("billGrpId").value=billGrpId;
			document.getElementById("FieldDept").value=FieldDept;
			document.getElementById("DdoDtl").value=DdoDtl;
			document.getElementById("postType").value=posttype;
			document.getElementById("flagSearch").value=flagSearch;
			//var url="./ifms.htm?actionFlag=searchChangePostDtls&billGrpId="+billGrpId+"&FieldDept="+FieldDept+"&DdoDtl="+DdoDtl+"&postType="+posttype+"&flagSearch="+flagSearch;
			url="./ifms.htm";
		
			document.frmAdminCrtPost.action=url;
			document.frmAdminCrtPost.submit();
			showProgressbar("Please wait...");
			return true;
		}
		else if(dsgnId>0)
		{
			document.getElementById("actionFlag").value="searchChangePostDtls";
			document.getElementById("dsgnId").value=dsgnId;
			document.getElementById("FieldDept").value=FieldDept;
			document.getElementById("DdoDtl").value=DdoDtl;
			document.getElementById("flagSearch").value=flagSearch;
			//var url="./ifms.htm?actionFlag=searchChangePostDtls&dsgnId="+dsgnId+"&FieldDept="+FieldDept+"&DdoDtl="+DdoDtl+"&flagSearch="+flagSearch;
			url="./ifms.htm";
			
			document.frmAdminCrtPost.action=url;
			document.frmAdminCrtPost.submit();
			showProgressbar("Please wait...");
			return true;
			
		}
		else if(billGrpId>0)
		{
			document.getElementById("actionFlag").value="searchChangePostDtls";
			document.getElementById("billGrpId").value=billGrpId;
			document.getElementById("FieldDept").value=FieldDept;
			document.getElementById("DdoDtl").value=DdoDtl;
			document.getElementById("flagSearch").value=flagSearch;
			//var url="./ifms.htm?actionFlag=searchChangePostDtls&billGrpId="+billGrpId+"&FieldDept="+FieldDept+"&DdoDtl="+DdoDtl+"&flagSearch="+flagSearch;
			url="./ifms.htm";
			
			document.frmAdminCrtPost.action=url;
			document.frmAdminCrtPost.submit();
			showProgressbar("Please wait...");
			return true;
			
			
		}
		else if(posttype>0)
		{
			document.getElementById("actionFlag").value="searchChangePostDtls";
			document.getElementById("FieldDept").value=FieldDept;
			document.getElementById("DdoDtl").value=DdoDtl;
			document.getElementById("postType").value=posttype;
			document.getElementById("flagSearch").value=flagSearch;
			//var url="./ifms.htm?actionFlag=searchChangePostDtls&FieldDept="+FieldDept+"&DdoDtl="+DdoDtl+"&postType="+posttype+"&flagSearch="+flagSearch;
			url="./ifms.htm";
		
			document.frmAdminCrtPost.action=url;
			document.frmAdminCrtPost.submit();
			showProgressbar("Please wait...");
			return true;
			
			
		}
		else
		{	
			document.getElementById("actionFlag").value="searchChangePostDtls";
			document.getElementById("FieldDept").value=FieldDept;
			document.getElementById("DdoDtl").value=DdoDtl;
			document.getElementById("flagSearch").value=flagSearch;
			
			//var url="./ifms.htm?actionFlag=searchChangePostDtls&billGrpId=&FieldDept="+FieldDept+"&DdoDtl="+DdoDtl+"&flagSearch="+flagSearch;
			url="./ifms.htm";
			
			document.frmAdminCrtPost.action=url;
			document.frmAdminCrtPost.submit();
			showProgressbar("Please wait...");
			return true;
			
		}
	}
	function backtoSearchPage(){
		document.getElementById("actionFlag").value="PostConversion";
		document.getElementById("elementId").value="90002641";
		//self.location.href = "ifms.htm?actionFlag=PostConversion&elementId=90002651";
		var url="ifms.htm";
		document.frmAdminCrtPost.action=url;
		document.frmAdminCrtPost.submit();
	}
	function showPostDtls(){
		var FieldDept = document.getElementById('cmbFieldDept').value;
		var DdoDtl = document.getElementById('cmbDdoDtl').value;
		
		if(FieldDept != "-1" && DdoDtl != "-1"){
			document.getElementById("actionFlag").value="showChangePostDtl";
			document.getElementById("elementId").value="90002641";
			document.getElementById("FD").value=FieldDept;
			document.getElementById("DDO").value=DdoDtl;
			//self.location.href = "ifms.htm?actionFlag=showChangePostDtl&elementId=90002651&FD="+FieldDept+"&DDO="+DdoDtl;
			self.location.href = "ifms.htm";
		}
	}
	
    
	function compareDates(fieldName1,fieldName2,alrtStr,flag)
	{
		
		
	
		var one_day = 1000*60*60*24; 

		var Date1 = fieldName1.value;
		var Date2 = fieldName2.value;

		var la_Date1 = new Array();
	    la_Date1 = Date1.split("/");
	    var la_Date2 = new Array();
	    la_Date2 = Date2.split("/");
	    
	    var date1 = new Date(la_Date1[2],la_Date1[1]-1,la_Date1[0]);
	    var date2 = new Date(la_Date2[2],la_Date2[1]-1,la_Date2[0]);
	    
		var Diff = Math.floor((date2.getTime() - date1.getTime())/(one_day)); 
		
		if(flag == '=' && Diff == 0){
			alert(alrtStr);
			fieldName2.value="";
			return false;
		}

	    else if( (flag == '<' && Diff<0) || (flag == '>' && Diff>0))
	    {
	        	alert(alrtStr);
	        	fieldName2.value="";
	        	return false;
	        	
	    	    }
	    else {
	    	return true;
	    }
	}
	function DisplayOrderDate(i) {
		var xmlHttp = null;
		var orderId = document.getElementById("orderCmb"+i).value;
         if(orderId==-1)
        	 document.getElementById("OrderDate"+i).value ="";
		var retValue = true;
		if (orderId == "") {
			alert("Please search the Order Name");
			retValue = false;
		} else {

			try {
				xmlHttp = new XMLHttpRequest();
			} catch (e) {
				// Internet Explorer    
				try {
					xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
				} catch (e) {
					try {
						xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
					} catch (e) {
						alert("Your browser does not support AJAX!");
						retValue = false;
					}
				}
			}
			var url = "hrms.htm?actionFlag=getOrderDate&orderId=" + orderId
					+ "";
			xmlHttp.onreadystatechange = function() {

				if (xmlHttp.readyState == 4) {

					if (xmlHttp.status == 200) {

						var oderDate;

						var XMLDocForAjax = xmlHttp.responseXML.documentElement;

						var orderDate = XMLDocForAjax
								.getElementsByTagName('getOldOrderDate');

						if (orderDate.length != 0) {

							orderDate = orderDate[0].childNodes[0].text;

							document.getElementById("OrderDate"+i).value = orderDate;
						}
					}
				}
			}

			xmlHttp.open("POST", encodeURI(url), false);
			xmlHttp.setRequestHeader("Content-Type",
					"text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));
			return retValue;
		}
	}

	
	function convertFunction()
	{
	var stringfor1=null;
	var totalString=null;
	
	    var size=document.getElementById('datavalue').value;
	    ///alert("size="+size);
	   
	    var isChecked=false;
	    showProgressbar("Please wait..."); 
	   
	    
		for(var i=1; i<=size;i++)
			{
	      if(document.getElementById("chkbx"+i).checked==true){    
	    	  isChecked=true;   
	    	 // alert("check0" +isChecked);
		var postId=document.getElementById("chkbx"+i).value;
	
		var postType=document.getElementById("postType"+i).innerHTML
		var newPost=document.getElementById("newPost"+i).innerHTML;
		 var orderid=document.getElementById("orderCmb" + i).value;
		if(postType=="T")
		{
			var endDate=document.getElementById("endDate"+i).innerHTML;
	
		 stringfor1= postId+"~"+postType+"~"+newPost+"~"+endDate+"~"+orderid;
	
		 totalString= totalString+","+stringfor1;
		}
		else
			{
			var endDate1=document.getElementById("tbxEndDate"+i).value;
		  if((document.getElementById("tbxEndDate"+i )!=null )&& document.getElementById("tbxEndDate"+i).value=="")
		  {
			  alert('Please enter the end Date');
			  hideProgressbar();
			  return false;
		  }
		
			 stringfor1= postId+"~"+postType+"~"+newPost+"~"+endDate1+"~"+orderid;
			 totalString= totalString+","+stringfor1;
			}
			

		}
			
		}
	
if(isChecked){
		
		//var uri = 'ifms.htm?actionFlag=saveChangePostDtls&totalString='+totalString;
		var uri = 'ifms.htm';
		
		
		var url="actionFlag=saveChangePostDtls&totalString="+totalString+"&postId="+postId;
		
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {
						getCount(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...');} 
			          } );
        
		
		//document.frmAdminCrtPost.action = uri ;
		//document.frmAdminCrtPost.submit();
	//	var url="./ifms.htm?actionFlag=saveChangePostDtls&totalString="+totalString;
		
	}
	
		else
		{
			alert("Please select at least 1 post");
			hideProgressbar("Please Wait......");
				return 0;
		}
	    hideProgressbar("Please Wait......");
	}

function getCount(myAjax){
		XMLDoc = myAjax.responseXML.documentElement;
		var tempCount = XMLDoc.getElementsByTagName('tempCount');
		var permCount = XMLDoc.getElementsByTagName('permCount');
		
		var tCount = tempCount[0].firstChild.nodeValue;
		var pCount = permCount[0].firstChild.nodeValue;
		
		alert(tCount+ ' post has been converted to temporary and ' +pCount+ '  post has been converted to permanent.');
		

		//document.frmAdminCrtPost.action = uri ;
		//document.frmAdminCrtPost.submit();
		document.getElementById("actionFlag").value="PostConversion";
		//var url="./ifms.htm?actionFlag=PostConversion";
		var url="./ifms.htm";
		
		document.frmAdminCrtPost.action = url ;
		document.frmAdminCrtPost.submit();
			
		
	}

function getExcelReport()
{
	 //showProgressbar("Please wait..."); 
	//alert("Within Excel Reporting"+flagSearch);
	var FieldDept = document.getElementById('cmbFieldDept').value;
		var DdoDtl = document.getElementById('cmbDdoDtl').value;
		
		document.getElementById("actionFlag").value="generateDDOExcelTest";
		document.getElementById("FD").value=FieldDept;
		document.getElementById("DDO").value=DdoDtl;
	 	//var  url="./ifms.htm?actionFlag=generateDDOExcelTest&FD="+FieldDept+"&DDO="+DdoDtl;
	 	var  url="./ifms.htm"; 
	 	document.frmAdminCrtPost.action = url ;
		document.frmAdminCrtPost.submit();
	//	hideProgressbar("Please Wait...");
		}	


</script>
</head>
<body>
<c:set value="0" var="i"></c:set>
<br />
<br />
<fieldset class="tabstyle"><legend>Details</legend>

<table width="100%" border="1" class="tabstyle">
	<tr>
		<td></td>
		<td>Total</td>
		<td>Permanent</td>
		<td>Temporary</td>
	</tr>

	<tr>
		<td>Total</td>
		<td><c:out value="${total}"></c:out></td>
		<td>${permtotal}</td>
		<td>${temptotal}</td>
	</tr>
	<tr>
		<td>Vacant</td>
	
		<td>${resValue.vacanttotal}</td>
		<td>${resValue.pervacantcount}</td>
		<td>${resValue.tempvacantcount}</td>
		

	</tr>
	<tr>
		<td>Filled</td>
		<td>${resValue.filledtotal}</td>
		<td>${resValue.perfilledcount}</td>
		<td>${resValue.tempfilledcount}</td>
		

	</tr>

</table>
</fieldset>
<hdiits:form name="frmAdminCrtPost" validate="true" method="post"
	encType="multipart/form-data">

	<br />
	<br />
	<fieldset class="tabstyle"><legend>Search Post
	Details</legend>
	<table width="100%">
		<tr>
			<td width="8%"></td>
			<td width="10%"><fmt:message key="CMN.FIELDDEPT"
				bundle="${dcpsLables}"></fmt:message></td>
			<td width="30%"><select name="cmbFieldDept" id="cmbFieldDept"
				style="width: 350px" disabled="disabled">
				<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach var="FieldDept" items="${resValue.lLstFieldDept}">
					<c:choose>
						<c:when test="${resValue.FieldDeptCode == FieldDept.id}">
							<option value="${FieldDept.id}" selected="selected"><c:out
								value="${FieldDept.desc}"></c:out></option>
						</c:when>
						<c:otherwise>
							<option value="${FieldDept.id}"><c:out
								value="${FieldDept.desc}" /></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select></td>
			<td width="15%">DDO</td>
			<td width="35%"><select name="cmbDdoDtl" id="cmbDdoDtl"
				onchange="showPostDtls();" style="width: 350px" disabled="disabled">
				<c:if test="${resValue.lLstDdo == null}">
					<option value="-1"><fmt:message
						key="COMMON.DROPDOWN.SELECT" /></option>
				</c:if>
				<c:forEach var="DDO" items="${resValue.lLstDdo}">
					<c:choose>
						<c:when test="${resValue.DDOCode == DDO.id}">
							<option value="${DDO.id}" selected="selected"><c:out
								value="${DDO.desc}"></c:out></option>
						</c:when>
						<c:otherwise>
							<option value="${DDO.id}"><c:out value="${DDO.desc}" />
							</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td></td>
			<td class="fieldLabel"><b><hdiits:caption
				captionid="admin.designation" bundle="${adminCreatePostLabel}" /></b></td>
			<td class="fieldLabel"><select name="designationCmb"
				id="designationCmb" captionid="admin.designation"
				bundle="${adminCreatePostLabel}" mandatory="false"
				validation="sel.isrequired">
				<option value=""><hdiits:caption captionid="admin.select"
					bundle="${adminCreatePostLabel}"></hdiits:caption></option>
				<c:forEach items="${designation}" var="desi">
					<c:choose>
						<c:when test="${desi.dsgnCode == resValue.designationId}">
							<option value="<c:out value='${desi.dsgnCode}'/>"
								selected="selected"><c:out value="${desi.dsgnName}" /></option>
						</c:when>
						<c:otherwise>
							<option value="<c:out value='${desi.dsgnCode}'/>"><c:out
								value="${desi.dsgnName}" /></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select></td>

			<td class="fieldLabel">Scheme Code (<hdiits:caption
				captionid="admin.bill" bundle="${adminCreatePostLabel}" />)</td>
			<td class="fieldLabel"><select name="billCmb" id="billCmb"
				captionid="admin.bill" style="width: 350px"
				bundle="${adminCreatePostLabel}" mandatory="false"
				validation="sel.isrequired">
				<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach items="${billList}" var="billList">
					<c:choose>
						<c:when test="${billList.dcpsDdoBillGroupId == resValue.billId}">
							<option value="<c:out value="${billList.dcpsDdoBillGroupId}"/>"
								title="${billList.dcpsDdoBillDescription}" selected="selected"><c:out
								value="${billList.dcpsDdoSchemeCode} (${billList.dcpsDdoBillDescription})" /></option>

						</c:when>
						<c:otherwise>
							<option value="<c:out value="${billList.dcpsDdoBillGroupId}"/>"
								title="${billList.dcpsDdoBillDescription}"><c:out
								value="${billList.dcpsDdoSchemeCode} (${billList.dcpsDdoBillDescription})" /></option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td></td>

			<td class="fieldLabel">Post Type</td>
			<td class="fieldLabel"><select name="postTypeCmb"
				id="postTypeCmb" style="width: 100px" mandatory="false"
				validation="sel.isrequired">
				<c:forEach items="${resValue.postTypeLst}" var="postList">
					<c:choose>
						<c:when test="${postList[0]  == resValue.postTypeid}">
							<option value="${postList[0]}" selected="selected">${postList[1]}</option>							
						</c:when>
						<c:otherwise>
						<option value="${postList[0]}" >${postList[1]}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select></td>
			<td></td>
			<td></td>
		</tr>

	</table>
	<input type="hidden" name="flagSearch" id="flagSearch"
		value='${flagSearch}' /> <br>
	<center><hdiits:button name="Search" id="Search" type="button"
		value="Search" onclick="searchFunction();" /> <hdiits:button
		name="btnBack" id="btnBack" type="button" value="Back"
		onclick="backtoSearchPage();" /></center>
	<br>

	</fieldset>
	<div id="combo" style="overflow: auto;height:400px;">
	<table class="datatable">
	<thead>
	<tr>
	<th class="datatableheader sortable" colspan="5">&nbsp;
	</th>
<th class="datatableheader sortable" colspan="4">
Old Details [Before Conversion of Post(s)]
</th>
<th class="datatableheader sortable" colspan="4">
New Details [After Conversion of Post(s)]
</th>

</tr>
	<tr>
	<th class="datatableheader sortable">
<input type="checkbox" id="mainchkbx" value="" onclick="checkboxfunction()" />
</th>
<th class="datatableheader sortable">
Sr. No.
</th>
<th class="datatableheader">Designation</th>
<th class="datatableheader">Post Name</th>
<th class="datatableheader">Status</th>
<th class="datatableheader sortable">
Post Type
</th>
<th class="datatableheader sortable">
Post Start Date
</th>
<th class="datatableheader sortable">
Post End Date
</th>

<!--<th class="datatableheader sortable">
Bill Group
</th>
--><th class="datatableheader sortable">
GR No./GR Date
</th>
<th class="datatableheader sortable">
New Post
</th>
<th class="datatableheader sortable">
New End Date
</th>
<th class="datatableheader sortable">
New GR No.
</th>
<th class="datatableheader sortable">
New GR Date
</th>


</tr>
</thead>
<tbody>
<c:set var="i" value="1"/>
<c:forEach items="${dataList}" var="row">
<c:choose>
<c:when test="${i%2==0}">
<tr class="odd">
</c:when>
<c:otherwise>
<tr class="even">
</c:otherwise>
</c:choose>

<td class="tablecelltext" style="text-align: center; font-size: 12px;">
<input type="checkbox" id="chkbx${i}" value="${row.postId}"
				onclick="testFunction(chkbx${i}, postType${i}, newPost${i},endDate${i},${i});" />
</td>
<td class="tablecelltext" style="text-align: center; font-size: 12px;">
<c:out value="${i}"></c:out>
</td>
<td class="tablecelltext" style="text-align: center; font-size: 12px;">
${row.dsgnname}
</td>
<td class="tablecelltext" style="text-align: center; font-size: 12px;">
		${row.postname}
</td>
<td class="tablecelltext" style="text-align: center; font-size: 12px;">
	<label><c:choose>
				<c:when test="${row.empFullName == 'VACANT'}">
					<b><input type="radio" disabled="disabled" style="border:none;"></b>
				</c:when>
				<c:otherwise>
					<input type="radio" disabled="disabled" checked="checked"  style="border:none;width:100%">
				</c:otherwise>
			</c:choose>
			</label>
</td>
<td class="tablecelltext" style="text-align: center; font-size: 12px;">
	<label id="postType${i}">${row.postType}</label>
</td>
<td class="tablecelltext" style="text-align: center; font-size: 12px;">
<label>${row.startDate}</label>
			<input type="hidden" id="startDate${i}" value="${row.startDate}" />
</td>
<td class="tablecelltext" style="text-align: center; font-size: 12px;">
	${row.endDate}
</td>


<!--<td class="tablecelltext" style="text-align: center; font-size: 12px;">
   	${row.billNo}
</td>
--><td class="tablecelltext" style="text-align: center; font-size: 12px;">
		${row.empFname}/&nbsp${row.empMname}
</td>
<!--<td class="tablecelltext" style="text-align: center; font-size: 12px;">-->
<td class="tablecelltext" style="text-align: center; font-size: 12px;">
	<label id="newPost${i}"></label>
</td>
<td class="tablecelltext" style="text-align: center; font-size: 12px;">
<div id="tbxEndDate1${i}" style="display: none">
<label id="endDate${i}"></label>
			<input type="text" name="tbxEndDate" id="tbxEndDate${i}"
				
				onkeypress="digitFormat(this);dateFormat(this);"
				onBlur="compareDates(
			       document.getElementById('startDate${i}'),document.getElementById('tbxEndDate${i}')
			       ,'End Date should be greater than start date','<');" />
			       <img src='images/CalendarImages/ico-calendar.gif' 
															onClick='window_open("tbxEndDate${i}",375,570)'
															style="cursor: pointer;"/><label class="mandatoryindicator">*</label>
</div>															
</td>
<td class="tablecelltext" style="text-align: center; font-size: 12px;">
<select name="orderCmb" id="orderCmb${i}" style="width: 230px"
				onchange="DisplayOrderDate('${i}');">
				<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach items="${resultObj.resultValue.orderList}"
					var="orderList">
					<option value="<c:out value="${orderList.orderId}"/>"><c:out
						value="${orderList.orderName}" /></option>

				</c:forEach>
			</select>

</td>
<td class="tablecelltext" style="text-align: center; font-size: 12px;">
<input type="text" size="10" name="OrderDate" id="OrderDate${i}"
				maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
				onBlur="validateDate(this);" value="" disabled="disabled" />

</td>
<!--<td class="tablecelltext" style="text-align: center; font-size: 12px;">
 	<input type="text" size="10" name ="Remarks" id="Remarks${i}" />         

</td>

--></tr>
<c:set var="i" value="${i+1}"/>
</c:forEach>
</tbody>

	
		</table>
		</div>
	
	<!--<display:table pagesize="500" name="${dataList}" id="row"
		export="false" requestURI="hrms.htm?actionFlag=showChangePostDtl"
		requestURIcontext="false" style="width:100%;"
		decorator="com.tcs.sgv.user.service.AdminOrgPostDtlDecorator">

		<display:column style="text-align: center;font-size:12px;"
			class="tablecelltext" title="Sr. No." headerClass="datatableheader"
			sortable="true">
			<input type="checkbox" id="chkbx${i}" value="${row.postId}"
				onclick="testFunction(chkbx${i},postType${i},newPost${i},endDate${i},${i});" />
		</display:column>


		<display:column style="text-align: center;font-size:12px;"
			class="tablecelltext" title="Sr. No." headerClass="datatableheader"
			sortable="true" value="${i+1}">
			<c:out value="${i}"></c:out>
		</display:column>

		<c:set value="${row.postId}" var="otherIdForLink" />
		<display:column class="tablecelltext" title="Employee Name"
			headerClass="datatableheader" sortable="true"
			style="text-align:LEFT;font-size:12px;">
			<%--<a href="./hrms.htm?actionFlag=editAdminOrgPostData&reqId=${row.postId}&edit=Y" id = "otherLink${row.postId}">
								${row.empFullName} </a>--%>


			<c:choose>
				<c:when test="${row.empFullName == 'VACANT'}">
					<b><input type="radio" disabled="disabled"></b>
				</c:when>
				<c:otherwise>
					<input type="radio" disabled="disabled" checked="checked">
				</c:otherwise>
			</c:choose>
		</display:column>

		<display:column class="tablecelltext" title="Designation"
			headerClass="datatableheader"
			style="text-align: LEFT;font-size:12px;" sortable="true">${row.dsgnname}</display:column>

		<display:column style="text-align: LEFT;font-size:12px;"
			class="tablecelltext" title="Post Name" headerClass="datatableheader">
			<%--<a href="./hrms.htm?actionFlag=editAdminOrgPostData&reqId=${row.postId}&edit=Y">${row.postname}</a>--%>
		${row.postname}
		</display:column>


		<display:column style="text-align: LEFT;font-size:12px;"
			class="tablecelltext" title="Post Type" headerClass="datatableheader"
			sortable="true">
			<%--<a href="./hrms.htm?actionFlag=editAdminOrgPostData&reqId=${row.postId}&edit=Y">${row.postname}</a>--%>
			<label id="postType${i}">${row.postType}</label>
		</display:column>

		<display:column style="text-align: LEFT;font-size:12px;"
			class="tablecelltext" title="Post Start Date"
			headerClass="datatableheader" sortable="true">
			<%--<a href="./hrms.htm?actionFlag=editAdminOrgPostData&reqId=${row.postId}&edit=Y">${row.postname}</a>--%>
			<label>${row.startDate}</label>
			<input type="hidden" id="startDate${i}" value="${row.startDate}" />
		</display:column>
		<display:column style="text-align: LEFT;font-size:12px;"
			class="tablecelltext" title="Post End Date"
			headerClass="datatableheader" sortable="true">
			<%--<a href="./hrms.htm?actionFlag=editAdminOrgPostData&reqId=${row.postId}&edit=Y">${row.postname}</a>--%>
		${row.endDate}
		</display:column>



		<display:column class="tablecelltext" title="New Post Type"
			headerClass="datatableheader"
			style="text-align: LEFT;font-size:12px;" sortable="true">
			<label id="newPost${i}"></label>
		</display:column>
		<display:column class="tablecelltext" title="End Date"
			headerClass="datatableheader"
			style="text-align: LEFT;font-size:12px;" sortable="true">
			<label id="endDate${i}"></label>
			<input type="text" name="tbxEndDate" id="tbxEndDate${i}"
				style="display: none"
				onkeypress="digitFormat(this);dateFormat(this);"
				onBlur="compareDates(
			       document.getElementById('startDate${i}'),document.getElementById('tbxEndDate${i}')
			       ,'End Date should be greater than date of joining','<');" />


		</display:column>

		<display:column style="text-align: LEFT;font-size:12px;"
			class="tablecelltext" title="GR No." headerClass="datatableheader"
			sortable="true">
			<%--<a href="./hrms.htm?actionFlag=editAdminOrgPostData&reqId=${row.postId}&edit=Y">${row.postname}</a>--%>
		${row.empFname}
		</display:column>

		<display:column style="text-align: LEFT;font-size:12px;"
			class="tablecelltext" title="GR Date" headerClass="datatableheader"
			sortable="true">
			<%--<a href="./hrms.htm?actionFlag=editAdminOrgPostData&reqId=${row.postId}&edit=Y">${row.postname}</a>--%>
		${row.empMname}
		</display:column>

		<display:column style="text-align: LEFT;font-size:12px;"
			class="tablecelltext" title="New GR No."
			headerClass="datatableheader" sortable="true">
			<%--<a href="./hrms.htm?actionFlag=editAdminOrgPostData&reqId=${row.postId}&edit=Y">${row.postname}</a>--%>

			<select name="orderCmb" id="orderCmb${i}" style="width: 230px"
				onchange="DisplayOrderDate('${i}');">
				<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" /></option>
				<c:forEach items="${resultObj.resultValue.orderList}"
					var="orderList">
					<option value="<c:out value="${orderList.orderId}"/>"><c:out
						value="${orderList.orderName}" /></option>

				</c:forEach>
			</select>
		</display:column>

		<display:column style="text-align: LEFT;font-size:12px;"
			class="tablecelltext" title=" New GR Date."
			headerClass="datatableheader" sortable="true">
			<%--<a href="./hrms.htm?actionFlag=editAdminOrgPostData&reqId=${row.postId}&edit=Y">${row.postname}</a>--%>

			<input type="text" size="10" name="OrderDate" id="OrderDate${i}"
				maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
				onBlur="validateDate(this);" value="" disabled="disabled" />

		</display:column>


		<display:setProperty name="export.pdf" value="false" />
		<c:set var="i" value="${i+1}"></c:set>

	</display:table>
	--><table width="100%">
		<tr width="100%">
			<td align="center"><%--	<hdiits:button name="addNewEntry_button" captionid="admin.AddEntry" bundle="${adminCreatePostLabel}" onclick="addNewEntry()" type="button"/> --%>
			<%--	<hdiits:button captionid="admin.Delete" bundle="${adminCreatePostLabel}" onclick="deleteData()" name="cmdDel2" type="button"/> --%>
			</td>
		</tr>
	</table>
	<c:choose>
		<c:when
			test="${listSize eq 1 && empName != null && not empty empName }">
			<script>
        	document.getElementById("otherLink${otherIdForLink}").click();
        </script>
		</c:when>
	</c:choose>
	<input type="hidden" value="${datasize}" id="datavalue">
	
	
	<input type="hidden" name="actionFlag" id="actionFlag"/>
	<input type="hidden" name="reqId" id="reqId"/>
	<input type="hidden" name="empId" id="empId"/>
	<input type="hidden" name="Post" id="Post"/>
	<input type="hidden" name="PsrNo" id="PsrNo"/>
	<input type="hidden" name="BillNo" id="BillNo"/>
	<input type="hidden" name="Dsgn" id="Dsgn"/>
	<input type="hidden" name="dsgnId" id="dsgnId"/>
	<input type="hidden" name="billGrpId" id="billGrpId"/>
	<input type="hidden" name="FieldDept" id="FieldDept"/>
	<input type="hidden" name="DdoDtl" id="DdoDtl"/>
	<input type="hidden" name="postType" id="postType"/>
	<input type="hidden" name="flagSearch" id="flagSearch"/>
	<input type="hidden" name="elementId" id="elementId"/>
	<input type="hidden" name="FD" id="FD"/>
<input type="hidden" name="DDO" id="DDO"/>
	
	<center><c:if test="${datasize gt '0'}">
		<hdiits:button name="Convert" id="Convert" type="button"
			value="Convert" onclick="convertFunction();" />
	</c:if></center>
	<hdiits:button name="Export" id="Export" type="button"
		value="Export to Excel" onclick="getExcelReport('${flagSearch}');" style="width:140 px"/>
</hdiits:form>

</body>
</html>
<%
}catch(Exception e) {e.printStackTrace();}
%>
<script>

function testFunction(postId, postType, newPost,endDate,i){


	var postTypeValue= postType.innerHTML;

	var field1="tbxEndDate1"+i;
	if(document.getElementById("chkbx" + i).checked==true)
	{	


	if(postTypeValue=="T"){
		newPost.innerHTML="P";
		endDate.innerHTML="NA";
	}
	else if(postTypeValue=="P"){;
		newPost.innerHTML="T";
		endDate.style.display="none";
		document.getElementById(field1).style.display="block";
      
			
	}
	}
	else
	{
		var postTypeValue1= postType.innerHTML;
	var field3="tbxEndDate1"+i;	
	if(postTypeValue1=="T"){
		newPost.innerHTML="";
		endDate.innerHTML="";
	}
	else if(postTypeValue=="P"){
		newPost.innerHTML="";
		endDate.style.display="none";
		document.getElementById(field3).style.display="none";

	}
	
}
}
</script>
<ajax:select
	baseUrl="${pageContext.request.contextPath}/ifms.htm?actionFlag=getDdoListFromFieldDept"
	source="cmbFieldDept" target="cmbDdoDtl"
	parameters="FieldDeptCode={cmbFieldDept}"></ajax:select>