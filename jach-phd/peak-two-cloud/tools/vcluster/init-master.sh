#!/bin/sh
echo "Executing initialization script for master..."
MASTER_NAME=biostat-master

#echo "Hello World from Master!"
#echo "Hello World from Master!" > /root/master.txt
#echo -n "Master IP Address: "
MASTER_IP=`/sbin/ifconfig eth0 | sed -n 's/.*inet *addr:\([0-9\.]*\).*/\1/p'`

echo "127.0.0.1  localhost" > /mirror/hosts.mpi
echo "$MASTER_IP  $MASTER_NAME" >> /mirror/hosts.mpi
chown mpiuser.mpiuser /mirror/hosts.mpi
chmod 777 /mirror/hosts.mpi
rm /etc/hosts
ln -s /mirror/hosts.mpi /etc/hosts

echo "$MASTER_NAME" > /mirror/nodes.txt
chown mpiuser.mpiuser /mirror/nodes.txt
chmod 777 /mirror/nodes.txt

echo "UserKnownHostsFile /dev/null" >> /etc/ssh/ssh_config
echo "StrictHostKeyChecking no" >> /etc/ssh/ssh_config

lamboot

