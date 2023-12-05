package com.github.hybusa.EffectiveMobileTestTask.repositories;

import com.github.hybusa.EffectiveMobileTestTask.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
