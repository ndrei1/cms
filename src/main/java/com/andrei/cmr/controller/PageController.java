package com.andrei.cmr.controller;

import com.andrei.cmr.domain.Page;
import com.andrei.cmr.repository.PageRepository;
import com.andrei.cmr.service.pageservice.PageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Controller
public class PageController {

    private final PageRepository pageRepository;
    private final PageService pageService;

    public PageController(PageRepository pageRepository, PageService pageService) {
        this.pageRepository = pageRepository;
        this.pageService = pageService;
    }

    @GetMapping(value = "page/{slug}")
    public String page(@PathVariable String slug, Model model) {
        Page page = pageRepository.findBySlug(slug);
        pageService.buildPage(page);
        model.addAttribute("page", page);
        return "page";
    }


    @GetMapping(value = "createPage")
    public String createPage(Model model) {
        model.addAttribute("page", new Page());
        return "createPage";
    }

    @PostMapping(value = "addPage")
    public String addPage(@ModelAttribute Page page, String htmlCode, @RequestParam(defaultValue = "false") boolean publishedCheck) {
        page.setPublished(publishedCheck);
        page.setPublishedTime(new Date());
        page.setHtmlCode(htmlCode.getBytes(StandardCharsets.UTF_8));
        pageRepository.save(page);

        return "redirect:/mainPage";
    }

    @GetMapping("page/{slug}/delete")
    public String deletePage(@PathVariable String slug) {
        pageRepository.delete(pageRepository.findBySlug(slug));
        return "redirect:/mainPage";
    }

    @GetMapping("page/{slug}/update")
    public String updatePage(@PathVariable String slug, Model model) {
        Page page = pageRepository.findBySlug(slug);
        String code = new String(page.getHtmlCode(), StandardCharsets.UTF_8);
        model.addAttribute("code", code);
        model.addAttribute("page", page);
        return "updatePage";
    }

    @PostMapping("updatePage")
    public String update(@ModelAttribute Page page, @RequestParam String code, @RequestParam(defaultValue = "false") boolean publishedCheck) {
        page.setPublished(publishedCheck);
        page.setPublishedTime(new Date());
        page.setHtmlCode(code.getBytes(StandardCharsets.UTF_8));
        pageRepository.save(page);
        return "redirect:/mainPage";
    }
}
