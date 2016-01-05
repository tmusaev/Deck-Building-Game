package core;

import java.io.IOException;
import java.util.ArrayList;

public class Game{
    public static Player currentPlayer;
    static ArrayList<Player> players = new ArrayList();
    
    
    public Game(ArrayList<Player> p) 
    {
        players = p;
        currentPlayer = players.get(0);
        //ServerSocket listener = new ServerSocket(8901);
        //Player player1 = new Player();
        //Superman player2 = new Superman();
        //table.players.add(player1);
        //table.players.add(player2);
    }
    
    public void playGame()
    {
        for(;;)
        {
            //userinterface.drawUI(table, 0);
            //table.players.get(0).takeTurn();
            //table.lineUp.fill(table.mainDeck);
            //userinterface.drawUI(table, 1);
            //table.players.get(1).takeTurn();
            //table.lineUp.fill(table.mainDeck);
        }
        //for(;;)
       // {
            //userinterface.drawUI(table, 0);
            //Thread.sleep(100);
        //}
          
           
        //Player player1 = players.get(0);
        //player1.takeTurn(player1);
    }
    
    public static boolean isCurrentPlayer(Player p)
    {
        if(p == currentPlayer)
            return true;
        else
            return false;
    }
    
    public static boolean canAffordCard(Player p, Card c)
    {
        if(p.power >= c.cost)
        {
            p.power = p.power - c.cost;
            return true;
        }
        else
            return false;
    }
    
    public static void nextPlayer()
    {
        if(currentPlayer == players.get(0))
        {
            //currentPlayer.listener.sendMessage(1);
            currentPlayer = players.get(1);
            currentPlayer.listener.sendMessage(0);
        }
        else
        {
            //currentPlayer.listener.sendMessage(1);
            currentPlayer = players.get(0);
            currentPlayer.listener.sendMessage(0);
        }
            
        
    }
    
    public static void updateOpponent() throws IOException
    {
        if(currentPlayer == players.get(0))
        {
            players.get(1).listener.update();
            players.get(1).listener.writeOpp();
            //currentPlayer.listener.sendMessage(0);
        }
        else
        {
            players.get(0).listener.update();
            players.get(0).listener.writeOpp();
        }
    }
    
    
}
