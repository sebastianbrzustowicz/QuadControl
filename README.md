## QuadControl

QuadControl is a Java-based embedded application for compatible communication with a dedicated API.  	    
A large part of this application is inner loop thrust control of the rotors based on a sensor data.      
App was created especially for Raspberry Pi 4 with Raspberry Pi OS, because dedicated libraries for RPi 4 were used.		    
Compatible API which this embedded app is communicating with is shared [here](https://github.com/sebastianbrzustowicz/Robot-tasker-API).   

### Disclaimer
Complete version which guarantee best performance is not available publicly.		  
Only alpha version of app with license restrictions is available.		  
Please contact me if you are interested in cooperation.		  
I am willing to help. 	

## Transferred data

The data retrieved from the API is as follows:

```json
{
  "vehicleId": "e218e18c-9e1c-11ee-8c90-0242ac120002",
  "mode": 1,
  "vtol": 0,
  "x": 1,
  "y": 0,
  "alt": 1,
  "yaw": 0,
  "camTrig": 0,
  "camTog": 0,
  "camPitch": 0,
  "clamp": 0
}
```

## Tests

Some simple JUnit tests have been implemented:
```java
to do
```

## License

QuadControl is released under the CC BY-NC-ND 4.0 license.

## Author

Sebastian Brzustowicz &lt;Se.Brzustowicz@gmail.com&gt;
