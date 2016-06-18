package com.blogspot.tsprates.arvore_binaria;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AppTest
{

    private final ArvoreBinaria arv = new ArvoreBinaria();

    @Test
    public void testAdicionaRaiz()
    {
        arv.adiciona(new No(1));
        assertEquals("[1]", arv.emOrdem());
    }

    @Test
    public void testAdicionaDoisNos()
    {
        arv.adiciona(new No(2));
        arv.adiciona(new No(1));
        assertEquals("[1, 2]", arv.emOrdem());
    }

    @Test
    public void testAdicionaMaisNos()
    {
        arv.adiciona(new No(2));
        arv.adiciona(new No(1));
        arv.adiciona(new No(5));
        arv.adiciona(new No(4));
        arv.adiciona(new No(3));
        assertEquals("[1, 2, 3, 4, 5]", arv.emOrdem());
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveArvoreSemSerIniciada()
    {
        arv.remove(1);
    }

    @Test
    public void testRemoveFolha()
    {
        arv.adiciona(new No(2));
        arv.adiciona(new No(1));
        arv.adiciona(new No(5));
        arv.adiciona(new No(4));
        arv.adiciona(new No(3));
        arv.remove(3);
        assertEquals("[1, 2, 4, 5]", arv.emOrdem());
    }

    @Test
    public void testRemoveNoComFolhaEsquerda()
    {
        arv.adiciona(new No(2));
        arv.adiciona(new No(1));
        arv.adiciona(new No(5));
        arv.adiciona(new No(4));
        arv.adiciona(new No(3));
        arv.remove(4);
        assertEquals("[1, 2, 3, 5]", arv.emOrdem());
    }

    @Test
    public void testRemoveNoInterno()
    {
        arv.adiciona(new No(2));
        arv.adiciona(new No(1));
        arv.adiciona(new No(5));
        arv.adiciona(new No(4));
        arv.adiciona(new No(3));
        arv.adiciona(new No(8));
        arv.adiciona(new No(7));
        arv.adiciona(new No(6));
        arv.remove(5);
        assertEquals("[1, 2, 3, 4, 6, 7, 8]", arv.emOrdem());
    }

    @Test
    public void testRemoveRaiz()
    {
        arv.adiciona(new No(2));
        arv.adiciona(new No(1));
        arv.adiciona(new No(5));
        arv.adiciona(new No(4));
        arv.adiciona(new No(3));
        arv.adiciona(new No(8));
        arv.adiciona(new No(7));
        arv.adiciona(new No(6));
        arv.remove(2);
        assertEquals("[1, 3, 4, 5, 6, 7, 8]", arv.emOrdem());
    }

    @Test
    public void testRemoveNoNaoEncontradoNaArvoreNaoFazNada()
    {
        arv.adiciona(new No(2));
        arv.adiciona(new No(1));
        arv.adiciona(new No(5));
        arv.adiciona(new No(4));
        arv.adiciona(new No(3));
        arv.adiciona(new No(8));
        arv.adiciona(new No(7));
        arv.adiciona(new No(6));
        arv.remove(20);
        assertEquals("[1, 2, 3, 4, 5, 6, 7, 8]", arv.emOrdem());
    }

    @Test
    public void testAdicionaERemoveNoParaArvoreVazia()
    {
        arv.adiciona(new No(2));
        arv.remove(2);
        assertEquals("[]", arv.getOrdem(ArvoreBinaria.TipoOrdem.EM_ORDEM));
    }

    @Test
    public void testPreOrdem()
    {
        arv.adiciona(new No(2));
        arv.adiciona(new No(7));
        arv.adiciona(new No(8));
        arv.adiciona(new No(5));
        arv.adiciona(new No(9));

        assertEquals("[2, 7, 5, 8, 9]", arv.preOrdem());
    }

    @Test
    public void testPosOrdem()
    {
        arv.adiciona(new No(2));
        arv.adiciona(new No(7));
        arv.adiciona(new No(8));
        arv.adiciona(new No(5));
        arv.adiciona(new No(9));

        assertEquals("[5, 9, 8, 7, 2]", arv.posOrdem());
    }
}
