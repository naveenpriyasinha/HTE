var mycars=new Array();

	var arrlength;
	
		
		



  function OnChange(obj,ctry)
  {
 
  		if(ctry == 'department')
  		{
   		
  			document.searchEmployee.action="./hdiits.htm?actionFlag=upList&userId=-1";
			document.searchEmployee.submit();
  		
  		}
  		
 		if(ctry == 'location')
  		{
   		
  			document.searchEmployee.action="./hdiits.htm?actionFlag=upList&userId=-1";
			document.searchEmployee.submit();
  		
  		}
 		if(ctry == 'post')
  		{
  		
	  		document.searchEmployee.action="./hdiits.htm?actionFlag=upList&userId=-1";
			document.searchEmployee.submit();
  		}
  }
  function searchGo()
  {
  			document.searchEmployee.action="./hdiits.htm?actionFlag=searchEmp&userId=-1";
			document.searchEmployee.submit();
  }  
  
  

var emparray = new Array();
var empcount = 0 ;
var finalEmpArr = new Array();


	
	
	
	
	function windowclose()
	{
		
		parseStr();
		window.close(); 
	}  
  function parseStr()
  {
  	
  	var newElem;
  	newElem = document.createElement("option");
  	newElem.value = document.searchEmployee.newemplist.options[1].value;
  	var str=newElem.value;
  	var arrStr=new Array();
  	arrStr=str.split(',');

	
  }

function highlight(which,color){
if (document.all||document.getElementById)
which.style.backgroundColor=color
}





function addRecord()
{
	if (xmlHttp.readyState == 4) 
	{
		var encXML=xmlHttp.responseText;
		if(encXML=="error")
		{
			
		}
		else
		{
			
		}
		
	}
}

function addOrUpdateRecordForEmpSearch(methodName, actionFlag, fieldArray) 
	{	
		
		xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null) {
		  alert ("Your browser does not support AJAX!");
		  return;
		} 
		showProgressbar("Please Wait...");
		var reqBody = getRequestBody(fieldArray);	
		var url='hdiits.htm?actionFlag=' + actionFlag + '&' + reqBody;
		methodName = methodName + "()";
		
		xmlHttp.onreadystatechange=function() {
			if(xmlHttp.readyState == 4) {
				eval(methodName);
				hideProgressbar();
			}	
		}
	
		xmlHttp.open("POST",encodeURI(url),false);
		xmlHttp.send(null);
	}	


function checkclick(form){

if(form.checked == true)
{
emparray[empcount]=form.id;
empcount++;

}else
{ 
  for(var i=0; i<emparray.length; i++)
  {  

  
  if(emparray[i]== form.id){
  emparray.splice(i,1);
    empcount--;
  }

  
  }

}


}

  function submmitReq(){
		var id=0;
		var lCnt=0;
	
		for (var lCntr = 0; lCntr < document.searchEmployee.elements.length; lCntr++)
		{ 
		     if((document.searchEmployee.elements[lCntr].type == "checkbox") && (document.searchEmployee.elements[lCntr].checked == true ) ) 
		     {
		        id=document.searchEmployee.elements[lCntr].id;
		        
		        
		        lCnt++;
		      }
		}
	
		if(lCnt<=0)
		{
		     alert("Please Select one row");
		      return;
		}
		if(lCnt>1)
		{
			 alert("Please Select only one row");
			 return;
		}if(lCnt==1)
		{
			
			
			
			var tmpVal=document.getElementById(id).value;
			var cheBoxVal=new Array();
			cheBoxVal=tmpVal.split("~");
			document.getElementById('hidUserId').value=cheBoxVal[0];
			addOrUpdateRecordForEmpSearch('addRecord','updateTransferVO',new Array('hidUserId'));
			
			
			var subId=id.substring(5,id.length);
			var tmpSubId=subId*1+1;
			var hidCatIdNm='hidCatId'+subId;
			
			var empName=document.getElementById('row').rows[tmpSubId].cells;
			var finalEmpArr=new Array();
			finalEmpArr[0]=empName[2].innerHTML;
			finalEmpArr[1]=empName[3].innerHTML;
			finalEmpArr[2]=empName[4].innerHTML;
			finalEmpArr[3]=cheBoxVal[0];
			finalEmpArr[4]=document.getElementById(hidCatIdNm).value;
			
			
			
			
			
		}

window.opener.empSearch(finalEmpArr);

window.close();

}
  