# Zippers

```tut
case class StreamZipper[+A](lefts: Stream[A], focus: A, rights: Stream[A])
```

* Left side is a reverse stream
* `flatMap` less useful and costly (usually not provided, use `coflatMap`)
* `fold` called `Catamorphism` in math
* Good `fold`: pass all possible constructors to fold, should get starting instance

```tut
val someOption = Some(1)
someOption.fold(None: Option[Int])(Option.apply)
```

Unfolds: anamorphisms
Can create a context from value and generator function

```
def unfold[A, B](seed: A)(f: A => Option[(B, A)]): Stream[B] =
  f(seed).fold[Stream[B]](Stream.empty) {
    case (b,a) => Stream.cons(b, unfold(a)(f))
  }

case class StreamZipper[+A](lefts: Stream[A], focus: A, rights: Stream[A]) {
  def previous = lefts.headOption
  def next = rights.headOption

  // All possible cursors of the current instance
  def positions: StreamZipper[StreamZipper[A]] = {
    val left = unfold(this)(_.previous.map(x => (x, x)))
    val right = unfold(this)(_.next.map(x => (x, x)))

    streamZipper(left, this, right)
  }
}
```

# Comonad

* extract returns the focus
* coflatMap returns positions.map(f)
* coflatMap forgets original value (like `map` and `flatMap`), introduce a `coflatMapWith`
  * flatMap and then return a tuple containing the original element and the mapped element

Comonad works on element *with its environment* and allows you to annotate it

# Writer monad

* Accumulate steps of calculation
* Works on any type with semigroup instance (append)

# DList (difference list)

* Constant time for append and concatenation
* O(n) for traversal
* Naive implementation is composing functions, stack overflows
* Use trampoline instead

```
class DList[A](f: List[A] => Trampoline[List[A]])
```

