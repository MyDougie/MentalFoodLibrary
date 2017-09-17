package MFLP.model.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import MFLP.controller.Action;


/**
 * Application Lifecycle Listener implementation class ActionMappingListener
 *
 */
@WebListener
public class ActionMappingListener implements ServletContextListener {

	public ActionMappingListener() {
	}

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent e) {
		ServletContext application = e.getServletContext();
		String fileName = application.getInitParameter("fileName");

		Map<String, Action> map = new HashMap<>();
		try {

			Properties pro = new Properties();

			pro.load(application.getResourceAsStream(("/WEB-INF/classes/") + fileName));

			Iterator<Object> keys = pro.keySet().iterator();
			while (keys.hasNext()) {
				String key = (String) keys.next();
				String value = pro.getProperty(key);
				//System.out.println(value);
				Action action = (Action) Class.forName(value).newInstance();
				
				map.put(key, action);
			
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// key�� value�� �и��ؼ� value�� ��ü�� ����

		// map�� �����ؼ� map�� key�� ������ ��ü�� value�� ����
		application.setAttribute("actionName", map);

		// map�� � ���������� �����Ҽ��ֵ��� application�� ����
	}
}
