package com.blogspot.tsprates.arvore_binaria;

public class No
{
    private final int valor;

    public No direita, esquerda;

    public No( int valor )
    {
        this.valor = valor;
    }

    public int getValor()
    {
        return valor;
    }

}
