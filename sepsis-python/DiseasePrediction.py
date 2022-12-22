#!C:\Users\hp\AppData\Local\Programs\Python\Python39\python

import cgi
import mysql.connector as mycon
import base64
from DBOperations import *
 
cnt=0
cnt1=0
print("Content-type: text/html")
print()

form=cgi.FieldStorage()
userid=form.getvalue("userid")
print("param val="+userid)
 
#print("userid="+uid+" usershare="+requesterUserid)
try:
    #calculateReviewsScore1(message)
    pred=getScores(userid)
    print("classification")
    print(pred)
       
    print("<html>")
    print("<head>")
    print("<meta http-equiv='refresh' content='0;url=http://localhost:8080/SepsisOutput?param="+str(pred) +"&userid="+str(userid)+"'/>")
    print("</head>")
    print("</html>")
    
    
except Exception as e:
        print(e)
        print("Variable x is not defined"+ NameError)