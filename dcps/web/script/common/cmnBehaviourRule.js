var myrules = {
		'input' : function(element)
		{
			if(element.readOnly != true)
			{
				element.onblur = function()
				{
					if(element.type=='text')
					{
						element.style.backgroundColor = 'white';
						element.style.borderColor='black';
						element.style.borderWidth= '1px'; 
					    element.style.borderStyle= 'solid' ;
					 }
				}

				element.onfocus = function()
				{
					if(element.type=='text')
					{
						element.style.backgroundColor ='FDFCE9';
						element.style.borderColor='blue';
						element.style.borderWidth= '0.4mm'; 
						element.style.borderStyle= 'solid' ;
					}
				}
			}

		},
		'select' : function(element)
		{
			element.onblur = function()
			{
				element.style.backgroundColor = 'white';
				element.style.borderColor='black';
				element.style.borderWidth= '1px'; 
			    element.style.borderStyle= 'solid' ;
			}
			element.onfocus = function()
			{
				element.style.backgroundColor ='FDFCE9';
				element.style.borderColor='blue';
				element.style.borderWidth= '1px'; 
				element.style.borderStyle= 'solid' ;
			}
		}

	};

	Behaviour.register(myrules);