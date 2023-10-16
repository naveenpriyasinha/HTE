<%@ include file="../core/include.jsp" %>
<!--
 * Filename	   : salestaxUpload.jsp
 * Purpose	   : 
 * @VERSION    : 1.0
 * @AUTHOR     : Smit Shah
 * Date        : 5 March 2009
 * 
 * 
 **-->
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %> 
<fmt:setLocale value='<%=session.getAttribute("locale")%>'/>
<fmt:setBundle basename="resources.app.diary.DiaryLables" var="diaryLables" scope="request"/>
<fmt:setBundle basename="resources.expacct.expacctAlerts" var="expacctAlert" scope="application"/>
<fmt:setBundle basename="resources.expacct.expacctHeads" var="expacctHead" scope="application"/>        
<fmt:setBundle basename="resources.expacct.expacct_en_US" var="expacctLabels" scope="application"/>

  <script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"> </script>
  <script type="text/javascript" src="<c:url value="script/common/tabcontent.js"/>"> </script>
  <script type="text/javascript" src="script/common/commonfunctions.js"> </script>
  <script  type="text/javascript" src="script/common/CalendarPopup.js"> </script>
  <script  type="text/javascript" src="script/login/validation.js"> </script>
  <script  type="text/javascript" src="script/common/tagLibValidation.js"> </script>
  <script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"> </script>
  <script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"> </script>
  <script type="text/javascript" src="script/exprcpt/Common.js"> </script>
  <script type="text/javascript">
  	<%
     	 String dateTime = new SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date());
     %>
    function validate() 
     {
        var curDate=document.getElementById("id_hdDate").value;     
          var upDate=document.getElementById("id_txtDate").value;
		var conCurrDate=checkDate(curDate);
		//alert(conCurrDate);
		var conUpDate=checkDate(upDate);
				//alert(conUpDate);
         if(conUpDate>conCurrDate)
         {
   	          	alert("<fmt:message key="BS.ALRT.VALIDDATE" bundle="${expacctAlert}"/>");
         }
         else if((document.frm_Upload.txtDate.value.length)==0 )			
	     {
		     alert("<fmt:message key="BS.ALRT.DATENOTNULL" bundle="${expacctAlert}"/>");
		     document.frm_Upload.txtDate.focus();
	     }
	     else
	     {
	     	var rowCount=document.getElementById("myTableattachment").rows.length;
	     	if(rowCount<2)
	     	{
	     		alert("<fmt:message key="BS.ATTACH_SCROLL" bundle="${expacctAlert}"/>");
	     		return;
	     	}
	     	document.getElementById("wait").style.visibility="visible"; 
	     	document.frm_Upload.submit();
	     }
     }
    
    
    function fnValidateDate(cntrName)
     {
     	if(cntrName.value!='')
     	{
     		isDate(cntrName.value,'dd/MM/yyyy',cntrName); 
     	}
     }	
   </script>
   <c:set var="resultObj" value="${result}"> </c:set>
   <c:set var="resValue" value="${resultObj.resultValue}"> </c:set>	
   <c:set var="scrollDtlsList" value="${resValue.scrollDtlsList}"> </c:set>
   <c:set var="messageList" value="${resValue.messageList}"> </c:set>
      
  <hdiits:form validate="true" method="post"  action="ifms.htm?actionFlag=uploadSalesTaxScroll" name="frm_Upload" encType="multipart/form-data">
      <div id="statusBar" style="width:50%; background-color: #BADCFD ;display:none;font: bold 10px Arial">
	  </div>		
	  <div id="progressImage" style="display:none">
	  </div>		
        <table cellspacing="0" cellpadding="0" align="center" width="95%" class="TableBorderLTRBN" >
          <tr> 
            <td  align="center" class="formTitle"><b>Sales Tax Scroll</td>
          </tr>
          <tr>
            <td>
              <fieldset class="tabstyle">
                <legend  id="headingMsg"><fmt:message key="BS.HEAD.UPLOAD" bundle="${expacctHead}"/></legend>
                <table width="100%" cellspacing="0" cellpadding="0">
                  <c:if test="${messageList!=null}">
                    <tr>
                      <td>
                        <jsp:include page="/WEB-INF/jsp/common/messageList.jsp"/>
                      </td>
                    </tr>
                  </c:if>
                  <tr>
                    <td height="10">
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <table width="40%" align="center" cellpadding="0" cellspacing="0">
                        <tr height="20">
                          <td align="left" class="Label2" width="20%">
                            Latest Dealer Data Date(dd/mm/yyyy) </td>
                          <td align="left" width="20%">
                          	<input type="hidden" value="<%=dateTime%>" name="hdDate" id="id_hdDate"/>
                            <hdiits:text name="txtDate" maxlength="10" id="id_txtDate" tabindex="1" size="20" onblur="fnValidateDate(this)"/>&nbsp;<img src=images/CalendarImages/ico-calendar.gif width=20 onClick=window_open("txtDate",375,570) >
                            <font color="red">*</font>
                          </td>
                        </tr>
	                  </table>
                    </td>
                  </tr>
                  <tr>
                    <td height="10">
                    </td>
                  </tr>
                  <tr>
                    <td colspan="4">
                      <jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
                        <jsp:param name="attachmentName" value="attachment" />
                        <jsp:param name="formName" value="frm_Upload" />
                        <jsp:param name="attachmentType" value="Document" />
                        <jsp:param name="attachmentTitle" value="Attachment" />
                        <jsp:param name="multiple" value="N" />
                      </jsp:include>
                    </td>
                  </tr>
                  <tr height="20">
                    <td>
                    </td>
                  </tr>
                  <tr align="center">
                    <td>	
                      <hdiits:button name="btnUpload" type="button" value="Upload"  onclick="javascript:validate();" tabindex="2"/>
                    </td>	
                  </tr>
                  <tr>
                    <td height="20">
                    	<div id='wait'style="visibility:hidden" align="center"><b>Please wait. Upload Scroll in progress....</b> 
						    <img src ="images/Hourglass_icon.gif" id="wait" >
						</div>
                    </td>
                  </tr>
                   <tr>
                      <td align="center">     
                       <div class="scrollablediv">
						<display:table list="${scrollDtlsList}" id="vo" excludedParams="ajax" export="false" partialList="" style="width:80%;" cellspacing="2" cellpadding="2" requestURI="ifms.htm?actionFlag=showSalestaxUpSclForm">
							
							 <display:setProperty name="paging.banner.placement" value="both"/>
							 <display:column class="oddcentre" sortable="true" property="uploadedDate"  title="Uploaded Date"  headerClass="tableHeader" style="text-align:center" />       
							 <display:column class="oddcentre" sortable="true" property ="userName"  title="User Name"  headerClass="tableHeader" style="text-align:center"/>
							 <display:column class="oddcentre" sortable="true" property ="latestDate"  title="Latest Dealer Data Date"  headerClass="tableHeader" style="text-align:center"/>
							 <display:column class="oddcentre" sortable="true" property="fileName"  title="File Name"  headerClass="tableHeader" style="text-align:center"/>
							 <display:column class="oddcentre" sortable="true" property="noOfRecords"  title="No Of Records"  headerClass="tableHeader" style="text-align:center"/>
							
			 			</display:table>
			 			</div>
                      </td>
                    </tr>
                </table>
              </fieldset>
            </td>
          </tr>
        </table>
      </hdiits:form>
 <script>
	document.getElementById('id_txtDate').focus();
</script>
	 
	 