package com.ayden.main.command.commands.util

import com.ayden.main.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.GameMode._

import scala.None

object GamemodeCommand extends Command {
  override def name: String = "gamemode"

  private val gamemodeMap = Map(
    "0" ->        SURVIVAL,
    "s" ->        SURVIVAL,
    "survival" -> SURVIVAL,

    "1" ->        CREATIVE,
    "c" ->        CREATIVE,
    "creative" -> CREATIVE,

    "2" ->         ADVENTURE,
    "a" ->         ADVENTURE,
    "adventure" -> ADVENTURE,

    "3" ->         SPECTATOR,
    "sp" ->        SPECTATOR,
    "spectator" -> SPECTATOR
  )

  override def run(sender: CommandSender, arguments: Array[String]): Unit = {
    import com.ayden.main.command.CommandImplicits._
    import org.bukkit.ChatColor._

    val player = sender.toPlayer
    arguments.requireLength_>=(1)
    val gamemodeString = arguments(0)
    val maybeGamemode = gamemodeMap.get(gamemodeString)
    val gamemode = maybeGamemode match {
      case None => throw new Exception(s"Gamemode '$gamemodeString' does not exist.")
      case Some(g) => g
    }
    player.setGameMode(gamemode)
    player.sendMessage(s"${GOLD}Your gamemode has been set to $AQUA$gamemodeString$GOLD.")
  }
}
