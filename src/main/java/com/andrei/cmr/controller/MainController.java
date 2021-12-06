package com.andrei.cmr.controller;

import com.andrei.cmr.domain.Page;
import com.andrei.cmr.repository.PageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    private final PageRepository pageRepository;

    public MainController(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    @PostMapping(value = "/updatePublished")
    public String updatePublished(@RequestParam(defaultValue = "false") boolean publishedCheck, Model model) {
        List<Page> publishedPage;
        if (publishedCheck) {
            publishedPage = pageRepository.findAll();
        } else {
            publishedPage = pageRepository.findAllByPublishedIsTrueOrderByPriority();
        }
        model.addAttribute("pages",publishedPage);
        return "mainPage";
    }


    @GetMapping("mainPage")
    public String mainPage(Model model) {
        List<Page> publishedPage = pageRepository.findAllByPublishedIsTrueOrderByPriority();
        model.addAttribute("pages",publishedPage);
        return "mainPage";
    }

}
