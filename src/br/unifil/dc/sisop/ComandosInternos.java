package br.unifil.dc.sisop;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

/**
 * Write a description of class ComandosInternos here.
 *
 * @author Ricardo Inacio Alvares e Silva
 * @version 180823
 */
public final class ComandosInternos {
    
    public static int exibirRelogio() {
        //Sao 14:45 de 20/05/2014.

        //Define os formatos de Hora e data
        DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        DateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

        //Pega a data
        Date dataAtual = new Date();

        //Gera as strings de data e hora formatadas
        String stringHora = formatoHora.format(dataAtual);
        String stringData = formatoData.format(dataAtual);


        System.out.println("São " + stringHora + " de " + stringData + ".");

        //throw new RuntimeException("Método ainda não implementado");
        return 1;
    }
    
    public static int escreverListaArquivos(Optional<String> nomeDir) {
        throw new RuntimeException("Método ainda não implementado");
    }
    
    public static int criarNovoDiretorio(String nomeDir) {
        throw new RuntimeException("Método ainda não implementado");
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
