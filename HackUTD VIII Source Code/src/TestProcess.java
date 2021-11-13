import java.io.IOException;

public class TestProcess
{
	public static void main(String[]args) throws IOException
	{
		Process stocksProcess = Runtime.getRuntime().exec("ScrapeApeWisdom_Stocks.bat");
		Process cryptoProcess = Runtime.getRuntime().exec("ScrapeApeWisdom_Cryptos.bat");
		
		stocksProcess.destroy();
		cryptoProcess.destroy();
		
	}
}
