var express = require('express');
//var path = require('path');
//var favicon = require("serve-favicon");
//var logger = require("morgan");
var cookieParser = require("cookie-parser");
var bodyParser = require('body-parser');



var reportController = require('./controllers/reports-controller.js');
var actionsController = require('./controllers/actions-controller.js');
var profilesController = require('./controllers/profiles-controller.js');
var propertiesController = require('./controllers/properties-controller.js');
var tracesController = require('./controllers/traces-controller.js');
var usersController = require('./controllers/users-controller.js');
var statusController = require('./controllers/status-controller.js');

var server = express();
var expressWs = require('express-ws')(server);

server.use(bodyParser.json());
server.use(bodyParser.urlencoded({ extended: false }));
server.use(cookieParser());

var route = express.Router();

// ################# INFORMES ########################
route.route('/reports')
    .get(reportController.list)
    .post(reportController.create);
route.route('/reports/:id')
    .get(reportController.read)
    .patch(reportController.update)
    .delete(reportController.delete);

// ################# ACCIONES ########################
route.route('/actions')
    .get(actionsController.list)
    .post(actionsController.create);
route.route('/actions/:id')
    .get(actionsController.read)
    .patch(actionsController.update)
    .delete(actionsController.delete);

// ################# PROFILES ########################
route.route('/profiles')
    .get(profilesController.list)
    .post(profilesController.create);
route.route('/profiles/:id')
    .get(profilesController.read)
    .patch(profilesController.update)
    .delete(profilesController.delete);

// ################# PROPERTIES ########################
route.route('/properties')
    .get(propertiesController.list)
    .post(propertiesController.create);
route.route('/properties/:id')
    .get(propertiesController.read)
    .patch(propertiesController.update)
    .delete(propertiesController.delete);

// ################# TRAZAS ########################
route.route('/traces')
    .get(tracesController.list)
    .post(tracesController.create);
route.route('/traces/:id')
    .get(tracesController.read)
    .patch(tracesController.update)
    .delete(tracesController.delete);

// ################# USUARIOS ########################
route.route('/users')
    .get(usersController.list)
    .post(usersController.create);
route.route('/users/:id')
    .get(usersController.read)
    .patch(usersController.update)
    .delete(usersController.delete);

// ################# SERVER STATUS ########################
route.route('/status/time')
    .get(statusController.time);

// ######################################################
server.use(function (request, response, next) {
    console.log('middleware');
    request.testing = 'testing';
    response.header("Access-Control-Allow-Origin", "*");
    response.header("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
    response.header("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");
    response.header("Content-Type", "application/json");
    response.header("Cache-Control", "no-cache, no-store, must-revalidate");
    return next();
});
server.get('/', function(request, response, next){
    console.log('get route', request.testing);
    response.end();
  });
server.use('/api', route);
/*
server.all('/', function(request, response, next) {
    response.header("Access-Control-Allow-Origin", "*");
    response.header("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
    response.header("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");
    next();
});
*/

// #################### WEBSOCKETS ##################################
server.ws('/', function(ws, req) {
    ws.on('message', function(msg) {
      console.log(msg);
      ws.send(msg);
    });
    ws.send('something');
    console.log('socket', req.testing);
  });

// ######################################################
server.listen(3001, function(){
    console.log('Running testing server in port 3001...');
});