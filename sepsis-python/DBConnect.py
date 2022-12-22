import mysql.connector as mycon

def connect() : 
    con=mycon.connect(host='localhost',user='root',password='raju@1307',database='sepsisdb')
    return con