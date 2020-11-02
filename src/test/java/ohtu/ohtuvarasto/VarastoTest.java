package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
        varasto.lisaaVarastoon(-1);
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
        varasto.lisaaVarastoon(varasto.paljonkoMahtuu() + 1);
        assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);
        assertEquals(2, saatuMaara, vertailuTarkkuus);
        assertEquals(0.0, varasto.otaVarastosta(-1), vertailuTarkkuus);
        assertEquals(varasto.getSaldo(), varasto.otaVarastosta(varasto.getSaldo() + 1), vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void kelvotonVarasto() {
        Varasto kelvoton = new Varasto(0);
        assertEquals(0, kelvoton.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void kuormitettuVarasto() {
        Varasto kuormitettu = new Varasto(10, 1);
        assertEquals(10, kuormitettu.getTilavuus(), vertailuTarkkuus);
        assertEquals(1, kuormitettu.getSaldo(), vertailuTarkkuus);
        Varasto kelvoton = new Varasto(0, 1);
        assertEquals(0, kelvoton.getTilavuus(), vertailuTarkkuus);
        Varasto negatiivinenSaldo = new Varasto(10, -1);
        assertEquals(0, negatiivinenSaldo.getSaldo(), vertailuTarkkuus);
        Varasto ylivuoto = new Varasto(10, 11);
        assertEquals(10, ylivuoto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void toStringTest() {
        assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }

}