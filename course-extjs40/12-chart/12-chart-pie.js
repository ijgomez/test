var myDataStore = Ext.create('Ext.data.JsonStore', {
    fields: ['os', 'data1' ],
    data: [
        { os: 'Android', data1: 68.3 },
        { os: 'iOS', data1: 17.9 },
        { os: 'Windows Phone', data1: 10.2 },
        { os: 'BlackBerry', data1: 1.7 },
        { os: 'Others', data1: 1.9 }
    ]
});

Ext.onReady(function(){

    var chart = Ext.create("Ext.chart.Chart", {
        height: 410,
        width: 410,
        padding: '10 0 0 0',
        style: 'background: #fff',
        shadow: false,
        insetPadding: 40,
        renderTo: Ext.getBody(),

        animate: true,
        store: this.myDataStore,
        legend: {
            field: 'os',
            position: 'bottom',
            boxStrokeWidth: 0,
            labelFont: '12px Helvetica'
        },
        series: [{
            type: 'pie',
            angleField: 'data1',
            label: {
                field: 'os',
                display: 'outside',
                calloutLine: true
            },
            showInLegend: true,
            highlight: { // ESTE SE ENCARGA DE SEPARA EL TROZO DE TARTA DE LOS DEMAS
                segment: {
                    margin: 20
                }
            },
            tips: { //PERMITE CONFIGURAR LOS TOOLTIPS
                trackMouse: true,
                renderer: function(storeItem, item) {
                    this.setTitle(storeItem.get('os') + ': ' + storeItem.get('data1') + '%');
                }
            }
        }]
    });
});

