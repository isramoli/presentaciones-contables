package edu.viu.presentacionescontables.usuarios.controller;


import edu.viu.presentacionescontables.usuarios.entity.Usuario;
import edu.viu.presentacionescontables.usuarios.service.IUsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUsuarioService usuarioService;

    @Test
    public void testListarUsuarios() throws Exception {
        Usuario usuario1 = new Usuario();
        usuario1.setNombre("usuario1");
        Usuario usuario2 = new Usuario();
        usuario2.setNombre("usuario2");
        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);

        when(usuarioService.buscarTodosUsuarios()).thenReturn(usuarios);

        mockMvc.perform(get("/usuarios/lista-usuarios"))
                .andExpect(status().isOk())
                .andExpect(view().name("usuarios/lista-usuarios"))
                .andExpect(model().attribute("usuarios", hasSize(2)))
                .andExpect(model().attributeExists("titulo"));

        verify(usuarioService, times(1)).buscarTodosUsuarios();
        verifyNoMoreInteractions(usuarioService);
    }

    @Test
    public void testCrear() throws Exception {
        // Prueba para el método de creación de usuario

        mockMvc.perform(get("/usuarios/editar-usuario"))
                .andExpect(status().isOk())
                .andExpect(view().name("usuarios/editar-usuario"))
                .andExpect(model().attributeExists("usuario", "modoCreacion", "tiposUsuarios", "titulo"));

        verifyNoInteractions(usuarioService);
    }

    @Test
    public void testEditar() throws Exception {
        // Prueba para el método de edición de usuario

        String nombreUsuario = "usuario1";
        Usuario usuario = new Usuario();
        usuario.setNombre(nombreUsuario);

        when(usuarioService.buscarPorNombre(nombreUsuario)).thenReturn(Optional.of(usuario));

        mockMvc.perform(get("/usuarios/editar-usuario/{nombre}", nombreUsuario))
                .andExpect(status().isOk())
                .andExpect(view().name("usuarios/editar-usuario"))
                .andExpect(model().attribute("usuario", usuario))
                .andExpect(model().attribute("modoCreacion", false))
                .andExpect(model().attributeExists("tiposUsuarios", "titulo"));

        verify(usuarioService, times(1)).buscarPorNombre(nombreUsuario);
        verifyNoMoreInteractions(usuarioService);
    }

    @Test
    public void testGuardarValidacionFallida() throws Exception {
        // Prueba para el método de guardado de usuario con validación fallida

        Usuario usuario = new Usuario();
        usuario.setNombre("usuario1");

        mockMvc.perform(post("/usuarios/editar-usuario")
                        .flashAttr("usuario", usuario)
                        .param("modoCreacion", "true"))
                .andExpect(status().isOk())
                .andExpect(view().name("/usuarios/editar-usuario"))
                .andExpect(model().attributeExists("titulo"));

        verifyNoInteractions(usuarioService);
    }

    @Test
    public void testEliminar() throws Exception {
        // Prueba para el método de eliminación de usuario

        String nombreUsuario = "usuario1";

        mockMvc.perform(get("/usuarios/eliminar/{nombre}", nombreUsuario))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/usuarios/lista-usuarios"))
                .andExpect(flash().attribute("success", "Usuario eliminado con correctamente!"));

        verify(usuarioService, times(1)).borrar(nombreUsuario);
        verifyNoMoreInteractions(usuarioService);
    }


}
