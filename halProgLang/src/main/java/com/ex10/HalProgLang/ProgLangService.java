package com.ex10.HalProgLang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgLangService {
    @Autowired
    public static ProgLangRepository progLangRepository;
    public static Page<ProgLang> pagination(Pageable pageable) {
        return progLangRepository.findAll(pageable);
    }
}
