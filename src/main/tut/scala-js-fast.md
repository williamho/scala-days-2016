Don't use Long

Scala collections `map` faster than native JS map

* see closure inlining. CanBuildFrom is a lot of indirections, that are inlined
classes that traits have the methods on all classes even though they're all the same
* dynamic dispatch causes slowdown
* multi-inline -- if method being called is the same across all options, just pick one and inline
* doesn't work if override

Try hard to inline methods that accept lambdas (but only if it's called once)

Pattern matches are not easy to optimize

