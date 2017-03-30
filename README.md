# ent

Prerequisites
------------

Ent requires Java 1.8 and Ruby 2.1 or greater with JAVA_HOME set in your path. In addition, some prequrisite packages are required. The following is a list of steps to help get everything required to run ent installed on a system.  

```
sudo apt-add-repository ppa:brightbox/ruby-ng
sudo add-apt-repository ppa:openjdk-r/ppa
sudo apt-get update
sudo apt-get install openjdk-8-jdk
```

At this point, please set JAVA_HOME to the location of the installed java on your system. It's likely to be to /usr/lib/jvm/java-8-openjdk-amd64, but may differ.

```
sudo apt-get install ruby2.4*
sudo apt-get install gem
sudo apt-get install zlib1g*
sudo gem install ptools
sudo gem install nokogiri
sudo gem install terminal-table
sudo apt-get install ant
```


Installation
------------

From the cloned repository (we will refer to as ENT_HOME), execute the following commands to then build Ent.

```
cd /vendor/jrapl-port ; make clean; make ; cd ../..
ant clean; ant jar
```

Afterwards the ```entc``` and ```ent``` commands are used to compile and run ent programs respectively. Both are located in ENT\_HOME/bin (we recommend adding ENT\_HOME/bin to your path).

Usage
------------

```ent```/```entc``` function similarly to ```java```/```javac```: Use ```entc``` to compile Ent files (.ent or .java) to java class files and then invoke the Ent runtime with ```ent```.

```
entc -d class_files HelloWorld.java
ent -cp class_files HelloWorld
```

Benchmarks
------------

We have provided the link to our [benchmark suite used to evaluated Ent](https://www.google.com/search?client=ubuntu&channel=fs&q=markdown+link&ie=utf-8&oe=utf-8). This suite contains all code used, data, as well as scripts that help run the benchmarks and analyze the associated data.

We recommend consulting the companion documentation contained in /doc/artifact.pdf. It contains detailed documentation of the structure of the benchmarks, examining the data, and running the experiments.


Technical Report 
------------

We have provided a technical report that contains a summary of the data used in our paper, as well as our formal system and its proofs. This is located at /doc/ent-report.pdf.

