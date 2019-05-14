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
	while(true) {
	    try {
		
		 // - accept connection from server socket 
            Socket conn = ssock.accept();
            System.out.println("Connection b/w client and server is established.");
          
            DataInputStream din = new DataInputStream(conn.getInputStream());
	        int respDataLen = din.readInt();
	        System.out.println("Server: received data payload has length " + respDataLen);
	        byte[] bytes = new byte[respDataLen];
	        din.readFully(bytes);
	        //String data = new String(bytes, StandardCharsets.UTF_8);
            //System.out.println("data = " + data);
            
            DataOutputStream dout = new DataOutputStream(conn.getOutputStream());
	        long startTime = System.currentTimeMillis();
	        dout.writeInt(bytes.length);
	        dout.write(bytes);
	        dout.flush();
	        System.out.println("Server: sent response header and " + bytes.length + " bytes of payload data to client");
            
            conn.close();
            //terminate the server if client sends exit request
//            if(msg.equalsIgnoreCase("exit")) break;
            break;
//		  - read requests from connection repeatedly
//		  - for each request, compute an output and send a response
//		  - each message has a 4-byte header followed by a payload
//		  - the header is the length of the payload
//		    (signed, two's complement, big-endian)
//		  - the payload is a string
//		    (UTF-8, big-endian)
		
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    
        System.out.println("Shutting down Socket server!!");
        //close the ServerSocket object
        ssock.close();
    }
}
