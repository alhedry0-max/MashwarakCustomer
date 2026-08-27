package com.mashwarak.customer

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24)
            setBackgroundColor(0xFFF7F8FA.toInt())
        }

        val title = TextView(this).apply {
            text = getString(R.string.app_name)
            textSize = 28f
            setTextColor(0xFF111827.toInt())
            setPadding(0, 24, 0, 8)
        }

        val subtitle = TextView(this).apply {
            text = "احجز مشوارك بسهولة وسرعة"
            textSize = 17f
            setTextColor(0xFF4B5563.toInt())
            setPadding(0, 0, 0, 24)
        }

        val from = TextView(this).apply {
            text = "📍 موقع الانطلاق\nحدد موقعك الحالي"
            textSize = 18f
            setTextColor(0xFF111827.toInt())
            setPadding(20)
            setBackgroundColor(0xFFFFFFFF.toInt())
        }

        val to = TextView(this).apply {
            text = "🏁 الوجهة\nإلى أين تريد الذهاب؟"
            textSize = 18f
            setTextColor(0xFF111827.toInt())
            setPadding(20)
            setBackgroundColor(0xFFFFFFFF.toInt())
        }

        val request = Button(this).apply {
            text = "طلب مشوار"
            textSize = 18f
            setOnClickListener {
                subtitle.text = "تم إرسال طلب المشوار. بانتظار السائق..."
                request.isEnabled = false
            }
        }

        root.addView(title, LinearLayout.LayoutParams(-1, -2))
        root.addView(subtitle, LinearLayout.LayoutParams(-1, -2))
        root.addView(from, LinearLayout.LayoutParams(-1, 150).apply {
            bottomMargin = 16
        })
        root.addView(to, LinearLayout.LayoutParams(-1, 150).apply {
            bottomMargin = 24
        })
        root.addView(request, LinearLayout.LayoutParams(-1, 60))

        setContentView(root)
    }
}
