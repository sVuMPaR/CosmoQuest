import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

import static org.mockito.Mockito.*;

/**
 * Базовые тесты для GameServlet.
 * Вызывается handleReset через рефлексию (работает при любом пакете и версии сервлета).
 */
public class GameServletTest {

    private GameServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @Before
    public void setUp() {
        servlet = new GameServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
    }

    private void callHandleReset() throws Exception {
        Method handleReset = null;
        for (Method m : GameServlet.class.getDeclaredMethods()) {
            if ("handleReset".equals(m.getName()) && m.getParameterCount() == 2) {
                handleReset = m;
                break;
            }
        }
        if (handleReset == null) {
            throw new AssertionError("GameServlet.handleReset(HttpServletRequest, HttpServletResponse) not found. Убедитесь, что GameServlet скомпилирован из актуального исходника с методом handleReset.");
        }
        handleReset.setAccessible(true);
        handleReset.invoke(servlet, request, response);
    }

    @Test
    public void doGetResetInvalidatesSessionAndRedirects() throws Exception {
        when(request.getServletPath()).thenReturn("/reset");
        when(request.getContextPath()).thenReturn("");
        when(request.getSession(false)).thenReturn(session);

        callHandleReset();

        verify(session).invalidate();
        verify(response).sendRedirect("/Quests/quest0.jsp");
    }

    @Test
    public void doGetResetWithNullSessionStillRedirects() throws Exception {
        when(request.getServletPath()).thenReturn("/reset");
        when(request.getContextPath()).thenReturn("/HelloQuest");
        when(request.getSession(false)).thenReturn(null);

        callHandleReset();

        verify(response).sendRedirect("/HelloQuest/Quests/quest0.jsp");
    }
}
