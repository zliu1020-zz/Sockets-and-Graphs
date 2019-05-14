import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.nio.charset.*;

class CCServer {
    public static void main(String args[]) throws Exception {            
	if (args.length != 1) {
	    System.out.println("usage: java CCServer port");
	    System.exit(-1);
	}
	int port = Integer.parseInt(args[0]);

	ServerSocket ssock = new ServerSocket(port);
	System.out.println("listening on port " + port);
        
    Runtime.getRuntime().addShutdownHook(new Thread() {
        @Override
        public void run() {
            System.out.println("SIGTERM/SIGKILL/SIGINT received.");
            try{
                ssock.close();
                System.out.println("Terminate the socket server.");
            }catch(Exception e){
                e.printStackTrace();
            }
        }   
    });
        
	while(true) {
	    try {
		
		 // - accept connection from server socket 
            Socket conn = ssock.accept();
            System.out.println("Connection b/w client and server is established.");
          
            while(true){
                //	- read requests from connection repeatedly    
                InputStream is;
                DataInputStream din;
                int dataLength;
                try{
                    is = conn.getInputStream();
                    din = new DataInputStream(is);
	                dataLength = din.readInt();
                }catch(IOException e){
                    System.out.println("Client has terminated the connection. Moving on.");
                    break;
                }      
	        
                byte[] bytes = new byte[dataLength];
	            din.readFully(bytes);
            
                //	- for each request, compute an output and send a response    
                DataOutputStream dout = new DataOutputStream(conn.getOutputStream());
                //	- each message has a 4-byte header followed by a payload
                //	- the header is the length of the payload
                //	  (signed, two's complement, big-endian)
                dout.writeInt(bytes.length);
        
                //	- the payload is a string
                //	(UTF-8, big-endian)
	            dout.write(bytes);
	            dout.flush();    
            }

            conn.close();	
	    } catch (Exception e) {
		  e.printStackTrace();
	    }
	}
    }
}
