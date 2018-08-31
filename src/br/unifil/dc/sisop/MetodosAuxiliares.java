package br.unifil.dc.sisop;

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

}
