import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/game")
public class GameServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Получаем сессию (создаётся автоматически, если отсутствует)
        HttpSession session = req.getSession();

        // Получаем ответ пользователя
        String answer = req.getParameter("answer");

        // Безопасное получение и инициализация счётчика побед
        Integer victories = (Integer) session.getAttribute("victories");
        if (victories == null) {
            victories = 0;
            session.setAttribute("victories", victories);
            System.out.println("Инициализирован victories=0");
        }

        // Безопасное получение и инициализация текущего квеста
        Integer currentQuest = (Integer) session.getAttribute("currentQuest");
        if (currentQuest == null) {
            currentQuest = 0;
            session.setAttribute("currentQuest", currentQuest);
            System.out.println("Инициализирован currentQuest=0");
        }

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

            // Переходим к следующему квесту
            currentQuest++;
            session.setAttribute("currentQuest", currentQuest);

            // Переход: если currentQuest ≤ 2 → следующий квест, иначе → final
            if (currentQuest <= 2) {
                resp.sendRedirect(req.getContextPath() + "/Quests/quest" + currentQuest + ".jsp");
            } else {
                resp.sendRedirect(req.getContextPath() + "/Quests/final.jsp");
            }
        } else {
            // Поражение
            session.setAttribute("result", "Поражение! Вы отказались.");
            resp.sendRedirect(req.getContextPath() + "/result.jsp");
        }
    }
}
