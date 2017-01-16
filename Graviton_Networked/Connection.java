import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.ConnectException;
import java.net.BindException;

/**
 * A class consisting of methods for setting up network connections between scenarios.
 * 
 * To use this class, simply instantiate it. There are 3 possible constructors to choose from.
 * The first constructor takes no parameters, you can use this to set up a default server connection 
 * which will listen for a client on the default port. The second option is to specify an IP address
 * as a String, this will cause the connection to connect as a client on the default port to the 
 * specified IP address. Thirdly, you can select both the host and the port to connect to. In the last
 * two cases, leaving the host parameter blank causes the connection to be set up as a server, so if 
 * you want a server connection on a specific port, simply call <code>new Connection("", port);</code>.
 * <br>
 * <br>
 * Once the connection is initialised at both ends, you can then use the various send and receive methods
 * such as sendInts and receiveInts to send and receive data at either side of the connection. Variable
 * arguments are used on the send methods, so you can pass as many parameters to them as you like and
 * the corresponding receive method will wrap them up in an array.
 * <br>
 * <br>
 * Once the connection is finished with, the <code>close()</code> method should be called to clear the
 * connections and free up the ports.
 * 
 * @author Michael Berry
 * @version 12/04/2008
 */
public class Connection 
{
    /** The type of the connection, whether this is the client or server end. */
    public enum Type {
        /** A Server connection */ SERVER,
        /** A Client connection */ CLIENT
    }
    
    /** The stream used for writing objects */
    private ObjectOutputStream out;
    /** The stream used for reading objects */
    private ObjectInputStream in;
    /** True if the connection is initialised */
    private boolean initialised = false;
    /** The type of connection */
    private Type type;
    /** The port the connection is on */
    private int port;
    /** The Local IP address */
    private String thisIP;
    /** The remote IP address */
    private String remoteIP;
    
    /** The default port if no other is specified */
    public final int DEFAULT_PORT = 55555;
    
    /**
     * Initialises the connection as a server using the default port.
     */
    public Connection()
    {
        init("", DEFAULT_PORT);
    }
    
    /**
     * Initialises the connection using the default port.
     * @param host The host address of the server. If this contains an address, the program is initialised
     * as a client that connects to the server with the address host. If host is blank, the program is 
     * initialised as the server.
     */
    public Connection(String host)
    {
        init(host, DEFAULT_PORT);
    }
    
    /**
     * Initialises the connection with a specific port.
     * @param host The host address of the server. If this contains an address, the program is initialised
     * as a client that connects to the server with the address host. If host is blank, the program is 
     * initialised as the server.
     * @param port The port to use with the connection.
     * @throws RuntimeException if the connection has already been initialised.
     */
    public Connection(String host, int port)
    {
        init(host, port);
    }
    
    /**
     * Initialises the connection.
     * @param host The host address of the server. If this contains an address, the program is initialised
     * as a client that connects to the server with the address host. If host is blank, the program is 
     * initialised as the server.
     * @param port The port to use with the connection.
     * @throws RuntimeException if the connection has already been initialised.
     */
    private void init(String host, int port)
    {
        if(isInitialised()) throw new RuntimeException("Already initialised!");
        
        remoteIP = host;
        this.port = port;
        try {
            thisIP = InetAddress.getLocalHost().getHostAddress();
        }
        catch(UnknownHostException ex) {
            thisIP = "Unknown";
        }
        
        if(host.trim().equals("") || host==null) {
            initServer(port);
        }
        else {
            initClient(host, port);
        }
        initialised = true;
    }
    
    /**
     * Sends Strings.
     * @throws RuntimeException if there is a problem with the connection or the connection hasn't been initialised.
     */
    public void sendStrings(String ...arr) {
        try {
            if(!isInitialised()) throw new NullPointerException();
            out.writeObject(arr);
            out.flush();
        }
        catch(NullPointerException ex) {
            throw new RuntimeException("Error: Connection has not been initialised.");
        }
        catch(IOException ex) {
            throw new RuntimeException("IO Error: Connection may have been dropped or reset.");
        }
    }
    
