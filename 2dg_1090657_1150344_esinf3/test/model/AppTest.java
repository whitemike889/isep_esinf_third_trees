/*
 */
package model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pc asus
 */
public class AppTest {
    
    /**
     * Test of lerDados method, of class App.
     */
    @Test
    public void testLerDados() {
        System.out.println("lerDados");
        App instance = new App();
        instance.lerDados();
        final int qtdUnidades = 9;
        final int qtdDezenas = 27;
        final int qtdCentenas = 9;
        final int qtdTotal = qtdUnidades + qtdDezenas + qtdCentenas;
        assertEquals("Numero de poligonos Unidades deve ser 9",instance.qtdPoligonosUnidades(),qtdUnidades);
        assertEquals("Numero de poligonos Dezenas deve ser 27",instance.qtdPoligonosDezenas(),qtdDezenas);
        assertEquals("Numero de poligonos Centenas deve ser 9",instance.qtdPoligonosCentenas(),qtdCentenas);
        
        assertEquals("O total de poligonos criados deve ser 45",instance.qtdPoligonosTotal(),qtdTotal);
    }

    /**
     * Test of construirPoligono method, of class App.
     */
    @Test
    public void testConstruirPoligono() {
        System.out.println("construirPoligono");
        int numlados = 119;
        App instance = new App();
        instance.lerDados();
        String expResult = "hectaenneakaidecagon";
        String result = instance.construirPoligono(numlados);
        assertEquals(expResult, result);
    }
    
}
