<%
try {
	
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%@ taglib uri   = "http://ajaxtags.org/tags/ajax" prefix= "ajax" %> 

<script type="text/javascript"
	src="<c:url value="/script/common/base64.js"/>">
</script>


<script type="text/javascript" src="/script/common/ajax_saveData.js"> </script>



<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>

<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>


<fmt:setBundle basename="resources.hr.allocation.Allocation" var="AllocLab" scope="request"/>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="YearList" value="${resValue.YearList}"> </c:set>
<c:set var="CategoryList" value="${resValue.CategoryList}"> </c:set>
<c:set var="DesignationList" value="${resValue.DesignationList}"> </c:set>
<c:set var="QualificationsList" value="${resValue.QualificationsList}"> </c:set>
<c:set var="PrefixList" value="${resValue.PrefixList}"> </c:set>
<c:set var="langId" value="${resValue.langId}"> </c:set>
<c:set var="GenderList" value="${resValue.GenderList}"> </c:set>
<c:set var="DistrictList" value="${resValue.DistrictList}"/>
<c:set var="newlist" value="${resValue.newlist}"/>
<c:set var="xmlFilePathNameForMulAdd" value="${resValue.xmlFilePathNameForMulAdd}" />
<c:set var="fileid" value="${resValue.fileId}"/>
<c:set var="hdInvvisitPanchMpgSet" value="${resValue.hdInvvisitpanchMpgSetKey}" />

<script type="text/javascript">
var district='',type='',loc='',eduquali='',spec='',sadv='';

function deleteDBRecord(rowId)
{
resetData();
var delCap = "";
		
			var delRow 	= document.getElementById(rowId);
			var content = delRow.cells[0].innerHTML;
			showAlert(content);
			content = content.replace(".xml_N",".xml_D");
			content = content.replace(".xml_U",".xml_D");
			delRow.cells[0].innerHTML = content;					
			delRow.style.display='none';
	
		document.getElementById('btnAdd').style.display=''; 	    
   	    	document.getElementById('btnUpdate').style.display='none'; 	
			document.getElementById('Update1').style.display='none';
		
}

	
	function getJurisdiction(cmb)
{

			var id=cmb.value;
			
			if(id=='') {return;}					
			if(id=='Select') {
				var z=document.getElementById('sel_jurisdiction');
				for (var i=z.length;i>=0;i--)
   				{	     	   								
					z.remove(z.value);
					z.remove(i);					   				
   				}
   				var y=document.createElement('option');
   				y.text='--Select--';
				y.value='Select';
   				try
				{
					z.add(y,null); 	    						
				}
				catch(ex)
				{	   			 
	 				z.add(y);	   			 				 
				}
				var a=document.getElementById('sel_loc');
				for (var b=a.length;b>=0;b--)
   				{	     	   								
					a.remove(a.value);
					a.remove(b);					   				
   				}
   				var c=document.createElement('option');
   				c.text='--Select--';
				c.value='Select';
   				try
				{
					a.add(c,null); 	    						
				}
				catch(ex)
				{	   			 
	 				a.add(c);	   			 				 
				}
				return;
			}	
			try{   
    			xmlHttp=new XMLHttpRequest();    			   
	    	}
			catch (e)
			{    // Internet Explorer    
					try{
      					xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");      					 
      				}
		    		catch (e){
		          		try
        		  		{
                	           	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");                   	        
        		  		}
				      	catch (e)
				      	{			           	 			           	   	  
			            	  return false;        
			      		}
			 		}			 
        	}        
			var url = "hrms.htm?actionFlag=getjurisdictionall&cmbid="+id;    
			xmlHttp.open("POST", encodeURI(url) , true);			
			xmlHttp.onreadystatechange = processResponsejuris;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));

}
function processResponsejuris()
		{						
			if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{     

						var textId;												
		            	var z=document.getElementById('sel_jurisdiction');			            		            			            	
				    	var xmlStr = xmlHttp.responseText;
				    	var XMLDoc=getDOMFromXML(xmlStr);   // xmlHttp.responseXML.documentElement;						    			    	
				    	var SubQuaStr = XMLDoc.getElementsByTagName('SubJurisdiction');				    	
				    	var SubCoCurrStr_ID = XMLDoc.getElementsByTagName('ID'); 
				    	for (var i=z.length;i>=0;i--)
	     				{	     				     					
							z.remove(z.value);
							z.remove(i);
	     				}	     		     							
						for ( var i = 0 ; i < SubQuaStr.length ; i++ )
	     				{	     		     								
	     				    value=SubQuaStr[i].childNodes[0].text;	     				    
	     				    textId=SubCoCurrStr_ID[i].childNodes[0].text;
	     					var y=document.createElement('option');
		 					y.text=value;
							y.value=textId;

							try
	   						{
	    						z.add(y,null); 	    						
	   						}
	 						catch(ex)
	   						{	   			 
	   			 				z.add(y);	   			 				 
	   						}     						     					
	           			}
	           			var a=document.getElementById('sel_loc');
					for (var b=a.length;b>=0;b--)
   					{	     	   								
					a.remove(a.value);
					a.remove(b);					   				
   					}
   					var c=document.createElement('option');
   					c.text='--Select--';
					c.value='Select';
   					try
					{
					a.add(c,null); 	    						
					}
					catch(ex)
					{	   			 
	 				a.add(c);	   			 				 
					}
	           			if(type!=''){
	           			
	           			document.getElementById('sel_jurisdiction').value=type;type='';
	           			getLocations(document.Allocation.location);}
				}
				else 
				{  			

				}
			}
		}
