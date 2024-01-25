## QuadControl

QuadControl is a Java-based embedded application for compatible communication with a dedicated API.  	    
A large part of this application is inner loop thrust control of the rotors based on a sensor data.      
App was created especially for Raspberry Pi 4 with Raspberry Pi OS, because dedicated libraries for RPi 4 were used.		    
Compatible API which this embedded app is communicating with is shared [here](https://github.com/sebastianbrzustowicz/Robot-tasker-API).     
You need to run the code on the Raspberry Pi 4 for the programme to be sure that the program works correctly.    

### Disclaimer
Complete version which guarantee best performance is not available publicly.		  
Only alpha version of app with license restrictions is available.		  
Please contact me if you are interested in cooperation.		  
I am willing to help. 	

## Transferred data

The data retrieved from the API have to be the same in client and vehicle.      
Handshake should be established between server and client according to data order.    
The software provides cyclic data exchange with frequency specified in main file.    
The data is sent and received in raw string format and its values stands for variables below.    
Frame sent to API with sensors data:
```
VEHICLE                                 // <- fixed prefix for vehicle message
0                                       // <- actual roll from sensor
0                                       // <- actual pitch from sensor
0                                       // <- actual yaw from sensor
0                                       // <- actual altitude from sensor
0                                       // <- actual isClamp
END                                     // <- fixed ending statement of message
```

Frame received from API with desired values data:
```
CLIENT                                  // <- fixed prefix for client message
4436ed9a-5228-46c0-b825-6d0a3cd90437    // <- vehicleId
1                                       // <- mode
0                                       // <- vtol
0                                       // <- x
0                                       // <- y
0                                       // <- alt
0                                       // <- yaw
false                                   // <- camTrig
false                                   // <- camTog
0                                       // <- camPitch
false                                   // <- clamp
END                                     // <- fixed ending statement of message
```

## Tests

Some simple JUnit tests have been implemented:
```java
updateControlData()
```

## License

QuadControl is released under the CC BY-NC-ND 4.0 license.

## Author

Sebastian Brzustowicz &lt;Se.Brzustowicz@gmail.com&gt;
