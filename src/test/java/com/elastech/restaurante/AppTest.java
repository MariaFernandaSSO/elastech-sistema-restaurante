package com.elastech.restaurante;

import com.elastech.restaurante.cardapio.ComBaconExtra;
import com.elastech.restaurante.cardapio.ComCheddar;
import com.elastech.restaurante.cardapio.ItemCardapio;
import com.elastech.restaurante.cardapio.Lanche;
import com.elastech.restaurante.cardapio.LancheFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AppTest {

    @Test
    void doisMaisDoisSaoQuatro() {
        assertEquals(4, 2 + 2);
    }

    @Test
    void precoNegativoLancaExcecao() {
        assertThrows(IllegalArgumentException.class, () -> new Lanche("X-Teste", -1.0));
    }

    @Test
    void xBaconComCheddarCusta26() {
        ItemCardapio lanche = new ComCheddar(LancheFactory.xBacon());
        assertEquals(26.0, lanche.getPreco(), 0.001);
    }

    @Test
    void xBaconComCheddarEBaconExtraCusta32() {
        ItemCardapio lanche = new ComCheddar(LancheFactory.xBacon());
        lanche = new ComBaconExtra(lanche);
        assertEquals(32.0, lanche.getPreco(), 0.001);
        assertEquals("X-Bacon + cheddar + bacon extra", lanche.getDescricao());
    }
}
