package abudu.awsa.repositories;

import abudu.awsa.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(Task status);
    List<Task> findByPriority(Task priority);
    List<Task> findByDeadlineBefore(LocalDateTime deadline);
    List<Task> findByDeadlineAfter(LocalDateTime deadline);
}
