package com.example.draw_function;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //控件
    EditText editText;
    CustomView customView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.Function_Input);
        final TextView txtFunction=(TextView)findViewById(R.id.Function_Input);
        final CustomView customView=(CustomView)findViewById(R.id.Draw_Function);
        Button buttonPlot=(Button)findViewById(R.id.Button);
        customView.setStrFunction("");
        customView.invalidate();
        buttonPlot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(customView!=null){
                    String strFunction=txtFunction.getText().toString();
                    Toast.makeText(MainActivity.this,strFunction,Toast.LENGTH_LONG).show();
                    customView.setStrFunction(strFunction);
                    customView.invalidate();
                }
            }
        });
    }



}