function getLocations(cmb)
{

var id=cmb.value;

if(id=='') {return;}	
				
			if(id=='select') {
			
				var z=document.getElementById('sel_loc');
				for (var i=z.length;i>=0;i--)
   				{	     	   								
					z.remove(z.value);
					z.remove(i);					   				
   				}
   				var y=document.createElement('option');
   				y.text='--Select--';
				y.value='Select';
   				try
				{
					z.add(y,null); 	    						
				}
				catch(ex)
				{	   			 
	 				z.add(y);	   			 				 
				}
				return;
			}	
			try{   
    			xmlHttp=new XMLHttpRequest();    			   
	    	}
			catch (e)
			{    // Internet Explorer    
					try{
      					xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");      					 
      				}
		    		catch (e){
		          		try
        		  		{
                	           	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");                   	        
        		  		}
				      	catch (e)
				      	{			           	 			           	   	  
			            	  return false;        
			      		}
			 		}
			 					 
        	}        
			var url = "hrms.htm?actionFlag=getlocationdg&cmbid="+id;    
			xmlHttp.open("POST", encodeURI(url) , true);			
			xmlHttp.onreadystatechange = processResponseloc;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));
	
}
function processResponseloc()
		{						
			if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{     
						
						var textId;												
		            	var z=document.getElementById('sel_loc');			            		            			            	
				    	var xmlStr = xmlHttp.responseText;
				    	var XMLDoc=getDOMFromXML(xmlStr);   // xmlHttp.responseXML.documentElement;						    			    	
				    	var SubQuaStr = XMLDoc.getElementsByTagName('SubLevel');				    	
				    	var SubCoCurrStr_ID = XMLDoc.getElementsByTagName('ID'); 
				    	for (var i=z.length;i>=0;i--)
	     				{	     				     					
							z.remove(z.value);
							z.remove(i);
	     				}	     		     							
						for ( var i = 0 ; i < SubQuaStr.length ; i++ )
	     				{	     		     								
	     				    value=SubQuaStr[i].childNodes[0].text;	     				    
	     				    textId=SubCoCurrStr_ID[i].childNodes[0].text;
	     					var y=document.createElement('option');
		 					y.text=value;
							y.value=textId;

							try
	   						{
	    						z.add(y,null); 	    						
	   						}
	 						catch(ex)
	   						{	   			 
	   			 				z.add(y);	   			 				 
	   						}     						     					
	           			}
	           			if(loc!=''){
	           			document.getElementById('sel_loc').value=loc;loc='';}
				}
				else 
				{  			

				}
			}
		}
function getSpecialisation(cmb)
{
		
			var id=cmb.value;				
			if(id=='') {return;}
								
			if(id=='select') {
			
				var z=document.getElementById('sel_specialisation');
				for (var i=z.length;i>=0;i--)
   				{	     	   								
					z.remove(z.value);
					z.remove(i);					   				
   				}
   				var y=document.createElement('option');
   				y.text='--Select--';
				y.value='Select';
   				try
				{
					z.add(y,null); 	    						
				}
				catch(ex)
				{	   			 
	 				z.add(y);	   			 				 
				}
				return;
			}	
			try{   
    			xmlHttp=new XMLHttpRequest();    			   
	    	}
			catch (e)
			{    // Internet Explorer    
					try{
      					xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");      					 
      				}
		    		catch (e){
		          		try
        		  		{
                	           	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");                   	        
        		  		}
				      	catch (e)
				      	{			           	 			           	   	  
			            	  return false;        
			      		}
			 		}			 
        	}        
			var url = "hrms.htm?actionFlag=geteducationdetails&cmbid="+id;    
			xmlHttp.open("POST", encodeURI(url) , true);			
			xmlHttp.onreadystatechange = processSpecialisation;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));
		}			
function processSpecialisation()
		{						
			if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{     

						var textId;												
		            	var z=document.getElementById('sel_specialisation');			            		            			            	
				    	var xmlStr = xmlHttp.responseText;
				    	
				    	var XMLDoc=getDOMFromXML(xmlStr);   // xmlHttp.responseXML.documentElement;						    			    	
				    	var SubQuaStr = XMLDoc.getElementsByTagName('SubQualification');				    	
				    	var SubCoCurrStr_ID = XMLDoc.getElementsByTagName('ID'); 
				    	for (var i=z.length;i>=0;i--)
	     				{	     				     					
							z.remove(z.value);
							z.remove(i);
	     				}	     		     							
						for ( var i = 0 ; i < SubQuaStr.length ; i++ )
	     				{	     		     								
	     				    value=SubQuaStr[i].childNodes[0].text;	     				    
	     				    textId=SubCoCurrStr_ID[i].childNodes[0].text;
	     					var y=document.createElement('option');
		 					y.text=value;
							y.value=textId;

							try
	   						{
	    						z.add(y,null); 	    						
	   						}
	 						catch(ex)
	   						{	   			 
	   			 				z.add(y);	   			 				 
	   						}     						     					
	           			}
	           			
	           			
	           			if(sadv!=''){document.getElementById('sel_specialisation').value=sadv;sadv='';
	           			getJurisdiction(document.Allocation.sel_district);}
				}
				else 
				{  			

				}
			}
		}
