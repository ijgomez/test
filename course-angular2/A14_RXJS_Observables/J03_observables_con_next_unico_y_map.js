
let Rx = require('rx');

//=================DEFINICIO DEL PROCESO LENTO===========================
let observable = Rx.Observable.create(function (observer) {

    var pares = []; // Matriz de pares encontrados.
    for (let i = 0; i <= 100; i+=2) {
        pares.push(i);//guardamos en la matriz el par actual.
        
    }

    //Avisamos a los observadores qeu hemos completado el unico paso.
    observer.onNext({pares: pares});//Retorno de los datos en bruto.
    //Avisamos a los observadores que el proceso ha terminado.
    observer.onCompleted();
});

//Queremos recibir los datos, formateados y separados por almoadibllas.
//=================PROCESODO INTERMEDIO ===========================
// La funcion map permite procesar el  resultado que emite el observable.
let observable2 = observable.map(function (datosEnBruto){
    let datosFormateados = "";

    for (let i = 0; i < datosEnBruto.pares.length; i++) {
        datosFormateados += datosEnBruto.pares[i] + '#';
    }

    return datosFormateados;
});


//=================SUSCRIPCION AL PROCESO LENTO===========================
// observable.subscribe(callbackNext, callbackError, callbackCompleted);
var resul;

//Como solo hay un paso, nos suscribimos solamente al next
observable2.subscribe(
    function (datos) { //Next
        console.log(datos);
    }
);