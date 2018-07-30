<%@ page contentType="text/html; charset=ISO-8859-1" language="java"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<link type="text/css" href="../../css/folder-tree-static.css"
			rel="stylesheet" />
		<link type="text/css" href="../../css/context-menu.css"
			rel="stylesheet" />
		<script type="text/javascript" src="../../js/tree/ajax.js"></script>
		<script type="text/javascript" src="../../js/tree/context-menu.js"></script>
		<script type="text/javascript" src="../../js/tree/folder-tree-static.js"></script>
	</head>

	<body>
		<ul id="dhtmlgoodies_tree" class="dhtmlgoodies_tree">
			<li>
				<a href="#" id="node_1">Europe</a>
				<ul>
					<li parentId="1">
						<a href="#" id="node_2">Loading...</a>
					</li>
				</ul>
			</li>
			<li>
				<a href="#" id="node_3">Asia</a>
				<ul>
					<li parentId="2">
						<a href="#" id="node_4">Loading...</a>
					</li>
				</ul>
			</li>
			<li>
				<a href="#" id="node_5">Africa</a>
				<ul>
					<li parentId="3">
						<a href="#" id="node_6">Loading...</a>
					</li>
				</ul>
			</li>
			<li>
				<a href="#" id="node_7">America</a>
				<ul>
					<li parentId="4">
						<a href="#" id="node_8">Loading...</a>
					</li>
				</ul>
			</li>
		</ul>
		<a href="#" onclick="expandAll('dhtmlgoodies_tree');return false">Expand
			all</a>
		<a href="#" onclick="collapseAll('dhtmlgoodies_tree');return false">Collapse
			all</a>




		<ul id="contextMenu">
			<li>
				<a href="#" onclick="addNewNode(event);return false">Add new
					node</a>
			</li>
			<li>
				<a href="#" onclick="deleteNode(event);return false">Delete node</a>
			</li>
		</ul>
		<script type="text/javascript">
	initContextMenu();
	</script>
	</body>
</html>
