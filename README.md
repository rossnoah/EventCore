
# EventCore

## [Join the discord for more information and support: Discord](https://discord.gg/5djuBSKWuV)

EventCore is a [Paper](https://github.com/PaperMC/Paper) plugin that helps administrators run last man standing style events in Minecraft. It features a system for kits, player teleporting, death handling, border shrinking and more!

## Dependencies

EventCore requires the PlaceholderAPI and DeathMessages plugins and works best with the WorldGuard and WorldEdit plugins as well.

EventCore was built on Java 20 for Minecraft version 1.19 but should work with any version of 1.19 or above.

## Installation

EventCore has a minimal configuration that is primarily used for the server's branding.

```yml
logo: "YOUR LOGO TEXT"
accent: "<#02f79d>"
red: "<#ec131a>"
green: "<#46e212>"

discord: "Click here to join the discord!"
discordlink: "https://discord.gg/yourdiscord"

help:
  - ""
  - ""
  - "<gold><bold>Event Commands</bold></gold>"
  - "<gray><strikethrough>---------------------</strikethrough></gray>"
  - "<gold>/help</gold><gray> - </gray><white>Shows this page</white>"
  - "<gold>/discord</gold><gray> - </gray><white>Gives discord link</white>"
  - "<gold>/alive</gold><gray> - </gray><white>Shows you how many players are left alive</white>"
```

## Commands and Permissions

### dashboard
- **Description:** Open the event dashboard
- **Usage:** `/<command>`
- **Permission:** `event.admin`
- **Aliases:** `dash`

### savekit
- **Description:** Save the event kit
- **Usage:** `/<command>`
- **Permission:** `event.admin`

### loadkit
- **Description:** Load your own kit
- **Usage:** `/<command>`
- **Permission:** `event.admin`

### setborder
- **Description:** Sets the border
- **Usage:** `/<command> <radius>`
- **Permission:** `event.admin`

### shrinkborder
- **Description:** Shrinks the border
- **Usage:** `/<command> <distance> [time]`
- **Permission:** `event.admin`
- **Aliases:** `shrink`

### expandborder
- **Description:** Shrinks the border
- **Usage:** `/<command> <distance> [time]`
- **Permission:** `event.admin`
- **Aliases:** `expand`

### kickspectators
- **Description:** Kicks all players in spectator
- **Usage:** `/<command>`
- **Permission:** `event.admin`

### alive
- **Description:** Shows you how many players are left alive
- **Usage:** `/<command>`
- **Permission:** `event.player`
- **Aliases:** `players`, `playersalive`

### discord
- **Description:** Gives discord link
- **Usage:** `/<command>`
- **Permission:** `event.player`

### help
- **Description:** Gives info about the event
- **Usage:** `/<command>`
- **Permission:** `event.player`

### spread
- **Description:** Spread out all players
- **Usage:** `/<command>`
- **Permission:** `event.admin`

### setbuildlimit
- **Description:** Update the build height limit
- **Usage:** `/<command> <height>`
- **Permission:** `event.admin`

### clearspawn
- **Description:** Removes the event spawn
- **Usage:** `/<command>`
- **Permission:** `event.admin`

### top
- **Description:** Your description
- **Usage:** `/<command>`
- **Permission:** `event.player`
- **Aliases:** `topkills`, `kills`
