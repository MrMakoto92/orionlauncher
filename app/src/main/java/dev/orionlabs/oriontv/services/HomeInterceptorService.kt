package dev.orionlabs.oriontv.services

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.view.KeyEvent
import android.view.accessibility.AccessibilityEvent
import dev.orionlabs.oriontv.MainActivity

class HomeInterceptorService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // No procesamos eventos de interfaz estándar (TalkBack, etc.)
    }

    override fun onInterrupt() {
        // Callback si el servicio es interrumpido por el sistema
    }

    /**
     * Captura las pulsaciones de teclas físicas del control remoto.
     */
    override fun onKeyEvent(event: KeyEvent): Boolean {
        if (event.keyCode == KeyEvent.KEYCODE_HOME) {
            // Interceptamos la tecla al soltarla (ACTION_UP) para no disparar llamadas duplicadas
            if (event.action == KeyEvent.ACTION_UP) {
                launchOrionLauncher()
            }
            // Retornamos true para consumir el evento y evitar que el launcher por defecto responda
            return true
        }
        return super.onKeyEvent(event)
    }

    private fun launchOrionLauncher() {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        startActivity(intent)
    }
}
