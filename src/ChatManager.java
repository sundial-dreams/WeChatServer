import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * 邓鹏飞
 *
 * 客户端请求管理类
 */
public class ChatManager
{
   private ChatManager(){

   }
   private static final ChatManager cm = new ChatManager();
   public static ChatManager getCm(){
       return cm;
   }

   Map<String,ChatSocket> map = new HashMap<>();//账号名映射客户端请求

   public void add(String name,ChatSocket chatSocket)
   {
      map.put(name,chatSocket);
   }
   public void remove(String name){
       map.remove(name);
   }
   //发消息
   public void sendMsg(String from,String to,String Msg) throws IOException {
       for(Map.Entry<String ,ChatSocket> entry:map.entrySet()){
                  ChatSocket socket = entry.getValue();
                  if(entry.getKey().equals(to))
                       socket.out(from+" "+to+" "+Msg);
       }
   }
   //上线
   public void onLine(String dialogName){
       for(Map.Entry<String,ChatSocket> entry:map.entrySet()){
           ChatSocket socket = entry.getValue();
           if(!entry.getKey().equals(dialogName)){
               socket.out(dialogName+" #### #@@@");
           }
       }

   }
   //下线
   public void onOut(String name){

       for(Map.Entry<String,ChatSocket> entry:map.entrySet()){
           ChatSocket socket = entry.getValue();
           if(!entry.getKey().equals(name)){
               socket.out(name+" #### @@@@");
           }
       }

   }


}