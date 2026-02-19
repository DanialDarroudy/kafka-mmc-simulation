#!/bin/sh

if [ -z "$1" ]; then
  echo "❌ Error: Please provide ARRIVAL_RATE (requests per second)"
  echo "Usage: ./run.sh <arrival_rate>"
  exit 1
fi

ARRIVAL_RATE=$1

echo "✅ Starting k6 with ARRIVAL_RATE=${ARRIVAL_RATE} req/sec"

k6 run \
  --vus 1 \
  --duration 24h \
  -e ARRIVAL_RATE=${ARRIVAL_RATE} \
  /scripts/poisson.js