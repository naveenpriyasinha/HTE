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
<c:set var="resvalue" value="${resultObj.resultValue}" > </c:set>
 <c:set  var="orderresultSet" value='${resultObj.resultValue.orderresultSet}' />
  <c:set  var="headresultSet" value='${resultObj.resultValue.headresultSet}' /> 
  <c:set  var="userpostrltSet" value="${resultObj.resultValue.userpostrltSet}" /> 
  <c:set var="ohpList" value="${resultObj.resultValue.ohpList }"/>
  <c:set var="result" value="${resultObj.resultValue.result}" ></c:set>
  <c:set var="msg" value="${resultObj.resultValue.msg}" ></c:set> 
  <c:set var="subHeadList" value="${resvalue.subHeadList}" ></c:set>
  <c:set var="postList" value="${resvalue.postList}" ></c:set>
   <c:set var="orderId" value="${resvalue.orderId}" ></c:set>
    <c:set var="OhpMapId" value="${resvalue.OhpMapId}" ></c:set>
   <c:set var="subHeadId" value="${resvalue.subHeadId}" ></c:set>
   <c:set var="PostId" value="${resvalue.PostId}" ></c:set> 
      <c:set var="desigId" value="${resvalue.desigId}" ></c:set> 
   <c:set var="empList" value="${resvalue.empList}" ></c:set>
<!--  <c:set var="temp" value="${resvalue.temp}" ></c:set>-->
     <c:set var="HeadList" value="${resultObj.resultValue.HeadList}"></c:set>
      <c:set var="headsId" value="${resvalue.headsId}" ></c:set>
       <c:set var="headsId" value="${resvalue.headsId}" ></c:set>
  <c:set  var="desigresultSet" value='${resultObj.resultValue.desigresultSet}' /> 
    <c:set var="orgEmpList" value="${resultObj.resultValue.orgEmpList}" ></c:set>
        <c:set var="billList" value="${resultObj.resultValue.billList}" ></c:set>
                <c:set var="billNo" value="${resultObj.resultValue.billNo}" ></c:set>
   
   
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


function GetHeadStructure()
{

var k = document.getElementById("headId").value;
if(k!=null && k !=-1){

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
			            document.editOHPmpg.headstructure.value= demandCode + '-' + budmjrhd_code + '-' + budsubmjrhd_code + '-' + budminhd_code + '-' + subHeadName ;
     			       
	                 }
	                
  }
  
}



function GetPostfromDesg()
{
		  var v=document.getElementById("postId").length;
		  for(i=1;i<v;i++)
		  {
			  lgth = document.getElementById("postId").options.length -1;
			  document.getElementById("postId").options[lgth] = null;
		  }		
		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url=''; 
		  var uri='';
		  url= uri+'&dsgnId='+ document.editOHPmpg.desig.value;
		  var actionf="GetPostfromDesg";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url+"&editFlag=1"; 
		  xmlHttp.onreadystatechange=GetPostsfromDesg;
		  xmlHttp.open("POST",encodeURI(url),true);
		  xmlHttp.send(null);		  	

}

