// Importamos los elementos de las bibliotecas que vamos a emplear
var express = require('express');
var path = require('path');
var favicon = require("serve-favicon");
var logger = require("morgan");
var cookieParser = require("cookie-parser");
var bodyParser = require('body-parser');
var basedatos = require("./basedatos.js");

var app = express();

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());

// ############### HABILITAR CROSS ORIGIN EN LAS PETICIONES GET,POST,DELETE,ETC ########################
app.all('/', function(request, response, next) {
    response.header("Access-Control-Allow-Origin", "*");
    response.header("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
    response.header("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");
    next();
   });

 // ############### HABILITAR CROSS ORIGIN EN LAS PETICIONES DE ARCHIVOS ESTÁTICOS ########################
 app.use(function(request, response, next) {
    response.header("Access-Control-Allow-Origin", "*");
    response.header("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
    response.header("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");
    next();
  });

// ################ SERVIR ARCHIVOS ESTÁTICOS ###################################
app.use(express.static(path.join(__dirname, 'angular'))); 

// ################# Operaciones con los informes ########################
app.get("/reports", function (request, response) {
    response.setHeader("Content-Type", "application/json");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.send(basedatos.informes);
    return;
});

app.post("/reports", function (request, response) {
    var report = request.body;
    console.log("CREATE: ", report);
    report = basedatos.informes.length;
    basedatos.informes.push(nuevo);
    response.setHeader("Content-Type", "application/json");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.send(report);
});
app.get("/reports/:id", function (request, response) {
    var id = request.params.id;
    var finded = basedatos.informes.filter(i => i.id == id);
    console.log("READ: ", id);
    response.setHeader("Content-Type", "application/json");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.send(finded[0]);
});
app.patch("/reports/:id", function (request, response) {
    var report = request.body;
    console.log("UPDATE: ", report);
    var informes = basedatos.informes;
    for (var i = 0; i < informes.length; i++) {
        if (informes[i].id == report.id) {
            informes[i] = report;
            break;
        }
    }
    response.setHeader("Content-Type", "application/json");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.send(report);
});
app.delete("/reports/:id", function (request, response){
    var id = request.params.id;
    var borrados = basedatos.informes.filter(i => i.id == id);
    bbdd.informes = basedatos.informes.filter(i => i.id != id);
    console.log("DELETE: ", dni);
    response.setHeader("Content-Type", "application/json");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.send(borrados[0]);
});

// ################# Operaciones con las propiedades ########################
app.get("/properties", function (request, response) {
    response.setHeader("Content-Type", "application/json");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.send(basedatos.propiedades);
    return;
});

// ################# Operaciones con las trazas ########################
app.get("/traces", function (request, response) {
    response.setHeader("Content-Type", "application/json");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.send(basedatos.trazas);
    return;
});

// ################# Operaciones con los usuarios ########################
app.get("/users", function (request, response) {
    response.setHeader("Content-Type", "application/json");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.send(basedatos.usuarios);
    return;
});

// ################# Operaciones con los perfiles ########################
app.get("/profiles", function (request, response) {
    response.setHeader("Content-Type", "application/json");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.send(basedatos.perfiles);
    return;
});

// ################# Operaciones con las acciones ########################
app.get("/actions", function (request, response) {
    response.setHeader("Content-Type", "application/json");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.send(basedatos.acciones);
    return;
});

// ################ EJECUTAR SERVIDOR ###################################
app.listen(3001, function () {
    console.log("Aplicación escuchando en el puerto 3001");
});