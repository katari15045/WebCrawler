import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class NutchResultParser
{
	public void start()
	{
		try
		{
			FileReader fileReader = new FileReader("/opt/apache-nutch-1.13/output/dump");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line = bufferedReader.readLine();

			while( line != null )
			{
				if( line.contains("URL::") )
				{
					System.out.println( line.substring(6, line.length()) );
				}

				line = bufferedReader.readLine();
			}

			fileReader.close();
		}

		catch(IOException e)
		{
			e.printStackTrace();
		}

	}
}