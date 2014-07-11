#!/bin/bash
echo "Executing initialization script for slave..."
MASTER_IP=192.168.10.2
SLAVE_NAME=grav-slave-10

echo "Unmounting /mirror"
umount /mirror

echo "Mounting /mirror"
mount $MASTER_IP:/mirror /mirror

SLAVE_IP=`/sbin/ifconfig eth0 | sed -n 's/.*inet *addr:\([0-9\.]*\).*/\1/p'`

echo $SLAVE_IP

echo "$SLAVE_IP  $SLAVE_NAME" >> /mirror/hosts.mpi
echo "$SLAVE_NAME" >> /mirror/nodes.txt

rm /etc/hosts
ln -s /mirror/hosts.mpi /etc/hosts

lamboot


