<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>



<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="deducNamesFromMpg" value="${resValue.deducNamesFromMpg}" > </c:set>	
<c:set var="actionList" value="${resValue.actionList}" > </c:set> 
<c:set var="deducMpgSize" value="${resValue.deducMpgSize}" > </c:set>
<c:set var="deducNamesFromExpr" value="${resValue.deducNamesFromExpr}" > </c:set>

<script>
function checkComboStates(allow_code,val,amount)
{
//alert('in checkstate funct..' + val + 'amount ' + amount);

 for(i=0;i<opener.document.insEmpMpg.tax_name.length;i++)
  if(opener.document.insEmpMpg.tax_name[i].value == val)
   {
     //opener.document.insEmpMpg.tax_name[i].checked = true;     
     //opener.document.insEmpMpg.tax_name[i].disabled = true;
     
      var txtname = 'txt' + val; 
      var txtIsChecked = 'txtIsChecked' + val;
      var allowcodeId = 'txtDeducCode' + val;
  //          alert('textbox name ' + txtname);
//      alert('value of textbox is ' + opener.document.getElementById(txtname).value);
     opener.document.getElementById(txtname).value = amount;
     opener.document.getElementById(txtIsChecked).value="1";
     opener.document.getElementById(txtname).style.display = '';
     //opener.document.getElementById(txtname).disabled=true;
     var btnAdd = 'btn_add' + val; 
     opener.document.getElementById(btnAdd).style.display = 'none';
     opener.document.getElementById(btnAdd).value='Edit';
     opener.document.getElementById(allowcodeId).value=allow_code;
   }
}


function checkComboStatesDefault(allow_code,val,amount)
{
//alert('in checkComboStatesDefault funct..' + val + 'amount ' + amount);

 for(i=0;i<opener.document.insEmpMpg.tax_name.length;i++)
  if(opener.document.insEmpMpg.tax_name[i].value == val)
   {
     //opener.document.insEmpMpg.tax_name[i].checked = true;     
     //opener.document.insEmpMpg.tax_name[i].disabled = true;
     
      var txtname = 'txt' + val; 
      var txtIsChecked = 'txtIsChecked' + val;
      var allowcodeId = 'txtDeducCode' + val;
  //          alert('textbox name ' + txtname);
//      alert('value of textbox is ' + opener.document.getElementById(txtname).value);
     opener.document.getElementById(txtname).value = amount;
     opener.document.getElementById(txtIsChecked).value="1";
     opener.document.getElementById(txtname).style.display = '';
     opener.document.getElementById(txtname).disabled=true;
     var btnAdd = 'btn_add' + val; 
     opener.document.getElementById(btnAdd).style.display = 'none';
     opener.document.getElementById(btnAdd).value='Edit';
     opener.document.getElementById(allowcodeId).value=allow_code;
   }
}



function resetMainForm(val)
{
         var txtname = 'txt' + val; 
         var txtIsChecked = 'txtIsChecked' + val;
         var allowcodeId = 'txtDeducCode' + val;
         opener.document.getElementById(txtname).style.display = '';
         opener.document.getElementById(txtname).disabled = false;
         opener.document.getElementById(txtname).value='';
         var btnAdd = 'btn_add' + val;
         opener.document.getElementById(btnAdd).value = 'Add';      
         opener.document.getElementById(btnAdd).style.display = 'none';   
         opener.document.getElementById(txtIsChecked).value='';
         opener.document.getElementById(allowcodeId).value='';
}
</script>

<hdiits:form name="insEmpMpg" validate="true" method="POST" 
	         action="">
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1">EMPLOYEE ALLOWANCE MAPPING</a></li>
	</ul>
	</div>
	
	
<div class="halftabcontentstyle">
<div id="tcontent1" class="tabcontent" tabno="0">
<c:forEach  var="list" items="${actionList}">  
<script>
 resetMainForm(${list[0]});
 </script>
</c:forEach>

<c:choose>

<c:when test = "${deducMpgSize eq 0}">
<script>
resetMainFormCombo();
</script>
<c:forEach  var="list" items="${actionList}">  
<script>
 resetMainForm(${list[0]});
 </script>
</c:forEach>
</c:when>

<c:otherwise>
<c:forEach  var="list" items="${deducNamesFromMpg}">  
<script>
 checkComboStates(${list[0]}, ${list[1].deducCode}, ${list[2]});
 </script>
</c:forEach>
</c:otherwise>
</c:choose>


<c:forEach  var="exprList" items="${deducNamesFromExpr}"> 
<c:forEach  var="list" items="${deducNamesFromMpg}">

 <c:choose>
   <c:when test="${list[1].deducCode==exprList.hrPayDeducTypeMst.deducCode}">
    <script>
     checkComboStatesDefault(${list[0]}, ${list[1].deducCode}, ${list[2]});    
     </script>
   </c:when>
  </c:choose>
 </c:forEach>
 </c:forEach>



<script>
self.close();
</script>

</div>
</div>
</hdiits:form>

<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		//setComboState()


		</script>
		<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />


<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

