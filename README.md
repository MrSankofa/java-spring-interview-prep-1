## Environment:
- Java version: 1.8
- Maven version: 3.*
- Spring Boot version: 2.2.1.RELEASE

## Data:
Sample example of JSON data object:
```json
[
  {
    "name": "Trial Product",
    "price": "909",
    "discount_percentage": 10,
    "image": "http://placehold.it/940x300/999/CCC",
    "ratings": [
      {
        "rating": 3,
        "rated_by": "User123"
      },
      {
        "rating": 4,
        "rated_by": "User1233"
      }
    ]
  },
  {
    "name": "Trial Product 2",
    "detail": "Lorem ipsum dolor sit amet",
    "price": "850",
    "discount_percentage": 5,
    "image": "http://placehold.it/300x300/999/CCC",
    "ratings": [
      {
        "rating": 5,
        "rated_by": "User123"
      },
      {
        "rating": 4,
        "rated_by": "User1233"
      }
    ]
  }
]
```

## Requirements:
You have two APIs available to you one hackerrank which returns Products and CRUDCRUD to practice POST, PUT, UPDATE and DELETE. Your tasks are below:


The task is to implement a set of REST service that exposes the endpoints, which allows for filtering and sorting the collection of product records in the following way:

1. Enable this project to make http requests so that you can simulate receiving database data in the repository class
2. Create a red Test for the Repository
   3. it should mock the API Response
   5. should return a JSONArray
   6. assert the response has the appropriate length, and each object has the correct names
3. Create a repository
   4. should get all products from the hacker rank API
   5. should create a product on CRUD CRUD
      6. should use a separate repository and use the api key from the application properties
   6. should get a product from CRUD CRUD
4. Make the Tests pass
5. Create a red test for the service
   6. should use mockito extendwith
   7. should use a set up to create the mock JSON array and JSON objects
   8. should test getting all projects from the service (ensure the productRepository is called only once)
   9. should test getting sortedProducts (ensure the productRepository is called only once)
   10. should test sorted Products getting a null for the price
   11. should test sorted products for price not being a number
   12. should test filtered products within a range
6. Create a service
   7. should get all products
   8. should get sorted products
   9. should convert jsonObject to a product
   10. should get a product
   11. should delete a product
   12. should get the products >= a min price
7. Make the test pass
8. Create a red test for the controller
   9. should test for getting sorted books
   10. should test handling getting an empty response
   11. should test for CORs headers present for sorted books
   12. should test for return 404 when exception occurs for sorted books
   13. should test for CORS header present on filtered books
   14. should test for CORS header present on filtered books
   15. should test for success on filtered books
   16. should test for bad request on filtered books
   17. should test for bad request on post request
   18. should test for empty response on filtered books
9. Create a controller
   10. should filter price with path variables
   11. should create a product
   12. should return sorted products
   13. should delete a product
   14. should get a product
   15. should update a product
   16. should get all products
   17. should get all products >= a min price with a query param
10. Make the test pass
11. Create a Controller Integration Test
    12. should enabled the test to use TestRestTemplate 
    12. should use the entire app and should test for resource URI in creating a product


`POST` request to `/rating/compute/` :
* all the average ratings for all the products should be computed and returned as an array of the given objects.
* the response code is 200 and the response body is the array of the computed ratings


`GET` request to `/filter/discount/{discount_percentage}`:
* returns a collection of all events whose discount percentage is greater than the given input
* the response code is 200, and the response body is an array of all products that have a discount percentage greater than the one given in the query parameter
* In case there are no such products return status code 400


`GET` request to ` /filter/price/{initial_range}/{final_range}`:
* returns a collection of all products whose price is between the initial and the final range supplied
* the response code is 200, and the response body is an array of products in the price range provided
* In case there are no such products return status code 400


`GET` request to `/sort/price`:
* returns a collection of all products sorted by their pricing
* the response code is 200 and the response body is an array of the product names sorted in ascending order of price


Your task is to complete the given project so that it passes all the test cases when running the provided unit tests.

## Commands
- run: 
```bash
mvn clean package; java -jar target/project_jar-1.0.jar
```
- install: 
```bash
mvn clean install
```
- test: 
```bash
mvn clean test
```
