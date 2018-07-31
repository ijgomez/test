var basedatos = require("../basedatos.js");

var data = basedatos.informes;

module.exports = {
    create: function(informe){
        informe.id = data.length + 1;
        data.push(informe);
        return informe;
    },
    read: function(id){
        var finded = data.filter(i => i.id == id);
        if (finded.length == 1) {
            return finded[0];
        }
        throw new Error('data not found!!');
    },
    update: function(informe){
        for (var i = 0; i < data.length; i++) {
            if (data[i].id == informe.id) {
                data[i] = informe;
                break;
            }
        }
        return informe;
    },
    delete: function(id){
        var borrados = data.filter(i => i.id == id);
        data = data.filter(i => i.id != id);
        return borrados.length;
    },
    listAll: function(){
        return data;
    }
};