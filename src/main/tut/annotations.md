# Annotations

## @inline
-XX:InlineSmallCode (default 1000 bytes assembly)
-XX:MaxInlineSize (default 35 bytes bytecode)
-XX:MaxTrivialSize (default 5 bytes bytecode)

JITWatch

scalac -optimise -Yinline-warnings Test.scala // Buggy

## @specialized
Can specify types we want to specialize

Alternatives: non/debox, denisrosset/metal, miniboxing

