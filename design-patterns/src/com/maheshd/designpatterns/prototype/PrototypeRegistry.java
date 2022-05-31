/**
 * 
 */
package com.maheshd.designpatterns.prototype;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mahesh
 *
 */
public class PrototypeRegistry {

	private static Map<String, Employee> empPrototypes = null;
	
	static {
		// Costly operation like creating new objects, loading objects from DB, etc.
		empPrototypes = new HashMap<>();
		empPrototypes.put("admin", new Admin());
		empPrototypes.put("developer", new Developer());
		empPrototypes.put("tester", new Tester());
	}
	
	public static Employee getEmployee(String type) throws CloneNotSupportedException {
		return (Employee) empPrototypes.get(type).clone();
	}
}
