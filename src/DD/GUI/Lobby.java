package DD.GUI;

import java.util.ArrayList;

import DD.Character.DDCharacter;

public class Lobby{
	
	
	int playerNum;
	String lobbyName;
	ArrayList<DDCharacter> charList = new ArrayList<DDCharacter>();

	public Lobby ()
	{
		playerNum = 0;
		
	}
	
	public Lobby(String lobbyName)
	{
		this.lobbyName = lobbyName;
		playerNum = 0;
	}
	
	public void addChar (DDCharacter character)
	{
		charList.add(character);
	}
	
	public ArrayList<DDCharacter> getCharList()
	{
		return charList;
	}
	
	public void removeChar(DDCharacter character)
	{
		String characterName = character.getCharacterSheet().getName();
		
		for (int i = 0; i < charList.size(); i++) 
		{
			String temp = charList.get(i).getCharacterSheet().getName();
			if(temp.equals(characterName))
			{
				charList.remove(i);
			}
			
			
		}
		
	}
	
	public void removeChar (int index)
	{
		charList.remove(index);
	}
	
}
