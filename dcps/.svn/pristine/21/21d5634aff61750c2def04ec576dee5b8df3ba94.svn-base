var Tree = Ext.tree;
var tree;

Ext.onReady(function(){
var filepath="";    

    tree = new Tree.TreePanel('tree-div', {
        animate:true, 
        enableDD:false,
        containerScroll: true, 
        rootVisible:false,
        lines:true,
        loader : true
    });

    //var filepath="hdiits.htm?viewName=wf-WorkFlowLink&r="+Math.random();
    var filepath="transfertree.jsp";
    
    // set the root node
    var root = new Tree.AsyncTreeNode({
        text: '<b>Organisations</b>', 
        draggable:false, 
        id:'0',
        allowCopy:false,
        loader: new Tree.TreeLoader ({dataUrl:filepath})
    });
    
    // render the tree

    tree.setRootNode(root);    
    tree.render();
    root.select();
    //root.expand();
    tree.expandAll();
    tree.on("click", function(node, e) { 
        onClickTree(node,e);                              
    });
  
/*    var node1 = new Tree.TreeNode( { "cls" : "file", "leaf" : true, "text": 'Sub Item 1', "id": 'Sub Item 1'});
    var node2 = new Tree.TreeNode ( { "cls" : "folder", "text": '<b>Folder 2</b>', "id": 'Folder 2'});
        var node21 = new Tree.TreeNode( { "cls" : "file", "leaf" : true, "text": 'Sub Item 2.1', "id": 'Sub Item 2.1'});
        var node22 = new Tree.TreeNode( { "cls" : "folder", "text": '<b>Folder 2.2</b>', "id": 'Folder 2.2'});
            var node221 = new Tree.TreeNode( { "cls" : "file", "leaf" : true, "text": 'Sub Item 2.2.1', "id": 'Sub Item 2.2.1'});
            var node222 = new Tree.TreeNode( { "cls" : "file", "leaf" : true, "text": 'Sub Item 2.2.2', "id": 'Sub Item 2.2.2'});
            var node223 = new Tree.TreeNode( { "cls" : "file", "leaf" : true, "text": 'Sub Item 2.2.3', "id": 'Sub Item 2.2.3'});
        var node23 = new Tree.TreeNode( { "cls" : "file", "leaf" : true, "text": 'Sub Item 2.3', "id": 'Sub Item 2.3'});    

    root.appendChild(node1);
    root.appendChild(node2);
        node2.appendChild(node21);
        node2.appendChild(node22);
            node22.appendChild(node221);
            node22.appendChild (node222);
            node22.appendChild(node223);
        node2.appendChild(node23);    
*/

});
function onClickTree(node,e)
{
    // write your code here
    // when user select node of tree 
    // this will call
    //alert(" nn " + node.id + " : " + node.text + " : " + node.url + " : " +e);
    var fetchURL = node.id;
   
    if(fetchURL!='All')
    {
    var als=fetchURL.split('^');
   if(als.length==2)
   {
    showProgressbar();
    window.frames['decisiontable'].location='./hrms.htm?actionFlag=decisiontable&nodeid='+node.id;
    }
    }
//	var submitURL = fetchURL.split(',');
	//alert(" s " + submitURL[1] + " :" + submitURL[0]);
	//alert(document.getElementById("dataFrame") + " : " + parent.frames['dataFrame']);
//	document.getElementById("dataFrame").src=submitURL[1];
	//alert(document.getElementById("dataFrame").src + " : " + parent.frames['dataFrame'].src);
	
}