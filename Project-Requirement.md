#Problem Overview
Each scooter has a unique id, and its current location (lat, lng). Users should be able to view scooters, start a scooter reservation, end their reservation, and pay for the distance they traveled. Build REST APIs for the following and share the Git repository with us. You can populate your database with any dummy data you want. You can write the code in Python/Django, Ruby/Rails, JS/Express or in any other web framework you prefer, but Python/Django is preferred.
Requirements

Search for an address and find nearby available scooters. (input: lat, lng, radius in meters. Output - list of parking spots within the radius).
Reserve a scooter.
Bonus
Automated tests
Ending reservations
Any kind of mock payments
Proposals on how to improve the APIs
### Sample API requests/responses:
1. 
```
GET /api/v1/scooters/available?lat=37.788989&lng=-122.404810&radius=20.0 
```

Response:
```
[
    {
        "id": 10,
        "lat": 37.788548,
        "lng": -122.411548
    },
    {
        "id": 8,
        "lat": 37.783223,
        "lng": -122.398630
    }
]
```


2.) POST /api/v1/scooters/reserve?id=10

Once you reserve scooter 10, if you call the scooters available api (first example endpoint), it should not return scooter 10 as it is reserved.

Additionally, please provide a simple report at the end that covers the below questions. Please don't focus too much on the report, we're not focused on writing quality, this is just for us to have more context on what you accomplished and were thinking about during the challenge.
Can you briefly summarize your progress?
What things did you have to do for this challenge that were new to you?
Is there anything you got blocked on during the challenge?
If this was a real situation and you had been given more time, say an additional 1 week, what are the top things in your mind that you would want to improve about this implementation? What would you think are the necessary improvements to make it something we could confidently deploy in production?
