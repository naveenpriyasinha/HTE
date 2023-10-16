<%
try {
%>
<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript"  
    src="script/common/commonfunctions.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>

<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>

<c:set var="resultObj" value="${result}" > </c:set>
  <c:set  var="orderresultSet" value='${resultObj.resultValue.orderresultSet}' />
  <c:set  var="headresultSet" value='${resultObj.resultValue.headresultSet}' /> 
  <c:set  var="orgPostList" value="${resultObj.resultValue.orgPostList}" /> 
   <c:set  var="userpostrltSet" value="${resultObj.resultValue.userpostrltSet}" /> 
   <c:set  var="ohpList" value="${resultObj.resultValue.ohpList}" />
  <c:set var="result" value="${resultObj.resultValue.result}" ></c:set>
    <c:set var="deptList" value="${resultObj.resultValue.deptList}" ></c:set>
  <c:set var="msg" value="${resultObj.resultValue.msg}" ></c:set> 
    <c:set var="orgEmpList" value="${resultObj.resultValue.orgEmpList}" ></c:set>
  <c:set  var="desigresultSet" value='${resultObj.resultValue.desigresultSet}' /> 
  
   <c:set var="billList" value="${resultObj.resultValue.billList}" ></c:set>

    
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>


<script type="text/javascript" language="JavaScript">
function submitForm(){ 
	win = window.open("common/progressbar.html",'','width=270,height=30,titlebar=0,toolbar=0,left=400,top=300');
	return true;
}
function init()
{
	//alert(${resValue.msg});
	//document.forms[0].deptname.focus();
}

function getOrderList()
{
clearOrderList();
		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url; 
		  var uri='';
		  url= uri+'&cmbDept='+ document.forms[0].cmbDept.value;
		  var actionf="getOrderListByLocation";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
        //alert(' ' + url);	  		  		  
			xmlHttp.onreadystatechange=deptChanged;
			xmlHttp.open("POST",encodeURI(url),true);
			xmlHttp.send(null);		  	
}

function deptChanged()
	{
		if (xmlHttp.readyState==complete_state)
		{ 		
			
  	        
			var order = document.getElementById("order");
			
					var XMLDoc=xmlHttp.responseXML.documentElement;			
                    
                    if(XMLDoc==null)
                    {
                      window.status = 'No Records Found.';
                     }
                    else
                    {
                        window.status='';
                        var orderList = XMLDoc.getElementsByTagName('order-mapping');
	           			for ( var i = 0 ; i < orderList.length ; i++ )
	     				{
	     				    val=orderList[i].childNodes[0].text;    
	     				    text = orderList[i].childNodes[1].text; 
	//     				    alert('Village val is:-->' + val + 'and text is:--->' + text);
	     				    var y = document.createElement('option')   
	     			        y.value=val;
	     			        y.text=text;
	     			        
 	                        try
 	   				        {	      				    					
                               order.add(y,null);
	           		        }
	           		
	 						 catch(ex)
	   						 {
	   			 	  		    order.add(y); 
	   						 }
	   		          }
	   		         }
 	   }
 }  


function clearOrderList()
{
	var v=document.getElementById("order").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("order").options.length -1;
			document.getElementById("order").options[lgth] = null;
	}		
}

function GetHeadStructure()
{
var k = document.getElementById("headId").value;
document.addOHPmpg.headstructure.value="";

 			xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url=''; 
		  var uri='';
		  url= uri+'&subHeadId='+ k;
		  var actionf="GetHeadFromSubHead";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url;

		  xmlHttp.onreadystatechange=GetHeadFromSubHead;
		  xmlHttp.open("POST",encodeURI(url),true);
		  xmlHttp.send(null);		  	


}


