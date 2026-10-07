<a id="english"></a>

<div align="center">

<img src="https://i.ibb.co/JjcKTBgG/White-Icon.png" width="120" alt="Orion Launcher logo" />

# Orion Launcher

**The official Orion NVR Launcher developed by ORION LABS.**

![Platform](https://img.shields.io/badge/Android%20TV%20·%20Google%20TV-3DDC84?logo=android&logoColor=white)




</a>


</div>

Orion Launcher Preview.

| Main screen | Settings | OrionLabs |
|:---:|:---:|:---:|
| <img src="docs/img/aerial.png" width="100%"/> | <img src="docs/img/settings.png" width="100%"/> | <img src="app/src/main/res/drawable/orion_labs_splash.jpg" width="100%"/> |

---

## Features

- New: Five new icons for the launcher's internal interface.
- New: New way added to set the launcher as default using accessibility services.

---

## Requirements

- **Android TV or Google TV** (requires the `leanback` feature — it won't install on phones/tablets).
- **Android 5.0 (Lollipop) or newer** — the whole Android TV lineage.
- **ARM (32- or 64-bit)**
- **No dependencies** — no companion app and **no Google Play Services** required; runs on AOSP boxes too.
- Driven entirely by the **remote / D-pad**; no touchscreen needed.

---

## Install

**1. Enable ADB debugging on the TV**

- `Settings → System → About →` click **Android TV OS build** 7 times
- `Settings → System → Developer options →` enable **USB / Wireless debugging**

**2. Connect and install from your computer** (TV IP is under `Settings → Network`):

```sh
adb connect <tv-ip>:5555
adb install -r orion-launcher.apk
```


---

## Set as default launcher

**Integration with the accessibility:**
```
Finally, the way to set the default launcher through accessibility has been enabled. 
```

**Generic Android TV / AOSP boxes** — press **Home**, pick **Orion Launcher**, choose **Always**.

**Certified Google TV** — Google blocks the on-screen home picker, so set it once over ADB:

```sh
adb shell cmd package set-home-activity dev.orionlabs.oriontv/.MainActivity
```

<details>
<summary><b>Stock launcher still taking over?</b></summary>

<br>

Check which launcher grabs **Home**:

```sh
adb shell cmd package resolve-activity -a android.intent.action.MAIN -c android.intent.category.HOME
```

Disable whichever package it reports (reversible with `adb shell pm enable <package>`) — the usual suspects:

```sh
adb shell pm disable-user --user 0 com.google.android.apps.tv.launcherx      # Google TV
adb shell pm disable-user --user 0 com.google.android.tvlauncher             # Android TV
adb shell pm disable-user --user 0 com.google.android.tungsten.setupwraith   # setup/recovery
```

Re-check; if another launcher takes over, disable that one too, until **Home** lands on Couchy.

</details>

The built-in setup wizard walks through this with your TV's IP pre-filled. No computer? It also offers a button-remap method (map **Home** to Couchy with an app like Button Mapper).

---

## Privacy

No ads, analytics, accounts or background services. The only network use is optional aerial-video streaming, off by default. Sections, ordering, hidden apps and wallpaper stay in one local file.

## Changelog

<details>
<summary>Click to expand</summary>

**v1.0.6 By OrionLABS**
- New: Five new icons for the launcher's internal interface.
- New: New way added to set the launcher as default using accessibility services.
</details>


<br>
