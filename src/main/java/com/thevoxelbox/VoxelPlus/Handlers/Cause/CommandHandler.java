package com.thevoxelbox.VoxelPlus.Handlers.Cause;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import com.thevoxelbox.VoxelPlus.Handlers.Result.*;

public class CommandHandler implements Listener
{
	public static boolean onCommand(final Player player, final String[] split, final String command, final String[] args)
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
		else if(command.equalsIgnoreCase("vhelm")) //wear block command
		{
			switch(args.length)
			{
			case 1:
				return WearBlock.WearBlock(player, args[0]);
			case 2:
				return WearBlock.WearBlock(player, args[0], args[1]);
			default:
				player.sendMessage("Please type as follows: /vhelm <ID> <data value>");
				break;
			}
		}
		return false;
	}
}
