from DBConnect import *
import base64

from KNNAlgo import knnalgo

def updateReading(result="NA",id="NA") : 
    conn = connect()    
    cursor = conn.cursor()
    args = [result,id]
    args1=cursor.callproc('updatePrediction', args)
    print("Return value:", args1)
    #for result in cursor.stored_results():
     #       print(result.fetchall())
    cnt=cursor.rowcount 
    conn.commit()
     

def getScores(userid='na') : 
    lst=[]
    conn = connect()    
    cursor = conn.cursor()
    #cursor.execute("select* from userprofile where userid='"+uid+"'")
    print("select result from sepsis_readings where userid='"+userid+"'")
    sql_select_query = "select result from sepsis_readings where userid='"+userid+"'"
    cursor.execute(sql_select_query)
    record = cursor.fetchall()
    #record = cursor.fetchall()
    final_result = [list(i) for i in record]
    print(final_result)
    lst=[]
    for row in final_result:   
        lst.append(row[0])
         
    print(userid)
    print(lst) 
    ypred=knnalgo(lst)
    print("pred="+str(ypred[0]))
    pred=str(ypred[0])
    updateReading(pred,userid)
    return pred
def getReadingIDs(userid='na') : 
    lst=[]
    conn = connect()    
    cursor = conn.cursor()
    #cursor.execute("select* from userprofile where userid='"+uid+"'")
    print("select cid from readingsforclassification where userid='"+userid+"' order by cid asc")
    sql_select_query = "select cid from readingsforclassification where userid='"+userid+"' order by cid asc"
    cursor.execute(sql_select_query)
    record = cursor.fetchall()
     
    """ 
    print(cursor.rowcount)
    final_result = [list(i) for i in record]
    print(final_result)
    #conn.commit()
    for x in record:
        lst.append(str(x[0]))
        print(str(x[0])+'\n')
    return final_result
    """
 
def convertToBase64(message='NA') :
    message_bytes = message.encode('ascii')
    base64_bytes = base64.b64encode(message_bytes)
    base64_message = base64_bytes.decode('ascii')
    print(base64_message)
    return base64_message

def convertFromBase64(base64_message='NA') :
    base64_bytes = base64_message.encode('ascii')
    message_bytes = base64.b64decode(base64_bytes)
    message = message_bytes.decode('ascii')
    print(message)
    return message
    
#getScores('sneha') 