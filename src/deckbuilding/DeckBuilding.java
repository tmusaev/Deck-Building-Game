package deckbuilding;
import core.Game;
import core.Player;
import core.Table;
import java.net.ServerSocket;
import java.util.ArrayList;

public class DeckBuilding {

    public static void main(String[] args) throws Exception
    {
        ServerSocket listener = new ServerSocket(8901);
        System.out.println("Deckbuilding Server is Running");
        try {
            while (true) {
                Table table = new Table();
                Player player1 = new Player(table, listener.accept());
                Player player2 = new Player(table, listener.accept());
                player1.setOpponent(player2);
                player2.setOpponent(player1);
                ArrayList<Player> players = new ArrayList();
                players.add(player1);
                players.add(player2);
                Game game = new Game(players);
                //Game.Player playerO = game.new Player(listener.accept(), 'O');
                //playerX.setOpponent(playerO);
                //playerO.setOpponent(playerX);
                //game.currentPlayer = player1;
                player1.startListening();
                player2.startListening();
                //playerO.start();
            }
        } 
        
        finally {
            listener.close();
        }
        //Game game = new Game();
        //game.playGame();
    }
    
}