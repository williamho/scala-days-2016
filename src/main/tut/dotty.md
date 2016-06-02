# Dotty

github.com/dotty-linker/dotty

Java: 45ms
Scala 2.11: 872ms
Scala 2.12.0-MS: 680ms
ScalaBlitz: 42ms
Linker: 68ms
ScalaJS: 2440ms

Generics are replaced by Objects

def plus[T](a: T, b: T)(implicit num: Numeric[T]): T
// ...is rewritten to...
def plus(a: Object, b: Object, num: Numeric): Object

@specialized (2010)
@miboxed (2012)
^ Both break modularity, require assumptions

Dotty linker analyzes program and libraries, see what instantiations are needed,
specializes them without needing annotations

Type specialization removes boxing
Term specialization removes virtual dispatch

Auto-specialization:
slower, 1.5x on average (still faster than scalac)
dependencies need to have TASTY (compiled by dotty)
does not help if library does tricks (asInstanceOf, which Vector does) or uses nulls

## Library-specific optimizations

linker allows defining rewrite rules with @rewrites annotation
e.g., `x.length == 0` becomes `x.isEmpty`
conditional rewrites (convert division by constant power of 2 to a shift)
filter followed by another filter becomes one filter (only if pure)
custom warnings/errors (reduceLeft makes no sense on parallel collections)
rewrite `x.info(x)` to `if (x.isLoggable(INFO)) x.log(TRACE, x)`
rewrite rules are defined as tests (scalacheck), to check both sides are equal

guy making correction "isn't that supposed to be ||" no he is wrong

                  | Rewrite rules       | Macros
------------------|---------------------|------------------------
Defined           | Program/libs        | Inside implementation
Applied           | Based on analysis   | Syntax only
Infuences typing  | No                  | Yes
Java              | Yes                 | No
Across libs       | Yes                 | No

Can use scala.meta macros inside rewrite rules

