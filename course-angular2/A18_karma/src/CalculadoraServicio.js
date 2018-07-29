"use strict";
var CalculadoraServicio = (function () {
    function CalculadoraServicio(txta, txtb, operacion) {
        if (txta === void 0) { txta = undefined; }
        if (txtb === void 0) { txtb = undefined; }
        if (operacion === void 0) { operacion = undefined; }
        this.resultado = undefined;
        this.operaciones = [
            { valor: '+', texto: "sumar" }, { valor: '-', texto: 'restar' },
            { valor: 'x', texto: 'mult.' }, { valor: '/', texto: 'div' }
        ];
        this.txta = txta;
        this.txtb = txtb;
        this.operacion = operacion;
    }
    CalculadoraServicio.prototype.calcular = function () {
        var a = parseFloat(this.txta);
        var b = parseFloat(this.txtb);
        if (this.operacion == '+') {
            this.resultado = a + b;
        }
        else if (this.operacion === '-') {
            this.resultado = a - b;
        }
        else if (this.operacion === 'x') {
            this.resultado = a * b;
        }
        else if (this.operacion === '/') {
            this.resultado = a / b;
        }
    };
    return CalculadoraServicio;
}());
//exports.CalculadoraServicio = CalculadoraServicio;
//# sourceMappingURL=CalculadoraServicio.js.map