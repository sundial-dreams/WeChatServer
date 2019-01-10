import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 邓鹏飞
 *
 *
 * 服务端
 * 监听客户端请求
 */
public class ServerListener extends Thread{
    public void run(){
        try {
            ServerSocket serverSocket = new ServerSocket(2347);
            while(true){
                Socket socket = serverSocket.accept();
                ChatSocket chatSocket = new ChatSocket(socket);
                DataInputStream in = new DataInputStream(socket.getInputStream());
                String name = in.readUTF();
                chatSocket.start();
                ChatManager.getCm().add(name,chatSocket);
                ChatManager.getCm().onLine(name);
                System.out.print(name);
                System.out.println("链接成功");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
