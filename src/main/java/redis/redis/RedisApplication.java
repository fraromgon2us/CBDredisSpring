package redis.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import redis.redis.entity.Usuario;
import redis.redis.repository.UserRepository;

@SpringBootApplication
@EnableCaching
public class RedisApplication implements CommandLineRunner {
    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private final UserRepository userRepository;

    @Autowired
    public RedisApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}

    @Override
    public void run(String... strings) {
        LOG.info("Saving users. Current user count is {}.", userRepository.count());
        Integer i = 0;
        while( i < 10){
            Usuario us = new Usuario("Usuario"+i, i+1);
            userRepository.save(us);
            i++;
        }
    }
}
