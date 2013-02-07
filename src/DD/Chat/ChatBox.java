package DD.Chat;

import com.sun.xml.internal.stream.Entity;

/*****************************************************************************************************
 * ChatBox will display all ChatMessages received by the client/server on the GUI. It will also keep
 * track of all previous messages so the user can go through older messages.
 ******************************************************************************************************/

public class ChatBox extends Entity
{

	@Override
	public boolean isExternal() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUnparsed() {
		// TODO Auto-generated method stub
		return false;
	}

} /* end ChatBox class */
