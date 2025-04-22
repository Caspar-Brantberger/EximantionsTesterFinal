import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    static boolean existsByEmail(String email) {
        return existsByEmail(email);
    }
}
