#!/usr/bin/env bash
curl -i -H "Content-Type: application/json" -X POST -d '{
    "username": "admin",
    "password": "password"
}' http://localhost:8080/api/login
read  -n 1 -p "" wait