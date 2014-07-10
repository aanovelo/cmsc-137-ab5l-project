#!/bin/sh
echo "Executing initialization script for master..."
MASTER_NAME=sample-hadoop-master
MASTER_IP=`/sbin/ifconfig eth0 | sed -n 's/.*inet *addr:\([0-9\.]*\).*/\1/p'`

echo "$MASTER_NAME" > /etc/hostname
echo "127.0.0.1  localhost" > /etc/hosts
echo "$MASTER_IP  $MASTER_NAME" >> /etc/hosts
echo "$MASTER_NAME" > /home/hduser/hadoop/etc/hadoop/slaves

su - hduser
sed -i 's/MASTER_HOSTNAME/sample-hadoop-master/g' /home/hduser/hadoop/etc/hadoop/yarn-site.xml

#clear data node directory
rm -fr /home/hduser/mydata/hdfs/*

