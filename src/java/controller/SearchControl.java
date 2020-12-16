package controller;

import entity.Digital;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DigitalModel;

@WebServlet(name = "SearchControl", urlPatterns = {"/SearchControl"})
public class SearchControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String txt = request.getParameter("txtSearch").trim();
            int index = Integer.parseInt(request.getParameter("index"));
            int size = 3;
            DigitalModel digitalDAO = new DigitalModel();
            int endPage;
            List<Digital> list;
            if (txt == null) {
                int count = digitalDAO.countAll();
                endPage = count / size;
                if (count % size != 0) {
                    endPage++;
                }
                list = digitalDAO.getAll();
            } else {
                int count = digitalDAO.count(txt);
                endPage = count / size;
                if (count == 0) {
                    request.getRequestDispatcher("Error.jsp").forward(request, response);
                }
                if (count % size != 0) {
                    endPage++;
                }
                list = digitalDAO.search(txt, index);
            }
            request.setAttribute("endPage", endPage);
            request.setAttribute("listResultSearch", list);
            request.setAttribute("txt", txt);
            request.setAttribute("index", index);
            request.getRequestDispatcher("SearchResultPage.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
