
<%try { %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<%@ page contentType="text/html;charset=UTF-8"%>
<fmt:requestEncoding value="UTF-8" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}" />
<c:set var="empList" value="${resValue.empList}" />
<c:set var="empListSize" value="${resValue.empListSize}" />
<c:set var="lstDistrict" value="${resValue.lstDistrict}" />
<c:set var="lstCityClass" value="${resValue.lstCityClass}" />
<c:set var="msg" value="${resValue.msg}" />


<c:set var="Taluka" value="${resValue.Taluka}" />
<c:set var="Taluka1" value="${resValue.lstTaluka}" />
	<c:set var="hdnCounter" value="0"/>

<style>/* Added by pratik 08-08-23 */
.scrollablediv {
    width: 100% !important;
}
</style>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="DCPSLables"
	scope="request" />
<hdiits:form name="frmCityClassUpdation" encType="multipart/form-data"
	validate="true" method="post">
	<fieldset class="tabstyle"><legend> <b><fmt:message
		key="CMN.CITYCLASSUPDATION" bundle="${DCPSLables}"></fmt:message></b> </legend> <c:set
		var="hdnCounter" value="0" />

	<div class="scrollablediv">
		<% int srno=1; %>
	<display:table list="${empList}"
		style="text-align:center;width:100%" id="empList" requestURI=""
		export="" pagesize="50">
		
		<display:setProperty name="paging.banner.placement" value="bottom" />

		<display:column titleKey="CMN.CHKBXEMPSELECT"
			title="<input name='chkSelect'  type='checkbox' onclick='checkUncheckAll(this)'/>"
			headerClass="datatableheader" style="text-align:center;width:5%"
			class="oddcentre" sortable="true">
			<input type="checkbox" align="left" name="checkbox${empList_rowNum}"
				id="checkbox${empList_rowNum}"
				onclick="toggleDropCityClass(${empList_rowNum})" />
				
				
		</display:column>
				

		<display:column headerClass="datatableheader"
			style="text-align:left;width:15%" class="oddcentre" sortable="true"
			titleKey="CMN.SEVARTHID">
			<c:out value="${empList[0]}"></c:out>
			<input type="hidden" id="hdnSevarthID${empList_rowNum}"
				name="hdnSevarthID${empList_rowNum}" value="${empList[0]}">
			<input type="hidden" id="valueI" name="valueI" value="<%=srno %>">
		</display:column>

		<display:column headerClass="datatableheader"
			style="text-align:left;width:25%" class="oddcentre" sortable="true"
			titleKey="CMN.EMPNAME">
			<c:out value="${empList[1]}"></c:out>
		</display:column>

		<display:column headerClass="datatableheader"
			style="text-align:left;width:15%" class="oddcentre" sortable="true"
			titleKey="CMN.CITYCLASSNAME">
			<c:out value="${empList[2]}"></c:out>
		</display:column>

		<display:column titleKey="CMN.DIDSTRICT" headerClass="datatableheader"
			style="text-align:center;width:15%" class="oddcentre" sortable="true">
			<select disabled="disabled" id="dictrictName${empList_rowNum}"
				name="dictrictName${empList_rowNum}"
				onchange="getTalukaList('${empList_rowNum}');">
			
				<c:forEach var="districtList" items="${resValue.lstDistrict}">
				<c:choose>
         				<c:when test="${empList!=null}">
         						<c:choose>
         							<c:when test="${districtList.id==empList[7]}">
         								<option value="${districtList.id}" selected="selected">${districtList.desc}</option>
         							</c:when>
         							<c:otherwise>
         								<option value="${districtList.id}" title="${districtList.desc}">${districtList.desc}</option>
         							</c:otherwise>
         							</c:choose>
         						</c:when>
         					
         						<c:otherwise>
         								<option value="${districtList.id}" title="${districtList.desc}">${districtList.desc}</option>
         						</c:otherwise>
         					</c:choose>	
						
					</c:forEach>
				
				
				
				
				
			</select>

		</display:column>

		<display:column titleKey="CMN.TALUKA" headerClass="datatableheader"
			style="text-align:center;width:15%" class="oddcentre" sortable="true">

			<select disabled="disabled" id="talukaName${empList_rowNum}"
				name="talukaName${empList_rowNum}">
			<c:forEach var="Taluka1" items="${resValue.lstTaluka}">
				<c:choose>
         				<c:when test="${empList!=null}">
         						<c:choose>
         							<c:when test="${Taluka1.id==empList[8]}">
         								<option value="${Taluka1.id}" selected="selected">${Taluka1.desc}</option>
         							</c:when>
         							<c:otherwise>
         								<option value="${Taluka1.id}" title="${Taluka1.desc}">${Taluka1.desc}</option>
         							</c:otherwise>
         							</c:choose>
         						</c:when>
         					
         						<c:otherwise>
         								<option value="${Taluka1.id}" title="${Taluka1.desc}">${Taluka1.desc}</option>
         						</c:otherwise>
         					</c:choose>	
						
					</c:forEach>
					
					
					
				</select>

		</display:column>

		<display:column titleKey="CMN.CITYCLASSNAME"
			headerClass="datatableheader" style="text-align:center;width:20%"
			class="oddcentre" sortable="true">
			<select disabled="disabled" id="cityClassName${empList_rowNum}"
				name="cityClassName${empList_rowNum}">
				<c:forEach var="cityClassList" items="${resValue.lstCityClass}">
					<option value="${cityClassList.id}">${cityClassList.desc}</option>
				</c:forEach>
			</select>

		</display:column>
		<c:set var="hdnCounter" value="${hdnCounter+1}" />
	<%srno=srno+1; %>
		
	</display:table></div>
	</fieldset>

	<input type="hidden" id="hdnCounter" name="hdnCounter"
		value="${hdnCounter}" />
	<input type="hidden" id="empListSize" name="empListSize"
		value="${empListSize}" />

	<div align="center"><br>
	<hdiits:button name="BTN.UpdateBasic" id="btnUpdate" type="button"
		captionid="BTN.UPDATEBANKBRANCH" bundle="${DCPSLables}"
		onclick="updateCityClass();" />&nbsp; <hdiits:button
		name="BTN.ResetBasic" id="btnReset" type="button"
		captionid="NGR.RESET" bundle="${DCPSLables}" onclick="resetAll();" />
	</div>

