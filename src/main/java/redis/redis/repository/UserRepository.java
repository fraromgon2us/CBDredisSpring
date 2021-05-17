package redis.redis.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import redis.redis.entity.Usuario;

@Repository
public interface UserRepository extends CrudRepository<Usuario,Long> { }
