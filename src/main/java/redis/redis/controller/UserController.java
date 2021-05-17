package redis.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import redis.redis.entity.Usuario;
import redis.redis.repository.UserRepository;

import java.util.Optional;


@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Cacheable(value = "users", key = "#idUsuario",unless = "#result.edad < 40")
    @RequestMapping(value = "/{idUsuario}", method = RequestMethod.GET)
    public Usuario getUser(@PathVariable String idUsuario) {
        Optional<Usuario> usuario  = this.userRepository.findById(Long.valueOf(idUsuario));
        return usuario.orElse(null);
    }

    @Cacheable(value ="usuariosTodos", key = "1")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Iterable<Usuario> getUser() {
        return userRepository.findAll();
    }

    @CachePut(value = "users", key = "#usuario.id")
    @PutMapping("/update")
    public Usuario actualizaUsuario(@RequestBody Usuario usuario) {
        userRepository.save(usuario);
        return usuario;
    }
    @CacheEvict(value = "users", key = "#idUsuario")
    @DeleteMapping("/{idUsuario}")
    public void deleteUserByID(@PathVariable String idUsuario) {
        userRepository.deleteById(Long.valueOf(idUsuario));
    }

    @Cacheable(cacheManager = "manejadorPrimario" ,value = "users", key = "#idUsuario",unless = "#result.edad < 40")
    @RequestMapping(value = "/mayor40/{idUsuario}", method = RequestMethod.GET)
    public Usuario getUserMayorDe40(@PathVariable String idUsuario) {
        Optional<Usuario> usuario  = this.userRepository.findById(Long.valueOf(idUsuario));
        return usuario.orElse(null);
    }

    @Cacheable(cacheManager = "manejadorSecundario" ,value = "users", key = "#idUsuario",unless = "#result.edad >= 40")
    @RequestMapping(value = "/menor40/{idUsuario}", method = RequestMethod.GET)
    public Usuario getUserMenoresDe40(@PathVariable String idUsuario) {
        Optional<Usuario> usuario  = this.userRepository.findById(Long.valueOf(idUsuario));
        return usuario.orElse(null);
    }
}
