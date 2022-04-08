package bcel.world.bug.poc;

import org.aspectj.ajdt.internal.core.builder.AjBuildManager;
import org.aspectj.bridge.IMessageHandler;
import org.aspectj.weaver.ICrossReferenceHandler;
import org.aspectj.weaver.bcel.BcelWorld;

public class CustomBuildManager extends AjBuildManager {
	
	BcelWorld currentBcelWorld = null;
	ICrossReferenceHandler xref;

	public CustomBuildManager(IMessageHandler holder, ICrossReferenceHandler xref) {
		super(holder);
		this.xref = xref;
	}

	@Override
	public BcelWorld getBcelWorld() {
		if(currentBcelWorld == null || currentBcelWorld != super.getBcelWorld()) {
			currentBcelWorld = super.getBcelWorld();
			currentBcelWorld.setCrossReferenceHandler(xref);
		}
		return currentBcelWorld;
	}
}
