package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResumeServlet extends HttpServlet {
    static final Storage SQL_STORAGE = Config.get().getStorage();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String s = "";
        response.getWriter().write("""
                                           <table border = "1">
                                           <caption>Таблица резюме</caption>
                                           <tr>
                                               <th>UUID</th>
                                               <th>Full name</th>
                                           </tr>""");
        for (Resume r : SQL_STORAGE.getAllSorted()) {
            s = s + "<tr>\n";
            s = s + "    <td>" + r.getUuid() + "</td>\n";
            s = s + "    <td>" + r.getFullName() + "</td>\n";
            s = s + "</tr>\n";
        }
        response.getWriter().write("\n" + s+ "</table>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