function getSubQualifications(cmb)
		{
			
			var id=cmb.value;
						
			if(id=='') {return;}					
			if(id=='Select') {
			var a=document.getElementById('sel_specialisation');
				for (var b=a.length;b>=0;b--)
   				{	     	   								
					a.remove(a.value);
					a.remove(b);					   				
   				}
   				var c=document.createElement('option');
   				c.text='--Select--';
				c.value='Select';
   				try
				{
					a.add(c,null); 	    						
				}
				catch(ex)
				{	   			 
	 				a.add(c);	   			 				 
				}
				var z=document.getElementById('sel_subeduqua');
				for (var i=z.length;i>=0;i--)
   				{	     	   								
					z.remove(z.value);
					z.remove(i);					   				
   				}
   				var y=document.createElement('option');
   				y.text='--Select--';
				y.value='Select';
   				try
				{
					z.add(y,null); 	    						
				}
				catch(ex)
				{	   			 
	 				z.add(y);	   			 				 
				}
				return;
			}	
			try{   
    			xmlHttp=new XMLHttpRequest();    			   
	    	}
			catch (e)
			{    // Internet Explorer    
					try{
      					xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");      					 
      				}
		    		catch (e){
		          		try
        		  		{
                	           	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");                   	        
        		  		}
				      	catch (e)
				      	{			           	 			           	   	  
			            	  return false;        
			      		}
			 		}			 
        	}        
			var url = "hrms.htm?actionFlag=geteducationdetails&cmbid="+id;    
			xmlHttp.open("POST", encodeURI(url) , true);			
			xmlHttp.onreadystatechange = processResponse;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));
		}			
		function processResponse()
		{						
			if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{     

						var textId;												
		            	var z=document.getElementById('sel_subeduqua');			            		            			            	
				    	var xmlStr = xmlHttp.responseText;
				    					    	
				    	var XMLDoc=getDOMFromXML(xmlStr);   // xmlHttp.responseXML.documentElement;						    			    	
				    	var SubQuaStr = XMLDoc.getElementsByTagName('SubQualification');				    	
				    	var SubCoCurrStr_ID = XMLDoc.getElementsByTagName('ID'); 
				    	for (var i=z.length;i>=0;i--)
	     				{	     				     					
							z.remove(z.value);
							z.remove(i);
	     				}	     		     							
						for ( var i = 0 ; i < SubQuaStr.length ; i++ )
	     				{	     		     								
	     				    value=SubQuaStr[i].childNodes[0].text;	     				    
	     				    textId=SubCoCurrStr_ID[i].childNodes[0].text;
	     					var y=document.createElement('option');
		 					y.text=value;
							y.value=textId;

							try
	   						{
	    						z.add(y,null); 	    						
	   						}
	 						catch(ex)
	   						{	   			 
	   			 				z.add(y);	   			 				 
	   						}     						     					
	           			}
	           		var a=document.getElementById('sel_specialisation');
					for (var b=a.length;b>=0;b--)
   					{	     	   								
					a.remove(a.value);
					a.remove(b);					   				
   					}
   					var c=document.createElement('option');
   					c.text='--Select--';
					c.value='Select';
   					try
					{
					a.add(c,null); 	    						
					}
					catch(ex)
					{	   			 
	 				a.add(c);	   			 				 
					}
	           			
	           			if(spec!=''){document.getElementById('sel_subeduqua').value=spec;spec='';
	           									
							getSpecialisation(document.Allocation.specialisation);
	           			}
				}
				else 
				{  			

				}
			}
		}
	
		function  charvalidate(element)
		{
		
		     var iChars = "`~!@#$%^&*()_-+={}[]|\:;<>,-.?/'"+"\"";
         	  var fpnum =/^\d*$/g;
         	   den=element.split('');
         	   
         	   spec1=iChars.split('');
         	   
         	   
         	  for(i=0;i<element.length;i++)
         	  {
         	 if(fpnum.test(den[i]) )
         	 {
         	     return true;
         	     }
         	     else
         	     {
         	     for(j=0;j<iChars.length;j++)
         	     {
                      if(den[i]==spec1[j] || den[i]==' '  )
                      {
                      return true;
                      }    	     
         	     }
         	     }
         	  } 
         	 
         	 
         	  	  
         	  
		}
		
		function updateandvalidate()
		{
		
		var bithdate=document.Allocation.datetime_dob.value;
		var splitDate=bithdate.split("/");
	
		var yrrec=document.Allocation.sel_yrofrecruit.value;
	var diff=eval(yrrec)-eval(splitDate[2]);
		if(document.Allocation.sel_empprefix.selectedIndex==0)
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.enterempprefix"/>');
		}
		else if(charvalidate(document.Allocation.txt_fstname.value) || document.Allocation.txt_fstname.value.length==0 )
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.enterfirstnameeng"/>');
		}
		else if( charvalidate(document.Allocation.txt_mdlname.value))
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.entermdlnameeng"/>');
		}
		else if(document.Allocation.txt_lstname.value.length==0 || charvalidate(document.Allocation.txt_lstname.value))
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.enterlstnameeng"/>');
		}
		
		else if(document.Allocation.sel_category.selectedIndex==0)
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.selectappcategory"/>');
		}
		else if(document.Allocation.sel_gender.selectedIndex==0)
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.selectgender"/>');
		}
		else if(document.Allocation.datetime_dob.value.length==0)
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.selectdob"/>');
		}
		else if(document.Allocation.sel_yrofrecruit.selectedIndex==0)
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.selectyrrecruit"/>');
		}
		else if(document.Allocation.sel_post.selectedIndex==0)
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.selectpost"/>');
		}
		else if(document.Allocation.sel_subeduqua.selectedIndex==0)
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.selecteduqua"/>');
		}
		else if(document.Allocation.sel_district.selectedIndex==0)
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.selectdistrict"/>');
		}
		else if(document.Allocation.sel_jurisdiction.selectedIndex==0)
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.selectjurisdiction"/>');
		}
		
		else if(document.Allocation.sel_loc.selectedIndex==0)
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.selectlocation"/>');
		}
		else if(diff<18 )
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.difference"/>');
		}
		else
		{
		addOrUpdateRecord('updateRecord','adddataallocation', new Array('sel_empprefix','txt_fstname', 'txt_mdlname','txt_lstname','sel_category','sel_gender','datetime_dob','sel_yrofrecruit','sel_post','sel_eduqua','sel_subeduqua','sel_specialisation','sel_district','sel_jurisdiction','sel_loc'));
		}
		}
		
		function updateRecord() {
	  if (xmlHttp.readyState == 4) { 	
		var langid=document.Allocation.langId.value;
		
		 var name=document.Allocation.sel_empprefix.options[document.Allocation.sel_empprefix.selectedIndex].innerHTML+"  "+document.Allocation.txt_fstname.value+"  "+document.Allocation.txt_mdlname.value+"  "+document.Allocation.txt_lstname.value;
		document.Allocation.name12.value=name;
		
		SubCombo=document.getElementById('sel_subeduqua');
	 var subeduqual=SubCombo.options[SubCombo.selectedIndex].text;
		SpecialisationCombo=document.getElementById('sel_specialisation');
	var specialisation=SpecialisationCombo.options[SpecialisationCombo.selectedIndex].text;
	var educa=subeduqual;
	
	document.getElementById('educationalqual').value=educa;
		
			var displayFieldArray = new Array("name12","sel_category","sel_post","datetime_dob","educationalqual","sel_yrofrecruit","sel_district","sel_loc");

		 updateDataInTable('encXML', displayFieldArray);		
	
		resetData();

		document.getElementById('btnAdd').style.display='';
		document.getElementById('btnUpdate').style.display='none';  
		document.getElementById('Update1').style.display='none';    	  }
	}
		function updatedbrecord()
		{

		var bithdate=document.Allocation.datetime_dob.value;
		var splitDate=bithdate.split("/");
	
		var yrrec=document.Allocation.sel_yrofrecruit.value;
	var diff=eval(yrrec)-eval(splitDate[2]);

		if(document.Allocation.sel_empprefix.selectedIndex==0)
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.enterempprefix"/>');
		}
		else if(charvalidate(document.Allocation.txt_fstname.value) || document.Allocation.txt_fstname.value.length==0 )
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.enterfirstnameeng"/>');
		}
		else if( charvalidate(document.Allocation.txt_mdlname.value))
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.entermdlnameeng"/>');
		}
		else if(document.Allocation.txt_lstname.value.length==0 || charvalidate(document.Allocation.txt_lstname.value))
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.enterlstnameeng"/>');
		}
		
		else if(document.Allocation.sel_category.selectedIndex==0)
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.selectappcategory"/>');
		}
		else if(document.Allocation.sel_gender.selectedIndex==0)
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.selectgender"/>');
		}
		else if(document.Allocation.datetime_dob.value.length==0)
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.selectdob"/>');
		}
		else if(document.Allocation.sel_yrofrecruit.selectedIndex==0)
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.selectyrrecruit"/>');
		}
		else if(document.Allocation.sel_post.selectedIndex==0)
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.selectpost"/>');
		}
		else if(document.Allocation.sel_subeduqua.selectedIndex==0)
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.selecteduqua"/>');
		}
		else if(document.Allocation.sel_district.selectedIndex==0)
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.selectdistrict"/>');
		}
		else if(document.Allocation.sel_jurisdiction.selectedIndex==0)
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.selectjurisdiction"/>');
		}
		
		else if(document.Allocation.sel_loc.selectedIndex==0)
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.selectlocation"/>');
		}
		else if(diff<18 )
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.difference"/>');
		}
		else
		{
			addOrUpdateRecord('updateDBARecord','updatedballocation', new Array('sel_empprefix','txt_fstname', 'txt_mdlname','txt_lstname','sel_category','sel_gender','datetime_dob','sel_yrofrecruit','sel_post','sel_eduqua','sel_subeduqua','sel_specialisation','sel_district','sel_jurisdiction','sel_loc','serialno'));
		}
	}
		function updateDBARecord() {
	  if (xmlHttp.readyState == 4) { 	
	
		var langid=document.Allocation.langId.value;
		
		 var name=document.Allocation.sel_empprefix.options[document.Allocation.sel_empprefix.selectedIndex].innerHTML+"  "+document.Allocation.txt_fstname.value+"  "+document.Allocation.txt_mdlname.value+"  "+document.Allocation.txt_lstname.value;
		document.Allocation.name12.value=name;
		
		SubCombo=document.getElementById('sel_subeduqua');
	 var subeduqual=SubCombo.options[SubCombo.selectedIndex].text;
		SpecialisationCombo=document.getElementById('sel_specialisation');
	var specialisation=SpecialisationCombo.options[SpecialisationCombo.selectedIndex].text;
	var educa=subeduqual;
	
	document.getElementById('educationalqual').value=educa;
		
			var displayFieldArray = new Array("name12","sel_category","sel_post","datetime_dob","educationalqual","sel_yrofrecruit","sel_district","sel_loc");

		 updateDataInTable('addedPunch',displayFieldArray);		
	
		resetData();

		document.getElementById('btnAdd').style.display='';
		document.getElementById('Update1').style.display='none'; 
		document.getElementById('btnUpdate').style.display='none';    	  }
	}
	
		function validation()
		{ 
		
		var bithdate=document.Allocation.datetime_dob.value;
		var splitDate=bithdate.split("/");
	
		var yrrec=document.Allocation.sel_yrofrecruit.value;
	var diff=eval(yrrec)-eval(splitDate[2]);
		if(document.Allocation.sel_empprefix.selectedIndex==0)
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.enterempprefix"/>');
		}
		else if(charvalidate(document.Allocation.txt_fstname.value) || document.Allocation.txt_fstname.value.length==0 )
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.enterfirstnameeng"/>');
		}
		else if( charvalidate(document.Allocation.txt_mdlname.value))
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.entermdlnameeng"/>');
		}
		else if(document.Allocation.txt_lstname.value.length==0 || charvalidate(document.Allocation.txt_lstname.value))
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.enterlstnameeng"/>');
		}
		
		else if(document.Allocation.sel_category.selectedIndex==0)
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.selectappcategory"/>');
		}
		else if(document.Allocation.sel_gender.selectedIndex==0)
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.selectgender"/>');
		}
		else if(document.Allocation.datetime_dob.value.length==0)
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.selectdob"/>');
		}
		else if(document.Allocation.sel_yrofrecruit.selectedIndex==0)
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.selectyrrecruit"/>');
		}
		else if(document.Allocation.sel_post.selectedIndex==0)
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.selectpost"/>');
		}
		else if(document.Allocation.sel_subeduqua.selectedIndex==0)
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.selecteduqua"/>');
		}
		else if(document.Allocation.sel_district.selectedIndex==0)
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.selectdistrict"/>');
		}
		else if(document.Allocation.sel_jurisdiction.selectedIndex==0)
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.selectjurisdiction"/>');
		}
		else if(document.Allocation.sel_loc.selectedIndex==0)
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.selectlocation"/>');
		}
		else if(diff<18 )
		{
		alert('<fmt:message bundle="${AllocLab}" key="Allocation.difference"/>');
		}
		else
		{
		
		addOrUpdateRecord('addRecord','adddataallocation', new Array('sel_empprefix','txt_fstname','txt_mdlname','txt_lstname','sel_category','sel_gender','datetime_dob','sel_yrofrecruit','sel_post','sel_eduqua','sel_subeduqua','sel_specialisation','sel_district','sel_jurisdiction','sel_loc' ));
		
		}
		}

