package br.rs.inicia_conversa_whats

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editPhone = findViewById<TextInputEditText>(R.id.editPhone)
        val editMessage = findViewById<TextInputEditText>(R.id.editMessage)
        val btnStartChat = findViewById<Button>(R.id.btnStartChat)

        btnStartChat.setOnClickListener {
            val phone = editPhone.text.toString().trim()
            val message = editMessage.text.toString().trim()

            if (phone.isNotEmpty()) {
                val cleanPhone = phone.replace(Regex("[^0-9]"), "")

                val url = if (message.isNotEmpty()) {
                    "https://wa.me/55$cleanPhone?text=${Uri.encode(message)}"
                } else {
                    "https://wa.me/$cleanPhone"
                }

                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)

                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
            } else {
                editPhone.error = "Digite um número de telefone válido"
            }
        }
    }
}