## Installation  
Run the following command to start the application using Docker Compose:    
```  
docker-compose up
```
Welcome to the application:  
http://localhost:8095

## Application provides the ability through the UI:      

### Get the list of all counters  
You can get it using the following URI:  
http://localhost:8095/counter/counters  
Method: GET    

### Create a new counter  
To create and add counter to DB, use the following URI:  
http://localhost:8095/counter/counters/add-new-counter  
Method: GET  

### Delete a counter
To delete a counter by ID, use the following URI:  
http://localhost:8095/counter/counters/delete-counter  
Method: GET  

## Also, the application contains two REST-point:    

### Get a counter    
You can get it using the following URI:    
http://localhost:8095/counter/get-counter?counterId=1057  
Method: GET    
**Returns:**  
Value: 300  

### Increment counter  
To increment counter by value, use the following URI:    
http://localhost:8095/counter/increment-counter  

**Request JSON:**  
```json
{
    "counterId":1057,
    "incrementCount":20
}
```
Method: POST

**Returns:**
New value of counter, 320


## Also, Swagger is available in the project and you can access it via the link:
http://localhost:8095/swagger-ui/index.html
