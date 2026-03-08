package Client;

import java.net.*;
import java.util.Scanner;

public class ClientMain {

    public static void main(String[] args) {

        try {

            DatagramSocket socket = new DatagramSocket();

            InetAddress server = InetAddress.getByName("localhost");

            Scanner input = new Scanner(System.in);

            System.out.print("Scrivi messaggio: ");

            String messaggio = input.nextLine();

            byte[] buffer = messaggio.getBytes();

            DatagramPacket packet =
                    new DatagramPacket(buffer, buffer.length, server, 5000);

            socket.send(packet);

            buffer = new byte[1024];

            DatagramPacket risposta = new DatagramPacket(buffer, buffer.length);

            socket.receive(risposta);

            String rispostaServer = new String(risposta.getData(),0,risposta.getLength());

            System.out.println("Risposta server: " + rispostaServer);

            socket.close();

        } catch (Exception e) {

            System.out.println("Errore nella comunicazione");

            e.printStackTrace();

        }

    }
}