package dioobanu.yahoo.appipk;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class MainActivity extends AppCompatActivity {

    private EditText mNamaMakul, mJumlahSKS, mNilai;
    private Button btnSimpanArray, btnTampilData, btnHitungIP;
    private TextView TotalSKS, NilaiIP;

    ArrayList<Integer> mtsks= new ArrayList<>();
    ArrayList<String> tambahArray = new ArrayList<>();
    ListView tampil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNamaMakul = findViewById(R.id.namamatakuliah);
        mJumlahSKS = findViewById(R.id.jumlahsks);
        mNilai = findViewById(R.id.nilai);

        btnSimpanArray = findViewById(R.id.button);
        btnTampilData = findViewById(R.id.button1);
        btnHitungIP = findViewById(R.id.button2);

        TotalSKS = findViewById(R.id.totalsks);
        NilaiIP = findViewById(R.id.nilaiip);

        tampil = findViewById(R.id.makul_list);

        btnSimpanArray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nilaiAngka = 0;
                final int finalNilaiAngka = nilaiAngka;
                final String makul = mNamaMakul.getText().toString();
                final String sks = mJumlahSKS.getText().toString();
                final String nilai = mNilai.getText().toString();
                final int hsks = Integer.parseInt(mJumlahSKS.getText().toString());

                //Keterangan bobot: A = 4, B = 3, C = 2, D = 1, E = 0
                /*if(nilai.equals("A") || nilai.equals("a")){nilaiAngka=4;}
                else if (nilai.equals("B") || nilai.equals("b")){nilaiAngka=3;}
                else if (nilai.equals("C") || nilai.equals("c")){nilaiAngka=2;}
                else if (nilai.equals("D") || nilai.equals("d")){nilaiAngka=1;}
                else if (nilai.equals("E") || nilai.equals("e")){nilaiAngka=0;}*/


                if (tambahArray.contains(makul+"\t"+sks+"\t"+nilai)){
                    mtsks.contains(hsks);

                    Toast.makeText(getBaseContext(), "Berhasil menyimpan ke dalam bentuk Array.", Toast.LENGTH_LONG).show();
                }
                else if ((makul.equals("") && sks.equals("") && nilai.equals("")) || makul.equals("") || sks.equals("") || nilai.equals("")){
                    Toast.makeText(getBaseContext(), "Mohon isi semua kolom inputan.", Toast.LENGTH_LONG).show();
                }
                else {
                    btnTampilData.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onClick(View v) {
                            tambahArray.add(makul+"\t   "+sks+" SKS\t   Nilai "+nilai);
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, tambahArray);
                            tampil.setAdapter(adapter);
                            btnTampilData.setEnabled(false);
                            btnHitungIP.setEnabled(true);
                        }
                    });


                    btnHitungIP.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onClick(View v) {
                            int sum=0;
                            mtsks.add(hsks);
                            //Hitung total jumlah SKS
                            for (int i=0; i<mtsks.size(); i++){
                                sum += mtsks.get(i);
                                TotalSKS.setText("Total SKS : "+sum);
                                btnHitungIP.setEnabled(false);
                                btnTampilData.setEnabled(false);
                                btnSimpanArray.setEnabled(true);
                            }


                            //Hitung : IP = ((2 x 4) + (3 x 2) + (2 x 3) ) / (2 + 3 + 2)
                            //IP = (8 + 6 + 6) / 7
                            //IP = 20 / 7
                            //IP = 2,86

                        }
                    });
                    ((EditText)findViewById(R.id.namamatakuliah)).setText("");
                    ((EditText)findViewById(R.id.jumlahsks)).setText("");
                    ((EditText)findViewById(R.id.nilai)).setText("");
                    btnHitungIP.setEnabled(false);
                    btnTampilData.setEnabled(true);
                    btnSimpanArray.setEnabled(false);
                }
            }
        });


    }



}
