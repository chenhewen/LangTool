package com.robust.cmd;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import org.reflections.Reflections;

public class HelpCmd extends BaseCmd {

	@Override
	public void onRun(String[] args) {
		System.out.println(getUsageInfo());
	}

	@Override
	public String getUsageInfo() {
		Reflections reflections = new Reflections("com.robust");
		Set<Class<? extends BaseCmd>> subTypes = 
		           reflections.getSubTypesOf(BaseCmd.class);
		
		StringBuffer sb = new StringBuffer();
		for (Class<? extends BaseCmd> clazz : subTypes) {
			try {
				Object newInstance = clazz.newInstance();
				if (newInstance instanceof HelpCmd) {
					continue;
				}
				Method declaredMethod = newInstance.getClass().getMethod("getUsageInfo");
				String helpTip = (String) declaredMethod.invoke(newInstance);
				sb.append(helpTip).append("\n");
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} 
		}
		
		return sb.toString();
	}
}
