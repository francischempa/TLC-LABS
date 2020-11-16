public class Main{
	public static void main(String args[]){
		for( int index = 0; index < 10; index++ ){
			int n = (int) ( Math.random() * 10 + 1 );
			System.out.println( n + ( ( n % 2 == 0 )  ? " Number is Odd":" Number is Even" ) );
		}

	}
}