function addRecord() {
	  if (xmlHttp.readyState == 4)
	  { 
	  
	
	
	var langid=document.Allocation.langId.value;
	
	 var name=document.Allocation.sel_empprefix.options[document.Allocation.sel_empprefix.selectedIndex].innerHTML+"  "+document.Allocation.txt_fstname.value+"  "+document.Allocation.txt_mdlname.value+"  "+document.Allocation.txt_lstname.value;
	document.Allocation.name12.value=name;
	
	SubCombo=document.getElementById('sel_subeduqua');
	 var subeduqual=SubCombo.options[SubCombo.selectedIndex].text;
		SpecialisationCombo=document.getElementById('sel_specialisation');
	var specialisation=SpecialisationCombo.options[SpecialisationCombo.selectedIndex].text;
	var educa=subeduqual;
	
	document.getElementById('educationalqual').value=educa;
var displayFieldArray = new Array("name12","sel_category","sel_post","datetime_dob","educationalqual","sel_yrofrecruit","sel_district","sel_loc");


addDataInTable('txnAdd','encXML', displayFieldArray,'editRecord','deleterecord');				
   	    resetData(); 
   	   
   	     			
	   }	
	}
	
function resetData() 
{	
		document.Allocation.sel_empprefix.selectedIndex=0;
		document.Allocation.txt_fstname.value='';
		document.Allocation.txt_mdlname.value='';
		document.Allocation.txt_lstname.value='';
		document.Allocation.sel_gender.selectedIndex=0;
		document.Allocation.sel_yrofrecruit.selectedIndex=0;
		document.Allocation.datetime_dob.value='';
		document.Allocation.age.value='';
		document.Allocation.sel_category.selectedIndex=0;
		document.Allocation.sel_post.selectedIndex=0;
		document.Allocation.sel_eduqua.value='Select';
		document.Allocation.sel_district.value='Select';
		getSubQualifications(document.Allocation.sel_eduqua);
		getJurisdiction(document.Allocation.sel_district);
		
		
		
   	}		
