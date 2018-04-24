import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Text11
{
	public static void main(String[] args)
	{
		String url = "http://www.baidu.com/";
		String rule = "<a .*href=.+</a>";
		
		try
		{
			URL tempURL = new URL(url);
			URLConnection connection = tempURL.openConnection();
			InputStream inputStream = connection.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			Pattern pattern = Pattern.compile(rule);
			Matcher matcher = null;
			
			Set<String> set = new HashSet<>();
			String tempContent = "";
			
			String content = "";
			while ((content = bufferedReader.readLine()) != null)
			{
//				System.out.println(content);
				
				matcher = pattern.matcher(content);
				
				while (matcher.find())
				{
					tempContent = matcher.group();
					set.add(tempContent);
				}
				
			}
			
			for (String t : set)
			{
				System.out.println(t);
			}
			
			
		}
		catch (Exception e)
		{
			System.out.println("catch error");
		}
	}
}