package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

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
   
    /**
     * 
     */
    private Socket socket;
    
    /**
     * 
     */
    private String username;
    
    
    /**
     * 
     * @param socket
     * @param username
     * @throws IOException 
     */
    public Client(Socket socket, String username) throws IOException{
        this.socket = socket;
        this.username = username;
    }
    
    public void send(){
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
                printer.println("[" + this.username+ "] " + line);
                printer.flush();
            }
        }catch(IOException ex){
            System.out.println("Sending problem");
        }
    }
    
    public static void main(String[] args) throws IOException {
        try{
            //Prendere username
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter username: ");
            String username = scanner.nextLine();
            
            //Var 
            Socket socket = new Socket(HOST, PORT);
            Client client = new Client(socket, username);
            
            //Ascolto + scrittura
            new Reader(socket).start();
            client.send();
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }  
    }
}