function editRecord(rowId) {
	updateRow=null;


		sendAjaxRequestForEdit(rowId,'populateForm');
	}
	
	
			
function populateForm() {
	  if (xmlHttp.readyState == 4) { 	
	
	  	var decXML = xmlHttp.responseText;
	  	
		var xmlDOM = getDOMFromXML(decXML);
		document.Allocation.sel_empprefix.value=getXPathValueFromDOM(xmlDOM,'salutation'); 
			document.Allocation.txt_fstname.value=getXPathValueFromDOM(xmlDOM,'empFirstName');
			if(getXPathValueFromDOM(xmlDOM,'empMiddleName')!=null)
			{
			document.Allocation.txt_mdlname.value=getXPathValueFromDOM(xmlDOM,'empMiddleName');
			}
			document.Allocation.txt_lstname.value=getXPathValueFromDOM(xmlDOM,'empLastName');
			document.Allocation.sel_gender.value=getXPathValueFromDOM(xmlDOM,'gender');
			document.Allocation.sel_yrofrecruit.value=getXPathValueFromDOM(xmlDOM,'yearrecruit');
			eduquali=getXPathValueFromDOM(xmlDOM,'educqualification');
			spec=getXPathValueFromDOM(xmlDOM,'subEducqualification');
			
			document.Allocation.specialisation.value=spec;
			sadv=getXPathValueFromDOM(xmlDOM,'specialisation');
		var date1=getXPathValueFromDOM(xmlDOM,'dob'); 
			dateArray=date1.split(' ');
			
			dateArr=dateArray[0].split('-');
var formattedDate= dateArr[2]+'/'+dateArr[1]+'/'+dateArr[0] ;

			document.Allocation.datetime_dob.value=formattedDate;		
			countAge(document.Allocation.datetime_dob.value);
			document.Allocation.sel_category.value=getXPathValueFromDOM(xmlDOM,'categoryId');
			document.Allocation.sel_post.value=getXPathValueFromDOM(xmlDOM,'designation');
			document.Allocation.sel_district.value=getXPathValueFromDOM(xmlDOM,'districtId');
			type=getXPathValueFromDOM(xmlDOM,'jurisdiction');
			
			loc=getXPathValueFromDOM(xmlDOM,'jurisdictionId');
       
		 
		 document.Allocation.subeducationalqual.value=eduquali;
			document.Allocation.location.value=type;
			getSubQualifications(document.Allocation.subeducationalqual);
			
			
			
			
			    
			document.getElementById('btnAdd').style.display='none'; 	    
   	    	document.getElementById('btnUpdate').style.display=''; 	
   	      document.getElementById('Update1').style.display='none'; 
   	    	document.Allocation.sel_eduqua.value=eduquali;
			
			  
	   }
	}
