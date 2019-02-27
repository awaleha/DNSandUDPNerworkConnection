import java.io.*;
import java.net.*;
import javax.net.ssl.HttpsURLConnection;

class UDPClient
{
   public static void main(String args[]) throws Exception
   {

      HTTPGet http = new HTTPGet();
      http.sendGet();

      BufferedReader inFromUser =
      new BufferedReader(new InputStreamReader(System.in));
      DatagramSocket clientSocket = new DatagramSocket();
      InetAddress IPAddress = InetAddress.getByName("localhost");
      byte[] sendData = new byte[1024];
      byte[] receiveData = new byte[1024];
      String sentence = inFromUser.readLine();
      sendData = sentence.getBytes();
      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 7077);
      clientSocket.send(sendPacket);
      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
      clientSocket.receive(receivePacket);
      String modifiedSentence = new String(receivePacket.getData());
      System.out.println("FROM SERVER:" + modifiedSentence);
      
       sendPacket = new DatagramPacket(modifiedSentence, sendData.length, IPAddress, 7076);
      clientSocket.send(sendPacket);
       receivePacket = new DatagramPacket(receiveData, receiveData.length);
      clientSocket.receive(receivePacket);
       String hisDns = new String(receivePacket.getData());
      System.out.println("FROM SERVER:" + hisDns);

       sendPacket = new DatagramPacket(hisDns, sendData.length, IPAddress, 7075);
      clientSocket.send(sendPacket);
       receivePacket = new DatagramPacket(receiveData, receiveData.length);
      clientSocket.receive(receivePacket);
       String herDNS = new String(receivePacket.getData());
      System.out.println("FROM SERVER:" + herDNS);

      clientSocket.close();
   }

   private void sendGet() throws Exception {
      String url = "http://www.google.com/search?q=ryerson";
      
      URL obj = new URL(url);
      HttpURLConnection con = (HttpURLConnection) obj.openConnection();

      // optional default is GET
      con.setRequestMethod("GET");

      //add request header
      con.setRequestProperty("User-Agent", USER_AGENT);

      int responseCode = con.getResponseCode();
      System.out.println("\nSending 'GET' request to URL : " + url);
      System.out.println("Response Code : " + responseCode);

      BufferedReader in = new BufferedReader(
              new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuffer response = new StringBuffer();

      int num = 2; //this is the file number we are lookig for

      while ((inputLine = in.readLine()) != null) {
         response.append(inputLine);
         if (inputLine.contains("hiscinema.com/F" + num)){
            //send get request to herCDN for herCDN.com/F + num
         }
      }
      in.close();

      //print result
      System.out.println(response.toString());
   }
}