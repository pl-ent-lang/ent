# ent

Installation
------------

We assume that ent has been unpacked/cloned to ENT\_HOME in the following examples.

Ant is the only prerequisite to build ent. Simply run ant from the ent directory.

```
cd ENT_HOME
ant 
```

Afterwards the ```entc``` and ```ent``` commands are used to compile and run ent programs respectively. Both are located in ENT\_HOME/bin (we recommend adding ENT\_HOME/bin to your path).

Usage
------------

```ent```/```entc``` function similarly to ```java```/```javac```: Use ```entc``` to compile Ent files (.ent or .java) to java class files and then invoke the Ent runtime with ```ent```.

```
entc -d class_files HelloWorld.java
ent -cp class_files HelloWorld
```
