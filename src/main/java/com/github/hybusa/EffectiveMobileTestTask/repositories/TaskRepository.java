package com.github.hybusa.EffectiveMobileTestTask.repositories;

import com.github.hybusa.EffectiveMobileTestTask.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