function deleterecord(rowId)
{
			resetData();
			trow=document.getElementById(rowId);
			trow.parentNode.deleteRow(trow.rowIndex);
				document.getElementById('btnAdd').style.display=''; 	    
   	    	document.getElementById('btnUpdate').style.display='none'; 	
			document.getElementById('Update1').style.display='none';
			
}	
function editDBRecord(rowId)
{

updateRow=null;
		sendAjaxRequestForEdit(rowId,'populateFormDB');
}
function populateFormDB()
{  
if (xmlHttp.readyState == 4)
{	

	var decXML = xmlHttp.responseText;
	  	
		var xmlDOM = getDOMFromXML(decXML);
		
		document.Allocation.serialno.value=getXPathValueFromDOM(xmlDOM,'serialNo');
		document.Allocation.sel_empprefix.value=getXPathValueFromDOM(xmlDOM,'salutation');
		
			document.Allocation.txt_fstname.value=getXPathValueFromDOM(xmlDOM,'empFirstName');
			if(getXPathValueFromDOM(xmlDOM,'empMiddleName')!=null)
			{
			document.Allocation.txt_mdlname.value=getXPathValueFromDOM(xmlDOM,'empMiddleName');
			}
			document.Allocation.txt_lstname.value=getXPathValueFromDOM(xmlDOM,'empLastName');
			document.Allocation.sel_gender.value=getXPathValueFromDOM(xmlDOM,'genderId');
			document.Allocation.sel_yrofrecruit.value=getXPathValueFromDOM(xmlDOM,'yearrecruitId');
			
			document.Allocation.sel_category.value=getXPathValueFromDOM(xmlDOM,'categoryId');
			eduquali=getXPathValueFromDOM(xmlDOM,'educqualificationId');
			
			spec=getXPathValueFromDOM(xmlDOM,'subEducqualificationId');
			
			document.Allocation.specialisation.value=spec;
			
			sadv=getXPathValueFromDOM(xmlDOM,'specialisationId');
			
		var date1=getXPathValueFromDOM(xmlDOM,'dob'); 
			dateArray=date1.split(' ');
			
			dateArr=dateArray[0].split('-');
var formattedDate= dateArr[2]+'/'+dateArr[1]+'/'+dateArr[0] ;

			document.Allocation.datetime_dob.value=formattedDate;		
			countAge(document.Allocation.datetime_dob.value);
			
			document.Allocation.sel_post.value=getXPathValueFromDOM(xmlDOM,'designationId');
			document.Allocation.sel_district.value=getXPathValueFromDOM(xmlDOM,'districtId');
			type=getXPathValueFromDOM(xmlDOM,'comboString');
			loc=getXPathValueFromDOM(xmlDOM,'locationId');
		
			document.Allocation.subeducationalqual.value=eduquali;
			
			getSubQualifications(document.Allocation.subeducationalqual);
				
				
   	      
   	    	document.Allocation.sel_eduqua.value=eduquali;
   	    	document.Allocation.location.value=type;
			document.getElementById('btnAdd').style.display='none'; 
			document.getElementById('btnUpdate').style.display='none'; 	    
   	    	document.getElementById('Update1').style.display=''; 
			
			
			
			
			
         
}
}


function countAge(birthDate)
{	
								
			var splitDate=birthDate.split("/");							
			var bday=parseInt(splitDate[0]);
			var bmo=(parseInt(splitDate[1])-1);
			var byr=parseInt(splitDate[2]);
			var age;
			var now = new Date();		
			
			tday=now.getUTCDate();
			tmo=(now.getUTCMonth());
			tyr=(now.getUTCFullYear());
			if((tmo > bmo)||(tmo==bmo & tday>=bday)) {age=byr}				
			else  {age=byr+1}
			if(isNaN(tyr-age)==true){}
			else if((tyr-age)>150 || (tyr-age)<=-1)			
			{				
				document.Allocation.age.value='';
				document.Allocation.datetime_dob.value='';
				alert('<fmt:message bundle="${AllocLab}" key="Allocation.validdob"/>');
			}
			else if((tyr-age)<18)
			{
			document.Allocation.age.value='';
			document.Allocation.datetime_dob.value='';
			alert('<fmt:message bundle="${AllocLab}" key="Allocation.above18"/>');
			}
			else {	
			document.Allocation.age.value=tyr-age;
				}
		}

	
</script>





