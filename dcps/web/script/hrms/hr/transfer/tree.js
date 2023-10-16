Ext.onReady(function(){

	// Note: For the purposes of following along with the tutorial, all 
	// new code should be placed inside this method.
	
/*	alert("Congratulations!  You have Ext configured correctly!");*/
	
	Ext.get('myButton').on('click',function(){
			Ext.MessageBox.show({
					title :'Click',								
					msg	: 'You Click Button',
					width:400,
					buttons:Ext.MessageBox.OK

			});
	});

	Ext.get('addNode').on('click',function(){
			var node3 = new Tree.TreeNode( { "cls" : "folder", "text": '<b>Folder 3</b>', "id": 'Folder 3'});
			selectedNode.appendChild(node3); 			
   });
	
	
var Tree = Ext.tree;
var selectedNode ;
    
    var tree = new Tree.TreePanel('tree-div', {
        animate:true, 
		enableDD:false,
        containerScroll: true,
		rootVisible:true,
		lines:true
    });

    // set the root node
    var root = new Tree.TreeNode({
    	"cls" : "folder",
        text: '<b>Folder</b>',
        id:'source'
    });
   
	
	var node1 = new Tree.TreeNode( { "cls" : "file", "leaf" : true, "text": 'Sub Item 1', "id": 'Sub Item 1'});
	var node2 = new Tree.TreeNode( { "cls" : "folder", "text": '<b>Folder 2</b>', "id": 'Folder 2'});
		var node21 = new Tree.TreeNode( { "cls" : "file", "leaf" : true, "text": 'Sub Item 2.1', "id": 'Sub Item 2.1'});
		var node22 = new Tree.TreeNode( { "cls" : "folder", "text": '<b>Folder 2.2</b>', "id": 'Folder 2.2'});
			var node221 = new Tree.TreeNode( { "cls" : "file", "leaf" : true, "text": 'Sub Item 2.2.1', "id": 'Sub Item 2.2.1'});
			var node222 = new Tree.TreeNode( { "cls" : "file", "leaf" : true, "text": 'Sub Item 2.2.2', "id": 'Sub Item 2.2.2'});
			var node223 = new Tree.TreeNode( { "cls" : "file", "leaf" : true, "text": 'Sub Item 2.2.3', "id": 'Sub Item 2.2.3'});
		var node23 = new Tree.TreeNode( { "cls" : "file", "leaf" : false, "text": 'Sub Item 2.3', "id": 'Sub Item 2.3'});	

	root.appendChild(node1);
	root.appendChild(node2);
		node2.appendChild(node21);
		node2.appendChild(node22);
			node22.appendChild(node221);
			node22.appendChild(node222);
			node22.appendChild(node223);
		node2.appendChild(node23);	

    // render the tree
    tree.setRootNode(root);
    tree.render();
	tree.expandAll();
	tree.selectPath("folder");
	selectedNode = root;
	tree.on("click", function(node, e) {
		// In this function you have full access to the node that was clicked.
		var selectedId = node.attributes.id;
		var text = node.text;
		var div =  document.getElementById("selNode");
		div.innerHTML ="<br><p>Selected Node Name : " + text + "<p>";
		// set a breakpoint and look at the variables in FireBug.
		selectedNode = node;
	});
});