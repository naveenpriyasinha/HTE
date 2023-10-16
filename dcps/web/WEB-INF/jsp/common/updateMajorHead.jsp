<%try { %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<%@ page contentType="text/html;charset=UTF-8"%>
<fmt:requestEncoding value="UTF-8" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"/>
<c:set var="empList" value="${resValue.empList}" />
<c:set var="empListSize" value="${resValue.empListSize}" />
<c:set var="lstMajorHeadCodes" value="${resValue.lstMajorHeadCodes}"></c:set>
<style> /* added by pratik 03-08-23 */
.scrollablediv {
    width: 100% !important;
}
</style>
<fmt:setBundle	basename="resources.dcps.dcpsLabels" var="DCPSLables" scope="request" />
<hdiits:form name="frmMajorHeadUpdation" encType="multipart/form-data" validate="true" method="post">
<fieldset class="tabstyle"><legend> <b><fmt:message
		key="CMN.MAJORHEADUPDATION" bundle="${DCPSLables}"></fmt:message></b> </legend>
		<c:set var="hdnCounter" value="0"/>
		
		<div class="scrollablediv">
			<display:table list="${empList}" id="empList" requestURI="" export="" pagesize="1000">
				<display:setProperty name="paging.banner.placement" value="bottom" />
				
					<display:column  titleKey="CMN.CHKBXEMPSELECT" title="<input name='chkSelect'  type='checkbox' onclick='checkUncheckAll(this)'/>" headerClass="datatableheader" style="text-align:center;width:5%" class="oddcentre" sortable="true">
						<input type="checkbox" align="left" name="checkbox${empList_rowNum}" id="checkbox${empList_rowNum}" onclick="toggleDropHeadOfAcctCode(${empList_rowNum})" />
					</display:column>
					
					<display:column headerClass="datatableheader" style="text-align:left;width:15%" class="oddcentre" sortable="true" titleKey="CMN.SEVARTHID">
						<c:out value="${empList[0]}"></c:out>
						<input type="hidden" id="hdnSevarthID${empList_rowNum}" name="hdnSevarthID${empList_rowNum}" value="${empList[0]}">
						<input type="hidden" id="hdnGradeID${empList_rowNum}" name="hdnGradeID${empList_rowNum}" value="${empList[2]}">
						
							<c:set var="hdnCounter" value="${hdnCounter+1}"/>
					</display:column>
			
					<display:column headerClass="datatableheader" style="text-align:left;width:40%" class="oddcentre" sortable="true" titleKey="CMN.EMPNAME">
						<c:out value="${empList[1]}"></c:out>
					</display:column>
					
					<display:column titleKey="CMN.MAJORHEADCODE" headerClass="datatableheader" style="text-align:center;width:40%" class="oddcentre" sortable="true">
					<select style="width: 442px;" disabled="disabled" id="majorHeadCode${empList_rowNum}" name="majorHeadCode${empList_rowNum}" >
					
					<option value="-1"><fmt:message   key="COMMON.DROPDOWN.SELECT" /></option>
					<c:forEach var="HeadCodes" items="${resValue.lstMajorHeadCodes}">
						<c:choose>
							<c:when test="${empList[3] == HeadCodes.lookupDesc}">
								<option value="${HeadCodes.lookupDesc}" selected="selected"><c:out
									value="${HeadCodes.lookupDesc}"></c:out></option>
							</c:when>
							<c:otherwise>
								<option value="${HeadCodes.lookupDesc}"><c:out
									value="${HeadCodes.lookupDesc}"></c:out></option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
						
					</select>
					
					</display:column>
		
			</display:table>
		</div>
</fieldset>
		
<input type="hidden" id="hdnCounter" name="hdnCounter" value="${hdnCounter}"/>
<input type="hidden" id="empListSize" name="empListSize" value="${empListSize}"/>

	<div align="center"><br>
		<hdiits:button name="BTN.UpdateBasic" id="btnUpdate" type="button" captionid="BTN.UPDATEBANKBRANCH" bundle="${DCPSLables}" onclick="updateMajorHeadCode();"  />&nbsp;
		 <hdiits:button name="BTN.ResetBasic" id="btnReset" type="button" captionid="NGR.RESET" bundle="${DCPSLables}" onclick="resetAll();" />
	</div>

</hdiits:form>

<script>
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
		var majorHeadCode = document.getElementById('majorHeadCode'+k);
		if(document.getElementById("checkbox"+k).checked)
		{
			majorHeadCode.disabled = false;
		}else{
			
			majorHeadCode.disabled = true;
		}
	}
}


