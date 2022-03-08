package server;

/**
 * Handler that enstablishs the comunications
 * between the server and the clients.
 * @author Lorenzo Spadea
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server{
    /**
     * Listening port of the server.
     */
    public final static int PORT = 5555;

    /**
     * List of handler listener for the clients.
     */
    public List<Handler> connections;

    /**
     * Istantiate the server with the list of handlers.
     */
    public Server(){
        this.connections = new ArrayList<Handler>();
    }

    /**
     * Broadcasts the datas to all the connectzed clients.
     * @param datas the datsa to send.
     */
    public synchronized void sendMessage(byte[] datas){
        for(int i = 0 ; i < connections.size();i++){
            connections.get(i).sendMessage(datas); 
        }   
    }
    
    /**
     * Adds client when conncetion is enstablished.
     * @param client the client connected.
     */
    public void addClient(Handler client){
        connections.add(client);
    }

    /**
     * Removes client from the handler list when connection dies.
     * @param client the client to remove.
     */
    public void removeClient(Handler client){
        connections.remove(client);
    }

    public static void main(String[] args){
        Server sr = new Server();
        try{
            ServerSocket srv = new ServerSocket(PORT);
            while(true){
                
                System.out.println("Listening on port " + PORT);
                Socket client = srv.accept();
                new Handler(client , sr).start();
                System.out.println("Connection from "
                        + client.getRemoteSocketAddress().toString());
            }
        }catch(IOException ioe){
            System.out.println("Server not started correctly");
        }
    }
}

