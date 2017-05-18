import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;


public class NutchResultParser
{
	private LinkedHashSet<String> links;

	public LinkedHashSet<String> start()
	{
		try
		{
			FileReader fileReader = new FileReader("/opt/apache-nutch-1.13/output/dump");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = bufferedReader.readLine();
			links = new LinkedHashSet<String>();

			while( line != null )
			{
				if( line.contains("URL::") )
				{
					links.add( line.substring(6, line.length()) );
				}

				line = bufferedReader.readLine();
			}

			fileReader.close();
		}

		catch(IOException e)
		{
			e.printStackTrace();
		}

		return links;

	}
}