package ps.jawwal.dialogpickers;



import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText timeEditText,dateEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeEditText=findViewById(R.id.editTextTime);
        timeEditText.setFocusable(false);
        timeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                MyTimePicker myTimePicker= new MyTimePicker(new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        timeEditText.setText(hourOfDay+":"+minute);
                    }
                });
                myTimePicker.show(getSupportFragmentManager(),"timePicker");
            }
        });


        dateEditText=findViewById(R.id.editTextDate);
        dateEditText.setFocusable(false);
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MyDatePicker datePicker=new MyDatePicker();
                datePicker.setMylistener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dateEditText.setText(year+"/"+month+"/"+dayOfMonth);
                    }
                });
                datePicker.show(getSupportFragmentManager(),"Datepicker");
            }
        });



        TextView  textView = findViewById(R.id.textview);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Alert");
                builder.setMessage("Hi everyone!! ");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Yes is clicked!",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"No is clicked!",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                builder.setNeutralButton("Ignor", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"Ignor is clicked!",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog =builder.create();
                alertDialog.show();
            }

        });

    }

    public static class MyDatePicker extends DialogFragment {
        private DatePickerDialog.OnDateSetListener mylistener;

        public void setMylistener(DatePickerDialog.OnDateSetListener listener){
            mylistener=listener;
        }
        MyDatePicker(DatePickerDialog.OnDateSetListener listener){
            mylistener=listener;
        }
        MyDatePicker(){
        }
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            Calendar calendar = Calendar.getInstance();
            return new DatePickerDialog(getActivity(),mylistener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        }

        @Nullable
        @Override
        public Dialog getDialog() {
            return super.getDialog();
        }

        //        @Override
//        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//            date=year+"/"+month+"/"+dayOfMonth;
////            Toast.makeText(getActivity(),year+"/"+month+"/"+dayOfMonth,Toast.LENGTH_SHORT).show();
//        }
    }


    public static class MyTimePicker extends DialogFragment {
        private TimePickerDialog.OnTimeSetListener myListener;
        MyTimePicker(TimePickerDialog.OnTimeSetListener myListener){
            this.myListener=myListener;
        }
        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            Calendar calendar = Calendar.getInstance();
            return new TimePickerDialog(getActivity(),myListener,calendar.get(Calendar.HOUR),calendar.get(Calendar.MINUTE),false );
        }
    }
}