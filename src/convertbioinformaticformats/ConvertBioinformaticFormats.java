/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package convertbioinformaticformats;

/**
 *
 * @author projects
 */
public class ConvertBioinformaticFormats {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        if(args.length > 0){
            if(args[0].equals("1")){
                if(args.length == 2){
                    ConvertMAF_MAF_TABS gp = new ConvertMAF_MAF_TABS();
                    gp.generateNewFormat(args[1]);
                } else {
                    System.out.println("Usage: jar -jar 1 <input_file_maf_format> > output_file_tabs");
                }
            } else if(args[0].equals("2")){
                if(args.length == 4){
                    ConvertMAF_TABStoGBROWSE_SYN gp = new ConvertMAF_TABStoGBROWSE_SYN();
                    gp.generateNewFormat(args[1],args[2],args[3]);
                } else {
                    System.out.println("Usage: jar -jar 2 <query_1> <query_2> <input_file_maf_tabs> > output_file");
                    System.out.println("Example: jar -jar 2 <cane> <sorghum> ../fileMAFTabs > ../fileImportGBrowse_Syn");
                }
            } else if(args[0].equals("3")){
                
                Integer argsLength = args.length;
                if(argsLength > 0 && argsLength == 5){
                    ConvertBLASTtoGBROWSE_SYN i = new ConvertBLASTtoGBROWSE_SYN();
                    String inputFile = "/home/projects/Desktop/saida_blast";
                    String outputFile = "/home/projects/Desktop/saida_blast.out";
                    String query_db = "sorghum";
                    String subject_db = "cane";
                    query_db = args[1];
                    subject_db = args[2];
                    inputFile = args[3];
                    outputFile = args[4];

                    i.parserFormatBLASTtoGBROWSE_SYN(query_db,subject_db,inputFile,outputFile);

                } else {
                    System.out.println("Usage: jar -jar 3 <query_database> <subject_database> <input file> <output file>");
                    System.out.println("Example: java -jar 3 sorghum cane /home/user/file_format_blast_tabs /home/user/file_format_gbrowse_syn_tabs");
                }
                
            } else if(args[0].equals("4")){
                
                if(args.length == 3){
                    ConvertMAF_CLUSTALW gp = new ConvertMAF_CLUSTALW();
                    gp.generateNewFormat(args[1],args[2]);
                } else {
                    System.out.println("Usage: jar -jar 4 <input file> <output file>.");
                }
                
            } else if(args[0].equals("5")){
                
                if(args.length == 3){
                    ConvertMAF_GBrowseSyn gp = new ConvertMAF_GBrowseSyn();
                    gp.generateNewFormat(args[1],args[2]);
                } else {
                    System.out.println("Usage: jar -jar 5 <input file> <output file>.");
                }
                
            } else {
                System.out.println("<option> is not valid");
            }
            
            
            
        } else {
            System.out.println("Usage: jar -jar <option> <params[]>");
            System.out.println("<option>");
            System.out.println("1 - convert from MAF to MAF_tab format");
            System.out.println("2 - convert from MAF_tab to Gbrowse_Syn format");
            System.out.println("3 - convert from Blast to Gbrowse_Syn format");
            System.out.println("4 - convert from MAF to Clustalw format");
            System.out.println("5 - convert from MAF to GBrowse_Syn format");
            
        }
    }
}
