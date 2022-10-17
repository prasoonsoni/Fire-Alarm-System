import serial
from firebase import firebase
serial_read = serial.Serial('COM6',9600)
firebase = firebase.FirebaseApplication('https://fire-alarm-c4c97-default-rtdb.firebaseio.com/', None)
while(True):
    arduino_output = serial_read.readline()
    decoded = arduino_output.decode().rstrip()
    values = decoded.split(" ")
    print(values)
    fire_status = True if values[0]=="True" else False
    gas_value = values[1]
    temperature = values[2]
    firebase.put('/status','fire_status',fire_status)
    firebase.put('/status','temperature',temperature)
    firebase.put('/status','gas_value',gas_value)
    print('Status Updated')