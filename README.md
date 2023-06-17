Dependencies: PlaceholderAPI, (DecentHolograms or ProtocolLib), WorldGuard, WorldEdit

You are also recommended to join the community on the Discord server [here](https://discord.gg/gRF6FVwDJp).

# Features

- Customization of effects
- Event system providing a high level of customization
- Support for generating locations in all worlds
- Support for schematics
- Airdrop summoning items
- Location generation settings
- Pre-generation system for locations
- Item drop probability system
- Support for JavaScript scripts
- Automatic enchantment of items

# Event System

Every airdrop action is accompanied by an event. Listeners can listen to these events and perform specific actions. Here's an example:

```yaml
alert-start:
  description: '&fMessage about the start in 30 seconds' #brief description
  event: 'TIMER' #event
  requirement:
    check-1: #condition
      type: 'NUMERICAL_CHECK'
      input: '{time-to-start} == 30' #if 30 seconds until start
  commands: #execute command
    - '[MESSAGE_ALL] {air-name}&7 will appear in 30 seconds!'
```

# Schematic Support

You can add as many schematics as you want and thanks to the event system, spawn different schematics based on the biome, for example. 
![Screenshot_42](https://github.com/By1337/BAirDrop/assets/115788253/552302f2-3e74-44e8-a5af-c9c8b74d5227)

# Customizable Effects

```yaml
ef=2:
  type: CIRCLE
  ticks: 36000
  timeUpdate: 10
  radius: 2
  count: 0
  viewDistance: 30
  step: 0.05
  offset-x: 0.5
  offset-y: 0.7
  offset-z: 0.5
  number-of-steps: 6.5
  particle: REDSTONE
  #only REDSTONE#
  size: 3
  color-rgb-r: 255
  color-rgb-g: 0
  color-rgb-b: 255
```

All effects are objects, and for example, you can copy the effect above, rename it to ef=3, change the settings, and you will get another unique effect.

# Airdrop Summoning Item

```yaml
summoner:
  item-1:
    material: SOUL_CAMPFIRE #Material
    name: '&aAirdrop beacon'
    lore:
      - '&7line 1'
      - '&7line 2'
    airdrop: RANDOM #Which airdrop will be summoned
    clone: true #Summon a clone of the airdrop?
    use-player-location: true #Should the airdrop be summoned at the player's location?
    check-up-blocks: false #Should the airdrop check for blocks above before summoning?
    flatness-check: false #Should the airdrop check for location flatness when summoned?
    call: [] #Listeners to be called upon airdrop summoning
```
![Screenshot_43](https://github.com/By1337/BAirDrop/assets/115788253/01f65f58-e190-44da-b806-18942ec1359e)


