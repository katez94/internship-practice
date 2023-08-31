### Task 4. WEB + API testing (E2E testing)

Website under test - [https://vk.com/](https://vk.com/)

Automatic test was developed with launch capabilities on Firefox and Google Chrome. I implemented VkApiUtils class to work with http-requests, specifically, the logic for sending the request is carried out in the universal sendGetRequest() method, which takes the name of the method for sending the desired request and HashMap as a parameter. The test can be run on both the English and Russian versions of the site.

Stack: Maven, TestNG, Aquality-selenium Automation Framework, RestAssured, Lombok, Slf4j, Gson and Google guava.
