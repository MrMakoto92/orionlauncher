<a id="english"></a>

# Orion Launcher based on Couchy Launcher

## Project layout

Everything is Kotlin + Jetpack Compose for TV (`androidx.tv:tv-material`), min SDK 21.

```
app/src/main/java/com/conreo/couchytv/
  MainActivity.kt          entry point, HOME intent-filter, package-change rescan
  Actions.kt               launch / app-info / uninstall / force-stop / settings intents
  data/Config.kt           LauncherConfig (@Serializable) + DataStore persistence
  data/AppRepository.kt     app scan, auto-categorization, section grouping, icon cache
  data/BuiltinAerials.kt    built-in aerial manifest loading
  data/Status.kt            network + VPN status flow
  ui/LauncherScreen.kt      the launcher: layouts, wallpaper (ExoPlayer), move mode
  ui/SettingsSheet.kt       D-pad settings panel (animated sub-screens)
  ui/SetupWizard.kt         first-run wizard (default-home + VPN steps)
  ui/AppCard.kt, Menus.kt, StatusBar.kt, Theme.kt, Icons.kt
app/src/main/res/          strings (values + 17 locales), drawables, themes
```

## Conventions

- **Remote-first:** every control must be reachable with the D-pad alone. Text input only ever happens inside a dedicated dialog (see `RenameDialog`).
- **No hardcoded user-facing text** — put it in `res/values/strings.xml` and reference it with `stringResource(...)`. Default category and wallpaper names are localized too.
- **Match the surrounding style** — the codebase favors small, commented, self-explanatory composables. Comments explain *why*, not *what*.
- **State** lives in `LauncherConfig` and persists via `ConfigStore`. New config fields need a sensible default (deserialization ignores unknown keys, so old installs stay compatible).
- Keep dependencies minimal and FOSS (this ships on F-Droid — no Google Play Services, no trackers).

## Translations

To add or fix a language, copy `app/src/main/res/values/strings.xml` to `values-<code>/strings.xml` and translate the *values* (keep the `name="..."` keys). Android selects the right file automatically; English is the fallback.

Currently shipped: `ar de es fr hi in it ja ko nl pl pt ru th tr vi zh` (+ English).

Universal symbols (`0.5×`, `◄` / `►`) don't need translating.

## Pull requests

1. Keep changes focused; describe *what* and *why*.
2. Make sure `./gradlew assembleDebug` passes and the app runs on a real (or emulated) Android TV.
3. For UI changes, attach a screenshot.
4. New user-facing strings must be added to `values/strings.xml` (and ideally `values-fr`, `values-zh`).

<br>
