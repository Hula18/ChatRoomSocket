package Chat_Room_Server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/*Xử lí dữ liệu từ client*/
public class ClientHandler implements Runnable {
    private Socket mySocket ;
    private String id ;
    private InputStream input ;
    private OutputStream output ;
    private ChatServer chatServer ;

    public ClientHandler(Socket mysocket, String id,ChatServer chatServer) {
        this.mySocket = mysocket;
        this.id = id;
        try{
            this.input = mySocket.getInputStream();
            this.output = mySocket.getOutputStream() ;
        }catch (Exception e){
            e.printStackTrace();
        }
        this.chatServer = chatServer ;
    }


    @Override
    public void run() {
         try{
             byte[] buffer = new byte[1024] ;
             int byteRead ;
             while((byteRead = input.read(buffer)) != -1){
                 String message = new String(buffer,0,byteRead) ;
                 chatServer.broacastMessage(this.id,message);
             }
         }catch (Exception e){
             e.printStackTrace();
         }
    }

    public void sendMEssage(String message){
        try{
            output.write(message.getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
