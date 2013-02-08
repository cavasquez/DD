package DD.Network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Queue;
import DD.Network.Message.NetworkMessage;

/*****************************************************************************************************
* DDSocket is an experimental socket that will attempt to use one thread to control both listening
* and sending.
******************************************************************************************************/

public abstract class DDSocket extends Thread
{

/************************************ Class Attributes *************************************/
protected Socket socket = null;
protected ObjectInputStream input = null;
protected ObjectOutputStream output = null;
protected volatile boolean sending;
protected volatile boolean done;  /* done should be called when thread is to be closed */
protected boolean working;	/* thread is doing work */
protected int socketID;	/* Unique ID for thread */
protected volatile Queue<NetworkMessage> messageList;	/* messages to be sent */

