# Architecture
***

## What does the software architecture look like?
We have a three component system consisting of a backend SpringBoot-application, a Reactjs frontend and a MySQL database.
The Springboot is a Rest-API, managing and handling the data including it's logical operations. The API communicates via ResponseObjects, consisting of two fields: frentlyError and frentlyData.
//TODO -- Error Handling --
The database saves ApplicationUsers, representing users of the website, which can then be lenders and/ or borrowers. Furthermore we have articles standing for the products one can offer to lend and/ or borrow. The program also saves an inquiry from a potential interest. In addition to that the program is able to set following three states for an inquiry: "open, declined, accepted". The Lending Period is embedded in the inquiry class. After an inquiry is accepted, a Transaction Object is saved, again with one of three states: "open, closed" or with "conflict", handling the case that an article is being returned damaged. 
Every datatype has its own REST-Controller, with no logical operations. The request is pushed downwards towards the services handling the data and the service returns either the proper response or an exception.

### Modules/Packages

#### Controllers
We designed our controllers after the needs of the frontend, and used services which implement the logic behind each interaction.
The controllers and their routes are as described:
  * "/borrow"
    * "/getAll" - Delivers all current articles which are being offered
    * "/inquiry"  - Makes an inquiry in order to let a potential user borrow an article
  * "/conflict"
    * "/getAll" - Delivers all conflicts
    * "/resolve"  - Resolves a conflict
  * "/inquiry"
    * "/getAll" - Delivers all inquiries for a specific user
    * "/accept" - Accepts an inquiry
    * "/decline"  - Declines an inquiry
  * "/lend"
    * "/getAll" - Delivers all articles of a specifc owner, which can be lend
    * "/creat"  - Creates new offer and posts the article
  * "/transaction"
    * "/getAll" - Delivers all transactions of which the user is part of
    * "/problem" - Updates a specific transaction to make a conflict

##### Response
Every controller returns a frentlyResponse to the Frontend. frentlyResponse has two fields, frentlyData and frentlyError. Whereas only one is filled either with data or error. \
frentlyData carries the specifc correct response, for example an article. At first we used an interface but later decieded to change it to
the general Object. This way we can even return lists. \
frentlyError is composed of an error-message and an error-type, given with an enum.

#### Config
//TODO write documentation for config
#### Models
Models are the data, being saved in our repository and they are specified by the Objects in our Java application. They are as described:
  * ApplicationUser
  * Article
  * Inquiry
  * Transaction

#### Repositories
Our backend uses CRUD-Repositories to access and update our dataobjects in the database. We havent made many changes to the repository interfaces, mostly to return lists with a specific type.
#### Security
//TODO write documentation for the security package
JWTAuthentication for the frontend.
#### Services
For almost each model, there is a service handling the business logic and saving the output into the database and/ or returning an dataobject. Each service is providing the opportunity to create, retrieve, update and to delete data from the database. Several services offer even more functionality. Overall we decided to use only the repository based on the usage of the given service within the service. We prefered to use constructor based dependency injection in our services, because it is being suggested by the Spring Team. All methods of the services are tested thoroughly, except for very simple oneliners, which don't contain major logical operations.

## Why did we choose to implement it this way?
***
First we had a discussion on how to implement the project.
Essentialy there were two lines of thought. First we implement this as multiples Self-Contained-Systems. One for borrower and one for the lenders.
The other option was to implement it as a Single-System App.
Since we would have to have a lot of the same data in both SCS, we choose to just implement it as a Single-System App.
