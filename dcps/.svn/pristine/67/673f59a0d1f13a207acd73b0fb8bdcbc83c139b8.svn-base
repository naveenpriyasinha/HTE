<%@ include file="../core/include.jsp" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<%@ page contentType="text/html;charset=UTF-8"%>			
<%
try
{
%>
  <link rel="stylesheet" type="text/css" href="common/css/tabcontent.css"/>
  <script ="text/javascript" src="common/script/tabcontent.js">
</script>


<script type="text/javascript">
function validate_form(thisform)
{
	
	var total = document.forms[0].serviceid.length;
		var selected = false;
	if (!total)
	{
		if(document.forms[0].serviceid.checked==true)		
			selected = true;
	}	
	else
	{
		for (i=0;i<total;i++)
		{
			if(document.forms[0].serviceid[i].checked==true)
			{
				selected = true;
				break;
			}
		}
	}
	if (selected==false)
	{
		alert("Select it");
		return false;
	}
	var r=confirm("Are you sure? you want to delete this")
	if (r==true)
   	{
    	return true
    }
  	else
    {
    	return false
    }
}

//------------------11-4-2007 Changes----------------------
	function set_values(a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12)
	{
		document.frmcsearch.srvcid.value=a1;
		document.frmcsearch.srvclass.value=a2;
		document.frmcsearch.srvdesc.value=a3;
		document.frmcsearch.srvmethod.value=a4;
		document.frmcsearch.srvname.value=a5;
		document.frmcsearch.srvstatus.value=a6;
		document.frmcsearch.srvtype.value=a7;
		document.frmcsearch.crtdate.value=a8;
		document.frmcsearch.crtempid.value=a9;
		document.frmcsearch.updtdate.value=a10;
		document.frmcsearch.updtempid.value=a11;
		document.frmcsearch.editmode.value=a12;
		
		document.frmcsearch.action = "ifms.htm?viewName=addService";
		document.frmcsearch.submit();
		
	}
</script>

<form name="frmcsearch" method="post" action="./ifms.htm" onsubmit="return validate_form(this)" method="post">
<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resultValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="configurationList" value="${resultValue.configurationList}" > </c:set>

 <!--   <input type="button" value="delete" name="actionFlag" value="deleteConfiguration"> 			  -->	  
		<input type="submit" value="delete" class="DispButton"/>
		<input type="hidden" name="actionFlag" value="deleteConfiguration"/>							
<%
int i = 1;
%>	 	  
  <display:table list="${configurationList}" pagesize="12" requestURI="ifms.htm?actionFlag=viewConfiguration"
   id="row" excludedParams="ajax" varTotals="totals" export="true" partialList="" style="width:100%">
	<display:setProperty name="paging.banner.placement" value="bottom"  />    
	<display:column class="tablecelltext" titleKey="SERIAL_NUMBER" headerClass="datatableheader" defaultorder="ascending" sortName="external" ><%= i %> </display:column>  	
	<display:column class="tablecelltext" titleKey="DELETE" headerClass="datatableheader" ><input name="serviceid" value="${row.serviceId}"  index="<%=i%>" type="checkbox" > </display:column>	
   
        
    <display:column class="tablecelltext" titleKey="SERVICE_CLASS"
    sortable="true" headerClass="datatableheader" > 
    <!-- <a href="ifms.htm?viewName=addService&srvcid=${row.serviceId}&srvclass=${row.serviceClass}&srvdesc='${row.serviceDesc}'&srvmethod=${row.serviceMethod}&srvname=${row.serviceName}&srvstatus=${row.serviceStatus}&srvtype=${row.serviceType}&crtdate=${row.createdDate}&crtempid=${row.createdEmpId}&updtdate=${row.updatedDate}&updtempid=${row.updatedEmpId}&editmode=y" > ${row.serviceClass}</a>  -->
    <a onclick="set_values('${row.serviceId}','${row.serviceClass}','${row.serviceDesc}','${row.serviceMethod}','${row.serviceName}','${row.serviceStatus}','${row.serviceType}','${row.createdDate}','${row.createdEmpId}','${row.updatedDate}','${row.updatedEmpId}','y')" href="#"> ${row.serviceClass}</a>
     

    </display:column>
    
    
    
    <display:column property="serviceName" class="tablecelltext" titleKey="SERVICE_NAME" sortable="true" headerClass="datatableheader"  />
    <display:column property="serviceMethod" class="tablecelltext" titleKey="SERVICE_METHOD" sortable="true" headerClass="datatableheader" />
    <% i++; %>
    <display:footer media="html">
  </display:footer>
  </display:table>  
	 <input type="hidden" name="srvcid">
     <input type="hidden" name="srvclass">    
     <input type="hidden" name="srvdesc">            
     <input type="hidden" name="srvmethod">
     <input type="hidden" name="srvname">
     <input type="hidden" name="srvstatus">
     <input type="hidden" name="srvtype">
     <input type="hidden" name="crtdate">
     <input type="hidden" name="crtempid">
     <input type="hidden" name="updtdate">
     <input type="hidden" name="updtempid">
     <input type="hidden" name="editmode">
        </form>
<%
}catch(Exception e){
	e.printStackTrace();
}
%>      