    /**
     * Receives Strings.
     * @throws RuntimeException if there is a problem with the connection.
     */
    public String[] receiveStrings() {
        String[] dat = null;
        try {
            if(!isInitialised()) throw new NullPointerException();
            dat = (String[])in.readObject();
        }
        catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        catch(NullPointerException ex) {
            throw new RuntimeException("Error: Connection has not been initialised.");
        }
        catch(IOException ex) {
            throw new RuntimeException("IO Error: Connection may have been dropped or reset.");
        }
        
        return dat;
    }    
    
    /**
     * Sends Integers.
     * @throws RuntimeException if there is a problem with the connection or the connection hasn't been initialised.
     */
    public void sendInts(int ...arr) {
        try {
            if(!isInitialised()) throw new NullPointerException();
            out.writeObject(arr);
            out.flush();
        }
        catch(NullPointerException ex) {
            throw new RuntimeException("Error: Connection has not been initialised.");
        }
        catch(IOException ex) {
            throw new RuntimeException("IO Error: Connection may have been dropped or reset.");
        }
    }
    
    /**
     * Receives Integers.
     * @throws RuntimeException if there is a problem with the connection.
     */
    public int[] receiveInts() {
        int[] dat = null;
        try {
            if(!isInitialised()) throw new NullPointerException();
            dat = (int[])in.readObject();
        }
        catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        catch(NullPointerException ex) {
            throw new RuntimeException("Error: Connection has not been initialised.");
        }
        catch(IOException ex) {
            throw new RuntimeException("IO Error: Connection may have been dropped or reset.");
        }
        
        return dat;
    }
    
    /**
     * Sends Booleans.
     * @throws RuntimeException if there is a problem with the connection or the connection hasn't been initialised.
     */
    public void sendBooleans(boolean ...arr) {
        try {
            if(!isInitialised()) throw new NullPointerException();
            out.writeObject(arr);
            out.flush();
        }
        catch(NullPointerException ex) {
            throw new RuntimeException("Error: Connection has not been initialised.");
        }
        catch(IOException ex) {
            throw new RuntimeException("IO Error: Connection may have been dropped or reset.");
        }
    }
    
    /**
     * Receives Booleans.
     * @throws RuntimeException if there is a problem with the connection.
     */
    public boolean[] receiveBooleans() {
        boolean[] dat = null;
        try {
            if(!isInitialised()) throw new NullPointerException();
            dat = (boolean[])in.readObject();
        }
        catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        catch(NullPointerException ex) {
            throw new RuntimeException("Error: Connection has not been initialised.");
        }
        catch(IOException ex) {
            throw new RuntimeException("IO Error: Connection may have been dropped or reset.");
        }
        
        return dat;
    }    
    
    /**
     * Sends Objects.
     * @throws RuntimeException if there is a problem with the connection or the connection hasn't been initialised.
     */
    public void sendObjects(Object ...arr) {
        try {
            if(!isInitialised()) throw new NullPointerException();
            out.writeObject(arr);
            out.flush();
        }
        catch(NullPointerException ex) {
            throw new RuntimeException("Error: Connection has not been initialised.");
        }
        catch(IOException ex) {
            throw new RuntimeException("IO Error: Connection may have been dropped or reset.");
        }
    }
    
    /**
     * Receives Objects.
     * @throws RuntimeException if there is a problem with the connection.
     */
    public Object[] receiveObjects() {
        Object[] dat = null;
        try {
            if(!isInitialised()) throw new NullPointerException();
            dat = (Object[])in.readObject();
        }
        catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        catch(NullPointerException ex) {
            throw new RuntimeException("Error: Connection has not been initialised.");
        }
        catch(IOException ex) {
            throw new RuntimeException("IO Error: Connection may have been dropped or reset.");
        }
        
        return dat;
    }
    
