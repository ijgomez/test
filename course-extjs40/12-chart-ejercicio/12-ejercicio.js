var secuenciaIdCliente = 0;
var secuenciaIdPedido = 0;
var secuenciaIdProducto = 0;
var secuenciaClientes = 0;

//-------------------------------------------------

Ext.define("Cliente",
	{
			extend: 'Ext.data.Model',
			fields: [
			         {name: 'id',    type: 'int'},
			         {name: 'nombre',  type: 'string'}
		    ]
	}
);

//-------------------------------------------------

Ext.define("Producto",
	{
		extend: "Ext.data.Model",
		fields: [
		         {name: 'id',    type: 'int'},
		         {name: 'pedido_id',    type: 'int'},
		         {name: 'nombre',    type: 'string'},
		         {name: 'descripcion',    type: 'string'},
//---------------------Nuevo Campo----------------------		         
		         {name: 'precio_unitario', type: 'int'},
		         {name: 'cantidad', type: 'int'}
	    ]
	}
);

//-------------------------------------------------

Ext.define("Pedido",
	{
		extend: "Ext.data.Model",
		fields: [
		         {name: 'id',    type: 'int'},
		         {name: 'cliente_id',    type: 'int'},
//---------------------Nuevo Campo----------------------
		         {name: 'total', type: 'int'},
		         {name: 'fecha', type: 'date', format: 'd-m-Y'}
	    ],
	    belongsTo: [
	             {model: 'Cliente'} //getCliente()
	    ],
	    hasMany: [
	             {model: 'Producto'} //productos()
	    ],
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
					nombreCliente: this.getCliente().get('nombre'),
					nombreProducto: this.productos().first().get('nombre'),
					descripcionProducto: this.productos().first().get('descripcion')
				}
			});
		}
	}
);



//-------------------------------------------------

var storePedido = Ext.create('Ext.data.Store', {
	model: 'Pedido',
	autoSync: true,
	autoLoad: true,
	proxy: {
		type: 'ajax',
		url: 'datos.json',
		reader: {
			root: 'data',
			successProperty : 'meta.success'
		}
	}
});

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
						},
						{
							text: 'Consultar Pedido',
							handler: function(){
								
								var pantallaPrincipal = Ext.ComponentQuery.query("viewport > panel");
								
								storePedido.each(function(item){
									pantallaPrincipal[0].add(Ext.create("Ext.chart.Chart",{
										store: item.productos(),
										width: 400,
										height: 400,
										animate: true,
										series: [
									         {
									        	 type: 'pie',
									        	 showInLegend: true,
									        	 angleField: 'cantidad',
									             tips: {
									                 trackMouse: true,
									                 width: 140,
									                 height: 28,
									                 renderer: function(storeItem, item) {
									                     var total = 0;
									                     storePedido.getAt(storePedido.find('id',1)).productos().each(function(rec) {
									                         total += rec.get('cantidad');
									                     });
									                     this.setTitle(storeItem.get('nombre') + ': ' + Math.round(storeItem.get('cantidad') / total * 100) + '%');
									                 }
									             },
									             highlight: {
									                 segment: {
									                     margin: 20
									                 }
									             },
									             label: {
									                 field: 'nombre',
									                 display: 'rotate',
									                 contrast: true,
									                 font: '18px Arial',
									                 hideLessThan: 18
									             }
									         }
										]
									}));
								});
								
								
								
								//Iteramos sobre los pedidos que retorna el Store
								/*storePedido.each(function(item){
									var pantallaPrincipal = Ext.ComponentQuery.query("viewport > panel");

									pantallaPrincipal[0].add(item.toComponent());
								});*/
							}
						}
					]
				}
			}
		]
	});
});