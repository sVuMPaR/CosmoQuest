import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet({"/game", "/reset"})
public class GameServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String path = req.getServletPath();

        if (path.equals("/Quests/reset")) {
            HttpSession session = req.getSession();
            session.invalidate();
            resp.sendRedirect(req.getContextPath() + "/Quests/quest0.jsp");
            return;
        }

        HttpSession session = req.getSession();

        String answer = req.getParameter("answer");
        Integer victories = (Integer) session.getAttribute("victories");
        if (victories == null) victories = 0;

        Integer currentQuest = (Integer) session.getAttribute("currentQuest");
        if (currentQuest == null) currentQuest = 0;


        // Проверка: выбран ли ответ?
        if (answer == null || answer.isEmpty()) {
            req.setAttribute("message", "Выберите вариант!");
            req.getRequestDispatcher("/Quests/quest" + currentQuest + ".jsp").forward(req, resp);
            return;
        }

        // Проверяем, пройден ли квест уже
        String questStatus = (String) session.getAttribute("quest" + currentQuest);
        if ("completed".equals(questStatus)) {
            req.setAttribute("message", "Этот квест уже пройден!");
            req.getRequestDispatcher("/Quests/quest" + currentQuest + ".jsp").forward(req, resp);
            return;
        }

        // Обработка победы
        if ("accept".equals(answer)) {
            // Увеличиваем победы
            victories++;
            session.setAttribute("victories", victories);

            // Отмечаем квест как завершённый
            session.setAttribute("quest" + currentQuest, "completed");

            // ТОЛЬКО ЗДЕСЬ увеличиваем номер квеста (после успешной победы)
            currentQuest++;
            session.setAttribute("currentQuest", currentQuest);

            // Переход на следующий квест или финал
            if (currentQuest > 2) {
                resp.sendRedirect(req.getContextPath() + "/Quests/final.jsp");
            } else {
                resp.sendRedirect(req.getContextPath() + "/Quests/quest" + currentQuest + ".jsp");
            }
        } else {
            // Поражение
            session.setAttribute("result", "Поражение! Вы отказались.");
            resp.sendRedirect(req.getContextPath() + "/result.jsp");
        }
    }
}

