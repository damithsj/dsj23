import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.StringTokenizer;
import ifs.fnd.base.IfsException;
 
public class testTransformer 
{
    public static void main(String[] args)
    {
		// input file
		try {
		Path fileName = Path.of("..//testFiles//accounts.csv");         
        String s = Files.readString(fileName);
	    csvToXmlApp10 tc = new csvToXmlApp10();
		
		System.out.println(tc.transform(s));
		} catch (IfsException | IOException e) {
			e.printStackTrace();
		}

    }
}