    /**
     * Initialises the server.
     */
    private void initServer(final int port) {
        type = Type.SERVER;
        new Thread() {
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(port);
                    Socket clientSocket = serverSocket.accept();
                    out = new ObjectOutputStream(clientSocket.getOutputStream());
                    in = new ObjectInputStream(clientSocket.getInputStream());
                }
                catch(BindException ex) {
                    initServer(port+1);
                }
                catch(IOException ex) {
                    System.err.println("Error initialising server.");
                    ex.printStackTrace();
                }
            }
        }.start();
        
    }
    
    /**
     * Initialises the client.
     * @param host The server that the client will connect to.
     * @param port The port the client will use to connect.
     * @throws RuntimeException if the client can't connect.
     */
    private void initClient(String host, int port) {
        type = Type.CLIENT;
        try {
            Socket socket = new Socket(host, port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        }
        catch(ConnectException ex) {
            throw new RuntimeException("IO Error: Connection to server failed. \nCheck the server is running the server side of the program, the server's IP \naddress is correct, and that the ports match and are not blocked by \na firewall.");
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Returns a boolean value based on whether this connection is initialised or not.
     * @return True if the connection has been initialised, False otherwise.
     */
    public boolean isInitialised() {
        return initialised;
    }
    
    /**
     * Closes the connections.
     * @throws RuntimeException if the streams cannot be closed.
     */
    public void close()
    {
        try {
            initialised = false;
            out.close();
            in.close();
        }
        catch(IOException ex) {
            throw new RuntimeException("Error closing streams...");
        }
    }
    
    /**
     * Gets the local IP address.
     * @return The IP address of the local machine. If the IP address cannot be found, "unknown" is returned.
     * @see getRemoteIP()
     */
    public String getLocalIP()
    {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        }
        catch(UnknownHostException ex) {
            return "unknown";
        }
    }
    
    /**
     * Gets the remote IP address this computer is connected to.
     * @return The remote IP address at the other end of this connection.
     * @see getLocalIP()
     */
    public String getRemoteIP()
    {
        if(!isInitialised()) throw new RuntimeException("Connection not initialised.");
        
        return remoteIP;
    }
    
    /**
     * Gets the port being used for this connection.
     * @return The port used for this connection.
     */
    public int getPort()
    {
        if(!isInitialised()) throw new RuntimeException("Connection not initialised.");
        return port;
    }
    
    /**
     * Determines whether the port being used is the default port.
     * @return True if the port being used is the default port.
     */
    public boolean isDefaultPort()
    {
        return port==DEFAULT_PORT;
    }
    
    /**
     * Returns whether this computer is a client or a server.
     * @returns server if the computer is a server, client if it's a client.
     */
    public Type getType()
    {
        return type;
    }
    
    /**
     * Gives details of the connection in readable form. Details included are the machine's local IP, the remote IP the machine is connected to, 
     * whether the connection is a client or server and the port used for connections.
     * @return Connection details.
     */
    public String toString()
    {
        if(!isInitialised()) return "Connection not initialised.";
        
        String ret="Connection initialised with " + remoteIP + " : \n";

        ret += "Local IP: " + thisIP + "\n";
        ret += "Client/Server: " + type;
        ret += "Port: " + port + "\n";
        
        return ret;
    }
    
    /**
     * Determines whether 2 connections are equivalent. To be equivalent, the connection type, local IP, remote IP and port all need to match.
     * @return True if the 2 connections are equivalent, False otherwise.
     */
    public boolean equals(Connection connection)
    {
        if(!(getLocalIP().equals(connection.getLocalIP()))) return false;
        if(!(getRemoteIP().equals(connection.getRemoteIP()))) return false;
        if(getPort() != connection.getPort()) return false;
        if(!(getType().equals(connection.getType()))) return false;
        
        return true;
    }
    
}
