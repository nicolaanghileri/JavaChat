package client;

import java.io.IOException;
import java.net.Socket;

/**
 * 
 * @author Nicola Anghileri
 * @version 06.03.2022
 */
public class Client {
    /**
     * 
     */
    public static final String HOST = "127.0.0.1";
    
    /**
     * 
     */
    public static final int PORT = 5555;
    
    
    public static void main(String[] args) throws IOException {
        try{
            Socket socket = new Socket(HOST, PORT);
            new Reader(socket).start();
            new Writer(socket).start();
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        
       
    }
    
   
}
