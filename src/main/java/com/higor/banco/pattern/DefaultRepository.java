package com.higor.banco.pattern;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DefaultRepository<T extends DefaultEntity> extends JpaRepository<T, Integer> {

}
