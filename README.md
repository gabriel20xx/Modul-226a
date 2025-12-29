# Breakthrough

![Screenshot](https://github.com/gabriel20xx/Modul-226a/raw/main/Screenshot.png)

A classic Breakout-style arcade game built with Greenfoot as part of Modul 226a.

## Table of Contents
- [Introduction](#introduction)
- [Requirements](#requirements)
- [Installation](#installation)
- [How to Start](#how-to-start)
- [Game Modes](#game-modes)
- [Controls](#controls)
- [Game Features](#game-features)
- [Scoring System](#scoring-system)
- [Authors](#authors)

## Introduction
Breakthrough is a fun arcade game featuring a paddle, a ball, and lots of bricks! The objective is simple: use your paddle to bounce the ball and destroy all the bricks in each level. The ball bounces off objects it touches, and you control the paddle's horizontal movement to keep the ball in play.

## Requirements
- [Greenfoot](https://www.greenfoot.org/) (Version 3.0 or higher recommended)
- Java Development Kit (JDK) 8 or higher

## Installation
1. Download and install [Greenfoot](https://www.greenfoot.org/download)
2. Clone or download this repository
3. Open Greenfoot
4. Select `Open...` from the `Scenario` menu
5. Navigate to the `breakthrough` folder in this repository
6. Click `Open` to load the project

## How to Start
The game should automatically start when you click the "Run" button.  
If it doesn't work:
1. Click on the "Welcome" world
2. Choose the option "new Welcome()"
3. Click the "Run" button


## Game Modes
There are currently two different modes available:

### Singleplayer
Play solo and control the paddle by yourself.

### Co-op
Team up with a friend! Both players work together using two paddles to keep the ball in play.

## Controls

### Singleplayer Mode
- **Move Left**: `A` or `←` (Left Arrow)
- **Move Right**: `D` or `→` (Right Arrow)
- **Speed Boost**: `Shift` or `Space`

### Co-op Mode
Each player controls one paddle:

**Player 1:**
- Move Left: `A`
- Move Right: `D`
- Speed Boost: `Shift`

**Player 2:**
- Move Left: `←` (Left Arrow)
- Move Right: `→` (Right Arrow)
- Speed Boost: `Space`

## Game Features
### Bricks
Bricks are static objects placed throughout the level. They are destroyed when the ball touches them.  
**Note:** Some bricks are indestructible, and others require multiple hits to destroy.

### Perks
Perks randomly appear during gameplay. When the paddle or ball touches a perk, you gain an extra life.

### Ball
The ball gradually increases its speed over time, making the game progressively more challenging.  
**Warning:** If the ball reaches the bottom of the screen, you lose one life.

### Lives
You start with **3 lives**. Collecting perks grants additional lives.  
**Game Over:** The game ends when you run out of lives.

## Scoring System
Your score is updated based on the following actions:
- **Brick Destroyed**: +50 Points
- **Ball Touches Paddle**: +10 Points
- **Life Lost**: -500 Points

Complete all levels to see the final scoreboard!

## Authors
- **Gabriel Franz** - Co-Developer
- **Cornel Forster** - Co-Developer

Created as part of Modul 226a coursework.

---

*Enjoy playing Breakthrough! 🎮*
