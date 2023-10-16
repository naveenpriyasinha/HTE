var myrules = {
		'input' : function(element)
		{
			element.onblur = function()
			{
				if(element.type=='text')
				{
					element.className = 'pnsntexttag';
				}
				if(element.name == 'txtbranchCode')
				{
					getBranchByBranchCodeMnthly();
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
