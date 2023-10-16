 <%
 try {
 %>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<script type="text/javascript" src="<c:url value="/script/common/address.js"/>">
</script>
<script type="text/javascript" src="script/common/tagLibValidation.js">
</script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>">
</script>
<script type="text/javascript" src="script/common/CalendarPopup.js">
</script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>">
</script>
<c:set var="resultObj" value="${result}" > 
</c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > 
</c:set>

<fmt:setBundle basename="resources.gnl.CourtCases.CCLables" var="ccLables" scope="request" />

<table align="left" width="100%" >
    <tr>
    <td>
    <hdiits:fieldGroup bundle="${ccLables}" captionLang="multi" expandable="true" id="hrngDtlFldGrp" titleCaptionId="HRMS.CRT.caseHrngDetalis">
      <table width="100%" >
        <tr>
          <td width="25%">
            <b><hdiits:caption captionid="HRMS.CRT.DOH" bundle="${ccLables}" /></b>
          </td>
          <td width="25%">  
            <hdiits:dateTime captionid="HRMS.CRT.DOH" bundle="${ccLables}" caption="Date Of Hearing" name="DOH" mandatory="true" maxvalue="31/12/2099"/>
          </td>
        </tr>
        <tr>
          <td width="25%">
            <b><hdiits:caption captionid="HRMS.CRT.gov_p_crt" bundle="${ccLables}"/></b>
          </td>
          <td width="25%">  
            <hdiits:select name="govPCrt" size ="1" captionid="drop_down">
              <hdiits:option value="n">
                <fmt:message key="HRMS.No" bundle="${ccLables}"/>
              </hdiits:option>
            <hdiits:option value="y" >
                <fmt:message key="HRMS.Yes" bundle="${ccLables}"/>
              </hdiits:option>
              </hdiits:select>
          </td>
          <td width="25%">
            <b><hdiits:caption captionid="HRMS.CRT.attndName" bundle="${ccLables}"/></b>
          </td>
          <td width="25%">  
            <hdiits:textarea rows="3" cols="40" name="attndName" caption ="Attendee Name" mandatory="true" maxlength="200"/>
          </td>
        </tr>
        <tr>
          <td width="25%">
            <b><hdiits:caption captionid="HRMS.CRT.typeNotice" bundle="${ccLables}"/></b>
          </td >
          <td width="25%">  
            <hdiits:text name="typeNotice" caption="Type Of Notice"  maxlength="40" />
          </td>	
        </tr>
        <tr>
          <td width="25%">
            <b><hdiits:caption captionid="HRMS.CRT.advctGen" bundle="${ccLables}"/></b>
          </td>
          <td width="25%">  
            <hdiits:select name="advctGen" size ="1" captionid="drop_down">
              <hdiits:option value="n">
                <fmt:message key="HRMS.No" bundle="${ccLables}"/>
              </hdiits:option>
            <hdiits:option value="y" >
                <fmt:message key="HRMS.Yes" bundle="${ccLables}"/>
              </hdiits:option>
              </hdiits:select>
          </td>
          <td width="25%">
            <b><hdiits:caption captionid="HRMS.CRT.govAdvct" bundle="${ccLables}"/></b> 
          </td>
          <td width="25%">  
            <hdiits:select name="govAdvct" size ="1" captionid="drop_down">
               <hdiits:option value="n">
                <fmt:message key="HRMS.No" bundle="${ccLables}"/>
              </hdiits:option>
            <hdiits:option value="y" >
                <fmt:message key="HRMS.Yes" bundle="${ccLables}"/>
              </hdiits:option>
             </hdiits:select>
          </td>
        </tr>
        <tr>
          <td width="25%">
            <b><hdiits:caption captionid="HRMS.CRT.addAdvctGen" bundle="${ccLables}"/></b> 
          </td>
          <td width="25%">  
            <hdiits:select name="addAdvctGen" size ="1" captionid="drop_down">
              <hdiits:option value="n">
                <fmt:message key="HRMS.No" bundle="${ccLables}"/>
              </hdiits:option>
            <hdiits:option value="y" >
                <fmt:message key="HRMS.Yes" bundle="${ccLables}"/>
              </hdiits:option>
              </hdiits:select>
          </td>
          <td width="25%">
            <b><hdiits:caption captionid="HRMS.CRT.partyAdvct" bundle="${ccLables}"/></b>
          </td>
          <td width="25%">  
            <hdiits:select name="partyAdvct" size ="1" captionid="drop_down">
              <hdiits:option value="n">
                <fmt:message key="HRMS.No" bundle="${ccLables}"/>
              </hdiits:option>
            
              <hdiits:option value="y" >
                <fmt:message key="HRMS.Yes" bundle="${ccLables}"/>
              </hdiits:option>
              </hdiits:select>
          </td>
        </tr>
        <tr>
          <td width="25%">
            <b><hdiits:caption captionid="HRMS.CRT.hrngDetalis" bundle="${ccLables}"/></b>
          </td>
          <td width="25%">  
            <hdiits:text name="hrngDetalis" caption="Hearing Details"  maxlength="40" mandatory="true"/>
          </td>	
        </tr>
      </table>	
      </hdiits:fieldGroup>
    </td>
  </tr>
  <tr>
    <td>
      <hdiits:fieldGroup bundle="${ccLables}" expandable="true" id="nxtHrngDtlFldGrp" titleCaptionId="HRMS.CRT.nxtHrngDetalis">  
      <table width="100%" >
        <tr>
          <td width="25%">
            <b><hdiits:caption captionid="HRMS.CRT.nxtHDate" bundle="${ccLables}"/></b>
          
          </td>
          <td width="25%">  
            <hdiits:dateTime captionid="HRMS.CRT.nxtHDate" bundle="${ccLables}" caption="Next Hearing Date" name="nxtHDate" maxvalue="31/12/2099"/>
          </td>
          <td width="25%">
            <b><hdiits:caption captionid="HRMS.CRT.memAttnd" bundle="${ccLables}"/></b>
          </td>
          <td width="25%">  
            <hdiits:textarea rows="3" cols="40" name="memAttnd" caption ="Members To Attend" maxlength="200"/>
          </td>
          
        </tr>
        
        <tr>
          <td  width="25%">
            <b><hdiits:caption captionid="HRMS.CRT.rqrAttnd" bundle="${ccLables}"/></b>
          </td>
          <td  width="25%">  
            <hdiits:select name="rqrAttnd" size ="1" captionid="drop_down">
              <hdiits:option value="n">
                <fmt:message key="HRMS.No" bundle="${ccLables}"/>
              </hdiits:option>
            <hdiits:option value="y" >
                <fmt:message key="HRMS.Yes" bundle="${ccLables}"/>
              </hdiits:option>
              </hdiits:select>
          </td>
          
        </tr>
      </table>
      </hdiits:fieldGroup>
    </td>
  </tr>
  

</table>
<%
 }
 catch(Exception e)
 {
 e.printStackTrace();
 }
 %>