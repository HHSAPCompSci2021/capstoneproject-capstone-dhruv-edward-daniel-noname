from os import listdir
from os.path import isfile, join
mypath = r"F:\dispose\capstoneproject-capstone-dhruv-edward-daniel-noname\CapstoneProject\images\bg"
onlyfiles = [f for f in listdir(mypath) if isfile(join(mypath, f))]
for i in onlyfiles:
    print(i)
