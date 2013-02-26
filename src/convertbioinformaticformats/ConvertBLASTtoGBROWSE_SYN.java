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
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author projects
 */
public class ConvertBLASTtoGBROWSE_SYN {

    public void parserFormatBLASTtoGBROWSE_SYN(String query_db, String subject_db, String inputFile, String outputFile){
        BufferedReader readbuffer = null;
        String strRead = "";
        String content = "";
        File f;
                
        try {  
            
            //Read buffer
            try {
                readbuffer = new BufferedReader(new FileReader(inputFile));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ConvertBLASTtoGBROWSE_SYN.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //Start writing file
            f = new File(outputFile);
            Writer w = new FileWriter(f);
            
            String query_id = "";
            String subject_id = "";
            Integer query_start = 0;
            Integer query_end = 0;
            Integer subject_start = 0;
            Integer subject_end = 0;
            Integer temp = 0;
            String query_orientation = "";
            String subject_orientation = "";
            
            String[] splitarray = null;
            
            try {
                while ((strRead=readbuffer.readLine())!=null){
                    
                    query_id = "";
                    subject_id = "";
                    query_start = 0;
                    query_end = 0;
                    subject_start = 0;
                    subject_end = 0;
                    temp = 0;
                    query_orientation = "";
                    subject_orientation = "";
                    
                    
                    splitarray = strRead.split("\t");
                    
                    if(splitarray.length > 9){
                        query_id = splitarray[0];
                        subject_id = splitarray[1];
                        query_start = Integer.parseInt(splitarray[6]);
                        query_end = Integer.parseInt(splitarray[7]);
                        subject_start = Integer.parseInt(splitarray[8]);
                        subject_end = Integer.parseInt(splitarray[9]);
                        
                        if(query_start < query_end){
                            query_orientation = "+";
                        } else {
                            query_orientation = "-";
                            temp = query_start;
                            query_start = query_end;
                            query_end = temp;
                        }
                        
                        if(subject_start < subject_end){
                            subject_orientation = "+";
                        } else {
                            subject_orientation = "-";
                            temp = subject_start;
                            subject_start = subject_end;
                            subject_end = temp;
                        }
                                                
                    }
                    
                    w.write(query_db+"\t"+query_id+"\t"+query_start+"\t"+query_end+"\t"+query_orientation+"\t.\t"+subject_db+"\t"+subject_id+"\t"+subject_start+"\t"+subject_end+"\t"+subject_orientation+"\t.\n");                    
                    
                }
            } catch(Exception e){
                e.printStackTrace();
            }
            
            w.close();
            readbuffer.close();
            
            
        } catch (Exception e) {  
            e.printStackTrace();
        }
        
    
    }
        
}
