<%@page import="com.tcs.sgv.common.utils.StringUtility"%> 
<%@include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator" %>
<%@page import="com.tcs.sgv.appgen.valueobject.AdmScreenMst"%>
<%@page import="com.tcs.sgv.appgen.valueobject.AdmDtlScreen"%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<script type="text/javascript" >
	var navDisplay = true;
</script>

<fmt:setBundle basename="resources.CommonLables" var="adminLables" scope="request"/>
<fmt:setBundle basename="resources.appgen.Labels" var="appgenLables" scope="request"/>

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="admScreenMstVOList" value="${resValue.admScreenMstVOList}"></c:set>
<c:set var="loginLanguage" value="${resValue.loginLanguage}"></c:set>
<c:set var="loginLangId" value="${resValue.loginLangId}"></c:set>

<script type="text/javascript" >
	function editScreen(keyAndScreenName){
//		alert(keyAndScreenName);
		
		var keyAndScreenNameStr=keyAndScreenName.toString();
	
		array =	keyAndScreenNameStr.split(":");
//		alert("Screen Id:"+array[0]);
//		alert("You are going to edit Screen Name:"+array[1]);
// 		Set hidden variable used in the service
		document.getElementById("screenIdForEdit").value=array[0];
		document.forms[0].action='hdiits.htm?actionFlag=getScreenMstAndScreendtlVOByScreenId';
		document.forms[0].submit();
		
	/*
	action="hdiits.htm?actionFlag=getScreenMstAndScreendtlVOByScreenId"
	requestURI="hdiits.htm?actionFlag=getScreenMstAndScreendtlVOByScreenId"
	*/
	}
	
		function validateForm_tableWizard(){
//			alert (" in the validateForm_editDeleteForm function.. 2");
			return true;
		}
	// FUNCTION FOR DELETING DATA
	function deleteData(){
//		alert('deleteData() START...');
		var isChecked = false;
		for (var i = 0; i < document.forms[0].deletedata.length; i++) {
	   			if (document.forms[0].deletedata[i].checked) {
	     			 isChecked = true;
	  			}
		}
		if(isChecked ){
		var answer=confirm("Are you sure want to delete the selected data?");
			if(answer){
		        try
		    	{   
		    	// Firefox, Opera 8.0+, Safari    		    	
		    	xmlHttp=new XMLHttpRequest();    
		    	}
				catch (e)
				{    // Internet Explorer    
					try
		      		{
		      		    xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");   
		      		}
				    catch (e) {
				          try
		        		  {
		        		          xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
		        		  }catch (e)
					      {
					              alert("Your browser does not support AJAX!");        
					              return false;        
					      }
					 }
		    	}
		    	var para = run();
		    	//alert(para);
		    	var url = "hdiits.htm?actionFlag=deleteScreenDataFromAllTable&"+ para;
		    	xmlHttp.onreadystatechange = function(){
		    	if (xmlHttp.readyState == 4) 
				{     
					if (xmlHttp.status == 200) 
					{ 
						document.getElementById("myDiv").innerHTML = xmlHttp.responseText;
						//alert('<%=request.getRequestURL() %>');
						
						var requeswtURL= '<%=request.getRequestURL() %>';
						//alert(requeswtURL);						
//						window.location.href="http://localhost:8080/hdiits/hdiits.htm?actionFlag=getAllScreenMstAndScreenDtlVO";
						window.location.href=requeswtURL+"?actionFlag=getAllScreenMstAndScreenDtlVO";
			    	}
			    }
			   }
			   xmlHttp.open("POST", encodeURI(url) , false);    
			   xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");		
			   xmlHttp.send(encodeURIComponent(null));
//		document.forms[0].action='hdiits.htm?actionFlag=deleteScreenDataFromAllTable';
//		document.forms[0].submit();
			}else{
			}
		}
		else{
			alert("Please select the checkbox");
		}
	}
	
	
	
	function validateForm_tableWizard(){
	
	//alert (" in the validateForm_editDeleteForm function.. 2");
	return true;
	}
</script>


