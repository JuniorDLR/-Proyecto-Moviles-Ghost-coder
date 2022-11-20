package ni.edu.uca.msclases.Horarios

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import ni.edu.uca.msclases.R
import ni.edu.uca.msclases.databinding.ActivityMainBinding
import java.util.*

class ActivityMainHorario : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val btn = findViewById<Button>(R.id.btn_Notificar)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        createNotificationChannel()

        btn.setOnClickListener {
            scheduleNotification()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun scheduleNotification() {
        val intent = Intent(applicationContext, Notificar::class.java)
        val title = findViewById<EditText>(R.id.Tituloet).toString()
        val mesaje = findViewById<EditText>(R.id.messageet).toString()
        intent.putExtra(titleExtra, title)
        intent.putExtra(messagueExtra, mesaje)

        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            notificacionID,
            intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = getTime()
        alarmManager.setAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            pendingIntent
        )
        showAlert(time, title, mesaje)
    }

    private fun showAlert(time: Long, title: String, mesaje: String) {
        val date = Date(time)
        val dateFormat = android.text.format.DateFormat.getLongDateFormat(applicationContext)
        val timeFormat = android.text.format.DateFormat.getTimeFormat(applicationContext)

        AlertDialog.Builder(this)
            .setTitle("Notificacion SH")
            .setMessage(
                "Titulo" + title +
                        "\nMensaje" + mesaje +
                        "\nAT"+dateFormat.format(date)+""+timeFormat.format(date))
            .setPositiveButton("okay"){_,_->}.show()

    }


    @SuppressLint("CutPasteId")
    @RequiresApi(Build.VERSION_CODES.N)
    private fun getTime(): Long {
        val minute = findViewById<TimePicker>(R.id.TimePicker).minute
        val hora = findViewById<TimePicker>(R.id.TimePicker).hour
        val dia = findViewById<DatePicker>(R.id.DatePicker).dayOfMonth
        val mes = findViewById<DatePicker>(R.id.DatePicker).month
        val anio = findViewById<DatePicker>(R.id.DatePicker).year

        val calendar = Calendar.getInstance()
        calendar.set(anio, dia, mes, hora, minute)
        return calendar.timeInMillis
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val name = "Notif Chanel"
        val desc = "A Description of the Channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelsID, name, importance)
        channel.description = desc
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)


    }
}