/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.blogspot.h3dema;

/**
 *
 * @author Henrique
 */
public class Splaytree {
    
    /**
     * testa a classe splay
     * @param args
     */
    public static void main(String[] args){
        
        System.out.println("Exemplo com valores inteiros:");
        Splay<Integer, Integer> st1 = new Splay<>();
        System.out.println("Altura da árvore: "+st1.altura());
        System.out.println("Tamanho da árvore: "+st1.tamanho());
        st1.inserir(5, 5);
        System.out.println("Altura da árvore: "+st1.altura());
        System.out.println("Tamanho da árvore: "+st1.tamanho());
        st1.inserir(9, 9);
        System.out.println("Altura da árvore: "+st1.altura());
        System.out.println("Tamanho da árvore: "+st1.tamanho());
        st1.inserir(13, 13);
        System.out.println("Altura da árvore: "+st1.altura());
        System.out.println("Tamanho da árvore: "+st1.tamanho());
        st1.inserir(11, 11);
        System.out.println("Altura da árvore: "+st1.altura());
        System.out.println("Tamanho da árvore: "+st1.tamanho());
        st1.inserir(3, 1);
        System.out.println("Altura da árvore: "+st1.altura());
        System.out.println("Tamanho da árvore: "+st1.tamanho());
        st1.inserir(1, 1);
        System.out.println("Altura da árvore: "+st1.altura());
        System.out.println("Tamanho da árvore: "+st1.tamanho());
        System.out.println("");
        
        
        System.out.println("Exemplo com valores string:");
        Splay<String, String> st = new Splay<String, String>();
        // inserir 8 animais
        st.inserir("Arctica islandica", "410 anos");
        st.inserir("Turritopsis Nutricula", "500 anos");
        st.inserir("Koi","226 anos");
        st.inserir("Baleia-da-Groenlândia" ,"210 anos");
        st.inserir("Tartaruga-rajada","188 anos");
        st.inserir("Avestruz", "50 anos");
        st.inserir("Elefante", "60 anos");
        st.inserir("Papagaio", "80 anos");
        // 
        System.out.println("Altura da árvore: "+st.altura());
        System.out.println("Tamanho da árvore: "+st.tamanho());
        st.remover("Turritopsis Nutricula");
        System.out.println("-1: " + st.tamanho());
        st.remover("Koi");
        System.out.println("-1: " + st.tamanho());
        st.remover("Elefante");
        System.out.println("-1: " + st.tamanho());
        System.out.println("Pesquisa dados em Baleia-da-Groenlândia:"+st.pesquisa("Baleia-da-Groenlândia"));
        System.out.println("Altura da árvore: "+st.altura());
        System.out.println("Tamanho da árvore: "+st.tamanho());
        System.out.println();
    
    }
    
}
