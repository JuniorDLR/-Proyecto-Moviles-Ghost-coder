package ni.edu.uca.msclases.Horarios

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.findNavController
import ni.edu.uca.msclases.R
import ni.edu.uca.msclases.databinding.FragmentHorarioBinding
import java.util.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Horario : Fragment() {
    private var _binding: FragmentHorarioBinding?=null
    private val binding :FragmentHorarioBinding
    get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View=
        FragmentHorarioBinding
            .inflate(inflater,container,false)
            .also { _binding=it }
            .root


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
    }



    @RequiresApi(Build.VERSION_CODES.M)
    private fun setupListener()= with(binding){
       val ActivityMainHorario=(requireActivity()as AppCompatActivity)
       btnNotificar.setOnClickListener {
           scheduleNotification()
       }
   }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun scheduleNotification()= with(binding) {
        val intent = Intent(context, Notificar::class.java)
        val title = binding.Tituloet.text.toString()

        val mesaje = binding.messageet.text.toString()
        intent.putExtra(titleExtra, title)
        intent.putExtra(messagueExtra, mesaje)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            notificacionID,
            intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = requireActivity().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            getTime()
        } else {
            TODO("VERSION.SDK_INT < N")
        }
        alarmManager.setAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            pendingIntent
        )
        showAlert(time, title, mesaje)
    }

    private fun showAlert(time: Long, title: String, mesaje: String) {
        val date = Date(time)
        val dateFormat = android.text.format.DateFormat.getLongDateFormat(context)
        val timeFormat = android.text.format.DateFormat.getTimeFormat(context)

        AlertDialog.Builder(context)
            .setTitle("Notificacion SH")
            .setMessage(
                "Titulo" + title +
                        "\nMensaje" + mesaje +
                        "\nAT"+dateFormat.format(date)+""+timeFormat.format(date))
            .setPositiveButton("okay"){_,_->}.show()

    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun getTime(): Long = with(binding) {
        val minute =binding.TimePicker.minute
        val hora = binding.TimePicker.hour
        val dia = binding.DatePicker.dayOfMonth
        val mes = binding.DatePicker.month
        val anio = binding.DatePicker.year

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
        val manager = requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)


    }


}