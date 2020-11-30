import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;

public class Serveur {
    final static int PORT=7878;
    public static void main(String args[]) throws Exception {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println ("j'attends la connexion d'un client");
        Socket socket = serverSocket.accept();
        try {
            InputStream IS = socket.getInputStream();
            InputStreamReader ISR = new InputStreamReader(IS);
            BufferedReader reader = new BufferedReader(ISR);
            OutputStream OS = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(OS);
            String response=reader.readLine();
            System.out.println(response);
            writer.println("Bienvenue Mr le client");
            writer.flush();
            writer.println("Date: "+LocalDate.now().getDayOfMonth()+"/"+LocalDate.now().getMonth()+"/"+LocalDate.now().getYear());
            writer.flush();
            writer.println("send me sentence :");
            writer.flush();
            response = reader.readLine();
            while(!response.equals("bye")){
                System.out.println(response.toUpperCase());
                writer.println("send me sentence :");
                writer.flush();
                response = reader.readLine();
            }
            reader.close();
            writer.close();
            socket.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
