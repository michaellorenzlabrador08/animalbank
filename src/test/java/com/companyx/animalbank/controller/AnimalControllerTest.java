package com.companyx.animalbank.controller;

import com.companyx.animalbank.dto.AnimalDto;
import com.companyx.animalbank.entity.Animal;
import com.companyx.animalbank.exception.InputIsEmpty;
import com.companyx.animalbank.service.animal.AnimalService;
import com.companyx.animalbank.service.animal.AnimalServiceImpl;
import com.companyx.animalbank.util.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class AnimalControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AnimalServiceImpl animalService;

    @InjectMocks
    private AnimalController animalController;


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(animalController)
                .build();
    }

    @Test
    public void testGetHello() throws Exception {

        //when , perform, verify

        when(animalService.getHello()).thenReturn("hello people!");
        mockMvc.perform(get("/api/animal/hello")
        )
                .andExpect(status().isOk())
                .andExpect(content().string("hello people!"));

        Mockito.verify(animalService).getHello();
    }


    //Expecting response as {"title": "Greetings", "value": "Hello World" }
    /*
    @Test
    public void testJson() throws Exception {
        mockMvc.perform(get("hello/json")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", Matchers.is("Greetings")))
                .andExpect(jsonPath("$.value", Matchers.is("Hello World")));
    }
    */

    @Test
    public void testPost() throws Exception {

        String json = "{\n" +
                "  \"name\": \"michael\",\n" +
                "  \"color\": \"yellow\",\n" +
                "  \"address\": \"simeie\",\n" +
                "  \"age\": 1,\n" +
                "  \"weight\": 10.5\n" +
                "}";

        mockMvc.perform(post("/api/animal/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
        // .andExpect(jsonPath("$.success", Matchers.is(true)));
    }

    @Test
    public void testCreateRequest() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        AnimalDto dto = new AnimalDto();
        dto.setName("michael");
        dto.setAddress("simei");
        dto.setColor("yellow");
        dto.setWeight(10.0);
        dto.setAge(2);


        Animal animal = new Animal(dto.getName(), dto.getColor(), dto.getAddress(), dto.getAge(), dto.getWeight());
        animal.setId((long) 1);

        String json = objectMapper.writeValueAsString(dto);
        System.out.println(json);

        when(animalService.createAnimal(dto)).thenReturn(animal);
        MvcResult mvcResult = mockMvc.perform(post("/api/animal/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andReturn();


        Response<Animal> response = new Response<>();
        response.success(animal);


        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        String expectedJsonResponse = objectMapper.writeValueAsString(response);

        Mockito.verify(animalService).createAnimal(dto);

        assertEquals(expectedJsonResponse, actualJsonResponse);
    }



}