package client;

import java.io.IOException;
import java.net.Socket;

/**
 * 
 * @author Nicola Anghileri
 * @version 06.03.2022
 */
public class Client {

    
    
    public static void main(String[] args) throws IOException {
        Socket socket = null;
       
        try{
            socket = new Socket("127.0.0.1", 5555);
            System.out.println("Connected with: " + socket.getInetAddress() +
                    " at port: " + socket.getPort());
            
            
            
            
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        
       
    }
    
   
}
