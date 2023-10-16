

		var actRowId=null;
        var prevRowId=null; 
        var tbody = null;
      
    function getLocationCmb(){
    	if(document.getElementById('cmbOffice').value=="-1"){
    		var locCmb=document.getElementById('cmbLocation');
				
			for(var i=0;i<=locCmb.options.length;i++)
			{
				locCmb.remove(1);
			}
			document.getElementById('cmbLocation').disabled="true";
    		document.getElementById('cmbOffice').focus();
    		document.getElementById('cmbDsgn').disabled="true";
    		return false;
    	}
    
		var locCmb=document.getElementById('cmbLocation');
		for(var i=0;i<=locCmb.options.length;i++)
		{
			locCmb.remove(1);
		}
		addOrUpdateRecord('addLocationCmb','getGradLocationCmb',new Array('cmbOffice'));
	}

	function addLocationCmb(){
		
		if (xmlHttp.readyState == 4) 
		{
			
			var encXML=xmlHttp.responseText;
			if(encXML!="error")
			{
				document.getElementById('cmbLocation').disabled='';	
				var locList=encXML.split("$");
				for (var i=0; i < locList.length;++i){
					var keyValPair=locList[i].split("/");
					addOption(document.gradationList.cmbLocation, keyValPair[0], keyValPair[1]);
				}
				
			}else
			{
				var locCmb=document.getElementById('cmbLocation');
				
				for(var i=0;i<=locCmb.options.length;i++)
				{
					locCmb.remove(1);
				}
				document.getElementById('cmbLocation').disabled="true";
			}	
			
			
		}
	}

	function addOption(selectbox,text,value )
	{
		var optn = document.createElement("OPTION");
		optn.text = text;
		optn.value = value;
		selectbox.options.add(optn);
	}
      
      
      	function handleOver()
      	{
      		document.getElementById("cursorStyle").style.cursor="hand";
      	}
      
         function init() 
         { 
            var tbody = document.getElementById('Gradation'); 
            tbody.onmousedown = startDrag; 
         } 

        function startDrag(e) 
         { 
            if (!e) e = event; 
            var row = e.srcElement ? e.srcElement : e.target; 
            while (row && row.nodeName != 'TR') row = row.parentNode; 
            if (!row) return false;             
            actRowId=row.id; 
          
            tbody = this; 
            tbody.activeRow = row; 
            tbody.onmousemove = doDrag; 
            document.onmouseup = updateHiddenPropert;
         } 
         
   
		 function doDrag(e) 
         { 
         	document.getElementById("cursorStyle").style.cursor="move";
            if (!e) e = event; 
            var row = e.srcElement ? e.srcElement : e.target; 
            while (row && row.nodeName != 'TR') row = row.parentNode; 
            if (!row || row == this.activeRow) return false; 
            if (row.previousSibling && row.previousSibling == this.activeRow) this.insertBefore(row, this.activeRow); 
            else row.parentNode.insertBefore(this.activeRow, row); 
            prevRowId=row.id-1;
          } 
            
    	 function updateHiddenPropert()
         { 
         	//document.getElementById("cursorStyle").style.cursor="default";
             var hiddenFrontId="hiddenRowId";
             if( prevRowId<actRowId)
             {      
             		
	             	var nexttoprevrow = prevRowId + 1;
	           		var nextToPrevElement=document.getElementById(hiddenFrontId+nexttoprevrow);
	           		var nextToPrevElementId=document.getElementById(hiddenFrontId+nexttoprevrow).id;   
           		    var nextToPrevValue=nextToPrevElement.value; 
	                var temp=document.getElementById(hiddenFrontId+actRowId).value;
	           		document.getElementById(hiddenFrontId+nexttoprevrow).id = document.getElementById(hiddenFrontId+actRowId).id;
	           		document.getElementById(hiddenFrontId+nexttoprevrow).value = temp;
	           		for(i=actRowId;i>nexttoprevrow+1;i--)
	           		{          
			           		temp=document.getElementById(hiddenFrontId+(i-1)).value;           		
			           		document.getElementById(hiddenFrontId+i).id=	document.getElementById(hiddenFrontId+(i-1)).id;		        
			           		document.getElementById(hiddenFrontId+i).value=temp;			
	           		}	
	           	        document.getElementById(hiddenFrontId+(nexttoprevrow+1)).id =	nextToPrevElementId;
		           		document.getElementById(hiddenFrontId+(nexttoprevrow+1)).value =	nextToPrevValue;  
						           		     
		        
             }
             else
             {
              		//alert('in else loop' + actRowId + '        ' + prevRowId);          
	             	var nexttoprevrow = prevRowId + 1;
	                var nextToPrevElement=document.getElementById(hiddenFrontId+nexttoprevrow); 
	                var nextToPrevElementId=document.getElementById(hiddenFrontId+nexttoprevrow).id;      
	                var nextToPrevValue=nextToPrevElement.value;         		
	           		var temp=document.getElementById(hiddenFrontId+actRowId).value;
	           		document.getElementById(hiddenFrontId+nexttoprevrow).id =	document.getElementById(hiddenFrontId+actRowId).id;
	           		document.getElementById(hiddenFrontId+nexttoprevrow).value = temp;
	           		
	           		for(var i=actRowId;i<prevRowId;i++)
	           		{        
	           				//alert('in for');
	           				var nextId=hiddenFrontId+(parseInt(i)+1);
	           			    var temp=document.getElementById(nextId).value;
	       				    document.getElementById(hiddenFrontId+i).id=document.getElementById(nextId).id;
	       				    document.getElementById(hiddenFrontId+i).value=temp;
	           		}	
	           		  
	           		document.getElementById(hiddenFrontId+(prevRowId)).id =	nextToPrevElementId; 
	           		document.getElementById(hiddenFrontId+(prevRowId)).value =	nextToPrevValue;	           		
           	}     	    
               tbody.onmousemove = null; 
               tbody.activeRow = null; 
               document.onmouseup = null; 
         }
         
         
        function enableLoc(){
			//if(document.getElementById('cmbOffice').selectedIndex != -1){	
			document.getElementById('cmbLocation').disabled='';	
			//}
		}

		function enableDsgn(form){
				if(form.value=='-1')
				{
					document.getElementById('cmbDsgn').disabled='true';			
					document.getElementById('cmbDsgn').selectedIndex=0;
				}else
				{
					document.getElementById('cmbDsgn').disabled='';
				}
				
		}

		function enableEmpNo(form){
		if(form.value=='-1')
				{
					document.getElementById('empNo').readOnly='true';			
				}else
				{
					document.getElementById('empNo').readOnly='';
				}
		}

		

	function addDataWithOutAJAX()
	{
		var urlstyle="hdiits.htm?actionFlag=getAjxGLdata";
		if(checkEmpNo()){
			document.gradationList.action=urlstyle;	
			document.gradationList.submit();
			document.gradationList.genList.disabled=true;
		}
	}

	

	function submitData()
	{		
		var checkedDragDrop=new Array();
		var checkedArray=new Array();
		var Cnt=0;
		for (var lCntr = 0; lCntr < document.gradationList.elements.length; lCntr++)
		{ 
		     if((document.gradationList.elements[lCntr].type == "checkbox") && (document.gradationList.elements[lCntr].checked == true ) ) 
		     {
		     	
			       var id1=document.gradationList.elements[lCntr].id;
		    	   var subId=id1.substring(6,id1.length);
			       checkedDragDrop[Cnt]='hiddenRowId'+subId;
			   	   checkedArray[Cnt]=subId;
			       Cnt++;
		      }
		}
		
		document.getElementById('hidChkBoxArray').value=checkedArray;  
		document.getElementById('dragDrop').value=checkedDragDrop;
		var urlstyle="hdiits.htm?actionFlag=saveGLData";	
		document.gradationList.action=urlstyle;		
		if(checkSubmit())
		{
			document.gradationList.submit();  
		}else
		{
			return;
		}	
	}	

	function closeWindow()
	{
		var urlstyle="hdiits.htm?actionFlag=getHomePage";
		document.gradationList.action=urlstyle;
		document.gradationList.submit();
	}
         