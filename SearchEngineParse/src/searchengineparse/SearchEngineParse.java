/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchengineparse;

/**
 *
 * @author Iva
 */
public class SearchEngineParse {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        TokensProcessor tp = new TokensProcessor();
        tp.run("C:\\Users\\Iva\\Documents\\II-2015\\CI-2414 Recup\\SearchEngine\\dirTokens");
        tp.seeTokenList();
    }
    
}
