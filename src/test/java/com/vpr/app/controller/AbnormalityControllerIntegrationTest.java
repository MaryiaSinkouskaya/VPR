package com.vpr.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vpr.app.dto.request.AbnormalityRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AbnormalityControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  private String token;

  @BeforeEach
  void setUp() throws Exception {
    token =
        authenticateAndGetToken("bjksdkjvsd@mail.com", "nsdn47n");
  }

  @Test
  void contextLoads() throws Exception {
    assert mockMvc != null; // Just verifies wiring
  }

  private String authenticateAndGetToken(String email, String password) throws Exception {
    String json = String.format("""
            {
              "email": "%s",
              "password": "%s"
            }
        """, email, password);

    String response = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/authenticate")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn().getResponse().getContentAsString();

    Map<String, String> map = objectMapper.readValue(response, Map.class);
    return map.get("access_token");
  }

  @Test
  void testGetAllAbnormality_withJwtAuth() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/abnormality/1")
            .header("Authorization", "Bearer " + token))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  @Disabled
  void testCreateUpdateAndDeleteAbnormality() throws Exception {
    // Create
    AbnormalityRequestDto createDto = new AbnormalityRequestDto();
    createDto.setName("Test Abnormality");

    String createJson = objectMapper.writeValueAsString(createDto);

    String responseCreate = mockMvc.perform(MockMvcRequestBuilders.post("/api/abnormality")
            .header("Authorization", "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON)
            .content(createJson))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andReturn().getResponse().getContentAsString();

    Map<String, Object> created = objectMapper.readValue(responseCreate, Map.class);
    Integer id = ((Number) created.get("id")).intValue();

    // Update
    createDto.setId(id);

    String updateJson = objectMapper.writeValueAsString(createDto);

    mockMvc.perform(MockMvcRequestBuilders.patch("/api/abnormality")
            .header("Authorization", "Bearer " + token)
            .contentType(MediaType.APPLICATION_JSON)
            .content(updateJson))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Updated description"));

    // Delete
    mockMvc.perform(MockMvcRequestBuilders.delete("/api/abnormality/{id}", id)
            .header("Authorization", "Bearer " + token))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
  }
}
