#!/bin/bash
IP=$(openstack floating ip list -f value -c "Floating IP Address")

for i in $(seq 1 15); do
curl -v -H "Content-Type: application/json" -X POST -d "{\"id\":\"$i\",\"identification\":\"PASS$i\", \"securityDateDate\":\"2019-06-03T23:00:00Z\", \"boardingDate\":\"2019-06-03T23:40:00Z\", \"gate\":\"GATE-$i\", \"airportDestiny\":\"LAX\"}" http://$IP:8083/api/airport-control;
sleep 0.1;
done

for i in $(seq 1 15); do
curl -v -H "Content-Type: application/json" -X POST -d "{\"number\":\"FLIGHT-$i\", \"capacity\":160}" http://$IP:8082/api/aircrafts;
sleep 0.1;
done

