import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Shell
{
	private String command;
	private StringBuilder output;

	public void execute(String inpCommand)
	{
		command = inpCommand;
		executeCommand();
	}

	public String getOutput()
	{
		return output.toString();
	}

	private void executeCommand()
	{
		Process process;
		BufferedReader bufferedReader = null;
		String currentLine;

		output = new StringBuilder();

		try
		{
			process = Runtime.getRuntime().exec(command);
			process.waitFor();
			bufferedReader = new BufferedReader( new InputStreamReader( process.getInputStream() ) );
			currentLine = bufferedReader.readLine();

			while( currentLine != null )
			{		
				output.append(currentLine);
				output.append("\n");
				
				currentLine = bufferedReader.readLine();
			}

			bufferedReader.close();
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}