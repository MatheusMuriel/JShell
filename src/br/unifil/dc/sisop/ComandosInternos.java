package br.unifil.dc.sisop;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Write a description of class ComandosInternos here.
 *
 * @author Ricardo Inacio Alvares e Silva
 * @version 180823
 */
public final class ComandosInternos {
    /**
     * Metodo que informa o horario e a data atual
     * Define os formatos de hora e data, pega a data do sistema
     * formata para o padrão definido e imprime na tela
     * @return
     */
    public static int exibirRelogio() {
        try{
            //Define os formatos de Hora e data
            DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
            DateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

            //Pega a data atual
            Date dataAtual = new Date();

            //Gera as strings de data e hora formatadas
            String stringHora = formatoHora.format(dataAtual);
            String stringData = formatoData.format(dataAtual);


            System.out.println("São " + stringHora + " de " + stringData + ".");
            return 1;
        } catch (Exception erro){
            System.out.println("Ouve uma falha na chamada/formatação da data e hora");
            System.out.println(erro.getMessage());
            return 1;
        }
    }


    /**
     * Metodo que lista os nomes de todos os arquivos e diretórios do atual diretório de trabalho,
     * um em cada linha.
     *
     * Cria uma lista de Files contendo cada arquivo/pasta do diretorio
     * Printa de 1 em 1
     *
     * @param nomeDir Opcional que recebe
     * @return
     */
    public static int escreverListaArquivos(Optional<String> nomeDir) {

        //Cria um objeto File do diretorio atual
        File diretorio = new File(nomeDir.get());

        //Gera uma Lista de Files dos arquivos do diretorio
        List<File> listaArquivos = Arrays.asList(diretorio.listFiles());

        //For each que pega o nome cada arquivo da lista e printa em uma linha
        for (File f : listaArquivos) System.out.println(f.getName());


        return 1;
        //throw new RuntimeException("Método ainda não implementado");
    }

    /**
     * cria um novo diretório com nome definido pelo primeiro argumento em
     * ComandoPrompt::getArgumentos. Este diretório é colocado no diretório atual de
     * trabalho.
     *
     * https://docs.oracle.com/javase/10/docs/api/java/nio/file/Files.html#createDirectory(java.nio.file.Path,java.nio.file.attribute.FileAttribute...)
     *
     * @param nomeDir nome do diretorio atual de trabalho
     * @return
     */
    public static int criarNovoDiretorio(String nomeDir, List<String> args) {

        //Diretorio atual de trabalho
        //File diretorio = new File(args.get(1));
        System.out.println(args.get(0));
        new File(args.get(0)).mkdir();


        return 1;
        //throw new RuntimeException("Método ainda não implementado");
    }

    /**
     * Metodo que apaga o diretorio passado no primeiro parametro
     * Primeiro ele verifica qual é a barra do sistema ( se é ' / ' ou ' \ ')
     * Concatena o diretorio de trabalho com a barra e com o nome do diretorio a ser excluido
     * gerando assim o caminho absoluto do diretorio a ser excluido
     * Verifica se esse diretorio existe e se é mesmo um diretorio
     * Caso contrario lança um aviso referente a os problemas acima
     *
     * @param args Lista de parametros do comando
     * @return
     */
    public static int apagarDiretorio(List<String> args) {

        String barraDoSistema = System.getProperty("file.separator");
        String caminhoCompleto;
        caminhoCompleto = System.getProperty("user.dir") + barraDoSistema + args.get(0);

        //cria um objeto File do caminha do diretorio que vai ser apagado
        File dirASerApagado = new File(caminhoCompleto);

        //Verifica se esse diretorio é um diretorio e se ele existe
        if((dirASerApagado.exists()) && (dirASerApagado.isDirectory())){

            //Se sim, ele deleta
            dirASerApagado.delete();

        } else if (!dirASerApagado.exists())      System.out.println("Esse diretorio não existe </3");
          else if (!dirASerApagado.isDirectory()) System.out.println("Isso não é um diretorio");

        return 1;
    }
    
    public static int mudarDiretorioTrabalho(String nomeDir){
        throw new RuntimeException("Método ainda não implementado");
    }
    
    /**
     * Essa classe não deve ser instanciada.
     */
    private ComandosInternos() {}
}
