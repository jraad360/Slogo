API REVIEW
==

jar121, dpk14

### Part 1

#### Flexibility & Encapsulation
I think overall my API is designed to be flexible. Since it is rigidly separated into front-end/back-end internal/external, you can add a feature on either side, and all you have tgo worry about is deciding what information you need from the other side. Most of the methods in the API are pretty much getters (likely immutable), and then the feature uses the information to do its own thing independent of the other side. It is currently set up to be pretty modular, because most of the information flow is from a side's internal part, to its external part and then to the other side. Only the internal part should take in information from the other side. Using this design, it would be very easy to see where particular problems with information are coming from.

The internal backend is in charge of parsing in commands and executing them. Therefore, I would have to handle every possible text error.
* input does not match pattern
* wrong amount of arguments
* missing expected elements
* wrong type of argument

#### Exceptions

For most of these, the errors fall on the input chosen by the user, so the backend should not be responsible for doing anything with these. Therefore, we would throw these exceptions to the frontend, which would decide what to do with this.

####



### Part 2

Unfortunately, I do not have too much code written yet, so I have not had an opportunity to implement design patterns. However, I do see the usefulness of the factory pattern. Any time we are parsing in things and constructing objects according to the read Strings, the factory pattern gives a way of using a separate to not have to use a bunch of if statements within the parser itself.

I am most excited about working on the setting of parameters to funcions and figuring out how we will cycle through nested commands.API_REVIEW_jar121_dpk14.md