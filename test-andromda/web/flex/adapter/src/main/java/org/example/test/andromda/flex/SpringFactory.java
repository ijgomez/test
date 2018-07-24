package org.example.test.andromda.flex;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import flex.messaging.FactoryInstance;
import flex.messaging.FlexFactory;
import flex.messaging.config.ConfigMap;
import flex.messaging.services.ServiceException;

/** 
 * The FactoryFactory interface is implemented by factory components that provide instances to the data services messaging framework. To configure data services 
 * to use this factory, add the following lines to your services-config.xml file (located in the WEB-INF/flex directory of your web application): 
 * 
 * <pre> 
 *    <factories> 
 *     <factory id="spring" class="flex.samples.factories.SpringFactory" /> 
 *  </factories> 
 * </pre> 
 * 
 * You also must configure the web application to use spring and must copy the spring.jar file into your WEB-INF/lib directory.  To configure your app server 
 * to use Spring, you add the following lines to your WEB-INF/web.xml file:
 *  
 * <pre> 
 *   <context-param> 
 *        <param-name>contextConfigLocation</param-name> 
 *        <param-value>/WEB-INF/applicationContext.xml</param-value> 
 *   </context-param> 
 * 
 *   <listener> 
 *     <listener-class> 
*     org.springframework.web.context.ContextLoaderListener</listener-class> 
 *   </listener> 
 * </pre>
 *  
 * Then you put your Spring bean configuration in WEB-INF/applicationContext.xml (as per the line above).  For example:
 *  
 * <pre> 
 *   <?xml version="1.0" encoding="UTF-8"?> 
 *   <!DOCTYPE beans PUBLIC "-//SPRING//DTD BEAN//EN" "http://www.springframework.org/dtd/spring-beans.dtd"> 
 *    
 *   <beans> 
 *     <bean name="weatherBean" class="dev.weather.WeatherService" singleton="true"/> 
 *   </beans> 
 * </pre>
 *  
 * Now you are ready to define a Remoting Service destination  that maps to this existing service. To do this you'd add this to your 
 * WEB-INF/flex/remoting-config.xml: 
 * 
 * <pre> 
 *   <destination id="WeatherService"> 
 *      <properties> 
 *          <factory>spring</factory> 
 *          <source>weatherBean</source> 
 *      </properties> 
 *   </destination> 
 * </pre> 
 */
public class SpringFactory implements FlexFactory {

	private static final String SOURCE = "source"; 
	
	/**
	 * This method can be used to initialize the factory itself. It is called
	 * with configuration parameters from the factory tag which defines the id
	 * of the factory.
	 */
	public void initialize(String id, ConfigMap configMap) { }
	
	/**
	 * This method is called when we initialize the definition of an instance
	 * which will be looked up by this factory. It should validate that the
	 * properties supplied are valid to define an instance. Any valid properties
	 * used for this configuration must be accessed to avoid warnings about
	 * unused configuration elements. If your factory is only used for
	 * application scoped components, this method can simply return a factory
	 * instance which delegates the creation of the component to the
	 * FactoryInstance's lookup method.
	 */
	public FactoryInstance createFactoryInstance(String id, ConfigMap properties) {
		SpringFactoryInstance instance = new SpringFactoryInstance(this, id, properties);
		instance.setSource(properties.getPropertyAsString(SOURCE, instance.getId()));
		return instance;
	} // end method createFactoryInstance()
	
	/**
	 * Returns the instance specified by the source and properties arguments.
	 * For the factory, this may mean constructing a new instance, optionally
	 * registering it in some other name space such as the session or JNDI, and
	 * then returning it or it may mean creating a new instance and returning
	 * it. This method is called for each request to operate on the given item
	 * by the system so it should be relatively efficient.
	 * <p>
	 * If your factory does not support the scope property, it report an error
	 * if scope is supplied in the properties for this instance.
	 * </p>
	 */
	public Object lookup(FactoryInstance inst) {
		SpringFactoryInstance factoryInstance = (SpringFactoryInstance) inst;
		return factoryInstance.lookup();
	}
	
	static class SpringFactoryInstance extends FactoryInstance {
		
		SpringFactoryInstance(SpringFactory factory, String id, ConfigMap properties) {
			super(factory, id, properties);
		}

		public String toString() {
			return "SpringFactory instance for id=" + getId() + " source=" + getSource() + " scope=" + getScope();
		}

		public Object lookup() {
			ApplicationContext appContext = WebApplicationContextUtils.getWebApplicationContext(flex.messaging.FlexContext.getServletConfig().getServletContext());
			String beanName = getSource();
			try {
				
				//return appContext.getBean(beanName);
				
				// si se cargar el contexto de spring a traves del beanRefFactory hay que recuperar los bean de la siguiente forma: 
				return ((ApplicationContext) appContext.getBean("beanRefFactory")).getBean(beanName);
				
			} catch (NoSuchBeanDefinitionException nexc) {
				ServiceException e = new ServiceException();
				String msg = "Spring service named '" + beanName + "' does not exist.";
				e.setMessage(msg);
				e.setRootCause(nexc);
				e.setDetails(msg);
				e.setCode("Server.Processing");
				throw e;
			} catch (BeansException bexc) {
				ServiceException e = new ServiceException();
				String msg = "Unable to create Spring service named '" + beanName + "' ";
				e.setMessage(msg);
				e.setRootCause(bexc);
				e.setDetails(msg);
				e.setCode("Server.Processing");
				throw e;
			}
		}

	}
	

}
