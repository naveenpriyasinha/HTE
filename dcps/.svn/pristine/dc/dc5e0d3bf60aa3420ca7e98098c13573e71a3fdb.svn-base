  var myrules = {
		'input' : function(element)
		{
			element.onblur = function()
			{
				if(element.type=='text')
				{
					element.className = 'pnsntexttag';
				}
				
				if(element.name=='txtEffectedDate')
				{	
					validateDate(this);
					if(element.value != "")
					{
						if(isProper(document.getElementById("oldtxtEffectedDate").value,element.value,"New Effective from date should be greater than Old Effective From Date.") == false)
						{
							element.value = "";
							element.focus();
							return false;
						}
						setDefaultMonth(this);
					}
				}
				if(element.name=='txtNewRate')
				{	
					chkNewRateAmnt();
				}
				if(element.name=='txtNewMinAmt')
				{	
					chkNewRateAmnt();
				}
				
				
			}
			element.onfocus = function()
			{
				if(element.type=='text')
				{
					element.className = 'pnsntextOnFocustag';
				}
			}
		},
		'select' : function(element)
		{
			element.onblur = function()
			{
				element.className = 'pnsnselecttag';
			}
			element.onfocus = function()
			{
				element.className = 'pnsnselectOnFocustag';
			}			
		}
	
	};
	
	Behaviour.register(myrules);
	
	function setOnBlur(element)
	{
		
		if(element.type=='text')
		{
			// text onblur
			element.style.backgroundColor = 'white';
			element.style.borderColor='black';
			element.style.borderWidth= '1px'; 
		    element.style.borderStyle= 'solid' ;
		}
		
		 if(element.type=='select')
		 {
		 	// select onblur
		 	element.style.backgroundColor = 'white';
			element.style.borderColor='black';
			element.style.borderWidth= '1px'; 
		    element.style.borderStyle= 'solid' ;
		 }
	}
		
	function setOnFocus(element)
	{
		if(element.type=='text')
		{
			// text onfocus
			element.style.backgroundColor ='#FEEEF4';
			element.style.borderColor= '#FF0000';
			element.style.borderWidth= '2px'; 
			element.style.borderStyle= 'solid' ;
		}
		if(element.type=='select')
		{
			// text onfocus
			element.style.backgroundColor ='#FEEEF4';
			element.style.borderColor= '#FF0000';
			element.style.borderWidth= '1px'; 
			element.style.borderStyle= 'solid' ;
		}
	}