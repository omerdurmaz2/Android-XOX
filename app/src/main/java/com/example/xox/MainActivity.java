package com.example.xox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SyncAdapterType;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadPoolExecutor;

public class MainActivity extends AppCompatActivity {

    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, btnReset, btnRestart;
    TextView xSkor, oSkor, txtX, txtO;
    public static String taraf = "";
    public static String makine = "";
    public static String mod = "";
    public String[][] dizi = new String[3][3];
    public Boolean bitti = false;
    public int sayac = 0;
    public int xScore = 0, oScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tanimla();
        Intent intent = new Intent(this, tarafsec.class);
        startActivity(intent);
        tiklamalar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        tarafYak();
    }

    private void tanimla() {
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        b5 = (Button) findViewById(R.id.b5);
        b6 = (Button) findViewById(R.id.b6);
        b7 = (Button) findViewById(R.id.b7);
        b8 = (Button) findViewById(R.id.b8);
        b9 = (Button) findViewById(R.id.b9);
        xSkor = (TextView) findViewById(R.id.xSkor);
        oSkor = (TextView) findViewById(R.id.oSkor);
        txtX = (TextView) findViewById(R.id.txtX);
        txtO = (TextView) findViewById(R.id.txtO);
        btnReset = (Button) findViewById(R.id.btnReset);
        btnRestart = (Button) findViewById(R.id.btnRestart);
    }


    public String kare(int n) {
        switch (n) {
            case 1:
                return dizi[0][0];
            case 2:
                return dizi[0][1];
            case 3:
                return dizi[0][2];
            case 4:
                return dizi[1][0];
            case 5:
                return dizi[1][1];
            case 6:
                return dizi[1][2];
            case 7:
                return dizi[2][0];
            case 8:
                return dizi[2][1];
            case 9:
                return dizi[2][2];
        }
        return "";
    }

    private int karsiHamle() {
        //Yatay Sorgu
        if (kare(1) == kare(2) && (kare(1) != null) && (kare(3) == null)) return 3; //1=2 >3
        else if (kare(1) == kare(3) && (kare(1) != null) && (kare(2) == null)) return 2;//1=3 >2
        else if (kare(2) == kare(3) && (kare(2) != null) && (kare(1) == null)) return 1;//2=3 >1
        else if (kare(4) == kare(5) && (kare(4) != null) && (kare(6) == null)) return 6;//4=5 >6
        else if (kare(4) == kare(6) && (kare(4) != null) && (kare(5) == null)) return 5;//4=6 >5
        else if (kare(5) == kare(6) && (kare(5) != null) && (kare(4) == null)) return 4;//5=6 >4
        else if (kare(7) == kare(8) && (kare(7) != null) && (kare(9) == null)) return 9;//7=8 >9
        else if (kare(7) == kare(9) && (kare(7) != null) && (kare(8) == null)) return 8;//7=9 >8
        else if (kare(8) == kare(9) && (kare(8) != null) && (kare(7) == null)) return 7;//8=9 >7
            //Dikey Sorgu
        else if (kare(1) == kare(4) && (kare(1) != null) && (kare(7) == null)) return 7;//1=4 >7
        else if (kare(1) == kare(7) && (kare(1) != null) && (kare(4) == null)) return 4;//1=7 >4
        else if (kare(4) == kare(7) && (kare(4) != null) && (kare(1) == null)) return 1;//4=7 >1
        else if (kare(2) == kare(5) && (kare(2) != null) && (kare(8) == null)) return 8;//2=5 >8
        else if (kare(2) == kare(8) && (kare(2) != null) && (kare(5) == null)) return 5;//2=8 >5
        else if (kare(5) == kare(8) && (kare(5) != null) && (kare(2) == null)) return 2;//5=8 >2
        else if (kare(3) == kare(6) && (kare(3) != null) && (kare(9) == null)) return 9;//3=6 >9
        else if (kare(3) == kare(9) && (kare(3) != null) && (kare(6) == null)) return 6;//3=9 >6
        else if (kare(6) == kare(9) && (kare(6) != null) && (kare(3) == null)) return 3;//6=9 >3
            //Çarpraz Sorgu
        else if (kare(1) == kare(5) && (kare(1) != null) && (kare(9) == null)) return 9;//1=5 >9
        else if (kare(1) == kare(9) && (kare(1) != null) && (kare(5) == null)) return 5;//1=9 >5
        else if (kare(5) == kare(9) && (kare(5) != null) && (kare(1) == null)) return 1;//5=9 >1
        else if (kare(3) == kare(5) && (kare(3) != null) && (kare(7) == null)) return 7;//3=5 >7
        else if (kare(3) == kare(7) && (kare(3) != null) && (kare(5) == null)) return 5;//3=7 >5
        else if (kare(5) == kare(7) && (kare(5) != null) && (kare(3) == null)) return 3;//5=7 >3

        else {
            return -1;
        } // Hiçbiri Olmuyorsa
    }

    public int bitirmeHamlesi() {
        //Yatay Sorgu
        if (kare(1) == kare(2) && (kare(1) != null) && (kare(1) == makine) && (kare(3) == null))
            return 3; //1=2 >3
        else if (kare(1) == kare(3) && (kare(1) != null) && (kare(1) == makine) && (kare(2) == null))
            return 2;//1=3 >2
        else if (kare(2) == kare(3) && (kare(2) != null) && (kare(2) == makine) && (kare(1) == null))
            return 1;//2=3 >1
        else if (kare(4) == kare(5) && (kare(4) != null) && (kare(4) == makine) && (kare(6) == null))
            return 6;//4=5 >6
        else if (kare(4) == kare(6) && (kare(4) != null) && (kare(4) == makine) && (kare(5) == null))
            return 5;//4=6 >5
        else if (kare(5) == kare(6) && (kare(5) != null) && (kare(5) == makine) && (kare(4) == null))
            return 4;//5=6 >4
        else if (kare(7) == kare(8) && (kare(7) != null) && (kare(7) == makine) && (kare(9) == null))
            return 9;//7=8 >9
        else if (kare(7) == kare(9) && (kare(7) != null) && (kare(7) == makine) && (kare(8) == null))
            return 8;//7=9 >8
        else if (kare(8) == kare(9) && (kare(8) != null) && (kare(8) == makine) && (kare(7) == null))
            return 7;//8=9 >7
            //Dikey Sorgu
        else if (kare(1) == kare(4) && (kare(1) != null) && (kare(1) == makine) && (kare(7) == null))
            return 7;//1=4 >7
        else if (kare(1) == kare(7) && (kare(1) != null) && (kare(1) == makine) && (kare(4) == null))
            return 4;//1=7 >4
        else if (kare(4) == kare(7) && (kare(4) != null) && (kare(4) == makine) && (kare(1) == null))
            return 1;//4=7 >1
        else if (kare(2) == kare(5) && (kare(2) != null) && (kare(2) == makine) && (kare(8) == null))
            return 8;//2=5 >8
        else if (kare(2) == kare(8) && (kare(2) != null) && (kare(2) == makine) && (kare(5) == null))
            return 5;//2=8 >5
        else if (kare(5) == kare(8) && (kare(5) != null) && (kare(5) == makine) && (kare(2) == null))
            return 2;//5=8 >2
        else if (kare(3) == kare(6) && (kare(3) != null) && (kare(3) == makine) && (kare(9) == null))
            return 9;//3=6 >9
        else if (kare(3) == kare(9) && (kare(3) != null) && (kare(3) == makine) && (kare(6) == null))
            return 6;//3=9 >6
        else if (kare(6) == kare(9) && (kare(6) != null) && (kare(6) == makine) && (kare(3) == null))
            return 3;//6=9 >3
            //Çarpraz Sorgu
        else if (kare(1) == kare(5) && (kare(1) != null) && (kare(1) == makine) && (kare(9) == null))
            return 9;//1=5 >9
        else if (kare(1) == kare(9) && (kare(1) != null) && (kare(1) == makine) && (kare(5) == null))
            return 5;//1=9 >5
        else if (kare(5) == kare(9) && (kare(5) != null) && (kare(5) == makine) && (kare(1) == null))
            return 1;//5=9 >1
        else if (kare(3) == kare(5) && (kare(3) != null) && (kare(3) == makine) && (kare(7) == null))
            return 7;//3=5 >7
        else if (kare(3) == kare(7) && (kare(3) != null) && (kare(3) == makine) && (kare(5) == null))
            return 5;//3=7 >5
        else if (kare(5) == kare(7) && (kare(5) != null) && (kare(5) == makine) && (kare(3) == null))
            return 3;//5=7 >3

        else {
            return -1;
        } // Hiçbiri Olmuyorsa

    }

    private void yapayZeka() {
        if (bitti == false) {
            int cevap = -1;
            cevap = bitirmeHamlesi();

            for (int i = 0; i < 1; i++) {
                if (cevap == -1) cevap = karsiHamle();
                if (cevap == -1) {
                    if (kare(5) == null) {
                        cevap = 5;
                        break;
                    }

                    if (kare(1) == null) {
                        cevap = 1;
                        break;
                    } else if (kare(1) == makine && kare(9) == null) {
                        cevap = 9;
                        break;
                    } else if (kare(1) == makine && kare(9) == makine && kare(3) == null) {
                        cevap = 3;
                        break;
                    } else if (kare(1) == makine && kare(9) == makine && kare(7) == null) {
                        cevap = 7;
                        break;
                    }
                    if (kare(3) == null) {
                        cevap = 3;
                        break;
                    } else if (kare(3) == makine && kare(7) == null) {
                        cevap = 7;
                        break;
                    } else if (kare(3) == makine && kare(7) == makine && kare(9) == null) {
                        cevap = 9;
                        break;
                    } else if (kare(1) == makine && kare(9) == makine && kare(7) == null) {
                        cevap = 1;
                        break;
                    }

                    if (kare(1) == null) cevap = 1;
                    if (kare(2) == null) cevap = 2;
                    if (kare(3) == null) cevap = 3;
                    if (kare(4) == null) cevap = 4;
                    if (kare(5) == null) cevap = 5;
                    if (kare(6) == null) cevap = 6;
                    if (kare(7) == null) cevap = 7;
                    if (kare(8) == null) cevap = 8;
                    if (kare(9) == null) cevap = 9;
                }

            }

            switch (cevap) {
                case 1:
                    b1.performClick();
                    break;
                case 2:
                    b2.performClick();
                    break;
                case 3:
                    b3.performClick();
                    break;
                case 4:
                    b4.performClick();
                    break;
                case 5:
                    b5.performClick();
                    break;
                case 6:
                    b6.performClick();
                    break;
                case 7:
                    b7.performClick();
                    break;
                case 8:
                    b8.performClick();
                    break;
                case 9:
                    b9.performClick();
                    break;
            }
        }


    }


    private void kontrol() {


        sayac++;
        if (dizi[0][0] == dizi[0][1] && dizi[0][0] == dizi[0][2] && (dizi[0][0] == "o" || dizi[0][0] == "x")) {
            bitir(1);
        } else if (dizi[1][0] == dizi[1][1] && dizi[1][0] == dizi[1][2] && (dizi[1][0] == "o" || dizi[1][0] == "x")) {
            bitir(2);
        } else if (dizi[2][0] == dizi[2][1] && dizi[2][0] == dizi[2][2] && (dizi[2][0] == "o" || dizi[2][0] == "x")) {
            bitir(3);
        } else if (dizi[0][0] == dizi[1][0] && dizi[0][0] == dizi[2][0] && (dizi[0][0] == "o" || dizi[0][0] == "x")) {
            bitir(4);
        } else if (dizi[0][1] == dizi[1][1] && dizi[0][1] == dizi[2][1] && (dizi[0][1] == "o" || dizi[0][1] == "x")) {
            bitir(5);
        } else if (dizi[0][2] == dizi[1][2] && dizi[0][2] == dizi[2][2] && (dizi[0][2] == "o" || dizi[0][2] == "x")) {
            bitir(6);
        } else if (dizi[0][0] == dizi[1][1] && dizi[0][0] == dizi[2][2] && (dizi[0][0] == "o" || dizi[0][0] == "x")) {
            bitir(7);
        } else if (dizi[2][0] == dizi[1][1] && dizi[2][0] == dizi[0][2] && (dizi[2][0] == "o" || dizi[2][0] == "x")) {
            bitir(8);
        } else if (sayac == 9) {
            mesajVer("Berabere");
            sifirla();
        } else if (sayac == 1 || sayac == 3 || sayac == 5 || sayac == 7) // Eğer sıra bilgisayardaysa yapılacaklar
        {
            degis();

            if (mod == "cp") {
                final Handler handler = new Handler();
                final Timer timer;
                timer = new Timer();

                //
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {

                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                                yapayZeka();
                                timer.cancel();

                            }
                        });
                    }
                };
                timer.schedule(timerTask, 700);
            }
        } else {
            degis();
        }
        engelKaldir();


    }

    private void mesajVer(String mesaj) {
        Toast.makeText(getApplicationContext(), mesaj, Toast.LENGTH_SHORT).show();
    }


    private void engelle() {
        b1.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);
        b4.setEnabled(false);
        b5.setEnabled(false);
        b6.setEnabled(false);
        b7.setEnabled(false);
        b8.setEnabled(false);
        b9.setEnabled(false);
    }

    public void engelKaldir() {
        if (dizi[0][0] == null) b1.setEnabled(true);
        if (dizi[0][1] == null) b2.setEnabled(true);
        if (dizi[0][2] == null) b3.setEnabled(true);
        if (dizi[1][0] == null) b4.setEnabled(true);
        if (dizi[1][1] == null) b5.setEnabled(true);
        if (dizi[1][2] == null) b6.setEnabled(true);
        if (dizi[2][0] == null) b7.setEnabled(true);
        if (dizi[2][1] == null) b8.setEnabled(true);
        if (dizi[2][2] == null) b9.setEnabled(true);
    }

    private void skorArttir() {
        if (taraf == "x") {
            xScore++;
            xSkor.setText(String.valueOf(xScore));
        } else {
            oScore++;
            oSkor.setText(String.valueOf(oScore));
        }
    }

    private void sifirla() {
        b1.setBackgroundColor(Color.BLACK);
        b2.setBackgroundColor(Color.BLACK);
        b3.setBackgroundColor(Color.BLACK);
        b4.setBackgroundColor(Color.BLACK);
        b5.setBackgroundColor(Color.BLACK);
        b6.setBackgroundColor(Color.BLACK);
        b7.setBackgroundColor(Color.BLACK);
        b8.setBackgroundColor(Color.BLACK);
        b9.setBackgroundColor(Color.BLACK);


        b1.setText("");
        b2.setText("");
        b3.setText("");
        b4.setText("");
        b5.setText("");
        b6.setText("");
        b7.setText("");
        b8.setText("");
        b9.setText("");


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                dizi[i][j] = null;
            }
        }

        engelKaldir();

        sayac = 0;
        bitti = false;

    }

    private void degis() {

        if (taraf == "x") taraf = "o";
        else {
            taraf = "x";
            Log.i("res", "" + makine + "-" + taraf);
        }
        if (!bitti) {

            Log.i("res", "geldi" );

            tarafYak();

        }
        Log.i("res", "bitti: "+bitti );

    }

    private void bitir(int yol) {
        bitti = true;
        switch (yol) {
            case 1:
                b1.setBackgroundColor(Color.parseColor("#FF8B4513"));
                b2.setBackgroundColor(Color.parseColor("#FF8B4513"));
                b3.setBackgroundColor(Color.parseColor("#FF8B4513"));
                break;
            case 2:
                b4.setBackgroundColor(Color.parseColor("#FF8B4513"));
                b5.setBackgroundColor(Color.parseColor("#FF8B4513"));
                b6.setBackgroundColor(Color.parseColor("#FF8B4513"));
                break;
            case 3:
                b7.setBackgroundColor(Color.parseColor("#FF8B4513"));
                b8.setBackgroundColor(Color.parseColor("#FF8B4513"));
                b9.setBackgroundColor(Color.parseColor("#FF8B4513"));
                break;
            case 4:
                b1.setBackgroundColor(Color.parseColor("#FF8B4513"));
                b4.setBackgroundColor(Color.parseColor("#FF8B4513"));
                b7.setBackgroundColor(Color.parseColor("#FF8B4513"));
                break;
            case 5:
                b2.setBackgroundColor(Color.parseColor("#FF8B4513"));
                b5.setBackgroundColor(Color.parseColor("#FF8B4513"));
                b8.setBackgroundColor(Color.parseColor("#FF8B4513"));
                break;
            case 6:
                b3.setBackgroundColor(Color.parseColor("#FF8B4513"));
                b6.setBackgroundColor(Color.parseColor("#FF8B4513"));
                b9.setBackgroundColor(Color.parseColor("#FF8B4513"));
                break;
            case 7:
                b1.setBackgroundColor(Color.parseColor("#FF8B4513"));
                b5.setBackgroundColor(Color.parseColor("#FF8B4513"));
                ;
                b9.setBackgroundColor(Color.parseColor("#FF8B4513"));
                break;
            case 8:
                b3.setBackgroundColor(Color.parseColor("#FF8B4513"));
                b5.setBackgroundColor(Color.parseColor("#FF8B4513"));
                b7.setBackgroundColor(Color.parseColor("#FF8B4513"));
                break;
        }
        mesajVer(taraf.toUpperCase() + " Kazandı");
        engelle();
        skorArttir();

        final Handler handler = new Handler();
        final Timer timer;
        timer = new Timer();

        //
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        sifirla();
                        if (makine == taraf) {
                            degis();
                        }
                        timer.cancel();

                    }
                });
            }
        };
        timer.schedule(timerTask, 1000);


    }


    private void tarafYak() {
        if (taraf == "x") {
            txtX.setTextColor(Color.parseColor("#FF8B4513"));
            ;
            txtO.setTextColor(Color.WHITE);
        } else {
            txtX.setTextColor(Color.WHITE);
            txtO.setTextColor(Color.parseColor("#FF8B4513"));
            ;
        }
    }

    private void tiklamalar() {
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1.setText(taraf);
                dizi[0][0] = taraf;
                engelle();
                kontrol();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b2.setText(taraf);
                dizi[0][1] = taraf;
                engelle();
                kontrol();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b3.setText(taraf);
                dizi[0][2] = taraf;
                engelle();
                kontrol();
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b4.setText(taraf);
                dizi[1][0] = taraf;
                engelle();
                kontrol();
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b5.setText(taraf);
                dizi[1][1] = taraf;
                engelle();
                kontrol();
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b6.setText(taraf);
                dizi[1][2] = taraf;
                engelle();
                kontrol();
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b7.setText(taraf);
                dizi[2][0] = taraf;
                engelle();
                kontrol();
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b8.setText(taraf);
                dizi[2][1] = taraf;
                engelle();
                kontrol();
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b9.setText(taraf);
                dizi[2][2] = taraf;
                engelle();
                kontrol();
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sifirla();
                xScore = 0;
                oScore = 0;
                xSkor.setText("0");
                oSkor.setText("0");
            }
        });

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
    }


}
