package com.example.douanetemporelle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * النشاط الأول: نموذج تسجيل المسافر
 */
public class MainActivity extends Activity {

    // 1. عناصر الواجهة
    private EditText etNom;
    private EditText etNaissance;
    private EditText etDestination;
    private Spinner spinnerMotif;
    private Button btnInspecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2. ربط العناصر بالـ layout
        etNom = findViewById(R.id.et_nom_cf);
        etNaissance = findViewById(R.id.et_naissance_cf);
        etDestination = findViewById(R.id.et_destination_cf);
        spinnerMotif = findViewById(R.id.spinner_motif_cf);
        btnInspecter = findViewById(R.id.btn_inspecter_cf);

        // 3. ملء القائمة المنسدلة بأسباب السفر
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.motifs, android.R.layout.simple_spinner_dropdown_item);
        spinnerMotif.setAdapter(adapter);

        // 4. عند الضغط على الزر نتحقق من البيانات ثم ننتقل للنشاط الثاني
        btnInspecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inspecter();
            }
        });
    }

    private void inspecter() {
        String nom = etNom.getText().toString().trim();
        String sNaissance = etNaissance.getText().toString().trim();
        String sDestination = etDestination.getText().toString().trim();

        // 5. التحقق من أن الحقول غير فارغة
        if (nom.isEmpty()) {
            etNom.setError("Nom obligatoire");
            return;
        }
        if (sNaissance.length() != 4) {
            etNaissance.setError("Année sur 4 chiffres");
            return;
        }
        if (sDestination.length() != 4) {
            etDestination.setError("Année sur 4 chiffres");
            return;
        }

        int naissance = Integer.parseInt(sNaissance);
        int destination = Integer.parseInt(sDestination);
        String motif = spinnerMotif.getSelectedItem().toString();

        // 6. Intent صريح نحو VerificationActivity مع المفاتيح المبنية بالدالة الثابتة
        Intent intent = new Intent(MainActivity.this, VerificationActivity.class);
        intent.putExtra(Cles.cle(Cles.MATRICULE, Cles.NOM), nom);
        intent.putExtra(Cles.cle(Cles.MATRICULE, Cles.NAISSANCE), naissance);
        intent.putExtra(Cles.cle(Cles.MATRICULE, Cles.DESTINATION), destination);
        intent.putExtra(Cles.cle(Cles.MATRICULE, Cles.MOTIF), motif);
        startActivity(intent);
    }
}
