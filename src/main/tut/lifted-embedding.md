# Polymorphic Record Types in a Lifted Embedding

http://github.com/szeiger/slick/tree/toy-slick-scaladays2016

Literals converted to `TypedType`

Rep[T]

ScalaQuery had `~` for creating Rep[T] representing tuples, made it hard to
return arbitrary types (nesting tuples, returning objects, hlists, etc)

## Polymorphic Record Types

(Int, String, String)

class Pair[T1, T2](val v1: T1, val v2: V2)

Int :: String :: HNil

CanBuildFrom

Use Shape[-Mixed, Unpacked, Packed] instead of Rep[T]
* Mixed: Used for lookup
* Unpacked: plain scala
* Packed: Rep[T]

