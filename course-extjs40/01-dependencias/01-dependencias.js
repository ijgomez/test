var miVariableGlobal = "Ext esta instalado correctamente!";
Ext.onReady(function(){

	tmp = "Mi variable global indirecta";

	alert(miVariableGlobal);
	Ext.Msg.alert(miVariableGlobal);

	console.info("Mensaje de informacion");
});