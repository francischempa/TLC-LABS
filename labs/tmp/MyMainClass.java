public Interface Animal{
	public void eat();
	public void sleep();
}

public class Lion implements Animal{
	private String owner = "";

	public setOwner(String _owner){

	}

	public getOwner(){
		
	}

	@overide
	public void eat(){
		System.out.println("Hum Hum Grrrhhh");
	}
	
	@overide
	public void sleep(){
		System.out.println("Zzzz");
	}
}
public class MyMainClass{
	
}