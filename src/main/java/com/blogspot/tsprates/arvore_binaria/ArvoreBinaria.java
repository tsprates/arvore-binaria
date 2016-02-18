package com.blogspot.tsprates.arvore_binaria;

public class ArvoreBinaria {

    private No raiz = null;

    public final static int EM_ORDEM = 1, PRE_ORDEM = 2, POS_ORDEM = 3;

    public void adiciona(No no) {

	if (raiz == null) {
	    raiz = no;
	} else {
	    No iter = raiz;
	    No atual = null;

	    do {
		atual = iter;
		iter = getFilho(iter, no.getValor());
	    } while (iter != null);

	    if (atual.getValor() > no.getValor()) {
		atual.esquerda = no;
	    } else {
		atual.direita = no;
	    }
	}

    }

    public void remove(int valor) {
	if (raiz == null) {
	    throw new IllegalStateException("Não há nenhum elemento na árvore.");
	} else {
	    No iter = raiz;
	    No iterAnterior = null;

	    do {
		if (iter.getValor() == valor) {
		    break;
		}

		iterAnterior = iter;
		iter = getFilho(iter, valor);
	    } while (iter != null);

	    // não encontrado nó
	    if (iter == null) {
		return;
	    }

	    if (iter.direita == null && iter.esquerda == null) {
		atualizaFilho(valor, iterAnterior, null);
	    } else if (iter.direita != null && iter.esquerda == null) {
		atualizaFilho(valor, iterAnterior, iter.direita);
	    } else if (iter.direita == null && iter.esquerda != null) {
		atualizaFilho(valor, iterAnterior, iter.esquerda);
	    } else if (iter.direita != null && iter.esquerda != null) {
		No aux = iter.direita;
		No auxAnterior = iter;

		do {
		    auxAnterior = aux;
		    aux = aux.esquerda;
		} while (aux != null && aux.esquerda != null);

		if (aux == null) {
		    atualizaFilho(valor, iterAnterior, auxAnterior);
		    auxAnterior.esquerda = iter.esquerda;
		} else {
		    atualizaFilho(valor, iterAnterior, aux);
		    aux.direita = iter.direita;
		    aux.esquerda = iter.esquerda;
		    auxAnterior.esquerda = null;
		}
	    }
	}

    }

    private void atualizaFilho(int valor, No iterAnterior, No novo) {
	if (iterAnterior == null) {
	    raiz = novo;
	} else if (iterAnterior.getValor() > valor) {
	    iterAnterior.esquerda = novo;
	} else {
	    iterAnterior.direita = novo;
	}
    }

    private No getFilho(No pai, int valor) {
	if (pai.getValor() > valor) {
	    return pai.esquerda;
	} else {
	    return pai.direita;
	}
    }

    public String getEmOrdem() {
	return mostraOrdem(EM_ORDEM);
    }

    public String getPreOrdem() {
	return mostraOrdem(PRE_ORDEM);
    }

    public String getPosOrdem() {
	return mostraOrdem(POS_ORDEM);
    }

    public String mostraOrdem(int tipoOrdem) {
	StringBuilder sb = new StringBuilder("[");

	if (raiz != null) {
	    switch (tipoOrdem) {
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

    private void emOrdem(No no, StringBuilder sb) {
	if (no != null) {
	    emOrdem(no.esquerda, sb);
	    sb.append(no.getValor()).append(", ");
	    emOrdem(no.direita, sb);
	}
    }

    private void posOrdem(No no, StringBuilder sb) {
	if (no != null) {
	    posOrdem(no.esquerda, sb);
	    posOrdem(no.direita, sb);
	    sb.append(no.getValor()).append(", ");
	}
    }

    private void preOrdem(No no, StringBuilder sb) {
	if (no != null) {
	    sb.append(no.getValor()).append(", ");
	    preOrdem(no.esquerda, sb);
	    preOrdem(no.direita, sb);
	}
    }
}