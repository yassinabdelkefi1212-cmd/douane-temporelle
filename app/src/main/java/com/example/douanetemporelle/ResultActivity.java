package com.example.douanetemporelle;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * النشاط الثالث: الحكم النهائي
 */
public class ResultActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // 1. استرجاع الحكم واسم المسافر
        Intent recu = getIntent();
        boolean approuve = recu.getBooleanExtra(Cles.cle(Cles.MATRICULE, Cles.VERDICT), false);
        String nom = recu.getStringExtra(Cles.cle(Cles.MATRICULE, Cles.NOM));

        LinearLayout fond = findViewById(R.id.layout_fond_cf);
        TextView message = findViewById(R.id.tv_message_cf);
        Button btnNouveau = findViewById(R.id.btn_nouveau_cf);

        // 2. تغيير لون الخلفية والرسالة حسب الحكم (أخضر / أحمر)
        if (approuve) {
            fond.setBackgroundColor(Color.parseColor("#2E7D32"));
            message.setText("Bon voyage " + nom + " !");
        } else {
            fond.setBackgroundColor(Color.parseColor("#C62828"));
            message.setText(nom + " est en état d'arrestation temporelle");
        }

        // 3. العودة إلى MainActivity مع إغلاق كل الأنشطة السابقة
        btnNouveau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
}
