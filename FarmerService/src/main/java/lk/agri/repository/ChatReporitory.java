package lk.agri.repository;

import lk.agri.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatReporitory extends JpaRepository<Chat, String> {
}
