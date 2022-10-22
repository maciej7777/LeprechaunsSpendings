package com.hacktoberfest.leprechaunsspendings.spending.web;

import com.hacktoberfest.leprechaunsspendings.spending.service.SpendingService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
@AutoConfigureMockMvc
class SpendingControllerTest {

    @MockBean
    private SpendingService spendingService;

    @Autowired
    private SpendingController spendingController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenPostValidSpending_thenCorrectResponse() throws Exception {
        String spending = "{" +
                "\"author\": \"Mac\"," +
                "\"spendingType\": \"FOOD\"," +
                "\"money\": {\"amount\":\"100.00\",\"currency\":\"EUR\"}," +
                "\"title\": \"beer\"," +
                "\"description\": \"beer\"," +
                "\"date\": \"2022-10-15\"" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/spendings")
                        .content(spending)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenPostSpendingWithoutAuthorTypeAndDate_thenExceptionIsReturned() throws Exception {
        String spending = "{" +
                "\"title\": \"beer\"," +
                "\"description\": \"beer\"" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/spendings")
                        .content(spending)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.author", Is.is("Author must be provided")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.spendingType", Is.is("Spending type must be provided")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date", Is.is("Date must be provided")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.money", Is.is("Amount and currency must be provided")));

    }

    @Test
    public void whenPostSpendingWithEmptyAuthorDateAndMoney_thenExceptionIsReturned() throws Exception {
        String spending = "{" +
                "\"author\": \"\"," +
                "\"spendingType\": \"FOOD\"," +
                "\"money\": {}," +
                "\"title\": \"beer\"," +
                "\"description\": \"beer\"," +
                "\"date\": \"\"" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/spendings")
                        .content(spending)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.author", Is.is("Author must be provided")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date", Is.is("Date must be provided")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.['money.currency']", Is.is("Currency must be provided")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.['money.amount']", Is.is("Amount must be provided")));

    }

    @Test
    public void whenPostSpendingWithInvalidAmount_thenExceptionIsReturned() throws Exception {
        String spending = "{" +
                "\"author\": \"Mac\"," +
                "\"spendingType\": \"FOOD\"," +
                "\"money\": {\"amount\":\"0.00\",\"currency\":\"EUR\"}," +
                "\"title\": \"beer\"," +
                "\"description\": \"beer\"," +
                "\"date\": \"2022-10-15\"" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/spendings")
                        .content(spending)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.['money.amount']", Is.is("Spending must be greater then 0")));
        ;
    }
}