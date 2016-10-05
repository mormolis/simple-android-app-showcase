package gr.glab.ypologismosmoriondiakrotima;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import  android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.TextView;
import java.lang.Float;
import  android.util.Log;



public class GelAnth extends AppCompatActivity {
    public final static String PEDIA = "gr.glab.ypologismosmoriondiakrotima.PEDIA";
    public final static String MORIA = "gr.glab.ypologismosmoriondiakrotima.MORIA";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gel_anth);
        setTitle("Ανθρωπιστικών Σπουδών");
    }

    public void calcGrades(View view) {

        EditText vathmos = (EditText) findViewById(R.id.vathmosEkthesi);

        String vathmos1 = vathmos.getText().toString();

        vathmos = (EditText) findViewById(R.id.vathmosArxaia);

        String vathmos2 = vathmos.getText().toString();

        vathmos = (EditText) findViewById(R.id.vathmosIstoria);

        String vathmos3 = vathmos.getText().toString();

        CheckBox vE1 = (CheckBox) findViewById(R.id.checkBox); //latinika (VathmosEpilogis1)
        CheckBox vE2 = (CheckBox) findViewById(R.id.checkBox2); //biologia
        CheckBox vE3 = (CheckBox) findViewById(R.id.checkBox3); //mathimatika

        TextView warnMes = (TextView) findViewById(R.id.warningMessage);
        warnMes.setText("");
        float v1, v2, v3;

        if(vathmos1.equals("")){

            v1 = 0;

        } else{  v1 = returnFloat(vathmos1);}
        if(vathmos2.equals("")){
            v2=0;
        } else { v2 = returnFloat(vathmos2);}
        if(vathmos3.equals("")){
            v3=0;
        }else{  v3 = returnFloat(vathmos3);}


        int count = 0;

        if (vE1.isChecked()) {
            count++;
        }
        if (vE2.isChecked()) {
            count++;
        }
        if (vE3.isChecked()) {
            count++;
        }

        if (count > 2 || count == 0) {

            warnMes.append("Πρέπει να επιλέξεις 1 ή το πολύ 2 μαθήματα!");

        } else {

            if (v1 > 20 || v2 > 20 || v3 > 20) {
                warnMes.append("Οι βαθμοί πρέπει να είναι μικρότεροι ίσοι του 20");
            } else {
                boolean[] pedia = {false, false, false};
                float[] vathmoiEpil = {0, 0, 0};

                float sum;
                float temp;
                boolean olaKala = true;

                if (vE1.isChecked()) {
                    pedia[0] = true;
                    vathmos = (EditText) findViewById(R.id.vathmosLatinika);

                    String vathmos4 = vathmos.getText().toString();
                    if (vathmos4.equals("")) {
                        temp = 0;
                    } else{
                        temp = returnFloat(vathmos4);
                    }
                    if (temp <= 20) {
                        sum = v1 * 200 + v2 * 330 + v3 * 270;
                        vathmoiEpil[0] = sum + temp * 200;
                    } else {
                        olaKala = false;
                    }
                }
                if (vE2.isChecked()) {
                    pedia[1] = true;
                    vathmos = (EditText) findViewById(R.id.vathmosViologia);

                    String vathmos5 = vathmos.getText().toString();
                    if(vathmos5.equals("")){
                        temp = 0;
                    }else{
                        temp = returnFloat(vathmos5); }
                    if (temp <= 20) {
                        sum = v1 * 240 + v2 * 200 + v3 * 200;
                        vathmoiEpil[1] = sum + temp * 290;
                    } else {
                        olaKala = false;
                    }
                }
                if (vE3.isChecked()) {
                    pedia[2] = true;
                    vathmos = (EditText) findViewById(R.id.vathmosMathimatika);
                    String vathmos6 = vathmos.getText().toString();
                    if(vathmos6.equals("")){
                        temp=0;
                    }else{
                        temp = returnFloat(vathmos6);}
                    if (temp <= 20) {
                        sum = v1 * 330 + v2 * 200 + v3 * 200;
                        vathmoiEpil[2] = sum + temp * 270;
                    } else {
                        olaKala = false;
                    }
                }

                if (olaKala) {
                    Intent disGelAnth = new Intent(this, DisGelAnth.class);
                    disGelAnth.putExtra(PEDIA, pedia);
                    disGelAnth.putExtra(MORIA, vathmoiEpil);
                    startActivity(disGelAnth);

                } else {
                    warnMes.setText("Οι βαθμοί πρέπει να είναι μικρότεροι ίσοι του 20");
                }

            }

        }


    }


    public static float returnFloat(String textValue) {
        Float v = new Float("10.00f");
        float v1;
        if(textValue.equals("")){
            v1 = 0;
        } else{
            v1 = v.parseFloat(textValue) * 10; }
        float temp = v1 - (int) v1;
        if (temp > 0.5f) {
            v1 = v1 + 1;
        }
        int vv1 = (int) v1;
        v1 = vv1 / 10f;
        return v1;

    }


}
