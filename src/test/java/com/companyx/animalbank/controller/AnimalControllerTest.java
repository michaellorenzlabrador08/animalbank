package com.companyx.animalbank.controller;

import com.companyx.animalbank.dto.AnimalDto;
import com.companyx.animalbank.entity.Animal;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AnimalControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    public void test_create() throws Exception {
        AnimalDto dto = new AnimalDto("michael", "blue", "marymount", 2, 5.9);
        Animal newAnimal = new Animal((long) 1, "michael", "blue", "marymount", 2, 5.9);

        MvcResult mvcResult = mockMvc.perform(post("/api/animal/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk()).andReturn();

        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        String expectedJsonResponse = objectMapper.writeValueAsString(newAnimal);
        assertEquals(expectedJsonResponse, actualJsonResponse);

    }

    @Test
    public void test_list() throws Exception {
        mockMvc.perform(get("/api/animal/list")
        )
                .andExpect(status().isOk());
    }

    @Test
    public void test_update() throws Exception {

        Animal newAnimal = new Animal((long) 1, "new-michael", "blue", "marymount", 2, 5.9);
        mockMvc.perform(put("/api/animal/update/" + 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newAnimal)))
                .andExpect(status().isOk());

    }

    @Test
    public void test_delete() throws Exception {
        mockMvc.perform(delete("/api/animal/delete/" + 1)
        ).andExpect(status().isOk());
    }

    /*

    @Test
    public void testList() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        //when
        List<Animal> list = new ArrayList<>();
        list.add(new Animal((long) 1, "michael", "yellow", "simei", 2, 5.9));
        list.add(new Animal((long) 2, "michael", "yellow", "simei", 2, 5.9));
        list.add(new Animal((long) 3, "michael", "yellow", "simei", 2, 5.9));

        when(animalService.list()).thenReturn(list);
        //then
        MvcResult mvcResult = mockMvc.perform(get("/api/animal/list")
        )
                .andExpect(status().isOk())
                .andReturn();
        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        String expectedJsonResponse = mapper.writeValueAsString(list);


        //verify
        assertEquals(expectedJsonResponse, actualJsonResponse);
        verify(animalService).list();
    }

    @Test
    public void testCreate() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        //when
        AnimalDto dto = new AnimalDto("delf", "black", "marymount", 3, 2.0);
        Animal newAnimal = new Animal((long) 1, dto.getName(), dto.getColor(), dto.getAddress(), dto.getAge(), dto.getWeight());
        when(animalService.create(dto)).thenReturn(newAnimal);

        //Animal addedAnimal = animalController.create(dto);
        String request = mapper.writeValueAsString(dto);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/animal/create")
                .accept(MediaType.APPLICATION_JSON).content(request)
                .contentType(MediaType.APPLICATION_JSON);

        //then
        MvcResult mvcResult = mockMvc.perform(
                requestBuilder)
                .andReturn();

        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        String expectedJsonResponse = mapper.writeValueAsString(newAnimal);


        //verify
        assertEquals(expectedJsonResponse, actualJsonResponse);
        //assertEquals(newAnimal, addedAnimal);
        verify(animalService).create(dto);
    }

    @Test
    public void testUpdate() throws Exception {
        //when
        ObjectMapper mapper = new ObjectMapper();
        Animal oldAnimal = new Animal((long) 1, "michael", "yellow", "simei", 2, 5.9);
        Animal newAnimal = new Animal((long) 1, "michael-new", "yellow", "simei", 2, 5.9);

        when(animalService.update(oldAnimal, (long) 1)).thenReturn(newAnimal);
        String request = mapper.writeValueAsString(newAnimal);

        //then
        MvcResult mvcResult = mockMvc.perform(put("/api/animal/update/" + oldAnimal.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)
        )
                .andExpect(status().isOk()).andReturn();

        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        String expectedJsonResponse = mapper.writeValueAsString(newAnimal);

        //verify
        assertEquals(expectedJsonResponse, actualJsonResponse);
        verify(animalService).update(newAnimal, oldAnimal.getId());

    }


    @Test
    public void testDelete() throws Exception {
        //when
        when(animalService.delete((long) 1)).thenReturn(true);
        //then
        MvcResult mvcResult = mockMvc.perform(delete("/api/animal/delete/" + 1)
        )
                .andExpect(status().isOk()).andReturn();

        Boolean response = Boolean.valueOf(mvcResult.getResponse().getContentAsString());

        //verify
        verify(animalService).delete((long) 1);
        assertTrue(response);
    }

*/
}