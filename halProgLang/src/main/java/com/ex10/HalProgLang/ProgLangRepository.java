package com.ex10.HalProgLang;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(path = "repo-prog-languages")
public interface ProgLangRepository extends JpaRepository <ProgLang, Long> {
}
