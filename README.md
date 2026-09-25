# delay-sleep

Fabric mod that delays the earliest time you can sleep in a bed.

## Requirements

- Minecraft 26.3
- [Fabric Loader](https://fabricmc.net/use/installer) 0.19.5+
- [Fabric API](https://modrinth.com/mod/fabric-api)
- Java 25+

## Configuration

The `config/delay-sleep.json` file is created on first launch:

```json
{
  "minTickClear": 17843,
  "minTickRain": 13188
}
```

| Key            | Applies when        | Default | Vanilla |
| -------------- | ------------------- | ------- | ------- |
| `minTickClear` | Clear weather       | 17843   | 12542   |
| `minTickRain`  | Rain or thunderstorm | 13188   | 12010   |

Values range from `0` to `23999` (one full day) and are given in ticks since the
start of the day cycle (tick `0` = 6:00 AM). By default, `minTickClear` is
~11:50 PM and `minTickRain` ~7:11 PM. Dimensions with a fixed time (nether,
end) are not affected.

## Building

```sh
./gradlew build
```

The resulting `.jar` is in `build/libs/`.

## License

[MIT](LICENSE)
