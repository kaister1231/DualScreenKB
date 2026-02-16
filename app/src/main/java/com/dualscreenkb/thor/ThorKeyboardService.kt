package com.dualscreenkb.thor

import android.inputmethodservice.InputMethodService
import android.os.Handler
import android.os.Looper
import android.view.HapticFeedbackConstants
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ToggleButton

class ThorKeyboardService : InputMethodService() {

    private var ctrlSticky = false
    private var shiftSticky = false
    private var winSticky = false

    override fun onCreateInputView(): View {
        val inflater = LayoutInflater.from(this)
        val view = inflater.inflate(R.layout.thor_keyboard_layout, null)
        setupShortcuts(view)
        return view
    }

    private fun setupShortcuts(root: View) {
        val btnWinI = root.findViewById<Button>(R.id.btn_win_i)
        val btnCtrlC = root.findViewById<Button>(R.id.btn_ctrl_c)
        val btnAltTab = root.findViewById<Button>(R.id.btn_alt_tab)
        val btnWinD = root.findViewById<Button>(R.id.btn_win_d)

        val toggleCtrl = root.findViewById<ToggleButton>(R.id.btn_ctrl_toggle)
        val toggleShift = root.findViewById<ToggleButton>(R.id.btn_shift_toggle)
        val toggleWin = root.findViewById<ToggleButton>(R.id.btn_win_toggle)

        btnWinI.setOnClickListener {
            performHaptic(root)
            sendWindowsShortcut(KeyEvent.KEYCODE_META_LEFT, KeyEvent.KEYCODE_I)
        }

        btnCtrlC.setOnClickListener {
            performHaptic(root)
            sendWindowsShortcut(KeyEvent.KEYCODE_CTRL_LEFT, KeyEvent.KEYCODE_C)
        }

        btnAltTab.setOnClickListener {
            performHaptic(root)
            sendWindowsShortcut(KeyEvent.KEYCODE_ALT_LEFT, KeyEvent.KEYCODE_TAB)
        }

        btnWinD.setOnClickListener {
            performHaptic(root)
            sendWindowsShortcut(KeyEvent.KEYCODE_META_LEFT, KeyEvent.KEYCODE_D)
        }

        // Sticky modifier toggles:
        toggleCtrl.setOnCheckedChangeListener { _, isChecked ->
            performHaptic(root)
            toggleModifier(KeyEvent.KEYCODE_CTRL_LEFT, isChecked) { ctrlSticky = it }
        }
        toggleShift.setOnCheckedChangeListener { _, isChecked ->
            performHaptic(root)
            toggleModifier(KeyEvent.KEYCODE_SHIFT_LEFT, isChecked) { shiftSticky = it }
        }
        toggleWin.setOnCheckedChangeListener { _, isChecked ->
            performHaptic(root)
            toggleModifier(KeyEvent.KEYCODE_META_LEFT, isChecked) { winSticky = it }
        }

        // Basic letter keys mapping (demo)
        val map = mapOf(
            R.id.key_q to KeyEvent.KEYCODE_Q,
            R.id.key_w to KeyEvent.KEYCODE_W,
            R.id.key_e to KeyEvent.KEYCODE_E,
            R.id.key_r to KeyEvent.KEYCODE_R,
            R.id.key_t to KeyEvent.KEYCODE_T,
            R.id.key_y to KeyEvent.KEYCODE_Y,
            R.id.key_u to KeyEvent.KEYCODE_U,
            R.id.key_i to KeyEvent.KEYCODE_I,
            R.id.key_o to KeyEvent.KEYCODE_O,
            R.id.key_p to KeyEvent.KEYCODE_P,
            R.id.key_a to KeyEvent.KEYCODE_A,
            R.id.key_s to KeyEvent.KEYCODE_S,
            R.id.key_d to KeyEvent.KEYCODE_D,
            R.id.key_f to KeyEvent.KEYCODE_F,
            R.id.key_g to KeyEvent.KEYCODE_G,
            R.id.key_h to KeyEvent.KEYCODE_H,
            R.id.key_j to KeyEvent.KEYCODE_J,
            R.id.key_k to KeyEvent.KEYCODE_K,
            R.id.key_l to KeyEvent.KEYCODE_L,
            R.id.key_z to KeyEvent.KEYCODE_Z,
            R.id.key_x to KeyEvent.KEYCODE_X,
            R.id.key_c to KeyEvent.KEYCODE_C,
            R.id.key_v to KeyEvent.KEYCODE_V,
            R.id.key_b to KeyEvent.KEYCODE_B,
            R.id.key_n to KeyEvent.KEYCODE_N,
            R.id.key_m to KeyEvent.KEYCODE_M,
            R.id.key_space to KeyEvent.KEYCODE_SPACE,
            R.id.key_enter to KeyEvent.KEYCODE_ENTER,
            R.id.key_back to KeyEvent.KEYCODE_DEL,
            R.id.key_tab to KeyEvent.KEYCODE_TAB
        )

        for ((id, keyCode) in map) {
            val btn = root.findViewById<Button>(id)
            btn?.setOnClickListener {
                performHaptic(root)
                sendKeyWithPossibleStickyModifiers(keyCode)
            }
        }
    }

    private fun sendWindowsShortcut(modifier: Int, key: Int) {
        val ic = currentInputConnection ?: return
        // Press modifier
        ic.sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, modifier))
        // Key down/up
        ic.sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, key))
        ic.sendKeyEvent(KeyEvent(KeyEvent.ACTION_UP, key))
        // Release modifier
        ic.sendKeyEvent(KeyEvent(KeyEvent.ACTION_UP, modifier))
    }

    private fun toggleModifier(modKey: Int, enable: Boolean, updateState: (Boolean) -> Unit) {
        val ic = currentInputConnection ?: return
        if (enable) {
            ic.sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, modKey))
            updateState(true)
        } else {
            ic.sendKeyEvent(KeyEvent(KeyEvent.ACTION_UP, modKey))
            updateState(false)
        }
    }

    private fun sendKeyWithPossibleStickyModifiers(keyCode: Int) {
        val ic = currentInputConnection ?: return
        // If sticky modifiers are active they were already sent as ACTION_DOWN on toggle
        // We only need to send the key press itself
        ic.sendKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, keyCode))
        ic.sendKeyEvent(KeyEvent(KeyEvent.ACTION_UP, keyCode))

        // Optional: auto-release sticky modifiers after a short timeout (not implemented here)
    }

    private fun performHaptic(root: View) {
        // light keyboard feedback
        Handler(Looper.getMainLooper()).post {
            root.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP)
        }
    }
}
