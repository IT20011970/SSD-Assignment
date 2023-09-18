package lk.agri.repository;

import lk.agri.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserAccountRepository extends JpaRepository<UserAccount, String> {

    UserAccount findByEmailAndPassword(String email, String password);

}
