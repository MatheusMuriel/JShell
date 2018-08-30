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
    
    public static int apagarDiretorio(String nomeDir) {

        throw new RuntimeException("Método ainda não implementado");
    }
    
    public static int mudarDiretorioTrabalho(String nomeDir){
        throw new RuntimeException("Método ainda não implementado");
    }
    
    /**
     * Essa classe não deve ser instanciada.
     */
    private ComandosInternos() {}
}
