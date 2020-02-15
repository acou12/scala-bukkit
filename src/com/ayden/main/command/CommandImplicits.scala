package com.ayden.main.command

import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object CommandImplicits {

  implicit class CommandSenderImplicit(sender: CommandSender) {
    def toPlayer: Player = sender match {
      case p: Player => p
      case _ => throw new Exception("Sorry, you must be a player to run this command.")
    }
  }

  implicit class ArrayImplicit[T](a: Array[T]) {
    def requireLength(min: Int, max: Int): Unit =
      if (!(min <= a.length && a.length <= max))
        throw new Exception("Invalid arguments.")

    def requireLength(n: Int): Unit = {
      if (a.length != n) throw new Exception("Invalid arguments.")
    }

    def requireLength_>=(n: Int): Unit = {
      if (a.length < n) throw new Exception("Invalid arguments.")
    }
  }

  implicit class StringImplicit(s: String) {
    def safeToInt: Int = {
      try {
        val i = s.toInt
        i
      } catch {
        case _: NumberFormatException => throw new Exception("Invalid arguments.")
        case _ => throw new Exception("Something went wrong.")
      }
    }

    def safeToFloat: Float = {
      try {
        val i = s.toFloat
        i
      } catch {
        case _: NumberFormatException => throw new Exception("Invalid arguments.")
        case _ => throw new Exception("Something went wrong.")
      }
    }
  }

}
