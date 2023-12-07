package com.github.hybusa.EffectiveMobileTestTask.repositories;

import com.github.hybusa.EffectiveMobileTestTask.models.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByAuthorId(Long id, Pageable pageable);
    Page<Task> findByAssignedId(Long id, Pageable pageable);
}
