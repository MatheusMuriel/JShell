package br.unifil.dc.sisop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
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
     *
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
        //System.out.println("Olar");
        usuario_nome = System.getProperty("user.name"); //Nome do usuario logado
        usuario_diretorio = System.getProperty("user.dir"); //Nome do usuario logado
        System.err.print(usuario_nome + "#" + "UID" + ":" + usuario_diretorio + "%" );
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
    *
    * @return 
    */
    public static ComandoPrompt lerComando() {
        Scanner input = new Scanner(System.in);
        String entrada = input.nextLine();

        //Chama o construtor de ComandoPrompt, pasando a linha digitada como parametro
        ComandoPrompt cmdPrompt = new ComandoPrompt(entrada);
        return cmdPrompt;
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
        ProcessBuilder processo = new ProcessBuilder();
        //Comandos internos.
        switch (comando.getNome()){

            case ("encerrar"):  System.exit(0);

            case ("relogio"):{
                ComandosInternos.exibirRelogio();
                break;
            }
            case ("la"):{
                ComandosInternos.escreverListaArquivos(Optional.of(usuario_diretorio));
                break;
            }
            case ("cd"):{
                ComandosInternos.criarNovoDiretorio(comando.getArgumentos());
                break;
            }
            case ("ad"):{
                ComandosInternos.apagarDiretorio(comando.getArgumentos());
                break;
            }
            case ("mdt"):{
                ComandosInternos.mudarDiretorioTrabalho(comando.getArgumentos());
                break;
            }
            case ("teste"):{
                MetodosAuxiliares.arquivosDiretorio(Optional.of(usuario_diretorio));
                break;
            }
            default:{
                executarPrograma(comando);
            }

        }

    }

    public static int executarPrograma(ComandoPrompt comando) {
        File comandoNomeDir = new File(comando.getNome());

        //Verifica uma lista dos arquivos do diretorio
        //Se algum tiver o mesmo nome
        if(MetodosAuxiliares.arquivosDiretorio(Optional.empty()).contains(comandoNomeDir)){
            //verifica se o arquivo tem permissão de execução
            if (comandoNomeDir.canExecute()){
                //Se tiver ele cria um novo processo
            }else{
                //comando ser permissão
                System.out.println("O arquivo '" + comando.getNome() + "' não tem permissão de execução.");

            }


            //Se o processo retornar um valor diferente de 1 lança uma mensagem indicando o erro



        }else{
            //Se não tiver indica que o comando não existe e abre o prompt
            System.out.println("Comando ou programa '" + comando.getNome() + " inexistente.");
        }

        return 1;
        //throw new RuntimeException("Método ainda não implementado.");
    }

    public static String usuario_nome;
    public static String usuario_diretorio;

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
