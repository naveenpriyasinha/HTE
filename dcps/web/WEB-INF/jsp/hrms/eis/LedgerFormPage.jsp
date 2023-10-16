<%
try {
%>
<%@page import="java.util.*"%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.eis.eisLables_en_US" var="commonLables" scope="request"/>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/Address.js"/>">
</script>

<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
	
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="LocName" value = "${resValue.baseLoginMap.locationVO.locName}" />  
<c:set var="ledgerformdata" value="${resValue.ledgerformdata}" ></c:set>
<c:set var="yearList" value="${resValue.yearList}" ></c:set>
<script>


function generateledgerform()
{
// window.open("hrms.htm?actionFlag=generateledgerform","ledgerform","status=yes, toolbar=yes,resizable=1,height=400, width=600,scrollbars=1");
//  document.frmledgerformPara.submit();
window.close();
}
</script>


<hdiits:form name="frmledgerformPara" validate="true" method="POST"
	action="./hrms.htm?actionFlag=getLedgerFormpara" encType="multipart/form-data">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="Ledger Form" caption="Ledger Form"/> </b></a></li>
	</ul>
	</div>
	
	<div class="halftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0">
	<div align="center">
 <table width="80%" align="center" >
        <%
        ArrayList ledgerformdata = new ArrayList();
        ArrayList dataList = new ArrayList();
          
       
        ledgerformdata=(ArrayList)pageContext.getAttribute("ledgerformdata");
        int last=ledgerformdata.size();
        if(last>0)
        {
        dataList=(ArrayList)ledgerformdata.get(last-1);
					 %>
	  <tr>
        <td colspan="5"> <hr> </td>  
      </tr>

	  <tr>
        <td colspan="5"><b>${LocName}<b> </td>  
      </tr>

	  <tr>
        <td colspan="5"></td>  
      </tr>

    <tr>
       <td align="left" width="20%"> <b>PAY</b></td>
       <td align="right" width="25%"><%=dataList.get(0)%>  </td>  
       <td align="left" width="5%">&nbsp;</td>     
       <td align="left" width="20%" > <b>C.P.F.</b></td>
       <td align="right" width="25%"><%=dataList.get(1)%></td>       
     </tr>
    <tr>
       <td align="left" width="20%"> <b>SP. PAY</b></td>
       <td align="right" width="25%"><%=dataList.get(2)%>  </td>  
       <td align="left" width="5%">&nbsp;</td>     
       <td align="left" width="20%" > <b>G.P.F.</b></td>
       <td align="right" width="25%"><%=dataList.get(3)%>  </td>       
     </tr>
    <tr>
       <td align="left" width="20%"> <b>L.S.</b></td>
       <td align="right" width="25%"><%=dataList.get(6)%>  </td>  
       <td align="left" width="5%">&nbsp;</td>     
       <td align="left" width="20%" > <b>G.P.F. ADV.</b></td>
       <td align="right" width="25%"><%=dataList.get(5)%>  </td>       
     </tr>
    <tr>
       <td align="left" width="20%"> <b>Per.Pay</b></td>
       <td align="right" width="25%"><%=dataList.get(4)%>  </td>  
       <td align="left" width="5%">&nbsp;</td>     
       <td align="left" width="20%" > <b>S.I.S.</b></td>
       <td align="right" width="25%"><%=dataList.get(7)%>  </td>       
     </tr>
    <tr>
       <td align="left" width="20%"> <b>D.A.</b></td>
       <td align="right" width="25%"><%=dataList.get(8)%>  </td>  
       <td align="left" width="5%">&nbsp;</td>     
       <td align="left" width="20%" > <b>FAN.ADV.</b></td>
       <td align="right" width="25%"><%=dataList.get(9)%>  </td>       
     </tr>
    <tr>
       <td align="left" width="20%"> <b>D.PAY</b></td>
       <td align="right" width="25%"><%=dataList.get(10)%>  </td>  
       <td align="left" width="5%">&nbsp;</td>     
       <td align="left" width="20%" > <b>I. TAX</b></td>
       <td align="right" width="25%"><%=dataList.get(11)%>  </td>       
     </tr>
    <tr>
       <td align="left" width="20%"> <b>C.L.A.</b></td>
       <td align="right" width="25%"><%=dataList.get(12)%>  </td>  
       <td align="left" width="5%">&nbsp;</td>     
       <td align="left" width="20%" > <b>H.R.R.</b></td>
       <td align="right" width="25%"><%=dataList.get(13)%>  </td>       
     </tr>
    <tr>
       <td align="left" width="20%"> <b>H.R.A.</b></td>
       <td align="right" width="25%"><%=dataList.get(14)%>  </td>  
       <td align="left" width="5%">&nbsp;</td>     
       <td align="left" width="20%" > <b>PRO.TAX.</b></td>
       <td align="right" width="25%"><%=dataList.get(15)%>  </td>       
     </tr>
    <tr>
       <td align="left" width="20%"> <b>W.A.</b></td>
       <td align="right" width="25%"><%=dataList.get(16)%>  </td>  
       <td align="left" width="5%">&nbsp;</td>     
       <td align="left" width="20%" > <b>HBA-PRIN</b></td>
       <td align="right" width="25%"><%=dataList.get(17)%>  </td>       
     </tr>
    <tr>
       <td align="left" width="20%"> <b>Oth.A.</b></td>
       <td align="right" width="25%"><%=dataList.get(18)%>  </td>  
       <td align="left" width="5%">&nbsp;</td>     
       <td align="left" width="20%" > <b>HBA-INT</b></td>
       <td align="right" width="25%"><%=dataList.get(19)%>  </td>       
     </tr>
    <tr>
       <td align="left" width="20%"> <b>T.A.</b></td>
       <td align="right" width="25%"><%=dataList.get(20)%>  </td>  
       <td align="left" width="5%">&nbsp;</td>     
       <td align="left" width="20%" > <b>MCA-PRIN</b></td>
       <td align="right" width="25%"><%=dataList.get(21)%>  </td>       
     </tr>
    <tr>
       <td align="left" width="20%"> <b>MEDI.A.</b></td>
       <td align="right" width="25%"><%=dataList.get(22)%>  </td>  
       <td align="left" width="5%">&nbsp;</td>     
       <td align="left" width="20%" > <b>MCA-INT</b></td>
       <td align="right" width="25%"><%=dataList.get(23)%>  </td>       
     </tr>
    <tr>
       <td align="left" width="20%"> <b>I.R.</b></td>
       <td align="right" width="25%">0  </td>  
       <td align="left" width="5%">&nbsp;</td>     
       <td align="left" width="20%" > <b>Jeep Rent</b></td>
       <td align="right" width="25%"><%=dataList.get(25)%>  </td>       
     </tr>
    <tr>
       <td align="left" width="20%"> <b>FES.ADV.(-)</b></td>
       <td align="right" width="25%"><%=dataList.get(26)%>  </td>  
       <td align="left" colspan="3" width="50%">&nbsp;</td>     
     </tr>
    <tr>
       <td align="left" width="20%"> <b>FOOD.ADV.(-)</b></td>
       <td align="right" width="25%"><%=dataList.get(27)%>  </td>  
       <td align="left" colspan="3" width="50%">&nbsp;</td>     
     </tr>
    <tr>
       <td align="left" width="20%"> <b>MISC.RECV.</b></td>
       <td align="right" width="25%"><%=dataList.get(28)%>  </td>  
       <td align="left" colspan="3" width="50%">&nbsp;</td>     
     </tr>
	  <tr>
        <td colspan="5"> <hr> </td>  
      </tr>
      
    <tr>
       <td align="left" width="20%"> <b>GROSS AMT.</b></td>
       <td align="right" width="25%"><%=dataList.get(29)%>  </td>  
       <td align="left" width="5%">&nbsp;</td>     
       <td align="left" width="20%" > <b>Total DED.</b></td>
       <td align="right" width="25%"><%=dataList.get(30)%>  </td>       
     </tr>

    <tr>
       <td align="left" colspan="3" width="50%">&nbsp;</td>     
       <td align="left" width="20%"> <b>NET TOTAL</b></td>
       <td align="right" width="25%"><%=dataList.get(31)%>  </td>  
     </tr>
      <%}else{ %>
	  <tr>
        <td colspan="5"> <hr> </td>  
      </tr>
      <%} %>
	
   </table>
   <br> 
   <table>
   
	
	<tr> <td> </td></tr>
	<tr> <td> </td></tr>
	<tr>
	<td align="center" colspan="3">
	<hdiits:button name="btn1" value="Generate ledgerform" caption="OK" 
	id="btnSubmit1" captionid="Generate ledgerform" onclick="generateledgerform()" 
	   type="button" />
	  </td>
	  </tr>	
	</table> 
    <br>
 </div>
 
	
	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

