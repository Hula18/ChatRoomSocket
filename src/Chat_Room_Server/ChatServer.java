package Chat_Room_Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
    private static final int PORT = 7000 ;
    private final List<ClientHandler> clients = new ArrayList<>();


    public void Start_Server(){
        try{
            ServerSocket serverSocket = new ServerSocket(7000);
            System.out.println("Server started. Listening on port: "+PORT);

            //Clients kết nối đến server
            while (true){
                Socket clientSocket = serverSocket.accept() ;
                System.out.println("New client connected: "+clientSocket.getInetAddress().getHostAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket,System.currentTimeMillis()+"",this);
                clients.add(clientHandler) ;

                new Thread(clientHandler).start();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void broacastMessage(String id , String message){
        for(ClientHandler client : clients){
            if( !( client.getId().equals(id) ) ){
                client.sendMEssage(id+" : "+message);
            }
        }
    }

}
