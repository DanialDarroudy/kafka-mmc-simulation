# Kafka M/M/C Queue Simulation

This project simulates an **M/M/C queuing system** using Apache Kafka as the central FIFO queue.  
It models a distributed service system where incoming requests are generated with a Poisson arrival process and processed by multiple parallel servers implemented as threads inside a consumer service.

The system is fully containerized using Docker Compose and includes monitoring and visualization tools.

---

## Architecture Overview

The system components are:

- **Producer (Spring Boot)**  
  Receives HTTP requests and publishes messages to Kafka.

- **Kafka (Single Partition Topic)**  
  Acts as the central FIFO queue.

- **Consumer (Spring Boot)**  
  - Reads messages from Kafka  
  - Processes them using multiple worker threads (servers)  
  - Stores processing results in PostgreSQL  
  - Exposes metrics to Prometheus  

- **PostgreSQL**  
  Stores processed message statistics for analytical validation.

- **k6**  
  Generates Poisson-distributed traffic with configurable arrival rate (λ).

- **Prometheus**  
  Collects metrics from Producer and Consumer.

- **Grafana**  
  Visualizes performance metrics and system behavior.

---

## Technologies Used

- Java
- Spring Boot
- Apache Kafka
- PostgreSQL
- k6 (Poisson load generator)
- Prometheus
- Grafana
- Docker Compose

---

## Running the System

Make sure Docker and Docker Compose are installed.

From the project root:
`docker-compose up --build`

This will start:

- Zookeeper
- Kafka
- Producer
- Consumer
- PostgreSQL
- pgAdmin
- Prometheus
- Grafana
- k6 (idle until triggered)

---

## Running Load Test (Poisson Arrival Process)

Enter the k6 container:
`docker exec -it k6 sh`

Run the test with desired arrival rate (λ):
`./run.sh 5`

This means:

λ = 5 requests per second (Poisson distributed)

If no parameter is provided, the script will return an error.

The test runs indefinitely until stopped manually.

---

## Changing System Parameters

### Changing Number of Servers (C)

In `docker-compose.yml`, modify:
`simulation.server-count=3`

Important:

If `simulation.server-count = 3`, then:

- 3 worker threads are created
- The Kafka listener thread also participates in processing

So effectively **4 threads are active**.

After changing C:
`docker-compose down`
`docker-compose up --build`

---

### Changing Service Rate (μ)

Service rate depends on mean service time:
`simulation.service-mean-ms=200`


Mean service time = 200 ms  
μ ≈ 1 / 0.2 = 5 requests per second per server

Lower value → faster service  
Higher value → slower service  

Rebuild after change.

---

### Changing Arrival Rate (λ)

Arrival rate is controlled dynamically when running:
`./run.sh <lambda>`

## Monitoring

### Prometheus
http://localhost:9090

### Grafana
http://localhost:3000  
username: admin  
password: admin  

### PostgreSQL
host: localhost  
port: 5432  
database: mmc_simulation_db  
username: postgres  
password: postgres  

### pgAdmin
http://localhost:5050  
email: admin@admin.com  
password: admin  

---

## Key Metrics

The system exposes:

- Throughput
- Average Waiting Time (Wq)
- Average Service Time (S)
- Average Response Time (W)
- Server Utilization (ρ)
- Queue Length (Kafka consumer lag)

Metrics are scraped by Prometheus and visualized in Grafana.

---

## Experimental Procedure

1. Start system with docker-compose
2. Run load with a specific λ
3. Wait 2–3 minutes for steady state
4. Observe metrics in Grafana
5. Increase λ and observe saturation behavior
6. Modify C or service time and compare results

---
