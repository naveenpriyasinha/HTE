		function checkParentModuleScreenPermissions(Msgobj){
		
			var tempActionName = '';
			var len = document.form1.listCounter1.value;
			
			for(var i=0; i < eval(len); i++)
			{
				if(eval(len) == 1) {
					
					if(eval(document.form1.exist_screen_perm1.value) == 2)
					{
						if(eval(document.form1.selectPermission_old16.value) == 5)
						{
							alert(objName+' '+Msgobj1);
							return;
						}
						if(eval(document.form1.selectPermission_old16.value) == 1)
						{
							
							alert(objName+' '+Msgobj2);
							
							return;
						}
					}
					if(eval(document.form1.exist_screen_perm1.value) == 5)
					{
						if(eval(document.form1.selectPermission_old16.value) == 1)
						{
							
							alert(objName+' '+Msgobj3);
							
							return;
						}
					}
					
				}
				else
				{
					
					if(eval(document.form1.exist_screen_perm1.value) == 2)
					{
						if(eval(document.form1.selectPermission_old16[i].value) == 5)
						{
							
							alert(objName+' '+Msgobj1);
							
							return;
						}
						if(eval(document.form1.selectPermission_old16[i].value) == 1)
						{
							
							alert(objName+' '+Msgobj2);
							
							return;
						}
					}
					if(eval(document.form1.exist_screen_perm1.value) == 5)
					{
						if(eval(document.form1.selectPermission_old16[i].value) == 1)
						{
							
							alert(objName+' '+Msgobj3);
							
							return;
						}
					}
					
					
				}	
			}
						
			document.form1.ActionType.value="16";
			insertdt();		
					   
			alert(Msgobj);
			
			ajaxfunction(16);
			enable();
			document.form1.Field_Save.disabled = true;
		}
				
		function remove(src) {
			var orow = src.parentElement.parentElement;
			document.all("tabcont").deleteRow(orow.rowIndex);
		}
		
		function checkParentPermissions(Msgobj,Msgobj1,Msgobj2,Msgobj3,objName)
		{
			
			
			var len = document.form1.listCounter.value;
			var tempActionName = '';
			
			for(var i=0; i < eval(len); i++)
			{
				if(eval(len) == 1) {
									
					if(eval(document.form1.exist_module_perm.value) == 2)
					{
						if(eval(document.form1.selectPermission_old15.value) == 5)
						{
							
							alert(objName+' '+Msgobj1);
							
							return;
						}
						if(eval(document.form1.selectPermission_old15.value) == 1)
						{
							
							alert(objName+' '+Msgobj2);

							return;
						}
					}
					if(eval(document.form1.exist_module_perm.value) == 5)
					{
						if(eval(document.form1.selectPermission_old15.value) == 1)
						{
							
							alert(objName+' '+Msgobj3);
							
							return;
						}
					}
					
				}
				else
				{
				
				
					if(eval(document.form1.exist_module_perm.value) == 2)
					{
						if(eval(document.form1.selectPermission_old15[i].value) == 5)
						{
							
							alert(objName+' '+Msgobj1);
							
							return;
						}
						if(eval(document.form1.selectPermission_old15[i].value) == 1)
						{
							
							alert(objName+' '+Msgobj2);
							
							return;
						}
					}
					if(eval(document.form1.exist_module_perm.value) == 5)
					{
						if(eval(document.form1.selectPermission_old15[i].value) == 1)
						{
							
							alert(objName+' '+Msgobj3);
						
							return;
						}
					}
					
					
				}	
			}
						
			document.form1.ActionType.value="15";
			insertdt();	
			alert(Msgobj);
			
			ajaxfunction(15);
			enable();
			document.form1.Screen_Save.disabled = true;
			
			
		}
		
		function EnableSaveButton(Elementobj_no)
		{
			if(eval(Elementobj_no) == 14)
			{
				document.form1.Module_Save.disabled = false;
			}

			else if(eval(Elementobj_no) == 15)
			{
				document.form1.Screen_Save.disabled = false;
			}
			
			else if(eval(Elementobj_no) == 16)
			{
				document.form1.Field_Save.disabled = false;
			}
			
		}
		

		
		function insRow(obj,Msgobj,Msgobj1,Msgobj2,Msgobj3,Msgobj4,Msgobj5,Msgobj6){
    			
  			if(eval(obj) == 14) {
		   		if(document.form1.ModuleCombo.value == ''){
		    		
		    		alert(Msgobj);
		    		return false;
		    	}
		 	}
		 	else if(eval(obj) == 15) {
		   		
		   		if(document.form1.ScreenCombo.value == ''){
		    		
	    		    alert(Msgobj);
		    		return false;
		    	}
		    	
		 	}   
		 	else if(eval(obj) == 16) {
		   		if(document.form1.FieldCombo.value == ''){
		    		alert(Msgobj);
		    		
		    		return false;
		    	}
		 	}
		 	
		        var UserEntries;
			    if(eval(obj) == 14) {
			        document.form1.Module_Save.disabled = false;
					UserEntries=document.getElementById("ModuleCombo");
					var tempCombo='<select name="selectPermission_new">';
					tempCombo = tempCombo + '<option value="1" SELECTED>Editable</option><option value="2" >Hidden</option><option value="5" >Readonly</option>';			    
					tempCombo = tempCombo + '</select>';
					
				}
				if(eval(obj) == 15) {
					document.form1.Screen_Save.disabled = false;
					UserEntries=document.getElementById("ScreenCombo");
					
					var tempCombo='<select name="selectPermission_new">';
					if(eval(document.form1.exist_module_perm.value) == 1) {
						tempCombo = tempCombo + '<option value="1" SELECTED>Editable</option><option value="2" >Hidden</option><option value="5" >Readonly</option>';			    
					}
					else if(eval(document.form1.exist_module_perm.value) == 2) {
						tempCombo = tempCombo + '<option value="2" SELECTED>Hidden</option>';			    
					}
					else if(eval(document.form1.exist_module_perm.value) == 5) {
						tempCombo = tempCombo + '<option value="2" SELECTED>Hidden</option><option value="5" >Readonly</option>';			    
					}
					
					tempCombo = tempCombo + '</select>';
					
					
				}	
				if(eval(obj) == 16) {
					
					document.form1.Field_Save.disabled = false;
					UserEntries=document.getElementById("FieldCombo");
					var tempCombo='<select name="selectPermission_new">';
					
					if(eval(document.form1.exist_screen_perm1.value) == 1) {
						tempCombo = tempCombo + '<option value="1" SELECTED>Editable</option><option value="2" >Hidden</option><option value="5" >Readonly</option>';			    
					}
					else if(eval(document.form1.exist_screen_perm1.value) == 2) {
						tempCombo = tempCombo + '<option value="2" SELECTED>Hidden</option>';			    
					}
					else if(eval(document.form1.exist_screen_perm1.value) == 5) {
						tempCombo = tempCombo + '<option value="2" SELECTED>Hidden</option><option value="5" >Readonly</option>';			    
					}
					
					tempCombo = tempCombo + '</select>';
					
				}
					   
			   	for(var i=0;i<UserEntries.length;i++)
				{
					
					if(UserEntries.options[i].selected)
					{
						
							var rows=document.getElementById("ModuleTable").rows.length;
							if(eval(i) == 0 && eval(rows) == 0) {
     						var b=document.getElementById('ModuleTable').insertRow();
     						var moduleCodelbl=b.insertCell(0)
	     					moduleCodelbl.innerHTML='<b>' + Msgobj1 + '</b>';
	     					var moduleNamelbl=b.insertCell(1)
	     					moduleNamelbl.innerHTML='<b>' + Msgobj2 + '</b>';
	     					var roleIdlbl=b.insertCell(2)
	     					roleIdlbl.innerHTML='<b>' + Msgobj3 + '</b>';
	     					var roleNamelbl=b.insertCell(3)
	     					roleNamelbl.innerHTML='<b>' + Msgobj4 + '</b>';
	     					var permissionVallbl=b.insertCell(4)
	     					permissionVallbl.innerHTML='<b>' + Msgobj5 + '</b>';
	     					var deleteRecdlbl=b.insertCell(5)
	     					deleteRecdlbl.innerHTML='<b>' + Msgobj6 + '</b>';
	     				}
	     				
						var temp = UserEntries.options[i].value;
						if(eval(obj) == 14) {
							var a=document.getElementById('ModuleTable').insertRow()
													
						}
						if(eval(obj) == 15) {
							var a=document.getElementById('ScreenTable').insertRow()
							
						}
						if(eval(obj) == 16) {
							var a=document.getElementById('FieldTable').insertRow()

						}
					    var v=a.insertCell(0)
					    var w=a.insertCell(1)
					    var x=a.insertCell(2)
					    var y=a.insertCell(3)
					    var z=a.insertCell(4)
					    var zz=a.insertCell(5)
						var tempText='<input type="text" name="ActionCode_txt_new" size="3" value='+temp+' readonly="readonly">';//temp text to generate module code
						v.innerHTML=tempText;
						w.innerHTML=UserEntries.options[i].text;
					    x.innerHTML=document.form1.roleId.value
					    y.innerHTML=document.form1.roleName.value;
					    z.innerHTML=tempCombo;
					    var editString = "<a style=\"cursor:hand\" onClick=\"getSelected(this,"+obj+", '"+UserEntries.options[i].value+"' , '"+UserEntries.options[i].text+"' , '"+document.form1.roleId.value+"' , '"+document.form1.roleName.value+"')\"> Revoke </a>";
			      		zz.innerHTML=editString;
			      		
					}
					
				}
			removeFunction(obj);
		}
		
		function removeFunction(obj){
		
			if(eval(obj) == 14)
				var UserEntries=document.getElementById("ModuleCombo");
			else if(eval(obj) == 15)	
				var UserEntries=document.getElementById("ScreenCombo");
			else if(eval(obj) == 16)	
				var UserEntries=document.getElementById("FieldCombo");
				
			for(var i=0;i<UserEntries.length;i++)
			{
				if(UserEntries.options[i].selected)
				{
					UserEntries.remove(i);
					i = i - 1;
				}
			}
		}
		
		
		 function setVal()
		 {
		 	if(document.form1.hiddentext1.value.indexOf("#") == -1)
			 	document.form1.hiddentext1.value = document.form1.hiddenTest.value + "#";
		 }
		 
		
		 function getSelected(src,obj1,obj2,obj3,obj4,obj5,obj6){
		 	
		 	if(confirm("Are you sure you want to delete") == true)
		 	{
		 	   if(eval(obj6)==1)
		 	   {
		 	        ajaxfunction(23,obj2,obj4,obj5,obj1);
		 	       
		 	   }
			 	var y=document.createElement('option');
			 	y.text=obj3;
				y.value=obj2;
				if(eval(obj1) == 14) {
					var z=document.getElementById("ModuleCombo");	
				}	
				else if(eval(obj1) == 15) {
					var z=document.getElementById("ScreenCombo");
				}	
				else if(eval(obj1) == 16) {
					var z=document.getElementById("FieldCombo");
				}	
				
				try
		   		{
		    			z.add(y,null); 
		    			
		   		}
		 		catch(ex)
		   		{
		   			 
		   			 z.add(y); 
		   		}
			 	
			 	var row = src.parentElement.parentElement;
			 	if(eval(obj1) == 14) {
					document.all("ModuleTable").deleteRow(row.rowIndex);
				}
				else if(eval(obj1) == 15) {
					document.all("ScreenTable").deleteRow(row.rowIndex);
					document.form1.listCounter.value = eval(document.form1.listCounter.value) - 1;
					
					
				}
				else if(eval(obj1) == 16) {
					document.all("FieldTable").deleteRow(row.rowIndex);
					document.form1.listCounter1.value = eval(document.form1.listCounter1.value) - 1;
					
				}	
		 	
		 	}
		 	
		}
		function handleEmpty() 
		{
			  document.getElementById("ActionId").options.length = 0;
			  document.getElementById("ActionId").options[0] = new Option("None", "");
			  document.getElementById("ActionId").disabled = true;
		}	
		

		function saveModule(msgObj) {			
			
			document.form1.ActionType.value="14";
			insertdt();
			alert(msgObj);
			ajaxfunction(14);
			document.form1.Module_Save.disabled = true;
		}
	
		function deleteComboValues(obj)
		{
			if(eval(obj) == 14)
				var UserEntries=document.getElementById("ModuleCombo");
			if(eval(obj) == 15)
				var UserEntries=document.getElementById("ScreenCombo");
			if(eval(obj) == 16)
				var UserEntries=document.getElementById("FieldCombo");
				
			for(var i=0;i<UserEntries.length;i++)
			{
					UserEntries.remove(i);
					i = i - 1;
			}
			
			
		}
		
		function deleteTableValues(obj)
		{
			if(eval(obj) == 14) 
			{
				var rows=document.getElementById("ModuleTable").rows.length;
				for(var i=0;i<eval(rows);i++)
				{
					document.all("ModuleTable").deleteRow(document.getElementById("ModuleTable").rowIndex);
			      	
				}
			}
			if(eval(obj) == 15) 
			{
				var rows=document.getElementById("ScreenTable").rows.length;
				for(var i=0;i<eval(rows);i++)
				{
					document.all("ScreenTable").deleteRow(document.getElementById("ScreenTable").rowIndex);
			      	
				}
			}
			if(eval(obj) == 16) 
			{
				var rows=document.getElementById("FieldTable").rows.length;
				for(var i=0;i<eval(rows);i++)
				{
					document.all("FieldTable").deleteRow(document.getElementById("FieldTable").rowIndex);
			      	
				}
			}
			
		}

		