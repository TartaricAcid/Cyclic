package com.lothrazar.cyclicmagic.command;
import com.lothrazar.cyclicmagic.util.UtilChat;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class CommandGetHome extends BaseCommand implements ICommand {
  public static final String name = "gethome";
  public CommandGetHome(boolean op) {
    super(name, op);
  }
  @Override
  public void execute(MinecraftServer server, ICommandSender ic, String[] args) {
    if (ic instanceof EntityPlayer == false) { return; }
    EntityPlayer player = (EntityPlayer) ic;
    if (player.dimension != 0) {
      UtilChat.addChatMessage(player, "command.gethome.overworld");
      return;
    }
    BlockPos coords = player.getBedLocation(0);
    if (coords == null) {
      UtilChat.addChatMessage(player, "command.gethome.bed");
    }
    else {
      String pos = coords.getX() + ", " + coords.getY() + ", " + coords.getZ();
      UtilChat.addChatMessage(player, UtilChat.lang("command.gethome.yours") + pos);
    }
  }
}