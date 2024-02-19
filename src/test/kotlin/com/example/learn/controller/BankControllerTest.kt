package com.example.learn.controller

import com.example.learn.model.Bank
import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.Matcher
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.*
import org.mockito.ArgumentMatchers.isA
import org.mockito.ArgumentMatchers.matches
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.buffer.DataBufferUtils.matcher
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest @Autowired constructor(
    private val mockMvc: MockMvc, private val objectMapper: ObjectMapper
) {


    @Nested
    @DisplayName("getBanks()")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetBanks {

        @Test
        fun `should return all blank`() {
            //given

            //when then
            mockMvc.get("/api/banks").andDo { print() }.andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].accountNumber") {
                        value("asd")
                    }
                }
        }

    }

    @Nested
    @DisplayName("GET /api/banks/{accountNumber}")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class GetBank {
        @Test
        fun `should return the bank with the given account number`() {
            val accountNumber = "asd"

            //when + then
            mockMvc.get("/api/banks/$accountNumber")

                .andDo { print() }.andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.transactionFee") { value("10") }
                }

        }

        @Test
        fun `should return no such element found`() {
            val accountNumber = "ad"

            //when + then
            mockMvc.get("/api/banks/$accountNumber")

                .andDo { print() }.andExpect { status { isNotFound() } }

        }
    }


    @Nested
    @DisplayName("POST /api/banks")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class PostNewBank {

        @Test
        fun `should add the new bank`() {
            val newBank = Bank("12345", 55.3, 17)

            //when
            val performPost = mockMvc.post("/api/banks") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newBank)
            }

            //then
            performPost.andDo { print() }.andExpect {
                    status { isCreated() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.accountNumber"){value("12345")}
                jsonPath("$.trust") { value(matches("-?\\d+(\\.\\d+)?\n")) }
//                jsonPath("$.transactionFee") { value<Int>() }
                }
        }
    }
}