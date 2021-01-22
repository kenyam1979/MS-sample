# Architecture

Frontend -- React [x]  ---> http://localhost:3000

Backend -- Java (Sprint Boot)
* API GW ---> http://localhost:3001
* Order Service ---> http://localhost:8080
* Shipment Service ---> http://localhost:8081
* RabbitMQ ---> http://localhost:5672  (using demo docker image)

# Process

1. Frontend calls an API for order registration
1. Order Service registers a order
2. Order Service pushes a request to Shipment Service in async manner
3. Shipment Service arranges delivery
4. Shipment Service pushes shipment status back to Order Service
5. Order Service updates order status
6. Frontend displays updated status


# Tricks
[x] DDD 
[x] Async messaging over RabbitMQ 
[x] Saga 
[ ] Circuit breaker and catche 


# Progress

2020-12-24
Basic features have implemented. Error handling is still not implemented at all.
2020-12-25
Major error handling has implemented.
