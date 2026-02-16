# DualScreenKB (Thor IME)

DualScreenKB is a small Android Input Method (IME) demo for the AYN Thor dual-screen handheld.
It renders a custom keyboard on the bottom screen and sends Windows-style shortcuts to the
active input connection, with optional sticky modifiers for gameplay.

## Features

- Shortcut buttons: Win+I, Ctrl+C, Alt+Tab, Win+D.
- Sticky modifiers: Ctrl, Shift, Win (toggle on/off).
- Simple QWERTY demo grid (letters + Space, Enter, Back, Tab).
- Haptic feedback on key press.

## Project Structure

- [app/src/main/java/com/dualscreenkb/thor/MainActivity.kt](app/src/main/java/com/dualscreenkb/thor/MainActivity.kt)
   - Minimal settings launcher that opens Input Method settings.
- [app/src/main/java/com/dualscreenkb/thor/ThorKeyboardService.kt](app/src/main/java/com/dualscreenkb/thor/ThorKeyboardService.kt)
   - IME service implementation that sends key events and manages sticky modifiers.
- [app/src/main/res/layout/thor_keyboard_layout.xml](app/src/main/res/layout/thor_keyboard_layout.xml)
   - Keyboard UI layout for the bottom screen.
- [app/src/main/res/xml/method.xml](app/src/main/res/xml/method.xml)
   - IME metadata and settings activity definition.

## Build

Requirements:
- Android Studio (or Gradle on the command line)
- Android SDK 33
- JDK 17

```bash
./gradlew assembleDebug
```

## Install and Enable

1. Install the debug APK on the device.
2. Enable the IME:
    - Settings > System > Languages & input > On-screen keyboard > Manage keyboards
    - Enable "Thor IME" and select it as the active keyboard.
3. Launch Steam Link / Moonlight / Sunshine on the top screen.
4. Bring up the Thor IME on the bottom screen and use shortcut buttons or the demo keys.

## Development Notes

- Key dispatch uses `currentInputConnection.sendKeyEvent` with `KeyEvent` down/up pairs.
- Sticky modifiers are sent as `ACTION_DOWN` on toggle and released on toggle off.
- Layout is intentionally simple; it is not a full keyboard implementation.

## License

MIT
