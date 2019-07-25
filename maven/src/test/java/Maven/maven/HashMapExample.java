/*package Maven.maven;
import java.util.HashMap;

class HashMapExample
{
	String topic,specialization;	
	HashMapExample(String t,String s)
	{
		topic=t;
		specialization=s;
	}	
	public String toString()
	{
		return("> "+topic+"-"+specialization);
	}	
}
class HashMapExample
{
	String description,since,record;
	HashMapExample(String d,String s,String r)
	{
		description=d;
		since=s;
		record=r;
	}
	public String toString()
	{
		return("\t"+description+"\n\t"+since+"\n\t"+record+"\n");
	}	
}
class HashMapExample
{
	public static void main(String arg[])
	{
		Value k1,k2, k3;
		k1=new Value("sachin","cricket");
		k2=new Value("sachin","music");
		k3=new Value("newton","science");		
 
		Value v1,v2,v3;
		v1=new Value("Sachin Ramesh Tendulkar", "playing international cricket since 1989", "one of the best "
					+ "batsmen in cricket & highest run scrorer in test & ODIs\n\tfirst Indian sports person to receive "
					+ "BharathRatna\n\tnamed after famous composer SDBurman");
		v2=new Value("Sachin Dev Burman","started career in 1937 with bengali movies then moved to "
					+ "Bollywood","one of the best composers in India\n\tgiven music to nearly 100 movies");
		v3=new Value("Isaac Newton","Sir Isaac Newton has been in scientic field since 1665","he is famous for "
					+ "his laws of motion and universal gravitation formulae\n\this and his father names are same"
					+ "\n\the is a british national");
 
		HashMap<Value,Value> know=new HashMap<Value,Value>();
		know.put(k1,v1);
		know.put(k2,v2);
		know.put(k3,v3);
 
		System.out.println(k1); System.out.println(know.get(k1));
		System.out.println(k2); System.out.println(know.get(k2));
		System.out.println(k3); System.out.println(know.get(k3));
	}
}*/