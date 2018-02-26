package com.app.control;

import com.app.modelo.dao.UsuarioDAO;
import com.app.modelo.dto.Usuario;
import com.app.utils.AppException;
import java.sql.Connection;
import java.util.List;

public class ControlUsuario {
    Connection cnn;
    UsuarioDAO dao;

    public ControlUsuario(Connection cnn) {
        this.cnn = cnn;
        dao = new UsuarioDAO(cnn);
    }
    
    public void insert(Usuario vo) throws AppException{
        try {
            if(vo.getNombre().isEmpty()){
                throw new AppException("Campos Nombre incompleto");
            }
            if(dao.queryNombre(vo.getNombre()) != null){
                throw new AppException("Usuario ya existe");
            }
            dao.insert(vo);
        } catch (Exception e) {
            throw new AppException(e);
        }
    }
    
    public void update(Usuario vo) throws AppException{
        try {
            if(vo.getNombre().isEmpty() || vo.getClave().isEmpty()){
                throw new AppException("Campos Campos incompletos");
            }
            dao.update(vo);
        } catch (Exception e) {
            throw new AppException(e);
        }
    }
    
    public void delete(Usuario vo) throws AppException{
        try {
            if(vo.getIdusuario() != 0){
                throw new AppException("Usuario no reconose");
            }
            dao.update(vo);
        } catch (Exception e) {
            throw new AppException(e);
        }
    }
    
    public List<Usuario> query() throws AppException{
        try {
            return dao.query();
        } catch (Exception e) {
            throw new AppException(e);
        }
    }
    
    public Usuario query(String nombre) throws AppException{
        try {
            return dao.queryNombre(nombre);
        } catch (Exception e) {
            throw new AppException(e);
        }
    }
    
    public Usuario query(String nombre, String clave) throws AppException{
        try {
            Usuario vo = dao.queryNombre(nombre);
            if(vo == null || !vo.getClave().equals(clave)){
                throw new AppException("Clave o Usuario Incorrecto");
            }
            return vo;
        } catch (Exception e) {
            throw new AppException(e);
        }
    }
    
    
}
