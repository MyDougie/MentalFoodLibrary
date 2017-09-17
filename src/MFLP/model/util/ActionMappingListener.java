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
		// key와 value를 분리해서 value를 객체로 생성

		// map을 생성해서 map에 key와 생성된 객체를 value로 저장
		application.setAttribute("actionName", map);

		// map을 어떤 영역에서도 접근할수있도록 application에 저장
	}
}
