package forum.controller;

import forum.Main;
import forum.model.Post;
import forum.service.CommonService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class PostConrollerTest {

    @MockBean
    private CommonService service;

    @InjectMocks
    private PostConroller controller;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageWrongReq() throws Exception {
        this.mockMvc.perform(post("/create")
                        .param("name", "Куплю ладу-грант. Дорого."))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(username = "test")
    public void shouldReturnDefaultMessageSave() throws Exception {
        this.mockMvc.perform(post("/save").param("id", String.valueOf(1))
                        .param("name", "Куплю ладу-грант. Дорого."))
                .andDo(print())
                .andExpect(status().is3xxRedirection());

        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(service).saveOrEditPost(argument.capture());
        assertThat(argument.getValue().getName(), is("Куплю ладу-грант. Дорого."));
        assertThat(argument.getValue().getId(), is(1));
    }

    @Test
    @WithMockUser(username = "test")
    public void testCreatePostWithInjectMock() throws Exception {
        Post post = new Post();
        post.setName("Куплю ладу-грант. Дорого.");
        post.setDesc("Куплю ладу-грант. Дорого.");
        String res = controller.create();
        Assert.assertThat(res, is("/create"));
    }

    @Test
    @WithMockUser(username = "test")
    public void testDeleteMethod() throws Exception {
        this.mockMvc.perform(get("/delete").param("id", "2")
                        .param("name", "Куплю ладу-грант. Дорого.").param("desc", "Куплю ладу-грант. Дорого."))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }
}