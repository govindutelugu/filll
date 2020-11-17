package net.javaguides.springbootsecurity.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import net.javaguides.springbootsecurity.entities.Message;
import net.javaguides.springbootsecurity.entities.User;

/**
 * @author Caio Fernando
 */
public interface MessageRepository extends JpaRepository<Message,Integer>
{
    
    List<Message> findByUser(User user);    
}