#!/bin/sh
#echo "Hello World from Master!" > /root/master.txt
/sbin/ifconfig eth0 | sed -n 's/.*inet *addr:\([0-9\.]*\).*/\1/p' > /root/hosts.master
