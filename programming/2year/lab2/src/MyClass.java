import java.util.StringTokenizer;

public class MyClass {
	static final String DELIMETERS=" ";
	static StringBuffer str=new StringBuffer();
	public static void main(String []args) {
		for (String i:args)
		{
			StringTokenizer strTok=new StringTokenizer(i, DELIMETERS); 
			while (strTok.hasMoreTokens())
			{
				String s= strTok.nextToken();
				if (s.length()!=1)
				{
					str.append(s+" ");
				}
			}
		}
		System.out.print(str);
	}
}
