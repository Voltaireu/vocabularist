package com.voltaireu.vocabularist.dictionary;

import com.voltaireu.vocabularist.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {

}
