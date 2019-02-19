# Architecture
***

## What does the software architecture look like?
We have a three component system consisting of a backend SpringBoot-application a Reactjs frontend and a MySQL database.
The Springboot is a Rest-API, managing and handling the data and logic. The API communicates via a ResponseObject, consisting of two fields frentlyError and frentlyData.
//TODO -- Error Handling --
The database saves ApplicationUsers, representing the users of the website, which can be lenders and borrowers at the same time. Then we have articles standing for the products one can offer to lend and borrow. The program also saves an inquiry from a potential interest, and encoded in it are the three states: "open, declined,
accepted". Embedded in Inquiry is also the Lending Period. After an inquiry is accepted, a Transaction Object is saved, again with three states: "open, closed" and with "conflict", handling the case that an article is bein returned damaged.
Every datatype has its own REST-Controller, with no logic. The request is pushed downwards towards the services handling the data and the service returns either the proper response or an exception.

### Modules/Packages

#### Controllers
We designed our controllers after the needs of the frontend, and use services which implements the logic behind each interaction.
The controllers and their routs are:
  * "/borrow"
    * "/getAll" - Delivers all courent articles which are offered
    * "/inquiry"  - Makes an inquiry to borrow an article
  * "/conflict"
    * "/getAll" - Delivers all conflicts
    * "/resolve"  - Resolves a conflict
  * "/inquiry"
    * "/getAll" - Delivers all inquiries for a specific user
    * "/accept" - Accepts an inquiry
    * "/decline"  - Declines an inquiry
  * "/lend"
    * "/getAll" - Delivers all articles which the specific user lends to others
    * "/creat"  - Creats an new offer and posts the article
  * "/transaction"
    * "/getAll" - Delivers all transactions of which the user is part of
    * "/problem" - Updates a specifif transaction to make a conflict

##### Response
Every controller returns an frentlyResponse to the Frontend. frentlyResponse has two fields, frentlyData and frentlyError, one is always null. \
frentlyData carries the specifc correct response, for example an article. At first we used an interface but later decieded to change it to
the general Object so we can return lists. \
frentlyError is composed of an error-message and a error-type given with an enum.

#### Config
//TODO write documentation for config
#### Models
Models are the data, being saved in our repository and are specified by the Objects in our Java application. There are:
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
For each model, there is a service handling the business logic and saving the output intot the database and/ or returning an dataobject.

## Why did we choose to implement it this way?
***
First we had a discussion about a how to implement the project.
Essentialy there were two line of thought. First we implement this as multiples Self-Contained-Systems. One for borrower and one for the lenders.
The other option was to implement it as a Single-System App.
Since we would have to have a lot of the same data in both SCS, we choose to just implement it as a Single-System App.
