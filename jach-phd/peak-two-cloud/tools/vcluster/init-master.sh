#!/bin/sh
#echo "Hello World from Master!"
#echo "Hello World from Master!" > /root/master.txt
#echo -n "Master IP Address: "
MASTER_IP=`/sbin/ifconfig eth0 | sed -n 's/.*inet *addr:\([0-9\.]*\).*/\1/p'`

echo "$MASTER_IP  jach-master" > /etc/hosts
