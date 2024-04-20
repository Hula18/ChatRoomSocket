package Chat_Room_Client;


import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    private static final int PORT= 7000 ;
    private static final String url = "localhost" ;

    public void startClient(){
        try{
            Socket socket = new Socket(url,PORT);
            System.out.println("Connected to server");

            /*Liên tục đọc giữ liệu từ server*/
            ClientListener clientListener = new ClientListener(socket) ;
            new Thread(clientListener).start();

            /*Liên tục đọc giữ liệu từ scanner*/
            OutputStream outputStream = socket.getOutputStream() ;
            Scanner sc = new Scanner(System.in) ;

            while(true){
                String message = sc.nextLine() ;
                outputStream.write(message.getBytes());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
