package com.hacktoberfest.leprechaunsspendings.spending.web;

import com.hacktoberfest.leprechaunsspendings.spending.model.SpendingType;
import com.hacktoberfest.leprechaunsspendings.spending.service.MoneyDTO;
import com.hacktoberfest.leprechaunsspendings.spending.service.SpendingDTO;
import com.hacktoberfest.leprechaunsspendings.spending.service.SpendingService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.MockitoAnnotations.openMocks;

@WebMvcTest
@AutoConfigureMockMvc
class SpendingControllerTest {

    private static final String SPENDING_ID = "46430f71-0cf6-4264-a625-9b8e98e96a74";
    private static final String SPENDING_AUTHOR = "Mac";
    private static final SpendingType SPENDING_TYPE = SpendingType.FOOD;
    private static final MoneyDTO SPENDING_MONEY = new MoneyDTO(BigDecimal.TEN, "EUR");
    private static final String SPENDING_TITLE = "beer";
    private static final String SPENDING_DESCRIPTION = "beer 4 life";
    private static final String SPENDING_DATE_FORMAT = "yyyy-MM-dd";
    private static final DateTimeFormatter SPENDING_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(SPENDING_DATE_FORMAT);
    private static final LocalDate SPENDING_DATE = LocalDate.now();
    private static final String SPENDING_DATE_STRING = SPENDING_DATE.format(SPENDING_DATE_TIME_FORMATTER);

    @MockBean
    private SpendingService spendingService;

    @Autowired
    private SpendingController spendingController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenPostValidSpending_thenCorrectResponse() throws Exception {
        setupSpendingServiceResponse();
        String spending = "{" +
                "\"author\": \"" + SPENDING_AUTHOR + "\"," +
                "\"spendingType\": \"" + SPENDING_TYPE + "\"," +
                "\"money\": {\"amount\":\"100.00\",\"currency\":\"EUR\"}," +
                "\"title\": \"" + SPENDING_TITLE + "\"," +
                "\"description\": \"" + SPENDING_DESCRIPTION + "\"," +
                "\"date\": \"" + SPENDING_DATE_STRING + "\"" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/spendings")
                        .content(spending)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(SPENDING_ID)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author", Is.is(SPENDING_AUTHOR)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.spendingType", Is.is(SPENDING_TYPE.name())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.money.amount", Is.is(SPENDING_MONEY.getAmount().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.money.currency", Is.is(SPENDING_MONEY.getCurrency())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Is.is(SPENDING_TITLE)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", Is.is(SPENDING_DESCRIPTION)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date", Is.is(SPENDING_DATE.format(SPENDING_DATE_TIME_FORMATTER))));
    }

    private void setupSpendingServiceResponse() {
        SpendingDTO serviceResponse = SpendingDTO.Builder.create()
                .withId(UUID.fromString(SPENDING_ID))
                .withAuthor(SPENDING_AUTHOR)
                .withSpendingType(SPENDING_TYPE)
                .withAmount(SPENDING_MONEY.getAmount())
                .withCurrency(SPENDING_MONEY.getCurrency())
                .withTitle(SPENDING_TITLE)
                .withDescription(SPENDING_DESCRIPTION)
                .withDate(Date.valueOf(SPENDING_DATE))
                .build();

        Mockito.when(spendingService.createSpending(any())).thenReturn(serviceResponse);
        openMocks(this);
    }

    @Test
    public void whenPostSpendingWithoutAuthorTypeAndDate_thenExceptionIsReturned() throws Exception {
        String spending = "{" +
                "\"title\": \"" + SPENDING_TITLE + "\"," +
                "\"description\": \"" + SPENDING_DESCRIPTION + "\"" +
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
                "\"spendingType\": \"" + SPENDING_TYPE + "\"," +
                "\"money\": {}," +
                "\"title\": \"" + SPENDING_TITLE + "\"," +
                "\"description\": \"" + SPENDING_DESCRIPTION + "\"," +
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
    public void whenPostSpendingWithInvalidDate_thenExceptionIsReturned() throws Exception {
        LocalDate dateFromTheFuture = LocalDate.now().plusDays(1);
        String date = dateFromTheFuture.format(SPENDING_DATE_TIME_FORMATTER);
        String spending = "{" +
                "\"author\": \"" + SPENDING_AUTHOR + "\"," +
                "\"spendingType\": \"" + SPENDING_TYPE + "\"," +
                "\"money\": {\"amount\":\"" + SPENDING_MONEY.getAmount() + "\",\"currency\":\"" + SPENDING_MONEY.getCurrency() + "\"}," +
                "\"title\": \"" + SPENDING_TITLE + "\"," +
                "\"description\": \"" + SPENDING_DESCRIPTION + "\"," +
                "\"date\": \"" + date + "\"" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/spendings")
                        .content(spending)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.date", Is.is("Date must not be from the future")));
    }
}