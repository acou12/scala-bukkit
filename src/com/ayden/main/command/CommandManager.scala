package com.ayden.main.command

import java.util.logging.Level

import com.ayden.main.MyMainClass
import com.ayden.main.chat.SmiteCommand
import com.ayden.main.command.commands.{HelloWorldCommand, KitCommand}
import org.bukkit.Bukkit
import org.bukkit.command.{CommandExecutor, CommandSender}

object CommandManager extends CommandExecutor {

  val commands: List[Command] = List(
    HelloWorldCommand,
    KitCommand,
    SmiteCommand
  )

  val commandMap: Map[String, Command] = Map from (commands map { c => (c.name, c) })

  def registerCommands(): Unit = {
    commandMap foreach { case (name, _) =>
      MyMainClass.instance.getCommand(name).setExecutor(this)
    }
  }

  override def onCommand(commandSender: CommandSender, command: org.bukkit.command.Command, s: String, strings: Array[String]): Boolean = {
    import org.bukkit.ChatColor._
    val name = command.getName
    if (!commandMap.contains(name)) {
      commandSender.sendMessage(s"$RED[ERROR]$GRAY Command not yet implemented.")
      Bukkit.getLogger.severe(s"$name is not defined in com.ayden.main.command.CommandManager.")
    }
    val theCommand = commandMap(name)
    try {
      theCommand.run(commandSender, strings)
    } catch {
      case e: CommandException =>
        commandSender.sendMessage(s"$RED[ERROR]$GRAY ${e.getMessage}")
        if (e.showUsage) {
          commandSender.sendMessage(s"$RED[ERROR]$GRAY Usage: ${command.getUsage}")
        }
      case unchecked: Exception =>
        Bukkit.getLogger.log(Level.SEVERE, unchecked.getMessage, unchecked)
    }
    true
  }
}
