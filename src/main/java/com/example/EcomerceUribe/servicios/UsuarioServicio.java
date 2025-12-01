package com.example.EcomerceUribe.servicios;

import com.example.EcomerceUribe.modelos.DTOS.UsuarioGenericoDTO;
import com.example.EcomerceUribe.modelos.Usuario;
import com.example.EcomerceUribe.modelos.mapas.IUsuarioMapa;
import com.example.EcomerceUribe.repositorios.IUsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {

    @Autowired
    private IUsuarioRepositorio repositorio;

    @Autowired
    private IUsuarioMapa mapa;

    //Declaro funciones para activar los servicios disponible del API

    //1. ACTIVADO EL SERVICIO DE GUARDADO DE DATOS
    public UsuarioGenericoDTO guardarUsuariogenerico(Usuario datosUsuario){
        //LOGICA DE NEGOCIO
        //Validacion de correo duplicado
        if(this.repositorio.findByCorreo(datosUsuario.getCorreo()).isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ya existe un usuario registrado con el correo ingresado"
            );
        }
        //Validacion de que el nombre no esta vacio
        if(datosUsuario.getNombres()==null || datosUsuario.getNombres().isBlank() ){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El nombre del usuario es obligatorio"
            );

        }
        //validacion de que la contraseña es minima
        if(datosUsuario.getContraseña().length()<6){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La contraseña debe tener al menos 6 caracteres"
            );

        }
        //Intentar guardar el usuario
        Usuario usuarioQueGuardoElRepo=this.repositorio.save(datosUsuario);
        if(usuarioQueGuardoElRepo==null){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al guardar el usuario en la base de datos"
            );
        }
        //Retornar el dto al controlador
        return this.mapa.convertir_usuario_a_usuariogenericodto(usuarioQueGuardoElRepo);
    }

    //Buscar Usuarios LIST (todos)
    public List<UsuarioGenericoDTO> buscarTodosLosUsuarios(){
        List<Usuario> listadeUsuariosConsultados=this.repositorio.findAll();
        return this.mapa.convertir_lista_a_listadtogenerico(listadeUsuariosConsultados);
    }
    //Buscar usuario por ID

    public UsuarioGenericoDTO buscarUsuarioGenericoPorID(Integer id){
        Optional <Usuario> usuarioQueEstoyBuscando= this.repositorio.findById(id);
        if (!usuarioQueEstoyBuscando.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun usuario con el id "+id+ "suministrado"
            );
        }
        Usuario usuarioEncontrado = usuarioQueEstoyBuscando.get();
        return this.mapa.convertir_usuario_a_usuariogenericodto(usuarioEncontrado);
    }
    //eliminar usuario
    public void eliminarUsuario(Integer id){
        Optional<Usuario> usuarioQueEstoyBuscando=this .repositorio.findById(id);
        if (!usuarioQueEstoyBuscando.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontro ningun usuario con el id "+id+ "suministrado"
            );
    }
    Usuario usuarioEncontrado = usuarioQueEstoyBuscando.get();
        try {
            this.repositorio.delete(usuarioEncontrado);
        }catch (Exception error){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "No se puedo eliminar el usuario, " +error.getMessage()
            );
        }

        //modificar update datos del usuario
        public UsuarioGenericoDTO actualizarUsuario(Integer id, Usuario datosActualizados){
            Optional<Usuario> usuarioQueEstoyEditanto this.repositorio.findById(id);

        }

        //Aplique validaciones sobre datos enviados desde el Frond

        //Actualizo los campos que permitieron modificar

        //Nombre //Correo


        //Concluyo actualizacion en la base de datos

        }
}
