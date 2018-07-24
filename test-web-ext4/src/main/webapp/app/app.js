
Ext.application({
    name: 'BrazilJS',

    controllers: [
        'Contacts'
    ],

    launch: function() {
        Ext.create('Ext.container.Viewport', {
            layout: 'fit',
            items: [
                {
                    xtype: 'contactlist'
                }
            ]
        });
    }
});
