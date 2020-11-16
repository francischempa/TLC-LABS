



public class Trade{

    private String id;
    private String symbol;
    private int quantity;
    private double price;

    public double getValue(){
        return quantity * price;
    }

    //      CONSTRUCTOR 1
    public Trade(){
    }

    //      CONSTRUCTOR 2
    public Trade(String id, String symbol, int qty, double price){
        this.id = id;
        this.symbol = symbol;
        this.quantity = qty;
        this.price = price;
    }

    //      CONSTRUCTOR 3
    public Trade(String id, String symbol, int qty){
        this.id = id;
        this.symbol = symbol;
        this.quantity = qty; 
    }

    public void setPrice(double price){
        if(price >= 0){
            this.price = price;
        }else{
            System.out.println("Sorry price can't be a negative value!");
        }
    }
    

    public String toString(){
        return (  "id="  +   id  +   ", symbol="     +  symbol  +  ", qty=" +quantity+", price="+price);
    }

    public static void main(String[] args){ 

        Trade t1 = new Trade("T1", "APPL", 100, 50.1); 
        Trader chempa = new Trader();
        chempa.addTrade(t1);
        Trade t2 = new Trade("T1", "APPL", 10, 10.5); 
        chempa.addTrade(t2);
        System.out.println(chempa.getAccountValue());
        

    }
}