<%int i=1;
try{
%>

	<c:set var="i" value="1"></c:set>
	
	<hdiits:form name="tableWizard" validate = "true" method ="post" action="hdiits.htm?viewName=fieldWizard">
	<div><br><br>
		<!--  screenIdForEdit used in the service to get that AdmScreenMst VO -->
		<input type="hidden" name="screenIdForEdit" id="screenIdForEdit">
		
		<center><h2><fmt:message key="EDIT_SCREEN_TITLE_${loginLangId}"  bundle="${appgenLables}"/> </h2></center>
				
		<br><br><br>
		
		<%
		List screenMstList = (List)pageContext.getAttribute("admScreenMstVOList");
		String loginLanguage = (String)pageContext.getAttribute("loginLanguage");
		long loginLangId = (Long) pageContext.getAttribute("loginLangId");
		List tempList = new ArrayList();
		int mstListSize;
				
		AdmScreenMst admScreenMstTemp;
		AdmDtlScreen admDtlScreenTemp = null;
				
		mstListSize = screenMstList.size();
		
		//pageContext.setAttribute("mstScreenVO",admScreenMst);
		
		if(mstListSize != 0)
		{
			pageContext.setAttribute("mstList",screenMstList);
			int mstListIter=0;
			
		%>
		
		<table>		
		<tr align="right">
		<td align="right" width="900">
			<hdiits:button value="Delete" onclick="deleteData()" name="cmdDel" type="button"/>
		</td>
		</tr>
		</table>
		<display:table pagesize="10" name="${mstList}" id="row" requestURI=""  export="true" style="width:100%"
   		decorator="com.tcs.sgv.appgen.decorator.displayTagDecorator">
					
					
					<display:column class="tablecelltext" titleKey="SERIAL_NUMBER_${loginLangId}" headerClass="datatableheader"><c:out value="${i}"/></display:column>	
					<%
						admScreenMstTemp = (AdmScreenMst)screenMstList.get(mstListIter);
						
						//admDtlScreenTemp = (AdmDtlScreen)screenDtlList.get(dtlListIter);
						AdmDtlScreen iteratorVO;

						pageContext.setAttribute("AdmDtlScreenCaption","");
						pageContext.setAttribute("AdmDtlScreenTitle","");

						for(Iterator iter=admScreenMstTemp.getAdmDtlScreens().iterator();iter.hasNext();)
						{
							iteratorVO = (AdmDtlScreen)iter.next();
							if(iteratorVO.getLangId() == loginLangId)
							{
								admDtlScreenTemp = iteratorVO;
								pageContext.setAttribute("AdmDtlScreenCaption",admDtlScreenTemp.getAdmDtlscrCaption());
								pageContext.setAttribute("AdmDtlScreenTitle",admDtlScreenTemp.getAdmDtlscrTitle());								
							}
						}
						
						pageContext.setAttribute("admScreenMstTemp",admScreenMstTemp);
						pageContext.setAttribute("admDtlScreenTemp",admDtlScreenTemp);
					%>
					<display:column class="tablecelltext"  titleKey= "SCREEN_NAME_${loginLangId}" headerClass="datatableheader"><c:out value="${admScreenMstTemp.screenName}"></c:out></display:column>
					<display:column class="tablecelltext"  titleKey= "SCREEN_CAPTION_${loginLangId}" headerClass="datatableheader">
						<c:out value="${AdmDtlScreenCaption}"></c:out>
					</display:column>
					
					<display:column class="tablecelltext"  titleKey= "SCREEN_TITLE_${loginLangId}" headerClass="datatableheader">
						<c:out value="${AdmDtlScreenTitle}"></c:out>
					</display:column>					
	    	        <%
	    	        	mstListIter++;
	    	        %>	    	
	    	        
	    			<c:set var="i" value="${i+1}"></c:set>
   					<display:column property="edit" class="tablecelltext"  titleKey="EDIT_DELETE_ACTIONS_${loginLangId}"  headerClass="datatableheader"></display:column>
   		</display:table>
		
		<div id="myDiv">
		</div>
				
		<table cellspacing="10" cellpadding="0" height="30%" width="100%" >
		</table>
				
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>	
				
		<%
		}
		else
		{
		%><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>			
		&nbsp&nbsp&nbsp&nbsp&nbsp<b><fmt:message key="NO_SCREEN_MESSAGE_${loginLangId}"  bundle="${appgenLables}"/> </b>
		<% 	
		}
		%>
	</div>          
	</hdiits:form>
<%
}
catch(Exception e){
	e.printStackTrace();
}
%> 