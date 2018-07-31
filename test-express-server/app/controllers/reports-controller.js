
var service = require('../services/reports-service.js');

module.exports = {

    list: function(request, response) {
        console.log('list');

        // response.setHeader("Content-Type", "application/json");
        // response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        // response.setHeader("Access-Control-Allow-Origin", "*");
        // response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
        // response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");
        response.send(service.listAll());
    },
    create: function(request, response) {
        console.log('create');
        var data = request.body;
        data = service.create(data);

        // response.setHeader("Content-Type", "application/json");
        // response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.send(data);
    },
    read: function(request, response) {
        console.log('read');
        var id = request.params.id;

        // response.setHeader("Content-Type", "application/json");
        // response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.send(service.read(id));
    },
    update: function(request, response) {
        console.log('update');
        var id = request.params.id;
        var data = request.body;

        // response.setHeader("Content-Type", "application/json");
        // response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.send(service.update(data));
    },
    delete: function(request, response) {
        console.log('delete');
        var id = request.params.id;

        // response.setHeader("Content-Type", "application/json");
        // response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.send({ rows: service.delete(id)});
    }
}