public class Trader{
    private Account ac = new Account(); ;
    private String name = "";
    public double getAccountValue(){
        return ac.get();
    }
    public void addTrade(Trade t){
        double old_value = ac.get();
        double value_of_trade = t.getValue();
        double new_value = old_value + value_of_trade;
        ac.set(new_value);
    }
}