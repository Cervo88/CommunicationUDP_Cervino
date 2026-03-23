package Client;

import java.net.*;
import java.util.Scanner;

public class ClientMulticastUDP {
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

                // Creazione socket client UDP
                DatagramSocket socket = new DatagramSocket();

                // Indirizzo server
                InetAddress server = InetAddress.getByName("localhost");

                System.out.println(ROSSO + "CLIENT AVVIATO" + RESET);

                // Input dell'utente
                Scanner input = new Scanner(System.in);
                System.out.print("Scrivi messaggio: ");
                String messaggio = input.nextLine();

                // Creazione del pacchetto da inviare
                DatagramPacket packet = new DatagramPacket(
                                messaggio.getBytes(),
                                messaggio.length(),
                                server,
                                5000
                        );

                //Invio messaggio al server
                socket.send(packet);
                System.out.println(ROSSO + "Messaggio inviato al server" + RESET);

                byte[] buffer = new byte[1024];
                DatagramPacket risposta = new DatagramPacket(buffer, buffer.length);

                //Conferma della visione del messaggio da parte del server
                socket.receive(risposta);

                String msgRicevuto =
                        new String(risposta.getData(), 0, risposta.getLength());

                System.out.println(ANSI_BLUE + "Risposta server: " + msgRicevuto + RESET);

                // Creazione multicast
                MulticastSocket mSocket = new MulticastSocket(1900);

                // Accesso al gruppo multicast
                InetAddress gruppo = InetAddress.getByName("239.255.255.250");
                mSocket.joinGroup(gruppo);

                System.out.println(GREEN + "In attesa messaggio multicast..." + RESET);

                DatagramPacket gruppoPacket =
                        new DatagramPacket(buffer, buffer.length);

                mSocket.receive(gruppoPacket);

                String msgGruppo =
                        new String(gruppoPacket.getData(), 0, gruppoPacket.getLength());

                System.out.println(GREEN + "Messaggio dal gruppo: " + msgGruppo + RESET);

                // uscita dal gruppo
                mSocket.leaveGroup(gruppo);

                socket.close();
                mSocket.close();

            } catch (Exception e) {

                System.out.println("Errore nella comunicazione");

                e.printStackTrace();

            }

        }
    }
