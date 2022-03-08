package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Nicola Anghileri
 * @version 06.03.2022
 */
public class Reader extends Thread{
    
    private Socket socket;
    
    public Reader(Socket socket){
        this.socket = socket;
    }
    
    @Override
    public void run() {
        String line = null;
        try{
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            while(((line = reader.readLine()) != null)){
                System.out.println(line);
            }
        }catch(IOException e){}
    }
    
}
