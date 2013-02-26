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
public class ConvertMAF_GBrowseSyn {

    public void generateNewFormat(String query1, String query2, String inputFileName, String outputFileName){
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
                Logger.getLogger(ConvertMAF_GBrowseSyn.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(ConvertMAF_GBrowseSyn.class.getName()).log(Level.SEVERE, null, ex);
            } 
            arquivo.close();
            
            String src1 = "";
            String ref1 = "";
            Integer start1 = 0;
            Integer lenRef1 = 0;
            Integer end1 = 0;
            String strand1 = "";
            String seq1 = "";
            
            String src2 = "";
            String ref2 = "";
            Integer start2 = 0;
            Integer lenRef2 = 0;
            Integer end2 = 0;
            String strand2 = "";
            String seq2 = "";
            
            Integer rowsReaded = 0;
            try {
                // TODO code application logic here
                readbuffer = new BufferedReader(new FileReader(inputFileName+".temp"));//id
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ConvertMAF_GBrowseSyn.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                while ((strRead = readbuffer.readLine())!=null){
                    //logic
                    splitarray = strRead.split("\t");
                    lenSplit = splitarray.length;
                    
                    if(lenSplit >= 7){
                        
                        rowsReaded++;
                        
                        if(rowsReaded == 1){
                            src1 = query1;
                            ref1 = splitarray[1];
                            start1 = Integer.parseInt(splitarray[2]);
                            lenRef1 = Integer.parseInt(splitarray[3]);
                            end1 = start1+lenRef1-1;
                            strand1 = splitarray[4];
                            seq1 = splitarray[6];
                        } else if(rowsReaded == 2){
                            src2 = query2;
                            ref2 = splitarray[1];
                            start2 = Integer.parseInt(splitarray[2]);
                            lenRef2 = Integer.parseInt(splitarray[3]);
                            end2 = start2+lenRef2-1;
                            strand2 = splitarray[4];
                            seq2 = splitarray[6];
                            
                            rowsReaded = 0;
                            arquivoFinal.write(src1+"\t"+ref1+"\t"+start1+"\t"+end1+"\t"+strand1+"\t"+seq1+"\t"+src2+"\t"+ref2+"\t"+start2+"\t"+end2+"\t"+strand2+"\t"+seq2+"\n");
                            
                        }
                    }
                    readLines++;
                    
                }
            } catch (IOException ex) {
                Logger.getLogger(ConvertMAF_GBrowseSyn.class.getName()).log(Level.SEVERE, null, ex);
            } 
            arquivoFinal.close();
            
            
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        
    }
    
}
