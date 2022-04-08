# Bug in `org.aspectj.weaver.bcel.BcelWorld.reportMatch(BcelWorld.java:135)`

This repo is a POC to reproduce the bug. To reproduce do this:

```sh
git clone git@github.com:dagyu/bcel-world-bug-poc.git
cd bcel-world-bug-poc
./gradlew run
```

Then you we'll see that a new `ajcore.TIMESTAMP.txt` file is produced. 

You can also read the `ajcore` dump without run this project reading https://github.com/dagyu/bcel-world-bug-poc/blob/main/ajcore.20220408.211124.390.txt. 
In particular the bug is this:

```
 -- (NullPointerException) Cannot invoke "org.aspectj.asm.IRelationship$Kind.getName()" because the return value of "org.aspectj.weaver.bcel.BcelWorld.determineRelKind(org.aspectj.weaver.ShadowMunger)" is null
Cannot invoke "org.aspectj.asm.IRelationship$Kind.getName()" because the return value of "org.aspectj.weaver.bcel.BcelWorld.determineRelKind(org.aspectj.weaver.ShadowMunger)" is null
java.lang.NullPointerException: Cannot invoke "org.aspectj.asm.IRelationship$Kind.getName()" because the return value of "org.aspectj.weaver.bcel.BcelWorld.determineRelKind(org.aspectj.weaver.ShadowMunger)" is null
	at org.aspectj.weaver.bcel.BcelWorld.reportMatch(BcelWorld.java:135)
	at org.aspectj.weaver.Shadow.implementMungers(Shadow.java:646)
	at org.aspectj.weaver.Shadow.implement(Shadow.java:547)
	at org.aspectj.weaver.bcel.BcelClassWeaver.implement(BcelClassWeaver.java:3361)
	at org.aspectj.weaver.bcel.BcelClassWeaver.weave(BcelClassWeaver.java:499)
	at org.aspectj.weaver.bcel.BcelClassWeaver.weave(BcelClassWeaver.java:103)
	at org.aspectj.weaver.bcel.BcelWeaver.weave(BcelWeaver.java:1706)
	at org.aspectj.weaver.bcel.BcelWeaver.weaveWithoutDump(BcelWeaver.java:1650)
	at org.aspectj.weaver.bcel.BcelWeaver.weaveAndNotify(BcelWeaver.java:1417)
	at org.aspectj.weaver.bcel.BcelWeaver.weave(BcelWeaver.java:1192)
	at org.aspectj.ajdt.internal.compiler.AjPipeliningCompilerAdapter.weaveQueuedEntries(AjPipeliningCompilerAdapter.java:510)
	at org.aspectj.ajdt.internal.compiler.AjPipeliningCompilerAdapter.queueForWeaving(AjPipeliningCompilerAdapter.java:446)
	at org.aspectj.ajdt.internal.compiler.AjPipeliningCompilerAdapter.afterProcessing(AjPipeliningCompilerAdapter.java:431)
	at org.aspectj.ajdt.internal.compiler.CompilerAdapter.ajc$after$org_aspectj_ajdt_internal_compiler_CompilerAdapter$5$6b855184(CompilerAdapter.aj:104)
	at org.aspectj.org.eclipse.jdt.internal.compiler.Compiler.process(Compiler.java:943)
	at org.aspectj.org.eclipse.jdt.internal.compiler.Compiler.processCompiledUnits(Compiler.java:576)
	at org.aspectj.org.eclipse.jdt.internal.compiler.Compiler.compile(Compiler.java:476)
	at org.aspectj.org.eclipse.jdt.internal.compiler.Compiler.compile(Compiler.java:427)
	at org.aspectj.ajdt.internal.core.builder.AjBuildManager.performCompilation(AjBuildManager.java:1096)
	at org.aspectj.ajdt.internal.core.builder.AjBuildManager.performBuild(AjBuildManager.java:275)
	at org.aspectj.ajdt.internal.core.builder.AjBuildManager.performBuild(AjBuildManager.java:224)
	at org.aspectj.ajdt.internal.core.builder.AjBuildManager.incrementalBuild(AjBuildManager.java:192)
	at org.aspectj.ajdt.ajc.AjdtCommand.doCommand(AjdtCommand.java:102)
	at org.aspectj.ajdt.ajc.AjdtCommand.repeatCommand(AjdtCommand.java:62)
	at bcel.world.bug.poc.AjCompiler.compile(AjCompiler.java:40)
	at bcel.world.bug.poc.BcelWorldBugPoc.main(BcelWorldBugPoc.java:17)
 ```
