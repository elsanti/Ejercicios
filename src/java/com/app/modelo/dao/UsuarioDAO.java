package com.app.modelo.dao;

import java.sql.SQLException;
import java.sql.Connection;
import com.app.modelo.dto.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    Connection cnn;

    public UsuarioDAO(Connection cnn) {
        this.cnn = cnn;
    }

    public void insert(Usuario vo) throws SQLException {
        PreparedStatement sentencia = cnn.prepareStatement("INSERT INTO usuario(nombre,clave) VALUES (?,?)");
        int i = 1;
        sentencia.setString(i++, vo.getNombre());
        sentencia.setString(i++, vo.getClave());
        sentencia.executeUpdate();
    }

    public void update(Usuario vo) throws SQLException {
        PreparedStatement sentencia = cnn.prepareStatement("UPDATE usuario SET nombre=? ,clave=? WHERE idusuario= ?");
        int i = 1;
        sentencia.setString(i++, vo.getNombre());
        sentencia.setString(i++, vo.getClave());
        sentencia.setInt(i++, vo.getIdusuario());
        sentencia.executeUpdate();
    }

    public void delete(Usuario vo) throws SQLException {
        PreparedStatement sentencia = cnn.prepareStatement("DELETE FROM usuario WHERE idusuario= ?");
        int i = 1;
        sentencia.setInt(i++, vo.getIdusuario());
        sentencia.executeUpdate();
    }

    public List<Usuario> query() throws SQLException {
        PreparedStatement sentencia = cnn.prepareStatement("SELECT * FROM usuario");
        int i = 1;
        ResultSet resultado = sentencia.executeQuery();
        List<Usuario> lista = new ArrayList<>();
        while (resultado.next()) {
            lista.add(getVo(resultado));
        }
        return lista;
    }

    public Usuario queryNombre(String nombre) throws SQLException {
        PreparedStatement sentencia = cnn.prepareStatement("SELECT * FROM usuario WHERE nombre=?");
        sentencia.setString(1, nombre);
        ResultSet resultado = sentencia.executeQuery();
        if (resultado.next()) {
            return getVo(resultado);
        }
        return null;
    }

    public Usuario getVo(ResultSet resultado) throws SQLException {
        Usuario vot = new Usuario();
        vot.setIdusuario(resultado.getInt("idusuario"));
        vot.setNombre(resultado.getString("nombre"));
        vot.setClave(resultado.getString("clave"));
        return vot;
    }

}
