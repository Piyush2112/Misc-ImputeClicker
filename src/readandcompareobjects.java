import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class readandcompareobjects {



	public static void main(String[] args) throws IOException 
	{
		File newnames = new File("c:\\cd_controlled_doc.txt");
		//File renames = new File("c:\\renames.txt");
		String line;

		BufferedReader br = new BufferedReader(new FileReader(newnames));

		StringBuffer newname= new StringBuffer();


		while ((line = br.readLine()) != null) 
		{
			System.out.println(line);
		}

	}


}
