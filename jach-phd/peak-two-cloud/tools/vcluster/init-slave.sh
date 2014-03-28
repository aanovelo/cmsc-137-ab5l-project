#!/bin/bash
echo "Executing initialization script for slave..."
MASTER_IP=10.0.3.231
SLAVE_NAME=jach-slave-1

echo "Unmounting /mirror"
umount /mirror

echo "Mounting /mirror"
mount $MASTER_IP:/mirror /mirror

SLAVE_IP=`/sbin/ifconfig eth0 | sed -n 's/.*inet *addr:\([0-9\.]*\).*/\1/p'`

echo $SLAVE_IP

echo "$SLAVE_IP  $SLAVE_NAME" >> /mirror/hosts.mpi

rm /etc/hosts
ln -s /mirror/hosts.mpi /etc/hosts



