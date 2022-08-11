# Pixelmon MDK [![Discord](https://img.shields.io/discord/831966641586831431)](https://discord.gg/7vqgtrjDGw) [![GitHub](https://img.shields.io/github/license/Pixelmon-Development/API)](https://www.gnu.org/licenses/lgpl-3.0.html)

This repository serves as an example for how to create a Pixelmon sidemod
using Forge 1.16.5 and Pixelmon's 9.X.X API.

If you have any unanswered questions after reading through this README then
please join my Discord and ask your questions in the [developer channel](https://discord.gg/7vqgtrjDGw).


<!-- TOC -->
* Pixelmon MD
  * [Getting Started](#getting-started)
    * [Download](#download)
    * [Clone](#clone)
    * [Editting the MDK](#editing-the-mdk)
    * [Building](#building)
  * [FAQ](#faq)
  * [Creating commands](#commands)
  * [Listeners](#listeners)
  * [EnvyAPI](#envyapi)
<!-- TOC -->

## Getting Started
To get started first clone this repository, or download the contents, to a 
folder where you want to work. 

### Download
To download the repository click the green "Code" button and then press "Download ZIP".
As seen below:

![img.png](img.png)

### Cloning
To clone the repository please follow the instructions [here](https://git-scm.com/book/en/v2/Git-Basics-Getting-a-Git-Repository)
under "Cloning an Existing Repository".

### Editing the MDK
Once you have the decompressed files in your workspace open the folder using IntelliJ.

Make sure to replace all "modid" references with the ID of your mod. When creating the mod ID please ensure
that it is all lower case and only contains alphanumeric characters (otherwise Forge/Minecraft will refuse to load it)

You should also change all "your.domain.path" references (and folder names) to the
packaging of your choice. You should own the domain that you're using and if not
a common practice is using `me.yournamehere.`.

#### Building
Once you have edited the above you're ready to go! To then compile your mod
you should use the `./gradlew build` command which will produce a JAR file
in the `releases` folder with a version number appended to the end.

Make sure to update your version number for each release you do!

## FAQ
Frequently Asked Questions

### Why do I want a version number at the end of the JAR name?
This makes it easier for end users to determine which version of the 
mod they are currently running. If you're looking for a jar without
any version numbers look in the `build/libs/` directory

### How do I run in debug
I usually don't run my mods in the Forge `runClient` so unfortunately I
am not much help in offering support there. When I am debugging/testing
I usually add logger lines to determine what variables contain what values,
or where the logic has reached at that point.

If someone else can explain how to make runClient work with a sidemod I am
more than happy to put the explanation above.

## Commands
To create commands using Mojang's Brigadier I recommend you read through
their documentation [here](https://github.com/Mojang/brigadier/blob/master/README.md)

I have my own API for commands. If you're looking for examples of how to 
use that I'd suggest looking through the [bingo](https://github.com/EnvyWare/ReforgedBingo) sidemod I have made.

## Listeners
In the project you can find an example Pixelmon listener. However, below I'll
include an example for TCG, Pixelmon and Forge.

Mod class:
```java
@SubscribeEvent
public static void onServerStarting(FMLServerStartingEvent event) {
    GenericEventListener eventListener = new GenericEventListener();
    MinecraftForge.EVENT_BUS.register(eventListener);
    Pixelmon.EVENT_BUS.register(eventListener);
    TCG.EVENT_BUS.register(eventListener);
}
```

GenericEventListener class:
```java
public static class GenericEventListener {
    
    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerLoggedInEvent event) {
        
    }
    
    @SubscribeEvent
    public void onTCGEvent(TCGEvent event) {
        
    }
    
    @SubscribeEvent
    public void onEggHatch(EggHatchEvent.Pre event) {
        
    }
}
```

## EnvyAPI

If you're looking for an example of how to use my API please look at
any of the sidemods on this GitHub organization.