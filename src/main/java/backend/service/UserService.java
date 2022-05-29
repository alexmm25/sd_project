package backend.service;

import backend.model.User;
import backend.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static Logger logger = LogManager.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    /**
     *
     * @param id the ID of the user to be deleted
     */
    public void deleteByID(Long id) {
        logger.info("User deleted successfully!");
        userRepository.deleteById(id);
    }

    /**
     *
     * @return a list of all the users
     */
    public List<User> getAllUsers() {
        logger.info("Got list of users successfully!");
        return userRepository.findAll();
    }
}