</hdiits:form>

<script>

var msessgae='${resValue.msg}';
if(msessgae !=null && msessgae !=""){

	alert(msessgae);
}

function checkUncheckAll(theElement) 
{
	var theForm = theElement.form;
	var totalEmp = document.getElementById("hdnCounter").value ;
	for(var z = 0; z < theForm.length; z++)
	{
		if(theForm[z].type == 'checkbox' && theForm[z].name != 'chkSelect')
		{
			theForm[z].checked = theElement.checked;
			
		}
	}

	var k;
	for(k=1;k<=totalEmp;k++)
	{
		var dictrictName = document.getElementById('dictrictName'+k);
		var talukaName = document.getElementById('talukaName'+k);
		var cityClassName=document.getElementById('cityClassName'+k);
		var basicSalary= document.getElementById('basicSalary'+k);
		if(document.getElementById("checkbox"+k).checked)
		{
			dictrictName.disabled = false;
			talukaName.disabled=false;
			cityClassName.disabled=false;
		}else{
			
			dictrictName.disabled = true;
			talukaName.disabled=true;
			cityClassName.disabled=true;
		}
	}
}

function resetAll(){
	document.getElementById("frmCityClassUpdation").reset();
	var fmLength = document.getElementById("empListSize").value;
	var theForm = document.getElementById("frmCityClassUpdation");
	
	for(var z = 1; z <= fmLength; z++)
	{
		
		document.getElementById("dictrictName"+z).disabled = true;
		document.getElementById("talukaName"+z).disabled = true;
		document.getElementById("cityClassName"+z).disabled = true;
	}
	
}


function toggleDropCityClass(rowNum){
	
	var selectBox=document.getElementById("checkbox"+rowNum);
	var dictrictName=document.getElementById("dictrictName"+rowNum);
	var talukaName=document.getElementById("talukaName"+rowNum);
	var cityClassName=document.getElementById("cityClassName"+rowNum);
	if(selectBox.checked)
	{
		talukaName.disabled=false;
		dictrictName.disabled=false;
		cityClassName.disabled=false;
	}else{
		
		dictrictName.disabled=true;
		talukaName.disabled=true;
		cityClassName.disabled=true;
	}
}

