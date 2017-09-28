@echo off
echo Job starting 
pscp -i C:\Users\hahmim\Downloads\myEC2Puty.ppk C:\Users\hahmim\Documents\APS\IdentityData\Person.xml  ec2-user@ec2-54-174-19-252.compute-1.amazonaws.com:
echo Job finished
