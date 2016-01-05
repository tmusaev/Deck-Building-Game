package cards.SuperPower;

import core.Card;
import core.Defendable;
import core.Player;

public class SuperSpeed extends Card implements Defendable{
    
    public SuperSpeed() 
    {
        name = "Super Speed";
        cost = 3;
        victoryPoints = 1;
        type = "Super Power";
        expansion = "1DBG";
        quantity = 4;
    }
    
    public void play(Player player)
    {
        player.drawCard();
    }
    
    public void defense(Player player)
    {
        player.drawCard();
        player.drawCard();
    }

}
