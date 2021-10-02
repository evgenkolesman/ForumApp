package forum.controller;

import forum.Main;
import forum.model.Post;
import forum.repository.PostRepository;
import forum.service.CommonService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Calendar;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class PostConrollerTest {

    @MockBean
    private CommonService service;
    @MockBean
    private PostRepository repo;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageWrongReq() throws Exception {
        this.mockMvc.perform(post("/create")
                        .param("name","Куплю ладу-грант. Дорого."))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/create")
                        .param("name","Куплю ладу-грант. Дорого."))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(repo).save(argument.capture());
        assertThat(argument.getValue().getName(), is("Куплю ладу-грант. Дорого."));
    }

    @Test
    @WithMockUser(username = "test")
    public void testSavePost() throws Exception {
        this.mockMvc.perform(post("/save")
                        .param("name","Куплю ладу-грант. Дорого.").param("user", "test"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(service).saveOrEditPost(argument.capture());
        assertThat(argument.getValue().getUser().getName(), is("test"));
        assertThat(argument.getValue().getName(), is("Куплю ладу-грант. Дорого."));

    }

    @Test
    @WithMockUser(username = "test")
    public void testUpdatePost() throws Exception {
        this.mockMvc.perform(get("/update")
                        .param("name","Куплю ладу-грант. Дорого."))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(service).saveOrEditPost(argument.capture());
        assertThat(argument.getValue().getUser().getName(), is("test"));
        assertThat(argument.getValue().getName(), is("Куплю ладу-грант. Дорого."));

    }

}