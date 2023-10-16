
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
		"http://www.w3.org/TR/html4/strict.dtd" >
<meta http-equiv="Pragma" content="no-cache">
		
<%@ page contentType="text/html;charset=UTF-8"%>
<%String menuName = request.getParameter("menuName"); %>

<script type="text/javascript" src="<c:url value="script/common/statusbar.js"/>"></script>
<script>
	var aa;
</script>
<html>
<head>
	<style type="text/css">
		@import "dojoTree/dijit/tests/css/dijitTests.css";
	</style>

	<script type="text/javascript" src="dojoTree/dojo/dojo.js"
		djConfig="parseOnLoad: true, isDebug: false"></script>
	<script type="text/javascript" src="dojoTree/dijit/tests/_testCommon.js"></script>

	<script language="JavaScript" type="text/javascript">
		dojo.require("dojo.data.ItemFileReadStore");
		dojo.require("dijit.Tree");
		dojo.require("dijit.ColorPalette");
		dojo.require("dijit.Menu");
		dojo.require("dojo.parser");
		dojo.require("dojo.dnd.common");
		dojo.require("dojo.dnd.Source");
			// scan page for widgets and instantiate them
	</script>

</head>
<body>

	<div dojoType="dojo.data.ItemFileReadStore" jsId="continentStore"
		url="WorkFlowLink1.jsp?<%=menuName%>" style="overflow:scrolling;"></div>
	<div dojoType="dijit.tree.ForestStoreModel" jsId="continentModel" 
		store="continentStore" query="{type:'continent'}"
		rootId="ContinentRoot" rootLabel="Continents" childrenAttrs="children" style="overflow:scrolling;"></div>
		
	<div dojoType="dijit.Tree" id="tree2"
		model="continentModel" showRoot="false" openOnClick="false" style="height: 400px; width: 300px">

		<script type="dojo/connect">
			var menu = dijit.byId("tree_menu");
			// when we right-click anywhere on the tree, make sure we open the menu
			//menu.bindDomNode(this.domNode);

			dojo.connect(menu, "_openMyself", this, function(e){
				// get a hold of, and log out, the tree node that was the source of this open event
				var tn = dijit.getEnclosingWidget(e.target);
				console.debug(tn);

				// now inspect the data store item that backs the tree node:
				console.debug(tn.item);

				// contrived condition: if this tree node doesn't have any children, disable all of the menu items
				//menu.getChildren().forEach(function(i){ i.setDisabled(!tn.item.children); });

				// IMPLEMENT CUSTOM MENU BEHAVIOR HERE
			});
		</script>
		<script type="dojo/method" event="getIconClass" args="item, opened">
           return (item == this.model.root || continentStore.getValue(item, "type") == "continent") ?
                   (opened ? "customFolderOpenedIcon" : "customFolderClosedIcon") :
                    "noteIcon";
		</script>
		<script type="dojo/method" event="onClick" args="item">
			showProgressbar();
			if(continentStore.getValue(item, "url").indexOf('reportService') != -1)
			{
				if("Outward" == continentStore.getValue(item, "showLinkName"))
				{
					t = setTimeout("checkFrame()",1000); 
				}
			}
				
			document.getElementById("dataFrame").src = continentStore.getValue(item, "url");

		</script>
</div>

</body>
</html>




