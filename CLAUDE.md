# AI Agent Guide (DualScreenKB)

This file provides repo-specific guidance for any AI agent (Claude, Copilot, GPT, etc.).
Follow these notes to keep changes consistent and safe.

## Project Summary

- Android IME demo for the AYN Thor dual-screen handheld.
- Custom keyboard UI on the bottom screen.
- Sends Windows-style shortcuts and optional sticky modifiers.

## Key Files

- app/src/main/java/com/dualscreenkb/thor/ThorKeyboardService.kt
  - IME service that inflates the keyboard layout and sends key events.
- app/src/main/res/layout/thor_keyboard_layout.xml
  - Keyboard UI layout and button IDs.
- app/src/main/java/com/dualscreenkb/thor/MainActivity.kt
  - Opens Android Input Method settings for enabling the IME.
- app/src/main/res/xml/method.xml
  - IME metadata for Android.

## Build and Run

- Build: ./gradlew assembleDebug
- Install the APK on device and enable "Thor IME" in Input Method settings.

## Behavior Notes

- Shortcuts send a modifier press, key down/up, then modifier release.
- Sticky modifiers are "held" by sending ACTION_DOWN on toggle and ACTION_UP on toggle off.
- Layout is a simplified demo and not a full keyboard implementation.

## Change Guidelines

- Keep UI IDs in layout and Kotlin mappings in sync.
- Prefer explicit KeyEvent down/up pairs for non-text keys.
- Avoid large refactors unless requested; this is a small demo app.
- Preserve ASCII-only text unless a file already contains Unicode.

## Testing Checklist

- Build: ./gradlew assembleDebug
- Manual: enable IME, verify shortcut buttons and sticky modifiers.

## Suggested Enhancements (If Asked)

- Add more shortcuts or layers (media keys, function keys).
- Add optional auto-release timer for sticky modifiers.
- Improve accessibility labels and localization.
