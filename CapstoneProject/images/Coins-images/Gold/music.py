from os import listdir
import os
from os.path import isfile, join

mypath = r"F:\dispose\capstoneproject-capstone-dhruv-edward-daniel-noname\CapstoneProject\images\Coins-images\Gold"

onlyfiles = [f for f in listdir(mypath) if isfile(join(mypath, f))]

for i in onlyfiles:
    if("@2x" in i):
        os.remove(i);
    else:
        print(i)
