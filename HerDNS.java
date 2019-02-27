import java.io.*;
import java.net.*;

class HerDNS
{
   public static void main(String args[]) throws Exception
      {
            DatagramSocket serverSocket = new DatagramSocket(7075);
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            
            System.out.println("-- Running Server at " + InetAddress.getLocalHost() + "--");
            while(true)
               {
                  //System.out.println("111");
                  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                  serverSocket.receive(receivePacket);
                  String sentence = new String("IPAddress");

                  InetAddress IPAddress = receivePacket.getAddress();
                  int port = receivePacket.getPort();

                  sendData = capitalizedSentence.getBytes();
                  DatagramPacket sendPacket =
                  new DatagramPacket(sendData, sendData.length, IPAddress, port);

                  serverSocket.send(sendPacket);
               }
      }
}