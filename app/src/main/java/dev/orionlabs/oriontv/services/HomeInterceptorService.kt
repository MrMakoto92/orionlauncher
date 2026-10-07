package dev.orionlabs.oriontv.services

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.view.KeyEvent
import android.view.accessibility.AccessibilityEvent
import dev.orionlabs.oriontv.MainActivity

class HomeInterceptorService : AccessibilityService() {

    override fun onServiceConnected() {
        super.onServiceConnected()
        // Cuando el servicio de accesibilidad se conecta, aseguramos que el launcher esté listo
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Indica al sistema que reasigne y reinicie el servicio si el proceso es eliminado
        return START_STICKY
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        // No procesamos eventos de interfaz estándar
    }

    override fun onInterrupt() {
        // Callback si el servicio es interrumpido
    }

    override fun onKeyEvent(event: KeyEvent): Boolean {
        if (event.keyCode == KeyEvent.KEYCODE_HOME) {
            if (event.action == KeyEvent.ACTION_UP) {
                launchOrionLauncher()
            }
            return true
        }
        return super.onKeyEvent(event)
    }

    /**
     * Si el sistema, tvQuickActions Pro o una app limpiadora mata el proceso principal,
     * este callback se dispara y relanza Orion Launcher al instante.
     */
    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        launchOrionLauncher()
    }

    private fun launchOrionLauncher() {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or 
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or 
                    Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        startActivity(intent)
    }
}
