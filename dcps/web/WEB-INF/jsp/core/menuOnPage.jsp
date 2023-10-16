<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ResourceBundle"%>
<%@page import="com.tcs.sgv.core.valueobject.ResultObject"%>
<%@page import="com.tcs.sgv.acl.service.UserElements"%>
<%@page import="com.tcs.sgv.acl.service.UserElement"%>
<%@page import="org.apache.commons.logging.Log"%>
<%@page import="org.apache.commons.logging.LogFactory"%>
<%@page import="java.util.ArrayList"%>
<%@ include file="include.jsp"%>

<fmt:setLocale value='<%=session.getAttribute("locale")%>'/>
<c:set var="contextPath" scope="page">${pageContext.request.contextPath}</c:set>
<script type="text/javascript" src='<c:url value="/script/common/menuExpandCollapseUtility.js"/>'></script>
<c:set var="divCntr" scope="page" value="0"></c:set>
<input type="hidden"  name="themename" value="${themename}">

<script type="text/javascript">

function menuCloseOpenBtnClk()
	{		
		var themename = document.getElementById('themename').value;
		var menuCloseOpenBtn = document.getElementById('menuCloseOpenBtn');
		var menuTableObj = document.getElementById('menuOnPageId');
				
		if(menuTableObj.style.display == 'none')
		{			
			document.getElementById('menuTableID').setAttribute("width","200px");
	        menuCloseOpenBtn.src = "themes/"+themename+"/images/win-tool-leftarrow-01.png";	               
	        menuTableObj.style.display = '';
		}else
		{			
			menuCloseOpenBtn.src = "themes/"+themename+"/images/win-tool-rightarrow-01.png";	               
	        menuTableObj.style.display = 'none';	
	        document.getElementById('menuTableID').setAttribute("width","5px");
		}
	}
</script>


<%
	Log logger = LogFactory.getLog(getClass());

	//For preparing contextpath string for ajax call
	ResourceBundle addressBundle = ResourceBundle.getBundle("resources/Constants");
	String projNameStr= addressBundle.getString("PROJECT_NAME");	
	projNameStr = "/"+projNameStr;
	String contextPath = (String)pageContext.getAttribute("contextPath");
	String contextPathStr = contextPath+projNameStr;
	pageContext.setAttribute("contextPathStr",contextPathStr);  	
	
	%>
		<input type="hidden"  name="projectContextPath" value="${contextPathStr}">
	<%
	
	
	
	int noOfColumns = 2;
	if(request.getParameter("numberOfColumns") != null)
	{
		noOfColumns = Integer.parseInt(request.getParameter("numberOfColumns"));
		
	}
	//noOfColumns = 1;
	boolean expandCollapseUtilReq = Boolean.valueOf(request.getParameter("expandCollapseUtilReq")); 
	boolean allExpCollReq = Boolean.valueOf(request.getParameter("allExpCollReq")); 
	allExpCollReq = true;
	pageContext.setAttribute("expandCollapseUtilReq",expandCollapseUtilReq);
	pageContext.setAttribute("allExpCollReq",allExpCollReq);
	int colWidth = 100;
	
	if(request.getParameter("columnWidth") != null)
	{
		if(noOfColumns == 1)
		{
			colWidth = Integer.parseInt(request.getParameter("columnWidth"));	
		}
	}
	
	pageContext.setAttribute("colWidth",colWidth);
	
	String statusStr = "";
	boolean preferencesRequired = false;
	boolean fromLoginPage = true;		  	  	
	
	//For managing user menu preferences
  	List divStatusLt = (List)request.getAttribute("divStatusLt");
  	divStatusLt = null; //changed because of the problem coming with two level user preferences
	if(expandCollapseUtilReq)
	{
		preferencesRequired = Boolean.valueOf(request.getParameter("preferencesRequired")); 
		
		if(divStatusLt == null || divStatusLt.size() == 0)
		{
			statusStr = request.getParameter("initExpandCollpsStatus");			
		}
		else
		{
			fromLoginPage = false;
		}
	}
	
	
	UserElements userElements= (UserElements)request.getAttribute("userElementsForMenuOnPage");		
	logger.debug("User elements object for menu on application page  - "+userElements);
	String menuString = userElements.getMenuString();
	
	List firstLevelModuleIndex = userElements.getRootChildElements();
	List elementList = userElements.getAllElements();
	
	List<UserElement> firstLevelModules = new ArrayList();	
	boolean emptyHomePageMenu = false;
	if(firstLevelModuleIndex == null || firstLevelModuleIndex.size() == 0)
	{
		emptyHomePageMenu = true;
	}	

	pageContext.setAttribute("emptyHomePageMenu",emptyHomePageMenu);
	int totalMainMenus=userElements.getMainMenusCount();
	UserElement userElement= null;	
	logger.debug("Total main menus for menu on page - "+totalMainMenus);
	UserElement tempElement;
	Integer moduleIndex;
	for(int i=0;i<firstLevelModuleIndex.size();i++)
	{
		moduleIndex = (Integer)firstLevelModuleIndex.get(i);
		tempElement = (UserElement)elementList.get(moduleIndex);
		firstLevelModules.add(tempElement);
	}
