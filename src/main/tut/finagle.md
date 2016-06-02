# Finagle

Service as a function of Request to Future[Response]
Client stack contains load balancer

Servers handle requests, concurrency limit, request timeouts, metrics/tracing

```
val server = Http.server
  .withAdmissionControl.concurrencyLimit(
    maxConcurrentRequests = 10,
    maxWaiters = 0
  )
```

Clients handle:
retries, circuit breaking, service discovery, failure detection,
timeouts/expirations, metrics/tracing, load balancing, interrupts,
rate limiting, context propagation, connection pooling

jittered retry backoff
failure accrual (e.g., require a specific success rate per node)

500s considered success (valid HTTP response)
finagle-core knows nothing about the protocol
Client handles response classification

