import java.io.PrintWriter;
import java.io.IOException;

public class MyDatabase
{
	private String data;

	public void store(String inpData)
	{
		data = inpData;
		writeToFile();
	}

	private void writeToFile()
	{
		try
		{
			PrintWriter printWriter = new PrintWriter("sample.xml");
			printWriter.write(data);
			printWriter.flush();
			printWriter.close();
		}

		catch( IOException e )
		{
			e.printStackTrace();
		}
	}
}