%>
<table height="100%" id="menuTableID">
	
	<tr>
	    <td valign="top" >
			<table id="menuOnPageId" border="0" cellpadding="2" cellspacing="2" style="background:white;width:${colWidth}%;">
				<tr id="trId">	
					<c:if test="${emptyHomePageMenu eq false}">
						<c:if test="${expandCollapseUtilReq eq true}">
							<c:if test="${allExpCollReq eq true}">
								</br>
								<img src= "themes/${themename}/images/win-tool-expand-01.png"></img>&nbsp<a href="#focusonsamecomponent" onclick="expandOrCollapseAllNodes(this,'expandAll')"><fmt:message key="EXPAND_ALL"/></a>
								<img src= "themes/${themename}/images/win-tool-collapse-01.png">&nbsp
					 		<a href="#focusonsamecomponent" onclick="expandOrCollapseAllNodes(this,'collapseAll')"></img><fmt:message key="COLLAPSE_ALL"/></a>	
				 			</c:if>
				 		</c:if>
				 		</br>
			 			<c:set var="themeName" value="${themename}" />
			 		</c:if>					 		
			 		<%
			 		try{
				 		UserElement moduleElement;
				 		StringBuffer firstColumn = new StringBuffer();			 		
				 		StringBuffer secondColumn= new StringBuffer();
								
				 		StringBuffer clmTablesArray[] = new StringBuffer[noOfColumns];
				 		StringBuffer clmTableDataArray[] = new StringBuffer[noOfColumns];
				 		for(int i=0;i<noOfColumns;i++)
				 		{
				 			clmTablesArray[i] = new StringBuffer();
				 			clmTableDataArray[i] = new StringBuffer();
				 		}	
						
				 		StringBuffer firstClmTableData = new StringBuffer();
				 		StringBuffer secondClmTableData = new StringBuffer();
				 		String themeName = (String)pageContext.getAttribute("themeName");					 		
						
				 		int dsplStatusCntr = 0;
				 		for(int j=0; j<firstLevelModules.size();)
				 		{
				 			for(int i=0;i<noOfColumns ; i++,j++)
				 			{		
						 		StringBuffer tableStarting = new StringBuffer();
								StringBuffer finalUlLiString = new StringBuffer();
						 		StringBuffer tableEnding = new StringBuffer();
								 				
				 				if(j<firstLevelModules.size())
					 			{
					 				moduleElement = (UserElement)firstLevelModules.get(j);
						 			pageContext.setAttribute("index",j+1);
						 			String moduleName = moduleElement.getElementName();
						 			pageContext.setAttribute("firstLevelName",moduleName);
						 			UserElement childElement;
				 					List childElementIndex = userElements.getChildElements(moduleElement.getElementId());
				 					List<UserElement> childElementList = new ArrayList();
							 					
				 					tableStarting.append("<table cellspacing=\"1\" class=\"homepagemenutable\" width=\"100%\">");
				 					tableStarting.append("<thead><tr><td style=\"vertical-align: middle;padding-top:4px; \">");
				 					if(expandCollapseUtilReq)
				 					{	
				 						if("".equalsIgnoreCase(statusStr))
				 						{
				 							tableStarting.append("<a onclick=\"expandOrCollapseNode(this)\" style=\"display:block;width:100%;height:22px;padding-top:3px;padding-left:7px;background:url(themes/"+themeName+"/images/win-tool-collapse-01.png) 95% no-repeat\">");
				 						}
				 						else
				 						{
					 						tableStarting.append("<a onclick=\"expandOrCollapseNode(this)\" style=\"display:block;width:100%;height:22px;padding-top:3px;padding-left:7px\">");
				 						}
				 					}
				 					else
				 					{
				 						tableStarting.append("<a onclick=\"javascript:return\" style=\"display:block;width:100%;height:22px;padding-top:3px;padding-left:7px;background:none\">");
				 					}
				 					
				 					tableStarting.append(moduleName);
				 					//tableStarting.append("<img src= \"themes/"+themeName+"/images/opened.gif\" align=\"right\"></img>");
				 					tableStarting.append("</a>");					 					
								 	
				 					tableStarting.append("</td></tr></thead>");			 					
								 	tableStarting.append("<tbody><tr><td>");					 					
								 	if(!fromLoginPage)
								 	{
								 		if(divStatusLt != null && divStatusLt.size() != 0)
								 		{
								 			statusStr = (String)divStatusLt.get(dsplStatusCntr);	
								 		}					 		
								 	}
								 				
									if(childElementIndex.isEmpty())
									{													
										finalUlLiString.append("<div style=\"display:"+statusStr+";\" id=\"homePageDiv_"+dsplStatusCntr+"\"></br> No Applications Available</div>");											
									}
									else
									{
										Integer elementIndex;
																		
										finalUlLiString.append("<div class=\"mlmenu spvertical themegray arrow inaccesible\" style=\"display:"+statusStr+";\" id=\"homePageDiv_"+dsplStatusCntr+"\">");
										finalUlLiString.append("<ul>");
												 						
										for(int k=0;k< childElementIndex.size();k++)
									 	{
											elementIndex = (Integer)childElementIndex.get(k);
									 		finalUlLiString.append(userElements.getUlLiStringForElement(elementIndex,preferencesRequired));
									 	}										
										finalUlLiString.append("</ul></div>");
									}								
									%>									
									<c:set var="divCntr" scope="page" value="${divCntr+1}"></c:set>	
									<%
										tableEnding.append("</td></tr></tbody>");
										//tableEnding.append("<tfoot><tr><td>&nbsp;</td></tr></tfoot>");
								 		tableEnding.append("</table><br style=\"font-size:2px;\">");
								 		dsplStatusCntr++;
								}
								else
								{							 			
									//tableStarting.append(" ");
									//finalUlLiString.append(" ");
									//tableEnding.append(" ");							 			
								}					 
				 				
				 				clmTableDataArray[i].append(tableStarting);
				 				clmTableDataArray[i].append(finalUlLiString);
				 				clmTableDataArray[i].append(tableEnding);	
							}				 			
						}	
				 		
				 		int width = 100/noOfColumns;
				 		
				 		for(int i=0;i<noOfColumns;i++)
				 		{
				 			clmTablesArray[i].append("<td valign=\"top\" class=\"homepagemaintabletd\" width=\""+width+"%\">");
				 			clmTablesArray[i].append(clmTableDataArray[i]);
				 			clmTablesArray[i].append("</td>");			 		
				 			out.print(clmTablesArray[i].toString());
				 		}
				 		}catch(Exception ex ){
				 			ex.printStackTrace();
				 		}
					%>	
					<input type="hidden" name="divCount" value="${divCntr}">	
				</tr>
			</table>	
		</td>
  	</tr>
</table>		 		