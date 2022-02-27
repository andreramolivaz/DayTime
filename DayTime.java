package daytime;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DayTime{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String serverAddress;
        serverAddress="casaroby.dlinkddns.com";
        int serverPort=13;
        byte buffer[] = new byte[512];
        
        while(true){
            DatagramPacket outPacket = new DatagramPacket("".getBytes(), 0, InetAddress.getByName(serverAddress), serverPort);
            DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);
            DatagramSocket dSocket = new DatagramSocket();

            dSocket.send(outPacket);
            
            dSocket.setSoTimeout(4000);
            
            try{
                dSocket.receive(inPacket);
                System.out.println("Pacchetto ricevuto: "+new String(buffer,0,inPacket.getLength()));
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(DayTimePersonnettaz.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            catch(SocketTimeoutException ex){
                System.out.println("Il server non ha rispostonto entro i 4 secondi stabiliti.");
            }
        }
    }
}
