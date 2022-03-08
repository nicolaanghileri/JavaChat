package server;

/**
 * Center that enstablishs the connection between multiple clients to make 
 * them able to chat.
 * @author Lorenzo Spadea
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Handler extends Thread{
    
    /**
     * The client socket for comunication.
     */
    private Socket cli;

    /**
     * The Server hosting the comunication.
     */
    private Server srv;

    /**
     * The input reading the datas.
     */
    private InputStream input;

    /**
     * The output sending out the datas.
     */
    private OutputStream output;

    /**
     * Istantiate the Hdnaler with the client socket connection and the server 
     * socket connection
     * @param client
     * @param server 
     */
    public Handler(Socket client , Server server){
        this.cli = client;
        this.srv = server;

        try{
            this.input = client.getInputStream();
            this.output = client.getOutputStream();
        }catch(IOException ioe){
            System.out.println("Error enstablishing the two sided comunication");
        }
    }

    /**
     * Sends the message sent from the client to the server.
     */
    public void sendMessage(byte[] data) {
        try{
            output.write(data);
        }catch(IOException ioe){
            System.out.println("Error Sending datas");
        }
    }
    
    @Override
    public void run(){
        try {
            srv.addClient(this);
            int reading = 0;
            while((reading = input.read()) != -1){
                srv.sendMessage(new byte[]{(byte) reading});
            }
            output.close();
            input.close();
            cli.close();
            srv.removeClient(this);
        }catch(IOException ioe){
            System.out.println("Error sending message");
        }
    }
}