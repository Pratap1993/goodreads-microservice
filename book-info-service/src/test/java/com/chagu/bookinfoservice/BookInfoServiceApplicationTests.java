package com.chagu.bookinfoservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookInfoServiceApplicationTests {

    @Test
    void contextLoads() {
        //TestRestTemplate
        //WebTestClient
        /*Integer[] arr = new Integer[]{1, 2, 3};
        System.out.println(Arrays.toString(arr));*/
    }

    /*
    *
    * @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerWebTestClientTest {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  void shouldCreateNewCustomers() {
    this.webTestClient
      .post()
      .uri("/api/customers")
      .bodyValue("""
         {
        "firstName": "Mike",
        "lastName": "Thomson",
        "id": 43
       }
        """)
      .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
      .exchange()
      .expectStatus()
      .isCreated();
  }
}
    *
    * */

}
