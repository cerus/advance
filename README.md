<h2 align="center">advance</h2>
<p align="center">Spigot library for displaying advancement banners</p>

<p align="center">
    <img alt="Minecraft advancement banner" src="https://i.imgur.com/VNbKDsO.png">
</p>

<br>

## Overview

advance is...

- a library for displaying custom advancement banners

advance is **not**...

- a library for creating custom advancements in the advancements tab
- a library for creating custom advancements in general

## Compatibility

advance is compatible with 1.16.5, 1.17 and 1.17.1.

## Installation

Latest version: `1.0.0`

<details>
<summary>Maven</summary>

```xml

<project>
    <properties>
        <!-- Replace with latest version -->
        <advance.version>1.0.0</advance.version>
    </properties>

    <dependencies>
        <!-- General api module -->
        <dependency>
            <groupId>dev.cerus.advance</groupId>
            <artifactId>api</artifactId>
            <version>${advance.version}</version>
            <scope>compile</scope>
        </dependency>

        <!-- Implementation for 1.16.5 -->
        <!-- Only implement this if you need 1.16.5 support -->
        <dependency>
            <groupId>dev.cerus.advance</groupId>
            <artifactId>api-16R3</artifactId>
            <version>${advance.version}</version>
            <scope>compile</scope>
        </dependency>

        <!-- Implementation for 1.17(.1) -->
        <!-- Only implement this if you need 1.17(.1) support -->
        <dependency>
            <groupId>dev.cerus.advance</groupId>
            <artifactId>api-17R1</artifactId>
            <version>${advance.version}</version>
            <scope>compile</scope>
        </dependency>

        <!-- Optional: Utilities for advance -->
        <dependency>
            <groupId>dev.cerus.advance</groupId>
            <artifactId>util</artifactId>
            <version>${advance.version}</version>
            <scope>compile</scope>
        </dependency>
    </dependencies>
</project>
```

</details>

## Usage

```java
public class YourClass {

    public void doStuff(Player player) {
        // Without utils module:
        // (Replace with the implementation for the targeted version)
        AdvanceApi api = new dev.cerus.advance.api.v16r3.AdvanceApiImpl();
        // With utils module:
        // (Automatically detects version)
        api = new AdvanceApiFactory().makeApi();

        api.showAdvancement(
                // This doesn't have to be a Minecraft key
                NamespacedKey.minecraft("advance/example"),

                // Text that will be displayed in the banner
                new BaseComponent[] {new TextComponent("Your text here")},

                // Banner icon, can be any item
                new ItemStack(Material.DIRT),

                // Either CHALLENGE, GOAL or TASK
                FrameType.CHALLENGE,

                // Array of receiving players
                player
        );

        // Also check out:
        // AdvanceApi#showTask()
        // AdvanceApi#showGoal()
        // AdvanceApi#showChallenge()
        // Advancement.of()
    }

}
```