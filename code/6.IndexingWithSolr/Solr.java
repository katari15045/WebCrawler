
public class Solr
{
	private static Shell shell;

	public static void main(String[] args)
	{
		shell = new Shell();
		shell.execute("bash sample.sh");

		System.out.println( shell.getOutput() );
	}
}