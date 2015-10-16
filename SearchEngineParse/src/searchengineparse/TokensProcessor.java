/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchengineparse;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Iva
 */
public class TokensProcessor {
    
    /*
        term | cant | idArchivo
    */
    
    private ArrayList<Token> tokens; 
    
    public TokensProcessor(){
        tokens = new ArrayList();
    }
    
    public void seeTokenList(){
        String text = "";
        BufferedWriter output = null;
        try {
            File file = new File("output.txt");
            output = new BufferedWriter(new FileWriter(file));
            for(int i=0;i<tokens.size();i++){
                text+="Token: "+tokens.get(i).getToken()+" | Number: "+tokens.get(i).getTokens()+" | DocID: "+tokens.get(i).getDocID();
                text+="\n";
                //System.out.println("Token: "+tokens.get(i).getToken()+" | Number: "+tokens.get(i).getTokens()+" | DocID: "+tokens.get(i).getDocID());
            }
            output.write(text);
        } catch ( IOException e ) {System.out.println("ERROR: failed to create the output file.");} 
        finally {
            if ( output != null ) try {
                output.close();
            } catch (IOException ex) {System.out.println("ERROR: failed to close the file.");}
        }
    }
    
    //adds a new token found into the list of tokens
    private void addToken(String token, String docID){
        Token newToken = new Token(token, docID);
        boolean exists = false; 
        int indexWhereExists = -1;
        for(int i=0;i<tokens.size();i++){
            if(tokens.get(i).compareTo(newToken)==true){
                exists = true;
                indexWhereExists = i; 
            }
        }
        if(exists){
            tokens.get(indexWhereExists).add();
        }else{
            tokens.add(newToken);
        }
    }
    
    //returns the string representation of a file
    public String getStrFile(String path) throws FileNotFoundException{
        BufferedReader br = new BufferedReader(new FileReader(path));
        String everything = "";
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            br.close();
            everything = sb.toString();
        } catch (IOException ex) {System.out.println("ERROR: failed convertion from file to String.");}
        return everything;
    }
    
    //this method assumes EVERY TOKEN IS IN ONE LINE OF THE FILE
    public void processFile(File file){
        try {
            String docID = file.getName();
            String fileS = getStrFile(file.getAbsolutePath());
            String terms[] = fileS.split("[\\r\\n]+"); //NOT SURE IF THIS WORKS
            for(int i=0; i<terms.length;i++){
                addToken(terms[i].trim(), docID);
            }
        } catch (FileNotFoundException ex) { System.out.print("ERROR: failed file processing."); }
    }
    
    public void processDir(String path){
        File[] files = new File(path).listFiles();
        for (File file : files) {
            if (!file.isDirectory())
               processFile(file);
        }
    }
    
    public void run(String path){
        processDir(path);
    }
    
    /*
    public void showFiles(File[] files) {
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println("Directory: " + file.getName());
                showFiles(file.listFiles()); // Calls same method again.
            } else {
                //System.out.println("File: " + file.getName());
                processFile(file);
                
            }
        }
    }*/
    
}
