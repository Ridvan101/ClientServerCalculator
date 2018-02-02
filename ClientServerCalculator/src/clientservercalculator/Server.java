package clientservercalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(7777);

        while (true) {
            System.out.println("Server is listening...");
            try (
                    Socket socket = server.accept();
                    InputStream input = socket.getInputStream();
                    OutputStream output = socket.getOutputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(input));) {

                String operandi = br.readLine();
                System.out.println(operandi);

                int prviOperand = Integer.parseInt(operandi.split(",")[0]);
                int drugiOperand = Integer.parseInt(operandi.split(",")[1]);

                String rezultat = String.valueOf(prviOperand + drugiOperand);
                System.out.println(rezultat);
                output.write((rezultat + "\r\n").getBytes());
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }
}
