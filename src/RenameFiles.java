import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class RenameFiles {

	/**
	 * @param args
	 */
	//static int season=0;
	static StringBuffer season= new StringBuffer();
	static StringBuffer names= new StringBuffer();
	static StringBuffer paths= new StringBuffer();
	static String eol = System.getProperty("line.separator");
	static StringBuffer newname= new StringBuffer();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		System.out.println("Hi");
		
		/*File inputFolder = new File("Z:\\Series\\Seinfeld");
			File namesfile = new File("c:\\names.txt");
		File pathsfile = new File("c:\\abspaths.txt");
		File seasonsfile = new File("c:\\seasons.txt");*/
		File newnames = new File("c:\\newnames.txt");
		File renames = new File("c:\\renames.txt");
		
		BufferedReader br = new BufferedReader(new FileReader(newnames));
		String line;
		 StringBuffer newname= new StringBuffer();
		 String seasons="";
		 String oldseasons="";
		 int episode=01;
		while ((line = br.readLine()) != null) {
		  // System.out.println(line);
		   File renameTarget= new File(line);
		   renameTarget.getName();
		   seasons=line.substring(26,27);
		   //System.out.println("oldeseason: "+oldseasons + "seasons : " + seasons);
		   if (!(oldseasons.equalsIgnoreCase(seasons)))
			   episode=01;
		   oldseasons=seasons;
		   
		  newname.delete(0, newname.length());
		  newname.append("Seinfeld - S0"+line.substring(26,27)+"E" +episode +" - "+ line.substring(28) + eol);
		  episode++;
		  System.out.println(newname);
		   //renameTarget.renameTo(newname);
		  
		}
		br.close();
		FileWriter fw3 = new FileWriter(renames.getAbsoluteFile());
		BufferedWriter bw3 = new BufferedWriter(fw3);
		bw3.write(newname.toString());
		bw3.close();
      /*  traverse(inputFolder, "");
        System.out.println(season);
        System.out.println(names);
		FileWriter fw = new FileWriter(seasonsfile.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(season.toString());
		bw.close();
		FileWriter fw1 = new FileWriter(namesfile.getAbsoluteFile());
		BufferedWriter bw1 = new BufferedWriter(fw1);
		bw1.write(names.toString());
		bw1.close();
		FileWriter fw2 = new FileWriter(pathsfile.getAbsoluteFile());
		BufferedWriter bw2 = new BufferedWriter(fw2);
		bw2.write(paths.toString());
		bw2.close();
*/
		System.out.println("Done");
	}
        
        public static void traverse(File parentNode, String leftIndent) {
            if (parentNode.isDirectory()) {
            	//s=leftIndent + parentNode.getName();
               // System.out.println(leftIndent + parentNode.getName());
                //System.out.println(leftIndent + parentNode.getName().substring(7));
                season.append(parentNode.getName().substring(7) + eol);
     //System.out.println(season);
                // Use left padding to create tree structure in the console output.
                leftIndent += "   ";
     
                File childNodes[] = parentNode.listFiles();
                for (File childNode : childNodes) {
                    traverse(childNode, leftIndent);
                }
            } else {
            	
            	if(parentNode.getName().equalsIgnoreCase("Thumbs.db"))
            		//System.out.println(parentNode.delete());
            		System.out.println(parentNode.getName());
            	names.append(parentNode.getName() + eol);
            	paths.append(parentNode.getAbsolutePath() + eol);
            	//season.append("\n");
                //System.out.println("Name: "+ leftIndent + parentNode.getName()+ leftIndent +" ------- Path: " + parentNode.getAbsolutePath());
            }
		

	}

}
