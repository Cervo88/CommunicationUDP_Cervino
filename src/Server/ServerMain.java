package Server;

import java.io.IOException;
import java.net.*;

public class ServerMain {

    public static void main(String[] args) {

        try {

            DatagramSocket socket = new DatagramSocket(5000);

            System.out.println("Server avviato...");

            byte[] buffer = new byte[1024];

            while (true) {

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                socket.receive(packet);

                String messaggio = new String(packet.getData(), 0, packet.getLength());

                System.out.println("Ricevuto: " + messaggio);

                DatagramPacket risposta = new DatagramPacket(
                        packet.getData(),
                        packet.getLength(),
                        packet.getAddress(),
                        packet.getPort()
                );

                socket.send(risposta);

                System.out.println("Messaggio rimandato");
            }


        } catch (UnknownHostException e) {
            System.err.println("Server non trovato");

        } catch(SocketException e) {
            System.err.println("Errore");

        } catch (IOException e) {

            System.err.println("Errore nel server");

            e.printStackTrace();

        }

    }
}