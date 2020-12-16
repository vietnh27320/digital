package controller;

import entity.Digital;
import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DigitalModel;

@WebServlet(name = "HomePage", urlPatterns = {"/HomePage"})
public class HomePage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            DigitalModel digitalModel = new DigitalModel();
            Random r = new Random();
            int radNumberPage = r.nextInt(digitalModel.countAll()) + 1;
            String digitalid = request.getParameter("id");
            int id = 0;
            digitalid = (digitalid == null) ? radNumberPage + "" : digitalid;
            try {
                id = Integer.parseInt(digitalid);
            } catch (NumberFormatException e) {
                request.getRequestDispatcher("Error.jsp").forward(request, response);
            }

            if (id == 1) {
                Digital d = digitalModel.getTop1Digital();
                request.setAttribute("top1", d);
            } else {
                Digital d = digitalModel.getOne(id);
                if (d != null) {
                    request.setAttribute("top1", d);
                } else {
                    request.setAttribute("top1", digitalModel.getTop1Digital());
                }
            }
            request.getRequestDispatcher("HomePage.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