<hdiits:form name="Allocation" validate="true" method="POST"  action="./hrms.htm?actionFlag=allocation" encType="text/form-data" >

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>
		<hdiits:caption captionid="Recruitment.details" bundle="${AllocLab}" captionLang="single"></hdiits:caption></b></a></li>
		
	</ul>
	</div>
	
	<div class="tabcontentstyle" style="height: 100%">
	
	
	<div id="tcontent1" class="tabcontent" tabno="0">	
	

   
	<hdiits:fieldGroup titleCaptionId="Allocation.listofemp" bundle="${AllocLab}" expandable="true" mandatory="true" > 
	
	<table width="100%">
	<tr>
	<td width="25%"><hdiits:caption captionid="Allocation.empprefix" bundle="${AllocLab}"/></td>
	<td width="25%"><hdiits:select name="sel_empprefix" default="" mandatory="true" id="sel_empprefix"><hdiits:option value="" ><fmt:message key="Allocation.select" bundle="${AllocLab}"/></hdiits:option>
	<c:forEach var="prefix" items="${PrefixList}">
	 <option value=<c:out value="${prefix.lookupId}"/>>
	<c:out value="${prefix.lookupDesc}"/></option>
	</c:forEach>
	</hdiits:select></td>
	</tr>
	<tr>
	<td width="25%"> </td><td width="25%"><hdiits:caption captionid="Allocation.fstname" bundle="${AllocLab}"/></td><td width="25%"><hdiits:caption captionid="Allocation.mdlname" bundle="${AllocLab}"/></td><td width="25%"><hdiits:caption captionid="Allocation.lstname" bundle="${AllocLab}"/></td>
	</tr>
	<tr>
	<td width="25%"><hdiits:caption captionid="Allocation.namecandidate" bundle="${AllocLab}"/><hdiits:caption captionid="Allocation.ineng" bundle="${AllocLab}"/></td><td width="25%"><hdiits:text name="txt_fstname" maxlength="25"  mandatory="true"/> </td><td width="25%"><hdiits:text name="txt_mdlname" maxlength="25" /> </td><td width="25%"><hdiits:text name="txt_lstname" maxlength="25" mandatory="true"/> </td>
	</tr>
	
	<tr>
	<hdiits:hidden name="name12" default="" id="name12"/>
	<hdiits:hidden name="langId" default="${langId}"/>
	<hdiits:hidden name="educationalqual" default="" id="educationalqual"/>
	<hdiits:hidden name="subeducationalqual" default="" id="subeducationalqual"/>
	<hdiits:hidden name="specialisation" default="" id="specialisation"/>
	<hdiits:hidden name="location" default="" id="location"/>
	<hdiits:hidden name="serialno" id="serialno" default=""/>
	<hdiits:hidden name="wffileId_hidden" id="wffileId_hidden" />
	<hdiits:hidden name="fileId" id="fileId" default="${fileid}"/>
	<td width="25%"><hdiits:caption captionid="Allocation.category" bundle="${AllocLab}"/></td><td width="25%"><hdiits:select name="sel_category" mandatory="true" ><hdiits:option value="" ><fmt:message key="Allocation.select" bundle="${AllocLab}"/></hdiits:option>
	<c:forEach var="Category" items="${CategoryList}">
	 <option value=<c:out value="${Category.lookupId}"/>>
	<c:out value="${Category.lookupDesc}"/></option>
	</c:forEach>	
	</hdiits:select>	</td>
	<td width="25%"><hdiits:caption captionid="Allocation.gender" bundle="${AllocLab}"/></td>
	<td width="25%"><hdiits:select name="sel_gender" default="" mandatory="true"><hdiits:option value="" ><fmt:message key="Allocation.select" bundle="${AllocLab}"/></hdiits:option>
	<c:forEach var="Gender" items="${GenderList}">
	 <option value=<c:out value="${Gender.lookupName}"/>>
	<c:out value="${Gender.lookupDesc}"/></option>
	</c:forEach>
	</hdiits:select>
	 </td>
	</tr>
	
	<tr>
	
	<td width="25%"><hdiits:caption captionid="Allocation.dob" bundle="${AllocLab}"/></td>
	<td width="25%"><hdiits:dateTime name="datetime_dob" captionid="Allocation.dob" bundle="${AllocLab}" default=""  onblur="countAge(this.value)" mandatory="true" maxvalue="31/12/2099" /></td>
	<td width="25%"><hdiits:caption captionid="Allocation.age" bundle="${AllocLab}"/></td>
	<td width="25%"><hdiits:age name="age" id="age" captionid="Allocation.age" bundle="${AllocLab}" readonly="true" default=""/></td>
	
	
	
	
	</tr>
	
	<tr>
	<td width="25%"><hdiits:caption captionid="Allocation.post" bundle="${AllocLab}"/></td><td width="25%"><hdiits:select name="sel_post" mandatory="true"><hdiits:option value="" ><fmt:message key="Allocation.select" bundle="${AllocLab}"/></hdiits:option>
	<c:forEach var="Designation" items="${DesignationList}">
	 <option value=<c:out value="${Designation.dsgnId}"/>>
	<c:out value="${Designation.dsgnName}"/></option>
	</c:forEach>
	</hdiits:select>	</td>
	<td width="25%"><hdiits:caption captionid="Allocation.yrrecruit" bundle="${AllocLab}"/></td>
	<td width="25%"><hdiits:select name="sel_yrofrecruit" default="" mandatory="true"><hdiits:option value="" ><fmt:message key="Allocation.select" bundle="${AllocLab}"/></hdiits:option>
	<c:forEach var="Year" items="${YearList}">
	 <option value=<c:out value="${Year.lookupName}"/>>
	<c:out value="${Year.lookupDesc}"/></option>
	</c:forEach>
	</hdiits:select></td>
	</tr>
	
	<tr>
	
	<td width="25%"><hdiits:caption captionid="Allocation.eduqual" bundle="${AllocLab}"/></td>
	<td width="25%"><hdiits:select name="sel_eduqua" mandatory="true" default="" onchange="getSubQualifications(this);" sort="false"><hdiits:option value="Select" ><fmt:message key="Allocation.select" bundle="${AllocLab}"/></hdiits:option>
	<c:forEach var="Qualifications" items="${QualificationsList}">
	 <option value=<c:out value="${Qualifications.lookupName}"/>>
	<c:out value="${Qualifications.lookupDesc}"/></option>
	</c:forEach>	
	</hdiits:select></td>
	<td width="25%"><hdiits:caption captionid="Allocation.subeduqual" bundle="${AllocLab}"/></td>
	<td width="25%"><hdiits:select name="sel_subeduqua" default="" id="sel_subeduqua" onchange="getSpecialisation(this);" mandatory="true"><hdiits:option value="Select" ><fmt:message key="Allocation.select" bundle="${AllocLab}"/></hdiits:option>
		
	</hdiits:select></td>
	</tr>
	<tr>
	<td width="25%"><hdiits:caption captionid="Allocation.specialisation" bundle="${AllocLab}"/></td>
	<td width="25%"><hdiits:select name="sel_specialisation" default="" id="sel_specialisation" ><hdiits:option value="Select" ><fmt:message key="Allocation.select" bundle="${AllocLab}"/></hdiits:option>
		
	</hdiits:select></td>
	</tr>
	<tr>
	<td width="25%"><b><hdiits:caption captionid="Allocation.district" bundle="${AllocLab}"/></b></td>
	<td width="25%"><hdiits:select name="sel_district"  onchange="getJurisdiction(this);" sort="false" mandatory="true"><hdiits:option value="Select" ><fmt:message key="Allocation.select" bundle="${AllocLab}"/></hdiits:option>
	
	<c:forEach var="DistrictList" items="${DistrictList}">
	 <option value=<c:out value="${DistrictList.districtId}"/>>
	<c:out value="${DistrictList.districtName}"/></option>
	</c:forEach>	
	</hdiits:select></td>
	<td width="25%"><b><hdiits:caption captionid="Allocation.type" bundle="${AllocLab}"/></b></td>
	<td width="25%"><hdiits:select name="sel_jurisdiction"  id="sel_jurisdiction" onchange="getLocations(this);" mandatory="true"><hdiits:option value="Select" ><fmt:message key="Allocation.select" bundle="${AllocLab}"/></hdiits:option>
		
	</hdiits:select></td>
	</tr>
	<tr>
	<td width="25%"><b><hdiits:caption captionid="Allocation.locations" bundle="${AllocLab}"/></b></td>
	<td width="25%"><hdiits:select name="sel_loc"  id="sel_loc" mandatory="true"><hdiits:option value="Select" ><fmt:message key="Allocation.select" bundle="${AllocLab}"/></hdiits:option>
	</hdiits:select></td>
	</tr>
	</table>
	<table width="90%"><tr>
	<td width="50%"></td><td><hdiits:button id="btnAdd" name="btnAdd" type="button" onclick="javascript:validation()" captionid="Allocation.Add" bundle="${AllocLab}"/>
	<hdiits:button id="btnUpdate" name="btnUpdate" type="button"  captionid="Allocation.Update" bundle="${AllocLab}" style="display:none" onclick="updateandvalidate()"/>
	<hdiits:button name="Update1" id="Update1" type="button"  captionid="Allocation.Update" bundle="${AllocLab}" style="display:none" onclick="updatedbrecord()"/></td>
	</tr>	
	</table>
	
	
	<table name="txnAdd" id="txnAdd"   border="1" align="center"    cellpadding="0" width="100%" cellspacing="0" BGCOLOR="WHITE"  style="display:none">
	<tr class="datatableheader">
	<td  align="center" ><b><hdiits:caption captionid="Allocation.name" bundle="${AllocLab}"/></b></td>
	
	<td align="center"><b><hdiits:caption captionid="Allocation.category" bundle="${AllocLab}"/></b></td>
	<td align="center"><b><hdiits:caption captionid="Allocation.post" bundle="${AllocLab}"/></b></td>
	<td align="center"><b><hdiits:caption captionid="Allocation.dob" bundle="${AllocLab}"/></b></td>
	<td align="center"><b><hdiits:caption captionid="Allocation.eduqual" bundle="${AllocLab}"/></b></td>
	<td align="center"><b><hdiits:caption captionid="Allocation.yrrecruit" bundle="${AllocLab}"/></b></td>
	<td align="center"><b><hdiits:caption captionid="Allocation.district" bundle="${AllocLab}"/></b></td>
	<td align="center"><b><hdiits:caption captionid="Allocation.locations" bundle="${AllocLab}"/></b></td>
	<td align="center" ></td>
	</tr> 
	
	</table>
	</hdiits:fieldGroup>
	<c:if test="${not empty hdInvvisitPanchMpgSet}">
	<c:forEach items="${hdInvvisitPanchMpgSet}" var="hdInvvisitPanchMpgTuples" varStatus="x">

