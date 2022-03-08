package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Nicola Anghileri
 * @version 06.03.2022
 */
public class Writer extends Thread{
    /**
     * 
     */
    private Socket socket = null;
    
    /**
     * 
     * @param socket
     */
    public Writer(Socket socket){
        this.socket = socket;
    }
    
    /**
     * 
     */
    @Override
    public void run(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        PrintWriter printer = null;
        try {
            printer = new PrintWriter(socket.getOutputStream());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        String line = null;
        try{
            while((line = reader.readLine()) != null){
                printer.println(line);
                printer.flush();
            }
        }catch(IOException ex){
            
        }
    }
}
