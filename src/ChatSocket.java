import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 邓鹏飞
 *
 * 服务端
 */
public class ChatSocket extends Thread {
    Socket socket;
    public ChatSocket(Socket s) {
        socket = s;
    }

    public void out(String Msg) {
        try {
            new DataOutputStream(socket.getOutputStream()).writeUTF(Msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void run() {
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            String line;
            while ((line = in.readUTF()) != null) {
                System.out.println(line);
                String str[] = line.split(" ");
                String I_account = str[0];
                String Y_account = str[1];
                String Msg = "";
                for (int i = 2; i < str.length; i++) {
                    Msg += str[i]+" ";
                }


                if (I_account.equals("####")) {
                    ChatManager.getCm().remove(Y_account);
                    ChatManager.getCm().onOut(Y_account);
                    System.out.println(Y_account + "已退出!");
                }
                else if(I_account.equals("###@")){
                    ChatManager.getCm().sendMsg(Y_account,Msg,I_account);
                }
                else if(I_account.equals("##@@"))
                {
                    ChatManager.getCm().sendMsg(Y_account,Msg,I_account);
                }
                else {
                    if(Msg.equals("####")||Msg.equals("###@")||Msg.equals("##@@")||Msg.equals("#@@@"))
                        Msg+=".";
                    ChatManager.getCm().sendMsg(I_account, Y_account, Msg);
                }
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

}
