<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<% 
try{
%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="../core/include.jsp" %>

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="dataList" value="${resValue.recordList}"> </c:set>
<c:set var="fieldList" value="${resValue.fieldCaptionList}"> </c:set>	
<c:set var="screenId" value="${resValue.scrid }"></c:set>
<c:set var="primaryKey" value="${resValue.primaryKey }"></c:set>
<c:set var="voClassName" value="${resValue.voClassName}"></c:set>
<c:set var="statusFlagVoPropery" value="${resValue.statusFlagVoPropery}"></c:set>

<script type="text/javascript">

//@author keyur patel(202428) 
function test(combinedString1)
{
	var combinedString = new String();
	var primaryKeyId = new String();
	var columnName = new String();
	var columnValue = new String();
	combinedString = combinedString1.toString();
	array=combinedString.split("&");
	array.pop();
	for(var i=0;i<array.length;i++)
	{
		itemarr = array[i].split("=");
		var item=document.getElementById(itemarr[0]);
		item.value=itemarr[1];
		arrayForEdit = array[i].split("=");
		columnName = arrayForEdit[0].toString();
		columnValue = arrayForEdit[1].toString();
		//alert("Column name ---> "+columnName);
		//alert("Column value --->"+columnValue);
		if(columnName == "pk")
		{
			primaryKeyId = columnValue;
		}
	}
	document.getElementById('editmode').value = "Y";
	document.getElementById('primaryKeyId').value= primaryKeyId;
	document.forms[0].action='hdiits.htm?actionFlag=insertviewScreen';
	window.document.forms[0].submit();
}

function deleteData()
{
	//alert('in delete data');
	var isChecked = false;

	for (var i = 0; i < document.forms[0].deletedata.length; i++) 
	{
   			if (document.forms[0].deletedata[i].checked) 
   			{
     			isChecked = true;
  			}
	}
	if(isChecked )
	{
		var answer=confirm("Are you sure want to delete the selected data?");
		if(answer)
		{
			document.forms[0].action='hdiits.htm?actionFlag=deleteData';
			document.forms[0].submit();
		}
		else
		{
		
		}
	}
	else
	{
		alert("Please select the checkbox");
	}
}

function addNewEntry()
{
	document.forms[0].action='hdiits.htm?screenId=${screenId}&actionFlag=insertviewScreen';
	document.forms[0].submit();
}

</script>

<%
	int i=1;
%>

<form action="hdiits.htm" method="post">

    <table>
		<tr align="right">
			<td align="right" width="900">
				<hdiits:button value="Delete" onclick="deleteData()" name="cmdDel1" type="button"/>
			</td>
		</tr>
	</table>

    <display:table  pagesize="10" name="${dataList}" id="row" requestURI="hdiits.htm?actionFlag=getAllData"  export="true" style="width:100%"
    				decorator="com.tcs.sgv.appgen.decorator.displayTagDecorator" 
    >

		<display:column class="tablecelltext" titleKey="SERIAL_NUMBER" headerClass="datatableheader"  >
			<%=i++ %>
		</display:column>

		<c:forEach var="fld" items="${fieldList}">
	 	   <display:column class="tablecelltext" property="${fld[1]}" title= "${fld[0]}" headerClass="datatableheader"/>
	    </c:forEach>

	    <display:column property="link2" class="tablecelltext" media="html" title="Actions"  headerClass="datatableheader">
	    </display:column>

		<display:setProperty name="export.pdf" value="true" />

    </display:table>

   	<table>
		<tr align="right">
			<td align="right" width="900">
				<hdiits:button name="addNewEntry_button" value="Add new entry" onclick="addNewEntry()" type="button"/>
				<hdiits:button value="Delete" onclick="deleteData()" name="cmdDel2" type="button"/>
			</td>
		</tr>
	</table>
 <div id="myDiv">
 </div>

 <c:forEach var="fld" items="${fieldList}">
 <input type="hidden" name="${fld[1]}" id="${fld[1]}"/> </c:forEach>
 <input type="hidden" name="primaryKey" id="primaryKey">
 <input type="hidden" name="pk" id="pk">
 <input type="hidden" name="actionFlag" value="insertviewScreen">
 <input type="hidden" name="screenId" value="${screenId}">
 <input type="hidden" name="editmode" value="N">
 <input type="hidden" name="primaryKeyId">
 <input type="hidden" name="voClassNameForScreen" value="${voClassName}">
 <input type="hidden" name="statusFlagVoPropery" value="${statusFlagVoPropery}">

 </form>
<%
}catch(Exception e){
	e.printStackTrace();
}
%>