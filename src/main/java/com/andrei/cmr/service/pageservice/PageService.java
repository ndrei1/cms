package com.andrei.cmr.service.pageservice;

import com.andrei.cmr.domain.Page;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class PageService {

    private void createPage(String htmlCode) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream("src/main/resources/templates/page.html", false);
            fileOutputStream.write(htmlCode.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void buildPage(Page page) {
        String body = new String(page.getHtmlCode(), StandardCharsets.UTF_8);
        String htmlCode = "<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + " <script type=\"text/javascript\" src=\"https://livejs.com/live.js\"></script>"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta name=\"description\" content=\"" +
                page.getDescription() +
                "\">\n" +
                "    <title>"
                + page.getTitle() + "</title>\n" +
                "</head>" +
                "<body>"
                + "\n <a th:href=\"@{/page/{slug}/delete(slug=${page.slug})}\" >Delete</a>"
                + " <a th:href=\"@{/page/{slug}/update(slug=${page.slug})}\">Update</a>"
                + "<h1>"
                + page.getH1()
                + "</h1>"
                + "\">\n"
                + body

                + "</body>";

        createPage(htmlCode);
    }
}
