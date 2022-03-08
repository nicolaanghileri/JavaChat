
package server;

/**
 *
 * @author spade
 */
import client.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Handler extends Thread{
    
    private Socket cli;

    private Server srv;

    private InputStream input;

    private OutputStream output;

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

    public void sendMessage(byte[] data) {
        try{
            output.write(data);
        }catch(IOException ioe){
            System.out.println("");
        }
    }
}
