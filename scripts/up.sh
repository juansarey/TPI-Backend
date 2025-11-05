#!/usr/bin/env bash
set -e
# build del jar (sin tests) y subir stack
( cd maestros-service && mvn -q clean package -DskipTests )
docker compose up --build