function selectGradeID(grade_id,rowNum)
{
	//alert("hii grade_id "+grade_id);
	//alert("hii rowNum "+rowNum);
	var headCode = document.getElementById("majorHeadCode"+rowNum).value ;
	//alert(headCode);
	
    if(grade_id==100067)
    {
    //alert(document.getElementById("majorHeadCode"+rowNum).value);
        if((document.getElementById("majorHeadCode"+rowNum).value!=-1 && document.getElementById("majorHeadCode"+rowNum).value==8009517301) || document.getElementById("majorHeadCode"+rowNum).value==8336501101)
        {   
        	//alert("okkk");
        }
        else {
        	alert("selected account code is not correct");
        	document.getElementById("majorHeadCode"+rowNum).value="-1";
        }
       }


    if(grade_id==100066 || grade_id==100001 || grade_id==100064 )
    {
    	 //alert(document.getElementById("majorHeadCode"+rowNum).value);
        if((document.getElementById("majorHeadCode"+rowNum).value!=-1 && document.getElementById("majorHeadCode"+rowNum).value==8009501201) || document.getElementById("majorHeadCode"+rowNum).value==8336501101 )
        {   
        	//alert("okkk");
        }

        else {
            alert("selected account code is not correct");
        	document.getElementById("majorHeadCode"+rowNum).value="-1";
        }

        
       }
    
    
}	


	






function resetAll(){
	document.getElementById("frmMajorHeadUpdation").reset();
	var fmLength = document.getElementById("empListSize").value;
var theForm = document.getElementById("frmMajorHeadUpdation");
	
	for(var z = 1; z <= fmLength; z++)
	{
		
		document.getElementById("majorHeadCode"+z).disabled = true;
	}
	
}


function toggleDropHeadOfAcctCode(rowNum){
	var selectBox=document.getElementById("checkbox"+rowNum);
	var majorHeadCode=document.getElementById("majorHeadCode"+rowNum);
	
	if(selectBox.checked)
	{
		
		majorHeadCode.disabled=false;
	}else{
		
		majorHeadCode.disabled=true;
	}
}










function updateMajorHeadCode()
{
	var majorHeadCode = "";
	var sevarthID="";
	var totalEmp = document.getElementById("hdnCounter").value ;
	//alert("totalEmp: "+totalEmp);
	var selectedEmp = Number(0);
	var totalSelectedEmp = Number(0);
	var k;
	for(k=1;k<=totalEmp;k++)
	{
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

	for(k=1;k<=totalEmp;k++)
	{
		if(document.getElementById("checkbox"+k).checked)
		{
			if(document.getElementById("majorHeadCode"+k).value=='-1'){
				var ddoCode=document.getElementById("hdnSevarthID"+k).value;
				alert("Please Select the Major Head Code of SevarthID : "+ddoCode);
				return false;
			}
			sevarthID = sevarthID + document.getElementById("hdnSevarthID"+k).value.trim() + "~" ;
			
			majorHeadCode = majorHeadCode + document.getElementById("majorHeadCode"+k).value.trim() + "~" ;
			//alert("majorHeadCode: "+majorHeadCode);
			
		}
	}
	alert("Head Code Updated Successfully");
	//alert("sevarthID :"+sevarthID+"majorHeadCode :"+majorHeadCode);
	var url;
	url="./hrms.htm?actionFlag=updateMajorHead&sevarthID="+sevarthID+"&majorHeadCode="+majorHeadCode+"&flag=Y";
	document.frmMajorHeadUpdation.action= url;
	document.frmMajorHeadUpdation.submit();
}

</script>


<% } catch(Exception e){
e.printStackTrace();
}%>