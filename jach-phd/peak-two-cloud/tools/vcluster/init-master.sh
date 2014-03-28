#!/bin/sh
echo "Executing initialization script for master..."
#echo "Hello World from Master!"
#echo "Hello World from Master!" > /root/master.txt
#echo -n "Master IP Address: "
MASTER_IP=`/sbin/ifconfig eth0 | sed -n 's/.*inet *addr:\([0-9\.]*\).*/\1/p'`

echo "$MASTER_IP  jach-master" > /mirror/hosts.mpi
chown mpiuser.mpiuser /mirror/hosts.mpi
chmod 777 /mirror/hosts.mpi
rm /etc/hosts
ln -s /mirror/hosts.mpi /etc/hosts
