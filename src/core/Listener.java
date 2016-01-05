package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StreamCorruptedException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Listener extends Thread{
    Player player;
    Socket socket;
    BufferedReader input;
    public PrintWriter output;
    ObjectOutputStream outputStream;
    ArrayList<ArrayList<String>> gameState = new ArrayList();
    ArrayList<ArrayList<String>> oppState = new ArrayList();
    
    public Listener(Player p, Socket s, ArrayList<ArrayList<String>> g)
    {
        player = p;
        socket = s;
        gameState = g;
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            //outputStream.writeObject(player.hand.getString());
            outputStream.writeObject(gameState);
            outputStream.flush();
            //output.println("WELCOME " + mark);
            output.println("MESSAGE Waiting for opp to connect");
        } catch (IOException e) {
                System.out.println("Player died: " + e);
            }
        
    }
    
    public void setOppState(ArrayList<ArrayList<String>> o) throws IOException
    {
        oppState = o;
        //writeOpp();
    }
    
    public void writeOpp() throws IOException
    {
        oppState = player.updateOppState();
        //try{
        //outputStream.reset();
        output.println("TAKE");
        //outputStream.writeObject(oppState);
        //}
        //catch(StreamCorruptedException e)
        //{
            //e.printStackTrace();
        //}
        
    }
    
    public void sendMessage(int i)
    {
        if (i == 0)
            output.println("MESSAGE YOUR TURN");
        else if(i == 1)
            output.println("MESSAGE OPPONENT'S TURN");
    }
    
    public void update() throws IOException 
    {
        gameState = player.updateGameState();
        output.println("UPDATE");
        outputStream.reset();
        outputStream.writeObject(gameState);
        outputStream.flush();
        output.println("POWER "+player.power);
    }
    
    public void run() {
            try {
                //writeOpp();
                if(Game.isCurrentPlayer(player))
                {
                    output.println("MESSAGE YOUR TURN");
                }
                else
                {
                    output.println("MESSAGE OPPONENT'S TURN");
                }
                output.println("TAKE");
                // Repeatedly get commands from the client and process them.
                while (true) {
                    String command = input.readLine();
                    if (command.startsWith("PLAY")) {
                        int index = Integer.parseInt(command.substring(5));
                        if (Game.isCurrentPlayer(player)) {
                            try{
                                player.playCard(index);
                                //update();
                                //Game.updateOpponent();
                            }
                            catch(InterruptedException ex){
                                ex.printStackTrace();
                            }
                            //output.println("PLAYED "+index);
                            /*gameState = player.updateGameState();
                            output.println("UPDATE");
                            outputStream.writeObject(gameState);
                            outputStream.flush();
                            output.println("POWER "+player.power);*/
                            //output.println("TAKE");
                        } else {
                            output.println("MESSAGE NOT YOUR TURN");
                        }
                    } 
                    else if(command.startsWith("BUY"))
                    {
                        int index = Integer.parseInt(command.substring(4));
                        if (Game.isCurrentPlayer(player))
                        {
                            if(Game.canAffordCard(player, player.table.lineUp.peekCard(index)))
                            {
                                player.buyLineUp(index);
                                gameState = player.updateGameState();
                                output.println("UPDATE");
                                outputStream.writeObject(gameState);
                                output.println("POWER "+player.power);
                            }
                            else
                            {
                                output.println("MESSAGE NOT ENOUGH POWER");
                            }
                        }
                        else {
                            output.println("MESSAGE NOT YOUR TURN");
                        }
                    }
                    else if(command.startsWith("KICK"))
                    {
                        if (Game.isCurrentPlayer(player))
                        {
                            if(Game.canAffordCard(player, player.table.kickStack.peek()))
                            {
                                player.buyKick();
                                gameState = player.updateGameState();
                                output.println("UPDATE");
                                outputStream.writeObject(gameState);
                                output.println("POWER "+player.power);
                            }
                            else
                            {
                                output.println("MESSAGE NOT ENOUGH POWER");
                            }
                        }
                        else {
                            output.println("MESSAGE NOT YOUR TURN");
                        }
                    }
                    
                    else if(command.startsWith("SV"))
                    {
                        if (Game.isCurrentPlayer(player))
                        {
                            if(Game.canAffordCard(player, player.table.superVillainStack.peek()))
                            {
                                player.buySuperVillain();
                                gameState = player.updateGameState();
                                output.println("UPDATE");
                                outputStream.writeObject(gameState);
                                output.println("POWER "+player.power);
                                if(player.table.superVillainStack.count() == 0)
                                    output.println("GAMEOVER");
                            }
                            else
                            {
                                output.println("MESSAGE NOT ENOUGH POWER");
                            }
                        }
                        else {
                            output.println("MESSAGE NOT YOUR TURN");
                        }
                    }
                    
                    else if (command.startsWith("QUIT")) {
                        return;
                    }
                    else if (command.startsWith("END"))
                    {
                        if (Game.isCurrentPlayer(player))
                        {
                        player.endTurn();
                        gameState = player.updateGameState();
                        output.println("UPDATE");
                        outputStream.writeObject(gameState);
                        //output.println("HAND");
                        //outputStream.writeObject(player.hand.getString());
                        output.println("POWER "+player.power);
                        output.println("MESSAGE OPPONENT'S TURN");
                        }
                        else
                            output.println("MESSAGE NOT YOUR TURN");
                            
                    }
                    else if(command.startsWith("GIMME"))
                    {
                        try{
                        //outputStream.reset();
                        output.println("OPP");
                        outputStream.writeObject(oppState);
                        outputStream.flush();
                        }
                        catch(StreamCorruptedException e)
                        {
                        e.printStackTrace();
                        }
                    }
                    else if(command.startsWith("GIMMEUPP"))
                    {
                        output.println("UPDATE");
                        outputStream.writeObject(gameState);
                    }
                    else if(command.startsWith("BATSIGNAL"))
                    {
                        int index = Integer.parseInt(command.substring(10));
                        player.batSig(index);
                    }
                    else if (command.startsWith("DISCARD"))
                    {
                        int index = Integer.parseInt(command.substring(8));
                        try{
                            player.discard(index);
                        }
                        catch(InterruptedException ex){
                            ex.printStackTrace();
                        }
                            
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {socket.close();} catch (IOException e) {}
            }
        }

}
