package backend.model

sealed trait Duration

object Long extends Duration
object Medium extends Duration
object Short extends Duration

