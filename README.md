# iris-extra-uniforms

## What

Provides extra uniforms to Iris shaders based on (mainly) server-side information normally unavailable to shaders.

Currently implemented:

| Uniform                            | Type   | Description                                         |
|------------------------------------|--------|-----------------------------------------------------|
| `eu_e4b4_ieu_targeted_by_enderman` | `bool` | Player currently targeted by enderman               |
| `eu_e4b4_ieu_scoreboard_[0-8]`     | `int`  | Value of scoreboard objective `iris-uniforms-[0-8]` |

## Setup

For setup instructions, please see the [Fabric Documentation page](https://docs.fabricmc.net/develop/getting-started/creating-a-project#setting-up) related to the IDE that you are using.
