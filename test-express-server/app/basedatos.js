var utils = require("./helpers/utils.js");

// ########################### INFORMES  ###########################
var informes = [
    { id: 1, name: 'Report 1', description: 'Description of Report 1'},
    { id: 2, name: 'Report 2', description: 'Description of Report 2'},
    { id: 3, name: 'Report 3', description: 'Description of Report 3'},
    { id: 4, name: 'Report 4', description: 'Description of Report 4'},
    { id: 5, name: 'Report 5', description: 'Description of Report 5'},
    { id: 6, name: 'Report 6', description: 'Description of Report 6'},
    { id: 7, name: 'Report 7', description: 'Description of Report 7'},
    { id: 8, name: 'Report 8', description: 'Description of Report 8'},
    { id: 9, name: 'Report 9', description: 'Description of Report 9'},
    { id: 10, name: 'Report 10', description: 'Description of Report 10'},
    { id: 11, name: 'Report 11', description: 'Description of Report 11'},
    { id: 12, name: 'Report 12', description: 'Description of Report 12'},
    { id: 13, name: 'Report 13', description: 'Description of Report 13'},
    { id: 14, name: 'Report 14', description: 'Description of Report 14'},
    { id: 15, name: 'Report 15', description: 'Description of Report 15'}
];
module.exports.informes = informes;

// ########################### SOPORTE   ###########################
var propiedades = [
    { id: 1, code: 'PROPERTY.1', value: utils.ramdonString(20)},
    { id: 2, code: 'PROPERTY.2', value: utils.ramdonString(20)},
    { id: 3, code: 'PROPERTY.3', value: utils.randomNumber(0, 60000)},
    { id: 4, code: 'PROPERTY.4', value: utils.randomNumber(0, 10)},
    { id: 5, code: 'PROPERTY.5', value: utils.ramdonString(20)},
];
var trazas = [
    {id: 1, dateTime: new Date(), message: utils.ramdonString(50)},
    {id: 2, dateTime: new Date(), message: utils.ramdonString(50)},
    {id: 3, dateTime: new Date(), message: utils.ramdonString(50)},
    {id: 4, dateTime: new Date(), message: utils.ramdonString(50)},
    {id: 5, dateTime: new Date(), message: utils.ramdonString(50)},
    {id: 6, dateTime: new Date(), message: utils.ramdonString(50)},
    {id: 7, dateTime: new Date(), message: utils.ramdonString(50)},
    {id: 8, dateTime: new Date(), message: utils.ramdonString(50)},
    {id: 9, dateTime: new Date(), message: utils.ramdonString(50)},
    {id: 10, dateTime: new Date(), message: utils.ramdonString(50)}
];
module.exports.propiedades = propiedades;
module.exports.trazas = trazas;
// ########################### SEGURIDAD ###########################
var usuarios = [
    { id: 1, name: 'user1', profiles: []},
    { id: 2, name: 'user2', profiles: []},
    { id: 3, name: 'user3', profiles: []}
];
var perfiles = [
    { id: 1, name: 'ADMINISTRATOR', actions: []},
    { id: 2, name: 'SUPPORT', actions: []},
    { id: 3, name: 'OPERATOR', actions: []}
];
var acciones = [
    {id: 1, name: 'REPORTS'},
    {id: 2, name: 'SUPPORT'},
    {id: 3, name: 'SECURITY'}
];
module.exports.usuarios = usuarios;
module.exports.perfiles = perfiles;
module.exports.acciones = acciones;


