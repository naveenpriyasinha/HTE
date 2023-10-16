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
<c:set  var="graderesultSet" value='${resvalue.graderesultSet}' />
<c:set  var="desigresultSet" value='${resvalue.desigresultSet}' /> 
<c:set  var="graddesgscaleresultSet" value='${resvalue.graddesgscaleresultSet}' /> 
<c:set  var="scaleresultSet" value='${resultObj.resultValue.scaleresultSet}' />

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
					var index=0;
					var flg=true;
					 for ( var i = 0 ; i < entries.length ; i++ )
	     			 {
     				    val=entries[i].childNodes[0].text;    
     				    text = entries[i].childNodes[1].text; 
     				    var y = document.createElement('option')   
     			        y.value=val;
     			        y.text=text;
      			        if(${graddesgscaleresultSet.hrEisGdMpg.gdMapId}==y.value)
      			        {
      			        index=i;
      			        flg=false;
      			        }
      			        try
   				        {      				    					
                            Designations.add(y,null);
           			    }
 				        catch(ex)
   				        {
   			 		       Designations.add(y); 
   			   	        }	
	                 document.SGDMPG.desig.selectedIndex = index+1;
	                 if(flg)
	                 document.SGDMPG.desig.selectedIndex = 0;
	                 }
  }
}

</script>
<body onload="init()">
<hdiits:form name="SGDMPG" validate="true" method="POST"
	action="./hdiits.htm?actionFlag=AddGradeDesignationScalempg&updateflag=true&SgdMapId=${graddesgscaleresultSet.sgdMapId}"  encType="multipart/form-data">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b> <hdiits:caption captionid="eis.insertgradeDesgScaleMaster" bundle="${commonLables}"/></b></a></li>
	</ul>
</div>
	
	
<div class="halftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0">

	<TABLE  align="center" width = "60%">  
	<TR>
		<TD>
		  <b><hdiits:caption captionid="eis.grade_name" bundle="${commonLables}"/>:</b>
		</TD>			
			<TD><hdiits:select  mandatory="true" name="grade" sort="false"  captionid="eis.grade_name" onchange="GetDesignations()" bundle="${commonLables}" validation="sel.isrequired" size ="1" sort="false"  >
				<hdiits:option value="">-------------Select-------------</hdiits:option>
						<c:forEach var="graderesultSet" items="${graderesultSet}">
								<c:choose>
									<c:when test="${graddesgscaleresultSet.hrEisGdMpg.orgGradeMst.gradeId==graderesultSet.gradeId}">
										<option value='<c:out value="${graderesultSet.gradeId}"/>' selected="true">
										<c:out value="${graderesultSet.gradeName}"/></option>
									</c:when>
									<c:otherwise>
										<option value='<c:out value="${graderesultSet.gradeId}"/>'>
										<c:out value="${graderesultSet.gradeName}"/></option>
									</c:otherwise>
								</c:choose>								
				    	</c:forEach>		
				</hdiits:select>
			</TD>
	
	
	</tr>
	<TR>
		<TD>
		  <b><hdiits:caption captionid="eis.desig_name" bundle="${commonLables}"/>:</b>
		</TD>			
			
			<TD><hdiits:select  mandatory="true" captionid="eis.desig_name" sort="false"  bundle="${commonLables}" validation="sel.isrequired" id="desig" name="gdMapId" size ="1" sort="false"   >
				<hdiits:option value="">-------------Select-------------</hdiits:option>
				</hdiits:select>
			</TD>
			
	</tr>
	<TR>


		<TD>
		   <b><hdiits:caption captionid="OT.empScale" bundle="${commonLables}"/>:</b>
		</TD>			
			
			<TD><hdiits:select  mandatory="true"  captionid="OT.empScale" sort="false" bundle="${commonLables}"  validation="sel.isrequired"  name="scale" size ="1" sort="false" >
				<hdiits:option value="">-------------Select-------------</hdiits:option>
						<c:forEach var="scaleresultSet" items="${scaleresultSet}">
								<c:choose>
									<c:when test="${graddesgscaleresultSet.hrEisScaleMst.scaleId==scaleresultSet.scaleId}">
										<option value='<c:out value="${scaleresultSet.scaleId}"/>' selected="true">
										<c:out value="${scaleresultSet.scaleStartAmt}-${scaleresultSet.scaleIncrAmt}-${scaleresultSet.scaleEndAmt}"/></option>
									</c:when>
									<c:otherwise>
										<option value='<c:out value="${scaleresultSet.scaleId}"/>'>
										<c:out value="${scaleresultSet.scaleStartAmt}-${scaleresultSet.scaleIncrAmt}-${scaleresultSet.scaleEndAmt}"/></option>
									</c:otherwise>
								</c:choose>								
				    	</c:forEach>		
				</hdiits:select>
			</TD>
				</tr>
	</TABLE>
	</div> 

	<jsp:include page="../../core/PayTabnavigation.jsp" />
	</div>
	<script type="text/javascript">
		initializetabcontent("maintab")
		GetDesignations()
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


										