package fr.paulficot;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server that send data to a server
 *
 * @author Paul FICOT
 * @version 1.0
 */
public class SocketServer extends Thread {

    public static final int PORT_NUMBER = 1234;

    protected Socket socket;

    /**
     * Constructor of the socket
     *
     * @param socket java socket
     */
    private SocketServer(Socket socket) {
        this.socket = socket;
        System.out.println("New client connected from " + socket.getInetAddress().getHostAddress());
        start();
    }

    /**
     * Instantiate socket
     */
    public void run() {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = socket.getInputStream();
            out = socket.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String request;
            while ((request = br.readLine()) != null)
            {
                System.out.println("Message received from server:" + request);
                if( request.equals("Coucou") )
                {
                    String msgReturn = "Bonjour\n";
                    out.write(msgReturn.getBytes());
                    System.exit(1);
                }
            }

        } catch (IOException ex) {
            System.out.println("Unable to get streams from client");
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Start the socket
     */
    public static void serverMain() {
        System.out.println("SocketServer waiting client");
        ServerSocket server = null;
        try
        {
            server = new ServerSocket(PORT_NUMBER);
            while (true)
            {
                /**
                 * create a new {@link SocketServer} object for each connection
                 * this will allow multiple client connections
                 */
                new SocketServer(server.accept());
            }
        } catch (IOException ex) {
            System.out.println("Unable to start server.");
        } finally
        {
            try
            {
                if (server != null)
                    server.close();
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }

}
