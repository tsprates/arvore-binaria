package com.blogspot.tsprates.arvore_binaria;

public class ArvoreBinaria
{

    private No raiz = null;

    public final static int EM_ORDEM = 1, PRE_ORDEM = 2, POS_ORDEM = 3;

    public void adiciona(No no)
    {

        if (raiz == null)
        {
            raiz = no;
        }
        else
        {
            No iter = raiz;
            No atual = null;

            do
            {
                atual = iter;
                iter = getFilho(iter, no.getValor());
            }
            while (iter != null);

            if (atual.getValor() > no.getValor())
            {
                atual.esquerda = no;
            }
            else
            {
                atual.direita = no;
            }
        }

    }

    public void remove(int valor)
    {
        if (raiz == null)
        {
            throw new IllegalStateException("Não há nenhum elemento na árvore.");
        }
        else
        {
            No iter = raiz;
            No iterPai = null;

            do
            {
                if (iter.getValor() == valor)
                {
                    break;
                }

                iterPai = iter;
                iter = getFilho(iter, valor);
            }
            while (iter != null);

            // não encontrado nó
            if (iter == null)
            {
                return;
            }

            if (iter.direita == null && iter.esquerda == null)
            {
                atualizaFilho(valor, iterPai, null);
            }
            else if (iter.direita != null && iter.esquerda == null)
            {
                atualizaFilho(valor, iterPai, iter.direita);
            }
            else if (iter.direita == null && iter.esquerda != null)
            {
                atualizaFilho(valor, iterPai, iter.esquerda);
            }
            else
            {
                if (iter.direita != null && iter.esquerda != null)
                {
                    No aux = iter.direita;
                    No paiAux = iter;

                    do
                    {
                        paiAux = aux;
                        aux = aux.esquerda;
                    }
                    while (aux != null && aux.esquerda != null);

                    if (aux == null)
                    {
                        atualizaFilho(valor, iterPai, paiAux);
                        paiAux.esquerda = iter.esquerda;
                    }
                    else
                    {
                        atualizaFilho(valor, iterPai, aux);
                        aux.direita = iter.direita;
                        aux.esquerda = iter.esquerda;
                        paiAux.esquerda = null;
                    }
                }
            }
        }

    }

    private void atualizaFilho(int valor, No pai, No novo)
    {
        if (pai == null)
        {
            raiz = novo;
        }
        else
        {
            if (pai.getValor() > valor)
            {
                pai.esquerda = novo;
            }
            else
            {
                pai.direita = novo;
            }
        }
    }

    private No getFilho(No pai, int valor)
    {
        if (pai.getValor() > valor)
        {
            return pai.esquerda;
        }
        else
        {
            return pai.direita;
        }
    }

    public String emOrdem()
    {
        return getOrdem(EM_ORDEM);
    }

    public String preOrdem()
    {
        return getOrdem(PRE_ORDEM);
    }

    public String posOrdem()
    {
        return getOrdem(POS_ORDEM);
    }

    public String getOrdem(int tipoOrdem)
    {
        StringBuilder sb = new StringBuilder("[");

        if (raiz != null)
        {
            switch (tipoOrdem)
            {
                case PRE_ORDEM:
                    preOrdem(raiz, sb);
                    break;
                case POS_ORDEM:
                    posOrdem(raiz, sb);
                    break;
                default:
                case EM_ORDEM:
                    emOrdem(raiz, sb);
                    break;
            }

            sb.setLength(sb.length() - 2);
        }

        sb.append("]");
        return sb.toString();
    }

    private void emOrdem(No no, StringBuilder sb)
    {
        if (no != null)
        {
            emOrdem(no.esquerda, sb);
            sb.append(no.getValor()).append(", ");
            emOrdem(no.direita, sb);
        }
    }

    private void posOrdem(No no, StringBuilder sb)
    {
        if (no != null)
        {
            posOrdem(no.esquerda, sb);
            posOrdem(no.direita, sb);
            sb.append(no.getValor()).append(", ");
        }
    }

    private void preOrdem(No no, StringBuilder sb)
    {
        if (no != null)
        {
            sb.append(no.getValor()).append(", ");
            preOrdem(no.esquerda, sb);
            preOrdem(no.direita, sb);
        }
    }
}
