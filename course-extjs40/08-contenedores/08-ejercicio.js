var secuenciaIdCliente = 0;
var secuenciaIdPedido = 0;
var secuenciaIdProducto = 0;
var secuenciaClientes = 0;
//-------------------------------------------------

Ext.define(
	"Cliente",
	{
		//id: ++secuenciaIdCliente, //Esto es para la definición de las constantes asociadas al tipo
		constructor: function(config){
			this.id = ++secuenciaIdCliente;
			this.nombre = config.nombre;
		},
		getId: function(){
			return this.id;
		},
		getNombre: function(){
			return this.nombre;
		},
		setNombre: function(nombre){
			this.nombre = nombre;
		},
		toString: function(){
			return "{'id': " + this.id + ", 'nombre': " + this.nombre +"}";
		},
		toHtml: function(){
			return {
				tag: "div",
				id: "cliente" + this.id,
				html: this.nombre
			};
		}
	}
);

//-------------------------------------------------

Ext.define(
	"Pedido",
	{
		extend: "Ext.util.Observable",
		//id: ++secuenciaIdPedido,
		constructor: function(config){
			this.id = ++secuenciaIdPedido;
			this.cliente = config.cliente;
			this.productos = config.productos;
			this.addEvents('repintarProducto');
			this.listeners = config.listeners;
			this.callParent(arguments);
		},
		getId: function(){
			return this.id;
		},
		getCliente: function(){
			return this.cliente;
		},
		getProductos: function(){
			return this.productos;
		},
		addProducto: function(producto){
			//Realizar el trabajo propio del metodo
			this.productos.push(producto);
			//Lanzar el evento que indica que se ha modificado los productos
			this.fireEvent('repintarProducto', producto);

		},
		removeProducto: function(posicion){
			this.productos.remove(this.productos[posicion]);
		},
		toString: function(){
			var resultado = "{'id':" + this.id + ",'cliente': " + this.cliente.toString();

			resultado += ",'productos': [";

			for (var i = 0; i < this.productos.length; i++) {
				resultado += this.productos[i].toString() + ", ";
			};

			resultado += "]}";

			return resultado;
		},
		toHtml: function(){
			
			var productos = new Array();

			for (var i = 0; i < this.productos.length; i++) {
				productos.push(this.productos[i].toHtml());
			};

			return {
				tag: "div",
				id: "pedido" + this.id,
				children:[
					this.cliente.toHtml(),
					{
						tag: "div",
						id: "productos",
						children: productos
					}
				]
			};
		},
		toComponent: function(){
			return Ext.create("Ext.Component",{
				tpl: new Ext.Template([
    					'<div id="pedido">',
        					'<div id="cliente">{nombreCliente}</div>',
        					'<div id="productos">',
        						'<div id="producto">',
        							'<div>{nombreProducto}</div>',
        							'<div>{descripcionProducto}</div>',
        						'</div>',
        					'</div>',
    					'</div>',
				]),
				data: {
					nombreCliente: this.getCliente().getNombre(),
					nombreProducto: this.getProductos()[0].getNombre(),
					descripcionProducto: this.getProductos()[0].getDescripcion()
				}
			});
		}
	}
);

//-------------------------------------------------

Ext.define(
	"Producto",
	{
		constructor: function(config){
			this.id = ++secuenciaIdProducto;
			this.nombre = config.nombre;
			this.descricpion = config.descricpion;
		},
		getId: function(){
			return this.id;
		},
		getNombre: function(){
			return this.nombre;
		},
		setNombre: function(nombre){
			this.nombre = nombre;
		},
		getDescripcion: function(){
			return this.descricpion;
		},
		setDescripcion: function(descricpion){
			this.descricpion = descricpion;
		},
		toString: function(){
			return "{'id': " + this.id 
				+ ", 'nombre': " + this.nombre
				 + ", 'descricpion': " + this.descricpion +"}";
		},
		toHtml: function(){
			return {
				tag: "div",
				id: "producto" + this.id,
				children: [
					{
						tag: "div",
						id: "nombre producto" + this.id,
						html: this.nombre
					},{
						tag: "div",
						id: "descripcion producto" + this.id,
						html: this.descricpion
					}
				]
			};
		}
	}
);

//-------------------------------------------------

Ext.onReady(function(){


	var ventanaWizard = Ext.create("Ext.window.Window",{
		layout: 'card',
		width: 400,
		height: 400,
		items: [
				{
					xtype: 'form',
					id: 'formularioCliente',
					title: 'Panel de Cliente',
					defaultType: 'textfield',
					items: [
						{
							fieldLabel: 'Nombre',
	        				name: 'nombre',
	        				allowBlank: false
						}
					]
				},
				{
					xtype: 'form',
					id: 'formularioProducto',
					title: 'Panel de Producto',
					defaultType: 'textfield',
					items: [
						{
							fieldLabel: 'Nombre',
	        				name: 'nombre',
	        				allowBlank: false
						},
						{
							fieldLabel: 'Descripcion',
	        				name: 'descricpion',
	        				allowBlank: false
						}
					]
				}
		],
		bbar: {
			xtype: 'toolbar',
			items: [
				{
					id: 'btAnterior',
					text: 'Anterior',
					disabled: true,
					handler: function(){
						ventanaWizard.getLayout().prev();
						Ext.getCmp('btAnterior').setDisabled(!ventanaWizard.getLayout().getPrev());
						Ext.getCmp('btSiguiente').setDisabled(!ventanaWizard.getLayout().getNext());
						Ext.getCmp('btGuardar').setDisabled(ventanaWizard.getLayout().getNext());
					}
				},
				{
					xtype: 'tbfill'
				},
				{
					id: 'btSiguiente',
					text: 'Siguiente',
					handler: function(){
						ventanaWizard.getLayout().next();
						Ext.getCmp('btAnterior').setDisabled(!ventanaWizard.getLayout().getPrev());
						Ext.getCmp('btSiguiente').setDisabled(!ventanaWizard.getLayout().getNext());
						Ext.getCmp('btGuardar').setDisabled(ventanaWizard.getLayout().getNext());
					}
				},
				{
					id: 'btGuardar',
					text: 'Guardar',
					disabled: true,
					handler: function(){
						
						var formularioCliente = Ext.getCmp("formularioCliente");

						var parametrosConfiguracionCliente = formularioCliente.getForm().getValues();

						var formularioProducto = Ext.getCmp("formularioProducto");

						var parametrosConfiguracionProducto = formularioProducto.getForm().getValues();

						var cliente = Ext.create("Cliente", parametrosConfiguracionCliente);

						var producto = Ext.create("Producto", parametrosConfiguracionProducto);

						var pedido = Ext.create("Pedido", 
										{cliente: cliente, productos: [producto]});

						var pantallaPrincipal = Ext.ComponentQuery.query("viewport > panel");

						pantallaPrincipal[0].add(pedido.toComponent());

						ventanaWizard.getLayout().setActiveItem(0);
						ventanaWizard.hide();
					}
				}
			]
		}
	});

	Ext.create("Ext.container.Viewport",{
		layout: 'fit',
		items: [
			{
				id: 'panelPrincipal',
				xtype: 'panel',
				tbar: {
					xtype: 'toolbar',
					items: [
						{
							text: 'Add Pedido',
							handler: function(){
								ventanaWizard.show();
							}
						}
					]
				}
			}
		]
	});

});