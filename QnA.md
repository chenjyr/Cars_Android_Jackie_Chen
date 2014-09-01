What do you think are the greatest risk areas in completing this project?

> Determining the level of detail to display, and designing an intuitive UI that makes it easy to understand the data.
Also, determining what to retrieve on-demand through UI interaction and what to retrieve right away.


What changes would you make to the data API if you were able to influence its design?

> If we care about the standard price for every car in order to show the user if it's a bargain or not, then that should probably go directly into the available_cars call to reduce network usage. 
In fact, what would make sense is to have a combined API call that can get all 3 API call data into one call, but with parameters so the client can specify exactly what it wants, while still having the separate existing APIs. This way, if the client wishes to display all the information to the user on a single view, only one call is needed (each car result could have all the info in API #1 and #2, and also have a 'best years' and 'worst years' lists from API #3). If the API was designed to allow apps to easily display good and bad deals of used cars, then this combined call makes sense.
To consider the scale of the app, we should think about what if the number of cars can become very high, then we probably want to have some sort of pagination to the API, so the client can specify the number of cars to retrieve, and the page #, and perhaps even a parameter that sorts the cars in a specific way (by make, price, etc.).


List a two or three features that you are unlikely to have time to implement that would add significant value to this app.

> 1. Different types of views that the user can choose from.
2. A "past prices" for cars to be used to determine good/bad deals, based from the API response.
3. Social aspect (commenting, reviews, sharing, etc.)

--

How long did this assignment take (in hours)?

>


What was the hardest part?

>


If you could go back and give yourself advice at the beginning of the project, what would it be?

>


Did you learn anything new?

>


Do you feel that this assignment allowed you to showcase your abilities effectively?

>


Are there any Android-related skills that you weren't able to demonstrate in this excercise? If so, what are they?

>