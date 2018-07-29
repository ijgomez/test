
let Rx = require('rx');

//=================DEFINICIO DEL PROCESO LENTO===========================
let observable = Rx.Observable.create(function (observer) {

    var pares = []; // Matriz de pares encontrados.
    for (let i = 0; i <= 100; i+=2) {
        pares.push(i);//guardamos en la matriz el par actual.
        
    }

    //Avisamos a los observadores qeu hemos completado el unico paso.
    observer.onNext({pares: pares});
    //Avisamos a los observadores que el proceso ha terminado.
    observer.onCompleted();
});


//=================SUSCRIPCION AL PROCESO LENTO===========================
// observable.subscribe(callbackNext, callbackError, callbackCompleted);
var resul;

//Como solo hay un paso, nos suscribimos solamente al next
observable.subscribe(
    function (datos) { //Next
        console.log(datos.pares);
    }
);