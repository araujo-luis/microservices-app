# microservices-app

Final Proyect - Master's Degree in Web Technology, Cloud Computing and Mobile Applications - Valencia University

## airlines-app

This REST API, allows: flight management and search, ticket reservation / management and, in general, access to all reports and inquiries offered by the persistence layer.


## shops-app

This REST API uses the Spring reactive-stack and allows the management and exploitation of information related to registration and purchases in the boarding area.

## Deployment

The infrastructure is be created in OpenStack:

- Net
- Subnet
- Router that connects the created network with the external network so that the instances have access from the outside.

With `docker-machine` an instance is created that has an interface to the network created in Openstack. This instance acts as a swarm node (it will have both roles: manager node and node that executes containers) and must have a floating IP assigned

For each service, an image is created ans is uploaded to the repository found at `twcammaster.uv.es`. 

For relational and non-relational persistence, containers should be used (in this case, images from the standard dockerhub repository).