function GetPostsfromDesg()
	{	
		if (xmlHttp.readyState==complete_state)
		{ 
			var post = document.getElementById("postId");
			
					var XMLDoc=xmlHttp.responseXML.documentElement;
				    var entries = XMLDoc.getElementsByTagName('post-mapping');
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
		  url= uri+'&orderId='+ document.editOHPmpg.order.value;
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
var flag=1;
function chk()
{
	var order=document.editOHPmpg.order.value;
	var head=document.editOHPmpg.head.value;
	var post=document.editOHPmpg.post.value;
	
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
	var mpgId=${OhpMapId};
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
						flag=0;
					}
					else
					flag=1;
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
	document.editOHPmpg.action="./hrms.htm?actionFlag=addOrderHeadPostMpg&updateflag=Y&orderHeadPostId=${OhpMapId}"
	document.editOHPmpg.submit();
}

function validateForm()
{	
    chk();
    if (eval(flag)==1)
	{
	return true;
	}
	else
	return false;
}


</script>

<body onload="init()">
<hdiits:form name="editOHPmpg" validate="true" method="POST"
action="javascript:beforeSubmit()"
	 encType="text/form-data">
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b> <fmt:message key="OHP.updateOrderHeadPost" bundle="${commonLables}"/></b></a></li>
	</ul>
</div>
	
	
<div class="halftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0">

	<TABLE  align="center" width = "60%">  
	<TR>
		<TD>
		   
		  <b><fmt:message key="OM.orderName" bundle="${commonLables}"/></b>
		</TD>			
			<TD><hdiits:select  mandatory="true" name="order"   captionid="OHP.ordername"  bundle="${commonLables}" validation="sel.isrequired" size ="1" sort="false"  onchange="Getheads()" >
				<hdiits:option value="">-------------Select-------------</hdiits:option>
				<c:forEach var="orderresultSet" items="${orderresultSet}">
					<fmt:formatDate value="${orderresultSet.orderDate}" pattern="dd/MM/yyyy" var="startDate"/>
							 <c:choose>
									<c:when test="${orderresultSet.orderId eq orderId}">
										<option value='<c:out value="${orderresultSet.orderId}"/>' selected="true">
										<c:out value="${orderresultSet.orderName}(Dt.:- ${startDate})"/></option>
									</c:when>
									<c:otherwise>
										<option value='<c:out value="${orderresultSet.orderId}"/>'>
										<c:out value="${orderresultSet.orderName} (Dt.:- ${startDate})"/></option>
									</c:otherwise> 
								</c:choose>					    	
							</c:forEach>						
					</hdiits:select>
			</TD>
	
	
	</tr>
	<TR>
		<TD>
		  <b><fmt:message key="OHP.headname" bundle="${commonLables}"/></b>
		</TD>			
		<TD><hdiits:select  mandatory="true" style="width:50%" captionid="OHP.headname" validation="sel.isrequired"   bundle="${commonLables}"  id="headId" name="head" size ="1" sort="false" onchange="GetHeadStructure()">
				<hdiits:option value="-1">-------------Select-------------</hdiits:option>	
					<c:forEach var="HeadList" items="${HeadList}">
							 <c:choose>
									<c:when test='${HeadList.element_code eq subHeadId}' >
										<option value='${HeadList.sgvaBudsubhdMst.budsubhdId}' selected="true" >
										<c:out value="${HeadList.sgvaBudsubhdMst.budsubhdDescLong}" /></option>
									</c:when>
									<c:otherwise>
										<option value='${HeadList.sgvaBudsubhdMst.budsubhdId}'>
										<c:out value='${HeadList.sgvaBudsubhdMst.budsubhdDescLong}' /></option>
									</c:otherwise> 
								</c:choose>					    	
							</c:forEach>						
				
			</hdiits:select>
			</TD>
			
	</tr>
	
	<TR>
		<TD>
		  <b> <fmt:message key="eis.desig_name" bundle="${commonLables}"/></b>
		</TD>			
			<TD><hdiits:select onchange="GetPostfromDesg()"  style="width:50%"  sort="false" captionid="eis.desig_name" bundle="${commonLables}"  id="dsgnId" name="desig" size ="1"   >
				<hdiits:option value="" selected="true">-------------Select-------------</hdiits:option>
						<c:forEach var="desigresultSet" items="${resultObj.resultValue.desigresultSet}">
							<c:choose>
									<c:when test="${desigresultSet.dsgnId eq desigId}">
										<option value='<c:out value="${desigresultSet.dsgnId}"/>' selected="true">
										<c:out value=" ${desigresultSet.dsgnName}"/></option>
									</c:when>
									<c:otherwise>
										<option value='<c:out value="${desigresultSet.dsgnId}"/>'>
										<c:out value="${desigresultSet.dsgnName}"/></option>
									</c:otherwise> 
						</c:choose>			
							
							
				    	</c:forEach>		
				</hdiits:select>
			</TD>
	</tr>
	
	<TR>

		<TD width = "35%">
		<b>   <fmt:message key="OHP.postname" bundle="${commonLables}"/></b>
		</TD>			
			<TD width = "65%"><hdiits:select style="width:80%"  mandatory="true" sort="false" captionid="OHP.postname" bundle="${commonLables}" validation="sel.isrequired"  id="postId" name="post" size ="1"   >
					 <hdiits:option value="">-------------Select-------------</hdiits:option>
					 	<c:forEach var="ohpList"  items="${ohpList}">					 	
						<c:choose>
									<c:when test="${ohpList.postId eq PostId}">
										<option value='<c:out value="${ohpList.postId}"/>' selected="true">
										<c:out value=" ${ohpList.post}"/></option>
									</c:when>
									<c:otherwise>
										<option value='<c:out value="${ohpList.postId}"/>'>
										<c:out value="${ohpList.post}"/></option>
									</c:otherwise> 
						</c:choose>					    	
					 	</c:forEach>
				</hdiits:select>
			</TD>


				</tr>
				<tr>
				<td width ="20%"><b>Head Structure&nbsp;&nbsp;&nbsp;&nbsp;</b></td>
	<td > <hdiits:text name="headstructure" id="txtHeadSt"  size="46" readonly="true"/></td>
	</tr>
	
	<c:if test="${billList ne null}">
	<tr>
	<TD  align="left" class="Label"> <b> <fmt:message key="PR.BILLNO" bundle="${commonLables}"/> </b> </TD>

	<td>
	<hdiits:select name="billNo" size="1" id="billNo" sort="false"  caption="BillNo" captionid="billNo"
		validation="sel.isrequired" mandatory="true">
	   <hdiits:option value="-1"> --Select-- </hdiits:option>
	    <c:forEach var="billList" items="${billList}">
	     <c:choose>
	      <c:when test="${billNo eq  billList.billHeadId}">
	      <hdiits:option value="${billList.billHeadId}" selected="true"><c:out value="${billList.billId}"> </c:out></hdiits:option>
	      </c:when>
	      <c:otherwise>
         <hdiits:option value="${billList.billHeadId}"><c:out value="${billList.billId}"> </c:out></hdiits:option>
         </c:otherwise>
         </c:choose>
         </c:forEach>
	   </hdiits:select>
	  </td>
	  </tr>
	 </c:if>
	 
	</TABLE>
	</div> 
		<hdiits:hidden default="getOrderHeadPostData" name="givenurl"/>
		 	<hdiits:jsField  name="validate" jsFunction="validateForm()" /> 	
<fmt:setBundle basename="resources.payroll.payrollLables_en_US" var="Lables" scope="request"/>
	<jsp:include page="../../core/PayTabnavigation.jsp" />
	</div>
	<script type="text/javascript">
		initializetabcontent("maintab")
		
			  
		//Getheads();
			
		if("${msg}"!='') {
			alert("${msg}");
			var url="./hrms.htm?actionFlag=getOrderHeadPostData";
			document.editOHPmpg.action=url;
			document.editOHPmpg.submit();
		}
		else
		{
			GetHeadStructure();
			document.editOHPmpg.order.focus();
		}
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />



</hdiits:form>
</body>
<%
	} 
  catch (Exception e) 
    {
		e.printStackTrace();
	}
%>


										