import ifs.fnd.base.IfsException;
import ifs.fnd.connect.xml.Transformer;
import java.io.*;
import java.util.StringTokenizer;


public class csvToXmlApp10 implements Transformer {

   @Override
   public void init() throws IfsException{
   }

   @Override
   public String transform(String s) throws IfsException{      
		
        StringBuffer sb = new StringBuffer();       
        String messageStart, messageEnd, linesStart, linesEnd, temp_;

        messageStart = "<?xml version='1.0' encoding='UTF-8'?>\n" +
						"<RECEIVE_ACCOUNT xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"urn:ifsworld-com:schemas:ifs_financials_inbound_messages_receive_account_request\">\n" +
						"\t<ACCOUNTS>\n";
						

        messageEnd = "\t</ACCOUNTS>\n" +
					 "</RECEIVE_ACCOUNT>";
					 
        linesStart =  "\t\t<ACCOUNT>\n";
        linesEnd =  "\t\t</ACCOUNT>\n";

        sb.append(messageStart);
		
		try {
			Reader inputString = new StringReader(s);
			BufferedReader reader = new BufferedReader(inputString);
			String line = reader.readLine(); //read the first line, discard the header
			line = reader.readLine(); //start reading from the second line
			
			while (line != null) {				
				sb.append(linesStart);
				
				//tokenize each element in line
                StringTokenizer lineString = new StringTokenizer(line, ",");
				
                int pos = 0;
                while (lineString.hasMoreTokens()) {
					switch (pos) {						
					  case 0:
						sb.append("\t\t\t<ACCOUNT>" + lineString.nextToken() + "</ACCOUNT>\n");
						break;
					  case 1:
						sb.append("\t\t\t<ACCOUNT_GROUP>" + lineString.nextToken() + "</ACCOUNT_GROUP>\n");
						break;
					  case 2:
						sb.append("\t\t\t<ACCOUNT_TYPE>" + lineString.nextToken() + "</ACCOUNT_TYPE>\n");
						break;
					  case 3:
						sb.append("\t\t\t<COMPANY>" + lineString.nextToken() + "</COMPANY>\n");
						break;
					  case 4:
						sb.append("\t\t\t<DESCRIPTION>" + lineString.nextToken() + "</DESCRIPTION>\n");
						break;
					  case 5:
						sb.append("\t\t\t<VALID_FROM>" + lineString.nextToken() + "</VALID_FROM>\n");
						break;
					  case 6:
						sb.append("\t\t\t<VALID_UNTIL>" + lineString.nextToken() + "</VALID_UNTIL>\n");
						break;
					  default: //read other tokens if present just to consume
						temp_ = lineString.nextToken();
						break;
					}
                    pos += 1;					
                }  				
				
				sb.append(linesEnd);
				// read next line
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sb.append(messageEnd);
        return sb.toString();
    }
}