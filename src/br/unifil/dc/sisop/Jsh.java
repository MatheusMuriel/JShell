package br.unifil.dc.sisop;

import java.util.Scanner;

/**
 * Write a description of class Jsh here.
 *
 * @author Ricardo Inacio Alvares e Silva
 * @version 180823
 */
public final class Jsh {
    
    /**
    * Funcao principal do Jsh.
    */
    public static void promptTerminal() {

        while (true) {
    		exibirPrompt();
    		ComandoPrompt comandoEntrado = lerComando();
    		executarComando(comandoEntrado);
    	}
    }

    /**
    * Escreve o prompt na saida padrao para o usuário reconhecê-lo e saber que o
    * terminal está pronto para receber o próximo comando como entrada.
    */
    public static void exibirPrompt() {
        /*
        1. (15 pontos) Implemente o método exibirPrompt(), que escreve o prompt de linha de
        comando do shell, que indica qual o usuário está utilizando e qual o diretório de trabalho
        atual. O prompt do nosso shell é da forma “login#UID:workdir>”. Como exemplo, se
        tivermos um usuário cujo UID é 1337, nome de login é “alunos2012”, e o diretório atual
        de trabalho1 do processo do shell é “/home/shared/”, o prompt será da seguinte forma:
        alunos2012#1337:/home/shared/%
         */
        System.out.println("Olar");
        String usuario_nome = System.getProperty("user.name"); //Nome do usuario logado
        String usuario_diretorio = System.getProperty("user.dir"); //Nome do usuario logado
        System.out.print(usuario_nome + "#" + "UID" + ":" + usuario_diretorio + "%" );
//        throw new RuntimeException("Método ainda não implementado.");
    }

    /**
    * Preenche as strings comando e parametros com a entrada do usuario do terminal.
    * A primeira palavra digitada eh sempre o nome do comando desejado. Quaisquer
    * outras palavras subsequentes sao parametros para o comando. A palavras sao
    * separadas pelo caractere de espaco ' '. A leitura de um comando eh feita ate
    * que o usuario pressione a tecla <ENTER>, ou seja, ate que seja lido o caractere
    * EOL (End Of Line).
    *
    * @return 
    */
    public static ComandoPrompt lerComando() {

        Scanner input = new Scanner(System.in);
        ComandoPrompt entrada = new ComandoPrompt(input.nextLine());
        return entrada;
        //throw new RuntimeException("Método ainda não implementado.");
    }

    /**
    * Recebe o comando lido e os parametros, verifica se eh um comando interno e,
    * se for, o executa.
    * 
    * Se nao for, verifica se é o nome de um programa terceiro localizado no atual 
    * diretorio de trabalho. Se for, cria um novo processo e o executa. Enquanto
    * esse processo executa, o processo do uniterm deve permanecer em espera.
    *
    * Se nao for nenhuma das situacoes anteriores, exibe uma mensagem de comando ou
    * programa desconhecido.
    */
    public static void executarComando(ComandoPrompt comando) {
        throw new RuntimeException("Método ainda não implementado.");
    }

    public static int executarPrograma(ComandoPrompt comando) {
        throw new RuntimeException("Método ainda não implementado.");
    }
    
    
    /**
     * Entrada do programa. Provavelmente você não precisará modificar esse método.
     */
    public static void main(String[] args) {

        promptTerminal();
    }
    
    
    /**
     * Essa classe não deve ser instanciada.
     */
    private Jsh() {}
}
