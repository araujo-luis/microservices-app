#!/bin/bash
OS_NETWORK="selfservice"
OS_SUBNET="subred"
OS_ROUTER="router"
OS_SECURITY_GROUP="lab1"
OS_FAVLOR="twcam-flavor"
OS_IMAGE="ubuntu-4-docker"
function create-infrastructure(){
    source proyecto8-openrc.sh
    openstack network create $OS_NETWORK
    echo "NETWORK $OS_NETWORK CREATED"
    openstack subnet create --subnet-range 10.2.0.0/24 --network $OS_NETWORK --dns-nameserver 8.8.4.4 $OS_SUBNET
    echo "SUB-NETWORK SUBNET CREATED"

    openstack router create $OS_ROUTER
    echo "ROUTER CREATED"
    openstack router add subnet $OS_ROUTER $OS_SUBNET
    echo "ROUTER CONNECTED TO SUBNET"
    neutron router-gateway-set $OS_ROUTER external-network
    echo "ROUTER CONNECTED TO EXTERNAL-NETWORK"

    openstack security group create $OS_SECURITY_GROUP
    echo "SECURITY GROUP CREATED"
    openstack security group rule create --proto tcp --dst-port 22 $OS_SECURITY_GROUP
    openstack security group rule create --proto tcp --dst-port 8082 $OS_SECURITY_GROUP
    openstack security group rule create --proto tcp --dst-port 8083 $OS_SECURITY_GROUP
    openstack security group rule create --proto tcp --dst-port 2376 $OS_SECURITY_GROUP
    echo "RULES ADDED"
}

function create-vm(){
    source proyecto8-openrc.sh
    docker-machine create \
                   --openstack-flavor-name $OS_FAVLOR \
                   --openstack-image-name $OS_IMAGE \
                   --openstack-net-name $OS_NETWORK \
                   --openstack-floatingip-pool external-network \
                   --openstack-ssh-user ubuntu \
                   --openstack-tenant-name proyecto8 \
                   --openstack-sec-groups $OS_SECURITY_GROUP \
                   --openstack-domain-name Default \
                   --driver openstack \
                   $1
}

function init-docker-swarm(){
    source docker-helper.sh
    docker-in $1
    ip=$(docker-machine ssh $1 ifconfig ens3 | grep -Eo "inet (addr:)?([0-9]*\.){3}[0-9]*" | grep -Eo "([0-9]*\.){3}[0-9]*")
    docker swarm init --advertise-addr $ip
    docker-in localhost
}

function init-services(){
    source docker-helper.sh
    docker-in $1
    docker stack deploy --compose-file docker-cloud.yml webapp
    docker-in localhost
}

function run-all(){
    create-infrastructure
    create-vm $1
    init-docker-swarm $1
    init-services $1
}