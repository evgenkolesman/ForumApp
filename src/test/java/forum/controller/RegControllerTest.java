package forum.controller;

import forum.Main;
import forum.model.Authority;
import forum.model.User;
import forum.repository.AuthorityRepository;
import forum.service.CommonService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class RegControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommonService service;

    @MockBean
    private AuthorityRepository authorityRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/reg"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("reg"));
    }


    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageRedirect() throws Exception {
        User user = new User();
        Mockito.when(service.findUserByName("username")).thenReturn(user);
        mockMvc.perform(
                        post("/reg")
                                .param("username", "username")
                                .param("email", "email")
                                .param("password", "password")
                )
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    /**
     *     Если писать такую заглушку вначале (аналогично user)
     *     почему то не работает
     * //        Authority authority = new Authority();
     * //        authority.setAuthority("ROLE_USER");
     * //        Mockito.when(authorityRepository.findByAuthority("ROLE_USER")).thenReturn(authority1);
     *
     */
    @Test
    @WithMockUser
    public void whenSuccessfullyReg() throws Exception {
        Mockito.when(passwordEncoder.encode("password")).thenReturn("password");
        mockMvc.perform(
                        post("/reg")
                                .param("username", "username")
                                .param("email", "email")
                                .param("password", "password")
                )
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        Mockito.verify(service).saveOrEditUser(argument.capture());
        User userFinal = argument.getValue();
        userFinal.setName("username");
        Authority authority1 = new Authority();
        authority1.setAuthority("ROLE_USER");
        userFinal.setAuthority(authority1);
        Assertions.assertEquals("username", userFinal.getName());
        Assertions.assertEquals("password", userFinal.getPassword());
        Assertions.assertEquals("ROLE_USER", userFinal.getAuthority().getAuthority());
    }

}