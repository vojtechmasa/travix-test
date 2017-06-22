package com.travix.medusa.busyflights;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BusyFlightsApplication.class)
@WebAppConfiguration
@ActiveProfiles("test")
public class BusyFlightsApplicationTests {

  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Before
  public void setup() {
    mockMvc = webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void contextLoads() {
  }

  //Integration tests. The partial functionality is tested within unit tests
  @Test
  public void shouldAggregateFlightsCorrectly() throws Exception {
    mockMvc.perform(get("/busyFlights")
        .param("origin", "PHA")
        .param("destination", "LON")
        .param("departureDate", "2016-01-01")
        .param("returnDate", "2016-02-01")
        .param("numberOfPassengers", "1"))
        .andDo(print())
        .andExpect(status().isOk())
        //TODO rewrite to jsonPath
        .andExpect(content().json(
            //@formatter:off
            "[" +
                "{" +
                "\"airline\":\"A1\"," +
                "\"supplier\":\"CrazyAir\"," +
                "\"fare\":\"20.20\"," +
                "\"departureAirportCode\":\"PHA\"," +
                "\"destinationAirportCode\":\"LON\"," +
                "\"departureDate\":\"2016-01-01T12:00:00\"," +
                "\"arrivalDate\":\"2016-02-01T12:00:00\"" +
                "}," +
                "{" +
                "\"airline\":\"A2\"," +
                "\"supplier\":\"CrazyAir\"," +
                "\"fare\":\"25.20\"," +
                "\"departureAirportCode\":\"PHA\"," +
                "\"destinationAirportCode\":\"LON\"," +
                "\"departureDate\":\"2016-01-01T12:00:00\"," +
                "\"arrivalDate\":\"2016-02-01T12:00:00\"" +
                "}," +
                "{" +
                "\"airline\":\"X2\"," +
                "\"supplier\":\"ToughJet\"," +
                "\"fare\":\"37.80\"," +
                "\"departureAirportCode\":\"PHA\"," +
                "\"destinationAirportCode\":\"LON\"," +
                "\"departureDate\":\"2016-01-01T12:00:00Z[UTC]\"," +
                "\"arrivalDate\":\"2016-02-01T12:00:00Z[UTC]\"}," +
                "{\"airline\":\"X3\",\"supplier\":\"ToughJet\"," +
                "\"fare\":\"49.84\",\"departureAirportCode\":\"PHA\"," +
                "\"destinationAirportCode\":\"LON\"," +
                "\"departureDate\":\"2016-01-01T12:00:00Z[UTC]\"," +
                "\"arrivalDate\":\"2016-02-01T12:00:00Z[UTC]\"" +
                "}" +
                "]"
            //@formatter:on
        ));
    ;
  }

}
