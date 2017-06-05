package com.blogspot.h3dema;

/**
 * ***********************************************************************
 *
 * classe para manipulação de Splay Trees<br/>
 * permite execução de inserção, pesquisa e exclusão<br/>
 * realiza splay em toda operação
 *
 *
 * @author Henrique
 * @param <K> chave
 * @param <V> valor
 */
public class Splay<K extends Comparable<K>, V> {

    private No raiz;   // raiz da árvore splay

    /**
     * classe interna que descreve como é o nó da árvore
     */
    private class No {

        private K chave;       // chave do nó
        private V valor;       // dado contido no nó
        private No esquerda;   // subárvores à esquerda
        private No direita;    // subárvores à direita do nó

        /**
         * construtor
         *
         * @param chave
         * @param valor
         */
        public No(K chave, V valor) {
            this.chave = chave;
            this.valor = valor;
        }
    }

    /**
     * retorna se contem o nó representado pela chave
     *
     * @param chave
     * @return
     */
    public boolean existe(K chave) {
        return (pesquisa(chave) != null);
    }

    /**
     * retorna a altura da árvore<br/>
     * note que uma árvore com um nó tem altura zero
     *
     * @return altura da árvore
     */
    public int altura() {
        return altura(raiz);
    }

    /**
     * calcula a altura a partir do nó x
     *
     * @param x nó "x"
     * @return altura ou -1 se nó for nulo
     */
    private int altura(No x) {
        if (x == null) {
            return -1;
        }
        return 1 + Math.max(altura(x.esquerda), altura(x.direita));
    }

    /**
     *
     * @return retorna o tamanho da árvore a partir da raiz
     */
    public int tamanho() {
        return tamanho(raiz);
    }

    /**
     * retorna o tamanho
     *
     * @param x nó selecionado
     * @return tamanho
     */
    private int tamanho(No x) {
        if (x == null) {
            return 0;
        } else {
            return (1 + tamanho(x.esquerda) + tamanho(x.direita));
        }
    }

    /**
     * realiza rotação para direita no nó
     */
    private No rotacaoDireita(No no) {
        No x = no.esquerda;
        no.esquerda = x.direita;
        x.direita = no;
        return x;
    }

    /**
     * realiza rotação para direita no nó
     */
    private No rotacaoEsquerda(No no) {
        No x = no.direita;
        no.direita = x.esquerda;
        x.esquerda = no;
        return x;
    }

    /**
     * realiza splay na árvore cuja raiz é o nó "no"<br/>
     * se o nó com a chave indicada existir, este nó é deslocado para a raiz da
     * árvore <br/>
     * se não existir, o último nó acessado durante a pesquisa torna-se a raiz
     */
    private No splay(No no, K chave) {
        // se nó é nulo, nada a fazer
        if (no == null) {
            return null;
        }

        // verifica se maior ou menor
        int cmp1 = chave.compareTo(no.chave);

        if (cmp1 < 0) {
            if (no.esquerda == null) {
                // chave não existe na árvore
                return no;
            }
            int cmp2 = chave.compareTo(no.esquerda.chave);
            if (cmp2 < 0) {
                // faz splay recursivamente
                no.esquerda.esquerda = splay(no.esquerda.esquerda, chave);
                no = rotacaoDireita(no);
            } else if (cmp2 > 0) {
                // faz splay recursivamente
                no.esquerda.direita = splay(no.esquerda.direita, chave);
                if (no.esquerda.direita != null) {
                    no.esquerda = rotacaoEsquerda(no.esquerda);
                }
            }
            if (no.esquerda == null) {
                return no;
            } else {
                return rotacaoDireita(no);
            }
        } else if (cmp1 > 0) {
            if (no.direita == null) {
                // chave não existe na árvore
                return no;
            }

            int cmp2 = chave.compareTo(no.direita.chave);
            if (cmp2 < 0) {
                // faz splay recursivamente
                no.direita.esquerda = splay(no.direita.esquerda, chave);
                if (no.direita.esquerda != null) {
                    no.direita = rotacaoDireita(no.direita);
                }
            } else if (cmp2 > 0) {
                // faz splay recursivamente
                no.direita.direita = splay(no.direita.direita, chave);
                no = rotacaoEsquerda(no);
            }

            if (no.direita == null) {
                return no;
            } else {
                return rotacaoEsquerda(no);
            }
        } else {
            return no;
        }
    }

    /**
     * pesquisa splay
     *
     * @param chave chave que será utilizada na pesquisa
     * @return retorna o valor associado ao nó com a chave indicada ou nulo
     */
    public V pesquisa(K chave) {
        raiz = splay(raiz, chave);
        int comparacao = chave.compareTo(raiz.chave);
        if (comparacao == 0) {
            return raiz.valor;
        } else {
            return null;
        }
    }

    /**
     * inserção splay
     *
     * @param chave insere o nó com a chave
     * @param valor valor a ser armazenado
     */
    public void inserir(K chave, V valor) {
        /**
         * se a raiz não existe, deve criar o nó como raiz
         */
        if (raiz == null) {
            raiz = new No(chave, valor);
            return;
        }

        /**
         * caso contrário, faz primeiro o splay
         */
        raiz = splay(raiz, chave);

        // agora faz a inserção
        int cmp = chave.compareTo(raiz.chave);
        if (cmp < 0) {
            // insere o novo nó como raiz
            No n = new No(chave, valor);
            n.esquerda = raiz.esquerda;
            n.direita = raiz;
            raiz.esquerda = null;
            raiz = n;
        } else if (cmp > 0) {
            No n = new No(chave, valor);
            n.direita = raiz.direita;
            n.esquerda = raiz;
            raiz.direita = null;
            raiz = n;
        } else if (cmp == 0) {
            // a chave está duplicada, portanto somente substituimos o valor armazenado
            raiz.valor = valor;
        }

    }

    /**
     * exclusão splay<br/>
     *
     * este método faz o splay do nó indicado pela chave e em seguida realizada
     * a exclusão utilizado Hibbard modificado. Depois do splay, o nó raiz ou é
     * o nó a ser excluido ou não existe o nó com a chave indicada.<br/>
     *
     * @param chave chave do nó a ser apagado
     */
    public void remover(K chave) {
        // árvore vazia, nada para excluir
        if (raiz == null) {
            return;
        }

        /**
         * caso contrário, faz primeiro o splay
         */
        raiz = splay(raiz, chave);
        int cmp = chave.compareTo(raiz.chave);

        if (cmp == 0) {
            if (raiz.esquerda == null) {
                raiz = raiz.direita;
            } else {
                No x = raiz.direita;
                raiz = raiz.esquerda;
                splay(raiz, chave);
                raiz.direita = x;
            }
        }
        // else: chave não está na árvore para poder ser apagada
    }

}
