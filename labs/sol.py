# number = int(input())
# if(number % 2 == 1):
#     print(number, " is odd.")
# else:
#     print(number, " is even.")
# 

# import random
# randNumber = random.randint(0,10)
# while(True):
#     number = int ( input("Please enter a number between 0 and 10: ") )
#     if(number == randNumber):
#         print("Yaayy!!! You got it right!")
#         break
#     else:
#         print("Opps!!! try again!")



# listOfNumbers = [1, 4, 9, 16, 25]
# for number in listOfNumbers:
#     if(number % 2 == 0):
#         print(number,end=",")
# 
# 
 # age = 5
 # total = 0
 # while age > 0:
 #     total += age    
 #     age-=1
 #     
 # print(total,"years")
 # print(total*12, "months")
 # print(total*365, "days")
 # print(total*365*24, "hours")
# 
# 
# exp = [5,5000*500000000,'j',1j,(1,),[1],None]
# for i in exp:
#     print(type(i))


n = 50
curr = 1
prev = 0
print(1)
x = 2
while(x <= n):
    tmp = prev
    prev = curr
    curr = tmp + curr
    print(curr)
    x+=1
    

