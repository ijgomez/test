// Importamos los elementos de las bibliotecas que vamos a emplear
var express = require('express');
var path = require('path');
var favicon = require("serve-favicon");
var logger = require("morgan");
var cookieParser = require("cookie-parser");
var bodyParser = require('body-parser');
var spain = require("./ListaLocalidades.js");
var app = express();

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());

// ############### HABILITAR CROSS ORIGIN EN LAS PETICIONES GET,POST,DELETE,ETC ########################
app.all('/', function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  //res.header("Access-Control-Allow-Headers", "X-Requested-With");
  res.header("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
  res.header("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");
  next();
 });

 // ############### HABILITAR CROSS ORIGIN EN LAS PETICIONES DE ARCHIVOS ESTÁTICOS ########################
 app.use(function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  //res.header("Access-Control-Allow-Headers", "X-Requested-With");
    res.header("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
	res.header("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");
  next();
});
// ################ SERVIR ARCHIVOS ESTÁTICOS ###################################
app.use(express.static(path.join(__dirname, 'angular'))); 
 
var bbdd = {
    "personas": [
        { "dni": 22, "nombre": "Daniel", "apellidos": "Valiente", "saldo": 834.777 },
        { "dni": 32, "nombre": "Sergio", "apellidos": "Valiente", "saldo": 245.888 },
        { "dni": 54, "nombre": "Laura", "apellidos": "Villanueva", "saldo": 265.333 },
        { "dni": 85, "nombre": "Alicia", "apellidos": "Gonzalez", "saldo": 338.777 },
        { "dni": 91, "nombre": "Martin", "apellidos": "Gonzalez", "saldo": 523.888 },
        { "dni": 100, "nombre": "Marta", "apellidos": "Gomez", "saldo": 348.777 },
        { "dni": 121, "nombre": "Manuel", "apellidos": "Diaz", "saldo": 234.333 },
        { "dni": 134, "nombre": "Raquel", "apellidos": "Gomez", "saldo": 264.888 },
        { "dni": 154, "nombre": "Luis", "apellidos": "Garcia", "saldo": 744.777 },
        { "dni": 155, "nombre": "Diego", "apellidos": "Garcia", "saldo": 384.888 },
        { "dni": 163, "nombre": "Ana", "apellidos": "Garcia", "saldo": 246.777 },
        { "dni": 171, "nombre": "Eloisa", "apellidos": "Jimenez", "saldo": 234.888 },
        { "dni": 183, "nombre": "Pablo", "apellidos": "Lopez", "saldo": 425.333 },
        { "dni": 190, "nombre": "Gonzalo", "apellidos": "Lopez", "saldo": 564.333 },
        { "dni": 193, "nombre": "Antonio", "apellidos": "Martinez", "saldo": 664.333 },
        { "dni": 194, "nombre": "Pablo", "apellidos": "Martin", "saldo": 864.333 }
    ]
};


// ################# LISTADO DE PERSONAS o BUSCAR POR APELLIDOS ########################
app.get("/personas", function (req, res) {
    res.setHeader("Content-Type", "application/json");
    res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    if(req.query["apellidos"]==undefined){
        res.send(bbdd.personas);
        return;
    } else {
        var apellidos = req.query["apellidos"].toLowerCase();
        var resultado = bbdd.personas.filter(
            function (persona) {
                return persona.apellidos.toLowerCase().indexOf(apellidos) >= 0;
            }
        );
        res.send(resultado);
    }
});
// ################# BUSCAR POR DNI ########################
app.get("/personas/:dni", function (req, res) {
    var dni = req.params.dni;
    var encontrados = bbdd.personas.filter(p => p.dni == dni);
    console.log("Encontrado: ", dni);
    res.setHeader("Content-Type", "application/json");
    res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    res.send(encontrados[0]);
});
// ################# INSERTAR ########################
app.post("/personas", function (req, res) {
    // body: objeto json con los datos recibidos, siempre que 
    // el Content-Type=application/json.
    var nuevo = req.body;
    console.log("Insertando: ", nuevo);
    bbdd.personas.push(nuevo);
    res.setHeader("Content-Type", "application/json");
    res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    res.send(nuevo);
});
// ################# BORRAR ########################
app.delete("/personas/:dni", function (req, res) {
    var dni = req.params.dni;
    var borrados = bbdd.personas.filter(p => p.dni == dni);
    bbdd.personas = bbdd.personas.filter(p => p.dni != dni);
            //filter(function(p){ return p.dni != dni;})
    console.log("Borrando: ", dni);
    res.setHeader("Content-Type", "application/json");
    res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    res.send(borrados[0]);
});
// ################# BORRAR VARIOS ########################
app.post("/borrar_personas", function (req, res) {
    var mDni = req.body;//Matriz de dnis que hay que borrar.
    bbdd.personas = bbdd.personas.filter(
        (p) => {
            for (var i = 0; i < mDni.length; i++) {
                if (p.dni == mDni[i]) {
                    return false;
                }
            }
            return true;
        }
    );
    res.setHeader("Content-Type", "application/json");
    res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    res.send(bbdd.personas);
});
// ################# EDITAR ########################
app.put("/personas", function (req, res) {
    var editando = req.body;
    console.log("Editando: ", editando);
    for(var i=0;i<bbdd.personas.length;i++){
        if(bbdd.personas[i].dni==editando.dni){
            bbdd.personas[i] = editando;
            break;
        }
    }
    res.setHeader("Content-Type", "application/json");
    res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    res.send(editando);
});
// ################# LISTADO DE PROVINCIAS ########################
app.get("/provincias", function (req, res) {
    res.setHeader("Content-Type", "application/json");
    res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    res.send(spain.provincias);
});
// ################# LISTADO DE LOCALIDADES DE UNA PROVINCIA ##############
// localhost:3020/localidades?provincia=28
app.get("/localidades", function (req, res) {
    res.setHeader("Content-Type", "application/json");
    res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    var prov = parseInt(req.query["provincia"]);
    res.send(spain.localidades[prov]);
});

// Cuando la ruta puesta en el navegador no corresponda con ningún 
// archivo estático, entonces que sirva el arhvio A10A_mantenimiento_clientes_routing.html
// app.use(function (req, res) {
//     res.sendFile(__dirname + "/angular/A10A_mantenimiento_clientes_routing.html");
// });


app.listen(3001, function () {
    console.log("Aplicación escuchando en el puerto 3001");
});
