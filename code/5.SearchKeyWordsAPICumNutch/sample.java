import java.util.Scanner;
import java.util.LinkedHashSet;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class sample
{
	private static ApacheNutch apacheNutch;
	private static NutchResultParser nutchResultParser;
	private static String url;

	public static void main(String[] args) throws Exception
	{
		//takeUserInput();
		//storeLinkInAFile();
		//apacheNutch = new ApacheNutch();
		//apacheNutch.start();
		nutchResultParser = new NutchResultParser();
		nutchResultParser.start();
	}

	private static void takeUserInput()
	{
		Scanner scanner = new Scanner( System.in );
		System.out.print("url -> ");
		url = scanner.nextLine();
	}

	private static void storeLinkInAFile()
	{
		try
		{
			PrintWriter printWriter = new PrintWriter("seed.txt");
			printWriter.print(url);
			printWriter.close();	
		}

		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}