function getTalukaList(counter)
{
	//alert("Inside getTalukaList");
	var dictrictName  = document.getElementById("dictrictName"+counter).value;
  //  alert("dictrictName"+dictrictName);
	if(dictrictName != -1)
	{
		//alert("Inside 1st if ");
		var uri="ifms.htm?actionFlag=getTalukaForCityClass&dictrictName="+dictrictName;
		
		xmlHttp=GetXmlHttpObject();

	   if (xmlHttp==null)
	   {
		   alert ("Your browser does not support AJAX!");
		   return;
	   }  
	   
	//   alert('comes before showProgressbar');
	   showProgressbar();
	   
	   xmlHttp.onreadystatechange=function()
	   {
		 //  alert('2');
		   if (xmlHttp.readyState==4)
			{ 
			 //  alert('3');
				if(xmlHttp.status==200)
				{
				//	alert('4');
					hideProgressbar();
					XMLDoc = xmlHttp.responseXML.documentElement;
					var XmlHiddenValues = XMLDoc.getElementsByTagName('item');
					document.getElementById('talukaName'+counter).options.length = 0;
					
					var optnStart = document.createElement("OPTION");
					optnStart.value = "-1";
					optnStart.text = "--Select--";
					document.getElementById("talukaName"+counter).options.add(optnStart);
					
					for(var j = 0;j<XmlHiddenValues.length;j++)
					{
						var talukaName =  XmlHiddenValues[j].childNodes[0].firstChild.nodeValue;
						//alert("talukaName"+talukaName);
						var talukaCode =  XmlHiddenValues[j].childNodes[1].firstChild.nodeValue;
						var optn = document.createElement("OPTION");
						optn.value = talukaCode;
						optn.text = talukaName;
						document.getElementById("talukaName"+counter).options.add(optn);
					}
					
				}
			}
	   };
	   xmlHttp.open("POST",uri,true);
	   xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	   xmlHttp.send(uri);
	}
	
}

function updateCityClass()
{
	var dictrictName = "";
	var sevarthID="";
	var empName="";
	var talukaName="";
	var cityClassName="";

//	alert("Inside the upadte city class");
	var totalEmp = document.getElementById("hdnCounter").value ;

	//alert("Value of totalEmp"+totalEmp);
	
	var iValue=document.getElementById("valueI").value;
	
	//alert("Value of ivalue"+iValue);
	
	//alert("totalEmp: "+totalEmp);
	
	
	var Empsize = document.getElementById("empListSize").value;

	//alert("Employee List Size"+Empsize);

	
	var selectedEmp = Number(0);
	var totalSelectedEmp = Number(0);
	var k;

	if(Number(iValue)+Number(50)<=Number(totalEmp)){
		totalEmp=Number(50)+Number(iValue);
	
		}
	else
	{
		var cnt=(Number(Empsize)-Number(iValue));
		totalEmp=Number(iValue)+Number(cnt)+Number(1);
		//alert('Value of current employyelist is'+totalEmp);
		}
//	alert("Value of employee after updation:"+totalEmp);
	
	for(k=iValue;k<totalEmp;k++)
	{
//alert("Checking the value of if");
		if(document.getElementById("checkbox"+k).checked)
		{
			selectedEmp = k ;	
			totalSelectedEmp++ ;
		}
	}
	//alert("totalSelectedEmp: "+totalSelectedEmp);
	
	if(selectedEmp == 0)
		{
			alert('Please select at least one Employee');
			return false; 
		}

	for(k=iValue;k<totalEmp;k++)
	{


		//alert("Inside the updation");
		
		if(document.getElementById("checkbox"+k).checked)
		{
			if(document.getElementById("dictrictName"+k).value=='-1')
			{
				var ddoCode=document.getElementById("hdnSevarthID"+k).value;
				alert("Please Select the District Name Code of SevarthID : "+ddoCode);
				return false;
			}
			if(	document.getElementById("cityClassName"+k).value=='-1')
			{
				var sevarth_id=document.getElementById("hdnSevarthID"+k).value;
				alert("Please Select the  City Class of SevarthID : "+sevarth_id);
				return false;
		
			}
		if(document.getElementById("talukaName"+k).value=='-1')
		{
			var sevarth_id1=document.getElementById("hdnSevarthID"+k).value;
			alert("Please Select the  Taluka of SevarthID : "+sevarth_id1);
			return false;
		}
			
			sevarthID = sevarthID + document.getElementById("hdnSevarthID"+k).value.trim() + "~" ;
			
			dictrictName = dictrictName + document.getElementById("dictrictName"+k).value.trim() + "~" ;

			//empName = empName + document.getElementById("hdnEmpName"+k).value.trim() + "~" ;

			talukaName = talukaName + document.getElementById("talukaName"+k).value.trim() + "~";

			cityClassName = cityClassName + document.getElementById("cityClassName"+k).value.trim() + "~";
			//alert("cityClassName: "+cityClassName);
			
		}
	}	
	//alert("sevarthID :"+sevarthID+"empName :"+empName+"dictrictName"+dictrictName+"talukaName"+talukaName+"cityClassName"+cityClassName);
	var url;
	url="./hrms.htm?actionFlag=updateCityClassForEmployee&sevarthID="+sevarthID+"&dictrictName="+dictrictName+"&cityClassName="+cityClassName+"&talukaName="+talukaName+"&flag=Y";
	document.frmCityClassUpdation.action= url;
	document.frmCityClassUpdation.submit();
}

</script>


<% } catch(Exception e){
e.printStackTrace();
}%>