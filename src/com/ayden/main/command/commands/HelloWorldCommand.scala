package com.ayden.main.command.commands

import com.ayden.main.command.Command
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.inventory.ItemStack

object HelloWorldCommand extends Command {
  override def name: String = "hello"

  override def requirements(): Unit = ()

  override def run(sender: CommandSender, args: Array[String]): Unit = {
    import com.ayden.main.command.CommandImplicits._
    val player = sender.toPlayer
    args.requireLength_>=(1)
    if (args(0) != "world")
      throw new Exception("command must be run with /hello world")
    player.sendMessage("Hello, World!")
    player.setVelocity(player.getVelocity.clone.setY(5))
    player.getInventory.addItem(new ItemStack(Material.APPLE, 64))
  }
}
