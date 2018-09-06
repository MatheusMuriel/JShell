package br.unifil.dc.sisop;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MetodosAuxiliares {

    /**
     * Metodo auxiliar, que pode ou não resceber um nome de diretorio RELATIVO
     * Se sim: Devolve o caminho ABSOLUTO referente ao diretorio relativo recebido
     * Se não: Devolve o caminha ABSOLUTO referente ao diretorio atual
     * @param dir Opcional que rescebe um diretorio
     * @return
     */
    public static String gerarCaminhoAbsoluto(Optional<String> dir){
        String caminhoRetorno;

        //Pega a barra do sistema
        String barraDoSistema = System.getProperty("file.separator");
        String userDir        = System.getProperty("user.dir");

        caminhoRetorno = dir.map(s -> userDir + barraDoSistema + s).orElse(userDir);


        /* Metodo acima funnciona como um
        if (dir.isPresent()){
            caminhoRetorno = userDir + barraDoSistema + dir.get();
        }else{
            caminhoRetorno = userDir;
        }
         */

        return caminhoRetorno;
    }

    /**
     * Metodo que obtem uma lista dos arquivos do diretorio passado como parametro.
     * Caso naão seja passado anda ele pega o diretorio atual.
     *
     * @param dir Opcional que é diretorio a ser listado
     * @return retorna a lista de Arquivos do diretorio
     */
    public static List<File> arquivosDiretorio(Optional<String> dir){

        File diretorio;
        List<File> listaArquivos;

        if(dir.isPresent()){
            //Diretorio específicado
            diretorio = new File(dir.get());

            //Verifica se o diretorio existe
            if (diretorio.isDirectory()) System.out.println("Diretorio invalido.");

        }else{
            //Nenhum diretorio passado, usar o diretorio atual
            diretorio = new File(System.getProperty("user.dir"));
        }

        listaArquivos = Arrays.asList(diretorio.listFiles());

        return listaArquivos;

    }

}
