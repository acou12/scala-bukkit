package com.ayden.main.command

import org.bukkit.Material
import org.bukkit.Material._
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

import scala.collection.immutable.List

object KitCommand extends Command {

  private val kitMap: Map[String, Kit] = Map(
    "noob" -> Kit(items = List((Material.WOOD_SWORD, 10))),
    "epic" -> Kit(
      helmet = DIAMOND_ORE,
      chestplate = DIAMOND_CHESTPLATE,
      leggings = DIAMOND_LEGGINGS,
      boots = DIAMOND_BOOTS,
      items = List(
        (DIAMOND_AXE, 1),
        (DIAMOND_HOE, 1),
        (DIAMOND_PICKAXE, 1),
        (DIAMOND_SWORD, 1),
        (GOLDEN_APPLE, 64)
      )
    )
  )

  override def name: String = "kit"

  override def run(commandSender: CommandSender, strings: Array[String]): Unit = {
    val kitName = strings(0)
    if (!kitMap.contains(kitName)) {
      throw new Exception("Invalid kit name. Try /kit list")
    }
    val kit = kitMap(kitName)
    val player: Player = commandSender match {
      case p: Player => p
      case _ => throw new Exception()
    }
    player.getInventory.setArmorContents(
      Array(
        new ItemStack(kit.helmet),
        new ItemStack(kit.chestplate),
        new ItemStack(kit.leggings),
        new ItemStack(kit.boots)
      ).reverse
    )
    player.getInventory.addItem(kit.items map { case (m, n) => new ItemStack(m, n) }: _*)
  }
}

case class Kit(
                helmet: Material = Material.AIR,
                chestplate: Material = Material.AIR,
                leggings: Material = Material.AIR,
                boots: Material = Material.AIR,
                items: List[(Material, Int)]) {
}