function GetHeadFromSubHead()
	{	
		if (xmlHttp.readyState==complete_state)
		{ 
			var post = document.getElementById("srcPostId");
			
					var XMLDoc=xmlHttp.responseXML.documentElement;
				    var entries = XMLDoc.getElementsByTagName('heads-mapping');
					var demandCode=0;
					var budmjrhd_code=0;
					var budsubmjrhd_code=0;
					var budminhd_code=0;
					var subHeadName='';
					 for ( var i = 0 ; i < entries.length ; i++ )
	     			 {
     				    demandCode=entries[i].childNodes[0].text; 
     				    budmjrhd_code=entries[i].childNodes[1].text;    
     				    budsubmjrhd_code=entries[i].childNodes[2].text;    
     				    budminhd_code=entries[i].childNodes[3].text;    
     				    subHeadName =entries[i].childNodes[4].text; 
						
     				  
     			         document.addOHPmpg.headstructure.value= demandCode + '-' + budmjrhd_code + '-' + budsubmjrhd_code + '-' + budminhd_code + '-' + subHeadName ;
	                 }
	                
  }
}






function GetPostfromDesg()
{
		  
		  var v=document.getElementById("srcPostId").length;
		  for(i=0;i<v;i++)
		  {
			  lgth = document.getElementById("srcPostId").options.length -1;
			  document.getElementById("srcPostId").options[lgth] = null;
		  }	
		  // Added By Urvin. To Remove the Destincation Post List.
		   v=document.getElementById("postId").length;
		 {
		  for(i=0;i<v;i++)
		  {
				if(document.getElementById("postId").options[i].value==""){
			  lgth = document.getElementById("postId").options.length -1;
			  document.getElementById("postId").options[lgth] = null;
			  }
			  
		  }	
		  
		  }
		  	
		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url=''; 
		  var uri='';
		  url= uri+'&dsgnId='+ document.addOHPmpg.desig.value;
		  var actionf="GetPostfromDesg";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url+"&editFlag=0"; 
		  xmlHttp.onreadystatechange=GetPostsfromDesg;
		  xmlHttp.open("POST",encodeURI(url),true);
		  xmlHttp.send(null);		  	
}

function GetPostsfromDesg()
	{	
		if (xmlHttp.readyState==complete_state)
		{ 
			var post = document.getElementById("srcPostId");
			
					var XMLDoc=xmlHttp.responseXML.documentElement;
				    var entries = XMLDoc.getElementsByTagName('post-mapping');
					var val=0;
					var text='';
					 for ( var i = 0 ; i < entries.length ; i++ )
	     			 {
     				    val=entries[i].childNodes[0].text;    
     				    text =entries[i].childNodes[1].text; 
     				    var y = document.createElement('option')   
     			        y.value=val;
     			        y.text=text;	
     			        try
   				        {      				    					
                            post.add(y,null);
           			    }
 				        catch(ex)
   				        {
   			 		       post.add(y); 
   			   	        }	
	                
	                 }
  }
}


function Getheads()
{
	var v=document.getElementById("headId").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("headId").options.length -1;
			document.getElementById("headId").options[lgth] = null;
	}		
		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url=''; 
		  var uri='';
		  url= uri+'&orderId='+ document.addOHPmpg.order.value;
		  var actionf="Getheads";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
		  
		  xmlHttp.onreadystatechange=orderheadmap;
		  xmlHttp.open("POST",encodeURI(url),true);
		  xmlHttp.send(null);		  	
}

function orderheadmap()
	{	
		if (xmlHttp.readyState==complete_state)
		{ 
			var heads= document.getElementById("headId");
			
					var XMLDoc=xmlHttp.responseXML.documentElement;
				    var entries = XMLDoc.getElementsByTagName('heads-mapping');
					var val=0;
					var text='';
					 for ( var i = 0 ; i < entries.length ; i++ )
	     			 {
     				    val=entries[i].childNodes[0].text;    
     				    text = entries[i].childNodes[1].text; 
     				    var y = document.createElement('option')   
     			        y.value=val;
     			        y.text=text;	
     			        try
   				        {      				    					
                            heads.add(y,null);
           			    }
 				        catch(ex)
   				        {
   			 		       heads.add(y); 
   			   	        }	
	                
	                 }
  }
}


