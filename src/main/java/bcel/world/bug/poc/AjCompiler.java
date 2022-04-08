package bcel.world.bug.poc;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import org.aspectj.ajdt.ajc.AjdtCommand;
import org.aspectj.bridge.IMessage;
import org.aspectj.bridge.MessageHandler;

public class AjCompiler {
	
	private String sourceroots;

	public AjCompiler(String sourceroots) {
		this.sourceroots = sourceroots;
	}
	
	public void compile() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		AjdtCommand command = new AjdtCommand();
		MessageHandler messageHandler = new MessageHandler();
		
		CustomBuildManager customBuildManager = new CustomBuildManager(
				messageHandler, 
				(from,to,kind,runtimeTest) -> {}
				);
		
		Field field = command.getClass().getDeclaredField("buildManager");
		field.setAccessible(true);
		field.set(command, customBuildManager);

		field = command.getClass().getDeclaredField("savedArgs");
		String[] args = new String[] {
			"-sourceroots", this.sourceroots,
		};
		
		field.setAccessible(true);
		field.set(command, args);
		
		command.repeatCommand(messageHandler);
		
		
		Arrays.stream(messageHandler.getMessages(IMessage.ERROR, true))
			.forEach(e -> System.out.println(e.getMessage()));
	}
}
