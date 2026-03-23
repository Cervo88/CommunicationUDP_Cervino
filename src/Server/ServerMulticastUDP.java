package Server;

import java.io.IOException;
import java.net.*;

public class ServerMulticastUDP {
    //colore del prompt del server
    public static final String ANSI_BLUE = "\u001B[34m";
    //colore del prompt del client
    public static final String ROSSO = "\033[1;31m";
    // colore del gruppo
    public static final String GREEN = "\033[4;32m";
    //colore reset
    public static final String RESET = "\033[0m";

    public static void main(String[] args) {

        try {

            // 1) Creazione socket server su porta 5000
            DatagramSocket socket = new DatagramSocket(5000);

            System.out.println(ANSI_BLUE + "Server avviato..." + RESET);

            byte[] buffer = new byte[1024];

            while(true) {

                //recezione client
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String messaggio = new String(packet.getData(), 0, packet.getLength());

                System.out.println(ROSSO + "Ricevuto dal client: " + messaggio + RESET);

                // Preparazione risposta
                String risposta = "OK";
                DatagramPacket rispostaPacket = new DatagramPacket(
                        packet.getData(),
                        packet.getLength(),
                        packet.getAddress(),
                        packet.getPort()
                );

                // Invio risposta al client
                socket.send(rispostaPacket);
            System.out.println(ANSI_BLUE + "Messaggio rimandato" + RESET);

            //invio multicast
                InetAddress gruppo = InetAddress.getByName("239.255.255.250");

                String msgGruppo = "Messaggio al gruppo";
                DatagramPacket gruppoPacket = new DatagramPacket(
                        packet.getData(),
                        packet.getLength(),
                        gruppo,
                        1900
                        );
                //metodo per inviare
                socket.send(gruppoPacket);
                System.out.println(GREEN + "Messaggio inviato al gruppo" + RESET);

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