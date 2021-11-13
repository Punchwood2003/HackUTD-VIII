import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class TestProcess
{
	public static void main(String[]args) throws IOException
	{
		ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "text.bat");
		File dir = new File("(add file location)");
		pb.directory(dir);
		Process p = pb.start();
		
		InputStream in = p.getInputStream();
        for (int i = 0; i < in.available(); i++) {
           System.out.println("" + in.read());
        }
	}
}