function checkAvailability(neworderHeadId,newpostId)
{

	var neworderHeadId=neworderHeadId.value;
	var newpostId = newpostId.value;
		
	
	
	if(neworderHeadId !="" &&  newpostId !="")
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
	}
		var url = "hrms.htm?actionFlag=checkOrderheadpostAvailability&newpostId=" +newpostId+",neworderHeadId="+neworderHeadId;  	
		
    xmlHttp.onreadystatechange = function() {	

		if (xmlHttp.readyState == 4) {     			
			if (xmlHttp.status == 200) {			
				var XMLDocForAjax=xmlHttp.responseXML.documentElement;
				var orderNameMapping = XMLDocForAjax.getElementsByTagName('orderNameMapping');	

				var flag="true";				
				if(orderNameMapping.length != 0) {		

						if(orderNameMapping[0].childNodes[3].text==flag)
						{			
							alert("Order Name is already Exists, Please Enter other Name");
							
							document.addOHPmpg.post.value='';
							document.addOHPmpg.post.focus();
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

var chkflag=1;
function chk()
{
	var order=document.addOHPmpg.order.value;
	var head=document.addOHPmpg.head.value;
	var post=document.addOHPmpg.post.value;
	
	if(order!=""&&head!=""&&post!="")
	{
	try
   	{   
   		xmlHttp=new XMLHttpRequest();    
   	}
	catch (e)
	{    // Internet Explorer    
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
		        return false;        
		    }
		}
	}
	var mpgId=0;
    var url = "hrms.htm?actionFlag=chkorderheadPostMpg&order="+order+"&post="+post+"&head="+head+"&mpgId="+mpgId;  
    xmlHttp.onreadystatechange = function()
	{
		
		if (xmlHttp.readyState == 4) 
		{     
			
			if (xmlHttp.status == 200) 
			{
				var XMLDocForAjax=xmlHttp.responseXML.documentElement;
				var mpgflag = XMLDocForAjax.getElementsByTagName('mpgflag');	
				
				if(mpgflag.length != 0)
				{
						if(mpgflag[0].childNodes[0].text!='null'&&mpgflag[0].childNodes[0].text=='true')
					{			
						alert("Mapping already Exists ");
						chkflag=0;
					}
					else
					chkflag=1;
				}
				
			}
		}
	}
	
	xmlHttp.open("POST", encodeURI(url) , false);    
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));	
	return true;
	}
	
	
}
	
function beforeSubmit()
{
	//alert('before submit called');
	var frmName = document.getElementsByTagName("form")[0];
	//alert(frmName);
	var listBox = frmName.post;
	//alert(listBox);
	var len = listBox.length;
	//alert('The size of list box is'+len);
	document.addOHPmpg.postLenVal.value=len;
	

    var v=document.getElementById("postId");
	for(i=0;i<v.options.length;i++)
	{
      v.options[i].selected = true;

	}	
	
	
	
		document.addOHPmpg.action="./hdiits.htm?actionFlag=multipleAddOrderHeadPostData";
		document.addOHPmpg.submit();

}	

function validateForm()
{	
	
	var frmName = document.getElementsByTagName("form")[0];

	var listBox = frmName.post;


	var len = listBox.length;


if(len==0){
alert('Please Select Post');
chkflag=0;
return;
}


	
	
	
	
	//alert('Function called');
	
    chk();
    if (chkflag==1)
	{
	//alert('inside if');
	return true;
	}
	else
	return false;
}

// Added By Urvin Shah.
// Function:- This method add selected items from Source Post List to Destination List and remove the Selected Items from the Source Post List.

function moveFromSrcToDest(){
	var destObj = document.getElementById('postId');
	var srcObj = document.getElementById('srcPostId');
	var srcPostLgth = document.getElementById('srcPostId').length;
	var destPostLgth = document.getElementById('postId').length;
	for(i=srcPostLgth-1;i>=0;i--) {
		if(srcObj.options[i].selected) {
			var optDest = document.createElement('option');
		    optDest.value=srcObj.options[i].value;
		    optDest.text=srcObj.options[i].text;
		    //alert('text and value is ' + srcObj.options[i].value + ' ' + srcObj.options[i].text);
		 
		    try {      				    					
                destObj.add(optDest,null);
                
           	}
 			catch(ex) {
   			 	 destObj.add(optDest); 
   			}
   			srcObj.options[i]=null;
		}	
	}
}

// Function:- This method add all items from Source Post List and add them in to Destination List and remove all items from the Source Post List.

function moveAllFromSrcToDest(){
	var destObj = document.getElementById('postId');
	var srcObj = document.getElementById('srcPostId');
	var srcPostLgth = document.getElementById('srcPostId').length;
	var destPostLgth = document.getElementById('postId').length;
	for(i=0;i<srcPostLgth;i++) {
		var optDest = document.createElement('option');
	    optDest.value=srcObj.options[i].value;
	    optDest.text=srcObj.options[i].text;
	    
	    try {      				    					
               destObj.add(optDest,null);
          	}
		catch(ex) {
 			destObj.add(optDest); 
 		}
	}
	for(i=srcPostLgth-1;i>=0;i--) {
		srcObj.options[i]=null;
	}
}

// Function:- This method remove selected items from Destination Post List and add to Source List and add to the Source Post List.

function moveFromDestToSrc(){
	var destObj = document.getElementById('postId');
	var srcObj = document.getElementById('srcPostId');
	var srcPostLgth = document.getElementById('srcPostId').length;
	var destPostLgth = document.getElementById('postId').length;
	for(i=destPostLgth-1;i>=0;i--) {
		if(destObj.options[i].selected) {
			var optSrc = document.createElement('option');
		    optSrc.value=destObj.options[i].value;
		    optSrc.text=destObj.options[i].text;
		    try {      				    					
                srcObj.add(optSrc,null);
           	}
 			catch(ex) {
   			 	 srcObj.add(optSrc); 
   			}
   			destObj.options[i]=null;
		}	
	}
}

// Function:- This method remove all items from Destination Post List and add them in to the Source Post List.

function moveAllFromDestToSrc(){
	var destObj = document.getElementById('postId');
	var srcObj = document.getElementById('srcPostId');
	var srcPostLgth = document.getElementById('srcPostId').length;
	var destPostLgth = document.getElementById('postId').length;
	for(i=0;i<destPostLgth;i++) {
		var optSrc = document.createElement('option');
	    optSrc.value=destObj.options[i].value;
	    optSrc.text=destObj.options[i].text;
	    try {      				    					
               srcObj.add(optSrc,null);
          	}
		catch(ex) {
 			srcObj.add(optSrc); 
 		}
	}
	for(i=destPostLgth-1;i>=0;i--) {
		destObj.options[i]=null;
	}
}


</script>
<body onload="init()">
<hdiits:form name="addOHPmpg" validate="true" method="POST"
  	action="javascript:beforeSubmit()" >

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b> <fmt:message key="OHP.insertOrderHeadPost" bundle="${commonLables}"/></b></a></li>
	</ul>
</div>
	
	
<div class="halftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0">
<br>
	<TABLE  align="center" width = "90%" border="0">  
	
	
	<TR>
		<TD  align="left" class="Label" width="15%">
		 <b><fmt:message key="PR.DEPT" bundle="${commonLables}"/></b></TD>
    	<TD	width ="35%"><hdiits:select style="width:90%"  name="cmbDept" size="1" sort="false" caption="Department" captionid="Dept" validation="sel.isrequired" mandatory="true" onchange="getOrderList()" >
	   <hdiits:option value="-1">-------------Select-------------</hdiits:option>
	  			<c:forEach items="${deptList}" var="deptList">
	    		   
			<c:choose>
				<c:when test="${deptList.locId==loginLocId}">
					<hdiits:option value="${deptList.locationCode}" selected="true" > ${deptList.locName} </hdiits:option>
			    </c:when>
				<c:otherwise>
				     <hdiits:option value="${deptList.locationCode}">${deptList.locName} </hdiits:option>
				</c:otherwise>
			</c:choose>
	    		</c:forEach>
	   	</hdiits:select>
	   </TD>
	
		<TD align="left" width = "15%">
		  <b><fmt:message key="OM.orderName" bundle="${commonLables}"/></b></TD>
		<TD align="left" width ="35%"><hdiits:select  style="width:90%"  mandatory="true" sort="false"  captionid="OM.orderName" bundle="${commonLables}" validation="sel.isrequired"  name="order" size ="1" onchange="Getheads()" >
				<hdiits:option value="">-------------Select-------------</hdiits:option>
								
				</hdiits:select>
			</TD>

	
	</TR>
	<tr></tr><tr></tr>
	
	
	<tr>
			
	<TD  align="left" class="Label" >
		<b>   <fmt:message key="OHP.headname" bundle="${commonLables}"/></b>
	</TD>
	
		<TD ><hdiits:select style="width:90%"  mandatory="true" captionid="OHP.headname"  sort="false"  bundle="${commonLables}" validation="sel.isrequired"  id="headId" name="head" size ="1" onchange="GetHeadStructure()" >
				<hdiits:option value="">-------------Select-------------</hdiits:option>
					<!--  <c:forEach var="headresultSet" items="${headresultSet}">
							<option value="${headresultSet.budsubhdId}" >
								${headresultSet.budsubhdDescLong}
							</option>	
				    	</c:forEach>	-->
				</hdiits:select>
			</TD>
	
	
	
		<TD align="left">
		  <b> <fmt:message key="eis.desig_name" bundle="${commonLables}"/></b>
		</TD>		
		
			<TD align="left"> <hdiits:select onchange="GetPostfromDesg()"  sort="false"   style="width:90%"  mandatory="true" captionid="eis.desig_name" bundle="${commonLables}" validation="sel.isrequired" id="dsgnId" name="desig" size ="1"   >
				<hdiits:option value="" selected="true">-------------Select-------------</hdiits:option>
						<c:forEach var="desigresultSet" items="${resultObj.resultValue.desigresultSet}">
							<option value='<c:out value="${desigresultSet.dsgnId}"/>' >
								<c:out value="${desigresultSet.dsgnName}"/>
				    	</c:forEach>		
				</hdiits:select>
			</TD>
</tr>
	
		<tr></tr><tr></tr>
	<TR>
		<TD>
		<b>   <fmt:message key="OHP.postname" bundle="${commonLables}" 	/></b>
		</TD>			
		
		<TD>
			<hdiits:select style="width:95%" captionid="OHP.postname" sort="false"   bundle="${commonLables}" id="srcPostId" name="srcPost"  multiple="true" size ="5">
					<%--<c:forEach var="ohpList"  items="${ohpList}"> 
					 	
						<option value="${ohpList.postId}" >
						${ohpList.empFName}  ${ohpList.empMName} ${ohpList.empLName}  ${ohpList.post}
							</option>

					</c:forEach>--%>
			</hdiits:select>

				
			</TD>

			<td align="center">
			<table >
			<tr>
				<td>
					<hdiits:button name="addFromSrvToDest" type="button" captionid="eis.addFromSrcToDest" bundle="${commonLables}" onclick="moveFromSrcToDest()"/>
				</td>
			</tr>
			<tr>
				<td>
					<hdiits:button name="addMultiFromSrcToDest" type="button" captionid="eis.addMultiFromSrcToDest" bundle="${commonLables}" onclick="moveAllFromSrcToDest()"/>
				</td>
			</tr>
			<tr>
				<td>
					<hdiits:button name="addFromDestToSrc" type="button" captionid="eis.addFromDestToSrc" bundle="${commonLables}" onclick="moveFromDestToSrc()"/>
				</td>
			</tr>
			<tr>
				<td>
					<hdiits:button name="addMultiFromDestToSrc" type="button" captionid="eis.addMultiFromDestToSrc" bundle="${commonLables}" onclick="moveAllFromDestToSrc()"/>
				</td>	
			</tr>
			</table>
			</td>
			
			<TD>

			<hdiits:select style="width:95%" captionid="OHP.postname" bundle="${commonLables}" id="postId" mandatory="true" name="post" multiple="true" size ="5">

					<%--<c:forEach var="ohpList"  items="${ohpList}"> 
					 	
						<option value="${ohpList.postId}" >
						${ohpList.empFName}  ${ohpList.empMName} ${ohpList.empLName}  ${ohpList.post}
							</option>

					</c:forEach>--%>
				</hdiits:select>
		  </TD>			
		</tr>
	
	<tr>
	
	<td><b>Head Structure&nbsp;&nbsp;&nbsp;&nbsp;</b></td>
	<td  colspan="3" > <hdiits:text name="headstructure" id="txtHeadSt"  size="100" /></td>
	</tr>
	
	<c:if test="${billList ne null}">
	<tr>
	<TD  align="left" class="Label"> <fmt:message key="PR.BILLNO" bundle="${commonLables}"/></TD>

	<td>
	<hdiits:select name="billNo" size="1" id="billNo" sort="false"  caption="BillNo" captionid="billNo"
		validation="sel.isrequired" mandatory="true">
	   <hdiits:option value="-1"> --Select-- </hdiits:option>
	    <c:forEach var="billList" items="${billList}">
         <hdiits:option value="${billList.billHeadId}"><c:out value="${billList.billId}"> </c:out></hdiits:option>
         </c:forEach>
	   </hdiits:select>
	  </td>
	  </tr>
	 </c:if>
	
	
	<hdiits:hidden name="postLenVal"></hdiits:hidden>
	<tr> </tr>
	</TABLE>
	</div> 
	 	<hdiits:jsField  name="validate" jsFunction="validateForm()" /> 
	
<fmt:setBundle basename="resources.eis.eisLables_en_US" var="Lables" scope="request"/>	
	 <hdiits:hidden default="getOrderHeadPostData" name="givenurl"/> 
<jsp:include page="../../core/PayTabnavigation.jsp" />
	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
	document.addOHPmpg.order.focus();
		if("${msg}"!='') {
			alert("${msg}");
			var url="./hrms.htm?actionFlag=getOrderHeadPostData";
			document.addOHPmpg.action=url;
			document.addOHPmpg.submit();
		}
			
		/*if("${msg}"!=null&&"${msg}"!='')
		{
			if("${result}"!=null&&"${result}"!=''&&"${result}"=="0")
			{
				alert("${msg}");
				var url="./hrms.htm?actionFlag=OrderHeadPostMaster";
				document.addOHPmpg.action=url;
				document.addOHPmpg.submit();
			}
			if("${result}"!=null&&"${result}"!=''&&"${result}"=="-1")
			{
				alert("${msg}");
				var url="./hrms.htm?actionFlag=GradeDesignationScaleMaster";
				document.addOHPmpg.action=url;
				document.addOHPmpg.submit();
			}
		}*/
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />

<script type="text/javascript" language="javascript">
/*		  v=document.getElementById("postId").length;
		  alert("Value of:-"+v);
		  for(i=0;i<v;i++)
		  {
			  lgth = document.getElementById("postId").options.length -1;
			  document.getElementById("postId").options[lgth] = null;
		  }	*/
</script>

</hdiits:form>
</body>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>


