package com.main.command.commands.util

import com.ayden.main.command.Command
import com.main.command.{Command, CommandException}
import org.bukkit.Bukkit
import org.bukkit.GameMode._
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object GamemodeCommand extends Command {
  private val gamemodeMap = Map(
    "0" -> SURVIVAL,
    "s" -> SURVIVAL,
    "survival" -> SURVIVAL,

    "1" -> CREATIVE,
    "c" -> CREATIVE,
    "creative" -> CREATIVE,

    "2" -> ADVENTURE,
    "a" -> ADVENTURE,
    "adventure" -> ADVENTURE,

    "3" -> SPECTATOR,
    "sp" -> SPECTATOR,
    "spectator" -> SPECTATOR
  )

  override def name: String = "gamemode"

  override def run(sender: CommandSender, arguments: Array[String]): Unit = {
    import org.bukkit.ChatColor._

    require(arguments.length == 1 || arguments.length == 2)
    // todo: encapsulate this into a method, it's very common
    val target: Player = if (arguments.length == 2) {
      val p = Bukkit.getPlayer(arguments(1))
      if (p == null)
        throw CommandException.PlayerNotFound(arguments(1))
      p
    } else {
      require(sender.isInstanceOf[Player], CommandException.MustBePlayer)
      sender.asInstanceOf[Player]
    }
    val gamemodeString = arguments(0)
    val maybeGamemode = gamemodeMap.get(gamemodeString)
    val gamemode = maybeGamemode match {
      case None => throw CommandException(s"Gamemode '$gamemodeString' does not exist.")
      case Some(g) => g
    }
    target.setGameMode(gamemode)
    target.sendMessage(s"${GOLD}Your gamemode has been set to $AQUA$gamemodeString$GOLD.")
  }
}
