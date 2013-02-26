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
public class ConvertMAF_MAF_TABS {

    public void generateNewFormat(String inputFileName){
        BufferedReader readbuffer = null;
        String strRead = "";
        
        try {  
            try {
                // TODO code application logic here
                readbuffer = new BufferedReader(new FileReader(inputFileName));//id
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ConvertMAF_MAF_TABS.class.getName()).log(Level.SEVERE, null, ex);
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
                        System.out.println(splittedText);//+"\n"
                    }
                    
                    readLines++;
                    
                }
            } catch (IOException ex) {
                Logger.getLogger(ConvertMAF_MAF_TABS.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        
    }
    
}
