package com.example.douanetemporelle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * النشاط الثاني: ماسح المفارقة (يشبه جواز السفر)
 */
public class VerificationActivity extends Activity {

    private String nom;
    private Button btnApprouver;
    private Button btnRefuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        // 1. استرجاع البيانات المرسلة من النشاط الأول
        Intent recu = getIntent();
        nom = recu.getStringExtra(Cles.cle(Cles.MATRICULE, Cles.NOM));
        int naissance = recu.getIntExtra(Cles.cle(Cles.MATRICULE, Cles.NAISSANCE), 0);
        int destination = recu.getIntExtra(Cles.cle(Cles.MATRICULE, Cles.DESTINATION), 0);
        String motif = recu.getStringExtra(Cles.cle(Cles.MATRICULE, Cles.MOTIF));

        // 2. حساب العمر الزمني = سنة الوجهة - سنة الميلاد
        int ageTemporel = destination - naissance;

        // 3. عرض الملخص
        ((TextView) findViewById(R.id.tv_nom_cf)).setText("Nom : " + nom);
        ((TextView) findViewById(R.id.tv_age_cf)).setText("Âge temporel : " + ageTemporel + " ans");
        ((TextView) findViewById(R.id.tv_trajet_cf)).setText("Trajet : " + naissance + " → " + destination);
        ((TextView) findViewById(R.id.tv_motif_cf)).setText("Motif : " + motif);

        btnApprouver = findViewById(R.id.btn_approuver_cf);
        btnRefuser = findViewById(R.id.btn_refuser_cf);

        // 4. الشرط: "Modification du passé" والفرق عدد أولي => تعطيل زر الموافقة منذ التحميل
        if ("Modification du passé".equals(motif) && estPremier(ageTemporel)) {
            btnApprouver.setEnabled(false);
            TextView alerte = findViewById(R.id.tv_alerte_cf);
            alerte.setText("⚠ Paradoxe détecté : " + ageTemporel + " est un nombre premier !");
            alerte.setVisibility(View.VISIBLE);
        }

        // 5. زر الموافقة => الحكم true
        btnApprouver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                envoyerVerdict(true);
            }
        });

        // 6. زر الرفض => الحكم false
        btnRefuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                envoyerVerdict(false);
            }
        });
    }

    // 7. إرسال الحكم (boolean) واسم المسافر إلى النشاط الثالث
    private void envoyerVerdict(boolean approuve) {
        Intent intent = new Intent(VerificationActivity.this, ResultActivity.class);
        intent.putExtra(Cles.cle(Cles.MATRICULE, Cles.VERDICT), approuve);
        intent.putExtra(Cles.cle(Cles.MATRICULE, Cles.NOM), nom);
        startActivity(intent);
    }

    // 8. دالة فحص العدد الأولي
    private boolean estPremier(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; (long) i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
