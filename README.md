# X-Price Project

X-price is a java project which is compare the price of products and present the result to the users.

##### Technologies
* Spring Boot version 3.2.0-SNAPSHOT
* MySQL
* Mockito


##### Domain Package
Domain package consists of:
* Currency Class : represents a currency, contains fields id, code, countryCode, also toDTO method.
* PriceDetail Class : represents prices of products, contains fields id, product, website, createdDate, updatedDate, salePrice, currency, also toDTO method.
* Product Class : represents a  product, contains fields id, name, brand, also toDTO method.
* Website Class : represents a website, contains fields id, name, url, also toDTO method.

##### Controller Package
Domain package consists of:
* Currency Controller : 
    - getCurrency -> returns currency for given currency id
    - getCurrencies -> returns all currencies
    - createCurrency -> creates a currency
* PriceDetail Controller : 
    - getPriceDetails -> returns all price details for given product id and currency id
    - getPriceDetailsWithLowestPrice -> returns the price detail for given product id and currency id, with lowest sale price
    - createPriceDetail -> creates a price detail
    - updatePriceDetailPrice -> updates sale price of price detail for given priceDetailId and salePrice
* Product Controller : 
    - getProduct -> returns product for given product id
    - getProducts -> returns all products
    - createProduct -> creates a product
* Website Controller : 
    - getWebsite -> returns website for given website id
    - getWebsites -> returns all websites
    - createWebsite -> creates a website


##### DTO Package
Dto package consists of:
* CurrencyDTO Class
* PriceDetailDTO Class
* ProductDTO Class
* WebsiteDTO Class

#### Unit Tests

Mockito is used.
Run appropriate test class, to run the test suits.

### Swagger Url

http://localhost:9090/swagger-ui/index.html

######Note: Lombok plugin is used, especially for generating getters and setters. 