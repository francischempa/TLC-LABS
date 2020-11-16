import java.util.Random;

public class Main{
	public static void main(String args[]){
		Random randGen = new Random();
		for( int index = 0; index < 10; index++ ){
			int n = randGen.nextInt(100);
			String name;
			if(n==0) name = "frozen";
			else if(n < 15) name = "cold";
			else if(n < 25) name = "cool";
			else if(n < 41) name = "warm";
			else if(n < 61) name = "hot";
			else if(n < 81) name = "very hot";
			else if(n < 100) name = "extremely hot";
			else if(n == 100) name = "boiling";
			else name = "";
			System.out.println(name + "\n");

		}
		
	}
}
