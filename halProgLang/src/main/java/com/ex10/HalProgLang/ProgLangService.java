package com.ex10.HalProgLang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ProgLangService {
    @Autowired
    public static ProgLangRepository progLangRepository;

    public ProgLang newProgLang (ProgLang progLang){
        return progLangRepository.saveAndFlush(progLang);
    }
    public static Page<ProgLang> pagination(Pageable pageable) {
        return progLangRepository.findAll(pageable);
    }

    public ProgLang changeInventor (ProgLang newInventor) {
        if (newInventor != null) {
            newInventor.setInventor("Giò");
            return progLangRepository.saveAndFlush(newInventor);
        }
        return null;
    }
}
