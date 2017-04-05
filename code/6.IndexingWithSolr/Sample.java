import java.util.LinkedHashSet;

public class Sample
{
	private static Shell shell;
	private static MyDatabase database;
	private static Parser parser;
	private static LinkedHashSet<String> urlSet;

	public static void main(String[] args)
	{
		shell = new Shell();
		shell.execute("bash sample.sh");
		shell.execute("curl http://localhost:8983/solr/temp_core/select?q=redbus");
		database = new MyDatabase();
		System.out.println(shell.getOutput());
		database.store( shell.getOutput() );
		parser = new Parser();
		urlSet = parser.getUrls();

		System.out.println(urlSet);
	}
}