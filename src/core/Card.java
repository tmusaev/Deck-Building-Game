package core;


public class Card{
    public String name;
    public int cost;
    public int victoryPoints;
    public String type;
    public String expansion;
    public int quantity;
    
    public String getname(){
        return name;
    }
    public int getcost() {
        return cost;
    }
    public int getvictoryPoints() {
        return victoryPoints;
    }
    public String gettype(){
        return type;
    }
    
    public void play(Player player) throws InterruptedException
    {
        
    }
}
