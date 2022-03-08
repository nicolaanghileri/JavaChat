package server;
import  client.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server{
    public final static int PORT = 5555;

    public List<Handler> connections;

    public Server(){
        this.connections = new ArrayList<Handler>();
    }

    public synchronized void sendMessage(byte[] datas){
        for(int i = 0 ; i < datas.length; i++){
            connections.get(i).sendMessage(datas);
        }   
    }

    public void addClient(Handler client){
        connections.add(client);
    }

    public void removeClient(Handler client){
        connections.remove(client);
    }

    public void main(String[] args){
        try{
            ServerSocket srv = new ServerSocket(PORT);

            while(true){
                
                System.out.println("Listening on port " + PORT);
                Socket client = srv.accept();
                new Handler(client , this).start();
                System.out.println("Connection from "
                        + client.getRemoteSocketAddress().toString());
            }
        }catch(IOException ioe){
            System.out.println("Server not started correctly");
        }
    }
}

