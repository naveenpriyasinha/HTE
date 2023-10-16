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
  <c:set  var="graderesultSet" value='${resultObj.resultValue.graderesultSet}' />
  <c:set  var="desigresultSet" value='${resultObj.resultValue.desigresultSet}' /> 
  <c:set  var="scaleresultSet" value='${resultObj.resultValue.scaleresultSet}' /> 
  <c:set var="result" value="${resultObj.resultValue.result}" ></c:set>
  <c:set var="msg" value="${resultObj.resultValue.msg}" ></c:set>

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




function GetDesignations()
{
	var v=document.getElementById("desig").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("desig").options.length -1;
			document.getElementById("desig").options[lgth] = null;
	}		
		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url=''; 
		  var uri='';
		  url= uri+'&gradeID='+ document.SGDMPG.grade.value;
		  var actionf="GetDesignations";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
		  xmlHttp.onreadystatechange=gradechangeddesigs;
		  xmlHttp.open("POST",encodeURI(url),true);
		  xmlHttp.send(null);		  	
}

function gradechangeddesigs()
	{	
		if (xmlHttp.readyState==complete_state)
		{ 
			var Designations = document.getElementById("desig");
			
					var XMLDoc=xmlHttp.responseXML.documentElement;
				    var entries = XMLDoc.getElementsByTagName('Designations-mapping');
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
                            Designations.add(y,null);
           			    }
 				        catch(ex)
   				        {
   			 		       Designations.add(y); 
   			   	        }	
	                
	                 }
  }
}

</script>
<body onload="init()">
<hdiits:form name="SGDMPG" validate="true" method="POST"
	action="./hdiits.htm?actionFlag=AddGradeDesignationScalempg" >

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b> <fmt:message key="eis.insertgradeDesgScaleMaster" bundle="${commonLables}"/></b></a></li>
	</ul>
</div>
	
	
<div class="halftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0">
<br>
	<TABLE  align="center" width = "60%">  
	<TR>
		<TD width = "25%">
		 <b> <fmt:message key="eis.grade_name" bundle="${commonLables}"/></b>
		</TD>			
			<TD width = "60%"><hdiits:select  mandatory="true" style="width:50%" sort="false"  captionid="eis.grade_name" bundle="${commonLables}" validation="sel.isrequired" onchange="GetDesignations()" name="grade" size ="1"  >
				<hdiits:option value="">--------------------Select----------------</hdiits:option>
						<c:forEach var="graderesultSet" items="${resultObj.resultValue.graderesultSet}">
							<option value='<c:out value="${graderesultSet.gradeId}"/>' >
								<c:out value="${graderesultSet.gradeName}"/>
				    	</c:forEach>		
				</hdiits:select>
			</TD>
	
	
	</tr>
	<TR>
		<TD width = "25%">
		<b>   <fmt:message key="eis.desig_name" bundle="${commonLables}"/></b>
		</TD>			
			<TD width = "40%"><hdiits:select style="width:50%"  mandatory="true" sort="false" captionid="eis.desig_name" bundle="${commonLables}" validation="sel.isrequired"  id="desig" name="gdMapId" size ="1"   >
  				<hdiits:option value="">--------------------Select----------------</hdiits:option>
						<%--<c:forEach var="desigresultSet" items="${resultObj.resultValue.desigresultSet}">
							<option value='<c:out value="${desigresultSet.desigId}"/>' >
								<c:out value="${desigresultSet.desigName}"/>
				    	</c:forEach>--%>		
				</hdiits:select>
			</TD>
	</tr>
	<TR>
		<TD width = "25%">
		   
		   <b><fmt:message key="OT.empScale" bundle="${commonLables}"/></b>
		</TD>			
			<TD width = "60%"><hdiits:select style="width:50%" mandatory="true" sort="false"  captionid="OT.empScale" bundle="${commonLables}"  validation="sel.isrequired" name="scale" size ="1"   >
				<hdiits:option value="select">---------------------Select-----------------</hdiits:option>
						<c:forEach var="scaleresultSet" items="${scaleresultSet}">
						<c:choose>
						  <c:when test="${scaleresultSet.scaleHigherIncrAmt ne 0}">
						  		<c:set var="scale" value="${scaleresultSet.scaleStartAmt}-${scaleresultSet.scaleIncrAmt}-${scaleresultSet.scaleEndAmt}-${scaleresultSet.scaleHigherIncrAmt}-${scaleresultSet.scaleHigherEndAmt}"/>
						  </c:when>
						  <c:otherwise>
						  		<c:set var="scale" value="${scaleresultSet.scaleStartAmt}-${scaleresultSet.scaleIncrAmt}-${scaleresultSet.scaleEndAmt}(  ${scaleresultSet.scaleGradePay}  )"/>
						  </c:otherwise>
			 			</c:choose>
						
							<option value='<c:out value="${scaleresultSet.scaleId}"/>' >
								<c:out value="${scale}"/>
				    	</c:forEach>		
				</hdiits:select>
			</TD>
	</tr>
	<tr> </tr>
	</TABLE>
	</div> 
	<hdiits:hidden default="getGradDesgScaleMap" name="givenurl"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="Lables" scope="page"/>	<jsp:include page="../../core/PayTabnavigation.jsp" />
	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		if("${msg}"!=null&&"${msg}"!='')
		{
			if("${result}"!=null&&"${result}"!=''&&"${result}"=="0")
			{
				alert("${msg}");
					
				var url="./hrms.htm?actionFlag=getGradDesgScaleMap";
				document.SGDMPG.action=url;
				document.SGDMPG.submit();
			}
			if("${result}"!=null&&"${result}"!=''&&"${result}"=="-1")
			{
				alert("${msg}");
				
				//}			
				var message = "Mapping Already Exists";
				if("${msg}" == message){
					var url="./hrms.htm?actionFlag=getGradDesgScaleMap";
					document.SGDMPG.action=url;
					document.SGDMPG.submit();
					//document.getElementById("scale").options[0];
				}
				else {				
					var url="./hrms.htm?actionFlag=getGradDesgScaleMap";
					document.SGDMPG.action=url;
					document.SGDMPG.submit();
				}
			}
		}
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />

<script type="text/javascript" language="javascript">
		document.forms[0].elements['grade'].focus();
		

</script>

</hdiits:form>
</body>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>


