package com.thevoxelbox.VoxelPlus.Handlers.Cause;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import com.thevoxelbox.VoxelPlus.Handlers.Result.*;

public class CommandHandler implements Listener
{
	public static boolean onCommand(final Player player, final String[] split, final String command)
	{
		if(command.equalsIgnoreCase("getmedrunk")) //drink Lager command
		{
			return Drunk.DrinkBeer(player);
		}
		else if(command.equalsIgnoreCase("getmehyper")) //drink Coffee command
		{
			return Caffine.DrinkCoffee(player);
		}
		else if(command.equalsIgnoreCase("getmesmart")) //read Book command
		{
			return LevelGain.ReadBook(player);
		}
		else if(command.equalsIgnoreCase("getmecancer")) //smoke Pipe command
		{
			return Smoke.SmokePipe(player);
		}
		else if(command.equalsIgnoreCase("vhelm"))
		{
			return WearBlock.WearBlock(player, id, data);
		}
		return false;
	}
}
