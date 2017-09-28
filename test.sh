#!/bin/bash
@echo off
echo Job starting 
result="${PWD}"
scp -i ~/FerkatPemKey.pem $result/data/CR3247_Identity_Individual_CreatedFromExcelUsingVBA_Jansen.xml ec2-user@ec2-54-226-88-62.compute-1.amazonaws.com:
echo Job finished