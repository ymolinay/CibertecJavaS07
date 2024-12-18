package com.cibertec.dao;

import com.cibertec.models.Usuario;
import java.sql.SQLException;
import java.util.List;

public interface UsuarioDao {
    List<Usuario> listarUsuarios() throws SQLException;
    Usuario obtenerUsuarioPorId(int id) throws SQLException;
    Usuario validarUsuario(String correo, String clave) throws SQLException;
    void registrarUsuario(Usuario usuario) throws SQLException;
    void editarUsuario(Usuario usuario) throws SQLException;
    void eliminarUsuario(int id) throws SQLException;
}