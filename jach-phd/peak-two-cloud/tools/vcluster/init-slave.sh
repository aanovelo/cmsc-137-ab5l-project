#!/bin/bash
echo "Executing initialization script for slave..."


MASTER_IP=10.0.3.231

umount /mirror
mount $MASTER_IP:/mirror /mirror

SLAVE_IP=`/sbin/ifconfig eth0 | sed -n 's/.*inet *addr:\([0-9\.]*\).*/\1/p'`

echo "$SLAVE_IP  jach-slave-1" >> /mirror/hosts.mpi





