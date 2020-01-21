package com.mobile.deny.restoran;

import android.app.Activity;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener{

    Button btnPros;

    TextView txtHrgSoto,txtHrgNasgor,txtHrgMigor,txtHrgMibus,txtHrgPclayam,txtHrgpcllele
            ,txtHrgPokat,txtHrgJeruk,txtHrgMangga,txtHrgTomat,txtHrgWortel,txtHrgSirsak,txtRinci;
    EditText editJmlSoto,editJmlNasgor,editJmlMigor,editJmlMibus,editJmlPclayam,editJmlpcllele
            ,editJmlPokat,editJmlJeruk,editJmlMangga,editJmlTomat,editJmlWortel,editJmlSirsak,editNoMeja;
    CheckBox CbSoto,CbNasgor,CbMigor,CbMibus,CbPclayam,CbPcllele,CbPokat,CbJeruk,CbMangga,CbTomat,CbWortel,CbSirsak;

    RadioGroup RgOrang;
    RadioButton rbSelect;

    Spinner spinJenis;
    String[] Jenisbyr={"Cash","Master Card","Delivery Order"};

    String pilMakanan = "" , pilMinuman = "",selected;

    Double totSoto,totNasgor,totMigor,totMirebus,totPclAyam,totPclLele
            ,totPokat,totJeruk,totMangga,totWortel,totTomat,totSirsak
            ,totHargaMkn,totHargaMnm,jml,disk,pjk,totBayar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totSoto = totMigor = totNasgor = totJeruk = totMirebus = totPclAyam = totPclLele = totPokat = totMangga = totWortel = totSirsak = totTomat = 0.0;

        txtHrgSoto=(TextView)findViewById(R.id.txtSoto);
        txtHrgNasgor=(TextView)findViewById(R.id.txtNasgor);
        txtHrgMigor=(TextView)findViewById(R.id.txtMigor);
        txtHrgMibus=(TextView)findViewById(R.id.txtMirebus);
        txtHrgPclayam=(TextView)findViewById(R.id.txtPclAyam);
        txtHrgpcllele=(TextView)findViewById(R.id.txtPclLele);
        txtHrgPokat=(TextView)findViewById(R.id.txtAlpukat);
        txtHrgJeruk=(TextView)findViewById(R.id.txtJeruk);
        txtHrgMangga=(TextView)findViewById(R.id.txtMangga);
        txtHrgTomat=(TextView)findViewById(R.id.txtTomat);
        txtHrgWortel=(TextView)findViewById(R.id.txtWortel);
        txtHrgSirsak=(TextView)findViewById(R.id.txtSirsak);
        txtRinci=(TextView)findViewById(R.id.txtDetail);

        editJmlSoto=(EditText)findViewById(R.id.editSoto);
        //editJmlSoto.setVisibility(View.INVISIBLE);
        editJmlNasgor=(EditText)findViewById(R.id.editNasgor);
        //editJmlNasgor.setVisibility(View.INVISIBLE);
        editJmlMigor=(EditText)findViewById(R.id.editMigor);
        //editJmlMigor.setVisibility(View.INVISIBLE);
        editJmlMibus=(EditText)findViewById(R.id.editMirebus);
        //editJmlMibus.setVisibility(View.INVISIBLE);
        editJmlPclayam=(EditText)findViewById(R.id.editPclAyam);
        //editJmlPclayam.setVisibility(View.INVISIBLE);
        editJmlpcllele=(EditText)findViewById(R.id.editPclLele);
        //editJmlpcllele.setVisibility(View.INVISIBLE);
        editJmlPokat=(EditText)findViewById(R.id.editAlpukat);
        //editJmlPokat.setVisibility(View.INVISIBLE);
        editJmlJeruk=(EditText)findViewById(R.id.editJeruk);
        //editJmlJeruk.setVisibility(View.INVISIBLE);
        editJmlMangga=(EditText)findViewById(R.id.editMangga);
        //editJmlMangga.setVisibility(View.INVISIBLE);
        editJmlTomat=(EditText)findViewById(R.id.editTomat);
        //editJmlTomat.setVisibility(View.INVISIBLE);
        editJmlWortel=(EditText)findViewById(R.id.editWortel);
        //editJmlWortel.setVisibility(View.INVISIBLE);
        editJmlSirsak=(EditText)findViewById(R.id.editSirsak);
        //editJmlSirsak.setVisibility(View.INVISIBLE);
        editNoMeja=(EditText)findViewById(R.id.editNo);

        CbSoto=(CheckBox)findViewById(R.id.cbSoto);
        CbNasgor=(CheckBox)findViewById(R.id.cbNasgor);
        CbMigor=(CheckBox)findViewById(R.id.cbMigor);
        CbMibus=(CheckBox) findViewById(R.id.cbMirebus);
        CbPclayam=(CheckBox)findViewById(R.id.cbPclAyam);
        CbPcllele=(CheckBox)findViewById(R.id.cbPclLele);
        CbPokat=(CheckBox)findViewById(R.id.cbAlpukat);
        CbJeruk=(CheckBox)findViewById(R.id.cbJeruk);
        CbMangga=(CheckBox)findViewById(R.id.cbMangga);
        CbTomat=(CheckBox)findViewById(R.id.cbTomat);
        CbWortel=(CheckBox)findViewById(R.id.cbWortel);
        CbSirsak=(CheckBox)findViewById(R.id.cbSirsak);

        RgOrang=(RadioGroup)findViewById(R.id.rgOrang);
        spinJenis=(Spinner)findViewById(R.id.SpinnerByr);
        btnPros=(Button)findViewById(R.id.btnProses);

        spinJenis.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        ArrayAdapter JenisAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,Jenisbyr);
        JenisAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinJenis.setAdapter(JenisAdapter);
        selected=spinJenis.getSelectedItem().toString();
        btnPros.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                rbSelect=(RadioButton)findViewById(RgOrang.getCheckedRadioButtonId());
                ProsesData();
                detail();
            }
        });
    }

    public void ProsesData(){
        ceklis();
        totHargaMkn = totSoto+totPclAyam+totPclLele+ totMirebus+totMigor+totNasgor;
        totHargaMnm = totPokat+totWortel+totTomat+totSirsak+totMangga+totJeruk;
        pjk = (totHargaMkn+totHargaMnm)*0.05;
        jml = totHargaMkn+totHargaMnm;
        if(jml>=100000){
            disk = jml*0.25;
        }else {
            disk = 0.0;
        }

        totBayar = jml+pjk-disk;
    }

    public void ceklis(){
        if(CbSoto.isChecked()){
            pilMakanan = pilMakanan + "\n" + CbSoto.getText().toString() + " = " + editJmlSoto.getText().toString();
            //editJmlSoto.setVisibility(View.VISIBLE);
            totSoto = Integer.parseInt(editJmlSoto.getText().toString())*Double.parseDouble(txtHrgSoto.getText().toString());
        }
        if(CbPclayam.isChecked()){
            pilMakanan = pilMakanan + "\n" + CbPclayam.getText().toString() + " = " + editJmlPclayam.getText().toString();
            totPclAyam = Double.parseDouble(txtHrgPclayam.getText().toString())*Integer.parseInt(editJmlPclayam.getText().toString());
        }
        if (CbPcllele.isChecked()){
            pilMakanan = pilMakanan + "\n" + CbPcllele.getText().toString() + " = " + editJmlpcllele.getText().toString();
            totPclLele = Double.parseDouble(txtHrgpcllele.getText().toString())*Integer.parseInt(editJmlpcllele.getText().toString());
        }
        if (CbMigor.isChecked()){
            pilMakanan = pilMakanan + "\n" + CbMigor.getText().toString() + " = " + editJmlMigor.getText().toString();
            totMigor = Double.parseDouble(txtHrgMigor.getText().toString())*Integer.parseInt(editJmlMigor.getText().toString());
        }
        if (CbMibus.isChecked()){
            pilMakanan = pilMakanan + "\n" + CbMibus.getText().toString()+ " = " + editJmlMibus.getText().toString();
            totMirebus = Double.parseDouble(txtHrgMibus.getText().toString())*Integer.parseInt(editJmlMibus.getText().toString());
        }
        if(CbNasgor.isChecked()){
            pilMakanan = pilMakanan + "\n" + CbNasgor.getText().toString()+ " = " + editJmlNasgor.getText().toString();
            totNasgor = Double.parseDouble(txtHrgNasgor.getText().toString())*Integer.parseInt(editJmlNasgor.getText().toString());
        }
        if (CbPokat.isChecked()){
            pilMinuman = pilMinuman + "\n" + CbPokat.getText().toString() + " = " + editJmlPokat.getText().toString();
            totPokat = Integer.parseInt(editJmlPokat.getText().toString())*Double.parseDouble(txtHrgPokat.getText().toString());
        }
        if (CbJeruk.isChecked()){
            pilMinuman = pilMinuman + "\n" + CbJeruk.getText().toString()+ " = " + editJmlJeruk.getText().toString();
            totJeruk = Double.parseDouble(txtHrgJeruk.getText().toString())*Integer.parseInt(editJmlJeruk.getText().toString());
        }
        if (CbMangga.isChecked()){
            pilMinuman = pilMinuman + "\n" + CbMangga.getText().toString()+ " = " + editJmlMangga.getText().toString();
            totMangga = Double.parseDouble(txtHrgMangga.getText().toString())*Integer.parseInt(editJmlMangga.getText().toString());
        }
        if (CbTomat.isChecked()){
            pilMinuman = pilMinuman + "\n" + CbTomat.getText().toString()+ " = " + editJmlTomat.getText().toString();
            totTomat = Double.parseDouble(txtHrgTomat.getText().toString())*Integer.parseInt(editJmlTomat.getText().toString());
        }
        if (CbSirsak.isChecked()){
            pilMinuman = pilMinuman + "\n" + CbSirsak.getText().toString()+ " = " + editJmlSirsak.getText().toString();
            totSirsak = Double.parseDouble(txtHrgSirsak.getText().toString())*Integer.parseInt(editJmlSirsak.getText().toString());
        }
        if (CbWortel.isChecked()){
            pilMinuman = pilMinuman + "\n" + CbWortel.getText().toString()+ " = " + editJmlWortel.getText().toString();
            totWortel = Double.parseDouble(txtHrgWortel.getText().toString())*Integer.parseInt(editJmlWortel.getText().toString());
        }
    }

    public void detail(){
        txtRinci.setText("\nNo Meja : " + editNoMeja.getText().toString() +
                "\nJumlah Orang : " + rbSelect.getText().toString() +
                "\n\nMakanan : " + "\t\t" + pilMakanan + "\n\nMinuman : " + "\t\t"+ pilMinuman +
                "\n\nTotal : " + "Rp."+ jml + "\nPajak : " + "Rp."+ pjk + "\nDiskon : " + "Rp." + disk +
                "\nTotal bayar : " + "Rp." + totBayar + "\nJenis Pembayaran : " + selected + "\n");
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
