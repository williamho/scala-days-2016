# scala.meta

```tut
import scala.meta._

val res2 = "foo + bar".parse[Term]
val res3 = res2.get
val res4 = res3.structure
val res5 = "foo.+(bar)".parse[Term].get
val res6 = res5.structure

val res7 = """
     def foo = bar
            /* hello world */
   foo
"""

res7.parse[Term]

val res9 = ("{" + res7 + "}").parse[Term]
res9.get.structure
res9.get.tokens
res9.get.tokens.structure

// sbt support
val buildsbt = new java.io.File("build.sbt")
buildsbt.parse[Source] // fails, sbt file

import scala.meta.dialects.Sbt0137
val res18 = Sbt0137(buildsbt).parse[Source]
res18.get.structure

val foo = q"foo"
q"$foo + bar"

val q"$x + $y" = q"$foo + bar"
```

Current setup: running programs at compile time, and then inlining

New macros: `inline def ... = meta {  }`

```
import scala.meta._

@main object Test {
  println("hello world")
}
```

## Real world usages

* codacy patterns
* scalafmt

