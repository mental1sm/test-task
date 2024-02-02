package com.mentalism.testtask;

import com.mentalism.testtask.service.StringService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

import java.util.LinkedHashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TestTaskApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    StringService stringService;

    @Test
    void contextLoads() {
    }

    @Test
    void testStringServiceCharsCount() throws Exception {
        String input = "a42seeeerr11";
        LinkedHashMap<String, Integer> expectatedMap = new LinkedHashMap<>();
        expectatedMap.put("e", 4);
        expectatedMap.put("1", 2);
        expectatedMap.put("r", 2);
        expectatedMap.put("2", 1);
        expectatedMap.put("4", 1);
        expectatedMap.put("a", 1);
        expectatedMap.put("s", 1);

        LinkedHashMap<String, Integer> outputMap = stringService.calculateCharCount(input);
        Assertions.assertThat(outputMap).isEqualTo(expectatedMap);
    }

    @Test
    void testStringController() throws Exception {
        String input = "a42seeeerr11";
        String expectatedOutput = """
                {
                  "e" : 4,
                  "1" : 2,
                  "r" : 2,
                  "2" : 1,
                  "4" : 1,
                  "a" : 1,
                  "s" : 1
                }
                """;

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString("/string/char-count")
                .queryParam("processingString", input);

        mockMvc.perform(get(uriBuilder.toUriString()))
                .andExpect(status().isOk())
                .andExpect(content().json(expectatedOutput));
    }

}
