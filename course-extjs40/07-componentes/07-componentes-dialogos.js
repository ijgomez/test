Ext.onReady(function(){

	Ext.MessageBox.confirm(
		'Borrar registro', //Titulo
		'Quiere borrar el registro?', //Pregunta a confirmar
		function(btn){
			console.log('Pulso ' + btn );
			if (btn == 'yes'){
				Ext.Msg.alert('Registro borrado');
			} else if (btn == 'no') {
				console.log('No se borro el registro');
			}
		}
	);
});