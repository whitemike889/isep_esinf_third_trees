/*
 */
package model;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import tree.ArvorePoligonos;

/**
 *
 * @author pc asus
 */
public class AppTest {

    private static final String FX_NOME_LADOS = "teste_nome_lados.txt";
    private static final String FX_LADOS_NOMES = "teste_lados_nome.txt";

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
        assertEquals("Numero de poligonos Unidades deve ser 9", instance.qtdPoligonosUnidades(), qtdUnidades);
        assertEquals("Numero de poligonos Dezenas deve ser 27", instance.qtdPoligonosDezenas(), qtdDezenas);
        assertEquals("Numero de poligonos Centenas deve ser 9", instance.qtdPoligonosCentenas(), qtdCentenas);

        assertEquals("O total de poligonos criados deve ser 45", instance.qtdPoligonosTotal(), qtdTotal);
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
        String result = instance.construirNomeDoPoligono(numlados);
        assertEquals(expResult, result);
    }

    @Test
    public void testNomeDadoOLado() {
        System.out.println("test NomeLados");
        App app = new App();
        app.lerDados();
        ArvorePoligonos arvore_pol_gerados = app.construirArvorePoligonos();

        Ficheiro f = new Ficheiro();
        List<String> lista = f.lerFicheiro(AppTest.FX_LADOS_NOMES);
        ArvorePoligonos arvore_pol_lidos = new ArvorePoligonos();

        for (String s : lista) {
            String ls[] = s.split(";");
            Poligono p = new Poligono(Integer.parseInt(ls[0]), ls[1]);
            arvore_pol_lidos.insert(p);
        }

        for (Poligono p : arvore_pol_lidos.inOrder()) {
            assertTrue("O poligono lido é igual ao poligono gerado",arvore_pol_gerados.procurarSePoligonoExiste(p));
        }

    }

}
