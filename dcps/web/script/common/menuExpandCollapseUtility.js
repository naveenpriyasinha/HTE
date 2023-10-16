
function expandNode(nodeToExpand,divToBeShown)
	{  
	          var themename = document.getElementById('themename').value;
	          nodeToExpand.style.background = "url(themes/"+themename+"/images/win-tool-collapse-01.png) 95% no-repeat";
	          /*
		          if (nodeToExpand.children.length > 0)
		          {
		               if (nodeToExpand.children.item(0).tagName == "IMG")
		               {
		                    nodeToExpand.children.item(0).src = "themes/"+themename+"/images/opened.gif";
		               }
		          }
	          */          
	          divToBeShown.style.display = '';
	}
	
	function collapseNode(nodeToCollapse,divToBeHiden)
	{          
	           var themename = document.getElementById('themename').value;
	          nodeToCollapse.style.background = "url(themes/"+themename+"/images/win-tool-expand-01.png) 95% no-repeat";
	          /*
		          if (nodeToCollapse.children.length > 0)
		          {
		               if (nodeToCollapse.children.item(0).tagName == "IMG")
		               {
		                    nodeToCollapse.children.item(0).src = "themes/"+themename+"/images/closed.gif";
		               }
		          }
	          */
	          divToBeHiden.style.display = 'none';
	}
	
	function expandOrCollapseNode(node)
	{	     
	     var thisNode = node; 
	     var prntNode = node;     
	     while(prntNode.parentNode && prntNode.nodeName != 'table' && prntNode.nodeName != 'TABLE'&& prntNode.className != 'homepagemenutable')
	     {
	     	prntNode = prntNode.parentNode;
	     	//alert("Node name ---> "+ prntNode.nodeName);
	     }
	     
	     var divNode = prntNode.getElementsByTagName('div')[0];
	     
	    
	     
	     if (divNode.style.display == 'none')
	     {        
	         expandNode(thisNode,divNode);
	     }
	     // Collapse the branch if it is visible
	     else
	     {        
	         collapseNode(thisNode,divNode);
	     }
	
	}
	
	
	function expandOrCollapseAllNodes(linkNode,functionName)
	{	
		
		var prntNode = linkNode;     
	    while(prntNode.parentNode && prntNode.nodeName != 'tr' && prntNode.nodeName != 'TR' && prntNode.id != 'trId')
	    {
	    	prntNode = prntNode.parentNode;     	
	    }
	    
		var trNode = prntNode;	
	   
		var tableNodes = trNode.getElementsByTagName('table');		
		
		for(var i = 0 ; i <tableNodes.length ; i++)
	   	{
	   		var tableNode = tableNodes[i];
	   		var tHeadNode = tableNode.getElementsByTagName("thead")[0]; 
	   		var tBodyNode = tableNode.getElementsByTagName("tbody")[0];
	   	
	   		var imgLinkNode = tHeadNode.getElementsByTagName("a")[0];
	   		var divNode = tBodyNode.getElementsByTagName("div")[0];
	                 
	        if(functionName == 'expandAll')
	        {
		        if (divNode && divNode.style.display == 'none')
		        {
		        	expandNode(imgLinkNode,divNode);                  
		        } 
	        } 
	        else if(functionName == 'collapseAll')          
	        {
	        	if (divNode && divNode.style.display != 'none')
		        {
		        	collapseNode(imgLinkNode,divNode);                  
		        }
	        }
	   	}	
	}
	
	function collapseAllNodes(linkNode)
	{
	    
	    var prntNode = linkNode;     
	    while(prntNode.parentNode && prntNode.nodeName != 'tr' && prntNode.nodeName != 'TR' && prntNode.id != 'trId')
	    {
	    	prntNode = prntNode.parentNode;     	
	    }
	     
		var trNode = prntNode;
		var divNodes = trNode.getElementsByTagName("div");
		for(var i = 0 ; i <divNodes.length ; i++)
	   	{
	        node = divNodes[i];             
	        if (node && node.style.display != 'none')
	        {
	        	node.style.display = 'none';                  
	        } 
	   	}	
	}
	
