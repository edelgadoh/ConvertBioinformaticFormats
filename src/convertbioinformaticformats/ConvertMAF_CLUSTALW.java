/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package convertbioinformaticformats;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author projects
 */
public class ConvertMAF_CLUSTALW {

    public void generateNewFormat(String inputFileName, String outputFileName){
        BufferedReader readbuffer = null;
        String strRead = "";
        FileWriter arquivo;
        FileWriter arquivoFinal;
        
        Map<String,List<String[]>> hp_oligo_seq_read = new HashMap<String,List<String[]>>();
        
        try {  
            arquivo = new FileWriter(new File(inputFileName+".temp"));
            arquivoFinal = new FileWriter(new File(outputFileName));
            
            try {
                // TODO code application logic here
                readbuffer = new BufferedReader(new FileReader(inputFileName));//id
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ConvertMAF_CLUSTALW.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //logica
            String splitarray[];
            Integer lenSplit = 0;
            int readLines = 0;
            String probe_name = "";
            String genbank_acc = "";
            String splittedText = "";
            List<String[]> dataOligoSeq = new ArrayList<String[]>();
            try {
                while ((strRead = readbuffer.readLine())!=null){
                    //logic
                    if( !(strRead.startsWith("#") || strRead.startsWith("a")) ){
                        splitarray = strRead.split(" ");
                        lenSplit = splitarray.length;
                        splittedText = "";
                        for(String s: splitarray){
                            if(!s.equals("")){
                                splittedText += s+"\t";
                            }                            
                        }
                        arquivo.write(splittedText+"\n");
                    }
                    
                    readLines++;
                    
                }
            } catch (IOException ex) {
                Logger.getLogger(ConvertMAF_CLUSTALW.class.getName()).log(Level.SEVERE, null, ex);
            } 
            arquivo.close();
            
            
            String specie = "";
            Integer startContig = 0;
            Integer lenContig = 0;
            Integer endContig = 0;
            try {
                // TODO code application logic here
                readbuffer = new BufferedReader(new FileReader(inputFileName+".temp"));//id
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ConvertMAF_CLUSTALW.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                arquivoFinal.write("\n");
                arquivoFinal.write("CLUSTAL W(1.81) multiple sequence alignment W(1.81)\n");
                arquivoFinal.write("\n");
                while ((strRead = readbuffer.readLine())!=null){
                    //logic
                    
                    if(strRead.equals("")){
                        arquivoFinal.write("\n");
                        arquivoFinal.write("CLUSTAL W(1.81) multiple sequence alignment W(1.81)\n");
                        arquivoFinal.write("\n");
                    }
                    splitarray = strRead.split("\t");
                    lenSplit = splitarray.length;
                    
                    
                    if(lenSplit >= 7){
                        if(splitarray[1].contains("contig")){
                            specie = "cane";
                        } else {
                            specie = "sorghum";
                        }
                        startContig = Integer.parseInt(splitarray[2]);
                        lenContig = Integer.parseInt(splitarray[3]);
                        endContig = startContig+lenContig-1;
                        arquivoFinal.write(specie+"-"+splitarray[1]+"("+splitarray[4]+")/"+splitarray[2]+"-"+endContig.toString()+" "+splitarray[6]+"\n");
                        //Species-seqid(strand)/start-end
                    }                    
                    readLines++;
                    
                }
            } catch (IOException ex) {
                Logger.getLogger(ConvertMAF_CLUSTALW.class.getName()).log(Level.SEVERE, null, ex);
            } 
            arquivoFinal.close();
            
            
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        
    }
        
}
