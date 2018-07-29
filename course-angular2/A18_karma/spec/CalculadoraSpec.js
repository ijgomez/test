describe("La clase CalculadoraServicio", function() {
  
  var calc;

  beforeEach(function (){
    calc = new CalculadoraServicio("0", "0", "+");
  });
  // PRUEBAS
  it("se puede incioanlizar en su constructor", function(){
    expect(calc).not.toBeNull();
    expect(calc.txta).toEqual('0');
    expect(calc.txtb).toEqual('0');
    expect(calc.operacion).toEqual('+');

    expect(calc.resultado).toBeUndefined();
  });

  describe('Puede realizar la operacion', function(){

    beforeEach(function (){
      calc.txta = '22';
      calc.txtb = '3';
    });

    it("sumar", function(){
      calc.calcular();

      expect(calc.resultado).toBeDefined();
      expect(calc.resultado).toBe(25);
      
    });

  it("multi", function(){
      calc.operacion = 'x';
      calc.calcular();

      expect(calc.resultado).toBeDefined();
      expect(calc.resultado).toBe(66);
      
    });
  });
});
