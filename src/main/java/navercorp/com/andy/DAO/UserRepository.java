package navercorp.com.andy.DAO;

import navercorp.com.andy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
