#!/bin/bash
function docker-in(){
    if [ -z $1 ]; then
        echo "Specify the host as first argument"
        elif [ $1 = "localhost" ]; then
            eval $(docker-machine env -u --shell sh/bash)
        else
        eval $(docker-machine env $1 --shell sh/bash)
    fi
}