<%
try{
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>

<fmt:setBundle basename="resources.hr.payfixation.PayRelated" var="PayLab" scope="request"/>


<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="newlist" value="${resultValue.newlist}" > </c:set>

<html>
<script type="text/javascript">
function checkresp()
{

var flag=false;
var chkBx=document.forms[0].check;
if(chkBx.checked==true)
{
flag=true;

}



for(var i=0;i<chkBx.length;i++)

{

if(chkBx[i].checked==true)
{
flag=true;

}

}
if(!flag){
alert('<fmt:message bundle="${PayLab}" key="Pf.selectbox"/>');
}
if(flag)
{
document.frmVac.action ="hrms.htm?actionFlag=byincr";
document.frmVac.submit();
}

}
function checkMulti(j){

var chkBx=document.forms[0].check;
for(var i=0;i<chkBx.length;i++)
{
if(chkBx[i].checked=true)
{
chkBx[i].checked=false;
}
 if(i==j)
{
chkBx[i].checked=true;
}
}
}
function Closebt()
{	
	method="POST";
	document.frmVac.action="./hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
	
	document.frmVac.submit();
}
</script>
    <hdiits:form method="POST" name="frmVac" action="./hrms.htm?" validate="true" encType="text/form-data">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="Pf.PayFixation" bundle="${PayLab}" captionLang="single"></hdiits:caption></b></a></li>
		</ul>
	</div>
<div class="tabcontentstyle">	  
	<hdiits:hidden name="actionFlag" default="byincr"/>
	<div id="tcontent1" class="tabcontent">   
	<hdiits:fieldGroup titleCaptionId="Pf.det" bundle="${PayLab}" mandatory="true" > 
					
		<c:set var="i" value="0"/>
			<%int a=0; %>	
		  <display:table name="${newlist}" id="row" export="false" style="width:100%" requestURI="" defaultsort="2"  defaultorder="ascending" >
           <display:column class="tablecelltext" title="" headerClass="datatableheader" style="text-align: center" ><hdiits:checkbox name="check" value="${row.payfixid}" onclick="checkMulti('${i}')" /></display:column>
           <display:column class="tablecelltext"  titleKey="Pf.Pay.SerialNo"  headerClass="datatableheader" value="<%=a=a+1 %>" style="text-align: center" >    </display:column>
		  	 <display:column class="tablecelltext" titleKey="Pf.name" headerClass="datatableheader" style="text-align: center" >
		  	 <a>${row.empPrefix} ${row.empFName} ${row.empMName} ${row.empLName} </a>
		  	 </display:column>	
			  <display:column class="tablecelltext"  titleKey="Pf.desig"  headerClass="datatableheader" value="${row.designation}"  style="text-align: center" > </display:column>	
			 <display:column class="tablecelltext"  titleKey="Pf.presentscale"  headerClass="datatableheader" value="${row.presentpayscale}"   style="text-align: center">  </display:column>
			 <display:column class="tablecelltext"  titleKey="Pf.scale"  headerClass="datatableheader" value="${row.revisedpayscale}"   style="text-align: center">  </display:column>
		  	<display:column class="tablecelltext"  titleKey="Pf.Pay.reason"  headerClass="datatableheader" value="${row.reasonPayfixed}" style="text-align: center"  >  </display:column>
			
		  	<display:column class="tablecelltext" titleKey="Pf.Pay.date"  headerClass="datatableheader" value="${row.dateofPayfixation}" style="text-align: center" > </display:column>
		  	<c:set var="i" value="${i+1}"/>
  		  </display:table>
  		 <table>
  		 <tr>
  		   <td>
		
		</td>
	</tr>
  		 
		</table>
		</hdiits:fieldGroup>
		<br><br><br><br>
		<table align="center" id="SubBtns" style="display:none">
			<hdiits:button name="Submit" type="button" captionid="Pay.Submit" onclick="checkresp()" bundle="${PayLab}"/>
			
	
	<hdiits:button type="button" captionid="Pay.Close" name="Close" value="Close" onclick="Closebt()" bundle="${PayLab}"/>
			</table>
			</div>
			
			
				</div>
	<c:if test="${not empty newlist}">
	<script type="text/javascript">
document.getElementById('SubBtns').style.display='';
	
		</script>
		</c:if>
	<script type="text/javascript">

		initializetabcontent("maintab")
		</script>
	<hdiits:validate controlNames="tesxt" locale='<%=(String)session.getAttribute("locale")%>' />

</hdiits:form>
</html>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>