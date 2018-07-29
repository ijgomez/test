
let Rx = require('rx');

// los observables realizan un proceso que no termina inmediatamente.
// el observable puede informar de un cambio o de la finalizacion del proceso 
// a todos los objetos que se hallan subscrito al observable, estos subscritores indicaran las funcion
// que se debe ejecutar cuando el observable le pase un mensaje.

//=================DEFINICIO DEL PROCESO LENTO===========================
let observable = Rx.Observable.create(function (observer) {
    //------- La funcion pasada al metodo create es el proceso lento ----
    // Este proceso lento empieza inmediatamente, juesto despues de llamar al metodo create.
    // Mediante el observer el proceso lento puede avisar a los suscritores de un cambio o de la finalizacion del proceso.

    var pares = []; // Matriz de pares encontrados.
    for (let i = 0; i <= 100; i+=2) {
        pares.push(i);//guardamos en la matriz el par actual.
        //avisar a los observadores que hemos completado un paso.
        observer.onNext({paso: i, pares: pares});
    }

    //Avisamos a los observadores que el proceso ha terminado.
    observer.onCompleted();
});


//=================SUSCRIPCION AL PROCESO LENTO===========================
// observable.subscribe(callbackNext, callbackError, callbackCompleted);
var resul;


observable.subscribe(
    function (datos) { //Next
        console.log('paso', datos.paso);
        resul = datos.pares;
    },
    function (datos) { // Error
        console.error(datos);
    },
    function () { // Completed
        console.log('==================== FIN ===========================');
        console.log(resul);
    }
);