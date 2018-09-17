package br.unifil.dc.sisop;


import java.io.*;
import java.util.*;

/**
 * Projeto Sistemas Operacionais: jsh
 * Centro Universitario Filadelfia
 * Alunos: Matheus Muriel e Guilherme Manhani
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

        usuario_nome = System.getProperty("user.name"); //Nome do usuario logado

        usuario_diretorio = System.getProperty("user.dir"); //Nome do usuario logado

        usuario_UID = MetodosAuxiliares.obtemUID().get();

        System.err.print(usuario_nome + "#" + usuario_UID + ":" + usuario_diretorio + "%" );

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
            default:{
                //executarPrograma(comando);
                Jsh.executarPrograma(comando);
            }

        }

    }

    /**
     * Metodo que verifica e executa um programa contido no diretorio atual
     * Pega um lista de todos os arquivos do diretorio, verifica se o comando tem o mesmo nome
     * se tiver ele verifica se pode ser executado
     * executa o programa
     * @param comando comando contendo o nome do executavel
     * @return
     */
    public static int executarPrograma(ComandoPrompt comando) {

        File comandoNomeDir = new File(MetodosAuxiliares.gerarCaminhoAbsoluto(Optional.of(comando.getNome())));

        List<File> arquivosDiretorio = MetodosAuxiliares.arquivosDiretorio(Optional.empty());

        if(arquivosDiretorio.contains(comandoNomeDir)){

            if (comandoNomeDir.canExecute()){

                if (executaProcesso(comando) != 0) System.err.println("Erro de execução");

            }else{
                System.err.println("O arquivo '" + comando.getNome() + "' não tem permissão de execução.");
            }

        }else{
            System.err.println("Comando ou programa '" + comando.getNome() + "' inexistente.");
        }

        return 1;

    }


    /**
     * Metodo que rescbe um comando e o executa via shell
     * Esse metodo constroi um processo e captura sua saida
     * transforma ela em uma string e printa na tela
     *
     * @param comando nome dp programa a ser executado
     * @return codigo de saida do processo (o normal é 0)
     */
    public static int executaProcesso_Old(ComandoPrompt comando){

        Process p;
        int valorSaida = 0;

        try {
            String[] cmd = {"/bin/sh", "-c", ( "./" + comando.getNome() ) };

            p = Runtime.getRuntime().exec(cmd);
            valorSaida = p.waitFor();
            InputStream input = p.getInputStream();

            byte[] saida = input.readAllBytes();

            String stgSaida = new String(saida);

            System.err.println(stgSaida);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return valorSaida;
    }

    public static int executaProcesso(ComandoPrompt comando){

        String caminhoRelativo = comando.getNome();
        String caminhoAbsoluto = MetodosAuxiliares.gerarCaminhoAbsoluto(Optional.of(caminhoRelativo));
        int valorSaida = 0;

        ProcessBuilder construtorProcesso = new ProcessBuilder(caminhoAbsoluto);

        try {
            Process p = construtorProcesso.start();
            valorSaida = p.waitFor();
            InputStream input = p.getInputStream();

            byte[] saida = input.readAllBytes();

            String stgSaida = new String(saida);

            System.out.println(stgSaida);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return valorSaida;

    }

    public static String usuario_nome;
    public static String usuario_diretorio;
    public static int    usuario_UID;
    public static String    barraSistema = System.getProperty("file.separator");

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
