package fr.paulficot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {

    public static void clientMain()
    {
        String host = "127.0.0.1";
        int port = 1234;
        new SocketClient(host, port);
    }

    public SocketClient(String host, int port)
    {
        try
        {
            String serverHostname = new String(host);

            System.out.println("Connecting to host " + serverHostname + " on port " + port + ".");

            Socket echoSocket = null;
            PrintWriter out = null;
            BufferedReader in = null;

            try
            {
                echoSocket = new Socket(serverHostname, port);
                out = new PrintWriter(echoSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            }
            catch (UnknownHostException e)
            {
                System.err.println("Unknown host: " + serverHostname);
                System.exit(1);
            }
            catch (IOException e)
            {
                System.err.println("Connexion closed");
                System.exit(1);
            }
            out.println("tutu");
            out.println("tati");
            out.println("Coucou");
            String line = in.readLine();

            System.out.println("Message received from server: "+line);

            /** Closing all the resources */
            out.close();
            in.close();
            echoSocket.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
