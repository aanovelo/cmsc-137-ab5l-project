#!/bin/bash
echo "Executing initialization script for slave..."
MASTER_IP=192.168.10.3
SLAVE_NAME=rhea-hadoop-slave-2
MASTER_NAME=rhea-hadoop-master

SLAVE_IP=`/sbin/ifconfig eth0 | sed -n 's/.*inet *addr:\([0-9\.]*\).*/\1/p'`

echo "$SLAVE_NAME" > /etc/hostname
echo "$SLAVE_IP  $SLAVE_NAME" > /etc/hosts
echo "$MASTER_IP  $MASTER_NAME" >> /etc/hosts

ssh 192.168.10.3 "echo '$SLAVE_IP  rhea-hadoop-slave-2' >> /etc/hosts" 
ssh 192.168.10.3 "echo 'rhea-hadoop-slave-2' >> /home/hduser/hadoop/etc/hadoop/slaves" 

su - hduser
sed -i 's/MASTER_HOSTNAME/rhea-hadoop-master/g' /home/hduser/hadoop/etc/hadoop/yarn-site.xml
rm -fr /home/hduser/mydata/hdfs/*
