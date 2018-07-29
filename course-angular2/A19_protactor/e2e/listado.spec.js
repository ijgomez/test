
describe('La seccion de mantenimiento', function() {
  browser.get('http://localhost:3000');
  it('tiene inicialmente 16 clientes', function() {
    //########## LISTADO ANTES ###################
    var filas = element.all(by.css('table.datos')).all(by.css('tbody tr'));
    expect(filas.count()).toEqual(16);
    expect(filas.get(0).element(by.css('td')).getText()).toEqual('22'mvn );
  });
  it('permite insertar un cliente', function() {
    //########## INSERTAR ###################
    // En angular 2 no está imiplementado by.model ni by.repeater.
    element(by.css('input#dni')).sendKeys('444');
    element(by.css('input#nombre')).sendKeys('Pepe');
    element(by.css('input#apellidos')).sendKeys('Ramirez');
    element(by.css('input#saldo')).sendKeys('4000');
    browser.sleep(6000);//Dejamos un tiempo antes de insertar para ver las cajas rellenas
    element(by.css('p.botones>button')).click();
    //########## LISTADO DESPUES ###################
    var filas = element.all(by.css('table.datos')).all(by.css('tbody tr'));
    expect(filas.count()).toEqual(17);
    expect(filas.get(16).element(by.css('td')).getText()).toEqual('444');
    browser.sleep(6000);//Dejamos un tiempo para ver el resultado
  });
});

