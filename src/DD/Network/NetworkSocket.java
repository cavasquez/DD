package DD.Network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Queue;
import DD.Network.Message.NetworkMessage;

/*****************************************************************************************************
* NetworkSocket will contain the basic tools that Listener and Client will be using.
******************************************************************************************************/

public abstract class NetworkSocket extends Thread
{

        /************************************ Class Attributes *************************************/
	protected Socket socket = null;
	protected ObjectInputStream input = null;
	protected ObjectOutputStream output = null;
	protected int socketID;					/* Unique ID for thread */
        protected boolean done;                                 /* Thread is done */
	
	/************************************ Class Methods *************************************/
	public NetworkSocket(Socket socket, int socketID)
	{
		super("DDSocketThread" + Integer.toHexString(socketID));
		this.socket = socket;
		this.socketID = socketID;
                this.done = false;
	} /* end Server constructor */
	
	public void run() {} /* end run method */
	
	protected void createStreams()
	{
		try 
		{
			input = new ObjectInputStream(socket.getInputStream());
			output = new ObjectOutputStream(socket.getOutputStream());
		} /* end try */ 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /* end catch */
	} /* end createStreams method */

	protected void closeStreams()
	{
		try 
		{
			input.close();
			output.close();
		} /* end try */ 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} /* end catch */
		
	} /* end closeServerSocket method */

	public void close()
	{
		/* thread is done */
		done = true;
	} /* end close method */
	
} /* end NetworkSocket class */
