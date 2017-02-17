# ent

Prerequisites
------------
Ent makes use of ruby scripts to hook in to popular java build frameworks, notably ant and gradle. For that reason, ruby must be installed, along with the nokogiri gem. If you have ruby gem's installed, simply run the following command.

```
gem install nokogiri
```

If one wishes the run the test suite, the ptools gem is required.

```
gem install ptools
``` 

Lastly, JRAPL is required to run the extended test suite, as well as the [ent benchmarks](https://github.com/anthonycanino1/entbench). We have vendored jrapl directly into the repository. Run ```make``` from vendor/jrapl. 

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
