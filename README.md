# DualScreenKB - AYN Thor IME demo

This is a demo Android Input Method (IME) intended for the AYN Thor dual-screen handheld.
It provides a custom keyboard on the bottom screen that can send Windows-style shortcuts
(e.g., Win+I, Ctrl+C, Alt+Tab) and supports sticky modifiers for gameplay (Ctrl/Shift/Win).

Build & Run
1. Import this project into Android Studio (or run ./gradlew assembleDebug).
2. Install the APK on your AYN Thor device.
3. Enable the IME:
   - Settings > System > Languages & input > On-screen keyboard > Manage keyboards
   - Enable "Thor IME" and then select it as the active keyboard (keyboard icon or input settings).
4. Start Steam Link / Moonlight / Sunshine on the top screen and bring up the Thor IME on the bottom screen.
   Tap shortcut buttons to send keys to the streamed Windows game.

Notes
- The IME uses currentInputConnection.sendKeyEvent to dispatch KeyEvent objects.
- For production use, refine layouts, localize strings, add proper accessibility & security handling.

License: MIT
