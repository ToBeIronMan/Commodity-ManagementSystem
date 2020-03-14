package wust.commodity_management_system.listener;

import wust.commodity_management_system.model.User;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.*;

/**
 * @author yihui.yan
 */
public class SessionListener implements HttpSessionListener {

	// key为sessionId，value为HttpSession，使用static，定义静态变量，使之程序运行时，一直存在内存中。
	private static Map<String, HttpSession> sessionMap = new java.util.concurrent.ConcurrentHashMap<String, HttpSession>(
			500);

	/**
	 * HttpSessionListener中的方法，在创建session
	 */
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
	}

	/** 
     * HttpSessionListener中的方法，回收session时,删除sessionMap中对应的session 
     */  
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		getSessionMap().remove(event.getSession().getId());
	}

	/**
	 * 得到在线用户会话集合
	 */
	public static List<HttpSession> getUserSessions() {
		List<HttpSession> list = new ArrayList<HttpSession>();
		Iterator<String> iterator = getSessionMapKeySetIt();
		while (iterator.hasNext()) {
			String key = iterator.next();
			HttpSession session = getSessionMap().get(key);
			list.add(session);
		}
		return list;
	}

	/**
	 * 得到用户对应会话map，key为用户code,value为会话ID
	 */
	public static Map<String, String> getUserSessionMap() {
		Map<String, String> map = new HashMap<String, String>();
		Iterator<String> iter = getSessionMapKeySetIt();
		while (iter.hasNext()) {
			String sessionId = iter.next();
			HttpSession session = getSessionMap().get(sessionId);
			User user = (User) session.getAttribute("user");
			if (user != null) {
				map.put(user.getUserName(), sessionId);
			}
		}
		return map;
	}

	/**
	 * 移除用户Session
	 */
	public synchronized static void removeUserSession(String userName) {
		Map<String, String> userSessionMap = getUserSessionMap();
		if (userSessionMap.containsKey(userName)) {
			String sessionId = userSessionMap.get(userName);
			getSessionMap().get(sessionId).invalidate();
			getSessionMap().remove(sessionId);
		}
	}

	/**
	 * 增加用户到session集合中
	 */
	public static void addUserSession(HttpSession session) {
		getSessionMap().put(session.getId(), session);
	}

	/**
	 * 移除一个session
	 */
	public static void removeSession(String sessionId) {
		getSessionMap().remove(sessionId);
	}

	/**
	 * map中是否村在key为key
	 */
	public static boolean containsKey(String key) {
		return getSessionMap().containsKey(key);
	}

	/**
	 * 判断该用户是否已重复登录，使用 同步方法，只允许一个线程进入
	 */
	public synchronized static boolean checkIfHasLogin(User user) {
		Iterator<String> iter = getSessionMapKeySetIt();
		while (iter.hasNext()) {
			String sessionId = iter.next();
			HttpSession session = getSessionMap().get(sessionId);
			User userSession = (User) session.getAttribute("user");
			if (userSession != null) {
				if (userSession.getUserName().equals(user.getUserName())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 获取在线的sessionMap
	 */
	public static Map<String, HttpSession> getSessionMap() {
		return sessionMap;
	}

	/**
	 * 获取在线sessionMap中的SessionId
	 */
	public static Iterator<String> getSessionMapKeySetIt() {
		return getSessionMap().keySet().iterator();
	}

}
