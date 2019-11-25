package com.voltaireu.vocabularist.exam;

import com.voltaireu.vocabularist.exam.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Long> {
}
