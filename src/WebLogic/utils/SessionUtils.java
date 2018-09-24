package WebLogic.utils;


import WebLogic.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {

    public static String getUsername (HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Object sessionAttribute = session != null ? session.getAttribute(Constants.USERNAME) : null;
        return sessionAttribute != null ? sessionAttribute.toString() : null;
    }

    public static Boolean getPlayerType (HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Object sessionAttribute = session != null ? session.getAttribute(Constants.IS_COMPUTER) : null;
        return sessionAttribute != null ? true : false;
    }

    public static void clearSession (HttpServletRequest request) {
        request.getSession().invalidate();
    }
}