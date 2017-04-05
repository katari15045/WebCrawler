
public class Solr
{
	private static Shell shell;
	private static MyDatabase database;

	public static void main(String[] args)
	{
		shell = new Shell();
		shell.execute("bash sample.sh");
		shell.execute("curl http://localhost:8983/solr/temp_core/select?q=redbus");
		database = new MyDatabase();
		System.out.println(shell.getOutput());
		database.store( shell.getOutput() );
	}
}