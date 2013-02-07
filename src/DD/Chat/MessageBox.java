package DD.Chat;

import com.sun.xml.internal.stream.Entity;

/*****************************************************************************************************
 * MessageBox will be the box through which the player will communicate (chat) with the rest of the
 * players. It is strictly input and will send the message to ChatSystem for interpretation and
 * network access.
 ******************************************************************************************************/

public class MessageBox  extends Entity
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

} /* end MessageBox method */