<c:set var="curXMLFileName" value="${xmlFilePathNameForMulAdd[x.index]}" ></c:set>


<c:set var="name123" value="${hdInvvisitPanchMpgTuples.salutationdesc} ${hdInvvisitPanchMpgTuples.empFirstName} ${hdInvvisitPanchMpgTuples.empMiddleName} ${hdInvvisitPanchMpgTuples.empLastName}"/>

<c:set var="category" value="${hdInvvisitPanchMpgTuples.category}"/>
<c:set var="post" value="${hdInvvisitPanchMpgTuples.designation}"/>
<fmt:formatDate var="dob" value="${hdInvvisitPanchMpgTuples.dob}" pattern="dd/MM/yyyy"/>

<c:set var="eduqualification" value="${hdInvvisitPanchMpgTuples.subEducqualification}"/>
<c:set var="yrrecruit" value="${hdInvvisitPanchMpgTuples.yearrecruit}"/>
<c:set var="districtname" value="${hdInvvisitPanchMpgTuples.district}"/>
<c:set var="locationjuris" value="${hdInvvisitPanchMpgTuples.location}"/>


<script type="text/javascript">
var xmlFileName = '${curXMLFileName}';
var displayFieldA  = new Array('${name123}','${category}','${post}','${dob}','${eduqualification}','${yrrecruit}','${districtname}','${locationjuris}');

addDBDataInTable('txnAdd','addedPunch',displayFieldA,xmlFileName,'editDBRecord','deleteDBRecord','');
</script>
</c:forEach>
	
	</c:if>
	<script type="text/javascript">
	 document.Allocation.datetime_dob.readOnly=true;
</script>
	</div>	

		
	
	
 	</div>
	<script type="text/javascript">

		initializetabcontent("maintab")
		</script>	

	<hdiits:validate controlNames="text" locale='<%=(String)session.getAttribute("locale")%>' />

</hdiits:form>
<%
} catch (Exception e) {
		e.printStackTrace();
	}
%>
